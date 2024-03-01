package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class BasicoJPQLTest extends EntityManagerTest {

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
}
