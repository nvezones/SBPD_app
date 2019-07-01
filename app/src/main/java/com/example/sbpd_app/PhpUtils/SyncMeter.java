package com.example.sbpd_app.PhpUtils;

import android.support.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SyncMeter extends StringRequest {
    public static String value[][]=new String[2][5];
    private static final String REGISTER_URL = "https://stockcsbhu.000webhostapp.com/app_backend/MeteringUnitsSS.php";
    private Map<String, String> parameters;

    public SyncMeter( Response.Listener<String> listener) {
        super(Method.POST,REGISTER_URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("ins_id","id1");
        parameters.put("totalMU_33kv","2");
        parameters.put("workingMU_33kv","1");
        parameters.put("defectiveMU_33kv","0");
        parameters.put("totalMU_11kv","3");
        parameters.put("workingMU_11kv","2");
        parameters.put("defectiveMU_11kv","1");

    }


    protected Map<String, String> getParams() throws AuthFailureError
    {
        return parameters;
    }
}
