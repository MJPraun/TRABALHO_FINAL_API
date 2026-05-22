package com.serratec.clinica.service;

import com.serratec.clinica.domain.Paciente;
import com.serratec.clinica.domain.Prontuario;
import com.serratec.clinica.dto.PacienteRequestDTO;
import com.serratec.clinica.dto.PacienteResponseDTO;
import com.serratec.clinica.exception.DuplicateEntryException;
import com.serratec.clinica.exception.ResourceNotFoundException;
import com.serratec.clinica.repository.PacienteRepository;
import com.serratec.clinica.repository.ProntuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final ProntuarioRepository prontuarioRepository;

    public PacienteService(PacienteRepository pacienteRepository, ProntuarioRepository prontuarioRepository) {
        this.pacienteRepository = pacienteRepository;
        this.prontuarioRepository = prontuarioRepository;
    }

    public List<PacienteResponseDTO> listarTodos() {
        return pacienteRepository.findAll().stream()
                .map(PacienteResponseDTO::new)
                .collect(Collectors.toList());
    }

    public PacienteResponseDTO buscarPorId(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com o ID: " + id));
        return new PacienteResponseDTO(paciente);
    }

    @Transactional
    public PacienteResponseDTO salvar(PacienteRequestDTO requestDTO) {
        if (pacienteRepository.existsByCpf(requestDTO.getCpf())) {
            throw new DuplicateEntryException("Já existe um paciente cadastrado com este CPF.");
        }
        if (pacienteRepository.existsByEmail(requestDTO.getEmail())) {
            throw new DuplicateEntryException("Já existe um paciente cadastrado com este E-mail.");
        }

        Prontuario prontuario = new Prontuario();
        prontuario.setDataAbertura(LocalDate.now());
        prontuario.setHistoricoMedico(requestDTO.getHistoricoMedicoInicial());
        prontuario = prontuarioRepository.save(prontuario);

        Paciente paciente = new Paciente();
        paciente.setNome(requestDTO.getNome());
        paciente.setCpf(requestDTO.getCpf());
        paciente.setEmail(requestDTO.getEmail());
        paciente.setTelefone(requestDTO.getTelefone());
        paciente.setEndereco(requestDTO.getEndereco());
        paciente.setDataNascimento(requestDTO.getDataNascimento());
        paciente.setProntuario(prontuario);

        paciente = pacienteRepository.save(paciente);
        return new PacienteResponseDTO(paciente);
    }

    @Transactional
    public PacienteResponseDTO atualizar(Long id, PacienteRequestDTO requestDTO) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com o ID: " + id));

        // Valida e evita e-mail ou CPF duplicados de outros pacientes
        pacienteRepository.findByCpf(requestDTO.getCpf()).ifPresent(p -> {
            if (!p.getId().equals(id)) throw new DuplicateEntryException("Este CPF já está sendo usado por outro paciente.");
        });
        pacienteRepository.findByEmail(requestDTO.getEmail()).ifPresent(p -> {
            if (!p.getId().equals(id)) throw new DuplicateEntryException("Este E-mail já está sendo usado por outro paciente.");
        });

        paciente.setNome(requestDTO.getNome());
        paciente.setCpf(requestDTO.getCpf());
        paciente.setEmail(requestDTO.getEmail());
        paciente.setTelefone(requestDTO.getTelefone());
        paciente.setEndereco(requestDTO.getEndereco());
        paciente.setDataNascimento(requestDTO.getDataNascimento());

        if (paciente.getProntuario() != null) {
            paciente.getProntuario().setHistoricoMedico(requestDTO.getHistoricoMedicoInicial());
        }

        paciente = pacienteRepository.save(paciente);
        return new PacienteResponseDTO(paciente);
    }

    public void deletar(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Paciente não encontrado com o ID: " + id);
        }
        pacienteRepository.deleteById(id);
    }
}