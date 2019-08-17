package com.zmsoft.framework.persistent.system.S903020UserRole;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zmsoft.framework.support.MyApiDocSupport;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 系统用户关联角色信息接口
 * @version 0.1.1
 * @since 0.1.1
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/doc/1.0/base/s903020userrole", method = { RequestMethod.GET})
public class S903020UserRoleApiDoc extends MyApiDocSupport { 

    //API文档描述
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public JSONObject loadApiDocValue() throws Exception {
        JSONObject all = new JSONObject();
        //输出基本信息
        all.put("name", "系统用户关联角色信息");
        all.put("baseurl", "/api/1.0/base/s903020userrole");
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
                item.put("enname", "userId");
                item.put("cnname", "用户ID");
                item.put("datatype", "String");
                item.put("meno", "");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "roleId");
                item.put("cnname", "角色ID");
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
                item.put("enname", "roleName");
                item.put("cnname", "角色名称");
                item.put("datatype", "String");
                item.put("meno", "");
                
                list.add(item);
            }
            all.put("itemlist", list);
        }
        
        return all;
    }
    
}
