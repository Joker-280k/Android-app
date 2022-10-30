package com.fernando.app193.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fernando.app193.BottomNavigation.Home;
import com.fernando.app193.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    //MediaPlayer reproductor;
    Button login;
    TextView register;
    TextView updatePassword;
    EditText textoCorreo, textoPassword;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.loginbtn);
        register = findViewById(R.id.cuentaNoExiste);
        updatePassword = findViewById(R.id.updatePassword);
        textoCorreo = findViewById(R.id.textoCorreo);
        textoPassword = findViewById(R.id.textoPassword);
        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {metodoLogin(); }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metodoExplicito();
            }
        });

        updatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metodoExplicito2();
            }
        });

//        reproductor = MediaPlayer.create(this, R.raw.cancion);
//        reproductor.setLooping(true);
//        reproductor.start();

    }

    public void metodoLogin(){
        String email = textoCorreo.getText().toString();
        String password = textoPassword.getText().toString();

        if(email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "No puedes dejar campos vacios", Toast.LENGTH_SHORT).show();

        }else{

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                updateUI(null);
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Login.this, "" +e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void updateUI(FirebaseUser user) {
        if(user != null){
            goToHome();
            Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Error al iniciar sesion", Toast.LENGTH_SHORT).show();
        }

    }


    private void metodoExplicito(){
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
        finish();
    }

    private void metodoExplicito2(){
        Intent intent = new Intent(Login.this, UpdatePassword.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            goToHome();
        }
    }

    private void goToHome() {
        startActivity(new Intent(Login.this, Home.class));
        finish();
    }
}