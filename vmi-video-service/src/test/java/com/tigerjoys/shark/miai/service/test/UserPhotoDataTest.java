package com.tigerjoys.shark.miai.service.test;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.inter.contract.ICopyUserContract;
import com.tigerjoys.shark.miai.inter.contract.IUserPhotoRefuseLogContract;
import com.tigerjoys.shark.miai.inter.entity.CopyUserEntity;
import com.tigerjoys.shark.miai.inter.entity.UserPhotoRefuseLogEntity;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

/**
 * 模拟生成对应的拒绝状态的头像数据
 * @author shiming
 *
 */
public class UserPhotoDataTest extends BaseTestConfig {

	@Autowired
	private ICopyUserContract copyUserContract;
	
	@Autowired
	private IUserPhotoRefuseLogContract userPhotoRefuseLogContract;
	
	@Test
	public void testCreateUserPhotoData() throws Exception {
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.sql("waiter=0 order by rand() limit 83"));
		List<CopyUserEntity> users = copyUserContract.load(pageModel);
		if(Tools.isNotNull(users)) {
			int i = 1;
			for (CopyUserEntity user : users) {
				Random ra =new Random();
				UserPhotoRefuseLogEntity t = new UserPhotoRefuseLogEntity();
				t.setUserid(user.getId());
				t.setNickname(user.getNickname());
				if(i >= 81) {
					t.setPhoto("/upload/user/photos/"+i+".png");
				} else {
					t.setPhoto("/upload/user/photos/"+i+".jpg");
				}
				//配置对应的时间值
				long rand1 = new Random().nextInt(86400);
				long rand2 = new Random().nextInt(250);
				long rand3 = rand1*rand2*1000;
				long time = System.currentTimeMillis() - rand3;
				System.err.println("时间值:"+time +" rand:"+rand3 +" current:"+System.currentTimeMillis());
				System.err.println("rand1:"+rand1 +" rand2:"+rand2+" rand3:"+rand3);
				t.setCreate_time(new Date(time));
				userPhotoRefuseLogContract.insert(t);
				i++;
			}
		}
	}
	
}
