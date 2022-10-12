package com.example.appnotification;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button b1;
    private EditText username, email, password, password1;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.btn);
        username = findViewById(R.id.editTextTextPersonName);
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        password1 = findViewById(R.id.editTextTextPassword1);
        auth = FirebaseAuth.getInstance();

        b1.setOnClickListener(view -> {
            if (!password.getText().toString().equals(password1.getText().toString())) {
                Toast.makeText(this, "Password do not match", Toast.LENGTH_SHORT).show();
                return;
            }
            signup(email.getText().toString(), password.getText().toString());
        });
        findViewById(R.id.btn2).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
            finish();
        });
    }

    private void signup(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Unable to create account!\nException " + task.getException(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}