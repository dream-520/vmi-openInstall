package org.shark.miai.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.tigerjoys.nbs.common.utils.Tools;

/**
 * 通用日期处理类
 * 
 * @author liuman
 */
public class TimeTools {

	private static TimeTools instance;

	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYYY_MM_DD_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYY_MM_DD_HH_mm = "yyyy-MM-dd HH:mm";
	public static final String YYYYMMDDHHmmss = "yyyyMMddHHmmss";
	public static final String YYYY_MM = "yyyy-MM";

	/**
	 * 格式化字符串-日期天开始时刻
	 */
	public static final String FORMAT_DAY_BEGIN = "yyyy-MM-dd 00:00:00";
	/**
	 * 格式化字符串-日期天结束时刻
	 */
	public static final String FORMAT_DAY_END = "yyyy-MM-dd 23:59:59";

	private static Logger logger = LoggerFactory.getLogger(TimeTools.class);

	public TimeTools() {
	}

	// public static DateBuilder getInstance() {
	// return new DateBuilder();
	// }
	public static TimeTools getInstance() {
		if (instance == null) {
			instance = new TimeTools();
		}
		return instance;
	}

	public static Date getDate(String sFormat, String date) {
		if (date == null || "".equals(date)) {
			return null;
		}

		SimpleDateFormat formatter = new SimpleDateFormat(sFormat);
		try {
			return formatter.parse(date);
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 取得系统当前时间
	 * 
	 * @return String yyyy-mm-dd
	 */
	public static String getCurrentDate_Simple() {
		Calendar rightNow = Calendar.getInstance();
		int year = rightNow.get(Calendar.YEAR);
		int month = rightNow.get(Calendar.MONTH) + 1;
		int day = rightNow.get(Calendar.DATE);
		return convertDateToString(convertStringToDate(year + "-" + month + "-" + day));
	}

	/**
	 * 取得系统当前时间的前n秒
	 * 
	 * @param n
	 *            int
	 */
	public static Date getSecBeforeCurrentDate(int second) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.SECOND, -second);
		return c.getTime();
	}

	/**
	 * 获取现在时间
	 * 
	 * @return 返回时间字符串 yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrentDate() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);

	}

	public static String getCurrentDate(String formatStr) {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		return format.format(date);

	}

	public static String formatStrDateToStrDate(String strDate, String s_fm, String d_fm) {
		try {
			SimpleDateFormat df = new SimpleDateFormat(s_fm);
			Date date = df.parse(strDate);
			df = new SimpleDateFormat(d_fm);
			return df.format(date);
		} catch (Exception e) {
			System.out.println("日期转换失败:" + e.getMessage());
			return "";
		}
	}

	/**
	 * 获取时间字符串
	 * 
	 * @return 返回时间字符串 yyyy-MM-dd HH:mm:ss
	 */
	public static String getDateString(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);

	}

	/**
	 * 获取现在时间
	 * 
	 * @return 返回时间字符串 yyyy-MM-dd HH:mm:ss
	 */
	public static String getDateToString(Date time, String fm) {
		if (null == time || StringUtils.isEmpty(fm)) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(fm);
		return format.format(time);
	}

	/**
	 * 获取现在时间
	 * 
	 * @return 返回时间字符串 yyyy-MM-dd HH:mm:ss
	 */
	public static String getDateByLongTime(long time) {
		Date date = new Date(time);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

	public static String getCurrentDateOrder() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return format.format(date);
	}

	public static String getCurrentDateTime(String pattern) {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);

	}

	/**
	 * 取得系统当前时间前n个月的相对应的一天
	 * 
	 * @param n
	 *            int
	 * @return String yyyy-mm-dd
	 */
	public static String getMonthOfDayBeforeCurrentDate(int month) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -month);
		return convertDateToString(convertStringToDate(
				"" + c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE)));
	}

	/**
	 * 取得系统当前时间后n个月的相对应的一天
	 * 
	 * @param n
	 *            int
	 * @return String yyyy-mm-dd
	 */
	public static String getMonthOfDayAfterCurrentDate(int month) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -month);
		return convertDateToString(convertStringToDate(
				"" + c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE)));
	}

	/**
	 * 获取本月第一天
	 * 
	 * @return
	 */
	public static String getFirstDayOfCurrentMonth() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DATE);
		return convertDateToString(convertStringToDate(year + "-" + month + "-" + day));
	}

	/**
	 * 获取本月最后一天
	 * 
	 * @return
	 */
	public static String getLastDayOfCurrentMonth() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DATE);
		return convertDateToString(convertStringToDate(year + "-" + month + "-" + day));
	}

	/**
	 * 获取某月的第一天
	 * 
	 * @return 返回时间长整型
	 */
	public static String getFirstDayOfMonthString(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DATE);
		return convertDateToString(convertStringToDate(year + "-" + month + "-" + day));
	}

	/**
	 * 获取某月的最后一天
	 * 
	 * @return 返回时间长整型
	 */
	public static String getLastDayOfMonthString(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DATE);
		return convertDateToString(convertStringToDate(year + "-" + month + "-" + day));
	}

	/**
	 * 得到指定时间小时
	 */
	public static int getHour(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		return hour;
	}

	/**
	 * 获取指定时间天
	 */
	public static int getDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day = c.get(Calendar.DAY_OF_MONTH);
		return day;
	}

	/**
	 * 得到现在分钟
	 * 
	 * @return
	 */
	public static int getMinute() {
		Calendar c = Calendar.getInstance();
		int min = c.get(Calendar.MINUTE);
		return min;
	}

	/**
	 * 取得系统当前时间的前n天
	 * 
	 * @param n
	 *            int
	 * @return String yyyy-mm-dd
	 */
	public static String getDayBeforeCurrentDate(int day) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -day);
		return convertDateToString(convertStringToDate(
				"" + c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE)));
	}

	/**
	 * 取得系统当前时间后n天
	 * 
	 * @param n
	 *            int
	 * @return String yyyy-mm-dd
	 */
	public static String getDayAfterCurrentDate(int day) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, day);
		return convertDateToString(convertStringToDate(
				"" + c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE)));
	}

	public static Date getDateTimeAfterCurrentDate(int day) throws ParseException {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		String newDate = getDayAfterCurrentDate(day);
		String time = format.format(date);
		SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatTime.parse(newDate + " " + time);
	}

	/**
	 * 取得当前时间前n天的时间
	 * 
	 * @param day
	 * @return yyyy-MM-dd HH:mm:ss
	 * @throws ParseException
	 */
	public static Date getDateTimeBeforeCurrentDate(int day) throws ParseException {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -day);
		return c.getTime();
	}

	/**
	 * 获取日期的星期
	 * 
	 * @param strDate
	 *            日期
	 * @return 星期数字
	 */
	public static int getWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int week = c.get(Calendar.DAY_OF_WEEK) - 1;
		return week;

	}

	/**
	 * * 获取指定日期是星期几 参数为null时表示获取当前日期是星期几
	 * 
	 * @param date
	 * @return
	 */
	public static String getWeekOfDate(Date date) {
		String[] weekOfDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
		}
		int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0) {
			w = 0;
		}
		return weekOfDays[w];
	}

	/**
	 * * 获取指定日期是星期几 参数为null时表示获取当前日期是星期几
	 * 
	 * @param date
	 * @return
	 */
	public static String anotherWeekOfDate(Date date) {
		String[] weekOfDays = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
		}
		int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0) {
			w = 0;
		}
		return weekOfDays[w];
	}

	public static String getWeekString(int week) {
		String strWeek = "";
		switch (week) {
		case 0:
			strWeek = "星期日";
			break;
		case 1:
			strWeek = "星期一";
			break;
		case 2:
			strWeek = "星期二";
			break;
		case 3:
			strWeek = "星期三";
			break;
		case 4:
			strWeek = "星期四";
			break;
		case 5:
			strWeek = "星期五";
			break;
		case 6:
			strWeek = "星期六";
			break;
		case 7:
			strWeek = "星期日";
			break;
		default:
			strWeek = "";
		}
		return strWeek;
	}

	// type=0,星期，type=1,周
	public static String getWeekString(int week, int type) {
		if (type == 0) {
			return getWeekString(week);
		}
		String strWeek = "";
		switch (week) {
		case 0:
			strWeek = "周日";
			break;
		case 1:
			strWeek = "周一";
			break;
		case 2:
			strWeek = "周二";
			break;
		case 3:
			strWeek = "周三";
			break;
		case 4:
			strWeek = "周四";
			break;
		case 5:
			strWeek = "周五";
			break;
		case 6:
			strWeek = "周六";
			break;
		case 7:
			strWeek = "周日";
			break;
		default:
			strWeek = "";
		}
		return strWeek;
	}

	/**
	 * 将一个日期字符串转化成日期
	 * 
	 * @param sDate
	 *            String
	 * @return Date yyyy-mm-dd
	 */
	public static Date convertStringToDate(String strDate) {
		Date date = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			date = df.parse(strDate);

		} catch (Exception e) {
			System.out.println("日期转换失败:" + e.getMessage());
		}
		return date;
	}

	public static Date convertStringToDate(String strDate, String fm) {
		Date date = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat(fm);
			date = df.parse(strDate);

		} catch (Exception e) {
			System.out.println("日期转换失败:" + e.getMessage());
		}
		return date;
	}

	public static Date convertStringToDate2(String strDate) {
		Date date = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = df.parse(strDate);

		} catch (Exception e) {
			System.out.println("日期转换失败:" + e.getMessage());
		}
		return date;
	}

	/**
	 * 输入两个字符串型的日期，比较两者的大小
	 * 
	 * @param fromDate
	 *            String
	 * @param toDate
	 *            String
	 * @return boolean before为true
	 */
	public static boolean compareTwoDateBigOrSmall(String fromDate, String toDate) {
		Date dateFrom = convertStringToDate(fromDate);
		Date dateTo = convertStringToDate(toDate);
		if (dateFrom.before(dateTo)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 输入两个字符串型的日期，比较两者的大小
	 * 
	 * @param Date1
	 *            String
	 * @param Date2
	 *            String
	 * @return -1 1<2 0 1=2 1 1>2 else -2
	 */
	public static int compareDate(String Date1, String Date2) {
		Date date1 = new Date();
		Date date2 = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date1 = df.parse(Date1);
			date2 = df.parse(Date2);
			if (date1.before(date2)) {
				return -1;
			} else if (date1.equals(date2)) {
				return 0;
			} else if (date1.after(date2)) {
				return 1;
			}
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		}
		return -2;
	}

	/**
	 * 将一个日期字符串转化成Calendar
	 * 
	 * @param sDate
	 *            String
	 * @return Calendar
	 */
	public static Calendar convertDateStringToCalendar(String strDate) {
		Date date = convertStringToDate(strDate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}

	/**
	 * 将一个日期转化成Calendar
	 * 
	 * @param date
	 *            Date
	 * @return Calendar
	 */
	public static Calendar convertDateToCalendar(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}

	/**
	 * 取得某个特定时间前n个月相对应的一天
	 * 
	 * @param sDate
	 *            String
	 * @param n
	 *            int
	 * @return yyyy-mm-dd
	 */
	public static String getDayOfMonthBeforeSpecificDate(String strDate, int month) {
		Calendar c = convertDateStringToCalendar(strDate);
		c.add(Calendar.MONTH, -month);
		return convertDateToString(convertStringToDate(
				"" + c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE)));
	}

	/**
	 * 取得某个特定时间前N小时的时间
	 * 
	 * @param sDate
	 *            String
	 * @param n
	 *            int
	 * @return yyyy-mm-dd HH:mm:ss
	 */
	public static String getDayOfHourBefore(String strDate, int hour) {

		Date date = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = df.parse(strDate);
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.HOUR, -hour);
			strDate = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE) + " "
					+ c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MILLISECOND) + ":" + c.get(Calendar.SECOND);
		} catch (Exception e) {
			System.out.println("日期转换失败:" + e.getMessage());
		}
		return strDate;
	}

	/**
	 * 取得某个特定时间前N分钟的时间
	 * 
	 * @param strDate
	 * @param min
	 * @return
	 */
	public static String getDayOfMinBefore(String strDate, int min) {
		String time = "";
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -min); // 把时间设置为当前时间-min分钟，同理，也可以设置其他时间
		time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());// 获取到完整的时间
		return time;
	}

	/**
	 * 取得某个特定时间后n个月相对应的一天
	 * 
	 * @param sDate
	 *            String
	 * @param n
	 *            int
	 * @return yyyy-mm-dd
	 */
	public static String getDayOfMonthAfterSpecificDate(String strDate, int month) {
		Calendar c = convertDateStringToCalendar(strDate);
		c.add(Calendar.MONTH, month);
		return convertDateToString(convertStringToDate(
				"" + c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE)));
	}

	/**
	 * 取得某个特定时间前n天,格式为yyyy-mm-dd
	 * 
	 * @param sDate
	 *            String
	 * @param day
	 *            int
	 * @return yyyy-mm-dd
	 */
	public static String getDayBeforeSpecificDate(String strDate, int day) {
		Calendar c = convertDateStringToCalendar(strDate);
		c.add(Calendar.DAY_OF_MONTH, -day);
		return convertDateToString(convertStringToDate(
				"" + c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE)));
	}

	/**
	 * 取得某个时间后n天,格式为yyyy-mm-dd
	 * 
	 * @param sDate
	 *            String
	 * @param day
	 *            int
	 * @return yyyy-mm-dd
	 */
	public static String getDayAfterSpecificDate(String strDate, int day) {
		Calendar c = convertDateStringToCalendar(strDate);
		c.add(Calendar.DAY_OF_MONTH, day);
		return convertDateToString(convertStringToDate(
				"" + c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE)));
	}

	/**
	 * 判断系统当前时间是否为闰年
	 * 
	 * @return
	 */
	public boolean isLeapYear() {
		java.util.Calendar rightNow = java.util.Calendar.getInstance();
		int year = rightNow.get(Calendar.YEAR);
		if (0 == year % 4 && (year % 100 != 0 || year % 400 == 0)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 将一个字符串转化为标准日期
	 * 
	 * @param str
	 * @return
	 */
	public Date convertStringToStandardDate(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		Date dateTime = null;
		if (!"".equals(str)) {
			try {
				date = sdf.parse(str);
				dateTime = new Date(date.getTime());
			} catch (ParseException e) {
				logger.error(e.getMessage(), e);
			}

		}
		return dateTime;
	}

	/**
	 * 将日期转化为字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String convertDateToString(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}

	/**
	 * 将日期转化为字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String convertDateToString_str(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		return dateFormat.format(date);
	}

	/**
	 * 将日期转换为字符串
	 * 
	 * @param date
	 *            日期
	 * @param pattern
	 *            格式字符串
	 * @return
	 */
	public static String formatDate(Date date, String pattern) {
		if (date == null)
			return "";
		if (pattern == null || "".equals(pattern)) {
			pattern = "yyyy-MM-dd";
		}
		SimpleDateFormat dateFormat = null;
		try {
			dateFormat = new SimpleDateFormat(pattern);
			return dateFormat.format(date);
		} catch (Exception e) {
			dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			return dateFormat.format(date);
		}

	}

	/**
	 * 获取指定时间月份
	 */
	public static int getMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH) + 1;
		return month;
	}

	/**
	 * 获取指定时间年份
	 */
	public static int getYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		return year;
	}

	/**
	 * 取得上一个小时
	 */
	public static String getPreHour(String format, int step) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) - step);
		return sdf.format(cal.getTime());
	}

	/**
	 * 
	 * 取得某日期的上一个月时间
	 */
	public static String getPrevMonthOfDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(convertStringToDate(date));
		cal.add(Calendar.MONTH, -1); // 前个月
		return sdf.format(cal.getTime());
	}

	/**
	 * 
	 * 取得某日期的下一个月时间
	 */
	public static String getNextMonthOfDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(convertStringToDate(date));
		cal.add(Calendar.MONTH, 1); // 后个月
		return sdf.format(cal.getTime());
	}

	public static Hashtable<Integer, Integer> getWeekNumberHT(long beginDate, long endDate) {
		Hashtable<Integer, Integer> weeks = new Hashtable<Integer, Integer>();
		for (int i = 1; i <= 7; i++) {
			weeks.put(i, 0);
		}
		long q = beginDate;
		for (q = beginDate; q <= endDate; q = q + new Long(24 * 3600 * 1000).longValue()) {
			Calendar c = Calendar.getInstance();
			Date date = new Date(q);
			c.setTime(date);
			int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
			if (dayofweek == 0) {
				dayofweek = 7;
			}
			if (weeks.containsKey(dayofweek)) {
				weeks.put(dayofweek, weeks.get(dayofweek) + 1);
			} else {
				weeks.put(dayofweek, 1);
			}
		}
		return weeks;
	}

	/**
	 * 上一星期
	 * 
	 * @param date
	 * @return
	 */
	public static String getPrevWeekOfDate(String date) {
		return getDayBeforeSpecificDate(date, 7);
	}

	/**
	 * 获取某年某月的最后一天
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return 最后一天
	 */
	public static int getLastDayOfMonth(int year, int month) {
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			return 31;
		}
		if (month == 4 || month == 6 || month == 9 || month == 11) {
			return 30;
		}
		if (month == 2) {
			if (isLeapYear(year)) {
				return 29;
			} else {
				return 28;
			}
		}
		return 0;
	}

	/**
	 * 是否闰年
	 * 
	 * @param year
	 *            年
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}

	/**
	 * 返回某段时间内 每一天
	 * 
	 * @param beginDateStr
	 * @param endDateStr
	 * @return
	 */
	public static List<String> getDaysByFromTo(String beginDateStr, String endDateStr) {
		Date beiginDate = convertStringToDate(beginDateStr);
		endDateStr = getDayAfterSpecificDate(endDateStr, 1);
		Date endDate = convertStringToDate(endDateStr);
		List<String> dateList = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date currentDate = new Date();
		if (currentDate.before(beiginDate)) {
			currentDate = beiginDate;
		}
		String currentDateStr = sdf.format(currentDate);
		while (currentDate.before(endDate)) {
			dateList.add(currentDateStr);
			currentDateStr = getDayAfterSpecificDate(currentDateStr, 1);
			currentDate = convertStringToDate(currentDateStr);
		}
		return dateList;
	}

	public static String getAfterMinute(String minute) {
		Long m = Long.parseLong(minute) * 60 * 1000;
		return getDateByLongTime(System.currentTimeMillis() + m, "MM-dd HH:mm");
	}

	/**
	 * 获取现在时间
	 * 
	 * @return 返回时间字符串 yyyy-MM-dd HH:mm:ss
	 */
	public static String getDateByLongTime(long time, String fm) {
		Date date = new Date(time);
		SimpleDateFormat format = new SimpleDateFormat(fm);
		return format.format(date);
	}

	/**
	 * 获取Calendar的字符串 yyyyMMdd格式
	 * 
	 * @param cal
	 * @return
	 */
	public static int formatCalendar(Calendar cal) {
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);

		int date = year * 10000 + month * 100 + day;

		return date;
	}

	/**
	 * 获取Weekday
	 * 
	 * @param cal
	 * @return
	 */
	public static int getWeekday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return getWeekday(cal);
	}

	/**
	 * 获取Weekday
	 * 
	 * @param cal
	 * @return
	 */
	public static int getWeekday(String dateStr, String format) {
		Date date = convertStringToDate(dateStr, format);
		return getWeekday(date);
	}

	/**
	 * 获取Weekday
	 * 
	 * @param cal
	 * @return
	 */
	public static int getWeekday(Calendar cal) {

		int weekday = cal.get(Calendar.DAY_OF_WEEK);
		if (weekday == 0) {
			weekday = 7;
		} else {
			weekday = weekday - 1;
		}
		return weekday;
	}

	/**
	 * 加/减小时
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addHour(Date date, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.HOUR, amount);
		return c.getTime();
	}

	public static String addHour(String strDate, int i) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(strDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + i);
		return sdf.format(cal.getTime());
	}

	/**
	 * 日期天数增加
	 * 
	 * @param date
	 *            日期实例
	 * @param days
	 *            增加的天数
	 * @return 增加后的日期实例
	 */
	public static Date addDay(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, days);
		return cal.getTime();
	}

	/**
	 * 得到日期天开始时刻字符串
	 * 
	 * @param date
	 *            日期实例
	 * @return 格式化字符串 ("yyyy-MM-dd 00:00:00")
	 */
	public static String toDayBeginStr(Date date) {
		return new SimpleDateFormat(FORMAT_DAY_BEGIN).format(date);
	}

	/**
	 * 得到日期天结束时刻字符串
	 * 
	 * @param date
	 *            日期实例
	 * @return 格式化字符串 ("yyyy-MM-dd 23:59:59")
	 */
	public static String toDayEndStr(Date date) {
		return new SimpleDateFormat(FORMAT_DAY_END).format(date);
	}

	/**
	 * 转化字符串到日期实例
	 * 
	 * @param dateStr
	 *            日期字符串
	 * @param format
	 *            格式字符串
	 * @return 日期实例
	 * @throws ParseException
	 *             转化异常
	 */
	public static Date toDate(String dateStr, String format) throws ParseException {
		return new SimpleDateFormat(format).parse(dateStr);
	}

	/**
	 * 转化字符串到日期实例
	 * 
	 * @param dateStr
	 *            字符串格式(yyyy-MM-dd HH:mm:ss)
	 * @return 日期实例
	 * @throws ParseException
	 *             转化异常
	 */
	public static Date toDate(String dateStr) throws ParseException {
		return toDate(dateStr, YYYY_MM_DD_HH_mm_ss);
	}

	/**
	 * 得到日期标准字符串
	 * 
	 * @param date
	 *            日期实例
	 * @return 格式化字符串 (yyyy-MM-dd HH:mm:ss)
	 */
	public static String toStr(Date date) {
		return new SimpleDateFormat(YYYY_MM_DD_HH_mm_ss).format(date);
	}

	/**
	 * 得到日期天结束时刻字符串
	 * 
	 * @param date
	 *            日期实例
	 * @return 格式化字符串 ("yyyy-MM-dd 23:59:59")
	 */
	public static Date getDayEndStr(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return c.getTime();
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smDate
	 *            较小的时间
	 * @param bDate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smDate, Date bDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smDate = sdf.parse(sdf.format(smDate));
		bDate = sdf.parse(sdf.format(bDate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smDate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bDate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 字符串的日期格式的计算
	 */
	public static int daysBetween(String smDate, String bDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smDate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bDate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 根据服务起始时间和服务周期获取标书日历控件的所有日期
	 * 
	 * @param startTime
	 * @param servicePeriod
	 * @return
	 */
	public static String[] servicePeriodToSelectDaysArray(String startTime, String servicePeriod) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		String startTimeArray[] = startTime.split("-");
		calendar.set(Integer.parseInt(startTimeArray[0]), Integer.parseInt(startTimeArray[1]) - 1,
				Integer.parseInt(startTimeArray[2]));
		String calendarDayArray[] = new String[31];
		for (int i = 0; i < servicePeriod.length(); i++) {
			// System.out.println(i + ":" + servicePeriod.charAt(i));
			if (i == 0) {
				calendarDayArray[i] = startTime;
			} else {
				calendar.add(Calendar.DAY_OF_MONTH, 1);
				calendarDayArray[i] = format.format(calendar.getTime());
			}
		}

		return calendarDayArray;
	}

	/**
	 * 根据勾选订单日期和司机到仓时间获得订单的服务时间
	 * 
	 * @param selectDay
	 * @param arriveRepoTime
	 * @return
	 */
	public static String getOrderServiceTime(String selectDay, Date arriveRepoTime) {
		DateFormat format = new SimpleDateFormat("HH:mm");
		StringBuffer sb = new StringBuffer(selectDay);
		return sb.append(" " + format.format(arriveRepoTime) + ":00").toString();
	}

	/**
	 * 根据出生日期获得年龄
	 * 
	 * @param birthDate
	 * @return
	 */
	public static int getAge(Date birthDate) {

		if (birthDate == null)
			throw new RuntimeException("出生日期不能为null");

		int age = 0;

		Date now = new Date();

		SimpleDateFormat format_y = new SimpleDateFormat("yyyy");
		SimpleDateFormat format_M = new SimpleDateFormat("MM");

		String birth_year = format_y.format(birthDate);
		String this_year = format_y.format(now);

		String birth_month = format_M.format(birthDate);
		String this_month = format_M.format(now);

		// 初步，估算
		age = Integer.parseInt(this_year) - Integer.parseInt(birth_year);

		// 如果未到出生月份，则age - 1
		if (this_month.compareTo(birth_month) < 0)
			age -= 1;
		if (age < 0)
			age = 0;
		return age;
	}

	/**
	 * 返回某段时间内 每一天
	 * 
	 * @param beginDateStr
	 * @param endDateStr
	 * @return
	 */
	public static List<String> getScopeDays(String beginDateStr, String endDateStr) {
		Date beiginDate = convertStringToDate(beginDateStr);
		endDateStr = getDayAfterSpecificDate(endDateStr, 1);
		Date endDate = convertStringToDate(endDateStr);
		List<String> dateList = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date currentDate = setDate(beginDateStr);
		if (currentDate.before(beiginDate)) {
			currentDate = beiginDate;
		}
		String currentDateStr = sdf.format(currentDate);
		while (currentDate.before(endDate)) {
			dateList.add(currentDateStr);
			currentDateStr = getDayAfterSpecificDate(currentDateStr, 1);
			currentDate = convertStringToDate(currentDateStr);
		}
		return dateList;
	}

	/**
	 * 设置是某一天
	 * 
	 * @param time
	 * @return
	 */
	public static Date setDate(String time) {
		String[] timeArray = time.split("-");
		String year = timeArray[0];
		String month = timeArray[1];
		String day = timeArray[2];
		Calendar instance = Calendar.getInstance();
		instance.set(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day));
		return instance.getTime();
	}

	/**
	 * 获得当前时间的第二天
	 * 
	 * @return
	 */
	public static Date getNextDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, +1);
		return calendar.getTime();
	}

	/**
	 * 获得当前时间第二天0时0分0秒的毫秒数
	 * 
	 * @return
	 */
	public static long getNextMorningTime() {
		Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);

		return calendar.getTimeInMillis();
	}

	/**
	 * 生成随机时间
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static Date randomDate(String beginDate, String endDate) {

		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date start = format.parse(beginDate);// 构造开始日期
			Date end = format.parse(endDate);// 构造结束日期
			// getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
			if (start.getTime() >= end.getTime()) {
				return null;
			}

			long date = random(start.getTime(), end.getTime());
			return new Date(date);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
	
	private static long random(long begin, long end) {
		long rtn = begin + (long) (Math.random() * (end - begin));
		// 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值
		if (rtn == begin || rtn == end) {
			return random(begin, end);
		}
		return rtn;
	}
	
	/**
	 * 获得前后几分钟的时间
	 * @param minute
	 * @return
	 */
    public static long getTimeByMinute(int minute) {

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.MINUTE, minute);

        return calendar.getTime().getTime();

    }
    
    /**
     * 获得当前时间到晚上24点的秒数
     * @return
     */
    public static int getRemindSeconds() {
		long currentTime = System.currentTimeMillis();
		long totalTime = getNextMorningTime();
		long remindTime = totalTime - currentTime;
		return new Long(remindTime/1000).intValue();
    }
    
    /**
     * 根据出生日期值判断是否是刚刚创建用户
     * @param birthDay
     * @return
     */
    public boolean judgeCreateUserByBirthday(Date birthDay) {
    	boolean flag = false;
		if (Tools.isNotNull(birthDay)) {
			int age = Tools.getAge(birthDay);
			//刚刚创建的用户是1000岁
			if (age > 150) {
				flag = true;
			}
		}
		
		return flag;
    }
    
    /** 
     *  
     * @param oldTime 较小的时间 
     * @param newTime 较大的时间 (如果为空   默认当前时间 ,表示和当前时间相比) 
     * @return -1 ：同一天.    0：昨天 .   1 ：至少是前天. 
     * @throws ParseException 转换异常 
     */  
    public static int isYeaterday(Date oldTime,Date newTime) throws ParseException{  
        if(newTime==null){  
            newTime=new Date();  
        }  
               //将下面的 理解成  yyyy-MM-dd 00：00：00 更好理解点  
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
        String todayStr = format.format(newTime);  
        Date today = format.parse(todayStr);  
        //昨天 86400000=24*60*60*1000 一天  
        if((today.getTime()-oldTime.getTime())>0 && (today.getTime()-oldTime.getTime())<=86400000) {  
            return 0;  
        }  
        else if((today.getTime()-oldTime.getTime())<=0){ //至少是今天  
            return -1;  
        }  
        else{ //至少是前天  
            return 1;  
        }  
          
    }
    
	/**
	 * 计算两个日期之间相差的分钟数
	 * 
	 * @param time1
	 *            较小的时间
	 * @param time2
	 *            较大的时间
	 * @return 相差分钟数
	 * @throws ParseException
	 */
	public static int minutesBetween(long time1, long time2){
		long between_minutes = (time2 - time1) / (1000 * 60);
		return Integer.parseInt(String.valueOf(between_minutes));
	}
    
}
