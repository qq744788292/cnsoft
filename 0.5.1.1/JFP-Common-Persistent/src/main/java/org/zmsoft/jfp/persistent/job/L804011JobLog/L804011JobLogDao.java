package org.zmsoft.jfp.persistent.job.L804011JobLog;

import java.util.List;

import org.zmsoft.jfp.framework.support.ISDatabaseSupport;

/** 定时任务运行日志表*/
public interface L804011JobLogDao extends ISDatabaseSupport<L804011JobLogDBO> {

	List<L804011JobLogDBO> doSelectTotal(L804011JobLogDBO param);

}
