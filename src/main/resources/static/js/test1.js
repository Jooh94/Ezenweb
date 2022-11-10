alert('asd')
                function getMapping1(){
                    $.ajax({
                        url : "/api/v1/get-api/hello",
                        success : re =>{ alert (re);}
                    })

                }
                function getMapping2(){
                    $.ajax({
                        url : "/api/v1/get-api/name",
                        success:re=>{alert(re);}
                    })

                }

                     function getMapping3(){
                        $.ajax({
                           url : "/api/v1/get-api/variable1/아무말대잔치",
                           success:re=>{alert(re);}
                        })

                  }
                     function getMapping4(){
                        $.ajax({
                           url : "/api/v1/get-api/variable2/이거맞나요",
                           success:re=>{alert(re);}
                        })

                  }

                     function getMapping5(){
                        $.ajax({
                           url : "/api/v1/get-api/variable3?variable=이거맞을까요",
                           success:re=>{alert(re);}
                        })

                  }
                     function getMapping6(){
                        $.ajax({
                           url : "/api/v1/get-api/requst1?name=qwe&email=qwe@qwe&organization=qweqwe",
                           success:re=>{alert(re);}
                        })

                  }
                     function getMapping7(){
                        $.ajax({
                           url : "/api/v1/get-api/requst2?key1=value1&key2=value2",
                           success:re=>{alert(re);}
                        })

                  }
                     function getMapping8(){
                        $.ajax({
                           url : "/api/v1/get-api/requst3?name=맞나&email=맞나요&organization=제발",
                           success:re=>{alert(re);}
                        })

                  }

 //--------------------------------post-------------------------//
        function postMapping1(){
             $.ajax({
               url : "/api/v1/post-api/domain",
               type:"post",
               success:function(re){alert(re);}
                         })

       }
        function postMapping2(){
                 let member={
                  name : "유재석",
                  email: "qweqweqwe@qweqwew",
                  organization:"qweqwe"
                               }
                $.ajax({
                 url : "/api/v1/post-api/member",
                 type:"post",
                 data:JSON.stringify(member),
                 contentType:"application/json", //전송타입 : application/json
                   success:function(re){alert(re)}
                       })
}

        function postMapping3(){
                 let member={
                  name : "유재석",
                  email: "qweqweqwe@qweqwew",
                  organization:"qweqwe"
                               }
                $.ajax({
                 url : "/api/v1/post-api/member",
                 type:"post",
                 data:JSON.stringify(member),
                 contentType:"application/json", //전송타입 : application/json
                   success:function(re){alert(re)}
                       })
}

//----------------put------------------------------------------//

function putMapping1(){
                 let member={
                  name : "유재석",
                  email: "qweqweqwe@qweqwew",
                  organization:"qweqwe"
                               }

        $.ajax({
            url : "/api/v1/put-api/member",
            type:"PUT",
            data: JSON.stringify(member),
            contentType: "application/json",
            success: function(re){alert(re)}

})
}


function putMapping2(){
                 let member={
                  name : "유재석",
                  email: "qweqweqwe@qweqwew",
                  organization:"qweqwe"
                               }

        $.ajax({
            url : "/api/v1/put-api/member1",
            type:"PUT",
            data: JSON.stringify(member),
            contentType: "application/json",
            success: function(re){alert(re)}

})
}

function putMapping3(){
                 let member={
                  name : "유재석",
                  email: "qweqweqwe@qweqwew",
                  organization:"qweqwe"
                               }

        $.ajax({
            url : "/api/v1/put-api/member2",
            type:"PUT",
            data: JSON.stringify(member),
            contentType: "application/json",
            success: function(re){
                console.log(re);
                console.log(re.name);

    }
})
}


//----------------------------------delete--------------------------------

function deletemapping1(){
    $.ajax({
       url:"/api/v1/delete-api/하하하",
       type:"delete",
       success :function(re){alert(re)}

    })
}
function deletemapping2(){
    $.ajax({
       url:"/api/v1/delete-api/requst1?variable=헤헤헤헤",
       type:"delete",
       success :function(re){alert(re)}

})
}



