package org.zmsoft.jfp.common.qrcode;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.zmsoft.jfp.framework.support.MyContentTypeSupport;
import org.zmsoft.jfp.framework.utils.QRCodeHelper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 网吧大师登录本平台系统唯一接口地址
 * 
 * @author zmsoft
 * @version 4.1.3 2017/07/25
 * @since  4.1.3 2017/07/25
 */
@Controller
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QrCodeController extends MyContentTypeSupport {

	@RequestMapping(value = "/99999030", method = RequestMethod.GET)
	public void do99999030GET(HttpServletRequest request, HttpServletResponse response, HttpSession session, String content) throws Exception {
		// int width = 65;// 设置图片的宽度
		// int height = 22;// 设置图片的高度
		do99999030(200, 200, content, request, response, session);
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
	@RequestMapping(value = "/99999031/{width}/{height}", method = RequestMethod.GET)
	public void do99993031GET(@PathVariable int width, @PathVariable int height, String content, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		do99999030(width, height, content, request, response, session);
	}

	public void do99999030(int width, int height, String content, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

		getResponseContextImageType(response);

		ServletOutputStream sos = response.getOutputStream();

		QRCodeHelper.loadQRCode(content, width, height, sos);
	}
}