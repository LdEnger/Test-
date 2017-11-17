var parameter = {};
var titleInfo = "信息提示";
var timeoutValue = 2000;

$(document).ready(function(){
    initTable();
});
function searchTempleteInfo(){
    initTable();
}


function  initTable() {
    var searchTempleteName = $('#searchTempleteName').val();
    //先销毁表格
    $('#customRecomTempleteTable').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#customRecomTempleteTable").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/customRecomTempleteList/getPageList", //获取数据的Servlet地址
        striped: true,  //表格显示条纹
        clickToSelect: false,
        pagination: true, //启动分页
        pageSize: 10,  //每页显示的记录数
        pageNumber: 1, //当前第几页
        pageList: [5, 10, 15, 20, 25],  //记录数可选列表
        search: false,  //是否启用查询
        showColumns: true,  //显示下拉框勾选要显示的列
        showRefresh: false,  //显示刷新按钮
        sidePagination: "server", //表示服务端请求
        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        //设置为limit可以获取limit, offset, search, sort, order
        queryParamsType: "undefined",
        queryParams: function queryParams(params) {//设置查询参数

            param = {
                page: params.pageNumber,
                rows: params.pageSize,
                templeteName : searchTempleteName,
                templeteLeve: 1
            };
            return param;
        },
        columns : [[{
            field :'templeteId',
            title : '模板ID',
            width : '15%',
            align : 'center',
            formatter : function(value, row ,index){
                return value;
            }

        },{
            field : 'templeteName',
            title : '推荐位模板名称',
            width  : '25%',
            align : 'center',
            formatter : function (value,row ,index){
                return value;
            }
        },{
            field : 'do',
            title :'操作',
            width : '40%',
            align : 'center',
            formatter : function(value ,row ){
                var edit = '';
                $.ajax({
                    async : false,
                    type : "post",
                    url : ctx +"/customRecomTempleteList/selectFatherId.json",
                    dataType : "json",
                    data : {'templeteId' : row.templeteId},
                    success : function(data) {
                        if(data > 0){
                            edit = '<span style="color:#7d7d7d">编辑</span>';
                        }else{
                            edit =  '<a href="javascript:editCustomRecomTemplete('+ row.templeteId +')">编辑</a>';
                        }
                    }

                });
                var str = '';
                str += edit;
                str += '&nbsp;&nbsp;<a href="javascript:viewCustomRecomTemplete('+ row.templeteId +')">预览</a>';
                str += '&nbsp;&nbsp;<a href="javascript:delCustomRecomTemplete('+ row.templeteId +')">删除</a>';
                return str;
            }
        }]],
        onClickCell: function (field,value ,row, td) {
            $(td.parent()[0]).css({"background":"rgb(255,184,72)"}).siblings().css({"background":"none"});
        }

    });
}

/*清除画板布局样式*/
function gridsterLayoutFirstClear() {
    var gridster = $("#gridsterLayoutFirst ul").gridster().data('gridster');
    gridster.remove_all_widgets();
}

var size_y1 = 0;

//新增第一个页面模板
function addCustomRecomTemplete(){
    $("#customRecomTempleteName").attr("disabled",false);
    $('#addFirstCustomRecomModel').modal({backdrop: 'static', keyboard: false});
    $('#addFirstCustomRecomModel').modal('show');
    size_y1 = 0;
}

var rrr = 0;
var ccc = 0;
var cWidth = 0;
var cHeight = 0;
// var zWidth = 0;
// var zHeight = 0;
//新增第二个页面模板 添加
function addCustomRecomTemplete1(row,col) {
    var canvas = document.getElementById('a_canvas');
    canvas.height =  canvas.height;
    $('#addTemplete4').show();
    $('#addTemplete5').show();
    $('#submitCustomRecomTemplete').show();
    $("#addTemplete5").removeAttr("disabled");
    $('#modalbody>ul').css({"height":"0px","width":"1px"});
    $('#addFirstCustomRecomModel').modal('hide');
    $("#row").val(row);
    $("#col").val(col);
    $("#templeteId").val('');
    $("#customRecomTempleteName").val('');
    $('#customRecomTempleteModal').modal({backdrop: 'static', keyboard: false});
    $('#customRecomTempleteModal').modal('show');

    gridsterLayoutFirstClear();
    var gridster = $("#gridsterLayoutFirst ul").gridster().data('gridster');
    gridster.enable( );
    gridster.enable_resize();

    if(col == 255){
        $("#addTemplete5").css({"display":"inline-block"});
    }else{
        $("#addTemplete5").css({"display":"none"});
    }

    rrr = Math.floor(1920/row);
    ccc = Math.floor(1080/col);


    var canvas = document.getElementById('a_canvas');
    // console.log(11111);
    canvas.width =  (rrr*row/3)*2+rrr;
    canvas.height =  ccc*col/3+ccc*2;

    drawTables(canvas, rrr*2, ccc);

    cWidth = (rrr*row/3)*2+rrr;
    cHeight = ccc*col/3+ccc*2;

    if(col == 255){
        $("#gridsterLayoutFirst").css({"width":"1200px"});
    } else if(col == 105){
        $("#gridsterLayoutFirst").css({"width":"1238px"});
    }else if(col == 245){
        $("#gridsterLayoutFirst").css({"width":"1205px"});
    }else if(col == 95){
        $("#gridsterLayoutFirst").css({"width":"1200px"});
    };

    // 设置p的高度
    $('#gridsterLayoutFirst').find('p').remove();
    $('#gridsterLayoutFirst').append('<p style="position: absolute;top:0;left:640px;width: 0px;background:red;height:348px;border-right:1px solid #000;"></p>');

    $("#gridsterLayoutFirst p").css({"height":cHeight});
    // $("#gridsterLayoutFirst span").css({"left":"640px"});
    $("#gridsterLayoutFirst ul").css({"width":"1px"});
    $('.modal-body').animate({scrollLeft:0}, 'slow');

}





/*创建布局组*/
function addTempleteLayoutTeam1(value) {
    addTempleteLayout1(value);
}

// function addTempleteLayoutTeam2() {
//     addTempleteLayout2();
// }

/* 创建按钮 */

var array1 = [];
var type = 0;

var count = 0;
function addTempleteLayout1(value) {

    var rows = ($("#row").val() - 0) / 3;
    var cols = ($("#col").val() - 0) / 3;


    if (Math.ceil(rows) == 99 && Math.ceil(cols) == 85) {
        type = 1;
    } else if (Math.ceil(rows) == 55 && Math.ceil(cols) == 35) {
        type = 2;
    } else if (Math.ceil(rows) == 59 && Math.ceil(cols) == 82) {
        type = 3;
    } else if (Math.ceil(rows) == 99 && Math.ceil(cols) == 32) {
        type = 4;
    }

    var last_uuid = $("#uuid").val();

    if ($("#uuid").val() == "") {
        var size_x = 1;
        var size_y = 0;

        var bigsizex = $('#' + last_uuid).attr('data-sizex') - 0;
        var bigsizey = $('#' + last_uuid).attr('data-sizey') - 0;
    } else {
        var size_x = $('#' + last_uuid).attr('data-row') - 0;
        var size_y = $('#' + last_uuid).attr('data-col') - 0;

        var bigsizex = $('#' + last_uuid).attr('data-sizex') - 0;
        var bigsizey = $('#' + last_uuid).attr('data-sizey') - 0;
    }
    ;

    var uuid = (new Date().getTime()) ^ Math.random();
    // alert(uuid);
    var gridster = $("#gridsterLayoutFirst ul").gridster().data('gridster');
    // console.log(gridster);
    var title = '<input type="button" class="remove" value="关闭" />';
    var position = '<input id="position' + uuid + '" value="1" type="hidden"/>';
    var li = '<li class="new" id=' + uuid + '>' + title + position + '</li>';
    gridster.resize_widget_dimensions({widget_base_dimensions: [rows, cols]});
    if (value == 1) {

        // console.log(size_y,size_x);
        if (bigsizex == 3 && bigsizey == 2) {
            if (size_y1 != 0) {
                gridster.add_widget(li, 1, 1, size_y1, size_x);
                size_y1 = 0;
            } else {
                gridster.add_widget(li, 1, 1, size_y + 3, size_x);
            }

        } else {
            if (size_y1 != 0) {
                gridster.add_widget(li, 1, 1, size_y1, size_x);
                size_y1 = 0;
            } else {
                gridster.add_widget(li, 1, 1, size_y + 1, size_x);
            }

        }
    } else if (value == 2) {
        if (size_y1 != 0) {
            gridster.add_widget(li, 3, 2, size_y1, size_x);
            size_y1 = 0;
        } else {
            gridster.add_widget(li, 3, 2);
        }

        $("#addTemplete5").attr("disabled", true);
    }
    $("#uuid").val(uuid);  //把id赋给$("#uuid"),供下一次添加模块的时候,判断位置使用
    maxRow(type, uuid);    //点击下面的按钮,添加模块,如果超出了下边界,就自动删掉

    //让添加视频模块失效和生效的方法开始
    var removes = [];
    $(".remove").each(function (ele, index) {
        removes.push($(this));

    });

    for (var i = 0; i < removes.length; i++) {
        if (removes[i].parent().attr("data-sizex") == 3) {
            removes[i].click(function () {
                $("#addTemplete5").removeAttr("disabled");
            });
        }
    }
    //让添加视频模块失效和生效的方法结束

    // var ul_width = $("#gridsterLayoutFirst>ul").width();
    // $("#gridsterLayoutFirst").css({"width":ul_width+rows});
    // alert($('#'+uuid).attr('data-col'));
    //让画布背景随着添加模块的增多延长的方法开始
    //     array1.push($('#'+uuid).attr('data-col'));
    //     var max = array1[0];
    //     for(var j = 1;j<array1.length;j++){
    //
    //         if(max<array1[j]){
    //             max = array1[j];
    //         }
    //     }

    // if($('#'+uuid).attr('data-col')*$('#'+uuid).width()>=640){
    //     alert(0);
    //     // $('#gridsterLayoutFirst ul').css("width", max*$('#'+uuid).width()+$('#'+uuid).attr('data-col')*2);
    // }
    // count++;
    // var widthh = $('#gridsterLayoutFirst ul li').width();
    // $('#gridsterLayoutFirst ul li').width( widthh+1);
    // var ulWidth = $('#gridsterLayoutFirst ul').width()-1*count;
    $('#gridsterLayoutFirst ul').css({"height": "360px"});


    // console.log(ulWidth);
    //让画布背景随着添加模块的增多延长的方法结束
    // 滚动条滚动开始
    // var count = 1;
    // if($('#'+uuid).attr('data-col')*$('#'+uuid).width()>640) {
    //     count++;
    //     $('#modalbody').animate({scrollLeft: $('#'+uuid).width()*count}, "slow");
    //
    // };
    //滚动条滚动结束
    //     fit_to_content();
    // console.log($("#"+last_uuid).attr("data-col")-0);
    // console.log(cWidth);

    if (type == 1) {
        if (($("#" + uuid).attr("data-sizex") - 0)  == 3){
            // alert(3);
           if( (($("#" +uuid).attr("data-col") - 0) + ($("#" + uuid).attr("data-sizex") - 0))/ 12 > 1){
               var ww = (($("#" +uuid).attr("data-col") - 0) + ($("#" + uuid).attr("data-sizex") - 0)-((($("#" +uuid).attr("data-col") - 0) + ($("#" + uuid).attr("data-sizex") - 0))% 12))/12;
               var canvas = document.getElementById('a_canvas');
               canvas.height = canvas.height;

               canvas.width = cWidth * (ww + 1);
               canvas.height = cHeight;
               drawTables(canvas, rrr * 2 * (ww + 1), ccc);
           }
        }
        if (($("#" + last_uuid).attr("data-col") - 0) % 12 == 0) {
            var ww = ($("#" + last_uuid).attr("data-col") - 0) / 12;
            var canvas = document.getElementById('a_canvas');
            canvas.height = canvas.height;

            canvas.width = cWidth * (ww + 1);
            canvas.height = cHeight;
            drawTables(canvas, rrr * 2 * (ww + 1), ccc);
            // console.log(type);
        }
    } else if (type == 2) {
        if (($("#" + last_uuid).attr("data-col") - 0) % 22 == 0) {
            var ww = ($("#" + last_uuid).attr("data-col") - 0) / 22;
            // console.log(ww);
            var canvas = document.getElementById('a_canvas');
            canvas.height = canvas.height;

            canvas.width = cWidth * (ww + 1);
            canvas.height = cHeight;
            drawTables(canvas, rrr * 2 * (ww + 1), ccc);
        }
    } else if (type == 3) {
        if (($("#" + last_uuid).attr("data-col") - 0) % 20 == 0) {
            var ww = ($("#" + last_uuid).attr("data-col") - 0) / 20;
            // console.log(ww);
            var canvas = document.getElementById('a_canvas');
            canvas.height = canvas.height;

            canvas.width = cWidth * (ww + 1);
            canvas.height = cHeight;
            drawTables(canvas, rrr * 2 * (ww + 1), ccc);
        }
    } else if (type == 4) {
        if (($("#" + last_uuid).attr("data-col") - 0) % 12 == 0) {
            var ww = ($("#" + last_uuid).attr("data-col") - 0) / 12;
            // console.log(ww);
            var canvas = document.getElementById('a_canvas');
            canvas.height = canvas.height;

            canvas.width = cWidth * (ww + 1);
            canvas.height = cHeight;
            drawTables(canvas, rrr * 2 * (ww + 1), ccc);
        }
    }




        var ps = ($('#gridsterLayoutFirst ul').width()-$('#gridsterLayoutFirst ul').width()%640)/640;
        // console.log("ul的宽度"+$('#gridsterLayoutFirst ul').width());
        if(ps>1){
                $('#gridsterLayoutFirst').find('p').remove();
                for(var x = 1;x <= ps;x++){
                    var $p = "<p style='position: absolute;top:0;left:"+640*x+"px;width: 0px;background:red;height:348px;border-right:1px solid #000;'></p>";

                    $('#gridsterLayoutFirst').append($p);
                };

        };
    $("#gridsterLayoutFirst p").css({"height":cHeight});
};





// function addTempleteLayout2() {
//     var uuid = (new Date().getTime()) ^ Math.random();
//     // alert(uuid);
//     var gridster = $("#gridsterLayoutFirst ul").gridster().data('gridster');
//     // console.log(gridster);
//     var title = '<input type="button" class="remove" value="关闭" />'
//     var position = '<input id="position'+uuid+'" value="1" type="hidden"/>';
//     var li = '<li class="new" id=' + uuid + '>' + title+position+ '</li>';
//     // gridster.resize_widget_dimensions({widget_base_dimensions: [rows, cols]});
//     gridster.add_widget(li, 2.89, 2);
//
//     $("#uuid").val(uuid);  //把id赋给$("#uuid"),供下一次添加模块的时候,判断位置使用
//     maxRow(uuid);    //点击下面的按钮,添加模块,如果超出了下边界,就自动删掉
//     array1.push($('#'+uuid).attr('data-col'));
//     var max = array1[0];
//     for(var j = 1;j<array1.length;j++){
//
//         if(max<array1[j]){
//             max = array1[j];
//         }
//     }
//
//     if($('#'+uuid).attr('data-col')*$('#'+uuid).width()>=640){
//
//         $('#gridsterLayoutFirst ul').css("width", max*$('#'+uuid).width()+$('#'+uuid).attr('data-col')*2);
//
//     }
//
//     $('#gridsterLayoutFirst ul').css({"height":"360px"});
// };


// 保存按钮
function submitCustomRecomTemplete() {
    var gridster = $("#gridsterLayoutFirst ul").gridster().data('gridster');
    var json = gridster.serialize();
    var layoutJson = Gridster.sort_by_row_and_col_asc(json);
    var lock = false;
    var lis = [];

    // console.log(layoutJson);
    // console.log(type);
    $.each(layoutJson,
        function() {
            if(type == 1){
                var row=this.row;
                var size_y=this.size_y;
                var sum=(row-0)+(size_y-0);
                if(sum>5){
                    lock = true;
                    lis.push(this.id);
                }
            };

            if(type == 2){
                var row=this.row;
                var size_y=this.size_y;
                var sum=(row-0)+(size_y-0);
                if(sum>11){
                    lock = true;
                    lis.push(this.id);
                }
            };

            if(type == 3){
                var row=this.row;
                var size_y=this.size_y;
                var sum=(row-0)+(size_y-0);
                if(sum>5){
                    lock = true;
                    lis.push(this.id);

                }
            };

            if(type == 4){
                var row=this.row;
                var size_y=this.size_y;
                var sum=(row-0)+(size_y-0);
                if(sum>12){
                    lock = true;
                    lis.push(this.id);

                }
            };




        });

    if(lock == true){
        if (!confirm("您的模块超出底框,点击确定将它们删除？点击取消手动纠正")) {
            return;

        }
        for(var i =0;i<lis.length;i++){
            gridster.remove_widget($("#"+lis[i]));
        }

    }


    var dialog = $('#customRecomTempleteModal');
    var templeteName = $("#customRecomTempleteName").val();
    var templeteId = $("#templeteId").val();
    // var gridster = $("#gridsterLayoutFirst ul").gridster().data('gridster');
    saveTemplete(templeteName, templeteId, "这是矩阵", dialog, gridster);  //调用下面的238行的保存的方法
    /*searchLogoLicenseInfo();*/
    /*$('#customRecomTempleteModal').modal('hide');*/
}

/* 删除按钮 */
function delCustomRecomTemplete(templeteId) {
    if (!confirm("是否删除？")) {
        return;
    }
    $.post(ctx +"/customRecomTempleteList/delete.json", {
        'templeteId' : templeteId
    }, function(data) {
        console.log(data.returnValue);
        if (data.returnValue == 0) {
            $('#customRecomTempleteModal').modal('hide');
            alert( '删除成功！');
            $("#customRecomTempleteTable").bootstrapTable('refresh', param);
            if($("#customRecomTempleteModal").bootstrapTable('getData').length == 1){ //删除最后一页的最后一条,自动跳到上一页
                $("#customRecomTempleteModal").bootstrapTable('prevPage'); //删除最后一页的最后一条,自动跳到上一页
            }

        } else {
            alert( "删除失败！矩阵已经被使用。");
        }
    }, "json");
}

/*保存*/
function saveTemplete(templeteName, templeteId, contentName, dialog, gridster) {
    var json = gridster.serialize();// 得到 所有的 widget    //拿到所有的布局信息,向后台传数据方法1 拿到的是templeteGridster.js 中48行中的所有数据
    var layoutJson = JSON.stringify(json);////拿到所有的布局信息,向后台传数据方法2
    var col =$("#col").val();
    var row = $("#row").val();
    // 判断页面填写是否为空
    if (templeteName == null || $.trim(templeteName) == "") {
        alert( "名称不能为空！");
        return;
    }
    if (layoutJson.length == 2) {
        alert( "矩阵不能为空！");
        return;
    }
    if (templeteId == 0) {  //如果有templeteId并且templeteId是0,为新增(添加)
        $.post(ctx +'/customRecomTempleteList/saveTemplete.json', {
            'templeteName' : templeteName,
            'contentName' : contentName,
            'col' : parseInt(col),
            'row' : parseInt(row),
            'layoutJson' : layoutJson,
        }, function(data) {
            if (data.returnValue == 0) {
                alert( '添加成功！');
                $('#customRecomTempleteModal').modal('hide');
                $("#customRecomTempleteTable").bootstrapTable('refresh', parameter);
            } else {
                alert( "添加失败！");
            }
        }, "json");
    } else {
        var col =null;
        var row =null;
        $.ajax({
            type:"post",
            async: false,
            url:ctx + "/customRecomTempleteList/getRolCow",
            data:{templeteId : templeteId},
            dataType:"json",
            success:function (res) {
                col = res[0].row;
                row = res[0].col;

            }}, "json");


        //如果templeteId没有 就是更新
        $.post(ctx +"/customRecomTempleteList/updateTempleteLayout.json", {
            'templeteName' : templeteName,
            'contentName' : contentName,
            'templeteId' : templeteId,
            'col' : parseInt(col),
            'row' : parseInt(row),
            'layoutJson' : layoutJson,
        }, function(data) {
            if (data.returnValue == 0) {
                alert( '更新成功！');
                $('#customRecomTempleteModal').modal('hide');
                $("#customRecomTempleteTable").bootstrapTable('refresh', parameter);
            } else if(data.returnValue == '-1'){
                alert( "更新失败！模板在推荐位管理中被使用。");
            }else{
                alert( "更新失败！");
            }
        }, "json");
    }
    initTable();

}

/* 编辑按钮 */
function editCustomRecomTemplete(templeteId) {
    $('#addTemplete4').show();

    $('#addFirstCustomRecomModel').hide();

    $('#submitCustomRecomTemplete').show();
    $('#clearCustomRecomTemplete').show();

    $("#customRecomTempleteName").attr("disabled",false);

    $('.modal-body').animate({scrollLeft:0}, 'slow');
    $('.modal-body').animate({scrollLeft:0}, 'slow');
    // console.log($('.modal-body').scrollLeft());
    // $('.editLayout').show();
    gridsterLayoutFirstClear();
    $.post(ctx +'/customRecomTempleteList/getCustomRecomTemplete.json', {
        'templeteId' : templeteId
    }, function(data) {
        if (data != null) {
            $('#templeteId').val(data.templeteId);
            $('#customRecomTempleteName').val(data.templeteName);
            $('#customRecomTempleteModal').modal({backdrop: 'static', keyboard: false});
            $('#customRecomTempleteModal').modal('show');
            getLayout(templeteId, 0);                                                            //调用getLayout方法
            gridsterEnable();


        } else {
            alert( "网络异常！");
        }
    }, "json");

}

// 预览按钮
function viewCustomRecomTemplete(templeteId) {
    $('#addFirstCustomRecomModel').hide();
    $("#submitCustomRecomTemplete").hide();
    $("#customRecomTempleteName").attr("disabled",true);
    $('#modalbody').animate({scrollLeft:0}, 'slow');
    $('#addTemplete4').hide();
    $('#addTemplete5').hide();
    $.post(ctx +'/customRecomTempleteList/getCustomRecomTemplete.json', {
        'templeteId' : templeteId
    }, function(data) {
        if (data != null) {
            $('#templeteId').val(data.templeteId);
            $('#customRecomTempleteName').val(data.templeteName);
            $('#customRecomTempleteModal').modal({backdrop: 'static', keyboard: false});
            $('#customRecomTempleteModal').modal('show');
            getLayout(templeteId, 1);// ，1代表是预览模式。                                         //调用getLayout方法
            gridsterDisable();                               //让gridster失效,不能够在拖动模块
        } else {
            $.messager.alert( "网络异常！");
        }
    }, "json");

}

//取消按钮的功能
function clearCustomRecomTemplete() {
    $('#customRecomTempleteModal').modal('hide');
    gridsterLayoutFirstClear();
}

function searchLogoLicenseInfo() {
    var opt = {
        url: ctx + "/customRecomTempleteList/getPageList",
        silent: true,
        query:{
        }
    };
    $("#customRecomTempleteTable").bootstrapTable('refresh', opt);
}