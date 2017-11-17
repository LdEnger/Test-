$(document).ready(function () {
    initTable();
    option();
});
var param ={};

//初始化第一张表格
function initTable(beanName,isEffictive) {
    //先销毁表格
    $('#sample_1').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#sample_1").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/portalBeanCurdList/getPageList", //获取数据的Servlet地址
        striped: true,  //表格显示条纹
        selectItemName : 	'btSelectItem',
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
                curdName : beanName,
                isEffeCtice : isEffictive
            };
            return param;
        },
        columns : [ [ {
            field : 'curdName',
            title : '列表名称',
            width : '8%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        },{
            field : 'enName',
            title : '豆腐块列表',
            width : '13%',
            align : 'center',
            formatter : function(value, row, index) {
           return value;
            }
        },{
           field :'isEffeCtice' ,
            title : '上下线',
            width : '12%',
            align : 'center',
            formatter : function(value, row ,index){
                if (1 == value) {
                    return '<div class="abtn"><a href="javascript:void(0)" style="color:#7d7d7d;text-decoration: none;cursor:text;" class="a_on a_on2">上线</a>&nbsp;|&nbsp;&nbsp;<a href="javascript:isAPKPublish('+row.curdID+','+row.isEffeCtice+')" class="btn btn-default btn-xs">下线</a></div>';
                } else {
                    return '<div class="abtn"><a href="javascript:isAPKPublish('+row.curdID+','+row.isEffeCtice+')" class="btn btn-primary btn-xs">上线</a>&nbsp;|&nbsp;&nbsp;<a href="javascript:void(0)" style="color: #7d7d7d;text-decoration: none;cursor:text;" class="a_on a_on2">下线</a></div>';
                }
            }
        },{
                field : 'do',
                title : '操作',
                width : '10%',
                align : 'center',
                formatter : function(value, row) {
                    var str = '';
                    str += '<a href="javascript:beanEdit(' + row.curdID + ",'"+ row.curdName + '\')">编辑</a>';
                    str += '&nbsp;|&nbsp;<a href="javascript:twoTable(' + row.curdID + ",'" + row.curdName + '\')">关联豆腐块列表</a>';
                    str += '&nbsp;|&nbsp; <a href="javascript:del('+row.curdID+')">删除</a>';
                    return str;
                }
            } ] ],
        onLoadSuccess: function () {  //加载成功时执行
            //layer.msg("加载成功");
        },
        onLoadError: function () {  //加载失败时执行
            // layer.msg("加载数据失败", {time: 1500, icon: 2});
        }
    });
}

function isAPKPublish(curdId,isEffeCtice) {
    var confirmInfo = null;
    var hotWords = {};
    if (isEffeCtice == 0) {
        hotWords = {
            "isEffeCtice": 1,
            "curdID": curdId
        };
        confirmInfo = "确定要置为上线状态"
    } else {
        hotWords = {
            "isEffeCtice": 0,
            "curdID": curdId
        }
        confirmInfo = "确定要置为下线状态"
    }
    if(confirm(confirmInfo)){
        $.post( ctx +"/portalBeanCurdList/updateIs",hotWords,function (data) {
            if(data.returnValue == 1){
                alert("修改失败");
                $("#sample_1").bootstrapTable('refresh',param);
            }else if(data.returnValue == 0){
                alert("修改成功")
                $("#sample_1").bootstrapTable('refresh',param);
            }
        },"json");
    }
}

//初始化第二张表格
function twoTable(curdID,curdName/*,beanCurd*/) {
    //先销毁表格
    $('#sample_table').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#sample_table").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/portalBeanCurdMappingController/getPageList", //获取数据的Servlet地址
        striped: true,  //表格显示条纹
        clickToSelect: false,
        pagination: true, //启动分页
        pageSize: 10,  //每页显示的记录数
        pageNumber: 1, //当前第几页
        pageList: [5, 10, 15, 20, 25],  //记录数可选列表
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
                rows: params.pageSize,
                'curdId' : curdID,
   /*             'entranceName' : beanCurd*/
            };
            return param;
        },
        columns : [ [ {
            field : 'seq',
            title : '顺序',
            width : '8%',
            align : 'center',
            formatter : function(value, row, index) {
                return index+1;
            }
        },{
            field : 'entranceName',
            title : '入口名称',
            width : '13%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        },{
            field :'entranceType' ,
            title : '入口应用类型',
            width : '12%',
            align : 'center',
            formatter : function(value, row ,index){
              if(0 == value){
                  return "系统应用"
              }else if(1 == value){
                  return "定制应用"
              }
            }
        },{
            field : 'seqDo',
            title : '顺序操作',
            width : '12%',
            align : 'center',
            formatter :function (value,row,index) {
                var str = "";
                if(true){
                    str += '<a href="javascript:stickFixedRecomment(' + row.id+"," + row.seq + ","+row.beanCurdId + ')">置顶</a>';
                    str += '&nbsp;<a href="javascript:upEntrance(' + row.id +"," + row.seq + "," + row.beanCurdId+ ')">上移</a>'
                    str += '&nbsp;<a href="javascript:downEntrance('+ row.id + "," + row.seq + "," + row.beanCurdId + ')">下移</a>'
                }
                return str;
            }
        },
            {
            field : 'do',
            title : '操作',
            width : '10%',
            align : 'center',
            formatter : function(value, row) {
                var str = '';
                str += '<a href="javascript:edit('+row.id + ",'"+ row.entranceId + '\')">编辑</a>';
                str += '&nbsp;|&nbsp; <a href="javascript:editDel('+row.id+')">删除</a>';
                return str;
            }
        } ] ],
        onLoadSuccess: function () {  //加载成功时执行
            $('#myModalTwo').modal({backdrop: 'static', keyboard: false});
        },
        onLoadError: function () {  //加载失败时执行
            // layer.msg("加载数据失败", {time: 1500, icon: 2});
        }
    });
    
    $("#txt_bean_curd_id").val(curdID);
    $("#txt_bean_Name").val(curdName);
    // setTimeout(function(){
    //     $('#myModalTwo').modal({backdrop: 'static', keyboard: false});
    // },1500);
}
//置顶
function stickFixedRecomment(id,seq,beanCurdId) {
    var beanCurdId1 = {
        'beanCurdId':beanCurdId,
    }
    var minSeq = null;
    $.post(ctx + "/portalBeanCurdMappingController/getMinSeq", beanCurdId1, function(data) {
        if(seq == data){

        }else {
            minSeq = parseInt(data) - 1;
            var content = {
                'id': id,
                'seq': minSeq,
            };
            $.post(ctx + "/portalBeanCurdMappingController/update", content, function (data) {
                if (data.returnValue == 0) {
                    alert('置顶成功！');
                    $("#sample_table").bootstrapTable('refresh', param);
                } else {
                    alert('置顶失败！');
                }
            }, "json");
        } }, "json");
}
//上移
function upEntrance(id,seq,beanCurdId) {
    var mapping = {
        'id': id,
        'seq': seq,
        'beanCurdId' :beanCurdId
    }
    $.post(ctx + "/portalBeanCurdMappingController/getMinMapping",mapping,function (data) {
        var mapping1 = {
            'id' :id,
            'seq' :data.seq
        };
        var mapping2 = {
            'id':data.id,
            'seq':seq
        };
        $.post(ctx + "/portalBeanCurdMappingController/update",mapping1,function (data) {
            if(data.returnValue == 0){
                $.post(ctx + "/portalBeanCurdMappingController/update",mapping2,function (data) {
                    if(data.returnValue == 0){
                        alert("上移成功");
                        $("#sample_table").bootstrapTable('refresh',param);
                    }else {
                        alert("上移失败");
                    }
                },"json");
            }else {
                alert("上移失败");
            }
        },"json");

    },"json");
}

function downEntrance(id,seq,beanCurdId) {
    var mapping = {
        'id': id,
        'seq': seq,
        'beanCurdId' :beanCurdId
    };
    $.post(ctx + "/portalBeanCurdMappingController/getMaxMapping",mapping,function (data) {
        var mapping1 = {
            'id' :id,
            'seq' :data.seq
        };
        var mapping2 = {
            'id':data.id,
            'seq':seq
        };
        $.post(ctx + "/portalBeanCurdMappingController/update",mapping1,function (data) {
            if(data.returnValue == 0){
                $.post(ctx + "/portalBeanCurdMappingController/update",mapping2,function (data) {
                    if(data.returnValue == 0){
                        alert("下移成功");
                        $("#sample_table").bootstrapTable('refresh',param);
                    }else {
                        alert("下移失败");
                    }
                },"json");
            }else {
                alert("下移失败");
            }
        },"json");

    },"json");
}

function editDel(id) {
    if(confirm("是否删除")){
        var Id = {
            "id" : id
        }
        $.post(ctx + "/portalBeanCurdMappingController/delete",Id,function (data) {
            if(data.returnValue == 0){
                alert("删除成功");
                $("#sample_table").bootstrapTable('refresh',param);
                $("#sample_1").bootstrapTable('refresh',param);
                if($("#sample_table").bootstrapTable('getData').length == 1){
                    $("#sample_table").bootstrapTable('prevPage');
                }
            }else {
                alert("删除失败");
            }
        },"json");
    }
}


function del(curdID) {
    if(confirm("是否删除")){
        var ID ={
            "curdID" : curdID}
        $.post(ctx + "/portalBeanCurdList/delete",ID,function (data) {
            if(data.returnValue == 0){
                alert("删除成功");
                $("#sample_1").bootstrapTable('refresh',param);
                if($("#sample_1").bootstrapTable('getData').length == 1){
                    $("#sample_1").bootstrapTable('prevPage');
                }
            }else if(data.returnValue == 1){
                alert("不能删除,有关联");
            }else {
                alert("删除失败");
            }
        },"json");
    }
}
function  clear_modal() {
    $("#curdID").val('');
    $("#isEffeCtice").val('');
    $("myModal").modal('hide');
}
function  clear_modalTwo() {
    $("#txt_entrance_type").val('');
    $("#txt_becurdId").val('');
    $("#txt_id").val('');
    $("#txt_beanCurdEntranceName").val('');
    $("#txt_beanCurdName").val('');
    $("#txt_bean_curd_id").val('');
    $("#txt_bean_Name").val('');
    $("#myModalTwo").modal('hide');
}
function beanCurdNameSelect() {
    var beanCurd  = $("#beanNameLike").val();
    var ppt = {
        url : ctx + "/portalBeanCurdMappingController/getPageList",
        silent : true,
        query:{
            entranceName : beanCurd
        }
    };
    $("#sample_table").bootstrapTable('refresh',ppt);

}


function edit(id,entranceId) {
    $("#txt_id").val(id);
    $("#txt_beanCurdEntranceName").val(entranceId);
/*    option();*/
    $("#modalEdit").modal();
}


function editAdd(){
    option();
    $("#modalEdit").modal({backdrop: 'static', keyboard: false});
}
function clean_Mapping() {
    $("#txt_entrance_type").val('');
    $("#txt_becurdId").val('');
    $("#txt_id").val('');
    $("#txt_beanCurdEntranceName").val('');
    $("#txt_beanCurdName").val('');
/*    $("#txt_bean_curd_id").val('');*/
/*    $("#txt_bean_Name").val('');*/
}
function clear_entrance() {
    $("#txt_becurdId").val('');
    $("#txt_id").val('');
    $("#txt_beanCurdEntranceName").val('');
    $("#txt_beanCurdName").val('');
    $("#txt_bean_curd_id").val('');
    $("#txt_bean_Name").val('');
    $("#modalEdit").modal('hide');
}
//豆腐块名称添加修改
function buanCurd_submit() {
    debugger;
    var enranId = $("#txt_id").val();
    var entranceId =  $("#txt_beanCurdEntranceName").val();
    var options = $("#txt_beanCurdEntranceName option:selected").text();
    var curdId = $("#txt_bean_curd_id").val();
    var curdName = $("#txt_bean_Name").val();
    var enatranIdPtt = {
        entranceId : entranceId
}
    $.ajax({
        type : "post",
        async : false,
        url : ctx + "/portalBeanCurdEditListController/getEdit",
        data:enatranIdPtt,
        dataType : "json",
        success : function (res) {
            console.log(res);
            $("#txt_entrance_type").val(res.entranceType);
        }
    });
    var tes = $("#txt_entrance_type").val();
    var ppt = {
        id:enranId,
        entranceId : entranceId,
        entranceName :options,
        entranceType:tes,
        beanCurdId:curdId,
        beanCurdName:curdName
    }
    var flag = true;
    if(flag == true) {
        $.ajax({
            type: "post",
            url: ctx + "/portalBeanCurdMappingController/getMappingCount",
            dataType: "json",
            data: ppt,
            async: false,
            success: function (res) {
                console.log(res);
                if (res != false) {
                    flag = false;
                    alert("该数据已经存在,请重新选择");
                }
            }
        });
    }
    console.log(flag);
    if(flag == true){
    if(enranId != null && enranId  != ''){
        $.post(ctx + "/portalBeanCurdMappingController/update",ppt,function (data) {
            if(data.returnValue == 0){
                alert("修改成功");
                $("#modalEdit").modal('hide');
                $("#sample_table").bootstrapTable('refresh',param);
                $("#sample_1").bootstrapTable('refresh',param);
                clean_Mapping();
            }else {
                alert("修改失败")
            }
        },"json")
    }else {
        $.post(ctx + "/portalBeanCurdMappingController/save",ppt,function (data) {
            if(data.returnValue == 0){
                alert("新增成功");
                $("#modalEdit").modal('hide');
                $("#sample_table").bootstrapTable('refresh',param);
                $("#sample_1").bootstrapTable('refresh',param);
                clean_Mapping();
            }else {
                alert("新增失败");
            }
        },"json")
    }}
}
function beanCurdNameLike() {
    var beanName = $("#beanCurdNameLike").val();
    var isEffictive = $("#txt_beanIsEffecitveLike").val();
/*    var ppt = {
        url : ctx + "/portalBeanCurdList/getPageList",
        silent : true,
        query:{
            curdName : beanName,
            isEffeCtice : isEffictive
        }
    };
    $("#sample_1").bootstrapTable('refresh',ppt);*/
    initTable(beanName,isEffictive);
}
function beanCurd_submit() {
    $("#txt_entrance_type").val('');
    $("#txt_becurdId").val('');
    $("#txt_id").val('');
    $("#txt_beanCurdEntranceName").val('');
    $("#txt_beanCurdName").val('');
    $("#txt_bean_curd_id").val('');
    $("#txt_bean_Name").val('');
    $("#myModalTwo").modal('hide');
}

function beanCurdAdd() {
    $("#beanCurdModal").modal({backdrop: 'static', keyboard: false});
}
//清空豆腐块列表添加
function clear_modalBean() {

    $("#txt_beanCurdName").val('');
    $("#txt_beabCurdId").val('');
    $("#sample_1").bootstrapTable('refresh',param);
    $("#beanCurdModal").modal('hide');
}
//豆腐块名称添加编辑
function beanCurdNameSubimt() {
    var beanName = $("#txt_beanCurdName").val();
    var beanId = $("#txt_beabCurdId").val();
    if ($.trim(beanName) == '') {
        alert("列表名称不能为空")
        return;
    }
    var ppt = {
        curdName: beanName,
        curdID: beanId
    }
    if (beanId != null && beanId != '') {
        $.post(ctx + "/portalBeanCurdList/update",ppt,function (data) {
            if(data.returnValue == 0){
                alert("修改成功");
                clear_modalBean();

            }else {
                alert("修改失败");
            }
        },"json")
    }else {
        $.post(ctx + "/portalBeanCurdList/save", ppt, function (data) {
            if (data.returnValue == 0) {
                alert("新增成功");
                clear_modalBean();
            } else {
                alert("新增失败")
            }
        }, "json")
    }
}
function beanEdit(curdId,curdName) {
    $("#txt_beabCurdId").val(curdId);
    $("#txt_beanCurdName").val(curdName);
    $("#beanCurdModal").modal({backdrop: 'static', keyboard: false});
}
function cleanBeanCurdName() {
    $("#txt_beabCurdId").val('');
    $("#txt_beanCurdName").val('');
    $("#beanCurdModal").modal('hide');
}

function  option() {
    var ppt = {
        isEffeCtice : 1
    }
$.ajax({
    type : "get",
    async : false,
    url : ctx + "/portalBeanCurdEditListController/getEditList",
    dataType : "json",
    data : ppt,
    success : function (res) {
        $("#txt_beanCurdEntranceName").empty();
        $.each(res,function (index,element) {
            var options = $("<option value='" + element['entranceId'] + "'>" +  element['entranceName'] + "</option>")
            $("#txt_beanCurdEntranceName").append(options);
        });
    }
})
}

function seqSelect(cuid) {
      var curdId = $("#txt_bean_curd_id").val();
}

