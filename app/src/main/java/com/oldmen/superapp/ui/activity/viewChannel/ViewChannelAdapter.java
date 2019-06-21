package com.oldmen.superapp.ui.activity.viewChannel;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oldmen.superapp.R;
import com.oldmen.superapp.Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ViewChannelAdapter extends RecyclerView.Adapter<ViewChannelAdapter.DataHolder> {

    private final String TAG = ViewChannelAdapter.class.getSimpleName();
    private JSONArray messages;

    public ViewChannelAdapter(){
        try {
            messages = new JSONArray(Data.UPDATE_STRING);
        } catch (JSONException e) {
            Log.e(TAG, "ViewChannelAdapter: Error in creating json");
        }
    }

    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DataHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DataHolder holder, int position) {
        try {
            JSONObject json = messages.getJSONObject(position);
            holder.messageBox.setText(json.getString("message"));
            holder.time.setText(json.getString("time"));
        } catch (JSONException e) {

        }
    }

    @Override
    public int getItemCount() {
        return messages == null ? 0 : messages.length();
    }

    class DataHolder extends RecyclerView.ViewHolder {

        private TextView messageBox;
        private TextView time;

        public DataHolder(@NonNull View itemView) {
            super(itemView);

            messageBox = itemView.findViewById(R.id.message_box);
            time = itemView.findViewById(R.id.message_time);
        }
    }
}
