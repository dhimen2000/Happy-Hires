package com.example.student.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.student.Activity.InterviewdataActivity;
import com.example.student.Model.Apply_Jobs_Model;
import com.example.student.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Application_Jobs_Adapter extends RecyclerView.Adapter<Application_Jobs_Adapter.ViewHolder> {

    DatabaseReference firebaseDatabase;
    String applyid;

    List<Apply_Jobs_Model> ApplicationList;

    public Application_Jobs_Adapter(List<Apply_Jobs_Model> applicationList) {
        this.ApplicationList = applicationList;
    }

    @NonNull
    @Override
    public Application_Jobs_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.applyjobs_recyclerview, parent, false);
        Application_Jobs_Adapter.ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Application_Jobs_Adapter.ViewHolder holder, int position) {
        Apply_Jobs_Model model = ApplicationList.get(position);
        holder.Applyjobname.setText(model.getJobTitle());
        holder.Applycompanyname.setText(model.getCompanyName());
        holder.Applyjobstatus.setText(model.getStatus());
        holder.Applyid.setText(model.getApplyid());

        applyid = holder.Applyid.getText().toString();

        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query query1= firebaseDatabase.child("ApplicationForJob").orderByChild("applyid").equalTo(applyid);
                query1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                            appleSnapshot.getRef().removeValue();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        if(model.getStatus().equals("Accepted"))
        {
            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), InterviewdataActivity.class);
                    intent.putExtra("Job Title For Interview",model.getJobTitle());
                    v.getContext().startActivity(intent);
                }
            });
        }
        else if(model.getStatus().equals("Rejected"))
        {
            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Sorry You are not selected..", Toast.LENGTH_LONG).show();
                }
            });
        }
        else
        {
            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Your Request is On Hold..", Toast.LENGTH_LONG).show();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return ApplicationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Applyjobname;
        TextView Applycompanyname;
        TextView Applyjobstatus;
        TextView Applyjobstudentname;
        TextView Applyid;
        CircleImageView image;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.Applyjobname = (TextView) itemView.findViewById(R.id.Application_job_name);
            this.Applycompanyname = (TextView) itemView.findViewById(R.id.Application_company_name);
            this.Applyjobstatus = (TextView) itemView.findViewById(R.id.Application_job_status);
            this.Applyjobstudentname = (TextView) itemView.findViewById(R.id.Applicationjobstudentname);
            this.Applyid = (TextView) itemView.findViewById(R.id.Applicationid);
            this.image = (CircleImageView) itemView.findViewById(R.id.delete);
            this.linearLayout = itemView.findViewById(R.id.adapterlinear);
        }
    }
}
