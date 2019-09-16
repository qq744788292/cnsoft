package com.tasoft.syb.test;

import java.util.HashMap;

import org.zmsoft.jfp.framework.security.value.MD5SecurityHelper;
import org.zmsoft.jfp.framework.utils.DateHelper;
import org.zmsoft.jfp.framework.utils.HttpServiceHelper;

public class UserTest {
	public static void main(String[] args) throws Exception {

		//HttpServiceHelper.doHttpPOST("http://192.168.11.191/rcq?m=THREEAUTH_NAME&key=0000000", "{\"callId\":10000147}");
		
		//System.out.println(MD5SecurityHelper.encrypt("10001" + "150405792349110010" + "1" + "" + "9628a68a6062b03e"));

		sysPay();
	}

	/**
	 * 微信支付
	 * @throws Exception
	 */
	public static void sysPay() throws Exception {
		HashMap<String, String> param;
		String serviceURL;
		{
			param = new HashMap<String, String>();
			//param.put("token", "a1991231262800a15050433941f580528137701664d19199005_2_3_a_1_a_8_");
//			param.put("pageCurrent", "1");
//			param.put("pageLimit", "4");
//			param.put("sex", "2");
			//param.put("sdcustomno", "17080816555811110");
			//param.put("state", "1");

			//serviceURL = "http://192.168.18.186:8080/11202035";
			serviceURL = "http://127.0.0.1:8110/20102010?token=11F2E780E0208040C0B090318125F2667819A030B0F936E769C_F_7_5_0_5_3_&menuid=1100";
			
			System.out.println(HttpServiceHelper.doHttpPOST(serviceURL, param));
		}

	}
	
	
	/**
	 * 微信支付
	 * @throws Exception
	 */
	public static void weixinPay() throws Exception {
		HashMap<String, String> param;
		String serviceURL;
		{
			param = new HashMap<String, String>();
			//param.put("token", "a1991231262800a15050433941f580528137701664d19199005_2_3_a_1_a_8_");
//			param.put("pageCurrent", "1");
//			param.put("pageLimit", "4");
//			param.put("sex", "2");
			//param.put("sdcustomno", "17080816555811110");
			//param.put("state", "1");

			//serviceURL = "http://192.168.18.186:8080/11202035";
			serviceURL = "http://player.wanjiela.com/50101030?bigClassifyId=20&centreClassifyId=2070&hdp=1&jobId=1502701773329&pageCurrent=1&pageLimit=20&token=a05.10b.b05.e00:512195204217a0d1a649e186d549a_3_6_8_3_9_b_a_5_d_";
			
			System.out.println(HttpServiceHelper.doHttpPOST(serviceURL, param));
		}

	}
	
	public static void aliPay() throws Exception {
		HashMap<String, String> param;
		String serviceURL;
		{
			param = new HashMap<String, String>();
			//param.put("token", "a1991231262800a15050433941f580528137701664d19199005_2_3_a_1_a_8_");
//			param.put("pageCurrent", "1");
//			param.put("pageLimit", "4");
//			param.put("sex", "2");
			param.put("out_trade_no", "17092011472510210");
			param.put("trade_status", "TRADE_SUCCESS");

			//serviceURL = "http://192.168.18.186:8080/11202035";
			serviceURL = "http://60.191.111.6:29086/99996052/17092011315910010";

			System.out.println(HttpServiceHelper.doHttpPOST(serviceURL, param));
		}

	}
}
