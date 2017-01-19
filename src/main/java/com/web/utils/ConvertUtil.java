package com.web.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import com.web.config.BizExpDictionary;
import com.web.exception.BizException;

public class ConvertUtil {
	/**
	 * 将request中的参数封装成
	 * 
	 * @param voClazz
	 * @param request
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <E> E requestToBean(Class clazz, HttpServletRequest request) {
		Object obj = null;
		try {
			obj = clazz.newInstance();
			Enumeration<String> params = request.getParameterNames();
			while (params.hasMoreElements()) {
				String paramName = params.nextElement();
				if ("action".equals(paramName) || "pageNo".equals(paramName)
						|| "pageSize".equals(paramName)) {
					continue;
				}
				Field field = clazz.getDeclaredField(paramName);
				field.setAccessible(true);
				String type = field.getGenericType().toString();
				String paramValue = request.getParameter(paramName);
				if (StringUtil.isBlank(paramValue)) {
					if (type.equals("class java.util.Date")) {
						field.set(obj, DateUtil.getDateByPattern(paramValue,
								DateUtil.YYMMDDHHMMSS));
					} else if (type.equals("class java.math.BigDecimal")) {
						field.set(obj, new BigDecimal(paramValue));
					} else if (type.equals("class java.lang.Integer")) {
						field.set(obj, new Integer(paramValue));
					} else if (type.equals("class java.lang.Boolean")) {
						if ("1".equals(paramValue)) {
							field.set(obj, true);
						} else {
							field.set(obj, false);
						}
					} else if (type.equals("class java.lang.Byte")) {
						field.set(obj, new Byte(paramValue));
					} else if (type.equals("class java.lang.Long")) {
						field.set(obj, new Long(paramValue));
					} else if (type.equals("class java.lang.Float")) {
						field.set(obj, new Float(paramValue));
					} else if (type.equals("class java.lang.Double")) {
						field.set(obj, new Double(paramValue));
					}
				} else {
					field.set(obj, paramValue);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizException(BizExpDictionary.CONVERTTOVOERROR);
		}
		return (E) obj;
	}

	/**
	 * 对象转换
	 * 
	 * @param newSource
	 *            现将要设置新值的对象
	 * @param source
	 *            源数据对象
	 */
	public static <T> void beanConvert(T newSource, T source) {
		try {
			BeanUtils.copyProperties(newSource, source);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param request
	 *            请求对象
	 * @param obj
	 *            要设置Bean的类型,传入试为: Bean.class
	 * @return
	 */
	public static <T> T getBeanToRequest(HttpServletRequest request,
			Class<T> clazz) {
		// 获取页面所有的请求参数名称
		Enumeration<String> enume = request.getParameterNames();
		T beanObj = null;
		try {
			beanObj = clazz.newInstance();
			while (enume.hasMoreElements()) {
				// 参数名称
				String propertyName = enume.nextElement();
				// 判断是否存在此属性
				if (isCheckBeanExitsPropertyName(clazz, propertyName)) {
					// 获取请求值
					Object propertyValue = request.getParameter(propertyName);
					setProperties(beanObj, propertyName, propertyValue);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return beanObj;
	}

	/**
	 * 检查对象中是否存在该属性名称
	 * 
	 * @param clazz
	 *            Class对象
	 * @param propertyName
	 *            属性名称
	 * @return boolean
	 */
	private static boolean isCheckBeanExitsPropertyName(Class<?> clazz,
			String propertyName) {
		boolean retValue = false;
		try {
			Field field = clazz.getDeclaredField(propertyName);
			if (null != field) {
				retValue = true;
			}
		} catch (NoSuchFieldException e) {
			System.out.println("类: " + clazz.getSimpleName() + ",不存在属性名: "
					+ propertyName + " ,详细错误信息: " + e.getMessage());
		}
		return retValue;

	}

	/**
	 * 设置字段值
	 * 
	 * @param obj
	 *            实例对象
	 * @param propertyName
	 *            属性名
	 * @param value
	 *            新的字段值
	 */
	public static void setProperties(Object object, String propertyName,
			Object value) throws IntrospectionException,
			IllegalAccessException, InvocationTargetException {
		PropertyDescriptor pd = new PropertyDescriptor(propertyName,
				object.getClass());
		Method methodSet = pd.getWriteMethod();
		methodSet.invoke(object, value);
	}

}
