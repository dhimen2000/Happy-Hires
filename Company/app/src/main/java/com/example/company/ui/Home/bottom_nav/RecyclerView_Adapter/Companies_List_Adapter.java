package com.example.company.ui.Home.bottom_nav.RecyclerView_Adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.company.Company_data_cardviewActivity;
import com.example.company.MainActivity;
import com.example.company.R;
import com.example.company.ui.Home.bottom_nav.Companies_ListFragment;
import com.example.company.ui.Home.bottom_nav.Recyclerview_Model.Companies_list_Model;

import java.util.List;

import static android.content.ContentValues.TAG;

public class Companies_List_Adapter extends RecyclerView.Adapter<Companies_List_Adapter.ViewHolder> {


    List<Companies_list_Model>companiesListModels;
public Companies_List_Adapter(List<Companies_list_Model>companiesListModels){
        this.companiesListModels=companiesListModels;
}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.activity_compant_data, parent, false);
        View listItem1= layoutInflater.inflate(R.layout.activity_company_data_cardview, parent, false);
        Companies_List_Adapter.ViewHolder viewHolder = new Companies_List_Adapter.ViewHolder(listItem);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Companies_list_Model ld=companiesListModels.get(position);
        holder.name.setText(ld.getName());
        holder.textView2.setText(ld.getEmail());
        holder.textView3.setText(ld.getAddress());
        holder.textView4.setText(ld.getCity());
        holder.textView5.setText(ld.getNumber());

       holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Recycle Click" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), Company_data_cardviewActivity.class);
                v.getContext().startActivity(intent);




            }
        });
    }

    @Override
    public int getItemCount() {
        return companiesListModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public TextView textView2;
        public TextView textView3;
        public TextView textView4;
        public TextView textView5;
        public LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.name =(TextView) itemView.findViewById(R.id.profile_company_name);
            this.textView2 =(TextView) itemView.findViewById(R.id.profile_email);
            this.textView3 =(TextView) itemView.findViewById(R.id.profile_address);
            this.textView4 =(TextView) itemView.findViewById(R.id.profile_city);
            this.textView5 =(TextView) itemView.findViewById(R.id.profile_number);
            this.linearLayout= (LinearLayout) itemView.findViewById(R.id.linearlayout1);
        }

        }
}
