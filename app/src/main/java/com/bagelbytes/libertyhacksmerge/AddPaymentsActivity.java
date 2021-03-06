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

import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;

public class AddPaymentsActivity extends AppCompatActivity implements Serializable{

    DBhandler db;
    Payment thePayment;
    PaymentMethod thePaymentMethod;
    String previousActivitySpinString;

    Payment original = new Payment();
    boolean update = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payments);

        //Get payment object from previous activity with key "payment" from the intent
        thePayment = (Payment) getIntent().getSerializableExtra("payment");

        previousActivitySpinString = (String) getIntent().getSerializableExtra("spinSelection").toString();
        if (thePayment.getPay() != null){
            update = true;
            original = (Payment) getIntent().getSerializableExtra("original");
        }
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

                    findViewById(R.id.button7).setVisibility(View.VISIBLE);

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

        findViewById(R.id.button7).setVisibility(View.GONE);
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
                    "","", "","", "", "");
            thePaymentMethod = pm;
        }
        if(typeSelection.equals("Credit Card")) {
            PaymentMethod pm = new PaymentMethod(typeSelection, "", "", "","", "",
                    numberInput, expDateInput, securityCodeInput);
            thePaymentMethod = pm;
        }
        if(typeSelection.equals("Bank Account")) {
            PaymentMethod pm = new PaymentMethod(typeSelection, "", "",
                    accountNameInput, accountRoutingNumberInput, accountNumberInput,
                    "", "", "");
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
        PaymentMethod pm = new PaymentMethod("PayPal", "sam@test.com", "abc",
                "","", "","", "","");

        db.addPaymentMethod(pm);
        int id = db.generatePaymentMethodID(pm);
        thePayment.setPaymentMethod(id);
        thePayment.setId(0);
        thePayment.setDate("11/7");
        thePayment.setPay(54.79);
        thePayment.setAuto(1);

        if(previousActivitySpinString.equals("Atlantic City Electric")) {
            thePayment.setPaymentMethod(id);
            thePayment.setId(0);
            thePayment.setDate("9/7");
            thePayment.setPay(240.60);
            thePayment.setAuto(1);
         }
       if(previousActivitySpinString.equals("Consolidated Edison New York")) {
                thePayment.setPaymentMethod(id);
                thePayment.setId(0);
                thePayment.setDate("10/8");
                thePayment.setPay(37.87);
               thePayment.setAuto(1);
            }
       if(previousActivitySpinString.equals("Eversource Energy")) {
                thePayment.setPaymentMethod(id);
                thePayment.setId(0);
                thePayment.setDate("9/36");
                thePayment.setPay(322.31);
                thePayment.setAuto(1);
            }
        if(previousActivitySpinString.equals("Hawaii Electric")) {
                thePayment.setPaymentMethod(id);
            thePayment.setId(0);
                thePayment.setDate("9/23");
                thePayment.setPay(209.63);
                thePayment.setAuto(1);
            }
        if(previousActivitySpinString.equals("Jersey Central Power and Light")) {
                thePayment.setPaymentMethod(id);
                thePayment.setId(0);
                thePayment.setDate("9/7");
                thePayment.setPay(177.68);
                thePayment.setAuto(1);
            }
        if(previousActivitySpinString.equals("Liberty Mutual Insurance")) {
                thePayment.setPaymentMethod(id);
                thePayment.setId(0);
                thePayment.setDate("11/25");
                thePayment.setPay(197.39);
                thePayment.setAuto(1);
            }
        if(previousActivitySpinString.equals("Baltimore Gas & Electric")) {
                thePayment.setPaymentMethod(id);
                thePayment.setId(0);
                thePayment.setDate("9/7");
                thePayment.setPay(289.49);
                thePayment.setAuto(1);
            }

        //add the payment to the database
        if (update){
            Log.d("TEST:", "UPDATE");
            Log.d("TEST:", thePayment.toString());
            db.updatePayment(thePayment,original);
            update = false;
        }else {
            Log.d("TEST:", "NEW");
            db.addPayment(thePayment);
        }



//display message that payment was succesfully saved
        Toast.makeText(AddPaymentsActivity.this,"Bill Saved Successfully.",Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(v.getContext(),ListActivity.class);
        startActivity(myIntent);
    }
//end of save button definition method
public void onScanPress(View v) {
    Intent scanIntent = new Intent(this, CardIOActivity.class);

    // customize these values to suit your needs.
    scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true); // default: false
    scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, false); // default: false
    scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false); // default: false

    // MY_SCAN_REQUEST_CODE is arbitrary and is only used within this activity.
    startActivityForResult(scanIntent, 5343);
}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EditText myEditText3 = (EditText) findViewById(R.id.editTextNumber);
        EditText myEditText4 = (EditText) findViewById(R.id.editTextExpDate);
        EditText myEditText5 = (EditText) findViewById(R.id.editTextSecurityCode);

        if (requestCode == 5343) {
            String cardNumber = "";
            String expDate = "";
            String cvv = "";
            if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
                CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);

                // Never log a raw card number. Avoid displaying it, but if necessary use getFormattedCardNumber(
                cardNumber = scanResult.cardNumber;
                // Do something with the raw number, e.g.:
                // myService.setCardNumber( scanResult.cardNumber );

                if (scanResult.isExpiryValid()) {
                    expDate = scanResult.expiryMonth + "/" + scanResult.expiryYear;
                }

                if (scanResult.cvv != null) {
                    // Never log or display a CVV
                    cvv += "CVV has " + scanResult.cvv.length() + " digits.\n";
                }


            }
            else {
                cardNumber = "Scan was canceled.";

            }
            // do something with resultDisplayStr, maybe display it in a textView
            // resultTextView.setText(resultDisplayStr);
            myEditText3.setText(cardNumber);
            myEditText4.setText(expDate);
            //myEditText5.setText("");
        }
        // else handle other activity results
    }

}