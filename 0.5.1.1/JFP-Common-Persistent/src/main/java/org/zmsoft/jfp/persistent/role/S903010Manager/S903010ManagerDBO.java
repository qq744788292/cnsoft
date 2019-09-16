package org.zmsoft.jfp.persistent.role.S903010Manager;

import org.zmsoft.jfp.framework.support.MyDataBaseObjectSupport3;

import java.sql.Date;
 
/** 系统管理用户表*/
public class S903010ManagerDBO extends MyDataBaseObjectSupport3
{
    /** 
     * 用户手机号码
     */
    private String userPhone = null;
 
    /** 
     * 用户账户
     */
    private String userAccount = null;
 
    /** 
     * 登录密码
     */
    private String userPassword = null;
 
    /** 
     * 企业ID
     */
    private String companyId = null;
 
    /** 
     * 企业名称
     */
    private String companyName = null;
 
    /** 
     * 所属部门ID
     */
    private String departmentId = null;
 
    /** 
     * 所属部门名称
     */
    private String departmentName = null;
 
    /** 
     * 用户姓名
     */
    private String userName = null;
 
    /** 
     * 用户性别
     */
    private String userSex = null;
 
    /** 
     * 用户邮箱
     */
    private String userMail = null;
 
    /** 
     * 用户头像
     */
    private String picUrl = null;
 
    /** 
     * 账户状态
     */
    private String allowLogin = null;
 
    /** 
     * 在线状态
     */
    private String onlineType = null;
 
    /** 
     * 最后登录IP
     */
    private String onlineLastIp = null;
 
    /** 
     * 最后登录时间
     */
    private String onlineLastTime = null;
 
    /** 
     * 数值型变量初始化<br>
     * 仅在插入场合默认调用
     * @see #loadDefauft()
     */
    public void prepareNumeric() {
    }
 
    /** 
     * 获取用户手机号码
     *
     * @return User_phone 用户手机号码
     */
    public String getUserPhone() {
        return this.userPhone;
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
     * 获取登录密码
     *
     * @return User_password 登录密码
     */
    public String getUserPassword() {
        return this.userPassword;
    }
 
    /** 
     * 获取企业ID
     *
     * @return Company_id 企业ID
     */
    public String getCompanyId() {
        return this.companyId;
    }
 
    /** 
     * 获取企业名称
     *
     * @return Company_name 企业名称
     */
    public String getCompanyName() {
        return this.companyName;
    }
 
    /** 
     * 获取所属部门ID
     *
     * @return Department_id 所属部门ID
     */
    public String getDepartmentId() {
        return this.departmentId;
    }
 
    /** 
     * 获取所属部门名称
     *
     * @return Department_name 所属部门名称
     */
    public String getDepartmentName() {
        return this.departmentName;
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
     * 获取用户性别
     *
     * @return User_sex 用户性别
     */
    public String getUserSex() {
        return this.userSex;
    }
 
    /** 
     * 获取用户邮箱
     *
     * @return User_mail 用户邮箱
     */
    public String getUserMail() {
        return this.userMail;
    }
 
    /** 
     * 获取用户头像
     *
     * @return Pic_url 用户头像
     */
    public String getPicUrl() {
        return this.picUrl;
    }
 
    /** 
     * 获取账户状态
     *
     * @return Allow_login 账户状态
     */
    public String getAllowLogin() {
        return this.allowLogin;
    }
 
    /** 
     * 获取在线状态
     *
     * @return Online_type 在线状态
     */
    public String getOnlineType() {
        return this.onlineType;
    }
 
    /** 
     * 获取最后登录IP
     *
     * @return Online_last_ip 最后登录IP
     */
    public String getOnlineLastIp() {
        return this.onlineLastIp;
    }
 
    /** 
     * 获取最后登录时间
     *
     * @return Online_last_time 最后登录时间
     */
    public String getOnlineLastTime() {
        return this.onlineLastTime;
    }
 
    /** 
     * 设置用户手机号码
     *
     * @param User_phone 用户手机号码
     */
    public void setUserPhone(String userphone) {
        this.userPhone = userphone;
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
     * 设置登录密码
     *
     * @param User_password 登录密码
     */
    public void setUserPassword(String userpassword) {
        this.userPassword = userpassword;
    }
 
    /** 
     * 设置企业ID
     *
     * @param Company_id 企业ID
     */
    public void setCompanyId(String companyid) {
        this.companyId = companyid;
    }
 
    /** 
     * 设置企业名称
     *
     * @param Company_name 企业名称
     */
    public void setCompanyName(String companyname) {
        this.companyName = companyname;
    }
 
    /** 
     * 设置所属部门ID
     *
     * @param Department_id 所属部门ID
     */
    public void setDepartmentId(String departmentid) {
        this.departmentId = departmentid;
    }
 
    /** 
     * 设置所属部门名称
     *
     * @param Department_name 所属部门名称
     */
    public void setDepartmentName(String departmentname) {
        this.departmentName = departmentname;
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
     * 设置用户性别
     *
     * @param User_sex 用户性别
     */
    public void setUserSex(String usersex) {
        this.userSex = usersex;
    }
 
    /** 
     * 设置用户邮箱
     *
     * @param User_mail 用户邮箱
     */
    public void setUserMail(String usermail) {
        this.userMail = usermail;
    }
 
    /** 
     * 设置用户头像
     *
     * @param Pic_url 用户头像
     */
    public void setPicUrl(String picurl) {
        this.picUrl = picurl;
    }
 
    /** 
     * 设置账户状态
     *
     * @param Allow_login 账户状态
     */
    public void setAllowLogin(String allowlogin) {
        this.allowLogin = allowlogin;
    }
 
    /** 
     * 设置在线状态
     *
     * @param Online_type 在线状态
     */
    public void setOnlineType(String onlinetype) {
        this.onlineType = onlinetype;
    }
 
    /** 
     * 设置最后登录IP
     *
     * @param Online_last_ip 最后登录IP
     */
    public void setOnlineLastIp(String onlinelastip) {
        this.onlineLastIp = onlinelastip;
    }
 
    /** 
     * 设置最后登录时间
     *
     * @param Online_last_time 最后登录时间
     */
    public void setOnlineLastTime(String onlinelasttime) {
        this.onlineLastTime = onlinelasttime;
    }
 
}
