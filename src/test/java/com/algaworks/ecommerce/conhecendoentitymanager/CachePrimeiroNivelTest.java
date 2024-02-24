package com.algaworks.ecommerce.conhecendoentitymanager;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.jupiter.api.Test;

public class CachePrimeiroNivelTest extends EntityManagerTest {

    @Test
    public void verificarCache(){
        Produto produto = entityManager.find(Produto.class, 1);

        Produto produtoResgatado = entityManager.find(Produto.class,1);
    }
}
