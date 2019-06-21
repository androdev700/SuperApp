package com.oldmen.superapp.ui.activity.addChannel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.oldmen.superapp.R;
import com.oldmen.superapp.db.dao.ChannelDao;
import com.oldmen.superapp.db.handler.SuperDatabase;
import com.oldmen.superapp.db.model.Channel;

public class AddActivity extends AppCompatActivity {

    TextView settingsView, advancedSettingsView;
    EditText nameEt, descriptionEt;
    RadioGroup channelType;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        settingsView = findViewById(R.id.settings);
        advancedSettingsView = findViewById(R.id.settings_advanced);

        nameEt = findViewById(R.id.name);
        descriptionEt = findViewById(R.id.description);

        channelType = findViewById(R.id.radio_group);
        channelType.check(R.id.type_public);

        mContext = this;

        settingsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, SettingsActivity.class));
            }
        });

        advancedSettingsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, AdvancedSettingsActivity.class));
            }
        });

        CardView createChannel = findViewById(R.id.create_channel);
        createChannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createChannel();
            }
        });
    }

    private void createChannel() {
        String name = nameEt.getText().toString();
        String description = descriptionEt.getText().toString();
        String type = "";
        if (channelType.getCheckedRadioButtonId() == R.id.type_public) {
            type = "public";
        } else {
            type = "private";
        }

        if (name.equals("")) {
            nameEt.setError("Please provide a name to the channel");
            return;
        } else if (description.equals("")) {
            descriptionEt.setError("Please provide a description to the channel");
            return;
        }

        Channel channel = new Channel(Integer.toString((int) (Math.random() * 10000)), name, type, description, null);

        ChannelDao channelDao = SuperDatabase.getInstance(this).channelDao();
        channelDao.insert(channel);
        Toast.makeText(this, "Creating channel", Toast.LENGTH_SHORT).show();
        finish();
    }
}
