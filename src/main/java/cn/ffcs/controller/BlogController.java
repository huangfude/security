package cn.ffcs.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import cn.ffcs.authorization.annotation.Authorization;
import cn.ffcs.config.Constants;
import cn.ffcs.model.ResultModel;
import cn.ffcs.model.RoleModel;
import cn.ffcs.repository.SysRoleUserRepository;
import cn.ffcs.service.SysRoleUserService;
import cn.ffcs.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by MәӧωρaЯsε on 2017/5/3.
 *
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

    private static final Logger logger = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    SysUserService sysUserService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultModel> getBlog(){
        JSONObject jsonModel = new JSONObject();
        jsonModel.put("blog","hello getBlog");
        return new ResponseEntity<>(ResultModel.ok(jsonModel), HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.POST)
    @ResponseBody
    @Authorization
    public ResponseEntity<ResultModel> getList(){
        JSONObject jsonModel = new JSONObject();
        jsonModel.put("blog","hello getList");
        return new ResponseEntity<>(ResultModel.ok(jsonModel), HttpStatus.OK);
    }

    @RequestMapping(value = "/one", method = RequestMethod.POST)
    @ResponseBody
    @Authorization
    public ResponseEntity<ResultModel> getOne(){
        JSONObject jsonModel = new JSONObject();
        jsonModel.put("blog","getOne");
        return new ResponseEntity<>(ResultModel.ok(jsonModel), HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    @Authorization
    public ResponseEntity<ResultModel> update(){

        JSONObject jsonModel = new JSONObject();
        jsonModel.put("blog","update");
        return new ResponseEntity<>(ResultModel.ok(jsonModel), HttpStatus.OK);
    }

}
