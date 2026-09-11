package com.rafa.tarefa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.rafa.tarefa.Exception.TarefaNaoEncontrada;
import com.rafa.tarefa.dtos.TarefaRequest;
import com.rafa.tarefa.dtos.TarefaResponse;
import com.rafa.tarefa.model.StatusTarefa;
import com.rafa.tarefa.model.Tarefa;
import com.rafa.tarefa.model.Usuario;
import com.rafa.tarefa.repository.TarefaRepository;
import com.rafa.tarefa.service.TarefaService;

@ExtendWith(MockitoExtension.class)
class TarefaServiceTest {
    private  Usuario usuario;

    @BeforeEach 
    void preparar(){
        usuario = new Usuario();
        usuario.setId(1L);

        Authentication authentication = mock(Authentication.class);

        when(authentication.getPrincipal()).thenReturn(usuario);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    @AfterEach 
    void limparContexto(){
        SecurityContextHolder.clearContext();
    }

    @Mock
    private TarefaRepository repository;

    @InjectMocks
    private TarefaService service;

    @Test
    void deveBuscarPorId (){
       

        Tarefa tarefa = new Tarefa();
        tarefa.setId(1L);
        tarefa.setTitulo("Estudar Java");
        tarefa.setDescricao("conseguir um emprego");
        tarefa.setStatus(StatusTarefa.PEDENTE);
        tarefa.setUsuario(usuario);

        

        when(repository.findByIdAndUsuario(1L, usuario)).thenReturn(Optional.of(tarefa));

        TarefaResponse resultado = service.buscarPorId(1L);

        assertEquals(1L, resultado.getId());
        assertEquals("Estudar Java", resultado.getTitulo());
        assertEquals("conseguir um emprego", resultado.getDescricao());
        assertEquals(StatusTarefa.PEDENTE, resultado.getStatus());
    }

    @Test 
    void deveLancarExcecaoTarefaNaoEncontrada(){
       

        when(repository.findByIdAndUsuario(1L, usuario)).thenReturn(Optional.empty());

        assertThrows(TarefaNaoEncontrada.class,()-> service.buscarPorId(1L));
    }

    @Test 
    void deveSalvarTarefa (){
        

        TarefaRequest dto = new TarefaRequest();

        dto.setTitulo("estudar java");
        dto.setDescricao("fazer testes");
        dto.setStatus(StatusTarefa.PEDENTE);

        

        when(repository.save(any(Tarefa.class))).thenAnswer(invocation -> {Tarefa tarefaSalva = invocation.getArgument(0); tarefaSalva.setId(1L);
            return tarefaSalva;
        });

        TarefaResponse resultado = service.salvar(dto);

        assertEquals(1L, resultado.getId());
        assertEquals("estudar java", resultado.getTitulo());
        assertEquals("fazer testes", resultado.getDescricao());
        assertEquals(StatusTarefa.PEDENTE, resultado.getStatus());

        ArgumentCaptor<Tarefa> captor= ArgumentCaptor.forClass(Tarefa.class);

        verify(repository).save(captor.capture());

        Tarefa tarefaEnviada = captor.getValue();

        assertEquals(usuario, tarefaEnviada.getUsuario());

         

        
        
    }
    @Test 
    void deveRemoverPorId(){

        Tarefa tarefa = new Tarefa();
        tarefa.setId(1L);
        tarefa.setUsuario(usuario);

        when(repository.findByIdAndUsuario(1L, usuario)).thenReturn(Optional.of(tarefa));

    service.remover(1L);

    verify(repository).delete(tarefa);
    }

    @Test 
    void deveLancaTarefaNaoEncontrada (){

        when(repository.findByIdAndUsuario(1L, usuario)).thenReturn(Optional.empty());

        assertThrows(TarefaNaoEncontrada.class, () -> service.remover(1L));
    }

    @Test 
    void deveAtualizarTarefa (){
        Tarefa tarefa = new Tarefa();
        tarefa.setId(1L);
        tarefa.setTitulo("estudar java");
        tarefa.setDescricao("fazer teste");
        tarefa.setStatus(StatusTarefa.PEDENTE);
        tarefa.setUsuario(usuario);

        TarefaRequest dto = new TarefaRequest();
        dto.setTitulo("novo titulo");
        dto.setDescricao("novo descricao");
        dto.setStatus(StatusTarefa.EM_ANDAMENTO);

        when(repository.findByIdAndUsuario(1L, usuario)).thenReturn(Optional.of(tarefa));

        when(repository.save(any(Tarefa.class))).thenReturn(tarefa);

        TarefaResponse resultado = service.atualizar(1L, dto);

        assertEquals("novo titulo", resultado.getTitulo());
        assertEquals("novo descricao", resultado.getDescricao());
        assertEquals(StatusTarefa.EM_ANDAMENTO, resultado.getStatus());

       verify(repository).save(tarefa);

    }
    @Test 
    void deveLancarTarefaNaoEncontradaAtualizar (){

    

        TarefaRequest dto = new TarefaRequest();
        dto.setTitulo("sal");
        dto.setDescricao("sla");
        dto.setStatus(StatusTarefa.CONCLUIDA);

        when(repository.findByIdAndUsuario(1L, usuario)).thenReturn(Optional.empty());

        assertThrows(TarefaNaoEncontrada.class, () -> service.atualizar(1L, dto));

        
    }

    
}
