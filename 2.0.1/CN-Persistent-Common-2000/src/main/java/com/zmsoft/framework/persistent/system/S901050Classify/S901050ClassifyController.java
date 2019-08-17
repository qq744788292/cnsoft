package com.zmsoft.framework.persistent.system.S901050Classify;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.beans.page.PageModel;
import org.zmsoft.framework.support.MyControllerSupport;


/**
 * 业务分类接口
 * @version 0.1.1
 * @since 0.1.1
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api/1.0/base/s901050classify", method = { RequestMethod.POST})
public class S901050ClassifyController extends MyControllerSupport { //MyTokenCommonSupport
    @Resource
    protected S901050ClassifyBusinesslogic bizS901050Classify;

    //private MyModelAndViewSupport model = super.getModelAndView("S901050_classify");

    //一览（分页查询）
//    @RequestMapping(value = "/list", method = RequestMethod.POST)
//    public RESTResultBean<S901050ClassifyDBO> doList(S901050ClassifyDBO param, PageModel<S901050ClassifyDBO> pageModel) throws Exception {
//        // 输出参数日志
//        logger.debug("param=====>>>>" + param.toString());
//        // 设定查询参数
//        pageModel.setFormParamBean(param);
//        // 开启记录数统计
//        //pageModel.setResultCountFlag(true);
//        // 分页查询
//        return bizS901050Classify.doList(pageModel);
//    }
//    
//    //信息详情
//    @RequestMapping(value = "/info", method = RequestMethod.POST)
//    public RESTResultBean<S901050ClassifyDBO> doInfo(S901050ClassifyDBO param) throws Exception {
//        // 输出参数日志
//        logger.debug("param=====>>>>" + param.toString());
//        // 数据查询
//        return bizS901050Classify.doInfo(param);
//    }
    
    //信息插入
    @RequestMapping(value = "/append", method = RequestMethod.POST)
    public RESTResultBean<String> doAppend(S901050ClassifyDBO param) throws Exception {
        // 输出参数日志
        logger.debug("param=====>>>>" + param.toString());
        // 信息插入
        return bizS901050Classify.doAppend(param);
    }
    
    //信息编辑
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public RESTResultBean<String> doModify(S901050ClassifyDBO param) throws Exception {
        // 输出参数日志
        logger.debug("param=====>>>>" + param.toString());
        // 信息插入
        return bizS901050Classify.doModify(param);
    }
    
    //信息删除
    @RequestMapping(value = "/discard", method = RequestMethod.POST)
    public RESTResultBean<String> doDiscard(S901050ClassifyDBO param) throws Exception {
        // 输出参数日志
        logger.debug("param=====>>>>" + param.toString());
        // 信息插入
        return bizS901050Classify.doDiscard(param);
    }
}
