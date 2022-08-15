package com.example.demo.common.util;

import java.lang.reflect.Method;

import com.example.demo.common.constant.PathSetting;

public class RefrectionUtil {

	private RefrectionUtil() {}
	
	public static Class<?> getDtoClazz(String clazzName) throws Exception{
		if (clazzName != null) {
			String dtoClazzName = StringUtil.convertFirstToUpperCase(clazzName);
			String headerPath = dtoClazzName.contains("Header") ? ".header." : ".";
			return Class.forName(PathSetting.DTO_PATH + headerPath + dtoClazzName);
		}

		return null;
	}

	public static Method getDtoClazzMethod(String clazzName, String fieldName) throws Exception {
		if (clazzName != null && fieldName != null) {
			Class<?> dtoClazz = RefrectionUtil.getDtoClazz(clazzName);
			Method method = dtoClazz.getMethod("get" + StringUtil.convertFirstToUpperCase(fieldName));
			return method;
		}

		return null;
	}
}
