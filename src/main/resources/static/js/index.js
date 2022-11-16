
getloginMno();

function getloginMno(){
        $.ajax({
         url: "/member/getloginMno",
         type:"get",
         success: function(re){
         alert(re)
        let headerbox ='';
        if( re=="0"){
          headerbox +=
              '<a href="/member/signup"> <button type="button">화원가입</button></a>'+
              '<a href="/member/login"><button type="button">로그인</button></a>'
        }
        else{ //로그인 했다
           headerbox +=
           '<a href="/member/logout"><button type="button" onclick="getlogoutMno()" >로그아웃</button></a>'+
                    '<a href="/member/findpassword"><button type="button">비밀번호찾기</button></a>'+
                    '<a href="/member/update"><button type="button">비밀번호수정</button></a>'+
                    '<a href="/member/delete"><button type="button">회원탈퇴</button></a>'
        }
        document.querySelector(".headerbox").innerHTML = headerbox;
   }
})

}
function getlogoutMno(){

    $.ajax({
        url: "/member/getlogoutMno", //요청 url
        type:"get", // 요청 메소드
        success: function(re){
        alert('로그아웃')
location.href="/";} // index.html 반환 해주는 매핑 주소 해주면된다
    })

    }

// 회원목록
list()
function list(){
    $.ajax({
    url: "/member/list",
    type:"get",
    success: function(list){
        let html = '<tr><th> 번호 </th> <th>이메일 </th> <th>비밀번호</th></tr>';
        list.forEach((m)=>{
            html +=
            '<tr> <td>'+m.mno+'</td> <td>'+m.memail+'</td> <td>'+m.mpassword+'</td></tr>';

        })
        document.querySelector(".mtable").innerHTML = html;

    }

    })
}