package com.example.GestionDeBiblioteca.Repositories;

import com.example.GestionDeBiblioteca.Entities.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
}
