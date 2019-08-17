package org.zmsoft.config.oss;

import org.springframework.stereotype.Service;
import org.zmsoft.config.AConfigSupport;

/**
 * 阿里支付配置
 */
@Service("AliOSSConfigService")
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AliOSSConfigService extends AConfigSupport {

	private final static String TYPE = "alioss.config";

	@Override
	public String loadType() {
		return TYPE;
	}

	/**
	 * 公网地址
	 */
	private String publicServer = null;
	/**
	 * 上传地址
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
	private float outputQuality = 1f;
	/**
	 * scale为尺寸大小压缩
	 */
	protected float scale = 1f;

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
		return outputQuality;
	}

	public void setOutputQuality(float outputQuality) {
		this.outputQuality = outputQuality;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

}
