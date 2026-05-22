package com.serratec.clinica.service;

import com.serratec.clinica.domain.Consulta;
import com.serratec.clinica.domain.Medico;
import com.serratec.clinica.domain.Paciente;
import com.serratec.clinica.dto.ConsultaRequestDTO;
import com.serratec.clinica.dto.ConsultaResponseDTO;
import com.serratec.clinica.exception.ResourceNotFoundException;
import com.serratec.clinica.repository.ConsultaRepository;
import com.serratec.clinica.repository.MedicoRepository;
import com.serratec.clinica.repository.PacienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    public ConsultaService(ConsultaRepository consultaRepository, PacienteRepository pacienteRepository, MedicoRepository medicoRepository) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
    }

    public List<ConsultaResponseDTO> listarTodas() {
        return consultaRepository.findAll().stream()
                .map(ConsultaResponseDTO::new)
                .collect(Collectors.toList());
    }

    public ConsultaResponseDTO buscarPorId(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada com o ID: " + id));
        return new ConsultaResponseDTO(consulta); 
    }

    @Transactional
    public ConsultaResponseDTO salvar(ConsultaRequestDTO requestDTO) {
        Paciente paciente = pacienteRepository.findById(requestDTO.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado. ID: " + requestDTO.getPacienteId()));

        Medico medico = medicoRepository.findById(requestDTO.getMedicoId())
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado. ID: " + requestDTO.getMedicoId()));

        Consulta consulta = new Consulta();
        consulta.setDataHora(requestDTO.getDataHora());
        consulta.setObservacoes(requestDTO.getObservacoes());
        consulta.setStatus("AGENDADA"); 
        consulta.setPaciente(paciente);
        consulta.setMedico(medico);

        consulta = consultaRepository.save(consulta);
        return new ConsultaResponseDTO(consulta);
    }

    @Transactional
    public ConsultaResponseDTO atualizar(Long id, ConsultaRequestDTO requestDTO) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada com o ID: " + id));

        Paciente paciente = pacienteRepository.findById(requestDTO.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado. ID: " + requestDTO.getPacienteId()));

        Medico medico = medicoRepository.findById(requestDTO.getMedicoId())
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado. ID: " + requestDTO.getMedicoId()));

        consulta.setDataHora(requestDTO.getDataHora());
        consulta.setObservacoes(requestDTO.getObservacoes());
        
        if (requestDTO.getStatus() != null && !requestDTO.getStatus().isBlank()) {
            consulta.setStatus(requestDTO.getStatus().toUpperCase());
        }
        
        consulta.setPaciente(paciente);
        consulta.setMedico(medico);

        consulta = consultaRepository.save(consulta);
        return new ConsultaResponseDTO(consulta);
    }

    public void deletar(Long id) {
        if (!consultaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Consulta não encontrada com o ID: " + id);
        }
        consultaRepository.deleteById(id);
    }
}