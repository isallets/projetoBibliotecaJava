package com.biblioteca.projetoBiblioteca.repository;

import com.biblioteca.projetoBiblioteca.model.ItemEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemEmprestimoRepository extends JpaRepository<ItemEmprestimo, Long> {
}
