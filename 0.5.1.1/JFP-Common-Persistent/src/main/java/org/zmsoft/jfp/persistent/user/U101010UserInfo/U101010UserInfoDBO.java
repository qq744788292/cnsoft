package org.zmsoft.jfp.persistent.user.U101010UserInfo;

import org.zmsoft.jfp.framework.support.MyDataBaseObjectSupport3;

import java.sql.Date;
 
/** 玩家基本信息*/
public class U101010UserInfoDBO extends MyDataBaseObjectSupport3
{
    /** 
     * 渠道标识
     */
    private String channelType = null;
 
    /** 
     * 注册方式
     */
    private String registerType = null;
 
    /** 
     * 手机号
     */
    private String userPhone = null;
 
    /** 
     * 微信ID
     */
    private String wxOpenId = null;
 
    /** 
     * QQID
     */
    private String qqOpenId = null;
 
    /** 
     * 昵称
     */
    private String nickName = null;
 
    /** 
     * 头像URL
     */
    private String headPortraitUrl = null;
 
    /** 
     * 性别
     */
    private String sex = null;
 
    /** 
     * 年龄段
     */
    private String ageGroup = null;
 
    /** 
     * 学历
     */
    private String education = null;
 
    /** 
     * 生日
     */
    private String birthday = null;
 
    /** 
     * 邮箱
     */
    private String mailbox = null;
 
    /** 
     * 真实姓名
     */
    private String realName = null;
 
    /** 
     * 身份证号码
     */
    private String idCard = null;
 
    /** 
     * 本人真实照片
     */
    private String realPhoto = null;
 
    /** 
     * 昵称审核状态
     */
    private String applyStateNick = null;
 
    /** 
     * 头像审核状态
     */
    private String applyStateHead = null;
 
    /** 
     * 实名审核状态
     */
    private String applyStateReal = null;
 
    /** 
     * 账号状态
     */
    private String state = null;
 
    /** 
     * 数值型变量初始化<br>
     * 仅在插入场合默认调用
     * @see #loadDefauft()
     */
    public void prepareNumeric() {
    }
 
    /** 
     * 获取渠道标识
     *
     * @return Channel_type 渠道标识
     */
    public String getChannelType() {
        return this.channelType;
    }
 
    /** 
     * 获取注册方式
     *
     * @return Register_type 注册方式
     */
    public String getRegisterType() {
        return this.registerType;
    }
 
    /** 
     * 获取手机号
     *
     * @return User_phone 手机号
     */
    public String getUserPhone() {
        return this.userPhone;
    }
 
    /** 
     * 获取微信ID
     *
     * @return Wx_open_id 微信ID
     */
    public String getWxOpenId() {
        return this.wxOpenId;
    }
 
    /** 
     * 获取QQID
     *
     * @return Qq_open_id QQID
     */
    public String getQqOpenId() {
        return this.qqOpenId;
    }
 
    /** 
     * 获取昵称
     *
     * @return Nick_name 昵称
     */
    public String getNickName() {
        return this.nickName;
    }
 
    /** 
     * 获取头像URL
     *
     * @return Head_portrait_url 头像URL
     */
    public String getHeadPortraitUrl() {
        return this.headPortraitUrl;
    }
 
    /** 
     * 获取性别
     *
     * @return Sex 性别
     */
    public String getSex() {
        return this.sex;
    }
 
    /** 
     * 获取年龄段
     *
     * @return Age_group 年龄段
     */
    public String getAgeGroup() {
        return this.ageGroup;
    }
 
    /** 
     * 获取学历
     *
     * @return Education 学历
     */
    public String getEducation() {
        return this.education;
    }
 
    /** 
     * 获取生日
     *
     * @return Birthday 生日
     */
    public String getBirthday() {
        return this.birthday;
    }
 
    /** 
     * 获取邮箱
     *
     * @return Mailbox 邮箱
     */
    public String getMailbox() {
        return this.mailbox;
    }
 
    /** 
     * 获取真实姓名
     *
     * @return Real_name 真实姓名
     */
    public String getRealName() {
        return this.realName;
    }
 
    /** 
     * 获取身份证号码
     *
     * @return Id_card 身份证号码
     */
    public String getIdCard() {
        return this.idCard;
    }
 
    /** 
     * 获取本人真实照片
     *
     * @return Real_photo 本人真实照片
     */
    public String getRealPhoto() {
        return this.realPhoto;
    }
 
    /** 
     * 获取昵称审核状态
     *
     * @return Apply_state_nick 昵称审核状态
     */
    public String getApplyStateNick() {
        return this.applyStateNick;
    }
 
    /** 
     * 获取头像审核状态
     *
     * @return Apply_state_head 头像审核状态
     */
    public String getApplyStateHead() {
        return this.applyStateHead;
    }
 
    /** 
     * 获取实名审核状态
     *
     * @return Apply_state_real 实名审核状态
     */
    public String getApplyStateReal() {
        return this.applyStateReal;
    }
 
    /** 
     * 获取账号状态
     *
     * @return State 账号状态
     */
    public String getState() {
        return this.state;
    }
 
    /** 
     * 设置渠道标识
     *
     * @param Channel_type 渠道标识
     */
    public void setChannelType(String channeltype) {
        this.channelType = channeltype;
    }
 
    /** 
     * 设置注册方式
     *
     * @param Register_type 注册方式
     */
    public void setRegisterType(String registertype) {
        this.registerType = registertype;
    }
 
    /** 
     * 设置手机号
     *
     * @param User_phone 手机号
     */
    public void setUserPhone(String userphone) {
        this.userPhone = userphone;
    }
 
    /** 
     * 设置微信ID
     *
     * @param Wx_open_id 微信ID
     */
    public void setWxOpenId(String wxopenid) {
        this.wxOpenId = wxopenid;
    }
 
    /** 
     * 设置QQID
     *
     * @param Qq_open_id QQID
     */
    public void setQqOpenId(String qqopenid) {
        this.qqOpenId = qqopenid;
    }
 
    /** 
     * 设置昵称
     *
     * @param Nick_name 昵称
     */
    public void setNickName(String nickname) {
        this.nickName = nickname;
    }
 
    /** 
     * 设置头像URL
     *
     * @param Head_portrait_url 头像URL
     */
    public void setHeadPortraitUrl(String headportraiturl) {
        this.headPortraitUrl = headportraiturl;
    }
 
    /** 
     * 设置性别
     *
     * @param Sex 性别
     */
    public void setSex(String sex) {
        this.sex = sex;
    }
 
    /** 
     * 设置年龄段
     *
     * @param Age_group 年龄段
     */
    public void setAgeGroup(String agegroup) {
        this.ageGroup = agegroup;
    }
 
    /** 
     * 设置学历
     *
     * @param Education 学历
     */
    public void setEducation(String education) {
        this.education = education;
    }
 
    /** 
     * 设置生日
     *
     * @param Birthday 生日
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
 
    /** 
     * 设置邮箱
     *
     * @param Mailbox 邮箱
     */
    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }
 
    /** 
     * 设置真实姓名
     *
     * @param Real_name 真实姓名
     */
    public void setRealName(String realname) {
        this.realName = realname;
    }
 
    /** 
     * 设置身份证号码
     *
     * @param Id_card 身份证号码
     */
    public void setIdCard(String idcard) {
        this.idCard = idcard;
    }
 
    /** 
     * 设置本人真实照片
     *
     * @param Real_photo 本人真实照片
     */
    public void setRealPhoto(String realphoto) {
        this.realPhoto = realphoto;
    }
 
    /** 
     * 设置昵称审核状态
     *
     * @param Apply_state_nick 昵称审核状态
     */
    public void setApplyStateNick(String applystatenick) {
        this.applyStateNick = applystatenick;
    }
 
    /** 
     * 设置头像审核状态
     *
     * @param Apply_state_head 头像审核状态
     */
    public void setApplyStateHead(String applystatehead) {
        this.applyStateHead = applystatehead;
    }
 
    /** 
     * 设置实名审核状态
     *
     * @param Apply_state_real 实名审核状态
     */
    public void setApplyStateReal(String applystatereal) {
        this.applyStateReal = applystatereal;
    }
 
    /** 
     * 设置账号状态
     *
     * @param State 账号状态
     */
    public void setState(String state) {
        this.state = state;
    }
 
}
