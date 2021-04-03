package com.example.briskdelivery;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginPageBtn = findViewById(R.id.loginPageBtn);


        loginPageBtn.setOnClickListener((View v) -> {

            EditText email = findViewById(R.id.email);
            EditText password = findViewById(R.id.password);

            DatabaseHelper databseHelper = new DatabaseHelper(this);
            User user = new User();
            user.setEmail(email.getText().toString());
            user.setUpass(password.getText().toString());
            int id = databseHelper.checkUser(user);

            if(id != 0){
                //start next activity
                Intent options = new Intent(Login.this, Options.class);
                //restList.putExtra("id", id);

                //storage id in the shared preferences
                final SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("userId", id);
                editor.commit();
                startActivity(options);
            }else{
                Toast.makeText(this, "Invalid information.", Toast.LENGTH_LONG).show(); ;
            }
        });
    }

//    public void Login(View view){
//        EditText etEmail = (EditText)findViewById(R.id.etEmail);
//        EditText etPassword = (EditText)findViewById(R.id.etPassword);
//
//        DatabaseHelper dbh = new DatabaseHelper(this);
//        User user = new User();
//        user.setEmail(etEmail.getText().toString());
//        user.setPassword(etPassword.getText().toString());
//        int id = dbh.checkUser(user);
//
//        if(id != 0){
//            //start next activity
//            Intent options = new Intent(Login.this, Options.class);
//            //restList.putExtra("id", id);
//
//            //storage id in the shared preferences
//            final SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
//            SharedPreferences.Editor editor = sp.edit();
//            editor.putInt("userId", id);
//            editor.commit();
//            startActivity(options);
//        }else{
//            Toast.makeText(this, "Invalid information.", Toast.LENGTH_LONG).show(); ;
//        }
//    }
}
