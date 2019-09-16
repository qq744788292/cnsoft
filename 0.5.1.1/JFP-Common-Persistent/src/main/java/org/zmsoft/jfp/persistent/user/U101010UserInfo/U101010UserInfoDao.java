package org.zmsoft.jfp.persistent.user.U101010UserInfo;

import org.zmsoft.jfp.framework.beans.page.PageModel;
import org.zmsoft.jfp.framework.support.ISDatabaseSupport;

import java.util.List;

/** 玩家基本信息*/
public interface U101010UserInfoDao extends ISDatabaseSupport<U101010UserInfoDBO> {

    List<U101010UserInfoDBO> doSelectPageByHead(PageModel<U101010UserInfoDBO> pageModel);
}
