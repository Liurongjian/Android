package com.codemao.android.downmanager;

import android.text.TextUtils;

import java.io.Closeable;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by rong on 2017/7/12.
 */

public class IOUtils {

	/**
	 * 关闭流
	 * @param closeables io
	 */
	public static void close(Closeable... closeables) {
		for (Closeable io : closeables) {
			if (io != null) {
				try {
					io.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * get file name from url
	 * @param url
	 * @return
	 */

	public static String getFileNameFromUrl(String url) {
		if (!TextUtils.isEmpty(url)) {
			int endIndex = url.indexOf("?");
			int startIndex = url.lastIndexOf("/") + 1;
			if(endIndex > 0 && startIndex <= endIndex) {
				return getMd5(url) + "_" +
						url.substring(url.lastIndexOf("/") + 1, endIndex);
			} else {
				return getMd5(url) + "_" +
						url.substring(url.lastIndexOf("/") + 1);
			}
		}

		return System.currentTimeMillis() + "";
	}

	private static String getMd5(String plainText) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			//32位加密
			return buf.toString();
			// 16位的加密
			//return buf.toString().substring(8, 24);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
}
