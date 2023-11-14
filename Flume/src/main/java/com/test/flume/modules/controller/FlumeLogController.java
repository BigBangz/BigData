package com.test.flume.modules.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
@RestController
@RequestMapping("/flume")
public class FlumeLogController {

    @GetMapping("")
    public ModelAndView index() {
        FlumeLog flumeLog = new FlumeLog();
        flumeLog.info("进入登陆页面");
        return new ModelAndView("index");
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<String> login() {
        log.info("登錄中");
        return ResponseEntity.ok("{\"result\": \"success\"}");
    }

}
