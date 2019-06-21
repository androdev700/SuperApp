package com.oldmen.superapp.ui.fragment.discovery;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.oldmen.superapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DiscoveryAdapter extends RecyclerView.Adapter<DiscoveryAdapter.DataHolder> {

    private String TAG = DiscoveryAdapter.class.getSimpleName();
    private Context mContext;

    private JSONArray  mList;

    public DiscoveryAdapter(Context context) {
        mContext = context;
        try {
            mList = new JSONArray("[{\"title\":\"Danik Bhaskar\",\"category\":\"News\",\"description\":\"This is very popular News channel\",\"rating\":4, \"members\":4000},{\"title\":\"Danik Bhaskar\",\"category\":\"News\",\"discription\":\"This is very popular News channel\",\"rating\":4, \"members\":4000},{\"title\":\"Danik Bhaskar\",\"category\":\"News\",\"discription\":\"This is very popular News channel\",\"rating\":4, \"members\":4000}]");
        } catch (JSONException e) {
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
            holder.discription.setText(json.getString("description"));
            holder.memberCount.setText(json.getString("members"));
            Glide.with(mContext).load("https://github.com/androdev700/SuperApp/raw/master/logos/Netflix_icon.png").into(holder.image);
        } catch (JSONException e) {
            Log.e(TAG, "onBindViewHolder: Error in extracting data");
        }
    }

    @Override
    public int getItemCount() {
        return mList.length();
    }

    class DataHolder extends RecyclerView.ViewHolder {

        private LinearLayout card;
        private TextView title;
        private TextView category;
//        private RatingBar rating;
        private TextView memberCount;
        private TextView discription;
        private ImageView image;


        public DataHolder(@NonNull View itemView) {
            super(itemView);

            card = itemView.findViewById(R.id.discovery_card);
            title = itemView.findViewById(R.id.title);
            category = itemView.findViewById(R.id.category);
//            rating = itemView.findViewById(R.id.rating_bar);
            memberCount = itemView.findViewById(R.id.rating_count);
            discription = itemView.findViewById(R.id.discription);
            image = itemView.findViewById(R.id.logo);

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "This is dummy item", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
