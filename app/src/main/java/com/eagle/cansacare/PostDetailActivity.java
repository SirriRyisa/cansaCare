package com.eagle.cansacare;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

public class PostDetailActivity extends AppCompatActivity {

    ImageView blogImage;
    TextView blogTxtTitle, blogDateName, blogTxtDescription;
    EditText blogComment;
    Button addCommentbtn;
    String PostKey;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_description);

//        initialize views

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
                DatabaseReference commentReference =
                        firebaseDatabase.getReference("Comments").child(PostKey).push();
                String comment_content = blogComment.getText().toString();
                String userId = UUID.randomUUID().toString();
//                        firebaseUser.getUid();
                String userName = "Sirri Ngwa";
//                        firebaseUser.getDisplayName();
                Comment comment = new Comment(comment_content, userId, userName);

                commentReference.setValue(comment).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        showMessage("Comment added");
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

        PostKey = getIntent().getExtras().getString("postKey","testing");

        String date = timeStampToString(getIntent().getExtras().getLong("postDate"));
        blogDateName.setText(date);

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
