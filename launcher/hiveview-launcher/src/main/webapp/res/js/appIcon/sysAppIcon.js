/**
 * Created by Administrator on 2017/7/10.
 */

$(document).ready(function () {
    initTable();
    init();
});
var param ={};
function initTable() {
    //先销毁表格
    $('#sample_1').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#sample_1").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/appIcon/getPageList", //获取数据的Servlet地址
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
                rows: params.pageSize
            };
            return param;
        },
        columns : [ [ /*{
            field : 'id',
            title : 'ID',
            width : '8%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        },*/{
            field : 'appName',
            title : '应用名称',
            width : '13%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        }, {
            field : 'appPackage',
            title : '包名',
            width : '18%',
            align : 'center',
            formatter : function(value) {
                return value;
            }
        }, {
            field : 'isShow',
            title : '是否显示',
            width : '12%',
            align : 'center',
            formatter : function(value) {
                if(value == 1){
                    value = '是';
                }
                if(value == 0){
                    value = '否';
                }
                return value;
            }
        }, {
            field : 'do',
            title : '操作',
            width : '14%',
            align : 'center',
            formatter : function(value, row) {
                var str = '';
                str += '<a href="javascript:edit('+row.id+",'"+row.appName+"','"+row.appPackage+"',"+row.isShow+')">编辑</a>';
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
    $('#txt_sysAppIconId').val('');
    $('#txt_sysAppName').val('');
    $('#txt_sysPackageName').val('');
    $('#txt_sysIsShow').val('1');
    $("#myModal").modal('hide');
}
function  edit(id,appName,appPackage,isShow) {
    // var selects = $('#sample_1').bootstrapTable('getSelections');
    // var newSelects = $.parseJSON(JSON.stringify(selects));
    // if(typeof(selects) == 'undefined'){
    //     alert("请选中一行")
    // }else{ alert(selects.recommendName);}
    $('#txt_sysAppIconId').val(id);
    $('#txt_sysAppName').val(appName);
    $('#txt_sysPackageName').val(appPackage);
    $('#txt_sysIsShow').val(isShow);
    $("#myModal").modal({backdrop: 'static', keyboard: false});
}
function  del(id) {
    if (confirm("是否确认删除？")) {
        var appIcon = {
            'id' : id,
        };
        $.post(ctx + "/appIcon/del", appIcon, function(data) {
            if (data.returnValue == 0) {
                alert( '成功！');
                $("#myModal").modal('hide');
                $("#sample_1").bootstrapTable('refresh', param);
                if($("#sample_1").bootstrapTable('getData').length == 1){
                    $("#sample_1").bootstrapTable('prevPage');}
            } else {
                alert( '失败！');
            }
        }, "json");
    }
}
function appIcon_submit() {
    var id =$('#txt_sysAppIconId').val();
    var appName = $('#txt_sysAppName').val();
    var appPackage = $('#txt_sysPackageName').val();
    var isShow = $('#txt_sysIsShow').val();
    console.log(isShow);
    if ($.trim(appName) == "") {
        alert("应用名称不能为空！");
        return;
    }
    if ($.trim(appPackage) == "") {
        alert("包名不能为空！");
        return;
    }
    if ($.trim(isShow) == "") {
        alert("是否显示不能为空！");
        return;
    }
    var appIcon = {
        'appName' : appName,
        'appPackage' : appPackage,
        'isShow' : isShow,
        'id' :id
    };
    if(id !=null && id !=''){
        $.post(ctx + "/appIcon/update", appIcon, function(data) {
            if (data.returnValue == 0) {
                alert( '修改成功！');
                $("#myModal").modal('hide');
                clear_diaglog();
                $("#sample_1").bootstrapTable('refresh', param);
            }else if(data.returnValue == 1){
                alert('修改包名重复，请重新修改！');
            } else {
                alert( '修改失败！');
            }
        }, "json");

    }else{
        $.post(ctx + "/appIcon/save", appIcon, function(data) {
            if (data.returnValue == 0) {
                alert( '新增成功！');
                $("#myModal").modal('hide');
                clear_diaglog();
                $("#sample_1").bootstrapTable('refresh', param);
            }else if(data.returnValue == 1){
                alert('添加包名重复，请重新添加！');
            }else {
                alert( '新增失败！');
            }
        }, "json");
    }
}
