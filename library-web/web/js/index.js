$(function () {
    //加载退出登陆
    signOut();
})
function signOut() {
    $( "#signOut_model" ).load( "signOutSystem.html", function() {
        $('#signOut_model').html();
            $(".signOut_btn").on("click",function () {
                $.ajax({
                    url:"signOut",
                    type:"post",
                    success:function (data) {
                        window.location.replace(data);
                    }
                })
            })
    });
}

