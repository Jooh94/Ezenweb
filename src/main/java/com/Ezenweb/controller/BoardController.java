package com.Ezenweb.controller;


import com.Ezenweb.domain.dto.BcategoryDto;
import com.Ezenweb.domain.dto.BoardDto;
import com.Ezenweb.domain.entity.bcategory.BcategoryRepository;
import com.Ezenweb.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController // 스프링이 이건 컨트롤이다 알아야하니 설정
@RequestMapping("/board")
public class BoardController {

    // 컨트롤 역할 : HTTP 요청 / model AND View응답

    // ----------------1.전역변수 -------------------------------//
   //1. 서비스 메소드 호출 위한 객체 생성 [제어역전]
    //1. 이건 관리자가 만든거 관리 내가해야한다
    //개발자가 new 연산자 사용해서 JVM 힙 메모리 할당해서 객체 생성
    //private  BoardService boardService = new BoardService();
    //2. boardService 부르기위한 두가지방법 인데 아래꺼쓰면 스프링이 관리한다
    //2.@AUtowired 어노테이션 이용해서 Spring 컨테이너에 빈 생성
    @Autowired
    private BoardService boardService;
    //----------------2.페이지 로드[html] [view]-----------------------//

    //1. 게시물페이지 열기
    @GetMapping("/list")
    public Resource getlist(){return new ClassPathResource("templates/board/list.html");}
    //2. 게시물쓰기 페이지열기
    @GetMapping("/write")
    public Resource getwrite(){return new ClassPathResource("templates/board/write.html");}
    //3. 게시물ㅈ회 페이지 열기
    @GetMapping("/view")
    public Resource getview(){return  new ClassPathResource("templates/board/view.html");}
    //4. 게시물수정 페이지 열기
    @GetMapping("/update")
    public Resource getupdate(){return new ClassPathResource("templates/board/update.html");}
    //-----------------3.요청과응답 처리[model]-------------------//
    // 객체로 받을땐 @RequestBody
    // 하나씩 받을땐 @RequestParam
    //1. 게시물 쓰기 [첨부파일]
    @PostMapping("/setboard")
    public boolean setboard(@RequestBody BoardDto boardDto){
        return boardService.setboard(boardDto);
    }
    //2. 게시물 목록 조회 [ 페이징 검색]
    @GetMapping("/boardlist")
    public List<BoardDto> getboardlist(@RequestParam("bcno")int bcno){
        return boardService.boardlist(bcno);
    }
    //3. 게시물 개별 조회
    @GetMapping("/getboard")
    public BoardDto getboard(@RequestParam("bno") int bno){
        return  boardService.getboard(bno);
    }
    //4. 게시물 삭제
    @DeleteMapping("/delboard")
    public boolean delboard(@RequestParam("bno") int bno){
        return  boardService.delboard(bno);
    }
    //5. 게시물 수정
    @PutMapping("/upboard")
    public boolean upboard(@RequestBody BoardDto boardDto){
        return boardService.upboard(boardDto);
    }


    //6. 카테고리 등록
    @PostMapping ("/setbcategory")
    public  boolean setbcategory(@RequestBody BcategoryDto bcategoryDto){
        return  boardService.setbcategory(bcategoryDto);
    }
    //7. 모든 카테고리 출력
    @GetMapping("/bcategorylist")
    public  List<BcategoryDto> bcategoryList(){
        return  boardService.bcategorlist();
    }
}
