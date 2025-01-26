package com.biblioteca.projetoBiblioteca.controller;

import com.biblioteca.projetoBiblioteca.model.ItemEmprestimo;
import com.biblioteca.projetoBiblioteca.service.ItemEmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itensEmprestimo")
public class ItemEmprestimoController {

    @Autowired
    private ItemEmprestimoService itemEmprestimoService;

    @GetMapping
    public List<ItemEmprestimo> listarTodos() {
        return itemEmprestimoService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<ItemEmprestimo> salvar(@RequestBody ItemEmprestimo itemEmprestimo) {
        return ResponseEntity.ok(itemEmprestimoService.salvar(itemEmprestimo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemEmprestimo> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(itemEmprestimoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        itemEmprestimoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
