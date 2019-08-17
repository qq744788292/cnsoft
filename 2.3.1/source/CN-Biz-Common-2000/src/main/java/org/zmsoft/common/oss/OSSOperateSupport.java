package org.zmsoft.common.oss;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.zmsoft.framework.constants.ICFrameworkConstants;
import org.zmsoft.framework.utils.DateHelper;
import org.zmsoft.framework.utils.FileHelper;
import org.zmsoft.framework.utils.PKHelper;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;

import net.coobird.thumbnailator.Thumbnails;

/**
 * OSS上传用法示例
 * 
 * @author ZMSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 *        {@link https://help.aliyun.com/document_detail/32008.html?spm=a2c4g.11186623.2.6.3c6f5d26i2YeBE}
 */
@Service("OSSOperateSupport")
public class OSSOperateSupport implements ICFrameworkConstants {

	// //////////////////////// 服务器节点配置////////////////////////
	// @Value("${oss.publicServer}")
	// protected String publicServer = "ZMSoft";
	// @Value("${oss.endpoint}")
	// protected String endpoint = "ZMSoft";
	//
	// //////////////////////// 账号信息配置////////////////////////
	// //@Value("${oss.endpoint}")
	// protected String accessKeyId = "ZMSoft";
	// //@Value("${oss.endpoint}")
	// protected String accessKeySecret = "ZMSoft";
	// //@Value("${oss.endpoint}")
	// protected String bucketName = "ZMSoft";

	private static String publicServer = "http://zmsoft.oss-cn-hangzhou.aliyuncs.com/";
	private static String endpoint = 	 "http://oss-cn-hangzhou.aliyuncs.com";
	private static String accessKeyId = "LTAIbdAu0xQvAsQx";
	private static String accessKeySecret = "aEQ9SKfxm4PZoBbayYfx9ZP0x5as4c";
	private static String bucketName = "zmsoft";

	//////////////////////// 图片压缩质量设置////////////////////////
	//@Value("${oss.outputQuality}")
	protected float outputQuality = 1f;
	//@Value("${oss.scale}")
	protected float scale = 1f;

	/**
	 * 文件保存到OSS
	 * 
	 * @param file
	 * @param docname
	 * @return
	 * @throws Throwable
	 */
	public String putObject(byte[] file, String docname) throws Throwable {
		return putObject(file, docname, true);
	}

	/**
	 * 文件保存到OSS
	 * 
	 * @param file
	 * @param docname
	 * @param path
	 *            是否开始
	 * @return
	 * @throws Throwable
	 */
	public String putObject(byte[] file, String docname, boolean path) throws Throwable {
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		try {
			String key = PKHelper.creatUUKey();
			String[] ft = docname.split("\\.");

			String realFileName = key;
			if (ft.length == 2)
				realFileName = key + CURRENT_PATH + ft[1];
			if (path)
				realFileName = DateHelper.currentDate3() + FOLDER_SEPARATOR + realFileName;
			else
				realFileName = docname;
			
			// 待上传的本地文件
			ossClient.putObject(bucketName, realFileName, new ByteArrayInputStream(file));
			// 设置公共读取权限
			//ossClient.setObjectAcl(bucketName, realFileName, CannedAccessControlList.PublicRead);
			// 返回真实路径
			return publicServer + realFileName;
		} finally {
			ossClient.shutdown();
		}
	}

	public String putObject(MultipartFile file, boolean pubUrl) throws Throwable {
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		try {
			String key = PKHelper.creatUUKey();
			String[] ft = file.getOriginalFilename().split("\\.");
			String realFileName = key;
			if (ft.length == 2)
				realFileName = key + CURRENT_PATH + ft[1];

			realFileName = DateHelper.currentDate3() + FOLDER_SEPARATOR + realFileName;

			try {
				// 图片尺寸不变，压缩图片文件质量大小outputQuality实现,参数1为最高质量,scale为尺寸大小压缩
				BufferedImage image = Thumbnails.of(file.getInputStream()).outputQuality(outputQuality).scale(scale).asBufferedImage();
				// BufferedImage 转 InputStream
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				ImageIO.write(image, ft[1], os);

				// 待上传的本地文件
				ossClient.putObject(bucketName, realFileName, new ByteArrayInputStream(os.toByteArray()));
			} catch (Exception e) {
				// 待上传的本地文件
				ossClient.putObject(bucketName, realFileName, file.getInputStream());
			}
			
			// 设置公共读取权限
			//ossClient.setObjectAcl(bucketName, realFileName, CannedAccessControlList.PublicRead);
			// 返回真实路径
			if (pubUrl)
				return publicServer + realFileName;
			return realFileName;
		} finally {
			ossClient.shutdown();
		}
	}

	/**
	 * 有效签名
	 * 
	 * @param key
	 * @param time
	 *            时长
	 * @return
	 * @deprecated
	 * @throws ClientException
	 */
	public String generatePresignedUrl(String key, int time) throws ClientException {
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		try {
			
			Date expires = new Date (new Date().getTime() + 1000 * 60); // 1 minute to expire

			GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, key);

			generatePresignedUrlRequest.setExpiration(expires);

			URL url = ossClient.generatePresignedUrl(generatePresignedUrlRequest);

			System.out.println(url.toString());
			
			
			
//			long da = new Date().getTime() + time;
//			String url = ossClient.generatePresignedUrl(bucketName, key, new Date(da)).toString();
//			ObjectAcl objectAcl = ossClient.getObjectAcl(bucketName, key);
//			System.out.println(objectAcl.getPermission().toString()+"<<<<<<<====");
//			System.out.println(url);
			return url.toString();
		} finally {
			ossClient.shutdown();
		}
	}

	public void removeObject(String key) throws IOException {
		// String key = "231395c199b74e7d84bedb146f957ae6.png";
		OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		client.deleteObject(bucketName, key);
	}

	public static void main(String[] args) throws Exception, Throwable {
		OSSOperateSupport a = new OSSOperateSupport();
		System.out.println(a.putObject(FileHelper.readFileByBytes("d:/1.jpg"), "123.jpg"));
	}
}
