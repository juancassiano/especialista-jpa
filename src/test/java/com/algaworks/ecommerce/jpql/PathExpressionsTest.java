package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class PathExpressionsTest extends EntityManagerTest {

    @Test
    public void buscarPedidosComProdutoEspecifico(){
        String jpql = "select p from Pedido p " +
                "join p.itens i where i.id.produtoId = 1";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql,Pedido.class);
        List<Pedido> lista = typedQuery.getResultList();
        Assert.assertTrue(lista.size() == 2);

    }

    @Test
    public void usarPathExpressions(){
        String jpql = "select p.cliente.nome from Pedido p";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql,Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }
}
