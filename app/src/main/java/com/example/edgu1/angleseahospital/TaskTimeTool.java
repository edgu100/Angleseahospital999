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

    public static int getAge(String birthStr) throws Exception {
        Date birthDay =  new Date(birthStr);
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)){
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;
        if (monthNow <= monthBirth){
            if (monthNow == monthBirth){
                if (dayOfMonthNow < dayOfMonthBirth)
                    age--;
            }else{
                age--;
            }
        }
        return age;
    }

}
