package com.spark.controller;

import com.spark.common.controller.BaseController;
import com.spark.common.model.ResponseInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by User on 2017/8/20.
 */
@RestController
@RequestMapping(value = "/rest/reptilia")
public class ReptiliaResource extends BaseController {

//    @RequestMapping(value = "/getByUser", method = RequestMethod.POST)
//    public ResponseInfo<Boolean> checkLogin() {
//        ResponseInfo<Boolean> responseInfo = buildSuccessRetunInfo();
//        responseInfo.setData(true);
//        return responseInfo;
//    }
}
