package com.example.student.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.student.Activity.JobdataActivity;
import com.example.student.R;
import com.example.student.Model.View_Jobs_Model;

import java.util.List;

public class View_Jobs_Adapter extends RecyclerView.Adapter<View_Jobs_Adapter.ViewHolder> {



    List<View_Jobs_Model> JobList;

    public View_Jobs_Adapter(List<View_Jobs_Model> jobList) {
        this.JobList = jobList;
    }

    @NonNull
    @Override
    public View_Jobs_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.viewjobs_recyclerview, parent, false);
        View_Jobs_Adapter.ViewHolder viewHolder = new View_Jobs_Adapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull View_Jobs_Adapter.ViewHolder holder, int position) {
       View_Jobs_Model model = JobList.get(position);
       holder.JobName.setText(model.getJobtitle());
       holder.JobComapnyName.setText(model.getCompanyname());

       holder.jobcardView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Toast.makeText(v.getContext(), "Click" + position, Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(v.getContext(), JobdataActivity.class);
               intent.putExtra("Job_Company_Name",model.getCompanyname());
               intent.putExtra("Job_Company_Email",model.getCompanyemail());
               intent.putExtra("Job_Title",model.getJobtitle());
               intent.putExtra("Job_Description",model.getRequirements());
               intent.putExtra("Job_Percentagecriteria",model.getPercentagecriteria());
               intent.putExtra("Job_Salaryrange",model.getSalaryrange());
               intent.putExtra("Job_Lastdate",model.getJoblastdate());
               intent.putExtra("Job_Pdf",model.getJobattachmenturl());
               intent.putExtra("jobid",model.getJobid());
               v.getContext().startActivity(intent);
           }
       });
    }

    @Override
    public int getItemCount() {
        return JobList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView jobcardView;
        TextView JobName;
        TextView JobComapnyName;
        TextView JobComapnyEmail;
        TextView JobDescription;
        TextView JobPercentagecriteria;
        TextView JobSalaryrange;
        TextView JobLastdate;
        TextView JobPdf;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.jobcardView = (CardView) itemView.findViewById(R.id.job_cv);
            this.JobName = (TextView) itemView.findViewById(R.id.job_name);
            this.JobComapnyName = (TextView) itemView.findViewById(R.id.job_company_name);
            this.JobComapnyEmail = (TextView) itemView.findViewById(R.id.job_company_email);
            this.JobDescription = (TextView) itemView.findViewById(R.id.job_Company_description);
            this.JobPercentagecriteria = (TextView) itemView.findViewById(R.id.job_company_percentagecriteria);
            this.JobSalaryrange = (TextView) itemView.findViewById(R.id.job_Company_salaryrange);
            this.JobLastdate = (TextView) itemView.findViewById(R.id.job_company_lastdate);
            this.JobPdf = (TextView) itemView.findViewById(R.id.job_company_pdf);
        }
    }
}
