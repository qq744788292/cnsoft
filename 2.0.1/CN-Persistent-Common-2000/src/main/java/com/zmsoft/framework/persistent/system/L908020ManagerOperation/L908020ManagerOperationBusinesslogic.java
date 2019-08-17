package com.zmsoft.framework.persistent.system.L908020ManagerOperation;
import javax.annotation.Resource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.beans.page.PageModel;
import org.zmsoft.framework.constants.ECodeMessageConstants;
import org.zmsoft.framework.support.MyBusinessLogicSupport;

/** 系统管理用户操作日志表*/
@Component("L908020ManagerOperationBusinesslogic")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class L908020ManagerOperationBusinesslogic extends MyBusinessLogicSupport {
    @Resource
    private L908020ManagerOperationDao daoL908020ManagerOperation;

    //获得API地址
    public String loadApiDocName() throws Exception {
        return "系统管理用户操作日志表;/doc/1.0/base/l908020manageroperation/all";
    }
    
    //一览（分页查询）
    public RESTResultBean<L908020ManagerOperationDBO> doList(PageModel<L908020ManagerOperationDBO> pageModel) {
        // 定义返回结果集合
        RESTResultBean<L908020ManagerOperationDBO> result = new RESTResultBean<L908020ManagerOperationDBO>();
        // 分页查询
        result.setData(daoL908020ManagerOperation.doSelectPage(pageModel));
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_SELECT);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
         return result;
    }
    
    //信息详情
    public RESTResultBean<L908020ManagerOperationDBO> doInfo(L908020ManagerOperationDBO param) {
        // 定义返回结果集合
        RESTResultBean<L908020ManagerOperationDBO> result = new RESTResultBean<L908020ManagerOperationDBO>();
        // 查询详情
        result.setData(daoL908020ManagerOperation.doRead(param));
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_SELECT);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
         return result;
    }
    
    //信息插入
    public RESTResultBean<String> doAppend(L908020ManagerOperationDBO param) {
        // 定义返回结果集合
        RESTResultBean<String> result = new RESTResultBean<String>();
        // 信息插入
        daoL908020ManagerOperation.doInsert(param);
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_INSERT);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
        return result;
    }
    
    //信息编辑
    public RESTResultBean<String> doModify(L908020ManagerOperationDBO param) {
        // 定义返回结果
        RESTResultBean<String> result = new RESTResultBean<String>();
        // 信息插入
        daoL908020ManagerOperation.doUpdate(param);
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_UPDATE);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
        return result;
    }
    
    //信息删除
    public RESTResultBean<String> doDiscard(L908020ManagerOperationDBO param) {
        // 定义返回结果
        RESTResultBean<String> result = new RESTResultBean<String>();
        // 输出参数日志
        logger.debug("param=====>>>>" + param.toString());
        // 信息插入
        daoL908020ManagerOperation.doDelete(param);
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_DELETE);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
        return result;
    }

}
