package org.zmsoft.framework.token;

import org.zmsoft.framework.constants.ICFrameworkConstants;
import org.zmsoft.framework.utils.EmptyHelper;

/**
 * 业务请求Token数据算法
 *
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 * @see TokenBusinessBean
 */
public class TokenBusinessHelper implements ICFrameworkConstants {

	public static void main(String[] args) throws Exception {
		System.out.println(getBizTokenData("987321", "1256", "abcdefghi", "gov"));
		System.out.println(getBizTokenData(getBizTokenData("987321", "1256", "abcdefghi", "gov"), 4)[0]);
		System.out.println(getBizTokenData(getBizTokenData("987321", "1256", "abcdefghi", "gov"), 4)[1]);
		System.out.println(getBizTokenData(getBizTokenData("987321", "1256", "abcdefghi", "gov"), 4)[2]);
		System.out.println(getBizTokenData(getBizTokenData("987321", "1256", "abcdefghi", "gov"), 4)[3]);
	}

	/**
	 * 交叉混淆，可以正序或者倒序，可以奇数和偶数
	 * 
	 * @param data
	 *            token 算法数据
	 * @return tonkenString
	 * @throws Exception
	 */
	public static String getBizTokenData(String... data) throws Exception {
		if (data == null)
			throw new Exception("不能存在空值");
		// 初始化
		StringBuilder tonkenKey = new StringBuilder(54);
		int length = 0;
		// 获得最大长度
		for (int i = 0; i < data.length; i++) {
			if (EmptyHelper.isEmpty(data[i]))
				throw new Exception("不能存在空值");
			int l = data[i].length();
			if (l > length)
				length = l;
		}
		// 整理结果
		for (int m = 0; m < length; m++) {
			for (int n = 0; n < data.length; n++) {
				// 逐级拼接
				if (m < data[n].length())
					tonkenKey.append(data[n].charAt(m));
				else
					tonkenKey.append(DOT);
			}
		}
		return tonkenKey.toString();
	}

	/**
	 * 根据企业ID获得用户ID
	 * 
	 * @param userToken
	 * @return
	 * @throws Exception
	 */
	public static String[] getBizTokenData(String userToken, int split) throws Exception {
		if (EmptyHelper.isEmpty(userToken))
			throw new Exception("不能存在空值");
		// 初始化
		StringBuilder[] data = new StringBuilder[split];
		String[] rets = new String[split];
		int length = 0;
		// 逐级解析
		while (length < userToken.length()) {
			for (int s = 0; s < split; s++) {
				// 初始化
				if (data[s] == null)
					data[s] = new StringBuilder(userToken.length());
				// 获得内容
				if (length < userToken.length()) {
					char d = userToken.charAt(length);
					// 有效性判断
					if (d != DOT2)
						data[s].append(d);
				}
				// 索引更新
				length = length + 1;
			}
		}
		// 整理结果
		for (int i = 0; i < split; i++) {
			rets[i] = data[i].toString();
		}
		return rets;
	}

}
