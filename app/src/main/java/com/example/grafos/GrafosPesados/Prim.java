package com.example.grafos.GrafosPesados;

import com.example.grafos.Excepciones.AristaYaExisteExcepcion;
import com.example.grafos.Excepciones.NroDeVerticesInvalidoExcepcion;

import java.util.ArrayList;
import java.util.List;

public class Prim {
    private GrafoPesado grafoP;
    private boolean[] marcados;

    public Prim(GrafoPesado unGrafo){
        this.grafoP = unGrafo;
        this.marcados = new boolean[grafoP.cantidadDeVertices()];
        for (int i = 0; i < marcados.length; i++){
            marcados[i] = false;
        }
    }

    public GrafoPesado arbolDeExpansion()
            throws NroDeVerticesInvalidoExcepcion, AristaYaExisteExcepcion {
        int n = this.grafoP.cantidadDeVertices();
        GrafoPesado grafoAux = new GrafoPesado(n);
        marcados[0] = true;
        while (!estanTodosMarcados()){
            double infinito = Double.MAX_VALUE;
            int verticeOrigen = 0;
            int verticeDestino = 0;
            List<Integer> listaDeMarcados = listaDeElementosMarcados();
            for (Integer elemento : listaDeElementosMarcados()) {
                Iterable<AdyacenteConPeso> adyacentes = this.grafoP.adyacentesDelVerticeConPeso(elemento);
                for (AdyacenteConPeso aux : adyacentes) {
                    if (!marcados[aux.getIndiceVertice()] && aux.getPeso() < infinito){
                        verticeOrigen = elemento;
                        verticeDestino = aux.getIndiceVertice();
                        infinito = aux.getPeso();
                    }
                }
            }

            grafoAux.insertarArista(verticeOrigen, verticeDestino, infinito);
            marcados[verticeDestino] = true;
        }
        return grafoAux;
    }

    private boolean estanTodosMarcados(){
        for (int i = 0; i < marcados.length; i++){
            if (!marcados[i]){
                return false;
            }
        }

        return true;
    }

    private List<Integer> listaDeElementosMarcados(){
        List<Integer> lista = new ArrayList<>();
        for (int i = 0; i < marcados.length; i++){
            if (marcados[i]){
                lista.add(i);
            }
        }

        return lista;
    }
}
