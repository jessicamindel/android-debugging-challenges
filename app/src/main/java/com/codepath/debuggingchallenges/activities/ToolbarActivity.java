package com.codepath.debuggingchallenges.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
// FIXED: Changed import to proper import for AppCompat
import android.support.v7.widget.Toolbar;

import com.codepath.debuggingchallenges.R;

public class ToolbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);

        // Find the toolbar view inside the activity layout
        // FIXED: Redundant cast
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("My toolbar!");

        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        // FIXED: Switched to AppCompat method rather than general Toolbar implementation
        setSupportActionBar(toolbar);

        TextView tvDescription = (TextView) findViewById(R.id.tvDescription);
        tvDescription.setText(R.string.hello);
    }
}
