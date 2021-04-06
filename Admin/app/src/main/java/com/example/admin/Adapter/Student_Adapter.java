package com.example.admin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.admin.Activity.CompanydataActivity;
import com.example.admin.Activity.StudentdataActivity;
import com.example.admin.Model.Company_Model;
import com.example.admin.Model.Student_Model;
import com.example.admin.R;

import java.util.ArrayList;
import java.util.List;

public class Student_Adapter extends RecyclerView.Adapter<Student_Adapter.ViewHolder> implements Filterable {

    Context context;
    List<Student_Model> StudentList;
    List<Student_Model> StudentListsearch;

    public Student_Adapter(Context context, List<Student_Model> studentList) {
        this.context = context;
        StudentList = studentList;
        StudentListsearch = new ArrayList<>(StudentList);
    }

    @NonNull
    @Override
    public Student_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.studentlistdesign, parent, false);
        Student_Adapter.ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Student_Adapter.ViewHolder holder, int position) {
        Student_Model model = StudentList.get(position);
        Glide.with(context).load(model.getImageUrl()).into(holder.StudentImg);
        holder.Name.setText(model.getName());
        holder.Email.setText(model.getEmail());
        holder.Number.setText(model.getNumber());
        holder.Dob.setText(model.getDob());
        holder.Gender.setText(model.getGender());
        holder.Address.setText(model.getAddress());
        holder.S_School.setText(model.getS_School());
        holder.S_Board.setText(model.getS_Board());
        holder.S_Percentage.setText(model.getS_Percentage());
        holder.S_Year.setText(model.getS_Year());
        holder.H_School.setText(model.getH_School());
        holder.H_Board.setText(model.getH_Board());
        holder.H_Percentage.setText(model.getH_Percentage());
        holder.H_Year.setText(model.getH_Year());
        holder.G_College.setText(model.getG_College());
        holder.G_Branch.setText(model.getG_Branch());
        holder.G_Enrollmentno.setText(model.getG_Enrollmentno());
        holder.G_Sem1.setText(model.getG_Sem1());
        holder.G_Year1.setText(model.getG_Year1());
        holder.G_Sem2.setText(model.getG_Sem2());
        holder.G_Year2.setText(model.getG_Year2());
        holder.G_Sem3.setText(model.getG_Sem3());
        holder.G_Year3.setText(model.getG_Year3());
        holder.G_Sem4.setText(model.getG_Sem4());
        holder.G_Year4.setText(model.getG_Year4());
        holder.G_Sem5.setText(model.getG_Sem5());
        holder.G_Year5.setText(model.getG_Year5());
        holder.G_Sem6.setText(model.getG_Sem6());
        holder.G_Year6.setText(model.getG_Year6());
        holder.G_Sem7.setText(model.getG_Sem7());
        holder.G_Year7.setText(model.getG_Year7());
        holder.G_Sem8.setText(model.getG_Sem8());
        holder.G_Year8.setText(model.getG_Year8());
        holder.ResumeUrl.setText(model.getResumeUrl());
        holder.PdfUrl.setText(model.getPdfUrl());

        holder.StudentcardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), StudentdataActivity.class);
                intent.putExtra("Student_Email", model.getEmail());
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return StudentList.size();
    }

    @Override
    public Filter getFilter() {
        return examplefilter;
    }

    private Filter examplefilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<Student_Model> filteredlist = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredlist.addAll(StudentListsearch);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Student_Model item : StudentListsearch) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filteredlist.add(item);
                    }
                    else if (item.getEmail().toLowerCase().contains(filterPattern)) {
                        filteredlist.add(item);
                    }
                    else if (item.getNumber().toLowerCase().contains(filterPattern)) {
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
            StudentList.clear();
            StudentList.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };


    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView StudentcardView;
        ImageView StudentImg;
        TextView Name;
        TextView Email;
        TextView Number;
        TextView Dob;
        TextView Gender;
        TextView Address;

        TextView S_School;
        TextView S_Board;
        TextView S_Percentage;
        TextView S_Year;

        TextView H_School;
        TextView H_Board;
        TextView H_Percentage;
        TextView H_Year;

        TextView G_College;
        TextView G_Branch;
        TextView G_Enrollmentno;
        TextView G_Sem1;
        TextView G_Year1;
        TextView G_Sem2;
        TextView G_Year2;
        TextView G_Sem3;
        TextView G_Year3;
        TextView G_Sem4;
        TextView G_Year4;
        TextView G_Sem5;
        TextView G_Year5;
        TextView G_Sem6;
        TextView G_Year6;
        TextView G_Sem7;
        TextView G_Year7;
        TextView G_Sem8;
        TextView G_Year8;

        TextView ResumeUrl;
        TextView PdfUrl;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.StudentcardView = (CardView) itemView.findViewById(R.id.student_cv);
            this.StudentImg = (ImageView) itemView.findViewById(R.id.student_image);
            this.Name = (TextView) itemView.findViewById(R.id.student_name);
            this.Email = (TextView) itemView.findViewById(R.id.student_email);
            this.Number = (TextView) itemView.findViewById(R.id.student_number);
            this.Dob =(TextView) itemView.findViewById(R.id.std_dob);
            this.Gender =(TextView) itemView.findViewById(R.id.std_gender);
            this.Address =(TextView) itemView.findViewById(R.id.std_address);

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
            this.PdfUrl =(TextView) itemView.findViewById(R.id.std_Checkpdf);

        }
    }
}
