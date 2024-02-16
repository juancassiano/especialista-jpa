package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

public class AutoRelacionamentoTest extends EntityManagerTest {

    @Test
    public void verificarRelacionamento() {
        Categoria categoriaPai = new Categoria();
        categoriaPai.setNome("Futebol");

        Categoria categoria = new Categoria();
        categoria.setNome("Uniformes");
        categoria.setCategoriaPai(categoriaPai);

        entityManager.getTransaction().begin();
        entityManager.persist(categoriaPai);
        entityManager.persist(categoria);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Categoria categoriaVerificado = entityManager.find(Categoria.class, categoria.getId());
        Categoria categoriaPaiVerificado = entityManager.find(Categoria.class, categoriaPai.getId());

        Assert.assertNotNull(categoriaVerificado.getCategoriaPai());
        Assert.assertFalse(categoriaPaiVerificado.getCategorias().isEmpty());
    }

}