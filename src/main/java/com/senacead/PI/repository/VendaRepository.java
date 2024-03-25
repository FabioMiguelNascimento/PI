package com.senacead.PI.repository;

import com.senacead.PI.entity.Venda;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Integer> {

    Venda findTopByOrderByIdDesc();

    List<Venda> findByDataVendaBetween(LocalDate dataInicio, LocalDate dataFim);

    List<Venda> findByClienteId(int clienteId);

    List<Venda> findByValorTotalBetween(float valorMinimo, float valorMaximo);

}
