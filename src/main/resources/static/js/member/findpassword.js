alert('asd')
function findpassword(){
    alert('findpassword')
    let memail = document.querySelector(".memail").value
        $.ajax({
            url:"/member/getpassword",
            type:"get",
            data: {"memail":memail},
            success:function(re){alert(re)}
        })


    }

