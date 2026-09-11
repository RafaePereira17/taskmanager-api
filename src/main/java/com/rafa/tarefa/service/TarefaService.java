package com.rafa.tarefa.service;
import java.time.LocalDateTime;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.rafa.tarefa.Exception.TarefaNaoEncontrada;
import com.rafa.tarefa.dtos.TarefaRequest;
import com.rafa.tarefa.dtos.TarefaResponse;
import com.rafa.tarefa.model.Tarefa;
import com.rafa.tarefa.model.Usuario;
import com.rafa.tarefa.repository.TarefaRepository;

@Service
public class TarefaService {
    private TarefaRepository repository;
    public TarefaService (TarefaRepository repository){
        this.repository=repository;
    } 
    private Usuario getUsuarioAutenticado(){
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    public TarefaResponse salvar (TarefaRequest dto){
        Usuario usuario = getUsuarioAutenticado();
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setStatus(dto.getStatus());
        tarefa.setDataCriacao(LocalDateTime.now());
        tarefa.setUsuario(usuario);

        Tarefa salvo = repository.save(tarefa);
        
        return converteParaResponse(salvo);

    }
    public Page<TarefaResponse> listar (Pageable pageable){
        Usuario usuario = getUsuarioAutenticado();
        Page<Tarefa> tarefas = repository.findByUsuario(usuario, pageable);

        return tarefas.map(t -> converteParaResponse(t));
        
    
    }
    public TarefaResponse buscarPorId (Long id){
        Usuario usuario = getUsuarioAutenticado();
        Optional<Tarefa> tarefa = repository.findByIdAndUsuario(id, usuario);
        if (tarefa.isEmpty()) {
            throw new  TarefaNaoEncontrada("Tarefa nao encontrada");
            
        }
        Tarefa tarefaEncontrada = tarefa.get();

        return converteParaResponse(tarefaEncontrada);

    }
    public TarefaResponse atualizar (Long id, TarefaRequest dto){
        Usuario usuario = getUsuarioAutenticado();
        Optional<Tarefa> tarefa = repository.findByIdAndUsuario(id, usuario);
        if (tarefa.isEmpty()) {
            throw new TarefaNaoEncontrada("Tarefa nao encontrada");
            
        }
        Tarefa tarefaEncontrada = tarefa.get();

        tarefaEncontrada.setTitulo(dto.getTitulo());
        tarefaEncontrada.setDescricao(dto.getDescricao());
        tarefaEncontrada.setStatus(dto.getStatus());

        Tarefa atualizada = repository.save(tarefaEncontrada);
        
        return converteParaResponse(atualizada);

    }
    public void remover (Long id){
        Usuario usuario = getUsuarioAutenticado();
        Optional<Tarefa> tarefa = repository.findByIdAndUsuario(id, usuario);
        if (tarefa.isEmpty()) {
            throw new TarefaNaoEncontrada("Tarefa nao encontrada");

            
        }
        Tarefa tarefaEncontrada = tarefa.get();
        repository.delete(tarefaEncontrada);
    }

    private TarefaResponse converteParaResponse (Tarefa tarefa){
        TarefaResponse response = new TarefaResponse();

        response.setId(tarefa.getId());
        response.setTitulo(tarefa.getTitulo());
        response.setDescricao(tarefa.getDescricao());
        response.setStatus(tarefa.getStatus());
        response.setDataCriacao(LocalDateTime.now());
        
        return response;
    }
    
}
