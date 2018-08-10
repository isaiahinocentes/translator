package com.translator.translator.speech;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.util.Log;
import java.util.Locale;

import static com.translator.translator.api.Functions.*;

public class Speech implements TextToSpeech.OnInitListener{

    private String text;
    private Locale locale;
    private TextToSpeech tts;

    public Speech(String text, String lang_dir, Context context) throws Exception{
        this.text = text;
        this.locale = new Locale(convertLocale(lang_dir));
        tts = new TextToSpeech(context,this);
    }

    //On Creation Function, will set the locale to the specified language
    @Override
    public void onInit(int i) {
        try {
            if (i == TextToSpeech.SUCCESS) {
                int result = tts.setLanguage(this.locale);
                //Check if the locale set is valid/supported
                if (result == TextToSpeech.LANG_MISSING_DATA
                        || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("TTS", "This Language is not supported");
                } else {
                    speakOut(text);
                }
            } else {
                Log.e("TTS", "Initialization Failed!");
            }
        } catch(Exception e){
            Log.e("TTS", "Unable to Speak Text");
            e.printStackTrace();
        }
    }

    //Initiate Speak text
    public void speakOut(String text) throws Exception{
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "id1");
    }


}
