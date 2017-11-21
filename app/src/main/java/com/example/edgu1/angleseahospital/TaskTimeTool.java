package com.example.edgu1.angleseahospital;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 21/11/2017.
 */

public class TaskTimeTool {

    public static String culTime(Double frenquent){
        String minStr=String.valueOf(frenquent).replaceAll("\\d+\\.", "").trim();
        int hour = (int) Math.floor(frenquent);
        int min =  Integer.valueOf(minStr).intValue();
        try{
            min = 60/(100/( min *10));
        }catch (Exception e){
            e.printStackTrace();
        }
        Date d = new Date();
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.HOUR, now.get(Calendar.HOUR) + hour);
        now.set(Calendar.MINUTE, now.get(Calendar.MINUTE) + min);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return sdf.format(now.getTime());
    }

}
