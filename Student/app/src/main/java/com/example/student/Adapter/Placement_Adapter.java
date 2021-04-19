package com.example.student.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.student.Activity.PlacementattachmentActivity;
import com.example.student.Model.Placement_Model;
import com.example.student.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class Placement_Adapter extends RecyclerView.Adapter<Placement_Adapter.ViewHolder> {

    List<Placement_Model> PlacementList;

    public Placement_Adapter(List<Placement_Model> placementList) {
        PlacementList = placementList;
    }

    @NonNull
    @Override
    public Placement_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.placementdesign, parent, false);
        Placement_Adapter.ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Placement_Adapter.ViewHolder holder, int position) {
        Placement_Model model = PlacementList.get(position);
        holder.placementYear.getEditText().setText(model.getPlacementyear());

        holder.ViewPlacement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PlacementattachmentActivity.class);
                intent.putExtra("Placement_PDF",model.getPlacementpdfurl());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return PlacementList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextInputLayout placementYear;
        TextView placementurl;
        Button ViewPlacement;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.placementYear = (TextInputLayout) itemView.findViewById(R.id.Placement_Year);
            this.placementurl = (TextView) itemView.findViewById(R.id.placementurl);
            this.ViewPlacement = (Button) itemView.findViewById(R.id.Checkplacement);
        }
    }
}
