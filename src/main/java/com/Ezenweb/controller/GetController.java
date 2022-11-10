package com.Ezenweb.controller;

import com.Ezenweb.domain.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//p.57
@RestController
@RequestMapping("/api/v1/get-api") //요청 URL 정의하기 // JSP 로 치면 서브렛 경로 주소값 인듯
public class GetController {
    // 해당 클래스 접근[호출/요청] : http://localhost:8080/api/v1/get-api
        //해당 클래스내 메소드 호출: http://localhost:8080/api/v1/get-api/hello
    @RequestMapping( value="/hello", method = RequestMethod.GET) //요청 RequestMethod.GET = jSP로 하면 doGet 라고할수있다
    public  String getHello(){ //함수명[아무거나/ 중복x]
        return "hello 들어왓다"; // response 응답
    }
        // @RequestMapping
            //1. GetMapping
                // @RequestMapping( value="/hello", method = RequestMethod.GET) //요청
                 // @Getmapping(value="/hello")
                // @Getmapping("/hello") 20줄과 20줄 21줄 은 같다
            //2. PostMapping
            //3. PutMapping
            //4. DeleteMapping

    //2. p.58
    @GetMapping("/name")
    public  String getName(){
        return  "Flature";
    }

    //3. p.59
    // 주의: 경로상의 변수명[{variable}]과 @PathVariable 매개변수[variable]
    @GetMapping("/variable1/{variable}") // 경로상의 변수[값]
    public String getVariable1(@PathVariable String variable){
        return  variable;
    }
    //4. p.60
    @GetMapping("/variable2/{variable}")
    public  String getVariable2(@PathVariable(value ="variable")String test){
        return  test;
    }
    //@PatchMapping http://localhost:8080/api/v1/get-api/variable2/하하하하
    //VS
    //@RequestParam http://localhost:8080/api/v1/get-api/variable3?variable=헤헤헤
    //4-2. p.60
    @GetMapping("/variable3")
    public  String getVariable3(@RequestParam String variable){
        return  variable;
    }
    //5. 61
    @GetMapping("/requst1")
    public String getRequstParam1(
            @RequestParam String name , @RequestParam  String email ,@RequestParam String organization){
        return name+" "+email+ " "+organization;
        //http://localhost:8080/api/v1/get-api/requst1?name=qwe&email=qwe@qwe&organization=qweqwe
    }

    //6. p.62
    @GetMapping("/requst2")
    public  String getRequstParam2(@RequestParam Map<String , String>param){
        return  param.toString();
    }
/*
    java 컬렉션 프레임워크
        1. List : 인덱스[중복가능] , set: 인덱스x[중복불가능] , map: 인덱스x [키:값] 엔트리사용
    js
        1. JSON

*/
    //http://localhost:8080/api/v1/get-api/requst1?name=qwe&email=qwe@qwe&organization=qweqwe
    //7. p.66
    @GetMapping("/requst3")
    public  String getRequstParam3(MemberDto memberDto){
        return memberDto.toString();
    }

}
