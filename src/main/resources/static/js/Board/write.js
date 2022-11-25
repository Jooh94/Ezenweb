alert('asd')

let bcno = 2; // 카테고리번호 전역변수
//// 1. 게시물 등록 메소드
//function setboard(){
//    let data = {
//        btitle : document.querySelector('.btitle').value ,
//        bcontent : document.querySelector('.bcontent').value,
//        bfile : document.querySelector('.bfile').value ,
//        bcno : bcno
//    }
//    $.ajax({
//        url : "/board/setboard",
//        type : "post",
//        data : JSON.stringify(data) ,
//        contentType : "application/json",
//        success : function(re) {
//            if( re == true){
//                alert('글작성성공');
//                location.href="/board/list";
//            }
//            else{ alert("글작성실패"); }
//        }
//    })
//}

//1.게시물 등록 메소드 //첨부파일이 있을경우 등록 메소드
function setboard(){

    let boardform = document.querySelector('.boardform')
    let formdata = new FormData(boardform);
    formdata.set("bcno",bcno) // 폼 데이터에 카테고리 정보 추가

    $.ajax({ // http 사용하는 jquery(제이쿼리) 비동기통신 함수[기본값 contentType : text/html]
        url:"/board/setboard",
        type:"post",                // Multipart 전송 방법1(첨부파일)
        data:formdata,
        //content-Type : multipart / formed-data<-- 파일첨부
        contentType: false,         // Multipart 전송 방법2(첨부파일)
        processData : false,        // Multipart 전송 방법3(첨부파일)
        success: function(re){
            if(re == true){
            alert('글작성성공');
            location.href="/board/list";

        }
            else{alert("글작성실패");}
        }
    })
}

//2. 게시물 카테고리 등록메소드
function setbcategory(){
    let data={bcname : document.querySelector(".bcname").value}
     $.ajax({
     url:"/board/setbcategory",
     type:"post",
     data:JSON.stringify(data),
     contentType:"application/json",
     success: function(re){
       if(re== true){
        alert('카테고리추가성공')
        bcategorylist();
     }else{
            alert('카테고리추가실패')
        }
       }
     })
}
// 카테고리 기본값


//3. 모든 카테고리 출력
bcategorylist()
function bcategorylist(){
    $.ajax({
    url:"/board/bcategorylist",
    type:"get",
    success:function(re){
        let html ="";
        re.forEach(c =>{
            html +='<button type="button" onclick="bcnochage('+c.bcno+')">'+c.bcname+'</button>';
        })
        document.querySelector('.bcategorybox').innerHTML = html;
        }
    })
}
//4. 카테고리를 선택했을때 선택된 카테고리 변경
function bcnochage(cno){bcno = cno; alert(bcno) } // 온클릭 함수안에 bcno 아닌가요 cno가 어디서온건가요..?
