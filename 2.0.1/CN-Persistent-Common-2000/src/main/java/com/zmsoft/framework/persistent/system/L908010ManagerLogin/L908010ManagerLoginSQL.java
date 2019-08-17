package com.zmsoft.framework.persistent.system.L908010ManagerLogin;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zmsoft.framework.db.manager.MyDBTableSQLSupport;

/** 系统管理用户登录日志表 */
@Component("L908010ManagerLoginSQL")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class L908010ManagerLoginSQL extends MyDBTableSQLSupport {
	
	// 表名
	public String getTableName() {
		return "l908010_manager_login";
	}
	
	// 获得SQL内容
	public String getSQL() throws Exception {
		// SQL语句
		StringBuilder sqlStringBuilder = new StringBuilder();

		sqlStringBuilder.append("CREATE TABLE l908010_manager_login");
		sqlStringBuilder.append("(");
		sqlStringBuilder.append("id VARCHAR(64) NOT NULL COMMENT '流水ID',");
		sqlStringBuilder.append("user_id VARCHAR(64) COMMENT '用户ID',");
		sqlStringBuilder.append("user_account VARCHAR(40) COMMENT '用户账户',");
		sqlStringBuilder.append("user_name VARCHAR(80) COMMENT '用户姓名',");
		sqlStringBuilder.append("login_time VARCHAR(80) COMMENT '登录时间',");
		sqlStringBuilder.append("login_ip VARCHAR(40) COMMENT '登录IP',");
		sqlStringBuilder.append("meno VARCHAR(200) COMMENT '备注',");
		sqlStringBuilder.append("fb1 VARCHAR(40) COMMENT '备用1',");
		sqlStringBuilder.append("fb2 VARCHAR(20) COMMENT '备用2',");
		sqlStringBuilder.append("fb3 VARCHAR(50) COMMENT '备用3',");
		sqlStringBuilder.append("fb4 VARCHAR(200) COMMENT '备用4',");
		sqlStringBuilder.append("fb5 VARCHAR(80) COMMENT '备用5',");
		sqlStringBuilder.append("eb1 VARCHAR(40) COMMENT '扩展1',");
		sqlStringBuilder.append("eb2 VARCHAR(80) COMMENT '扩展2',");
		sqlStringBuilder.append("eb3 VARCHAR(20) COMMENT '扩展3',");
		sqlStringBuilder.append("eb4 VARCHAR(10) COMMENT '扩展4',");
		sqlStringBuilder.append("eb5 VARCHAR(20) COMMENT '扩展5',");
		sqlStringBuilder.append("hdp VARCHAR(20) COMMENT '数据归属系统',");
		sqlStringBuilder.append("del_flag CHAR(1) DEFAULT '0' COMMENT '有效标识',");
		sqlStringBuilder.append("create_time VARCHAR(24) DEFAULT '2018-02-02' NOT NULL COMMENT '创建时间',");
		sqlStringBuilder.append("creator VARCHAR(64) DEFAULT 'SYSTEM' NOT NULL COMMENT '创建者',");
		sqlStringBuilder.append("update_time VARCHAR(24) COMMENT '更新时间',");
		sqlStringBuilder.append("updator VARCHAR(64) COMMENT '最后更新者',");
		sqlStringBuilder.append("PRIMARY KEY (id)");
		sqlStringBuilder.append(") COMMENT '系统管理用户登录日志表'");
		sqlStringBuilder.append(";");

		return sqlStringBuilder.toString();
	}
}