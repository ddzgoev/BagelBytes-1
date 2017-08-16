package com.bagelbytes.libertyhacksmerge;

/**
 * Created by tkaram on 8/16/2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class ListActivity extends Activity implements AdapterView.OnItemClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final ListView listview = (ListView) findViewById(R.id.listview1);
        listview.setOnItemClickListener(this);
        final Button signout = (Button) findViewById(R.id.signout);
        final Button addPayment = (Button) findViewById(R.id.addpayment);

    }

    // This is used to determine which list item is being clicked
    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        // Send to specific edit view

    }

    public void onButtonClick(View v) {
        // Send to blank payment view
        Intent myIntent = new Intent(v.getContext(),AddPaymentsActivity.class);
        v.getContext().startActivity(myIntent);
    }

    public void onSignOutClick() {
        // Send to login view

    }
}