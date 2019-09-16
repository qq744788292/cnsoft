package org.zmsoft.jfp.persistent.system.S901040Classify;
import org.zmsoft.jfp.framework.support.MyDataBaseObjectSupport3;
 
/** 分类信息*/
public class S901040ClassifyDBO extends MyDataBaseObjectSupport3
{
    /** 
     * 分类编号
     */
    private Long classifyNum = null;
 
    /** 
     * 分类名称
     */
    private String classifyName = null;
 
    /** 
     * 分类图标URL
     */
    private String classifyPicUrl = null;
 
    /** 
     * 数值型变量初始化<br>
     * 仅在插入场合默认调用
     * @see #loadDefauft()
     */
    public void prepareNumeric() {
       //初始化分类编号
       if(this.classifyNum == null)
            this.classifyNum = 0L;
    }
 
    /** 
     * 获取分类编号
     *
     * @return Classify_num 分类编号
     */
    public Long getClassifyNum() {
        return this.classifyNum;
    }
 
    /** 
     * 获取分类名称
     *
     * @return Classify_name 分类名称
     */
    public String getClassifyName() {
        return this.classifyName;
    }
 
    /** 
     * 获取分类图标URL
     *
     * @return Classify_pic_url 分类图标URL
     */
    public String getClassifyPicUrl() {
        return this.classifyPicUrl;
    }
 
    /** 
     * 设置分类编号
     *
     * @param Classify_num 分类编号
     */
    public void setClassifyNum(Long classifynum) {
        this.classifyNum = classifynum;
    }
 
    /** 
     * 设置分类名称
     *
     * @param Classify_name 分类名称
     */
    public void setClassifyName(String classifyname) {
        this.classifyName = classifyname;
    }
 
    /** 
     * 设置分类图标URL
     *
     * @param Classify_pic_url 分类图标URL
     */
    public void setClassifyPicUrl(String classifypicurl) {
        this.classifyPicUrl = classifypicurl;
    }
 
}
