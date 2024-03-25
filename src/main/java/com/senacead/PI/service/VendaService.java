package com.senacead.PI.service;

import com.senacead.PI.entity.ItemVenda;
import com.senacead.PI.entity.Venda;
import com.senacead.PI.repository.ClienteRepository;
import com.senacead.PI.repository.ItemVendaRepository;
import com.senacead.PI.repository.ProdutoRepository;
import com.senacead.PI.repository.VendaRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    
    private ItemVendaRepository itemVendaRepository;

    public VendaService(VendaRepository vendaRepository, ClienteRepository clienteRepository, ProdutoRepository produtoRepository, ItemVendaRepository itemVendaRepository) {
        this.vendaRepository = vendaRepository;
        this.itemVendaRepository = itemVendaRepository;
    }

    public Optional<Venda> findById(int id) {
        return vendaRepository.findById(id);
    }

    public void save(Venda venda) {
        vendaRepository.save(venda);
    }

    public int getLastvendaID() {
        Venda ultimaVenda = vendaRepository.findTopByOrderByIdDesc();
        if (ultimaVenda == null) {
            return 1;
        } else {
            return ultimaVenda.getId() + 1;
        }
    }

    private float calcularValorTotalVenda(Venda venda) {
        // Lógica para calcular o valor total da venda
        float valorTotal = 0.0f;
        for (ItemVenda item : venda.getItensVenda()) {
            valorTotal += item.getValorTotalProduto();
        }
        return valorTotal;
    }

    public List<Venda> findAll() {
        return vendaRepository.findAll();
    }

    public List<Venda> findByDataBetween(String dataInicio, String dataFim) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataInicioLocal = LocalDate.parse(dataInicio, formatter);
        LocalDate dataFimLocal = LocalDate.parse(dataFim, formatter);
        return vendaRepository.findByDataVendaBetween(dataInicioLocal, dataFimLocal);
    }
//

    public List<Venda> findByClienteId(int clienteId) {
        return vendaRepository.findByClienteId(clienteId);
    }
//

    public List<Venda> findByValorTotalBetween(float valorMinimo, float valorMaximo) {
        return vendaRepository.findByValorTotalBetween(valorMinimo, valorMaximo);
    }

    @Transactional
    public void excluirVendaEItens(int vendaId) {
        Optional<Venda> optionalVenda = vendaRepository.findById(vendaId);
        if (optionalVenda.isPresent()) {
            Venda venda = optionalVenda.get();
            List<ItemVenda> itensVenda = venda.getItensVenda();
            itemVendaRepository.deleteAll(itensVenda); 
            vendaRepository.delete(venda); 
        }
    }
}
