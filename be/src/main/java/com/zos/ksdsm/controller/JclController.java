package com.zos.ksdsm.controller;

import com.zos.ksdsm.domain.JobOutputListItem;
import com.zos.ksdsm.service.JclService;
import com.zos.ksdsm.util.AuthUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @program: be
 * @description: controller for jcl submit
 * @author: Yu Liu
 * @create: 2020/06/27
 **/
@CrossOrigin(origins = "*", allowCredentials = "true")
@Controller
public class JclController {

    @Resource
    private JclService js;

    /**
     * submit a JCL job and get response
     */

    @RequestMapping(value = "/jcl", method = RequestMethod.POST)
    public ResponseEntity<List<JobOutputListItem>> submitJCL(@RequestBody Map<String, String> body, HttpSession session) {
        if (AuthUtil.notLogin(session)) {
            return ResponseEntity.status(401).body(null);
        }
        List<JobOutputListItem> res;
        if ((res = js.submitJCL(session, body.get("jcl"))) != null) {
            return ResponseEntity.ok(res);
        } else {
            // time out
            return ResponseEntity.status(202).body(null);
        }
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<List<JobOutputListItem>> submitAddJCL(@RequestBody Map<String, String> body, HttpSession session) {
        if (AuthUtil.notLogin(session)) {
            return ResponseEntity.status(401).body(null);
        }
        List<JobOutputListItem> res;
        if ((res = js.submitAddJCL(session, body.get("name"))) != null) {
            return ResponseEntity.ok(res);
        } else {
            // time out
            return ResponseEntity.status(202).body(null);
        }
    }
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<List<JobOutputListItem>> submitDeleteJCL(@RequestBody Map<String, String> body, HttpSession session) {
        if (AuthUtil.notLogin(session)) {
            return ResponseEntity.status(401).body(null);
        }
        List<JobOutputListItem> res;
        if ((res = js.submitDeleteJCL(session, body.get("name"))) != null) {
            return ResponseEntity.ok(res);
        } else {
            // time out
            return ResponseEntity.status(202).body(null);
        }
    }
}
