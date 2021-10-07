package com.mvvm.systemapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RegisterAct extends AppCompatActivity {
    TextInputEditText SignUpMail,SignUpPass,name;
    Button SignUpButton;
    private FirebaseAuth auth;

    String currentDateandTime,tokenmain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        SignUpMail = findViewById(R.id.edit_textemailsignup);
        name = findViewById(R.id.edit_textname);
        SignUpPass = findViewById(R.id.edit_textpasssingup);
        auth=FirebaseAuth.getInstance();

//        Data and Time
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        currentDateandTime = sdf.format(new Date());
        SignUpButton = (Button) findViewById(R.id.signup_button);

        FirebaseMessaging.getInstance().getToken().addOnSuccessListener(token -> {
//                    tokenmain = token;
        }).addOnFailureListener(e -> {
            //handle e
        }).addOnCanceledListener(() -> {
            //handle cancel
        }).addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                tokenmain = task.getResult();
//                Toast.makeText(RegisterAct.this, "This is the token : " + task.getResult(), Toast.LENGTH_SHORT).show();
            }
        });

        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = SignUpMail.getText().toString();
                String pass = SignUpPass.getText().toString();
                String named= name.getText().toString();

                if(TextUtils.isEmpty(named)){
                    Toast.makeText(getApplicationContext(),"Please enter your Name",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"Please enter your E-mail address",Toast.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(getApplicationContext(),"Please enter your Password",Toast.LENGTH_LONG).show();
                }
                if (pass.length() == 0){
                    Toast.makeText(getApplicationContext(),"Please enter your Password",Toast.LENGTH_LONG).show();
                }
                if (pass.length()<8){
                    Toast.makeText(getApplicationContext(),"Password must be more than 8 digit",Toast.LENGTH_LONG).show();
                }
                else{
                    auth.createUserWithEmailAndPassword(email,pass)
                            .addOnCompleteListener(RegisterAct.this, new OnCompleteListener<AuthResult>() {

                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (!task.isSuccessful()) {
                                        Toast.makeText(RegisterAct.this, "ERROR",Toast.LENGTH_LONG).show();
                                    }
                                    else {
                                        adddatatofirestore(email,named,pass);
                                    }
                                }
                            });}
            }
        });
    }

    private void adddatatofirestore(String email, String named, String pass) {

        String s =email;
        String newS = s.replaceAll("@(.*)", "");
        System.out.println(newS);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Users");
        String userId = mDatabase.push().getKey();
        // adding our data to our users object class.
        modelclass usersdata = new modelclass("","","","",currentDateandTime,"",userId,email,named,tokenmain,"","");

        mDatabase.child(newS).setValue(usersdata).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(RegisterAct.this, "Registration Succesfully", Toast.LENGTH_SHORT).show();
                SharedPreferences sharedPreferences = getSharedPreferences("myfile", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username",newS);
                editor.putString("email",email);
                editor.commit();
                startActivity(new Intent(RegisterAct.this,MainActivity.class));
                finish();
            }

        });

    }

    public void login(View view) {
        startActivity(new Intent(RegisterAct.this,LoginAct.class));
        finish();
    }

}