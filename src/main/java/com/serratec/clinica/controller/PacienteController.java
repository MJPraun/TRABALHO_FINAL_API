package com.serratec.clinica.controller;

import com.serratec.clinica.dto.PacienteRequestDTO;
import com.serratec.clinica.dto.PacienteResponseDTO;
import com.serratec.clinica.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Paciente", description = "Endpoints para gerenciamento de pacientes")
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @Operation(summary = "Listar todos os pacientes", description = "Retorna uma lista contendo todos os pacientes cadastrados.")
    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> listar() {
        return ResponseEntity.ok(pacienteService.listarTodos());
    }

    @Operation(summary = "Buscar paciente por ID", description = "Busca os detalhes de um paciente específico através do ID informado.")
    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }

    @Operation(summary = "Cadastrar novo paciente", description = "Cadastra um paciente e cria automaticamente o seu prontuário clínico.")
    @PostMapping
    public ResponseEntity<PacienteResponseDTO> cadastrar(@Valid @RequestBody PacienteRequestDTO requestDTO) {
        PacienteResponseDTO novoPaciente = pacienteService.salvar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPaciente);
    }

    @Operation(summary = "Atualizar um paciente existente", description = "Atualiza os dados de um paciente com base no ID fornecido.")
    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> atualizar(@PathVariable Long id,
            @Valid @RequestBody PacienteRequestDTO requestDTO) {
        return ResponseEntity.ok(pacienteService.atualizar(id, requestDTO));
    }

    @Operation(summary = "Remover um paciente", description = "Exclui permanentemente um paciente do sistema pelo ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pacienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}