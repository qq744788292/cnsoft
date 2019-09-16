package org.isotope.jfp.framework.search.control;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.search.IPrepareDataType;
import org.isotope.jfp.framework.search.biz.SQLService;
import org.isotope.jfp.framework.search.biz.TableService;
import org.isotope.jfp.framework.security.SystemAdminInterceptor;
import org.isotope.jfp.framework.utils.BeanFactoryHelper;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全文检索服务索引管理
 * 
 * @author ISHome
 * @version 0.0.1
 * @since 0.0.1
 */
@Controller
public class DataIndexController implements ISFrameworkConstants {
	IPrepareDataType prepareDataType;

	@Resource
	SystemAdminInterceptor systemAdminInterceptor;

	@RequestMapping(value = "/DIC", method = RequestMethod.GET)
	public ModelAndView creatDataIndex(HttpServletRequest request, HttpServletResponse response, String T // 基于表创建索引名字
	, String I // 基于SQL创建索引名字
	, String C // 创建索引
	, String from // 起点
	, String size // 尺寸
	, String st // 开始日期
	, String et // 终了日期
	) throws Exception {
		prepareDataType = BeanFactoryHelper.getBean("prepareDataType");
		
		ModelAndView model = new ModelAndView("DWC/index");
		// 基于表进行操作
		if (EmptyHelper.isNotEmpty(T)) {
			TableService table = BeanFactoryHelper.getBean("ElasticsearchTableService");
			table.creatIndexByTable(prepareDataType, T, C, from, size);
		}
		// 基于SQL语句操作
		else if (EmptyHelper.isNotEmpty(I)) {
			SQLService sql = BeanFactoryHelper.getBean("ElasticsearchSQLService");
			if (systemAdminInterceptor.doCheckAdmin(request, response)) {
				sql.setStarttime(st);
				sql.setEndtime(et);
				sql.creatIndexBySQL(prepareDataType, I, C, from, size);
			}
		}
		return model;
	}

}
