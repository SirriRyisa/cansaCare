package com.eagle.cansacare.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eagle.cansacare.Post;
import com.eagle.cansacare.PostAdapter;
import com.eagle.cansacare.R;
import com.eagle.cansacare.databinding.FragmentHomeBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

//    private FragmentHomeBinding binding;
    RecyclerView postRecyclerView;
    PostAdapter postAdapter;
    FirebaseDatabase  firebaseDatabase;
    DatabaseReference databaseReference;

    List<Post> postList;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        View fragmentView = inflater.inflate(R.layout.post_recycler, container, false);
        postRecyclerView = fragmentView.findViewById(R.id.recycler_view_post);
        postRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        postRecyclerView.setHasFixedSize(true);
        firebaseDatabase  = FirebaseDatabase.getInstance();
        databaseReference  = firebaseDatabase.getReference("Posts");

        return fragmentView;

//        binding = FragmentHomeBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
//        final TextView textView = binding.textHomeThreat;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

    }

    @Override
    public void onStart() {
        super.onStart();

//        Getting list post from database

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                postList = new ArrayList<>();
                for (DataSnapshot postsnap : snapshot.getChildren()) {
                    Post post = postsnap.getValue(Post.class);
                    postList.add(post);
                }

                postAdapter = new PostAdapter(getActivity(), postList);
                postRecyclerView.setAdapter(postAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

//    @Override
//        public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//  }
}