package org.zmsoft.framework.beans.db;

import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.cache.session.SessionHelper;
import org.zmsoft.framework.common.ISLoginer;
import org.zmsoft.framework.constants.ICDBConstants;
import org.zmsoft.framework.support.helper.MyManagerNameTransHelper;
import org.zmsoft.framework.utils.DateHelper;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.framework.utils.PKHelper;

/**
 * 数据持久层超类
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public abstract class MyDataBaseDefaultSupportBean extends MyDataBaseObjectBean implements ICDBConstants, ISLoginer {

	/**
	 * 当前登录用户
	 */
	public UserBean currentUser() {
		UserBean loginer = SessionHelper.currentUser();
		if (loginer == null) {
			throw new RuntimeException("当前用户没有登录");
		}
		return loginer;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 获得数据主键
	 * 
	 * @return
	 */
	public String currentPrimarykey() {
		return getId();
	}

	/**
	 * 创建一个默认的主键
	 */
	public void makePrimarykey() {
		setId(PKHelper.creatPUKey());
	}

	/////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 表名
	 */
	private String tableName = null;

	public String getTableName() {
		return tableName;
	}

	public void loadTableName() {
		if (EmptyHelper.isEmpty(tableName)) {
			tableName = this.getClass().getSimpleName();
			if (tableName.indexOf("DBO") > 0)
				tableName = tableName.substring(0, tableName.indexOf("DBO"));
		}
	}

	public void changeTableNameToTemp() {
		this.tableName = getTableName() + "_tmp";
	}

	public void setTableName(String tablename) {
		this.tableName = tablename;
	}

	//////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 插入场合的数据初始化
	 */
	public void prepareNumeric() {

	}

	/**
	 * 拦截创建信息
	 */
	public void preparePrimarykey() {
		// 主键ID
		if (EmptyHelper.isEmpty(currentPrimarykey()))
			makePrimarykey();
	}

	private final static String convertServiceName = "myManagerNameTransSupport";

	/**
	 * @see <MyDataBaseOperateSupport#doInsert(MyDataBaseObjectSupport , int )>
	 */
	public void loadDefauft() {
		this.preparePrimarykey();
		this.prepareNumeric();
	}

	/**
	 * 拦截有效标记信息(0无视，1如果空设定为有效，2如果空设定无效)
	 */
	public void prepareDeleteFlag(int flag) {
		if (flag == 0) {
			return;
		}
		if (EmptyHelper.isEmpty(getDelFlag())) {
			if (flag == 1) {
				this.setDelFlag(ZERO);
			} else if (flag == 2) {
				this.setDelFlag(ONE);
			}
		}
	}

	/**
	 * 拦截创建信息(0无视，1如果空设定为系统SYSTEM，2如果空设定为登录用户)
	 */
	public void prepareCreator(int flag) {
		// Timestamp d = new Timestamp(System.currentTimeMillis());
		String t = DateHelper.currentTimeMillisCN1();

		if (EmptyHelper.isEmpty(getCreateTime()))
			setCreateTime(t);

		if (flag == 3)
			setCreator(currentUser().getUserId());
		else if (EmptyHelper.isEmpty(getCreator())) {
			if (flag == 0 || flag == 1)
				setCreator(SYSTEM);
			else if (flag == 2) {
				setCreator(currentUser().getUserId());
			}
		}
	}

	/**
	 * 拦截更新信息(0无视，1如果空设定为系统SYSTEM，2如果空设定为登录用户，3强制设定为登录用户)
	 */
	public void prepareUpdator(int flag) {
		if (flag == 0) {
			return;
		}

		String t = DateHelper.currentTimeMillisCN1();

		if (EmptyHelper.isEmpty(getUuu()))
			setUuu(t);// 设置最新时间

		if (EmptyHelper.isEmpty(getUpdateTime())) {
			setUpdateTime(t);
		}

		if (flag == 3)
			setUpdator(currentUser().getUserId());
		else if (EmptyHelper.isEmpty(getUpdator())) {
			if (flag == 1)
				setUpdator(SYSTEM);
			else if (flag == 2)
				setUpdator(currentUser().getUserId());
		}
	}

	/**
	 * 备注
	 */
	private String meno = null;

	/**
	 * 获取备注
	 *
	 * @return Meno 备注
	 */
	public String getMeno() {
		return this.meno;
	}

	/**
	 * 设置备注
	 *
	 * @param Meno
	 *            备注
	 */
	public void setMeno(String meno) {
		this.meno = meno;
	}

	/**
	 * 有效标识
	 */
	private String delFlag = null;

	/**
	 * 创建时间
	 */
	private String createTime = null;

	/**
	 * 创建者
	 */
	private String creator = null;

	/**
	 * 更新时间
	 */
	private String updateTime = null;

	/**
	 * 最后更新者
	 */
	private String updator = null;

	/**
	 * 获取有效标识
	 *
	 * @return Del_flag 有效标识
	 */
	public String getDelFlag() {
		return this.delFlag;
	}

	/**
	 * 获取创建时间
	 *
	 * @return Create_time 创建时间
	 */
	public String getCreateTime() {
		return this.createTime;
	}

	/**
	 * 获取创建者
	 *
	 * @return Creator 创建者
	 */
	public String getCreator() {
		return this.creator;
	}

	public String getCreatorName() {
		if (converNameFlag)
			return MyManagerNameTransHelper.convertName(convertServiceName, this.creator);
		return this.creator;
	}

	/**
	 * 获取更新时间
	 *
	 * @return Update_time 更新时间
	 */
	public String getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 获取最后更新者
	 *
	 * @return Updator 最后更新者
	 */
	public String getUpdator() {
		return this.updator;
	}

	public String getUpdatorName() {
		if (converNameFlag)
			return MyManagerNameTransHelper.convertName(convertServiceName, this.updator);
		return this.updator;
	}

	/**
	 * 设置有效标识
	 *
	 * @param Del_flag
	 *            有效标识
	 */
	public void setDelFlag(String delflag) {
		this.delFlag = delflag;
	}

	/**
	 * 设置创建时间
	 *
	 * @param Create_time
	 *            创建时间
	 */
	public void setCreateTime(String createtime) {
		this.createTime = createtime;
	}

	/**
	 * 设置创建者
	 *
	 * @param Creator
	 *            创建者
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * 设置更新时间
	 *
	 * @param Update_time
	 *            更新时间
	 */
	public void setUpdateTime(String updatetime) {
		this.updateTime = updatetime;
	}

	/**
	 * 设置最后更新者
	 *
	 * @param Updator
	 *            最后更新者
	 */
	public void setUpdator(String updator) {
		this.updator = updator;
	}

	/**
	 * 是否开启名称转换（默认开启）
	 */
	private boolean converNameFlag = true;

	public void setConverNameFlag(boolean converNameFlag) {
		this.converNameFlag = converNameFlag;
	}


}
