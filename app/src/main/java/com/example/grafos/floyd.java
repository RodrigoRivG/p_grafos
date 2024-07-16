package com.example.grafos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.grafos.Excepciones.AristaYaExisteExcepcion;
import com.example.grafos.Excepciones.NroDeVerticesInvalidoExcepcion;
import com.example.grafos.GrafosPesados.DiGrafoPesado;
import com.example.grafos.GrafosPesados.Floyd;

public class floyd extends AppCompatActivity {
    Button b_mostraDig;
    Button b_mostrarMCos;
    Button b_mostrarPred;

    DiGrafoPesado unDigPesado;
    Floyd floydGrafo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floyd);

        b_mostraDig = findViewById(R.id.b_grafo);
        b_mostrarMCos = findViewById(R.id.b_matDeC);
        b_mostrarPred = findViewById(R.id.b_mDePred);

        try {
            unDigPesado = new DiGrafoPesado(7);
            unDigPesado.insertarArista(0,1,10);
            unDigPesado.insertarArista(0,2,20);
            unDigPesado.insertarArista(1,4,50);
            unDigPesado.insertarArista(2,3,13);
            unDigPesado.insertarArista(2,6,5);
            unDigPesado.insertarArista(3,6,25);
            unDigPesado.insertarArista(3,5,15);
            unDigPesado.insertarArista(4,5,35);
            unDigPesado.insertarArista(5,6,40);
            unDigPesado.insertarArista(6,2,5);
            b_mostraDig.setText(unDigPesado.toString());
            floydGrafo = new Floyd(unDigPesado);
            b_mostrarMCos.setText(floydGrafo.matrizDeCaminosMasCortos());
            b_mostrarPred.setText(floydGrafo.matrizDePredecesores());
        } catch (NroDeVerticesInvalidoExcepcion e) {

        } catch (AristaYaExisteExcepcion e) {
            e.printStackTrace();
        }

    }
}