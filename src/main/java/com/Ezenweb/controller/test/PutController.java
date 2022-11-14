package com.Ezenweb.controller.test;


import com.Ezenweb.domain.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


//p.70
@RestController // @ResponseBody 생략가능
@RequestMapping("/api/v1/put-api")
public class PutController {


    //1.70
    @PutMapping("/member")
    public  String putMember(@RequestBody Map<String,String>putData){
        return putData.toString();

    }
    //2.2-1 p71
    @PutMapping("/member1")
    public  String psotMember(@RequestBody MemberDto memberDto){
        return  memberDto.toString();
    }

    //2-2 p.72
    @PutMapping("/member2")
    @ResponseBody
    public MemberDto postMemberDto(@RequestBody MemberDto memberDto){
        return  memberDto;
    }

}
