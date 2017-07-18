package cn.ffcs.controller;

import com.alibaba.fastjson.JSONObject;
import cn.ffcs.annotation.ULog;
import cn.ffcs.authorization.annotation.Authorization;
import cn.ffcs.authorization.annotation.CurrentUser;
import cn.ffcs.authorization.manager.TokenManager;
import cn.ffcs.config.ResultStatus;
import cn.ffcs.domain.SysUser;
import cn.ffcs.model.ResultModel;
import cn.ffcs.service.AdminUserService;
import cn.ffcs.service.SysUserService;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 获取和删除token的请求地址，在Restful设计中其实就对应着登录和退出登录的资源映射
 * @author ScienJus
 * @date 2015/7/30.
 */
@RestController
@RequestMapping("/tokens")
public class TokenController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private AdminUserService adminUserService;

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "登录")
    @ULog
    public ResponseEntity<ResultModel> login(@RequestParam String username, @RequestParam String password) {
        Assert.notNull(username, "username can not be empty");
        Assert.notNull(password, "password can not be empty");

        SysUser sysUser = sysUserService.getUserByUsername(username);

        if (sysUser == null ||  //未注册
                !sysUser.getPassword().equals(password)) {  //密码错误
            //提示用户名或密码错误
            return new ResponseEntity<>(ResultModel.error(ResultStatus.USERNAME_OR_PASSWORD_ERROR), HttpStatus.NOT_FOUND);
        }

        //拼装json
        JSONObject jsonModel = adminUserService.loginToken(sysUser);

        return new ResponseEntity<>(ResultModel.ok(jsonModel), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @Authorization
    @ApiOperation(value = "退出登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "authorization", required = true, dataType = "string", paramType = "header"),
    })
    public ResponseEntity<ResultModel> logout(@CurrentUser SysUser sysUser) {
        tokenManager.deleteToken(Integer.parseInt(sysUser.getId()+""));
        return new ResponseEntity<>(ResultModel.ok(), HttpStatus.OK);
    }

}
