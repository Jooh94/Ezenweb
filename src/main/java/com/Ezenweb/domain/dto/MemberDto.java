package com.Ezenweb.domain.dto;

import com.Ezenweb.domain.entity.member.MemberEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

@NoArgsConstructor // 빈생성자 주입
@AllArgsConstructor //풀 생성자 주입
@Getter @Setter //필드 들의 get,set 메소드 주입
@ToString //객체내 필드 정보 확인 TOString 메소드 주입
@Builder // 객체생성 안전성 보장[매개변수 개수/순서무관]



public class MemberDto implements UserDetails , OAuth2User {
    @Override
    public String getName() {
        return this.memail;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    private  int mno;
    private  String memail;
    private  String mpassword;
    private  String mphone;
    //* dto --> entity 변환

    private Set<GrantedAuthority> authorities; //인증 권한 토큰
    private  Map<String, Object> attributes; //결과 인증결과

    public MemberEntity toEntity(){
        return MemberEntity.builder()
                .mno(this.mno)
                .memail(this.memail)
                .mpassword(this.mpassword)
                .mphone(this.mphone)
                .build();
    }

    public void setAuthorities(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }
    @Override
    public String getPassword() {
        return mpassword;
    }

    @Override
    public String getUsername() {
        return memail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
