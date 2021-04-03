package com.example.briskdelivery;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
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
import com.google.android.gms.common.api.Status;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

public class ProfileActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    boolean isEdit = false;

    Button logoutBtn;
    TextView userName,userEmail;
    ImageView profileImage;
    EditText phoneNum,address;

    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;
    String idPass;
    String emailll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        Button save = findViewById(R.id.save);


        Intent getAccount = getIntent();


        phoneNum = findViewById(R.id.phoneNum);
        address = findViewById(R.id.address);



        logoutBtn=(Button)findViewById(R.id.logoutBtn);
        userName=(TextView)findViewById(R.id.name);
        userEmail=(TextView)findViewById(R.id.email);
       // userId=(TextView)findViewById(R.id.userId);
        profileImage=(ImageView)findViewById(R.id.profileImage);

        gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient=new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();
        final SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

        phoneNum.setText(sp.getString("phone",""));
        address.setText(sp.getString("address",""));
        DatabaseHelper dbh = new DatabaseHelper(this);

        boolean emailExists = dbh.checkEmailExists(emailll);

        if(emailExists){

            save.setText("update");
        }


        save.setOnClickListener((View v) -> {





            User newUser = new User(userName.getText().toString(),
                    userEmail.getText().toString(),
                    phoneNum.getText().toString(),
                    address.getText().toString(),
                    idPass,sp.getInt("userId",0));
            //check if email already exists or not


            if(emailExists){

                int uid = sp.getInt("userId",0);



                dbh.updateUser(newUser);
                Intent options = new Intent(ProfileActivity.this, Buttons.class);

                //storage id in the shared preferences
//                        SharedPreferences.Editor editor = sp.edit();
//                        editor.putInt("userId", id);
//                        editor.putString("phone", phoneNum.toString());
//                        editor.putString("address", address.toString());
//                        editor.commit();
//                        this.finish();
                startActivity(options);

//                Cursor c = dbh.selectUser(uid);
//
//                    StringBuilder sb = new StringBuilder();
//                    while (c.moveToNext()){
//                        sb.append(c.getString(0) + " Qty. " + c.getInt(1)+ "\n");
//                    }

            }
            else{

                    int id = (int) dbh.addUser(newUser);

                    if (id == -1) {
                        Toast.makeText(this, "Please check information", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "Information saved.", Toast.LENGTH_LONG).show();

                        //start next activity
                        Intent options = new Intent(ProfileActivity.this, Buttons.class);

                        //storage id in the shared preferences
                      SharedPreferences.Editor editor = sp.edit();
                      editor.putInt("userId", id);
                        editor.putString("phone", phoneNum.getText().toString());
                        editor.putString("address", address.getText().toString());
                        editor.commit();
                        this.finish();
                        startActivity(options);
                    }


            }

            //update query
//            if(isEdit){
//                // newUser.setUserId(sp.getInt("userId", 0));
//                dbh.updateUser(newUser);
//                Toast.makeText(this, "Information saved.", Toast.LENGTH_LONG).show(); ;
//                //start next activity
//                Intent options = new Intent(ProfileActivity.this, Options.class);
//                this.finish();
//                startActivity(options);
//            }else {
//                if (!emailExists) {
//                    int id = (int) dbh.addUser(newUser);
//
//                    if (id == -1) {
//                        Toast.makeText(this, "Please check information", Toast.LENGTH_LONG).show();
//                    } else {
//                        Toast.makeText(this, "Information saved.", Toast.LENGTH_LONG).show();
//
//                        //start next activity
//                        Intent options = new Intent(ProfileActivity.this, Options.class);
//
//                        //storage id in the shared preferences
//                        SharedPreferences.Editor editor = sp.edit();
//                        editor.putInt("userId", id);
//                        editor.putString("phone", phoneNum.toString());
//                        editor.putString("address", address.toString());
//                        editor.commit();
//                        this.finish();
//                        startActivity(options);
//                    }
//                }else {
//                    Toast.makeText(this, "E-mail already exists in the database.", Toast.LENGTH_LONG).show();
//                }
//            }
        });

//        save.setOnClickListener((View v) -> {
//
//            EditText email = findViewById(R.id.email);
//            EditText password = findViewById(R.id.password);
//            DatabaseHelper databseHelper = new DatabaseHelper(this);
//            User user = new User();
//            user.setEmail(email.getText().toString());
//            user.setPassword(idPass);
//            int id = databseHelper.checkUser(user);
//
//
//            startActivity(new Intent(ProfileActivity.this, MainActivity.class)
//            );
//
//        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(Status status) {
                                if (status.isSuccess()){
                                    gotoMainActivity();
                                }else{
                                    Toast.makeText(getApplicationContext(),"Session not close", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
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
            String name =account.getDisplayName();
            String email =account.getEmail();
            emailll=email;

            System.out.println(name+"  "+email);

            userName.setText(name);
            userEmail.setText(email);
            idPass = account.getId().toString();
           // userId.setText(idPass);
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
}
