package com.easou.novelpush.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.alibaba.fastjson.JSONObject;

/**
 * JSON工具类
 * 
 */
public class JsonUtil {
	protected static final Logger logger = Logger.getLogger(JsonUtil.class);

	// private static Logger weblogger = LoggerFactory.getLogger("WebVLogger");

	/**
	 * 将对象（List、Object...）转换成json的字符窜.
	 * 
	 * @param bean
	 * @return
	 */
	public static String beanToJson(Object bean) {
		return beanToJson(bean, "");
	}

	/**
	 * 将对象（List、Object...）转换成json的Byte[]数据.
	 * 
	 * @param bean
	 * @return
	 */
	public static byte[] beanToJsonByte(Object bean) {
		return beanToJsonByte(bean, "");
	}

	/**
	 * 将对象（List、Object...）转换成json的字符窜.
	 * 
	 * @param bean
	 * @param dateFormat
	 *            时间格式
	 * @return
	 */
	public static String beanToJson(Object bean, final String dateFormat) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonStr;
		try {
			if (StringUtils.isNotEmpty(dateFormat)) {
				DateFormat sd = new SimpleDateFormat(dateFormat);
				mapper.getSerializationConfig().setDateFormat(sd);
			}

			jsonStr = mapper.writeValueAsString(bean);
			// if (weblogger.isDebugEnabled())
			// weblogger.debug("marshalled json string is: {}", jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("(JSON)数据转换错误！beanToJson , bean: " + bean, e);
		}
		return jsonStr;
	}

	/**
	 * 将对象（List、Object...）转换成json的Byte[]数据.
	 * 
	 * @param bean
	 * @param dateFormat
	 *            时间格式
	 * @return
	 */
	public static byte[] beanToJsonByte(Object bean, final String dateFormat) {
		ObjectMapper mapper = new ObjectMapper();
		byte[] jsonStr;
		try {
			if (StringUtils.isNotEmpty(dateFormat)) {
				DateFormat sd = new SimpleDateFormat(dateFormat);
				mapper.getSerializationConfig().setDateFormat(sd);
			}

			jsonStr = mapper.writeValueAsBytes(bean);
			// if (weblogger.isDebugEnabled())
			// weblogger.debug("marshalled json string is: {}", jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("(JSON)数据转换错误！ beanToJsonByte , bean: " + bean, e);
		}
		return jsonStr;
	}

	/**
	 * 将对象（Object...）转换成json的字符窜.(List等集合除外)
	 * 
	 * @param bean
	 * @param fieldName
	 *            自定义字段
	 * @return
	 */
	@Deprecated
	public static String beanToJson(Object bean, String[] fieldName) {
		JSONObject jo = new JSONObject();
		bean2Json(jo, bean, fieldName, "");
		return jo.toString();
	}

	/**
	 * 将对象（Object...）转换成json的字符窜.(List等集合除外)
	 * 
	 * @param bean
	 * @param fieldName
	 *            自定义字段
	 * @param dateFormat
	 *            时间字段格式
	 * @return
	 */
	@Deprecated
	public static String beanToJson(Object bean, String[] fieldName, final String dateFormat) {
		JSONObject jo = new JSONObject();
		bean2Json(jo, bean, fieldName, dateFormat);
		return jo.toString();
	}

	private static void bean2Json(JSONObject jo, Object bean, final String[] fieldName, final String dateFormat) {
		Field field;
		Class<?> clazz = bean.getClass();
		try {
			for (String name : fieldName) {
				field = clazz.getDeclaredField(name);
				field.setAccessible(true);
				if (field.getType() == java.util.Date.class) {
					if (StringUtils.isNotEmpty(dateFormat)) {
						DateFormat df = new SimpleDateFormat(dateFormat);
						jo.put(name, df.format(field.get(bean)));
						continue;
					}
				}

				jo.put(name, field.get(bean));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("调用bean2JSONString(Object bean, String[] fieldName)时发生异常");
		}
	}

	/**
	 * 将对象JSON字符窜转换成指定的对象.
	 * 
	 * @param jsonStr
	 * @param clazz
	 * @return
	 */
	public static <T> T jsonToBean(String jsonStr, Class<T> clazz) {
		return jsonToBean(jsonStr, clazz, "");
	}

	/**
	 * 将对象JSON连接URL数据转换成指定的对象.
	 * 
	 * @param url
	 * @param clazz
	 * @return
	 */
	public static <T> T jsonToBean(URL url, Class<T> clazz) {
		return jsonToBean(url, clazz, "");
	}

	/**
	 * 将对象JSON字符窜转换成指定的对象.
	 * 
	 * @param jsonStr
	 * @param clazz
	 * @return
	 */
	public static <T> T jsonToBean(String jsonStr, Class<T> clazz, final String dateFormat) {
		ObjectMapper mapper = new ObjectMapper();
		T bean = null;
		try {
			if (StringUtils.isNotEmpty(dateFormat)) {
				DateFormat df = new SimpleDateFormat(dateFormat);
				mapper.getDeserializationConfig().setDateFormat(df);
			}

			bean = mapper.readValue(new String(jsonStr), clazz);
			// if (weblogger.isDebugEnabled())
			// weblogger.debug("jsonToObject return bean: {}", bean);

			return bean;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("(JSON)数据转换错误！jsonToBean , jsonStr: " + jsonStr, e);
		}
	}

	/**
	 * 将对象JSON连接URL数据转换成指定的对象.
	 * 
	 * @param url
	 * @param clazz
	 * @return
	 */
	public static <T> T jsonToBean(URL url, Class<T> clazz, final String dateFormat) {
		ObjectMapper mapper = new ObjectMapper();
		T bean = null;
		try {
			if (StringUtils.isNotEmpty(dateFormat)) {
				DateFormat df = new SimpleDateFormat(dateFormat);
				mapper.getDeserializationConfig().setDateFormat(df);
			}

			bean = mapper.readValue(url, clazz);
			// if (weblogger.isDebugEnabled())
			// weblogger.debug("jsonToObject return bean: {}", bean);

			return bean;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("(JSON)数据转换错误！jsonToBean, url: " + url + ", clazz : " + clazz, e);
		}
	}

	/**
	 * map中的key，value与json中的key，value对应
	 * 如果map中的key或者value不是基本数据类型，则返回该对象或数据toString()方法的值作为key或者value组成字符窜
	 * 
	 * @param map
	 * @return
	 */
	/*
	 * public static String map2JSONString(Map<?, ?> map) { JSONObject jo = new
	 * JSONObject(map); return jo.toString(); }
	 */

}