package com.example.grafos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button boton_grafo;
    Button boton_digrafo;
    Button boton_dijkstra;
    Button boton_kruskal;
    Button boton_prim;
    Button boton_floyd;
    Button b_warshall;
    Button b_ordenT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton_grafo = findViewById(R.id.boton_grafo);
        boton_grafo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent grafos = new Intent(MainActivity.this, boton_grafo.class);
                startActivity(grafos);
            }
        });

        boton_digrafo = findViewById(R.id.boton_digrafo);
        boton_digrafo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent digrafo = new Intent(MainActivity.this, boton_digrafo.class);
                startActivity(digrafo);
            }
        });

        boton_dijkstra = findViewById(R.id.Dijkstra);
        boton_dijkstra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dijkstra = new Intent(MainActivity.this, com.example.grafos.dijkstra.class);
                startActivity(dijkstra);
            }
        });

        boton_kruskal = findViewById(R.id.Kruskal);
        boton_kruskal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kruskal = new Intent(MainActivity.this, com.example.grafos.kruskal.class);
                startActivity(kruskal);
            }
        });

        boton_prim = findViewById(R.id.Prim);
        boton_prim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent prim = new Intent(MainActivity.this, prim.class);
                startActivity(prim);
            }
        });

        boton_floyd = findViewById(R.id.Floyd);
        boton_floyd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent floyd = new Intent(MainActivity.this, com.example.grafos.floyd.class);
                startActivity(floyd);
            }
        });

        b_warshall = findViewById(R.id.boton_Warshall);
        b_warshall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent warshall = new Intent(MainActivity.this, warshall.class);
                startActivity(warshall);
            }
        });

        b_ordenT = findViewById(R.id.boton_OrdenTop);
        b_ordenT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent orden = new Intent(MainActivity.this, com.example.grafos.ordenTop.class);
                startActivity(orden);
            }
        });

    }
}