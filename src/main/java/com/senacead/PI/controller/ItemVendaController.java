package com.senacead.PI.controller;

import com.senacead.PI.entity.Produto;
import com.senacead.PI.entity.Venda;
import com.senacead.PI.service.ClienteService;
import com.senacead.PI.service.ItemVendaService;
import com.senacead.PI.service.ProdutoService;
import com.senacead.PI.service.VendaService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ItemVendaController {

    private final VendaService vendaService;
    private final ProdutoService produtoService;

    public ItemVendaController(VendaService vendaService, ProdutoService produtoService, ClienteService clienteService, ItemVendaService itemVendaService) {
        this.vendaService = vendaService;
        this.produtoService = produtoService;
    }

    @GetMapping("/venda/cadastrarItens")
    public String cadastrarItens(@RequestParam("vendaId") int vendaId, Model model) {
        Venda venda = vendaService.findById(vendaId).orElse(null);
        if (venda == null) {
            return "redirect:/erro";
        }

        List<Produto> produtos = produtoService.findAll();
        model.addAttribute("venda", venda);
        model.addAttribute("produtos", produtos);

        return "cadastrarItens";
    }

}
