package com.example.grafos.GrafosNoPesados;

import com.example.grafos.Excepciones.AristaNoExisteExcepcion;
import com.example.grafos.Excepciones.AristaYaExisteExcepcion;
import com.example.grafos.Excepciones.NroDeVerticesInvalidoExcepcion;

import java.util.Collections;
import java.util.List;

public class DiGrafo extends Grafo {
    public DiGrafo(){
        super();
    }

    public DiGrafo(int nroInicialDeVertices) throws NroDeVerticesInvalidoExcepcion {
        super(nroInicialDeVertices);
    }

    @Override
    public void insertarArista(int posDeVerticeOrigen, int posDeVerticeDestino) throws
            AristaYaExisteExcepcion {
        validarVertice(posDeVerticeOrigen);
        validarVertice(posDeVerticeDestino);

        if (this.existeAdyacencia(posDeVerticeOrigen, posDeVerticeDestino)){
            throw new AristaYaExisteExcepcion();
        }

        List<Integer> adyacentesDelOrigen = this.listaDeAdyacencia.get(posDeVerticeOrigen);
        adyacentesDelOrigen.add(posDeVerticeDestino);
        Collections.sort(adyacentesDelOrigen);
    }

    @Override
    public void eliminarVertice(final int posDeVertice){
        validarVertice(posDeVertice);
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

    @Override
    public void eliminarArista(int posDeVerticeOrigen, int posDeVerticeDestino) throws
            AristaNoExisteExcepcion {
        validarVertice(posDeVerticeOrigen);
        validarVertice(posDeVerticeDestino);

        if (!this.existeAdyacencia(posDeVerticeOrigen, posDeVerticeDestino)){
            throw new AristaNoExisteExcepcion();
        }

        List<Integer> adyacentesDelOrigen = this.listaDeAdyacencia.get(posDeVerticeOrigen);
        int posicionAEliminar = adyacentesDelOrigen.indexOf(posDeVerticeDestino);
        adyacentesDelOrigen.remove(posicionAEliminar);
    }

    @Override
    public int cantidadDeAristas(){
        int cantDeAristas = 0;
        for (List<Integer> listaAdyacencia: this.listaDeAdyacencia) {
            cantDeAristas += listaAdyacencia.size();
        }

        return cantDeAristas;
    }

    @Override
    public int gradoDelVertice(int posDeVertice){
        validarVertice(posDeVertice);
        int grado = 0;

        List<Integer> adyacentesDelVertice = this.listaDeAdyacencia.get(posDeVertice);
        grado += adyacentesDelVertice.size();

        for (List<Integer> adyacentes : this.listaDeAdyacencia){
            if (adyacentes.contains(posDeVertice)){
                grado++;
            }
        }

        return grado;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < listaDeAdyacencia.size(); i++) {
            sb.append("Vértice ").append(i).append(" -> ");
            List<Integer> adyacentes = listaDeAdyacencia.get(i);

            for (int j = 0; j < adyacentes.size(); j++) {
                sb.append(adyacentes.get(j));
                if (j < adyacentes.size() - 1) {
                    sb.append(", ");
                }
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}
