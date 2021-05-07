package com.example.company.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.company.Model.Job_Model;
import com.example.company.R;
import com.example.company.Activity.Add_jobs_datacardview;

import java.util.List;


public class Job_Adapter extends RecyclerView.Adapter<Job_Adapter.ViewHolder>{

    List<Job_Model> joblist;

    public Job_Adapter(List<Job_Model> joblist) {
        this.joblist = joblist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.add_jobs_recycler, parent, false);
        Job_Adapter.ViewHolder viewHolder = new Job_Adapter.ViewHolder(listItem);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Job_Model ld = joblist.get(position);
        holder.companyname.setText(ld.getCompanyname());
        holder.jobtitle.setText(ld.getJobtitle());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), Add_jobs_datacardview.class);
                intent.putExtra("jid",ld.getJobid());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return joblist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView companyname;
        private TextView jobtitle;
        private LinearLayout linearLayout;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            this.companyname =(TextView) itemView.findViewById(R.id.job_company_name);
            this.jobtitle =(TextView) itemView.findViewById(R.id.job_title);
            this.linearLayout=(LinearLayout)itemView.findViewById(R.id.showjobdata);


        }
    }
}
