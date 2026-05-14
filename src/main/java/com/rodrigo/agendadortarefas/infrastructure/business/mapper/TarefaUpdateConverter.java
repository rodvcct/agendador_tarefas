package com.rodrigo.agendadortarefas.infrastructure.business.mapper;

import com.rodrigo.agendadortarefas.infrastructure.business.DTO.TarefasDTO;
import com.rodrigo.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.web.bind.annotation.Mapping;

import java.awt.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {

    void updateTarefas(TarefasDTO dto, @MappingTarget TarefasEntity entity);
}
