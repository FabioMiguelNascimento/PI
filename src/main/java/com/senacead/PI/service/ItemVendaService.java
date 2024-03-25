package com.senacead.PI.service;

import com.senacead.PI.entity.ItemVenda;
import com.senacead.PI.repository.ClienteRepository;
import com.senacead.PI.repository.ItemVendaRepository;
import com.senacead.PI.repository.ProdutoRepository;
import com.senacead.PI.repository.VendaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemVendaService {

    @Autowired
    private ItemVendaRepository itemVendaRepository;

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    private final ProdutoService produtoService;

    public ItemVendaService(ItemVendaRepository itemVendaRepository, ProdutoService produtoService) {
        this.itemVendaRepository = itemVendaRepository;
        this.produtoService = produtoService;
    }

    @Transactional
    public ItemVenda save(ItemVenda itemVenda) {
        return itemVendaRepository.save(itemVenda);
    }

    public List<ItemVenda> findByVendaId(int vendaId) {
        return itemVendaRepository.findByVendaId(vendaId);
    }

}
