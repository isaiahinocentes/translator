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

public class API{
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
}
