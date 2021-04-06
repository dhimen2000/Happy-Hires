package com.example.company.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.company.R;
import com.example.company.Activity.Student_data_cardviewActivity;
import com.example.company.Model.Student_List_Model;

import java.util.ArrayList;
import java.util.List;

public class Student_List_Adapter extends RecyclerView.Adapter<Student_List_Adapter.ViewHolder> implements Filterable {

    Context context;
    List<Student_List_Model> studentListModels;
    List<Student_List_Model> studentListModelsfull;

    public Student_List_Adapter(Context context, List<Student_List_Model> studentListModels) {
        this.context = context;
        this.studentListModels = studentListModels;
        studentListModelsfull = new ArrayList<>(studentListModels);
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.student_list_item, parent, false);

        Student_List_Adapter.ViewHolder viewHolder = new Student_List_Adapter.ViewHolder(listItem);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student_List_Model ld = studentListModels.get(position);
        holder.Email.setText(ld.getEmail());
        holder.Name.setText(ld.getName());
        holder.Dob.setText(ld.getDob());
        holder.Gender.setText(ld.getGender());
        holder.Number.setText(ld.getNumber());
        holder.Address.setText(ld.getAddress());
        Glide.with(context).load(ld.getImageUrl()).into(holder.ImageUrl);
        holder.S_School.setText(ld.getS_School());
        holder.S_Board.setText(ld.getS_Board());
        holder.S_Percentage.setText(ld.getS_Percentage());
        holder.S_Year.setText(ld.getS_Year());
        holder.H_School.setText(ld.getH_School());
        holder.H_Board.setText(ld.getH_Board());
        holder.H_Percentage.setText(ld.getH_Percentage());
        holder.H_Year.setText(ld.getH_Year());
        holder.G_College.setText(ld.getG_College());
        holder.G_Branch.setText(ld.getG_Branch());
        holder.G_Enrollmentno.setText(ld.getG_Enrollmentno());
        holder.G_Sem1.setText(ld.getG_Sem1());
        holder.G_Year1.setText(ld.getG_Year1());
        holder.G_Sem2.setText(ld.getG_Sem2());
        holder.G_Year2.setText(ld.getG_Year2());
        holder.G_Sem3.setText(ld.getG_Sem3());
        holder.G_Year3.setText(ld.getG_Year3());
        holder.G_Sem4.setText(ld.getG_Sem4());
        holder.G_Year4.setText(ld.getG_Year4());
        holder.G_Sem5.setText(ld.getG_Sem5());
        holder.G_Year5.setText(ld.getG_Year5());
        holder.G_Sem6.setText(ld.getG_Sem6());
        holder.G_Year6.setText(ld.getG_Year6());
        holder.G_Sem7.setText(ld.getG_Sem7());
        holder.G_Year7.setText(ld.getG_Year7());
        holder.G_Sem8.setText(ld.getG_Sem8());
        holder.G_Year8.setText(ld.getG_Year8());
        //holder.key.setText(ld.getKey());
        holder.ResumeUrl.setText(ld.getResumeUrl());
        holder.PdfUrl.setText(ld.getPdfUrl());


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Recycle Click" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), Student_data_cardviewActivity.class);
                intent.putExtra("email", ld.getEmail());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentListModels.size();
    }

    public Filter getFilter() {
        return examplefilter;
    }

    private Filter examplefilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<Student_List_Model> filteredlist= new ArrayList<>();
            if(constraint==null || constraint.length()==0){
                filteredlist.addAll(studentListModelsfull);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                 for(Student_List_Model item:studentListModelsfull){
                       if (item.getName().toLowerCase().contains(filterPattern)) {
                          filteredlist.add(item);
                   }
            }
        }
            FilterResults results = new FilterResults();
            results.values = filteredlist;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            studentListModels.clear();
            studentListModels.addAll((ArrayList) results.values);
            notifyDataSetChanged();

        }
    };


    public class ViewHolder extends RecyclerView.ViewHolder {


        public TextView Email;
        //public TextView Pass;
        public TextView Name;
        public TextView Dob;
        public TextView Gender;
        public TextView Number;
        public TextView Address;
        public ImageView ImageUrl;
        public TextView S_School;
        public TextView S_Board;
        public TextView S_Percentage;
        public TextView S_Year;
        public TextView H_School;
        public TextView H_Board;
        public TextView H_Percentage;
        public TextView H_Year;
        public TextView G_College;
        public TextView G_Branch;
        public TextView G_Enrollmentno;
        public TextView G_Sem1;
        public TextView G_Year1;
        public TextView G_Sem2;
        public TextView G_Year2;
        public TextView G_Sem3;
        public TextView G_Year3;
        public TextView G_Sem4;
        public TextView G_Year4;
        public TextView G_Sem5;
        public TextView G_Year5;
        public TextView G_Sem6;
        public TextView G_Year6;
        public TextView G_Sem7;
        public TextView G_Year7;
        public TextView G_Sem8;
        public TextView G_Year8;
        //public TextView Key;
        public TextView ResumeUrl;
        public TextView PdfUrl;
        public LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.Email =(TextView) itemView.findViewById(R.id.std_email);
            this.Name =(TextView) itemView.findViewById(R.id.std_name);
            this.Dob =(TextView) itemView.findViewById(R.id.std_dob);
            this.Gender =(TextView) itemView.findViewById(R.id.std_gender);
            this.Number =(TextView) itemView.findViewById(R.id.std_number);
            this.Address =(TextView) itemView.findViewById(R.id.std_address);
            this.ImageUrl =(ImageView) itemView.findViewById(R.id.std_imageurl);
            this.S_School =(TextView) itemView.findViewById(R.id.std_Secondary_School);
            this.S_Board =(TextView) itemView.findViewById(R.id.std_Secondary_Board);
            this.S_Percentage =(TextView) itemView.findViewById(R.id.std_Secondary_Percentage);
            this.S_Year =(TextView) itemView.findViewById(R.id.std_Secondary_Year);
            this.H_School =(TextView) itemView.findViewById(R.id.std_HigherSecondary_School);
            this.H_Board =(TextView) itemView.findViewById(R.id.std_HigherSecondary_Board);
            this.H_Percentage =(TextView) itemView.findViewById(R.id.std_HigherSecondary_Percentage);
            this.H_Year =(TextView) itemView.findViewById(R.id.std_HigherSecondary_Year);
            this.G_College =(TextView) itemView.findViewById(R.id.std_Graduation_College);
            this.G_Branch =(TextView) itemView.findViewById(R.id.std_Graduation_Branch);
            this.G_Enrollmentno =(TextView) itemView.findViewById(R.id.std_Graduation_Enrollmentno);
            this.G_Sem1 =(TextView) itemView.findViewById(R.id.std_Graduation_Sem1);
            this.G_Year1 =(TextView) itemView.findViewById(R.id.std_Graduation_Year1);
            this.G_Sem2 =(TextView) itemView.findViewById(R.id.std_Graduation_Sem2);
            this.G_Year2 =(TextView) itemView.findViewById(R.id.std_Graduation_Year2);
            this.G_Sem3 =(TextView) itemView.findViewById(R.id.std_Graduation_Sem3);
            this.G_Year3 =(TextView) itemView.findViewById(R.id.std_Graduation_Year3);
            this.G_Sem4 =(TextView) itemView.findViewById(R.id.std_Graduation_Sem4);
            this.G_Year4 =(TextView) itemView.findViewById(R.id.std_Graduation_Year4);
            this.G_Sem5 =(TextView) itemView.findViewById(R.id.std_Graduation_Sem5);
            this.G_Year5 =(TextView) itemView.findViewById(R.id.std_Graduation_Year5);
            this.G_Sem6 =(TextView) itemView.findViewById(R.id.std_Graduation_Sem6);
            this.G_Year6 =(TextView) itemView.findViewById(R.id.std_Graduation_Year6);
            this.G_Sem7 =(TextView) itemView.findViewById(R.id.std_Graduation_Sem7);
            this.G_Year7 =(TextView) itemView.findViewById(R.id.std_Graduation_Year7);
            this.G_Sem8 =(TextView) itemView.findViewById(R.id.std_Graduation_Sem8);
            this.G_Year8 =(TextView) itemView.findViewById(R.id.std_Graduation_Year8);
            this.ResumeUrl =(TextView) itemView.findViewById(R.id.std_CheckResume);
            //this.Key =(TextView) itemView.findViewById(R.id.std_K);
            this.PdfUrl =(TextView) itemView.findViewById(R.id.std_Checkpdf);
            this.linearLayout= (LinearLayout) itemView.findViewById(R.id.linearlayout);
        }


        }
    }

