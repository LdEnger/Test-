
$(document).ready(function () {
    $("#searchType").val("");
    $("#searchEffictive").val("");
    initTable();
});

function initTable() {
    var type = $("#searchType").val();
    var effective = $("#searchEffective").val();
    //先销毁表格
    $('#jumpInstructionTable').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#jumpInstructionTable").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/jumpInstruction/getPageList", //获取数据的Servlet地址
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
        queryParams: function queryParams(params) {   //设置查询参数
            param = {
                type:type,
                effective:effective,
                page: params.pageNumber,
                rows: params.pageSize
            };
            return param;
        },
        columns : [ [ {
            field : 'id',
            title : 'ID',
            width : '5%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        },{
            field : 'type',
            title : '启动名称',
            width : '20%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        }, {
            field : 'actionName',
            title : 'action名称',
            width : '20%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        },{
            field : 'startApkName',
            title : 'APK名称',
            width : '20%',
            align : 'center',
            formatter : function(value, row, index) {
                if(value<0){
                    return "";
                }
                return value;
            }
        }, {
            field : 'effective',
            title : '状态',
            width : '20%',
            align : 'center',
            formatter : function(value, row) {
                var str = '';
                if(value == 0){
                    str += '<button style="color:#fff;background:rgb(46,130,255);cursor:default;border-radius:3px 0 0 3px;border-top:1px solid #808080;border-bottom:1px solid #808080;border-left:1px solid #808080;height:26px;border-right:0">上线</button>';
                    str += '<a href="javascript:downJumpInstruction(' + row.id + ')" style="color:rgb(255,102,0);display: inline-block;height:24px;width:40px;border-top:1px solid #808080;border-bottom:1px solid #808080;border-right:1px solid #808080;vertical-align: -1px;line-height: 24px;text-decoration: none;border-radius:0 3px 3px 0">下线</a>';
                }else {
                    str += '<a href="javascript:upJumpInstruction('+ row.id +')" style="color:rgb(255,102,0);display: inline-block;height:24px;width:40px;border-top:1px solid #808080;border-bottom:1px solid #808080;border-left:1px solid #808080;vertical-align: -1px;line-height: 24px;text-decoration: none;border-radius:3px 0 0 3px">上线</a>';
                    str += '<button style="color:#fff;background:rgb(46,130,255);cursor:default;border-radius:0 3px 3px 0;border-top:1px solid #808080;border-bottom:1px solid #808080;border-right:1px solid #808080;height:26px;border-left:0">下线</button>';
                }
                return str;
            }
        }, {
            field : 'do',
            title : '操作',
            width : '20%',
            align : 'center',
            formatter : function(value, row) {
                var str = '';
                str += '<a href="javascript:editJumpInstruction('+row.id+",'"+row.type+"','"+row.actionName+"',"+row.startApk+')">编辑</a>';
                str += '&nbsp;|&nbsp; <a href="javascript:delJumpInstruction(' + row.id + ')">删除</a>';
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
/*查询按钮*/
function searchJumpInstructionInfo() {
    initTable();
}
var flag='0';
/*添加按钮*/
function addJumpInstruction() {
    flag='1';
    option();
    $('#id').val("").removeAttr("readonly");
    $('#type').val("");
    $('#actionName').val("");
    $('#txt_startApk').val(-1);
    $('#editJumpInstructionDiv').modal({backdrop: 'static', keyboard: false});
    $('#editJumpInstructionDiv').modal('show');
}
/*编辑按钮*/
function editJumpInstruction(id,type,actionName,startApk) {
    flag='0'
    option();
    console.log(id);
    $('#id').val(id).attr("readonly","readonly");
    $('#type').val(type);
    $('#actionName').val(actionName);
    $('#txt_startApk').val(startApk);
    $('#editJumpInstructionDiv').modal({backdrop: 'static', keyboard: false});
    $('#editJumpInstructionDiv').modal('show');
}
//下拉菜单数据初始化
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
            $("#txt_startApk").append("<option value='-1'>不填</option>");
            $.each(res ,function (index,element) {
                var options = $("<option value ='" + element['id'] +"'>" + element['apkName'] + "</option>");
                $("#txt_startApk").append(options);
            })
        }
    });
}
/*删除按钮*/
function delJumpInstruction(id) {
    var param = {};
    if (confirm("是否确认删除？")) {
        var JumpInstruction = {
            'id' : id
        };
        $.post(ctx + "/jumpInstruction/delete", JumpInstruction, function(data) {
            if (data.returnValue == 0) {
                alert( '删除成功！');
                $("#jumpInstructionTable").bootstrapTable('refresh', param);
/*                if($("#jumpInstructionTable").bootstrapTable('getData').length == 1){
                    $("#jumpInstructionTable").bootstrapTable('prevPage');
                }*/
            }else if(data.returnValue == 2){
                alert( 'group推荐位有关联！不可删除！');
            }else if(data.returnValue == 3){
                alert( 'group备份有关联！不可删除！');
            }else {
                alert( '删除失败！');
            }
        }, "json");
    }
}
/*上下线*/
function downJumpInstruction(id) {
    JumpInstruction_param = {id : id,effective:'1'};
    var param = {};
    $.post(ctx + "/jumpInstruction/update", JumpInstruction_param, function(data) {
        if (data.returnValue == 0) {
            alert( '下线成功！');
            $("#editJumpInstructionDiv").modal('hide');
            $("#jumpInstructionTable").bootstrapTable('refresh', param);
        } else {
            alert( '下线失败！');
        }
    }, "json");
}
function upJumpInstruction(id) {
    JumpInstruction_param = {id : id,effective:'0'};
    var param = {};
    $.post(ctx + "/jumpInstruction/update", JumpInstruction_param, function(data) {
        if (data.returnValue == 0) {
            alert( '上线成功！');
            $("#editJumpInstructionDiv").modal('hide');
            $("#jumpInstructionTable").bootstrapTable('refresh', param);
        } else {
            alert( '上线失败！');
        }
    }, "json");
}
/*保存编辑内容*/
function submitJumpInstructionInfo() {
    var id =  $('#id').val();
    var type = $('#type').val();
    var actionName = $('#actionName').val();
    var startApk = $("#txt_startApk").val();
    if (id == "") {
        alert( 'id不能为空！');
        return;
    }

    if (type == "") {
        alert( '请选择类型！');
        return;
    }
    if (actionName == "") {
        alert( 'action名称不能为空！');
        return;
    }
    var param = {};

    var jumpInstruction_param = {
        'id':id,
        'type':type,
        'actionName' : actionName,
        'startApk':startApk
    };
    $.post(ctx + "/jumpInstruction/getPageList?page=1&rows=1&type="+type, function(data) {
        var a = data.rows;
        for (var i=0;i<1;i++){
            if(data.total != 0 && a[i].id!=id ){
                alert( '已存在该启动指令！');
            }else {
                if(flag=='0'){
                    $.post(ctx + "/jumpInstruction/update", jumpInstruction_param, function(data) {
                        if (data.returnValue == 0) {
                            alert( '修改成功！');
                            $("#editJumpInstructionDiv").modal('hide');
                            $("#jumpInstructionTable").bootstrapTable('refresh', param);
                        } else {
                            alert( '修改失败！');
                        }
                    }, "json");

                }else{
                    $.post(ctx + "/jumpInstruction/getPageList?page=1&rows=2&id="+id, function(data) {
                        console.log(data);
                        if(data.total!=0){
                            alert( 'id已存在，请检查！');
                        }else{
                            $.post(ctx + "/jumpInstruction/add", jumpInstruction_param, function(data) {
                                console.log(data);
                                if (data.returnValue == 0) {
                                    alert( '新增成功！');
                                    $("#editJumpInstructionDiv").modal('hide');
                                    $("#jumpInstructionTable").bootstrapTable('refresh', param);
                                } else {
                                    alert( '新增失败！');
                                }
                            }, "json");
                        }
                    }, "json");

                }
            }
        }


    }, "json");
}
