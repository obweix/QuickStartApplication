package com.example.quickstartapplication.utils;

import android.graphics.Color;

import java.util.Random;

public class Utils {
    public static int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(200, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}
