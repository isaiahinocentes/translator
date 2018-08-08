package com.translator.translator.api;

public class Functions {

    public static String Text;
    public static String Lang;
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

        //Remove trailing and end spaces
        text = text.trim();
        //convert to lowercase
        text = text.toLowerCase();
        //Replace spaces between to %20
        text = text.replaceAll(" ", "%20");

        //Save the Value to the Class
        Text = "text="+text;
        Lang = "lang="+formatLanguage(lang_src, lang_dir);
        FormattedText = Text + "&" + Lang;

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
}
