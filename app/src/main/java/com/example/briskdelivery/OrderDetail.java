package com.example.briskdelivery;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


public class OrderDetail extends AppCompatActivity {

    int orderId =0;
    int userId =0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_order_detail);

        Intent intent = new Intent();
        intent = getIntent();
        orderId = 0;

        if(intent != null){
            orderId = (int) intent.getIntExtra("orderId", 0);
            userId = (int) intent.getIntExtra("userId", 0);

        }

        DatabaseHelper dbh = new DatabaseHelper(this);
        ArrayList<Orders> orders = dbh.GetOrders(userId);

        List<HashMap<String, String>> hmList = new ArrayList<HashMap<String, String>>();

        for (Orders d : orders) {
            HashMap<String, String> hm = new HashMap<String, String>();
//            hm.put("OrderId", d.getOrderId());
//
//            hm.put("price", d.getOrderTotal());
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
                Intent dishIntent = new Intent(com.example.briskdelivery.OrderDetail.this, MenuDish.class);
                int dishId = (int) id + 1;
                dishIntent.putExtra("dishId", dishId);
                dishIntent.putExtra("restId", orderId);
                startActivity(dishIntent);
            }
        });

    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_order_detail, container, false);
//    }

//    public void LoadOrderDetail(Orders orders){
//        TextView ftvRestName = (TextView) getActivity().findViewById(R.id.ftvRestName);
//        ftvRestName.setText(orders.toString());
//
//        com.example.briskdelivery.DatabaseHelper dbh = new com.example.briskdelivery.DatabaseHelper(super.getContext());
//        Cursor c = dbh.viewItems(orders.orderId);
//
//        if(c.getCount() > 0){
//            TextView items = (TextView) getActivity().findViewById(R.id.ftvItem);
//            StringBuilder sb = new StringBuilder();
//            while (c.moveToNext()){
//                 sb.append(c.getString(0) + " Qty. " + c.getInt(1)+ "\n");
//            }
//            items.setText("Order Items\n"+sb);
//        }
//    }

}
