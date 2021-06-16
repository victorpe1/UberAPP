package com.example.uberapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void bChofer(View view){
        Intent siguiente = new Intent( this,ChoferLoginActivity.class);
        startActivity(siguiente);
    }

    public void bCliente(View view){
        Intent siguiente = new Intent( this,ClienteLoginActivity.class);
        startActivity(siguiente);
    }


}