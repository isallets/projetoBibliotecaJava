package com.biblioteca.projetoBiblioteca.controller;

import com.biblioteca.projetoBiblioteca.model.ItemEmprestimo;
import com.biblioteca.projetoBiblioteca.service.ItemEmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itemEmprestimos")
public class ItemEmprestimoController {

    @Autowired
    private ItemEmprestimoService itemEmprestimoService;

    @GetMapping
    public List<ItemEmprestimo> listarTodos() {
        return itemEmprestimoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemEmprestimo> buscarPorId(@PathVariable Long id) {
        ItemEmprestimo itemEmprestimo = itemEmprestimoService.buscarPorId(id);

        if (itemEmprestimo != null) {
            return ResponseEntity.ok(itemEmprestimo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
/*
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        boolean deletado = itemEmprestimoService.deletarPorId(id);
        return deletado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
 */
}
