package com.serratec.clinica.dto;

import jakarta.validation.constraints.*;
import java.util.List;

public class MedicoRequestDTO {

    @NotBlank(message = "O nome do médico é obrigatório.")
    @Size(max = 100, message = "O nome não pode ultrapassar 100 caracteres.")
    private String nome;

    @NotBlank(message = "O CRM é obrigatório.")
    private String crm;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "Insira um formato de e-mail válido.")
    private String email;

    @NotEmpty(message = "O médico deve possuir pelo menos uma especialidade.")
    private List<Long> especialidadesIds; // Recebe os IDs das especialidades para vincular no banco

    public MedicoRequestDTO() {
    }

    public MedicoRequestDTO(String nome, String crm, String email, List<Long> especialidadesIds) {
        this.nome = nome;
        this.crm = crm;
        this.email = email;
        this.especialidadesIds = especialidadesIds;
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