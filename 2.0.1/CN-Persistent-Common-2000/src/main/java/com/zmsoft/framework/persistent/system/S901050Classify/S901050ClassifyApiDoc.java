package com.zmsoft.framework.persistent.system.S901050Classify;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zmsoft.framework.support.MyApiDocSupport;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 业务分类接口
 * @version 0.1.1
 * @since 0.1.1
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/doc/1.0/base/s901050classify", method = { RequestMethod.GET})
public class S901050ClassifyApiDoc extends MyApiDocSupport { 

    //API文档描述
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public JSONObject loadApiDocValue() throws Exception {
        JSONObject all = new JSONObject();
        //输出基本信息
        all.put("name", "业务分类");
        all.put("baseurl", "/api/1.0/base/s901050classify");
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
                item.put("enname", "classifyNum");
                item.put("cnname", "分类编号");
                item.put("datatype", "Long");
                item.put("meno", "1000影视2000游戏");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "classifyName");
                item.put("cnname", "分类名称");
                item.put("datatype", "String");
                item.put("meno", "1000影视2000游戏");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "classifyPicUrl");
                item.put("cnname", "分类图标URL");
                item.put("datatype", "String");
                item.put("meno", "访问地址");
                
                list.add(item);
            }
            all.put("itemlist", list);
        }
        
        return all;
    }
    
}
