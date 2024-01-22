package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Test;

public class EaglerELazyTest extends EntityManagerTest {

    @Test
    public void verificarComportamento() {
        Pedido pedido = entityManager.find(Pedido.class, 1);
        pedido.getItens().isEmpty();
    }

}