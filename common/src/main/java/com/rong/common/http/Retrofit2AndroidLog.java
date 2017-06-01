package com.rong.common.http;

import android.util.Log;

import okhttp3.logging.HttpLoggingInterceptor;

public class Retrofit2AndroidLog implements HttpLoggingInterceptor.Logger {
    private static final int MAX_LOG_LENGTH = 4000;
    private final String tag;

    public Retrofit2AndroidLog(String tag) {
        this.tag = tag;
    }

    @Override
    public void log(String message) {
        // Split by line, then ensure each line can fit into Log's maximum length.
        for (int i = 0, length = message.length(); i < length; i++) {
            int newline = message.indexOf('\n', i);

            if (-1 == newline) {
                newline = length;
            }

            do {
                int end = Math.min(newline, i + MAX_LOG_LENGTH);
                Log.d(tag, message.substring(i, end));
                i = end;
            } while (i < newline);
        }
    }

    public String getTag() {
        return this.tag;
    }
}
