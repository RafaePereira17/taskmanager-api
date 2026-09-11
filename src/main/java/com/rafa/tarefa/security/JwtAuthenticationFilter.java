package com.rafa.tarefa.security;

import java.io.IOException;
import java.util.Optional;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.rafa.tarefa.model.Usuario;
import com.rafa.tarefa.repository.UsuarioRepository;
import io.jsonwebtoken.lang.Collections;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    public JwtAuthenticationFilter (JwtService jwtService,UsuarioRepository usuarioRepository){
        this.jwtService=jwtService;
        this.usuarioRepository=usuarioRepository;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain)
        throws ServletException, IOException{

             String authHeader = request.getHeader("Authorization");

             if (authHeader == null || !authHeader.startsWith("Bearer")) {
                filterChain.doFilter(request, response);
                return;
                
             }
             String token = authHeader.substring(7);

             String email = jwtService.extrairEmail(token);

             Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

             if (usuario.isPresent() && jwtService.tokenValido(token, usuario.get())) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario.get(),null,Collections.emptyList());
                
                SecurityContextHolder.getContext().setAuthentication(authentication);
                
             }
             filterChain.doFilter(request, response);
        }
       
    }

    

