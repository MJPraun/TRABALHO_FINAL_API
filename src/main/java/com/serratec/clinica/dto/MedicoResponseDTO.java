package com.serratec.clinica.dto;

import com.serratec.clinica.domain.Medico;
import java.util.List;
import java.util.stream.Collectors;

public class MedicoResponseDTO {

    private Long id;
    private String nome;
    private String crm;
    private String email;
    private List<String> especialidades;

    public MedicoResponseDTO() {
    }

    public MedicoResponseDTO(Medico medico) {
        this.id = medico.getId();
        this.nome = medico.getNome();
        this.crm = medico.getCrm();
        this.email = medico.getEmail();
        this.especialidades = medico.getEspecialidades().stream()
                .map(e -> e.getNome())
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<String> especialidades) {
        this.especialidades = especialidades;
    }
}