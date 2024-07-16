package com.example.grafos;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.grafos.Excepciones.AristaYaExisteExcepcion;
import com.example.grafos.Excepciones.NroDeVerticesInvalidoExcepcion;
import com.example.grafos.GrafosNoPesados.DiGrafo;
import com.example.grafos.GrafosNoPesados.Warshall;

public class warshall extends AppCompatActivity {
    Button b_mDiGrafo;
    Button b_mW;

    DiGrafo grafoDig;
    Warshall grafoW;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warshall);

        b_mDiGrafo = findViewById(R.id.bmDig);
        b_mW = findViewById(R.id.bmMat);
        try {
            grafoDig = new DiGrafo(6);
            grafoDig.insertarArista(1, 4);
            grafoDig.insertarArista(1, 3);
            grafoDig.insertarArista(2, 0);
            grafoDig.insertarArista(3, 5);
            grafoDig.insertarArista(4, 0);
            grafoDig.insertarArista(4, 2);
            grafoDig.insertarArista(5, 2);
            grafoDig.insertarArista(5, 0);
            b_mDiGrafo.setText(grafoDig.toString());
            grafoW = new Warshall(grafoDig);
            b_mW.setText(grafoW.matrizDeCaminos());
        } catch (NroDeVerticesInvalidoExcepcion e) {
            e.printStackTrace();
        } catch (AristaYaExisteExcepcion e) {
            e.printStackTrace();
        }

    }
}