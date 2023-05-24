package com.automix.projeto.controller;

import com.automix.projeto.dto.ProdutoDto;
import com.automix.projeto.dto.ProdutoFileDto;
import com.automix.projeto.entity.CarrinhoEntity;
import com.automix.projeto.entity.ProdutoEntity;
import com.automix.projeto.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping(value = "/carrinho")
public class CarrinhoController {

  @Autowired
  private CarrinhoService carrinhoService;

  @RequestMapping(value = "/getProduto", method = RequestMethod.GET)
  @GetMapping
  public ResponseEntity<List<ProdutoFileDto>> getProduto(@RequestParam Long UserId) {
    List<ProdutoEntity> produtoEntities = carrinhoService.retornaCarros(UserId);

    List<ProdutoFileDto> produtoResponses = new ArrayList<>();

    for (ProdutoEntity produtoEntity : produtoEntities) {
      byte[] imagemBytes = produtoEntity.getFile();

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.IMAGE_JPEG);

      String imagemBase64 = Base64.getEncoder().encodeToString(imagemBytes);

      ProdutoFileDto produtoResponse = new ProdutoFileDto();
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

  @RequestMapping(value = "/adicionaCarrinho", method = RequestMethod.POST)
  @PostMapping()
  public ResponseEntity<CarrinhoEntity> adicionaProduto(@RequestParam Long userId,
                                                        @RequestParam Long produtoId) {
    try {
      CarrinhoEntity carrinhoEntity = carrinhoService.adicionaCarrinho(produtoId, userId);

      return ResponseEntity.ok(carrinhoEntity);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @RequestMapping(value = "/deleta", method = RequestMethod.DELETE)
  @DeleteMapping
  public void deletaCarrinho(Long idCarro){
    carrinhoService.deleteCar(idCarro);
  }
}
