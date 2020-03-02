package com.example.npcmanager.Activities;

import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.R;

public abstract class HomeButtonActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_home) {
            ActivityUtilities.loadMainActivity(this);
        }
        return true;
    }
}
