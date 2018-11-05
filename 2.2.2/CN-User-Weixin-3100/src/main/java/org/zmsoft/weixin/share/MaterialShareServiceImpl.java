package org.zmsoft.weixin.share;

import org.springframework.stereotype.Service;
import org.zmsoft.framework.constants.IFrameworkConstants;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.framework.weixin.IWxShareInfo;

import com.alibaba.fastjson.JSONObject;

/**
 * 微信公众号文章分享
 * 
 * @author fcy
 * @see <WxShareController>
 */
@Service("MaterialShareService")
public class MaterialShareServiceImpl implements IWxShareInfo, IFrameworkConstants {
	
	/**
	 * 获得文章分享数据信息
	 * 
	 * @param id
	 *            文章ID
	 * @return 返回参数必须包含<br>
	 *         title 分享标题<br>
	 *         desc 分享描述<br>
	 *         link 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致<br>
	 *         imgUrl 分享图标<br>
	 */
	public JSONObject loadShareInfo(String type,String id) {
		// 根据ID从分类素材关系里面获取需要信息
		// 分享内容判定
		JSONObject material = new JSONObject();
		if (EmptyHelper.isEmpty(id)) {
			return loadDefault();
		}else {
//			MaterialCacheBiz MaterialCacheBiz_ = BeanFactoryHelper.getBean("MaterialCacheBiz");
//			material = MaterialCacheBiz_.getBaseMaterialByPuk(id, true);
//			// 判断素材有效性
//			if (EmptyHelper.isEmpty(material)) {
//				material = MaterialCacheBiz_.getCollectionMaterialByPuk(id, true);
//				if (EmptyHelper.isEmpty(material)) {// 素材不存在
//					return loadDefault();
//				}
//			}
//			if("1".equals(type)){//楼盘详情分享
//				AdmEstateDBO admEstateDBO = new AdmEstateDBO();
//				admEstateDBO.setId(id);
//				AdmEstateDBO admEstate = admProjectDao.doRead(admEstateDBO);
//				if(EmptyHelper.isEmpty(admEstate)){
//					return loadDefault();
//				}
//				// 素材分享
//				//楼盘标题
//				material.put("title",admEstate.getEstateName());
//				// 设定分享描述
//				material.put("desc", admEstate.getBrightDesc());
//				// 设定分享图片/缩略图
//				material.put("imgUrl", admEstate.getListpicwechat());
//				// 设定分享地址
//				material.put("url", H5ConfigSupport_.getApiServerUrl() + "/WxShareView/"+type+"/"+ id);//微信分享,需授权
//			}else if("2".equals(type)){//活动分享
//				AdmActivityDBO admActivityDBO = new AdmActivityDBO();
//				admActivityDBO.setId(id);
//				AdmActivityDBO admActivity = admActivityDao.doRead(admActivityDBO);
//				if(EmptyHelper.isEmpty(admActivity)){
//					return loadDefault();
//				}
//				// 素材分享
//				//楼盘标题
//				material.put("title",admActivity.getActivityName());
//				// 设定分享描述
//				material.put("desc", admActivity.getContent());
//				// 设定分享图片/缩略图
//				material.put("imgUrl", admActivity.getImage1());
//				// 设定分享地址
//				material.put("url", H5ConfigSupport_.getApiServerUrl() + "/WxShareView/"+type+"/"+ id);//微信分享,需授权
//			}else{//文章分享
//				AdmNewsDBO admNewsDBO = new AdmNewsDBO();
//				admNewsDBO.setId(id);
//				AdmNewsDBO admNews = admNewsDao.doRead(admNewsDBO);
//				if(EmptyHelper.isEmpty(admNews)){
//					return loadDefault();
//				}
//				// 素材分享
//				//楼盘标题
//				material.put("title",admNews.getTitle());
//				// 设定分享描述
//				material.put("desc", admNews.getSummary());
//				// 设定分享图片/缩略图
//				material.put("imgUrl", admNews.getImageWechat());
//				// 设定分享地址
//				material.put("url", H5ConfigSupport_.getApiServerUrl() + "/WxShareView/"+type+"/"+ id);//微信分享,需授权
//				
//			}
			
			
			// 素材分享
			//楼盘标题
//			material.put("title",admEstate.getEstateName());
//			// 设定分享描述
//			material.put("desc", admEstate.getBrightDesc());
//			// 设定分享图片/缩略图
//			material.put("imgUrl", admEstate.getListpicwechat());
//			// 设定分享地址
//			material.put("url", apiServerUrl + "/WXMaterialView/"+type+"/"+ id);//微信分享,需授权
//			material.put("linkQQ", apiServerUrl + "/WxShareView/" + id);//QQ分享,不需授权

//			material.remove("content");
//			material.remove("synopsis");
//			material.remove("lookPicUrl");
		}
		return material;
	}

	private JSONObject loadDefault() {
		JSONObject material = new JSONObject();
		// 设定分享标题
		material.put("title", "置业绿城");
		// 设定分享简介描述
		material.put("desc", "美好生活,置业绿城");
//		// 设定分享图片/缩略图
//		material.put("imgUrl", "http://img.ug.tyytjy.com/o_1ciqt1t859fp2a6f64743lds3m.jpg");
//		// 设定分享地址
//		material.put("url", "http://img.ug.tyytjy.com");
		return material;
	}
}
