package com.annleal34.pointsnearme;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.libraries.places.api.model.Place;


public class MenuPrincipal extends AppCompatActivity {
    private ImageView autoPeca;
    private ImageView bombeiro;
    private ImageView combustivel;
    private ImageView hospital;
    private ImageView guincho;
    private ImageView oMecanica;
    private ImageView barPub;
    private ImageView hotel;
    private ImageView Mercado;
    private ImageView restaurante;
    private ImageView policia;
    public  static String typeFilter =" ";

    public  static String pesquisaMain =" ";

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        hospital= (ImageView) findViewById(R.id.Hospital);
        combustivel= (ImageView) findViewById(R.id.combustivel);
        autoPeca= (ImageView) findViewById(R.id.autopeca);
        bombeiro= (ImageView) findViewById(R.id.bombeiro);
        guincho= (ImageView) findViewById(R.id.Guincho);
        oMecanica= (ImageView) findViewById(R.id.oficina);
        barPub= (ImageView) findViewById(R.id.Bar);
        hotel= (ImageView) findViewById(R.id.Hotel);
        Mercado= (ImageView) findViewById(R.id.Mercado);
        restaurante= (ImageView) findViewById(R.id.Restaurantes);
        policia= (ImageView) findViewById(R.id.Policia);

        hospital.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                pesquisaMain = "Hospital";
                TelaPrincipal.pesquisa = MenuPrincipal.pesquisaMain;
                typeFilter ="hospital";
                TelaPrincipal.idFilter = typeFilter;
                Intent telaPrincipal = new Intent(MenuPrincipal.this,  MainActivity.class);
                startActivity(telaPrincipal);
            }
        });

        policia.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                pesquisaMain = "policia";
                TelaPrincipal.pesquisa = MenuPrincipal.pesquisaMain;
                typeFilter ="police";
                TelaPrincipal.idFilter = typeFilter;
                Intent telaPrincipal = new Intent(MenuPrincipal.this,  MainActivity.class);
                startActivity(telaPrincipal);
            }
        });

        hotel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                pesquisaMain = "Hotel";
                TelaPrincipal.pesquisa = MenuPrincipal.pesquisaMain;
                typeFilter ="hotel";
                TelaPrincipal.idFilter = typeFilter;
                Intent telaPrincipal = new Intent(MenuPrincipal.this,  MainActivity.class);
                startActivity(telaPrincipal);
            }
        });

        bombeiro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                pesquisaMain = "Bombeiro";
                TelaPrincipal.pesquisa = MenuPrincipal.pesquisaMain;
                typeFilter ="FIRE_STATION ";
                TelaPrincipal.idFilter = typeFilter;
                Intent telaPrincipal = new Intent(MenuPrincipal.this,  MainActivity.class);
                startActivity(telaPrincipal);
            }
        });

        guincho.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                pesquisaMain = "guincho";
                TelaPrincipal.pesquisa = MenuPrincipal.pesquisaMain;
                typeFilter ="guincho";
                TelaPrincipal.idFilter = typeFilter;
                Intent telaPrincipal = new Intent(MenuPrincipal.this,  MainActivity.class);
                startActivity(telaPrincipal);
            }
        });

        combustivel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                pesquisaMain = "Posto de Combustível";
                TelaPrincipal.pesquisa = MenuPrincipal.pesquisaMain;
                typeFilter ="gas_station";
                TelaPrincipal.idFilter = typeFilter;
                Intent telaPrincipal = new Intent(MenuPrincipal.this,  MainActivity.class);
                startActivity(telaPrincipal);
            }
        });





    }
}