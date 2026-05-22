package com.serratec.clinica.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import java.util.List;

@Schema(description = "Dados para criação ou atualização de um Médico")
public class MedicoRequestDTO {

    @Schema(description = "Nome completo do médico", example = "Dr. Carlos Eduardo")
    @NotBlank(message = "O nome do médico é obrigatório.")
    @Size(max = 100, message = "O nome não pode ultrapassar 100 caracteres.")
    private String nome;

    @Schema(description = "Número do CRM", example = "CRM/RJ 123456")
    @NotBlank(message = "O CRM é obrigatório.")
    private String crm;

    @Schema(description = "E-mail profissional", example = "carlos.medico@email.com")
    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "Insira um formato de e-mail válido.")
    private String email;

    @Schema(description = "Lista de IDs das especialidades associadas ao médico", example = "[1, 2]")
    @NotEmpty(message = "O médico deve possuir pelo menos uma especialidade.")
    private List<Long> especialidadesIds;

    public MedicoRequestDTO() {
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

    public List<Long> getEspecialidadesIds() {
        return especialidadesIds;
    }

    public void setEspecialidadesIds(List<Long> especialidadesIds) {
        this.especialidadesIds = especialidadesIds;
    }
}