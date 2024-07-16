package com.example.grafos.GrafosPesados;

import java.util.*;

public class FordFulkerson {
    private GrafoPesado grafo;
    private int numVertices;
    private double[][] flujo;
    private int origen;
    private int destino;

    public FordFulkerson(GrafoPesado grafo, int origen, int destino) {
        this.grafo = grafo;
        this.numVertices = grafo.cantidadDeVertices();
        this.flujo = new double[numVertices][numVertices];
        this.origen = origen;
        this.destino = destino;
    }

    public double encontrarFlujoMaximo() {
        double flujoMaximo = 0;

        while (true) {
            double[] capacidadResidual = new double[numVertices];
            int[] padre = new int[numVertices];
            boolean[] visitado = new boolean[numVertices];

            Queue<Integer> cola = new LinkedList<>();
            cola.offer(origen);
            visitado[origen] = true;
            capacidadResidual[origen] = Double.POSITIVE_INFINITY;

            while (!cola.isEmpty()) {
                int actual = cola.poll();

                for (int vecino : grafo.adyacentesDelVertice(actual)) {
                    if (!visitado[vecino] && capacidadResidual(actual, vecino) > 0) {
                        padre[vecino] = actual;
                        capacidadResidual[vecino] = Math.min(capacidadResidual(actual, vecino), capacidadResidual[actual]);
                        visitado[vecino] = true;
                        cola.offer(vecino);

                        if (vecino == destino) {
                            // Se ha encontrado un camino de aumento
                            double flujoAumento = capacidadResidual[destino];
                            flujoMaximo += flujoAumento;

                            // Actualizar el flujo en el camino de aumento
                            for (int v = destino; v != origen; v = padre[v]) {
                                flujo[padre[v]][v] += flujoAumento;
                                flujo[v][padre[v]] -= flujoAumento;
                            }

                            // Reiniciar BFS para buscar más caminos de aumento
                            visitado = new boolean[numVertices];
                            cola.clear();
                            cola.offer(origen);
                            visitado[origen] = true;
                            capacidadResidual[origen] = Double.POSITIVE_INFINITY;

                            break;  // Salir del bucle interno
                        }
                    }
                }
            }

            if (!visitado[destino]) {
                // No se encontraron más caminos de aumento
                break;
            }
        }

        return flujoMaximo;
    }

    private double capacidadResidual(int posDeVerticeOrigen, int posDeVerticeDestino) {
        // Calcular la capacidad residual entre dos vértices sin depender de un método en GrafoPesado
        List<AdyacenteConPeso> adyacentesDelOrigen = grafo.adyacentesDelVerticeConPeso(posDeVerticeOrigen);

        for (AdyacenteConPeso adyacente : adyacentesDelOrigen) {
            if (adyacente.getIndiceVertice() == posDeVerticeDestino) {
                double capacidadOriginal = adyacente.getPeso();
                double flujoActual = flujo[posDeVerticeOrigen][posDeVerticeDestino];
                return capacidadOriginal - flujoActual;
            }
        }

        return 0;
    }
}
