package com.tigerjoys.shark.miai.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.shark.miai.common.annotation.ForbidDialAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.ModelAndView;

import com.tigerjoys.nbs.common.ErrorCodeHolder;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.web.annotations.Login;
import com.tigerjoys.nbs.web.annotations.NoLogin;
import com.tigerjoys.nbs.web.context.BeatContext;
import com.tigerjoys.nbs.web.context.RequestHeader;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.nbs.web.interceptors.ControllerInterceptor;
import com.tigerjoys.nbs.web.utils.PageLoadClassUtil;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.IAnchorOnlineStateAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserOnlineListAgent;
import com.tigerjoys.shark.miai.agent.IUserOrdinaryOnlineListAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.enums.UserStatusEnum;
import com.tigerjoys.shark.miai.annotations.WaiterActionOnline;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;

/**
 * web拦截器
 * @author chengang
 *
 */
public class WebControllerInterceptor extends ControllerInterceptor {
	
	/**
	 * 记录接口调用完毕的执行时间
	 */
	private static ThreadLocal<Long> executiveTime = new ThreadLocal<Long>();
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IUserOnlineListAgent userOnlineListAgent;
	
	@Autowired
	private IAnchorOnlineStateAgent anchorOnlineStateAgent;
	
	@Autowired
	private IUserOrdinaryOnlineListAgent userOrdinaryOnlineListAgent;
	
	
	
	/**
	 * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
	 *
	 * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
	 * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(Const.IS_TEST) {
			executiveTime.set(System.currentTimeMillis());
		}
		
		//logger.info("WebControllerInterceptor.preHandle:"+request.getRequestURI()+","+request.getHeader("Header-Encrypt-Code"));
		
		ErrorCodeHolder errorCodeHolder = new ErrorCodeHolder();
		return super.preHandle(request, response, handler, errorCodeHolder , Const.IS_TEST , (method , clazz) -> {
			BeatContext context = RequestUtils.getCurrent();
			RequestHeader header = context.getHeader();
			//用户ID
			long userid = header.getUserid();
			//设备ID
			String clientId = header.getClientId();
			
			if(userid != 0 && !method.isAnnotationPresent(NoLogin.class)) {
				//此处需要刷新用户在线列表信息以及判断是否是登录了相同设备等...
				//此处查看用户是否在其他设备上登录了
				String onlineClientId = userOnlineListAgent.getOnlineUserClientId(userid);
				if(onlineClientId == null) {
					userOnlineListAgent.addOnlineUser(userid, clientId);
				} else if (!clientId.equals(onlineClientId)){
					errorCodeHolder.setErrorCode(ErrorCodeEnum.alreay_other_device);
					return false;
				}
				
				//必须要UserClientService注解的方法才用来刷新用户的点击,顺便刷新一下登录列表
				if(context.isUserClick()){
					//刷新一下登录/以及刷新在线时间列表
					userOnlineListAgent.refreshOnlineUser(userid , clientId);
					anchorOnlineStateAgent.refreshOnlineAnchor(userid);
				}
			}
			
			//此处需要验证是否登录了
			boolean isNeedLogin = (AnnotationUtils.findAnnotation(method , Login.class) != null || (AnnotationUtils.findAnnotation(clazz, Login.class) !=null && AnnotationUtils.findAnnotation(method, NoLogin.class)==null));
			if(isNeedLogin) {
				try {
					if(userid == 0) {
						errorCodeHolder.setErrorCode(ErrorCodeEnum.user_isnull);
						return false;
					}
					//此处验证用户信息
					UserBO user = userAgent.findById(userid);
					if(user == null) {
						errorCodeHolder.setErrorCode(ErrorCodeEnum.user_isnull);
						return false;
					}
					if(user.getStatus() == UserStatusEnum.disable.getCode()) {
						errorCodeHolder.setErrorCode(ErrorCodeEnum.user_freeze);
						return false;
					}
					//此处要验证token是否一致
					if(!user.getUniqueKey().equals(header.getToken())) {
						errorCodeHolder.setErrorCode(ErrorCodeEnum.token_expire_error);
						return false;
					}
					//此处验证token是否已超过7天有效期
					//logger.info(System.currentTimeMillis()+"---" + user.getLastLoginDate().getTime());
					if(System.currentTimeMillis() - user.getLastLoginDate().getTime() > Const.USER_TOKEN_EXPIRE_MILLIS) {
						errorCodeHolder.setErrorCode(ErrorCodeEnum.token_expire_error);
						return false;
					}
					
					if(user.isWaiter()) {
						//根据注解检测主播的动作行为
						boolean action = AnnotationUtils.findAnnotation(method, WaiterActionOnline.class) != null;
						if(action) {
							//进行数据入库操作处理
							anchorOnlineStateAgent.waiterActionOnline(user.getUserid());
						}
					}
					
					if(!user.isWaiter()) {
						//根据注解检测用户动作，禁用主播拨打
						ForbidDialAnnotation forbidDial = AnnotationUtils.findAnnotation(method, ForbidDialAnnotation.class) ;
						if(Tools.isNotNull(forbidDial)) {
							if(forbidDial.value()>0){
								userOrdinaryOnlineListAgent.setAnchorForbidDialFlag(userid,forbidDial.value());
							}
						}
					}
					
					
					
					context.setUser(user);
				} catch (Exception e) {
					logger.error("判断登录用户信息发生异常", e);
					errorCodeHolder.setErrorCode(ErrorCodeEnum.valid_login_user_error);
					return false;
				}
			}
			
			return true;
		});
	}
	
	/**
	 * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
	 */
	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		
		//加载类
		PageLoadClassUtil.setVelocityContext("Const", Const.class, modelAndView);
		PageLoadClassUtil.setVelocityContext("Tools", Tools.class, modelAndView);
		
		if(Const.IS_TEST) {
			long t = System.currentTimeMillis() - executiveTime.get().longValue();
			executiveTime.remove();
			logger.info("接口 "+request.getRequestURI()+" 执行耗时："+t+"毫秒。");
		}
	}
	
	
	

}