package com.example.grafos.GrafosNoPesados;

import com.example.grafos.ControlMarcados.ControlMarcado;

//import Utileria.UtilRecorrido;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {
    private Grafo grafo;
    private final ControlMarcado marcados;
    private final int verticeDePartida;
    private List<Integer> recorrido;

    public BFS(Grafo grafoBase, int posDeVerticeDePartida){
        grafoBase.validarVertice(posDeVerticeDePartida);
        verticeDePartida = posDeVerticeDePartida;
        recorrido = new ArrayList<>();
        this.grafo = grafoBase;
        marcados = new ControlMarcado(grafo.cantidadDeVertices());
        marcados.desmarcarTodos();
        ejecutarBFS(posDeVerticeDePartida);
    }

    public void ejecutarBFS(int posDeVertice){
        Queue<Integer> colaDeVertices = new LinkedList<>();
        colaDeVertices.offer(posDeVertice);
        marcados.marcarVertice(posDeVertice);
        do{
            int posDeVerticeEnTurno = colaDeVertices.poll();
            this.recorrido.add(posDeVerticeEnTurno);
            Iterable<Integer> adyacentesDelVerticeEnTurno = this.grafo.adyacentesDelVertice(posDeVerticeEnTurno);
            for (Integer adyacenteEnTurno : adyacentesDelVerticeEnTurno) {
                if (!this.marcados.estaMarcado(adyacenteEnTurno)){
                    colaDeVertices.offer(adyacenteEnTurno);
                    this.marcados.marcarVertice(adyacenteEnTurno);
                }
            }
        }while (!colaDeVertices.isEmpty());
    }

    public Iterable<Integer> obtenerVerticesVisitados(){
        return recorrido;
    }

    public boolean hayCaminoAVertice(int posDeVerticeDestino){
        this.grafo.validarVertice(posDeVerticeDestino);
        return this.marcados.estanMarcadosTodos();
    }

    public boolean hayCaminoATodos(){
        return marcados.estanMarcadosTodos();
    }
}
