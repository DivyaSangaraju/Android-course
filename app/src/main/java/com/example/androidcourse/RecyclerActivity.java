package com.example.androidcourse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecyclerActivity extends AppCompatActivity {
    RecyclerView recyclerView;
   // Adapterclass myadapterclass;


    EmployeeAdapter employeeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getdata();
    }
    private void getdata(){
        Toast.makeText(this, "method called", Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,"http://jsonplaceholder.typicode.com/users", response -> {
            //idlist = new ArrayList<>();

            Log.i("res", response);

            try {

                recyclerView.setVisibility(View.VISIBLE);
                //recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setHasFixedSize(true);
                JSONArray jarr = new JSONArray(response);
                List<Employee> list = new ArrayList<>();

                for(int i = 0; i < jarr.length(); i++){

                    Employee adapter = new Employee();


                    JSONObject jobj = jarr.getJSONObject(i);
                   //idlist.add(jobj.getString("username"));
                    adapter.setEmpid(jobj.getString("id"));
                    adapter.setEmpname(jobj.getString("name"));

                    list.add(adapter);


                }


               /* myadapterclass = new Adapterclass(list,getApplicationContext());
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(myadapterclass);
*/


                employeeAdapter = new EmployeeAdapter(list, RecyclerActivity.this);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(employeeAdapter);

            } catch (JSONException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
               // e.printStackTrace();
            }

        },error -> {
            Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();

        });
        RequestQueue requestQueue = Volley.newRequestQueue(RecyclerActivity.this);
        requestQueue.add(stringRequest);
    }



    public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.MyViewHolder> {

        List<Employee> EmployeeList;

        Context context;


        public EmployeeAdapter(List<Employee> productList, Context context) {
            this.EmployeeList = productList;
            this.context = context;

        }


        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            View itemView;

            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_data, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

            final Employee employee = EmployeeList.get(position);

            holder.id.setText(employee.getEmpid());
            holder.name.setText(employee.getEmpname());


        }

        @Override
        public int getItemCount() {

            return EmployeeList.size();
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }


        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView id,name;
            Bundle bundle;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);



                id = itemView.findViewById(R.id.idTv);
                name = itemView.findViewById(R.id.nameTv);
                bundle = new Bundle();

                itemView.setOnClickListener(view -> {

                    String EMPID = EmployeeList.get(getAdapterPosition()).getEmpid();
                    String EMPNAME = EmployeeList.get(getAdapterPosition()).getEmpname();

                    Intent intent = new Intent(context,DataPass.class);
                    bundle.putString("empid", EMPID);
                    bundle.putString("empname", EMPID);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                });

            }
        }
    }
}