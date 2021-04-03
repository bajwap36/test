package com.example.briskdelivery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class Options extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        Button rListBtn = findViewById(R.id.rListBtn33333);
        Button aDtlsBtn = findViewById(R.id.adtls3333);

        Button oHisbtn = findViewById(R.id.oHisbtn3333);


        rListBtn.setOnClickListener((View v) -> {

            startActivity(new Intent(com.example.briskdelivery.Options.this, com.example.briskdelivery.RestaurantList.class));

        });

        aDtlsBtn.setOnClickListener((View v) -> {

            startActivity(new Intent(com.example.briskdelivery.Options.this, History.class));

        });

        oHisbtn.setOnClickListener((View v) -> {

            startActivity(new Intent(com.example.briskdelivery.Options.this, com.example.briskdelivery.OrderDetail.class));

        });


    }


//    public void ToRestaurant(View view){
//        startActivity(new Intent(com.example.briskdelivery.Options.this, com.example.briskdelivery.RestaurantList.class));
//    }
//
//    public void ToHistory(View view){
//        startActivity(new Intent(com.example.briskdelivery.Options.this, History.class));
//    }
//
//    public void ToAccount(View view){
//        startActivity(new Intent(com.example.briskdelivery.Options.this, com.example.briskdelivery.Account.class));
//    }
//
//    public void ToInvite(View view) {startActivity(new Intent(com.example.briskdelivery.Options.this, Invite.class));}
}
