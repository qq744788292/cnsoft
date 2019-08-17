package com.zmsoft.framework.persistent.system.S902040GroupPermission;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zmsoft.framework.support.MyApiDocSupport;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 用户组菜单权限表接口
 * @version 0.1.1
 * @since 0.1.1
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/doc/1.0/base/s902040grouppermission", method = { RequestMethod.GET})
public class S902040GroupPermissionApiDoc extends MyApiDocSupport { 

    //API文档描述
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public JSONObject loadApiDocValue() throws Exception {
        JSONObject all = new JSONObject();
        //输出基本信息
        all.put("name", "用户组菜单权限表");
        all.put("baseurl", "/api/1.0/base/s902040grouppermission");
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
                item.put("enname", "groupId");
                item.put("cnname", "用户组ID");
                item.put("datatype", "String");
                item.put("meno", "");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "groupName");
                item.put("cnname", "用户组名称");
                item.put("datatype", "String");
                item.put("meno", "");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "pageId");
                item.put("cnname", "所属画面ID");
                item.put("datatype", "String");
                item.put("meno", "");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "pageName");
                item.put("cnname", "所属画面名称");
                item.put("datatype", "String");
                item.put("meno", "");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "menuId");
                item.put("cnname", "菜单ID");
                item.put("datatype", "String");
                item.put("meno", "");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "menuName");
                item.put("cnname", "菜单名称");
                item.put("datatype", "String");
                item.put("meno", "1增2删3改4查5导出");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "menuLevel");
                item.put("cnname", "菜单等级");
                item.put("datatype", "String");
                item.put("meno", "");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "menuSortNum");
                item.put("cnname", "菜单排列顺序");
                item.put("datatype", "Long");
                item.put("meno", "");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "menuPicUrl");
                item.put("cnname", "菜单图标");
                item.put("datatype", "String");
                item.put("meno", "");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "menuUrl");
                item.put("cnname", "菜单URL");
                item.put("datatype", "String");
                item.put("meno", "");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "fatherMenuId");
                item.put("cnname", "父菜单流水ID");
                item.put("datatype", "String");
                item.put("meno", "");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "menuIdArray");
                item.put("cnname", "页面按钮权限组");
                item.put("datatype", "String");
                item.put("meno", "JSON List");
                
                list.add(item);
            }
            all.put("itemlist", list);
        }
        
        return all;
    }
    
}
