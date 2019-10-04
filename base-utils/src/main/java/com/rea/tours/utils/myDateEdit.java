package com.rea.tours.utils;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class myDateEdit extends PropertyEditorSupport
{
    private SimpleDateFormat dateFormat;

    public myDateEdit(String str)
    {
        dateFormat = new SimpleDateFormat(str);
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException
    {

        try{
//            if(Pattern.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}", text))
//                dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            if(Pattern.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}", text))
//                dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            Date date = dateFormat.parse(text);
            setValue(date);
        }catch (ParseException e)
        {
            e.printStackTrace();
        }
    }
}
