package com.example.grafos.GrafosPesados;

public class FloydWarshall {
    DiGrafoPesado diGrafo;
    double [][]caminosMasCortos;

    public FloydWarshall (DiGrafoPesado unGrafo){
        this.diGrafo = unGrafo;
        int n = this.diGrafo.cantidadDeVertices();
        this.caminosMasCortos = new double[n][n];
        this.calcularDistanciasCortas();
    }

    public void calcularDistanciasCortas(){
        int cantDeVertices = this.diGrafo.cantidadDeVertices();
        for (int i = 0; i < cantDeVertices; i++){
            for (int j = 0; j < cantDeVertices; j++){
                if (i == j){
                    this.caminosMasCortos[i][j] = 0;
                }else {
                    double peso = this.diGrafo.existeAdyacencia(i, j) ? this.diGrafo.peso(i, j) : Double.POSITIVE_INFINITY;
                    this.caminosMasCortos[i][j] = peso;
                }
            }
        }

        for (int k = 0; k < cantDeVertices; k++){
            for (int i = 0; i < cantDeVertices; i++){
                for (int j = 0; j < cantDeVertices; j++){
                    double distanciaIK = this.caminosMasCortos[i][k];
                    double distanciaKJ = this.caminosMasCortos[k][j];
                    double distanciaIJ = this.caminosMasCortos[i][j];

                    if (distanciaIK + distanciaKJ < distanciaIJ){
                        this.caminosMasCortos[i][j] = distanciaIK + distanciaKJ;
                    }
                }
            }
        }
    }

    public String matrizDeCaminosMasCortos() {
        StringBuilder matriz = new StringBuilder();
        int V = this.diGrafo.cantidadDeVertices();

        // Agregar encabezado de columnas
        matriz.append("     ");
        for (int j = 0; j < V; j++) {
            matriz.append(String.format("%-6d", j));
        }
        matriz.append("\n");

        // Agregar líneas de separación
        matriz.append("   ");
        for (int j = 0; j < V; j++) {
            matriz.append("------");
        }
        matriz.append("\n");

        // Agregar filas con los valores de la matriz
        for (int i = 0; i < V; i++) {
            // Agregar encabezado de filas
            matriz.append(String.format("%-3d| ", i));
            for (int j = 0; j < V; j++) {
                if (this.caminosMasCortos[i][j] == Double.POSITIVE_INFINITY) {
                    matriz.append(String.format("%-6s", "INF"));
                } else {
                    matriz.append(String.format("%-6.2f", this.caminosMasCortos[i][j]));
                }
            }
            matriz.append("\n");
        }

        return matriz.toString();
    }
}
