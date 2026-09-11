package com.rafa.tarefa.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataCriacao;
    @Enumerated(EnumType.STRING)
    private StatusTarefa status;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Tarefa () {}

    public String getTitulo (){
        return titulo;
    }
    public void setTitulo (String titulo){
        this.titulo=titulo;
    }
    public Long getId (){
        return id;
    }
    public void setId (Long id){
        this.id=id;
    }
    public String getDescricao (){
        return descricao;
    }
    public void setDescricao (String descriacao){
        this.descricao=descriacao;
    }
    public LocalDateTime getDataCriacao (){
        return dataCriacao;
    }
    public void setDataCriacao (LocalDateTime dataCricao){
        this.dataCriacao=dataCricao;
    }
    public StatusTarefa getStatus (){
        return status;
    }
    public void setStatus (StatusTarefa status){
        this.status=status;
    }
    public Usuario getUsuario (){
        return usuario;
    }
    public void setUsuario (Usuario usuario){
        this.usuario=usuario;
    }

    
}
