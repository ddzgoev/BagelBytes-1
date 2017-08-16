package com.bagelbytes.libertyhacksmerge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddPaymentsActivity extends AppCompatActivity {

    DBhandler db;
    //Make a payment object with the values

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payments);
    }
    public void onCancelButtonClick(View v) {
        // Send to blank payment view
        finish();
    }

    public void onSaveButtonClick(View v) {
        // Extract the values from the editText views
        EditText myEditText1 = (EditText) findViewById(R.id.editTextPass);
        EditText myEditText2 = (EditText) findViewById(R.id.editTextName);
        EditText myEditText3 = (EditText) findViewById(R.id.editTextUser);
        EditText myEditText4 = (EditText) findViewById(R.id.editTextLoan);
        String message = myEditText1.getText().toString();
        String message1 = myEditText2.getText().toString();
        String message2 = myEditText3.getText().toString();
        String message3 = myEditText4.getText().toString();



        Payment p = new Payment(message1, message3, "8-16-17", 39.99, 1 , 0);
        db=new DBhandler(AddPaymentsActivity.this);
        db.addPayment(p);
        finish();
    }
}
