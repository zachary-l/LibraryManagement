$(function () {
    //加载退出登陆模态框
    signOut();
})
function signOut() {
    $( "#signOut_model" ).load( "signOutSystem.html", function() {
        $('#signOut_model').html();
    });

}

