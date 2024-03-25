package com.senacead.PI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senacead.PI.entity.ItemVenda;
import java.util.List;

@Repository
public interface ItemVendaRepository extends JpaRepository<ItemVenda, Integer> {
    List<ItemVenda> findByVendaId(int vendaId);

}
