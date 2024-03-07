package com.senacead.PI.service;

import com.senacead.PI.entity.ItemVenda;
import com.senacead.PI.entity.Venda;
import com.senacead.PI.repository.ItemVendaRepository;
import com.senacead.PI.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ItemVendaRepository itemVendaRepository; 

    @Transactional
    public boolean inserirVenda(Venda venda, List<ItemVenda> produtos, float valorTotal) {
        try {
            vendaRepository.save(venda); 
            for (ItemVenda item : produtos) {
                item.setVenda(venda); 
                itemVendaRepository.save(item);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Venda> recuperarVendas() {
        return vendaRepository.findAll();
    }

    public Venda recuperarVendaPorId(Long id) {
        return vendaRepository.findById(id).orElse(null);
    }

    public boolean editarItemVenda(ItemVenda itemVenda) {
        try {
            itemVendaRepository.save(itemVenda);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluirVenda(Long idVenda) {
        try {
            vendaRepository.deleteById(idVenda);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Venda> recuperarVendasPorCliente(String nomeCliente) {
        return vendaRepository.findByNomeCliente(nomeCliente);
    }

    public List<Venda> recuperarVendasPorIntervaloDeDatas(Date dataInicial, Date dataFinal) {
        return vendaRepository.findByDataBetween(dataInicial, dataFinal);
    }

}
