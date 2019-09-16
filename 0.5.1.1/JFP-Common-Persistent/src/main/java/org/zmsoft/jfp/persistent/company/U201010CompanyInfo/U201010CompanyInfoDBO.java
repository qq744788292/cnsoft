package org.zmsoft.jfp.persistent.company.U201010CompanyInfo;

import org.zmsoft.jfp.framework.support.MyDataBaseObjectSupport3;

/** 企业基本信息*/
public class U201010CompanyInfoDBO extends MyDataBaseObjectSupport3
{
    /** 
     * 公司名称
     */
    private String companyName = null;
 
    /** 
     * 统一社会信息用代码
     */
    private String companyCode = null;
 
    /** 
     * 公司注册地址（国家）
     */
    private String companyAddressCountry = null;
 
    /** 
     * 公司注册地址（省）
     */
    private String companyAddressProvince = null;
 
    /** 
     * 公司注册地址（市）
     */
    private String companyAddressCity = null;
 
    /** 
     * 公司注册地址（区）
     */
    private String companyAddressArea = null;
 
    /** 
     * 公司注册地址（街道）
     */
    private String companyAddress = null;
 
    /** 
     * 营业执照(副本)URL
     */
    private String businessLicenseCopy = null;
 
    /** 
     * 法人姓名
     */
    private String corporationName = null;
 
    /** 
     * 法人电话
     */
    private String corporationPhone = null;
 
    /** 
     * 法人身份证(正面)URL
     */
    private String legalPersonIdcardFront = null;
 
    /** 
     * 法人身份证(反面)URL
     */
    private String legalPersonIdcardBack = null;
 
    /** 
     * 联系人身份证(正面)URL
     */
    private String contactIdcardFront = null;
 
    /** 
     * 联系人身份证(反面)URL
     */
    private String contactIdcardBack = null;
 
    /** 
     * 联系人姓名
     */
    private String contactName = null;
 
    /** 
     * 联系人电话
     */
    private String contactTelephone = null;
 
    /** 
     * 审核状态
     */
    private String examineState = null;
 
    /** 
     * 账户余额
     */
    private String balance = null;
 
    /** 
     * 数值型变量初始化<br>
     * 仅在插入场合默认调用
     * @see loadDefauft
     */
    public void prepareNumeric() {
    }
 
    /** 
     * 获取公司名称
     *
     * @return Company_name 公司名称
     */
    public String getCompanyName() {
        return this.companyName;
    }
 
    /** 
     * 获取统一社会信息用代码
     *
     * @return Company_code 统一社会信息用代码
     */
    public String getCompanyCode() {
        return this.companyCode;
    }
 
    /** 
     * 获取公司注册地址（国家）
     *
     * @return Company_address_country 公司注册地址（国家）
     */
    public String getCompanyAddressCountry() {
        return this.companyAddressCountry;
    }
 
    /** 
     * 获取公司注册地址（省）
     *
     * @return Company_address_province 公司注册地址（省）
     */
    public String getCompanyAddressProvince() {
        return this.companyAddressProvince;
    }
 
    /** 
     * 获取公司注册地址（市）
     *
     * @return Company_address_city 公司注册地址（市）
     */
    public String getCompanyAddressCity() {
        return this.companyAddressCity;
    }
 
    /** 
     * 获取公司注册地址（区）
     *
     * @return Company_address_area 公司注册地址（区）
     */
    public String getCompanyAddressArea() {
        return this.companyAddressArea;
    }
 
    /** 
     * 获取公司注册地址（街道）
     *
     * @return Company_address 公司注册地址（街道）
     */
    public String getCompanyAddress() {
        return this.companyAddress;
    }
 
    /** 
     * 获取营业执照(副本)URL
     *
     * @return Business_license_copy 营业执照(副本)URL
     */
    public String getBusinessLicenseCopy() {
        return this.businessLicenseCopy;
    }
 
    /** 
     * 获取法人姓名
     *
     * @return Corporation_name 法人姓名
     */
    public String getCorporationName() {
        return this.corporationName;
    }
 
    /** 
     * 获取法人电话
     *
     * @return Corporation_phone 法人电话
     */
    public String getCorporationPhone() {
        return this.corporationPhone;
    }
 
    /** 
     * 获取法人身份证(正面)URL
     *
     * @return Legal_person_idcard_front 法人身份证(正面)URL
     */
    public String getLegalPersonIdcardFront() {
        return this.legalPersonIdcardFront;
    }
 
    /** 
     * 获取法人身份证(反面)URL
     *
     * @return Legal_person_idcard_back 法人身份证(反面)URL
     */
    public String getLegalPersonIdcardBack() {
        return this.legalPersonIdcardBack;
    }
 
    /** 
     * 获取联系人身份证(正面)URL
     *
     * @return Contact_idcard_front 联系人身份证(正面)URL
     */
    public String getContactIdcardFront() {
        return this.contactIdcardFront;
    }
 
    /** 
     * 获取联系人身份证(反面)URL
     *
     * @return Contact_idcard_back 联系人身份证(反面)URL
     */
    public String getContactIdcardBack() {
        return this.contactIdcardBack;
    }
 
    /** 
     * 获取联系人姓名
     *
     * @return Contact_name 联系人姓名
     */
    public String getContactName() {
        return this.contactName;
    }
 
    /** 
     * 获取联系人电话
     *
     * @return Contact_telephone 联系人电话
     */
    public String getContactTelephone() {
        return this.contactTelephone;
    }
 
    /** 
     * 获取审核状态
     *
     * @return Examine_state 审核状态
     */
    public String getExamineState() {
        return this.examineState;
    }
 
    /** 
     * 获取账户余额
     *
     * @return Balance 账户余额
     */
    public String getBalance() {
        return this.balance;
    }
 
    /** 
     * 设置公司名称
     *
     * @param Company_name 公司名称
     */
    public void setCompanyName(String companyname) {
        this.companyName = companyname;
    }
 
    /** 
     * 设置统一社会信息用代码
     *
     * @param Company_code 统一社会信息用代码
     */
    public void setCompanyCode(String companycode) {
        this.companyCode = companycode;
    }
 
    /** 
     * 设置公司注册地址（国家）
     *
     * @param Company_address_country 公司注册地址（国家）
     */
    public void setCompanyAddressCountry(String companyaddresscountry) {
        this.companyAddressCountry = companyaddresscountry;
    }
 
    /** 
     * 设置公司注册地址（省）
     *
     * @param Company_address_province 公司注册地址（省）
     */
    public void setCompanyAddressProvince(String companyaddressprovince) {
        this.companyAddressProvince = companyaddressprovince;
    }
 
    /** 
     * 设置公司注册地址（市）
     *
     * @param Company_address_city 公司注册地址（市）
     */
    public void setCompanyAddressCity(String companyaddresscity) {
        this.companyAddressCity = companyaddresscity;
    }
 
    /** 
     * 设置公司注册地址（区）
     *
     * @param Company_address_area 公司注册地址（区）
     */
    public void setCompanyAddressArea(String companyaddressarea) {
        this.companyAddressArea = companyaddressarea;
    }
 
    /** 
     * 设置公司注册地址（街道）
     *
     * @param Company_address 公司注册地址（街道）
     */
    public void setCompanyAddress(String companyaddress) {
        this.companyAddress = companyaddress;
    }
 
    /** 
     * 设置营业执照(副本)URL
     *
     * @param Business_license_copy 营业执照(副本)URL
     */
    public void setBusinessLicenseCopy(String businesslicensecopy) {
        this.businessLicenseCopy = businesslicensecopy;
    }
 
    /** 
     * 设置法人姓名
     *
     * @param Corporation_name 法人姓名
     */
    public void setCorporationName(String corporationname) {
        this.corporationName = corporationname;
    }
 
    /** 
     * 设置法人电话
     *
     * @param Corporation_phone 法人电话
     */
    public void setCorporationPhone(String corporationphone) {
        this.corporationPhone = corporationphone;
    }
 
    /** 
     * 设置法人身份证(正面)URL
     *
     * @param Legal_person_idcard_front 法人身份证(正面)URL
     */
    public void setLegalPersonIdcardFront(String legalpersonidcardfront) {
        this.legalPersonIdcardFront = legalpersonidcardfront;
    }
 
    /** 
     * 设置法人身份证(反面)URL
     *
     * @param Legal_person_idcard_back 法人身份证(反面)URL
     */
    public void setLegalPersonIdcardBack(String legalpersonidcardback) {
        this.legalPersonIdcardBack = legalpersonidcardback;
    }
 
    /** 
     * 设置联系人身份证(正面)URL
     *
     * @param Contact_idcard_front 联系人身份证(正面)URL
     */
    public void setContactIdcardFront(String contactidcardfront) {
        this.contactIdcardFront = contactidcardfront;
    }
 
    /** 
     * 设置联系人身份证(反面)URL
     *
     * @param Contact_idcard_back 联系人身份证(反面)URL
     */
    public void setContactIdcardBack(String contactidcardback) {
        this.contactIdcardBack = contactidcardback;
    }
 
    /** 
     * 设置联系人姓名
     *
     * @param Contact_name 联系人姓名
     */
    public void setContactName(String contactname) {
        this.contactName = contactname;
    }
 
    /** 
     * 设置联系人电话
     *
     * @param Contact_telephone 联系人电话
     */
    public void setContactTelephone(String contacttelephone) {
        this.contactTelephone = contacttelephone;
    }
 
    /** 
     * 设置审核状态
     *
     * @param Examine_state 审核状态
     */
    public void setExamineState(String examinestate) {
        this.examineState = examinestate;
    }
 
    /** 
     * 设置账户余额
     *
     * @param Balance 账户余额
     */
    public void setBalance(String balance) {
        this.balance = balance;
    }
 
}
