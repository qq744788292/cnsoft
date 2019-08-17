package com.zmsoft.framework.persistent.system.S901060Parameter;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.beans.page.PageModel;
import org.zmsoft.framework.support.MyControllerSupport;


/**
 * 分类参数定义接口
 * @version 0.1.1
 * @since 0.1.1
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api/1.0/base/s901060parameter", method = { RequestMethod.POST})
public class S901060ParameterController extends MyControllerSupport { //MyTokenCommonSupport
    @Resource
    protected S901060ParameterBusinesslogic bizS901060Parameter;

    //private MyModelAndViewSupport model = super.getModelAndView("S901060_parameter");

//    //一览（分页查询）
//    @RequestMapping(value = "/list", method = RequestMethod.POST)
//    public RESTResultBean<S901060ParameterDBO> doList(S901060ParameterDBO param, PageModel<S901060ParameterDBO> pageModel) throws Exception {
//        // 输出参数日志
//        logger.debug("param=====>>>>" + param.toString());
//        // 设定查询参数
//        pageModel.setFormParamBean(param);
//        // 开启记录数统计
//        //pageModel.setResultCountFlag(true);
//        // 分页查询
//        return bizS901060Parameter.doList(pageModel);
//    }
//    
//    //信息详情
//    @RequestMapping(value = "/info", method = RequestMethod.POST)
//    public RESTResultBean<S901060ParameterDBO> doInfo(S901060ParameterDBO param) throws Exception {
//        // 输出参数日志
//        logger.debug("param=====>>>>" + param.toString());
//        // 数据查询
//        return bizS901060Parameter.doInfo(param);
//    }
    
    //信息插入
    @RequestMapping(value = "/append", method = RequestMethod.POST)
    public RESTResultBean<String> doAppend(S901060ParameterDBO param) throws Exception {
        // 输出参数日志
        logger.debug("param=====>>>>" + param.toString());
        // 信息插入
        return bizS901060Parameter.doAppend(param);
    }
    
    //信息编辑
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public RESTResultBean<String> doModify(S901060ParameterDBO param) throws Exception {
        // 输出参数日志
        logger.debug("param=====>>>>" + param.toString());
        // 信息插入
        return bizS901060Parameter.doModify(param);
    }
    
    //信息删除
    @RequestMapping(value = "/discard", method = RequestMethod.POST)
    public RESTResultBean<String> doDiscard(S901060ParameterDBO param) throws Exception {
        // 输出参数日志
        logger.debug("param=====>>>>" + param.toString());
        // 信息插入
        return bizS901060Parameter.doDiscard(param);
    }
}
