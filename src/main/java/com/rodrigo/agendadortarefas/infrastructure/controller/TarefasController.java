package com.rodrigo.agendadortarefas.infrastructure.controller;


import com.rodrigo.agendadortarefas.infrastructure.business.DTO.TarefasDTO;
import com.rodrigo.agendadortarefas.infrastructure.business.TarefaService;
import com.rodrigo.agendadortarefas.infrastructure.entity.TarefasEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {

    private final TarefaService tarefasService;

    @PostMapping
    public ResponseEntity <TarefasDTO> gravarTarefas(@RequestBody TarefasDTO dto,
                                                     @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(tarefasService.gravarTarefa(token, dto));
    }
}
