﻿package org.jfpc.beans.common.MS0C1;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 系统公告*/
public class MS0C1DBO extends MyDataBaseObjectSupport
{
    /** 
     * 信息种类
     */
    private String k01_xxzl = "";
 
    /** 
     * 外部共享
     */
    private String k02_wbgx = "";
 
    /** 
     * 标题
     */
    private String f01_bt = "";
 
    /** 
     * 开始时间
     */
    private String f02_kssh = "";
 
    /** 
     * 终了时间
     */
    private String f03_zlsj = "";
 
    /** 
     * 排列顺序
     */
    private String f04_plsx = "";
 
    /** 
     * 紧急度
     */
    private String f05_jjd = "";
 
    /** 
     * 重要度
     */
    private String f06_zyd = "";
 
    /** 
     * 获取信息种类
     *
     * @return K01_XXZL 信息种类
     */
    public String getK01_xxzl() {
        return this.k01_xxzl;
    }
 
    /** 
     * 获取外部共享
     *
     * @return K02_WBGX 外部共享
     */
    public String getK02_wbgx() {
        return this.k02_wbgx;
    }
 
    /** 
     * 获取标题
     *
     * @return F01_BT 标题
     */
    public String getF01_bt() {
        return this.f01_bt;
    }
 
    /** 
     * 获取开始时间
     *
     * @return F02_KSSH 开始时间
     */
    public String getF02_kssh() {
        return this.f02_kssh;
    }
 
    /** 
     * 获取终了时间
     *
     * @return F03_ZLSJ 终了时间
     */
    public String getF03_zlsj() {
        return this.f03_zlsj;
    }
 
    /** 
     * 获取排列顺序
     *
     * @return F04_PLSX 排列顺序
     */
    public String getF04_plsx() {
        return this.f04_plsx;
    }
 
    /** 
     * 获取紧急度
     *
     * @return F05_JJD 紧急度
     */
    public String getF05_jjd() {
        return this.f05_jjd;
    }
 
    /** 
     * 获取重要度
     *
     * @return F06_ZYD 重要度
     */
    public String getF06_zyd() {
        return this.f06_zyd;
    }
 
    /** 
     * 设置信息种类
     *
     * @param K01_XXZL 信息种类
     */
    public void setK01_xxzl(String K01_XXZL) {
        this.k01_xxzl = K01_XXZL;
    }
 
    /** 
     * 设置外部共享
     *
     * @param K02_WBGX 外部共享
     */
    public void setK02_wbgx(String K02_WBGX) {
        this.k02_wbgx = K02_WBGX;
    }
 
    /** 
     * 设置标题
     *
     * @param F01_BT 标题
     */
    public void setF01_bt(String F01_BT) {
        this.f01_bt = F01_BT;
    }
 
    /** 
     * 设置开始时间
     *
     * @param F02_KSSH 开始时间
     */
    public void setF02_kssh(String F02_KSSH) {
        this.f02_kssh = F02_KSSH;
    }
 
    /** 
     * 设置终了时间
     *
     * @param F03_ZLSJ 终了时间
     */
    public void setF03_zlsj(String F03_ZLSJ) {
        this.f03_zlsj = F03_ZLSJ;
    }
 
    /** 
     * 设置排列顺序
     *
     * @param F04_PLSX 排列顺序
     */
    public void setF04_plsx(String F04_PLSX) {
        this.f04_plsx = F04_PLSX;
    }
 
    /** 
     * 设置紧急度
     *
     * @param F05_JJD 紧急度
     */
    public void setF05_jjd(String F05_JJD) {
        this.f05_jjd = F05_JJD;
    }
 
    /** 
     * 设置重要度
     *
     * @param F06_ZYD 重要度
     */
    public void setF06_zyd(String F06_ZYD) {
        this.f06_zyd = F06_ZYD;
    }
 
}
