package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RelacionamentoManyToOneTest extends EntityManagerTest {

    @Test
    public void verificarRelacionamentoPedidoCliente() {
        Cliente cliente = entityManager.find(Cliente.class, 1);
        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setDataUltimaAtualizacao(LocalDateTime.now());
        pedido.setTotal(BigDecimal.TEN);
        pedido.setCliente(cliente);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificado = entityManager.find(Pedido.class, pedido.getId());

        Assert.assertNotNull(pedidoVerificado.getCliente());
    }

    @Test
    public void verificarRelacionamentoItemPedido(){
        entityManager.getTransaction().begin();

        Cliente cliente = entityManager.find(Cliente.class, 1);
        Produto produto = entityManager.find(Produto.class, 1);

        Pedido pedido = new Pedido();
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setDataUltimaAtualizacao(LocalDateTime.now());
        pedido.setTotal(BigDecimal.TEN);
        pedido.setCliente(cliente);


        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(new ItemPedidoId());
//        itemPedido.setPedidoId(pedido.getId());
//        itemPedido.setProdutoId(produto.getId());
        itemPedido.setPrecoProduto(produto.getPreco());
        itemPedido.setQuantidade(1);
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);

        entityManager.persist(pedido);
        entityManager.persist(itemPedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        ItemPedido itemPedidoVerificado = entityManager.find(ItemPedido.class, new ItemPedidoId(
                pedido.getId(), produto.getId()
        ));

        Assert.assertNotNull(itemPedidoVerificado.getPedido());
        Assert.assertNotNull(itemPedidoVerificado.getProduto());
    }
}
