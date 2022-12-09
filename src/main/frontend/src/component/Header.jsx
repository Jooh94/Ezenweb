//1.
import React ,{useState}from 'react';
import Styles from '../css/header.css'; // src->css->header.css;
import logo from '../img/logo.png'  // 이미지 적용
import {  HashRouter,BrowserRouter,Routes,Route, Link ,Router } from "react-router-dom";
import axios from 'axios'; // react 비동기 통신 라이브러리


export default function Header(){

    const[login,setLogin] = useState(null); // 로그인된 회원정보 state 생명주기

    // 1. 서버와 통신 [axios] 비동기 통신
    axios
            .get("/member/getloginMno")
            //.get("url)
            //.post("url",data)
            //.delet("url")
            //.put("url"data)
            .then((response)=>{setLogin(response.data);}) // axios.type('URL').then( response=>{응답})
            //.then(옵션메소드)
            //.then((응답변수명)=>{응답 실행문})
                //응답객체명:http 응답 객체 변환
                    //응답 데이터 호출


    return(
    <div>
     <div className="header">
        <div className="header_logo">
             <Link to="/"><img className="logo" src={logo} /></Link>
        </div>
        <ul className ="top_menu">

                {login =="" ?
                (
                    <>
                        <li><Link to="/member/signup">회원가입</Link>  </li>
                        <li><Link to="/member/login">로그인</Link>    </li>
                    </>
                    )
                :
                (
                    <>
                        <li>{login}</li>
                        <li> <a href="/member/logout"> 로그아웃 </a>   </li>
                        <li> <Link to="/book/bookList"> 리액트공부방 </Link>   </li>

                    </>
                    )
                }
                        <li><Link to="/board/list">자유게시판</Link>   </li>

         </ul>
      </div>
    </div>
    );

}

/*

    가상 DOM 작성시 주의점
        1.<태그명></태그명>, <태그명 />
        2.( <태그명></태그명> )
        JSX 문법에서 태그[요소]들 묶어주는
        3.( <div> <태그명></태그명> <태그명</태그명> </div>)
        3.( <> <태그명></태그명> <태그명</태그명> </>)

        re( <button type=""> ,/button>)

*/