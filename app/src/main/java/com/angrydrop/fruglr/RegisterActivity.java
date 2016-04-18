package com.angrydrop.fruglr;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.angrydrop.fruglr.ServerRequests.ServerStatusRequest;

import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);

        final Button btnRegister = (Button) findViewById(R.id.btnRegister);
        assert btnRegister != null;



        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);

                final String email = etEmail.getText().toString();
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                Response.Listener<Integer> responseListener = new Response.Listener<Integer>() {
                    @Override
                    public void onResponse(Integer response) {
                      if(response == 200){
                          //Server Returned Response Code: 200 OK
                          //The account has been successfully created.
                          Toast.makeText(getApplicationContext(), "Account Created!", Toast.LENGTH_LONG);
                          Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                          RegisterActivity.this.startActivity(intent);
                      }else{
                          builder.setMessage("Response Code: " + response)
                                  .setNegativeButton("Retry", null)
                                  .create()
                                  .show();
                      }
                    }
                };

                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(error.networkResponse != null){
                            if(error.networkResponse.statusCode == 400){
                                builder.setMessage("Response Code: 400 BAD REQUEST")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }else{
                                builder.setMessage("Response Code: " + error.networkResponse.statusCode)
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        }else{
                            builder.setMessage("Error: NO RESPONSE FROM SERVER")
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show();
                        }
                    }
                };


                try {
                    JSONObject params = new JSONObject();
                    params.put("Email", email);
                    params.put("Username", username);
                    params.put("Password", password);


                    ServerStatusRequest registerRequest = new ServerStatusRequest(Request.Method.POST, "http://fruglrapi.azurewebsites.net/api/Account/Register", null, params.toString(), responseListener, errorListener);

                    RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                    queue.add(registerRequest);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
