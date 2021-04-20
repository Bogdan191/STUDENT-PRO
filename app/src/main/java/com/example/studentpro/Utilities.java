package com.example.studentpro;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.example.studentpro.RegisterActivity;

/*Aceasta este o clasa care contine mai multe functii ajutatoare, utilitare
pentru implementarea anumitor proprietati comune

 */
public class Utilities {

    public Utilities() {

    }

    //This method creates an alert box for the user.
    public void AlertMessage(Context apl, String Title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(apl);
        builder.setMessage(Message);
        builder.setCancelable(true);

        builder.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();

    }
}
