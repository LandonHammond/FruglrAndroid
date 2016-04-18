package com.angrydrop.fruglr;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * Created by Landon on 4/13/2016.
 */
public class TestRequest extends JsonObjectRequest {

    private static final String REGISTER_REQUEST_URL = "http://96.38.245.248/Fruglr/api/Account/Register";
    public TestRequest(JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(Method.POST, REGISTER_REQUEST_URL, jsonRequest, listener, errorListener);
    }
}
