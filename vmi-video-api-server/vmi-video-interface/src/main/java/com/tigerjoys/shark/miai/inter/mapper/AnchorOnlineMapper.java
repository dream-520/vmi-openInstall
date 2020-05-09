package com.tigerjoys.shark.miai.inter.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Producer;
import org.apache.ibatis.annotations.Select;

import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  [t_anchor_online]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2018-09-13 16:06:54
 *
 */
@Producer(entityType=AnchorOnlineEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AnchorOnlineMapper extends BaseMapper<AnchorOnlineEntity> {
    
	@Select("select * from t_anchor_online WHERE state=1 and online=3 and userid in(select friendid from t_user_friends where userid=#{userid}) and update_time<#{update_time} ORDER BY online desc, update_time desc limit 0,21")
	public List<AnchorOnlineEntity> attentionAnchors(@Param("userid") String userid, @Param("update_time") String update_time);
	
	@Select("select * from t_anchor_online WHERE state=1 and userid in(select friendid from t_user_friends where userid=#{userid} and create_time<#{update_time}) and update_time<#{update_time} ORDER BY online desc, star asc, id desc limit #{start},20")
	public List<AnchorOnlineEntity> newAttentionAnchors(@Param("userid") String userid, @Param("start") int start, @Param("update_time") String update_time);
	
	@Select("select * from t_anchor_online WHERE state=1 and userid in(select friendid from t_user_friends where userid=#{userid} and create_time<#{update_time}) and update_time<#{update_time} ORDER BY online desc, star desc, id desc limit #{start},20")
	public List<AnchorOnlineEntity> newAttentionAnchorsTwo(@Param("userid") String userid, @Param("start") int start, @Param("update_time") String update_time);
	
}