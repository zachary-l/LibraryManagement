$(function () {
    findBooks();
    findTypeBooks();
});


function findBooks() {
    $.ajax({
        url:"infoBooks/findBooks",
        type:"post",
        data:{currentPage:1},
        success:function (data) {
            infoBooksAddTable(data.list)
            infoBooksManagePage(data)
        }
    });
}
function findTypeBooks() {
    // sle_typeBooks
    $.ajax({
        url:"typeBook/findType",
        type:"post",
        success:function (data) {
            $.each(data,function (index,obj) {
                $(".sle_typeBooks").append("<option value='"+obj.tyId+"'>"+obj.tyName+"</option>");
            })
        }
    });
    $(".sle_typeBooks").on("change",function () {
        var typeId = $(".sle_typeBooks").val();
        $.ajax({
            url:"infoBooks/findBooksById",
            type:"post",
            data:{currentPage:1,tyId:typeId},
            success:function (data) {
                infoBooksAddTable(data.list)
                infoBooksManagePage(data)
            }
        });
    })
}
function infoBooksAddTable(list) {
    $(".infoBooks_tabs").remove();
    $.each(list, function (index, obj) {
        var publicationtime = obj.IN_PUBLICATIONTIME.split(" "),
            uplibaraytime=  obj.IN_UPLIBRARYTIME.split(" "),
            updatetime = obj.IN_UPDATETIME.split(" "),
            borrowfrom = "";
        if(obj.IN_BORROWFROM){borrowfrom = "允许外借"
        }else{borrowfrom = "不允许外借"}
        $(".booksInfoList_tab").append("<tr class='infoBooks_tabs'>" +
            "<td>" + obj.TY_NAME + "</td>" +
            "<td>" + obj.IN_ID + "</td>" +
            "<td>" + obj.IN_NAME + "</td>" +
            "<td>" + obj.IN_AUTHOR + "</td>" +
            "<td>" + obj.IN_PRESS + "</td>" +
            "<td>" + obj.IN_PRICE + "</td>" +
            "<td>" + obj.IN_NUM + "</td>" +
            "<td>" +publicationtime[0]+ "</td>" +
            "<td>" +uplibaraytime[0]+ "</td>" +
            "<td>" + updatetime[0] + "</td>" +
            "<td>" +borrowfrom+ "</td>" +
            "<td><a href='#'>修改</a></td>" +
            "<td><a href='#'>下架</a></td>" +
            "</tr>");
    });

}
//分页方法
function infoBooksManagePage(data) {
    //选择器选中分页的div容器，并调用pagination方法来设置分页控件
    $("#InfoBookManagement_page").pagination(data.countResult, {
        //第一个参数指定共多少条记录
        items_per_page: data.maxResult, // 每页显示多少条记录
        next_text: ">", //下一页按钮图标
        prev_text: "<", //上一页按钮图标
        num_display_entries: 5,//主体页数
        num_edge_entries: 2, //边缘页数
        callback: function (index) {//定义一个回调函数，用于每次点击页码发起分页查询请求
            var currentPage = ++index;
            $.get("infoBooks/findBooks", "currentPage=" + currentPage, function (data) {
                infoBooksAddTable(data.list);
            });
        }
    });
}