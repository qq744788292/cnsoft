package com.zmsoft.framework.persistent.system.S901040PageFunc;
import org.zmsoft.framework.beans.db.MyDataBaseObjectSupport3;
 
/** 页面按钮*/
public class S901040PageFuncDBO extends MyDataBaseObjectSupport3
{
    /** 
     * 所属画面ID
     */
    private String pageId = null;
 
    /** 
     * 所属画面名称
     */
    private String pageName = null;
 
    /** 
     * 画面版本号
     */
    private String pageVer = null;
 
    /** 
     * 按钮名称
     */
    private String funcName = null;
 
    /** 
     * 按钮排列顺序
     */
    private Long funcSortNum = null;
 
    /** 
     * 按钮URL
     */
    private String funcUrl = null;
 
    /** 
     * 数值型变量初始化<br>
     * 仅在插入场合默认调用
     * @see #loadDefauft()
     */
    public void prepareNumeric() {
       //初始化按钮排列顺序
       if(this.funcSortNum == null)
            this.funcSortNum = 0L;
    }
 
    /** 
     * 获取所属画面ID
     *
     * @return Page_id 所属画面ID
     */
    public String getPageId() {
        return this.pageId;
    }
 
    /** 
     * 获取所属画面名称
     *
     * @return Page_name 所属画面名称
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
     * 获取按钮名称
     *
     * @return Func_name 按钮名称
     */
    public String getFuncName() {
        return this.funcName;
    }
 
    /** 
     * 获取按钮排列顺序
     *
     * @return Func_sort_num 按钮排列顺序
     */
    public Long getFuncSortNum() {
        return this.funcSortNum;
    }
 
    /** 
     * 获取按钮URL
     *
     * @return Func_url 按钮URL
     */
    public String getFuncUrl() {
        return this.funcUrl;
    }
 
    /** 
     * 设置所属画面ID
     *
     * @param Page_id 所属画面ID
     */
    public void setPageId(String pageid) {
        this.pageId = pageid;
    }
 
    /** 
     * 设置所属画面名称
     *
     * @param Page_name 所属画面名称
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
     * 设置按钮名称
     *
     * @param Func_name 按钮名称
     */
    public void setFuncName(String funcname) {
        this.funcName = funcname;
    }
 
    /** 
     * 设置按钮排列顺序
     *
     * @param Func_sort_num 按钮排列顺序
     */
    public void setFuncSortNum(Long funcsortnum) {
        this.funcSortNum = funcsortnum;
    }
 
    /** 
     * 设置按钮URL
     *
     * @param Func_url 按钮URL
     */
    public void setFuncUrl(String funcurl) {
        this.funcUrl = funcurl;
    }
 
}
