package com.web.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;

public class ReflectUtil {

	/**
	 * 获取类的属性集合
	 * 
	 * @param clazzName
	 * @throws ClassNotFoundException
	 * @return List<String>
	 */
	public static List<String> getClassFields(String clazzName)
			throws ClassNotFoundException {
		List<String> fieldList = new ArrayList<String>();
		Class<?> classType = Class.forName(clazzName);
		Field[] fields = classType.getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field.getName());
			fieldList.add(field.getName());
		}
		return fieldList;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(Map<String, Object> param, Class<T> clazz)
			throws ParseException {
		Object value = null;
		Class<T>[] paramTypes = new Class[1];
		Object obj = null;

		try {
			// 创建实例
			obj = clazz.newInstance();
			Field[] f = clazz.getDeclaredFields();
			List<Field[]> flist = new ArrayList<Field[]>();
			flist.add(f);

			Class<T> superClazz = (Class<T>) clazz.getSuperclass();
			while (superClazz != null) {
				f = superClazz.getFields();
				flist.add(f);
				superClazz = (Class<T>) superClazz.getSuperclass();
			}

			for (Field[] fields : flist) {
				for (Field field : fields) {
					String fieldName = field.getName();
					value = param.get(fieldName);
					if (value != null) {
						paramTypes[0] = (Class<T>) field.getType();
						Method method = null;
						// 调用相应对象的set方法
						StringBuffer methodName = new StringBuffer("set");
						methodName.append(fieldName.substring(0, 1)
								.toUpperCase());
						methodName.append(fieldName.substring(1,
								fieldName.length()));
						method = clazz.getMethod(methodName.toString(),
								paramTypes);
						method.invoke(obj,
								getValue(value.toString(), paramTypes[0]));
					}
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return (T) obj;
	}

	/**
	 * 
	 * @param value
	 * @param clazz
	 * @return T
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getValue(String value, Class<T> clazz)
			throws ParseException {
		if (value == null) { // 如果值为null,则返回null
			return null;
		} else if (value.equals("")
				&& !clazz.getName().equals(String.class.getName())) { // 如果value值为"",而且要转为的类型不是string类型，那么就统一返回null，也就是空字符串不能转成任何其他类型的实体，只能返回null
			return null;
		} else if (Date.class.getName().equalsIgnoreCase(clazz.getName())) { // 增加对从String类型到Date
			return (T) DateUtil.getDateByPattern(value, DateUtil.YYMMDDHHMMSS);
		}
		return (T) ConvertUtils.convert(value, clazz);
	}

}
