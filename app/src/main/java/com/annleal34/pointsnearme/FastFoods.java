package com.annleal34.pointsnearme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.libraries.places.api.net.FetchPlaceRequest;

public class FastFoods extends AppCompatActivity {
    Button mcDonalds;
    public  static String pesquisaFastFood =" ";
    public  static String typeFilter =" ";
    Button bobs;
    Button chinaInBox;
    Button KFC;
    Button subway;
    Button habibs;
    Button fastFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_foods);

        mcDonalds = (Button) findViewById(R.id.idmcDonalds);
        KFC = (Button) findViewById(R.id.idKFC);
        bobs = (Button) findViewById(R.id.idBobs);
        chinaInBox = (Button) findViewById(R.id.idChina);
        subway = (Button) findViewById(R.id.idSubway);
        habibs = (Button) findViewById(R.id.idHabibs);
        fastFood = (Button) findViewById(R.id.idfast);

        mcDonalds.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                pesquisaFastFood = "McDonald's";
                TelaPrincipal.pesquisa = FastFoods.pesquisaFastFood;
                typeFilter ="restaurant";
                TelaPrincipal.idFilter = typeFilter;
                Intent telaPrincipal = new Intent(FastFoods.this,  MainActivity.class);
                startActivity(telaPrincipal);
            }
        });

        KFC.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                pesquisaFastFood = "Kentucky Fried Chicken";
                TelaPrincipal.pesquisa = FastFoods.pesquisaFastFood;
                typeFilter ="restaurant";
                TelaPrincipal.idFilter = typeFilter;
                Intent telaPrincipal = new Intent(FastFoods.this,  MainActivity.class);
                startActivity(telaPrincipal);
            }
        });

        bobs.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                pesquisaFastFood = "BOB's";
                TelaPrincipal.pesquisa = FastFoods.pesquisaFastFood;
                typeFilter ="Restaurant";
                TelaPrincipal.idFilter = typeFilter;
                Intent telaPrincipal = new Intent(FastFoods.this,  MainActivity.class);
                startActivity(telaPrincipal);
            }
        });

        chinaInBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                pesquisaFastFood = "China In Box";
                TelaPrincipal.pesquisa = FastFoods.pesquisaFastFood;
                typeFilter ="Restaurant";
                TelaPrincipal.idFilter = typeFilter;
                Intent telaPrincipal = new Intent(FastFoods.this,  MainActivity.class);
                startActivity(telaPrincipal);
            }
        });

        subway.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                pesquisaFastFood = "Subway";
                TelaPrincipal.pesquisa = FastFoods.pesquisaFastFood;
                typeFilter ="restaurant";
                TelaPrincipal.idFilter = typeFilter;
                Intent telaPrincipal = new Intent(FastFoods.this,  MainActivity.class);
                startActivity(telaPrincipal);
            }
        });

        habibs.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                pesquisaFastFood = "Habibs";
                TelaPrincipal.pesquisa = FastFoods.pesquisaFastFood;
                typeFilter ="restaurant";
                TelaPrincipal.idFilter = typeFilter;
                Intent telaPrincipal = new Intent(FastFoods.this,  MainActivity.class);
                startActivity(telaPrincipal);
            }
        });

        fastFood.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                pesquisaFastFood = "Delivery" ;
                typeFilter ="restaurant";
                TelaPrincipal.idFilter = typeFilter;
                TelaPrincipal.pesquisa = FastFoods.pesquisaFastFood;
                Intent telaPrincipal = new Intent(FastFoods.this,  MainActivity.class);
                startActivity(telaPrincipal);
            }
        });




    }



}