package com.rafa.tarefa.controller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.rafa.tarefa.dtos.TarefaRequest;
import com.rafa.tarefa.dtos.TarefaResponse;
import com.rafa.tarefa.service.TarefaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    private TarefaService service;
    public TarefaController (TarefaService service){
        this.service=service;
    }
    @PostMapping
    public ResponseEntity <TarefaResponse> salvar (@Valid @RequestBody TarefaRequest dto){
        TarefaResponse salvo = service.salvar(dto);
        return ResponseEntity.status(201).body(salvo);
    }
    @GetMapping
    public ResponseEntity<Page<TarefaResponse>> listar (Pageable pageable){
        return ResponseEntity.ok(service.listar(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponse> buscar (@PathVariable Long id){
        return ResponseEntity.ok(service.buscarPorId(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<TarefaResponse> atualizar (@PathVariable Long id, @Valid @RequestBody TarefaRequest dto){
        return ResponseEntity.ok(service.atualizar(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover (@PathVariable Long id){
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

    
}
