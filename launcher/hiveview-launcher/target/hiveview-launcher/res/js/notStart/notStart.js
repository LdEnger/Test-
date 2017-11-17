$(document).ready(function () {
    initTable();
    option();
})
var param = {};
function initTable(startName,startE){
    //先销毁表格
    $('#sample_1').bootstrapTable('destroy');
    //初始化表格 动态加载数据
    $('#sample_1').bootstrapTable({
        method: "get",
        url: ctx + "/PortalNotStartInstruction/getPage",
        striped : true,
        clickToselect : false,
        pagination : true,
        pageSize : 10,
        pageNumber : 1,
        pageList : [5,10,15,20,25],
        search : false,
        showColumns : true,
        showRefresh : false,
        sidePagination : "server",
        queryParamsType : "undefined",
        queryParams : function queryParams(params){
            param = {
                page : params.pageNumber,
                rows : params.pageSize,
                appName: startName,
                effective:startE
            };
            return param;
        },
        columns : [[{
            field : 'id',
            title : "ID",
            width : '8%',
            align : 'center',
            formatter : function (value ,row,index) {
                return value;
            }
        },{
            field : 'appName',
            title : "启动名称",
            width : '8%',
            align : 'center',
            formatter : function (value,row ,index) {

                return value ;
            }
        },{
            field : 'apkNames',
            title : "启动APK",
            width : '12%',
            align : 'center',
            formatter : function (value ,row ,index) {
            return value;
            }}
            ,{
                field : 'actionStr',
                title : "指令",
                width : '12%',
                align : 'center',
                formatter : function (value ,row ,index) {
                    return value;
                }}
            ,{
                field : 'effective',
                title : "上下线",
                width : '10%',
                align : 'center',
                formatter : function (value ,row ,index) {
                    if (1 == row.effective) {
                        return '<button style="color:#fff;background:rgb(46,130,255);cursor:default;border-radius:3px 0 0 3px;border-top:1px solid #808080;border-bottom:1px solid #808080;border-left:1px solid #808080;height:26px;border-right:0" class="a_on a_on2">上线</button><a href="javascript:isAPKPublish('+row.id+','+row.effective+')" style="color:rgb(255,102,0);display: inline-block;height:24px;width:40px;border-top:1px solid #808080;border-bottom:1px solid #808080;border-right:1px solid #808080;vertical-align: -1px;line-height: 24px;text-decoration: none;border-radius:0 3px 3px 0">下线</a>';
                    } else {
                        return '<a href="javascript:isAPKPublish('+row.id+','+row.effective+')" style="color:rgb(255,102,0);display: inline-block;height:24px;width:40px;border-top:1px solid #808080;border-bottom:1px solid #808080;border-left:1px solid #808080;vertical-align: -1px;line-height: 24px;text-decoration: none;border-radius:3px 0 0 3px">上线</a><button style="color:#fff;background:rgb(46,130,255);cursor:default;border-radius:0 3px 3px 0;border-top:1px solid #808080;border-bottom:1px solid #808080;border-right:1px solid #808080;height:26px;border-left:0" class="a_on a_on2">下线</button>';
                    }
                }
            },
            {
                field : 'do',
                title : "操作",
                width : '25%',
                align : 'center',
                formatter : function (value ,row) {
                    var str = '';
                    str += '<a href="javascript:edit('+row.id+ ",'" + row.appName + "','" + row.actionStr + "','" + row.startApk +'\' )">编辑</a>';
                    str += '&nbsp;&nbsp;<a href="javascript:del('+row.id+')">删除</a>';
                    return str;
                }
            }]],
        onLoadSuccess: function(){
        },
        onLoadError : function () {
        },
        onClickCell: function (field,value ,row, td) {
            $(td.parent()[0]).css({"background":"rgb(255,184,72)"}).siblings().css({"background":"none"});
        }
    });
}
function isAPKPublish(id,effective) {
    $.post(ctx + "/customRecomContentList/getCount",{'contentId':id,'contentType':12},function (data) {
        if(data > 0){
            alert("数据在“Group管理”中已做关联，无法下线，请先取消关联");
            return;
        }else {
            var confrimInfo = null;
            if(effective == 1){
                var ppt = {
                    'id' : id,
                    'effective': 0
                }
                confrimInfo = "确定置为下线状态";
            }else {
                var ppt = {
                    'id' : id,
                    'effective': 1
                }
                confrimInfo = "确定置为上线线状态";
            }
            if(confirm(confrimInfo)){
                $.post(ctx + "/PortalNotStartInstruction/updateEffice",ppt,function (data) {
                    if(data.returnValue == 0){
                        alert("修改成功");
                        $("#sample_1").bootstrapTable('refresh',param);
                    }else {
                        alert("修改失败");
                    }
                },'json');
            }
        }
    },'json')

}

function add() {
    $("#myModal").modal({backdrop: 'static', keyboard: false});
}


function edit(id,appName,actionStr,startApk) {
    $("#txt_notStartInstructionId").val(id);
    $("#txt_AppName").val(appName);
    $("#txt_startApk").val(startApk);
    $("#txt_actionStr").val(actionStr);
    $("#myModal").modal({backdrop: 'static', keyboard: false});
}
function startInstruction_submit() {
    var id =   $("#txt_notStartInstructionId").val();
    var appName = $("#txt_AppName").val();
    var startApk =   $("#txt_startApk").val();
    var actionStr  =   $("#txt_actionStr").val();

    if($.trim(appName) == ""){
        alert("启动名称不能为空");
        return;
    }
    if($.trim(actionStr) == ""){
        alert("Action指令不能为空");
        return;
    }
    var ppt = {
        'id' :id,
        'appName' : appName,
        'startApk':startApk,
        'actionStr' : actionStr
    }
    if(id != null && id != ''){
        $.post(ctx + "/PortalNotStartInstruction/update",ppt,function (data) {
            if(data.returnValue == 0){
                alert("修改成功");
                $("#sample_1").bootstrapTable('refresh',param);
                clear_diaglog();
            }else {
                alert("修改失败");
            }
        },'json');
    }else {
        $.post(ctx + "/PortalNotStartInstruction/save",ppt,function (data) {
            if(data.returnValue == 0){
                alert("添加成功");
                $("#sample_1").bootstrapTable('refresh',param);
                clear_diaglog1();
            }else {
                alert("添加失败");
            }
        },'json');
    }

}

function clear_diaglog1() {
    $("#txt_notStartInstructionId").val('');
    $("#txt_AppName").val('');
    $("#txt_actionStr").val('');
    $("#myModal").modal('hide');
    $("#sample_1").bootstrapTable('refresh',param);
}
function clear_diaglog() {
    $("#txt_notStartInstructionId").val('');
    $("#txt_AppName").val('');
/*    $("#txt_startApk").val('');*/
    $("#txt_actionStr").val('');
    $("#myModal").modal('hide');
    $("#sample_1").bootstrapTable('refresh',param);
}
function option() {
    var id = '';
    $.ajax({
        type: "post",
        async : false,
        url : ctx + "/apk/getComboboxList",
        dataType:"json",
        data : {'id': id},
        success: function (res) {
            $("#txt_startApk").empty();
            $.each(res ,function (index,element) {
                var options = $("<option value ='" + element['id'] +"'>" + element['apkName'] + "</option>");
                $("#txt_startApk").append(options);
            })
        }
    });
}
function del(id) {
    $.post(ctx+"/customRecomContentList/getCount",{'contentId':id,'contentType':12},function (data) {
        if(data > 0){
            alert("数据在“Group管理”中已做关联，无法删除，请先取消关联");
            return;
        }else {
            if (confirm("是否删除")) {
                $.post(ctx + "/PortalNotStartInstruction/delete", {'id': id}, function (data) {
                    if (data.returnValue == 0) {
                        alert("删除成功");
                        $("#sample_1").bootstrapTable('refresh', param);
                        if ($("#sample_1").bootstrapTable('getData').length == 1) {
                            $("#sample_1").bootstrapTable('prevPage');
                        }
                    } else {
                        alert("删除失败");
                    }
                }, 'json');
            }
        }
    },'json');
}


function selectIsEffective() {
    var startName = $("#start_name").val();
    var startE = $("#txt_start_effective").val();
    initTable(startName,startE);
}
/*


function selectIsEffective() {
    var apkName = $("#apkNameLikeSelect").val();
    var startName = $("#start_name").val();
    var startE = $("#txt_start_effective").val();
    var ppt = {
        url : ctx + "/PortalNotStartInstruction/getPage",
        silent :true,
        query:{
            appName: startName,
            effective:startE

        }
    }
    $("#sample_1").bootstrapTable('refresh',ppt);
}*/
