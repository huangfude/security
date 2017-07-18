package cn.ffcs.authorization.interceptor;

import com.alibaba.fastjson.JSONObject;
import cn.ffcs.authorization.annotation.Authorization;
import cn.ffcs.authorization.manager.PermissionManager;
import cn.ffcs.authorization.manager.impl.RedisTokenManager;
import cn.ffcs.authorization.model.TokenModel;
import cn.ffcs.authorization.manager.TokenManager;
import cn.ffcs.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 自定义拦截器，判断此次请求是否有权限
 * @see cn.ffcs.authorization.annotation.Authorization
 * @author ScienJus
 * @date 2015/7/30.
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    @Autowired
    private TokenManager manager;
    @Autowired
    private PermissionManager permissionManager;

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        //获得请求地址和请求方法
        String url = request.getServletPath();
        String myMethod = request.getMethod();
        logger.info("url:{},method:{}",url,myMethod);
        //从header中得到token
        String authorization = request.getHeader(Constants.AUTHORIZATION);

        TokenModel model = manager.getToken(authorization);

        //验证token
        if(hasAuthorization(method)){
            if(allowUrl(url)){
                logger.info("需要鉴权");
                if (manager.checkToken(model)  ) {
                    //如果token验证成功，将token对应的用户id存在request中，便于之后注入
                    request.setAttribute(Constants.CURRENT_USER_ID, model.getUserId());

                    //验证permission验证用户是否具有操作权限
                    if(!permissionManager.checkPermission(authorization,url,myMethod)){
                        logger.info("没有权限");
                        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        sendResponse(response,HttpServletResponse.SC_FORBIDDEN);
                        return false;
                    }
                    logger.info("权限验证成功");
                    return true;
                }else{
                    logger.info("有注解 && token 验证失败");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    sendResponse(response,HttpServletResponse.SC_UNAUTHORIZED);
                    return false;
                }
            }
            request.setAttribute(Constants.CURRENT_USER_ID, model.getUserId());
        }

//        logger.info("checkToken:false");
//        //如果验证token失败，并且方法注明了Authorization，返回401错误
////        return hasAuthorization(method,response);
        return true;
    }


    private void sendResponse(HttpServletResponse response,int code) throws IOException {
        response.setStatus(code);
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        JSONObject result = new JSONObject();
        String message;
        result.put("code",code);
       switch (code){
           case HttpServletResponse.SC_UNAUTHORIZED:
               message = "token过期";
               break;
           case HttpServletResponse.SC_FORBIDDEN:
               message = "没有权限";
               break;
           default:
               message = "denied";
       }
        result.put("message",message);
        result.put("content","");
        response.getOutputStream()
                .write((result.toString()).getBytes());
    }

    private boolean allowUrl(String url){
        return !Constants.ALLOWURL.contains(url);
    }

    private boolean hasAuthorization(Method method)  {
        return !(method.getAnnotation(Authorization.class) == null);
    }
}
