package com.algaworks.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
//@DiscriminatorColumn(name = "tipo_pagamento", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
//@Table(name = "pagamento") Ignorada na estratégia table per class
public abstract class Pagamento extends EntidadeBaseInteger{

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    @Enumerated(EnumType.STRING)
    private StatusPagamento status;
}
