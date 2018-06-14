package com.example.menaadel.salesapp.Utils;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by MenaAdel on 6/12/2018.
 */

public class UtilsFunctions {

    public static String constructFullName(String firstName, String lastName) {
        return firstName + " " + lastName;
    }

    public static boolean isValidEmail(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * method which check if this word size is more than or equal six letters
     *
     * @param word
     * @return
     */
    public static boolean isSizeMoreThanOrEqualSixLettters(String word) {
        if (word.length() >= 6) {
            return true;
        }
        return false;
    }

    public static String getCurrentDate(SimpleDateFormat mDateformat) {
        Calendar calendar = Calendar.getInstance();

        return mDateformat.format(calendar.getTime());
    }

    public static void showSnackbar(View root, String msg, int backgroundColor) {
        new CSnackbar.SnackBuilder(root, msg)
                .setMessageColor(Color.WHITE)
                .setBackroundColor(backgroundColor)
                .setMessageTextSize(18)
                .setDuration(Snackbar.LENGTH_LONG)
                .setDismissAction(true)
                .buildSnackBar()
                .show();

    }
}
