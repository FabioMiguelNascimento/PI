package com.senacead.PI.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "itemVenda")
public class ItemVenda implements Serializable {

    @Column(name = "ID")
    @Id
    private Long id;

    @Column(name = "NOMEPRODUTO")
    private String nomeProduto;

    @Column(name = "QUANTIDADE")
    private int quantidade;

    @Column(name = "VALORUNITARIO")
    private float valorUnitario;

    @ManyToOne
    @JoinColumn(name = "IDVENDA")
    private Venda venda;

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

}
