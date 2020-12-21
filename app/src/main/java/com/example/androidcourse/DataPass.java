package com.example.androidcourse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DataPass extends AppCompatActivity {
    Bundle bb;
    String id,name;
    TextView eid,ename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pass);

        bb = getIntent().getExtras();

         id = bb.getString("empid");
         name = bb.getString("empname");

         eid = findViewById(R.id.empid);
         ename = findViewById(R.id.empname);

         eid.setText(id);
        ename.setText(name);
    }
}