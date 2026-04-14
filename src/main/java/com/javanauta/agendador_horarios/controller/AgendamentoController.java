package com.javanauta.agendador_horarios.controller;

import com.javanauta.agendador_horarios.infrastructure.entity.Agendamento;
import com.javanauta.agendador_horarios.service.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<Agendamento> salvarAgendamento(@RequestBody Agendamento agendamento) {
        return ResponseEntity.accepted().body(agendamentoService.salvarAgendamento(agendamento));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarAgendamento(@RequestParam String cliente, @RequestParam LocalDateTime dataHoraAtendimento) {
        agendamentoService.deletarAgendamento(cliente, dataHoraAtendimento);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Agendamento> buscarAgendamentoDia(@RequestParam LocalDateTime data) {
        return ResponseEntity.ok(agendamentoService.buscarAgendamentosDia(data));
    }

    @PutMapping
    public ResponseEntity<Agendamento> alterarAgendamentos(@RequestBody Agendamento agendamento,
                                                           @RequestParam String cliente,
                                                           @RequestParam LocalDateTime dataHoraAgendamento) {
        return ResponseEntity.accepted().body(agendamentoService.alterarAgendamento(agendamento,
                cliente, dataHoraAgendamento));
    }
}
