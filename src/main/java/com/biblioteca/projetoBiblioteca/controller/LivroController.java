package com.biblioteca.projetoBiblioteca.controller;

import com.biblioteca.projetoBiblioteca.model.Livro;
import com.biblioteca.projetoBiblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @PostMapping
    public ResponseEntity<Livro> criarLivro(@RequestBody Livro livro) {
        Livro livroSalvo = livroService.criarLivro(livro);
        return ResponseEntity.ok(livroSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizarQuantidade(@PathVariable Long id, @RequestBody Livro livroAtualizado) {
        if (livroAtualizado.getQuantidade() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Livro livro = livroService.atualizarQuantidadeLivro(id, livroAtualizado.getQuantidade());
        return ResponseEntity.ok(livro);
    }

    @GetMapping
    public ResponseEntity<List<Livro>> listarTodos() {
        List<Livro> livros = livroService.listarTodosLivros();
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable Long id) {
        Livro livro = livroService.buscarLivroPorId(id).orElseThrow(() -> new IllegalArgumentException("Livro com o id " + id + " não encontrado."));
        return ResponseEntity.ok(livro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        Livro livro = livroService.buscarLivroPorId(id).orElseThrow(() -> new IllegalArgumentException("Livro com o id " + id + " não encontrado."));
            livroService.deletarLivroPorId(id);
            return ResponseEntity.noContent().build();
        }
}
