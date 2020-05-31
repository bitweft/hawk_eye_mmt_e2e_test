package com.mmt.helpers;

import org.jetbrains.annotations.Nullable;

import java.net.MalformedURLException;
import java.net.URL;

public class HubUrlHelper {
    @Nullable
    public static URL getUrl(String hubUrl) {
        URL url = null;
        try {
            url = new URL(hubUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
}
