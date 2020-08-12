package com.ringo.restfulcrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author ringo
 * @version 1.0
 * @date 2020/8/9 15:02
 */
@Controller
public class HelloController {

//    @RequestMapping({"/", "/index.html"})
//    @RequestMapping("/")
//    public String index(){
//        return "index";
//    }

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/success")
    public String success(Map<String, Object> map){
        map.put("hello", "<h1>你好</h1>");
        return "success";
    }
}
