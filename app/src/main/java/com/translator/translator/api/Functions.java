package com.translator.translator.api;

import android.content.Context;

import com.translator.translator.TextTranslation;

public class Functions {

    public static String q;
    public static String target; //target language
    public static String source; //Source Language
    public static String FormattedText;

    /**
     * <ul>
     *     <li>Trim the text</li>
     *     <li>make all small caps</li>
     *     <li>escape string .,?!</li>
     *     <li>replace spaces within phrase with "%20"</li>
     * </ul>
     * */
    public static String formatInput(String text, String lang_src, String lang_dir)
            throws  Exception{

        //If String is empty
        if(text.isEmpty()){
            throw new Exception("Text input is empty");
        }
        //Error Checking
        //if src and dir are the same, throw error
        if(lang_src.equals(lang_dir)){
            throw new Exception("The language source and direction are the same...");
        }
        //if either strings are null
        if(lang_src.isEmpty() || lang_dir.isEmpty()){
            throw new Exception("Language source or direction is empty");
        }

        //Remove trailing and end spaces
        text = text.trim();
        //convert to lowercase
        text = text.toLowerCase();
        //Replace spaces between to %20
        text = text.replaceAll(" ", "%20");

        q = "q="+text+"&";
        source = "source="+decodeLanguage(lang_src)+"&";
        target = "target="+decodeLanguage(lang_dir);
        FormattedText = q + "&" + source + "&" + target;

        //Return the formatted Text, ex.
        //text=text&lang=en-ta
        return FormattedText;
    }

    //Only three supported languages
    /**
     * Translation Languages:
     * ja - Japanese,
     * en - English,
     * tl - Tagalog
     * */
    public static String formatLanguage(String lang_src, String lang_dir)
            throws Exception{

        //Error Checking
        //if src and dir are the same, throw error
        if(lang_src.equals(lang_dir)){
            throw new Exception("The language source and direction are the same...");
        }
        //if either strings are null
        if(lang_src.isEmpty() || lang_dir.isEmpty()){
            throw new Exception("Language source or direction is empty");
        }

        String lang = "";
        //decode language source
        switch (lang_src){
            case "English": lang = "en"; break;
            case "Japanese": lang = "ja"; break;
            case "Tagalog": lang = "tl"; break;
        }

        //add hypen inbetween of keywords
        lang += "-";

        //decode language direction
        switch (lang_dir){
            case "English": lang += "en"; break;
            case "Japanese": lang += "ja"; break;
            case "Tagalog": lang += "tl"; break;
        }

        //return en-ja
        return lang;
    }

    public static String decodeLanguage(String lang){
        switch (lang){
            case "English": return "en";
            case "Japanese": return "ja";
            case "Tagalog": return "tl";
        }
        return "";
    }

    /**
     * This will format the lang_dir to locale keyword
     * for speech function
     */
    public static String convertLocale(String lang_dir) throws Exception{
        switch (lang_dir){
            case "English": return "en_PH";
            case "Japanese": return "ja_JP";
            case "Tagalog": return "fil_PH";
            default:
                throw new Exception("Invalid target language.");
        }
    }

}
