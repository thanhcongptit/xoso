package com.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GroupUtil {

	/** Item store properties * */
	private static Properties prop;

	/**
	 * @param cls
	 * @param propertyName
	 * @return properties
	 * @throws java.io.IOException
	 */
	private static java.util.Properties load(Class cls, String propertyName)
			throws java.io.IOException {
		InputStream is = cls.getResourceAsStream(propertyName);
		if (is != null) {
			java.util.Properties propers = new java.util.Properties();
			propers.load(is);
			return propers;
		} else {
			return null;
		}
	}

	/**
	 * @return properties
	 * @throws java.io.IOException
	 */
	private static Properties loadProperties() throws java.io.IOException {
		if (prop == null) {
			prop = load(GroupUtil.class, "group.properties");
		}
		return prop;
	}
	
	public static String getFolder(String folder) {
		if(folder == null) return null;
		try {
			prop = loadProperties();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop.getProperty(folder);
	}

	public static void main(String[] args) {
		System.out.println(GroupUtil.getFolder(null));		
	}
}
