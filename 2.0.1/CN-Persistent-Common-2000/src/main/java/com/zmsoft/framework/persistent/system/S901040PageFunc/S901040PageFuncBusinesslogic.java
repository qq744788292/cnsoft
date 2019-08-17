package com.zmsoft.framework.persistent.system.S901040PageFunc;
import javax.annotation.Resource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.beans.page.PageModel;
import org.zmsoft.framework.constants.ECodeMessageConstants;
import org.zmsoft.framework.support.MyBusinessLogicSupport;

/** 页面按钮*/
@Component("S901040PageFuncBusinesslogic")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class S901040PageFuncBusinesslogic extends MyBusinessLogicSupport {
    @Resource
    private S901040PageFuncDao daoS901040PageFunc;

    //获得API地址
    public String loadApiDocName() throws Exception {
        return "页面按钮;/doc/1.0/base/s901040pagefunc/all";
    }
    
    //一览（分页查询）
    public RESTResultBean<S901040PageFuncDBO> doList(PageModel<S901040PageFuncDBO> pageModel) {
        // 定义返回结果集合
        RESTResultBean<S901040PageFuncDBO> result = new RESTResultBean<S901040PageFuncDBO>();
        // 分页查询
        result.setData(daoS901040PageFunc.doSelectPage(pageModel));
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_SELECT);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
         return result;
    }
    
    //信息详情
    public RESTResultBean<S901040PageFuncDBO> doInfo(S901040PageFuncDBO param) {
        // 定义返回结果集合
        RESTResultBean<S901040PageFuncDBO> result = new RESTResultBean<S901040PageFuncDBO>();
        // 查询详情
        result.setData(daoS901040PageFunc.doRead(param));
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_SELECT);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
         return result;
    }
    
    //信息插入
    public RESTResultBean<String> doAppend(S901040PageFuncDBO param) {
        // 定义返回结果集合
        RESTResultBean<String> result = new RESTResultBean<String>();
        // 信息插入
        daoS901040PageFunc.doInsert(param);
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_INSERT);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
        return result;
    }
    
    //信息编辑
    public RESTResultBean<String> doModify(S901040PageFuncDBO param) {
        // 定义返回结果
        RESTResultBean<String> result = new RESTResultBean<String>();
        // 信息插入
        daoS901040PageFunc.doUpdate(param);
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_UPDATE);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
        return result;
    }
    
    //信息删除
    public RESTResultBean<String> doDiscard(S901040PageFuncDBO param) {
        // 定义返回结果
        RESTResultBean<String> result = new RESTResultBean<String>();
        // 输出参数日志
        logger.debug("param=====>>>>" + param.toString());
        // 信息插入
        daoS901040PageFunc.doDelete(param);
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_DELETE);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
        return result;
    }

}
