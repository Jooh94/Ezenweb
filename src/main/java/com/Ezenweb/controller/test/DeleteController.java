package com.Ezenweb.controller.test;


import org.springframework.web.bind.annotation.*;


//p.75
@RestController //
@RequestMapping("/api/v1/delete-api")
public class DeleteController {

        // 1.p.76
        @DeleteMapping("/{variable}")
        public String DeleteVariable(@PathVariable String variable){
            return  variable;
        }

        //2. p.76
    @DeleteMapping("/requst1")
    public  String getRequstParam1(@RequestParam String variable){
            return  variable;
    }

}
