package com.bagelbytes.libertyhacksmerge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
        // Get the views by id
        EditText myEditText1 = (EditText) findViewById(R.id.editTextPass);
        EditText myEditText2 = (EditText) findViewById(R.id.editTextName);
        EditText myEditText3 = (EditText) findViewById(R.id.editTextUser);
        EditText myEditText4 = (EditText) findViewById(R.id.editTextLoan);
        Spinner paymentMethodSpinner = (Spinner) findViewById(R.id.spinnerPaymentMethod);
        CheckBox mycheckBoxAutoPay = (CheckBox) findViewById(R.id.checkBoxAutoPay);

        // Extract the values from the views
        String passInput = myEditText1.getText().toString();
        String nameInput = myEditText2.getText().toString();
        String userInput = myEditText3.getText().toString();
        String loanInput = myEditText4.getText().toString();
        boolean auto = mycheckBoxAutoPay.isPressed();
        String spinnerCurrentSelection = paymentMethodSpinner.getSelectedItem().toString();

//public Payment(Integer id, String name, String service, String date, Double pay, Integer auto)
        Payment p = new Payment(0, nameInput, loanInput, "8-16-17", 39.99, 1);

        db = new DBhandler(AddPaymentsActivity.this);
        db.addPayment(p);
        Toast.makeText(AddPaymentsActivity.this,"Payment Saved",Toast.LENGTH_SHORT).show();
        finish();
    }
}
