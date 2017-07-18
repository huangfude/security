package cn.ffcs.serviceImpl;

import cn.ffcs.authorization.manager.PermissionManager;
import cn.ffcs.dao.SysRoleUserDao;
import cn.ffcs.domain.SysRoleUser;
import cn.ffcs.model.RoleModel;
import cn.ffcs.repository.SysRoleUserRepository;
import cn.ffcs.service.SysRoleUserService;
import org.aspectj.apache.bcel.classfile.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/**
 * Created by MәӧωρaЯsε on 2017/5/5.
 *
 */
@Service
public class SysRoleUserServiceImpl implements SysRoleUserService{

    private static final Logger logger = LoggerFactory.getLogger(SysRoleUserServiceImpl.class);

    @Autowired
    private SysRoleUserDao sysRoleUserDao;
    @Autowired
    private SysRoleUserRepository sysRoleUserRepository;
    @Autowired
    private SysRoleUserService sysRoleUserService;

    @Override
    public void deleteByUserid(int userid){
        sysRoleUserDao.deleteByUserId(userid);
    }

    @Override
    @Transactional
    public List<RoleModel> updateRoleUser(String roleIds, int userId, int currentUserId) {

        //根据当前用户删除所选角色所拥有的角色,不删除当前用户不具有的角色
        sysRoleUserDao.deleteByUserIdAndRoleIds(userId,currentUserId);

        List<Integer> integers = sysRoleUserDao.findOneByRoleAndUser(currentUserId);

        String[] roleIdArray = roleIds.split(",");
        roleIdArray = unique(roleIdArray);

        for (String aRoleIdArray : roleIdArray) {
            int roleId = Integer.parseInt(aRoleIdArray);
            if(integers.contains(roleId)){
                sysRoleUserDao.save(new SysRoleUser(roleId, userId));
            }
        }

        return sysRoleUserService.getUserRoles(userId,currentUserId);
    }

    @Override
    public List<RoleModel> getUserRoles(int userId,int cUserId) {

        logger.info("获得用户角色");
        List<RoleModel> sysRoles =  sysRoleUserRepository.getRoleByUserId(userId);
        logger.info("获取当前用户角色");
        List<RoleModel> allSysRoles = sysRoleUserRepository.getRoleByUserId(cUserId);

        for (RoleModel allSysRole : allSysRoles) {
            for (RoleModel sysRole : sysRoles) {
                if (allSysRole.equals(sysRole)) {
                    allSysRole.setChecked(true);
                }
            }
        }

        return allSysRoles;
    }

    //去重
    private String[] unique(String[] arrs){
        Set<String> set = new HashSet<>();
        String[] strArr = new String[0];
        Collections.addAll(set, arrs);
        return set.toArray(strArr);
    }

}
