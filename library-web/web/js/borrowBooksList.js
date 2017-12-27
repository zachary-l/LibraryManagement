$(function () {
    findList();
    addBorrowBooks();
});

//初始化列表
function findList() {
    $.ajax({
        url: "borrow/findList",
        type: "post",
        data: {currentPage: 1},
        success: function (data) {
            addList(data.list);
            page(data);
        }
    });
}

function addBorrowBooks() {
    $(".borrowBooks_addbtn").on("click", function () {
        var param = $.param({'currentPage': 1}) + '&' + $("#borrowBooks_addf1").serialize();
        $.ajax({
            url: "borrow/add",
            type: "post",
            data: param,
            success: function (data) {
                if (data.statusCode == 401) {
                    alert(data.message);
                } else if (data.statusCode == 200) {
                    addList(data.value.list);
                    page(data.value);
                    $(".borrowBooks_closebtn").trigger("click");
                }
            }
        })
    })

}

function addRetrun() {
    //借阅表id
    $(".return_btn").on("click", function () {
        var title = $(this).prop("title");
        var time = $(this).parent().prev().prev().prev().html();
        var b = returnBooksisDate(time);
        //true表示以过期
        //false表示没过期
        if (b) {
            //过期执行先交罚金再归还
            $(function () {
                $('#mybooksFind_modal').modal({
                    keyboard: true
                })
            });
            $(".borrowBooks_addbtn2").on("click", function () {
                var params = $.param({
                    borId: title,
                    currentPage: 1,
                    whetherFind: 1
                }) + '&' + $("#borrowBooks_addf3").serialize();
                $.ajax({
                    url: "returnBooks/addSincerity",
                    type: "post",
                    data: params,
                    success: function (data) {
                        if (data.statusCode == 401) {
                            alert(data.message);
                        } else if (data.statusCode == 200) {
                            addList(data.value.list);
                            page(data.value);
                            $(".borrowBooks_closebtn2").trigger("click");
                        }
                    }
                })
            })
        } else {
            $.ajax({
                url: "returnBooks/addSincerity",
                type: "post",
                data: {borId: title, currentPage: 1, whetherFind: 0},
                success: function (data) {
                    if (data.statusCode == 401) {
                        alert(data.message);
                    } else if (data.statusCode == 200) {
                        addList(data.value.list);
                        page(data.value);
                    }
                }
            })

        }
    })
}

function addList(list) {
    $(".borrowBooksList_tr").remove();
    $.each(list, function (index, obj) {
        $(".borrowBooksList_tab").append("<tr class='borrowBooksList_tr'>" +
            "<td>" + obj.BOR_ID + "</td>" +
            "<td>" + obj.RE_ID + "</td>" +
            "<td>" + obj.RE_NAME + "</td>" +
            "<td>" + obj.IN_NAME + "</td>" +
            "<td>" + obj.IN_ID + "</td>" +
            "<td>" + obj.BOR_TIME.split(" ")[0] + "</td>" +
            "<td>" + obj.BOR_TIME_LAST.split(" ")[0] + "</td>" +
            "<td>" + obj.BOR_NUM + "</td>" +
            "<td>" + obj.AD_NAME + "</td>" +
            "<td><a href='#' class='return_btn' title='" + obj.BOR_ID + "' >归还</a></td>" +
            "</tr>");
    });
    addRetrun();
}

//分页方法
function page(data) {
    //选择器选中分页的div容器，并调用pagination方法来设置分页控件
    $("#BorrowBookList_page").pagination(data.countResult, {
        //第一个参数指定共多少条记录
        items_per_page: data.maxResult, // 每页显示多少条记录
        next_text: ">", //下一页按钮图标
        prev_text: "<", //上一页按钮图标
        num_display_entries: 5,//主体页数
        num_edge_entries: 2, //边缘页数
        callback: function (index) {//定义一个回调函数，用于每次点击页码发起分页查询请求
            var currentPage = ++index;
            $.get("borrow/findList", "currentPage=" + currentPage, function (data) {
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