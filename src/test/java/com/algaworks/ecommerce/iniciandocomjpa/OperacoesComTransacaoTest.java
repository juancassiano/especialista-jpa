package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OperacoesComTransacaoTest extends EntityManagerTest {

    @Test
    public void inserirPrimeiroObjeto(){
        Produto produto = new Produto();
        produto.setNome("Câmera Canon");
        produto.setDescricao("A melhor definição para as suas fotos");
        produto.setPreco(new BigDecimal(5000));
        produto.setDataCriacao(LocalDateTime.now());

        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoVerifica = entityManager.find(Produto.class, produto.getId());

        Assert.assertNotNull(produtoVerifica);
    }

    @Test
    public void removerObjeto(){
        Produto produto = entityManager.find(Produto.class,3);
        entityManager.getTransaction().begin();
        entityManager.remove(produto);
        entityManager.getTransaction().commit();
        Produto produtoVerificacao = entityManager.find(Produto.class,3);

        Assert.assertNull(produtoVerificacao);
    }

    @Test
    public void atualizarObjeto(){
        Produto produto = new Produto();
        produto.setNome("Kindle Paperwhite");
        produto.setDescricao("Conheça o novo Kindle");
        produto.setPreco(new BigDecimal(599));

        entityManager.getTransaction().begin();
        Produto produtoSalvo = entityManager.merge(produto);
        entityManager.getTransaction().commit();
        entityManager.clear();
        Produto produtoVerificado = entityManager.find(Produto.class, produtoSalvo.getId());

        Assert.assertNotNull(produtoVerificado);
        Assert.assertEquals("Kindle Paperwhite",produtoVerificado.getNome());
    }
    @Test
    public void atualizarObjetoGerenciado(){
        Produto produto = entityManager.find(Produto.class,1);

        entityManager.getTransaction().begin();
        produto.setNome("Kindle Paperwhite 2 Geração");
        entityManager.getTransaction().commit();
        entityManager.clear();
        Produto produtoVerificado = entityManager.find(Produto.class, produto.getId());

        Assert.assertEquals("Kindle Paperwhite 2 Geração",produtoVerificado.getNome());
    }

    @Test
    public void inserirObjetoComMerge(){
        Produto produto = new Produto();
        produto.setNome("Microfone Rode Videmic");
        produto.setDescricao("A melhor qualidade de som");
        produto.setPreco(new BigDecimal(1000));
        produto.setDataCriacao(LocalDateTime.now());

        entityManager.getTransaction().begin();
        Produto produtoSalvo = entityManager.merge(produto);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoVerifica = entityManager.find(Produto.class, produtoSalvo.getId());

        Assert.assertNotNull(produtoVerifica);
    }

    @Test
    public void impedirOperacaoComBancoDeDados(){
        Produto produto = entityManager.find(Produto.class,1);
        entityManager.detach(produto);

        entityManager.getTransaction().begin();
        produto.setNome("Kindle Paperwhite 2 Geração");
        entityManager.getTransaction().commit();

        entityManager.clear();
        Produto produtoVerificado = entityManager.find(Produto.class, produto.getId());

        Assert.assertEquals("Kindle",produtoVerificado.getNome());
    }



}
