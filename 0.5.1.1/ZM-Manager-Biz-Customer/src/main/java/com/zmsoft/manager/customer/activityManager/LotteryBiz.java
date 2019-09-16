package com.zmsoft.manager.customer.activityManager;

import java.util.ArrayList;
import java.util.List;

import org.zmsoft.jfp.framework.utils.RandomHelper;

/**
 * 优惠券码生成算法
 * @author Administrator
 *
 */
public class LotteryBiz {
	// 规则为 aaaa01x 总共
	private static final String[] letter = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
	/**
	 * 生成7位券码
	 * @param number
	 * @return
	 */
	public static List<String> generateCode(int number) {
		List<String> codeList = new ArrayList<String>();
		int oneIndex = 0;// 第一位
		int twoIndex = 0;// 第二位
		int threeIndex = 0;// 第三位
		int fourIndex = 0;// 第四位
		for (int i = 0; i < number; i++) {
			if ((i % 100 == 0) && (i > 0)) {
				fourIndex++;
			}
			if ((fourIndex % 26 == 0) && (fourIndex > 0)) {
				threeIndex = fourIndex /26;
			}
			if ((threeIndex % 26 == 0) && (threeIndex > 0)) {
				twoIndex = (fourIndex /26)/26;
			}
			if ((twoIndex % 26 == 0) && (twoIndex > 0)) {
				oneIndex = ((fourIndex /26)/26)/26;
			}
			String fiveSix  = i % 100+"";
			if(fiveSix.length()==1){
				fiveSix = "0"+fiveSix;
			}
			String code = letter[oneIndex]+letter[twoIndex%26]+letter[threeIndex%26]+letter[fourIndex%26]+fiveSix+RandomHelper.getRandomString(1);
			codeList.add(code);
		}
		return codeList;
	}
}
