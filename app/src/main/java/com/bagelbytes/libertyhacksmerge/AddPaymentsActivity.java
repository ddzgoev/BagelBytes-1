package com.bagelbytes.libertyhacksmerge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AddPaymentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payments);
    }
    public void onCancelButtonClick(View v) {
        // Send to blank payment view
        finish();
    }
}
