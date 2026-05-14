package com.rodrigo.agendadortarefas.infrastructure.business;

import com.rodrigo.agendadortarefas.infrastructure.business.DTO.TarefasDTO;
import com.rodrigo.agendadortarefas.infrastructure.business.mapper.TarefaUpdateConverter;
import com.rodrigo.agendadortarefas.infrastructure.business.mapper.TarefasConverter;
import com.rodrigo.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.rodrigo.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.rodrigo.agendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.rodrigo.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.rodrigo.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor

public class TarefaService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefaConverter;
    private final JwtUtil jwtUtil;
    private final TarefaUpdateConverter tarefaUpdateConverter;

    public TarefasDTO gravarTarefa(String token, TarefasDTO dto) {


        String email = jwtUtil.extrairEmailDoToken(token.substring(7));
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        dto.setEmailUsuario(email);
        TarefasEntity entity = tarefaConverter.paraTarefaEntity(dto);


        return tarefaConverter.paraTarefaDTO(
                tarefasRepository.save(entity));
    }

    public List<TarefasDTO> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {

        return tarefaConverter.paraListaTarefasDto(
                tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal));
    }

    public List<TarefasDTO> buscaTarefasPorEmail(String token) {

        String email = jwtUtil.extrairEmailDoToken(token.substring(7));
        List<TarefasEntity> listaTarefas = tarefasRepository.findByEmailUsuario(email);

        return tarefaConverter.paraListaTarefasDto(listaTarefas);
    }

    public void deletaTarefaPorId(String id) {
        tarefasRepository.deleteById(id);
    }

    public TarefasDTO alteraStatus(StatusNotificacaoEnum status, String id) {

        try {
            TarefasEntity entity = tarefasRepository.findById(id).
                    orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada " + id));
            entity.setStatusNotificacaoEnum(status);
            return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar o status da tarefa " + e.getCause());
        }
    }

    public TarefasDTO updateTarefas(TarefasDTO dto, String id) {

        try {
            TarefasEntity entity = tarefasRepository.findById(id).
                    orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada " + id));

            tarefaUpdateConverter.updateTarefas(dto,entity);
            tarefasRepository.save(entity);
            return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));

        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar o status da tarefa " + e.getCause());
        }
    }
}
