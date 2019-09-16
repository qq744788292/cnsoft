package org.isotope.jfp.framework.search.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.search.IPrepareDataType;
import org.isotope.jfp.framework.search.ISSentenceConstants;
import org.isotope.jfp.framework.search.QuerySentence;
import org.isotope.jfp.framework.search.bean.QueryBean;
import org.isotope.jfp.framework.search.biz.SQLService;
import org.isotope.jfp.framework.support.MyTaskSupport;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 索引同步
 * 
 * @author 001745
 *
 */
public class DataIndexUpdateServiceJob extends MyTaskSupport {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	// 缓存队列
	QuerySentence myQuerySentence;

	public QuerySentence getMyQuerySentence() {
		return myQuerySentence;
	}

	public void setMyQuerySentence(QuerySentence myQuerySentence) {
		this.myQuerySentence = myQuerySentence;
	}

	SQLService sqlService;

	public SQLService getSqlService() {
		return sqlService;
	}

	public void setSqlService(SQLService sqlService) {
		this.sqlService = sqlService;
	}

	IPrepareDataType prepareDataType;

	public IPrepareDataType getPrepareDataType() {
		return prepareDataType;
	}

	public void setPrepareDataType(IPrepareDataType prepareDataType) {
		this.prepareDataType = prepareDataType;
	}

	/**
	 * Redis缓存空间索引
	 */
	int index = 0;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * 分页大小
	 */
	int size = 500;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	int splitMinute = 120;

	public int getSplitMinute() {
		return splitMinute;
	}

	public void setSplitMinute(int splitMinute) {
		this.splitMinute = splitMinute;
	}

	public boolean doProcessRepeat() throws Exception {
		logger.info("全文检索索引同步更新业务  >>>>>===== 开始");
		// 数据整理,基于Redis进行缓存同步
		{
			Calendar nowCalendar = Calendar.getInstance();
			Calendar lastCalendar = Calendar.getInstance();
			Map<String, QueryBean> updateMap = myQuerySentence.getUpdateMap();
			Iterator<Entry<String, QueryBean>> iter = updateMap.entrySet().iterator();
			while (iter.hasNext()) {
				Entry<String, QueryBean> entry = iter.next();
				doUpdate(nowCalendar, lastCalendar, entry.getKey(), entry.getValue());
			}
		}
		myCacheService.init();
		logger.info("全文检索索引同步更新业务  <<<<<===== 结束");
		return true;
	}

	@Autowired
	private ICacheService myCacheService;

	public void doUpdate(Calendar nowCalendar, Calendar lastCalendar, String key, QueryBean qb) throws Exception {
		doUpdate(nowCalendar, lastCalendar, key, qb, null);
	}

	public void doUpdate(Calendar nowCalendar, Calendar lastCalendar, String key, QueryBean qb, String startTime) throws Exception {
		// 获得最后一次更新时间
		myCacheService.selectDB(index);
		String lastTime = startTime;
		if (EmptyHelper.isEmpty(startTime)) {
			lastTime = (String) myCacheService.getObject(ISSentenceConstants.SENTENCE_UTD + qb.getId(), false);
			if (EmptyHelper.isEmpty(lastTime)) {
				logger.info("全文检索索引同步更新业务  xxxxx===== 取消....." + key);
				return;
			}
		}
		// splitMinute = 2;
		// 设定最后更新时间
		lastCalendar.setTimeInMillis(Long.parseLong(lastTime));
		lastCalendar.add(Calendar.MINUTE, splitMinute);
		// 判断时间超时
		if ((nowCalendar.getTimeInMillis() - lastCalendar.getTimeInMillis()) < 1000 * 60 * splitMinute) {
			logger.info("全文检索索引同步更新业务  xxxxx===== 取消....." + key);
			return;
		}
		// 参数初始化
		sqlService.init();
		logger.info("全文检索索引同步更新开始  =====>>>>>" + key);
		// 开始更新索引
		boolean upLast = true;
		while (upLast) {
			// 判断本次更新开始时间
			{
				lastCalendar.setTimeInMillis(Long.parseLong(lastTime));
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				sqlService.setStarttime(format.format(lastCalendar.getTime()));
			}
			// 计算本次更新结束时间
			lastCalendar.add(Calendar.MINUTE, splitMinute);

			// 超过当前时间
			if ((nowCalendar.getTimeInMillis() - lastCalendar.getTimeInMillis()) < 1000 * 60 * splitMinute) {
				upLast = false;
				logger.info("全文检索索引同步更新业务  xxxxx===== 取消....." + key);
				break;
			}

			lastTime = "" + lastCalendar.getTimeInMillis();
			{
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				sqlService.setEndtime(format.format(lastCalendar.getTime()));
			}
			logger.info("    索引更新  =====>>>>>=====>>>>> " + qb.getId() + "," + qb.getIndex());
			sqlService.updateIndexBySQL(prepareDataType, qb, EMPTY, "" + size);
		}
		// 设定最后一次更新时间
		myCacheService.selectDB(index);
		myCacheService.putObject(ISSentenceConstants.SENTENCE_UTD + qb.getId(), lastTime, 0, false);
		logger.info("全文检索索引同步更新结束  =====>>>>>" + key);
	}
}
