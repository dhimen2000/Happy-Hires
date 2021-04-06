package com.example.company.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.company.R;
import com.example.company.Model.Accepted_req_model;

import java.util.List;

public class Accepeted_request_Adapter extends RecyclerView.Adapter<Accepeted_request_Adapter.ViewHolder> {


    Context context;
    List<Accepted_req_model>acceptedReqModels;

    public  Accepeted_request_Adapter(Context Context,List<Accepted_req_model>acceptedReqModels){
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
        Accepted_req_model ld=acceptedReqModels.get(position);

        holder.name.setText(ld.getStudentName());
        holder.jobtitle.setText(ld.getJobTitle());

        //Glide.with(context).load(ld.getImageurl1()).into(holder.ImageUrl);



    }

    @Override
    public int getItemCount() {
        return acceptedReqModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView jobtitle;
        //public ImageView ImageUrl;
        //LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           this.name= itemView.findViewById(R.id.appointed_student_name);
           this.jobtitle=itemView.findViewById(R.id.appointed_student_jobtitle);
           //this.linearLayout=itemView.findViewById(R.id.acceptedreq);
            //this.ImageUrl=itemView.findViewById(R.id.std_imageurl);
        }
    }
}
