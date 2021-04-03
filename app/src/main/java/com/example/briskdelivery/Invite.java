package com.example.briskdelivery;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Invite extends AppCompatActivity {
    DatabaseHelper dbh;
    SharedPreferences sp;
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);

        dbh = new DatabaseHelper(this);
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        userId = sp.getInt("userId", 0);

        /*Button btnInvite = findViewById(R.id.btnInvite);
        btnInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            EditText email = findViewById(R.id.etEmailInvite);

                dbh.addInvite(userId, email.getText().toString());
//                Toast.makeText(this, "Invite sent", Toast.LENGTH_SHORT).show();
                email.setText("");
            }
        });
*/
    }

    public void invite(View view){
        EditText email = findViewById(R.id.etEmailInvite);
     //   dbh.addInvite(userId, email.getText().toString());
        Toast.makeText(this, "Invite sent", Toast.LENGTH_SHORT).show();
        email.setText("");
    }

}
