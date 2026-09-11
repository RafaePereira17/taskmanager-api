package com.rafa.tarefa.repository;
import com.rafa.tarefa.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rafa.tarefa.model.Tarefa;

import java.util.Optional;

public interface TarefaRepository extends JpaRepository <Tarefa , Long> {

    Page<Tarefa> findByUsuario(Usuario usuario, Pageable pageable);
    
    Optional<Tarefa> findByIdAndUsuario(Long id , Usuario usuario);
}
