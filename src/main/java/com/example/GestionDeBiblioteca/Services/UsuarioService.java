package com.example.GestionDeBiblioteca.Services;

import com.example.GestionDeBiblioteca.Dto.LoginDto;
import com.example.GestionDeBiblioteca.Entities.Usuario;
import com.example.GestionDeBiblioteca.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario registrarUsuario(Usuario usuario){

        Optional<Usuario> existente = usuarioRepository.findByEmail(usuario.getEmail());
        if(existente.isPresent()){
            throw new RuntimeException("El correo ya está registrado.");
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario iniciarSesion(LoginDto loginData){
        Usuario usuario = usuarioRepository.findByEmail(loginData.getEmail())
                .orElseThrow(() -> new RuntimeException("El correo electronico no existe."));
        if(!usuario.getContrasena().equals(loginData.getContrasena())){
            throw new RuntimeException("Contraseña incorrecta.");
        }

        return usuario;
    }
}
