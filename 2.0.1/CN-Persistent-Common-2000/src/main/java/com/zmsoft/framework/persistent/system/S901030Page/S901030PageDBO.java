package com.zmsoft.framework.persistent.system.S901030Page;
import org.zmsoft.framework.beans.db.MyDataBaseObjectSupport3;
 
/** 业务画面*/
public class S901030PageDBO extends MyDataBaseObjectSupport3
{
    /** 
     * 父画面流水ID
     */
    private String fatherPageId = null;
 
    /** 
     * 画面名称
     */
    private String pageName = null;
 
    /** 
     * 画面版本号
     */
    private String pageVer = null;
 
    /** 
     * 画面排列顺序
     */
    private Long pageSortNum = null;
 
    /** 
     * 画面URL
     */
    private String pageUrl = null;
 
    /** 
     * 数值型变量初始化<br>
     * 仅在插入场合默认调用
     * @see #loadDefauft()
     */
    public void prepareNumeric() {
       //初始化画面排列顺序
       if(this.pageSortNum == null)
            this.pageSortNum = 0L;
    }
 
    /** 
     * 获取父画面流水ID
     *
     * @return Father_page_id 父画面流水ID
     */
    public String getFatherPageId() {
        return this.fatherPageId;
    }
 
    /** 
     * 获取画面名称
     *
     * @return Page_name 画面名称
     */
    public String getPageName() {
        return this.pageName;
    }
 
    /** 
     * 获取画面版本号
     *
     * @return Page_ver 画面版本号
     */
    public String getPageVer() {
        return this.pageVer;
    }
 
    /** 
     * 获取画面排列顺序
     *
     * @return Page_sort_num 画面排列顺序
     */
    public Long getPageSortNum() {
        return this.pageSortNum;
    }
 
    /** 
     * 获取画面URL
     *
     * @return Page_url 画面URL
     */
    public String getPageUrl() {
        return this.pageUrl;
    }
 
    /** 
     * 设置父画面流水ID
     *
     * @param Father_page_id 父画面流水ID
     */
    public void setFatherPageId(String fatherpageid) {
        this.fatherPageId = fatherpageid;
    }
 
    /** 
     * 设置画面名称
     *
     * @param Page_name 画面名称
     */
    public void setPageName(String pagename) {
        this.pageName = pagename;
    }
 
    /** 
     * 设置画面版本号
     *
     * @param Page_ver 画面版本号
     */
    public void setPageVer(String pagever) {
        this.pageVer = pagever;
    }
 
    /** 
     * 设置画面排列顺序
     *
     * @param Page_sort_num 画面排列顺序
     */
    public void setPageSortNum(Long pagesortnum) {
        this.pageSortNum = pagesortnum;
    }
 
    /** 
     * 设置画面URL
     *
     * @param Page_url 画面URL
     */
    public void setPageUrl(String pageurl) {
        this.pageUrl = pageurl;
    }
 
}
