package com.biblioteca.projetoBiblioteca.service;

import com.biblioteca.projetoBiblioteca.model.Emprestimo;
import com.biblioteca.projetoBiblioteca.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    public List<Emprestimo> listarTodos() {
        return emprestimoRepository.findAll();
    }

    public Emprestimo salvar(Emprestimo emprestimo) {
        return emprestimoRepository.save(emprestimo);
    }

    public Emprestimo buscarPorId(Long id) {
        return emprestimoRepository.findById(id).orElseThrow(() -> new RuntimeException("Empréstimo não encontrado com o ID: " + id));
    }

    public void deletar(Long id) {
        emprestimoRepository.deleteById(id);
    }
}
