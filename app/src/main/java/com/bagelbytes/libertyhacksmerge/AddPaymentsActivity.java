package com.bagelbytes.libertyhacksmerge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class AddPaymentsActivity extends AppCompatActivity implements Serializable{

    DBhandler db;
    Payment thePayment;
    PaymentMethod thePaymentMethod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payments);

        //Get payment object from previous activity with key "payment" from the intent
        thePayment = (Payment) getIntent().getSerializableExtra("payment");


        //make stuff invisible until user actions are taken to make them visible
        makeAllTextFieldsInvisible();

        Spinner selectPaymentMethodSpinner = (Spinner) findViewById(R.id.spinnerSelectPaymentMethod);

        selectPaymentMethodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).toString().equals("ADD NEW PAYMENT METHOD")){
                    findViewById(R.id.spinnerAddPaymentMethod).setVisibility(View.VISIBLE);
                    findViewById(R.id.textViewAddNew).setVisibility(View.VISIBLE);
                }else{
                    findViewById(R.id.spinnerAddPaymentMethod).setVisibility(View.GONE);
                    findViewById(R.id.textViewAddNew).setVisibility(View.GONE);
                    makeAllTextFieldsInvisible();
                }
            }
            //just to complete the interface
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}});

        Spinner addPaymentMethodSpinner = (Spinner) findViewById(R.id.spinnerAddPaymentMethod);

        addPaymentMethodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Log.i("TAG", "Worked: " + parent.getItemAtPosition(position).toString());
                if(parent.getItemAtPosition(position).toString().equals("PayPal")){
                    makeAllTextFieldsInvisible();
                    findViewById(R.id.editTextEmail).setVisibility(View.VISIBLE);
                    findViewById(R.id.layTextEmail).setVisibility(View.VISIBLE);

                    findViewById(R.id.editTextPassword).setVisibility(View.VISIBLE);
                    findViewById(R.id.layTextPassword).setVisibility(View.VISIBLE);


                }
                if(parent.getItemAtPosition(position).toString().equals("Credit Card")){
                    makeAllTextFieldsInvisible();
                    findViewById(R.id.editTextNumber).setVisibility(View.VISIBLE);
                    findViewById(R.id.layTextNumber).setVisibility(View.VISIBLE);

                    findViewById(R.id.editTextExpDate).setVisibility(View.VISIBLE);
                    findViewById(R.id.layTextExpDate).setVisibility(View.VISIBLE);

                    findViewById(R.id.editTextSecurityCode).setVisibility(View.VISIBLE);
                    findViewById(R.id.layTextSecurityCode).setVisibility(View.VISIBLE);

                }
                if(parent.getItemAtPosition(position).toString().equals("Bank Account")){
                    makeAllTextFieldsInvisible();
                    findViewById(R.id.editTextAccountName).setVisibility(View.VISIBLE);
                    findViewById(R.id.layTextAccountName).setVisibility(View.VISIBLE);

                    findViewById(R.id.editTextRoutingNumber).setVisibility(View.VISIBLE);
                    findViewById(R.id.layTextRoutingNumber).setVisibility(View.VISIBLE);

                    findViewById(R.id.editTextAccountNumber).setVisibility(View.VISIBLE);
                    findViewById(R.id.layTextAccountNumber).setVisibility(View.VISIBLE);

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}});

    }

    private void makeAllTextFieldsInvisible() {

        findViewById(R.id.editTextEmail).setVisibility(View.GONE);
        findViewById(R.id.layTextEmail).setVisibility(View.GONE);

        findViewById(R.id.editTextPassword).setVisibility(View.GONE);
        findViewById(R.id.layTextPassword).setVisibility(View.GONE);

        findViewById(R.id.editTextNumber).setVisibility(View.GONE);
        findViewById(R.id.layTextNumber).setVisibility(View.GONE);

        findViewById(R.id.editTextExpDate).setVisibility(View.GONE);
        findViewById(R.id.layTextExpDate).setVisibility(View.GONE);

        findViewById(R.id.editTextSecurityCode).setVisibility(View.GONE);
        findViewById(R.id.layTextSecurityCode).setVisibility(View.GONE);

        findViewById(R.id.editTextAccountName).setVisibility(View.GONE);
        findViewById(R.id.layTextAccountName).setVisibility(View.GONE);

        findViewById(R.id.editTextRoutingNumber).setVisibility(View.GONE);
        findViewById(R.id.layTextRoutingNumber).setVisibility(View.GONE);

        findViewById(R.id.editTextAccountNumber).setVisibility(View.GONE);
        findViewById(R.id.layTextAccountNumber).setVisibility(View.GONE);

    }

//Defines what happens when the cancel button is clicked
    public void onCancelButtonClick(View v)
    {
// Send to blank payment view
        finish();
    }

//Defines what happens when the save button is clicked
    public void onSaveButtonClick(View v) {
        // Get the views for the text input fields by id
        EditText myEditText1 = (EditText) findViewById(R.id.editTextEmail);
        EditText myEditText2 = (EditText) findViewById(R.id.editTextPassword);
        EditText myEditText3 = (EditText) findViewById(R.id.editTextNumber);
        EditText myEditText4 = (EditText) findViewById(R.id.editTextExpDate);
        EditText myEditText5 = (EditText) findViewById(R.id.editTextSecurityCode);
        EditText myEditText6 = (EditText) findViewById(R.id.editTextAccountName);
        EditText myEditText7 = (EditText) findViewById(R.id.editTextAccountNumber);
        EditText myEditText8  = (EditText) findViewById(R.id.editTextRoutingNumber);

//select the spinners by id
        Spinner selectPaymentMethodSpinner = (Spinner) findViewById(R.id.spinnerSelectPaymentMethod);
        Spinner addPaymentMethodSpinner = (Spinner) findViewById(R.id.spinnerAddPaymentMethod);

//extract the current string value of the add payment method spinner
        String typeSelection = addPaymentMethodSpinner.getSelectedItem().toString();
// Extract the values from the views
        String emailInput = myEditText1.getText().toString();
        String passInput = myEditText2.getText().toString();
        String numberInput = myEditText3.getText().toString();
        String expDateInput = myEditText4.getText().toString();
        String securityCodeInput = myEditText5.getText().toString();
        String accountNameInput = myEditText6.getText().toString();
        String accountNumberInput = myEditText7.getText().toString();
        String accountRoutingNumberInput = myEditText8.getText().toString();



//        public PaymentMethod(String type, String paypalEmail, String paypalPassword,
//        String bankAccountName, int bankRoutingNumber, int bankAccountNumber,
//        int creditcardNumber, String creditcardExpirationDate,
//        int creditcardSecurityCode)

        db = new DBhandler(AddPaymentsActivity.this);

        if(typeSelection.equals("PayPal")) {
            PaymentMethod pm = new PaymentMethod(typeSelection, emailInput, passInput,
                    "",-1, -1,-1, "", -1);
            thePaymentMethod = pm;
        }
        if(typeSelection.equals("Credit Card")) {
            PaymentMethod pm = new PaymentMethod(typeSelection, "", "", "",-1, -1,
                    Integer.parseInt(numberInput), expDateInput, Integer.parseInt(securityCodeInput));
            thePaymentMethod = pm;
        }
        if(typeSelection.equals("Bank Account")) {
            PaymentMethod pm = new PaymentMethod(typeSelection, "", "",
                    accountNameInput, Integer.parseInt(accountRoutingNumberInput), Integer.parseInt(accountNumberInput),
                    -1, "", -1);
            thePaymentMethod = pm;
        }
//If the option to select a new payment is selected:
        if(selectPaymentMethodSpinner.equals("ADD NEW PAYMENT METHOD"))
        {
//add the newly created payment method to the paymentMethod table
            db.addPaymentMethod(thePaymentMethod);
//set the integer value PaymentMethod to be the ID of the payment method object
            thePayment.setPaymentMethod(thePaymentMethod.getId());
        }

//SET THE VALUES OF THE DUMMY PAYMENT LIST ITEM TO BE DISPLAYED FOR OUR DEMO
       // if(thePayment.getService().equals("Atlanta City Electric")) {
            thePayment.setId(0);
            thePayment.setName("Electric Bill");
            thePayment.setService("Atlantic City Electric");
            thePayment.setDate("9/7/2017");
            thePayment.setPay(245.64);
            thePayment.setAuto(1);
            thePayment.setAccountHolder("Manny");
            thePayment.setZip(28780);
            thePayment.setAccountNumber("1234567");
        //}

//add the payment to the database
        db.addPayment(thePayment);


//        private Integer id;
//        private String name;
//        private  String service; //provider
//        private  String date;
//        private  Double pay;
//        private Integer auto;

//display message that payment was succesfully saved
        Toast.makeText(AddPaymentsActivity.this,"Bill Saved Successfully.",Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(v.getContext(),ListActivity.class);
        startActivity(myIntent);
    }
//end of save button definition method


}