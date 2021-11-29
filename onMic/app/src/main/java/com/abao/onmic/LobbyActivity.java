package com.abao.onmic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LobbyActivity extends AppCompatActivity {

    private EditText email, password;
    private Button login;
    private TextView signup;
    private ProgressDialog dialog;
     FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        auth = FirebaseAuth.getInstance();
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        login = findViewById(R.id.btnLogin);
        signup = findViewById(R.id.tvSignup);
        dialog = new ProgressDialog(this);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LobbyActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });

    }

    private void Login() {
        String EMAIL = email.getText().toString();
        String PASSWORD = password.getText().toString();

        if (TextUtils.isEmpty(EMAIL)) {
            email.setError("Enter your Email");
            return;
        }
        else if (TextUtils.isEmpty(PASSWORD)) {
            password.setError("Enter your Password");
            return;
        }

        dialog.setMessage("Loading...");
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        auth.signInWithEmailAndPassword(EMAIL, PASSWORD).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(LobbyActivity.this, "Login Successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LobbyActivity.this,ContentActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LobbyActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                }
                dialog.dismiss();
            }
        });
    }
}