package com.serratec.clinica.repository;

import com.serratec.clinica.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);

    Optional<Paciente> findByCpf(String cpf);
    Optional<Paciente> findByEmail(String email); // Ajustado de String para Paciente
}