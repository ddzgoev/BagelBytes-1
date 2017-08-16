package com.bagelbytes.libertyhacksmerge;

/**
 * Created by tkaram on 8/16/2017.
 */


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class ListItemDetail extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listitem);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);

        String[] paymentNames = getResources().getStringArray(R.array.names);
        String[] serviceNames = getResources().getStringArray(R.array.services);

        TextView myTextView = (TextView) findViewById(R.id.name);
        myTextView.setText(paymentNames[position]);
        myTextView.setText(serviceNames[position]);


    }

}