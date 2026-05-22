package com.serratec.clinica.service;

import com.serratec.clinica.domain.Especialidade;
import com.serratec.clinica.domain.Medico;
import com.serratec.clinica.dto.MedicoRequestDTO;
import com.serratec.clinica.dto.MedicoResponseDTO;
import com.serratec.clinica.repository.EspecialidadeRepository;
import com.serratec.clinica.repository.MedicoRepository;
import org.springframework.stereotype.Service;

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

    public MedicoResponseDTO salvar(MedicoRequestDTO requestDTO) {
        Medico medico = new Medico();
        medico.setNome(requestDTO.getNome());
        medico.setCrm(requestDTO.getCrm());
        medico.setEmail(requestDTO.getEmail());

        List<Especialidade> especialidades = especialidadeRepository.findAllById(requestDTO.getEspecialidadesIds());
        medico.setEspecialidades(especialidades);

        medico = medicoRepository.save(medico);
        return new MedicoResponseDTO(medico);
    }
}