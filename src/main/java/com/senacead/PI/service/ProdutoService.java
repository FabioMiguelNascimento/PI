package com.senacead.PI.service;

import com.senacead.PI.entity.Produto;
import com.senacead.PI.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository rodutoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto save(String nome, int quantidade, float valor) {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setQuantidade(quantidade);
        produto.setValor(valor);
        return produtoRepository.save(produto);
    }

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public List<Produto> findAllById(List<Integer> ids) {
        return produtoRepository.findAllById(ids);
    }

    public Optional<Produto> findById(int id) {
        return produtoRepository.findById(id);
    }

    public boolean update(int id, Produto produtoAtualizado) {
        if (produtoRepository.existsById(id)) {
            produtoAtualizado.setId(id);
            produtoRepository.save(produtoAtualizado);
            return true;
        }
        return false;
    }

    public boolean deleteById(int id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return true;
        }
        return false;
    }


    public Optional<Produto> findByProdutoId(int id) {
        return produtoRepository.findById(id);
    }

}
