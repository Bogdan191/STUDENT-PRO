package com.example.studentpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomePageActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    Button mButtonLogOut;
    Button mButtonMyPage;
    Button mButtonMyClass;
    Button mButtonMoreOptions;
    FirebaseAuth firebaseAuth;
    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        setContentView(R.layout.homepage_activity);
        try {

            this.getSupportActionBar().hide();

        }catch(NullPointerException e) {

        }
        //setam butoanele
        mButtonLogOut = findViewById(R.id.buttonLogOutHomePage);
        mButtonMyPage = findViewById(R.id.buttonMyPage);
        mButtonMyClass = findViewById(R.id.buttonMyClass);
        mButtonMoreOptions = findViewById(R.id.buttonMoreOptionsHomePage);
        //obtinem instanta pentru baza de date de autentificare
        firebaseAuth = FirebaseAuth.getInstance();

        mButtonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ///Obtine user-ul curent logat
                FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                if(currentUser != null) { // inseamna ca delogarea are sens
                    firebaseAuth.signOut();
                    startActivity(new Intent(HomePageActivity.this, LoginActivity.class));
                }
            }
        });

        mButtonMyPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///Muta user-ul pe pagina profilului lui
                startActivity(new Intent(HomePageActivity.this, MyPageActivity.class));

            }
        });

        mButtonMyClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///Muta user-ul pe pagina cu date despre grupa lui
                startActivity(new Intent(HomePageActivity.this, MyClassActivity.class));
            }
        });


    }

    public void ShowMoreOptions(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.setOnMenuItemClickListener(this);

        popupMenu.inflate(R.menu.more_options);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.option1:
                Toast.makeText(this, "Ai ales optiunea 1!", Toast.LENGTH_LONG).show();
                return true;
            case R.id.option2:
                Toast.makeText(this, "Ai ales optiunea 2!", Toast.LENGTH_LONG).show();
                return true;
            case R.id.option3:
                Toast.makeText(this, "Ai ales optiunea 3!", Toast.LENGTH_LONG).show();
                return true;
            case R.id.option4:
                Toast.makeText(this, "Ai ales optiunea 4!", Toast.LENGTH_LONG).show();
                return true;
            case R.id.option5:
                ///Obtine user-ul curent logat
                FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                if(currentUser != null) { // inseamna ca delogarea are sens
                    firebaseAuth.signOut();
                    startActivity(new Intent(HomePageActivity.this, LoginActivity.class));
                }
                return true;
            default:
                return false;
        }
    }
}
