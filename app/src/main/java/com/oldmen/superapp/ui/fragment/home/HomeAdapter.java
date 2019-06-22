package com.oldmen.superapp.ui.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.oldmen.superapp.R;
import com.oldmen.superapp.db.model.Channel;
import com.oldmen.superapp.ui.activity.viewChannel.ViewChannel2Activity;
import com.oldmen.superapp.ui.activity.viewChannel.ViewChannelActivity;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.DataHolder> {

    private Context mContext;
    private List<Channel> mChannels;

    public HomeAdapter(Context context, List<Channel> channels) {
        mContext = context;
        mChannels = channels;
    }

    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DataHolder(LayoutInflater
                .from(mContext)
                .inflate(R.layout.home_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DataHolder holder, int position) {
        Channel channel = mChannels.get(position);
        holder.title.setText(channel.getName());
        holder.description.setText(channel.getDescription());
        holder.timeLapse.setText(channel.getTime());

        if (channel.getChannelImage() != null) {
            Glide.with(mContext)
                    .load(channel.getChannelImage())
                    .apply(RequestOptions.circleCropTransform())
                    .into(holder.image);
        } else {
            holder.image.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_user_light));
        }
    }

    @Override
    public int getItemCount() {
        return mChannels == null ? 0 : mChannels.size();
    }

    class DataHolder extends RecyclerView.ViewHolder {

        private LinearLayout card;
        private TextView title;
        private TextView description;
        private ImageView image;
        private TextView timeLapse;

        public DataHolder(@NonNull View itemView) {
            super(itemView);

            card = itemView.findViewById(R.id.home_card);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            image = itemView.findViewById(R.id.logo);
            timeLapse = itemView.findViewById(R.id.timeLapse);

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Open description activity
                    Channel channel = mChannels.get(getAdapterPosition());
                    if (channel.getDestinationType() != null) {
                        if (channel.getDestinationType().equals("WEB")) {
                            String url = channel.getUrl();
                            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                            CustomTabsIntent customTabsIntent = builder.build();
                            customTabsIntent.launchUrl(mContext, Uri.parse(url));
                        } else if (channel.getDestinationType().equals("IMAGE")) {
                            // Open description activity
                            Intent intent = new Intent(mContext, ViewChannel2Activity.class);
                            mContext.startActivity(intent);
                        } else if (channel.getDestinationType().equals("TEXT")) {
                            Intent intent = new Intent(mContext, ViewChannelActivity.class);
                            mContext.startActivity(intent);
                        }
                    }

                }
            });
        }
    }
}
