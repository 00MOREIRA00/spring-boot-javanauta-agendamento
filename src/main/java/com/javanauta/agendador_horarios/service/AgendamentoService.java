package com.javanauta.agendador_horarios.service;


import com.javanauta.agendador_horarios.infrastructure.entity.Agendamento;
import com.javanauta.agendador_horarios.infrastructure.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor; 
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    public Agendamento salvarAgendamento(Agendamento agendamento) {

        LocalDateTime horaAgendamento = agendamento.getDataHoraAgendamento();
        LocalDateTime horaFim = agendamento.getDataHoraAgendamento().plusHours(1);

        Agendamento agendados = agendamentoRepository.findByServicoAndDataHoraAgendamentoBetween(
                agendamento.getServico(),
                horaAgendamento,
                horaFim
        );

        if (Objects.nonNull(agendados)) {
            throw new RuntimeException("Já existe um agendamento para esse serviço nesse horário.");
        }

        return agendamentoRepository.save(agendamento);
    }

    public void deletarAgendamento(String cliente, LocalDateTime dataHoraAgendamento) {

        agendamentoRepository.deleteByDataHoraAgendamentoAndCliente(dataHoraAgendamento, cliente);
    }

    public List<Agendamento> buscarAgendamentosDia(LocalDate data){
        LocalDateTime primeiraHoraDia = data.atStartOfDay();
        LocalDateTime horaFinalDia = data.atTime(LocalTime.MAX);

        return agendamentoRepository.findByDataHoraAgendamentoBetween(primeiraHoraDia, horaFinalDia);
    }

    public Agendamento alterarAgendamento(Agendamento agendamento, String cliente, LocalDateTime dataHoraAgendamento) {
        Agendamento agenda = agendamentoRepository.findByDataHoraAgendamentoAndCliente(dataHoraAgendamento, cliente);

        if (Objects.isNull(agenda)) {
            throw new RuntimeException("Agendamento não encontrado para o cliente e horário especificados.");
        }

        agendamento.setId(agenda.getId());
        return agendamentoRepository.save(agendamento);
    }
}








// O RequiredArgsConstructor é uma anotação do Lombok que gera um construtor com argumentos para todos os campos finais (final) ou campos marcados como @NonNull. Isso é útil para injeção de dependências, onde você pode usar o construtor gerado para injetar as dependências necessárias na classe. No caso do AgendamentoService, se houvesse algum campo final ou marcado como @NonNull, o Lombok geraria automaticamente um construtor para esses campos, facilitando a criação de instâncias da classe e a injeção de dependências.