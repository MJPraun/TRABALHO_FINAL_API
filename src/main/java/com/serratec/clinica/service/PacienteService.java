package com.serratec.clinica.service;

import com.serratec.clinica.domain.Paciente;
import com.serratec.clinica.dto.PacienteRequestDTO;
import com.serratec.clinica.dto.PacienteResponseDTO;
import com.serratec.clinica.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<PacienteResponseDTO> listarTodos() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        return pacientes.stream()
                .map(PacienteResponseDTO::new)
                .collect(Collectors.toList());
    }

    public PacienteResponseDTO salvar(PacienteRequestDTO requestDTO) {
        Paciente paciente = new Paciente();
        paciente.setNome(requestDTO.getNome());
        paciente.setCpf(requestDTO.getCpf());
        paciente.setEmail(requestDTO.getEmail());
        paciente.setTelefone(requestDTO.getTelefone());
        paciente.setEndereco(requestDTO.getEndereco());
        paciente.setDataNascimento(requestDTO.getDataNascimento());

        paciente = pacienteRepository.save(paciente);
        return new PacienteResponseDTO(paciente);
    }
}