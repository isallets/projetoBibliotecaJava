package com.biblioteca.projetoBiblioteca.service;

import com.biblioteca.projetoBiblioteca.model.ItemEmprestimo;
import com.biblioteca.projetoBiblioteca.model.Livro;
import com.biblioteca.projetoBiblioteca.repository.ItemEmprestimoRepository;
import com.biblioteca.projetoBiblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemEmprestimoService {

    @Autowired
    private ItemEmprestimoRepository itemEmprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;

    public List<ItemEmprestimo> listarTodos() {
        return itemEmprestimoRepository.findAll();
    }

    public ItemEmprestimo salvar(ItemEmprestimo itemEmprestimo) {
        Livro livro = livroRepository.findById(itemEmprestimo.getIdLivro()).orElseThrow(() -> new RuntimeException("Livro não encontrado com o ID: " + itemEmprestimo.getIdLivro()));

        if (livro.getQuantidade() <= 0) {
            throw new RuntimeException("Não há exemplares disponíveis para empréstimo.");
        }

        livro.setQuantidade(livro.getQuantidade() - 1);

        livroRepository.save(livro);

        return itemEmprestimoRepository.save(itemEmprestimo);
    }

    public ItemEmprestimo buscarPorId(Long id) {
        return itemEmprestimoRepository.findById(id).orElseThrow(() -> new RuntimeException("Item de empréstimo não encontrado com o ID: " + id));
    }

    public void deletar(Long id) {
        itemEmprestimoRepository.deleteById(id);
    }
}
