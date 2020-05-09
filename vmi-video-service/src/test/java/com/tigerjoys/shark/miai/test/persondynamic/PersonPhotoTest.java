package com.tigerjoys.shark.miai.test.persondynamic;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.alibaba.fastjson.JSONArray;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.shark.miai.dto.service.PhotoPictureDto;
import com.tigerjoys.shark.miai.inter.contract.IUserPhotoResourceContract;
import com.tigerjoys.shark.miai.inter.entity.UserPhotoResourceEntity;
import com.tigerjoys.shark.miai.service.impl.PhotoServiceImpl;
import com.tigerjoys.shark.miai.utils.BaseMockitoTestCase;

/**
 * 对个人相册功能的单元测试
 * @author shiming
 */
public class PersonPhotoTest extends BaseMockitoTestCase {

	@InjectMocks
	private PhotoServiceImpl photoServiceImpl;
	
	@Mock
	private IUserPhotoResourceContract userPhotoResourceContract;
	
	private List<UserPhotoResourceEntity> listlt;
	
	private List<UserPhotoResourceEntity> listlg;
	
	private List<String> paths;
	
	private UserPhotoResourceEntity entity;
	
	@Before
	public void before() throws Exception{
		listlt = new ArrayList<>();
		listlg = new ArrayList<>();
		paths = new ArrayList<>();
		UserPhotoResourceEntity entity = null;
		//创建对应的测试数据
		for(int i=0; i < 6; i++){
			entity = new UserPhotoResourceEntity();
			entity.setId((long) i);
			entity.setPath("/picture/photo/"+i+".png");
			entity.setUserid(121L);
			entity.setState(1);
			listlt.add(entity);
		}
		
		for(int i=0; i < 11; i++){
			entity = new UserPhotoResourceEntity();
			entity.setId((long) i);
			entity.setPath("/picture/photo/"+i+".png");
			entity.setUserid(121L);
			entity.setState(1);
			listlg.add(entity);
		}
		
		//插入5张相册图片
		for(int i=0; i < 5; i++){
			paths.add("/picture/photo/"+i+".png");
		}
		
		entity = new UserPhotoResourceEntity();
		entity.setId(1001L);
		entity.setUserid(1001L);
		entity.setState(1);
	}
	
	@Test
	public void addPhotoPicture() throws Exception{
		logger.info("测试添加正常的测试用例--->开始");
		List<String> paths = new ArrayList<>();
		ActionResult result = photoServiceImpl.addPhotoPicture(10001, paths);
		System.err.println(JsonHelper.toJson(result));		
		assertNotNull(result);
		logger.info("测试添加正常的测试用例--->结束");
	}
	
	@Test
	public void addPhotoPictureThrow() throws Exception{
		logger.info("测试添加异常的测试用例--->开始");
		doThrow(new RuntimeException("test excpetion")).when(userPhotoResourceContract).insertAll(any());
		List<String> paths = new ArrayList<>();
		try{
			ActionResult result = photoServiceImpl.addPhotoPicture(10001, paths);
		} catch (Exception e) {
			assertNotNull(e);
		} finally{
			logger.info("测试添加异常的测试用例--->结束");
		}
	}
	
	@Test
	public void getPhotoListNull() throws Exception{
		/*测试获取对应的相册数据为空的测试*/
		logger.info("测试没有找到对应数据的测试用例--->开始");
		when(userPhotoResourceContract.load(any(PageModel.class))).thenReturn(null);
		List<PhotoPictureDto> list = photoServiceImpl.getPhotoList(0, 0, 0);
		assertNotNull(list);
		assertTrue("测试获取到数据为空的测试用例", list.size()==0);
		logger.info("测试没有找到对应数据的测试用例--->结束");
	}
	
	@Test
	public void getPhotoListlt10() throws Exception{
		/*测试获取对应的相册数据为空的测试*/
		logger.info("测试不满足一页的测试用例--->开始");
		when(userPhotoResourceContract.load(any(PageModel.class))).thenReturn(listlt);
		List<PhotoPictureDto> list = photoServiceImpl.getPhotoList(0, 0, 0);
		assertNotNull(list);
		assertTrue("测试获取到数据为对应条数的测试用例", list.size()==6);
		logger.info("测试不满足一页的测试用例--->结束");
	}
	
	@Test
	public void getPhotoListlg10() throws Exception{
		/*测试获取对应的相册数据为空的测试*/
		logger.info("测试满足一页的测试用例--->开始");
		when(userPhotoResourceContract.load(any(PageModel.class))).thenReturn(listlg);
		List<PhotoPictureDto> list = photoServiceImpl.getPhotoList(0, 0, 0);
		assertNotNull(list);
		assertTrue("测试获取到数据为超过一页的测试用例", list.size()==11);
		logger.info("测试满足一页的测试用例--->结束");
	}
	
	@Test
	public void deletePhotobyId() throws Exception{
		logger.info("测试正常删除数据的测试用例--->开始");
		when(userPhotoResourceContract.findById(anyLong())).thenReturn(entity);
		when(userPhotoResourceContract.update(any())).thenReturn(1);
		JSONArray arr = new JSONArray();
		arr.add(1);
		arr.add(2);
		ActionResult result = photoServiceImpl.deletePhotobyId(1001L, arr);
		System.err.println(JsonHelper.toJson(result));		
		assertNotNull(result);
		logger.info("测试正常删除数据的测试用例--->结束");
	}
	
}
