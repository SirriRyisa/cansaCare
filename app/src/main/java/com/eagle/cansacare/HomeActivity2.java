package com.eagle.cansacare;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.eagle.cansacare.databinding.ActivityHome2Binding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity2 extends AppCompatActivity {

    private static final int REQUESCODE = 1;
    private ActivityHome2Binding binding;
    private static final int PReqCode = 2;
//    private static final int SELECT_PICTURE = 1;
    Dialog popAddComment;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    ImageView newsFeedUserImage, newsPostImage, popupAddBtn;
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
            }
        });

        BottomNavigationView navView = findViewById(R.id.nav_view_home);
        navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int selectedItemId = item.getItemId();
                switch (selectedItemId) {
                    case R.id.navigation_dashboard:
//                        getSupportFragmentManager().beginTransaction()
//                            .setReorderingAllowed(true)
//                            .add(R.id.nav_host_fragment_activity_home2, ExampleFragment.class, null)
//                            .commit();
                        break;
                    case R.id.navigation_home: //we change the fragment here ;
                        break;
                    case R.id.navigation_notifications: //we change the fragment here ;
                        break;

                }
                return true;
            }
        });
    }

    private void setupNewsPostImage(){

        popupAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("clicking");
//                ImagePickerView.Builder()
//                        .setup {
//                    name { RESULT_NAME }
//                    max { 5 }
//                    title { "Image Picker" }
//                    single { false }
//                }
//            .start(this)
//                ImagePicker.Companion.with(HomeActivity2.this)
//                        .crop()        //Crop image(Optional), Check Customization for more option
//                        .cropOval()       //Allow dimmed layer to have a circle inside
//                        .cropFreeStyle()     //Let the user to resize crop bounds
//                        .galleryOnly()          //We have to define what image provider we want to use
//                        .maxResultSize(1080, 1080, true) //Final image resolution will be less than 1080 x 1080(Optional)
//                        .createIntent();
            }
        });
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
        newsFeedUserImage = view.findViewById(R.id.block_image);
        newsPostImage = view.findViewById(R.id.blog_image_view);
        popupAddBtn = view.findViewById(R.id.image_in_image);
        postTitle = view.findViewById(R.id.title_edit_text);
        postDescription = view.findViewById(R.id.description_edit_text);
        postProgressBar = view.findViewById(R.id.progress_bar);

        //Add firebase profile image
//        Picasso.get().load("https://i.imgur.com/DvpvklR.png").into(newsPostImage);

//        Add post Listener

        popupAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupAddBtn.setVisibility(View.INVISIBLE);
                postProgressBar.setVisibility(View.VISIBLE);

                if (!postTitle.getText().toString().isEmpty()
                        && !postDescription.getText().toString().isEmpty()) {


                    String title = postTitle.getText().toString().trim();
                    String message = postDescription.getText().toString().trim();

                    Post post = new Post(title, message, "2");
                    addPost(post);

//                    StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Document");
//
////                    final StorageReference imageFilePath = storageReference.child(pickedImgUrl.getLastPathSegment());
//                    final StorageReference filePath = storageReference.child(postTitle.getText().toString());
//                    filePath.putFile(Uri.parse(postTitle.getText().toString())).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                @Override
//                                public void onSuccess(Uri uri) {
////                                    String imageDownloadLink = uri.toString();
//
//                                    Post post = new Post(postTitle.getText().toString(),
//                                            postDescription.getText().toString(),
//                                            currentUser.getPhotoUrl().toString());
//
//                                    addPost(post);
//                                }
//                            }).addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    showMessage(e.getMessage());
//                                    popupAddBtn.setVisibility(View.INVISIBLE);
//                                    postProgressBar.setVisibility(View.VISIBLE);
//
//                                }
//                            });
//
//                        }
//                    });


                }else{
                    showMessage("Please a field in the title and description");
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
        Toast.makeText(HomeActivity2.this, message,Toast.LENGTH_LONG).show();
    }

}