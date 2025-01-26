package com.biblioteca.projetoBiblioteca.model;

import jakarta.persistence.*;

@Entity
public class ItemEmprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idLivro;

    private Long idEmprestimo;

    public ItemEmprestimo() {}

    public ItemEmprestimo(Long idLivro, Long idEmprestimo) {
        this.idLivro = idLivro;
        this.idEmprestimo = idEmprestimo;
    }

    public ItemEmprestimo(Long id, Long idLivro, Long idEmprestimo) {
        this.id = id;
        this.idLivro = idLivro;
        this.idEmprestimo = idEmprestimo;
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
