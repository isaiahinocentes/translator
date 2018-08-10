package com.translator.translator.speech;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import java.util.Locale;

import static com.translator.translator.api.Functions.*;

public class Speech implements TextToSpeech.OnInitListener{

    private Locale locale;
    private TextToSpeech tts;

    public Speech(String lang_dir, Context context) throws Exception{
        this.locale = new Locale(convertLocale(lang_dir));
        tts = new TextToSpeech(context,this);
    }

    //On Creation Function, will set the locale to the specified language
    @Override
    public void onInit(int i) {
        if(i == TextToSpeech.SUCCESS){
            int result = tts.setLanguage(this.locale);
            //Check if the locale set is valid/supported
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            }
        } else {
            Log.e("TTS", "Initialization Failed!");
        }
    }

    //Initiate Speak text
    public void speakOut(String text) throws Exception{
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "id1");
    }


}
