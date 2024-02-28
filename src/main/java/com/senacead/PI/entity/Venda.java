package com.senacead.PI.entity;

import jakarta.persistence.Column;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Venda {

    @Id
    @Column (name = "ID")
    private Long id;
    
    @Column (name = "NOMECLIENTE")
    private String nomeCliente;
    
    @Column (name = "VALORTOTAL")
    private float valorTotal;

    @Column (name = "DATA")
    private Date data;
    
    @Column (name = "CLIENTE")
    private Cliente cliente;
    
    @Column (name = "NOMEVENDA")
    private String nomeVenda;
    
    @Column (name = "ITENS")
    private List<ItemVenda> itens;

    public Venda() {
        this.itens = new ArrayList<>();
        this.data = new Date();
        this.cliente = new Cliente();
        this.nomeCliente = "";
    }

}