package org.zmsoft.config.alipay;

import org.springframework.stereotype.Service;
import org.zmsoft.config.AConfigSupport;
import org.zmsoft.framework.common.ISConfig;

/**
 * 阿里支付配置
 */
@Service("AliPayConfigService")
public class AliPayConfigService extends AConfigSupport implements ISConfig {

	private final static String TYPE = "ali.pay.config";

	// 公众号AppID
	private String aliAppID = "1234567890";
	private String aliAesPrivateKey = "123456789012345678901234567890";
	private String aliAppPrivateKey = "123456789012345678901234567890";
	private String aliAppPublicKey = "123456789012345678901234567890";
	// //支付宝开放平台应用APPID
	// public static String MAYI_APP_ID = "2018011501877685";
	// //AES密钥
	// public static String MAYI_AES_PRIVATE_KEY = "w6mvsfEk93f164qIkC3jaA==";
	// //应用对应私钥
	// public static String MAYI_APP_PRIVATE_KEY =
	// "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCA9Lc05ZvHAIQnOz/+es4akbKh9yYWULQvfkschzK/ZyFMb5no9ixc2ke2necl+D1/j/1dH7YUkkzHTfgXbF6a/W9TxzEpm0e8j9GvQNsF/yCiUAWx4fQlTp7M/ZIF3PMLrix9ikUZxbZXlapQYKjrlKDVL+Y2tBLFkJSlAxNRC/KhpvsAdrJ/6ApICrYRy5Oay1WYgB/aULAUixCRDd6UQ/ol8LCZjzvYVf3PJX78xw6rxvnArcKk8ht8rZ8ta8LAshxkecsvoR8ljrUyQQXWB43Gizd05ZKqKbsXIW+U8gcyZmfDw3hwtfFzdkmWAb4gL7r3bjj/axcdyM3Kq2ZzAgMBAAECggEAKp62nkj0v0SwaLbwY47651UYEOe6Ms3hf4DiGj+kyhWvr6+C0rfp2ZZa9UQizS3KRYq1Vj3Q0+O2dHiUckliyx7P2ISQ/wtPHkb6Kk3pFYZIptyDxVLEekANw/padI2d1lJuad91DxuFNFVGwOHxckrhDRSTpajGO32ZYyAtFi/AcwvVPGo2ePE8ceMUE8sC8x7Ldlqa9HEnW4OjO/lY5oiIqn36U7JwCBTkcGCstlZh/ZgeO6TCkJf1FDHRJ2hlRSkpmMknhcZ3Ivfo9xGWX+VKLMZRYlNmASc04nq/QP/kHCKXna/QdEl5WD0rBleb5rA6TI/XjJ/PUnsIWhdHAQKBgQC/NZCEcBfDZRBopadWUktKoY5Bw743A7lXvp187Be4ldT/NWxtLz3XhsmVJPiAt+YnHIjR1XlELWW+J1M9SyZC4PfWixL6hn3qntC5Sg679gMhA7sJd2goITa9qbKKllJhV8lA/l/dOw26oM6oXaDvVAuugAoGgQ0aBHqD3bA7EwKBgQCspv2LmVACBnhCVgr95l9xaq4509Wz5nmEJxbdEZhXops9d+5vjSbAhkAZXiSEl5M53hMKzK02BiaDGu7QNjcqthiOWQO4g+hiCUhcpV17WwibomxatKzsug4B/kqc/wMObahn9iMw4m2qxVcssMJb78kT7Tmx2ZH+NNcGZvUzIQKBgBYVSKCTFZuCg5eiciYdEQcIi5Z61fAipPhGUogH+GFgJJ5o/CHMD/dhYMyx3WV4V8Nynzak0gGWwKZqgkmU8VFODDGiFEoM2GQNfYu4yuTYb9oTRTm9qbDgzSKwg6dP3ynQ6rRv7IBvfpFsWxHE5n5dlY/Mx9uicfHsTH4IT38zAoGBAKdgJECnWmb8Oq2kmHdYVIKxN0IcWPpUns08z+901Ulv/Ct1DxPZtQ3DmHR/Tcu03ielYLSBhBHxmHhVLdtwE782VUv6g57nUetosFwQfDXQJOIsbfrpWRMNs/K+wTq+BgqAyFgwyCDmAgch1McgfOt/5GGLAvmlwOC9/Q7Z1S/BAoGASu2BvD02KEXw35zKvssmBTIEtcpG6UlFTrfPYBGl4xCn8LkpqgvNqqYb9ny0iubfwVfji/bJizEKmivmsXxNkZisNGO4Zk8zowVRU5vh53UK/NzWclo1nOxM8d8rJ1EJnGtBTA2x1vj11d0JfnfjaSC0yAtM0Kwg+OI6ZI//j+s=";
	// //应用对应公钥
	// public static String MAYI_ALIPAY_PUBLIC_KEY = "";
	// //支付宝公钥
	// public static String ALIPAY_PUBLIC_KEY_BASE = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgPS3NOWbxwCEJzs//nrOGpGyofcmFlC0L35LHIcyv2chTG+Z6PYsXNpHtp3nJfg9f4/9XR+2FJJMx034F2xemv1vU8cxKZtHvI/Rr0DbBf8golAFseH0JU6ezP2SBdzzC64sfYpFGcW2V5WqUGCo65Sg1S/mNrQSxZCUpQMTUQvyoab7AHayf+gKSAq2EcuTmstVmIAf2lCwFIsQkQ3elEP6JfCwmY872FX9zyV+/McOq8b5wK3CpPIbfK2fLWvCwLIcZHnLL6EfJY61MkEF1geNxos3dOWSqim7FyFvlPIHMmZnw8N4cLXxc3ZJlgG+IC+69244/2sXHcjNyqtmcwIDAQAB";
	//

	@Override
	public String loadType() {
		return TYPE;
	}

	//////////////////////////////////////////////////////
	public String getAliAppID() {
		return aliAppID;
	}

	public void setAliAppID(String aliAppID) {
		this.aliAppID = aliAppID;
	}

	public String getAliAesPrivateKey() {
		return aliAesPrivateKey;
	}

	public void setAliAesPrivateKey(String aliAesPrivateKey) {
		this.aliAesPrivateKey = aliAesPrivateKey;
	}

	public String getAliAppPrivateKey() {
		return aliAppPrivateKey;
	}

	public void setAliAppPrivateKey(String aliAppPrivateKey) {
		this.aliAppPrivateKey = aliAppPrivateKey;
	}

	public String getAliAppPublicKey() {
		return aliAppPublicKey;
	}

	public void setAliAppPublicKey(String aliAppPublicKey) {
		this.aliAppPublicKey = aliAppPublicKey;
	}
}
