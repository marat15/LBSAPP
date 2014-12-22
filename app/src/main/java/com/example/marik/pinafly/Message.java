package com.example.marik.pinafly;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Marik on 12/18/2014.
 */
public class Message {
    public static void message(Context context, String message)
    {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

}
