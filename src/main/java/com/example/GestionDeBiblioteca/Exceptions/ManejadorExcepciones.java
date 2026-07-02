package com.example.GestionDeBiblioteca.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ManejadorExcepciones {

    @ExceptionHandler(ExceptionDays.class)
    public ResponseEntity<ErrorRespuesta> manejarExcepcionDays(ExceptionDays ex){
        ErrorRespuesta error = new ErrorRespuesta(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Petición Incorrecta",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExcepcionNoExisteAutor.class)
    public ResponseEntity<ErrorRespuesta> manejarExcepcionNoExisteId(ExcepcionNoExisteAutor ex){
        ErrorRespuesta error = new ErrorRespuesta(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Id no encontrado",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
