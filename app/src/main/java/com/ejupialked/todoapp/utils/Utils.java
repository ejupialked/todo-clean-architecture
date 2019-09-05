package com.ejupialked.todoapp.utils;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.snackbar.Snackbar;

public class Utils {

    public static void showSnackBarMessage(String text, CoordinatorLayout coordinatorLayout){
        Snackbar snackbar = Snackbar.make(coordinatorLayout, text, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }


}
