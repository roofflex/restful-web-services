package com.roofflex.restfulwebservices.controller;

import com.roofflex.restfulwebservices.model.HelloWorldBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static com.roofflex.restfulwebservices.model.HelloWorldBean.helloWorldBean;

@RestController
public class HelloWorldController {

    @GetMapping("/hello-world")
    public String getHelloWorld() {
        return "Hello World";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean getHelloWorldBean() {
        return helloWorldBean("hello");
    }

    @GetMapping("/hello-world-bean/path-variable/{name}")
    public HelloWorldBean getHelloPathVariable(@PathVariable String name) {
        return helloWorldBean(String.format("hello, %s", name));
    }
}
