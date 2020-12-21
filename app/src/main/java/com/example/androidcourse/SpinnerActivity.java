package com.example.androidcourse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SpinnerActivity extends AppCompatActivity {
    Spinner spinner;
    List<String> idlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        spinner = findViewById(R.id.spinnerid);
        getdata();
    }
    private void getdata(){
        Toast.makeText(this, "method called", Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,"http://jsonplaceholder.typicode.com/users", response -> {
            idlist = new ArrayList<>();

            Log.i("res", response);

            try {
                JSONArray jarr = new JSONArray(response);

                for(int i = 0; i < jarr.length(); i++){


                    JSONObject jobj = jarr.getJSONObject(i);
                    idlist.add(jobj.getString("username"));

                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,idlist);
                spinner.setAdapter(adapter);



            } catch (JSONException e) {
                e.printStackTrace();
            }

        },error -> {

        });
        RequestQueue requestQueue = Volley.newRequestQueue(SpinnerActivity.this);
        requestQueue.add(stringRequest);
    }
}