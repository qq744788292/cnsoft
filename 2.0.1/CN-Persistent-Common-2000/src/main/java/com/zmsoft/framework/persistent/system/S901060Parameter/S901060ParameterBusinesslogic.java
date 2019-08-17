package com.zmsoft.framework.persistent.system.S901060Parameter;
import javax.annotation.Resource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.beans.page.PageModel;
import org.zmsoft.framework.constants.ECodeMessageConstants;
import org.zmsoft.framework.support.MyBusinessLogicSupport;

/** 分类参数定义*/
@Component("S901060ParameterBusinesslogic")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class S901060ParameterBusinesslogic extends MyBusinessLogicSupport {
    @Resource
    private S901060ParameterDao daoS901060Parameter;

    //获得API地址
    public String loadApiDocName() throws Exception {
        return "分类参数定义;/doc/1.0/base/s901060parameter/all";
    }
    
    //一览（分页查询）
    public RESTResultBean<S901060ParameterDBO> doList(PageModel<S901060ParameterDBO> pageModel) {
        // 定义返回结果集合
        RESTResultBean<S901060ParameterDBO> result = new RESTResultBean<S901060ParameterDBO>();
        // 分页查询
        result.setData(daoS901060Parameter.doSelectPage(pageModel));
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_SELECT);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
         return result;
    }
    
    //信息详情
    public RESTResultBean<S901060ParameterDBO> doInfo(S901060ParameterDBO param) {
        // 定义返回结果集合
        RESTResultBean<S901060ParameterDBO> result = new RESTResultBean<S901060ParameterDBO>();
        // 查询详情
        result.setData(daoS901060Parameter.doRead(param));
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_SELECT);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
         return result;
    }
    
    //信息插入
    public RESTResultBean<String> doAppend(S901060ParameterDBO param) {
        // 定义返回结果集合
        RESTResultBean<String> result = new RESTResultBean<String>();
        // 信息插入
        daoS901060Parameter.doInsert(param);
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_INSERT);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
        return result;
    }
    
    //信息编辑
    public RESTResultBean<String> doModify(S901060ParameterDBO param) {
        // 定义返回结果
        RESTResultBean<String> result = new RESTResultBean<String>();
        // 信息插入
        daoS901060Parameter.doUpdate(param);
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_UPDATE);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
        return result;
    }
    
    //信息删除
    public RESTResultBean<String> doDiscard(S901060ParameterDBO param) {
        // 定义返回结果
        RESTResultBean<String> result = new RESTResultBean<String>();
        // 输出参数日志
        logger.debug("param=====>>>>" + param.toString());
        // 信息插入
        daoS901060Parameter.doDelete(param);
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_DELETE);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
        return result;
    }

}
