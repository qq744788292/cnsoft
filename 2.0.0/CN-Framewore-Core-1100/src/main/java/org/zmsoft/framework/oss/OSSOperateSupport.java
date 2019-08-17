package org.zmsoft.framework.oss;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import org.zmsoft.framework.constants.IFrameworkConstants;
import org.zmsoft.framework.utils.DateHelper;
import org.zmsoft.framework.utils.PKHelper;

import net.coobird.thumbnailator.Thumbnails;

/**
 * OSS上传用法示例
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 * {@link https://help.aliyun.com/document_detail/32008.html?spm=a2c4g.11186623.2.6.3c6f5d26i2YeBE}
 */
@Service("OSSOperateSupport")
public class OSSOperateSupport implements IFrameworkConstants {
	
	@Value("${oss.publicServer}")
	protected  String publicServer ="https://gtcloud-center.oss-cn-hangzhou.aliyuncs.com/";
	@Value("${oss.endpoint}")
	protected  String endpoint="http://oss-cn-hangzhou.aliyuncs.com";
	@Value("${oss.outputQuality}")
	protected float outputQuality = 1f;
	@Value("${oss.scale}")
	protected float scale = 1f;
	
	protected  String accessKeyId="LTAICa7WVFxU11V0";
	protected  String accessKeySecret="UQQ5kBtf4qa8IZ6nBMcAGAaiOrg8Tp";
	protected  String bucketName="gtcloud-center";
	  
	public String putObject(byte[] file, String docname) throws Throwable {
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

	public String putObject(MultipartFile file, boolean pubUrl) throws Throwable {
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		try {
			String key = PKHelper.creatUUKey();
			String[] ft = file.getOriginalFilename().split("\\.");
			String realFileName = key;
			if (ft.length == 2)
				realFileName = key + CURRENT_PATH + ft[1];

			realFileName = DateHelper.currentDate3() + FOLDER_SEPARATOR + realFileName;

			try{
				// 图片尺寸不变，压缩图片文件质量大小outputQuality实现,参数1为最高质量,scale为尺寸大小压缩
				BufferedImage image = Thumbnails.of(file.getInputStream()).outputQuality(outputQuality).scale(scale).asBufferedImage();
				// BufferedImage 转 InputStream
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				ImageIO.write(image, ft[1], os);
	
				ossClient.putObject(bucketName, realFileName, new ByteArrayInputStream(os.toByteArray()));
			}catch(Exception e){
				ossClient.putObject(bucketName, realFileName, file.getInputStream());
			}
			
			if (pubUrl)
				return publicServer + realFileName;
			return realFileName;
		} finally {
			ossClient.shutdown();
		}
	}

	public void removeObject(String key) throws IOException {
		// String key = "231395c199b74e7d84bedb146f957ae6.png";
		OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		client.deleteObject(bucketName, key);
	}

}
