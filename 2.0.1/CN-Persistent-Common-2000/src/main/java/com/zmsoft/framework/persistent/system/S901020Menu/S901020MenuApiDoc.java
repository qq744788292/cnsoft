package com.zmsoft.framework.persistent.system.S901020Menu;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zmsoft.framework.support.MyApiDocSupport;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 功能菜单接口
 * @version 0.1.1
 * @since 0.1.1
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/doc/1.0/base/s901020menu", method = { RequestMethod.GET})
public class S901020MenuApiDoc extends MyApiDocSupport { 

    //API文档描述
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public JSONObject loadApiDocValue() throws Exception {
        JSONObject all = new JSONObject();
        //输出基本信息
        all.put("name", "功能菜单");
        all.put("baseurl", "/api/1.0/base/s901020menu");
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
                item.put("enname", "fatherMenuId");
                item.put("cnname", "父菜单流水ID");
                item.put("datatype", "String");
                item.put("meno", "");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "menuName");
                item.put("cnname", "菜单名称");
                item.put("datatype", "String");
                item.put("meno", "");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "menuVer");
                item.put("cnname", "菜单版本号");
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
                item.put("enname", "menuLevel");
                item.put("cnname", "菜单等级");
                item.put("datatype", "String");
                item.put("meno", "123");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "menuType");
                item.put("cnname", "菜单类别");
                item.put("datatype", "String");
                item.put("meno", "1000本地2000远程3000内部");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "nextSub");
                item.put("cnname", "下级菜单数目");
                item.put("datatype", "String");
                item.put("meno", "");
                
                list.add(item);
            }
            all.put("itemlist", list);
        }
        
        return all;
    }
    
}
