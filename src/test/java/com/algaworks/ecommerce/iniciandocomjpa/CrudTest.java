package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import org.junit.Assert;
import org.junit.Test;

public class CrudTest extends EntityManagerTest {

    @Test
    public void inserirCliente(){
        Cliente cliente = new Cliente();
        cliente.setNome("Cliente 3");

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificado = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertNotNull(clienteVerificado);
    }

    @Test
    public void buscarClientePorId(){
        Cliente cliente = entityManager.find(Cliente.class, 1);
        Assert.assertNotNull(cliente);
        Assert.assertEquals("Cliente 1", cliente.getNome());
    }

    @Test
    public void atualizarCliente(){
        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setNome("Cliente 2");

        entityManager.getTransaction().begin();
        entityManager.merge(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteverificado = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertEquals("Cliente 2", clienteverificado.getNome());
    }

    @Test
    public void removerCliente(){
        Cliente cliente = entityManager.find(Cliente.class, 2);

        entityManager.getTransaction().begin();
        entityManager.remove(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificado = entityManager.find(Cliente.class, cliente.getId());

        Assert.assertNull(clienteVerificado);
    }
}
