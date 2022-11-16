package com.Ezenweb.domain.entity;


import com.Ezenweb.domain.dto.MemberDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity // 해당 연결된 DB의 테이블과 매핑[연결]
@Table(name="member") //db에서 사용될 테이블 이름
@NoArgsConstructor // 빈생성자 주입
@AllArgsConstructor //풀 생성자 주입
@Getter
@Setter //필드 들의 get,set 메소드 주입
@ToString //객체내 필드 정보 확인 TOString 메소드 주입
@Builder // 객체생성 안전성 보장[매개변수 개수/순서무관]
public class MemberEntity {

        //1. 필드
        @Id // 엔티티당 무조건 1개이상 [pk]
        @GeneratedValue(strategy = GenerationType.IDENTITY)// 자동번호부여
        private int mno; // 회원번호 필드


        @Column(nullable = false) //  DB :not null
        private String memail;  // 회원이메일 = 회원아이디 필드
        @Column(nullable = false) //  DB :not null
        private String mpassword; // 회원비밀번호 필드

        @Column (nullable = false) //  DB :not null
        private String mphone;     // 회원 전화번호 필드



        //2.생성자 [ 룸복으로 사용]
        //3.메소드[룸복으로사용]
        //* 엔티티 ->
        public MemberDto toDto(){
                return MemberDto
                        .builder() //빌더 메소드시작
                        .mno(this.mno)
                        .memail(this.memail)
                        .mpassword(this.mpassword)
                        .mphone(this.mphone)
                        .build(); // 빌드 메소드끝
        }


}


