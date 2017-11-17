$(document).ready(function () {
    initTable();
    initUploadJs("areaImg");
});
var param ={};

//初始化第一张表格
function initTable() {
    //先销毁表格
    $('#sample_1').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#sample_1").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/areaController/getPage", //获取数据的Servlet地址
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
            field : 'areaName',
            title : '专区名称',
            width : '13%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        },{
            field : 'areaTitle',
            title : '专区标题名称',
            width : '13%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        },
            {
            field :'effective' ,
            title : '上下线',
            width : '12%',
            align : 'center',
            formatter : function(value, row ,index){
                if (1 == value) {
                    return '<div class="abtn"><a href="javascript:void(0)" style="color:#7d7d7d;text-decoration: none;cursor:text;" class="a_on a_on2">上线</a>&nbsp;|&nbsp;&nbsp;<a href="javascript:isEffective('+row.id+','+row.effective+')" class="btn btn-default btn-xs">下线</a></div>';
                } else {
                    return '<div class="abtn"><a href="javascript:isEffective('+row.id+','+row.effective+')" class="btn btn-primary btn-xs">上线</a>&nbsp;|&nbsp;&nbsp;<a href="javascript:void(0)" style="color: #7d7d7d;text-decoration: none;cursor:text;" class="a_on a_on2">下线</a></div>';
                }
            }
        },{
            field : 'do',
            title : '操作',
            width : '10%',
            align : 'center',
            formatter : function(value, row) {
                var str = '';
               /* str = '<a href="javascript:edit(' + row.entranceId + ",'" + row.entranceName + "'," + row.entranceType + ",'" + row.entranceAppName +"'," + row.entranceAppId + ')">编辑</a>';*/
                str += '<a href="javascript:areaEdit(' + row.id +",'" + row.areaName + "','" + row.areaTitle + "','" + row.areaIntroduce +  "','" + row.areaImg + '\')">编辑</a>';
                str += '&nbsp;|&nbsp;<a href="javascript:areaTable(' + row.id+ ')">关联内容</a>';
                str += '&nbsp;|&nbsp; <a href="javascript:del('+row.id+')">删除</a>';
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


//上下线
function isEffective(id,effective) {
    var confirminfo = null;
    var hotWords = {};
    if(effective == 0){
        hotWords = {
            "effective" : 1,
            id : id
        };
        confirminfo = "确定置为上线状态";
    }else {
        hotWords = {
            "effective" : 0,
            id : id
        }
        confirminfo = "确定置为下线状态"
    }
    if(confirm(confirminfo)){
        $.post(ctx + "/areaController/update",hotWords,function (data) {
            if(data.returnValue == 1){
                alert("修改失败");
                $("#sample_1").bootstrapTable("refresh",param);
            }  else if(data.returnValue == 0) {
                alert("修改成功");
                $("#sample_1").bootstrapTable("refresh",param);
            }
        },'json');
    }
}

//专区管理添加弹框
function areaAdd() {
    $("#myModal").modal({backdrop: 'static', keyboard: false});
}

//专区管理编辑弹框
function areaEdit(id,areaName,areaTitle,areaIntroduce,areaImg) {
    $("#txt_areaName").val(areaName);
    $("#txt_areaTitle").val(areaTitle);
    $("#txt_areaIntroduce").val(areaIntroduce);
    $("#txt_areaId").val(id);
    $("#areaImg").attr('src',areaImg);
    $("#myModal").modal({backdrop: 'static', keyboard: false});
}

//编辑添加按钮取消弹框
function  clear_diaglog() {
    $("#txt_areaId").val('');
    $("#txt_areaName").val('');
    $("#txt_areaIntroduce").val('');
    $("#txt_areaTitle").val('');
    $("#myModal").modal("hide");
}

function area_submit() {
    var id = $("#txt_areaId").val();
    var areaName = $("#txt_areaName").val();
    var areaTitle = $("#txt_areaTitle").val();
    var areaIntroduce = $("#txt_areaIntroduce").val();
    var areaImg = $("#areaImg")[0].src;
    var img = areaImg.substring(areaImg.length-4,areaImg.length + 1);

/*    if(img == "list"){
        alert("请上传图片");
        return;
    }*/
    if($.trim(areaName) == ""){
        alert("专区名称不能为空");
        return;
    }
    if($.trim(areaTitle) == ""){
        alert("专区标题名称不能为空");
        return;
    }


    var areaAdminiration = {
        "id" : id,
        "areaName" : areaName,
        "areaTitle" : areaTitle,
        "areaIntroduce" : areaIntroduce,
        "areaImg" : areaImg
    };
    if(id != null && id != ""){
            $.post(ctx + "/areaController/update",areaAdminiration, function (data) {
               if(data.returnValue == 0){
                   alert("修改成功");
                   $("#myModal").modal("hide");
                   clear_diaglog();
                   $("#sample_1").bootstrapTable("refresh",param);
               }else {
                   alert("修改失败");
               }
            },'json');
    }else {
        $.post(ctx + "/areaController/save",areaAdminiration,function (data) {
            if(data.returnValue == 0){
                alert("添加成功");
                $("#myModal").modal("hide");
                $("#sample_1").bootstrapTable("refresh",param);
                clear_diaglog();
            }else {
                alert("添加失败")
            }
        },'json');
    }

}



// 上传图片
function initUploadJs(file_id) {
    var file_upload = '#' + file_id + 'Url';
    $(file_upload).uploadify({
        'swf' :ctx +  '/res/js/plugin/uploadify/uploadify.swf',
        'uploader' :ctx +  '/upload/init.json', // fileUpload/newIcpUpload.json
        'height' : 25,
        'whith' : 120,
        'auto' : true,
        /*        'fileSizeLimit' : '5120KB',*/
        'fileDataName' : 'file',
        'buttonText' : '浏览...',
        'fileTypeExts' : '*.gif; *.jpg; *.png',
        'multi' : false,
        'method' : 'post',
        'debug' : false,
        'queueSizeLimit' : 1,
        'removeTimeout' : 1,
        'onSelect' : function(file) {
            var myself = this;
            var size = file.size

            if (file_id == "areaImg") {
                if (file.type != ".jpg" && file.type != ".png"
                    && file.type != ".gif") {
                    alert(titleInfo, '上传文件错误，请选择合法图片！');
                    $(file_upload).uploadify('cancel', '*');
                }
            }
            if (file.type == ".jpg" || file.type == ".png") {
                if (size > 1024 * 1024 * 1) {
                    alert(titleInfo, '图片大小超出1MB！');
                    myself.cancelUpload(file.id);
                    $('#' + file.id).remove();
                }
            } else {
                if (size > 1024 * 1024 * 200) {
                    alert(titleInfo, '视频大小超出200MB！');
                    myself.cancelUpload(file.id);
                    $('#' + file.id).remove();
                }
            }
        },
        'onUploadStart' : function(file) {// 传参
            var param = {};
            param.picHref = $(file_upload).val();
            $(file_upload).uploadify("settings", "formData", param);
        },
        'onUploadSuccess' : function(file, data, response) {
            var imgId = '#' + file_id;
            $(imgId).attr("src", data);// 预览
        },
        'onUploadError' : function(file, errorCode, errorMsg, errorString) {
            var errMsg = 'The file ' + file.name + ' could not be uploaded: '
                + errorString
            alert(errMsg);
        }
    });
}


//打开关联内容modal
function areaContent() {
    $("#areaModal").modal({backdrop: 'static', keyboard: false});
}

function areaTable(id) {
    //先销毁表格
    $('#areaContentTable').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#areaContentTable").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/PortalAreaContenController/getPage", //获取数据的Servlet地址
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
                id : id
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
            field : 'areaName',
            title : '标题',
            width : '13%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        },{
            field : 'seqDo',
            title : '顺序操作',
            width : '12%',
            align : 'center',
            formatter :function (value,row,index) {
                var str = "";
                if(true){
                    str += '&nbsp;<a href="javascript:upEntrance(' + row.id +"," + row.seq + ')">上移</a>'
                    str += '&nbsp;<a href="javascript:downEntrance('+ row.id + "," + row.seq + ')">下移</a>'
                }
                return str;
            }
        },{
                field : 'do',
                title : '操作',
                width : '10%',
                align : 'center',
                formatter : function(value, row) {
                    var str = '';
                    /* str = '<a href="javascript:edit(' + row.entranceId + ",'" + row.entranceName + "'," + row.entranceType + ",'" + row.entranceAppName +"'," + row.entranceAppId + ')">编辑</a>';*/
                    str += '<a href="javascript:areaEdit(' + row.id +",'" + row.areaName + "','" + row.areaTitle + "','" + row.areaIntroduce +  "','" + row.areaImg + '\')">编辑</a>';

                    str += '&nbsp;|&nbsp; <a href="javascript:delaa('+row.id+')">删除</a>';
                    return str;
                }
            } ] ],
        onLoadSuccess: function () {  //加载成功时执行
            $("#areaModal").modal({backdrop: 'static', keyboard: false});
        },
        onLoadError: function () {  //加载失败时执行
            // layer.msg("加载数据失败", {time: 1500, icon: 2});
        }
    });
}


function del(id) {
    if(confirm("是否删除")){
        var ID ={
            "id" : id}
        $.post(ctx + "/areaController/delete",ID,function (data) {
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

//上移
function upEntrance(id,seq) {
    var mapping = {
        'id': id,
        'seq': seq,
    }
    $.post(ctx + "/PortalAreaContenController/getAreaMinSeq",mapping,function (data) {
        var mapping1 = {
            'id' :id,
            'seq' :data.seq
        };
        var mapping2 = {
            'id':data.id,
            'seq':seq
        };
        $.post(ctx + "/PortalAreaContenController/update",mapping1,function (data) {
            if(data.returnValue == 0){
                $.post(ctx + "/PortalAreaContenController/update",mapping2,function (data) {
                    if(data.returnValue == 0){
                        alert("上移成功");
                        $("#areaContentTable").bootstrapTable('refresh',param);
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


//下移
function downEntrance(id,seq) {
    var mapping = {
        'id': id,
        'seq': seq,
    }
    $.post(ctx + "/PortalAreaContenController/getAreaMaxSeq",mapping,function (data) {
        var mapping1 = {
            'id' :id,
            'seq' :data.seq
        };
        var mapping2 = {
            'id':data.id,
            'seq':seq
        };
        $.post(ctx + "/PortalAreaContenController/update",mapping1,function (data) {
            if(data.returnValue == 0){
                $.post(ctx + "/PortalAreaContenController/update",mapping2,function (data) {
                    if(data.returnValue == 0){
                        alert("上移成功");
                        $("#areaContentTable").bootstrapTable('refresh',param);
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