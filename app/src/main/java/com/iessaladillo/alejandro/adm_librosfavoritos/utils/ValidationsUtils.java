package com.iessaladillo.alejandro.adm_librosfavoritos.utils;

import android.text.TextUtils;
import android.util.Patterns;

public class ValidationsUtils {

    private ValidationsUtils(){}

    public static boolean isValidUrl(String url) {
        return !TextUtils.isEmpty(url) && Patterns.WEB_URL.matcher(url).matches();
    }
}
