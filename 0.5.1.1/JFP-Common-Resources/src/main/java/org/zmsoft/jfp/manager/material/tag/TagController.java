package org.zmsoft.jfp.manager.material.tag;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.zmsoft.jfp.framework.beans.common.RESTResultBean;
import org.zmsoft.jfp.framework.beans.page.PageModel;
import org.zmsoft.jfp.framework.support.MyControllerSupport;
import org.zmsoft.jfp.framework.utils.EmptyHelper;
import org.zmsoft.jfp.manager.material.classify.ClassifyController;
import org.zmsoft.jfp.persistent.material.R702010Tag.R702010TagDBO;
import org.zmsoft.jfp.persistent.material.R702010Tag.R702010TagService;
import org.zmsoft.jfp.persistent.material.R702011Classify.R702011ClassifyDBO;

import javax.annotation.Resource;
import java.util.List;

/**
 * 标签管理
 * 
 * @version 0.0.1 2018/05/08
 * @since 0.0.1 2018/05/08
 * @author 周海俊
 */
@Controller
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TagController extends MyControllerSupport {

	@Resource
    R702010TagService R702010TagService_;

	/**
	 * 一览
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/70201010", method = RequestMethod.POST)
	public ModelAndView list70201010(R702010TagDBO param, PageModel<R702010TagDBO> pageModel, RESTResultBean<String> message) throws Exception {
		ModelAndView model = super.getModelAndView("/material/tag/tag-list");
		param.setDelFlag(ZERO);
		pageModel.setResultCountFlag(true);
		pageModel.setFormParamBean(param);
		pageModel.setOrderby("create_time DESC");

		pageModel = R702010TagService_.doSelectPage(pageModel);

		model.addObject("page", pageModel);
		model.addObject("param", param);
		model.addObject("message", message);

		return model;
	}

	/**
	 * 新建-跳转页面
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/70201020", method = RequestMethod.POST)
	public ModelAndView add70201020(R702010TagDBO param) throws Exception {
		ModelAndView model = getModelAndView("/material/tag/tag-modify");
		model.addObject("mode", ONE);// 新加
		model.addObject("data", param);
		model.addObject("classifyList", ClassifyController.doReadAll(new R702011ClassifyDBO()));
		return model;
	}

	/**
	 * 编辑-跳转页面
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/70201030", method = RequestMethod.POST)
	public ModelAndView edit70201030(R702010TagDBO param) throws Exception {
		ModelAndView model = getModelAndView("/material/tag/tag-modify");
		model.addObject("mode", TWO);// 编辑
		model.addObject("data", R702010TagService_.doRead(param));
        model.addObject("classifyList", ClassifyController.doReadAll(new R702011ClassifyDBO()));
        return model;
	}

	/**
	 * 删除
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/70201040", method = RequestMethod.POST)
	public ModelAndView delete70201040(R702010TagDBO param, PageModel<R702010TagDBO> pageModel,RESTResultBean<String> message) throws Exception {
		R702010TagService_.doDelete(param);
        message.setCode(ONE);
        message.setMsg(MESSAGE_DB_DELETE);
        return list70201010(new R702010TagDBO(), pageModel, message);
	}

	/**
	 * 保存
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/70201050", method = RequestMethod.POST)
	public ModelAndView save70201050(R702010TagDBO param, String mode, PageModel<R702010TagDBO> pageModel,RESTResultBean<String> message) throws Exception {
		if (ONE.equals(mode)) {
			R702010TagService_.doInsert(param);
            message.setCode(ONE);
            message.setMsg(MESSAGE_DB_INSERT);
		} else {
			R702010TagService_.doUpdate(param);
            message.setCode(FOUR);
            message.setMsg(MESSAGE_DB_UPDATE);
        }

        return list70201010(new R702010TagDBO(), pageModel, message);
	}

    /**
     * ajax验证是否已存在
     * @author 周海俊
     */
    @RequestMapping(value = "/70201090", method = RequestMethod.POST)
    @ResponseBody
    public Boolean check70201090(R702010TagDBO R702010TagDBO_,String mode,String currentPuk) throws Exception {
        List<R702010TagDBO> list=R702010TagService_.doSelectData(R702010TagDBO_);

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
                R702010TagDBO_ = list.get(0);
                if (R702010TagDBO_.getPuk().equals(currentPuk)) {//如果查询结果为当前修改数据
                    return true;
                } else {//如果查询结果不是当前修改数据.表示已存在
                    return false;
                }
            }
        }
    }
}
