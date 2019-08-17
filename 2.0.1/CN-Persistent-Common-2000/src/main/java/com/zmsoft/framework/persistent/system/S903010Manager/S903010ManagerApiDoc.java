package com.zmsoft.framework.persistent.system.S903010Manager;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zmsoft.framework.support.MyApiDocSupport;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 系统管理用户表接口
 * @version 0.1.1
 * @since 0.1.1
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/doc/1.0/base/s903010manager", method = { RequestMethod.GET})
public class S903010ManagerApiDoc extends MyApiDocSupport { 

    //API文档描述
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public JSONObject loadApiDocValue() throws Exception {
        JSONObject all = new JSONObject();
        //输出基本信息
        all.put("name", "系统管理用户表");
        all.put("baseurl", "/api/1.0/base/s903010manager");
        //输出API列表
        {
            JSONArray list = new JSONArray();
            list.add("/list");
            list.add("/info");
            list.add("/append");
            list.add("/modify");
            list.add("/discard");
            all.put("apilist", list);
        }
        //输出字段信息
        {
            JSONArray list = new JSONArray();
            JSONObject item;
            {
                item = new JSONObject();
                item.put("enname", "userPhone");
                item.put("cnname", "用户手机号码");
                item.put("datatype", "String");
                item.put("meno", "");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "userAccount");
                item.put("cnname", "用户账户");
                item.put("datatype", "String");
                item.put("meno", "");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "userPassword");
                item.put("cnname", "登录密码");
                item.put("datatype", "String");
                item.put("meno", "加密存储（MD5大写值）");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "companyId");
                item.put("cnname", "企业ID");
                item.put("datatype", "String");
                item.put("meno", "");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "companyName");
                item.put("cnname", "企业名称");
                item.put("datatype", "String");
                item.put("meno", "");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "departmentId");
                item.put("cnname", "所属部门ID");
                item.put("datatype", "String");
                item.put("meno", "");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "departmentName");
                item.put("cnname", "所属部门名称");
                item.put("datatype", "String");
                item.put("meno", "");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "userName");
                item.put("cnname", "用户姓名");
                item.put("datatype", "String");
                item.put("meno", "");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "userSex");
                item.put("cnname", "用户性别");
                item.put("datatype", "String");
                item.put("meno", "0不明1男2女");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "userMail");
                item.put("cnname", "用户邮箱");
                item.put("datatype", "String");
                item.put("meno", "");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "picUrl");
                item.put("cnname", "用户头像");
                item.put("datatype", "String");
                item.put("meno", "");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "allowLogin");
                item.put("cnname", "账户状态");
                item.put("datatype", "String");
                item.put("meno", "0正常1失效");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "onlineType");
                item.put("cnname", "在线状态");
                item.put("datatype", "String");
                item.put("meno", "1离线2在线");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "onlineLastIp");
                item.put("cnname", "最后登录IP");
                item.put("datatype", "String");
                item.put("meno", "");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "onlineLastTime");
                item.put("cnname", "最后登录时间");
                item.put("datatype", "String");
                item.put("meno", "");
                
                list.add(item);
            }
            all.put("itemlist", list);
        }
        
        return all;
    }
    
}
