package com.example.grafos.Excepciones;

public class AristaYaExisteExcepcion extends Throwable {
    public AristaYaExisteExcepcion() {
        super("La arista YA existe");
    }

    public AristaYaExisteExcepcion(String mensaje){
        super(mensaje);
    }
}
