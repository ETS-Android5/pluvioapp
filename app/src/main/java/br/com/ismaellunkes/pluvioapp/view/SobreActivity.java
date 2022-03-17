package br.com.ismaellunkes.pluvioapp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import br.com.ismaellunkes.pluvioapp.R;

public class SobreActivity extends AppCompatActivity {

    public static void sobre(AppCompatActivity activity){

        Intent intent = new Intent(activity, SobreActivity.class);

        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    private void cancelar() {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }
    @Override
    public void onBackPressed() {
        cancelar();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            default:
                cancelar();
                return true;
        }
    }
}