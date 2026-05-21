package com.example.AtividadePontuada2.controller;

import com.example.AtividadePontuada2.dto.FuncionarioRequestDTO;
import com.example.AtividadePontuada2.dto.FuncionarioResponseDTO;
import com.example.AtividadePontuada2.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;


    @GetMapping
    public ResponseEntity<List<FuncionarioResponseDTO>> listar() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.listarTodos());
    }


    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.buscarPorId(id));
    }


    @PostMapping
    public ResponseEntity<Map<String, Object>> salvar(@Valid @RequestBody FuncionarioRequestDTO dto) {
        service.salvarFuncionario(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("Mensagem", "Funcionário cadastrado com sucesso"));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> atualizar(@PathVariable Long id, @Valid @RequestBody FuncionarioRequestDTO dto) {
        service.atualizarFuncionario(id, dto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("Mensagem", "Funcionário atualizado com sucesso"));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deletar(@PathVariable Long id) {
        service.deletarFuncionario(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("Mensagem", "Funcionário deletado com sucesso"));
    }
}