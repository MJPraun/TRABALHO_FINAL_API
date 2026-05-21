package com.serratec.clinica.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prontuario")
public class Prontuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String historicoMedico;

    @Column(nullable = false)
    private LocalDate dataAbertura;
 
    @OneToOne(mappedBy = "prontuario")
    private Paciente paciente;

    public Prontuario() {
    }

    public Prontuario(Long id, String historicoMedico, 
        LocalDate dataAbertura, Paciente paciente) {
        this.id = id;
        this.historicoMedico = historicoMedico;
        this.dataAbertura = dataAbertura;
        this.paciente = paciente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHistoricoMedico() {
        return historicoMedico;
    }

    public void setHistoricoMedico(String historicoMedico) {
        this.historicoMedico = historicoMedico;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}