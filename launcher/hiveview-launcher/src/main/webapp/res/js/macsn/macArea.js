/**
 * Created by Administrator on 2017/7/13.
 */
$(document).ready(function () {
    initTable();
    option();
    initUploadMacSnExcel();
});
var param ={};



function option() {
    $.ajax({
       type:"get",
        url:ctx + "/areaGroup/getList",
        dataType:"json",
        success:function (res) {
            $("#txt_areaCode1").empty();
            $("#txt_areaCode").empty();
            //var all=$("<option value=''>全部</option>");
            var select=$("<option value='' selected='selected'>-请选择-</option>");
            $("#txt_areaCode").append(select);
           //$("#txt_areaCode1").append(all);
            $.each(res, function (index, element) {
                var $option = $("<option value='" + element['areaCode'] + "'>" + element['areaName'] + "</option>");
                var $option1=$("<option value='" + element['areaCode'] + "'>" + element['areaName'] + "</option>");
                $("#txt_areaCode1").append($option1);
                $("#txt_areaCode").append($option);
                $("#txt_areaCode1").val('');
            });
        }
    });

    //根据选择的分组查询
    /*$('#txt_areaCode1').change(function () {
        var areaCode=$(this).find("option:selected").val();
        initTable(areaCode, null, null);
    });*/

    $('#btn_select').click(function () {
        var selectMac=$('#txt_seleteMac').val();
        var selectSn=$('#txt_seleteSn').val();
        /*if($('#txt_areaCode1').children('option:selected').val()!=null){
            var areaCode=$('#txt_areaCode1').children('option:selected').val();
            initTable(areaCode,selectMac,selectSn);
        }
        initTable(null,selectMac,selectSn);*/
        var areaCode=$('#txt_areaCode1').children('option:selected').val();
        initTable(areaCode,selectMac,selectSn);
        //$('#txt_seleteMac').val('');
        //$('#txt_seleteSn').val('');
    });
}


function add() {
    //addclear_diaglog();
    $('#addModal').modal({backdrop: 'static', keyboard: false});
}
function addGroup() {
    //addGroupclear_diaglog();
    $('#addGroupModal').modal({backdrop: 'static', keyboard: false});
}
function manage() {
    //addclear_diaglog();
    //$('#addModal').modal({backdrop: 'static', keyboard: false});
    initGroupTable();
    setTimeout(function(){
        $('#manageModal').modal({backdrop: 'static', keyboard: false});
    },1500);
}
//添加单个的关闭按钮
function addclear_diaglog() {
    $('#area_submit').attr("disabled", false);
    $('#txt_macAreaId').val('');
    $('#txt_areaCode').val('');
    $('#txt_areaMac').val('');
    $('#txt_areaSn').val('');
    $('#addModal').modal('hide');
}

//管理分组的关闭按钮
function manageclear_diaglog() {
    $('#manageModal').modal('hide');
}

//添加分组的关闭按钮
function addGroupclear_diaglog() {
    $('#txt_areaGroupCode').attr("readonly",false)//去除input元素的readonly属性
    $('#txt_areaGroupId').val('');
    $('#txt_areaGroupCode').val('')
    $('#txt_areaGroupName').val('');
    $('#addGroupModal').modal('hide');
}

function initTable(code,selectMac,selectSn) {
   /* param['areaCode'] = code;
    param['mac']=selectMac;
    param['sn']=selectSn;*/
   /* param = {
        areaCode: code,
        mac: selectMac,
        sn:selectSn
    };*/
    //先销毁表格
    $('#sample_1').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#sample_1").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/macsn/getPageList", //获取数据的Servlet地址
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
                areaCode: code,
                mac: selectMac,
                sn:selectSn
            };
            return param;
        },
        columns : [ [ {
            field : 'mac',
            title : 'MAC',
            width : '13%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        }, {
            field : 'sn',
            title : 'SN',
            width : '13%',
            align : 'center',
            formatter : function(value) {
                return value;
            }
        }, {
            field : 'areaName',
            title : '分组',
            width : '20%',
            align : 'center',
            formatter : function(value) {
                return value;
            }
        }, {
            field : 'do',
            title : '操作',
            width : '13%',
            align : 'center',
            formatter : function(value, row) {
                var str = '';
                str += '<a href="javascript:edit('+row.id+",'"+row.mac+"','"+row.sn+"','"+row.areaName+"','"+row.areaCode+"'"+')">编辑</a>';
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

function  edit(id,mac,sn,areaName,areaCode) {
    // var selects = $('#sample_1').bootstrapTable('getSelections');
    // var newSelects = $.parseJSON(JSON.stringify(selects));
    // if(typeof(selects) == 'undefined'){
    //     alert("请选中一行")
    // }else{ alert(selects.recommendName);}
    $('#txt_macAreaId').val(id);
    $('#txt_areaCode').val(areaCode);
    $('#txt_areaCode option:selected').html(areaName);
    $('#txt_areaMac').val(mac);
    $('#txt_areaSn').val(sn);
    $("#addModal").modal({backdrop: 'static', keyboard: false});
}
function  del(id) {
    if (confirm("是否确认删除？")) {
        var macArea = {
            'id' : id,
        };
        $.post(ctx + "/macsn/del", macArea, function(data) {
            if (data.returnValue == 0) {
                alert( '成功！');
                $("#addModal").modal('hide');
                $("#sample_1").bootstrapTable('refresh', param);
                if($("#sample_1").bootstrapTable('getData').length == 1){
                    $("#sample_1").bootstrapTable('prevPage');
                }
            } else {
                alert( '失败！');
            }
        }, "json");
    }
}

function area_submit() {
    var id=$('#txt_macAreaId').val();
    var areaCode=$('#txt_areaCode').val();
    var areaName = $('#txt_areaCode option:selected').html();
    var mac=$('#txt_areaMac').val();
    var sn=$('#txt_areaSn').val();
    if ($.trim(areaCode) == "") {
        alert("请选择分组！");

        return;
    }
    if ($.trim(mac) == "") {
        alert("mac不能为空！");
        return;
    }
    if ($.trim(sn) == "") {
        alert("sn不能为空！");
        return;
    }
    $('#area_submit').attr("disabled", true);
    var macArea = {
        'id' : id,
        'areaCode' : areaCode,
        'areaName':areaName,
        'mac' : mac,
        'sn' :sn
    };
    if(id !=null && id !=''){
        $.post(ctx + "/macsn/update", macArea, function(data) {
            if (data.returnValue == 0) {
                alert( '修改成功！');
                $("#addModal").modal('hide');
                $("#sample_1").bootstrapTable('refresh', param);
                addclear_diaglog();
            }else if(data.returnValue == 1){
                alert('修改mac+sn重复，请重新修改！');
                $('#area_submit').attr("disabled", false);
            } else {
                alert( '修改失败！');
            }
        }, "json");

    }else{
        $.post(ctx + "/macsn/save", macArea, function(data) {
            if (data.returnValue == 0) {
                alert( '新增成功！');
                $("#addModal").modal('hide');
                addclear_diaglog();
                $("#sample_1").bootstrapTable('refresh', param);
            }else if(data.returnValue == 1){
                alert('添加mac+sn重复，请重新添加！');
                $('#area_submit').attr("disabled", false);
            }else {
                alert( '新增失败！');
            }
        }, "json");
    }
}


//管理分组的table
function initGroupTable() {
    //先销毁表格
    $('#sample_2').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#sample_2").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/areaGroup/getPageList", //获取数据的Servlet地址
        striped: true,  //表格显示条纹
        clickToSelect: false,
        pagination: true, //启动分页
        pageSize: 5,  //每页显示的记录数
        pageNumber: 1, //当前第几页
        pageList: [5],  //记录数可选列表
        search: false,  //是否启用查询
        showColumns: false,  //显示下拉框勾选要显示的列
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
        columns : [ [ {
            field : 'id',
            title : 'ID',
            width : '13%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        }, /*{
            field : 'areaCode',
            title : '分组Code',
            width : '13%',
            align : 'center',
            formatter : function(value) {
                return value;
            }
        },*/{
            field : 'areaName',
            title : '分组名称',
            width : '16%',
            align : 'center',
            formatter : function(value) {
                return value;
            }
        }, {
            field : 'do',
            title : '操作',
            width : '13%',
            align : 'center',
            formatter : function(value, row) {
                var str = '';
                str += '<a href="javascript:editGroup('+row.id+",'"+row.areaCode+"','"+row.areaName+"'"+')">编辑</a>';
                str += '&nbsp;|&nbsp; <a href="javascript:delGroup('+row.id+",'"+row.areaCode+"'"+')">删除</a>';
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

function  editGroup(id,areaCode,areaName) {
    // var selects = $('#sample_1').bootstrapTable('getSelections');
    // var newSelects = $.parseJSON(JSON.stringify(selects));
    // if(typeof(selects) == 'undefined'){
    //     alert("请选中一行")
    // }else{ alert(selects.recommendName);}
    $('#txt_areaGroupId').val(id);
    $('#txt_areaGroupCode').val(areaCode);
    $('#txt_areaGroupCode').attr("readonly",true)//将input元素设置为readonly
    $('#txt_areaGroupName').val(areaName);
    $("#addGroupModal").modal({backdrop: 'static', keyboard: false});
}
function  delGroup(id,areaCode) {
    if (confirm("当删除分组时，所在当前分组下的所有数据也将被删除，是否确认删除？")) {
        var AreaGroup = {
            'id' : id,
            'areaCode':areaCode
        };
        $.post(ctx + "/areaGroup/del", AreaGroup, function(data) {
            if (data.returnValue == 0) {
                alert( '成功！');
                $("#addGroupModal").modal('hide');
                $("#sample_2").bootstrapTable('refresh', param);
                $("#sample_1").bootstrapTable('refresh', param);
            }else if(data.returnValue == 1){
                alert('分组被launcher区域配置关联，不可删除，请先取消关联！');
            }else {
                alert( '失败！');
            }
        }, "json");
    }
}
function areaGroup_submit() {
    var id=$('#txt_areaGroupId').val();
    var areaCode=$('#txt_areaGroupCode').val();
    var areaName=$('#txt_areaGroupName').val();
    if ($.trim(areaCode) == "") {
        alert("分组Code不能为空！！");
        return;
    }
    if ($.trim(areaName) == "") {
        alert("分组名称不能为空！");
        return;
    }
    var AreaGroup = {
        'id' : id,
        'areaCode':areaCode,
        'areaName' : areaName
    };
    if(id !=null && id !=''){
        $.post(ctx + "/areaGroup/update", AreaGroup, function(data) {
            console.log(data);
            if (data.returnValue == 0) {
                alert( '修改成功！');
                addGroupclear_diaglog();
                $("#addGroupModal").modal('hide');
                $("#sample_2").bootstrapTable('refresh', param);
                $("#sample_1").bootstrapTable('refresh', param);
                option();
            }else if (data.returnValue == 2){
                alert('分组名称已存在，请重新修改!');
            } else {
                alert( '修改失败！');
            }
        }, "json");

    }else{
        $.post(ctx + "/areaGroup/save", AreaGroup, function(data) {
            console.log(data);
            if (data.returnValue == 0) {
                alert( '新增成功！');
                addGroupclear_diaglog();
                $("#addGroupModal").modal('hide');
                $("#sample_2").bootstrapTable('refresh', param);
                $("#sample_1").bootstrapTable('refresh', param);
                option();
            }else if (data.returnValue == 1){
                alert('分组Code已存在，请重新添加!');
            }else if (data.returnValue == 2){
                alert('分组名称已存在，请重新添加!');
            }  else {
                alert( '新增失败！');
            }
        }, "json");
    }
}

function initUploadMacSnExcel() {
        var import_button = $("#btn_import");
        import_button.bind("click", function() {
            var areaCode = $('#txt_areaCode1 option:selected').val();
            var areaName = $('#txt_areaCode1 option:selected').html();
            if(areaCode == null || areaCode ==''){
               alert("请选择分组后再导入！");
                return;
            }
            new AjaxUpload(import_button, {
                action : ctx+ '/macsn/uploadExcel',
                autoSubmit : true,
                name : 'file',// 文件对象名称（不是文件名）
                data : {"areaCode":areaCode,"areaName":areaName},
                onChange : function(file, extension) {
                    console.log(file);
                    var d = /\.[^\.]+$/.exec(file); // 文件后缀
                    if (d != ".xls" && d != ".xlsx") {
                        alert('文件格式错误，请上传.xls或.xlsx格式！');
                        return false;
                    } else {
                        $("#ImportFileName").text(file);
                    }
                },
                onComplete : function(file,data ,response) {
                    debugger;
                    console.log(data.result);
                 //  var str = response.replace(/<[^>]+>/g,"");
                 //  var obj = JSON.parse(response);

                    // var start = response.indexOf(">");
                    // if (start != -1) {
                    //     var end = response.indexOf("<", start + 1);
                    //     if (end != -1) {
                    //         response = response.substring(start + 1, end);
                    //     }
                    // }
                    var msg ="";
                    debugger;
                    if(data.result >0){
                        msg+="EXCEL导入成功，其中有"+data.result+"条写入失败，请检查mac,sn是否重复，或者mac,sn信息不完整！"
                    }else{
                        msg+="EXCEL导入成功，并且全部写入成功！"
                    }
                    $("#sample_1").bootstrapTable('refresh', param);
                    alert(msg);
                }
            });

    });
};
