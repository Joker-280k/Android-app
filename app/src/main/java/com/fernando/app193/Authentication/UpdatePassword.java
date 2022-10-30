package com.fernando.app193.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fernando.app193.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class UpdatePassword extends AppCompatActivity {

    Button reestableer;
    EditText correo;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        reestableer = findViewById(R.id.reestablecer);
        correo = findViewById(R.id.correo);
        mAuth = FirebaseAuth.getInstance();

        reestableer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metodoUpdate();
            }
        });

    }

    public void metodoUpdate(){
        String email = correo.getText().toString();

            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(UpdatePassword.this, "Correo enviado", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(UpdatePassword.this, "" +e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
    }
}
