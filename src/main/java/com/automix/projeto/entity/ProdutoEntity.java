package com.automix.projeto.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.File;

@Getter
@Setter
@Entity
@Table(name = "produto")
public class ProdutoEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "marca", nullable = false)
  private String marca;

  @Column(name = "modelo", nullable = false)
  private String modelo;

  @Column(name = "ano")
  private String ano;

  @Column(name = "preco")
  private Double preco;

  @Column(name = "descricao")
  private String descricao;

  @Column(name = "imagem", columnDefinition = "BLOB")
  private byte[] file;

  @ManyToOne
  @JoinColumn(name = "usuario_id")
  private UsuarioEntity vendedor;
}
