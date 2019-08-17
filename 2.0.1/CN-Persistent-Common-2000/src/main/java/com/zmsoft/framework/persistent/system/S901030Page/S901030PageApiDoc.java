package com.zmsoft.framework.persistent.system.S901030Page;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zmsoft.framework.support.MyApiDocSupport;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 业务画面接口
 * @version 0.1.1
 * @since 0.1.1
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/doc/1.0/base/s901030page", method = { RequestMethod.GET})
public class S901030PageApiDoc extends MyApiDocSupport { 

    //API文档描述
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public JSONObject loadApiDocValue() throws Exception {
        JSONObject all = new JSONObject();
        //输出基本信息
        all.put("name", "业务画面");
        all.put("baseurl", "/api/1.0/base/s901030page");
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
                item.put("enname", "fatherPageId");
                item.put("cnname", "父画面流水ID");
                item.put("datatype", "String");
                item.put("meno", "");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "pageName");
                item.put("cnname", "画面名称");
                item.put("datatype", "String");
                item.put("meno", "");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "pageVer");
                item.put("cnname", "画面版本号");
                item.put("datatype", "String");
                item.put("meno", "");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "pageSortNum");
                item.put("cnname", "画面排列顺序");
                item.put("datatype", "Long");
                item.put("meno", "");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "pageUrl");
                item.put("cnname", "画面URL");
                item.put("datatype", "String");
                item.put("meno", "");
                
                list.add(item);
            }
            all.put("itemlist", list);
        }
        
        return all;
    }
    
}
