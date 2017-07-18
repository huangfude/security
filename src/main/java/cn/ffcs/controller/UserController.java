package cn.ffcs.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import cn.ffcs.authorization.annotation.Authorization;
import cn.ffcs.config.Constants;
import cn.ffcs.model.ResultModel;
import cn.ffcs.model.RoleModel;
import cn.ffcs.service.SysRoleUserService;
import cn.ffcs.service.SysUserService;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by MәӧωρaЯsε on 2017/6/20.
 *
 */
@Controller
@RequestMapping("user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private SysRoleUserService sysRoleUserService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultModel> getUser(){
        JSONObject jsonModel = new JSONObject();
        jsonModel.put("user","hello user");
        return new ResponseEntity<>(ResultModel.ok(jsonModel), HttpStatus.OK);
    }

    @RequestMapping(value = "/getRoles", method = RequestMethod.POST)
    @ApiOperation(value = "获得用户拥有角色")
    @ResponseBody
    @Authorization
    public ResponseEntity getRolePermission(@RequestParam int userid){

        int currentUserID = (Integer) request.getAttribute(Constants.CURRENT_USER_ID);

        List<RoleModel> roleModels = sysRoleUserService.getUserRoles(userid,currentUserID);

        logger.info("current_user_id:{}",currentUserID);
        String jsonStr = JSON.toJSONString(roleModels);
        JSONArray jsonArray = JSONArray.parseArray(jsonStr);

        return new ResponseEntity<>(ResultModel.ok(jsonArray), HttpStatus.OK);
    }

    @RequestMapping(value = "/setRoles", method = RequestMethod.POST)
    @ApiOperation(value = "设置用户角色")
    @ResponseBody
    @Authorization
    public ResponseEntity setRolePermission(@RequestParam int userid,@RequestParam String roles){

        int currentUserID = (Integer) request.getAttribute(Constants.CURRENT_USER_ID);

        List<RoleModel> roleModels = sysRoleUserService.updateRoleUser(roles,userid,currentUserID);

        String jsonStr = JSON.toJSONString(roleModels);
        JSONArray jsonArray = JSONArray.parseArray(jsonStr);

        return new ResponseEntity<>(ResultModel.ok(jsonArray), HttpStatus.OK);
    }

    @RequestMapping(value = "/updateNickname", method = RequestMethod.POST)
    @ApiOperation(value = "设置用户昵称")
    @ResponseBody
    @Authorization
    public ResponseEntity<ResultModel> updateNickname(@RequestParam String nickname, @RequestParam Long userid){

        sysUserService.updateNicknameByUserid(nickname, userid);

        JSONObject jsonModel = new JSONObject();
        jsonModel.put("number",1);

        return new ResponseEntity<>(ResultModel.ok(jsonModel), HttpStatus.OK);
    }

}
