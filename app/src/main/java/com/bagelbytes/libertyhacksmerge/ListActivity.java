package com.bagelbytes.libertyhacksmerge;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ListActivity extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Connect to SQLite DB
        DBhandler db = new DBhandler(ListActivity.this);

        // Create dummy payments for testing -- should be removed TODO
        Payment pay1 = new Payment(1, "Car Insurance", "Liberty Mutual", "8/24", 104.50, 0);
        Payment pay2 = new Payment(2, "Utility Bill", "Solar Co", "9/14", 60.34, 1);
        db.addPayment(pay1);
        db.addPayment(pay2);

        // Create List

        ListView listview = (ListView) findViewById(R.id.listview);

        // Get List
        List<Payment> paymentArrayList = db.getAllPayments();

        // Sort List
        Collections.sort(paymentArrayList, comparator);

        // Add List
        PaymentAdapter customAdapter = new PaymentAdapter(this, paymentArrayList);
        listview.setAdapter(customAdapter);

        // Manage List Item Clicks
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                // Called when a list item is clicked

             //   TextView name = (TextView) adapter.getChildAt(0);
             //   TextView service = (TextView) adapter.getChildAt(1);

             //   String paymentName = name.getText().toString();
             //   String paymentService = service.getText().toString();

                // Send the user to a new activity to edit their payment
                Intent myIntent = new Intent(v.getContext(),Utility_Login.class);
                startActivity(myIntent);

            }
        });

    }

    // Custom Comparator used to sort Payment objects by Date (MM/dd format)
    Comparator<Payment> comparator = new Comparator<Payment>() {
        final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd", Locale.getDefault());

        @Override
        public int compare(Payment payment1, Payment payment2) {
            Date p1 = new Date();
            Date p2 = new Date();

            try {
                p1 = sdf.parse(payment1.getDate());
                p2 = sdf.parse(payment2.getDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (p1.before(p2)) {
                return -1;
            } else {
                return 1;
            }
        }

    };

    // Called when the pay button of a list item is clicked.
    public void onPayClick(View v) {
        // Get the row the clicked button is in
        RelativeLayout vwParentRow = (RelativeLayout) v.getParent();

        TextView name = (TextView) vwParentRow.getChildAt(0);
        TextView service = (TextView) vwParentRow.getChildAt(1);
        TextView pay = (TextView) vwParentRow.getChildAt(3);

        // Create payment confirmation dialog
        final NiftyDialogBuilder dialogBuilder=NiftyDialogBuilder.getInstance(this);
        dialogBuilder
                .withTitle(name.getText())
                .withTitleColor("#FFFFFF")
                .withDividerColor("#4775A0")
                .withMessage("Pay " + service.getText() + " " + pay.getText() + "?")
                .withMessageColor("#FFFFFF")
                .withDialogColor("#002663")
                .withDuration(300)
                .withEffect(Effectstype.Fadein)
                .withButton1Text("Pay")
                .withButton2Text("Cancel")
                .isCancelableOnTouchOutside(true)
                .setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Call payment function here - TODO
                        // Create payment success dialog
                        final NiftyDialogBuilder successDialog=NiftyDialogBuilder.getInstance(dialogBuilder.getContext());
                        successDialog
                                .withTitle("Success!")
                                .withTitleColor("#FFFFFF")
                                .withDividerColor("#4775A0")
                                .withMessage("Your payment has been sent.")
                                .withMessageColor("#FFFFFF")
                                .withDialogColor("#002663")
                                .withDuration(300)
                                .withEffect(Effectstype.Fadein)
                                .withButton1Text("Done")
                                .isCancelableOnTouchOutside(true)
                                .setButton1Click(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        successDialog.dismiss();
                                    }
                                });
                                dialogBuilder.dismiss();
                                successDialog.show();
                    }
                })
                .setButton2Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogBuilder.dismiss();
                    }
                })
                .show();

    }

    // Called when the "Add New Payment" button is clicked
    public void onButtonClick(View v) {
        Intent myIntent = new Intent(v.getContext(),Utility_Login.class);
        v.getContext().startActivity(myIntent);

    }

    // Called when the "Sign Out" button is clicked
    public void onSignOutClick(View v) {
        // Send to login view
        SharedPreferences preferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor= preferences.edit();
        editor.clear();
        editor.apply();
        finish();

    }

}