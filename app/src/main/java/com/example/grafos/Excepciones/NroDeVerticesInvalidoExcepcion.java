package com.example.grafos.Excepciones;

public class NroDeVerticesInvalidoExcepcion extends Throwable {
    public NroDeVerticesInvalidoExcepcion() {
        super("El número de vertices debe ser positivo");
    }

    public NroDeVerticesInvalidoExcepcion(String mensaje){
        super(mensaje);
    }
}
