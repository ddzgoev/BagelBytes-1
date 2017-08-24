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
        EditText myEditText1 = (EditText) findViewById(R.id.editTextEmail);
        EditText myEditText2 = (EditText) findViewById(R.id.editTextPassword);
        EditText myEditText3 = (EditText) findViewById(R.id.editTextNumber);
        EditText myEditText4 = (EditText) findViewById(R.id.editTextExpDate);
        EditText myEditText5 = (EditText) findViewById(R.id.editTextSecurityCode);
        EditText myEditText6 = (EditText) findViewById(R.id.editTextAccountName);
        EditText myEditText7 = (EditText) findViewById(R.id.editTextAccountNumber);
        EditText myEditText8 = (EditText) findViewById(R.id.editTextRoutingNumber);

        Spinner paymentMethodSpinner = (Spinner) findViewById(R.id.spinnerPaymentMethod);
        CheckBox mycheckBoxAutoPay = (CheckBox) findViewById(R.id.checkBoxAutoPay);

        // Extract the values from the views
        String TextEmail = myEditText1.getText().toString();
        String TextPassword = myEditText2.getText().toString();
        String TextNumber = myEditText3.getText().toString();
        String TextExpDate = myEditText4.getText().toString();
        String TextSecurityCode = myEditText5.getText().toString();
        String TextAccountName = myEditText6.getText().toString();
        String TextAccountNumber = myEditText7.getText().toString();
        String TextRoutingNumber = myEditText8.getText().toString();

        boolean auto = mycheckBoxAutoPay.isPressed();
        int autoInt;
        if(auto){
            autoInt = 1;
        }else{
            autoInt=0;
        }
        String spinnerCurrentSelection = paymentMethodSpinner.getSelectedItem().toString();

//public PaymentMethod(Integer id, String type, String paypalEmail,
//        String paypalPassword, String bankAccountName, int bankRoutingNumber,
//        int bankAccountNumber, int creditcardNumber, String creditcardExpirationDate,
//        int creditcardSecurityCode)
        PaymentMethod p = null;
        if(spinnerCurrentSelection.equals("PayPal")) {
            p = new PaymentMethod(TextEmail, TextPassword);
        }
        if(spinnerCurrentSelection.equals("Credit Card")) {
            p = new PaymentMethod(Integer.parseInt(TextNumber), TextExpDate, Integer.parseInt(TextSecurityCode));
        }
        if(spinnerCurrentSelection.equals("Bank Account")) {
            p = new PaymentMethod(TextAccountName, Integer.parseInt(TextAccountNumber), Integer.parseInt(TextRoutingNumber));
        }

        db = new DBhandler(AddPaymentsActivity.this);
        db.addPaymentMethod(p);
        Toast.makeText(AddPaymentsActivity.this,"Payment method succesfully saved.",Toast.LENGTH_SHORT).show();
        finish();
    }
}
