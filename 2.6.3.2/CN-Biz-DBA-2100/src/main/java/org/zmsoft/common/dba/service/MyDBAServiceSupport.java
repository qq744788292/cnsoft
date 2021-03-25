package org.zmsoft.common.dba.service;

import org.zmsoft.framework.constants.ICDBConstants;
import org.zmsoft.framework.support.MyServiceSupport;

public class MyDBAServiceSupport extends MyServiceSupport implements ICDBConstants{

	public void saveLog(String msg){
		myCacheService.addObjectInList(MSG_MQ_LIST, msg);
	}
}
