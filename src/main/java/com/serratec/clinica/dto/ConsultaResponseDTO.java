package com.serratec.clinica.dto;

import com.serratec.clinica.domain.Consulta;
import java.time.LocalDateTime;

public class ConsultaResponseDTO {

    private Long id;
    private LocalDateTime dataHora;
    private String status;
    private String observacoes;
    private String nomePaciente;
    private String nomeMedico;

    public ConsultaResponseDTO() {
    }

    public ConsultaResponseDTO(Consulta consulta) {
        this.id = consulta.getId();
        this.dataHora = consulta.getDataHora();
        this.status = consulta.getStatus();
        this.observacoes = consulta.getObservacoes();
        this.nomePaciente = consulta.getPaciente().getNome();
        this.nomeMedico = consulta.getMedico().getNome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }
}