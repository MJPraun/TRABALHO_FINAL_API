package com.serratec.clinica.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Schema(description = "Dados para criação ou atualização de um Paciente")
public class PacienteRequestDTO {

    @Schema(description = "Nome completo do paciente", example = "Mário José Praun")
    @NotBlank(message = "O nome do paciente é obrigatório.")
    @Size(max = 100, message = "O nome não pode ter mais de 100 caracteres.")
    private String nome;

    @Schema(description = "CPF com 11 dígitos (apenas números)", example = "12345678901")
    @NotBlank(message = "O CPF é obrigatório.")
    @Size(min = 11, max = 11, message = "O CPF deve conter 11 dígitos.")
    private String cpf;

    @Schema(description = "E-mail do paciente", example = "mario@email.com")
    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "Insira um formato de e-mail válido.")
    private String email;

    @Schema(description = "Telefone de contato", example = "21999999999")
    @NotBlank(message = "O telefone é obrigatório.")
    private String telefone;

    @Schema(description = "Endereço residencial", example = "Rua de Teresópolis, 100")
    @NotBlank(message = "O endereço é obrigatório.")
    private String endereco;

    @Schema(description = "Data de nascimento", example = "1972-03-15")
    @NotNull(message = "A data de nascimento é obrigatória.")
    private LocalDate dataNascimento;

    @Schema(description = "Histórico médico inicial para abrir o prontuário", example = "Hipertensão leve, sem alergias.")
    @NotBlank(message = "O histórico médico inicial é obrigatório para abertura do prontuário.")
    private String historicoMedicoInicial;

    public PacienteRequestDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getHistoricoMedicoInicial() {
        return historicoMedicoInicial;
    }

    public void setHistoricoMedicoInicial(String historicoMedicoInicial) {
        this.historicoMedicoInicial = historicoMedicoInicial;
    }
}