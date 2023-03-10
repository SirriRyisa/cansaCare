package com.eagle.cansacare.comment;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eagle.cansacare.R;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private Context mContext;
    private List<Comment> mComment;
    public CommentAdapter(Context mContext, List<Comment> listComment) {
        this.mComment = listComment;
        this.mComment = mComment;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_row, parent, false);

        return new CommentViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        holder.commentName.setText(mComment.get(position).getUserName());
        holder.comment.setText(mComment.get(position).getContent());
       // holder.commentTime.setText(timeStampToString((Long)mComment.get(position).getTimeStamp()));

    }

    @Override
    public int getItemCount() {
        return mComment.size();
    }
    public class CommentViewHolder extends RecyclerView.ViewHolder {
        TextView commentName, commentTime, comment;
        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);

            commentName = itemView.findViewById(R.id.name_comment);
            commentTime = itemView.findViewById(R.id.time_comment);
            comment = itemView.findViewById(R.id.comment);
        }
    }
    private String timeStampToString(long time) {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(time);
        String date = DateFormat.format("hh:mm", calendar).toString();
        return date;
    }
}
