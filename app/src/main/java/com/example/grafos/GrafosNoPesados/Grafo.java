package com.example.grafos.GrafosNoPesados;

import com.example.grafos.Excepciones.AristaNoExisteExcepcion;
import com.example.grafos.Excepciones.AristaYaExisteExcepcion;
import com.example.grafos.Excepciones.NroDeVerticesInvalidoExcepcion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grafo {
    protected List<List<Integer>> listaDeAdyacencia;

    public Grafo(){
        listaDeAdyacencia = new ArrayList<>();
    }

    public Grafo(int nroVerticesInicial) throws NroDeVerticesInvalidoExcepcion {
        if (nroVerticesInicial <= 0){
            throw new NroDeVerticesInvalidoExcepcion();
        }

        listaDeAdyacencia = new ArrayList<>();

        for (int i = 0; i < nroVerticesInicial; i++){
            this.insertarVertice();
        }
    }

    public final void insertarVertice(){
        List<Integer> adyacentesDelNuevoVertice = new ArrayList<>();
        this.listaDeAdyacencia.add(adyacentesDelNuevoVertice);
    }

    public void insertarArista(int posDeVerticeOrigen, int posDeVerticeDestino)
            throws AristaYaExisteExcepcion {
        if (this.existeAdyacencia(posDeVerticeOrigen, posDeVerticeDestino)){
            throw new AristaYaExisteExcepcion();
        }

        List<Integer> adyacentesDelOrigen = this.listaDeAdyacencia.get(posDeVerticeOrigen);
        adyacentesDelOrigen.add(posDeVerticeDestino);
        Collections.sort(adyacentesDelOrigen);
        if (posDeVerticeOrigen != posDeVerticeDestino){
            List<Integer> adyacentesDelDestino = this.listaDeAdyacencia.get(posDeVerticeDestino);
            adyacentesDelDestino.add(posDeVerticeOrigen);
            Collections.sort(adyacentesDelDestino);
        }
    }

    public boolean existeAdyacencia(int posDeVerticeOrigen, int posDeVerticeDestino){
        validarVertice(posDeVerticeOrigen);
        validarVertice(posDeVerticeDestino);
        List<Integer> adyacentesDeOrigen = this.listaDeAdyacencia.get(posDeVerticeOrigen);
        return adyacentesDeOrigen.contains(posDeVerticeDestino);
    }

    public void validarVertice(int posDeVertice){
        if (posDeVertice < 0 || posDeVertice >= listaDeAdyacencia.size()){
            throw new IllegalArgumentException("Vertice no valido");
        }
    }

    public void eliminarVertice(final int posDeVertice){
        validarVertice(posDeVertice);
        this.listaDeAdyacencia.remove(posDeVertice);
        for (List<Integer> adyacentes: this.listaDeAdyacencia) {
            int posicionAEliminarDeAdyacencia = adyacentes.indexOf(posDeVertice);
            if (posicionAEliminarDeAdyacencia >= 0){
                adyacentes.remove(posicionAEliminarDeAdyacencia);
            }

            for (int i = 0; i < adyacentes.size(); i++){
                int posicionDeAdyacente = adyacentes.get(i);
                if (posicionDeAdyacente > posDeVertice){
                    adyacentes.set(i, posicionDeAdyacente - 1);
                }
            }
        }
    }

    public void eliminarArista(int posDeVerticeOrigen, int posDeVerticeDestino)
            throws AristaNoExisteExcepcion {
        validarVertice(posDeVerticeOrigen);
        validarVertice(posDeVerticeDestino);

        if (!this.existeAdyacencia(posDeVerticeOrigen, posDeVerticeDestino)){
            throw new AristaNoExisteExcepcion();
        }

        List<Integer> listaAdyacentesInicial = this.listaDeAdyacencia.get(posDeVerticeOrigen);
        int posicionAEliminar = listaAdyacentesInicial.indexOf(posDeVerticeDestino);
        listaAdyacentesInicial.remove(posicionAEliminar);

        List<Integer> listaAdyacentesFinal = this.listaDeAdyacencia.get(posDeVerticeDestino);
        posicionAEliminar = listaAdyacentesFinal.indexOf(posDeVerticeOrigen);
        listaAdyacentesFinal.remove(posicionAEliminar);
    }

    public int cantidadDeVertices(){
        return this.listaDeAdyacencia.size();
    }


    public int cantidadDeAristas(){
        int cantidadDeAristas = 0;
        int cantidadDeLazos = 0;

        for (List<Integer> listaAdyacencia: this.listaDeAdyacencia) {
            for (Integer ad: listaAdyacencia) {
                if (ad.equals(this.listaDeAdyacencia.indexOf(listaAdyacencia))){
                    cantidadDeLazos++;
                }else{
                    cantidadDeAristas++;
                }
            }
        }

        cantidadDeAristas = (cantidadDeAristas / 2) + cantidadDeLazos;
        return cantidadDeAristas;
    }

    public Iterable<Integer> adyacentesDelVertice(int posDeVertice){
        validarVertice(posDeVertice);
        List<Integer> adyacentesDelVertice = this.listaDeAdyacencia.get(posDeVertice);
        Iterable<Integer> iterableDeAdyacentesDelVertice = (Iterable) adyacentesDelVertice;
        return iterableDeAdyacentesDelVertice;
    }

    public int gradoDelVertice(int posDeVertice){
        validarVertice(posDeVertice);
        List<Integer> adyacentesDelVertice = this.listaDeAdyacencia.get(posDeVertice);
        return adyacentesDelVertice.size();
    }

    @Override
    public String toString() {
        if(this.cantidadDeVertices() == 0)
            return "Grafo vacío";

        StringBuilder sb = new StringBuilder();
        sb.append("Grafo con ").append(this.cantidadDeVertices()).append(" vértices:\n");

        for(int i=0; i<this.cantidadDeVertices(); i++) {
            sb.append("Adyacentes del vértice ").append(i).append(": ");

            for(Integer adyacente : this.adyacentesDelVertice(i)) {
                sb.append(adyacente).append(" ");
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}
