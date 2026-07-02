package com.example.GestionDeBiblioteca.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    private String nombre;

    private LocalDate fechaRecibida;

    private LocalDate fechaEntrada;

    @ManyToOne
    private Autor autor;

    public Libro(){

    }
    public Libro(Long id,Autor autor ,String nombre, LocalDate fechaRecibida, LocalDate fechaEntrada) {
        this.id = id;
        this.nombre = nombre;
        this.autor = autor;
        this.fechaRecibida = fechaRecibida;
        this.fechaEntrada = fechaEntrada;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaRecibida() {
        return fechaRecibida;
    }

    public void setFechaRecibida(LocalDate fechaRecibida) {
        this.fechaRecibida = fechaRecibida;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
