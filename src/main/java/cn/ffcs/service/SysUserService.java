package cn.ffcs.service;

import cn.ffcs.domain.SysUser;

import javax.transaction.Transactional;

/**
 * Created by MәӧωρaЯsε on 2017/5/5.
 *
 */
public interface SysUserService {

    /**
     * 通过用户名获取用户
     * @param username 用户名
     * @return sysUser
     */
    SysUser getUserByUsername(String username);


    /**
     * 通过用户ID更新昵称
     * @param nickname 用户名
     * @param userId 用户id
     */
    @Transactional
    void updateNicknameByUserid(String nickname,Long userId);


}
