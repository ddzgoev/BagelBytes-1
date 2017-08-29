package com.bagelbytes.libertyhacksmerge;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Utility_Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility);

        Button submit = (Button) findViewById(R.id.btnSubmit);
        Button cancel = (Button) findViewById(R.id.btnCancel);
        final Spinner spnProvider = (Spinner) findViewById(R.id.spnProviders);
        final EditText edtBillName = (EditText) findViewById(R.id.edtBillName);
        final EditText edtAccountNumber = (EditText) findViewById(R.id.edtAccountNumber);
        final EditText edtAccountHolder = (EditText) findViewById(R.id.edtAccountHolder);
        final EditText edtAccountZIP = (EditText) findViewById(R.id.edtAccountZIP);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.provider_list,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spnProvider.setAdapter(staticAdapter);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            //TODO - make a check to see if fields are filled
            Payment p = new Payment();
            //must include
            p.setZip(Integer.parseInt(edtAccountZIP.getText().toString()));
            p.setAccountNumber(edtAccountNumber.getText().toString());
            p.setName(edtBillName.getText().toString());
            p.setAccountHolder(edtAccountHolder.getText().toString());
            p.setService(spnProvider.getSelectedItem().toString());
            Intent myIntent = new Intent(v.getContext(),AddPaymentsActivity.class);
            myIntent.putExtra("payment", p);
            v.getContext().startActivity(myIntent);

            }
        });



    }



//    public void firstStep(String username, String password){
//        String url ="https://utilityapi.com/api/v2/forms";
//        JSONObject params = new JSONObject();
//        // Request a string response from the provided URL.
//        JsonObjectRequest firstRequest = new JsonObjectRequest(Request.Method.POST, url, params,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            String uid = response.getString("uid");
//                            System.out.println("TWO");
//                            secondStep(uid);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//            }
//        }){
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String>  params = new HashMap<String, String>();
//                params.put("Authorization", "Bearer 1e010b87252f42b481a92a874d553867");
//                return params;
//            }
//        };
//        Volley.newRequestQueue(getApplicationContext()).add(firstRequest);
//    }
//
//    public void secondStep(String uid) {
//        StringBuilder url = new StringBuilder("https://utilityapi.com/api/v2/forms/");
//        url.append(uid);//now original string is changed
//        url.append("/test-submit");
//
//        JSONObject params = new JSONObject();
//        try {
//            params.put("utility", "DEMO");
//            params.put("scenario", "residential");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        JsonObjectRequest secondRequest = new JsonObjectRequest(Request.Method.POST,url.toString(),params,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            final String referral = response.getString("referral");
//                            System.out.println("THREE");
//                            thirdStep(referral);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                    }
//                }){
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String>  params = new HashMap<String, String>();
//                params.put("Authorization", "Bearer 1e010b87252f42b481a92a874d553867");
//                return params;
//            }
//        };
//
//        Volley.newRequestQueue(getApplicationContext()).add(secondRequest);
//    }
//
//
//    public void thirdStep(final String referral) {
//
//        StringBuilder url = new StringBuilder("https://utilityapi.com/api/v2/authorizations?referrals=");
//        url.append(referral);//now original string is changed
//        url.append("&include=meters");
//
//        JSONObject params = new JSONObject();
//
//        final JsonObjectRequest thirdRequest = new JsonObjectRequest(Request.Method.GET,url.toString(),params,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            JSONArray referrals = response.getJSONArray("authorizations");
//                            for(int i = 0; i < referrals.length(); i++){
//                                JSONObject auth = (JSONObject)referrals.get(i);
//                                JSONObject meters = auth.getJSONObject("meters");
//                                JSONArray metersArray = meters.getJSONArray("meters");
//                                for(i = 0; i < metersArray.length(); i++) {
//                                    JSONObject meter = (JSONObject)metersArray.get(i);
//                                    if (meter.getString("status").equals("pending")) {
//                                        System.out.println("AGAIN");
//                                        thirdStep(referral);
//                                    } else {
//                                        String uid = meter.getString("uid");
//                                        System.out.println("DONE - " + uid);
//                                        System.out.println("FOUR");
//                                        fourthStep(uid);
//                                    }
//                                }
//
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                    }
//                }){
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String>  params = new HashMap<String, String>();
//                params.put("Authorization", "Bearer 1e010b87252f42b481a92a874d553867");
//                return params;
//            }
//        };
//
//        //TODO - IMPROVE THIS
//        new CountDownTimer(3000, 1000) {
//            public void onTick(long millisUntilFinished) {
//                //do nothing
//            }
//            public void onFinish() {
//                Volley.newRequestQueue(getApplicationContext()).add(thirdRequest);
//            }
//        }.start();
//    }
//
//    public void fourthStep(final String uid) {
//        String url = "https://utilityapi.com/api/v2/meters/historical-collection";
//
//        JSONObject params = new JSONObject();
//        JSONArray j = new JSONArray();
//        j.put(uid);
//        try {
//            params.put("meters",j);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        //takes a while - todo look into
//        JsonObjectRequest fourthRequest = new JsonObjectRequest(Request.Method.POST,url,params,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            boolean success = response.getBoolean("success");
//                            System.out.println("WAS IT GOOD?: " +  success);
//                            if (success){
//                                System.out.println("FOUR DONE");
//                                fifthStep(uid);
//                            }else{
//                                Toast.makeText(Utility_Login.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                    }
//                }){
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String>  params = new HashMap<String, String>();
//                params.put("Authorization", "Bearer 1e010b87252f42b481a92a874d553867");
//                return params;
//            }
//        };
//
//        Volley.newRequestQueue(getApplicationContext()).add(fourthRequest);
//
//    }
//
//
//    public void fifthStep(final String uid) {
//        StringBuilder url = new StringBuilder("https://utilityapi.com/api/v2/meters/");
//        url.append(uid);
//
//        JSONObject params = new JSONObject();
//
//        final JsonObjectRequest fifthRequest = new JsonObjectRequest(Request.Method.GET,url.toString(),params,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            System.out.println("Status: " +  response.getString("status"));
//                            String status = response.getString("status");
//                            if(status.equals("pending")){
//                                fifthStep(uid);
//                            }else{
//                                String billCount = response.getString("bill_count");
//                                System.out.println("BILL COUNT: " + billCount);
//                                System.out.println("FIVE DONE");
//                                sixthStep(uid);
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                    }
//                }){
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String>  params = new HashMap<String, String>();
//                params.put("Authorization", "Bearer 1e010b87252f42b481a92a874d553867");
//                return params;
//            }
//        };
//        new CountDownTimer(3000, 1000) {
//            public void onTick(long millisUntilFinished) {
//                //do nothing
//            }
//            public void onFinish() {
//                Volley.newRequestQueue(getApplicationContext()).add(fifthRequest);
//            }
//        }.start();
//
//    }
//
//    public void sixthStep(final String uid) {
//        StringBuilder url = new StringBuilder("https://utilityapi.com/api/v2/bills?meters=");
//        url.append(uid);
//
//        JSONObject params = new JSONObject();
//
//        final JsonObjectRequest fifthRequest = new JsonObjectRequest(Request.Method.GET,url.toString(),params,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        System.out.println("Response: " +  response);
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                    }
//                }){
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String>  params = new HashMap<String, String>();
//                params.put("Authorization", "Bearer 1e010b87252f42b481a92a874d553867");
//                return params;
//            }
//        };
//        new CountDownTimer(3000, 1000) {
//            public void onTick(long millisUntilFinished) {
//                //do nothing
//            }
//            public void onFinish() {
//                Volley.newRequestQueue(getApplicationContext()).add(fifthRequest);
//            }
//        }.start();
//
//    }





}




