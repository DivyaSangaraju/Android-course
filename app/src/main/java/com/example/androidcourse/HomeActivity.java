package com.example.androidcourse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button spinner,recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        spinner = findViewById(R.id.spnrBtn);
        recyclerview = findViewById(R.id.RecyclerBtn);

       /* spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this,SpinnerActivity.class);
                startActivity(i);

            }
        }*/

        spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this,SpinnerActivity.class);
                startActivity(i);
            }
        });

                recyclerview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    Intent ri = new Intent(HomeActivity.this,RecyclerActivity.class);
                    startActivity(ri);
                    }
                });
    }
}