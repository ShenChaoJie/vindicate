package org.frame.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.helpu16.pinying.PYString;

public class MyUtil {
	private static MyUtil huu = new MyUtil();
	
	public static Map<String, String> mpMap = new HashMap<String, String>();
	private static String DEFAULT_MOBILE = "0000000";

	public static MyUtil getInstance() {
		return huu;
	}

	private MyUtil() {}

	public String getRandom(int length) {
		String seed = "abcd2wefg3da45678hijklmnopqrstuvwxyzABcdhnCDsxqaEFGHIJKLMNOPQRSTUVWXYZ"
				+ System.currentTimeMillis();
		String orderNo = "";
		SecureRandom random = new SecureRandom();
		for (int i = 0; i < length; i++) {
			int randnumber = random.nextInt(seed.length());
			String strRand = seed.substring(randnumber, randnumber + 1);
			orderNo += strRand;
		}
		return orderNo;
	}

	/**
	 * 补零
	 * 
	 * @param n
	 * @param length
	 * @return
	 */
	public String addZore(Integer n, int length) {
		StringBuffer t = new StringBuffer("");
		for (int i = 0; i < length - n.toString().length(); i++) {
			t.append("0");
		}
		return t.toString() + n.toString();
	}

	public Object invokeMethod(Object obj, String methodName, Object[] args) throws Exception {
		Reflection rf = Reflection.getInstance();
		return rf.invokeMethod(obj, methodName, args);
	}

	public Object invokeMethodValuNull(Object owner, String methodName,	Class arg) throws Exception {
		Reflection rf = Reflection.getInstance();
		return rf.invokeMethodValuNull(owner, methodName, arg);
	}

	/**
	 * 判断字符串是否为
	 * 
	 * @param str
	 * @return
	 */
	public boolean isNullEmpty(String str) {
		return org.apache.commons.lang.StringUtils.isEmpty(str);
	}

	/**
	 * 分割字符
	 * 
	 * @param str
	 * @return
	 */
	private final static char splitChar = 6;

	public String[] splitStr(String str) {
		return str.split("" + splitChar);
	}

	public Date getDate(String date) {
		String[] dates = StringUtils.split(date, "-");
		GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance();
		gc.set(new Integer(dates[0]).intValue(),
				new Integer(dates[1]).intValue() - 1,
				new Integer(dates[2]).intValue());
		return gc.getTime();
	}

	public Date getAllDatePart(String date) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(date);
	}

	public String getDateyyyMMdd(String date) {
		return formateDate(getDate(date), "yyyy-MM-dd");
	}
	
	public String getDateyyyMMdd(Date date) {
		return formateDate(date, "yyyy-MM-dd");
	}

	public Date TimestampToDate(java.sql.Timestamp tt) {
		GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance();
		gc.setTimeInMillis(tt.getTime());
		return gc.getTime();
	}

	public String formateDate(Date date, String pattern) {
		return org.apache.commons.lang.time.DateFormatUtils.format(date,pattern);
	}

	public void map2Object(Map<String, String> para, Object obj)
	throws Exception {
		java.lang.reflect.Field[] fs = obj.getClass().getDeclaredFields();
		String ftype = "";
		Object value;
		for (java.lang.reflect.Field f : fs) {
			if(f.getName().toLowerCase().equals("ctime")){
				invokeMethod(obj, getSeterr(f.getName()),new Object[] { this.getCurrenTime() });
			}
			if (!para.containsKey(f.getName()))continue;
			if (isNullEmpty(para.get(f.getName())))continue;
			ftype = f.getType().toString().toLowerCase();
			value = para.get(f.getName());
			if (ftype.indexOf("string") != -1) {
				invokeMethod(obj, getSeterr(f.getName()),new Object[] { value });
				continue;
			}
			if (ftype.indexOf("long") != -1) {
				if (!isNumber(value.toString()))continue;
				value = Long.valueOf(value.toString());
				invokeMethod(obj, getSeterr(f.getName()),new Object[] { value });
				continue;
			}
			if (ftype.indexOf("date") != -1) {
				// value=getDate(value.toString());
				value = getAllDatePart(value.toString());
				invokeMethod(obj, getSeterr(f.getName()),new Object[] { value });
				continue;
			}
			if (ftype.indexOf("integer") != -1) {
				if (!isNumber(value.toString()))continue;
				value = Integer.valueOf(value.toString());
				invokeMethod(obj, getSeterr(f.getName()),new Object[] { value });
				continue;
			}
			if (ftype.indexOf("double") != -1) {
				if (!isNumber(value.toString()))continue;
				value = Double.valueOf(value.toString());
				invokeMethod(obj, getSeterr(f.getName()),new Object[] { value });
				continue;
			}
		}
	}
	private String getSeterr(String f) {
		return "set" + f.substring(0, 1).toUpperCase() + f.substring(1, f.length());
	}

	public boolean isAllLetter(String inString) {
		Pattern p = Pattern.compile("[a-zA-Z]+");
		Matcher m = p.matcher(inString);
		return m.matches();
	}

	public boolean isNumber(String number) {
		return org.apache.commons.lang.math.NumberUtils.isNumber(number);
	}

	public boolean isMobile(String mobile) {
		Pattern p = Pattern.compile("[+]?[0-9]{11,15}");
		Matcher m = p.matcher(mobile);
		return m.matches();
	}

	public boolean isPhone(String phone) {
		Pattern p = Pattern.compile("[0][1-9]{2,3}[-]?[0-9]{6,9}");
		Matcher m = p.matcher(phone);
		return m.matches();
	}

	public boolean isZip(String zip) {
		Pattern p = Pattern.compile("[0-9]{5,8}");
		Matcher m = p.matcher(zip);
		return m.matches();
	}

	public boolean isEmail(String email) {
		Pattern p = Pattern.compile("\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		Matcher m = p.matcher(email);
		return m.matches();
	}

	public boolean isMemberName(String name) {
		Pattern p = Pattern.compile("[a-zA-Z0-9_]{3,15}");
		Matcher m = p.matcher(name);
		return m.matches();
	}

	public boolean isPs(String name) {
		Pattern p = Pattern.compile("[a-zA-Z0-9]{6,20}");
		Matcher m = p.matcher(name);
		return m.matches();
	}
	
	public boolean isURL(String url) {
		Pattern p = Pattern.compile("(http[s]?|ftp):\\/\\/[^\\/\\.]+?\\..+\\w");
		Matcher m = p.matcher(url);
		return m.matches();
	}

	/**
	 * @return 得到工程当前目录
	 */
	public String getRealPath() {
		String path = this.getClass().getResource("").getPath();
		path = path.substring(1, path.indexOf("WEB-INF/"));
		if(File.separator.equals("/")) return "/"+path;
		return path;
	}

	/**
	 * 把文本编码为Html代码
	 * 
	 * @param target
	 * @return 编码后的字符
	 */
	public String htmEncode(String target) {
		StringBuffer stringbuffer = new StringBuffer();
		int j = target.length();
		for (int i = 0; i < j; i++) {
			char c = target.charAt(i);
			switch (c) {
			case 60:
				stringbuffer.append("&lt;");
				break;
			case 62:
				stringbuffer.append("&gt;");
				break;
			case 38:
				stringbuffer.append("&amp;");//&
				break;
			case 34:
				stringbuffer.append("&quot;");
				break;
			case 169:
				stringbuffer.append("&copy;");
				break;
			case 174:
				stringbuffer.append("&reg;");
				break;
			case 165:
				stringbuffer.append("&yen;");
				break;
			case 8364:
				stringbuffer.append("&euro;");
				break;
			case 8482:
				stringbuffer.append("&#153;");
				break;
			case 13:
				if (i < j - 1 && target.charAt(i + 1) == 10) {
					stringbuffer.append("<br>");
					i++;
				}
				break;
			case 32:
				if (i < j - 1 && target.charAt(i + 1) == ' ') {
					stringbuffer.append(" &nbsp;");
					i++;
					break;
				}
			default:
				stringbuffer.append(c);
				break;
			}
		}
		return new String(stringbuffer.toString());
	}

	/**
	 * 把时间转为标准形�?	 * 
	 * @param dt
	 * @return 转换后的形式
	 */
	public static String toString(Date dt, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String str = sdf.format(dt);
		return str;
	}

	public static Date toDate(String dt) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = null;
		try {
			d = df.parse(dt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
	
	
	public void copyFile(File from,File to) throws Exception{
		FileChannel inC=new FileInputStream(from).getChannel();
        FileChannel outC=new FileOutputStream(to).getChannel();
        ByteBuffer b=null;
        int length=1024*1024*2;
        while(true){
            if(inC.position()==inC.size()){
                inC.close();
                outC.close();
                return;
            }
            if((inC.size()-inC.position())<length){
                length=(int)(inC.size()-inC.position());
            }else
                length=2097152;
            b=ByteBuffer.allocateDirect(length);
            inC.read(b);
            b.flip();
            outC.write(b);
            outC.force(false);
        }
	}
	public String getHzPy(String hz) throws Exception{
    if(isNullEmpty(hz))return "";
    String hanZi = this.getHanzi(hz);
    String pY = "";
    if(isNullEmpty(hanZi)){
        pY = hz;
    } else{
        for(int i = 0; i < hz.length(); i++)
            if(getHanziCode(hz.substring(i, i + 1)) == -1)
                pY = (new StringBuilder(String.valueOf(pY))).append(hz.substring(i, i + 1)).toString();
            else
                pY = (new StringBuilder(String.valueOf(pY))).append(PYString.getHzSm(hz.substring(i, i + 1))).toString();
    }
    return pY;
}

	public  int getHanziCode(String strHanzi) throws Exception{
    byte bytes[] = strHanzi.getBytes("gbk");
    if(bytes == null || bytes.length > 2 || bytes.length <= 0 || bytes.length == 1)
        return -1;
    if(bytes.length == 2){
        int hByte;
        if(bytes[0] < 0)
            hByte = 256 + bytes[0];
        else
            hByte = bytes[0];
        int lByte;
        if(bytes[1] < 0)
            lByte = 256 + bytes[1];
        else
            lByte = bytes[1];
        int iHanzi = (256 * hByte + lByte) - 0x10000;
        return iHanzi;
    } else{
        return -1;
    }
}
	public  String getHanzi(String inString) throws Exception{
	    String hzs[] = new String[inString.length()];
	    int hzsIndex = 0;
	    for(int i = 0; i < inString.length(); i++)
	        if(getHanziCode(inString.substring(i, i + 1)) != -1){
	            hzs[hzsIndex] = inString.substring(i, i + 1);
	            hzsIndex++;
	        }
	    String rhz = "";
	    for(int i = 0; i < hzs.length; i++)
	        if(!isNullEmpty(hzs[i]))
	            rhz = (new StringBuilder(String.valueOf(rhz))).append(hzs[i]).toString();
	    return rhz;
	}	
	public Date getCurrenTime(){
		return java.util.Calendar.getInstance().getTime();
	}
	
	private static final ObjectMapper mapper = new ObjectMapper();
	public String jackSon(Object object){
		StringWriter writer = new StringWriter();
        try {
            mapper.writeValue(writer, object);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            return null;
        }
        return writer.toString();
		
	}
	
	
	
	/**  
	   * 得到几天前的时间  
	   * @param d  
	   * @param day  
	   * @author xinwang.xu
	   * @return  
	   */  
	  public Date getDateBefore(Date d,int day){   
	   Calendar now =Calendar.getInstance();   
	   now.setTime(d);   
	   now.set(Calendar.DATE,now.get(Calendar.DATE)-day);   
	   return now.getTime();   
	  }   
	     
	  /**  
	   * 得到几天后的时间  
	   * @param d  
	   * @param day  
	   * @author xinwang.xu
	   * @return  
	   */  
	  public Date getDateAfter(Date d,int day){   
	   Calendar now =Calendar.getInstance();   
	   now.setTime(d);   
	   now.set(Calendar.DATE,now.get(Calendar.DATE)+day);   
	   return now.getTime();   
	  }  

	  /**
	   * 功能：得到日期的星期
	   * 1：星期一  ...7:星期�?
	   * @author xinwang.xu
	   * @date 2011-6-13 下午04:01:13
	   * @param d
	   * @return
	   */
	  public Integer getDayOfWeek(Date d){
		  Calendar now = Calendar.getInstance();
		  now.setTime(d);
		  int myWeek = now.get(Calendar.DAY_OF_WEEK)-1;//�?為生活常用的星期
		  if(myWeek==0)//如果�?星期天改�?
			  myWeek=7;
		  return myWeek;
	  }
	  
	  /**
	   * 功能：返回月份中的某�?��
	   * @author xinwang.xu
	   * @date 2011-6-13 下午04:15:21
	   * @param d
	   * @return
	   */
	  public Integer getDayOfMonth(Date d){
		  Calendar now = Calendar.getInstance();
		  now.setTime(d);
		  return now.get(Calendar.DAY_OF_MONTH);
	  }	  
	  
	  /**
	   * TODO MyUtil
	   * @author yali.guo
	   * @version 1.0
	   * <p><b>功能说明�?/b>删除文件夹下�?��的文件和目录</p>
	   * <p><b>创建时间�?/b>2011-7-12</p>
	   * <p><b>修改时间�?/b>2011-7-12</p>
	   */
	public void delAllFile(String path){
		  File file=new File(path);
			String[] files=file.list();
			for(String f:files){
				File temp=new File(path+File.separator+f);
				if(temp.isFile()){
					temp.delete();
				}
				if(temp.isDirectory()){
					delAllFile(path+File.separator+f);
					temp.delete();
				}
			}
	  }
	public Integer doubleToInt(Double d){
		if(null==d) return null;
		if(d.toString().lastIndexOf(".")>-1)
			return Integer.parseInt(d.toString().substring(0, d.toString().lastIndexOf(".")));
		else return Integer.parseInt(d.toString());
	}
	
	/**
	 * 根据手机号码号取得省份
	 * @param mobile
	 * @return
	 */
	public String getProvince(String mobile) {
		String province = null;
		mobile = mobile.substring(0, 7);
		province = mpMap.get(mobile);
		if (province == null) {
			province = mpMap.get(DEFAULT_MOBILE);
		}
		return province;
	}

	/**
	 * 根据手机号码取得前三位
	 * @param mobile
	 * @return
	 */
	public static String getMobileToStr3(String mobile) {
		String mobileStr3 = "0";
		if (StringUtils.isNotBlank(mobile)) {
			mobile = mobile.trim();
			if (mobile.length() >= 3) {
				if (StringUtils.isNumeric(mobile)) {
					mobileStr3 = mobile.substring(0, 3);
				}
			}
		}
		return mobileStr3;
	}
    private String getGeterr(String f) {
		return "get" + f.substring(0, 1).toUpperCase() + f.substring(1, f.length());
	}
    
    public static String convString(String str){
    	if(str == null){
    		return "";
    	}
    	return str;
    }
    
	/**
	 * 
	 * 功能：将127.0.0.1形式的IP地址转换成十进制整数，这里没有进行任何错误处理
	 * 
	 * @author zjy
	 * @param strIp
	 * @return
	 */
	public  long ipToLong(String strIp) {
		long[] ip = new long[4];
		// 先找到IP地址字符串中.的位置
		int position1 = strIp.indexOf(".");
		int position2 = strIp.indexOf(".", position1 + 1);
		int position3 = strIp.indexOf(".", position2 + 1);
		// 将每个.之间的字符串转换成整型
		ip[0] = Long.parseLong(strIp.substring(0, position1));
		ip[1] = Long.parseLong(strIp.substring(position1 + 1, position2));
		ip[2] = Long.parseLong(strIp.substring(position2 + 1, position3));
		ip[3] = Long.parseLong(strIp.substring(position3 + 1));
		return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
	}

	/**
	 * 
	 * 功能：将十进制整数形式转换成127.0.0.1形式的ip地址
	 * 
	 * @author zjy
	 * @param longIp
	 * @return
	 */
	public  String longToIP(long longIp) {
		StringBuffer sb = new StringBuffer("");
		// 直接右移24位
		sb.append(String.valueOf((longIp >>> 24)));
		sb.append(".");
		// 将高8位置0，然后右移16位
		sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
		sb.append(".");
		// 将高16位置0，然后右移8位
		sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
		sb.append(".");
		// 将高24位置0
		sb.append(String.valueOf((longIp & 0x000000FF)));
		return sb.toString();
	}
    
}
