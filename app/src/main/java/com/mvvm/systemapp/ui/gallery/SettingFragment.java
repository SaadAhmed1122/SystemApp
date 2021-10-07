package com.mvvm.systemapp.ui.gallery;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.slider.Slider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.mvvm.systemapp.R;
import com.mvvm.systemapp.databinding.FragmentGalleryBinding;
import com.mvvm.systemapp.modelclass;


import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SettingFragment extends Fragment {
    Button btnupdate;
    String value1,value2,value3,value4,tokenmain;

    FirebaseDatabase mDatabase;
    DatabaseReference mDb;

    String email,time,name,id,userKey;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mDb = mDatabase.getReference();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        userKey = user.getUid();

//        Fatching User Data form storage
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("myfile", Context.MODE_PRIVATE);
        String defaultvalue = "Defaultvalue";
        String usernamejj = sharedPreferences.getString("username",defaultvalue);


//     Fatch email , name , created time of current user
        mDb.child("Users").child(usernamejj).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                email = dataSnapshot.child("email").getValue(String.class);
                name = dataSnapshot.child("name").getValue(String.class);
                time = dataSnapshot.child("created_time").getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

//        Fatching Device token
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
//                Toast.makeText(getContext(), "This is the token : " + task.getResult(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_setting,container,false);

        Slider ss1= view.findViewById(R.id.slider1);
        Slider ss2= view.findViewById(R.id.slider2);
        Slider ss3= view.findViewById(R.id.slider3);
        Slider ss4= view.findViewById(R.id.slider4);
        EditText one = view.findViewById(R.id.edittext1);
        EditText two = view.findViewById(R.id.edittext2);
        EditText three = view.findViewById(R.id.editext3);
        EditText four = view.findViewById(R.id.edittext4);
         btnupdate = view.findViewById(R.id.updatebtn);

        one.setEnabled(false);
        two.setEnabled(false);
        three.setEnabled(false);
        four.setEnabled(false);

        ss1.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {

            }
            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {
                one.setText(String.valueOf(slider.getValue()));
                value1 = String.valueOf(slider.getValue());

//                Log.d("onStartTrackingTouch", String.valueOf(slider.getValue()));
            }
        });


        ss2.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {

            }
            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {
                two.setText(String.valueOf(slider.getValue()));
                value2 = String.valueOf(slider.getValue());

//                Log.d("onStartTrackingTouch", String.valueOf(slider.getValue()));
            }
        });


        ss3.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {

            }
            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {
                three.setText(String.valueOf(slider.getValue()));
                value3 = String.valueOf(slider.getValue());
//                Log.d("onStartTrackingTouch", String.valueOf(slider.getValue()));
            }
        });


        ss4.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {

            }
            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {
                four.setText(String.valueOf(slider.getValue()));
                value4 = String.valueOf(slider.getValue());
//                Log.d("onStartTrackingTouch", String.valueOf(slider.getValue()));
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getContext().getSharedPreferences("myfile", Context.MODE_PRIVATE);
                String defaultvalue = "Defaultvalue";
                String username = sharedPreferences.getString("username",defaultvalue);


                modelclass usersdata = new modelclass(value1,value2,value3,value4,time,"",id,email,name,tokenmain);
//Updata Data to firebase

                DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
                reference.keepSynced(true);
                reference.child("Users").child(username).setValue(usersdata)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                Toast.makeText(getContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(getContext(), "Updating Failed !!!", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

        return view;
    }
}