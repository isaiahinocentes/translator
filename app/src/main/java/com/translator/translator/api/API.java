package com.translator.translator.api;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class API {

    RequestQueue queue;

    boolean request_translation(String url, String key, String text, String lang, final Context ctx){
        queue = Volley.newRequestQueue(ctx);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    // Display the first 500 characters of the response string.
                    Toast.makeText(ctx, "Response is: "+ response.substring(0,500), Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(ctx, "That didn't work!" , Toast.LENGTH_SHORT).show();
                }
            }
        );
        queue.add(stringRequest);


        return false;
    }
}
