package com.example.GestionDeBiblioteca.Security;

import com.example.GestionDeBiblioteca.Entities.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key CLAVE_SECRETA = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long TIEMPO_EXPIRACION = 86400000;

    public String generarToken(Usuario usuario){
        return Jwts.builder()
                .setSubject(usuario.getEmail())
                .claim("id", usuario.getId())
                .claim("nombre", usuario.getNombre())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TIEMPO_EXPIRACION))
                .signWith(CLAVE_SECRETA)
                .compact();
    }

    public String extraerEmail(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(CLAVE_SECRETA)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validarToken(String token, Usuario usuario){
        try {
            final String emailToken = extraerEmail(token);

            Date expiracion = Jwts.parserBuilder()
                    .setSigningKey(CLAVE_SECRETA)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();

            boolean tokenExpirado = expiracion.before(new Date());

            return(emailToken.equals(usuario.getEmail()) && !tokenExpirado);
        } catch (Exception e) {
            return false;
        }
    }
}
