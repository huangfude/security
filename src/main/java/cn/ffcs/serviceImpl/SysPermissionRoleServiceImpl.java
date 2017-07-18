package cn.ffcs.serviceImpl;

import cn.ffcs.dao.SysPermissionDao;
import cn.ffcs.dao.SysPermissionRoleDao;
import cn.ffcs.domain.SysPermission;
import cn.ffcs.service.SysPermissionRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by MәӧωρaЯsε on 2017/5/5.
 *
 */
@Service
public class SysPermissionRoleServiceImpl implements SysPermissionRoleService {

    private static final Logger logger = LoggerFactory.getLogger(SysPermissionRoleServiceImpl.class);

    @Autowired
    private SysPermissionRoleDao sysPermissionRoleDao;
    @Autowired
    private SysPermissionDao sysPermissionDao;

    public void updatePermissionByRoleid(String ids,int roleId){

        sysPermissionRoleDao.saveRolePermission(ids,roleId);
    }

    @Override
    public List<SysPermission> getRolePermission(int roleId) {
        String pIds = sysPermissionRoleDao.getRolePermission(roleId);
        Set<Integer> set = new HashSet<>();

        for(String s : pIds.split(","))
            set.add(Integer.parseInt(s));
        System.out.println(Arrays.toString(set.toArray()));
        return sysPermissionDao.findByIdIn(set);
    }

}
