package com.example.grafos;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;

import com.example.grafos.Excepciones.AristaYaExisteExcepcion;
import com.example.grafos.Excepciones.NroDeVerticesInvalidoExcepcion;
import com.example.grafos.GrafosNoPesados.DiGrafo;
import com.example.grafos.GrafosNoPesados.OrdenamientoTopologico;

public class ordenTop extends AppCompatActivity {
    Button b_mdig;
    Button b_morden;

    DiGrafo unDig;
    OrdenamientoTopologico grafoOrd;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orden_top);

        b_mdig = findViewById(R.id.B_MDig);
        b_morden = findViewById(R.id.b_MOrd);

        try {
            unDig = new DiGrafo(6);
            unDig.insertarArista(0,1);
            unDig.insertarArista(0,2);
            unDig.insertarArista(0,3);
            unDig.insertarArista(3,2);
            unDig.insertarArista(3,4);
            unDig.insertarArista(4,2);
            unDig.insertarArista(5,3);
            b_mdig.setText(unDig.toString());
            grafoOrd = new OrdenamientoTopologico(unDig);
            b_morden.setText(grafoOrd.obtenerOrdenTopologico().toString());
        } catch (NroDeVerticesInvalidoExcepcion e) {
            e.printStackTrace();
        } catch (AristaYaExisteExcepcion e) {
            e.printStackTrace();
        }


    }
}