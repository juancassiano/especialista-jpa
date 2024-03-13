package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.TimeZone;

public class FuncoesTest extends EntityManagerTest {

    @Test
    public void aplicarFuncaoColecao() {
        String jpql = "select size(p.itens) from Pedido p where size(p.itens) > 1";

        TypedQuery<Integer> typedQuery = entityManager.createQuery(jpql, Integer.class);

        List<Integer> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(size -> System.out.println(size));
    }

    @Test
    public void aplicarFuncaoNumero() {
        //abs = absoluto | sqrt = raiz quadrara | mod = restante da divisão do 1º parâmetro pelo 2º
        String jpql = "select abs(p.total), mod(p.id, 2), sqrt(p.total) from Pedido p " +
                " where abs(p.total) > 1000";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(arr[0] + " | " + arr[1] + " | " + arr[2]));
    }
    @Test
    public void aplicarFuncaoData(){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        String jpql = "select current_date, current_time, current_timestamp from Pedido p";

        TypedQuery<Object []> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(arr[0] + " | " + arr[1] + " | " + arr[2]));
    }

    @Test
    public void aplicarFuncaoString(){
        //concat, length,locate substring lower, upper trim

        String jpql = "select c.nome , concat('Categoria: ',c.nome) from Categoria c";
//        String jpql = "select c.nome , length(c.nome) from Categoria c";
//        String jpql = "select c.nome, length(c.nome) from Categoria c where length(c.nome) >10";
//        String jpql = "select c.nome , locate('a', c.nome) from Categoria c";
//        String jpql = "select c.nome , substring(c.nome,1,2) from Categoria c";
//        String jpql = "select c.nome , lower(c.nome) from Categoria c";
//        String jpql = "select c.nome , upper(c.nome) from Categoria c";
//        String jpql = "select c.nome , trim(c.nome) from Categoria c";

        TypedQuery<Object []> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(arr[0] + " " + arr[1]));
    }
}
