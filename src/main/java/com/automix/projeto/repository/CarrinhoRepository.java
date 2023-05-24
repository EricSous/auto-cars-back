package com.automix.projeto.repository;

import com.automix.projeto.entity.CarrinhoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarrinhoRepository extends JpaRepository<CarrinhoEntity, Long> {


  List<CarrinhoEntity> findAllByUsuarioId(Long userId);

  CarrinhoEntity findByProdutoId(Long produtoId);
}
