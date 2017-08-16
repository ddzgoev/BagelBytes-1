package com.bagelbytes.libertyhacksmerge;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Utility_Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility_login);

        final EditText pass = (EditText) findViewById(R.id.edtPassword);
        final EditText user = (EditText) findViewById(R.id.edtUsername);
        final TextView test = (TextView) findViewById(R.id.txtUsername);
        Button submit = (Button) findViewById(R.id.Button);

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String password = pass.getText().toString();
                String username = user.getText().toString();
                firstStep(username, password);
            }
        });
    }



    public void firstStep(String username, String password){
        String url ="https://utilityapi.com/api/v2/forms";
        JSONObject params = new JSONObject();
        // Request a string response from the provided URL.
        JsonObjectRequest firstRequest = new JsonObjectRequest(Request.Method.POST, url, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        try {
                            String uid = response.getString("uid");
                            secondStep(uid);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Authorization", "Bearer 1e010b87252f42b481a92a874d553867");
                return params;
            }
        };
        Volley.newRequestQueue(getApplicationContext()).add(firstRequest);
    }

    public void secondStep(String uid) {
        StringBuilder url = new StringBuilder("https://utilityapi.com/api/v2/forms/");
        url.append(uid);//now original string is changed
        url.append("/test-submit");

        JSONObject params = new JSONObject();
        try {
            params.put("utility", "DEMO");
            params.put("scenario", "residential");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest secondRequest = new JsonObjectRequest(Request.Method.POST,url.toString(),params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String referral = response.getString("referral");
                            System.out.println("Referral!" + referral);
                            thirdStep(referral);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Authorization", "Bearer 1e010b87252f42b481a92a874d553867");
                return params;
            }
        };

        Volley.newRequestQueue(getApplicationContext()).add(secondRequest);
    }


    public void thirdStep(String referral) {
        StringBuilder url = new StringBuilder("https://utilityapi.com/api/v2/authorizations?referrals=");
        url.append(referral);//now original string is changed
        url.append("&include=meters");

        JSONObject params = new JSONObject();

        JsonObjectRequest thirdRequest = new JsonObjectRequest(Request.Method.GET,url.toString(),params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //System.out.print(response);
                            JSONArray referral = response.getJSONArray("authorizations");
                            for(int i = 0; i < referral.length(); i++){
                                JSONObject auth = (JSONObject)referral.get(i);
                                System.out.println("AUTHORIZATIONS: " + auth);

                                JSONObject meters = auth.getJSONObject("meters");
                                System.out.println("METERS: " + meters);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Authorization", "Bearer 1e010b87252f42b481a92a874d553867");
                return params;
            }
        };


        Volley.newRequestQueue(getApplicationContext()).add(thirdRequest);
    }



}




