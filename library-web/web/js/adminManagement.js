$(function () {
    $.ajax({
        url: "admin/findAll",
        type: "post",
        success: function (data) {
            adminManageAddTable(data)
        }
    });
})

//显示表格数据
function adminManageAddTable(list) {
    $(".adminmanage_tabs").remove();
    $.each(list, function (index, obj) {
        var sex = (obj.adSex) == true ? "男" : "女";
        var jur = obj.adJur;
        if (jur == 1) {
            jur = "普通管理员"
        }
        else if (jur == 2) {
            jur = "超级管理员"
        } else {
            jur = "管理员"
        }
        var time = obj.adTime.split(" ")
        $(".adminManage_tab").append("<tr class='adminmanage_tabs'>" +
            "<td>" + obj.adId + "</td>" +
            "<td>" + obj.adName + "</td>" +
            "<td>" + sex + "</td>" +
            "<td>" + obj.adPhone + "</td>" +
            "<td>" + jur + "</td>" +
            "<td>" + time[0] + "</td>" +
            "<td><a href='#'>修改信息</a></td>" +
            "<td><a href='#' class='deleteAdminBtn' title='" + obj.adId + "'>删除</a></td>" +
            "</tr>");
    });
}