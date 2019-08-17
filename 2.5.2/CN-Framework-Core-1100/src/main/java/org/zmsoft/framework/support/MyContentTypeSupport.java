package org.zmsoft.framework.support;

import javax.servlet.http.HttpServletResponse;

import org.zmsoft.framework.constants.ICFrameworkConstants;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.framework.utils.FilePathHelper;

/**
 * 文件头定义
 *
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 * @see http://baike.baidu.com/view/1547292.htm?fr=aladdin
 */
public class MyContentTypeSupport extends MyTokenCommonSupport implements ICFrameworkConstants {

	/**
	 * 获得下载页面信息头
	 * 
	 * @param response
	 */
	public static void getResponseContextDownType(HttpServletResponse response) {
		response.setContentType("application/x-msdownload");
		response.setHeader("Content-Disposition", "attachment; filename = MyWork.zip");// 保存文件名字
		getResponseContextType(response, "");
	}

	/**
	 * 获得 图片浏览页面信息头
	 * 
	 * @param response
	 */
	public static void getResponseContextImageType(HttpServletResponse response) {
		response.setContentType("image/jpeg");
		getResponseContextType(response, "");
	}

	/**
	 * 根据文件类型获得文件头
	 * 
	 * @param response
	 * @param id
	 */
	public static void getResponseContextType(HttpServletResponse response, String id) {
		// 添加返回类型
		if (EmptyHelper.isNotEmpty(id)) {
			try {
				String[] fileTypes = FilePathHelper.getFilePath(id, true);
				// response.setHeader("Content-Disposition", "attachment;
				// filename = "+PKHelper.creatPUKey()+fileTypes[3]);//保存文件名字
				response.setContentType(fileTypes[4]);
			} catch (Exception e) {

			}
		}

		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	}

}