package com.serratec.clinica.repository;

import com.serratec.clinica.domain.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    boolean existsByCrm(String crm);
    boolean existsByEmail(String email);

    Optional<Medico> findByCrm(String crm);
    Optional<Medico> findByEmail(String email);
}