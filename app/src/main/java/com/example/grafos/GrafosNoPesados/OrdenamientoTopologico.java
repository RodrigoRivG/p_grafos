package com.example.grafos.GrafosNoPesados;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class OrdenamientoTopologico {
    private DiGrafo grafoDirigido;
    private List<Integer> ordenTopologico;

    public OrdenamientoTopologico(DiGrafo grafoDirigido) {
        this.grafoDirigido = grafoDirigido;
        this.ordenTopologico = new ArrayList<>();
    }

    public List<Integer> obtenerOrdenTopologico() {
        int[] gradosEntrada = new int[grafoDirigido.cantidadDeVertices()];

        // Calcular grados de entrada
        for (int i = 0; i < grafoDirigido.cantidadDeVertices(); i++) {
            for (Integer adyacente : grafoDirigido.adyacentesDelVertice(i)) {
                gradosEntrada[adyacente]++;
            }
        }

        // Inicializar cola para vértices con grado de entrada cero
        Queue<Integer> cola = new LinkedList<>();
        for (int i = 0; i < grafoDirigido.cantidadDeVertices(); i++) {
            if (gradosEntrada[i] == 0) {
                cola.offer(i);
            }
        }

        // Procesar vértices en orden topológico
        while (!cola.isEmpty()) {
            int vertice = cola.poll();
            ordenTopologico.add(vertice);

            for (Integer adyacente : grafoDirigido.adyacentesDelVertice(vertice)) {
                gradosEntrada[adyacente]--;
                if (gradosEntrada[adyacente] == 0) {
                    cola.offer(adyacente);
                }
            }
        }

        // Verificar si el grafo contiene ciclos
        if (ordenTopologico.size() != grafoDirigido.cantidadDeVertices()) {
            return null; // El grafo contiene ciclos
        }

        return ordenTopologico;
    }
}
