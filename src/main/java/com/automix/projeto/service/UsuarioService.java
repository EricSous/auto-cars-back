package com.automix.projeto.service;

import com.automix.projeto.dto.UsuarioEditDto;
import com.automix.projeto.entity.UsuarioEntity;
import com.automix.projeto.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

  private final UsuarioRepository usuarioRepository;

  private final ObjectMapper objectMapper;

  @Autowired
  public UsuarioService(UsuarioRepository usuarioRepository, ObjectMapper objectMapper) {
    this.usuarioRepository = usuarioRepository;
    this.objectMapper = objectMapper;
  }

  public UsuarioEntity cadastrarUsuario(UsuarioEntity usuarioEntity) {
    return usuarioRepository.save(usuarioEntity);
  }

  public ResponseEntity<UsuarioEntity> obterUsuario(String login, String senha) {
    UsuarioEntity byLoginAndSenha = usuarioRepository.findByLoginAndSenha(login, senha);
    if(byLoginAndSenha != null){
      return new ResponseEntity<>(byLoginAndSenha, HttpStatus.OK);
    }
    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
  }

  public void deletarUsuario(Long id) throws Exception {
    if (!usuarioRepository.existsById(id)) {
     throw new Exception("");
    }
    usuarioRepository.deleteById(id);
  }

  public void editarUsuario(UsuarioEditDto usuarioEditDto){
    UsuarioEntity byLoginAndSenha = usuarioRepository.findByLoginAndSenha(usuarioEditDto.getLogin(), usuarioEditDto.getSenha());
    UsuarioEntity usuarioEntity = objectMapper.convertValue(usuarioEditDto, UsuarioEntity.class);
    usuarioEntity.setId(byLoginAndSenha.getId());
    usuarioRepository.save(usuarioEntity);
  }
}

