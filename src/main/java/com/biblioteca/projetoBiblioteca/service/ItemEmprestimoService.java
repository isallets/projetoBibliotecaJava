package com.biblioteca.projetoBiblioteca.service;

import com.biblioteca.projetoBiblioteca.model.ItemEmprestimo;
import com.biblioteca.projetoBiblioteca.repository.ItemEmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemEmprestimoService {

    @Autowired
    private ItemEmprestimoRepository itemEmprestimoRepository;

    public List<ItemEmprestimo> listarTodos() {
        return itemEmprestimoRepository.findAll();
    }

    public ItemEmprestimo buscarPorId(Long id) {
        Optional<ItemEmprestimo> itemEmprestimo = itemEmprestimoRepository.findById(id);
        return itemEmprestimo.orElse(null);
    }
/*
    public boolean deletarPorId(Long id) {
        if (itemEmprestimoRepository.existsById(id)) {
            itemEmprestimoRepository.deleteById(id);
            return true;
        }
        return false;
    }
 */
}
