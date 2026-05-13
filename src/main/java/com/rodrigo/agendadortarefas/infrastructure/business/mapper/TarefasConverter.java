package com.rodrigo.agendadortarefas.infrastructure.business.mapper;

import com.rodrigo.agendadortarefas.infrastructure.business.DTO.TarefasDTO;
import com.rodrigo.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")

public interface TarefasConverter {
    TarefasEntity paraTarefaEntity (TarefasDTO dto);

    TarefasDTO paraTarefaDTO (TarefasEntity entity);


}
