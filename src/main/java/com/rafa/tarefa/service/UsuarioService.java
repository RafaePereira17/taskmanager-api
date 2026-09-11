package com.rafa.tarefa.service;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.rafa.tarefa.Exception.EmailJaCadastrado;
import com.rafa.tarefa.dtos.LoginRequest;
import com.rafa.tarefa.dtos.LoginResponse;
import com.rafa.tarefa.dtos.UsuarioRequest;
import com.rafa.tarefa.dtos.UsuarioResponse;
import com.rafa.tarefa.model.Usuario;
import com.rafa.tarefa.repository.UsuarioRepository;
import com.rafa.tarefa.security.JwtService;



@Service
public class UsuarioService {
    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public UsuarioService (UsuarioRepository repository,PasswordEncoder passwordEncoder,JwtService jwtService){
        this.repository=repository;
        this.passwordEncoder= passwordEncoder;
        this.jwtService=jwtService;
    }
    public UsuarioResponse salvar (UsuarioRequest dto){
        if (repository.existsByEmail(dto.getEmail())) {
            throw new EmailJaCadastrado("Email ja cadsatrado");
            
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));

        

        Usuario salvo = repository.save(usuario);
        UsuarioResponse response = new UsuarioResponse();
        response.setId(salvo.getId());
        response.setNome(salvo.getNome());
        response.setEmail(salvo.getEmail());
        
        return response;
    }
    public LoginResponse login (LoginRequest dto){
        Optional<Usuario> usuario = repository.findByEmail(dto.getEmail());
        if (usuario.isEmpty()) {
            throw new RuntimeException("Email ou Senha Invalido");
            
        }
        if (!passwordEncoder.matches(dto.getSenha(), usuario.get().getSenha())) {
            throw new RuntimeException("Email ou Senha Invalidos");

            
            
        }
        String token = jwtService.gerarToken(usuario.get());
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        return response;
    }
    
} 
