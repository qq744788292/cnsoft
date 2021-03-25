package org.zmsoft.common.api;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.zmsoft.common.service.MyFileOperatorSupport;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.support.MyTokenCommonSupport;

/**
 * OSS资源管理
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
@Controller
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequestMapping(value = "/common/1.0/file", method = { RequestMethod.POST})
public class ApiOSSOperateController extends MyTokenCommonSupport {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	MyFileOperatorSupport MyFileOperatorSupport_;

	/**
	 * 文件上传接口
	 * 
	 * @param jobId
	 * @param pageCurrent
	 * @param pageLimit
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/upload/api", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean<String> fileUploadApi(HttpServletRequest request, HttpServletResponse response, String token,String jobId, MultipartFile upfile) {
		RESTResultBean<String> result = new RESTResultBean<String>();
		result.setJobId(jobId);
		try {
			if (upfile != null) {
				result.setData(MyFileOperatorSupport_.putObject(upfile, true));
				result.setMsg("操作成功");
			}
		} catch (Throwable e) {
			e.printStackTrace();
			result.setCode(1);
			result.setMsg("操作失败");
		}
		logger.debug("upload=====>>>>>" + result.toString());
		return result; // 返回result对象给前端
	}

	/**
	 * 文件上传页面组件
	 * 
	 * @param request
	 * @param response
	 * @param width
	 * @param height
	 * @param token
	 * @param id
	 * @param upfile
	 * @return
	 */
	@RequestMapping(value = "/upload/page", method = RequestMethod.POST)
	public ModelAndView fileUploadPage(HttpServletRequest request, HttpServletResponse response, int width, int height, String token, String jobId, MultipartFile upfile) {
		ModelAndView model = new ModelAndView("/common/fileupload");
		RESTResultBean<String> result = fileUploadApi(request, response, token, jobId, upfile);
		model.addObject("width", width);
		model.addObject("height", height);
		model.addObject("jobId", jobId);
		model.addObject("result", result);
		logger.debug("upload=====>>>>>" + result.toString());
		return model; // 返回result对象给前端
	}

	/**
	 * 富文本编辑器内上传
	 * 
	 * @author ZmSoft
	 * @param jobId
	 * @param upFile
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/file/upload/edit", method = RequestMethod.POST)
	public ModelAndView fileUploadEdit(String jobId, MultipartFile upFile) throws Exception {
		ModelAndView model = new ModelAndView("/common/pic2");
		String pic = "";
		try {
			if (upFile != null) {
				pic = MyFileOperatorSupport_.putObject(upFile, true);
			}
		} catch (Throwable e) {
			e.printStackTrace();
			pic = "error|服务器异常";
		}
		model.addObject("pic", pic);
		logger.debug("upload=====>>>>>" + pic);
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
	@RequestMapping(value = "/file/discard/api", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean<String> fileDiscardApi(String jobId, String ossUrl) {
		RESTResultBean<String> result = new RESTResultBean<String>();
		result.setJobId(jobId);
		try {
			String[] ks = ossUrl.split("/");
			MyFileOperatorSupport_.removeObject(ks[ks.length - 1]);
		} catch (Throwable e) {
			result.setCode(1);
			result.setMsg("操作失败");
		}
		return result; // 返回result对象给前端
	}

}
