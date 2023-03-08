package com.eagle.cansacare.post;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eagle.cansacare.R;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder>{

    Context mContext;
    List<Post> mList;

    public PostAdapter(Context mContext, List<Post> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View row = LayoutInflater.from(mContext).inflate(R.layout.post_item_layout, parent, false);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtViewCaption.setText(mList.get(position).getTitle());
//        holder.txtViewDescription.setText(mList.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txtViewCaption, txtViewDescription;
//        ImageView postImg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtViewCaption = itemView.findViewById(R.id.post_caption);
//            txtViewDescription = itemView.findViewById(R.id.post_description);
//            postImg = itemView.findViewById(R.id.post_image);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent postDetailActivity = new Intent(mContext, PostDetailActivity.class);
                    int position = getAdapterPosition();

                    postDetailActivity.putExtra("title", mList.get(position).getTitle());
                    postDetailActivity.putExtra("description", mList.get(position).getDescription());
                    postDetailActivity.putExtra("postKey", mList.get(position).getKey());
                    System.out.println(mList.get(position).getKey() + "======================");

                    long timeStamp = (long) mList.get(position).getTimeStamp();
                    postDetailActivity.putExtra("postDate", timeStamp);
                    mContext.startActivity(postDetailActivity);
                }
            });
        }
    }
}
