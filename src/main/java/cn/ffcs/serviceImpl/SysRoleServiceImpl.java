package cn.ffcs.serviceImpl;

import cn.ffcs.dao.SysPermissionDao;
import cn.ffcs.dao.SysPermissionRoleDao;
import cn.ffcs.domain.SysRoleUser;
import cn.ffcs.model.MenuModel;
import cn.ffcs.model.PermissionModel;
import cn.ffcs.model.RoleModel;
import cn.ffcs.repository.SysPermissionRepository;
import cn.ffcs.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MәӧωρaЯsε on 2017/6/21.
 *
 */
@Service
public class SysRoleServiceImpl implements SysRoleService{

    @Autowired
    private SysPermissionRepository sysPermissionRepository;
    @Autowired
    private SysPermissionRoleDao sysPermissionRoleDao;
    @Autowired
    private SysPermissionDao sysPermissionDao;

    @Override
    public List<PermissionModel> getRolePermission(int roleId) {

        String permissionIds = sysPermissionRoleDao.getRolePermission(roleId);

        String[] permissionIdArray = permissionIds.split(",");

        List<Integer> ids = new ArrayList<>();

        for (String permissionId : permissionIdArray) {
            ids.add(Integer.parseInt(permissionId));
        }

        List<PermissionModel> permissionModels = sysPermissionRepository.findMenu();

        for(PermissionModel permissionModel:permissionModels){

            List<PermissionModel> list = getNodes(permissionModel.getId(),ids);

            if(list.isEmpty()){
                if(ids.contains(permissionModel.getId()))
                    permissionModel.setChecked(true);
            }else{
                permissionModel.setPermissionModels(list);
                for(PermissionModel permission: list){
                    permissionModel.setChecked(permission.getChecked());
                    if(!permission.getChecked()){
                        break;
                    }
                }
            }
        }
        return permissionModels;
    }

    private List<PermissionModel> getNodes(int pid,List<Integer> ids){

        List<PermissionModel> permissionModels= sysPermissionRepository.findMenu(pid);

        for(PermissionModel permissionModel:permissionModels){

            List<PermissionModel> list = getNodes(permissionModel.getId(),ids);

            if(list.isEmpty()){
                if(ids.contains(permissionModel.getId()))
                    permissionModel.setChecked(true);
            }else{
                permissionModel.setPermissionModels(list);
                for(PermissionModel permission: list){
                    permissionModel.setChecked(permission.getChecked());
                    if(!permission.getChecked()){
                        break;
                    }
                }
            }
        }
        return permissionModels;
    }
}
