package com.example.grafos.GrafosPesados;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dijkstra {
    private double[] costos;
    private int[] predecesores;
    private boolean[] marcados;
    private DiGrafoPesado diGrafo;
    private static final double infinito = Double.POSITIVE_INFINITY;
    private List<List<Integer>> rutas;

    public Dijkstra(DiGrafoPesado unGrafo){
        this.diGrafo = unGrafo;
        int n = this.diGrafo.cantidadDeVertices();
        this.costos = new double[n];
        this.predecesores = new int[n];
        this.marcados = new boolean[n];
        for (int i = 0; i < n; i++){
            costos[i] = infinito;
            marcados[i] = false;
            predecesores[i] = -1;
        }
    }

    /*public List<Double> calcularCaminosMasCortos(int verticeOrigen, int verticeDestino)
            throws AristaNoExisteExcepcion {
        costos[verticeOrigen] = 0;
        marcados[verticeOrigen] = true;
        int enTurno = verticeOrigen;

        while ((!marcados[verticeDestino]) && ((costos[enTurno]!=infinito))){
            Iterable<Integer> lista = this.diGrafo.adyacentesDelVertice(enTurno);
            for(Integer elemento : lista){
                if(!this.marcados[elemento]){
                    double distancia = this.diGrafo.peso(enTurno, elemento);
                    if((distancia + costos[enTurno]) < costos[elemento]){
                        costos[elemento] = distancia + costos[enTurno];
                        predecesores[elemento] = enTurno;
                    }
                }
            }

            marcados[enTurno] = true;
            enTurno = verticeNoMarcadoDeMenorCosto();
        }

        List<Double> camino = new ArrayList<>();

        if(costos[verticeDestino] != infinito){
            int elemento = predecesores[verticeDestino];
            while(elemento != -1){
                camino.add((double) elemento);
                elemento = predecesores[elemento];
            }
        }

        return camino;
    }*/

    public List<Double> calcularCaminosMasCortos(int verticeOrigen, int verticeDestino) {
        costos[verticeOrigen] = 0;
        //marcados[verticeOrigen] = true;

        while (!marcados[verticeDestino]) {
            int enTurno = verticeNoMarcadoDeMenorCosto();

            if (costos[enTurno] == infinito) {
                // No hay camino desde verticeOrigen hasta verticeDestino
                break;
            }

            Iterable<Integer> lista = this.diGrafo.adyacentesDelVertice(enTurno);
            for (Integer elemento : lista) {
                if (!this.marcados[elemento]) {
                    double distancia = this.diGrafo.peso(enTurno, elemento);
                    if ((distancia + (costos[enTurno])) < (costos[elemento])) {
                        costos[elemento] = distancia + (costos[enTurno]);
                        predecesores[elemento] = enTurno;
                    }
                }
            }

            marcados[enTurno] = true;
        }

        List<Double> camino = new ArrayList<>();

        if (costos[verticeDestino] != infinito) {
            int elemento = verticeDestino;
            while (elemento != -1) {
                camino.add((double) elemento);
                elemento = predecesores[elemento];
            }
        }

        Collections.reverse(camino);

        camino.add(costos[verticeDestino]);
        return camino;
    }

    private int verticeNoMarcadoDeMenorCosto() {
        int vertice = 0;
        double min = infinito;
        for(int i = 0; i < marcados.length; i++){
            if((!marcados[i]) && (costos[i] <= min)){
                min = costos[i];
                vertice = i;
            }
        }

        return vertice;
    }
}
