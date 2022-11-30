package com.Ezenweb.controller;


import com.Ezenweb.domain.dto.BcategoryDto;
import com.Ezenweb.domain.dto.BoardDto;
import com.Ezenweb.domain.dto.NboardDto;
import com.Ezenweb.domain.dto.NcateogryDto;
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
    public Resource getlist() {
        return new ClassPathResource("templates/board/list.html");
    }

    //2. 게시물쓰기 페이지열기
    @GetMapping("/write")
    public Resource getwrite() {
        return new ClassPathResource("templates/board/write.html");
    }

    //3. 게시물ㅈ회 페이지 열기
    @GetMapping("/view")
    public Resource getview() {
        return new ClassPathResource("templates/board/view.html");
    }

    //4. 게시물수정 페이지 열기
    @GetMapping("/update")
    public Resource getupdate() {
        return new ClassPathResource("templates/board/update.html");
    }





    //비회원 글쓰기
    @GetMapping("/nwrite")
    public Resource ngetwrite() {
        return new ClassPathResource("templates/board/nwrite.html");
    }

    //비호원 게시물페이지
    @GetMapping("/nlist")
    public Resource ngetlist() {
        return new ClassPathResource("templates/board/nlist.html");
    }


    //비회원 수정 페이지
    @GetMapping("/nupdate")
    public Resource ngetupdate() {
        return new ClassPathResource("templates/board/nupdate.html");
    }



    //-----------------3.요청과응답 처리[model]-------------------//
    // 객체로 받을땐 @RequestBody
    // 하나씩 받을땐 @RequestParam
    //1. 게시물 쓰기 [첨부파일 없을때]
//    @PostMapping("/setboard")
//    public boolean setboard(@RequestBody BoardDto boardDto) {
//        System.out.println("확인:"+boardDto.toString());
//        return boardService.setboard(boardDto);
//    }
    //첨부파일 있을때
    @PostMapping("/setboard")
    public boolean setboard(BoardDto boardDto) {
        System.out.println("확인:"+boardDto.toString());
        return boardService.setboard(boardDto);
    }
    //2. 게시물 목록 조회 [ 페이징 검색]
    @GetMapping("/boardlist")
    public List<BoardDto> boardlist(
            @RequestParam("bcno") int bcno,
            @RequestParam("page") int page,
            @RequestParam("key") String key,
            @RequestParam("keyword") String keyword
    ) {
        return boardService.boardlist(page,bcno,key,keyword);


    }

    //3. 게시물 개별 조회
    @GetMapping("/getboard")
    public BoardDto getboard(@RequestParam("bno") int bno) {
        return boardService.getboard(bno);
    }

    //4. 게시물 삭제
    @DeleteMapping("/delboard")
    public boolean delboard(@RequestParam("bno") int bno) {
        return boardService.delboard(bno);
    }

    //5. 게시물 수정[첨부파일 X]
    @PutMapping("/upboard")
    public boolean upboard( BoardDto boardDto) {
        return boardService.upboard(boardDto);
    }


    //6. 카테고리 등록
    @PostMapping("/setbcategory")
    public boolean setbcategory(@RequestBody BcategoryDto bcategoryDto) {
        return boardService.setbcategory(bcategoryDto);
    }

    //7. 모든 카테고리 출력
    @GetMapping("/bcategorylist")
    public List<BcategoryDto> bcategoryList() {
        return boardService.bcategorlist();
    }



    //8 첨부파일 다운ㄹ드
    @GetMapping("/filedownload")
    public  void filedownload(@RequestParam("filename") String filename){
        boardService.filedownload(filename);
    }




    //비회원 글쓰기
    @PostMapping("/nsetboard")
    public boolean nsetboard(@RequestBody NboardDto nboardDto) {
      boolean result =  boardService.nsetboard(nboardDto);
        return  result;
    }

    //비회원 글 목록
    @GetMapping("/nboardlist")
    public List<NboardDto> nlist(@RequestParam("ncno") int ncno){

        return boardService.nlist(ncno);
    }

    //비회원 카테고리 등록
    @PostMapping("/nsetncategory")
    public boolean nsetncategory(@RequestBody  NcateogryDto ncategoryDto) {
        return boardService.nsetncategory(ncategoryDto);
    }


    // 비회원 카테고리 출력

    @GetMapping("/ncategorylist")
    public List<NcateogryDto> ncategoryList(){
        return  boardService.ncategorylist();
    }
    //4. 비회원 게시물삭제
    @DeleteMapping("/ndelboard")
    public boolean ndelboard(@RequestParam("nno") int nno) {
        return boardService.ndelboard(nno);
    }

    //5. 비회원 수정

    @PutMapping("/nupboard")
    public boolean nupboard(@RequestBody NboardDto nboardDto) {
        return boardService.nupboard(nboardDto);
    }





}