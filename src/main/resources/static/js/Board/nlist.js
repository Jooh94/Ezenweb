alert('pppp')

let ncno = 0;
//1. 게시물출력
boardlist()
function boardlist(){
    $.ajax({
        url:"/board/nboardlist",
        type:"get",
        data:{"ncno": ncno}, // 카테고리번호
        success: function(list){
        let html = '<tr><th> 번호 </th> <th>제목 </th> <th>내용</th></tr>';
        list.forEach((n)=>{
            html +=
            '<tr> <td>'+n.nno+'</td> <td>'+n.ntitle+'</td> <td>'+n.ncontent+'</td>'+
            '<td><button type="button" onclick="ndelboard('+n.nno+')">삭제</button></td>'+
            '<td><a href ="/board/nupdate"><button>수정</button></a></td></tr>';

        })
        document.querySelector('.ntable').innerHTML=html;

        }


    })

}
//3. 모든 카테고리 출력
ncategorylist()
function ncategorylist(){
    $.ajax({
    url:"/board/ncategorylist",
    type:"get",
    success: function(re){
        let html ='<button type="button" onclick="ncnochage(0)">전체보기</button>';
        re.forEach(c =>{
            html += '<button type="button" onclick="ncnochage('+c.ncno+')">'+c.ncname+'</button>';

        })
        document.querySelector('.ncategorybox').innerHTML = html;
        let cbtn = document.querySelectorAll(".cbtn")
    }

    })
}

//4. 카테고리를 선택했을때 선택된  카테고리 번호 변경

function ncnochage(cno){ncno= cno; alert(ncno+"의 카테고리선택");boardlist();}

//게시물 삭제
function ndelboard(nno){

let data = { nno: document.querySelector('.nno')}

  $.ajax({
    url : "/board/ndelboard",
    type:"delete",
    data:{"nno":nno},
    success:function(re){location.href="/board/nlist"}

  })

}
