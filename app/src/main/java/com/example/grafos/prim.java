package com.example.grafos;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.grafos.Excepciones.AristaYaExisteExcepcion;
import com.example.grafos.Excepciones.NroDeVerticesInvalidoExcepcion;
import com.example.grafos.GrafosPesados.GrafoPesado;
import com.example.grafos.GrafosPesados.Prim;

public class prim extends AppCompatActivity {
    GrafoPesado grafoA;
    GrafoPesado grafoB;
    Prim primGrafo;

    Button b_grafoA; //cargar grafoA
    Button b_grafoB; //cargar grafoB
    Button b_mGrafoO;
    Button b_mGrafoA;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prim);

        b_grafoA = findViewById(R.id.cGrafoA);
        b_grafoB = findViewById(R.id.Grafo_B);
        b_mGrafoO = findViewById(R.id.mGrafoO);
        b_mGrafoA = findViewById(R.id.mGrafoAux);

        b_grafoA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //grafoA = new GrafoPesado(6);
                    grafoA = new GrafoPesado(6);
                    grafoA.insertarArista(0, 1, 8);
                    grafoA.insertarArista(0, 2, 6);
                    grafoA.insertarArista(0, 4, 12);
                    grafoA.insertarArista(1, 2, 5);
                    grafoA.insertarArista(1, 4, 8);
                    grafoA.insertarArista(1, 5, 3);
                    grafoA.insertarArista(2, 3, 10);
                    grafoA.insertarArista(3, 5, 7);
                    b_mGrafoO.setText(grafoA.toString());
                    primGrafo = new Prim(grafoA);
                    b_mGrafoA.setText(primGrafo.arbolDeExpansion().toString());
                } catch (NroDeVerticesInvalidoExcepcion e) {
                    e.printStackTrace();
                } catch (AristaYaExisteExcepcion e) {
                    e.printStackTrace();
                }
            }
        });

        b_grafoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    grafoB=new GrafoPesado(6);
                    grafoB.insertarArista(0,1,10);
                    grafoB.insertarArista(0,2,15);
                    grafoB.insertarArista(1,2,20);
                    grafoB.insertarArista(1,3,30);
                    grafoB.insertarArista(2,3,5);
                    grafoB.insertarArista(4,1,9);
                    grafoB.insertarArista(4,5,7);
                    b_mGrafoO.setText(grafoB.toString());
                    primGrafo =new Prim(grafoB);
                    b_mGrafoA.setText(primGrafo.arbolDeExpansion().toString());
                } catch (AristaYaExisteExcepcion e) {
                    e.printStackTrace();
                } catch (NroDeVerticesInvalidoExcepcion e) {
                    e.printStackTrace();
                }

            }
        });
    }
}