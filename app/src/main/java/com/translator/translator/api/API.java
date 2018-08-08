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

    /* Request Format
    https://translate.yandex.net/api/v1.5/tr/translate?
    key=<API key>
    &text=<text to translate>
    &lang=<translation direction>
    &[format=<text format>]
    &[options=<translation options>]
    */

    //With added suffixes (? for url | & for API key)
        public static final String API_BASE_URL = "https://translate.yandex.net/api/v1.5/tr/translate?";
        public static final String API_KEY = "key=trnsl.1.1.20180806T180600Z.5d41060ee1dee3cd.460818f1319dce121769272399a405d23ba9c4d5&";
    //From request
        public static String TEXT;
        public static String LANG; //ends with "" | "&" if using next options
    //Nullable
        public static String FORMAT;
        public static String OPTIONS;
    //FINAL API REQUEST LINK
        public static String API_REQUEST;


    private RequestQueue queue;

    void request_translation(String url, String key, String text, String lang, final Context ctx){
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
    }
}
