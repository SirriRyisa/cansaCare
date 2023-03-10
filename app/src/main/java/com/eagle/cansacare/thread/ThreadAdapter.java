package com.eagle.cansacare.thread;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eagle.cansacare.R;

import java.util.List;

public class ThreadAdapter extends RecyclerView.Adapter<ThreadAdapter.ThreadViewHolder>{

    private final List<ThreadPost> threadPosts;

    public ThreadAdapter(List<ThreadPost> threadPosts) {
        // Sort the posts in descending order based on their post time
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            Collections.sort(threadPosts, Comparator.comparingLong(p -> ThreadPost.getPostTime()));
//        }
        this.threadPosts = threadPosts;
    }


    @NonNull
    @Override
    public ThreadAdapter.ThreadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_thread_card, parent, false);
        return new ThreadViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ThreadAdapter.ThreadViewHolder holder, int position) {
        // Get the post at the current position
        ThreadPost threadPost = threadPosts.get(position);

        // Bind the data to the views
        holder.postContentTextView.setText(threadPost.getContentPost());
        holder.patientTextView.setText(threadPost.getPatientName());
        holder.postTime.setText(getFormattedTime(threadPost.getPostTime()));
        holder.likesTextView.setText(Integer.toString(threadPost.getLikes()));

        // Set click listeners for the like and dislike buttons
        holder.likeButton.setOnClickListener(view -> {
            threadPost.like();
            holder.likesTextView.setText(Integer.toString(threadPost.getLikes()));
        });

        // Set click listener for the whole item view
        holder.itemView.setOnClickListener(v -> {
            // Create intent for the new activity
//            Intent intent = new Intent(v.getContext(), CommentOpened.class);
//            intent.putExtra("content", ThreadPost.getContentPost());
//            intent.putExtra("author", ThreadPost.getPatientName());
//            intent.putExtra("time", ThreadPost.getPostTime());
//            intent.putExtra("likes", ThreadPost.getLikes());
//
//            // Start the new activity
//            v.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return threadPosts.size();
    }
    private String getFormattedTime(long postTime) {
        long now = System.currentTimeMillis();
        long diff = now - postTime;

        if (diff < 5 * 60 * 1000) { // 5 minutes
            return "now";
        } else {
            long minutes = diff / (60 * 1000);
            long hours = minutes / 60;
            long days = hours / 24;

            if (days > 0) {
                return days + " days ago";
            } else if (hours > 0) {
                return hours + " hours ago";
            } else {
                return minutes + " minutes ago";
            }
        }
    }
    static class ThreadViewHolder extends RecyclerView.ViewHolder {

        ImageView likeButton;
        TextView likesTextView;
        TextView patientTextView;
        TextView postContentTextView;
        TextView postTime;

        public ThreadViewHolder(@NonNull View itemView) {
            super(itemView);
            patientTextView = itemView.findViewById(R.id.patient_name_thread);
            postContentTextView = itemView.findViewById(R.id.post_content_thread);
            postTime = itemView.findViewById(R.id.post_time_thread);
            likeButton = itemView.findViewById(R.id.like_button_thread);
            likesTextView = itemView.findViewById(R.id.likes_count_thread);
        }

    }

}
