package com.example.GestionDeBiblioteca.Controllers;

import com.example.GestionDeBiblioteca.Dto.LoginDto;
import com.example.GestionDeBiblioteca.Entities.Usuario;
import com.example.GestionDeBiblioteca.Security.JwtUtil;
import com.example.GestionDeBiblioteca.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
        try {
            Usuario nuevoUsuario = usuarioService.registrarUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginData) {
        try {
            Usuario usuarioLogueado = usuarioService.iniciarSesion(loginData);

            // Generamos el brazalete virtual para el usuario de la biblioteca
            String token = jwtUtil.generarToken(usuarioLogueado);

            // Empaquetamos la respuesta
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("usuario", usuarioLogueado);
            respuesta.put("token", token);

            return ResponseEntity.ok(respuesta);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
