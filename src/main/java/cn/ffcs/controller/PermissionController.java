package cn.ffcs.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import cn.ffcs.authorization.annotation.Authorization;
import cn.ffcs.domain.SysPermission;
import cn.ffcs.model.ResultModel;
import cn.ffcs.service.SysPermissionRoleService;
import com.wordnik.swagger.annotations.ApiOperation;
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
 ** Created by MeowParse on 2017/5/4.
 *
 */
@Controller
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    private SysPermissionRoleService sysPermissionRoleService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultModel> getPermission(){
        JSONObject jsonModel = new JSONObject();
        jsonModel.put("permission","hello permission");
        return new ResponseEntity<>(ResultModel.ok(jsonModel), HttpStatus.OK);
    }


    @RequestMapping(value = "/getOne", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获得角色权限")
    @Authorization
    public ResponseEntity getRolePermission(@RequestParam int roleId){

        List<SysPermission> sysPermissions = sysPermissionRoleService.getRolePermission(roleId);
        String jsonStr = JSON.toJSONString(sysPermissions);
        JSONArray jsonArray = JSONArray.parseArray(jsonStr);

        return new ResponseEntity<>(ResultModel.ok(jsonArray), HttpStatus.OK);
    }

    @RequestMapping(value = "/setOne", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "设置角色权限")
    @Authorization
    public ResponseEntity setRolePermission(@RequestParam int userid,@RequestParam String permission){

        /*List<SysPermission> sysPermissions = */sysPermissionRoleService.updatePermissionByRoleid(permission,userid);
//        String jsonStr = JSON.toJSONString(sysPermissions);
//        JSONArray jsonArray = JSONArray.parseArray(jsonStr);

        return new ResponseEntity<>(ResultModel.ok(true), HttpStatus.OK);
    }
}
