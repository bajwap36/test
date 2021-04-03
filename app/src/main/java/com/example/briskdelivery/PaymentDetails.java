package com.example.briskdelivery;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.JsonReader;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class PaymentDetails extends AppCompatActivity {


    TextView paypalId, paypalAmnt, paypalSt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);

        paypalId = findViewById(R.id.paypalId);
        paypalAmnt = findViewById(R.id.paypalAmnt);
        paypalSt = findViewById(R.id.paypalSt);

        Intent intent = getIntent();

        try {
            JSONObject jj = new JSONObject(intent.getStringExtra("PaymentDetails"));
            showDetails(jj.getJSONObject("response"),intent.getStringExtra("PaymentAmount"));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void showDetails(JSONObject response, String paymentAmount) {

        try{
            paypalId.setText(response.getString("id"));
            paypalAmnt.setText(response.getString("state"));
            paypalSt.setText(response.getString(String.format(("$%s"),paymentAmount)));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}