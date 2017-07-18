package cn.ffcs.service;

import com.alibaba.fastjson.JSONObject;
import cn.ffcs.domain.SysUser;

/**
 * Created by MәӧωρaЯsε on 2017/6/17.
 *
 */
public interface AdminUserService {

    /**
     *
     * @param sysUser 用户
     * @return json
     */
    public JSONObject loginToken(SysUser sysUser);

}
