$(function () {
    //加载退出登陆模态框
    signOut();
})
function signOut() {
    $( "#signOut_model" ).load( "signOutSystem.js.js.html", function() {
        $('#signOut_model').html();
            /*$(".signOut_btn").on("click",function () {
                alert("a");
            })*/
    });

}

