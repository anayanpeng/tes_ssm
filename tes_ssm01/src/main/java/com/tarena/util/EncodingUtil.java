package com.tarena.util;

import java.io.UnsupportedEncodingException;

/**
 * 此类是编码转换的工具类
 * @author adminitartor
 *
 */
public class EncodingUtil {
	public static String encode(String srcString,String srcEncoding,String destEncoding){
		String str=null;
	    try {
			str=new String(srcString.getBytes(srcEncoding),destEncoding);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	public static String encode(String srcString,String destEncoding){
		String str=null;
	    try {
			str=new String(srcString.getBytes("ISO8859-1"),destEncoding);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	public static String encode(String srcString){
		String str=null;
	    try {
			str=new String(srcString.getBytes("ISO8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
}