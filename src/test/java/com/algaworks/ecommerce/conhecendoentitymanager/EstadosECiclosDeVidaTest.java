package com.algaworks.ecommerce.conhecendoentitymanager;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;
import org.junit.Test;

public class EstadosECiclosDeVidaTest extends EntityManagerTest {

    @Test
    public void analisarEstados(){
        Categoria categoriaNovo = new Categoria(); //Categoria Nova
        Categoria categoriaGerenciadaMerge = entityManager.merge(categoriaNovo); //Passa a Ser Gerenciada
        Categoria categoriaGerenciada = entityManager.find(Categoria.class, 1);

        entityManager.remove(categoriaGerenciada); //Removido
        entityManager.persist(categoriaGerenciada); // Volta a ser Gerenciado

        entityManager.detach(categoriaGerenciada); // Desanexar Categoria
    }
}
