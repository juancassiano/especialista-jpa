package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.listener.GenericoListener;
import com.algaworks.ecommerce.listener.GerarNotaFiscalListener;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EntityListeners({GerarNotaFiscalListener.class, GenericoListener.class})
@Entity
@Table(name = "pedido")
public class Pedido  extends EntidadeBaseInteger {

//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_pedido_cliente"))
    private Cliente cliente;

    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_ultima_atualizacao", insertable = false)
    private LocalDateTime dataUltimaAtualizacao;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    @OneToOne(mappedBy = "pedido")
    private NotaFiscal notaFiscal;

    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal total;

    @Column(length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @Embedded
    private EnderecoEntregaPedido enderecoEntrega;

    @OneToOne(mappedBy = "pedido")
    private Pagamento pagamento;

    @OneToMany(mappedBy = "pedido")
//    @OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
    private List<ItemPedido> itens;

    @PrePersist
    public void aoPersistir(){
        dataCriacao = LocalDateTime.now();
        calcularTotal();
    }

    @PreUpdate
    public void aoAtualizar(){
        dataUltimaAtualizacao = LocalDateTime.now();
        calcularTotal();
    }

    public void calcularTotal(){
        if(itens != null){
            total = itens.stream().map(i -> new BigDecimal(i.getQuantidade()).multiply(i.getPrecoProduto()))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }else {
            total = BigDecimal.ZERO;
        }
    }

    public boolean isPago(){
        return StatusPedido.PAGO.equals(status);
    }
}
