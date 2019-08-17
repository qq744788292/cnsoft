package com.zmsoft.common.api.qrcode;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zmsoft.framework.support.MyContentTypeSupport;
import org.zmsoft.framework.utils.QRCodeHelper;

/**
 * 二维码
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
@Controller
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequestMapping(value = "/api/1.0/common/qrcode", method = { RequestMethod.POST })
public class ApiQrCodeController extends MyContentTypeSupport {

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public void doShow(HttpServletRequest request, HttpServletResponse response, HttpSession session, String content) throws Exception {
		// int width = 65;// 设置图片的宽度
		// int height = 22;// 设置图片的高度
		buildQrPicture(200, 200, content, request, response, session);
	}

	/**
	 * 自定义高度
	 * 
	 * @param width
	 * @param height
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/show/{width}/{height}", method = RequestMethod.GET)
	public void doShow(@PathVariable int width, @PathVariable int height, String content, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		buildQrPicture(width, height, content, request, response, session);
	}

	/////////////////////////////////////////////////////////////////////////////////
	private void buildQrPicture(int width, int height, String content, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

		getResponseContextImageType(response);

		ServletOutputStream sos = response.getOutputStream();

		QRCodeHelper.loadQRCode(content, width, height, sos);
	}
}