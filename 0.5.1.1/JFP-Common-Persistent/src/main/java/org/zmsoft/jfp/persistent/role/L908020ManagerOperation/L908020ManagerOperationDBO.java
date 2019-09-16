package org.zmsoft.jfp.persistent.role.L908020ManagerOperation;
import org.zmsoft.jfp.framework.support.MyDataBaseObjectSupport3;
 
/** 系统管理用户操作日志表*/
public class L908020ManagerOperationDBO extends MyDataBaseObjectSupport3
{
    /** 
     * 用户ID
     */
    private String userId = null;
 
    /** 
     * 用户账户
     */
    private String userAccount = null;
 
    /** 
     * 用户姓名
     */
    private String name = null;
 
    /** 
     * 变更类型
     */
    private String editType = null;
 
    /** 
     * 变更前内容
     */
    private String editBefore = null;
 
    /** 
     * 变更后内容
     */
    private String editAfter = null;
 
    /** 
     * 数值型变量初始化<br>
     * 仅在插入场合默认调用
     * @see #loadDefauft()
     */
    public void prepareNumeric() {
    }
 
    /** 
     * 获取用户ID
     *
     * @return User_id 用户ID
     */
    public String getUserId() {
        return this.userId;
    }
 
    /** 
     * 获取用户账户
     *
     * @return User_account 用户账户
     */
    public String getUserAccount() {
        return this.userAccount;
    }
 
    /** 
     * 获取用户姓名
     *
     * @return Name 用户姓名
     */
    public String getName() {
        return this.name;
    }
 
    /** 
     * 获取变更类型
     *
     * @return Edit_type 变更类型
     */
    public String getEditType() {
        return this.editType;
    }
 
    /** 
     * 获取变更前内容
     *
     * @return Edit_before 变更前内容
     */
    public String getEditBefore() {
        return this.editBefore;
    }
 
    /** 
     * 获取变更后内容
     *
     * @return Edit_after 变更后内容
     */
    public String getEditAfter() {
        return this.editAfter;
    }
 
    /** 
     * 设置用户ID
     *
     * @param User_id 用户ID
     */
    public void setUserId(String userid) {
        this.userId = userid;
    }
 
    /** 
     * 设置用户账户
     *
     * @param User_account 用户账户
     */
    public void setUserAccount(String useraccount) {
        this.userAccount = useraccount;
    }
 
    /** 
     * 设置用户姓名
     *
     * @param Name 用户姓名
     */
    public void setName(String name) {
        this.name = name;
    }
 
    /** 
     * 设置变更类型
     *
     * @param Edit_type 变更类型
     */
    public void setEditType(String edittype) {
        this.editType = edittype;
    }
 
    /** 
     * 设置变更前内容
     *
     * @param Edit_before 变更前内容
     */
    public void setEditBefore(String editbefore) {
        this.editBefore = editbefore;
    }
 
    /** 
     * 设置变更后内容
     *
     * @param Edit_after 变更后内容
     */
    public void setEditAfter(String editafter) {
        this.editAfter = editafter;
    }
 
}
