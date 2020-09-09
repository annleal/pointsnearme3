package com.annleal34.pointsnearme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.libraries.places.api.model.Place;

public class TelaPrincipal extends AppCompatActivity {
    public  static String pesquisa =" ";
    public  static String idFilter =" ";
    private ImageButton fastFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        fastFood = (ImageButton) findViewById(R.id.idFastFood);


        fastFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentFastFood = new Intent(TelaPrincipal.this,FastFoods.class);
                startActivity(intentFastFood);


            }
        });
    }
}