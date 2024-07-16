package com.example.grafos.GrafosPesados;



import com.example.grafos.Excepciones.AristaNoExisteExcepcion;
import com.example.grafos.Excepciones.AristaYaExisteExcepcion;
import com.example.grafos.Excepciones.NroDeVerticesInvalidoExcepcion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GrafoPesado {
    protected List<List<AdyacenteConPeso>> listasDeAdyacencia;

    public GrafoPesado(){
        listasDeAdyacencia = new ArrayList<>();
    }

    public GrafoPesado(int nroDeVerticesInicial)
            throws NroDeVerticesInvalidoExcepcion {
        if (nroDeVerticesInicial <= 0){
            throw new NroDeVerticesInvalidoExcepcion();
        }

        listasDeAdyacencia = new ArrayList<>();
        for (int i = 0; i < nroDeVerticesInicial; i++){
            this.insertarVertice();
        }
    }

    public void insertarVertice(){
        List<AdyacenteConPeso> adyacentesDelNuevoVertice = new ArrayList<>();
        this.listasDeAdyacencia.add(adyacentesDelNuevoVertice);
    }

    public void insertarArista(int posDeVerticeOrigen, int posDeVerticeDestino, double peso) throws AristaYaExisteExcepcion {
        if (this.existeAdyacencia(posDeVerticeOrigen, posDeVerticeDestino)){
            throw new AristaYaExisteExcepcion();
        }

        List<AdyacenteConPeso> adyacentesDelOrigen = this.listasDeAdyacencia.get(posDeVerticeOrigen);
        adyacentesDelOrigen.add(new AdyacenteConPeso(posDeVerticeDestino, peso));
        Collections.sort(adyacentesDelOrigen);
        if (posDeVerticeOrigen != posDeVerticeDestino){
            List<AdyacenteConPeso> adyacentesDelDestino = this.listasDeAdyacencia.get(posDeVerticeDestino);
            adyacentesDelDestino.add(new AdyacenteConPeso(posDeVerticeOrigen, peso));
            Collections.sort(adyacentesDelDestino);
        }
    }

    public boolean existeAdyacencia(int posDeVerticeOrigen, int posDeVerticeDestino){
        /*validarVertice(posDeVerticeOrigen);
        validarVertice(posDeVerticeDestino);
        List<AdyacenteConPeso> adyacentesDelOrigen = this.listasDeAdyacencia.get(posDeVerticeOrigen);
        AdyacenteConPeso adyacentesDestino = new AdyacenteConPeso(posDeVerticeDestino);
        return this.listasDeAdyacencia.contains(adyacentesDestino);*/
        validarVertice(posDeVerticeOrigen);
        validarVertice(posDeVerticeDestino);
        List<AdyacenteConPeso> adyacentesDelOrigen = this.listasDeAdyacencia.get(posDeVerticeOrigen);

        for (AdyacenteConPeso adyacente : adyacentesDelOrigen) {
            if (adyacente.getIndiceVertice() == posDeVerticeDestino) {
                return true;
            }
        }

        return false;
    }

    public void validarVertice(int posDeVertice){
        if (posDeVertice < 0 || posDeVertice > this.listasDeAdyacencia.size()){
            throw new IllegalArgumentException("Vertice no valido");
        }
    }

    public int cantidadDeVertices(){
        return this.listasDeAdyacencia.size();
    }

    public int cantidadDeAristas(){
        int cantidadAristas = 0;

        for (List<AdyacenteConPeso> adyacentes : this.listasDeAdyacencia) {
            cantidadAristas += adyacentes.size();
        }

        return cantidadAristas / 2;
    }

    public void eliminarArista(int posDeVerticeOrigen, int posDeVerticeDestino)
            throws AristaNoExisteExcepcion {

        validarVertice(posDeVerticeOrigen);
        validarVertice(posDeVerticeDestino);
        if (!this.existeAdyacencia(posDeVerticeOrigen, posDeVerticeDestino)){
            throw new AristaNoExisteExcepcion();
        }

        List<AdyacenteConPeso> adyacentesDelOrigen = this.listasDeAdyacencia.get(posDeVerticeOrigen);
        AdyacenteConPeso eliminar = new AdyacenteConPeso(posDeVerticeDestino, 0);
        int posAEliminar = adyacentesDelOrigen.indexOf(eliminar);
        adyacentesDelOrigen.remove(posAEliminar);

        if (posDeVerticeOrigen != posDeVerticeDestino){
            List<AdyacenteConPeso> adyacentesDelDestino = this.listasDeAdyacencia.get(posDeVerticeDestino);
            AdyacenteConPeso eliminarOtro = new AdyacenteConPeso(posDeVerticeOrigen, 0);
            int posAEliminarOtro = adyacentesDelDestino.indexOf(eliminarOtro);
            adyacentesDelDestino.remove(posAEliminarOtro);
        }
    }

    public double peso(int posDeVerticeOrigen, int posDeVerticeDestino){
        if (!this.existeAdyacencia(posDeVerticeOrigen, posDeVerticeDestino)){
            throw new RuntimeException("Adyacencia no existe");
        }

        List<AdyacenteConPeso> adyacentesDelOrigen = this.listasDeAdyacencia.get(posDeVerticeOrigen);
        for (AdyacenteConPeso adyacente : adyacentesDelOrigen) {
            if (adyacente.getIndiceVertice() == posDeVerticeDestino){
                return adyacente.getPeso();
            }
        }
        return 0;
    }

    public Iterable<Integer> adyacentesDelVertice(int posDeVertice){
        validarVertice(posDeVertice);
        List<Integer> adyacentesDelVertice = new ArrayList<>();
        for (AdyacenteConPeso unAdyacente : this.listasDeAdyacencia.get(posDeVertice)) {
            adyacentesDelVertice.add(unAdyacente.getIndiceVertice());
        }

        Iterable<Integer> iterableDeAdyacentesDelVertice = (Iterable)adyacentesDelVertice;
        return iterableDeAdyacentesDelVertice;
    }

    public List<AdyacenteConPeso> adyacentesDelVerticeConPeso(int posDelVertice){
        validarVertice(posDelVertice);
        List<AdyacenteConPeso> lista = this.listasDeAdyacencia.get(posDelVertice);
        return lista;
    }



    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < listasDeAdyacencia.size(); i++) {
            result.append("Vértice ").append(i).append(": ");

            List<AdyacenteConPeso> adyacentes = listasDeAdyacencia.get(i);
            for (AdyacenteConPeso adyacente : adyacentes) {
                result.append("(").append(adyacente.getIndiceVertice()).append(", ").append(adyacente.getPeso()).append(") ");
            }

            result.append("\n");
        }

        return result.toString();
    }
}
