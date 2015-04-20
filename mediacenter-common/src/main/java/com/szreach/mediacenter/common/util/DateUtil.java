package com.szreach.mediacenter.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 操作日期类型数据的工具类
 * 
 * @author lizhiwei
 * @version 1.0, 2011-7-28
 * @since
 */
public class DateUtil {
	//日期格式
	private static final String DATE_PATTERN_1 = "yyyy-MM-dd";
	private static final String DATE_PATTERN_2 = "yyyyMMdd";
	private static final String DATE_PATTERN_3 = "yyyy/MM/dd";
	
	//时间格式
	private static final String TIME_PATTERN_1 = "HH:mm:ss";
	private static final String TIME_PATTERN_2 = "HHmmss";
	
	//日期时间格式
	private static final String DATETIME_PATTERN_1 = "yyyy-MM-dd HH:mm:ss";
	private static final String DATETIME_PATTERN_2 = "yyyy-MM-dd HH:mm";
	
	private static final String TIMESTAMP = "yyyyMMddHHmmsssss";
	private static final String GMT_TIME ="d MMM yyyy hh:mm:ss GMT";
	
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat();
	
	//private static final SimpleDateFormat DATE_FORMAT_YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
	//private static final SimpleDateFormat DATE_FORMAT_HH_MM_SS = new SimpleDateFormat("HH:mm:ss");
	/**
	 * 获取当前日期
	 * @return String YYYY-MM-DD
	 */
	public static String getCurrentDateStr1() {
		return getCurrentDateStr(DATE_PATTERN_1);
	}
	
	/**
	 * 获取当前日期
	 * @return String YYYY-MM-DD
	 */
	public static String getCurrentDateStr2() {
		return getCurrentDateStr(DATE_PATTERN_2);
	}
	
	/**
	 * 获取当前日期
	 * @return String YYYY-MM-DD
	 */
	public static String getCurrentDateStr3() {
		return getCurrentDateStr(DATE_PATTERN_3);
	}
	
	/**
	 * 获取指定格式的当前日期
	 * @param format
	 * @return
	 */
	public static String getCurrentDateStr(String format) {
		String dateStr = "";
		Calendar cal = Calendar.getInstance();
		
		DATE_FORMAT.applyPattern(format);
		dateStr = DATE_FORMAT.format(cal.getTime());
		
		return dateStr;
	}
	
	/**
	 * 获取当前时间
	 * @return String HH:mm:ss
	 */
	public static String getCurrentTimeStr1() {
		return getCurrentTimeStr(TIME_PATTERN_1);
	}
	
	/**
	 * 获取当前时间
	 * @return String HHmmss
	 */
	public static String getCurrentTimeStr2() {
		return getCurrentTimeStr(TIME_PATTERN_2);
	}
	
	/**
	 * 获取当前时间
	 * @param format
	 * @return
	 */
	public static String getCurrentTimeStr(String format) {
		String timeStr = "";
		Calendar cal = Calendar.getInstance();
		
		DATE_FORMAT.applyPattern(format);
		timeStr = DATE_FORMAT.format(cal.getTime());
		
		return timeStr;
	}
	
	/**
	 * 获取当前日期时间
	 * @return String yyyy-MM-dd HH-mm-ss
	 */
	public static String getCurrentDateTimeStr() {
		
		return getCurrentDateStr1() + " " + getCurrentTimeStr1();
	}
	
	/**
	 * 获取当前日期时间
	 * @return String yyyyMMddHHmmss
	 */
	public static String getCurrentDateTimeStr2() {
		
		return getCurrentDateStr2() + getCurrentTimeStr2();
	}

	/**
	 * 获取当前日期时间
	 * @return String yyyyMMddHHmmss
	 */
	public static String getTimeStamp() {
		return getCurrentDateStr(TIMESTAMP);
	}
	
	
	public static String formatDate(Date d) {
		DATE_FORMAT.applyPattern(DATE_PATTERN_1);
		return DATE_FORMAT.format(d);
	}
	
	public static String formatDate(String dateStr) {
		Date d = null;
		String date = "";
		try {
			d = DATE_FORMAT.parse(dateStr);
			DATE_FORMAT.applyPattern(DATE_PATTERN_1);
			date = DATE_FORMAT.format(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static String formatTime(Date d) {
		DATE_FORMAT.applyPattern(TIME_PATTERN_1);
		return DATE_FORMAT.format(d);
	}
	
	public static String formatTime(String time) {
		Date d = null;
		String str = "";
		try {
			d = DATE_FORMAT.parse(time);
			DATE_FORMAT.applyPattern(TIME_PATTERN_1);
			str = DATE_FORMAT.format(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public static String formatDateTime(Date d) {
		return formatDateTime(d, DATETIME_PATTERN_1);
	}
	
	public static String formatDateTimeMinute(Date d) {
		return formatDateTime(d, DATETIME_PATTERN_2);
	}
	
	public static String formatDateTime(Date d, String format) {
		if(d == null) return "";
		DATE_FORMAT.applyPattern(format);
		return DATE_FORMAT.format(d);
	}
	
	public static String formatDateTime(String datetime) {
		Date d = null;
		String str = "";
		try {
			d = DATE_FORMAT.parse(datetime);
			DATE_FORMAT.applyPattern(DATETIME_PATTERN_1);
			str = DATE_FORMAT.format(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public static int getCurYear()
    {
        Calendar today = GregorianCalendar.getInstance();
        return today.get(Calendar.YEAR);
    }

    public static int getCurMonth()
    {
        Calendar today = GregorianCalendar.getInstance();
        return today.get(Calendar.MONTH) + 1;
    }

    public static int getCurDay()
    {
        Calendar today = GregorianCalendar.getInstance();
        return today.get(Calendar.DATE);
    }
	
	/**
     * 获取前后N年的年集合
     * 
     * @return
     */
    public static List<Integer> getYears(int prevYearNumber, int nextYearNumber)
    {
        List<Integer> years = new ArrayList<Integer>();
        Integer curYear = DateUtil.getCurYear();
        for (int i = curYear - prevYearNumber; i <= curYear + nextYearNumber; i++)
        {
            years.add(i);
        }
        return years;
    }
    
    /**
     * 获取月列表
     * @方法名称：
     * @方法描述：
     * @引用表：
     * @详细流程：
     *
     * @param n 
     *   n = 0 返回[1...12]
     *   n = 1.. 12 返回  [1.. n]
     *   n = -1 返回[1... 当前月]
     * @return
     */
    public static List<Integer> getMonths(int n)
    {
        if (n == 0)
        {
            n = 12; // 返回12个月
        }
        else if (n == -1)
        {
            n = DateUtil.getCurMonth();
        }
        
        List<Integer> month = new ArrayList<Integer>();
        
        for (int i = 1; i <= n; i++)
        {
            month.add(i);
        }
        return month;
    }
    
    /**
     * 获取前/后几个月的第一天
     * 
     * @return
     * @throws Exception
     */
    public static Calendar getFirstDayByAddMonth(int n)
    {
        Calendar lastDate = GregorianCalendar.getInstance();
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
        lastDate.add(Calendar.MONTH, n);// 减N个月，变为上月的1号
        return lastDate;
    }

    /**
     * 
     * @方法名称：格式化一个字符串日期
     * @方法描述：
     *
     * @param strDate
     * @param pattren
     * @return
     * @throws Exception
     */
    public static String formatDate(String strDate, String fromPattern, String toPattren) throws Exception
    {
        SimpleDateFormat sdf = new SimpleDateFormat(fromPattern);
        Date d = sdf.parse(strDate);
        sdf.applyPattern(toPattren);
        String date = sdf.format(d);
        return date;
    }
    
    /**
     * 
     * @方法名称：对某日期加减月
     *
     * @param now
     * @param n
     * @return
     */
    public static Date addMonth(Date date, int n)
    {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        
        calendar.add(Calendar.MONTH, n);
        
        return new Date(calendar.getTimeInMillis());
    }
    
    /**
     * 
     * @方法名称：对某日期加减天
     *
     * @param now
     * @param n
     * @return
     */
    public static Date addDay(Date date, int n)
    {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        
        calendar.add(Calendar.DAY_OF_MONTH, n);
        
        return new Date(calendar.getTimeInMillis());
    }
    
	public static String addMinute(String time, int minute) {
		Date startTime;
		try {
			DATE_FORMAT.applyPattern("HH:mm");
			startTime = DATE_FORMAT.parse(time);
			Calendar nowTime = Calendar.getInstance();
			nowTime.setTime(startTime);
			nowTime.add(Calendar.MINUTE, minute);
			return DATE_FORMAT.format(nowTime.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}
    
    public static Date toDateTime(String dateStr) {
		DATE_FORMAT.applyPattern(DATETIME_PATTERN_1);
		try {
			return DATE_FORMAT.parse(dateStr);
		} catch (ParseException e) {
			
		}
		return null;
	}
    public static Date toDateTimeMinute(String dateStr) {
    	DATE_FORMAT.applyPattern(DATETIME_PATTERN_2);
    	try {
    		return DATE_FORMAT.parse(dateStr);
    	} catch (ParseException e) {
    		
    	}
    	return null;
    }
    
    public static Date toDate(String dateStr) {
  		DATE_FORMAT.applyPattern(DATE_PATTERN_1);
  		try {
  			return DATE_FORMAT.parse(dateStr);
  		} catch (ParseException e) {
  			
  		}
  		return null;
  	}
    
    public static Date getCurrentDateTime(){
    	return Calendar.getInstance().getTime();
    }
    
    public static String getGmtStr(){
    	SimpleDateFormat sdf = new SimpleDateFormat("EEE,d MMM yyyy hh:mm:ss z", Locale.ENGLISH);
    	String timeStr = "";
		Calendar cal = Calendar.getInstance(Locale.ENGLISH);
		//DATE_FORMAT.applyPattern("EEE,d MMM yyyy hh:mm:ss z");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		timeStr = sdf.format(cal.getTime());
		
		return timeStr;
    }
  
	public static String date2cronExpression(Date date) {
		if (date == null)
			throw new IllegalArgumentException("The date must not be null");

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		StringBuffer cron = new StringBuffer();
		cron.append(calendar.get(Calendar.SECOND)).append(" ");
		cron.append(calendar.get(Calendar.MINUTE)).append(" ");
		cron.append(calendar.get(Calendar.HOUR_OF_DAY)).append(" ");
		cron.append(calendar.get(Calendar.DAY_OF_MONTH)).append(" ");
		cron.append(calendar.get(Calendar.MONTH) + 1).append(" ");
		cron.append("?").append(" ");
		cron.append(calendar.get(Calendar.YEAR));

		return cron.toString();
	}
	
	/**
	 * 与12:00:00相比，判断当前时间是上午还是下午，上午返回true,下午返回false
	 * @return
	 */
	public static boolean isAm() {
		boolean yes = true;
		String now = DateUtil.getCurrentDateTimeStr();
		String middle = DateUtil.getCurrentDateStr1()+" 12:00:00";
		if(DateUtil.toDateTime(now).getTime() - DateUtil.toDateTime(middle).getTime() > 0) {
			yes = false;//下午
		}

		return yes;
	}
	
	/**
	 * 比较两个时间字符串的大小，如果time1>time2，返回1，time1<time2，返回-1, time1=time2，返回0
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static int compareTimeStr(String time1, String time2) {
		int result = 0;
		Calendar calendar1 = Calendar.getInstance();
		String[] arr1 = time1.split(":");
		calendar1.set(Calendar.HOUR_OF_DAY, Integer.valueOf(arr1[0]));
		calendar1.set(Calendar.MINUTE, Integer.valueOf(arr1[1]));
		calendar1.set(Calendar.SECOND, Integer.valueOf(arr1[2]));
		
		Calendar calendar2 = Calendar.getInstance();
		String[] arr2 = time2.split(":");
		calendar2.set(Calendar.HOUR_OF_DAY, Integer.valueOf(arr2[0]));
		calendar2.set(Calendar.MINUTE, Integer.valueOf(arr2[1]));
		calendar2.set(Calendar.SECOND, Integer.valueOf(arr2[2]));
		
		result = calendar1.compareTo(calendar2);
		
		return result;
	}
	
	/**
	 * 前提条件是：start < end
	 * 如果compareTime<start, 返回-1; compareTime>end, 返回1; compareTime在start和end中间，返回0
	 * @param compareTime
	 * @param start
	 * @param end
	 * @return
	 */
	public static int compareTimeStrRange(String compareTime, String start, String end) {
		int result = 0;
		Calendar calendar1 = Calendar.getInstance();
		String[] arr1 = start.split(":");
		calendar1.set(Calendar.HOUR_OF_DAY, Integer.valueOf(arr1[0]));
		calendar1.set(Calendar.MINUTE, Integer.valueOf(arr1[1]));
		calendar1.set(Calendar.SECOND, Integer.valueOf(arr1[2]));
		
		Calendar calendar2 = Calendar.getInstance();
		String[] arr2 = end.split(":");
		calendar2.set(Calendar.HOUR_OF_DAY, Integer.valueOf(arr2[0]));
		calendar2.set(Calendar.MINUTE, Integer.valueOf(arr2[1]));
		calendar2.set(Calendar.SECOND, Integer.valueOf(arr2[2]));
		
		Calendar calendar3 = Calendar.getInstance();
		String[] arr3 = compareTime.split(":");
		calendar3.set(Calendar.HOUR_OF_DAY, Integer.valueOf(arr3[0]));
		calendar3.set(Calendar.MINUTE, Integer.valueOf(arr3[1]));
		calendar3.set(Calendar.SECOND, Integer.valueOf(arr3[2]));
		
		if(calendar3.compareTo(calendar1) < 0 ) {
			result = -1;
		} else if(calendar3.compareTo(calendar2) > 0 ) {
			result = 1;
		} else {
			
		}
		
		return result;
	}
	
	/** 
     * 两个时间相差距离多少天多少小时多少分多少秒 
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00 
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00 
     * @return long[] 返回值为：{天, 时, 分, 秒} 
     */ 
	public static long[] getDistanceTimes(String str1, String str2, String pattern) {  
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		df.applyPattern(pattern);
		
        Date one;  
        Date two;  
        long day = 0;  
        long hour = 0;  
        long min = 0;  
        long sec = 0;  
        try {  
            one = df.parse(str1);  
            two = df.parse(str2);  
            long time1 = one.getTime();  
            long time2 = two.getTime();  
            long diff ;  
            if(time1<time2) {  
                diff = time2 - time1;  
            } else {  
                diff = time1 - time2;  
            }  
            day = diff / (24 * 60 * 60 * 1000);  
            hour = (diff / (60 * 60 * 1000) - day * 24);  
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);  
            sec = (diff/1000-day*24*60*60-hour*60*60-min*60);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        long[] times = {day, hour, min, sec};  
        return times;  
    } 
	
	public static long diffTime(String str1, String str2, String pattern) {  
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");  
		df.applyPattern(pattern);
		
        Date one;  
        Date two;  
        long min = 0;  
        try {  
            one = df.parse(str1);  
            two = df.parse(str2);  
            long time1 = one.getTime();  
            long time2 = two.getTime();  
            long diff ;  
            if(time1<time2) {  
                diff = time2 - time1;  
            } else {  
                diff = time1 - time2;  
            }  
            min = ((diff / (60 * 1000)) );  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        return min;  
    } 
	
	public static long minusTime(String str1, String str2, String pattern) {  
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");  
		df.applyPattern(pattern);
		
        Date one;  
        Date two;  
        long min = 0;  
        try {  
            one = df.parse(str1);  
            two = df.parse(str2);  
            long time1 = one.getTime();  
            long time2 = two.getTime();  
            long diff ;  
          
            diff = time1 - time2;  
            
            min = ((diff / (60 * 1000)) );  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        return min;  
    } 
	
	public static long minusTimeSec(String str1, String str2, String pattern) {  
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");  
		df.applyPattern(pattern);
		
        Date one;  
        Date two;  
        long sec = 0;  
        try {  
            one = df.parse(str1);  
            two = df.parse(str2);  
            long time1 = one.getTime();  
            long time2 = two.getTime();  
            long diff ;  
          
            diff = time1 - time2;  
            
            sec = ((diff / (1000)) );  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        return sec;  
    } 
    
    public static void main(String[] args) {
    	System.out.println(DateUtil.date2cronExpression(new Date()));
    }
}
