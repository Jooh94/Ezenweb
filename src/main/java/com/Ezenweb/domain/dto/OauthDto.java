package com.Ezenweb.domain.dto;

import com.Ezenweb.domain.entity.member.MemberEntity;
import lombok.*;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.net.Socket;
import java.util.Map;
import java.util.Objects;

@NoArgsConstructor // 빈생성자 주입
@AllArgsConstructor //풀 생성자 주입
@Getter
@Setter //필드 들의 get,set 메소드 주입
@ToString //객체내 필드 정보 확인 TOString 메소드 주입
@Builder // 객체생성 안전성 보장[매개변수 개수/순서무관]

public class OauthDto {

    private String memail;                   // 아이디[이메일]
    private String mname;                       // 이름[닉네임]
    private String registrationId;           // outh 회사명
    private Map<String,Object> attributes;  // 인증 결과
    private String oauth2UserInfo;          // 회원정보

    // auth 회사에 따른 객체 생성 // 1.회사명 //2. 회원정보 //인증된 토크
    public static OauthDto of(String registrationId, String oauth2UserInfo, Map<String, Object>attributes){

        if (registrationId.equals("kakao")) {return ofKaKao(registrationId,oauth2UserInfo,attributes);}
        else if(registrationId.equals("naver")){return  ofnaver(registrationId,oauth2UserInfo,attributes);}
        else if (registrationId.equals("google")){return  ofGoogle(registrationId,oauth2UserInfo,attributes);}
        else{return  null;}

    }
    //1. 카카오 객체 생성 메소드
    public static OauthDto ofKaKao(String registrationId,String oauth2UserInfo,Map<String,Object>attributes){
        Map<String,Object> kakao_account= (Map<String, Object>) attributes.get(oauth2UserInfo);
            //
        Map<String,Object> profile =(Map<String, Object>) kakao_account.get("profile");
        return  OauthDto.builder()
                .memail((String)kakao_account.get("email"))
                .mname((String)profile.get("nickname"))
                .registrationId(registrationId)
                .oauth2UserInfo(oauth2UserInfo)
                .attributes(attributes)
                .build();
    }

    //2.네이버 객체 생성 메소드
    public static OauthDto ofnaver(String registrationId,String oauth2UserInfo,Map<String,Object>attributes){
        System.out.println("naver attributes:"+attributes);
        Map<String,Object> response = (Map<String, Object>) attributes.get(oauth2UserInfo);

        return  OauthDto.builder()
                .memail((String) response.get("email"))
                .mname((String) response.get("nickname"))
                .registrationId(registrationId)
                .oauth2UserInfo(oauth2UserInfo)
                .attributes(attributes)
                .build();
    }


    // 3. 구글 객체 생성 메소드
    public static OauthDto ofGoogle( String registrationId , String oauth2UserInfo , Map<String , Object> attributes ){
        System.out.println("Google attributes " + attributes );
        return OauthDto.builder()
                .memail((String) attributes.get("email"))
                .mname((String) attributes.get("name"))
                .registrationId( registrationId)
                .oauth2UserInfo(oauth2UserInfo )
                .attributes( attributes )
                .build();
    }


    //4. dto - ToEntity
    public MemberEntity toEntity(){
        return MemberEntity.builder()
                .memail(this.memail)
                .mrol(this.registrationId)
                .build();
    }


}
