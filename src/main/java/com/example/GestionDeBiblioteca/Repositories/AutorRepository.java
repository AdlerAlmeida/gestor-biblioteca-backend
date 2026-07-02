package com.example.GestionDeBiblioteca.Repositories;

import com.example.GestionDeBiblioteca.Entities.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
