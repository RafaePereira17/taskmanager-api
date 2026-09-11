package com.rafa.tarefa.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class ErroResponse {
    private Integer status;
    private String erro;
    private String mensagem;
    private LocalDateTime dataHora;
    private List<String> erros;

    public ErroResponse ( ) {}

    public Integer getStatus (){
        return status;
    }
    public void  setStatus (Integer status){
        this.status=status;

    }
    public String getErro (){
        return erro;
    }
    public void setErro (String erro){
        this.erro=erro;
    }
    public String getMensagem (){
        return mensagem;
    }
    public void setMensagem (String mensagem){
        this.mensagem=mensagem;
    }
    public LocalDateTime getDataHora () {
        return dataHora;
    }
    public void setDataHora (LocalDateTime dataHora){
        this.dataHora=dataHora;
    }
    public List<String> getErros () {
        return erros;
    }
    public void setErros (List<String> erros){
        this.erros = erros;
    }
    
}
