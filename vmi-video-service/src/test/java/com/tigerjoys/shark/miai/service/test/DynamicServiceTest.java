package com.tigerjoys.shark.miai.service.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tigerjoys.shark.miai.utils.BaseTestConfig;

/**
 * 动态相关功能的测试
 * @author shiming
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/applicationContext.xml"})
public class DynamicServiceTest extends BaseTestConfig {

	/*
	 
	@Autowired
	private IDynamicService dynamicService;
	
	@Autowired
	private IMeiUserInfoContract meiUserInfoContract;
	
	@Autowired
	private IMeiDynamicContract meiDynamicContract;
	
	@Autowired
	private IMeiPhotoContract meiPhotoContract;
	
	@Autowired
	private IMeiUserDynamicContract meiUserDynamicContract;
	
	@Autowired
	private IMeiUserPhotoContract meiUserPhotoContract;
	
	//@Test
	public void testDate() {
//		String date = dynamicService.getCreate_time_s(new Date());
//		System.out.println(date);
//		date = dynamicService.getCreate_time_s(Tools.getdate(new Date(), -1));
//		System.out.println(date);
//		date = dynamicService.getCreate_time_s(Tools.getdate(new Date(), -2));
//		System.out.println(date);
		
		List<Object> pictures = new ArrayList<>();
		pictures.add("2222");
		pictures.add("3333");
		pictures.toArray();
		String string = pictures.toString();
		System.out.println(string);
		
		
		JSONArray array = new JSONArray(pictures);
		System.out.println(array.toJSONString());
		
		String str = array.toJSONString();
		
		List<String> list1 = JsonHelper.toList(str, String.class);
		List<String> list2 = JsonHelper.toList(array, String.class);
		
		System.out.println(list1.toString());
		System.out.println(list2.toString());
		
	}
	
//	@Test
	public void testGetListDynamic(){
		try {
//			dynamicService.getDynamicList(5031969847050496l, 10, "50", 1);
//			ActionResult result = dynamicService.getDynamicList(1468676853399808l, 20, 0, 4);
//			int code = result.getCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void testInsertDynamic(){
		try {
			MultipartFile file = null;
			DynamicPostDto dto = new DynamicPostDto();
			dto.setContent("检测卡复健科福建卡了设计费离开的打发的房间阿里是否键疯狂拉升积分");
			dto.setType(1);
			dynamicService.insertDynamic(dto, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPhotoTran() throws Exception {
		long stamp = 0;
		PageModel pageModel = new PageModel();
		while (true) {
			pageModel.clearAll();
			pageModel.setPageSize(20 + 1);
			if(stamp > 0) {
				pageModel.addQuery(Restrictions.ge("id", stamp));
			}
			//pageModel.desc("id");
			List<MeiUserInfoEntity> list = meiUserInfoContract.load(pageModel);
			if(Tools.isNotNull(list)) {
				if(list.size() > 20) {
					//进行数据的截取处理  说有还有数据超过20条  
					stamp = list.get(list.size() - 1).getId();
					list = list.subList(0, list.size() - 1);
					for (MeiUserInfoEntity meiUserEntity : list) {
						long uid = meiUserEntity.getUid();
						//处理将对应的原始库中的相册添加到新库中的处理
						PageModel page = new PageModel();
						page.addQuery(Restrictions.eq("uid", uid));
						List<MeiPhotoEntity> list2 = meiPhotoContract.load(page);
						if(Tools.isNotNull(list2) && list2.size() > 0) {
							//循环将数据写入新库中
							for (MeiPhotoEntity meiPhotoEntity : list2) {
								if(Tools.isNotNull(meiPhotoEntity.getPath())) {
									MeiUserPhotoEntity entity = new MeiUserPhotoEntity();
									entity.setPath(meiPhotoEntity.getLocal_path());
									entity.setUid(uid);
									meiUserPhotoContract.insert(entity );
								}
							}
						}
					}
				} else {
					for (MeiUserInfoEntity meiUserEntity : list) {
						long uid = meiUserEntity.getUid();
						//处理将对应的原始库中的相册添加到新库中的处理
						PageModel page = new PageModel();
						page.addQuery(Restrictions.eq("uid", uid));
						page.addQuery(Restrictions.ne("path", null));
						List<MeiPhotoEntity> list2 = meiPhotoContract.load(page);
						if(Tools.isNotNull(list2) && list2.size() > 0) {
							//循环将数据写入新库中
							for (MeiPhotoEntity meiPhotoEntity : list2) {
								if(Tools.isNotNull(meiPhotoEntity.getPath())) {
									MeiUserPhotoEntity entity = new MeiUserPhotoEntity();
									entity.setPath(meiPhotoEntity.getLocal_path());
									entity.setUid(uid);
									meiUserPhotoContract.insert(entity );
								}
							}
						}
					}
					//说明本次本能是最后一页数据了  加载完这批数据就可以退出了
					System.err.println("这里是最后一批数据了, 加载完本批数据就可以退出了");
					break;
				}
			} else {
				//没有拉取到数据所有就可以直接退出了
				System.err.println("没有更多数据了 可以退出了。");
				break;
			}
		} 
	}
	
	
//	@Test
	public void testDynamicTran() throws Exception {
		List<Integer> times = new ArrayList<>();
		//分配对应的时间段
		times.add(1);  // 7 8 9 
		times.add(2);  //10 11 12
		times.add(3);  //13 14 15
		times.add(4);  //16 17 18
		times.add(5);  //19 20 21
		
		List<Integer> days1 = new ArrayList<>();
		days1.add(1);
		days1.add(2);
		days1.add(29);
		days1.add(30);
		days1.add(31);
		List<Integer> days2 = new ArrayList<>();
		days2.add(1);
		days2.add(2);
		days2.add(19);
		days2.add(20);
		days2.add(21);
		days2.add(22);
		days2.add(23);
		days2.add(24);
		days2.add(25);
		days2.add(26);
		days2.add(27);
		days2.add(28);
		days2.add(29);
		days2.add(30);
		days2.add(31);
		List<Integer> days3 = new ArrayList<>();
		days3.add(1);
		days3.add(2);
		days3.add(3);
		days3.add(4);
		days3.add(5);
		days3.add(6);
		days3.add(7);
		days3.add(8);
		days3.add(9);
		days3.add(10);
		days3.add(11);
		days3.add(12);
		days3.add(13);
		days3.add(14);
		days3.add(15);
		days3.add(16);
		days3.add(17);
		days3.add(18);
		days3.add(19);
		days3.add(20);
		days3.add(21);
		days3.add(22);
		days3.add(23);
		days3.add(24);
		days3.add(25);
		days3.add(26);
		days3.add(27);
		days3.add(28);
		days3.add(29);
		days3.add(30);
		days3.add(31);

		List<Date> collect = new ArrayList<>();
		
//		Collections.sort(times);
		
		//用于标示那一天上线的前一天的日期      
		int day = 3;
		
		long stamp = 0;
		PageModel pageModel = new PageModel();
		while (true) {
			pageModel.clearAll();
			pageModel.setPageSize(20 + 1);
			if(stamp > 0) {
				pageModel.addQuery(Restrictions.ge("id", stamp));
			}
			//pageModel.desc("id");
			List<MeiUserInfoEntity> list = meiUserInfoContract.load(pageModel);
			Comparator comparator=Collections.reverseOrder();
			if(Tools.isNotNull(list)) {
				if(list.size() > 20) {
					//进行数据的截取处理  说有还有数据超过20条  
					stamp = list.get(list.size() - 1).getId();
					list = list.subList(0, list.size() - 1);
					for (MeiUserInfoEntity meiUserEntity : list) {
						long uid = meiUserEntity.getUid();
						//处理将对应的原始库中的动态添加到新库中的处理
						PageModel page = new PageModel();
						page.addQuery(Restrictions.eq("uid", uid));
						long count = meiDynamicContract.count(page);
						if(count > 0) {
							//首先一次把数据全部加载过了
							page.setPageSize(120);
							List<MeiDynamicEntity> datas = meiDynamicContract.load(page);
							int total = datas.size();
							
							//说明这个用对应的动态数据 需要进行处理  处理分三个阶段进行
							if(count < 10) {
								//获取对应的随机天
								List<Integer> days = createRandomList(days1, total);
								List<Integer> smallDays = ListSamll(days, day);
								List<Integer> bigDays = ListBig(days, day);
								Collections.sort(smallDays, comparator);
								Collections.sort(bigDays, comparator);
								
								for(int remainder = 0; remainder < total;) {
									for (Integer dayM : smallDays) {
										//此处说明用户可能有不超过10条的动态  需要把这不到的10条动态分配到对应的5天
										//首先随机需要发送的条数
										int num = getRandomNumber(2, 3);
										//随机发送条数对应的时间段
										List<Integer> time = createRandomList(times, num);
										//对获取到的时间值进行倒叙排序
										
										Collections.sort(time, comparator);
										for (Integer i : time) {
											//获取对应的时间
											int hour = getHour(i);
											int min = getRandomNumber(1, 60);
											MeiDynamicEntity data = datas.get(remainder);
											Calendar cal = Calendar.getInstance();
											cal.set(Calendar.MONTH, 0);
											cal.set(Calendar.DAY_OF_MONTH, dayM);
											cal.set(Calendar.HOUR_OF_DAY, hour);
											cal.set(Calendar.MINUTE, min);
											Date birthday = cal.getTime();
											collect.add(birthday);
											MeiUserDynamicEntity entity = new MeiUserDynamicEntity();
											entity.setContent(data.getContent());
											entity.setCreatetime(birthday);
											entity.setPath(data.getLocal_path());
											entity.setUid(data.getUid());
											if(data.getType().equals(17)|| data.getType().equals(5)) {
												entity.setType(1);
											} else {
												entity.setType(2);
											}
											meiUserDynamicContract.insert(entity);
											remainder++;
											if(remainder >= total)
												break;
										}
										if(remainder >= total)
											break;
									}
									if(remainder >= total)
										break;
									System.err.println("111:"+remainder);
									for (Integer dayM : bigDays) {
										//此处说明用户可能有不超过10条的动态  需要把这不到的10条动态分配到对应的5天
										//首先随机需要发送的条数
										int num = getRandomNumber(2, 3);
										//随机发送条数对应的时间段
										List<Integer> time = createRandomList(times, num);
										//对获取到的时间值进行倒叙排序
										Collections.sort(time, comparator);
										for (Integer i : time) {
											//获取对应的时间
											int hour = getHour(i);
											int min = getRandomNumber(1, 60);
											System.err.println("222:"+remainder);
											MeiDynamicEntity data = datas.get(remainder);
											Calendar cal = Calendar.getInstance();
											cal.set(Calendar.MONTH, 11);
											cal.set(Calendar.DAY_OF_MONTH, dayM);
											cal.set(Calendar.HOUR_OF_DAY, hour);
											cal.set(Calendar.MINUTE, min);
											Date birthday = cal.getTime();
											collect.add(birthday);
											MeiUserDynamicEntity entity = new MeiUserDynamicEntity();
											entity.setContent(data.getContent());
											entity.setCreatetime(birthday);
											entity.setPath(data.getLocal_path());
											entity.setUid(data.getUid());
											if(data.getType().equals(17)|| data.getType().equals(5)) {
												entity.setType(1);
											} else {
												entity.setType(2);
											}
											meiUserDynamicContract.insert(entity);
											remainder++;
											if(remainder >= total)
												break;
										}
										if(remainder >= total)
											break;
									}
									
								}
							} else if(count < 30) {
								//获取对应的随机天
								List<Integer> days = createRandomList(days2, total);
								List<Integer> smallDays = ListSamll(days, day);
								List<Integer> bigDays = ListBig(days, day);
								Collections.sort(smallDays, comparator);
								Collections.sort(bigDays, comparator);
								
								for(int remainder = 0; remainder < total;) {
									for (Integer dayM : smallDays) {
										//此处说明用户可能有不超过10条的动态  需要把这不到的10条动态分配到对应的5天
										//首先随机需要发送的条数
										int num = getRandomNumber(2, 3);
										//随机发送条数对应的时间段
										List<Integer> time = createRandomList(times, num);
										//对获取到的时间值进行倒叙排序
										
										Collections.sort(time, comparator);
										for (Integer i : time) {
											//获取对应的时间
											int hour = getHour(i);
											int min = getRandomNumber(1, 60);
											MeiDynamicEntity data = datas.get(remainder);
											Calendar cal = Calendar.getInstance();
											cal.set(Calendar.MONTH, 0);
											cal.set(Calendar.DAY_OF_MONTH, dayM);
											cal.set(Calendar.HOUR_OF_DAY, hour);
											cal.set(Calendar.MINUTE, min);
											Date birthday = cal.getTime();
											collect.add(birthday);
											MeiUserDynamicEntity entity = new MeiUserDynamicEntity();
											entity.setContent(data.getContent());
											entity.setCreatetime(birthday);
											entity.setPath(data.getLocal_path());
											entity.setUid(data.getUid());
											if(data.getType().equals(17)|| data.getType().equals(5)) {
												entity.setType(1);
											} else {
												entity.setType(2);
											}
											meiUserDynamicContract.insert(entity);
											remainder++;
											if(remainder >= total)
												break;
										}
										if(remainder >= total)
											break;
									}
									if(remainder >= total)
										break;
									System.err.println("111:"+remainder);
									for (Integer dayM : bigDays) {
										//此处说明用户可能有不超过10条的动态  需要把这不到的10条动态分配到对应的5天
										//首先随机需要发送的条数
										int num = getRandomNumber(2, 3);
										//随机发送条数对应的时间段
										List<Integer> time = createRandomList(times, num);
										//对获取到的时间值进行倒叙排序
										Collections.sort(time, comparator);
										for (Integer i : time) {
											//获取对应的时间
											int hour = getHour(i);
											int min = getRandomNumber(1, 60);
											System.err.println("222:"+remainder);
											MeiDynamicEntity data = datas.get(remainder);
											Calendar cal = Calendar.getInstance();
											cal.set(Calendar.MONTH, 11);
											cal.set(Calendar.DAY_OF_MONTH, dayM);
											cal.set(Calendar.HOUR_OF_DAY, hour);
											cal.set(Calendar.MINUTE, min);
											Date birthday = cal.getTime();
											collect.add(birthday);
											MeiUserDynamicEntity entity = new MeiUserDynamicEntity();
											entity.setContent(data.getContent());
											entity.setCreatetime(birthday);
											entity.setPath(data.getLocal_path());
											entity.setUid(data.getUid());
											if(data.getType().equals(17)|| data.getType().equals(5)) {
												entity.setType(1);
											} else {
												entity.setType(2);
											}
											meiUserDynamicContract.insert(entity);
											remainder++;
											if(remainder >= total)
												break;
										}
										if(remainder >= total)
											break;
									}
									
								}
							} else if(count < 90) {
								//获取对应的随机天
								List<Integer> days = createRandomList(days3, total);
								List<Integer> smallDays = ListSamll(days, day);
								List<Integer> bigDays = ListBig(days, day);
								Collections.sort(smallDays, comparator);
								Collections.sort(bigDays, comparator);
								
								for(int remainder = 0; remainder < total;) {
									for (Integer dayM : smallDays) {
										//此处说明用户可能有不超过10条的动态  需要把这不到的10条动态分配到对应的5天
										//首先随机需要发送的条数
										int num = getRandomNumber(3, 4);
										//随机发送条数对应的时间段
										List<Integer> time = createRandomList(times, num);
										//对获取到的时间值进行倒叙排序
										
										Collections.sort(time, comparator);
										for (Integer i : time) {
											//获取对应的时间
											int hour = getHour(i);
											int min = getRandomNumber(1, 60);
											MeiDynamicEntity data = datas.get(remainder);
											Calendar cal = Calendar.getInstance();
											cal.set(Calendar.MONTH, 0);
											cal.set(Calendar.DAY_OF_MONTH, dayM);
											cal.set(Calendar.HOUR_OF_DAY, hour);
											cal.set(Calendar.MINUTE, min);
											Date birthday = cal.getTime();
											collect.add(birthday);
											MeiUserDynamicEntity entity = new MeiUserDynamicEntity();
											entity.setContent(data.getContent());
											entity.setCreatetime(birthday);
											entity.setPath(data.getLocal_path());
											entity.setUid(data.getUid());
											if(data.getType().equals(17)|| data.getType().equals(5)) {
												entity.setType(1);
											} else {
												entity.setType(2);
											}
											meiUserDynamicContract.insert(entity);
											remainder++;
											if(remainder >= total)
												break;
										}
										if(remainder >= total)
											break;
									}
									if(remainder >= total)
										break;
									System.err.println("111:"+remainder);
									for (Integer dayM : bigDays) {
										//此处说明用户可能有不超过10条的动态  需要把这不到的10条动态分配到对应的5天
										//首先随机需要发送的条数
										int num = getRandomNumber(2, 3);
										//随机发送条数对应的时间段
										List<Integer> time = createRandomList(times, num);
										//对获取到的时间值进行倒叙排序
										Collections.sort(time, comparator);
										for (Integer i : time) {
											//获取对应的时间
											int hour = getHour(i);
											int min = getRandomNumber(1, 60);
											System.err.println("222:"+remainder);
											MeiDynamicEntity data = datas.get(remainder);
											Calendar cal = Calendar.getInstance();
											cal.set(Calendar.MONTH, 11);
											cal.set(Calendar.DAY_OF_MONTH, dayM);
											cal.set(Calendar.HOUR_OF_DAY, hour);
											cal.set(Calendar.MINUTE, min);
											Date birthday = cal.getTime();
											collect.add(birthday);
											MeiUserDynamicEntity entity = new MeiUserDynamicEntity();
											entity.setContent(data.getContent());
											entity.setCreatetime(birthday);
											entity.setPath(data.getLocal_path());
											entity.setUid(data.getUid());
											if(data.getType().equals(17)|| data.getType().equals(5)) {
												entity.setType(1);
											} else {
												entity.setType(2);
											}
											meiUserDynamicContract.insert(entity);
											remainder++;
											if(remainder >= total)
												break;
										}
										if(remainder >= total)
											break;
									}
									
								}
							} else {
								int limit = 120;
								//获取对应的随机天
								List<Integer> days = createRandomList(days3, total);
								List<Integer> smallDays = ListSamll(days, day);
								List<Integer> bigDays = ListBig(days, day);
								Collections.sort(smallDays, comparator);
								Collections.sort(bigDays, comparator);
								
								for(int remainder = 0; remainder < total;) {
									for (Integer dayM : smallDays) {
										//此处说明用户可能有不超过10条的动态  需要把这不到的10条动态分配到对应的5天
										//首先随机需要发送的条数
										int num = getRandomNumber(4, 5);
										//随机发送条数对应的时间段
										List<Integer> time = createRandomList(times, num);
										//对获取到的时间值进行倒叙排序
										
										Collections.sort(time, comparator);
										for (Integer i : time) {
											//获取对应的时间
											int hour = getHour(i);
											int min = getRandomNumber(1, 60);
											MeiDynamicEntity data = datas.get(remainder);
											Calendar cal = Calendar.getInstance();
											cal.set(Calendar.MONTH, 0);
											cal.set(Calendar.DAY_OF_MONTH, dayM);
											cal.set(Calendar.HOUR_OF_DAY, hour);
											cal.set(Calendar.MINUTE, min);
											Date birthday = cal.getTime();
											collect.add(birthday);
											MeiUserDynamicEntity entity = new MeiUserDynamicEntity();
											entity.setContent(data.getContent());
											entity.setCreatetime(birthday);
											entity.setPath(data.getLocal_path());
											entity.setUid(data.getUid());
											if(data.getType().equals(17)|| data.getType().equals(5)) {
												entity.setType(1);
											} else {
												entity.setType(2);
											}
											meiUserDynamicContract.insert(entity);
											remainder++;
											if(remainder >= total || remainder > limit)
												break;
										}
										if(remainder >= total || remainder > limit)
											break;
									}
									if(remainder >= total || remainder > limit)
										break;
									System.err.println("111:"+remainder);
									for (Integer dayM : bigDays) {
										//此处说明用户可能有不超过10条的动态  需要把这不到的10条动态分配到对应的5天
										//首先随机需要发送的条数
										int num = getRandomNumber(4, 5);
										//随机发送条数对应的时间段
										List<Integer> time = createRandomList(times, num);
										//对获取到的时间值进行倒叙排序
										Collections.sort(time, comparator);
										for (Integer i : time) {
											//获取对应的时间
											int hour = getHour(i);
											int min = getRandomNumber(1, 60);
											System.err.println("222:"+remainder);
											MeiDynamicEntity data = datas.get(remainder);
											Calendar cal = Calendar.getInstance();
											cal.set(Calendar.MONTH, 11);
											cal.set(Calendar.DAY_OF_MONTH, dayM);
											cal.set(Calendar.HOUR_OF_DAY, hour);
											cal.set(Calendar.MINUTE, min);
											Date birthday = cal.getTime();
											collect.add(birthday);
											MeiUserDynamicEntity entity = new MeiUserDynamicEntity();
											entity.setContent(data.getContent());
											entity.setCreatetime(birthday);
											entity.setPath(data.getLocal_path());
											entity.setUid(data.getUid());
											if(data.getType().equals(17)|| data.getType().equals(5)) {
												entity.setType(1);
											} else {
												entity.setType(2);
											}
											meiUserDynamicContract.insert(entity);
											remainder++;
											if(remainder >= total || remainder > limit)
												break;
										}
										if(remainder >= total || remainder > limit)
											break;
									}
									
								}
							}
						}

					}
				} else {
//					for (MeiUserInfoEntity meiUserEntity : list) {
//						long uid = meiUserEntity.getUid();
//						//处理将对应的原始库中的相册添加到新库中的处理
//						PageModel page = new PageModel();
//						page.addQuery(Restrictions.eq("uid", uid));
//						page.addQuery(Restrictions.ne("path", null));
//						List<MeiPhotoEntity> list2 = meiPhotoContract.load(page);
//						if(Tools.isNotNull(list2) && list2.size() > 0) {
//							//循环将数据写入新库中
//							for (MeiPhotoEntity meiPhotoEntity : list2) {
//								if(Tools.isNotNull(meiPhotoEntity.getPath())) {
//									MeiUserPhotoEntity entity = new MeiUserPhotoEntity();
//									entity.setPath(meiPhotoEntity.getLocal_path());
//									entity.setUid(uid);
//									meiUserPhotoContract.insert(entity );
//								}
//							}
//						}
//					}
					//说明本次本能是最后一页数据了  加载完这批数据就可以退出了
					System.err.println("这里是最后一批数据了, 加载完本批数据就可以退出了");
					break;
				}
			} else {
				//没有拉取到数据所有就可以直接退出了
				System.err.println("没有更多数据了 可以退出了。");
				break;
			}
		} 
	}
	
	@Test
	public void testRandom() {
//		for(int i = 1; i < 500; i++) {
//			int num = getRandomNumber(2,3);
//			System.err.println(num);
//		}
		
		
		List<Integer> times = new ArrayList<>();
		//分配对应的时间段
		times.add(1);  // 7 8 9 
		times.add(2);  //10 11 12
		times.add(3);  //13 14 15
		times.add(4);  //16 17 18
		times.add(5);  //19 20 21
		int num = getRandomNumber(10, 12);
		List<Integer> time = createRandomList(times, num);
		//对获取到的时间值进行倒叙排序
		Comparator comparator=Collections.reverseOrder();
		Collections.sort(time, comparator);
		for (Integer i : time) {
			System.err.println(i);
		}
		
//		Calendar cal = Calendar.getInstance();
//		cal.set(Calendar.MONTH, 0);
//		cal.set(Calendar.DAY_OF_MONTH, 1);
//		cal.set(Calendar.HOUR_OF_DAY, 14);
//		cal.set(Calendar.MINUTE, 20);
//		
//		
//		Date birthday = cal.getTime();
//		System.err.println(birthday);
	}
	
	public int getHour(int time) {
		int ret = 0;
		switch (time) {
		case 1:
			ret = getRandomNumber(7, 9);
			break;
		case 2:
			ret = getRandomNumber(10, 12);
			break;
		case 3:
			ret = getRandomNumber(13, 15);
			break;
		case 4:
			ret = getRandomNumber(16, 18);
			break;
		case 5:
			ret = getRandomNumber(19, 21);
			break;
		default:
			break;
		}
		return ret;
	}
	
	public int getRandomNumber(int min, int max) {
		Random random = new Random();  
		int randomNumber =  random.nextInt(max)%(max-min+1) + min; 
		return randomNumber;
	}
	
	public List createRandomList(List list, int n) {
        Map map = new HashMap();
        List listNew = new ArrayList();
        if (list.size() <= n) {
            return list;
        } else {
            while (map.size() < n) {
                int random =getRandomNumber(0, list.size()-1);
                if (!map.containsKey(random)) {
                    map.put(random, "");
                    System.out.println(random + "===========" + list.get(random));
                    listNew.add(list.get(random));
                }
            }
            return listNew;
        }
    }
	
	public List<Integer> ListSamll(List<Integer> list, int day) {
		 List listNew = new ArrayList();
		 for (Integer i : list) {
			if(i < day) {
				listNew.add(i);
			}
		}
		return listNew;
    }
	
	public List<Integer> ListBig(List<Integer> list, int day) {
		List listNew = new ArrayList();
		 for (Integer i : list) {
			if(i >= day) {
				listNew.add(i);
			}
		}
		return listNew;
    }
    */
}
