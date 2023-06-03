package com.automix.projeto.controller;

import com.automix.projeto.dto.ProdutoDto;
import com.automix.projeto.dto.ProdutoFileDto;
import com.automix.projeto.entity.ProdutoEntity;
import com.automix.projeto.service.ProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {

  @Autowired
  private ProdutoService produtoService;

  @Autowired
  private ObjectMapper objectMapper;

  @RequestMapping(value = "/getProduto", method = RequestMethod.GET)
  @GetMapping
  public ResponseEntity<List<ProdutoFileDto>> getProduto() {
    List<ProdutoEntity> produtoEntities = produtoService.retornaTodosProdutos();

    List<ProdutoFileDto> produtoResponses = new ArrayList<>();

    for (ProdutoEntity produtoEntity : produtoEntities) {
      byte[] imagemBytes = produtoEntity.getFile();

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.IMAGE_JPEG);

      String imagemBase64 = Base64.getEncoder().encodeToString(imagemBytes);

      ProdutoFileDto produtoResponse = new ProdutoFileDto();
      produtoResponse.setId(produtoEntity.getId());
      produtoResponse.setMarca(produtoEntity.getMarca());
      produtoResponse.setModelo(produtoEntity.getModelo());
      produtoResponse.setAno(produtoEntity.getAno());
      produtoResponse.setPreco(produtoEntity.getPreco());
      produtoResponse.setDescricao(produtoEntity.getDescricao());
      produtoResponse.setFile(imagemBase64);

      produtoResponses.add(produtoResponse);
    }
    return new ResponseEntity<>(produtoResponses, HttpStatus.OK);
  }

  @PostMapping("/adicionaProduto")
  public ResponseEntity<ProdutoEntity> adicionaProduto(@ModelAttribute ProdutoDto produtoDto) {
    try {
      ProdutoEntity produtoEntity = produtoService.salvaProduto(produtoDto, produtoDto.getFile());

      return ResponseEntity.ok(produtoEntity);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @RequestMapping(value = "/deletarProduto/{id}", method = RequestMethod.DELETE)
  @DeleteMapping
  public String deleteProduto(@PathVariable("id") Long id) {
    produtoService.deleteProduto(id);
    return "Deletado com sucesso";
  }

  @RequestMapping(value = "/atualizaProduto", method = RequestMethod.PUT)
  @PutMapping
  public ProdutoEntity atualizaProduto(@RequestBody ProdutoEntity produtoEntity) {
    return produtoService.atualizaProduto(produtoEntity);
  }

}
