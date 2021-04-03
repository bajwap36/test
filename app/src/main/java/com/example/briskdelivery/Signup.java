package com.example.briskdelivery;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class Signup extends AppCompatActivity  implements GoogleApiClient.OnConnectionFailedListener {
    boolean isEdit = false;
    EditText fName,lName,phoneNum,address,email,password;
    ImageView profileImage;
    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button signUpButton = findViewById(R.id.signUpButton);


        Intent getAccount = getIntent();

         fName = findViewById(R.id.fName);
         lName = findViewById(R.id.lName);

         phoneNum = findViewById(R.id.phoneNum);
         address = findViewById(R.id.address);
       // EditText etZIP = (EditText)findViewById(R.id.zipCode);
         email = findViewById(R.id.email);
         password = findViewById(R.id.password);
       // Button btnSignup = findViewById(R.id.signUpButton);

        //Intent getAccount = getIntent();

        if(getAccount != null){
            if(getAccount.getStringExtra("type") != null )
                isEdit = (getAccount.getStringExtra("type").equals("edit"));
        if (isEdit){
                fName.setText(getAccount.getStringExtra("fname"));
            lName.setText(getAccount.getStringExtra("lname"));

            email.setText(getAccount.getStringExtra("email"));
                address.setText(getAccount.getStringExtra("address"));
                phoneNum.setText(getAccount.getStringExtra("phone"));
             //   etZIP.setText(getAccount.getStringExtra("zip"));
            signUpButton.setText("Update Information");
            }

        }

        signUpButton.setOnClickListener((View v) -> {

//            EditText etName = (EditText)findViewById(R.id.etName);
//            EditText etPhone = (EditText)findViewById(R.id.etPhone);
//            EditText etAddress = (EditText)findViewById(R.id.etAddress);
//            EditText etZIP = (EditText)findViewById(R.id.etZIP);
//            EditText etEmail = (EditText)findViewById(R.id.email);
//            EditText etPassword = (EditText)findViewById(R.id.password);

            final SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

//            String fnames[] = fName.getText().toString().split(" ");
//            String lnames[] = lName.getText().toString().split(" ");


            User newUser = new User(email.getText().toString(),
                    fName.getText().toString(),
                    lName.getText().toString(),
                    phoneNum.getText().toString(),
                    address.getText().toString(),
                    01);
            DatabaseHelper dbh = new DatabaseHelper(this);
            //check if email already exists or not
            boolean emailExists = dbh.checkEmailExists(newUser.getEmail());

            //update query
            if(isEdit){
                newUser.setuId(sp.getInt("userId", 0));
                dbh.updateUser(newUser);
                Toast.makeText(this, "Information saved.", Toast.LENGTH_LONG).show(); ;
                //start next activity
                Intent options = new Intent(com.example.briskdelivery.Signup.this, Options.class);
                this.finish();
                startActivity(options);
            }else {
                if (!emailExists) {
                    int id = (int) dbh.addUser(newUser);
                    if (id == -1) {
                        Toast.makeText(this, "Please check information", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "Information saved.", Toast.LENGTH_LONG).show();

                        //start next activity
                        Intent options = new Intent(com.example.briskdelivery.Signup.this, Options.class);

                        //storage id in the shared preferences
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putInt("userId", id);
                        editor.commit();
                        this.finish();
                        startActivity(options);
                    }
                }else {
                    Toast.makeText(this, "E-mail already exists in the database.", Toast.LENGTH_LONG).show();
                }
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> opr= Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if(opr.isDone()){
            GoogleSignInResult result=opr.get();
            handleSignInResult(result);
        }else{
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    private void handleSignInResult(GoogleSignInResult result){
        if(result.isSuccess()){
            GoogleSignInAccount account=result.getSignInAccount();
            fName.setText(account.getDisplayName());
            email.setText(account.getEmail());
           // user.setText(account.getId());
            try{
                Glide.with(this).load(account.getPhotoUrl()).into(profileImage);
            }catch (NullPointerException e){
                Toast.makeText(getApplicationContext(),"image not found", Toast.LENGTH_LONG).show();
            }

        }else{
            gotoMainActivity();
        }
    }

    private void gotoMainActivity(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

//    public void Signup(View view){
//        EditText etName = (EditText)findViewById(R.id.etName);
//        EditText etPhone = (EditText)findViewById(R.id.etPhone);
//        EditText etAddress = (EditText)findViewById(R.id.etAddress);
//        EditText etZIP = (EditText)findViewById(R.id.etZIP);
//        EditText etEmail = (EditText)findViewById(R.id.email);
//        EditText etPassword = (EditText)findViewById(R.id.password);
//
//        final SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
//
//        String names[] = etName.getText().toString().split(" ");
//
//        com.example.briskdelivery.User newUser = new com.example.briskdelivery.User(etEmail.getText().toString(),
//                names[0], (names.length > 1 ? names[1] : ""),
//                etPhone.getText().toString(),
//                etAddress.getText().toString(),
//                etZIP.getText().toString(),
//                etPassword.getText().toString());
//        com.example.briskdelivery.DatabaseHelper dbh = new com.example.briskdelivery.DatabaseHelper(this);
//        boolean emailExists = dbh.checkEmailExists(newUser.getEmail());
//
//        if(isEdit){
//            newUser.setUserId(sp.getInt("userId", 0));
//            dbh.updateUser(newUser);
//            Toast.makeText(this, "Information saved.", Toast.LENGTH_LONG).show(); ;
//            //start next activity
//            Intent options = new Intent(com.example.briskdelivery.Signup.this, Options.class);
//            this.finish();
//            startActivity(options);
//        }else {
//            if (!emailExists) {
//                int id = (int) dbh.addUser(newUser);
//                if (id == -1) {
//                    Toast.makeText(this, "Please check information", Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(this, "Information saved.", Toast.LENGTH_LONG).show();
//
//                    //start next activity
//                    Intent options = new Intent(com.example.briskdelivery.Signup.this, Options.class);
//
//                    //storage id in the shared preferences
//                    SharedPreferences.Editor editor = sp.edit();
//                    editor.putInt("userId", id);
//                    editor.commit();
//                    this.finish();
//                    startActivity(options);
//                }
//            }else {
//            }else {
//                Toast.makeText(this, "E-mail already exists in the database.", Toast.LENGTH_LONG).show();
//            }
//        }
//    }

}
