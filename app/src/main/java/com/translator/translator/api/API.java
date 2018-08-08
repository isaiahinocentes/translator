package com.translator.translator.api;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.translator.translator.TextTranslation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        public static final String API_BASE_URL = "https://translate.yandex.net/api/v1.5/tr.json/translate?";
        public static final String API_KEY = "key=trnsl.1.1.20180806T180600Z.5d41060ee1dee3cd.460818f1319dce121769272399a405d23ba9c4d5&";
        public static final String API_URL_KEY = API_BASE_URL+API_KEY;
    //From request
        public static String TEXT;
        public static String LANG; //ends with "" | "&" if using next options
        public static String TEXT_LANG;
    //Nullable
        public static String FORMAT;
        public static String OPTIONS;

    //FINAL API REQUEST LINK
        public static String API_URL_KEY_TEXT_LANG;


    private static RequestQueue queue;

    public static void requestTranslation(String TEXT_LANG, final ProgressBar progressBar, final Context ctx) {

        //Format the URL First
        API_URL_KEY_TEXT_LANG = API_URL_KEY + TEXT_LANG;

        //Show at Logcat
        Log.d("Hermes", "API URL: "+API_URL_KEY_TEXT_LANG);

        //Show ProgressBar
        progressBar.setVisibility(View.VISIBLE);

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, API_URL_KEY_TEXT_LANG,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion
                        progressBar.setVisibility(View.INVISIBLE);
                        Log.d("Hermes", response);
                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);

                            //Check response Code
                            int code = obj.getInt("code");
                            if(code == 200){
                                JSONArray arr_response = obj.getJSONArray("text");
                                //Get the Index[0] of text array reponse
                                TextTranslation.translation = arr_response.getString(0);
                                //showAlertDialog(TextTranslation.translation, ctx);
                                Toast.makeText(ctx, "Translation: "+TextTranslation.translation, Toast.LENGTH_LONG).show();
                            } else {
                                TextTranslation.translation = "";
                                throw new Exception("Error on API response");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        TextTranslation.translation = "";
                        //displaying the error in toast if occurrs
                        Toast.makeText(ctx, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(ctx);

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }

    /*public static void showAlertDialog(String text, Context ctx){
        AlertDialog.Builder alert = new AlertDialog.Builder(ctx);
        alert.setMessage(text)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {}})
                .setCancelable(true)
                .create();
        alert.show();
    }*/

}
