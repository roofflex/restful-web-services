package com.roofflex.restfulwebservices.controller;

import com.roofflex.restfulwebservices.model.HelloWorldBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

import static com.roofflex.restfulwebservices.model.HelloWorldBean.helloWorldBean;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

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

    @GetMapping("/hello-world-internationalized")
    public String getHelloWorldInternationalized() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default message", locale);
    }
}
