package com.fernando.app193.Intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fernando.app193.R;

public class SignUp extends AppCompatActivity {

    Button explicito2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Intent mensaje = getIntent();

        String nombre = mensaje.getStringExtra("nombreDeUsuario");
        Integer edad = mensaje.getIntExtra("edadDeUsuario", 0);
        String validacion;

        if (edad > 18){
            validacion = "Mayor de edad";
        } else {
            validacion = "Menor de edad";
        }
        Toast.makeText(this,validacion, Toast.LENGTH_SHORT).show();

        explicito2 = findViewById(R.id.btnIE2);


        explicito2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metodoExplicito();
            }
        });
    }

    private void metodoExplicito(){
        Intent intent = new Intent(SignUp.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}