package com.Ezenweb.domain.dto;

import com.Ezenweb.domain.entity.member.MemberEntity;
import lombok.*;

@NoArgsConstructor // 빈생성자 주입
@AllArgsConstructor //풀 생성자 주입
@Getter @Setter //필드 들의 get,set 메소드 주입
@ToString //객체내 필드 정보 확인 TOString 메소드 주입
@Builder // 객체생성 안전성 보장[매개변수 개수/순서무관]



public class MemberDto {

    private  int mno;
    private  String memail;
    private  String mpassword;
    private  String mphone;
    //* dto --> entity 변환
    public MemberEntity toEntity(){
        return MemberEntity.builder()
                .mno(this.mno)
                .memail(this.memail)
                .mpassword(this.mpassword)
                .mphone(this.mphone)
                .build();
    }

}
