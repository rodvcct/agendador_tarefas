package com.rodrigo.agendadortarefas.infrastructure.entity;

import com.rodrigo.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@RequiredArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
@Document ("tarefa")
public class TarefasEntity {

    private String id;
    private String nomeTarefa;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEvento;
    private String emailUsuario;
    private LocalDateTime dataAlteracao;
    private StatusNotificacaoEnum statusNotificacaoEnum;




}
