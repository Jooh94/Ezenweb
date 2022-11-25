package com.Ezenweb.controller;

import com.Ezenweb.domain.dto.MemberDto;
import com.Ezenweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000") // 리액트와 연결하기 위한 리액트 포트번호
@RestController // 해당 클래스가 RestController 임을 명시
@RequestMapping("/member") // 공통 URL 메핑주소
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/signup")
    public Resource getsignup(){return  new ClassPathResource("templates/member/signup.html");}
    @GetMapping("/login")
    public Resource getlogin(){return  new ClassPathResource("templates/member/login.html");}
    @GetMapping("/findpassword")
    public  Resource findpassword(){return  new ClassPathResource("templates/member/findpassword.html");}
    @GetMapping("/delete")
    public Resource getdelete(){return  new ClassPathResource("templates/member/delete.html");}
    @GetMapping("/update")
    public Resource update(){return  new ClassPathResource("templates/member/update.html");}

    //----------------------------------서비스/기능매핑---------------------------//
    @PostMapping("/setmember") // 회원가입 기능
    public  int setmember(@RequestBody MemberDto memberDto){
       int result = memberService.setmember(memberDto);
        return  result;
    }
    @PostMapping("/getmember") // 로그인 기능
    public  int getmember(@RequestBody MemberDto memberDto){
        int result = memberService.getmember(memberDto);
        return  result;
    }
    @GetMapping("/getpassword") //패스워드 찾기
    public  String getpassword(@RequestParam("memail") String memail){
        String result = memberService.getpassword(memail);
        return  result;

    }
    @DeleteMapping("/setdelete") //회원탈퇴
    public int setdelete(@RequestParam("mpassword") String mpassword){
        //1.서비스처리
        int result = memberService.setdelete(mpassword);


        //2.서비스 결과 반환
    return result;
    }
    @PutMapping("/setupdate") //회원수정
    public  int setupdate(@RequestParam("mpassword") String mpassword){
        int result = memberService.setupdate(mpassword);
        return result;

    }

    @GetMapping("/getloginMno") // 로그인 정보 확인
    public  int getloginMno(){
        int result = memberService.getloginMno();
        return  result;

    }

    @GetMapping("/getlogoutMno") //7.로그아웃
    public  void getlogoutMno(){memberService.getlogoutMno();}

    @GetMapping("/list") //8 회원목록
    @ResponseBody
    public List<MemberDto> list(){
      List<MemberDto> list = memberService.list();
        System.out.println("확인"+list);
      return list;
    }

    @GetMapping("/getauth")
    public String getauth(@RequestParam("toemail")String toemail){
        return memberService.getauth(toemail);
    }

}
