package com.zos.ksdsm.controller;

import com.zos.ksdsm.service.LoginService;
import com.zos.ksdsm.util.AuthUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @program: be
 * @description: controller for login
 * @author: Yu Liu
 * @create: 2020/06/23
 **/
@CrossOrigin(origins = "*", allowCredentials = "true")
@Controller
public class LoginController {
    @Resource
    private LoginService loginService;
    @RequestMapping(value="/hello",method = RequestMethod.GET)
    public String hello(){
        return "hello!";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody Map<String, String> account, HttpSession session) {
        if (AuthUtil.notLogin(session)) {
            switch (loginService.login(account, session)) {
                case "unauthorized":
                    return ResponseEntity.status(401).body("unauthorized");
                case "time out":
                    return ResponseEntity.status(502).body("zosmf time out");
            }
        }
//        loginService.setRole(session);
        return ResponseEntity.ok("successful");
    }

    @RequestMapping(value = "/logoff", method = RequestMethod.DELETE)
    public ResponseEntity<String> logout(HttpSession session) {
//        loginService.logoff(session);
        return ResponseEntity.ok("successful");
    }


}