package com.example.studentpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;


public class LoginActivity  extends AppCompatActivity{

    private Button buttonRegister;
    private Button buttonLogin;
    private CheckBox checkRememberMe;
    private EditText emailText;
    private EditText passwordText;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_activity);

        try {
            this.getSupportActionBar().hide();

        } catch (NullPointerException e) {
        }

        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        checkRememberMe = findViewById(R.id.checkBoxRememberMe);
        emailText = findViewById(R.id.editTextEmailLogin);
        passwordText = findViewById(R.id.editTextPasswordLogin);





        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openR();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { Login(); }
        });


        // Get instance of the Firebase
        mAuth = FirebaseAuth.getInstance();




    }

    public void openR () {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }

    public void Login() {

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        Utilities alertMessage = new Utilities();
        if(email.length() == 0 || password.length() == 0){
            alertMessage.AlertMessage(LoginActivity.this, "Login", "Completati amblele campuri!");
        }
        else {
            //autentificare user

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "Autentificare cu succes!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(LoginActivity.this, HomePageActivity.class));
                    }else {
                        alertMessage.AlertMessage(LoginActivity.this, "Login", "Autentificare esuata! " +
                                "Email-ul si/sau parola incorecte. Apasati ok pentru a reincerca");
                    }
                }
            });
        }



    }

    @Override
    protected void onStart() {
        super.onStart();

        //Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            Intent intent = new Intent(this, HomePageActivity.class);
            startActivity(intent);
        }
    }



}


