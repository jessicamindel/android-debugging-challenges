package com.codepath.debuggingchallenges.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.codepath.debuggingchallenges.R;

public class ChangeBackgroundActivity extends AppCompatActivity {

    private int oldColor = Color.BLUE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_background);
    }

    // FIXED: Method was never referenced, invalid name used in the layout file
    public boolean onGoClick(View view) {
        Log.i("CBActivity", "Hit go click");
        // FIXED: View did not have an ID in layout file
        View mainView = findViewById(android.R.id.content);
        mainView.setBackgroundColor(getNextColor());
        return false;
    }

    private int getNextColor() {
        int newColor = (oldColor == Color.BLUE) ? Color.RED : Color.BLUE;
        oldColor = newColor;
        return newColor;
    }
}
