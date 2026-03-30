package com.javanauta.agendador_horarios.enfrastructure.repository;

import com.javanauta.agendador_horarios.enfrastructure.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

// Adicionamos como parametro do metodo JPA a classe Agendamento e o tipo do ID que é Long
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

}
