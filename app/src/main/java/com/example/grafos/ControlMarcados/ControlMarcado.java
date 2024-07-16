package com.example.grafos.ControlMarcados;

import java.util.LinkedList;
import java.util.List;

public class ControlMarcado {
    private final List<Boolean> marcados;

    public ControlMarcado(int nroDeVertices){
        this.marcados = new LinkedList<>();
        for (int i = 0; i < nroDeVertices; i++) {
            this.marcados.add(Boolean.FALSE);
        }
    }

    public void marcarVertice(int posDeVertice){
        this.marcados.set(posDeVertice, Boolean.TRUE);
    }

    public void desmarcarVertice(int posDeVertice){
        this.marcados.set(posDeVertice, Boolean.FALSE);
    }

    public void desmarcarTodos(){
        for (int i = 0; i < this.marcados.size(); i++) {
            this.marcados.set(i, Boolean.FALSE);
        }
    }

    public boolean estanMarcadosTodos(){
        for(Boolean marcado : this.marcados){
            if(!marcado){
                return false;
            }
        }

        return true;
    }

    public boolean estaMarcado(int posDeVertice){
        return this.marcados.get(posDeVertice);
    }
}
