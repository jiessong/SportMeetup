package com.example.sportsdating.util;

import android.graphics.Typeface;
import android.widget.TextView;

public class TypefaceUtil {
    public static void setTypeface(TextView tv,String typePath){
        Typeface typeface = Typeface.createFromAsset(tv.getContext().getAssets(), typePath);
        //set typeface for textView
        tv.setTypeface(typeface);
    }
}
