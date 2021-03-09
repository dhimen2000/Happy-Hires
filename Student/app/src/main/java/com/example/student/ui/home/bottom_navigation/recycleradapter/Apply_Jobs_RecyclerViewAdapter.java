package com.example.student.ui.home.bottom_navigation.recycleradapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.student.R;
import com.example.student.ui.home.bottom_navigation.recyclermodel.Apply_Jobs_RecyclerViewModel;

public class Apply_Jobs_RecyclerViewAdapter extends RecyclerView.Adapter<Apply_Jobs_RecyclerViewAdapter.ViewHolder> {


    Apply_Jobs_RecyclerViewModel [] companydata;

    public Apply_Jobs_RecyclerViewAdapter(Apply_Jobs_RecyclerViewModel[] companydata) {
        this.companydata = companydata;
    }


    @NonNull
    @Override
    public Apply_Jobs_RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.applyjobs_recyclerview, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Apply_Jobs_RecyclerViewAdapter.ViewHolder holder, int position) {
        final Apply_Jobs_RecyclerViewModel applyJobsRecyclerViewModel = companydata[position];
        holder.textView1.setText(companydata[position].getCompanyName());
        holder.textView2.setText(companydata[position].getCompanyEmail());
    }

    @Override
    public int getItemCount() {
        return companydata.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView1;
        public TextView textView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView1 = (TextView) itemView.findViewById(R.id.tvCompanyname);
            this.textView2 = (TextView) itemView.findViewById(R.id.tvCompanyemail);
        }
    }
}
