package com.algaworks.ecommerce.mapeamentoavancado;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class ChaveCompostaTest extends EntityManagerTest {

    @Test
    public void salvarItem(){
        entityManager.getTransaction().begin();

        Cliente cliente = entityManager.find(Cliente.class,1);
        Produto produto = entityManager.find(Produto.class, 1);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setTotal(produto.getPreco());

        ItemPedido item = new ItemPedido();
//        item.setPedidoId(pedido.getId()); id Class
//        item.setProdutoId(produto.getId()); id Class
        item.setId(new ItemPedidoId());
        item.setPedido(pedido);
        item.setProduto(produto);
        item.setPrecoProduto(produto.getPreco());
        item.setQuantidade(1);

        entityManager.persist(pedido);
        entityManager.persist(item);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Pedido pedidoVerificado = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoVerificado);
        Assert.assertFalse(pedidoVerificado.getItens().isEmpty());
    }

    @Test
    public void buscarItem(){
        ItemPedido itemPedido = entityManager.find(ItemPedido.class, new ItemPedidoId(1,1));

        Assert.assertNotNull(itemPedido);
    }
}
