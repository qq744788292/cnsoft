package org.cnsoft.framework.json;

import java.util.List;
import java.util.Map;

import org.cnsoft.framework.utils.EmptyHelper;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 使用jackson来进行数组格式的json字符串转换成List。
 *
 */
public class JSONObject {
	static ObjectMapper oom = new ObjectMapper();

	public static ObjectMapper getObjectMapper() {
		return oom;
	}

	public static String toJsonString(Object object) {
		try {
			return getObjectMapper().writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object.getClass().getSimpleName() + "JSON 转换失败";
	}

	/**
	 * 使用jackson来进行数组格式的json字符串转换成对象。
	 * 
	 * @param <T>
	 * @param content
	 * @param clazz
	 * @return
	 */
	public static <T> T parseObject(String content, Class<T> clazz) {
		if (EmptyHelper.isEmpty(content))
			return null;
		try {
			// 忽略大小写与驼峰命名
			// mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
			// deprecated
			// mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
			// mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
			return getObjectMapper().readValue(content, clazz);
		} catch (Exception e) {
			System.err.println("content=="+content);
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 使用jackson来进行数组格式的json字符串转换成List。
	 *
	 * @param json
	 * @param jsonTypeReference
	 * @return
	 */
	public static <T> List<T> parseArray(String content, Class<T> clazz) {
		if (EmptyHelper.isEmpty(content))
			return null;
		try {
			JavaType javaType = getObjectMapper().getTypeFactory().constructParametricType(List.class, clazz);

			return cast(getObjectMapper().readValue(content, javaType));
		} catch (Exception e) {
			System.err.println("content=="+content);
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static <T> T cast(Object obj) {
		return (T) obj;
	}
	
	/**
	 * 使用jackson来进行数组格式的json字符串转换成Map。
	 *
	 * @param json
	 * @param jsonTypeReference
	 * @return
	 */
	public static Map<String, String> parseMap(String content) {
		if (EmptyHelper.isEmpty(content))
			return null;
		try {
			return getObjectMapper().readValue(content, new JSON2MapTypeReference());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
