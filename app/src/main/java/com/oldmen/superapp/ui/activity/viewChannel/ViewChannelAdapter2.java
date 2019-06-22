package com.oldmen.superapp.ui.activity.viewChannel;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.oldmen.superapp.Data;
import com.oldmen.superapp.R;
import com.oldmen.superapp.db.dao.CommentsDao;
import com.oldmen.superapp.db.dao.LikesDao;
import com.oldmen.superapp.db.handler.SuperDatabase;
import com.oldmen.superapp.db.model.Comments;
import com.oldmen.superapp.db.model.Likes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewChannelAdapter2 extends RecyclerView.Adapter<ViewChannelAdapter2.DataHolder> {

    private final String TAG = ViewChannelAdapter2.class.getSimpleName();
    private Context mContext;
    private JSONArray messages;
    private HashMap<String, Integer> mLikesList;
    private HashMap<String, Integer> mCommentCountList;
    private ArrayList<String> mLikedList;
    private List<Comments> mCommentsList;

    public ViewChannelAdapter2(Context context,
                               HashMap<String, Integer> likes,
                               HashMap<String, Integer> commentCount,
                               List<Comments> commentsList) {
        mContext = context;
        mLikesList = likes;
        mLikedList = new ArrayList<>();
        mCommentCountList = commentCount;
        mCommentsList = commentsList;
        Log.e(TAG, "ViewChannelAdapter2: likes size : " + mLikesList.size());
        try {
            messages = new JSONArray(Data.SHARE_CHATE_STRING);
        } catch (JSONException e) {
            Log.e(TAG, "ViewChannelAdapter: Error in creating json");
        }
    }

    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DataHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.like_comment_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DataHolder holder, int position) {
        try {
            Log.e(TAG, "onBindViewHolder: length " + messages.length());
            JSONObject json = messages.getJSONObject(position);
            Glide.with(mContext)
                    .load(json.getString("image"))
                    .into(holder.image);

            if (mLikedList.contains(position + "")) {
                holder.like.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_round_favorite_red_24px));
            } else {
                holder.like.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_round_favorite_24px));
            }

            int likes = mLikesList.get(json.getString("id")) == null ?
                    0 : mLikesList.get(json.getString("id"));
            String likesCountString = likes + " Likes";
            holder.likesCount.setText(likesCountString);

            int commentCount = mCommentCountList.get(json.getString("id")) == null ?
                    0 : mCommentCountList.get(json.getString("id"));
            String commentCountString = commentCount + " Comments";
            holder.commentsCount.setText(commentCountString);
        } catch (JSONException e) {

        }
    }

    @Override
    public int getItemCount() {
        return messages == null ? 0 : messages.length();
    }

    class DataHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private ImageView like;
        private TextView comment;
        private TextView likesCount;
        private TextView commentsCount;

        public DataHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            like = itemView.findViewById(R.id.like);
            comment = itemView.findViewById(R.id.comment);
            likesCount = itemView.findViewById(R.id.likes_count);
            commentsCount = itemView.findViewById(R.id.comments_count);


            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        int position = getAdapterPosition();
                        JSONObject data = messages.getJSONObject(position);
                        String id = data.getString("id");
                        int oldCount = mLikesList.get(id) == null ? 0 : mLikesList.get(id);
                        Log.e(TAG, " Old LIke Count : " + oldCount);
                        int updatedLike =
                                mLikedList.contains(position + "") ?
                                        oldCount - 1 : oldCount + 1;
                        String str = updatedLike + " Likes";
                        Log.e(TAG, "LIke Count : " + updatedLike);
                        likesCount.setText(str);
                        Likes likes = new Likes(id, updatedLike);
                        LikesDao likesDao = SuperDatabase.getInstance(mContext).likesDao();
                        likesDao.insert(likes);
                        mLikesList.put(id, updatedLike);
                        if (mLikedList.contains(position + "")) {
                            like.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_round_favorite_24px));
                            mLikedList.remove(position + "");
                        } else {
                            like.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_round_favorite_red_24px));
                            mLikedList.add(position + "");
                        }
                    } catch (JSONException e) {
                        Log.e(TAG, "onClick: error inside on click");
                    }

                }
            });

            comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        JSONObject json = messages.getJSONObject(getAdapterPosition());
                        showCommentPopUP(json.getString("id"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e(TAG, "onClick: error inside comment on click");
                    }
                }
            });
        }
    }

    private void showCommentPopUP(final String parentId) {
        final EditText input = new EditText(mContext);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Comment")
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String comment = input.getText().toString();
                        Comments comments = new Comments(
                                Integer.toString((int) (Math.random() * 10000)),
                                parentId,
                                comment
                        );
                        CommentsDao dao = SuperDatabase.getInstance(mContext).commentsDao();
                        dao.insert(comments);
                        int count = mCommentCountList.get(parentId) == null ? 0 : mCommentCountList.get(parentId) + 1;
                        mCommentCountList.put(parentId, count);
                        notifyDataSetChanged();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.setView(input);
        dialog.show();
    }
}
