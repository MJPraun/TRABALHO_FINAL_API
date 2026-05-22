package com.serratec.clinica.controller;

import com.serratec.clinica.dto.MedicoRequestDTO;
import com.serratec.clinica.dto.MedicoResponseDTO;
import com.serratec.clinica.service.MedicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Médico", description = "Endpoints para gerenciamento de médicos")
@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @Operation(summary = "Listar todos os médicos", description = "Retorna uma lista contendo todos os médicos cadastrados e suas especialidades.")
    @GetMapping
    public ResponseEntity<List<MedicoResponseDTO>> listar() {
        return ResponseEntity.ok(medicoService.listarTodos());
    }

    @Operation(summary = "Buscar médico por ID", description = "Busca os detalhes completos de um médico específico através do ID informado.")
    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(medicoService.buscarPorId(id));
    }

    @Operation(summary = "Cadastrar novo médico", description = "Cadastra um médico no sistema e o vincula a especialidades existentes.")
    @PostMapping
    public ResponseEntity<MedicoResponseDTO> cadastrar(@Valid @RequestBody MedicoRequestDTO requestDTO) {
        MedicoResponseDTO novoMedico = medicoService.salvar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoMedico);
    }

    @Operation(summary = "Atualizar um médico existente", description = "Atualiza os dados de um médico com base no ID fornecido.")
    @PutMapping("/{id}")
    public ResponseEntity<MedicoResponseDTO> atualizar(@PathVariable Long id,
            @Valid @RequestBody MedicoRequestDTO requestDTO) {
        return ResponseEntity.ok(medicoService.atualizar(id, requestDTO));
    }

    @Operation(summary = "Remover um médico", description = "Exclui permanentemente um médico do sistema pelo ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        medicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}