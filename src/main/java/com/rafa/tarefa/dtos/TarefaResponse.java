package com.rafa.tarefa.dtos;

import java.time.LocalDateTime;

import com.rafa.tarefa.model.StatusTarefa;

public class TarefaResponse {
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataCriacao;
    private StatusTarefa status;

    public TarefaResponse () {}

    public Long getId () {
        return id;
    }
    public void setId (Long id){
        this.id=id;
    }
    public String getTitulo (){
        return titulo;
    }
    public void setTitulo (String titulo ){
        this.titulo=titulo;
    }
    public String getDescricao () {
        return descricao;
    }
    public void setDescricao (String descricao){
        this.descricao=descricao;
    }
    public StatusTarefa getStatus () {
        return status;
    }
    public void setStatus (StatusTarefa status){
        this.status=status;
    }
    public LocalDateTime getDataCriacao () {
        return dataCriacao;
    }
    public void setDataCriacao (LocalDateTime dataCriacao){
        this.dataCriacao=dataCriacao;
    }
    
}
