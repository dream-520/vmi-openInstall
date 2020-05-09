package com.tigerjoys.shark.miai.inter.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Producer;
import org.apache.ibatis.annotations.Select;

import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.MessageTemplateEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  系统消息[t_message_template]表 dao通用操作接口实现类
 * @author chengang
 * @Date 2017-05-17 10:31:09
 *
 */
@Producer(entityType=MessageTemplateEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface MessageTemplateMapper extends BaseMapper<MessageTemplateEntity> {
    
	@Select("select * from (select * from t_message_template where status = 1 and type = 0 and send = 1 and push_crowd = 0 and publish_time >=#{create} and publish_time <=#{publish} union select * from t_message_template where status = 1 and type = 0 and send = 1 and push_crowd = 1 and publish_time >=#{create} and publish_time <= #{publish}) a order by create_time desc limit 0,21")
	public List<MessageTemplateEntity> getUserMessages(@Param("publish") String publish, @Param("create") String create);
	
	@Select("select * from (select * from t_message_template where status = 1 and type = 0 and send = 1 and push_crowd = 0 and publish_time >=#{create} and publish_time <= #{publish} union select * from t_message_template where status = 1 and type = 0 and send = 1 and push_crowd = 2 and publish_time >=#{create} and publish_time <= #{publish}) a order by create_time desc limit 0,21")
	public List<MessageTemplateEntity> getAnchorMessages(@Param("publish") String publish, @Param("create") String create);
	
}