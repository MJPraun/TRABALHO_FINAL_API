package com.serratec.clinica.controller;

import com.serratec.clinica.dto.MedicoRequestDTO;
import com.serratec.clinica.dto.MedicoResponseDTO;
import com.serratec.clinica.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @GetMapping
    public ResponseEntity<List<MedicoResponseDTO>> listar() {
        return ResponseEntity.ok(medicoService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<MedicoResponseDTO> cadastrar(@Valid @RequestBody MedicoRequestDTO requestDTO) {
        MedicoResponseDTO novoMedico = medicoService.salvar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoMedico);
    }
}