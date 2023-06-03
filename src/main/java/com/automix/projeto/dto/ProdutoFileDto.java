package com.automix.projeto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoFileDto {

  private Long id;
  private String marca;
  private String modelo;
  private String ano;
  private Long preco;
  private String descricao;
  private String file;
}
