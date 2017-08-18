package com.bagelbytes.libertyhacksmerge;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ListActivity extends Activity implements AdapterView.OnItemClickListener {


    DateFormat df = new SimpleDateFormat("MM/dd", Locale.getDefault());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Connect to SQLite DB
        DBhandler db = new DBhandler(ListActivity.this);

        // Create dummy payments for testing -- should be removed
        Payment pay1 = new Payment(1, "Car Insurance", "Liberty Mutual", "8/22", 104.50, 0);
        Payment pay2 = new Payment(2, "Utility Bill", "Solar Co", "4/23", 60.34, 1);
        db.addPayment(pay1);
        db.addPayment(pay2);

        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setOnItemClickListener(this);

        // Populate listview
        List<Payment> paymentArrayList = db.getAllPayments();
        Collections.sort(paymentArrayList, new Comparator<Payment>() {
            @Override
            public int compare(Payment payment, Payment t1) {
                Date payment1 = new Date();
                Date payment2 = new Date();
                try {
                    payment1 = df.parse(payment.getDate());
                    payment2 = df.parse(t1.getDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (payment1.before(payment2)) {
                    return -1;
                } else if (payment1.equals(payment2)) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        PaymentAdapter adapter = new PaymentAdapter(this, paymentArrayList);
        listview.setAdapter(adapter);
        listview.setItemsCanFocus(true);

    }


    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        // This is used to determine which list item is being clicked
        // Send to specific edit view

    }

    public void onButtonClick(View v) {
        Intent myIntent = new Intent(v.getContext(),Utility_Login.class);
        v.getContext().startActivity(myIntent);
    }

    public void onSignOutClick(View v) {
        // Send to login view
        SharedPreferences preferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor= preferences.edit();
        editor.clear();
        editor.apply();
        finish();
    }

    public void pay(View v) {
        // Get the row the clicked button is in
        RelativeLayout row = (RelativeLayout) v.getParent();

        TextView payTV = (TextView) row.findViewById(R.id.pay);
        String minPay = payTV.getText().toString();
        TextView serviceTV = (TextView) row.findViewById(R.id.service);
        String service = serviceTV.getText().toString();

    }
}