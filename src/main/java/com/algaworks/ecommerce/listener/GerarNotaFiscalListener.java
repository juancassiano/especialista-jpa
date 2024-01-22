package com.algaworks.ecommerce.listener;

import com.algaworks.ecommerce.Service.NotaFiscalService;
import com.algaworks.ecommerce.model.Pedido;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class GerarNotaFiscalListener {

    private NotaFiscalService notaFiscalService = new NotaFiscalService();

    @PrePersist
    @PreUpdate
    public void gerar(Pedido pedido){
        if(pedido.isPago() && pedido.getNotaFiscal() == null){
            notaFiscalService.gerar(pedido);
        }
    }
}
