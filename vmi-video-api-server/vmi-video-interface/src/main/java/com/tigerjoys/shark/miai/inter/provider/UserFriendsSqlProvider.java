package com.tigerjoys.shark.miai.inter.provider;

import com.tigerjoys.nbs.mybatis.core.EntityUtils;
import com.tigerjoys.nbs.mybatis.core.bytecode.ClassInfo;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;

/**
 * 用户的SQL组装类
 * @author chengang
 *
 */
public class UserFriendsSqlProvider extends DefaultSqlProvider {

	public UserFriendsSqlProvider(Class<?> entityClass) {
		super(entityClass);
	}
	
	/**
	 * 加载用户ID集合
	 * @param pageModel - PageModel
	 * @return String
	 */
	public String loadUserIds(PageModel pageModel) {
		ClassInfo classInfo = EntityUtils.getClassInfo(entityClass);
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("select userid from ");
		builder.append(getTablename(classInfo)).append(" ");
		
		builderWhere(this , builder , pageModel);
		builderOrderBy(this , builder , pageModel);
		builderLimit(this , builder , pageModel);
		
		return builder.toString();
	}
	
	/**
	 * 加载好友ID集合
	 * @param pageModel - PageModel
	 * @return String
	 */
	public String loadFriendIds(PageModel pageModel) {
		ClassInfo classInfo = EntityUtils.getClassInfo(entityClass);
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("select friendid from ");
		builder.append(getTablename(classInfo)).append(" ");
		
		builderWhere(this , builder , pageModel);
		builderOrderBy(this , builder , pageModel);
		builderLimit(this , builder , pageModel);
		
		return builder.toString();
	}

}
