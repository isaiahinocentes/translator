package com.translator.translator;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.translator.translator.speech.Speech;

public class SpeechTranslation extends AppCompatActivity {

    EditText txtInput;
    Spinner spnrLangSrc, spnrLangDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_translation);

        txtInput = findViewById(R.id.txtInput);
        spnrLangSrc = findViewById(R.id.spnrLangSource);
        spnrLangDir = findViewById(R.id.spnrLangDir);


    }

    public void updateTTSEngine(View v){
        Intent installIntent = new Intent();
        installIntent.setAction(
                TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
        startActivity(installIntent);
    }

    public void doSpeak(View v){
        //Get Text and Language Direction
        String text = String.valueOf(txtInput.getText());
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
