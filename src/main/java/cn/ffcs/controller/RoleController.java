package cn.ffcs.controller;

import com.alibaba.fastjson.JSONObject;
import cn.ffcs.annotation.ULog;
import cn.ffcs.authorization.annotation.Authorization;
import cn.ffcs.dao.SysPermissionRoleDao;
import cn.ffcs.model.PermissionModel;
import cn.ffcs.model.ResultModel;
import cn.ffcs.repository.SysPermissionRepository;
import cn.ffcs.service.SysRoleService;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by MәӧωρaЯsε on 2017/6/19.
 *
 */
@Controller
@RequestMapping("role")
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultModel> getRole(){
        JSONObject jsonModel = new JSONObject();
        jsonModel.put("role","hello role");
        return new ResponseEntity<>(ResultModel.ok(jsonModel), HttpStatus.OK);
    }


    @RequestMapping(value = "/getRole", method = RequestMethod.POST)
    @ResponseBody
    @Authorization
    @ApiOperation(value = "获得角色权限")
    public ResponseEntity getRolePermission(@RequestParam int roleId){

        int cRoleId = 1;

        List<PermissionModel> permissionModels = sysRoleService.getRolePermission(roleId);

        return new ResponseEntity<>(ResultModel.ok(permissionModels), HttpStatus.OK);
    }

//        @RequestMapping(value = "/setRole", method = RequestMethod.POST)
//        @ResponseBody
//        @Authorization
//        public ResponseEntity setRolePermission(@RequestParam Long userid,@RequestParam String permission){
//
//        /*List<SysPermission> sysPermissions = */sysPermissionRoleService.updatePermissionByRoleid(permission,userid);
////        String jsonStr = JSON.toJSONString(sysPermissions);
////        JSONArray jsonArray = JSONArray.parseArray(jsonStr);
//
//            return new ResponseEntity<>(ResultModel.ok(true), HttpStatus.OK);
//        }
}
