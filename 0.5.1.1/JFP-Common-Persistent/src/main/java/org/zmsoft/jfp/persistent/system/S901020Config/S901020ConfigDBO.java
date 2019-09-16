package org.zmsoft.jfp.persistent.system.S901020Config;
import org.zmsoft.jfp.framework.support.MyDataBaseObjectSupport3;
 
/** 系统配置*/
public class S901020ConfigDBO extends MyDataBaseObjectSupport3
{
    /** 
     * 分类
     */
    private String configType = null;
 
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
     * 获取分类
     *
     * @return Config_type 分类
     */
    public String getConfigType() {
        return this.configType;
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
     * 设置分类
     *
     * @param Config_type 分类
     */
    public void setConfigType(String configtype) {
        this.configType = configtype;
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
