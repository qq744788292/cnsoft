package com.zmsoft.framework.persistent.system.S901050Classify;
import javax.annotation.Resource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.beans.page.PageModel;
import org.zmsoft.framework.constants.ECodeMessageConstants;
import org.zmsoft.framework.support.MyBusinessLogicSupport;

/** 业务分类*/
@Component("S901050ClassifyBusinesslogic")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class S901050ClassifyBusinesslogic extends MyBusinessLogicSupport {
    @Resource
    private S901050ClassifyDao daoS901050Classify;

    //获得API地址
    public String loadApiDocName() throws Exception {
        return "业务分类;/doc/1.0/base/s901050classify/all";
    }
    
    //一览（分页查询）
    public RESTResultBean<S901050ClassifyDBO> doList(PageModel<S901050ClassifyDBO> pageModel) {
        // 定义返回结果集合
        RESTResultBean<S901050ClassifyDBO> result = new RESTResultBean<S901050ClassifyDBO>();
        // 分页查询
        result.setData(daoS901050Classify.doSelectPage(pageModel));
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_SELECT);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
         return result;
    }
    
    //信息详情
    public RESTResultBean<S901050ClassifyDBO> doInfo(S901050ClassifyDBO param) {
        // 定义返回结果集合
        RESTResultBean<S901050ClassifyDBO> result = new RESTResultBean<S901050ClassifyDBO>();
        // 查询详情
        result.setData(daoS901050Classify.doRead(param));
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_SELECT);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
         return result;
    }
    
    //信息插入
    public RESTResultBean<String> doAppend(S901050ClassifyDBO param) {
        // 定义返回结果集合
        RESTResultBean<String> result = new RESTResultBean<String>();
        // 信息插入
        daoS901050Classify.doInsert(param);
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_INSERT);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
        return result;
    }
    
    //信息编辑
    public RESTResultBean<String> doModify(S901050ClassifyDBO param) {
        // 定义返回结果
        RESTResultBean<String> result = new RESTResultBean<String>();
        // 信息插入
        daoS901050Classify.doUpdate(param);
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_UPDATE);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
        return result;
    }
    
    //信息删除
    public RESTResultBean<String> doDiscard(S901050ClassifyDBO param) {
        // 定义返回结果
        RESTResultBean<String> result = new RESTResultBean<String>();
        // 输出参数日志
        logger.debug("param=====>>>>" + param.toString());
        // 信息插入
        daoS901050Classify.doDelete(param);
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_DELETE);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
        return result;
    }

}
