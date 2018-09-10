/**
 * 
 */
package com.yunxin.cb.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Aidy_He
 *
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils {

	private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);
	
	private static String defaultPattern = "yyyy-MM-dd";
	
	public final static String TIMESTAMP="yyyy-MM-dd HH:mm:ss";
	
	public final static String TIME="yyyy-MM-dd HH:mm";
	
	public final static String HOUR="yyyy-MM-dd HH";
	
	public final static String DATE="yyyy-MM-dd";

	public final static SimpleDateFormat FORMAT_TIMESTAMP = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public final static SimpleDateFormat FORMAT_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public final static SimpleDateFormat FORMAT_HOUR = new SimpleDateFormat("yyyy-MM-dd HH");

	public final static SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");

	public final static SimpleDateFormat FORMAT_DIR = new SimpleDateFormat("yyyy/MM/dd/");

    /** 年月日时分秒(无下划线) yyyyMMddHHmmss */
    public static final String dtLong = "yyyyMMddHHmmss";

    public static final String stLong = "yMMddHHmmss";


    /**
     * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
     * @return
     *      以yyyyMMddHHmmss为格式的当前系统时间
     */
    public  static String getSeriNo(){
        Date date=new Date();
        DateFormat df=new SimpleDateFormat(stLong);
        return df.format(date);
    }

	/**
	 * 根据pattern判断字符串是否为合法日期
	 * 
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static boolean isValidDate(String dateStr, String pattern) {
		boolean isValid = false;
		if (pattern == null || pattern.length() < 1) {
			pattern = "yyyy-MM-dd";
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			String date = sdf.format(sdf.parse(dateStr));
			if (date.equalsIgnoreCase(dateStr)) {
				isValid = true;
			}
		} catch (Exception e) {
			isValid = false;
		}
		// 如果目标格式不正确，判断是否是其它格式的日期
		if (!isValid) {
			isValid = isValidDatePatterns(dateStr, "");
		}
		return isValid;
	}

	/***
	 * 根据patterns判断字符串是否为合法日期
	 * @param dateStr
	 * @param patterns
	 * @return
	 */
	public static boolean isValidDatePatterns(String dateStr, String patterns) {
		if (patterns == null || patterns.length() < 1) {
			patterns = "yyyy-MM-dd;dd/MM/yyyy;yyyy/MM/dd;yyyy/M/d h:mm";
		}
		boolean isValid = false;
		String[] patternArr = patterns.split(";");
		for (int i = 0; i < patternArr.length; i++) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(patternArr[i]);
				String date = sdf.format(sdf.parse(dateStr));
				if (date.equalsIgnoreCase(dateStr)) {
					isValid = true;
					DateUtils.defaultPattern = patternArr[i];
					break;
				}
			} catch (Exception e) {
				isValid = false;
			}
		}
		return isValid;
	}

	/****
	 * 转换指定格式的日期字符串
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static String getFormatDate(String dateStr, String pattern) {
		if (pattern == null || pattern.length() < 1) {
			pattern = "yyyy-MM-dd";
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.defaultPattern);
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			String date = format.format(sdf.parse(dateStr));
			return date;
		} catch (Exception e) {
			logger.error("日期格式化转换失败！", e);
		}
		return null;
	}

	/***
	 * 转换指定格式的日期对象
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String getFormatDate(Date date, String pattern) {
		if (pattern == null || pattern.length() < 1) {
			pattern = "yyyy-MM-dd";
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			String strDate = sdf.format(date);
			return strDate;
		} catch (Exception e) {
			logger.error("日期格式化转换失败！", e);
		}
		return null;
	}

	/***
	 * 日期字符串转日期对象
	 * @param s
	 * @return
	 */
	public static Date parseDate(String s) {
		DateFormat df = DateFormat.getDateInstance();
		Date myDate = null;
		try {
			myDate = df.parse(s);
		} catch (ParseException e) {
			logger.error("日期字符串转日期对象失败！", e);
		}
		return myDate;
	}

	/***
	 * 获取指定格式的当前系统时间
	 * @param pattern
	 * @return
	 */
	public static String getCurrentDate(String pattern) {
		Date date = new Date();
		if (pattern == null || pattern.length() < 1) {
			return FORMAT_DATE.format(date);
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		}
	}

	/***
	 * 获取当前系统时间
	 * @param
	 * @return
	 */
	public static String getCurrentDate() {
		Date date = new Date();
		return FORMAT_DATE.format(date);
	}

	/***
	 * 转换日期对象为年月日，时分秒
	 * @param date
	 * @return
	 */
	public static String getFormatTimestamp(Date date) {
		return FORMAT_TIMESTAMP.format(date);
	}

	/***
	 * 转换日期字符串为年月日，时分秒
	 * @param s
	 * @return
	 */
	public static Date parseTimestamp(String s) {
		Date date = null;
		try {
			date = FORMAT_TIMESTAMP.parse(s);
		} catch (Exception e) {
			logger.error("日期字符串转日期对象失败！", e);
		}
		return date;
	}

	/***
	 * 转换指定格式的日期字符串，返回日期对象
	 * @param s
	 * @param pattern
	 * @return
	 */
	public static Date parseDate(String s, String pattern) {
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			date = sdf.parse(s);
		} catch (Exception e) {
			logger.error("日期字符串转日期对象失败！", e);
		}
		return date;
	}

	/***
	 * 转换日期对象返回年月日
	 * @param date
	 * @return
	 */
	public static String getFormatDate(Date date) {
		return FORMAT_DATE.format(date);
	}

	/**
	 * 比较两个时间（date1大于date2 返回1,等于返回0，小于返回-1）
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int compareDate(String date1, String date2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {
			Date dt1 = df.parse(date1);
			Date dt2 = df.parse(date2);
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	/**
	 * 返回两个时间差(date1-date2)
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long differenceDate(Date date1, Date date2) {
		return date1.getTime() - date2.getTime();
	}
}
