package com.senacead.PI.service;

import com.senacead.PI.entity.Produto;
import com.senacead.PI.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public void cadastrarProduto(Produto produto) {
        produtoRepository.save(produto);
    }

    public List<Produto> obterTodosProdutos() {
        return produtoRepository.findAll();
    }

    public boolean editarProduto(Produto produto) {
        if (produtoRepository.existsById(produto.getId())) {
            produtoRepository.save(produto);
            return true;
        }
        return false;
    }

    public boolean excluirProduto(Long produtoID) {
        if (produtoRepository.existsById(produtoID)) {
            produtoRepository.deleteById(produtoID);
            return true;
        }
        return false;
    }

    public Produto obterProdutoPorID(Long produtoID) {
        return produtoRepository.findById(produtoID).orElse(null);
    }
}
