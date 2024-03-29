package com.algaworks.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
//@Entity@DiscriminatorValue("boleto")  Ignorada na estratégia table per class
@Entity
@DiscriminatorValue("boleto")
//@Table(name = "pagamento_boleto") Ignorando pela estratrégia single table
public class PagamentoBoleto extends Pagamento{


    @Column(name = "codigo_barras", length = 100)
    private String codigoBarras;


}
