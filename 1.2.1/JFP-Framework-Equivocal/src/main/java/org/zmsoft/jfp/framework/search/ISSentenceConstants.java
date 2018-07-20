package org.zmsoft.jfp.framework.search;

import org.zmsoft.jfp.framework.constants.IFrameworkConstants;

/**
 *
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public interface ISSentenceConstants extends IFrameworkConstants{
	public final static String ES_TYPE = "data";
	
	/**
	 * 最后更新时间
	 */
	public final static String SENTENCE_UTD = "SENTENCE:UTD:";
	/**
	 * 更新语句
	 */
	public final static String SENTENCE_UPD = "SENTENCE:UPD:";
	/**
	 * 创建语句
	 */
	public final static String SENTENCE_CRT = "SENTENCE:CRT:";
	/**
	 * 查询语句
	 */
	public final static String SENTENCE_SCH = "SENTENCE:SCH:";
	/**
	 * 最后同步日期
	 */
	public final static String SENTENCE_SYN = "SENTENCE:SYN:";
	
	/**
	 * 工商URL地址
	 */
	public static final String INDEX_URL = "INDEX:CAPURL:";
	
	/**
	 * 索引数据删除
	 */
	public static final String INDEX_DEL = "INDEX:DEL:";
}
