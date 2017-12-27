$(function () {
    findList();
});

//初始化列表
function findList() {
    $.ajax({
        url: "returnBooks/findList",
        type: "post",
        data: {currentPage: 1},
        success: function (data) {
            addList(data.list);
            page(data);
        }
    });
}

function addList(list) {
    $(".returnBooksList_tr").remove();
    $.each(list, function (index, obj) {
        $(".returnBooksList_tab").append("<tr class='returnBooksList_tr'>" +
            "<td>" + obj.RET_ID + "</td>" +
            "<td>" + obj.RE_ID + "</td>" +
            "<td>" + obj.RE_NAME + "</td>" +
            "<td>" + obj.IN_NAME + "</td>" +
            "<td>" + obj.IN_ID + "</td>" +
            "<td>" + obj.BOR_TIME.split(" ")[0] + "</td>" +
            "<td>" + obj.RET_TIME.split(" ")[0] + "</td>" +
            "<td>" + obj.RET_NUM + "</td>" +
            "<td>" + obj.AD_NAME + "</td>" +
            "<td>" + obj.RE_CREDIT + "</td>" +
            "</tr>");
    });
}

//分页方法
function page(data) {
    //选择器选中分页的div容器，并调用pagination方法来设置分页控件
    $("#returnBookList_page").pagination(data.countResult, {
        //第一个参数指定共多少条记录
        items_per_page: data.maxResult, // 每页显示多少条记录
        next_text: ">", //下一页按钮图标
        prev_text: "<", //上一页按钮图标
        num_display_entries: 5,//主体页数
        num_edge_entries: 2, //边缘页数
        callback: function (index) {//定义一个回调函数，用于每次点击页码发起分页查询请求
            var currentPage = ++index;
            $.get("returnBooks/findList", "currentPage=" + currentPage, function (data) {
                addList(data.list);
            });
        }
    });
}

//比较时间,判断是否过期
function returnBooksisDate(dateTime) {
    var mm = dateTime.split(" ");
    var yy = mm[0].split("-");
    var date1 = new Date();
    date1.setFullYear(yy[0], yy[1] - 1, yy[2]);
    var date2 = new Date();
    if (date1 < date2) {
        return true;
    } else {
        return false
    }
}