$(function () {
    $.ajax({
        url:"admin/findById",
        type:"post",
        success:function (data) {
        $("#admin_name").append(data.adName);
        }
    })
})