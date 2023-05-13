package com.automix.projeto.service;

import com.automix.projeto.dto.ProdutoDto;
import com.automix.projeto.entity.ProdutoEntity;
import com.automix.projeto.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

  @Autowired
  private ProdutoRepository produtoRepository;

  @Autowired
  private ObjectMapper objectMapper;

  public List<ProdutoEntity> retornaTodosProdutos() {
    return produtoRepository.findAll();
  }

  public ProdutoEntity salvaProduto(ProdutoDto produtoDto, MultipartFile file) {
    try {
      byte[] imagemBytes = file.getBytes();
      produtoDto.setFile(null);

      ProdutoEntity produtoEntity = objectMapper.convertValue(produtoDto, ProdutoEntity.class);
      produtoEntity.setFile(imagemBytes);

      ProdutoEntity savedEntity = produtoRepository.save(produtoEntity);

      return savedEntity;
    } catch (IOException e) {
      throw new RuntimeException("Erro ao ler o arquivo de imagem.", e);
    }
  }

  public void deleteProduto(Long id) {
    produtoRepository.deleteById(id);
  }

  public ProdutoEntity atualizaProduto(ProdutoEntity produtoEntity) {
    produtoRepository.save(produtoEntity);
    return produtoRepository.findFirstById(produtoEntity.getId());
  }

}
