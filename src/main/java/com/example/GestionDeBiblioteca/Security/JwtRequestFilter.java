package com.example.GestionDeBiblioteca.Security; // Ajusta a tu paquete real

import com.example.GestionDeBiblioteca.Entities.Usuario;
import com.example.GestionDeBiblioteca.Repositories.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Buscamos el encabezado "Authorization" en la petición
        final String authorizationHeader = request.getHeader("Authorization");

        String email = null;
        String jwt = null;

        // 2. Si viene el encabezado y empieza con "Bearer ", le quitamos el prefijo para dejar solo el token
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            try {
                email = jwtUtil.extraerEmail(jwt); // Sacamos el email del "molde"
            } catch (Exception e) {
                logger.error("Error al decodificar el token de la biblioteca: " + e.getMessage());
            }
        }

        // 3. Si encontramos un email, verificamos que el usuario realmente exista en la base de datos
        if (email != null) {
            Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);

            if (usuarioOpt.isPresent()) {
                Usuario usuario = usuarioOpt.get();

                // 4. ¡Validamos las firmas criptográficas y la expiración!
                if (jwtUtil.validarToken(jwt, usuario)) {
                    // 😎 ¡Acceso concedido! Inyectamos al usuario logueado en la petición
                    request.setAttribute("usuarioLogueado", usuario);
                } else {
                    //  Token alterado o caducado -> ¡BLOQUEADO!
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Token inválido o expirado. Acceso denegado a la biblioteca.");
                    return; // Corta el flujo aquí
                }
            }
        }

        // 5. Dejamos que la petición continúe su camino
        filterChain.doFilter(request, response);
    }
}