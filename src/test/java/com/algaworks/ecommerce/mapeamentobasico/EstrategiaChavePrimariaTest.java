package com.algaworks.ecommerce.mapeamentobasico;

import com.algaworks.ecommerce.iniciandocomjpa.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;
import org.junit.Assert;
import org.junit.Test;

public class EstrategiaChavePrimariaTest extends EntityManagerTest {

    @Test
    public void testarEstrategiaChave(){
        Categoria categoria = new Categoria();
        categoria.setNome("Eletrônicos");

        entityManager.getTransaction().begin();
        entityManager.persist(categoria);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Categoria categoriaVerificada = entityManager.find(Categoria.class, categoria.getId());

        Assert.assertNotNull(categoriaVerificada);
    }
}
