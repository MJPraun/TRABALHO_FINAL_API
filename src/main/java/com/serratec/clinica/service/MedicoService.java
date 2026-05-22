package com.serratec.clinica.service;

import com.serratec.clinica.domain.Especialidade;
import com.serratec.clinica.domain.Medico;
import com.serratec.clinica.dto.MedicoRequestDTO;
import com.serratec.clinica.dto.MedicoResponseDTO;
import com.serratec.clinica.exception.DuplicateEntryException;
import com.serratec.clinica.exception.ResourceNotFoundException;
import com.serratec.clinica.repository.EspecialidadeRepository;
import com.serratec.clinica.repository.MedicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;
    private final EspecialidadeRepository especialidadeRepository;

    public MedicoService(MedicoRepository medicoRepository, EspecialidadeRepository especialidadeRepository) {
        this.medicoRepository = medicoRepository;
        this.especialidadeRepository = especialidadeRepository;
    }

    public List<MedicoResponseDTO> listarTodos() {
        return medicoRepository.findAll().stream()
                .map(MedicoResponseDTO::new)
                .collect(Collectors.toList());
    }

    public MedicoResponseDTO buscarPorId(Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado com o ID: " + id));
        return new MedicoResponseDTO(medico);
    }

    @Transactional
    public MedicoResponseDTO salvar(MedicoRequestDTO requestDTO) {
        if (medicoRepository.existsByCrm(requestDTO.getCrm())) {
            throw new DuplicateEntryException("Já existe um médico cadastrado com este CRM.");
        }
        if (medicoRepository.existsByEmail(requestDTO.getEmail())) {
            throw new DuplicateEntryException("Já existe um médico cadastrado com este E-mail.");
        }

        Medico medico = new Medico();
        medico.setNome(requestDTO.getNome());
        medico.setCrm(requestDTO.getCrm());
        medico.setEmail(requestDTO.getEmail());

        List<Especialidade> especialidades = especialidadeRepository.findAllById(requestDTO.getEspecialidadesIds());
        medico.setEspecialidades(especialidades);

        medico = medicoRepository.save(medico);
        return new MedicoResponseDTO(medico);
    }

    @Transactional
    public MedicoResponseDTO atualizar(Long id, MedicoRequestDTO requestDTO) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado com o ID: " + id));

        medicoRepository.findByCrm(requestDTO.getCrm()).ifPresent(m -> {
            if (!m.getId().equals(id))
                throw new DuplicateEntryException("Este CRM já está sendo usado por outro médico.");
        });
        medicoRepository.findByEmail(requestDTO.getEmail()).ifPresent(m -> {
            if (!m.getId().equals(id))
                throw new DuplicateEntryException("Este E-mail já está sendo usado por outro médico.");
        });

        medico.setNome(requestDTO.getNome());
        medico.setCrm(requestDTO.getCrm());
        medico.setEmail(requestDTO.getEmail());

        List<Especialidade> especialidades = especialidadeRepository.findAllById(requestDTO.getEspecialidadesIds());
        medico.setEspecialidades(especialidades);

        medico = medicoRepository.save(medico);
        return new MedicoResponseDTO(medico);
    }

    public void deletar(Long id) {
        if (!medicoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Médico não encontrado com o ID: " + id);
        }
        medicoRepository.deleteById(id);
    }
}