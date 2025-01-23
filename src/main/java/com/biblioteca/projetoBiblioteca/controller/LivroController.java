package com.biblioteca.projetoBiblioteca.controller;

import com.biblioteca.projetoBiblioteca.model.Livro;
import com.biblioteca.projetoBiblioteca.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @PostMapping
    public ResponseEntity<Livro> criarLivro(@RequestBody Livro livro) {
        Livro livroSalvo = livroService.salvarLivro(livro);
        return ResponseEntity.ok(livroSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizarQuantidade(@PathVariable Long id, @RequestBody @Valid Livro livroAtualizado) {
        Livro livro = livroService.atualizarQuantidade(id, livroAtualizado.getQuantidade());
        return ResponseEntity.ok(livro);
    }

    @GetMapping
    public ResponseEntity<List<Livro>> listarTodos() {
        List<Livro> livros = livroService.listarTodos();
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable Long id) {
        Optional<Livro> livro = livroService.buscarPorId(id);
        return livro.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        Optional<Livro> livro = livroService.buscarPorId(id);
        if (livro.isPresent()) {
            livroService.deletarPorId(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
