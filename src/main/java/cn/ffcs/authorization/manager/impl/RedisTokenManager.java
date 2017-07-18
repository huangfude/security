package cn.ffcs.authorization.manager.impl;

import cn.ffcs.authorization.manager.TokenManager;
import cn.ffcs.authorization.model.TokenModel;
import cn.ffcs.config.Constants;
import cn.ffcs.controller.BlogController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 通过Redis存储和验证token的实现类
 * @see cn.ffcs.authorization.manager.TokenManager
 * @author ScienJus
 * @date 2015/7/31.
 */
@Component
public class RedisTokenManager implements TokenManager {

    private static final Logger logger = LoggerFactory.getLogger(RedisTokenManager.class);

    private RedisTemplate<Integer, String> redis;

    @Autowired
    @Resource(name="redisTemplate")
    public void setRedis(RedisTemplate<Integer, String> redis) {
        //泛型设置成Long后必须更改对应的序列化方案
        redis.setKeySerializer(new JdkSerializationRedisSerializer());
//        redis.setValueSerializer(new JdkSerializationRedisSerializer());
//        redis.setHashKeySerializer(new JdkSerializationRedisSerializer());
//        redis.setHashValueSerializer(new JdkSerializationRedisSerializer());
        this.redis = redis;

    }

    public TokenModel createToken(int userId) {
        //使用uuid作为源token
        String token = UUID.randomUUID().toString().replace("-", "");
        TokenModel model = new TokenModel((long) userId, token);
        //存储到redis并设置过期时间
        redis.boundValueOps(userId).set(token, Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return model;
    }

    //1_296d9be105134263889e5355c447f284
    public TokenModel getToken(String authentication) {
        if (authentication == null || authentication.length() == 0) {
            return null;
        }
        String[] param = authentication.split("_");
        if (param.length != 2) {
            return null;
        }
        //使用userId和源token简单拼接成的token，可以增加加密措施
        int userId = Integer.parseInt(param[0]);
        String token = param[1];
        return new TokenModel((long) userId, token);
    }

    public String getUserToken(int userId){

        return redis.boundValueOps(userId).get();
    }

    public boolean checkToken(TokenModel model) {
        if (model == null) {
            return false;
        }
        System.err.println("getUserId:"+model.getUserId());
        String token = redis.boundValueOps(model.getUserId().intValue()).get();
        logger.info("token:{}",token);
        if (token == null || !token.equals(model.getToken())) {
            logger.info("checkToken:false");
            return false;
        }
        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        redis.boundValueOps(model.getUserId().intValue()).expire(Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return true;
    }

    public void deleteToken(int userId) {
        redis.delete(userId);
    }
}
