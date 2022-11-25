//게시물 수정
alert('assadads')
let nno  = sessionStorage.getItem("nno");


//2. 수정 전의 게시물 보여줘야한다라...



function nupboard(){
    alert('dddddsvsv')
        let data={ // value 값을 넣어주셔야죠 ㅎㅎ // 문제점 보셨나요??벨류값..안넣어서인거같습니당
            ntitle:document.querySelector('.ntitle').value,
            ncontent:document.querySelector('.ncontent').value,
            nno:nno
        } // 그리고 제목과 내용은 수정할 정보인거죠??? 아니요 수정할 식별번호[대상] 와 수정할 내용들이죠
        // 그래서 게시물번호도 같이 가야 합니다 게시물번호! 맞을까요 선생님  네 그럼 저렇게 해도되나요?
    $.ajax({
        url:"/board/nupboard",
        type:"put",
        data:JSON.stringify(data),
        contentType: "application/json",
        success: function(re){

            if(re == true){
            console.log(re)
                alert('글 수정 성공');
                location.href="/board/nlist";

            }
            else{alert("글 수정 실패");}
        }
    })
}
