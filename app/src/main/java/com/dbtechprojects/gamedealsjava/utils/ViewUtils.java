package com.dbtechprojects.gamedealsjava.utils;

import android.app.Activity;;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.snackbar.Snackbar;

public class ViewUtils {

    public static void showSnackBar(View layout, Activity activity, String message) {
        Snackbar snackbar = Snackbar.make(layout, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    // hide keyboard, used for when inputting search
    public static void hideSoftKeyBoard(Context context, View view) {
        try {
            InputMethodManager inputContext = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputContext.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
