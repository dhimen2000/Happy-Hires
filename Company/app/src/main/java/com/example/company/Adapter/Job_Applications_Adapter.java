package com.example.company.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.company.Activity.Jobreqdata;
import com.example.company.R;
import com.example.company.Model.Job_Applications_Model;

import java.util.List;

public class Job_Applications_Adapter extends RecyclerView.Adapter<Job_Applications_Adapter.ViewHolder>{
    Context context;
    List<Job_Applications_Model> jobApplicationsModels;
    public Job_Applications_Adapter(List<Job_Applications_Model>jobApplicationsModels) {

        this.jobApplicationsModels = jobApplicationsModels;
    }
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.jobsapplied, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Job_Applications_Model ld =jobApplicationsModels.get(position);
        //holder.jusername.setText(ld.getStudentName());
        holder.email.setText(ld.getStudentEmail());
        holder.jobtitle.setText(ld.getJobTitle());
        //holder.status.setText(ld.getStatus());
        //Glide.with(context).load(ld.getImageurl1()).into(holder.ImageUrl);


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Recycle Click" + position, Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(v.getContext(), Jobreqdata.class);
                intent1.putExtra("Student Email",ld.getStudentEmail());
                intent1.putExtra("job",ld.getJobTitle());
                intent1.putExtra("Comapny Email",ld.getCompanyEmail());
                intent1.putExtra("Comapny Name",ld.getCompanyName());
                intent1.putExtra("id",ld.getApplyid());
                intent1.putExtra("Jobid",ld.getJobid());
                v.getContext().startActivity(intent1);
            }
        });
    }

    @Override
    public int getItemCount() {


        return jobApplicationsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //TextView jusername;
        TextView email;
        TextView jobtitle;
       // public ImageView ImageUrl;
        TextView status;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //jusername = itemView.findViewById(R.id.jusername);
            email=itemView.findViewById(R.id.reqemail);
            jobtitle=itemView.findViewById(R.id.reqjobtitle);
            //this.ImageUrl=itemView.findViewById(R.id.std_imageurl1);
            linearLayout=itemView.findViewById(R.id.reqlinearlayout);

        }
    }
}
