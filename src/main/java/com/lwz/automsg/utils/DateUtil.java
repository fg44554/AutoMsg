package com.lwz.automsg.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;


public class DateUtil {

        public static final String yearMonthDay= "yyyy年MM月dd日";
        public static final String yrMonDayHrMin= "yyyy年MM月dd日 HH:mm";
        public static final String yrMonDayHrMinSec_ = "yyyy-MM-dd HH:mm:ss";
        public static final String yrMonDayHrMinSe= "yyyy年MM月dd日HH:mm:ss";
        public static final String yrMonDayHrMinSec = "yyyyMMddHHmmss";
        public static final String yrMonDayHrMinSecShortYr = "yyMMddHHmmss";
        public static final String yrMonDayHrMin_ = "yyyy-MM-dd HH:mm";
        public static final String yrMonDay_ = "yyyy-MM-dd";
        public static final String yrMonDay = "yyyyMMdd";
        public static final String yrMonDay2 = "yyMMdd";
        public static final String yrMon_ = "yyyy-MM";
        public static final String yrMon = "yyyyMM";
        public static final String hrMinSec_ = "HH:mm:ss";
        public static final String hrMinSec = "HHmmss";
        public static final String hrMin_ = "HH:mm";
        public static final String hrMin = "HHmm";
        public static final String year = "yyyy";
        public static final String month = "MM";
        public static final String day = "dd";
        public static final String hour = "HH";
        public static final String monDay = "MMdd";

        public static final SimpleDateFormat yearMonthDaySDF = new SimpleDateFormat(yearMonthDay);
        public static final SimpleDateFormat yrMonDayHrMinSecSDF_ = new SimpleDateFormat(yrMonDayHrMinSec_);
        public static final SimpleDateFormat yrMonDayHrMinSecSDF = new SimpleDateFormat(yrMonDayHrMinSec);
        public static final SimpleDateFormat yrMonDayHrMinSecShortYrSDF = new SimpleDateFormat(yrMonDayHrMinSecShortYr);
        public static final SimpleDateFormat yrMonDayHrMinSDF_ = new SimpleDateFormat(yrMonDayHrMin_);
        public static final SimpleDateFormat yrMonDaySDF_ = new SimpleDateFormat(yrMonDay_);
        public static final SimpleDateFormat yrMonDaySDF = new SimpleDateFormat(yrMonDay);
        public static final SimpleDateFormat yrMonDaySDF2 = new SimpleDateFormat(yrMonDay2);
        public static final SimpleDateFormat yrMonSDF_ = new SimpleDateFormat(yrMon_);
        public static final SimpleDateFormat yrMonSDF = new SimpleDateFormat(yrMon);
        public static final SimpleDateFormat hrMinSecSDF_ = new SimpleDateFormat(hrMinSec_);
        public static final SimpleDateFormat hrMinSecSDF = new SimpleDateFormat(hrMinSec);
        public static final SimpleDateFormat hrMinSDF_ = new SimpleDateFormat(hrMin_);
        public static final SimpleDateFormat hrMinSDF = new SimpleDateFormat(hrMin);
        public static final SimpleDateFormat yearSDF = new SimpleDateFormat(year);
        public static final SimpleDateFormat monthSDF = new SimpleDateFormat(month);
        public static final SimpleDateFormat daySDF = new SimpleDateFormat(day);
        public static final SimpleDateFormat hourSDF = new SimpleDateFormat(hour);
        public static final SimpleDateFormat monDaySDF = new SimpleDateFormat(monDay);

        public enum DateInterVal {
            DAY, MONTH, YEAR
        }



        /**
         * String To Date---TESTED
         * @param dateStr 待转换的字符串型日期；
         * @param format 转化的日期格式
         * @return 返回该字符串的日期型数据；
         */
        public static Date stringToDate(String dateStr, String format) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            try {
                return sdf.parse(dateStr);
            } catch (ParseException e) {
                throw new IllegalArgumentException(e);
            }
        }



        /**
         * 转换成20150829的字符串格式---TESTED
         * @param date
         * @return
         */
        public static String dateToYrMonDay(Date date) {
            synchronized (yrMonDaySDF) {
                return yrMonDaySDF.format(date);
            }
        }


        /**
         * 转换成2015-08-29的字符串格式---TESTED
         * @param date
         * @return
         */
        public static String dateToYrMonDay_(Date date) {
            synchronized (yrMonDaySDF_) {
                return yrMonDaySDF_.format(date);
            }
        }


        /**
         * 增加或减少天数,注意入参本身是不改变的---TESTED
         * @param date 当前日期
         * @param amount 可以传负整数（整数天数+1，反之-1）
         * @return
         */
        public static Date addDays(Date date, int amount) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, amount);
            return cal.getTime();
        }


        /**
         * 时间转字符串格式---TESTED
         *
         * @param date
         * @param format
         * @return
         */
        public static String dateToString(Date date, String format) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        }


        /***
         * 根据当前时间偏移格式化
         *
         * @param interval
         *            编移类型 年|月|日
         * @param offset
         *            编移量 正数增加 负数减少
         * @param format
         *            格式 默认yyyy-MM-dd
         * @return String
         * @author Junhua Hu
         * @date 2016-11-9
         */
        public static String addDateByInterVal(DateInterVal interval, int offset,
                                               String format) {
            Calendar cal = Calendar.getInstance();
            format = format == null ? "yyyy-MM-dd" : format;
            switch (interval) {
                case DAY:
                    cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)
                            + offset);
                    break;
                case MONTH:
                    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + offset);
                    break;
                case YEAR:
                    cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + offset);
                    break;
                default:
                    break;
            }
            return DateFormatUtils.format(cal, format);
        }

        /***
         * 根据指定时间偏移格式化
         *
         * @param interval
         *            编移类型 年|月|日
         * @param offset
         *            编移量 正数增加 负数减少
         * @param format
         *            格式 默认yyyy-MM-dd
         * @param dateStr
         *            日期字符串,默认当天
         * @return String
         * @author Junhua Hu
         * @date 2016-11-9
         */
        public static String addDateByInterVal(DateInterVal interval, int offset,
                                               String format, String dateStr) {
            try {
                Calendar cal = Calendar.getInstance();
                format = format == null ? "yyyy-MM-dd" : format;
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                if (StringUtils.isNotBlank(dateStr)) {
                    cal.setTime(sdf.parse(dateStr));
                }
                switch (interval) {
                    case DAY:
                        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)
                                + offset);
                        break;
                    case MONTH:
                        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + offset);
                        break;
                    case YEAR:
                        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + offset);
                        break;
                    default:
                        break;
                }
                return DateFormatUtils.format(cal, format);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        /***
         * 返回指定日期偏移量
         *
         * @param format  格式 默认yyyy-MM-dd
         * @param dateStr 日期字符串,默认当天
         * @return int 偏移量
         * @author Junhua Hu
         * @date 2017-8-8
         */
        public static int parseOffsetForDate(DateInterVal interval, String format,
                                             String dateStr) {
            try {
                int offset = 0;
                Calendar cal = Calendar.getInstance();
                format = format == null ? "yyyy-MM-dd" : format;
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                Date dateDay = new Date();
                if (StringUtils.isNotBlank(dateStr)) {
                    dateDay = sdf.parse(dateStr);
                }
                switch (interval) {
                    case DAY:

                        break;
                    case MONTH:

                        break;
                    case YEAR:
                        int yearNow = cal.get(Calendar.YEAR);
                        int monthNow = cal.get(Calendar.MONTH);
                        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
                        cal.setTime(dateDay);

                        int yearBirth = cal.get(Calendar.YEAR);
                        int monthBirth = cal.get(Calendar.MONTH);
                        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

                        offset = yearNow - yearBirth;

                        if (monthNow <= monthBirth) {
                            if (monthNow == monthBirth) {
                                if (dayOfMonthNow < dayOfMonthBirth) {
                                    offset--;
                                }
                            } else {
                                offset--;
                            }
                        }
                        break;
                    default:
                        break;
                }
                return offset;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        }

        /***
         * 获取两个日期相差天数(不判断时分秒)
         * @Description: (描述)
         * @param @param startdate 开始
         * @param @param enddate 结束
         * @param @return
         * @return int
         * @throws
         */
        public static int getOffsetDayForDate(Date startdate, Date enddate) {
            int offset = 0;
            Calendar scal = Calendar.getInstance();
            scal.setTime(startdate);
            //清零时分秒
            scal.set(Calendar.HOUR, 0);
            scal.set(Calendar.MINUTE, 0);
            scal.set(Calendar.SECOND, 0);
            Calendar ecal = Calendar.getInstance();
            ecal.setTime(enddate);
            ecal.set(Calendar.HOUR, 0);
            ecal.set(Calendar.MINUTE, 0);
            ecal.set(Calendar.SECOND, 0);
            long sdate = scal.getTimeInMillis();
            long edate = ecal.getTimeInMillis();
            offset  = (int)((edate-sdate)/(1000*60*60*24));
            return offset;
        }


        public static String getFormatDateTime(Date date, String format)
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(date);
        }

        /**
         * 当天剩余秒数
         */
        public static int getTheRestOfTheDay(){
            long currentM = System.currentTimeMillis();
            Date date2 = null;
            try {
                date2 = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(getFormatDateTime(new Date(),"yyyy-MM-dd")+" 23:59:59");
            } catch (ParseException e) {
                date2 = new Date();
            }
            long dayM = date2.getTime();
            return (int)(dayM - currentM)/1000;
        }

        /**
         * 获取本月第一天
         * @return
         */
        public static String getFirstDayOfThisMonth(){
            Calendar c = Calendar.getInstance();
            c.add(Calendar.MONTH, 0);
            c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
            return   new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        }


        /**
         * 获取本月最后一天
         * @return
         */
        public static String getLastDayOfThisMonth(){
            Calendar ca = Calendar.getInstance();
            ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
            return  new SimpleDateFormat("yyyy-MM-dd").format(ca.getTime());
        }

        /**
         * 获取本月还剩余时间S
         */
        public static int getTheRestOfTheMonth(){
            long currentM = System.currentTimeMillis();
            Date date2 = null;
            try {
                date2 = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(getLastDayOfThisMonth()+" 23:59:59");
            } catch (ParseException e) {
                date2 = new Date();
            }
            long dayM = date2.getTime();
            return (int)(dayM - currentM)/1000;
        }

        /**
         * 时间总的秒数
         * @param seconds
         * @return
         */
        public static String remainingTime(long seconds){
            long startHours = seconds/3600;
            long startMinutes = (seconds%3600)/60;
            long startSeconds = (seconds%3600)%60;
            //时间：yyyy-MM-dd HH:mm:ss
            String startDateStr =  startHours + "小时" + startMinutes + "分" + startSeconds+"秒";
            return startDateStr;
        }

        /**
         * 字符串日期：1998-05-28 转 LocalDate
         * @param dateStr
         * @return
         */
        public static LocalDate dateStrLocalDate(String dateStr) {
            DateTimeFormatter df = DateTimeFormatter.ofPattern(DateUtil.yrMonDay_);
            LocalDate localDate = LocalDate.parse(dateStr,df);
            return localDate;
        }

        /**
         * 获取指定天早上九点日期
         * @param day 次日=1，第二天=2，依此类推
         * @return
         */
        public static Date getSpecifiedDayNineClockDate(Integer day) {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_YEAR, day);
            c.set(Calendar.HOUR_OF_DAY, 9);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
            return c.getTime();
        }

        /**
         * 计算今天是星期几
         * 返回值：0=星期日，1=星期一,2=星期二,3=星期三,4=星期四,5=星期五,6=星期六
         * @return
         */
        public static int getTodayWhatDayOfWeek(Date dt){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dt);
            return calendar.get(Calendar.DAY_OF_WEEK)-1;
        }

        /**
         * 1512057599-1510303379
         * @param args
         * @throws ParseException
         */

        public static void main(String[] args) throws ParseException {

            String firstDayOfThisMonth = getFirstDayOfThisMonth();
            System.out.println("获取本月第一天: " + firstDayOfThisMonth);
            String lastDayOfThisMonth = getLastDayOfThisMonth();
            System.out.println("获取本月第一天: " + lastDayOfThisMonth);

//		Date date = new Date();
//		String s = dateToYrMonDay(date);
//		System.out.println("今天Date: " + s);
//		String ss = dateToYrMonDay_(date);
//		System.out.println("今天Date:     " + ss);
//		System.out.println("----------------------------------------------------");
//		Date date1 = addDays(date, -1);
//		String s1 = dateToYrMonDay(date1);
//		System.out.println("前天Date:  " + s1);
//		String ss1 = dateToYrMonDay_(date1);
//		System.out.println("前天Date: " + ss1);
        }



}
