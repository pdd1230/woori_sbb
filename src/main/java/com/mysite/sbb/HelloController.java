package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller    // HelloController --> 컨테이너
public class HelloController {

    @GetMapping("/hello") //url 매핑 --> ' hello() <-->  /hello ' , ★ class 매핑이 아닌 메소드() 매핑
    @ResponseBody      // @ResponseBody 주석처리되면 --> return "hello" --> View
    public String hi(){
        return "hello";  // 'hello.html'--> 템플릿 이용 --> 화면 + data --> 동적인 화면

    }
}