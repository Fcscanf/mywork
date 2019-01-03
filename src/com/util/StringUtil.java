/*
 * Copyleft 2011 Power by colen.
 *
 * Project: booking
 * Date: Jul 2, 2011
 */
package com.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

/**
 * String Util Class
 *
 * @author colen
 */
public final class StringUtil extends StringUtils {

    /** Private Constructor */
    private StringUtil() {
    }

    /**
     * To String
     *
     * @param obj Object.
     * @return string
     */
    public static String toStr(Object obj) {
        return (obj != null) ? obj.toString() : null;
    }

    /**
     * Is Trim Empty
     *
     * @param s String
     * @return true\false
     */
    public static boolean isTrimEmpty(String s) {
        return isEmpty(s) ? true : isEmpty(s.trim());
    }
 
    /**
     * Remove repeated Element.
     *
     * @param arr Source Array
     * @return string array
     */
    public static String[] removeRepeat(String[] arr) {
        if (isEmpty(arr)) {
            return null;
        }
        Set<String> set = new LinkedHashSet<String>();
        for (String s : arr) {
            set.add(s);
        }
        // OUT
        return set.toArray(new String[0]);
    }

    
    public static boolean isEmpty(List l) {
        return (l == null || l.isEmpty()) ? true : false;
    }

    /**
     * Is Empty Array
     *
     * @param objs Objects
     * @return boolean
     */
    public static boolean isEmpty(Object[] objs) {
        return (objs == null || objs.length == 0) ? true : false;
    }
    
    /**
     * Make a String by String array
     *
     * @param arr Array.
     * @param spliter Split
     * @return
     */
    public static String makeString(String[] arr, String spliter) {
        StringBuilder bud = new StringBuilder();
        for (String a : arr) {
            bud.append(a).append(spliter);
        }
        return bud.substring(0, bud.length() - spliter.length());
    }

    /**
     * SubStr.
     *
     * @param s
     * @param len
     * @return
     */
    public static String substr(String s, int len) {
        if (isEmpty(s) || s.length() <= len) {
            return s;
        }
        return s.substring(0, len);
    }
    
    /**
     * cookie存储中文时，可先把存储的中文用utf-8编码一下
     * @param str
     */
    public static String encodeByUTF8(String str) {
    	try {
			str = URLEncoder.encode(str, "utf-8");
		} catch (UnsupportedEncodingException e) {}
    	return str;
    }
    
    
    /**
     * encode xml
     *
     * @param data
     * @return
     */
    public static String encodeXml(String data) {
        return data.replaceAll("&", "&amp;")
                .replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;")
                .replaceAll("\"", "&quot;")
                .replaceAll("'", "&apos;");
    }
    
    
    /**
     * decode xml
     *
     * @param data
     * @return
     */
    public static String decodeXml(String data) {
        return data.replaceAll("&lt;", "<")
                .replaceAll("&gt;", ">")
                .replaceAll("&quot;", "\"")
                .replaceAll("&amp;", "&")
                .replaceAll("&apos;", "'");
    }
    
    
    public static String replace(String src, String regex, String replace) {
        if (isTrimEmpty(src)) {
            return src;
        }
        return src.replaceAll(regex, replace);
    }
    
    
    public static void main(String[] args) {
        String orgData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><StaffBindRsp><HEAD><CODE>StaffBind</CODE><SID>:sid</SID><TIMESTAMP>:timestamp</TIMESTAMP><SERVICEID>:serviceid</SERVICEID></HEAD><BODY>#msg_body#</BODY></StaffBindRsp>";
        System.out.println("orgdata: " + orgData);
        
        String encodeData = encodeXml(orgData);
        System.out.println("encodeData: " + encodeData);
        
        String decodeData = decodeXml(encodeData);
        System.out.println("decodeData: " + decodeData);
    }
}


