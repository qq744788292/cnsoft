package org.zmsoft.jfp.persistent.role.L908010ManagerLogin;
import org.zmsoft.jfp.framework.support.MyDataBaseObjectSupport3;
 
/** 系统管理用户登录日志表*/
public class L908010ManagerLoginDBO extends MyDataBaseObjectSupport3
{
    /** 
     * 用户ID
     */
    private String userId = null;
 
    /** 
     * 用户账户
     */
    private String userAccount = null;
 
    /** 
     * 用户姓名
     */
    private String userName = null;
 
    /** 
     * 登录时间
     */
    private String loginTime = null;
 
    /** 
     * 登录IP
     */
    private String loginIp = null;
 
    /** 
     * 数值型变量初始化<br>
     * 仅在插入场合默认调用
     * @see #loadDefauft()
     */
    public void prepareNumeric() {
    }
 
    /** 
     * 获取用户ID
     *
     * @return User_id 用户ID
     */
    public String getUserId() {
        return this.userId;
    }
 
    /** 
     * 获取用户账户
     *
     * @return User_account 用户账户
     */
    public String getUserAccount() {
        return this.userAccount;
    }
 
    /** 
     * 获取用户姓名
     *
     * @return User_name 用户姓名
     */
    public String getUserName() {
        return this.userName;
    }
 
    /** 
     * 获取登录时间
     *
     * @return Login_time 登录时间
     */
    public String getLoginTime() {
        return this.loginTime;
    }
 
    /** 
     * 获取登录IP
     *
     * @return Login_ip 登录IP
     */
    public String getLoginIp() {
        return this.loginIp;
    }
 
    /** 
     * 设置用户ID
     *
     * @param User_id 用户ID
     */
    public void setUserId(String userid) {
        this.userId = userid;
    }
 
    /** 
     * 设置用户账户
     *
     * @param User_account 用户账户
     */
    public void setUserAccount(String useraccount) {
        this.userAccount = useraccount;
    }
 
    /** 
     * 设置用户姓名
     *
     * @param User_name 用户姓名
     */
    public void setUserName(String username) {
        this.userName = username;
    }
 
    /** 
     * 设置登录时间
     *
     * @param Login_time 登录时间
     */
    public void setLoginTime(String logintime) {
        this.loginTime = logintime;
    }
 
    /** 
     * 设置登录IP
     *
     * @param Login_ip 登录IP
     */
    public void setLoginIp(String loginip) {
        this.loginIp = loginip;
    }
 
}
