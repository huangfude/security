package cn.ffcs.authorization.manager.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import cn.ffcs.authorization.manager.PermissionManager;
import cn.ffcs.authorization.model.PermissionModel;
import cn.ffcs.config.Constants;
import cn.ffcs.domain.SysPermission;
import cn.ffcs.repository.SysRoleUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * Created by MәӧωρaЯsε on 2017/5/3.
 */
@Component
public class RedisPermissionManager implements PermissionManager{
    private static final Logger logger = LoggerFactory.getLogger(PermissionManager.class);

    private RedisTemplate<String, String> redis;

    @Autowired
    @Resource(name="redisTemplate")
    public void setRedis(RedisTemplate<String, String> redis) {
        //泛型设置成Long后必须更改对应的序列化方案
        redis.setKeySerializer(new JdkSerializationRedisSerializer());
//        redis.setValueSerializer(new JdkSerializationRedisSerializer());
//        redis.setHashKeySerializer(new StringRedisSerializer());
//        redis.setHashValueSerializer(new JdkSerializationRedisSerializer());
        this.redis = redis;

    }

    @Override
    public PermissionModel createPermission(String token, String sysPermissions) {

        PermissionModel model = new PermissionModel(token, sysPermissions);
        redis.opsForValue().set(token,sysPermissions, Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);

        return model;
    }

    @Override
    public boolean checkPermission(String token,String url,String method) {

        if (token == null || url == null) {
            return false;
        }

        String[] param = token.split("_");
        if (param.length != 2) {
            return false;
        }
        //使用userId和源token简单拼接成的token，可以增加加密措施
        token = param[1];

        String sysPermissions = redis.boundValueOps(token).get();
        if (sysPermissions == null ) {
            return false;
        }

        List<SysPermission> listSysPermission = JSON.parseArray(sysPermissions,SysPermission.class);
        for(SysPermission sysPermission:listSysPermission){
            if(sysPermission.getUrl().equals(url) && sysPermission.getMethod().equals(method)){
                return true;
            }
        }
        return false;
    }

    @Override
    public PermissionModel getPermission(String authentication) {
        return null;
    }

    @Override
    public void deletePermission(String token) {
        redis.delete(token);
    }
}
