package com.example.grafos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.grafos.Excepciones.AristaYaExisteExcepcion;
import com.example.grafos.Excepciones.NroDeVerticesInvalidoExcepcion;
import com.example.grafos.GrafosPesados.DiGrafoPesado;
import com.example.grafos.GrafosPesados.Dijkstra;

import java.util.ArrayList;
import java.util.List;

public class dijkstra extends AppCompatActivity {

    DiGrafoPesado digrafop;
    Button botonMostrar;
    EditText origen;
    EditText destino;
    Button caminos;
    Dijkstra dijkGrafo;
    TextView mostrarCam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dijkstra);

        botonMostrar = findViewById(R.id.mostrarDijkstra);
        mostrarCam = findViewById(R.id.mosttrarCamino);

        try {
            digrafop = new DiGrafoPesado(5);
            digrafop.insertarArista(0, 1, 1);
            digrafop.insertarArista(1, 3, 4);
            digrafop.insertarArista(1, 4, 7);
            digrafop.insertarArista(2, 0, 3);
            digrafop.insertarArista(2, 1, 2);
            digrafop.insertarArista(2, 4, 4);
            digrafop.insertarArista(3, 0, 6);
            digrafop.insertarArista(3, 4, 2);
            digrafop.insertarArista(4, 3, 3);
            botonMostrar.setText(digrafop.toString());
        } catch (NroDeVerticesInvalidoExcepcion e) {
            e.printStackTrace();
        } catch (AristaYaExisteExcepcion e) {
            e.printStackTrace();
        }

        dijkGrafo = new Dijkstra(digrafop);

        origen = findViewById(R.id.vertOrigen);
        destino = findViewById(R.id.vertDest);

        caminos = findViewById(R.id.boton__camino);
        caminos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vOrigen = origen.getText().toString();
                String vDest = destino.getText().toString();
                int vOri = Integer.parseInt(vOrigen);
                int vDes = Integer.parseInt(vDest);
                List<Double> caminoMasCorto = dijkGrafo.calcularCaminosMasCortos(vOri, vDes);
                //mostrarCam.setText(caminoMasCorto.toString());
                List<Integer> listaDeCamino = new ArrayList<>();
                double peso = caminoMasCorto.get(caminoMasCorto.size()-1);
                for (int i = 0; i < caminoMasCorto.size()-1; i++){
                    double aux = caminoMasCorto.get(i);
                    int v = (int) aux;
                    listaDeCamino.add(v);
                }
                mostrarCam.setText(listaDeCamino.toString()+" costo: "+peso);
            }
        });
    }
}