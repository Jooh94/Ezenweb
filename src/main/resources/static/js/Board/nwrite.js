alert('왔나여')

let ncno = 1; // 카테고리 전역변수
function nwrite(){

let data ={
    ntitle : document.querySelector('.ntitle').value,
    ncontent : document.querySelector('.ncontent').value,
    ncno : ncno
}

    $.ajax({
    url: "/board/nsetboard",
    type: "POST",
    data: JSON.stringify(data),
    contentType: "application/json",
    success: function(re){
             if(re == true){
             alert('글작성성공');
             location.href="/board/nlist";

         }
             else{alert("글작성실패");}

    }
    })

}

//2. 게시물 카테고리 등록 메소드

function nsetbcategory(){
alert("nsetbcategory")
    let data = {ncname: document.querySelector(".ncname").value}
    $.ajax({
        url:"/board/nsetncategory",
        type:"post",
        data:JSON.stringify(data),
        contentType:"application/json",
        success: function(re){
            if( re== true){
                alert('카테로기추가성공')
                ncategorylist();
            }else{
                alert('카테고리추가실패')
            }
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
        let html ="";
        re.forEach(c =>{
            html += '<button type="button" onclick="ncnochage('+c.ncno+')">'+c.ncname+'</button>';

        })
        document.querySelector('.ncategorybox').innerHTML = html;
        let cbtn = document.querySelectorAll(".cbtn")
    }

    })
}

//4. 카테고리를 선택했을때 선택된  카테고리 번호 변경


function ncnochage(cno){ncno= cno; alert(ncno+"의 카테고리선택")}