package org.zmsoft.config.alioss;

import org.springframework.stereotype.Service;
import org.zmsoft.config.AConfigSupport;
import org.zmsoft.framework.common.ISConfig;

/**
 * 阿里支付配置
 */
@Service("AliOSSConfigService")
public class AliOSSConfigService extends AConfigSupport implements ISConfig {

	private final static String TYPE = "ali.oss.config";

	@Override
	public String loadType() {
		return TYPE;
	}

	/**
	 * 公网地址
	 */
	private String publicServer = null;
	/**
	 * 上传地址(内网)
	 */
	private String uploadServer = null;
	/**
	 * 账户id
	 */
	private String accessKeyId = "";
	/**
	 * 密钥
	 */
	private String accessKeySecret = "";
	/**
	 * 数据库名称
	 */
	private String bucketName = "";

	/**
	 * 压缩图片文件质量大小outputQuality实现,参数1为最高质量,
	 */
	private String outputQuality = ONE;
	/**
	 * scale为尺寸大小压缩
	 */
	private String scale = ONE;

	public String getPublicServer() {
		return publicServer;
	}

	public void setPublicServer(String publicServer) {
		this.publicServer = publicServer;
	}

	public String getUploadServer() {
		return uploadServer;
	}

	public void setUploadServer(String uploadServer) {
		this.uploadServer = uploadServer;
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	public String getAccessKeySecret() {
		return accessKeySecret;
	}

	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public float getOutputQuality() {
		return Float.parseFloat(outputQuality);
	}

	public void setOutputQuality(String outputQuality) {
		this.outputQuality = outputQuality;
	}

	public float getScale() {
		return Float.parseFloat(scale);
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

}
