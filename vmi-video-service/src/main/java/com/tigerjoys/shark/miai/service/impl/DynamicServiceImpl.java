package com.tigerjoys.shark.miai.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.nbs.web.context.BeatContext;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.RedisCacheConst;
import com.tigerjoys.shark.miai.agent.ITaskAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.enums.TaskCategoryEnum;
import com.tigerjoys.shark.miai.dto.service.DynamicDto;
import com.tigerjoys.shark.miai.dto.service.DynamicPostDto;
import com.tigerjoys.shark.miai.enums.DynamicReqTypeEnum;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;
import com.tigerjoys.shark.miai.inter.contract.IUserDynamicContentContract;
import com.tigerjoys.shark.miai.inter.contract.IUserDynamicContract;
import com.tigerjoys.shark.miai.inter.contract.IUserDynamicFavorContract;
import com.tigerjoys.shark.miai.inter.contract.IUserPhotoResourceContract;
import com.tigerjoys.shark.miai.inter.entity.UserDynamicContentEntity;
import com.tigerjoys.shark.miai.inter.entity.UserDynamicEntity;
import com.tigerjoys.shark.miai.inter.entity.UserDynamicFavorEntity;
import com.tigerjoys.shark.miai.inter.entity.UserPhotoResourceEntity;
import com.tigerjoys.shark.miai.service.IDynamicService;
import com.tigerjoys.shark.miai.utils.Helper;
import com.tigerjoys.shark.miai.utils.ServiceHelper;
import com.tigerjoys.shark.miai.utils.ShowDateStringUtil;

@Service
public class DynamicServiceImpl implements IDynamicService {
	
	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private IUserDynamicContract userDynamicContract;
	
	@Autowired
	private IUserDynamicContentContract userDynamicContentContract;
	
	@Autowired
	private IUserDynamicFavorContract userDynamicFavorContract;
	
	@Autowired
	private IUserPhotoResourceContract userPhotoResourceContract;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private ITaskAgent taskAgent;
	
	@Autowired
	@Qualifier(RedisCacheConst.REDIS_PUBLIC_CACHE)
	private CacheRedis cacheRedis;
	
	@Override
	public ActionResult insertDynamic(DynamicPostDto dto, byte[] bytes) throws Exception {
		//这里单独处理图片上传的处理     注意如果打成zip包之后就不能进行图片顺序的控制了
//		List<String> paths = null;
//		try {
//			FileUploadListResult result = unzipPicture(bytes);
//			if(result.getCode() != 0)
//				return ActionResult.fail(result.getCode(), result.getMsg());
//			paths = result.getPaths();
//		} catch (Exception e) {
//			return ActionResult.fail();
//		}
//		//调用真正的插入动态的处理
//		return insertDynamic(dto, paths);
		return null;
	}
	
	@Override
	public ActionResult insertDynamic(DynamicPostDto dto, MultipartFile file) throws Exception {
		//进行视频上传处理  
//		List<String> paths = null;
//		try {
//			FileUploadListResult result = uploadVideo(file, "video");
//			if(result.getCode() != 0)
//				return ActionResult.fail(result.getCode(), result.getMsg());
//			paths = result.getPaths();
//		} catch (Exception e) {
//			return ActionResult.fail();
//		}
//		return insertDynamic(dto, paths);
		return null;
	}
	
	@Override
	@Transactional
	public ActionResult insertDynamic(DynamicPostDto dto, List<String> paths) throws Exception {
		BeatContext context = RequestUtils.getCurrent();
		long userid = context.getUserid();
		if(userid <= 0) {
			ActionResult.fail();
		}
		//进行插入动态数据处理
		Date now = new Date();
		UserDynamicEntity dynamicEntity = new UserDynamicEntity();
		dynamicEntity.setUserid(userid);
		dynamicEntity.setState(1);
		dynamicEntity.setCreate_time(now);
		dynamicEntity.setUpdate_time(now);
		dynamicEntity.setAudience_num(0L);
		dynamicEntity.setFavor_num(0L);
		dynamicEntity.setType(dto.getType());
		userDynamicContract.insert(dynamicEntity);
		logger.info("插入了动态数据表");
		//进行插入动态内容数据处理
		UserDynamicContentEntity contentEntity = new UserDynamicContentEntity();
		contentEntity.setDynamicid(dynamicEntity.getId());
		contentEntity.setContent(dto.getContent());
		String url = Tools.joinString(paths);
		contentEntity.setPath(url);
		userDynamicContentContract.insert(contentEntity);
		logger.info("插入了动态内容表");
		//进行任务完成处理
		try {
			if(dto.getType() == 1) {
				taskAgent.finishTask(userid, TaskCategoryEnum.IMAGE);
			} else if(dto.getType() == 2) {
				taskAgent.finishTask(userid, TaskCategoryEnum.VIDEO);
			}
		} catch (Exception e) {
			
		}
		return ActionResult.success();
	}
	
//	private FileUploadListResult uploadVideo(MultipartFile file, String directory) throws IOException {
//		if(file == null | Tools.isNull(file.getOriginalFilename())) {
//			return FileUploadListResult.getFileUploadDto(80001, "请上传avi,mp4,mov等格式的视频");
//		}
//		
//		//获得文件格式
//		String fileExt = null;
//		String picFileName = file.getOriginalFilename();
//		if(picFileName.indexOf(".")>-1){
//			fileExt = picFileName.substring(picFileName.lastIndexOf(".")+1).toLowerCase();
//		}
//		return uploadVideo(file, fileExt, directory);
//	}
	
	@Override
	public ActionResult getDynamicTabList(long userId, int pagesize) throws Exception {
		//首先获取关注页上的相关数据
		List<DynamicDto> favors = getDynamicListData(userId, userId, pagesize, null, 1);
		//然后获取最新页上的数据
		List<DynamicDto> news = getDynamicListData(userId, userId, pagesize, null, 2);
		//进行返回结果的拼接处理
		HashMap<String, Object> data = new HashMap<String, Object>();
		String stamp = null;
		boolean isNext = false;
		if(Tools.isNotNull(favors) && favors.size() > 0) {
			if(favors.size() > pagesize) {
				favors.subList(0, pagesize);
				isNext = true;
			}
			stamp = favors.get(favors.size() - 1).getId().toString();
		}
		data.put("favors", favors);
		data.put("favors_nextPage", isNext);
		data.put("favors_stamp", stamp);
		stamp = null;
		isNext = false;
		if(Tools.isNotNull(news) && news.size() > 0) {
			if(news.size() > pagesize) {
				news.subList(0, pagesize);
				isNext = true;
			}
			stamp = news.get(news.size() - 1).getId().toString();
		}
		data.put("news", news);
		data.put("news_nextPage", isNext);
		data.put("news_stamp", stamp);
		return ActionResult.success(data);
	}

//	@Override
//	public List<DynamicDto> getDynamicList(long userId, int pagesize, long stamp, int type) throws Exception {
//		List<DynamicDto> data = getDynamicListData(userId, pagesize, stamp, type);
//		return data;
//	}

	//注意这里在做处理是没有进行截断处理  在外围进行截断处理确定参数
	@Override
	public List<DynamicDto> getDynamicListData(long userId, long favorId, int pagesize, String stamp, int type) throws Exception {
		List<UserDynamicEntity> listEntity = null;
		List<DynamicDto> dynamicDtos = new ArrayList<>();
		if(type == DynamicReqTypeEnum.favor.getCode()) {
			//查看关注人的动态
			String index = Tools.getDateTime(new Date());
			if(Tools.isNotNull(stamp)){
				index = stamp;
			}
			listEntity = userDynamicContract.findAttentionDynamic(userId, index, pagesize+1);
		} else {
			//查看自己 最新 他人 的动态
			PageModel pageModel = new PageModel();
			pageModel.setPageSize(pagesize + 1);
			if(type != DynamicReqTypeEnum.news.getCode()) {
				pageModel.addQuery(Restrictions.eq("userid", userId));
			}
			pageModel.addQuery(Restrictions.eq("state", 1));
			if(Tools.isNotNull(stamp)) {
				pageModel.addQuery(Restrictions.lt("create_time", stamp));
			}
			pageModel.desc("create_time");
			listEntity = userDynamicContract.load(pageModel);
		}
		if(Tools.isNotNull(listEntity) && listEntity.size() > 0) {
			List<Long> dynamicIds = getDynamicIdList(listEntity);
			//根据对应的需要展示的动态id来获取当前用户是否对其进行点赞处理
			Map<Long, UserDynamicFavorEntity> favorMap = getBatchUserFavorStatus(favorId, dynamicIds);
			
			DynamicDto dynamicDto = null;
			//循环处理拼接对应的需要处理的数据
			for (UserDynamicEntity userDynamicEntity : listEntity) {
				//根据查询到的实体进行设置基础数据
				dynamicDto = DynamicDto.parse(userDynamicEntity);
				
				//设置用户的观看数量
//				String zValue = "0";
//				String cache = cacheRedis.hget(AgentRedisCacheConst.REDIS_DYNAMIC_HASH_LOOK_CACHE, dynamicDto.getId() + "");
//				if(Tools.isNotNull(cache)) {
//					zValue = cache;
//				}
				Long count = dynamicDto.getAudience_count();
//				count = count + Long.parseLong(zValue);
				dynamicDto.setAudience_count(count);
				
				//设置点赞个数
//				zValue = "0";
//				cache = cacheRedis.hget(AgentRedisCacheConst.REDIS_DYNAMIC_HASH_FAVOR_CACHE, dynamicDto.getId() + "");
//				if(Tools.isNotNull(cache)) {
//					zValue = cache;
//				}
				count = dynamicDto.getFavor_count();
//				count = count + Long.parseLong(zValue);
				dynamicDto.setFavor_count(count);
				
				//设置点赞的标示
				if(favorMap.get(dynamicDto.getId()) != null) {
					dynamicDto.setFavor_status(true);
				} else {
					dynamicDto.setFavor_status(false);
				}
				
				dynamicDto.setCreate_time(Tools.getDateTime(userDynamicEntity.getCreate_time()));
				//获取对应的动态内容
				UserDynamicContentEntity contentEntity = userDynamicContentContract.findById(dynamicDto.getId());
				if(Tools.isNotNull(contentEntity)) {
					dynamicDto.setContent(contentEntity.getContent());
					//设置动态的图片
					if(Tools.isNotNull(contentEntity.getPath()) && contentEntity.getPath().length() > 0) {
						List<String> paths = Arrays.asList(Tools.split(contentEntity.getPath()));
						if(Tools.isNotNull(paths) && paths.size() > 0){
							if(userDynamicEntity.getType().equals(1)){
								//进行图片获取视频路径的处理
								dynamicDto.setPaths(Helper.replacePath(paths, ServiceHelper.COMMON_150_TAG));
								dynamicDto.setBigPaths(Helper.replacePath(paths, null));
							} else {
								//设置前景图
								dynamicDto.setForeground(ServiceHelper.getCdnPhoto(paths.get(0), ServiceHelper.COMMON_150_TAG));
								//设置视频的前景图和视频路径
								dynamicDto.setVideo(ServiceHelper.getCdnVideo(paths.get(1)));
							}
						}	
					}
				}
				//设置并获取用户信息
				if(Tools.isNotNull(userDynamicEntity.getUserid()) && userDynamicEntity.getUserid().longValue() > 0) {
					UserBO user = userAgent.findById(userDynamicEntity.getUserid());
					if(Tools.isNotNull(user)) {
						dynamicDto.setUserid(user.getUserid()+"");
						dynamicDto.setNickname(user.getNickname());
						dynamicDto.setAge(ShowDateStringUtil.getAgeForCompareDate(user.getBirthday()));
						dynamicDto.setSex(user.getSex());
						dynamicDto.setVip(user.vipValue());
						//这个地方是否需要进行默认处理图片动作  检测是否为空值设置默认图片处理
						dynamicDto.setPhoto(ServiceHelper.getUserSmallPhoto(user.getPhoto()));
						dynamicDto.setSignature(user.getSignature());
					}
				}
				
				//设置显示时间
				String dateTime = getCreate_time_s(userDynamicEntity.getCreate_time());
				dynamicDto.setDate(dateTime);
				dynamicDtos.add(dynamicDto);
			}
		}
		return dynamicDtos;
	}

	
	@Override
	public ActionResult addDynamicAudience(long userId, long dynamicId) throws Exception {
		//这里使用缓存增量的处理   即将对应的数据设置到缓存中就可以了不需要直接进行刷入到数据库中    启用定时任务将增量更新到数据库中就可以了
		//这里是否需要进行查看对应动态是否存在????   这个地方还是进行检测吧 不检测后期进行统一插入时也会出现问题
		//首先检查对应的动态是否存在
		UserDynamicEntity dynamic = userDynamicContract.findById(dynamicId);
		if(Tools.isNull(dynamic)) {
			//返回没有找到对应的动态信息
			return ActionResult.fail(ErrorCodeEnum.db_not_found);
		}
		userDynamicContract.updateByStatement("audience_num=audience_num+1", "id="+dynamicId);
//		cacheRedis.sadd(AgentRedisCacheConst.REDIS_DYNAMIC_SET_LOOK_CACHE, dynamicId + "");
//		cacheRedis.hincrBy(AgentRedisCacheConst.REDIS_DYNAMIC_HASH_LOOK_CACHE, dynamicId + "", 1);
		return ActionResult.success();
	}

	/**
	 * 添加对动态的点赞信息
	 */
	@Override
	public ActionResult addDynamicFavor(long userId, long dynamicId) throws Exception {
		//这个地方首先需要检测对应id的动态是否存在
		UserDynamicEntity entity = userDynamicContract.findById(dynamicId);
		if(Tools.isNull(entity)) {
			return ActionResult.fail(ErrorCodeEnum.db_not_found);
		}
		PageModel pageModel = new PageModel();
		pageModel.addQuery(Restrictions.eq("userid", userId));
		pageModel.addQuery(Restrictions.eq("dynamic_id", dynamicId));
		long count = userDynamicFavorContract.count(pageModel);
		if(count > 0) {
			//向客户端返回 已经进行点赞处理 不能进行撤销
			return ActionResult.fail(ErrorCodeEnum.parameter_error.getCode(), "点赞不能取消");
		}
		UserDynamicFavorEntity favorEntity =new UserDynamicFavorEntity();
		Date now =new Date();
		favorEntity.setCreate_time(now);
		favorEntity.setDynamic_id(dynamicId);
		favorEntity.setStatus(1);
		favorEntity.setUpdate_time(now);
		favorEntity.setUserid(userId);	
		userDynamicFavorContract.insert(favorEntity);
		userDynamicContract.updateByStatement("favor_num=favor_num+1", "id="+dynamicId);
//		cacheRedis.sadd(AgentRedisCacheConst.REDIS_DYNAMIC_SET_FAVOR_CACHE, dynamicId + "");
//		cacheRedis.hincrBy(AgentRedisCacheConst.REDIS_DYNAMIC_HASH_FAVOR_CACHE, dynamicId + "", 1);
		//这个地方是否是需要将点赞数进行回传？？？？？？？？？？？？？？？？根据需要决定
		return ActionResult.success();
	}
	
	/**
	 * 根据用户id来删除对应的动态信息
	 */
	@Override
	public ActionResult deleteDynamicbyId(long userId, long dynamicId) throws Exception {
		//首先检查对应的动态是否存在
		PageModel pageModel = new PageModel();
		pageModel.addQuery(Restrictions.eq("userid", userId));
		pageModel.addQuery(Restrictions.eq("id", dynamicId));
		List<UserDynamicEntity> dynamics = userDynamicContract.load(pageModel);
		if(Tools.isNull(dynamics) || dynamics.size() <= 0) {
			//返回没有找到对应的动态信息
			return ActionResult.fail(ErrorCodeEnum.db_not_found);
		}
		//然后修改对应动态的状态信息为删除状态
		UserDynamicEntity dynamic = dynamics.get(0);
		dynamic.setState(-9);
		int count = userDynamicContract.update(dynamic);
		if(count <= 0) {
			//进行修改动态失败
			return ActionResult.fail(ErrorCodeEnum.db_error);
		}
		//这里返回修改动态成功的处理结果
		return ActionResult.success();
	}
	
	/**
	 * 获取对应动态的id列表值
	 * @param list
	 * @return
	 */
	private List<Long> getDynamicIdList(List<UserDynamicEntity> list) {
		List<Long> dynamicIds = new ArrayList<>();
		if(Tools.isNotNull(list)) {
			for (UserDynamicEntity dynamic : list) {
				dynamicIds.add(dynamic.getId());
			}
		}
		return dynamicIds;
	}
	
	/**
	 * 批量获取用户的点赞信息
	 * @param userId
	 * @param dynamicIds
	 * @return
	 * @throws Exception
	 */
	private Map<Long, UserDynamicFavorEntity> getBatchUserFavorStatus(long userId, List<Long> dynamicIds) throws Exception {
		PageModel pageModel = new PageModel();
		pageModel.addQuery(Restrictions.eq("userid", userId));
		pageModel.addQuery(Restrictions.in("dynamic_id", dynamicIds));
		pageModel.addQuery(Restrictions.eq("status", 1));
		Map<Long, UserDynamicFavorEntity> dataMap = new HashMap<>();
		List<UserDynamicFavorEntity> favors = userDynamicFavorContract.load(pageModel);
		if(Tools.isNotNull(favors) && favors.size() > 0){
			for (UserDynamicFavorEntity userDynamicFavorEntity : favors) {
				dataMap.put(userDynamicFavorEntity.getDynamic_id(), userDynamicFavorEntity);
			}
		}
		return dataMap;
	}
	
	/**
	 * 动态时间显示格式
	 *   今天的   今天  7:30
	 *   昨天的   昨天 7:30
	 *   剩余类型   0000 00-00 7:30
	 * @param create_time
	 * @return
	 */
	private String getCreate_time_s(Date create_time) {
		long cTime = create_time.getTime();
		long tTime = Tools.getDayTime();
		long yTime = Tools.getDayTime(1);
		if(cTime >= tTime) {
			//今天的
			return "今天 " + Tools.getFormatDate(create_time, "HH:mm");
		} else if(cTime >= yTime) {
			//昨天的
			return "昨天 " + Tools.getFormatDate(create_time, "HH:mm");
		} else {
			//剩余类型
			return Tools.getFormatDate(create_time, "yyyy-MM-dd HH:mm");
		}
	}

	@Override
	public List<String> getTopPhoto(long userId) throws Exception {
		PageModel pageModel = new PageModel();
		pageModel.addQuery(Restrictions.eq("userid", userId));
		pageModel.addQuery(Restrictions.eq("state", 1));
		pageModel.addLimitField(0, 10);
		pageModel.desc("id");
		List<String> photos = new ArrayList<>();
		List<UserPhotoResourceEntity> photoResourceEntities = userPhotoResourceContract.load(pageModel);
		if(Tools.isNotNull(photoResourceEntities) && photoResourceEntities.size() > 0) {
			for (UserPhotoResourceEntity userPhotoResourceEntity : photoResourceEntities) {
				photos.add(userPhotoResourceEntity.getPath());
			}
		}
		return photos;
	}

	@Override
	public List<String> getTopDynamic(long userId) throws Exception {
		//需求取5张   取的原则是在最新的动态中依次取满五张即可    即如果最新的动态里边有9个  直接在里边取五个就可以了  另外不取视频类型的数据
		//解决上述需求的操作过程   取5条最新的动态数据  然后进行数据拼接处理
		PageModel pageModel = new PageModel();
		pageModel.addQuery(Restrictions.eq("userid", userId));
		pageModel.addQuery(Restrictions.eq("state", 1));
		//设置查询类型是图片类型  排除视频类型
		pageModel.addQuery(Restrictions.eq("type", 1));
		pageModel.desc("id");
		pageModel.addLimitField(0, 5);
		List<String> photos = new ArrayList<>(); 
		List<UserDynamicEntity> dynamics = userDynamicContract.load(pageModel);
		if(Tools.isNotNull(dynamics) && dynamics.size() > 0) {
			for (UserDynamicEntity userDynamicEntity : dynamics) {
				UserDynamicContentEntity contentEntity = userDynamicContentContract.findById(userDynamicEntity.getId());
				if(Tools.isNotNull(contentEntity)) {
					String path = contentEntity.getPath();
					if(Tools.isNotNull(path)) {
						List<String> paths = JsonHelper.toList(path, String.class);
						//需要注意这个地方是否需要进行 路径的拼接处理
						photos.addAll(paths);
					}
				}
				if(photos.size() >= 5) {
					photos.subList(0, 5);
					break;
				}
			}
		}
		return photos;
	}

}
