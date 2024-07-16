package com.example.grafos.Excepciones;

public class AristaNoExisteExcepcion extends Throwable{
    public AristaNoExisteExcepcion() {
        super("La arista NO existe");
    }

    public AristaNoExisteExcepcion(String mensaje){
        super(mensaje);
    }
}
