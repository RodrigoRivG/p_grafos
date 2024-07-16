package com.example.grafos.GrafosNoPesados;

public class Warshall {
    private Grafo grafo;

    public Warshall(DiGrafo unGrafo){
        this.grafo = unGrafo;
    }

    public String matrizDeCaminos(){
        int n = grafo.cantidadDeVertices();
        int [][]matriz = new int [n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                matriz[i][j] = 0;
            }
        }

        for (int i = 0; i < n; i++){
            Iterable<Integer> lista = grafo.adyacentesDelVertice(i);
            for (Integer elemento : lista) {
                matriz[i][elemento] = 1;
            }
        }

        for (int k = 0; k < n; k++){
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    //matriz[i][j] = Math.min(matriz[i][j] + matriz[i][k] * matriz[k][j], 1);
                    matriz[i][j] = matriz[i][j] | (matriz[i][k] & matriz[k][j]);
                }
            }
        }

        String s = "";
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                s = s + matriz[i][j] + " ";
            }
            s = s + "\n";
        }

        return s;
    }
}
