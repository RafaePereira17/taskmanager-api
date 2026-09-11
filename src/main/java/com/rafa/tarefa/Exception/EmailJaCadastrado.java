package com.rafa.tarefa.Exception;

public class EmailJaCadastrado extends RuntimeException {
    public EmailJaCadastrado (String mensagem){
        super(mensagem);
    }
    
}
