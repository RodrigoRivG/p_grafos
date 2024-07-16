package com.example.grafos.GrafosPesados;

import com.example.grafos.ControlMarcados.ControlMarcado;
import com.example.grafos.Excepciones.AristaYaExisteExcepcion;
import com.example.grafos.Excepciones.NroDeVerticesInvalidoExcepcion;
import com.example.grafos.GrafosNoPesados.Grafo;

public class Ciclo {
    private final GrafoPesado grafo;
    private final Grafo grafoAux;
    private final ControlMarcado marcado;

    public Ciclo(GrafoPesado unGrafo) throws AristaYaExisteExcepcion, NroDeVerticesInvalidoExcepcion {
        this.grafo = unGrafo;
        this.grafoAux = new Grafo(this.grafo.cantidadDeVertices());
        this.marcado = new ControlMarcado(grafo.cantidadDeVertices());
        //this.ciclo(0); ignorar esta linea
    }

    public boolean ciclo(int vertice) throws AristaYaExisteExcepcion {
        this.marcado.marcarVertice(vertice);
        Iterable<Integer> adyacentesDelVertice = grafo.adyacentesDelVertice(vertice);
        for (Integer adyacente : adyacentesDelVertice) {
            if (!marcado.estaMarcado(adyacente)) {
                grafoAux.insertarArista(vertice, adyacente);
                if (ciclo(adyacente)) {
                    return true;
                }
            } else if (!grafoAux.existeAdyacencia(vertice, adyacente)) {  //si esta marcado, pregunto si en el grafo aux no existe arista entre el vertice y adyacente
                return true;
            }
        }
        return false; //si es que nunca encontro algun ciclo
    }

    public boolean ciclosEnIslas() throws AristaYaExisteExcepcion{
        for (int i = 0; i < this.grafo.cantidadDeVertices(); i++) {
            if (!this.marcado.estaMarcado(i)) {
                if (this.ciclo(i)) {
                    return true;
                }
            }
        }
        return false;
    }
}
