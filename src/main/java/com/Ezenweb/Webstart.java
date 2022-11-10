package com.Ezenweb;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 스프링 웹 기본설명 [ 1.MVC 2. 내장서버 3.RESTFUL 4.웹 설정 ]
@SpringBootApplication  // 스프링 웹 위한 기본 설정 어노테이션
public class Webstart {

    public static void main(String[] args) {
                //main 스레드
        SpringApplication.run(Webstart.class);
        // 스프링 어플리케이션 실행[현재클래스명.class]

    }
}


/*
    1. extend : 상속 [ 설계도 물려받는다. 1개만가능 ]
    2. @ 어노테이션
        1. 내장 : @override : 상속 메소드 재정의할때
        2. 메타 :

*/
