package com.automix.projeto.controller;

import com.automix.projeto.dto.UsuarioDto;
import com.automix.projeto.entity.UsuarioEntity;
import com.automix.projeto.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {

  @Autowired
  private UsuarioService usuarioService;

  @Autowired
  private ObjectMapper objectMapper;

  @PostMapping("/cadastrar")
  public ResponseEntity<UsuarioEntity> cadastrarUsuario(@Valid @RequestBody UsuarioDto usuarioDto) {
    UsuarioEntity usuario = usuarioService.cadastrarUsuario(objectMapper.convertValue(usuarioDto, UsuarioEntity.class));
    return new ResponseEntity<>(usuario, HttpStatus.CREATED);
  }

  @GetMapping("/usuarios/{login}/{senha}")
  public ResponseEntity<UsuarioEntity> obterUsuarioPorLoginESenha(@PathVariable String login,
                                                                  @PathVariable String senha) {
    return usuarioService.obterUsuario(login, senha);
  }

  @DeleteMapping("/usuarios/{id}")
  public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) throws Exception {
    usuarioService.deletarUsuario(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
