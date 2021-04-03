package com.example.briskdelivery;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {
    int restId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = new Intent();
        intent = getIntent();
        restId = 0;

        if(intent != null){
            restId = (int) intent.getIntExtra("restId", 0);
        }

        DatabaseHelper dbh = new DatabaseHelper(this);
        ArrayList<Dish> dishes = dbh.GetMenu(restId);

        List<HashMap<String, String>> hmList = new ArrayList<HashMap<String, String>>();

        for (Dish d : dishes) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("title", d.getTitle());
            hm.put("desc", d.getDescription());
            hm.put("price", Double.toString(d.getPrice()));
            hmList.add(hm);
        }

        String[] from = {"title", "desc", "price"} ;
        int[] to = {R.id.txtItem, R.id.txtSubItem1, R.id.txtSubItem2};

        MenuAdapter madapter = new MenuAdapter(hmList);

        final ListView lvMenu = findViewById(R.id.lvMenu);
        lvMenu.setAdapter(madapter);

        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent dishIntent = new Intent(com.example.briskdelivery.Menu.this, MenuDish.class);
                int dishId = (int) id + 1;
                dishIntent.putExtra("dishId", dishId);
                dishIntent.putExtra("restId", restId);
                startActivity(dishIntent);
            }
        });

    }

    public void PlaceOrder(View view){
        DatabaseHelper dbh = new DatabaseHelper(this);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        int orderId = sp.getInt("orderId", 0);
        //int userId = sp.getInt("userId", 0);

        if(orderId == 0){
            Toast.makeText(this,"Please select a dish first", Toast.LENGTH_LONG).show();
        }else{
            this.finish();
            startActivity(new Intent(com.example.briskdelivery.Menu.this, com.example.briskdelivery.Checkout.class));
        }
    }

}
