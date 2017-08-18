package com.bagelbytes.libertyhacksmerge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddPaymentsActivity extends AppCompatActivity{

    DBhandler db;
    //Make a payment object with the values
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payments);
        makeAllTextFieldsInvisible();

        Spinner paymentMethodSpinner = (Spinner) findViewById(R.id.spinnerPaymentMethod);
        Log.i("TAG", "On Create: "+paymentMethodSpinner.getSelectedItem().toString());

        paymentMethodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Log.i("TAG", "Worked: " + parent.getItemAtPosition(position).toString());
                if(parent.getItemAtPosition(position).toString().equals("PayPal")){
                    makeAllTextFieldsInvisible();
                    findViewById(R.id.editTextEmail).setVisibility(View.VISIBLE);
                    findViewById(R.id.editTextPassword).setVisibility(View.VISIBLE);

                }
                if(parent.getItemAtPosition(position).toString().equals("Credit Card")){
                    makeAllTextFieldsInvisible();
                    findViewById(R.id.editTextNumber).setVisibility(View.VISIBLE);
                    findViewById(R.id.editTextExpDate).setVisibility(View.VISIBLE);
                    findViewById(R.id.editTextSecurityCode).setVisibility(View.VISIBLE);
                }
                if(parent.getItemAtPosition(position).toString().equals("Bank Account")){
                    makeAllTextFieldsInvisible();
                    findViewById(R.id.editTextAccountName).setVisibility(View.VISIBLE);
                    findViewById(R.id.editTextRoutingNumber).setVisibility(View.VISIBLE);
                    findViewById(R.id.editTextAccountNumber).setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void makeAllTextFieldsInvisible() {
        findViewById(R.id.editTextEmail).setVisibility(View.GONE);
        findViewById(R.id.editTextPassword).setVisibility(View.GONE);
        findViewById(R.id.editTextNumber).setVisibility(View.GONE);
        findViewById(R.id.editTextExpDate).setVisibility(View.GONE);
        findViewById(R.id.editTextSecurityCode).setVisibility(View.GONE);
        findViewById(R.id.editTextAccountName).setVisibility(View.GONE);
        findViewById(R.id.editTextRoutingNumber).setVisibility(View.GONE);
        findViewById(R.id.editTextAccountNumber).setVisibility(View.GONE);
    }


    public void onCancelButtonClick(View v) {
        // Send to blank payment view
        finish();
    }

    public void onSaveButtonClick(View v) {
        // Get the views by id
        EditText myEditText1 = (EditText) findViewById(R.id.editTextPassword);
        EditText myEditText2 = (EditText) findViewById(R.id.editTextAccountName);
        EditText myEditText3 = (EditText) findViewById(R.id.editTextExpDate);
        EditText myEditText4 = (EditText) findViewById(R.id.editTextExpDate);
        Spinner paymentMethodSpinner = (Spinner) findViewById(R.id.spinnerPaymentMethod);
        CheckBox mycheckBoxAutoPay = (CheckBox) findViewById(R.id.checkBoxAutoPay);

        // Extract the values from the views
        String passInput = myEditText1.getText().toString();
        String nameInput = myEditText2.getText().toString();
        String userInput = myEditText3.getText().toString();
        String loanInput = myEditText4.getText().toString();
        boolean auto = mycheckBoxAutoPay.isPressed();
        int autoInt;
        if(auto){
            autoInt = 1;
        }else{
            autoInt=0;
        }
        String spinnerCurrentSelection = paymentMethodSpinner.getSelectedItem().toString();

//public Payment(Integer id, String name, String service, String date, Double pay, Integer auto)
        Payment p = new Payment(0, nameInput, loanInput, "8-16-17", 39.99, autoInt);

        db = new DBhandler(AddPaymentsActivity.this);
        db.addPayment(p);
        Toast.makeText(AddPaymentsActivity.this,"Payment Saved",Toast.LENGTH_SHORT).show();
        finish();
    }
}
