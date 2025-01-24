package com.biblioteca.projetoBiblioteca.service;

import com.biblioteca.projetoBiblioteca.model.Livro;
import com.biblioteca.projetoBiblioteca.repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public Livro criarLivro(Livro livro) {
        return livroRepository.save(livro);
    }

    @Transactional
    public Livro atualizarQuantidadeLivro(Long id, Integer quantidadeAdicionar) {
        Livro livroExistente = livroRepository.findById(id).orElseThrow(() -> new RuntimeException("Livro não encontrado com ID " + id));

        livroExistente.setQuantidade(livroExistente.getQuantidade() + quantidadeAdicionar);

        return livroRepository.save(livroExistente);
    }

    public List<Livro> listarTodosLivros() {
        return livroRepository.findAll();
    }

    public Optional<Livro> buscarLivroPorId(Long id) {
        return livroRepository.findById(id);
    }

    public void deletarLivroPorId(Long id) {
        livroRepository.deleteById(id);
    }
}
