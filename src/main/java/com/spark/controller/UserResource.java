package com.spark.controller;

import com.spark.common.controller.BaseController;
import com.spark.common.model.ResponseInfo;
import com.spark.model.User;
import com.spark.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 2017/7/28.
 */
@RestController
@RequestMapping("/rest")
public class UserResource extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public ResponseInfo<Map<String, Object>> list(HttpServletRequest request) {
        ResponseInfo<Map<String, Object>> responseInfo = buildSuccessRetunInfo();
        Map<String, Object> map = new HashMap<String, Object>();
        Iterable<User> userList = userService.findAll();
        map.put("userList", userList);
        responseInfo.setData(map);
        return responseInfo;
    }
}
