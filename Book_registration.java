package com.example.library_app_java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;

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

public class Book_registration extends AppCompatActivity {
    EditText bookId;
    EditText bookName;
    EditText bookAuthor;
    EditText publishedYear;
    RadioButton radiobtn1;

    RadioButton radiobtn2;

    RadioGroup bookstat;

    String BS=null;

    Button AddBookBtn;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_registration);
        backclicklistner();


        bookId=findViewById(R.id.BookID);
        bookName=findViewById(R.id.BookName);
        bookAuthor=findViewById(R.id.BookAuthor);
        publishedYear=findViewById(R.id.publishedyear);
        AddBookBtn=findViewById(R.id.book_add_btn);
        radiobtn1=findViewById(R.id.radio_Available);
        radiobtn2=findViewById(R.id.radio_NotAvailable);
        bookstat=findViewById(R.id.radio_group_book_status);



database= FirebaseDatabase.getInstance().getReference().child("Books");

        AddBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertBookData();

            }
        });

    }
    private void insertBookData(){

        if(radiobtn1.isChecked()){
            BS="Available";
        }
        else if(radiobtn2.isChecked()) {
            BS="Not Availble";
        }
        String BookID=bookId.getText().toString();
        String BookName=bookName.getText().toString();
        String BookAuthor=bookAuthor.getText().toString();
        String PublishedYear=publishedYear.getText().toString();
        String BookStatus=BS;

        Books Books=new Books(BookID,BookName,BookAuthor,PublishedYear,BookStatus);
        database.child(BookID).setValue(Books).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                bookId.setText("");
                bookName.setText("");
                bookAuthor.setText("");
                publishedYear.setText("");
                bookstat.clearCheck();

            }

        });


        Toast.makeText(this,"Book Succesfully Added", Toast.LENGTH_SHORT).show();



    }

    public void backclicklistner() {
        TextView Add_book_back_button = findViewById(R.id.add_book_back_btn);
        Add_book_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Book_registration.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}