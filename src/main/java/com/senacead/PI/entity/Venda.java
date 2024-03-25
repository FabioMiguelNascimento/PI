package com.senacead.PI.entity;

import lombok.Data;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Data
@Entity
public class Venda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "data_venda")
    private LocalDate  dataVenda;

    @Column(name = "valor_total")
    private float valorTotal;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemVenda> itensVenda = new ArrayList<>(); // Renomeei para itensVenda

}
