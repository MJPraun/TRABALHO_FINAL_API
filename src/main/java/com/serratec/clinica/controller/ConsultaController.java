package com.serratec.clinica.controller;

import com.serratec.clinica.dto.ConsultaRequestDTO;
import com.serratec.clinica.dto.ConsultaResponseDTO;
import com.serratec.clinica.service.ConsultaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Consulta", description = "Endpoints para agendamento e controle de consultas")
@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @Operation(summary = "Listar todas as consultas", description = "Retorna o histórico completo de consultas agendadas, realizadas ou canceladas.")
    @GetMapping
    public ResponseEntity<List<ConsultaResponseDTO>> listar() {
        return ResponseEntity.ok(consultaService.listarTodas());
    }

    @Operation(summary = "Buscar consulta por ID", description = "Recupera as informações detalhadas de um agendamento específico pelo ID.")
    @GetMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.buscarPorId(id));
    }

    @Operation(summary = "Agendar nova consulta", description = "Cria um novo agendamento vinculando um paciente e um médico existentes.")
    @PostMapping
    public ResponseEntity<ConsultaResponseDTO> cadastrar(@Valid @RequestBody ConsultaRequestDTO requestDTO) {
        ConsultaResponseDTO novaConsulta = consultaService.salvar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaConsulta);
    }

    @Operation(summary = "Atualizar dados de uma consulta", description = "Permite alterar a data, observações ou modificar o status (ex: CANCELADA) da consulta.")
    @PutMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> atualizar(@PathVariable Long id,
            @Valid @RequestBody ConsultaRequestDTO requestDTO) {
        return ResponseEntity.ok(consultaService.atualizar(id, requestDTO));
    }

    @Operation(summary = "Remover um agendamento", description = "Exclui permanentemente o registro da consulta do sistema.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        consultaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}