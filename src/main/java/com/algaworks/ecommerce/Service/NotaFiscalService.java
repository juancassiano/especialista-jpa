package com.algaworks.ecommerce.Service;


import com.algaworks.ecommerce.model.Pedido;

public class NotaFiscalService {
    public void gerar(Pedido pedido) {
        System.out.println(String.format("Gerar nota para o pedido %d", pedido.getId()));
    }
}
