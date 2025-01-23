package com.biblioteca.projetoBiblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class ItemEmprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "O ID do livro é obrigatório")
    private Long idLivro;

    @NotNull(message = "O ID do empréstimo é obrigatório")
    private Long idEmprestimo;

    public ItemEmprestimo(Long id, Long idLivro, Long idEmprestimo) {
        this.id = id;
        this.idLivro = idLivro;
        this.idEmprestimo = idEmprestimo;
    }

    public ItemEmprestimo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Long idLivro) {
        this.idLivro = idLivro;
    }

    public Long getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(Long idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }
}
