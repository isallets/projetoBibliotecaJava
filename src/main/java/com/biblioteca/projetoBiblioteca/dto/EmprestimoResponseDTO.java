package com.biblioteca.projetoBiblioteca.dto;

import com.biblioteca.projetoBiblioteca.model.Livro;

import java.time.LocalDate;
import java.util.List;

public class EmprestimoResponseDTO {
    private Long id;
    private LocalDate dataEmprestimo;
    private List<Livro> livros;

    public EmprestimoResponseDTO(Long id, LocalDate dataEmprestimo, List<Livro> livros) {
        this.id = id;
        this.dataEmprestimo = dataEmprestimo;
        this.livros = livros;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
}
