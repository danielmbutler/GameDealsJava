package com.dbtechprojects.gamedealsjava.utils;

import android.content.Context;
import com.squareup.picasso.Picasso;

import java.util.concurrent.Executors;

//Image loader Singleton

public class ImageLoader {

    private static Picasso instance;

    public static Picasso getSharedInstance(Context context)
    {
        if(instance == null)
        {
            instance = new Picasso.Builder(context)
                    .executor(Executors.newSingleThreadExecutor())
                    .build();
        }
        return instance;
    }
}
