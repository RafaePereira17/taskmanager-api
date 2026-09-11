package com.rafa.tarefa.Exception;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.rafa.tarefa.dtos.ErroResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TarefaNaoEncontrada.class)
    public ResponseEntity<ErroResponse> TarefaNaoEncontrada(TarefaNaoEncontrada ex){
        ErroResponse erro = new ErroResponse();
        erro.setStatus(404);
        erro.setErro("Not Found");
        erro.setMensagem(ex.getMessage());
        erro.setDataHora(LocalDateTime.now());

        return ResponseEntity.status(404).body(erro);
    }
    @ExceptionHandler(EmailJaCadastrado.class)
    public ResponseEntity<ErroResponse> tratarEmailCadatrado (EmailJaCadastrado ex){
        ErroResponse erro = new ErroResponse();
        erro.setStatus(409);
        erro.setErro("Conflict");
        erro.setMensagem(ex.getMessage());
        erro.setDataHora(LocalDateTime.now());

        return ResponseEntity.status(409).body(erro);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> tratarValid (MethodArgumentNotValidException ex){

        List<FieldError> errosCampos = ex.getBindingResult().getFieldErrors();

        List<String> mensagens = new ArrayList<>();

            for (FieldError erroCampo : errosCampos){
                String mensagem = erroCampo.getField() + ": " + erroCampo.getDefaultMessage();

                mensagens.add(mensagem);
            }


        


        ErroResponse erro = new ErroResponse();
        erro.setStatus(400);
        erro.setErro("Bad Request");
        erro.setMensagem("Dadso Invalidos");
        erro.setErros(mensagens);
        erro.setDataHora(LocalDateTime.now());

        return ResponseEntity.status(400).body(erro);
    }
    
}
