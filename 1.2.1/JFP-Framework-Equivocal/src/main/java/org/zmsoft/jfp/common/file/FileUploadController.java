package org.zmsoft.jfp.common.file;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zmsoft.jfp.framework.beans.common.RESTResultBean;
import org.zmsoft.jfp.framework.support.MyTokenCommonSupport;
import org.zmsoft.jfp.framework.utils.EmptyHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 文件上传
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
@Controller
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FileUploadController extends MyTokenCommonSupport {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	static final String default_pic = "";///resources/img/fileupload.png

	@RequestMapping(value = "/99999000", method = RequestMethod.GET)
	public ModelAndView doFileUploadGET(HttpServletRequest request, HttpServletResponse response, 
			int width, int height, String value,
			String type, String token, String id) {
		return doFileUploadPOST(request, response,width,height,value,type, token, id); // 返回result对象给前端
	}

	@RequestMapping(value = "/99999000", method = RequestMethod.POST)
	public ModelAndView doFileUploadPOST(HttpServletRequest request, HttpServletResponse response, 
			int width, int height, String value,
			String type, String token, String id) {
		ModelAndView model = new ModelAndView("/common/fileupload");

		RESTResultBean<String> result = new RESTResultBean<String>();
		if(EmptyHelper.isEmpty(value))
			result.setData(default_pic);
		else
			result.setData(value);

		model.addObject("type", type);//0文件1图片
		model.addObject("token", token);
		model.addObject("id", id);
		model.addObject("width", width);
		model.addObject("height", height-50);

		model.addObject("result", result);

		return model; // 返回result对象给前端
	}

}
