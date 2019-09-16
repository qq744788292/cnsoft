package org.isotope.jfp.mpc.weixin.beans.recevers;

import java.util.ArrayList;

import org.isotope.jfp.framework.beans.message.info.UserReceverBean;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.isotope.jfp.mpc.weixin.beans.recever.WeiXinCompanyGroupUserReceverBean;
import org.isotope.jfp.mpc.weixin.beans.recever.WeiXinCompanyTagReceverBean;

/**
 * 微信企业号标签信息
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 *
 */
public class WeiXinCompanyTagReceverListBean extends UserReceverBean {
	ArrayList<WeiXinCompanyTagReceverBean> recevers = new ArrayList<WeiXinCompanyTagReceverBean>();

	public ArrayList<WeiXinCompanyTagReceverBean> getRecevers() {
		return recevers;
	}
	/**
	 * 消息群发
	 * @param wxId
	 */
	public void setRecevers(String... tagWxId) {
		if (EmptyHelper.isNotEmpty(tagWxId)) {
			WeiXinCompanyTagReceverBean user;
			for (String wxId : tagWxId) {
				user = new WeiXinCompanyTagReceverBean();
				user.setTagId(wxId);
				user.setWxId(wxId);
				recevers.add(user);
			}
		}
	}
	public void setRecevers(ArrayList<WeiXinCompanyTagReceverBean> recevers) {
		this.recevers = recevers;
	}
	public void addRecevers(WeiXinCompanyTagReceverBean recever) {
		this.recevers.add(recever);
	}
}
