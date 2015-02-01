package com.bitforforabetterworld.bitsflashlight;

import android.content.Context;
import android.widget.Toast;

public class Toaster {
    private final Context context;

    public Toaster(Context context) {
        this.context = context;
    }
    public void toast(CharSequence text) {
//        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
