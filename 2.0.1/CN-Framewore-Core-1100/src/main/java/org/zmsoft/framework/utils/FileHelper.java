package org.zmsoft.framework.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

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
}
