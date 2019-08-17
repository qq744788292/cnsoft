package org.zmsoft.framework.beans;

/**
 * 关联企业管理机构定义
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/08/08
 * @since 1.0.0 2018/02/02
 * @see <MyFrameWorkSupport>
 */
public class RoleBean extends ObjectBean {
	/**
	 * 企业类型（10企业20政府部门30电脑网站）
	 */
	private String companyType;
	/**
	 * 企业ID
	 */
	private String companyId;
	/**
	 * 企业名称
	 */
	private String companyNameCN;

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyNameCN() {
		return companyNameCN;
	}

	public void setCompanyNameCN(String companyNameCN) {
		this.companyNameCN = companyNameCN;
	}

}
