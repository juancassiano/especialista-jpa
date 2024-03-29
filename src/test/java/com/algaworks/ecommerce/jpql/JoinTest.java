package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class JoinTest extends EntityManagerTest {

    @Test
    public void usarJoinFetch(){
        String jpql = "select p from Pedido p " +
                "left join fetch p.pagamento " +
                "join fetch p.cliente " +
                "left join fetch p.notaFiscal " +
                "where p.id = 1";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);

        Pedido pedido= typedQuery.getSingleResult();
        Assert.assertFalse(pedido.getItens().isEmpty());
    }

    @Test
    public void fazerLeftJoin(){
        String jpql = "select p from Pedido p left join p.pagamento pag on pag.status='PROCESSANDO'";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void fazerJoin(){
        String jpql = "select p from Pedido p join p.pagamento pag";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }
}
