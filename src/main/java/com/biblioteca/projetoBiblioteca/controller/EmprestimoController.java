package com.biblioteca.projetoBiblioteca.controller;

import com.biblioteca.projetoBiblioteca.model.Emprestimo;
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

    @GetMapping
    public List<Emprestimo> listarTodos() {
        return emprestimoService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<Emprestimo> salvar(@RequestBody Emprestimo emprestimo) {
        return ResponseEntity.ok(emprestimoService.salvar(emprestimo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(emprestimoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        emprestimoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
