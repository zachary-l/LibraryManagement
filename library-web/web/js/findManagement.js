$(function () {
    findList();
});

//初始化列表
function findList() {
    $.ajax({
        url: "findBooks/list",
        type: "post",
        data: {currentPage: 1},
        success: function (data) {
            addList(data.list);
            page(data);
        }
    });
}

function addList(list) {
    $(".findList_tr").remove();
    $.each(list, function (index, obj) {
        $(".findList_tab").append("<tr class='findList_tr'>" +
            "<td>" + obj.RF_ID + "</td>" +
            "<td>" + obj.RE_ID + "</td>" +
            "<td>" + obj.RE_NAME + "</td>" +
            "<td>" + obj.RF_EXPLAIN + "</td>" +
            "<td>" + obj.RF_MONEY + "</td>" +
            "<td>" + obj.TF_TYPE + "</td>" +
            "<td>" + obj.BOR_TIME.split(" ")[0] + "</td>" +
            "<td>" + obj.RET_TIME.split(" ")[0] + "</td>" +
            "<td>" + obj.RET_NUM + "</td>" +
            "</tr>");
    });
}

//分页方法
function page(data) {
    //选择器选中分页的div容器，并调用pagination方法来设置分页控件
    $("#findBookList_page").pagination(data.countResult, {
        //第一个参数指定共多少条记录
        items_per_page: data.maxResult, // 每页显示多少条记录
        next_text: ">", //下一页按钮图标
        prev_text: "<", //上一页按钮图标
        num_display_entries: 5,//主体页数
        num_edge_entries: 2, //边缘页数
        callback: function (index) {//定义一个回调函数，用于每次点击页码发起分页查询请求
            var currentPage = ++index;
            $.get("findBooks/list", "currentPage=" + currentPage, function (data) {
                addList(data.list);
            });
        }
    });
}