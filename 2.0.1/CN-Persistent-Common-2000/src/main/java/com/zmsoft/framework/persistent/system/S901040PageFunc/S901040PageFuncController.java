package com.zmsoft.framework.persistent.system.S901040PageFunc;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.beans.page.PageModel;
import org.zmsoft.framework.support.MyControllerSupport;


/**
 * 页面按钮接口
 * @version 0.1.1
 * @since 0.1.1
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api/1.0/base/s901040pagefunc", method = { RequestMethod.POST})
public class S901040PageFuncController extends MyControllerSupport { //MyTokenCommonSupport
    @Resource
    protected S901040PageFuncBusinesslogic bizS901040PageFunc;

    //private MyModelAndViewSupport model = super.getModelAndView("S901040_page_func");

    //一览（分页查询）
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public RESTResultBean<S901040PageFuncDBO> doList(S901040PageFuncDBO param, PageModel<S901040PageFuncDBO> pageModel) throws Exception {
        // 输出参数日志
        logger.debug("param=====>>>>" + param.toString());
        // 设定查询参数
        pageModel.setFormParamBean(param);
        // 开启记录数统计
        //pageModel.setResultCountFlag(true);
        // 分页查询
        return bizS901040PageFunc.doList(pageModel);
    }
    
    //信息详情
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public RESTResultBean<S901040PageFuncDBO> doInfo(S901040PageFuncDBO param) throws Exception {
        // 输出参数日志
        logger.debug("param=====>>>>" + param.toString());
        // 数据查询
        return bizS901040PageFunc.doInfo(param);
    }
    
    //信息插入
    @RequestMapping(value = "/append", method = RequestMethod.POST)
    public RESTResultBean<String> doAppend(S901040PageFuncDBO param) throws Exception {
        // 输出参数日志
        logger.debug("param=====>>>>" + param.toString());
        // 信息插入
        return bizS901040PageFunc.doAppend(param);
    }
    
    //信息编辑
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public RESTResultBean<String> doModify(S901040PageFuncDBO param) throws Exception {
        // 输出参数日志
        logger.debug("param=====>>>>" + param.toString());
        // 信息插入
        return bizS901040PageFunc.doModify(param);
    }
    
    //信息删除
    @RequestMapping(value = "/discard", method = RequestMethod.POST)
    public RESTResultBean<String> doDiscard(S901040PageFuncDBO param) throws Exception {
        // 输出参数日志
        logger.debug("param=====>>>>" + param.toString());
        // 信息插入
        return bizS901040PageFunc.doDiscard(param);
    }
}
