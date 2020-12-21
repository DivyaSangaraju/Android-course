package com.example.androidcourse;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapterclass extends RecyclerView.Adapter<Adapterclass.Myviewholder> {
    List<Employee> EmployeeList;

    Context context;


    public Adapterclass(List<Employee> emplist,Context context){
        this.EmployeeList = emplist;
        this.context = context;

    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_data, parent, false);

        Myviewholder myviewholder = new Myviewholder(view);
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position) {
        final Employee employee = EmployeeList.get(position);

        holder.id.setText(employee.getEmpid());
        holder.name.setText(employee.getEmpname());
    }

    @Override
    public int getItemCount() {
        return EmployeeList.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder{

        TextView id,name;
        Bundle bundle;

        public Myviewholder(@NonNull View itemView) {
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
