package org.zmsoft.jfp.cache;

/**
 * 系统常量
 */
public interface IMaterialConstants {
	public static final String CLASSIFY_SYS_TYPE = "1000";// 1000影视2000游戏
	public static final int PAGESIZE_TEN = 10; // 每页数据数量
	
	/**
	 * 分类列表KEY
	 */
	public static final String CACHE_FILMDOWN_CLASSIFY = "CACHE:FILMDOWN:CLASSIFY:";
	/**
	 * 分类标签列表KEY
	 */
	public static final String CACHE_FILMDOWN_CLASSIFY_TAG = "CACHE:FILMDOWN:CLASSIFY:TAG:";
	
	/**
	 * 分类与素材列表KEY
	 */
	public static final String CACHE_FILMDOWN_CLASSIFY_MATERIAL = "CACHE:FILMDOWN:CLASSIFY:MATERIAL:";
	/**
	 * 标签与素材列表KEY
	 */
	public static final String CACHE_FILMDOWN_TAG_MATERIAL = "CACHE:FILMDOWN:TAG:MATERIAL:";
	/**
	 * 排行列表KEY
	 */
	public static final String CACHE_FILMDOWN_TOP_MATERIAL = "CACHE:FILMDOWN:TOP:MATERIAL:";
	/**
	 * 热门列表KEY
	 */
	public static final String CACHE_FILMDOWN_HOT_MATERIAL = "CACHE:FILMDOWN:HOT:MATERIAL:";
	/**
	 *最新列表KEY
	 */
	public static final String CACHE_FILMDOWN_NEW_MATERIAL = "CACHE:FILMDOWN:NEW:MATERIAL:";
	/**
	 *搜索列表KEY
	 */
	public static final String CACHE_FILMDOWN_SEARCH_MATERIAL = "CACHE:FILMDOWN:SEARCH:MATERIAL:";
	/**
	 *随机列表KEY
	 */
	public static final String CACHE_FILMDOWN_RANDOM_MATERIAL = "CACHE:FILMDOWN:RANDOM:MATERIAL:";


	/**
	 * 素材详情KEY
	 */
	public static final String CACHE_FILMDOWN_MATERIAL_INFO = "CACHE:FILMDOWN:MATERIAL:INFO:";


}
