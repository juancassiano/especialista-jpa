package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.listener.GenericoListener;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@EntityListeners({GenericoListener.class})
@Table(name = "produto", uniqueConstraints = { @UniqueConstraint(name = "unq_nome", columnNames = {"nome"})},
indexes = { @Index(name = "idx_nome", columnList = "nome")})
public class Produto extends EntidadeBaseInteger {

    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    @Lob
    private String descricao;

    @Column(precision = 19, scale = 2)
    private BigDecimal preco;

    @ManyToMany
    @JoinTable(name = "produto_categoria",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id"),
            foreignKey = @ForeignKey(name = "fk_produto_categoria"))
    private List<Categoria>categorias;

    @OneToOne(mappedBy = "produto")
    private Estoque estoque;

    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_ultima_atualizacao", insertable = false)
    private LocalDateTime dataUltimaAtualizacao;

    @ElementCollection
    @CollectionTable(name = "produto_tag",
            joinColumns = @JoinColumn(name = "produto_id"),
            foreignKey = @ForeignKey(name = "fk_produto_tag"))
    @Column(name = "tag", length = 50, nullable = false)
    private List<String> tags;

    @ElementCollection
    @CollectionTable(name = "produto_atributo",
        joinColumns = @JoinColumn(name = "produto_id"),
        foreignKey = @ForeignKey(name = "fk_produto_atributo"))
    private List<Atributo> atributos;

    @Lob
    @Column(length = 1000)
    private byte[] foto;
}
