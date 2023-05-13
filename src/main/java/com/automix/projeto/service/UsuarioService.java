package com.automix.projeto.service;

import com.automix.projeto.entity.UsuarioEntity;
import com.automix.projeto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

  private UsuarioRepository usuarioRepository;

  @Autowired
  public UsuarioService(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
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
}

