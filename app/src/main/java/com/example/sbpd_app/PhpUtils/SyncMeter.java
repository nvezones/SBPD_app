package com.example.sbpd_app.PhpUtils;

import android.support.annotation.Nullable;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

public class SyncMeter extends StringRequest {
    public static String value[][]=new String[2][5];
    public SyncMeter(int method, String url, Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }
}
