package com.tigerjoys.shark.miai.inter.provider;

import java.util.List;
import java.util.Map;

import com.tigerjoys.nbs.mybatis.core.EntityUtils;
import com.tigerjoys.nbs.mybatis.core.bytecode.ClassInfo;
import com.tigerjoys.nbs.mybatis.core.bytecode.JavaTypeJdbc;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;

/**
 * 业务消息的SQL组装类
 * @author liuman
 *
 */
public class BussinessMessageSqlProvider extends DefaultSqlProvider {

	public BussinessMessageSqlProvider(Class<?> entityClass) {
		super(entityClass);
	}
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String loadByUseridAndType(Map<String,Object> param) {
		ClassInfo classInfo = EntityUtils.getClassInfo(entityClass);
		
		StringBuilder builder = new StringBuilder();
		List<Integer> types = (List<Integer>) param.get("types");
		int i = 0 , j = types.size();
		for (Integer type : types) {
			builder.append("(select * from ");
			builder.append(getTablename(classInfo)).append(" ");
			builder.append("where userid = ").append("#{userId,jdbcType=").append(JavaTypeJdbc.LONG.jdbc).append("} ");
			builder.append("and type = ").append(type).append(" ");
			builder.append("order by create_time desc limit 0,1)").append(" ");
			if (++i < j) {
				builder.append("union all").append(" ");
			};
		}
		
		
		return builder.toString();
	}

}
