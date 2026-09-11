package com.rafa.tarefa.controller;
import com.rafa.tarefa.dtos.LoginRequest;
import com.rafa.tarefa.dtos.LoginResponse;
import com.rafa.tarefa.dtos.UsuarioRequest;
import com.rafa.tarefa.dtos.UsuarioResponse;
import com.rafa.tarefa.service.UsuarioService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService service;
    public UsuarioController(UsuarioService service){
        this.service=service;
    }
    @PostMapping
    public ResponseEntity<UsuarioResponse> salvar (@Valid @RequestBody UsuarioRequest dto){
        UsuarioResponse usuario = service.salvar(dto);
        return ResponseEntity.status(201).body(usuario);
    }
    @PostMapping("/login")
    public ResponseEntity <LoginResponse> login (@Valid @RequestBody LoginRequest dto){
        return ResponseEntity.ok(service.login(dto));
    }
}
