package com.example.library_app_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clicklistner();
    }
    public void clicklistner(){
        CardView Add_mem_button=findViewById(R.id.home_add_mem_btn);
        Add_mem_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Member_registration.class);
                startActivity(intent);
            }
        });
        CardView Add_book_button=findViewById(R.id.home_add_book_btn);
        Add_book_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Book_registration.class);
                startActivity(intent);
            }
        });
        CardView borrow_book_button=findViewById(R.id.home_borrow_book_btn);
        borrow_book_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Book_borrowing.class);
                startActivity(intent);
            }
        });
        CardView return_book_button=findViewById(R.id.home_return_book_btn);
        return_book_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, return_book.class);
                startActivity(intent);
            }
        });
    }
}