package com.example.company.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.company.R;
import com.example.company.Model.Register_Model;
import com.example.company.Activity.View_Attachment_Company;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {
Button Edit;
    FirebaseAuth firebaseAuth;
    StorageReference storageReference;
    DatabaseReference firebaseDatabase;
    SharedPreferences sharedPreferences;
    Context context;
   Button attachment;
   CircleImageView profileimage;
EditText Edit_profile_company_email, Edit_profile_company_name,Edit_profile_company_number,Edit_profile_company_address,Edit_profile_company_city;
String edit_profile_company_email,edit_profile_company_name,edit_profile_company_number,edit_profile_company_address,edit_profile_company_city;
String Email,key,password,imgurl,pdfurl, name,email,number,address,website;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        firebaseAuth = firebaseAuth.getInstance();

        //storageReference = FirebaseStorage.getInstance().getReference();


        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        Edit = root.findViewById(R.id.edit_profile_company);
        profileimage=root.findViewById(R.id.profile_image);
        Edit_profile_company_email = root.findViewById(R.id.edit_profile_company_email);
        Edit_profile_company_name = root.findViewById(R.id.edit_profile_company_name);
        Edit_profile_company_number = root.findViewById(R.id.edit_profile_company_number);
        Edit_profile_company_address = root.findViewById(R.id.edit_profile_company_address);
        Edit_profile_company_city = root.findViewById(R.id.edit_profile_company_city);
        attachment=root.findViewById(R.id.viewattachmentcompany);


        sharedPreferences  = this.getActivity().getSharedPreferences("MySharedPref",MODE_PRIVATE);

        edit_profile_company_email = sharedPreferences.getString("E-mail", "");
        Log.d("email",edit_profile_company_email);
        edit_profile_company_name = sharedPreferences.getString("C-name", "");
        edit_profile_company_address = sharedPreferences.getString("C-address", "");
        edit_profile_company_number = sharedPreferences.getString("C-number", "");
        edit_profile_company_city = sharedPreferences.getString("C-city", "");



        Query query = firebaseDatabase.child("Company").orderByChild("email").equalTo(edit_profile_company_email);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Register_Model registerModel = dataSnapshot.getValue(Register_Model.class);

                        key = registerModel.getId();
                        password = registerModel.getPassword();
                        imgurl = registerModel.getImgurl();
                        //Glide.with(context).load(registerModel.getImgurl()).into(profileimage);
                        pdfurl = registerModel.getPdfurl();
                        edit_profile_company_name=registerModel.getName();
                        Edit_profile_company_name.setText(edit_profile_company_name);
                        edit_profile_company_email=registerModel.getEmail();
                        Edit_profile_company_email.setText(edit_profile_company_email);
                        edit_profile_company_number=registerModel.getNumber();
                        Edit_profile_company_number.setText(edit_profile_company_number);
                        edit_profile_company_address=registerModel.getAddress();
                        Edit_profile_company_address.setText(edit_profile_company_address);
                        edit_profile_company_city=registerModel.getWebsite();
                        Edit_profile_company_city.setText(edit_profile_company_city);




                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        attachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), View_Attachment_Company.class);
                intent.putExtra("Attachment",pdfurl);
                startActivity(intent);
            }
        });
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = Edit_profile_company_email.getText().toString();
                name= Edit_profile_company_name.getText().toString();
                number = Edit_profile_company_number.getText().toString();
                address = Edit_profile_company_address.getText().toString();
                website = Edit_profile_company_city.getText().toString();


                Register_Model registerModel = new Register_Model(name,number,email,address,website,password,key,imgurl,pdfurl);

                firebaseDatabase.child("Company").child(key).setValue(registerModel);
                Toast.makeText(getActivity(), "Update Successfully", Toast.LENGTH_SHORT).show();


            }
        });




                        return root;

                    }
                }