package cn.ffcs.serviceImpl;

import cn.ffcs.dao.SysUserDao;
import cn.ffcs.domain.SysUser;
import cn.ffcs.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by MәӧωρaЯsε on 2017/5/5.
 *
 */
@Service
public class SysUserServiceImpl implements SysUserService{

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public SysUser getUserByUsername(String username) {
        return sysUserDao.findByUsername(username);
    }

    @Override
    public void updateNicknameByUserid(String username,Long userId) {
         sysUserDao.updateNickname(username,userId);
    }

}
