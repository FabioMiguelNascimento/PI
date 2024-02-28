package com.senacead.PI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.senacead.PI.entity.Venda;
import java.util.Date;
import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
    
    List<Venda> findByNomeCliente(String nomeCliente);
    
    List<Venda> findByDataBetween(Date dataInicial, Date dataFinal);
}

