package com.senacead.PI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.senacead.PI.entity.Produto;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
