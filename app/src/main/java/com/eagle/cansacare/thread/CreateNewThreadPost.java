package com.eagle.cansacare.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eagle.cansacare.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class CreateNewThreadPost extends AppCompatActivity {

    private EditText postEditText;
    private DatabaseReference  databaseReference = FirebaseDatabase.getInstance().getReference();
    private final CollectionReference databaseReferenceStore = FirebaseFirestore.getInstance().collection("User");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_thread_post);

        postEditText = findViewById(R.id.edit_text_create_post);
        TextView postButton = findViewById(R.id.share_button_create_post);
        ImageView goBackButton = findViewById(R.id.send_back_thread);

        // Get a reference to the posts node in the Realtime Database
        databaseReference = databaseReference.child("threadPosts");

        postButton.setOnClickListener(view -> {
            // Get the current user's Name
//            String patientName = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getDisplayName();
            String userId = Objects.requireNonNull(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());

            Log.e("USER", userId);

            databaseReferenceStore.document(userId).get().addOnCompleteListener(task -> {

                if (!task.isSuccessful()){
                   return;
                }

                String patientName = Objects.requireNonNull(Objects.requireNonNull(task.getResult().getData()).get("displayName")).toString();
                // Generate a new ID for the post
                String postId = databaseReference.push().getKey();

                // Get the post text from the EditText
                String postContent = postEditText.getText().toString();

                // Get the current timestamp in milliseconds
                long postTime = System.currentTimeMillis();
                // Create a new Post object with the generated ID, current user's ID, post text, and timestamp
                ThreadPost threadPost = new ThreadPost(postId, patientName, postContent, postTime, 0);
                //postId, PatientName, postContent, postTime, 0
                // Save the post to the Realtime Database
                assert postId != null;
                databaseReference.child(postId).setValue(threadPost);

                Toast.makeText(CreateNewThreadPost.this, "Post published !", Toast.LENGTH_SHORT).show();

                // Finish the activity and go back to the main activity
                finish();
            });




        });

        goBackButton.setOnClickListener(v -> {
            Intent intent = new Intent(CreateNewThreadPost.this, ThreadDisplay.class);
            startActivity(intent);
        });

    }

}