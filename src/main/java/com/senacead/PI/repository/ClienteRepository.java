package com.senacead.PI.repository;

import org.springframework.stereotype.Repository;
import com.senacead.PI.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}