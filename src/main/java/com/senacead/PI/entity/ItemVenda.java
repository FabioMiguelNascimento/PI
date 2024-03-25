package com.senacead.PI.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "item_venda")
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private int quantidade;

    @Column(name = "valor_unitario")
    private float valorUnitario;

    @Column(name = "valor_total_produto")
    private float valorTotalProduto;

    @ManyToOne
    @JoinColumn(name = "venda_id")
    private Venda venda;

}
