package org.zmsoft.jfp.framework.constants;

/**
 * 系统常量
 *
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public interface IDBConstants {

	public final static String FILE_EXTENSION_NAME_SQL = ".sql";
	public final static String CREATE_TABLE = "CREATE TABLE ";
	public final static String DROP_TABLE = "DROP TABLE ";
	public final static String MESSAGE_STATEMENT_START = ">>>>>数据库连接获得成功>>>>>";
	public final static String MESSAGE_STATEMENT_END = "<<<<<数据库处理结束<<<<<";
	public final static String MESSAGE_SQLLOCATIONS_NULL = "没有找到任何SQL配置文件";

	public final static String MESSAGE_DATALOCATIONS_NULL = "没有找到任何初始化数据文件";
	

	public final static String MESSAGE_DB_SELECT = "数据查询成功";
	public final static String MESSAGE_DB_INSERT = "数据增加成功";
	public final static String MESSAGE_DB_UPDATE = "数据更新成功";
	public final static String MESSAGE_DB_DELETE = "数据删除成功";
	public final static String MESSAGE_DB_SAVE = "数据保存成功";
	public final static String MESSAGE_DB_FAIL = "数据操作失败";

	public final static String DataSource_MYSQL = "MySql";
	public final static String DataSource_DEFAULT = DataSource_MYSQL;

	public final static int DB_SELECT = 0;
	public final static int DB_INSERT = 1;
	public final static int DB_UPDATE = 2;
	public final static int DB_DELETE = 3;
}
