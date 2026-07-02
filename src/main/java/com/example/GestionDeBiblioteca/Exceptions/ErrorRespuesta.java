package com.example.GestionDeBiblioteca.Exceptions;
import java.time.LocalDateTime;

public class ErrorRespuesta {

    private LocalDateTime timestamp;
    private int codigo;
    private String error;
    private String mensaje;

    public ErrorRespuesta(){
    }

    public ErrorRespuesta(LocalDateTime timestamp, int codigo, String error, String mensaje) {
        this.timestamp = timestamp;
        this.codigo = codigo;
        this.error = error;
        this.mensaje = mensaje;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
