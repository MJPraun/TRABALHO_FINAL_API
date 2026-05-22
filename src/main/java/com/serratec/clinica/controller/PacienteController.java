package com.serratec.clinica.controller;

import com.serratec.clinica.dto.PacienteRequestDTO;
import com.serratec.clinica.dto.PacienteResponseDTO;
import com.serratec.clinica.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> listar() {
        List<PacienteResponseDTO> lista = pacienteService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<PacienteResponseDTO> cadastrar(@Valid @RequestBody PacienteRequestDTO requestDTO) {
        PacienteResponseDTO novoPaciente = pacienteService.salvar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPaciente);
    }
}