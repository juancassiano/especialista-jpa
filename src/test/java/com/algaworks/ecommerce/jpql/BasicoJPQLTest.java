package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.dto.ProdutoDTO;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class BasicoJPQLTest extends EntityManagerTest {

    @Test
    public void usarOrdenadorResultado(){
        String jpql = "select c from Cliente c order by c.nome desc";

        TypedQuery<Cliente> typedQuery = entityManager.createQuery(jpql, Cliente.class);
        List<Cliente> lista = typedQuery.getResultList();
        lista.forEach(c -> System.out.println(c.getId() + " - " + c.getNome()));
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void projetarNoDTO(){
        String jpql = "select new com.algaworks.ecommerce.dto.ProdutoDTO(id, nome) from Produto";
        TypedQuery<ProdutoDTO> typedQuery = entityManager.createQuery(jpql,ProdutoDTO.class);
        List<ProdutoDTO> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void projetarResultado(){
        String jpql = "select id, nome from Produto";
        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql,Object[].class);
        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertTrue(lista.get(0).length ==2);
    }
    @Test
    public void selecionarUmAtributoParaRetorno(){
        String jpql = "select p.nome from Produto p";

        TypedQuery<String> typedQuery = entityManager.createQuery(jpql, String.class);
        List<String> produtos = typedQuery.getResultList();
        Assert.assertTrue(String.class.equals(produtos.get(0).getClass()));

        String jpqlCliente = "select p.cliente from Pedido p";
        TypedQuery<Cliente> typedQueryCliente = entityManager.createQuery(jpqlCliente, Cliente.class);
        List<Cliente> listaClientes = typedQueryCliente.getResultList();
        Assert.assertTrue(Cliente.class.equals(listaClientes.get(0).getClass()));
    }

    @Test
    public void buscarPorIdentificador(){
        //entityManager.find(Pedido.class,1)
        TypedQuery<Pedido> typedQuery = entityManager
                .createQuery("select p from Pedido p where p.id = 1", Pedido.class);
        // SQL -> select p.* from Pedido p where p.id = 1



//        List<Pedido> lista = typedQuery.getResultList();
        Pedido pedido = typedQuery.getSingleResult();
//        Assert.assertFalse(lista.isEmpty());
        Assert.assertNotNull(pedido);
    }

    @Test
    public void mostrarDiferencasQueries(){
        String jpql = "select p from Pedido p where p.id = 1";
        TypedQuery<Pedido> typedQuery = entityManager
                .createQuery(jpql, Pedido.class);
        Pedido pedido1 = typedQuery.getSingleResult();
        Assert.assertNotNull(pedido1);

        Query query = entityManager.createQuery(jpql);
        Pedido pedido2 = (Pedido) query.getSingleResult();
        Assert.assertNotNull(pedido2);
    }
}
