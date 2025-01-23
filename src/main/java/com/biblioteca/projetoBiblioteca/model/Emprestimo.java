package com.biblioteca.projetoBiblioteca.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "A data do empréstimo é obrigatória")
    private LocalDate dataEmprestimo;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "emprestimo_id")
    private List<ItemEmprestimo> itensEmprestimo;

    public Emprestimo(Long id, LocalDate dataEmprestimo, List<ItemEmprestimo> itensEmprestimo) {
        this.id = id;
        this.dataEmprestimo = dataEmprestimo;
        this.itensEmprestimo = itensEmprestimo;
    }

    public Emprestimo() {
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

    public List<ItemEmprestimo> getItensEmprestimo() {
        return itensEmprestimo;
    }

    public void setItensEmprestimo(List<ItemEmprestimo> itensEmprestimo) {
        this.itensEmprestimo = itensEmprestimo;
    }
}
