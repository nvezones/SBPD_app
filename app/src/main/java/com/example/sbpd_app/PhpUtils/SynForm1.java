package com.example.sbpd_app.PhpUtils;


import android.support.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SynForm1 extends StringRequest {

    private static final String REGISTER_URL = "url_for_web";
    private Map<String, String> parameters;

    public SynForm1(String col1,String col2,String col3,Response.Listener<String> listener {
        super(Method.POST,REGISTER_URL, listener, null);
        this.parameters = parameters;
        parameters = new HashMap<>();
        parameters.put("col1",col1);
        parameters.put("col2",col2);
        parameters.put("col3",col3);


    }

    protected Map<String, String> getParams() throws AuthFailureError
    {
        return parameters;
    }
}
