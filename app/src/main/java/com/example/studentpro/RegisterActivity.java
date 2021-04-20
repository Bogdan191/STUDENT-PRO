package com.example.studentpro;



import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.installations.Utils;



public class RegisterActivity extends AppCompatActivity {

    private Button loadPicture;
    private Button register;
    private EditText emailText;
    private EditText userNameText;
    private EditText passwordText;
    private EditText confirmPasswordText;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        setContentView(R.layout.register_activity);
        try {
            this.getSupportActionBar().hide();

        } catch (NullPointerException e) {
        }

        loadPicture = (Button) findViewById(R.id.buttonLoadPicture);
        register = (Button) findViewById(R.id.buttonRegisterNow);
        emailText = findViewById(R.id.editTextEmail);
        userNameText = findViewById(R.id.editTextUsername);
        passwordText = findViewById(R.id.editTextPassword);
        confirmPasswordText = findViewById(R.id.editTextConfirmPassword);
        loadPicture.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                LoadPicture();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });


        //Get the instanbce of The FireBaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

    }

    public void LoadPicture() {

    }




    public void Register() {


        String email = emailText.getText().toString();
        String userName = userNameText.getText().toString();
        String password = passwordText.getText().toString();
        String confirmPassword = confirmPasswordText.getText().toString();

        boolean emailCorect = false;
        boolean parolaCorect = false;
        boolean userCorect = false;
        Utilities u = new Utilities();
        //Email-ul introdus trebuie sa contina caracterul special '@' si sa aiba lungimea minima de 10 caractere.
        if(email.length() < 10 || email.contains("@") == false) {
            u.AlertMessage(this, "Email Incorect!", "Email-ul nu are lungimea minima (10) sau nu contine caracterul " +
                    "@!");

        } else {
            emailCorect = true;
        }

        // de revenit aici si de testat username-ul daca exista deja in baza de date!
        if(userName.length() < 5) {
            u.AlertMessage(this, "Username incorect!", "Username-ul este incorect sau exista deja in baza de date!");
        }else {
            userCorect = true;
        }
        // Verifica parola care trebuie sa respecte o anumita forma
        // dupa regex-ul dat
        //Parola trebuie sa aiba minim 8 caractere, cel putin o cifra, cel putin o litera mica si una mare
        //cel putin un caracter special si sa nu contina space, tab etc
        if(password.matches("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$") == false) {
            u.AlertMessage(this, "Parola incorecta!", "Parola trebuie sa contina cel putin 8 caractere, " +
                    "macar o litera UpperCase, macar una lowerCase, cel putin un caracter special si sa nu aiba spatii sau tab-uri in " +
                    "componenta!");

        } else if(confirmPassword.equals(password) == false) {
            u.AlertMessage(this, "Parole incorecte!", "Parolele nu se potrivesc!");
        } else {
            parolaCorect = true;
        }

        // daca toate campurile sunt corecte, atunci putem aduaga in baza de date
        if(emailCorect && userCorect && parolaCorect) {
            //Toast.makeText(this, "Dada emailuri si registerre", Toast.LENGTH_LONG).show();
            // Inregistrare user in baza de date firebase
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){ // s-a realizat cu succes inregistrarea utilizatorului in baza de date
                        Toast.makeText(RegisterActivity.this, "Utilizator inregistrat!", Toast.LENGTH_LONG).show();
                        u.AlertMessage(RegisterActivity.this, "Da", "Utilizator creat!");
                        //ne mutam pe pagina de login
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    }else { // a aparut o eroarea in timpul inregistrarii in baza de date
                        u.AlertMessage(RegisterActivity.this, "Eraore", "Eraore la inregistrarea in baza de date");
                    }
                }
            });
        }


    }

}
