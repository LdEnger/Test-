/**
 * Created by Administrator on 2017/7/10.
 */

$(document).ready(function () {
    initTable();
    init();
});

function select() {
    var appName=$("#appname").val();
    var isEffective=$("#txt_selectIsEffective").val();
    console.log(appName);
    console.log(isEffective);
    initTable(appName,isEffective);
}
var param ={};
function initTable(appName,isEffective) {
    //先销毁表格
    $('#sample_1').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#sample_1").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/start/getPageList", //获取数据的Servlet地址
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
                appName:appName,
                isEffective:isEffective
            };
            return param;
        },
        columns : [ [ {
            field : 'id',
            title : 'ID',
            width : '8%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        },{
            field : 'appName',
            title : '启动名称',
            width : '13%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        }/*, {
            field : 'startType',
            title : '启动类型',
            width : '13%',
            align : 'center',
            formatter : function(value) {
                if(value == 1){
                    value = '开机启动';
                }
                if(value == 0){
                    value = '非开机启动';
                }
                return value;
            }
        }*/, {
            field : 'instructionType',
            title : '指令类型',
            width : '13%',
            align : 'center',
            formatter : function(value) {
                if(value == 1){
                    value = '包名';
                }
                if(value == 0){
                    value = 'Action名';
                }
                return value;
            }
        }, {
            field : 'characterString',
            title : '字符串',
            width : '20%',
            align : 'center',
            formatter : function(value) {
                return value;
            }
        }, {
            field : 'isEffective',
            title : '上下线',
            width : '13%',
            align : 'center',
            formatter : function(value,row) {
                var str = '';
                if(value==1){
                    str += '<button style="color:#fff;background:rgb(46,130,255);cursor:default;border-radius:3px 0 0 3px;border-top:1px solid #808080;border-bottom:1px solid #808080;border-left:1px solid #808080;height:26px;border-right:0">上线</button>';
                    str += '<a href="javascript:down('+row.id+')" style="color:rgb(255,102,0);display: inline-block;height:24px;width:40px;border-top:1px solid #808080;border-bottom:1px solid #808080;border-right:1px solid #808080;vertical-align: -1px;line-height: 24px;text-decoration: none;border-radius:0 3px 3px 0">下线</a>';
                }else if(value == 0){
                    str += '<a href="javascript:up('+row.id+')" style="color:rgb(255,102,0);display: inline-block;height:24px;width:40px;border-top:1px solid #808080;border-bottom:1px solid #808080;border-left:1px solid #808080;vertical-align: -1px;line-height: 24px;text-decoration: none;border-radius:3px 0 0 3px">上线</a>';
                    str += '<button style="color:#fff;background:rgb(46,130,255);cursor:default;border-radius:0 3px 3px 0;border-top:1px solid #808080;border-bottom:1px solid #808080;border-right:1px solid #808080;height:26px;border-left:0">下线</button>';
                }
                return str;
            }
        }, {
            field : 'do',
            title : '操作',
            width : '13%',
            align : 'center',
            formatter : function(value, row) {
                var str = '';
                str += '<a href="javascript:edit('+row.id+",'"+row.appName+"',"/*+row.startType+","*/+row.instructionType+",'"+row.characterString+"'"+')">编辑</a>';
                str += '&nbsp;|&nbsp; <a href="javascript:del('+row.id+')">删除</a>';
                return str;
            }
        } ] ],
        onLoadSuccess: function () {  //加载成功时执行
            //layer.msg("加载成功");
        },
        onLoadError: function () {  //加载失败时执行
            // layer.msg("加载数据失败", {time: 1500, icon: 2});
        },
        onClickCell: function (field,value ,row, td) {
            $(td.parent()[0]).css({"background":"rgb(255,184,72)"}).siblings().css({"background":"none"});
        }
    });
}
function down(id) {
    start = {id : id,isEffective:0};
    var param = {};
    $.post(ctx + "/start/update", start, function(data) {
        if (data.returnValue == 0) {
            alert( '下线成功！');
            $("#sample_1").bootstrapTable('refresh', param);
        }else if(data.returnValue == 2){
            alert('数据被豆腐块关联，不可下线');
        }else if(data.returnValue == 3){
            alert('数据被launcher关联，不可下线');
        }else {
            alert( '下线失败！');
        }
    }, "json");
}
function up(id) {
    start = {id : id,isEffective:1};
    var param = {};
    $.post(ctx + "/start/update", start, function(data) {
        if (data.returnValue == 0) {
            alert( '上线成功！');
            $("#sample_1").bootstrapTable('refresh', param);
        } else {
            alert( '上线失败！');
        }
    }, "json");
}
function init() {
    $("#btn_add").click(function () {
        // $("#myModalLabel").text("桌面图标过滤管理");
        $('#myModal').modal({backdrop: 'static', keyboard: false});
    });
}
/*function  add() {
    clear_diaglog();
    $('#myModal').modal({backdrop: 'static', keyboard: false});
}*/
function  clear_diaglog(){
    $('#txt_startInstructionId').val('');
    $('#txt_AppName').val('');
    //$('#txt_startType').val('1');
    $('#txt_instructionType').val('1');
    $('#txt_characterString').val('');
    $("#myModal").modal('hide');
}
function  edit(id,appName/*,startType*/,instructionType,characterString) {
    $('#txt_startInstructionId').val(id);
    $('#txt_AppName').val(appName);
    //$('#txt_startType').val(startType);
    $('#txt_instructionType').val(instructionType);
    $('#txt_characterString').val(characterString);
    $("#myModal").modal({backdrop: 'static', keyboard: false});
}
function  del(id) {
    if (confirm("是否确认删除？")) {
        var startInstruction = {
            'id' : id,
        };
        $.post(ctx + "/start/del", startInstruction, function(data) {
            if (data.returnValue == 0) {
                alert( '成功！');
                $("#myModal").modal('hide');
                $("#sample_1").bootstrapTable('refresh', param);
                if($("#sample_1").bootstrapTable('getData').length == 1){
                    $("#sample_1").bootstrapTable('prevPage');
                }
            }else if(data.returnValue == 1){
                alert( '数据被launcher关联，不可删除！');
                $("#myModal").modal('hide');
            }else if(data.returnValue == 2){
                alert( '数据被豆腐块关联，不可删除！');
                $("#myModal").modal('hide');
            }else {
                alert( '失败！');
            }
        }, "json");
    }
}
function startInstruction_submit() {
    var id =$('#txt_startInstructionId').val();
    var appName = $('#txt_AppName').val();
    //var startType = $('#txt_startType').val();
    var instructionType = $('#txt_instructionType').val();
    var characterString = $('#txt_characterString').val();
    if ($.trim(appName) == "") {
        alert("应用名称不能为空！");
        return;
    }
    // if ($.trim(startType) == "") {
    //     alert("开机类型不能为空！");
    //     return;
    // }
    if ($.trim(instructionType) == "") {
        alert("指令类型不能为空！");
        return;
    }
    if ($.trim(characterString) == "") {
        alert("字符串不能为空！");
        return;
    }

    if(id !=null && id !=''){
        var startInstruction = {
            'appName' : appName,
            //'startType' :startType,
            'instructionType' : instructionType,
            'characterString' : characterString,
            'id' :id
        };
        $.post(ctx + "/start/update", startInstruction, function(data) {
            if (data.returnValue == 0) {
                alert( '修改成功！');
                $("#myModal").modal('hide');
                clear_diaglog();
                $("#sample_1").bootstrapTable('refresh', param);
            }else if(data.returnValue == 1){
                alert( '修改字符串重复，请重新修改！');
            } else {
                alert( '修改失败！');
            }
        }, "json");

    }else{
        var startInstruction = {
            'appName' : appName,
            //'startType' :startType,
            'instructionType' : instructionType,
            'characterString' : characterString,
            'id' :id,
            'isEffective':0
        };
        $.post(ctx + "/start/save", startInstruction, function(data) {
            if (data.returnValue == 0) {
                alert( '新增成功！');
                $("#myModal").modal('hide');
                clear_diaglog();
                $("#sample_1").bootstrapTable('refresh', param);
            }else if(data.returnValue == 1){
                alert( '添加字符串重复，请重新添加！');
            } else {
                alert( '新增失败！');
            }
        }, "json");
    }
}
