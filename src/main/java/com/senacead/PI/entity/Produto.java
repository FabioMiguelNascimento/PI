package com.senacead.PI.entity;

import jakarta.persistence.Column;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Produto {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NOME")
    private String nome;
    
    @Column(name = "QUANTIDADE")
    private int quantidade;
    
    @Column(name = "VALOR")
    private float valor;

}
