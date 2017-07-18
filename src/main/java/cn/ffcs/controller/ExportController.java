package cn.ffcs.controller;

import com.alibaba.fastjson.JSONObject;
import cn.ffcs.model.ResultModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MәӧωρaЯsε on 2017/5/8.
 *
 */
@RestController
@RequestMapping("/export")
public class ExportController {

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultModel> getExport(){
        JSONObject jsonModel = new JSONObject();
        jsonModel.put("export","hello export");
        return new ResponseEntity<>(ResultModel.ok(jsonModel), HttpStatus.OK);
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultModel> getTest(){

        JSONObject jsonModel = new JSONObject();
        jsonModel.put("status","导出中");
        return new ResponseEntity<>(ResultModel.ok(jsonModel), HttpStatus.OK);
    }

}
