import React from 'react'
export default function Login(props){
    return (
        <div>로그인
               <form  method="post" action ="/member/getmember">
                   이메일 :<input type="text" name="memail" /><br/>
                   비밀번호 :<input type="password" name="mpassword"/><br/>
                   <input type="submit" value="로그인" />
                   <a href="/oauth2/authorization/kakao"> 카카오로그인</a>
                   <a href="/oauth2/authorization/naver"> 네이버로그인</a>
                   <a href="/oauth2/authorization/google"> 구글 로그인</a>
               </form>
         </div>
    );
}