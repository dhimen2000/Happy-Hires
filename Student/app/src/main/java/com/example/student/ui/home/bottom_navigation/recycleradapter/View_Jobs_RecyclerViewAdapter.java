package com.example.student.ui.home.bottom_navigation.recycleradapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.student.R;
import com.example.student.ui.home.bottom_navigation.recyclermodel.Company_List_RecyclerViewModel;
import com.example.student.ui.home.bottom_navigation.recyclermodel.View_Jobs_RecyclerViewModel;

public class View_Jobs_RecyclerViewAdapter extends RecyclerView.Adapter<View_Jobs_RecyclerViewAdapter.ViewHolder> {

    View_Jobs_RecyclerViewModel [] jobsdata;

    public View_Jobs_RecyclerViewAdapter(View_Jobs_RecyclerViewModel[] jobsdata) {
        this.jobsdata = jobsdata;
    }

    @NonNull
    @Override
    public View_Jobs_RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.viewjobs_recyclerview, parent, false);
        View_Jobs_RecyclerViewAdapter.ViewHolder viewHolder = new View_Jobs_RecyclerViewAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull View_Jobs_RecyclerViewAdapter.ViewHolder holder, int position) {
        final View_Jobs_RecyclerViewModel viewJobsRecyclerViewModel = jobsdata[position];
        holder.textView1.setText(jobsdata[position].getJobList());
        holder.textView2.setText(jobsdata[position].getJobs());
    }

    @Override
    public int getItemCount() {
        return jobsdata.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView1;
        public TextView textView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView1 = (TextView) itemView.findViewById(R.id.tvjoblist);
            this.textView2 = (TextView) itemView.findViewById(R.id.tvjobs);
        }
    }
}
