package br.verumapps.utils;

/*Created by Ygor Martins under the name and laws of Galax Software in 28/03/2019.

 Utils Class for Android Applications*/

import android.content.*;
import android.view.*;
import android.widget.*;
import android.app.*;
import android.text.*;
import android.graphics.drawable.*;
import android.graphics.*;
import java.util.*;
import android.util.*;
import android.view.animation.*;
import java.text.*;
import java.util.regex.*;
import android.animation.*;

public class GalaxUtils
{
    //Components
    Calendar cal_msg_date;

    public static int getDisplayWidthPixels(Context _context)
    {
        return _context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getDisplayHeightPixels(Context _context)
    {
        return _context.getResources().getDisplayMetrics().heightPixels;
    }

    public static boolean DetectIfStringContainsKeywordInList(String text, int arrayString, Activity at)
    {
        Boolean keyFound = false;
        String[] keysArrayString = at.getResources().getStringArray(arrayString);
        List keysList = Arrays.asList(keysArrayString);
        for (String element: keysList)
        {
            if (text.toLowerCase().contains(element.toLowerCase()))
            {
                keyFound = true;
            }
        }
        return keyFound;
    }

    public String getDate()
    {
        String strDate;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        strDate = (String)dateFormat.format(date);
        return strDate;
    }

    public boolean isEditTextEmpty(EditText et)
    {
        boolean isEdtxtEmpty = false;
        if (et.getText().length() <= 0)
        {
            isEdtxtEmpty = true;
            return isEdtxtEmpty;
        }
        else
        {
            return isEdtxtEmpty;
        }
    }

    public static boolean isEmailValid(String email)
    {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    //LocaleUtils.toLocale(language);
}
