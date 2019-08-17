package com.zmsoft.framework.persistent.system.S901060Parameter;
import org.zmsoft.framework.beans.db.MyDataBaseObjectSupport3;
 
/** 分类参数定义*/
public class S901060ParameterDBO extends MyDataBaseObjectSupport3
{
    /** 
     * 业务分类ID
     */
    private String configTypeId = null;
 
    /** 
     * 键定义
     */
    private String configKey = null;
 
    /** 
     * 值内容
     */
    private String configValue = null;
 
    /** 
     * 数值型变量初始化<br>
     * 仅在插入场合默认调用
     * @see #loadDefauft()
     */
    public void prepareNumeric() {
    }
 
    /** 
     * 获取业务分类ID
     *
     * @return Config_type_id 业务分类ID
     */
    public String getConfigTypeId() {
        return this.configTypeId;
    }
 
    /** 
     * 获取键定义
     *
     * @return Config_key 键定义
     */
    public String getConfigKey() {
        return this.configKey;
    }
 
    /** 
     * 获取值内容
     *
     * @return Config_value 值内容
     */
    public String getConfigValue() {
        return this.configValue;
    }
 
    /** 
     * 设置业务分类ID
     *
     * @param Config_type_id 业务分类ID
     */
    public void setConfigTypeId(String configtypeid) {
        this.configTypeId = configtypeid;
    }
 
    /** 
     * 设置键定义
     *
     * @param Config_key 键定义
     */
    public void setConfigKey(String configkey) {
        this.configKey = configkey;
    }
 
    /** 
     * 设置值内容
     *
     * @param Config_value 值内容
     */
    public void setConfigValue(String configvalue) {
        this.configValue = configvalue;
    }
 
}
