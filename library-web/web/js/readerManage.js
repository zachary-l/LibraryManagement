
$(function(){
    //查询显示数据
    $.ajax({
        url : "reader/find",
        type : "post",
        data : "currentPage=1",
        success : function(data){
            readerManageAddTable(data.list);
            readerManagePage(data);
        }
    });
    insertReader();

});
//添加数据并刷新显示数据
function insertReader() {
    $(".readerManage_addbtn").on("click",function () {
        var param = $.param({'currentPage':1})+'&'+$("#reader_addf1").serialize();
        $.ajax({
            url:"reader/addReader",
            type:"post",
            data:param,
            success:function (data) {
                if (data.statusCode == 401) {
                    toastr.error(data.message);
                } else if(data.statusCode==200) {
                    readerManageAddTable(data.value.list);
                    readerManagePage(data.value);
                    $(".readerManage_closebtn").trigger("click");
                }
            }
        })
    })
    
}

//显示表格数据
function readerManageAddTable(list){
    $(".readermanage_tabs").remove();
    $.each(list,function(index, obj){
        var sex=(obj.RE_SEX)==true?"男":"女";
            $(".readerManage_tab").append("<tr class='readermanage_tabs'>" +
                "<td>"+obj.RE_NAME+"</td>" +
                "<td>"+sex+"</td>" +
                "<td>"+obj.RE_PHONE+"</td>" +
                "<td>"+obj.RE_ADDRESS+"</td>" +
                "<td>"+obj.RE_CREDIT+"</td>" +
                "<td><a href='#'>修改信息</a></td>" +
                "<td><a href='#' class='deleteReaderBtn' title='"+obj.RE_ID+"'>删除</a></td>" +
                "</tr>");
    });
    deleteReader();
}

function deleteReader() {
    $(".deleteReaderBtn").on("click",function () {
        var id = $(this).prop("title");
        alert(id)
        $.ajax({
            url:"reader/deleteReader",
            type:"post",
            data:{reId:id,currentPage:1},
            success:function (data) {
                if (data.statusCode == 401) {
                    toastr.error(data.message);
                } else if(data.statusCode==200) {
                    readerManageAddTable(data.value.list);
                    readerManagePage(data.value);
                }
            }
        })
    });

}

//分页方法
function readerManagePage(data){
    //选择器选中分页的div容器，并调用pagination方法来设置分页控件
    $("#readerManagement_page").pagination(data.countResult, {
        //第一个参数指定共多少条记录
        items_per_page : data.maxResult, // 每页显示多少条记录
        next_text : ">", //下一页按钮图标
        prev_text : "<", //上一页按钮图标
        num_display_entries : 5,//主体页数
        num_edge_entries : 2, //边缘页数
        callback : function(index){//定义一个回调函数，用于每次点击页码发起分页查询请求
            var currentPage = ++index;
            $.get("reader/find","currentPage="+currentPage, function(data){
                myscheduleAddTable(data.list);
            });
        }
    });
}

//比较时间,判断是否过期
function scheduleSetDate(schsetDate){
    var mm = schsetDate.split(" ");
    var yy=mm[0].split("-");
    var shi = mm[1].split(":");
    var date1 = new Date();
    date1.setFullYear(yy[0],yy[1]-1,yy[2]);
    date1.setHours(shi[0]);
    date1.setMinutes(shi[1]);
    var date2=new Date();
    //alert(date1);
    //alert(date2);
    if(date1<date2){
        return "已过期";
    }else{
        return "进行中"
    }
}