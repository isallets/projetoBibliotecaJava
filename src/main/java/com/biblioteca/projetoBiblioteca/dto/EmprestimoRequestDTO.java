package com.biblioteca.projetoBiblioteca.dto;

import java.time.LocalDate;
import java.util.List;

public class EmprestimoRequestDTO {
    private LocalDate dataEmprestimo;
    private List<Long> livrosIds;

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public List<Long> getLivrosIds() {
        return livrosIds;
    }

    public void setLivrosIds(List<Long> livrosIds) {
        this.livrosIds = livrosIds;
    }
}
