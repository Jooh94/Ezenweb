package com.Ezenweb.controller;


import com.Ezenweb.domain.dto.BoardDto;
import com.Ezenweb.service.BoardService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController // 해당 클래스가 컨트롤 목적 사용
@RequestMapping("/board")
public class BoardController {

    //-----------------------HTMl LOAD URL----------------------------

        //1. 게시물 목록 페이지 열기
        @GetMapping("/list")
    public Resource list(){ //import org.springframework.core.io.Resource; 꼭 이거써야함
            return  new ClassPathResource("templates/board/list.html");
        }
        //2. 게시물 쓰기페이지열기

        @GetMapping("/write")
    public  Resource write(){
            return  new ClassPathResource("templates/board/write.html");
        }
        // ---------------------------------------------------------------
    //1.게시물 글쓰기 처리
    @PostMapping ("/setboard")
    @ResponseBody
    public  boolean setboard(@RequestBody BoardDto boardDto){

        System.out.println(boardDto.toString());
            //1. DTO 내용확인
            //2. ------> 서비스[비즈니스 로직]로 이동
            //3.반환
       boolean result = new BoardService().setboard(boardDto);
        return  true;
            // boolean : Content-Type : application/json
            // Sting : Content-Type : text/html; charset=UTF-8
            // Resource: Content-Type: text/html

        }
    //2.게시물 목록보기 [페이지,검색]
    @GetMapping("/getboards")
    @ResponseBody
    public ArrayList<BoardDto> getboards(){
            //1.------------>서비스[비즈니스 로직] 로 이동
            //2. 반환
       ArrayList<BoardDto> list = new BoardService().getboards();
        return  list;
    }

    //3.게시물 개별 조회 처리
    //@GetMapping("/getboard")
    //4.게시물 수정 처리
    //@PostMapping("/updateboard")
    //5.게시물삭제처리
    //@DeleteMapping("deleteboard")
}
