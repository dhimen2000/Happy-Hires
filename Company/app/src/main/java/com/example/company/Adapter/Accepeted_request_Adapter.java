package com.example.company.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.company.Activity.Interview;
import com.example.company.Activity.Jobreqdata;
import com.example.company.Activity.Student_data_cardviewActivity;
import com.example.company.Model.Schedule_Appoinment_Model;
import com.example.company.R;
import com.example.company.Model.Accepted_req_model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Accepeted_request_Adapter extends RecyclerView.Adapter<Accepeted_request_Adapter.ViewHolder> {
    DatabaseReference databaseReference;
    SharedPreferences sharedpreferences;
    String applyid;
    Context context;
    List<Schedule_Appoinment_Model>acceptedReqModels;

    public  Accepeted_request_Adapter(Context Context, List<Schedule_Appoinment_Model> acceptedReqModels){
        this.context = context;
        this.acceptedReqModels=acceptedReqModels;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.accepted_req, parent, false);

        Accepeted_request_Adapter.ViewHolder viewHolder = new Accepeted_request_Adapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Schedule_Appoinment_Model ld=acceptedReqModels.get(position);


        holder.name.setText(ld.getStudentemail());
        holder.jobtitle.setText(ld.getJobtitle());




        holder.linearLayout.setOnClickListener(new View.OnClickListener() {

    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(), "Recycle Click" + position, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(v.getContext(), Interview.class);
        intent.putExtra("Student Email",ld.getStudentemail());
        intent.putExtra("applyid",ld.getKey());
        v.getContext().startActivity(intent);
    }
});

    }

    @Override
    public int getItemCount() {
        return acceptedReqModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView jobtitle;
        ImageView imageView;
        EditText id;
        LinearLayout linearLayout;

        //public ImageView ImageUrl;
        //LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

           this.name= itemView.findViewById(R.id.appointed_student_name);
           this.jobtitle=itemView.findViewById(R.id.appointed_student_jobtitle);
          this.linearLayout=itemView.findViewById(R.id.linearlayoutappoint);
           //this.linearLayout=itemView.findViewById(R.id.acceptedreq);
            //this.ImageUrl=itemView.findViewById(R.id.std_imageurl);
        }
    }
}
