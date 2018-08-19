package com.translator.translator;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.ArrayList;

public class SpeechTranslation extends AppCompatActivity implements Response.ErrorListener, Response.Listener {

    EditText txtInput;
    Spinner spnrLangSrc, spnrLangDir;
    ProgressBar progressBar;
    String translated_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_translation);

        txtInput = findViewById(R.id.txtInput);
        spnrLangSrc = findViewById(R.id.spnrLangSrc);
        spnrLangDir = findViewById(R.id.spnrLangDir);
        progressBar = findViewById(R.id.progressBar);
    }

    /*public void updateTTSEngine(View v){
        Intent installIntent = new Intent();
        installIntent.setAction(
                TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
        startActivity(installIntent);
    }*/

    /**
     * Translate the Input and Speak translation in selected language
     */
    public void translateAndSpeak(View v){
        try{
            String text = txtInput.getText().toString();
            String lang_src = spnrLangSrc.getSelectedItem().toString();
            String lang_dir = spnrLangDir.getSelectedItem().toString();
            //Format Input
            String formattedText = Functions.formatInput(text, lang_src, lang_dir);
            //Send API Request and Catch Response
            requestTranslation(formattedText);

        } catch (Exception e){
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
                translated_text = translation;
                //Speak the translated text
                doSpeak(translated_text);

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

    protected static final int RESULT_SPEECH = 1;
    public void recognizeSpeech(View v){
        try {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            String value = spnrLangSrc.getSelectedItem().toString();
            value = Functions.convertLocale(value);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, value);

            startActivityForResult(intent, RESULT_SPEECH);
            txtInput.setText("");

        } catch (Exception a) {
            Toast.makeText(getApplicationContext(),
                "Opps! Your device doesn't support Speech to Text",
                Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RESULT_SPEECH: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> text = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    Log.d("TTS", text.toString());
                    txtInput.setText(text.get(0));
                }
                break;
            }

        }
    }



    public void doSpeak(String text){
        //Get Text and Language Direction
        String lang_dir = String.valueOf(spnrLangDir.getSelectedItem());

        try {
            Speech speak = new Speech(text, lang_dir, this);
            speak.speakOut(text);
        } catch (Exception e) {
            Toast.makeText(this, "Error: "+e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

}
