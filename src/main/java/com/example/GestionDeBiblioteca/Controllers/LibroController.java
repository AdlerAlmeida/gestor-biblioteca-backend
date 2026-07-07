package com.example.GestionDeBiblioteca.Controllers;

import com.example.GestionDeBiblioteca.Entities.Libro;
import com.example.GestionDeBiblioteca.Entities.Usuario;
import com.example.GestionDeBiblioteca.Services.LibroService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarLibro(@RequestBody Libro libro, HttpServletRequest request) {
        // 🕵️‍♂️ Le pedimos al request el usuario que el filtro ya validó en la puerta
        Usuario usuarioLogueado = (Usuario) request.getAttribute("usuarioLogueado");

        // Si está vacío, significa que el intruso no mandó el token "Bearer" correcto
        if (usuarioLogueado == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Debes iniciar sesión con un token válido para registrar libros.");
        }

        // 😎 Si el guardia lo dejó pasar, guardamos el libro de forma normal
        // Libro libroGuardado = libroService.guardar(libro);
        return ResponseEntity.ok("Libro registrado con éxito por el usuario: " + usuarioLogueado.getNombre());
    }
}
