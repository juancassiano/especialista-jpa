package com.algaworks.ecommerce.mapeamentobasico;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.EnderecoEntregaPedido;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPedido;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MapeamentoObjetoImbutido extends EntityManagerTest {

    @Test
    public void analizarMapeamentoObjetoImbutido() {
        Cliente cliente = entityManager.find(Cliente.class, 1);

        EnderecoEntregaPedido enderecoEntregaPedido = new EnderecoEntregaPedido();
        enderecoEntregaPedido.setCep("21210-210");
        enderecoEntregaPedido.setLogradouro("Rua 01");
        enderecoEntregaPedido.setBairro("Algum lugar");
        enderecoEntregaPedido.setCidade("Caxias");
        enderecoEntregaPedido.setNumero("100");
        enderecoEntregaPedido.setEstado("RJ");

        Pedido pedido = new Pedido();
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setTotal(new BigDecimal(1000));
        pedido.setEnderecoEntrega(enderecoEntregaPedido);
        pedido.setCliente(cliente);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificado = entityManager.find(Pedido.class, pedido.getId());

        Assert.assertNotNull(pedidoVerificado);
        Assert.assertNotNull(pedidoVerificado.getEnderecoEntrega());
        Assert.assertNotNull(pedidoVerificado.getEnderecoEntrega().getCep());

    }
}
