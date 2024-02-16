package com.algaworks.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
//@DiscriminatorValue("cartao")  Ignorada na estratégia table per class
@DiscriminatorValue("cartao")
@Entity
//@Table(name = "pagamento_cartao") Ignorando pela estratrégia single table
public class PagamentoCartao extends Pagamento{

    @Column(name = "numero_cartao", length = 50)
    private String numeroCartao;


}
