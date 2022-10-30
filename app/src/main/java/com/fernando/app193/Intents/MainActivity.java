package com.fernando.app193.Intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fernando.app193.R;

public class MainActivity extends AppCompatActivity {


    Button explicito, implicito;
    EditText textoNombre, textoEdad, textoMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        explicito = findViewById(R.id.btnIE);
        implicito = findViewById(R.id.btnII);
        textoNombre = findViewById(R.id.txtNombre);
        textoEdad = findViewById(R.id.txtEdad);
        textoMensaje = findViewById(R.id.txtMensaje);


        explicito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metodoExplicito();
            }
        });

        implicito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metodoImplicito();
            }
        });

//        Log.d("cicloDeVida","OnCreate()");
    }

    private void metodoExplicito(){
//        Intent intent = new Intent(MainActivity.this, SignUp.class);
//        intent.putExtra("nombreDeUsuario", textoNombre.getText().toString());
//        intent.putExtra("edadDeUsuario", Integer.parseInt(textoEdad.getText().toString()));
//
//        startActivity(intent);
//        finish();
//
//        Intent explicito con paquete


        // Zoom: 0-23 geo:46.414382,10.013988?z=15
        // Busqueda: geo:0,0?q=mykey
        // Navegacion: google.navigation:q=Bosque+De+La+Ciudad
        // Mode: d,l,b,w google.navigation:q=Bosque+De+La+Ciudad&mode=w
        // StreetView: google.streetview:cbll=46.414382,10.013988

        String mensaje = textoMensaje.getText().toString();
        Uri uri = Uri.parse("geo:0,0?q=" + mensaje);

        Intent intent2 = new Intent();
        intent2.setAction(Intent.ACTION_VIEW);
        intent2.setData(uri);
        intent2.setPackage("com.google.android.apps.maps");
        startActivity(intent2);


    }

    private void metodoImplicito(){
        String mensaje = textoMensaje.getText().toString();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, mensaje);
        intent.setType("text/plain");
        startActivity(intent);
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        Log.d("cicloDeVida","onStart()");
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Log.d("cicloDeVida","onResume()");
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        Log.d("cicloDeVida","onPause()");
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        Log.d("cicloDeVida","onStop()");
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Log.d("cicloDeVida","onDestroy()");
//    }
}