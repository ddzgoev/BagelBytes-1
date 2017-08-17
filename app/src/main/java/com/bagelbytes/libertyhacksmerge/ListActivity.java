package com.bagelbytes.libertyhacksmerge;

/**
 * Created by tkaram on 8/16/2017.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends Activity implements AdapterView.OnItemClickListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Connect to SQLite DB
        DBhandler db = new DBhandler(ListActivity.this);

        // Create dummy payments for testing -- should be removed
        Payment pay1 = new Payment(1, "Car Insurance", "Liberty Mutual", "8/27", 104.50, 0);
        Payment pay2 = new Payment(2, "Utility Bill", "Solar Co", "9/14", 60.34, 1);
        db.addPayment(pay1);
        db.addPayment(pay2);

        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setOnItemClickListener(this);

        // Populate listview
        List<Payment> paymentArrayList = db.getAllPayments();
        PaymentAdapter adapter = new PaymentAdapter(this, paymentArrayList);
        listview.setAdapter(adapter);

    }

    // This is used to determine which list item is being clicked
    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        // Send to specific edit view

    }

    public void onButtonClick(View v) {

        AddPaymentDialog f = new AddPaymentDialog();
        f.show(getFragmentManager(), "dialog");
        //Intent myIntent = new Intent(v.getContext(),Utility_Login.class);
        //v.getContext().startActivity(myIntent);
    }

    public void onSignOutClick(View v) {
        // Send to login view
        SharedPreferences preferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor= preferences.edit();
        editor.clear();
        editor.commit();
        finish();


//        Intent myIntent = new Intent(v.getContext(), LoginActivity.class);
//        v.getContext().startActivity(myIntent);
    }


}