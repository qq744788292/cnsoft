package org.zmsoft.jfp.framework.search.control;

import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.zmsoft.jfp.framework.search.ESQuerySentenceFactory;
import org.zmsoft.jfp.framework.search.ISPrepareDataType;
import org.zmsoft.jfp.framework.search.bean.QueryBean;
import org.zmsoft.jfp.framework.search.biz.ESDataService;
import org.zmsoft.jfp.framework.search.biz.ESSQLService;
import org.zmsoft.jfp.framework.search.biz.ESTableService;
import org.zmsoft.jfp.framework.search.task.DataIndexUpdateServiceJob;
import org.zmsoft.jfp.framework.utils.BeanFactoryHelper;
import org.zmsoft.jfp.framework.utils.EmptyHelper;

/**
 * 全文检索服务索引管理
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
@Controller
public class DataIndexController implements IFrameworkConstants {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	ISPrepareDataType prepareDataType;

	@RequestMapping(value = "/DIC", method = RequestMethod.GET)
	public ModelAndView creatDataIndex(HttpServletRequest request, HttpServletResponse response, String T // 基于表创建索引名字
			, String I // 基于SQL创建索引名字
			, String C // 创建索引
			, String from // 起点
			, String size // 尺寸
			, String st // 开始日期
			, String et // 终了日期
	) throws Exception {
		logger.debug("=====>>>>>START=====>>>>>");
		prepareDataType = BeanFactoryHelper.getBean("prepareDataType");

		ModelAndView model = new ModelAndView("DWC/index");
		// 基于表进行操作
		if (EmptyHelper.isNotEmpty(T)) {
			ESTableService table = BeanFactoryHelper.getBean("ElasticsearchTableService");
			table.creatIndexByTable(prepareDataType, T, C, from, size);
		}
		// 基于SQL语句操作
		else if (EmptyHelper.isNotEmpty(I)) {
			ESSQLService sql = BeanFactoryHelper.getBean("ElasticsearchSQLService");
			sql.init();
			{
				sql.setStarttime(st);
				sql.setEndtime(et);
				sql.creatIndexBySQL(prepareDataType, I, C, from, size);
			}
		}
		logger.debug("=====>>>>>END=====>>>>>");
		return model;
	}

	/**
	 * 更新索引
	 * 
	 * @param request
	 * @param response
	 * @param k
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/DIU", method = RequestMethod.GET)
	public ModelAndView updateDataIndex(HttpServletRequest request, HttpServletResponse response, String k, String st // 开始日期
			, String sl // 差分日期
	) throws Exception {
		logger.debug("=====>>>>>START=====>>>>>");
		prepareDataType = BeanFactoryHelper.getBean("prepareDataType");
		Calendar nowCalendar = Calendar.getInstance();
		Calendar lastCalendar = Calendar.getInstance();
		DataIndexUpdateServiceJob diusj = BeanFactoryHelper.getBean("dataIndexServiceMonitorJob");
		ESQuerySentenceFactory myQuerySentence = BeanFactoryHelper.getBean("ESQuerySentenceFactory");
		QueryBean qb = myQuerySentence.getUpdateMap().get(k);
		if (qb != null) {
			int splitMinute = diusj.getSplitMinute();
			// 进行差分篡改
			if (EmptyHelper.isNotEmpty(sl)) {
				diusj.setSplitMinute(Integer.parseInt(sl));
			}
			diusj.setPrepareDataType(prepareDataType);
			diusj.doUpdate(nowCalendar, lastCalendar, k, qb, st);

			diusj.setSplitMinute(splitMinute);
		}
		logger.debug("=====>>>>>END=====>>>>>");
		ModelAndView model = new ModelAndView("DWC/index");
		return model;
	}

	/**
	 * 删除一条数据
	 * 
	 * @param request
	 * @param response
	 * @param k
	 * @param d
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/DID", method = RequestMethod.GET)
	public ModelAndView deleteDataIndex(HttpServletRequest request, HttpServletResponse response, String k, String d) throws Exception {
		logger.debug("=====>>>>>START=====>>>>>");
		ESDataService dataService = BeanFactoryHelper.getBean("DataService");
		ESQuerySentenceFactory myQuerySentence = BeanFactoryHelper.getBean("ESQuerySentenceFactory");
		QueryBean qb = myQuerySentence.getUpdateMap().get(k);
		if (EmptyHelper.isNotEmpty(d) && qb != null) {
			ArrayList<String> datas = new ArrayList<String>();
			for (String dd : d.split(COMMA))
				datas.add(dd);

			dataService.deleteDataInIndex(qb.getIndex(), datas);
		}
		logger.debug("=====>>>>>END=====>>>>>");
		ModelAndView model = new ModelAndView("DWC/index");
		return model;
	}
}
