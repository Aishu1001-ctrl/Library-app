package com.example.library_app_java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Member_registration extends AppCompatActivity {
    EditText memberId;
    EditText memberName;
    EditText dateOfBirth;
    EditText email;

    RadioButton radio1;

    RadioButton radio2;

    RadioButton radio3;

    RadioButton radio4;

    String gender=null;
    String memberStatus=null;

    RadioGroup gen;

    RadioGroup memstat;

    Button AddMemBtn;

    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_registration);
        backclicklistner();

        memberId=findViewById(R.id.MemID);
        memberName=findViewById(R.id.MemName);
        dateOfBirth=findViewById(R.id.MemDOB);
        email=findViewById(R.id.MemEmail);
        radio1=findViewById(R.id.radio_female);
        radio2=findViewById(R.id.radio_male);
        radio3=findViewById(R.id.radio_NotBorrowed);
        radio4=findViewById(R.id.radio_Borrowed);
        AddMemBtn=findViewById(R.id.mem_add_btn);
        gen=findViewById(R.id.radio_group_member_gender);
        memstat=findViewById(R.id.radio_group_member_status);

        database= FirebaseDatabase.getInstance().getReference().child("Members");

        AddMemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertMemData();

            }
        });
    }

    private void insertMemData(){

        if(radio1.isChecked()){
            gender="Female";
        }
        else if(radio2.isChecked()) {
            gender="Male";
        }
        if(radio3.isChecked()){
            memberStatus="Not Borrowed";
        }
        else if(radio4.isChecked()) {
            memberStatus="Borrowed";
        }

        String MemberID=memberId.getText().toString();
        String MemberName=memberName.getText().toString();
        String MemberDOB=dateOfBirth.getText().toString();
        String MemberEmail=email.getText().toString();
        String MemberGender=gender;
        String MemberStatus=memberStatus;



        Members Members=new Members(MemberID,MemberName,MemberDOB,MemberEmail,MemberGender,MemberStatus);
        database.child(MemberID).setValue(Members).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                memberId.setText("");
                memberName.setText("");
                email.setText("");
                dateOfBirth.setText("");
                gen.clearCheck();
                memstat.clearCheck();
            }
        });

        Toast.makeText(this,"Member Succesfully Added", Toast.LENGTH_SHORT).show();
    }

    public void backclicklistner() {
        TextView Add_mem_back_button = findViewById(R.id.mem_add_back_btn);
        Add_mem_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Member_registration.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}