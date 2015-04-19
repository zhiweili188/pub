package com.szreach.mediacenter.common.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import sun.misc.BASE64Encoder;

public class CommonTools {

	// private static final SimpleDateFormat sdf = new SimpleDateFormat();
	public static final String DF_FULL_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String DF_MONTH_PATTERN = "yyyy-MM";
	public static final String DF_DATE_PATTERN = "yyyy-MM-dd";
	public static final String DF_TIME_PATTERN = "HH:mm:ss";
	public static final String DF_TIME_MINUTE_PATTERN = "HH:mm";

	/**
	 * 随机产生全局唯一GUID标识,用于各表的ID字段
	 * 
	 * @return
	 */
	public static String getGUID() {
		UUID uuid = UUID.randomUUID();
		String guid = uuid.toString().replace("-", "");
		return guid;
	}

	/**
	 * MD5算法 在RFC 1321中，给出了Test suite用来检验你的实现是否正确： MD5 ("") =
	 * d41d8cd98f00b204e9800998ecf8427e MD5 ("a") =
	 * 0cc175b9c0f1b6a831c399e269772661 MD5 ("abc") =
	 * 900150983cd24fb0d6963f7d28e17f72 MD5 ("message digest") =
	 * f96b697d7cb7938d525a2f31aaf161d0 MD5 ("abcdefghijklmnopqrstuvwxyz") =
	 * c3fcd3d76192e4007dfb496cca67e13b
	 * 
	 * @param s
	 * @return
	 */
	public final static String getMD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * LDAP上默认的MD5加密方式
	 * @param password
	 * @return
	 */
	public static String getLdapMD5(String password) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes("UTF-8"));
			byte s[] = md.digest();
            
			for (int i = 0; i < s.length; i++) {
				result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6);
			}
			
			result = new String(new BASE64Encoder().encode(s));
		} catch (Exception e) {
		}
		return "{MD5}"+result;
	}

	/**
	 * 取客户端真实的IP地址，此方法会自动屏弃代理服务器的IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (!isNotNull(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("x-real-ip");
		}
		if (isNull(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (isNull(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (isNull(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (isNull(ip)) {
			ip = "unknown";
		}
		return ip;
	}

	/**
	 * 获取客户端的IP地址，这里主要过滤本地访问时出现的127.0.0.1的IP地址
	 * @param request
	 * @return
	 */
	public static String getRequestAddrIp(HttpServletRequest request, String localIp) {
		String ip = CommonTools.getIpAddr(request);
		if ("127.0.0.1".equals(ip) || "localhost".equalsIgnoreCase(ip)) {
			ip = request.getLocalAddr();
		}
		if ("127.0.0.1".equals(ip) || "localhost".equalsIgnoreCase(ip)) {
			ip = localIp;
		}
		return ip;
	}
	
	/**
	 * 用于判断输入的字符串是否是数字
	 * 
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNum(String s) {
		if (s != null && s.matches("[0-9]+")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 是否为空
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNull(String value) {
		return value == null || value.trim().length() == 0;
	}

	/**
	 * 是否不为空
	 * 
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNotNull(String value) {
		return !isNull(value);
	}

	/**
	 * 字符长度是否 >=min
	 * 
	 * @param value
	 * @param min
	 * @return
	 */
	public static boolean minLength(String value, int min) {
		return getStringLength(value) >= min;
	}

	/**
	 * 字符长度是否 <=max
	 * 
	 * @param value
	 * @param max
	 * @return
	 */
	public static boolean maxLength(String value, int max) {
		return getStringLength(value) <= max;
	}

	/**
	 * 字符长度是否 >=min $$ <=max
	 * 
	 * @param value
	 * @param min
	 * @param max
	 * @return
	 */
	public static boolean betweenLength(String value, int min, int max) {
		int length = getStringLength(value);
		return length >= min && length <= min;
	}

	/**
	 * 获取字符串字符长度，汉字占2字符
	 * 
	 * @param value
	 * @return
	 */
	public static int getStringLength(String value) {
		if (isNull(value)) {
			return 0;
		}

		value = value.replace("[^\\x00-\\xff]", "ch");
		return value.length();
	}

	/**
	 * 正则表达式匹配
	 * 
	 * 
	 * @param value
	 * @param exp
	 * @return
	 */
	public static boolean match(String value, String exp) {
		if (value == null || exp == null) {
			return false;
		}

		return value.matches(exp);
	}

	/**
	 * 日期字符串转换为日期
	 * 
	 * @param string
	 * @param pattern
	 * @return
	 */
	public static Date stringToDate(String string, String pattern) {
		if( isNull(string)){
			return null;
		}
		if (!isNotNull(pattern)) {
			pattern = DF_FULL_PATTERN;
		}
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern(pattern);
		try {
			return sdf.parse(string);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 日期转换为字符串
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String dateToString(Date date, String pattern) {
		if (pattern == null) {
			pattern = DF_FULL_PATTERN;
		}
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern(pattern);
		return sdf.format(date);
	}

	/**
	 * 转换当前时间，供页面上js进行国际化显示
	 * 
	 * 
	 * document.write(Message.dateString(<%=com.szreach.common.CommonTools.
	 * nowDateString()%>));
	 * 
	 * @return
	 */
	public static String nowDateString() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH) + "-" + (cal.get(Calendar.DAY_OF_WEEK) - 1);
	}

	/**
	 * 获取当天最后一秒时间，常用于结束时间
	 * 
	 * 
	 * @param date
	 * @return
	 */
	public static Date toLastMSDate(Date date) {
		if (date == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 59);
		return cal.getTime();
	}

	/**
	 * 获取当月最后一秒时间，常用于结束时间
	 * 
	 * 
	 * @param date
	 * @return
	 */
	public static Date toLastMSMonth(Date date) {
		if (date == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 59);
		return cal.getTime();
	}

	/**
	 * 相差月份
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int differMonth(Date date1, Date date2) {
		Date before = null;
		Date after = null;
		if (date1.before(date2)) {
			before = date1;
			after = date2;
		} else {
			before = date2;
			after = date1;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(before);
		int year1 = cal.get(Calendar.YEAR);
		int month1 = cal.get(Calendar.MONTH);
		cal.setTime(after);
		int year2 = cal.get(Calendar.YEAR);
		int month2 = (year2 - year1) * 12 + cal.get(Calendar.MONTH);
		return month2 - month1;
	}

	/**
	 * 转换java.util.Date为java.sql.Date，常用于数据库插入日期数据
	 * 
	 * 
	 * @param date
	 * @return
	 */
	public static java.sql.Date toSqlDate(Date date) {
		if (date == null) {
			return null;
		}

		return new java.sql.Date(date.getTime());
	}

	


	/**
	 * 获取指定路径（分区）的剩余空间大小。单位MB
	 * 
	 * @param path
	 *            "D:\\REC\\"
	 * @return
	 */
	public static long getSpaceFreeSize(String path) {

		// File file = new File("D:\\REC\\");
		File file = new File(path);
		return file.getFreeSpace() / 1024 / 1024;

	}

	/**
	 * 获取指定路径（分区）的剩余空间比例。单位%
	 * 
	 * @param path
	 *            "D:\\REC\\"
	 * @return
	 */
	public static int getSpaceFreePercent(String path) {

		// File file = new File("D:\\REC\\");
		File file = new File(path);
		int ret = 0;
		long total = file.getTotalSpace();
		long free = file.getFreeSpace();
		float per = (float) free / (float) total;
		ret = (int) (per * 100);
		return ret;

	}

	/**
	 * 获取指定路径（分区）的总空间大小。单位MB
	 * 
	 * @param path
	 *            "D:\\REC\\"
	 * @return
	 */
	public static long getSpaceTotal(String path) {
		// File file = new File("D:\\REC\\");
		File file = new File(path);
		return file.getTotalSpace() / 1024 / 1024;

	}

	/**
	 * 获取浮点型转换为字符串，保留pointNumber位小数，四舍五入
	 * 
	 * @param value
	 * @param pointNumber
	 * @return
	 */
	public static String getPointDoubleString(double value, int pointNumber) {
		StringBuffer format = new StringBuffer();
		format.append("0.");
		for (int i = 0; i < pointNumber; i++) {
			format.append("#");
		}
		DecimalFormat df = new DecimalFormat(format.toString());
		return df.format(value);
	}

	

	// StartTime=20110908134830
	// EndTime=20110908134930
	// 返回录制时间：hh:mm:ss
	public static String getDuration(String startTime, String endTime) {
		return getDuration(startTime, endTime, "yyyyMMddHHmmss");
	}

	public static String getDuration(String startTime, String endTime, String pattern) {
		String fileLen = "";
		if (startTime == null || endTime == null)
			return "";
		long len = 0;
		SimpleDateFormat ft = new SimpleDateFormat(pattern);
		try {
			Date startDate = ft.parse(startTime);
			Date endDate = ft.parse(endTime);
			len = (endDate.getTime() - startDate.getTime()) / 1000;
			NumberFormat nf = new DecimalFormat("00");
			return fileLen + nf.format((int) (len / 3600)) + ":" + nf.format((int) (len % 3600 / 60)) + ":" + nf.format((int) (len % 60));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileLen;
	}

	public static String second2TimeString(int second) {
		int minute = second / 60;
		second -= minute * 60;
		int hour = minute / 60;
		minute -= hour * 60;

		StringBuffer time = new StringBuffer();
		if (hour < 10) {
			time.append("0");
		}
		time.append(hour);
		time.append(":");
		if (minute < 10) {
			time.append("0");
		}
		time.append(minute);
		time.append(":");
		if (second < 10) {
			time.append("0");
		}
		time.append(second);
		return time.toString();
	}

	/**
	 * 删除目录
	 * 
	 * @param dir
	 */
	public static void deleteDir(File dir) {
		if (dir.isDirectory()) {
			for (File f : dir.listFiles()) {
				deleteDir(f);
			}
		}
		dir.delete();
	}

	public static void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static int[] stringToIntArray(String str) {
		String[] strs = str.split(",");
		int[] values = new int[strs.length];
		for (int i = 0; i < strs.length; i++) {
			values[i] = Integer.parseInt(strs[i].trim());
		}
		return values;
	}

	public static int getNearlyValue(int[] args, int value) {
		int min = Math.abs(value - args[0]);
		int result = args[0];
		for (int i = 0; i < args.length; i++) {
			if (Math.abs(value - args[i]) < min) {
				min = Math.abs(value - args[i]);
				result = args[i];
			}
		}
		return result;
	}

	public static String getBandWidth(int bandWidth) {
		if (bandWidth >= 1000) {
			String str = String.valueOf(bandWidth / 1000.0);
			DecimalFormat df = new DecimalFormat("###.00");
			try {
				str = df.parse(str).toString();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return str + "Mbps";
		}
		return bandWidth + "kbps";
	}

	/**
	 * 转换boolean[]为int[]
	 * 
	 * @param bs
	 * @return
	 */
	public static int[] bTointArray(boolean[] bs) {
		int[] is = new int[bs.length];
		for (int i = 0; i < bs.length; i++) {
			is[i] = (bs[i] ? 1 : 0);
		}
		return is;
	}

	/**
	 * 转换int[]为boolean[]
	 * 
	 * @param is
	 * @return
	 */
	public static boolean[] intTobArray(int[] is) {
		boolean[] bs = new boolean[is.length];
		for (int i = 0; i < is.length; i++) {
			bs[i] = is[i] == 1;
		}
		return bs;
	}

	/**
	 * 转换2,4为boolean[]={false,true,false,true,false,false}
	 * 
	 * @param indexes
	 * @param length
	 * @return
	 */
	public static boolean[] sTobArray(String indexes, int length) {
		boolean[] bs = new boolean[length];
		if (CommonTools.isNotNull(indexes)) {
			for (String index : indexes.split(",")) {
				bs[Integer.parseInt(index.trim()) - 1] = true;
			}
		}
		return bs;
	}

	public static String bTosArray(boolean[] bs) {
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < bs.length; i++) {
			if (bs[i] == true) {
				s.append(i + 1);
				s.append(",");
			}
		}
		if (s.length() > 0) {
			s.deleteCharAt(s.length() - 1);
		}
		return s.toString();
	}

	/**
	 * 转换2,4为int[]={0,1,0,1,0,0}
	 * 
	 * @param indexes
	 * @param length
	 * @return
	 */
	public static int[] sToiArray(String indexes, int length) {
		int[] bs = new int[length];
		if (CommonTools.isNotNull(indexes)) {
			for (String index : indexes.split(",")) {
				bs[Integer.parseInt(index.trim()) - 1] = 1;
			}
		}
		return bs;
	}

	/*
	 * 转 为 UTF-8
	 */
	public static String encode(String s) {
		try {
			return URLEncoder.encode(s, "UTF-8").replaceAll("[+]", "%20");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String decode(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 不够位数前面补0
	 * 
	 * @param value
	 * @param len
	 * @return
	 */
	public static String lessInsertZero(int value, int len) {
		StringBuffer s = new StringBuffer();
		s.append(value);
		for (int i = s.length(); i < len; i++) {
			s.insert(0, '0');
		}
		return s.toString();
	}

	/**
	 * 执行cmd命令
	 * 
	 * @param cmd
	 */
	public static void execCmd(String cmd) {
		StringBuffer str = new StringBuffer();
		str.append("cmd.exe /c \"").append(cmd).append("\"");
		try {
			Runtime.getRuntime().exec(str.toString());
		} catch (IOException e) {
		}
	}

	/**
	 * 执行cmd命令并返回影响字符串
	 * 
	 * @param cmd
	 * @return
	 */
	public static String getExecCmdResult(String cmd) {
		StringBuffer sb = new StringBuffer("");
		StringBuffer str = new StringBuffer();
		str.append("cmd.exe /c \"").append(cmd).append("\"");
		Process ls_proc = null;
		try {
			ls_proc = Runtime.getRuntime().exec(str.toString());
			BufferedReader in = new BufferedReader(new InputStreamReader(new DataInputStream(ls_proc.getInputStream())));
			String ss = null;
			while ((ss = in.readLine()) != null) {
				sb.append(ss).append("\n");
			}
			in.close();
		} catch (IOException e) {
		}
		return sb.toString();
	}

	/**
	 * 检查IP地址
	 * 
	 * @param strIp
	 * @return
	 */
	public static boolean isIp(String strIp) {
		return !"".equals(CheckIP(strIp));
	}

	/**
	 * 检查IP地址
	 * 
	 * @param strIP
	 * @return
	 */
	public static String CheckIP(String strIP) {
		String strResult;
		String[] str;

		str = strIP.split("\\.", 5);
		int nLen = str.length;

		if (nLen != 4)
			return "";

		if (!isNum(str[0]))
			return "";

		if (!isNum(str[1]))
			return "";

		if (!isNum(str[2]))
			return "";

		if (!isNum(str[3]))
			return "";

		int nSub = 0;
		nSub = Integer.parseInt(str[0]);
		if (nSub <= 0 || nSub > 255)
			return "";

		strResult = Integer.toString(nSub);
		strResult += ".";

		nSub = Integer.parseInt(str[1]);
		if (nSub < 0 || nSub > 255)
			return "";

		strResult += Integer.toString(nSub);
		strResult += ".";

		nSub = Integer.parseInt(str[2]);
		if (nSub < 0 || nSub > 255)
			return "";

		strResult += Integer.toString(nSub);
		strResult += ".";

		nSub = Integer.parseInt(str[3]);
		if (nSub <= 0 || nSub >= 255)
			return "";

		strResult += Integer.toString(nSub);

		return strResult;
	}

	/**
	 * 截取字符串，截取的部分用"..."替代
	 * 
	 * @param str
	 *            截取的字符串
	 * @param nCount
	 *            截取的长度
	 * 
	 * @return
	 */
	public static String getShortString(String str, int nCount) {
		String strTemp = "";
		if (str == null)
			return "";
		int nTotal = 0;

		if (str.length() <= 0) {
			return "";
		}
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) >= 128) {
				nTotal += 2;
			} else {
				nTotal++;
			}
			if (nTotal > nCount) {
				strTemp += " ...";
				break;
			} else {
				strTemp += str.charAt(i);
			}
		}
		return strTemp;
	}

	/**
	 * 检测日期或者时间
	 * 
	 * @param str
	 * @param strFormat
	 * @return
	 */
	public static boolean checkCalendar(String str, String strFormat) {
		SimpleDateFormat myFormatter = new SimpleDateFormat(strFormat);
		Calendar calNow = Calendar.getInstance();
		try {
			calNow.setTime(myFormatter.parse(str));
		} catch (ParseException ex) {
			return false;
		}
		return true;
	}

	/**
	 * 检测日期，可扩展为日期格式：xxxx年xx月xx日等格式
	 * 
	 * @param dateStr
	 * @return
	 */
	public static boolean checkDate(String dateStr) {
		return checkCalendar(dateStr, "yyyy-MM-dd");
	}

	/**
	 * 检测时间是否规范，可扩展
	 * 
	 * @param timeStr
	 * @return
	 */
	public static boolean checkTime(String timeStr) {
		return checkCalendar(timeStr, "HH:mm:ss");
	}

	/*
	 * 拼接json数组 [{value:"",name:""},{value:"",name:""}...]
	 */
	public static String jointMapJsonList(HashMap<String, String> map) {
		StringBuffer res = new StringBuffer();

		res.append("[");

		for (Map.Entry<String, String> entry : map.entrySet()) {
			res.append("{");
			res.append("'value':").append("'").append(entry.getKey()).append("',");
			res.append("'name':").append("'").append(entry.getValue()).append("'");
			res.append("}");
			res.append(",");
		}
		if (!map.isEmpty())
			res.deleteCharAt(res.length() - 1);

		res.append("]");
		return res.toString();
	}

	/**
	 * 拼接为select2select使用的json数组 {src:[{value:"",name:""},{value:"",name:""}...] ,
	 * target:[{value:"",name:""},{value:"",name:""}...]}
	 * 
	 * @param src
	 * @param target
	 * @return
	 */
	public static String select2selectTOJSON(HashMap<String, String> src, HashMap<String, String> target) {
		StringBuffer res = new StringBuffer();

		res.append("{src:");
		res.append(jointMapJsonList(src));
		res.append(",target:");
		res.append(jointMapJsonList(target));
		res.append("}");
		// System.out.println("[json]:"+res);

		return res.toString();
	}

	/**
	 * 当前时间的字符串形式，主要用于文件名
	 * 
	 * @return
	 */
	public static String getCurrentFileTimeStr() {
		Calendar calNow = Calendar.getInstance();
		return formatCalendar(calNow, "yyyyMMddHHmmss");
	}

	/**
	 * 格式化时间
	 * 
	 * @param cal
	 * @param strFormat
	 * @return
	 */
	public static String formatCalendar(Calendar cal, String strFormat) {
		SimpleDateFormat m = new SimpleDateFormat(strFormat);
		return m.format(cal.getTime());
	}

	/**
	 * 两个时间相差距离多少天多少小时多少分多少秒 added by xuzhg 20120131
	 * 
	 * @param str1
	 *            时间参数 1 格式：1990-01-01 12:00:00
	 * @param str2
	 *            时间参数 2 格式：2009-01-01 12:00:00
	 * @return String 返回值为：xx天xx小时xx分xx秒
	 * 
	 */
	public static String getDistanceTime(String str1, String str2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date one;
		Date two;
		String ret = "";
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		try {
			one = df.parse(str1);
			two = df.parse(str2);
			long time1 = one.getTime();
			long time2 = two.getTime();
			long diff;
			if (time1 < time2) {
				diff = time2 - time1;
			} else {
				diff = time1 - time2;
			}
			day = diff / (24 * 60 * 60 * 1000);
			hour = (diff / (60 * 60 * 1000) - day * 24);
			min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
			sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (day > 0) {
			ret += day + "天";
		}
		if (hour > 0) {
			ret += hour + "小时";
		}
		if (min > 0) {
			ret += min + "分";
		}
		if (sec > 0) {
			ret += sec + "秒";
		}
		return ret;
	}

	/**
	 * 验证是否访问URL地址正常
	 * 
	 * @param url
	 * @return
	 */
	public static boolean canGoToUrl(String url) {
		boolean isOK = false;
		URL u = null;
		InputStream in = null;
		try {
			u = new URL(url);
			in = u.openStream();
			BufferedReader d = new BufferedReader(new InputStreamReader(in));
			if (d.readLine() != null) {
				isOK = true;
			}
			in.close();
			in = null;
		} catch (IOException ex) {
		} finally {
			try {
				if (in != null) {
					in.close();
					in = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return isOK;
	}

	/**
	 * 获取长度为StrLength长度的随机字符串
	 * 
	 * @param strLength
	 * @return
	 */
	public static String getRandomStr(int strLength) {
		if (strLength <= 0) {
			return "";
		}
		String result = "";
		String strArr = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
		char[] ch = strArr.toCharArray();
		Random ran = new Random();
		int count = 0, index;
		while (count < strLength) {
			count++;
			index = ran.nextInt(strArr.length());
			result += ch[index];
		}
		return result;
	}





	/**
	 * 修改系统日期和时间
	 * @param date日期数组
	 * @param time时间数组
	 */
	public static boolean modifySyatemDateAndTime(String[] date, String[] time) {
		String cmd = "";
		boolean ret = true;
		try {
			if (System.getProperty("os.name").matches("^(?i)Windows.*$")) {// Window系统
				if(date != null && date.length == 3) {
					// 格式：yyyy-MM-dd
					cmd = " cmd /c date " + date[0] + "-" + date[1] + "-" + date[2];
					Runtime.getRuntime().exec(cmd);
				}
				
				if(time != null && time.length == 3) {
					// 格式 HH:mm:ss
					cmd = "  cmd /c time " + time[0] + ":" + time[1] + ":" + time[2];
					Runtime.getRuntime().exec(cmd);
				}
			} else {// Linux 系统
				if(date != null && date.length == 3) {
					// 格式：yyyy/MM/dd
					cmd = "  date -s " + date[0] + "/" + date[1] + "/" + date[2];
					Runtime.getRuntime().exec(cmd);
				}
				
				if(time != null && time.length == 3) {
					// 格式 HH:mm:ss
					cmd = "  date -s " + time[0] + ":" + time[1] + ":" + time[2];
					Runtime.getRuntime().exec(cmd);
				}
			}
		} catch (IOException e) {
			ret = false;
			e.printStackTrace();
		}
		
		return ret;
	}
	
	public static boolean restartTomcatServer() {
		String cmd = "service tomcat restart";
		boolean ret = true;
		try {
			if (System.getProperty("os.name").matches("^(?i)Windows.*$")) {// Window系统
				ret = false;
			} else {// Linux 系统
				Runtime.getRuntime().exec(cmd);
			}
		} catch (IOException e) {
			ret = false;
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * 验证是否是当天
	 * @param lessonDate
	 * @return
	 */
	public static boolean isToday(String lessonDate) {
		if (!isNotNull(lessonDate)) {
			return false;
		}
		
		Calendar now = Calendar.getInstance();
		Calendar cmp = Calendar.getInstance();
		try {
			Date d = new SimpleDateFormat("yyyy-MM-dd").parse(lessonDate);
			cmp.setTime(d);
			if (cmp.get(Calendar.YEAR) == now.get(Calendar.YEAR)
					&& cmp.get(Calendar.MONTH) == now.get(Calendar.MONTH)
					&& cmp.get(Calendar.DAY_OF_MONTH) == now.get(Calendar.DAY_OF_MONTH)) {
				return true;
			}
		} catch (ParseException e) {
			return false;
		}
		return false;
	}
	
}
