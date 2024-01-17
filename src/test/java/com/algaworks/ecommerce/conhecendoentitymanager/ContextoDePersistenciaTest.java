package com.algaworks.ecommerce.conhecendoentitymanager;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Test;

import java.math.BigDecimal;

public class ContextoDePersistenciaTest extends EntityManagerTest {

    @Test
    public void contextoPersistencia(){
        Produto produto = entityManager.find(Produto.class,1);

        entityManager.getTransaction().begin();
        produto.setPreco(new BigDecimal(100));
        Produto produto2 = new Produto();
        produto2.setNome("Caneca de café");
        produto2.setDescricao("Caneca para café");
        entityManager.persist(produto2);

        Produto produto3 = new Produto();
        produto3.setNome("Caneca de chá");
        produto3.setPreco(new BigDecimal(100));
        produto3 = entityManager.merge(produto3);
        produto3.setDescricao("Caneca para chá");

        entityManager.flush();
        produto3.setDescricao("Alterando descrição");
        entityManager.getTransaction().commit();
    }
}
