package com.oldmen.superapp.ui.fragment.discovery;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.oldmen.superapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DiscoveryAdapter extends RecyclerView.Adapter<DiscoveryAdapter.DataHolder> {

    private String TAG = DiscoveryAdapter.class.getSimpleName();
    private Context mContext;

    private JSONArray mList;

    public DiscoveryAdapter(Context context) {
        mContext = context;
        try {
//            mList = new JSONArray("[{\"title\":\"Danik Bhaskar\",\"category\":\"News\",\"description\":\"This is very popular News channel\",\"rating\":4, \"members\":4000},{\"title\":\"Danik Bhaskar\",\"category\":\"News\",\"description\":\"This is very popular News channel\",\"rating\":4, \"members\":4000},{\"title\":\"Danik Bhaskar\",\"category\":\"News\",\"description\":\"This is very popular News channel\",\"rating\":4, \"members\":4000}]");
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
                    Toast.makeText(mContext, "Coming Soon", Toast.LENGTH_SHORT).show();
                }
            });

            mFollowButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "You followed this channel", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
