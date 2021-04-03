package com.example.briskdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Buttons extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons);

        Button rListBtn = findViewById(R.id.rListBtn);
      //  Button aDtlsBtn = findViewById(R.id.aDtlsBtn);
//viewMap
        Button oHisbtn = findViewById(R.id.oHisbtn);
        Button viewMap = findViewById(R.id.viewMap);

        rListBtn.setOnClickListener((View v) -> {

            startActivity(new Intent(Buttons.this, com.example.briskdelivery.RestaurantList.class));

        });

//        aDtlsBtn.setOnClickListener((View v) -> {
//
//            startActivity(new Intent(Buttons.this, History.class));
//
//        });

        oHisbtn.setOnClickListener((View v) -> {

            startActivity(new Intent(Buttons.this, com.example.briskdelivery.History.class));

        });

        viewMap.setOnClickListener((View v) -> {

            startActivity(new Intent(Buttons.this, MapsActivity.class)
            );

        });
    }
}