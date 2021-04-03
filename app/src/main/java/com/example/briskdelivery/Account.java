package com.example.briskdelivery;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class Account extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Button editBtn = findViewById(R.id.editBtn);
        Button logoutBtn = findViewById(R.id.logoutBtn);



        TextView tvAccName = findViewById(R.id.afname);
        TextView tvAccEmail = findViewById(R.id.aemail);
        TextView tvAccPhone = findViewById(R.id.aphonenum);
        TextView tvAccAdd = findViewById(R.id.aaddress);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        int userId = sp.getInt("userId", 0);

        com.example.briskdelivery.DatabaseHelper dhb = new com.example.briskdelivery.DatabaseHelper(this);
        User user = dhb.GetUser(userId);

        tvAccName.setText(user.getFullName());
        tvAccEmail.setText(user.getEmail());
        tvAccAdd.setText(user.getAddress());
        tvAccPhone.setText(user.getPhone());
        //tvAccZip.setText(user.getZIPCode());

        editBtn.setOnClickListener((View v) -> {

//            TextView tvAccName = findViewById(R.id.afname);
//            TextView tvAccEmail = findViewById(R.id.aemail);
//            TextView tvAccPhone = findViewById(R.id.aphonenum);
//            TextView tvAccAdd = findViewById(R.id.aaddress);

            Intent signup = new Intent(Account.this, com.example.briskdelivery.ProfileActivity.class);
            signup.putExtra("name", tvAccName.getText().toString());
            signup.putExtra("email",tvAccEmail.getText().toString() );
            signup.putExtra("address", tvAccAdd.getText().toString());
            signup.putExtra("phone", tvAccPhone.getText().toString());
            signup.putExtra("type", "edit");
            startActivity(signup);
        });

        logoutBtn.setOnClickListener((View v) -> {

//            TextView tvAccName = findViewById(R.id.afname);
//            TextView tvAccEmail = findViewById(R.id.aemail);
//            TextView tvAccPhone = findViewById(R.id.aphonenum);
//            TextView tvAccAdd = findViewById(R.id.aaddress);

         //   SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = sp.edit();
            editor.remove("userId");
            editor.commit();
            startActivity(new Intent(com.example.briskdelivery.Account.this, MainActivity.class));
        });

    }



//    public void EditAccount(View view){
//        TextView tvAccName = findViewById(R.id.afname);
//        TextView tvAccEmail = findViewById(R.id.aemail);
//        TextView tvAccPhone = findViewById(R.id.aphonenum);
//        TextView tvAccAdd = findViewById(R.id.aaddress);
//
//        Intent signup = new Intent(Account.this, com.example.briskdelivery.Signup.class);
//        signup.putExtra("name", tvAccName.getText().toString());
//        signup.putExtra("email",tvAccEmail.getText().toString() );
//        signup.putExtra("address", tvAccAdd.getText().toString());
//        signup.putExtra("phone", tvAccPhone.getText().toString());
//        signup.putExtra("type", "edit");
//        startActivity(signup);
//    }

//    public void Logout(View view){
//        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
//        SharedPreferences.Editor editor = sp.edit();
//        editor.remove("userId");
//        editor.commit();
//        startActivity(new Intent(com.example.briskdelivery.Account.this, MainActivity.class));
//    }
}
