package com.automix.projeto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDto {

  private String marca;
  private String modelo;
  private String ano;
  private String preco;
  private String descricao;
  private MultipartFile file;

}
