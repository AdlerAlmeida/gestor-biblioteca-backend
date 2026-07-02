package com.example.GestionDeBiblioteca.Exceptions;

public class ExcepcionNoExisteAutor extends RuntimeException{
    public ExcepcionNoExisteAutor(String mensaje){
        super(mensaje);
    }
}
