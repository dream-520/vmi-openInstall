package com.tigerjoys.shark.miai.agent.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IDynamicAgent;
import com.tigerjoys.shark.miai.inter.contract.IUserDynamicContentContract;
import com.tigerjoys.shark.miai.inter.contract.IUserDynamicContract;
import com.tigerjoys.shark.miai.inter.contract.IUserPhotoResourceContract;
import com.tigerjoys.shark.miai.inter.entity.UserDynamicContentEntity;
import com.tigerjoys.shark.miai.inter.entity.UserDynamicEntity;
import com.tigerjoys.shark.miai.inter.entity.UserPhotoResourceEntity;

@Service
public class DynamicAgentImpl implements IDynamicAgent {
	
	@Autowired
	private IUserDynamicContract userDynamicContract;
	
	@Autowired
	private IUserDynamicContentContract userDynamicContentContract;
	
	@Autowired
	private IUserPhotoResourceContract userPhotoResourceContract;

	@Override
	public Map<String, List<String>> getTopPhoto(long userId) throws Exception {
		PageModel pageModel = new PageModel();
		pageModel.addQuery(Restrictions.eq("userid", userId));
		pageModel.addQuery(Restrictions.eq("state", 1));
		pageModel.addLimitField(0, 100);
		pageModel.desc("id");
		Map<String, List<String>> result = new HashMap<>();
		List<String> bigPaths = new ArrayList<>();
		List<String> smallPaths = new ArrayList<>();
		List<UserPhotoResourceEntity> entities = userPhotoResourceContract.load(pageModel);
		//获取对应的大图路径
		if(Tools.isNotNull(entities) && entities.size() > 0) {
			for (UserPhotoResourceEntity entity : entities) {
				//这个地方需要注意进行图片路径的拼接处理
				bigPaths.add(entity.getPath());
			}
		}
		result.put("bigPaths", bigPaths);
		//处理获取对应的小图的处理   根据大图路径获取对应的小图的路径
		//这里暂时使用大图的路径处理
		if(bigPaths.size() > 10){
			smallPaths = bigPaths.subList(0, 10);
		}else if(bigPaths.size() > 0){
			smallPaths.addAll(bigPaths);
		}
		result.put("smallPaths", smallPaths);
		return result;
	}

	@Override
	public List<String> getTopDynamic(long userId) throws Exception {
		//需求取5张   取的原则是在最新的动态中依次取满五张即可    即如果最新的动态里边有9个  直接在里边取五个就可以了  另外不取视频类型的数据
		//解决上述需求的操作过程   取5条最新的动态数据  然后进行数据拼接处理
		PageModel pageModel = new PageModel();
		pageModel.addQuery(Restrictions.eq("userid", userId));
		pageModel.addQuery(Restrictions.eq("state", 1));
		//设置查询类型是图片类型  排除视频类型
//		pageModel.addQuery(Restrictions.eq("type", 1));
		pageModel.desc("id");
		pageModel.addLimitField(0, 4);
		List<String> photos = new ArrayList<>(); 
		List<UserDynamicEntity> dynamics = userDynamicContract.load(pageModel);
		if(Tools.isNotNull(dynamics) && dynamics.size() > 0) {
			for (UserDynamicEntity userDynamicEntity : dynamics) {
				UserDynamicContentEntity contentEntity = userDynamicContentContract.findById(userDynamicEntity.getId());
				if(Tools.isNotNull(contentEntity)) {
					String path = contentEntity.getPath();
					if(Tools.isNotNull(path) && path.length() > 0) {
						String[] paths = Tools.split(path);
						if(userDynamicEntity.getType() == 1) {
							//图片类型的动态
							if(paths.length > 0) {
								photos.addAll(Arrays.asList(paths));
							}
						} else if(userDynamicEntity.getType() == 2){
							//视频类型的动态
							if(paths.length > 1) {
								photos.add(paths[0]);
							}
						}
					}
				}
				if(photos.size() >= 4) {
					photos = photos.subList(0, 4);
					break;
				}
			}
		}
		return photos;
	}

}
