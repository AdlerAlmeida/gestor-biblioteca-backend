package com.example.GestionDeBiblioteca.Controllers;

import com.example.GestionDeBiblioteca.Entities.Libro;
import com.example.GestionDeBiblioteca.Services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping("/lista")
    public List<Libro> obtenerLibros(){
        return libroService.obtenerLibros();
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> crearLibro(@RequestBody Libro libro){
        libroService.crearLibro(libro);
        return ResponseEntity.ok("Libro registrado con éxito en la biblioteca!");
    }
}
