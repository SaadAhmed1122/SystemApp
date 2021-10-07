package com.mvvm.systemapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class LoginAct extends AppCompatActivity {

    private TextInputEditText SignInMail, SignInPass;
    private FirebaseAuth auth;
    private Button SignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        // set the view now
        checkuser();

        SignInMail =  findViewById(R.id.edit_textemail);
        SignInPass =  findViewById(R.id.edit_textpass);
        SignInButton =  findViewById(R.id.submit_button);
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = SignInMail.getText().toString();
                final String password = SignInPass.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter your mail address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter your password", Toast.LENGTH_SHORT).show();
                    return;
                }

                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginAct.this,
                                new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 8) {
                                        Toast.makeText(getApplicationContext(), "Password must be more than 8 digit", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Invalid Email and Password", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Intent intent = new Intent(LoginAct.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });

            }

    });
    }

    void checkuser(){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.addAuthStateListener(
                new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                        if (firebaseUser != null) {
                            startActivity(new Intent(LoginAct.this,MainActivity.class));
                            finish();
                        }

    }
                });
    }


    public void openregisteract(View view) {
        startActivity(new Intent(LoginAct.this,RegisterAct.class));
        finish();
    }
}