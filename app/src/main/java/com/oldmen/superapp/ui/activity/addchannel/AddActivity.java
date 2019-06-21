package com.oldmen.superapp.ui.activity.addchannel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.oldmen.superapp.R;

public class AddActivity extends AppCompatActivity {

    TextView settingsView, advancedSettingsView;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        settingsView = findViewById(R.id.settings);
        advancedSettingsView = findViewById(R.id.settings_advanced);

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
    }
}
