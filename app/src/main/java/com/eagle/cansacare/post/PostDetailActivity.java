package com.eagle.cansacare.post;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eagle.cansacare.comment.Comment;
import com.eagle.cansacare.comment.CommentAdapter;
import com.eagle.cansacare.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class PostDetailActivity extends AppCompatActivity {
    ImageView blogImage;
    TextView blogTxtTitle, blogDateName, blogTxtDescription;
    EditText blogComment;
    Button addCommentbtn;
    String PostKey;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    RecyclerView commentRecyclerView;
    CommentAdapter commentAdapter;
    List<Comment> listComment;
    static String COMMENT_KEY = "Comments";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_description);

//        initialize views


        commentRecyclerView = findViewById(R.id.comment_recyclerView);
        blogImage = findViewById(R.id.post_image);
        blogTxtTitle = findViewById(R.id.post_caption);
        blogDateName = findViewById(R.id.blog_date);
        blogTxtDescription = findViewById(R.id.post_description);
        blogComment = findViewById(R.id.blog_comment);
        addCommentbtn = findViewById(R.id.blog_add_btn);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

//        Adding comment button eventClickListener

        addCommentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCommentbtn.setVisibility(View.INVISIBLE);
                DatabaseReference commentReference = firebaseDatabase.getReference(COMMENT_KEY).child(PostKey).push();
                String comment_content = blogComment.getText().toString();
                String userId = firebaseUser.getUid();
                String userName = firebaseUser.getDisplayName();
//                UUID.randomUUID().toString();
//                String userName = "Sirri Ngwa";
//

                Comment comment = new Comment(comment_content, userId, userName);

                commentReference.setValue(comment).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        showMessage("Comment successfully added");
                        blogComment.setText("");
                        addCommentbtn.setVisibility(View.VISIBLE);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showMessage("Failed to add comment : "+ e.getMessage());
                    }
                });
            }
        });

//        Binding data into views
//        Before binding the data we need get the data
//        We start by sending post data to activity

        String postTitle = getIntent().getExtras().getString("title");
        blogTxtTitle.setText(postTitle);

        String postDescription = getIntent().getExtras().getString("description");
        blogTxtDescription.setText(postDescription);

        PostKey = getIntent().getExtras().getString("postKey");

        String date = timeStampToString(getIntent().getExtras().getLong("postDate"));
        blogDateName.setText(date);

//        Initializing comment recyclerView

        iniRecyclerView();

    }

    private void iniRecyclerView(){

        commentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DatabaseReference commentRef = firebaseDatabase.getReference(COMMENT_KEY).child(PostKey);
        commentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listComment = new ArrayList<>();
                for (DataSnapshot snap:snapshot.getChildren()) {
//                    String key = snapshot.getKey();
                    Comment comment = snap.getValue(Comment.class);
//                    comment.setKey(key);

                    listComment.add(comment);
                }

                commentAdapter = new CommentAdapter(PostDetailActivity.this, listComment);
                commentRecyclerView.setAdapter(commentAdapter);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    private String timeStampToString(long time) {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(time);
        String date = DateFormat.format("dd-MM-yyyy", calendar).toString();
        return date;
    }
}
