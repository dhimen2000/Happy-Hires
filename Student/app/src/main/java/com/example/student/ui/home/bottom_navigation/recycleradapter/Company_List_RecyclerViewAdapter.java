package com.example.student.ui.home.bottom_navigation.recycleradapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.student.R;
import com.example.student.ui.home.bottom_navigation.recyclermodel.Apply_Jobs_RecyclerViewModel;
import com.example.student.ui.home.bottom_navigation.recyclermodel.Company_List_RecyclerViewModel;

public class Company_List_RecyclerViewAdapter extends RecyclerView.Adapter<Company_List_RecyclerViewAdapter.ViewHolder> {

    Company_List_RecyclerViewModel [] companylist;

    public Company_List_RecyclerViewAdapter(Company_List_RecyclerViewModel[] companylist) {
        this.companylist = companylist;
    }

    @NonNull
    @Override
    public Company_List_RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.companylist_recyclerview, parent, false);
        Company_List_RecyclerViewAdapter.ViewHolder viewHolder = new Company_List_RecyclerViewAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Company_List_RecyclerViewAdapter.ViewHolder holder, int position) {
        final Company_List_RecyclerViewModel companyListRecyclerViewModel = companylist[position];
        holder.textView1.setText(companylist[position].getCompanyList());
        holder.textView2.setText(companylist[position].getCompanyNumber());
    }

    @Override
    public int getItemCount() {
        return companylist.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView1;
        public TextView textView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView1 = (TextView) itemView.findViewById(R.id.tvCompanylist);
            this.textView2 = (TextView) itemView.findViewById(R.id.tvCompanynumber);
        }
    }
}
