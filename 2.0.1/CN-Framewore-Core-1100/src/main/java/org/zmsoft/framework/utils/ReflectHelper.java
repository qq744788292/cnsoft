package org.zmsoft.framework.utils;

import java.lang.reflect.Field;

public class ReflectHelper {
	
	// 该方法的参数列表是一个类的 类名、成员变量、给变量的赋值
    public static void setProperty(Object obj, String PropertyName, Object value)
            throws NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {

        // 获取obj类的字节文件对象
		Class c = obj.getClass();
		try {
			// 获取该类的成员变量
			Field f = c.getDeclaredField(PropertyName);
			// 取消语言访问检查
			f.setAccessible(true);
			// 给变量赋值
			f.set(obj, value);
		} catch (Exception e) {
		}
        
		//设置父类的变量值
        try {
			Class cSuper = c.getSuperclass();
			// 获取该类的成员变量
			Field fSuper = cSuper.getDeclaredField(PropertyName);
			// 取消语言访问检查
			fSuper.setAccessible(true);
			// 给变量赋值
			fSuper.set(obj, value);
		} catch (Exception e) {
		}
        
    }
}
