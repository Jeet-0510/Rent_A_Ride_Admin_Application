package com.example.vehiclerentappadmin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AboutUs extends AppCompatActivity {

    TextView EJeet, EKaran, EYash, PJeet, PKaran, PYash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        EJeet = findViewById(R.id.txtEmailJeet);
        EKaran = findViewById(R.id.txtEmailKaran);
        EYash = findViewById(R.id.txtEmailYash);
        PJeet = findViewById(R.id.txtPhoneJeet);
        PKaran = findViewById(R.id.txtPhoneKaran);
        PYash = findViewById(R.id.txtPhoneYash);

        EJeet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + EJeet.getText().toString()));
                startActivity(intent);
            }
        });

        PJeet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: " + PJeet.getText().toString()));
                startActivity(intent);
            }
        });

        EKaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + EKaran.getText().toString()));
                startActivity(intent);
            }
        });

        PKaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: " + PKaran.getText().toString()));
                startActivity(intent);
            }
        });

        EYash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + EYash.getText().toString()));
                startActivity(intent);
            }
        });

        PYash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: " + PYash.getText().toString()));
                startActivity(intent);
            }
        });
    }
}