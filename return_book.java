package com.example.library_app_java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.library_app_java.databinding.ActivityBookBorrowingBinding;
import com.example.library_app_java.databinding.ActivityReturnBookBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class return_book extends AppCompatActivity {

    ActivityReturnBookBinding binding;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityReturnBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        backclicklistner();


        binding.bookReturnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MemberID= binding.memReturnID.getText().toString();
                String BookID=binding.BookReturnID.getText().toString();
                if (!MemberID.isEmpty()&&!BookID.isEmpty()){
                    updateMember(MemberID);
                    updateBook(BookID);

                }
                else {
                    Toast.makeText(return_book.this,"Fill all Feilds",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateBook(String bookID) {
        reference= FirebaseDatabase.getInstance().getReference("Books");
        reference.child(bookID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                DataSnapshot dataSnapshot= task.getResult();

                String BookName=String.valueOf(dataSnapshot.child("bookName").getValue());
                String    BookAuthor=String.valueOf(dataSnapshot.child("bookAuthor").getValue());
                String   BookStatus="Available";
                String   BookYear=String.valueOf(dataSnapshot.child("publishedYear").getValue());

                reference = FirebaseDatabase.getInstance().getReference("Books");
                Books Books=new  Books(bookID,BookName,BookAuthor,BookYear,BookStatus);
                reference.child(bookID).setValue(Books);


                Toast.makeText(return_book.this,BookName+" has been returned",Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(return_book.this,"Failled",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateMember(String memberID) {
        reference = FirebaseDatabase.getInstance().getReference("Members");
        reference.child(memberID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                DataSnapshot dataSnapshot = task.getResult();

                String MemberName = String.valueOf(dataSnapshot.child("memberName").getValue());
                String MemberDOB = String.valueOf(dataSnapshot.child("memberDOB").getValue());
                String MemberStatus = "Not Borrowed";
                String MemberEmail = String.valueOf(dataSnapshot.child("memberEmail").getValue());
                String Gender = String.valueOf(dataSnapshot.child("memberGender").getValue());

                reference = FirebaseDatabase.getInstance().getReference("Members");
                Members Members=new  Members(memberID,MemberName,MemberDOB,MemberEmail,Gender,MemberStatus);
                reference.child(memberID).setValue(Members);

                binding.BookReturnID.setText("");
                binding.memReturnID.setText("");
                binding.radioGroupReturnBookStatus.clearCheck();
                binding.radioGroupReturnMemStatus.clearCheck();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(return_book.this,"Failled",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void backclicklistner() {
        TextView return_book_back_button = findViewById(R.id.return_back_btn);
        return_book_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(return_book.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}