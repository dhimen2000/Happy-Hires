package com.example.student.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.student.Activity.CompanydataActivity;
import com.example.student.R;
import com.example.student.Model.Company_List_Model;

import java.util.List;

public class Company_List_Adapter extends RecyclerView.Adapter<Company_List_Adapter.ViewHolder> {

    Context context;
    List<Company_List_Model> CompanyList;

    public Company_List_Adapter(Context context, List<Company_List_Model> companyList) {
        this.context = context;
        this.CompanyList = companyList;
    }

    @NonNull
    @Override
    public Company_List_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.companylist_recyclerview, parent, false);
        Company_List_Adapter.ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Company_List_Adapter.ViewHolder holder, int position) {
       Company_List_Model model = CompanyList.get(position);
       Glide.with(context).load(model.getImgurl()).into(holder.CompanyImg);
       holder.ComapnyName.setText(model.getName());
       holder.CompanyWebsite.setText(model.getWebsite());

       holder.CompanycardView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Toast.makeText(v.getContext(), "Click" + position, Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(v.getContext(), CompanydataActivity.class);
               intent.putExtra("Image",model.getImgurl());
               intent.putExtra("Name",model.getName());
               intent.putExtra("Email",model.getEmail());
               intent.putExtra("Number",model.getNumber());
               intent.putExtra("Website",model.getWebsite());
               intent.putExtra("Address",model.getAddress());
               intent.putExtra("Attachment",model.getPdfurl());
               v.getContext().startActivity(intent);
           }
       });

    }

    @Override
    public int getItemCount() {
        return CompanyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView CompanycardView;
        ImageView CompanyImg;
        TextView ComapnyName;
        TextView CompanyEmail;
        TextView CompanyNumber;
        TextView CompanyWebsite;
        TextView CompanyAddress;
        TextView CompanyPdf;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.CompanycardView = (CardView) itemView.findViewById(R.id.company_cv);
            this.CompanyImg = (ImageView) itemView.findViewById(R.id.company_image);
            this.ComapnyName = (TextView) itemView.findViewById(R.id.company_name);
            this.CompanyEmail = (TextView) itemView.findViewById(R.id.company_email);
            this.CompanyNumber = (TextView) itemView.findViewById(R.id.Company_number);
            this.CompanyWebsite = (TextView) itemView.findViewById(R.id.company_website);
            this.CompanyAddress = (TextView) itemView.findViewById(R.id.company_address);
            this.CompanyPdf = (TextView) itemView.findViewById(R.id.company_pdf);
        }
    }
}
