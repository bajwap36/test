package com.example.briskdelivery;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.briskdelivery.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class History extends AppCompatActivity {

    ArrayList<com.example.briskdelivery.Orders> ordersList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Button backBtn = findViewById(R.id.backBtn);


        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        int userId = sp.getInt("userId", 0);

        com.example.briskdelivery.DatabaseHelper dhb = new com.example.briskdelivery.DatabaseHelper(this);
        //ArrayList<Orders> ordersList = dhb.GetOrders(userId);
        final ArrayList<com.example.briskdelivery.Orders> ordersList = dhb.GetOrders(userId);

        List<HashMap<String, String>> hmList = new ArrayList<HashMap<String, String>>();

        for (com.example.briskdelivery.Orders o : ordersList) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("id", "Order Number: " + Integer.toString(o.getOrderId()));
            hm.put("date", "Date: " + o.getDate());
            hm.put("price", "Total: " + o.getOrderTotal());
            hmList.add(hm);
        }

        String[] from = {"id", "date", "price"} ;
        int[] to = {R.id.txtItem, R.id.txtSubItem1, R.id.txtSubItem2};

        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), hmList, R.layout.listview_menu, from, to);

        ListView lvOrders = (ListView) findViewById(R.id.lvOrders);
        lvOrders.setAdapter(adapter);

        lvOrders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent hisintent = new Intent( History.this,  OrderDetail.class);
                //int restId = (int) id + 1;
                int orderId = ordersList.get(position).getOrderId();
                hisintent.putExtra("orderId", orderId);
                hisintent.putExtra("userId", userId);

                startActivity(hisintent);
            }
        });

        backBtn.setOnClickListener((View v) -> {

            startActivity(new Intent(History.this, com.example.briskdelivery.Buttons.class));

        });



//        lvOrders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                FragmentManager fm = getSupportFragmentManager();
//               com.example.briskdelivery.OrderDetail fOrder = (com.example.briskdelivery.OrderDetail)fm.findFragmentById(R.id.fragment);
//                fOrder.LoadOrderDetail(ordersList.get(position));
//            }
//        });

    }
}
