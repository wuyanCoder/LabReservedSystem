/**
* @Title: DateTest
* @Package com.sean.tools
* @Description: TODO(获取时间)
* @author wsl
* @date 2018.9.15
* @version V1.0
*/
package com.sean.tools;
import java.io.IOException;
import java.text.ParseException;  
import java.text.SimpleDateFormat;  
import java.util.Calendar;  
import java.util.Date;  
public class DateTest {   
    public static String Date() throws IOException {  
        Calendar now = Calendar.getInstance(); 
        Date d = new Date();   
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
        String dateNowStr = sdf.format(d);  
        return dateNowStr;
    }  
}  
