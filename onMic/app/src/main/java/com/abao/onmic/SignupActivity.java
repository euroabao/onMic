package com.abao.onmic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignupActivity extends AppCompatActivity {

    private EditText name, address, phonenumber, email, password;
    private Button signup;
    private TextView login;
     FirebaseAuth auth;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();
        name = findViewById(R.id.etName);
        address = findViewById(R.id.etAddress);
        phonenumber = findViewById(R.id.etPhoneNumber);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        signup = findViewById(R.id.btnSignup);
        login = findViewById(R.id.tvLogin);
        dialog = new ProgressDialog(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Signup();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this,LobbyActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void Signup() {

        String NAME = name.getText().toString();
        String ADDRESS = address.getText().toString();
        String PHONENUMBER = phonenumber.getText().toString();
        String EMAIL = email.getText().toString();
        String PASSWORD = password.getText().toString();

        if (TextUtils.isEmpty(EMAIL)) {
            email.setError("Enter your Email");
            return;
        }
        else if (!ValidationEmail(EMAIL)) {
            email.setError("Invalid Email");
        }
        else if (TextUtils.isEmpty(ADDRESS)) {
            password.setError("Enter your Address");
            return;
        }
        else if (TextUtils.isEmpty(PHONENUMBER)) {
            phonenumber.setError("Enter your Phone Number");
            return;
        }
        else if (PASSWORD.length() < 5) {
            password.setError("Password is too short");
        }
        dialog.setMessage("Loading...");
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);

        auth.createUserWithEmailAndPassword(EMAIL, PASSWORD).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    User user = new User(NAME, ADDRESS, PHONENUMBER, EMAIL, PASSWORD);

                    FirebaseDatabase.getInstance().getReference("User")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(SignupActivity.this, "Credentials Restored Successfully", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(SignupActivity.this, "Restoration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                    Toast.makeText(SignupActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignupActivity.this,ContentActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(SignupActivity.this, "Register Failed", Toast.LENGTH_LONG).show();
                }
                dialog.dismiss();
            }
        });

    }

    private Boolean ValidationEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

}