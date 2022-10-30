package com.fernando.app193.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fernando.app193.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    Button register;
    TextView login;
    EditText textoCorreo, textoPassword;
    FirebaseAuth mAuth;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        login = findViewById(R.id.cuentaExiste);
        register = findViewById(R.id.registerbtn);
        textoCorreo = findViewById(R.id.username2);
        textoPassword = findViewById(R.id.password2);
        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {metodoExplicito(); }
        });

        register.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               metodoRegistrar();
           }
       });
    }

    public void metodoRegistrar(){
        String email = textoCorreo.getText().toString();
        String password = textoPassword.getText().toString();

        if(textoCorreo.getText().toString().isEmpty()) {
            Toast.makeText(this, "No puedes dejar campos vacios", Toast.LENGTH_SHORT).show();

        }if(textoPassword.getText().toString().isEmpty()) {
            Toast.makeText(this, "No puedes dejar campos vacios", Toast.LENGTH_SHORT).show();
        }else{

            mAuth.createUserWithEmailAndPassword(email, password)
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
                            Toast.makeText(Register.this, "" +e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void updateUI(FirebaseUser user) {
        if(user != null){
            Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Error al registrar", Toast.LENGTH_SHORT).show();
        }
    }


    private void metodoExplicito(){
        Intent intent = new Intent(Register.this, Login.class);
        startActivity(intent);
        finish();
    }
}