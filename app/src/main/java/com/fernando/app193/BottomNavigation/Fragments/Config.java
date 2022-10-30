package com.fernando.app193.BottomNavigation.Fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fernando.app193.Authentication.Login;
import com.fernando.app193.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Config extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_config, container, false);

        Button deleteAccount = view.findViewById(R.id.btnDeleteAccount);
        Button changePassword = view.findViewById(R.id.btnChangePassword2);

        changePassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                getAlertDialog(1);
            }
        });

        deleteAccount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getAlertDialog(2);
            }
        });

        return view;
    }

    private void getAlertDialog(int option){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_authentication, null);

        EditText email = dialogView.findViewById(R.id.dialogEmail);
        EditText password = dialogView.findViewById(R.id.dialogPassword);
        EditText reauthenticate = dialogView.findViewById(R.id.btnReauthenticate);
        EditText newpassword = dialogView.findViewById(R.id.dialogNewPassword);

        if(option == 1){
            newpassword.setVisibility(View.VISIBLE);
        }else{
        }

        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        reauthenticate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                AuthCredential credential = EmailAuthProvider
                        .getCredential(email.getText().toString(), password.getText().toString());

                if (user != null)
                    user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                getOption(option, alertDialog, newpassword.getText().toString());
                            }else{
                                alertDialog.dismiss();
                                Toast.makeText(getContext(), "Error al autenticar", Toast.LENGTH_SHORT).show();
                            }
                    }

                        private void getOption(int option, AlertDialog alertDialog, String newPassword) {
                            if (option == 1){
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                user.updatePassword(newPassword)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    alertDialog.dismiss();
                                                    Toast.makeText(getContext(), "Contraseña Actualizada", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            }else{
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                user.delete()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    alertDialog.dismiss();
                                                    Toast.makeText(getContext(), "Usuario Eliminado", Toast.LENGTH_SHORT).show();
                                                    FirebaseAuth.getInstance().signOut();
                                                    startActivity(new Intent(getContext(), Login.class));
                                                    getActivity().finish();
                                                } else {
                                                    alertDialog.dismiss();
                                                    Toast.makeText(getContext(), "Error al autenticar", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            }
                        }
                    });

            }
        });
    }
}