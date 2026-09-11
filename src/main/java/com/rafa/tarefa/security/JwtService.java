package com.rafa.tarefa.security;

import org.springframework.stereotype.Service;

import com.rafa.tarefa.model.Usuario;
import javax.crypto.SecretKey;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;
    private SecretKey getSigningKey (){
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
    private Claims extrairClaims(String token){
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
    }
    
    
    public String gerarToken(Usuario usuario){
        return Jwts.builder().subject(usuario.getEmail()).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + expiration)).signWith(getSigningKey()).compact();
                      
    }
    public String extrairEmail (String token){
        return extrairClaims(token).getSubject();
        
    }
    public Date extrairExpiracao (String token){
        return extrairClaims(token).getExpiration();
    }
    public boolean tokenExpirado(String token){
        return extrairExpiracao(token).before(new Date());
    }
    public boolean tokenValido (String token, Usuario usuario){
        String email = extrairEmail(token);
        return email.equals(usuario.getEmail()) && !tokenExpirado(token);
    }

    
    
}
