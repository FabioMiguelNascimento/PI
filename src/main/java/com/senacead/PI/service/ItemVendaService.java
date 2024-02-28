package com.senacead.PI.service;

import com.senacead.PI.entity.ItemVenda;
import com.senacead.PI.repository.ItemVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemVendaService {

    @Autowired
    private ItemVendaRepository itemVendaRepository;

    public boolean inserirItemVenda(ItemVenda itemVenda) {
        try {
            itemVendaRepository.save(itemVenda);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
