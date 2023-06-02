package com.automix.projeto.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class UsuarioEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "login", nullable = false)
  private String login;

  @Column(name = "senha", nullable = false)
  private String senha;

  @Column(name = "nome")
  private String nome;

  @Column(name = "email")
  private String email;

  @OneToMany(mappedBy = "vendedor")
  private List<ProdutoEntity> produtos;
}
