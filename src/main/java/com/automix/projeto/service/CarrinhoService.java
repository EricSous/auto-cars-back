package com.automix.projeto.service;

import com.automix.projeto.dto.ProdutoDto;
import com.automix.projeto.dto.ProdutoFileDto;
import com.automix.projeto.entity.CarrinhoEntity;
import com.automix.projeto.entity.ProdutoEntity;
import com.automix.projeto.repository.CarrinhoRepository;
import com.automix.projeto.repository.ProdutoRepository;
import com.automix.projeto.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarrinhoService {

  @Autowired
  private CarrinhoRepository carrinhoRepository;

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private ProdutoRepository produtoRepository;

  @Autowired
  private ObjectMapper objectMapper;

  public CarrinhoEntity adicionaCarrinho(Long produtoId, Long userId) {
    CarrinhoEntity carrinhoEntity = new CarrinhoEntity();
    carrinhoEntity.setUsuario(usuarioRepository.findById(userId).orElseThrow());
    carrinhoEntity.setProduto(produtoRepository.findById(produtoId).orElseThrow());

    return carrinhoRepository.save(carrinhoEntity);
  }

  public List<ProdutoEntity> retornaCarros(Long userId) {
    List<CarrinhoEntity> allByUsuarioId = carrinhoRepository.findAllByUsuarioId(userId);

    List<Long> idsProdutos = allByUsuarioId.stream()
      .map(produto -> produto.getProduto().getId())
      .collect(Collectors.toList());

    return produtoRepository.findAll().stream()
      .filter(produto -> idsProdutos.contains(produto.getId()))
      .collect(Collectors.toList());
  }

  public void deleteCar(Long idCarro){
    CarrinhoEntity byProdutoId = carrinhoRepository.findByProdutoId(idCarro);
    carrinhoRepository.deleteById(byProdutoId.getId());
  }
}
