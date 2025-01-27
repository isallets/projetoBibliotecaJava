package com.biblioteca.projetoBiblioteca.controller;

import com.biblioteca.projetoBiblioteca.dto.EmprestimoRequestDTO;
import com.biblioteca.projetoBiblioteca.dto.EmprestimoResponseDTO;
import com.biblioteca.projetoBiblioteca.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @PostMapping
    public ResponseEntity<EmprestimoResponseDTO> criarEmprestimo(@RequestBody EmprestimoRequestDTO emprestimoRequestDTO) {
        try {
            EmprestimoResponseDTO emprestimo = emprestimoService.criarEmprestimo(emprestimoRequestDTO);
            return ResponseEntity.ok(emprestimo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public List<EmprestimoResponseDTO> listarEmprestimos() {
        return emprestimoService.listarEmprestimos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmprestimoResponseDTO> atualizarEmprestimo(
            @PathVariable Long id,
            @RequestBody List<Long> novosLivrosIds
    ) {
        try {
            EmprestimoResponseDTO emprestimoAtualizado = emprestimoService.atualizarEmprestimo(id, novosLivrosIds);
            return ResponseEntity.ok(emprestimoAtualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmprestimo(@PathVariable Long id) {
        try {
            emprestimoService.deletarEmprestimo(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
