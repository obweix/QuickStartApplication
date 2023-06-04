package com.example.quickstartapplication.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;

public class Utils {
    public static int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(200, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }


    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static void setMargins (View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }
}
