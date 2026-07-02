package com.example.GestionDeBiblioteca.Controllers;

import com.example.GestionDeBiblioteca.Entities.Autor;
import com.example.GestionDeBiblioteca.Repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;


    @PostMapping("/crear")
    public ResponseEntity<?> crearAutor(@RequestBody Autor autor){
        autorRepository.save(autor);
        return ResponseEntity.ok("Autor: " + autor.getNombre() + " creado con éxito.");
    }
}
