package com.zmsoft.framework.persistent.system.S901010Config;
import org.zmsoft.framework.beans.db.MyDataBaseObjectSupport3;
 
/** 系统配置*/
public class S901010ConfigDBO extends MyDataBaseObjectSupport3
{
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
