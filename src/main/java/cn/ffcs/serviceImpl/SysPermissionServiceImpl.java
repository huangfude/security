package cn.ffcs.serviceImpl;

import cn.ffcs.dao.SysPermissionDao;
import cn.ffcs.domain.SysPermission;
import cn.ffcs.model.PermissionModel;
import cn.ffcs.service.SysPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by MәӧωρaЯsε on 2017/5/5.
 *
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService{

    private static final Logger logger = LoggerFactory.getLogger(SysPermissionServiceImpl.class);

    @Autowired
    SysPermissionDao sysPermissionDao;

    @Override
    public List<SysPermission> getPermissionByUserid(long id){
        String pIds = sysPermissionDao.findAllById(id);
        System.out.println(pIds);
        Set<Integer> set = new HashSet<>();

        for(String s : pIds.split(","))
            set.add(Integer.parseInt(s));
        System.out.println(Arrays.toString(set.toArray()));
        logger.info(Arrays.toString(sysPermissionDao.findByIdIn(set).toArray()));
        return sysPermissionDao.findByIdIn(set);
    }

    @Override
    public List<Integer> findAllById(long id) {
        String pIds = sysPermissionDao.findAllById(id);
        List<Integer> list = new ArrayList<>();
        for(String s : pIds.split(","))
            list.add(Integer.parseInt(s));
        return list;
    }

    @Override
    public Map<Integer, PermissionModel> pListToMap(List<PermissionModel> permissionModels) {
        Map<Integer,PermissionModel> permissionModelMap = new HashMap<>();
        for (PermissionModel permissionModel : permissionModels){
            permissionModelMap.put(permissionModel.getId(),permissionModel);
        }

        return permissionModelMap;
    }

}
