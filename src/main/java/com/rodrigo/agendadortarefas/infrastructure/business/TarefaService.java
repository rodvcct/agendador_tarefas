package com.rodrigo.agendadortarefas.infrastructure.business;

import com.rodrigo.agendadortarefas.infrastructure.business.DTO.TarefasDTO;
import com.rodrigo.agendadortarefas.infrastructure.business.mapper.TarefasConverter;
import com.rodrigo.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.rodrigo.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.rodrigo.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.rodrigo.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor

public class TarefaService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefaConverter;
    private final JwtUtil jwtUtil;

    public TarefasDTO gravarTarefa(String token, TarefasDTO dto){


        String email = jwtUtil.extrairEmailDoToken(token.substring(7));
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        dto.setEmailUsuario(email);
        TarefasEntity entity = tarefaConverter.paraTarefaEntity(dto);


        return tarefaConverter.paraTarefaDTO(
                tarefasRepository.save(entity));
    }
}
