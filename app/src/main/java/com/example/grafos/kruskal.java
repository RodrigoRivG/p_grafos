package com.example.grafos;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.grafos.Excepciones.AristaNoExisteExcepcion;
import com.example.grafos.Excepciones.AristaYaExisteExcepcion;
import com.example.grafos.Excepciones.NroDeVerticesInvalidoExcepcion;
import com.example.grafos.GrafosPesados.GrafoPesado;
import com.example.grafos.GrafosPesados.Kruskal;

public class kruskal extends AppCompatActivity {
    Button b_grafoA;
    Button b_grafoB;
    Button b_gOriginal;
    Button b_gExp;

    GrafoPesado grafoA;
    GrafoPesado grafoB;
    Kruskal kgrafo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kruskal);

        b_gOriginal = findViewById(R.id.b_grafoOri);
        b_gExp = findViewById(R.id.b_grafoExp);

        b_grafoA = findViewById(R.id.grafoA);
        b_grafoA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    grafoA = new GrafoPesado(7);
                    grafoA.insertarArista(0, 1, 2);
                    grafoA.insertarArista(0, 3, 2);
                    grafoA.insertarArista(0, 2, 4);
                    grafoA.insertarArista(1, 5, 7);
                    grafoA.insertarArista(1, 4, 10);
                    grafoA.insertarArista(2, 5, 8);
                    grafoA.insertarArista(2, 3, 5);
                    grafoA.insertarArista(2, 6, 10);
                    grafoA.insertarArista(4, 6, 5);
                    grafoA.insertarArista(4, 5, 4);
                    b_gOriginal.setText(grafoA.toString());

                    kgrafo = new Kruskal(grafoA);
                    b_gExp.setText(kgrafo.obtenerArbolExpansionMinima().toString());

                } catch (NroDeVerticesInvalidoExcepcion e) {
                    e.printStackTrace();
                } catch (AristaYaExisteExcepcion e) {
                    e.printStackTrace();
                } catch (AristaNoExisteExcepcion e) {
                    e.printStackTrace();
                }
            }
        });

        b_grafoB = findViewById(R.id.cGrafoB);
        b_grafoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    grafoB = new GrafoPesado(7);
                    grafoB.insertarArista(0, 1, 2);
                    grafoB.insertarArista(0, 3, 2);
                    grafoB.insertarArista(0, 2, 4);
                    grafoB.insertarArista(1, 4, 10);
                    grafoB.insertarArista(1, 5, 7);
                    grafoB.insertarArista(2, 5, 8);
                    grafoB.insertarArista(2, 6, 10);
                    grafoB.insertarArista(4, 5, 4);
                    grafoB.insertarArista(4, 6, 5);
                    b_gOriginal.setText(grafoB.toString());

                    kgrafo = new Kruskal(grafoB);
                    b_gExp.setText(kgrafo.obtenerArbolExpansionMinima().toString());
                } catch (NroDeVerticesInvalidoExcepcion e) {
                    e.printStackTrace();
                } catch (AristaYaExisteExcepcion e) {
                    e.printStackTrace();
                } catch (AristaNoExisteExcepcion e) {
                    e.printStackTrace();
                }

            }
        });
    }
}