package com.example.grafos.GrafosPesados;


import com.example.grafos.Excepciones.AristaNoExisteExcepcion;
import com.example.grafos.Excepciones.AristaYaExisteExcepcion;
import com.example.grafos.Excepciones.NroDeVerticesInvalidoExcepcion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Kruskal {
    private GrafoPesado grafo;

    public Kruskal(GrafoPesado grafo) {
        this.grafo = grafo;
    }

    public GrafoPesado obtenerArbolExpansionMinima() throws AristaYaExisteExcepcion, NroDeVerticesInvalidoExcepcion, AristaNoExisteExcepcion {
        GrafoPesado grafoAuxiliar = new GrafoPesado(grafo.cantidadDeVertices());
        List<AdyacenteConPeso> aristasOrdenadas = obtenerAristasOrdenadas();

        for (AdyacenteConPeso arista : aristasOrdenadas) {
            int verticeOrigen = arista.getIndiceVertice();
            List<AdyacenteConPeso> adyacentesOrigen = grafo.adyacentesDelVerticeConPeso(verticeOrigen);
            double peso = arista.getPeso();
            for (int i = 0; i < adyacentesOrigen.size(); i++){
                AdyacenteConPeso adyacente = adyacentesOrigen.get(i);
                double otroPeso = adyacente.getPeso();
                if (peso == otroPeso){
                    int verticeDestino = adyacente.getIndiceVertice();
                    if (!grafoAuxiliar.existeAdyacencia(verticeOrigen, verticeDestino)) {
                        grafoAuxiliar.insertarArista(verticeOrigen, verticeDestino, peso);
                        Ciclo existeCiclo = new Ciclo(grafoAuxiliar);
                        if (existeCiclo.ciclo(verticeOrigen)){
                            grafoAuxiliar.eliminarArista(verticeOrigen, verticeDestino);
                        }
                        break;
                    }
                }
            }
        }
        return grafoAuxiliar;
    }

    private List<AdyacenteConPeso> obtenerAristasOrdenadas() {
        List<AdyacenteConPeso> aristas = new ArrayList<>();

        for (int verticeOrigen = 0; verticeOrigen < grafo.cantidadDeVertices(); verticeOrigen++) {
            aristas.addAll(grafo.adyacentesDelVerticeConPeso(verticeOrigen));
        }

        aristas.sort(Comparator.comparingDouble(AdyacenteConPeso::getPeso));
        return aristas;
    }
}
