package com.rafa.tarefa.dtos;

import com.rafa.tarefa.model.StatusTarefa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TarefaRequest {
    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;
    @NotNull
    private StatusTarefa status;

    public TarefaRequest () {}

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

    
}
