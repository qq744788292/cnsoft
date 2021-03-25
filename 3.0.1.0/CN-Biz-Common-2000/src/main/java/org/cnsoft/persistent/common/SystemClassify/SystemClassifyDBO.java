package org.cnsoft.persistent.common.SystemClassify;
import org.cnsoft.framework.db.support.ext.MyDataBaseObjectSupport2;
 
/** 分类信息*/
public class SystemClassifyDBO extends MyDataBaseObjectSupport2
{
    /** 
     * 分类ID
     */
    private String classifyId = null;
 
    /** 
     * 分类名称
     */
    private String classifyName = null;
 
    /** 
     * 分类图标URL
     */
    private String classifyPicUrl = null;
  
    /** 
     * 获取分类ID
     *
     * @return Classify_id 分类ID
     */
    public String getClassifyId() {
        return this.classifyId;
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
    public void setClassifyId(String classifyid) {
        this.classifyId = classifyid;
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
