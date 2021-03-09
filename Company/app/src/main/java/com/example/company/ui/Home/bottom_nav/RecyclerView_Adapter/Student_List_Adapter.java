package com.example.company.ui.Home.bottom_nav.RecyclerView_Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.company.R;
import com.example.company.ui.Home.bottom_nav.Recyclerview_Model.Student_List_Model;

public class Student_List_Adapter extends RecyclerView.Adapter<Student_List_Adapter.ViewHolder> {

    public Student_List_Adapter(Student_List_Model[] ld) {
        this.ld = ld;
    }

    Student_List_Model[] ld;

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.student_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       Student_List_Model student_list=ld[position];
        holder.textView1.setText(ld[position].getName());
        holder.textView2.setText(ld[position].getNumber());
        holder.textView3.setText(ld[position].getEmail());


    }

    @Override
    public int getItemCount() {
        return ld.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView textView1;
            public TextView textView2;
            public TextView textView3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView1 =(TextView) itemView.findViewById(R.id.std_name);
            this.textView2 =(TextView) itemView.findViewById(R.id.std_no);
            this.textView3 =(TextView) itemView.findViewById(R.id.std_email);
        }

        }
    }

