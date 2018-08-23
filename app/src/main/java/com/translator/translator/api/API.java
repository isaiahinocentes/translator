package com.translator.translator.api;

public class API{

    //Yandex Translate API
        public static final String yURL = "https://translate.yandex.net/api/v1.5/tr.json/translate?"; //Yandex
        public static final String yKey = "key=trnsl.1.1.20180806T180600Z.5d41060ee1dee3cd.460818f1319dce121769272399a405d23ba9c4d5&";//Yandex
        public static final String yAPI_URL_KEY = yURL + yKey;

    //Google Translate API
        public static final String ggURL = "https://translation.googleapis.com/language/translate/v2?";
        public static final String ggKEY = "key=AIzaSyAxF1PpknoukyF7iOoHpmpksdZKNzG-nPU&";

    //FINAL API REQUEST LINK
        public static String API_URL_KEY_TEXT_LANG;
}
