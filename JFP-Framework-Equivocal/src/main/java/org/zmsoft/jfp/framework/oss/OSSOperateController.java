package org.zmsoft.jfp.framework.oss;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zmsoft.jfp.framework.beans.common.RESTResultBean;
import org.zmsoft.jfp.framework.support.MyTokenCommonSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * OSS资源管理
 * 
 * @author zmsoft
 * @version 0.0.1 2017/03/15
 * @since 0.0.1 2017/03/15
 */
@Controller
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class OSSOperateController extends MyTokenCommonSupport {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 文件上传
	 * 
	 * @param jobId
	 * @param pageCurrent
	 * @param pageLimit
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/99999010", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean<String> putObject10909010(HttpServletRequest request, HttpServletResponse response, 
			int width, int height, 
			String token, String id, MultipartFile upfile) {
		RESTResultBean<String> result = new RESTResultBean<String>();
		result.setJobId(token);
		try {
			if (upfile != null) {
				result.setData(OSSOperateHelper.putObject(upfile, true));
				result.setMsg("操作成功");
			}
		} catch (Throwable e) {
			e.printStackTrace();
			result.setCode(ONE);
			result.setMsg("操作失败");
		}
		System.out.println(result);
		return result; // 返回result对象给前端
	}

	@RequestMapping(value = "/99999011", method = RequestMethod.POST)
	public ModelAndView putObjectPage10909011(HttpServletRequest request, HttpServletResponse response, 
			int width, int height, 
			String token, String id, MultipartFile upfile) {
		ModelAndView model = new ModelAndView("/common/fileupload");
		RESTResultBean<String> result = putObject10909010(request, response, width, height, token,id, upfile);
		model.addObject("width", width);
		model.addObject("height", height);
		model.addObject("id", id);
		model.addObject("result", result);
		logger.debug(result.toString());
		return model; // 返回result对象给前端
	}

	/**
	 * 富文本上传
	 * @author 蔡强
	 * @param jobId
	 * @param upFile
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/99999012", method = RequestMethod.POST)
	public ModelAndView putObject10909012(String jobId, MultipartFile upFile) throws Exception {
		ModelAndView model = new ModelAndView("/common/pic2");
		String pic = "";
		try {
			if (upFile != null) {
				pic = OSSOperateHelper.putObject(upFile, true);
			}
		} catch (Throwable e) {
			e.printStackTrace();
			pic = "error|服务器异常";
		}
		System.out.println(pic);
		model.addObject("pic", pic);
		logger.debug(pic);
		return model; // 返回result对象给前端
	}

	/**
	 * 文件删除
	 * 
	 * @param jobId
	 * @param commodityId
	 *            商品流水ID
	 * @param convertNum
	 *            商品兑换数量
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/99999020", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean<String> removeObject(String jobId, String ossUrl) {
		RESTResultBean<String> result = new RESTResultBean<String>();
		result.setJobId(jobId);
		try {
			String[] ks = ossUrl.split("/");
			OSSOperateHelper.removeObject(ks[ks.length - 1]);
		} catch (Throwable e) {
			result.setCode(ONE);
			result.setMsg("操作失败");
		}
		return result; // 返回result对象给前端
	}
	
}
