package org.zmsoft.jfp.framework.oss;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.zmsoft.jfp.framework.utils.DateHelper;
import org.zmsoft.jfp.framework.utils.PKHelper;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;

import net.coobird.thumbnailator.Thumbnails;

/**
 * 断点续传上传用法示例
 * 
 * @since 2.4.2 2016/1/6
 */
public class OSSOperateHelper implements IFrameworkConstants {

	private static String publicServer = "http://zmsoft.oss-cn-hangzhou.aliyuncs.com/";
	private static String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
	private static String accessKeyId = "LTAIbdAu0xQvAsQx";
	private static String accessKeySecret = "aEQ9SKfxm4PZoBbayYfx9ZP0x5as4c";
	private static String bucketName = "zmsoft";

	public static String putObject(byte[] file, String docname) throws Throwable {
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		try {
			String key = PKHelper.creatUUKey();
			String[] ft = docname.split("\\.");

			String realFileName = key;
			if (ft.length == 2)
				realFileName = key + CURRENT_PATH + ft[1];

			realFileName = DateHelper.currentDate3() + FOLDER_SEPARATOR + realFileName;
			
			// 待上传的本地文件
			 ossClient.putObject(bucketName, realFileName, new ByteArrayInputStream(file));
			return publicServer + realFileName;
		} finally {
			ossClient.shutdown();
		}
	}

	public static String putObject(MultipartFile file, boolean pubUrl) throws Throwable {
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		try {
			String key = PKHelper.creatUUKey();
			String[] ft = file.getOriginalFilename().split("\\.");
			String realFileName = key;
			if (ft.length == 2)
				realFileName = key + CURRENT_PATH + ft[1];

			realFileName = DateHelper.currentDate3() + FOLDER_SEPARATOR + realFileName;

			InputStream is = file.getInputStream();
			ByteArrayOutputStream os = new ByteArrayOutputStream();

			//图片尺寸不变，压缩图片文件质量大小outputQuality实现,参数1为最高质量,scale为尺寸大小压缩
			Thumbnails.of(is).outputQuality(0.75f).scale(1f).toOutputStream(os);
			is = new ByteArrayInputStream(os.toByteArray());

			ossClient.putObject(bucketName, realFileName, is);
			if (pubUrl)
				return publicServer + realFileName;
			return realFileName;
		} finally {
			ossClient.shutdown();
		}
	}

	public static void removeObject(String key) throws IOException {
		// String key = "231395c199b74e7d84bedb146f957ae6.png";
		OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		client.deleteObject(bucketName, key);
	}

}
