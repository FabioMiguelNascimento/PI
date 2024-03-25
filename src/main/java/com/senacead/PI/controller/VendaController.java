package com.senacead.PI.controller;

import com.senacead.PI.entity.Cliente;
import com.senacead.PI.entity.ItemVenda;
import com.senacead.PI.entity.Venda;
import com.senacead.PI.service.ClienteService;
import com.senacead.PI.service.ItemVendaService;
import com.senacead.PI.service.ProdutoService;
import com.senacead.PI.service.VendaService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VendaController {

    private final VendaService vendaService;
    private final ProdutoService produtoService;
    private final ClienteService clienteService;
    private final ItemVendaService itemVendaService;

    public VendaController(VendaService vendaService, ProdutoService produtoService, ClienteService clienteService, ItemVendaService itemVendaService) {
        this.vendaService = vendaService;
        this.produtoService = produtoService;
        this.clienteService = clienteService;
        this.itemVendaService = itemVendaService;
    }

    @GetMapping("/venda/vendas")
    public String listarProdutos(Model model) {
        List<Venda> vendas = vendaService.findAll();
        carregarClientesNoModel(model); // Chama o método para carregar clientes no modelo
        model.addAttribute("vendas", vendas);
        return "vendas";
    }

    @GetMapping("/venda/vendas/filtrarPeriodo")
    public String filtrarPorPeriodo(
            @RequestParam("dataInicio") String dataInicio,
            @RequestParam("dataFim") String dataFim,
            Model model
    ) {
        List<Venda> vendasFiltradas = vendaService.findByDataBetween(dataInicio, dataFim);
        model.addAttribute("vendas", vendasFiltradas);
        return "vendas"; // Retorna a página de vendas com os dados filtrados
    }

//
    @GetMapping("/venda/venda/filtrarValor")
    public String filtrarPorValor(
            @RequestParam("valorMinimo") float valorMinimo,
            @RequestParam("valorMaximo") float valorMaximo,
            Model model
    ) {
        // Implemente a lógica para filtrar as vendas por valor
        List<Venda> vendasFiltradas = vendaService.findByValorTotalBetween(valorMinimo, valorMaximo);
        model.addAttribute("vendas", vendasFiltradas);
        carregarClientesNoModel(model); // Carrega os clientes no modelo
        return "vendas";
    }

    @GetMapping("/venda/venda/filtrarCliente")
    public String filtrarPorCliente(@RequestParam("clienteId") int clienteId, Model model) {
        List<Venda> vendasFiltradas = vendaService.findByClienteId(clienteId);
        model.addAttribute("vendas", vendasFiltradas);
        carregarClientesNoModel(model); // Chama o método para carregar clientes no modelo
        return "vendas";
    }

    @GetMapping("/venda/adicionarVenda")
    public String adicionarVenda(Model model) {
        List<Cliente> clientes = clienteService.findAll();
        Venda novaVenda = new Venda();
        int lastVendaId = vendaService.getLastvendaID();
        novaVenda.setId(lastVendaId);
        model.addAttribute("venda", novaVenda);
        model.addAttribute("clientes", clientes);
        return "cadastroVenda";
    }

    @GetMapping("/venda/excluirVenda")
    public String excluirVenda(@RequestParam("id") int vendaId) {
        vendaService.excluirVendaEItens(vendaId);
        return "redirect:/venda/vendas"; // Redireciona para a página de vendas após a exclusão
    }

    @GetMapping("/venda/inspecionarVenda")
    public String inspecionarVenda(@RequestParam("id") int vendaId, Model model) {
        Optional<Venda> optionalVenda = vendaService.findById(vendaId);
        if (optionalVenda.isPresent()) {
            Venda venda = optionalVenda.get();
            List<ItemVenda> itensVenda = itemVendaService.findByVendaId(vendaId);
            model.addAttribute("venda", venda);
            model.addAttribute("itensVenda", itensVenda);
            return "inspecao-venda"; // Nome da página para inspecionar a venda
        } else {
            return "redirect:/venda/vendas"; // Redireciona para a página de vendas caso a venda não seja encontrada
        }
    }

    @PostMapping("/venda/cadastrarItens")
    public String inserirVenda(@RequestParam("clienteId") int clienteId,
            @RequestParam("vendaId") int vendaId,
            @RequestParam("data") String dataStr) {
        Optional<Cliente> optionalCliente = clienteService.findById(clienteId);
        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate data = LocalDate.parse(dataStr, formatter);
            Venda novaVenda = new Venda();
            novaVenda.setId(vendaId);
            novaVenda.setDataVenda(data);
            novaVenda.setCliente(cliente);
            vendaService.save(novaVenda);
            return "redirect:/venda/cadastrarItens?vendaId=" + vendaId;
        } else {
            return "redirect:/venda/vendas";
        }
    }

    @PostMapping("/venda/adicionarItens")
    public ResponseEntity<String> adicionarItensVenda(@RequestParam("vendaId") int vendaId,
            @RequestBody List<ItemVenda> itensVenda) {
        Optional<Venda> optionalVenda = vendaService.findById(vendaId);
        if (optionalVenda.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Venda não encontrada");
        }

        Venda venda = optionalVenda.get();
        for (ItemVenda item : itensVenda) {
            item.setVenda(venda);
            itemVendaService.save(item);
        }

        atualizarValorTotalVenda(vendaId);

        return ResponseEntity.ok("Itens da venda adicionados com sucesso");
    }

    private void atualizarValorTotalVenda(int vendaId) {
        float valorTotalVenda = 0;
        List<ItemVenda> itensVenda = itemVendaService.findByVendaId(vendaId);

        for (ItemVenda item : itensVenda) {
            valorTotalVenda += item.getValorTotalProduto();
        }

        Optional<Venda> optionalVenda = vendaService.findById(vendaId);
        if (optionalVenda.isPresent()) {
            Venda venda = optionalVenda.get();
            venda.setValorTotal(valorTotalVenda);
            vendaService.save(venda);
        }
    }

    private void carregarClientesNoModel(Model model) {
        List<Cliente> clientes = clienteService.findAll();
        model.addAttribute("clientes", clientes);
    }

}
