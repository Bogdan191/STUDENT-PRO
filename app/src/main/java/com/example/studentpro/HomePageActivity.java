package com.example.studentpro;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class HomePageActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    Button mButtonLogOut;
    Button mButtonMyPage;
    Button mButtonMyClass;
    Button mButtonMoreOptions;
    Button mButtonDBProbe;

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
        mButtonMyPage = findViewById(R.id.buttonMyPage);
        mButtonMyClass = findViewById(R.id.buttonMyClass);
        mButtonMoreOptions = findViewById(R.id.buttonMoreOptionsHomePage);

        mButtonDBProbe = findViewById(R.id.buttonDatabaseProbe);

        //obtinem instanta pentru baza de date de autentificare
        firebaseAuth = FirebaseAuth.getInstance();



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

        mButtonDBProbe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomePageActivity.this, "Se incearca sa se scrie in DB!", Toast.LENGTH_LONG).show();
                // Scrie un mesaj in baza de date

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                if(db == null) {
                    Toast.makeText(HomePageActivity.this, "Nu s-a gasit instanta bazei de date!", Toast.LENGTH_LONG).show();

                }else {
                    Toast.makeText(HomePageActivity.this, "S-a gasit instanta bazei de date!", Toast.LENGTH_LONG).show();

                }
                Map<String, Object> user = new HashMap<>();
                user.put("Nume si prenume", "Pintilie Bogdan-Ioan");
                user.put("email", "bogdanpinitlie00@yahoo.com");
                user.put("grupa", 342);

                //Adauga noul document la baza de date cu un ID generat
                db.collection("users")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("users", "DocumentSnapshot added with ID: " + documentReference.getId());

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("users", "Error adding document", e);
                            }
                        });


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
