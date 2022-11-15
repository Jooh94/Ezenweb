alert('dd')

function setmember(){
    alert('setmember');

    let info ={
       memail : document.querySelector('.memail').value,
       mpassword:document.querySelector('.mpassword').value

    }

    $.ajax({
        url: "/member/setmember",
        type:"post",
        data:JSON.stringify(info),
        contentType:"application/json",
        success: function(re){alert(re)}
    })
}