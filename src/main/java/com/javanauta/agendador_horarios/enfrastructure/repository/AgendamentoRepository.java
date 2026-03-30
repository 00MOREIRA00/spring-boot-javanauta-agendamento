package com.javanauta.agendador_horarios.enfrastructure.repository;

import com.javanauta.agendador_horarios.enfrastructure.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

}
