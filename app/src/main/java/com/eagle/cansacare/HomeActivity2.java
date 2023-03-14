package com.eagle.cansacare;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.eagle.cansacare.post.Post;
import com.eagle.cansacare.ui.dashboard.DashboardFragment;
import com.eagle.cansacare.ui.home.HomeFragment;
import com.eagle.cansacare.ui.bookings.BookingFragment;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.UUID;

public class HomeActivity2 extends AppCompatActivity {


    private static final int REQUESCODE = 1;
    //    private ActivityHome2Binding binding;
    private static final int PReqCode = 2;
    //    private static final int SELECT_PICTURE = 1;
    Dialog popAddComment;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    ImageView newsFeedUserImage, newsPostImage, popupAddBtn, blog_image_view;
    TextView postTitle, postDescription;
    ProgressBar postProgressBar;
    private Uri pickedImgUrl = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar_chat);
//        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

//        setupNewsPostImage();

//         iniNewsfeed();
//        ImageView image = findViewById(R.id.dummy_image);
//
//        Picasso.get().load("https://i.imgur.com/DvpvklR.png").into(image);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floating_bar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                popAddComment.show();
                iniNewsfeed();

                setupNewsPostImage();
            }
        });

        BottomNavigationView navView = findViewById(R.id.nav_view_home);

        performFragmentTransaction(new HomeFragment());

        navView.setOnItemSelectedListener(item -> {
            int menuSelectedId = item.getItemId();

            Fragment fragment;

            if (menuSelectedId == R.id.navigation_home) {
                fragment = new HomeFragment();
            } else if (menuSelectedId == R.id.navigation_dashboard) {
                fragment = new DashboardFragment();
            } else if (menuSelectedId == R.id.book_appointment){
                fragment = new BookingFragment();
            }else {
                fragment = new BookingFragment();

            }

            performFragmentTransaction(fragment);
            return true;
        });

    }

    private void performFragmentTransaction(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_fragment_activity_home2, fragment, "ok yes")
                .commit();

    }

    private void setupNewsPostImage() {

        popupAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAndRequestCameraPermission();
            }
        });
    }

    private void checkAndRequestCameraPermission() {
        if(ContextCompat.checkSelfPermission(HomeActivity2.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(HomeActivity2.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
//                    PReqCode);
            if(ActivityCompat.shouldShowRequestPermissionRationale(HomeActivity2.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Toast.makeText(HomeActivity2.this, "Please allow camera access", Toast.LENGTH_SHORT).show();
            }

            else {
                ActivityCompat.requestPermissions(HomeActivity2.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        PReqCode);
            }
        }
        else
            openGallery();
    }

    public void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,REQUESCODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && data != null) {
            pickedImgUrl = data.getData();
            newsPostImage.setImageURI(pickedImgUrl);
        }else{
            Log.e("cancer","failed to get image");
        }
    }

    public void onClick(View view) {
        popAddComment.show();
    }

    private void iniNewsfeed() {
        popAddComment = new Dialog(this);

        View view = View.inflate(this, R.layout.fragment_chat_layout, null);
        popAddComment.setContentView(view);
        popAddComment.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popAddComment.getWindow().setLayout(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT);
        popAddComment.getWindow().getAttributes().gravity = Gravity.TOP;
//
//        ini news feed widgets
        newsFeedUserImage = view.findViewById(R.id.post_image);
        newsPostImage = view.findViewById(R.id.blog_image_view);
        popupAddBtn = view.findViewById(R.id.image_in_image);
        postTitle = view.findViewById(R.id.title_edit_text);
        postDescription = view.findViewById(R.id.description_edit_text);
        postProgressBar = view.findViewById(R.id.progress_bar);

        //Add firebase profile image

//        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/cansacare.appspot.com/o" + "/v0/b/cansacare.appspot.com/o/images/8523b024-8061-493f-ba74-01e1c9fa48ae.png").into(newsFeedUserImage);


//        Add post Listener

       newsPostImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupAddBtn.setVisibility(View.INVISIBLE);
                postProgressBar.setVisibility(View.VISIBLE);

                if (!postTitle.getText().toString().isEmpty()
                        && !postDescription.getText().toString().isEmpty()
                        && pickedImgUrl != null) {

                    String title = postTitle.getText().toString().trim();
                    String message = postDescription.getText().toString().trim();
//                    String picture = newsPostImage.toString();

                    String image = UUID.randomUUID().toString() + ".png";
                    StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("images/"+image);

                    storageReference.putFile(pickedImgUrl).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                                @Override
                                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                    if (!task.isSuccessful()) {
                                        Log.e("cancer","failed to upload image");
                                        throw task.getException();

                                    }

                                    // Continue with the task to get the download URL
                                    return storageReference.getDownloadUrl();
                                }
                            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {
                                Uri downloadUri = task.getResult();
                                Post post = new Post(title, message, currentUser.getUid(), downloadUri.toString());
                                addPost(post);
                                Log.i("cancer","post added");
                            } else {
                                Log.e("cancer","failed to upload image");
                            }

                        }
                    });

//
                } else {
                    showMessage("Please verify all input field have description and choose post image");
                    popupAddBtn.setVisibility(View.VISIBLE);
                    postProgressBar.setVisibility(View.INVISIBLE);
                }

            }
        });

        popAddComment.show();
    }

    private void addPost(Post post) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Posts").push();
        String key = myRef.getKey();

        post.setPostKey(key);

        myRef.setValue(post.toMap()).addOnSuccessListener(unused -> {
            showMessage("Post added successfully");
            postProgressBar.setVisibility(View.INVISIBLE);
            popupAddBtn.setVisibility(View.VISIBLE);
            popAddComment.dismiss();

        });
    }

    private void showMessage(String message) {
        Toast.makeText(HomeActivity2.this, message, Toast.LENGTH_LONG).show();
    }

}