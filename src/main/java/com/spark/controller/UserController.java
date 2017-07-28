package com.spark.controller;

import com.spark.common.controller.BaseController;
import com.spark.common.enums.EnumResponseCode;
import com.spark.common.model.ResponseInfo;
import com.spark.model.User;
import com.spark.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by User on 2017/6/9.
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public User checkLogin() {
//        return userService.save(user);
        return null;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseInfo<Map<String, Object>> requestUser(@RequestBody User user) {
        ResponseInfo<Map<String, Object>> responseInfo = buildSuccessRetunInfo();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user", userService.save(user));
        responseInfo.setData(map);
        return responseInfo;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseInfo<Map<String, Object>> login(
            @RequestBody Map<String, String> json,
            HttpSession session ) throws ServletException {
        ResponseInfo<Map<String, Object>> responseInfo;
        Map<String, Object> map = new HashMap<String, Object>();
        if(json.get("email") == null || json.get("password") == null) {
            throw new ServletException("Please fill in email and password");
        }

        String emial = json.get("email");
        String password = json.get("password");

        User user = userService.findByEmail(emial);
        if(user == null) {
//            throw new ServletException("User account not found.");
            responseInfo = buildErrorRetunInfo(EnumResponseCode.RESOURCE_NOT_FOUND);
            return responseInfo;
        }

        String pwd = user.getPassword();

        if(!password.equals(pwd)) {
//            throw new ServletException("invalid login,PLease check your account and password.");
            responseInfo = buildValidateErrorRetunInfo();
            return responseInfo;
        }
        String token = Jwts.builder().setSubject(emial).claim("roles", "user").setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secretkey").compact();
        session.setAttribute(token, user);
        map.put("token", token);
        responseInfo = buildSuccessRetunInfo();
        responseInfo.setData(map);
        return responseInfo;
    }
}
