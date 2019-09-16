package org.zmsoft.framework.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
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

	/**
	 * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
	 * 
	 * @return
	 * 
	 * @throws IOException
	 */
	public static byte[] readFileByBytes(String fileName) throws Exception {
		File file = new File(fileName);

		BufferedInputStream bufInputStream = new BufferedInputStream(new FileInputStream(file));
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		try {
			byte[] bytes = new byte[1];

			// 将文件内容写入位数组流
			while (bufInputStream.read(bytes) != -1) {
				arrayOutputStream.write(bytes);
			}
			// 以字符方式显示位数组内容
			return arrayOutputStream.toByteArray();
		} finally {
			arrayOutputStream.close();
			bufInputStream.close();
		}
	}
}
