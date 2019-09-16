package org.zmsoft.jfp.manager.system.classify;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.zmsoft.jfp.framework.beans.common.RESTResultBean;
import org.zmsoft.jfp.framework.beans.page.PageModel;
import org.zmsoft.jfp.framework.support.MyControllerSupport;
import org.zmsoft.jfp.framework.utils.BeanFactoryHelper;
import org.zmsoft.jfp.framework.utils.EmptyHelper;
import org.zmsoft.jfp.persistent.system.S901040Classify.S901040ClassifyDBO;
import org.zmsoft.jfp.persistent.system.S901040Classify.S901040ClassifyService;

/**
 * 标签管理
 * 
 * @version 0.0.1 2018/05/08
 * @since 0.0.1 2018/05/08
 * @author 周海俊
 */
@Controller
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ClassifyController extends MyControllerSupport {

	@Resource
    S901040ClassifyService S901040ClassifyService_;

	/**
	 * 一览
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/90104010", method = RequestMethod.POST)
	public ModelAndView list90104010(S901040ClassifyDBO param, PageModel<S901040ClassifyDBO> pageModel, RESTResultBean<String> message) throws Exception {
		ModelAndView model = super.getModelAndView("/system/systemClassify/system-Classify-list");
		param.setDelFlag(ZERO);
		pageModel.setResultCountFlag(true);
		pageModel.setFormParamBean(param);
		pageModel.setOrderby("create_time DESC");

		pageModel = S901040ClassifyService_.doSelectPage(pageModel);

		model.addObject("page", pageModel);
		model.addObject("param", param);
		model.addObject("message", message);

		return model;
	}

	/**
	 * 新建-跳转页面
	 * e
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/90104020", method = RequestMethod.POST)
	public ModelAndView add90104020(S901040ClassifyDBO param) throws Exception {
		ModelAndView model = getModelAndView("/system/systemClassify/system-Classify-modify");
		model.addObject("mode", ONE);// 新加
		model.addObject("data", param);
		return model;
	}

	/**
	 * 编辑-跳转页面
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/90104030", method = RequestMethod.POST)
	public ModelAndView edit90104030(S901040ClassifyDBO param) throws Exception {
		ModelAndView model = getModelAndView("/system/systemClassify/system-Classify-modify");
		model.addObject("mode", TWO);// 编辑
		model.addObject("data", S901040ClassifyService_.doRead(param));
		return model;
	}

	/**
	 * 删除
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/90104040", method = RequestMethod.POST)
	public ModelAndView delete90104040(S901040ClassifyDBO param, PageModel<S901040ClassifyDBO> pageModel,RESTResultBean<String> message) throws Exception {
		S901040ClassifyService_.doDelete(param);
        message.setCode(ONE);
        message.setMsg(MESSAGE_DB_DELETE);
        return list90104010(new S901040ClassifyDBO(), pageModel, message);
	}

	/**
	 * 保存
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/90104050", method = RequestMethod.POST)
	public ModelAndView save90104050(S901040ClassifyDBO param, String mode, PageModel<S901040ClassifyDBO> pageModel,RESTResultBean<String> message) throws Exception {
		if (ONE.equals(mode)) {
			S901040ClassifyService_.doInsert(param);
            message.setCode(ONE);
            message.setMsg(MESSAGE_DB_INSERT);
		} else {
			S901040ClassifyService_.doUpdate(param);
            message.setCode(FOUR);
            message.setMsg(MESSAGE_DB_UPDATE);
        }

        return list90104010(new S901040ClassifyDBO(), pageModel, message);
	}


    /**
     * 查询所有记录
     * @param S901040ClassifyDBO_
     * @return
     * @throws Exception
     */
    public static List<S901040ClassifyDBO> doReadAll(S901040ClassifyDBO S901040ClassifyDBO_) throws Exception {
        S901040ClassifyService S901040ClassifyService_= BeanFactoryHelper.getBean("S901040ClassifyService");
        return S901040ClassifyService_.doSelectData(S901040ClassifyDBO_);
    }
    /**
     * ajax验证是否已存在
     * @author 周海俊
     */
    @RequestMapping(value = "/90104090", method = RequestMethod.POST)
    @ResponseBody
    public Boolean check70201090(S901040ClassifyDBO S901040ClassifyDBO_,String mode,String currentPuk) throws Exception {
        List<S901040ClassifyDBO> list=S901040ClassifyService_.doSelectData(S901040ClassifyDBO_);

        if(mode.equals(ONE)) {//如果是添加
            if (EmptyHelper.isEmpty(list)) {
                return true;
            } else {
                return false;
            }
        }else {
            //如果是修改,查询结果则有以下可能性,以name为例
            //1,查询结果为0条,表示name可用,返回true
            //1,查询结果1条,为当前修改数据.则返回true(当修改时不修改name,但还是触发了name唯一性验证时出现这情况)
            //2,查询结果为1条,不是当前修改数据,表示其他数据以存在改name,则返回false
            //3.查询结果大于1条 返回false(基本上不会出现,数据错误情况下会出现)
            if (list.size()>1) {
                return false;
            }else if(list.size()==0) {
                return true;
            }else{
                S901040ClassifyDBO_ = list.get(0);
                if (S901040ClassifyDBO_.getPuk().equals(currentPuk)) {//如果查询结果为当前修改数据
                    return true;
                } else {//如果查询结果不是当前修改数据.表示已存在
                    return false;
                }
            }
        }
    }
}
