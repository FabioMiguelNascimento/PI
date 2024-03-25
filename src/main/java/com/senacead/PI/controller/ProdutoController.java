package com.senacead.PI.controller;

import com.senacead.PI.entity.Produto;
import com.senacead.PI.service.ProdutoService;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/produto/procad")
    public String exibirFormCadastrarProduto() {
        return "cadastroProduto";
    }

    @PostMapping("/produto/procad")
    public String cadastrarProduto(@RequestParam("nome") String nome, @RequestParam("quantidade") int quantidade, @RequestParam("valor") float valor) {
        produtoService.save(nome, quantidade, valor);
        return "redirect:/produto/produtos";
    }

    @GetMapping("/produto/produtos")
    public String listarProdutos(Model model) {
        List<Produto> produtos = produtoService.findAll();
        model.addAttribute("produtos", produtos);
        return "produtos";
    }

    @GetMapping("/produto/editarProduto")
    public String exibirFormularioEditarProduto(@RequestParam("id") int id, Model model) {
        Produto produto = produtoService.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        if (produto != null) {
            model.addAttribute("produto", produto);
            return "editarProduto";
        } else {
            return "produtoNaoEncontrado";
        }
    }

    @PostMapping("/produto/salvarEdicaoProduto")
    public String salvarProdutoEditado(@ModelAttribute("produto") Produto produto) {
        boolean sucesso = produtoService.update(produto.getId(), produto);
        if (sucesso) {
            return "redirect:/produto/produtos";
        } else {
            return "redirect:/erro";
        }
    }

    @PostMapping("/produto/excluirProduto")
    public String excluirProduto(@RequestParam("id") int id) {
        boolean sucesso = produtoService.deleteById(id);
        if (sucesso) {
            return "redirect:/produto/produtos";
        } else {
            return "redirect:/erro";
        }
    }

}
