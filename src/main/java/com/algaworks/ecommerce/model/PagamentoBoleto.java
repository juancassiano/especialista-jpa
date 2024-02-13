package com.algaworks.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
//@Entity@DiscriminatorValue("boleto")  Ignorada na estratégia table per class
//@Table(name = "pagamento_boleto") Ignorando pela estratrégia single table
@Table(name = "pagamento_boleto")
public class PagamentoBoleto extends Pagamento{


    @Column(name = "codigo_barras")
    private String codigoBarras;


}
