package com.rafa.tarefa.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;

import com.rafa.tarefa.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

    boolean existsByEmail(String email);
    
    Optional<Usuario> findByEmail(String email);
}
