package com.example.GestionDeBiblioteca.Services;

import com.example.GestionDeBiblioteca.Entities.Autor;
import com.example.GestionDeBiblioteca.Entities.Libro;
import com.example.GestionDeBiblioteca.Exceptions.ExcepcionNoExisteAutor;
import com.example.GestionDeBiblioteca.Exceptions.ExceptionDays;
import com.example.GestionDeBiblioteca.Repositories.AutorRepository;
import com.example.GestionDeBiblioteca.Repositories.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    public List<Libro> obtenerLibros(){
        return libroRepository.findAll();
    }

    public void crearLibro(Libro libro){

      Long id = libro.getAutor().getId();

    Autor existeAutor = autorRepository.findById(id).orElse(null);

    if(existeAutor == null){
        throw new ExcepcionNoExisteAutor("El id: " + id + " no existe.");
    }

        libro.setAutor(existeAutor);
        long dias = java.time.temporal.ChronoUnit.DAYS.between(libro.getFechaEntrada(),libro.getFechaRecibida());
        if(dias > 30){
            throw new ExceptionDays("El libro no se puede rentar por 30 días o más!");
        }


        libroRepository.save(libro);
    }
}
