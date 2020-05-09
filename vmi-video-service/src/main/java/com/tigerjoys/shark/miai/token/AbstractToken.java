package com.tigerjoys.shark.miai.token;

import org.apache.commons.lang.StringUtils;

import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.exception.NotKeyExistsException;
import com.tigerjoys.nbs.common.utils.MD5;
import com.tigerjoys.nbs.common.utils.Tools;


/**
 * 抽象的token生成类
 * @author chengang
 *
 */
public abstract class AbstractToken implements Token {
	
	//key前缀
	protected final String 			prefixToken;
	protected final String			prefixClient;
	//key过期秒数
	protected final int 			timeout;
	//redis客户端
	protected final CacheRedis		cacheRedis;

	/**
	 * 构造
	 * @param redisClient - redis客户端
	 * @param prefix - token在redis中的前缀
	 * @param timeout - token在redis中的超时时间
	 */
	public AbstractToken(CacheRedis cacheRedis , String prefix , int timeout) {
		this.cacheRedis = cacheRedis;
		this.prefixToken = prefix+"_t_";
		this.prefixClient = prefix+"_c_";
		this.timeout = timeout;
	}
	
	@Override
	public String createToken(String id) {
		return MD5.encode(System.currentTimeMillis()+id+TokenConstants.VMI_TOKEN_KEYSECRET+timeout);
	}
	
	@Override
	public void addTokenToRedis(String id , String token) {
		//将token存储到redis中
		cacheRedis.transaction(tx -> {
			tx.setex(prefixToken+id, timeout, token);
			tx.setex(prefixClient+token, timeout, id);
		});
	}

	@Override
	public String createAndAddTokenToRedis(String id) {
		String token = createToken(id);
		addTokenToRedis(id, token);
		return token;
	}
	
	@Override
	public String getToken(String id) {
		return cacheRedis.get(prefixToken+id);
	}
	
	@Override
	public String getId(String token) {
		return cacheRedis.get(prefixClient+token);
	}
	
	@Override
	public void refreshToken(String id) throws NotKeyExistsException {
		String token = getToken(id);
		if(Tools.isNull(token)) {
			throw new NotKeyExistsException("key:"+(prefixToken+id)+" not exists!");
		}
		addTokenToRedis(id, token);
	}

	@Override
	public boolean validToken(String id, String token) {
		String t = getToken(id);
		return StringUtils.equals(t, token);
	}

	@Override
	public void removeToken(String id) {
		String token = getToken(id);
		cacheRedis.pipelined(pipeline -> {
			if(Tools.isNotNull(token)) {
				pipeline.del(prefixClient+token);
				pipeline.del(prefixToken+id);
			}
		});
	}

}
