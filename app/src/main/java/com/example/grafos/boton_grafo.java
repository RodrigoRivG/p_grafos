package com.example.grafos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.example.grafos.Excepciones.AristaYaExisteExcepcion;
import com.example.grafos.Excepciones.NroDeVerticesInvalidoExcepcion;
import com.example.grafos.GrafosNoPesados.Grafo;


public class boton_grafo extends AppCompatActivity {
    Grafo unGrafo;
    Button botonMostrar;
    Button b_insertarVert;
    Button b_eliminarVert;
    EditText casillaDeNumAEli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boton_grafo);

        botonMostrar = findViewById(R.id.mostrarGrafo);

        try {
            this.unGrafo = new Grafo(5);
            this.unGrafo.insertarArista(0, 1);
            this.unGrafo.insertarArista(0, 3);
            this.unGrafo.insertarArista(1, 2);
            this.unGrafo.insertarArista(2, 4);
            this.unGrafo.insertarArista(2, 3);
            botonMostrar.setText(this.unGrafo.toString());
        } catch (NroDeVerticesInvalidoExcepcion e) {
            e.printStackTrace();
        } catch (AristaYaExisteExcepcion e) {
            e.printStackTrace();
        }

        b_insertarVert = findViewById(R.id.insertVert);
        b_insertarVert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unGrafo.insertarVertice();
                botonMostrar.setText(unGrafo.toString());
            }
        });


        b_eliminarVert = findViewById(R.id.eliminarVert);
        casillaDeNumAEli = findViewById(R.id.eli_numvert);
        b_eliminarVert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String verticeAElimar = casillaDeNumAEli.getText().toString();
                int vertAElim = Integer.parseInt(verticeAElimar);
                unGrafo.eliminarVertice(vertAElim);
                botonMostrar.setText(unGrafo.toString());
            }
        });


    }
}