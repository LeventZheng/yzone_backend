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
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 2017/7/28.
 */
@RestController
@RequestMapping("/rest/user")
public class UserResource extends BaseController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public ResponseInfo<Boolean> checkLogin() {
        ResponseInfo<Boolean> responseInfo = buildSuccessRetunInfo();
        responseInfo.setData(true);
        return responseInfo;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseInfo<Iterable<User>> list() {
        ResponseInfo<Iterable<User>> responseInfo = buildSuccessRetunInfo();
        Iterable<User> userList = userService.findAll();
        responseInfo.setData(userList);
        return responseInfo;
    }
}
