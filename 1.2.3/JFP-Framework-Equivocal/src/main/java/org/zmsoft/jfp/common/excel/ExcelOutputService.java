package org.zmsoft.jfp.common.excel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

/**
 * Excel输出
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public class ExcelOutputService {

	public void downloadExcel(HttpServletRequest request, HttpServletResponse response,JSON data,String templateNmae){
		System.out.println("这里输出Excel....."+data);
	}
	public void downloadExcel(HttpServletRequest request, HttpServletResponse response,JSONArray data,String templateNmae){
		System.out.println("这里输出Excel....."+data);
	}	
}
