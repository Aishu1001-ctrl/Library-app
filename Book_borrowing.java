package com.example.library_app_java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.library_app_java.databinding.ActivityBookBorrowingBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Book_borrowing extends AppCompatActivity {

ActivityBookBorrowingBinding binding;
DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityBookBorrowingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        backclicklistner();

        binding.bookBorrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MemberID= binding.memBorrowID.getText().toString();
                String BookID=binding.BookBorrowID.getText().toString();

                if (!MemberID.isEmpty()&&!BookID.isEmpty()){
                    updateMember(MemberID);
                    updateBook(BookID);

                }
                else {
                    Toast.makeText(Book_borrowing.this,"Fill all Feilds",Toast.LENGTH_SHORT).show();
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
                String   BookStatus="Not Available";
                String   BookYear=String.valueOf(dataSnapshot.child("publishedYear").getValue());

                reference = FirebaseDatabase.getInstance().getReference("Books");
                Books Books=new  Books(bookID,BookName,BookAuthor,BookYear,BookStatus);
                reference.child(bookID).setValue(Books);


                Toast.makeText(Book_borrowing.this,BookName+" has been borrowed",Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Book_borrowing.this,"Failled",Toast.LENGTH_SHORT).show();
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
                String MemberStatus = "Borrowed";
                String MemberEmail = String.valueOf(dataSnapshot.child("memberEmail").getValue());
                String Gender = String.valueOf(dataSnapshot.child("memberGender").getValue());

                reference = FirebaseDatabase.getInstance().getReference("Members");
                Members Members=new  Members(memberID,MemberName,MemberDOB,MemberEmail,Gender,MemberStatus);
                reference.child(memberID).setValue(Members);

                binding.BookBorrowID.setText("");
                binding.memBorrowID.setText("");
                binding.radioGroupBorrowBookStatus.clearCheck();
                binding.radioGroupBorrowMemStatus.clearCheck();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Book_borrowing.this,"Failled",Toast.LENGTH_SHORT).show();
            }
        });
    }

            public void backclicklistner() {
                TextView borrow_book_back_button = findViewById(R.id.borrow_back_btn);
                borrow_book_back_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Book_borrowing.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
            }

}