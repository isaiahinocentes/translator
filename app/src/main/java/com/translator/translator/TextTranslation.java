package com.translator.translator;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.translator.translator.api.API;
import com.translator.translator.api.Functions;

public class TextTranslation extends AppCompatActivity {

    EditText editText_input;
    Spinner spinner_lang_src;
    ProgressBar progressBar;
    public static String translation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_translation);
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
            //Get References to Spinner and InputText
            editText_input = findViewById(R.id.txtInput);
            spinner_lang_src = findViewById(R.id.spnrLangSource);
            progressBar = findViewById(R.id.progressBar);
            translation = "";

            //Get Values
            String text = String.valueOf(editText_input.getText()); //Text to be translated
            String lang_src = String.valueOf(spinner_lang_src.getSelectedItem()); //English, Japanese, or Tagalog
            //Sample for now, translate all to english
            String lang_dir = "English";

            //Format the input text to be sent to API Requests
            String formattedText = Functions.formatInput(text, lang_src, lang_dir);

            //Send to API Request
            API.requestTranslation(formattedText, progressBar, this.getApplicationContext());


        } catch(Exception e){
            //Show Toast Message for Error
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void showTranslation(String text){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage(translation)
            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {}})
            .setCancelable(true)
            .create();
        alert.show();
    }



}
