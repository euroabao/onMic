package com.abao.onmic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserInfo extends AppCompatActivity {
    
    private TextView shop, showname, showaddress,showphonenumber, showemail, showpassword, showorderstatus;
    private Button logout;
     FirebaseAuth auth;
     FirebaseUser fbuser;
     DatabaseReference reference;

    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        shop = findViewById(R.id.tvShop);
        logout = findViewById(R.id.btnLogout);

        auth = FirebaseAuth.getInstance();
        fbuser = auth.getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = fbuser.getUid();

        showname = findViewById(R.id.tvName);
        showaddress = findViewById(R.id.tvAddress);
        showphonenumber = findViewById(R.id.tvPhoneNumber);
        showemail = findViewById(R.id.tvEmail);
        showpassword = findViewById(R.id.tvPassword);
        showorderstatus = findViewById(R.id.tvItemOrdered);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);

                if (user != null) {
                    String name, address, phonenumber, email, password;
                    name = user.name;
                    address = user.address;
                    phonenumber = user.phonenumber;
                    email = user.email;
                    password = user.password;

                    showname.setText(name);
                    showaddress.setText(address);
                    showphonenumber.setText(phonenumber);
                    showemail.setText(email);
                    showpassword.setText(password);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserInfo.this, "There is an Error", Toast.LENGTH_LONG).show();
            }
        });

        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(UserInfo.this, ContentActivity.class);
               startActivity(intent);
               overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Toast.makeText(UserInfo.this, "Logged Out", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UserInfo.this, LobbyActivity.class);
                startActivity(intent);
            }

        });
    }
}