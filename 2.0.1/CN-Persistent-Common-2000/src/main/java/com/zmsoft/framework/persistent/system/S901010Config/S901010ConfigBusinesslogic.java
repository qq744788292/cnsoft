package com.zmsoft.framework.persistent.system.S901010Config;
import javax.annotation.Resource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.beans.page.PageModel;
import org.zmsoft.framework.constants.ECodeMessageConstants;
import org.zmsoft.framework.support.MyBusinessLogicSupport;

/** 系统配置*/
@Component("S901010ConfigBusinesslogic")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class S901010ConfigBusinesslogic extends MyBusinessLogicSupport {
    @Resource
    private S901010ConfigDao daoS901010Config;

    //获得API地址
    public String loadApiDocName() throws Exception {
        return "系统配置;/doc/1.0/base/s901010config/all";
    }
    
    //一览（分页查询）
    public RESTResultBean<S901010ConfigDBO> doList(PageModel<S901010ConfigDBO> pageModel) {
        // 定义返回结果集合
        RESTResultBean<S901010ConfigDBO> result = new RESTResultBean<S901010ConfigDBO>();
        // 分页查询
        result.setData(daoS901010Config.doSelectPage(pageModel));
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_SELECT);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
         return result;
    }
    
    //信息详情
    public RESTResultBean<S901010ConfigDBO> doInfo(S901010ConfigDBO param) {
        // 定义返回结果集合
        RESTResultBean<S901010ConfigDBO> result = new RESTResultBean<S901010ConfigDBO>();
        // 查询详情
        result.setData(daoS901010Config.doRead(param));
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_SELECT);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
         return result;
    }
    
    //信息插入
    public RESTResultBean<String> doAppend(S901010ConfigDBO param) {
        // 定义返回结果集合
        RESTResultBean<String> result = new RESTResultBean<String>();
        // 信息插入
        daoS901010Config.doInsert(param);
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_INSERT);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
        return result;
    }
    
    //信息编辑
    public RESTResultBean<String> doModify(S901010ConfigDBO param) {
        // 定义返回结果
        RESTResultBean<String> result = new RESTResultBean<String>();
        // 信息插入
        daoS901010Config.doUpdate(param);
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_UPDATE);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
        return result;
    }
    
    //信息删除
    public RESTResultBean<String> doDiscard(S901010ConfigDBO param) {
        // 定义返回结果
        RESTResultBean<String> result = new RESTResultBean<String>();
        // 输出参数日志
        logger.debug("param=====>>>>" + param.toString());
        // 信息插入
        daoS901010Config.doDelete(param);
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_DELETE);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
        return result;
    }

}
