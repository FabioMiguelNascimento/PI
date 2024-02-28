package com.senacead.PI.entity;


import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    
    @Column(name = "ID")
    @Id
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "NUMERO")
    private String numero;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "CPF")
    private String cpf;

}
