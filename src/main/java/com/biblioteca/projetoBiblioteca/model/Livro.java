package com.biblioteca.projetoBiblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Livro {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "titulo é fundamental")
    private String titulo;
    @NotBlank(message = "quantidade é fundamental")
    private Integer quantidade;

    public Livro(Long id, String titulo, Integer quantidade) {
        this.id = id;
        this.titulo = titulo;
        this.quantidade = quantidade;
    }

    public Livro() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
