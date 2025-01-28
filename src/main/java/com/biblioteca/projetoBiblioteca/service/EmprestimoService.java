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

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private ItemEmprestimoRepository itemEmprestimoRepository;

    public EmprestimoResponseDTO criarEmprestimo(EmprestimoRequestDTO requestDTO) {
        List<Livro> livros = requestDTO.getLivrosIds().stream().map(id -> livroRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Livro com ID " + id + " não encontrado"))).toList();

        livros.forEach(livro -> {
            if (livro.getQuantidade() <= 0) {
                throw new IllegalArgumentException("O livro " + livro.getTitulo() + " está fora de estoque");
            }livro.setQuantidade(livro.getQuantidade() - 1);
            livroRepository.save(livro);
        });

        Emprestimo emprestimo = new Emprestimo(requestDTO.getDataEmprestimo(), livros);
        Emprestimo savedEmprestimo = emprestimoRepository.save(emprestimo);

        livros.forEach(livro -> {
            ItemEmprestimo itemEmprestimo = new ItemEmprestimo(livro.getId(), savedEmprestimo.getId());
            itemEmprestimoRepository.save(itemEmprestimo);
        });

        return new EmprestimoResponseDTO(
                savedEmprestimo.getId(),
                savedEmprestimo.getDataEmprestimo(),
                savedEmprestimo.getLivros()
        );
    }

    public List<EmprestimoResponseDTO> listarEmprestimos() {
        return emprestimoRepository.findAll().stream().map(emprestimo -> new EmprestimoResponseDTO(emprestimo.getId(), emprestimo.getDataEmprestimo(), emprestimo.getLivros())).toList();
    }

    public EmprestimoResponseDTO atualizarEmprestimo(Long emprestimoId, List<Long> novosLivrosIds) {
        Emprestimo emprestimo = emprestimoRepository.findById(emprestimoId).orElseThrow(() -> new IllegalArgumentException("Empréstimo com ID " + emprestimoId + " não encontrado"));

        List<Livro> novosLivros = novosLivrosIds.stream().map(id -> livroRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Livro com ID " + id + " não encontrado"))).toList();

        novosLivros.forEach(livro -> {
            if (livro.getQuantidade() <= 0) {
                throw new IllegalArgumentException("O livro " + livro.getTitulo() + " está fora de estoque");
            }livro.setQuantidade(livro.getQuantidade() - 1);
            livroRepository.save(livro);
        });

        emprestimo.getLivros().addAll(novosLivros);
        Emprestimo emprestimoAtualizado = emprestimoRepository.save(emprestimo);

        return new EmprestimoResponseDTO(
                emprestimoAtualizado.getId(),
                emprestimoAtualizado.getDataEmprestimo(),
                emprestimoAtualizado.getLivros()
        );
    }

    public void deletarEmprestimo(Long id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Empréstimo com ID " + id + " não encontrado"));

        emprestimo.getLivros().forEach(livro -> {
            livro.setQuantidade(livro.getQuantidade() + 1);
            livroRepository.save(livro);
        });

        emprestimoRepository.delete(emprestimo);
    }
}
