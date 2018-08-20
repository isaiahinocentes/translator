package com.translator.translator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.translator.translator.api.API;
import com.translator.translator.api.Functions;
import com.translator.translator.speech.Speech;

import org.json.JSONArray;
import org.json.JSONObject;

public class TextTranslation extends AppCompatActivity implements Response.Listener, Response.ErrorListener{

    EditText editText_input, editText_output;
    Spinner spinner_lang_src, spinner_lang_dir;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_translation);
        //Get References to Spinner and InputText
        editText_input = findViewById(R.id.txtInput);
        editText_output = findViewById(R.id.txtOutput);
        spinner_lang_src = findViewById(R.id.spnrLangSrc);
        spinner_lang_dir = findViewById(R.id.spnrLangDir);
        progressBar = findViewById(R.id.progressBar);

    }

    /**
     * Get Element references ->
     * get element values ->
     * format text and language ->
     * send API Request ->
     * Retrieve and show API Response
     */
    public void translateInput(View v){
        try{

            //Get Values
            String text = String.valueOf(editText_input.getText()); //Text to be translated
            String lang_src = String.valueOf(spinner_lang_src.getSelectedItem()); //English, Japanese, or Tagalog
            String lang_dir = String.valueOf(spinner_lang_dir.getSelectedItem());

            //Format the input text to be sent to API Requests
            String formattedText = Functions.formatInput(text, lang_src, lang_dir);

            //Send to API Request
            //and Show Output
            requestTranslation(formattedText);


        } catch(Exception e){
            //Show Toast Message for Error
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    //Translation Request Function
    public void requestTranslation(String TEXT_LANG) {
        //Format the URL First
        API.API_URL_KEY_TEXT_LANG = API.API_URL_KEY + TEXT_LANG;
        //Show at Logcat
        Log.d("Hermes", "API URL: "+API.API_URL_KEY_TEXT_LANG);

        //Show ProgressBar
        progressBar.setVisibility(View.VISIBLE);

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, API.API_URL_KEY_TEXT_LANG,this, this);
        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }
    //Function to be called after Response
    @Override
    public void onResponse(Object response) {
        //String to be returned
        String translation;
        //hiding the progressbar after completion
        progressBar.setVisibility(View.INVISIBLE);
        Log.d("Hermes", response.toString());
        try {
            //getting the whole json object from the response
            JSONObject obj = new JSONObject(response.toString());

            //Check response Code
            int code = obj.getInt("code");
            if(code == 200){
                JSONArray arr_response = obj.getJSONArray("text");
                //Get the Index[0] of text array reponse
                translation = arr_response.getString(0);

                //Show translated Texts
                editText_output.setText(translation); //Show at Textbox
                Toast.makeText(this, "Translation: "+translation, Toast.LENGTH_LONG).show();
            } else { //If code is not success
                throw new Exception(obj.toString());
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error: "+e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
    //Function if error on Response
    @Override
    public void onErrorResponse(VolleyError error) {
        //hiding the progressbar after completion
        progressBar.setVisibility(View.INVISIBLE);
        Log.d("Hermes", error.toString());
        //displaying the error in toast if occurrs
        Toast.makeText(this , error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    /*========== Speech for Texboxes =======================================*/
    public void doSpeak(View v){
        //Get Text and Language Direction
        String text = "";
        String lang_dir = "";

        if(v.equals(findViewById(R.id.micInput))){
            text = String.valueOf(editText_input.getText());
            lang_dir = String.valueOf(spinner_lang_src.getSelectedItem());
        } else if(v.equals(findViewById(R.id.micOutput))){
            text = String.valueOf(editText_output.getText());
            lang_dir = String.valueOf(spinner_lang_dir.getSelectedItem());
        }

        try {
            Speech speak = new Speech(text, lang_dir, this);
            speak.speakOut(text);
        } catch (Exception e) {
            Toast.makeText(this, "Error: "+e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    /*========== Switch Intent =======================================*/
    //Go to Speech Translation Feature
    public void gotoSpeechTranslation(View v){
        Intent intent = new Intent(TextTranslation.this, SpeechTranslation.class);
        startActivity(intent);
    }
}
