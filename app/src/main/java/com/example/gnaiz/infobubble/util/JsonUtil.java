package com.example.gnaiz.infobubble.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {

    public static Gson gson;

    static {
        gson=new GsonBuilder().create();
    }
}
