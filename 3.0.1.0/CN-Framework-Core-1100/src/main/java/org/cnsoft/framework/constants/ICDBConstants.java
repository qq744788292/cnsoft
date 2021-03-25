package org.cnsoft.framework.constants;

/**
 * 系统常量
 *
 * @author CNSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public interface ICDBConstants {

	public final static String MSG_MQ_LIST = "DBA_MSG_MQ_LIST";

	public final static String FILE_EXTENSION_NAME_SQL = ".sql";
	public final static String CREATE_TABLE = "CREATE TABLE ";
	public final static String SELECT_TABLE = "SELECT * FROM ";
	public final static String DROP_TABLE = "DROP TABLE IF EXISTS ";
	
	public final static String MESSAGE_STATEMENT_START = ">>>>>数据库连接获得成功>>>>>";
	public final static String MESSAGE_STATEMENT_END = "<<<<<数据库处理结束<<<<<";

	public final static String MESSAGE_SQLLOCATIONS_NULL = "没有找到任何SQL配置文件";

	public final static String MESSAGE_DATALOCATIONS_NULL = "没有找到任何初始化数据文件";

	public final static String MESSAGE_SUCCESS = "操作成功";
	public final static String MESSAGE_FAIL = "操作失败";

	public final static String DataSource_MYSQL = "MySql";
	public final static String DataSource_DEFAULT = DataSource_MYSQL;

	public final static String Default_Dialect = "DefaultDialect";

	public final static String Orderby_Weight_ASC = "weight ASC";
	public final static String Orderby_Id_ASC = "id ASC";
	
	public final static String Orderby_Weight_DESC = "weight DESC";
	public final static String Orderby_CreateTime_DESC = "create_time DESC";
	public final static String Orderby_Id_DESC = "id DESC";
	public final static String Orderby_Rand = "rand()";

}
