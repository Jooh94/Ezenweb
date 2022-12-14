import React,{useState,useEffect} from 'react'
import axios from 'axios'


export default function BoardList(){
    //1.메모리
        //1.게시물 리스트 state 상태
    const [boardlist,setBoardlist]= useState([])

    //2. 서버로부터 게시물 리스트를 가져오는 함수
    const getboardlist = () =>{
        axios
            .get("/board/boardlist",{params:{bcno:0 , page:1 , key:"",keyword:""} } )
            .then(res=>{
                console.log(res.data)
                setBoardlist(res.data);
            } )
            .catch(err=>{console.log(err);})
    }
    useEffect(getboardlist,[])

    return (
        <div>
               <a href="/board/write"> 글쓰기[로그인했을때만표시] </a>
               <h1> 글목록 페이지</h1>
               <div className="bcategorybox"></div>
               <table className="btable">
               </table>
        </div>

    );
}