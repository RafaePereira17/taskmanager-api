package com.rafa.tarefa.model;
import java.util.List;

import jakarta.persistence.*;
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    @OneToMany(mappedBy = "usuario")
    private List<Tarefa> tarefas;
    
    public Usuario() {}

    public Long getId (){
        return id;
    }
    public void setId (Long id){
        this.id=id;
    }
    public  String getNome (){
        return nome;
    }
    public void setNome (String nome){
        this.nome=nome;
    }
    public String getEmail () {
        return email;
    }
    public void setEmail (String email){
        this.email=email;
    }
    public String getSenha () {
        return senha;
    }
    public void setSenha (String senha){
        this.senha=senha;
    }
    public List<Tarefa> getTarefas (){
        return tarefas;
    }
    public void setTarefas (List<Tarefa> tarefas){
        this.tarefas=tarefas;
    }

    
    
}
