package com.oldmen.superapp.ui.fragment.discovery;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.oldmen.superapp.Data;
import com.oldmen.superapp.R;
import com.oldmen.superapp.db.dao.ChannelDao;
import com.oldmen.superapp.db.handler.SuperDatabase;
import com.oldmen.superapp.db.model.Channel;
import com.oldmen.superapp.ui.activity.viewChannel.ViewChannelActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class DiscoveryAdapter extends RecyclerView.Adapter<DiscoveryAdapter.DataHolder> {

    private String TAG = DiscoveryAdapter.class.getSimpleName();
    private Context mContext;
    private List<String> mChannels;
    private JSONArray mList;

    public DiscoveryAdapter(Context context, List<String> channels) {
        mContext = context;
        mChannels = channels;
        try {
            mList = new JSONArray(Data.DISCOVERY_STRING);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "DiscoveryAdapter: Error in creating json");
        }
    }

    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DataHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.discovery_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DataHolder holder, int position) {

        try {
            JSONObject json = mList.getJSONObject(position);
            holder.title.setText(json.getString("title"));
            holder.category.setText(json.getString("category"));
            holder.description.setText(json.getString("description"));
            holder.memberCount.setText(json.getString("members"));
            Glide.with(mContext).load(json.getString("logo")).into(holder.image);

            if (mChannels.contains(json.getString("id"))) {
                holder.mFollowButton.setText("Followed");
            } else {
                holder.mFollowButton.setText("Follow");
            }

        } catch (JSONException e) {
            Log.e(TAG, "onBindViewHolder: Error in extracting data");
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.length();
    }

    class DataHolder extends RecyclerView.ViewHolder {

        private LinearLayout card;
        private TextView title;
        private TextView category;
        private TextView memberCount;
        private TextView description;
        private ImageView image;
        private TextView mFollowButton;

        public DataHolder(@NonNull View itemView) {
            super(itemView);

            card = itemView.findViewById(R.id.discovery_card);
            title = itemView.findViewById(R.id.title);
            category = itemView.findViewById(R.id.category);
            memberCount = itemView.findViewById(R.id.rating_count);
            description = itemView.findViewById(R.id.discription);
            image = itemView.findViewById(R.id.logo);
            mFollowButton = itemView.findViewById(R.id.follow);

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        JSONObject object = mList.getJSONObject(getAdapterPosition());
                        if (object.getString("type").equals("WEB")) {
                            String url = object.getString("url");
                            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                            CustomTabsIntent customTabsIntent = builder.build();
                            customTabsIntent.launchUrl(mContext, Uri.parse(url));
                        } else {
                            // Open description activity
                            Intent intent = new Intent(mContext, ViewChannelActivity.class);
                            mContext.startActivity(intent);
                        }
                    } catch (Exception ex) {
                        Log.e(TAG, "DataHolder: Error in extracting data");
                    }
                }
            });

            mFollowButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "You followed this channel", Toast.LENGTH_SHORT).show();
                    try {
                        JSONObject object = mList.getJSONObject(getAdapterPosition());
                        Channel channel = new Channel(
                                object.getString("id"),
                                object.getString("title"),
                                "public",
                                object.getString("description"),
                                object.getString("logo"),
                                object.getString("type"),
                                object.getString("url")
                        );
                        mFollowButton.setText("Followed");
                        ChannelDao channelDao = SuperDatabase.getInstance(mContext).channelDao();
                        channelDao.insert(channel);
                        Toast.makeText(mContext, "You followed " + channel.getName(), Toast.LENGTH_LONG).show();
                    } catch (Exception ex) {
                        Log.e(TAG, "DataHolder: Error in extracting data");
                    }
                }
            });
        }
    }
}
