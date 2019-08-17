package org.zmsoft.framework.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;

import org.springframework.core.io.Resource;

public class FileHelper {
	public static void doFileWrite(String content, String fileFullName) throws Exception {
		// 1：利用File类找到要操作的对象
		File file = new File(fileFullName);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}

		// 2：准备输出流
		Writer out = new FileWriter(file);
		out.write(content);
		out.close();

	}
	
	/**
	 * 按行读取文件内容
	 * 
	 * @param configLocation
	 * @return SQL内容
	 * @throws Exception
	 */
	public ArrayList<String> loadResourceDataToSQL(Resource configLocation) throws Exception {
		ArrayList<String> list = new ArrayList<String>();
		InputStream is = configLocation.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		String data = null;
		while ((data = br.readLine()) != null) {
			list.add(data);
		}

		return list;
	}
}
