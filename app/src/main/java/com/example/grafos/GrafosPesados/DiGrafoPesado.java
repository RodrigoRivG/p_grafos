package com.example.grafos.GrafosPesados;



import com.example.grafos.Excepciones.AristaNoExisteExcepcion;
import com.example.grafos.Excepciones.AristaYaExisteExcepcion;
import com.example.grafos.Excepciones.NroDeVerticesInvalidoExcepcion;

import java.util.Collections;
import java.util.List;

public class DiGrafoPesado extends GrafoPesado {
    public DiGrafoPesado(){
        super();
    }

    public DiGrafoPesado(int nroDeVertices) throws NroDeVerticesInvalidoExcepcion {
        super(nroDeVertices);
    }

    @Override
    public void insertarArista(int posDeVerticeOrigen, int posDeVerticeDestino, double peso)
            throws AristaYaExisteExcepcion {
        validarVertice(posDeVerticeOrigen);
        validarVertice(posDeVerticeDestino);

        if (existeAdyacencia(posDeVerticeOrigen, posDeVerticeDestino)){
            throw new AristaYaExisteExcepcion();
        }

        List<AdyacenteConPeso> adyacentesDelOrigen = this.listasDeAdyacencia.get(posDeVerticeOrigen);
        adyacentesDelOrigen.add(new AdyacenteConPeso(posDeVerticeDestino, peso));
        Collections.sort(adyacentesDelOrigen);
    }

    @Override
    public void eliminarArista(int posDeVerticeOrigen, int posDeVerticeDestino)
            throws AristaNoExisteExcepcion {
        validarVertice(posDeVerticeOrigen);
        validarVertice(posDeVerticeDestino);
        if (!existeAdyacencia(posDeVerticeOrigen, posDeVerticeDestino)){
            throw new AristaNoExisteExcepcion();
        }

        List<AdyacenteConPeso> adyacentesDelOrigen = this.listasDeAdyacencia.get(posDeVerticeOrigen);
        AdyacenteConPeso eliminar = new AdyacenteConPeso(posDeVerticeDestino);
        int posAEliminar = adyacentesDelOrigen.indexOf(eliminar);
        adyacentesDelOrigen.remove(posAEliminar);
    }

    @Override
    public int cantidadDeAristas() {
        int cantidadDeAristas = 0;
        for (int i = 0; i < listasDeAdyacencia.size(); i++){
            List<AdyacenteConPeso> adyacentes = this.listasDeAdyacencia.get(i);
            cantidadDeAristas += adyacentes.size();
        }

        return cantidadDeAristas;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < listasDeAdyacencia.size(); i++) {
            List<AdyacenteConPeso> adyacentes = this.listasDeAdyacencia.get(i);

            result.append("Vértice ").append(i).append(": ");

            for (AdyacenteConPeso adyacente : adyacentes) {
                result.append("(").append(adyacente.getIndiceVertice()).append(", ").append(adyacente.getPeso()).append(") ");
            }

            result.append("\n");
        }

        return result.toString();
    }
}
