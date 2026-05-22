package com.serratec.clinica.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.FutureOrPresent;
import java.time.LocalDateTime;

@Schema(description = "Dados para agendamento ou atualização de uma Consulta")
public class ConsultaRequestDTO {

    @Schema(description = "Data e hora do agendamento (não pode ser no passado)", example = "2026-06-15T14:30:00")
    @NotNull(message = "A data e hora da consulta são obrigatórias.")
    @FutureOrPresent(message = "A consulta não pode ser agendada em uma data passada.")
    private LocalDateTime dataHora;

    @Schema(description = "Observações médicas ou detalhes da queixa", example = "Retorno de rotina para avaliação de exames.")
    private String observacoes;

    @Schema(description = "ID do Paciente já cadastrado", example = "1")
    @NotNull(message = "O ID do paciente é obrigatório.")
    private Long pacienteId;

    @Schema(description = "ID do Médico já cadastrado", example = "1")
    @NotNull(message = "O ID do médico é obrigatório.")
    private Long medicoId;

    @Schema(description = "Status atual da consulta (usado principalmente no PUT)", example = "CANCELADA")
    private String status;

    public ConsultaRequestDTO() {
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Long getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Long medicoId) {
        this.medicoId = medicoId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}