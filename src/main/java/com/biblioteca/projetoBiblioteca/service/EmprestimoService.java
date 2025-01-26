package com.biblioteca.projetoBiblioteca.service;

import com.biblioteca.projetoBiblioteca.dto.EmprestimoRequestDTO;
import com.biblioteca.projetoBiblioteca.dto.EmprestimoResponseDTO;
import com.biblioteca.projetoBiblioteca.model.Emprestimo;
import com.biblioteca.projetoBiblioteca.model.ItemEmprestimo;
import com.biblioteca.projetoBiblioteca.model.Livro;
import com.biblioteca.projetoBiblioteca.repository.EmprestimoRepository;
import com.biblioteca.projetoBiblioteca.repository.ItemEmprestimoRepository;
import com.biblioteca.projetoBiblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private ItemEmprestimoRepository itemEmprestimoRepository;

    public EmprestimoResponseDTO criarEmprestimo(EmprestimoRequestDTO requestDTO) {
        // Recuperar os livros pelo ID
        List<Livro> livros = requestDTO.getLivrosIds().stream()
                .map(id -> livroRepository.findById(id).orElseThrow(
                        () -> new IllegalArgumentException("Livro com ID " + id + " não encontrado")
                ))
                .collect(Collectors.toList());

        // Validar quantidade e atualizar o estoque
        livros.forEach(livro -> {
            if (livro.getQuantidade() <= 0) {
                throw new IllegalArgumentException("O livro " + livro.getTitulo() + " está fora de estoque");
            }
            livro.setQuantidade(livro.getQuantidade() - 1);
            livroRepository.save(livro);
        });

        // Criar e salvar o empréstimo
        Emprestimo emprestimo = new Emprestimo(requestDTO.getDataEmprestimo(), livros);
        Emprestimo savedEmprestimo = emprestimoRepository.save(emprestimo);

        // Criar e salvar itens do empréstimo
        livros.forEach(livro -> {
            ItemEmprestimo itemEmprestimo = new ItemEmprestimo(livro.getId(), savedEmprestimo.getId());
            itemEmprestimoRepository.save(itemEmprestimo);
        });

        // Retornar o DTO do empréstimo
        return new EmprestimoResponseDTO(
                savedEmprestimo.getId(),
                savedEmprestimo.getDataEmprestimo(),
                savedEmprestimo.getLivros()
        );
    }

    public List<EmprestimoResponseDTO> listarEmprestimos() {
        // Listar todos os empréstimos e converter para DTO
        return emprestimoRepository.findAll().stream()
                .map(emprestimo -> new EmprestimoResponseDTO(
                        emprestimo.getId(),
                        emprestimo.getDataEmprestimo(),
                        emprestimo.getLivros()
                ))
                .collect(Collectors.toList());
    }
}
