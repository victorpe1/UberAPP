package com.example.uberapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ClienteLoginActivity extends AppCompatActivity {

    private EditText email, password;

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_login);

        auth = FirebaseAuth.getInstance();
        firebaseAuthListener = firebaseAuth -> {
            FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();
            if (usuario != null) {
                Intent siguiente = new Intent(ClienteLoginActivity.this, MapaActivity.class);
                startActivity(siguiente);
            }
        };

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

    }


    public void bResgistrar(View view) {
        final String _email = email.getText().toString();
        final String _pass = password.getText().toString();

        auth.createUserWithEmailAndPassword(_email, _pass).addOnCompleteListener(ClienteLoginActivity.this,
                task -> {
            if (!task.isSuccessful()) {
                Toast.makeText(ClienteLoginActivity.this, "Registro ERROR", Toast.LENGTH_SHORT).show();
            } else {
                String user_id = auth.getCurrentUser().getUid();
                DatabaseReference user_db = FirebaseDatabase.getInstance().getReference().child("Usuario").child("Clientes").child(user_id);
                user_db.setValue(true);
            }
        });
    }

    public void bLogin(View view) {
        final String _email = email.getText().toString();
        final String _pass = password.getText().toString();

        auth.signInWithEmailAndPassword(_email, _pass).addOnCompleteListener(ClienteLoginActivity.this,
                task -> {
            if (!task.isSuccessful()) {
                Toast.makeText(ClienteLoginActivity.this, "Inicio sesion ERROR", Toast.LENGTH_SHORT).show();
            } else {
                String user_id = auth.getCurrentUser().getUid();
                DatabaseReference user_db = FirebaseDatabase.getInstance().getReference().child("Usuario").child("Clientes").child(user_id);
                user_db.setValue(true);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        auth.removeAuthStateListener(firebaseAuthListener);
    }
}


