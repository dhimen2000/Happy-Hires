package com.example.company.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.company.Model.Register_Model;
import com.example.company.R;

import java.util.List;

public class Register_Adapter extends RecyclerView.Adapter<Register_Adapter.ViewHolder> {
    @NonNull

    List<Register_Model> register_modelList;
    public Register_Adapter(List<Register_Model>register_modelList){this.register_modelList=register_modelList;}
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.fragment_profile, parent, false);
        ViewHolder
                viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Register_Model ld=register_modelList.get(position);
//        holder.textView1.setText(ld.getName());
//        holder.textView2.setText(ld.getEmail());
//        holder.textView3.setText(ld.getAddress());
//        holder.textView4.setText(ld.getWebsite());
//        holder.textView5.setText(ld.getNumber());

    }

    @Override
    public int getItemCount() {
        return register_modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1;
        public TextView textView2;
        public TextView textView3;
        public TextView textView4;
        public TextView textView5;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            this.textView1 =(TextView) itemView.findViewById(R.id.profile_company_name);
//            this.textView2 =(TextView) itemView.findViewById(R.id.profile_email);
//            this.textView3 =(TextView) itemView.findViewById(R.id.profile_address);
//            this.textView4 =(TextView) itemView.findViewById(R.id.profile_website);
//            this.textView5 =(TextView) itemView.findViewById(R.id.profile_number);
        }
    }
}
