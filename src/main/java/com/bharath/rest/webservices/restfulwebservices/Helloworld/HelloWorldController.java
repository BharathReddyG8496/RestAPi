package com.bharath.rest.webservices.restfulwebservices.Helloworld;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HelloWorldController {

    @GetMapping(value = "/hello-world")
    public String returnHello(){
        return "Hello World";
    }

    @GetMapping(value = "/hello-world-bean")
    public HelloWorld returnHelloBean(){
        return new HelloWorld("Hello world");
    }
    @RequestMapping("/hello-bean-list")
    public List<HelloWorld> helloWorldList(){
        return List.of(new HelloWorld("hello"), new HelloWorld("world"), new HelloWorld("How are you"));

    }

    @RequestMapping("/hello-bean/parameters/{name}")
    public HelloWorld helloWorldParameters(@PathVariable String name){
        return new HelloWorld("Parameter is " + name);

    }
}
