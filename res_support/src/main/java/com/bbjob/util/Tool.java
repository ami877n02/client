package com.bbjob.util;

import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tool {
	// static Log log = LogFactory.getLog(Tool.class);

	public static final String replace(String line, String oldString,
			String newString) {
		if (line == null) {
			return null;
		}
		int i = 0;
		if ((i = line.indexOf(oldString, i)) >= 0) {
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = line.indexOf(oldString, i)) > 0) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		}
		return line;
	}

	public static String[] StringPartition(String str, String sign) {
		List<String> arrayString = new ArrayList<String>();
		if (str.indexOf(sign) != -1) {
			StringTokenizer st = new StringTokenizer(str, sign);
			while (st.hasMoreTokens()) {

				arrayString.add(st.nextToken());

			}
			return arrayString.toArray(new String[arrayString.size()]);
		} else {
			return null;
		}
	}

	public Tool() {
	}

	/**
	 * ???li??????str??????????????????????????????????
	 */
	public static String[] explode(String li, String str) {
		if (str == null) {
			String[] rs = new String[0];
			return rs;
		}
		int num = 1;
		String temp = str;
		for (int i = temp.indexOf(li); i > -1; i = temp.indexOf(li)) {
			temp = temp.substring(i + li.length());
			num++;
		}
		// if (num == 1)
		// return new String[0];
		int j = 0;
		temp = str;
		String[] rs = new String[num];
		for (int i = 1; i < num; i++) {
			int p = temp.indexOf(li);
			rs[j] = temp.substring(0, p);
			temp = temp.substring(p + li.length());
			j++;
		}
		rs[j] = temp;
		return rs;
	}

	/**
	 * ???li??????str??????????????????????????????????
	 */
	public static String[] split(String li, String str) {
		if ((str == null) || (str.trim().length() == 0))
			str = null;
		return explode(li, str);
	}

	/**
	 * ???li??????str??????????????????????????????????
	 */
	public static String[] explode_new(String li, String str) {
		StringTokenizer st = new StringTokenizer(str, li);
		int rssize = 0;
		if (str.startsWith(li))
			rssize++;
		if (str.endsWith(li))
			rssize++;
		String[] rs = new String[st.countTokens() + rssize];
		int i = 0;
		if (str.startsWith(li)) {
			rs[i] = "";
			i++;
		}
		while (st.hasMoreTokens()) {
			rs[i] = st.nextToken();
			i++;
		}
		if (str.endsWith(li)) {
			rs[i] = "";
		}
		return rs;
	}

	/**
	 * Joins the elements of the provided array into a single string containing
	 * a list of CSV elements.
	 * 
	 * @param list
	 *            The list of values to join together.
	 * @param separator
	 *            The separator character.
	 * @return The CSV text.
	 */
	public static String join(String separator, String[] list) {
		if (list == null)
			list = new String[0];
		StringBuffer csv = new StringBuffer();
		for (int i = 0; i < list.length; i++) {
			if (i > 0) {
				csv.append(separator);
			}
			csv.append(list[i]);
		}
		return csv.toString();
	}

	public static String join(String separator, Collection<String> list) {
		StringBuffer csv = new StringBuffer();
		Iterator it = list.iterator();
		for (int i = 0; it.hasNext(); i++) {
			if (i > 0) {
				csv.append(separator);
			}
			csv.append((String) it.next());
		}
		return csv.toString();
	}

	/**
	 * ???str????????????????????????
	 */
	public static int StrToInt(String str) {
		int rs = 0;
		if (str != null) {
			try {
				Integer in = new Integer(str);
				rs = in.intValue();
			} catch (NumberFormatException e) {
				rs = 0;
			}
		}
		return rs;
	}

	/**
	 * ??????????????????????????????????????????????0????
	 */
	public static String getTwoDate(int rq) {
		String temp = "" + rq;
		if (rq > 0 && rq < 10)
			temp = "0" + rq;
		return temp;
	}

	/**
	 * ?????????????????????????????????????????? 2006????1????1????
	 */
	public static int subSecond(java.util.Date d1, java.util.Date d2) {

		long mss = d2.getTime() - d1.getTime();
		long ss = mss / 1000;

		return (int) ss;
	}

	public static String getDateStr() {
		Format formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.format(new Date());
	}

	/**
	 * ??????????????????????????????????????????
	 */

	public static int subDate(java.util.Date d1, java.util.Date d2) {
		// GregorianCalendar gc1= new GregorianCalendar();
		// GregorianCalendar gc2= new GregorianCalendar();
		// gc1.setTime(d1);
		// gc2.setTime(d2);
		// gc1.computFields();
		long mss = d2.getTime() - d1.getTime();
		long ss = mss / 1000;
		long ms = ss / 60;
		long hs = ms / 60;
		long ds = hs / 24;
		return (int) ds;
	}

	/**
	 * ??????????????????????????????????????????????
	 */

	public static int subDateMine(java.util.Date d1, java.util.Date d2) {
		// GregorianCalendar gc1= new GregorianCalendar();
		// GregorianCalendar gc2= new GregorianCalendar();
		// gc1.setTime(d1);
		// gc2.setTime(d2);
		// gc1.computFields();
		long mss = d2.getTime() - d1.getTime();
		long ss = mss / 1000;
		long ms = ss / 60;
		long hs = ms / 60;
		long ds = hs / 24;
		return (int) ms;
	}

	// ??????????????????????????????????
	// ??????????2002????04????25???? ??????????
	public static String get_current_date() {
		String[] week = new String[7];
		week[0] = "????";
		week[1] = "????";
		week[2] = "????";
		week[3] = "????";
		week[4] = "????";
		week[5] = "????";
		week[6] = "????";
		java.util.Date d1 = new java.util.Date();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(d1);
		return gc.get(Calendar.YEAR) + "????" + (gc.get(Calendar.MONTH) + 1) + "????"
				+ gc.get(Calendar.DAY_OF_MONTH) + "???? ??????"
				+ week[gc.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY];
	}

	public static String stringOfTime() {
		return stringOfTime(new java.util.Date());
	}

	public static String stringOfTime(java.util.Date date) {
		Format formatter = new SimpleDateFormat("HH:mm");
		return formatter.format(date);
	}

	/**
	 * ??????????????????????
	 */
	public static String stringOfDateTime() {
		return stringOfDateTime(new java.util.Date());
	}

	public static String stringOfDateTime(java.util.Date date) {
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(date);
	}

	public static String stringDateTime() {
		return stringDateTime(new java.util.Date());
	}

	public static String stringDateTime(java.util.Date date) {
		Format formatter = new SimpleDateFormat("HHmmss");
		return formatter.format(date);
	}

	// ????????????????????????????????????????,????????????????,??????????????????
	public static java.util.Date stringToDateTime(String str) {
		try {
			if (str.length() == 10) {
				str = str + " 00:00:00";
			}
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return (java.util.Date) formatter.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return new java.util.Date();
		}
	}

	// ??????????????????????????????????????????????????????????????????????????????????????????????????
	public static java.util.Date stringToDateTime2(String str) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return (java.util.Date) formatter.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return new java.util.Date("1970/01/01 ");
		}
	}

	/**
	 * ??????????????????????
	 */

	public static String stringOfCnDateTime() {
		return stringOfCnDateTime(new java.util.Date());
	}

	public static String stringOfCnDateTime(java.util.Date date) {
		Format formatter = new SimpleDateFormat("yyyy???M???d???? H???m???s????");
		return formatter.format(date);
	}

	/**
	 * ??????????????????????
	 */
	public static String stringOfCnDate() {
		return stringOfCnDate(new java.util.Date());
	}

	public static String stringOfCnDate(java.util.Date date) {
		Format formatter = new SimpleDateFormat("yyyy???M???d????");
		return formatter.format(date);
	}

	/**
	 * ?????????????????????yyyy-MM-dd
	 */
	public static String stringOfDate() {
		return stringOfDate(new java.util.Date());
	}

	public static String stringOfDate(java.util.Date date) {
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}

	/**
	 * ?????????????????????yyyy-M-d
	 */
	public static String stringOfDate2() {
		return stringOfDate2(new java.util.Date());
	}

	public static String stringOfDate2(java.util.Date date) {
		Format formatter = new SimpleDateFormat("yyyy-M-d");
		return formatter.format(date);
	}

	/**
	 * ????????????????????????????????????????????
	 */
	public static String lastDateOfMonth(int year, int month) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(Calendar.YEAR, year);
		gc.set(Calendar.MONTH, month - 1);
		int maxDate = gc.getActualMaximum(Calendar.DAY_OF_MONTH);
		gc.set(Calendar.DATE, maxDate);
		return stringOfDate(gc.getTime());
	}

	/**
	 * ????????????????????????????????????
	 */

	public static int weekOfDate(java.util.Date d1) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(d1);
		return gc.get(Calendar.DAY_OF_WEEK);
	}

	public static String amorPmTime() {

		return amorPmTime(new java.util.Date());
	}

	public static String amorPmTime(java.util.Date d1) {

		SimpleDateFormat formatter2 = new SimpleDateFormat(" a hh:mm");
		return formatter2.format(d1);
	}

	/**
	 * ????????????????????????????
	 */
	public static String weekOfDate() {
		String[] week = new String[7];
		week[0] = "????";
		week[1] = "????";
		week[2] = "????";
		week[3] = "????";
		week[4] = "????";
		week[5] = "????";
		week[6] = "????";
		return week[weekOfDate(new java.util.Date())];
	}

	/**
	 * ??????????????????????????????
	 */
	public static int dayOfDate(java.util.Date d1) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(d1);
		return gc.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * ??????????????????????????????
	 */
	public static int monthOfDate(java.util.Date d1) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(d1);
		return gc.get(Calendar.MONTH) + 1;
	}

	/**
	 * ??????????????????????????????
	 */
	public static int yearOfDate(java.util.Date d1) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(d1);
		return gc.get(Calendar.YEAR);
	}

	/**
	 * ??????????????????????????????
	 */
	public static int hourOfDate(java.util.Date d1) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(d1);
		return gc.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * ??????????????????????????????
	 */
	public static int minuteOfDate(java.util.Date d1) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(d1);
		return gc.get(Calendar.MINUTE);
	}

	/**
	 * ??????????????????????????????
	 */
	public static int secondOfDate(java.util.Date d1) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(d1);
		return gc.get(Calendar.SECOND);
	}

	/**
	 * ????????????????????????
	 */
	public static java.util.Date addDateByMonth(java.util.Date d, int mcount) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(d);
		gc.add(Calendar.MONTH, mcount);
		gc.add(Calendar.DATE, -1);
		return new java.util.Date(gc.getTime().getTime());
	}

	/**
	 * ????????????????????????
	 */
	public static java.util.Date addDateByDay(java.util.Date d, int dcount) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(d);
		gc.add(Calendar.DATE, dcount);
		return new java.util.Date(gc.getTime().getTime());
	}

	/**
	 * ????????????????????????
	 */
	public static java.util.Date addDateBySecond(java.util.Date d, int scount) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(d);
		gc.add(Calendar.SECOND, scount);
		return gc.getTime();
	}

	/**
	 * zhurx 20040224 ????????????????????????????????????
	 */
	public static java.sql.Date isTime(String shijian) {
		java.sql.Date time = null;
		try {
			time = java.sql.Date.valueOf(shijian);
			return time;
		} catch (IllegalArgumentException myException) {
			return time;
		}
	}

	/**
	 * zhurx 20040306 ??????????????????yyyy-MM-dd HH:mm:ss?????? ????????????java.util.Date
	 */

	public static java.util.Date isDateTime(String datestr) {
		// Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// return formatter.format(date);
		java.util.Date rdatetime = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			// DateFormat formatter = new DateFormat("yyyy-MM-dd HH:mm:ss");
			ParsePosition pos = new ParsePosition(0);
			rdatetime = formatter.parse(datestr, pos);
			return rdatetime;
		} catch (IllegalArgumentException myException) {
			return rdatetime;
		}

	}

	public static String HtmlSelect(String value[], String text[],
			String selected) {
		StringBuffer htmlSelect = new StringBuffer();
		try {
			for (int i = 0; i < text.length; i++) {

				if (selected != null && selected.trim().equals(value[i].trim())) {
					htmlSelect.append("<OPTION value=\"");
					htmlSelect.append(value[i]);
					htmlSelect.append("\"  selected>");
					htmlSelect.append(text[i]);
					htmlSelect.append(" </OPTION>");
				} else {
					htmlSelect.append("<OPTION value=\"");
					htmlSelect.append(value[i]);
					htmlSelect.append("\" >");
					htmlSelect.append(text[i]);
					htmlSelect.append(" </OPTION>");
				}
			}

		} catch (Exception e) {

			// TODO ???????????? catch ????
			// log.error("??????:" + e.getMessage());
		}

		return htmlSelect.toString();

	}

	/**
	 * ???double??????????????????????????????????????????????
	 */
	public static double round(double d) {
		DecimalFormat nf = new DecimalFormat("0.00");
		return Double.parseDouble(nf.format(d));
	}

	public static java.util.Date getDate() {
		return new java.util.Date();
	}

	public static String[] getYearList(int startY, int len) {
		String[] list = new String[len];
		for (int i = startY; i < len + startY; i++)
			list[i - startY] = "" + (i + 1);
		return list;
	}

	public static String[] getMonDayList(int len) {
		String[] list = new String[len];
		for (int i = 0; i < len; i++) {
			if (i < 9)
				list[i] = "0" + (1 + i);
			else
				list[i] = "" + (1 + i);
		}
		return list;
	}

	public static boolean inArray(String str, String[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals(str)) {
				return true;
			}
		}
		return false;
	}

	public static boolean inArray(Object o, List arr) {
		int size = arr.size();
		for (int i = 0; i < size; i++) {
			if (arr.get(i).equals(o)) {
				return true;
			}
		}
		return false;
	}

	

	public static void setVal(int idx, int type, ResultSet rs,
			PreparedStatement pstmt) throws SQLException {
		switch (type) {
		case Types.TINYINT:
		case Types.SMALLINT:
			pstmt.setShort(idx, rs.getShort(idx));
			break;
		case Types.INTEGER:
			pstmt.setInt(idx, rs.getInt(idx));
			break;
		case Types.BIGINT:
			pstmt.setLong(idx, rs.getLong(idx));
			break;
		case Types.BOOLEAN:
			pstmt.setBoolean(idx, rs.getBoolean(idx));
			break;
		case Types.CHAR:
		case Types.VARCHAR:
			pstmt.setString(idx, rs.getString(idx));
			break;
		case Types.DATE:
			pstmt.setDate(idx, rs.getDate(idx));
			break;
		case Types.DECIMAL:
			pstmt.setBigDecimal(idx, rs.getBigDecimal(idx));
			break;
		case Types.DOUBLE:
			pstmt.setDouble(idx, rs.getDouble(idx));
			break;
		case Types.FLOAT:
			pstmt.setFloat(idx, rs.getFloat(idx));
			break;
		case Types.NUMERIC:
			pstmt.setFloat(idx, rs.getFloat(idx));
			break;
		case Types.REAL:
			pstmt.setFloat(idx, rs.getFloat(idx));
			break;
		case Types.TIME:
			pstmt.setTime(idx, rs.getTime(idx));
			break;
		case Types.TIMESTAMP:
			pstmt.setTimestamp(idx, rs.getTimestamp(idx));
			break;
		default:
			pstmt.setString(idx, rs.getString(idx));
		}
	}

	public static String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					// System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}

	/**
	 * ???Session?????????UserInfo
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */

	// ????????????ip
	public static String getip() {
		String lname = null;
		String lip = null;
		try {
			InetAddress add = InetAddress.getLocalHost();
			lip = add.getHostAddress();
			lname = add.getHostName();
			// System.out.println(add.getHostName()+": "+add.getHostAddress());
			return lip;
		} catch (Exception e) {
			// System.out.print(e.getMessage());
			return lip;
		}
	}

	public static String getMacAddressIP(String remotePcIP) {
		String str = "";
		String macAddress = "";
		try {
			Process pp = Runtime.getRuntime().exec("nbtstat -A " + remotePcIP);
			InputStreamReader ir = new InputStreamReader(pp.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			for (int i = 1; i < 100; i++) {
				str = input.readLine();
				if (str != null) {
					if (str.indexOf("MAC Address") > 1) {
						macAddress = str.substring(
								str.indexOf("MAC Address") + 14, str.length());
						break;
					}
				}
			}
		} catch (IOException ex) {
		}
		return macAddress;
	}

	// ??????????????????????????????????
	public static String getMacAddressName(String remotePcIP) {
		String str = "";
		String macAddress = "";
		try {
			Process pp = Runtime.getRuntime().exec("nbtstat -a " + remotePcIP);
			InputStreamReader ir = new InputStreamReader(pp.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			for (int i = 1; i < 100; i++) {
				str = input.readLine();
				if (str != null) {
					if (str.indexOf("MAC Address") > 1) {
						macAddress = str.substring(
								str.indexOf("MAC Address") + 14, str.length());
						break;
					}
				}
			}
		} catch (IOException ex) {
		}
		return macAddress;
	}

	// ?????????????????????????????????????????????????????????u--???????????????l-????????????
	public static void changePathName(String path, String up) {
		// System.out.println("->->->changepathname Begin...");
		File d = new File(path); // ????????????????????????????????????????????????????????
		File lists[] = d.listFiles();
		String pathss = new String("");
		// ??????????????????????????????????????????????
		for (int i = 0; i < lists.length; i++) {
			if (lists[i].isFile()) {
				String filename = lists[i].getName();
				if (up.equals("u"))
					filename = upCase(filename);
				else
					filename = lowerCase(filename);

				String toName = new String(path + filename);
				File tempf = new File(toName);
				lists[i].renameTo(tempf);
				// System.out.println("new fullfilename is:" + toName);
			} else {
				pathss = path;
				// ??????????????????????
				pathss = pathss + lists[i].getName() + "\\";
				// ??????????????????????????
				changePathName(pathss, up);
			}
		}
		// System.out.println("->->->changepathname End...");
	}

	public static String lowerCase(String filename) {
		// System.out.println("=>to lowerCase Begin...");
		String tempstr = new String("");
		char tempch = ' ';
		for (int i = 0; i < filename.length(); i++) {
			tempch = filename.charAt(i);
			if (64 < filename.charAt(i) && filename.charAt(i) < 91)// ????????????????
				tempch += 32;
			tempstr += tempch;
		}
		// System.out.println("new filename is:" + tempstr);
		// System.out.println("=>tolowerCase End...");
		return tempstr;
	}

	public static String upCase(String filename) {
		// System.out.println("=>to upCase Begin...");
		String tempstr = new String("");
		char tempch = ' ';
		for (int i = 0; i < filename.length(); i++) {
			tempch = filename.charAt(i);
			if (97 < filename.charAt(i) && filename.charAt(i) < 122)// ????????????????
				tempch -= 32;
			tempstr += tempch;
		}
		return tempstr;
	}

	/* ?????????????????????? */
	public static String convertToChineseNumber(double number) {
		StringBuffer chineseNumber = new StringBuffer();
		boolean pand = false;
		String[] num = { "????", "????", "????", "????", "????", "????", "????", "????", "????", "????" };
		String[] unit = { "????", "????", "????", "????", "????", "????", "????", "????", "????", "????",
				"????", "????", "????", "????", "????" };
		String tempNumber = String.valueOf(Math.round((number * 100)));

		if (tempNumber != null && tempNumber.length() > 1
				&& tempNumber.charAt(0) == '-') {
			pand = true;
			tempNumber = tempNumber.substring(1, tempNumber.length());

		}
		int tempNumberLength = tempNumber.length();
		if ("0".equals(tempNumber)) {
			return "??????????";
		}
		if (tempNumberLength > 15) {
			// throw new InputException("??????????????????.");
			System.out.println(("??????????????????"));
		}
		boolean preReadZero = true; // ????????????????????????????
		for (int i = tempNumberLength; i > 0; i--) {
			if ((tempNumberLength - i + 2) % 4 == 0) {
				// ????????????????????????????????????????????????????????????????,????????????????????????????????????????????????????????????????????????????????????????
				if (i - 4 >= 0 && "0000".equals(tempNumber.substring(i - 4, i))) {
					if (!preReadZero) {
						chineseNumber.insert(0, "????");
						preReadZero = true;
					}
					i -= 3; // ???????????????????i--
					continue;
				}
				// ????????????????????????????????????????????????????????????????????????????????????????????????????????????
				preReadZero = true;
			}
			Integer digit = Integer.parseInt(tempNumber.substring(i - 1, i));
			if (digit == 0) {
				// ????????????????????????????????????????????????????????????????????????????????????????
				if (!preReadZero) {
					chineseNumber.insert(0, "????");
					preReadZero = true;
				}
				// ??????????????????????????????????????????????????????????????????????????????????????????????????????
				if ((tempNumberLength - i + 2) % 4 == 0) {
					chineseNumber.insert(0, unit[tempNumberLength - i]);
				}
			}
			// ????????????????????????????????????????????????????????????????????????????
			else {
				chineseNumber
						.insert(0, num[digit] + unit[tempNumberLength - i]);
				preReadZero = false;
			}
		}
		// ???????????????????????????????????????????????????????????????????????
		if (tempNumberLength - 2 >= 0
				&& "00".equals(tempNumber.substring(tempNumberLength - 2,
						tempNumberLength))) {
			chineseNumber.append("????");
		}
		if (pand)
			chineseNumber.insert(0, "????");
		return chineseNumber.toString();
	}

	/**
	 * ??????????????????????
	 * 
	 * @param str
	 * @return
	 */
	public static boolean pandNumber(String str) {
		int length = str.length();
		int v = 0;

		for (int i = 0; i < length; i++) {
			v = str.charAt(i);
			if (v < 49 || v > 57)
				return false;
		}
		return true;
	}

	public static String[] StringNumber(String str) {
		int length = str.length();
		char v = 0;
		StringBuffer string = new StringBuffer();
		StringBuffer number = new StringBuffer();
		for (int i = 0; i < length; i++) {
			v = str.charAt(i);
			if (v < 49 || v > 57)
				string.append(v);
			else
				number.append(v);
		}
		return new String[] { string.toString(), number.toString() };
	}

	/**
	 * MD5????????????
	 * 
	 * @param s
	 * @return
	 */
	public final static String MD5(String s) {
		if (s == null)
			return null;
		char hexDigits[] = { 'D', 'E', 'F', '0', 'A', 'B', 'C', '1', '2', '3',
				'4', '5', '6', '7', '8', '9' };
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
			e.printStackTrace();
			return null;
		}
	}

	public static Object convert(String value, Class t)

	{
		if (value == null) {
			if (t.equals(java.lang.Boolean.class) || t.equals(Boolean.TYPE)) {
				value = "false";
				return new Boolean(value);

			} else {
				return null;
			}
		}

		if (t.equals(java.lang.Boolean.class) || t.equals(Boolean.TYPE)) {

			if (value.equals("1") || value.equalsIgnoreCase("on")
					|| value.equalsIgnoreCase("true"))
				value = "true";
			else
				value = "false";

			return new Boolean(value);
		}

		if (t.equals(java.lang.Byte.class) || t.equals(Byte.TYPE))
			return new Byte(value);
		if (t.equals(java.lang.Character.class) || t.equals(Character.TYPE))
			return value.length() <= 0 ? null : new Character(value.charAt(0));
		if (t.equals(java.lang.Short.class) || t.equals(Short.TYPE))
			return new Short(value);
		if (t.equals(java.lang.Integer.class) || t.equals(Integer.TYPE))
			return new Integer(value);
		if (t.equals(java.lang.Float.class) || t.equals(Float.TYPE))
			return new Float(value);
		if (t.equals(java.lang.Long.class) || t.equals(Long.TYPE))
			return new Long(value);
		if (t.equals(java.lang.Double.class) || t.equals(Double.TYPE))
			return new Double(value);
		if (t.equals(java.lang.String.class))
			return value;
		if (t.equals(java.io.File.class))
			return new File(value);
		return null;

	}

	public static int maxIntArray(int[] args) {
		if (args == null)
			return 0;
		int max = 0;
		for (int i = 0; i < args.length; i++) {
			max += args[i];

		}
		return max;
	}

	public static boolean ArrayCheck(String[] Array, String str) {
		if (Array == null)
			return false;
		for (int i = 0; i < Array.length; i++) {
			if (str == null || Array[i] == null) {
				return false;
			} else {
				if (str.trim().equals(Array[i].trim()))
					return true;
			}

		}
		return false;
	}

	public static boolean ArrayCheck(List Array, Object str) {
		if (Array == null)
			return false;
		int size = Array.size();
		for (int i = 0; i < size; i++) {
			if (str == null || Array.get(i) == null) {
				return false;
			} else {
				if (str.equals(Array.get(i)))
					return true;
			}

		}
		return false;
	}

	public static String ObjectOutput(Object Object) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos;

		try {
			oos = new ObjectOutputStream(baos);
			oos.writeObject(Object);
			String out = new String(baos.toByteArray());

			return out;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static Object ObjectInput(String str) {

		try {
			ByteArrayInputStream input = new ByteArrayInputStream(
					str.getBytes());
			ObjectInputStream in = new ObjectInputStream(input);
			return in.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static void invoke(PropertyDescriptor pd[], String parameterName,
			String value, Object o) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {

		for (int j = 0; j < pd.length; j++) {

			if (parameterName.equalsIgnoreCase(pd[j].getName())) {
				pd[j].getWriteMethod().invoke(o,
						Tool.convert(value, pd[j].getPropertyType()));
				break;
			}

		}
	}

	public static long getQuot(String time1, String time2) {
		long quot = 0;
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date1 = ft.parse(time1);
			Date date2 = ft.parse(time2);
			quot = date1.getTime() - date2.getTime();
			quot = quot / 1000 / 60 / 60 / 24;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return quot;
	}

	public static String NumberFormat(double c) {
		DecimalFormat format = new DecimalFormat

		("#0.00");
		String formatedNumber = format.format(c);

		return formatedNumber;
	}

	/**
	 * ???diff????????????????????????????
	 * 
	 * @param diff
	 *            ????????????
	 * @param aorb
	 *            "a"??????????"b"??????
	 * @return
	 */
	public static String dateOfSomeDayDiff(int diff, String aorb) {
		String dd = "";
		Date date = null;
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		long day = (long) diff * 24 * 60 * 60 * 1000;
		long current = System.currentTimeMillis();
		if ("a".equalsIgnoreCase(aorb))
			date = new Date(current + day);
		if ("b".equalsIgnoreCase(aorb))
			date = new Date(current - day);
		return dd = ft.format(date);
	}

	/**
	 * ????????????????????????????
	 * 
	 * @param resource
	 *            ??????
	 * @param length
	 *            ??????
	 * @return
	 */

	public static String fixedLengthStringNull(String resource, int length) {
		if (resource.length() < length)
			return resource;

		return resource.subSequence(0, length) + "";
	}

	public static String fixedLengthString(String resource, int length) {
		return fixedLengthStringNull(resource, length) + "...";
	}

	public static String replaceToNull(String resource, String[] replace) {
		if (null == replace)
			return resource;
		String temp = resource;
		for (int i = 0; i < replace.length; i++) {
			temp = temp.replaceAll(replace[i], " ");
		}
		return temp;
	}

	public static String getSoftsize(long size) {
		float f = 0f;
		String tmp = "";
		if (size > 1024 * 1024) {
			f = (float) size / 1024 / 1024;
			tmp = "MB";
		} else if (size > 1024) {
			f = (float) size / 1024;
			tmp = "KB";
		} else {
			f = (float) size;
			tmp = "B";
		}

		java.text.DecimalFormat df = new DecimalFormat(".##");
		tmp = (String) df.format(f) + tmp;
		return tmp;
	}

	/**
	 * ??????html??????????????????????????????
	 * 
	 * @param inputString
	 * @return String
	 */
	public static String htmlTagFilter(String inputString) {
		if (null == inputString || "".equals(inputString.trim()))
			return "";
		String htmlStr = inputString; // ???html??????????????????
		String textStr = "";
		Pattern p_script;
		Matcher m_script;
		Pattern p_style;
		Matcher m_style;
		Pattern p_html;
		Matcher m_html;

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // ??????script??????????????????{????<script[^>]*?>[\\s\\S]*?<\\/script>
																										// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // ??????style??????????????????{????<style[^>]*?>[\\s\\S]*?<\\/style>
																									// }
			String regEx_html = "<[^>]+>"; // ??????HTML????????????????????????

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // ??????script??????

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // ??????style??????

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // ??????html??????

			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr;// ??????????????????????
	}

	/**
	 * ??????????????????????????????
	 * 
	 * @return
	 */
	public static String getOsName() {
		String osname = "";
		osname = System.getProperty("os.name");
		return osname;
	}

	public static String getLsh(int lshLength, int no) {
		int len = lshLength - String.valueOf(no).length();
		String temp = "";
		for (int i = 0; i < len; i++) {
			temp += "0";
		}
		temp += no;
		return temp;
	}

	

	public static String filterEndWithZero(BigDecimal dec) {
		BigDecimal dec45 = dec.setScale(4, BigDecimal.ROUND_HALF_UP);
		String dec_str = String.valueOf(dec45);

		String decArr[] = dec_str.split("\\.");
		String decInt = decArr[0];
		String decPoint = decArr[1];
		int len = decPoint.length();
		for (int i = 0; i < 4; i++) {
			if (decPoint.endsWith("0")) {
				len = len - 1;
				decPoint = decPoint.substring(0, len);
			} else {
				break;
			}

		}
		decPoint = decPoint.substring(0, len);

		String reStr = "";
		if (decPoint.length() == 0) {
			reStr = decInt;
		} else {
			reStr = decInt + "." + decPoint;
		}
		return reStr;
	}

	public static List keepCount(List list, int maxCount) {
		if (list == null) {
			return new ArrayList();
		} else if (list.size() <= maxCount) {
			return list;
		} else {
			List res = new ArrayList();
			for (int i = 0; i < maxCount; i++) {
				res.add(list.get(i));
			}
			return res;
		}
	}

	/*
	 * ????????????????????????
	 */
	public String getCurrentYearFirst() {
		int yearPlus = this.getYearPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, yearPlus);
		Date yearDay = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preYearDay = df.format(yearDay);
		return preYearDay;
	}

	private int getYearPlus() {
		Calendar cd = Calendar.getInstance();
		int yearOfNumber = cd.get(Calendar.DAY_OF_YEAR);// ????????????????????????????????????
		cd.set(Calendar.DAY_OF_YEAR, 1);// ????????????????????????????????
		cd.roll(Calendar.DAY_OF_YEAR, -1);// ??????????????????????????
		int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
		if (yearOfNumber == 1) {
			return -MaxYear;
		} else {
			return 1 - yearOfNumber;
		}
	}

	/*
	 * ??????????????????????????
	 */
	public String getCurrentYearEnd() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// ??????????????????????????????????
		String years = dateFormat.format(date);
		return years + "-12-31";
	}

	
	
	/**
	 * 
	 * @param i ????????????????
	 * @return String??????????
	 */
	public  static String getRandomString(Integer i){
		String num="";
		for(int j=0;j<i;j++){
			Random r=new Random();
			num += r.nextInt(9);
		 }
		 return num;
		}
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replaceAll("-", "");
		return uuid.toUpperCase();
	}

}
