package com.angrydrop.fruglr;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Landon on 4/13/2016.
 */
public class RegisterOldRequest extends StringRequest{
    private static final String REGISTER_REQUEST_URL = "http://localhost:52847/api/Account/Register";
    private Map<String, String> params;

    public RegisterOldRequest(String email, String username, String password, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("Email", email);
        //params.put("username", username);
        params.put("Password", password);
        params.put("ConfirmPassword", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
