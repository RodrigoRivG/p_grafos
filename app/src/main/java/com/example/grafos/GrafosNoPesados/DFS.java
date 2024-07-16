package com.example.grafos.GrafosNoPesados;

import com.example.grafos.ControlMarcados.ControlMarcado;

//import Utileria.UtilRecorrido;

import java.util.ArrayList;
import java.util.List;

public class DFS {
    private List<Integer> recorrido;
    //private Grafo grafo;
    private DiGrafo digrafo;
    private ControlMarcado marcados;

    public DFS(DiGrafo unGrafo, int verticeDePartida){
        this.digrafo = unGrafo;
        digrafo.validarVertice(verticeDePartida);
        recorrido = new ArrayList<>();
        marcados = new ControlMarcado(digrafo.cantidadDeVertices());
        marcados.desmarcarTodos();
        continuarDFS(verticeDePartida);
    }

    public void continuarDFS(int verticeDePartida){
        marcados.marcarVertice(verticeDePartida);
        recorrido.add(verticeDePartida);
        Iterable<Integer> adyacencia = digrafo.adyacentesDelVertice(verticeDePartida);
        for (Integer enTurno : adyacencia) {
            if (!marcados.estaMarcado(enTurno)){
                continuarDFS(enTurno);
            }
        }
    }

    public boolean buscarCaminos(int verticeOrigen, int verticeDestino){
        marcados.desmarcarTodos();
        return buscarCaminosDFS(verticeOrigen, verticeDestino);
    }

    private boolean buscarCaminosDFS(int verticeOrigen, int verticeDestino){
        marcados.marcarVertice(verticeOrigen);
        if (verticeOrigen == verticeDestino){
            return true;
        }

        Iterable<Integer> adyacencia = digrafo.adyacentesDelVertice(verticeOrigen);
        for (Integer enTurno : adyacencia) {
            if (!marcados.estaMarcado(enTurno)){
                if (buscarCaminosDFS(enTurno, verticeDestino)){
                    return true;
                }
            }
        }
        return false;
    }

    public List<Integer> obtenerRecorrido(){
        return recorrido;
    }
}
