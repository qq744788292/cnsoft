package com.zmsoft.framework.persistent.system.S901060Parameter;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zmsoft.framework.support.MyApiDocSupport;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 分类参数定义接口
 * @version 0.1.1
 * @since 0.1.1
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/doc/1.0/base/s901060parameter", method = { RequestMethod.GET})
public class S901060ParameterApiDoc extends MyApiDocSupport { 

    //API文档描述
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public JSONObject loadApiDocValue() throws Exception {
        JSONObject all = new JSONObject();
        //输出基本信息
        all.put("name", "分类参数定义");
        all.put("baseurl", "/api/1.0/base/s901060parameter");
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
                item.put("enname", "configTypeId");
                item.put("cnname", "业务分类ID");
                item.put("datatype", "String");
                item.put("meno", "【业务分类】.id");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "configKey");
                item.put("cnname", "键定义");
                item.put("datatype", "String");
                item.put("meno", "");
                
                list.add(item);
            }
            {
                item = new JSONObject();
                item.put("enname", "configValue");
                item.put("cnname", "值内容");
                item.put("datatype", "String");
                item.put("meno", "");
                
                list.add(item);
            }
            all.put("itemlist", list);
        }
        
        return all;
    }
    
}
