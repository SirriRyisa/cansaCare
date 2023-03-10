package com.eagle.cansacare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ThreadDisplay extends AppCompatActivity {
    private final List<ThreadPost> threadPosts = new ArrayList<>(); // Define the list of posts

    ImageView goBackButton, createPost;

    DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_display);

        RecyclerView recyclerView = findViewById(R.id.recycler_thread);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ThreadAdapter threadAdapter = new ThreadAdapter(threadPosts); // create a new instance of threadAdapter
        recyclerView.setAdapter(threadAdapter); // set the adapter for the RecyclerView


        // Define the reference of the database
        mDatabase = FirebaseDatabase.getInstance().getReference("threadPosts");

        // Read the posts from Firebase Realtime Database
        mDatabase.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                threadPosts.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ThreadPost threadPost = dataSnapshot.getValue(ThreadPost.class);
                    threadPosts.add(threadPost);
                }
                threadAdapter.notifyDataSetChanged(); // notify the adapter about the data changes
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("TAG", "onCancelled", error.toException());
            }

        });

        goBackButton = findViewById(R.id.send_back_thread);
        goBackButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, HomeActivity2.class);
            intent.putExtra("goToFragmentChannel", true);
            startActivity(intent);
        });

        createPost = findViewById(R.id.create_new_post_thread);
        createPost.setOnClickListener(v -> {
            Intent intent = new Intent(ThreadDisplay.this, CreateNewThreadPost.class);
            startActivity(intent);
        });
    }
}