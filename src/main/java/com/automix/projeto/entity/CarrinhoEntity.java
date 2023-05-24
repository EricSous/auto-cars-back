package com.automix.projeto.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "carrinho")
public class CarrinhoEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "produto_id", nullable = false)
  private ProdutoEntity produto;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "usuario_id", nullable = false)
  private UsuarioEntity usuario;
}
