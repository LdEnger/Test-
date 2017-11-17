$(document).ready(function () {
    initTable();
    option();
    optionsy();
    initUploadJs("recommendContentImg");
});
var param ={};
function initTable(beanName,isEffictive) {
    //先销毁表格
    $('#sample_1').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#sample_1").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/portalBeanCurdEditListController/getList", //获取数据的Servlet地址
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
                entranceName :beanName,
                isEffeCtice : isEffictive
            };
            return param;
        },
        columns : [ [ {
            field : 'entranceName',
            title : '豆腐块名称',
            width : '8%',
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
                }else {
                    return "定制应用"
                }
            }
        },{
            field : 'isEffeCtice',
            title : '上下线',
            width : '13%',
            align : 'center',
            formatter : function(value, row ,index){
                if (1 == value) {
                    return '<div class="abtn"><a href="javascript:void(0)" style="color:#7d7d7d;text-decoration: none;cursor:text;" class="a_on a_on2">上线</a>&nbsp;|&nbsp;&nbsp;<a href="javascript:isAPKPublish('+row.entranceId+','+row.isEffeCtice+')" class="btn btn-default btn-xs">下线</a></div>';
                } else {
                    return '<div class="abtn"><a href="javascript:isAPKPublish('+row.entranceId+','+row.isEffeCtice+')" class="btn btn-primary btn-xs">上线</a>&nbsp;|&nbsp;&nbsp;<a href="javascript:void(0)" style="color: #7d7d7d;text-decoration: none;cursor:text;" class="a_on a_on2">下线</a></div>';
                }
            }
        },{
            field : 'do',
            title : '操作',
            width : '10%',
            align : 'center',
            formatter : function(value, row) {
                var str = '';
                if(row.entranceType == 0){
                    str = '<a href="javascript:edit(' + row.entranceId + ",'" + row.entranceName + "'," + row.entranceType + ",'" + row.entranceAppName +"'," + row.entranceAppId + ')">编辑</a>';
                    str += '&nbsp;|&nbsp;<a href="javascript:beanCurd('+row.entranceId + ' )">内容图管理</a>';
                    str += '&nbsp;|&nbsp; <a href="javascript:del('+row.entranceId+')">删除</a>';
                }else {
                    str = '<a href="javascript:edit(' + row.entranceId + ",'" + row.entranceName + "'," + row.entranceType + ",'" + row.custommizeAppName +"'," + row.customizeAppId + ",'" + row.entranceAppVersion +'\' )">编辑</a>';
                    str += '&nbsp;|&nbsp;<a href="javascript:beanCurd('+row.entranceId + ' )">内容图管理</a>';
                    str += '&nbsp;|&nbsp; <a href="javascript:del('+row.entranceId+ ')">删除</a>';
                }

                return str;
            }
        } ] ],
        onLoadSuccess: function () {  //加载成功时执行
            //layer.msg("加载成功");
        },
        onLoadError: function () {  //加载失败时执行
            // layer.msg("加载数据失败", {time: 1500, icon: 2});
        }
    }); }

function isAPKPublish(entranceId,isEffeCtice) {
    var confirmInfo = null;
    var homw = {};
    if(isEffeCtice == 0){
        homw = {
            entranceId : entranceId,
            isEffeCtice :1
        }
        confirmInfo = "确定置为上线状态";
    }else {
        homw = {
            entranceId :entranceId,
            isEffeCtice : 0
        }
        confirmInfo = "确定置为下线状态";
    }
    if(confirm(confirmInfo)){
        $.post(ctx + "/portalBeanCurdEditListController/update",homw,function (data) {
            if(data.returnValue == 1 ){
                alert("修改失败");
                $("#sample_1").bootstrapTable('refresh',param);
            }else  if(data.returnValue == 0){
                alert("修改成功");
                $("#sample_1").bootstrapTable('refresh',param);
            }

        },"json")
    }

}
//修改豆腐内容列表赋值
function edit(entranceId,entranceName,entranceType,AppName,AppId,entranceAppVersion) {
 $("#txt_entrance_id").val(entranceId);
    $("#txt_entranceName").val(entranceName);
    $("#txt-entranceType").val(entranceType);
    if(entranceType == 0){
        $("#entranAppId").show();
        $("#txt-entrance_app_name").val(AppId);
        $("#nameId").hide();
        // $("#txt-entranceAppName1").show();
        // $("#txt-entranceAppName").hide();
    }else {
        $("#entranAppId").hide();
        $("#txt_lastvarsion").val(entranceAppVersion);
        $("#txt_entranceNames").val(AppName);
        $("#txt_apkName").val(AppName);
        $("#txt_apkId").val(AppId);
        $("#nameId").show();
        // $("#txt-entranceAppName1").hide();
        // $("#txt-entranceAppName").show();
    }
    $("#myModal").modal({backdrop: 'static', keyboard: false});
}
function editdd(){
    $("#txt-entrance_app_name").val('');
    $("#myModal").modal({backdrop: 'static', keyboard: false});
}

//初始化下拉框
function option() {
    var selectVar = $("#txt-entranceType").val();
    if(selectVar == 0){
        $("#nameId").hide();
        $("#txt-entrance_app_name").val('');
        $("#entranAppId").show();
    }else {
        $("#txt-entrance_app_name").val('');
        $("#entranAppId").hide();
        $("#nameId").show();
    }
}
function seleceChang(){
    var selectVar = $("#txt-entranceType").val();
    if(selectVar == 1){
        $("#txt-entrance_app_name").val('');
        $("#entranAppId").hide();
        $("#nameId").show();
        // $("#txt-entranceAppName").show();
        // $("#txt-entranceAppName1").hide();
    }else {
        $("#txt_entranceNames").val('');
        $("#entranAppId").show();
        $("#nameId").hide();
        // $("#txt-entranceAppName").hide();
        // $("#txt-entranceAppName1").show();
    }
}
//内容图表格
var param ={};
function beanCurd(entranceId) {
    $("#txt_entranceId").val(entranceId);
    //先销毁表格
    $('#sample_three').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#sample_three").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/portalBeanCurdImgListController/getPageList", //获取数据的Servlet地址
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
                entranceId : entranceId
            };
            return param;
        },
        columns : [ [ {
            field : 'seq',
            title : '顺序',
            width : '8%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        },{
            field :'entranceTitle' ,
            title : '标题',
            width : '12%',
            align : 'center',
            formatter : function(value, row ,index){
                return value;
            }
        },{
            field :'entranceSubtitle' ,
            title : '副标题',
            width : '12%',
            align : 'center',
            formatter : function(value, row ,index){
                return value;
            }
        },{
            field : 'isEffective',
            title : '状态',
            width : '13%',
            align : 'center',
            formatter : function(value, row, index) {
                if(1 == value){
                    return '<div>有效</div>'
                }else {
                    return '<div>无效</div>'
                }

            }
        },{
            field :'entranceImg' ,
            title : '图片',
            width : '12%',
            align : 'center',
            formatter : function(value, row ,index){
                return "<img style='height:80px;width:100px;' src='" + value + "'/>";
            }
        },{
            field : 'do',
            title : '操作',
            width : '10%',
            align : 'center',
            formatter : function(value, row) {
                var str = '';/*
                '<a href="javascript:edit('+ row.entranceId +",'" + row.entranceName + "',"+ row.entranceType + ')">编辑</a>';*/
                str += '<a href="javascript:ImgEdit('+ row.imgId +')">编辑</a>';
                str += '&nbsp;|&nbsp; <a href="javascript:ImgDel('+row.imgId+')">删除</a>';
                return str;
            }
        } ] ],
        onLoadSuccess: function () {  //加载成功时执行
            $("#myModalSy").modal({backdrop: 'static', keyboard: false});
        },
        onLoadError: function () {  //加载失败时执行
            // layer.msg("加载数据失败", {time: 1500, icon: 2});
        }
    });
    // setTimeout(function(){
    //
    // },1500);
}
function  beanCurdNameSelect() {
    var entranceTitle = $("#entranceTitleLike").val();
        var opt = {
        url : ctx + "/portalBeanCurdImgListController/getPageList",
        slient : true,
        query :{
            entranceTitle : entranceTitle
        }
    };
    $("#sample_three").bootstrapTable("refresh",opt);

}
function del(entranceId){
debugger;
    if(confirm("是否确认删除")){
            var  beanCurd = {
                entranceId : entranceId
            }
            $.post(ctx + "/portalBeanCurdMappingController/getMappingCount",beanCurd,function (data) {
                if(data == 0){

                    $.post(ctx + "/portalBeanCurdEditListController/delete",beanCurd,function (data) {
                        if(data.returnValue == 0){
                            alert("删除成功");
                            $("#sample_1").bootstrapTable('refresh', param);
                            if($("#sample_1").bootstrapTable('getData').length == 1){
                                $("#sample_1").bootstrapTable('prevPage');
                            }
                        }
                    },"json");
                }else {
                    alert("已于其他应用关联,无法删除");
                }
            },"json");


    }
}
function  ImgDel(imgId) {
    if(confirm("是否确认删除")){
        var imgCurd = {
            imgId : imgId
        }
        $.post(ctx + "/portalBeanCurdImgListController/delete",imgCurd,function (data) {
           if(data.returnValue == 0){
               alert("删除成功");
               $("#sample_three").bootstrapTable('refresh', param);
               if($("#sample_three").bootstrapTable('getData').length == 1){
                   $("#sample_three").bootstrapTable('prevPage');
               }
           }else {
               alert("删除失败");
           }
        },"json");
    }
}
function ImgEdit(imgId) {
    $("#recommendContentImg").val('');
    $("#recommendContentImgUrl").val('');
    imgAddrEdit(imgId);
}
function imgAddrEdit(imgId) {
    $("#txt_imgId").val(imgId);
    var imgCurdImg = {
        imgId :imgId
    }
    $.post(ctx + "/portalBeanCurdImgListController/getImgList",imgCurdImg,function (data) {
        $("#txt_imgSeq").val(data.seq);
        $("#txt_entranceTitle").val(data.entranceTitle);
        $("#txt_entranceSubtitle").val(data.entranceSubtitle);
        $("#text-isEffective").val(data.isEffective);
        $("#recommendContentImg").attr('src',data.entranceImg);
    },"json");
    $("#myModalThree").modal({backdrop: 'static', keyboard: false});
}


function beanCurdImgAdd() {
    $("#myModalThree").modal({backdrop: 'static', keyboard: false});
}

function beanCurdImgList_submit() {
    var entranceId = $("#txt_entranceId").val();
    var imgId = $("#txt_imgId").val();
    var imgSeq = $("#txt_imgSeq").val();
    var entranceTitle = $("#txt_entranceTitle").val();
    var entranceSubtitle = $("#txt_entranceSubtitle").val();
    var isEffective = $("#text-isEffective").val();
    var imgUrl = $("#recommendContentImg")[0].src;

    console.log(imgUrl);
    var imgAddr = imgUrl.substring(imgUrl.length- 4,imgUrl.length + 1);


    if($.trim(imgSeq) == ''){
        alert("顺序不能为空");
        return;
    }
    if($.trim(entranceTitle) == ''){
        alert("标题不能为空");
        return;
    }
    if(imgAddr == 'list'){
        alert("请上传图片");
        return;
    }
    var beanCurdImg = {
        'imgId' :imgId,
        'seq' : imgSeq,
        'entranceTitle' :entranceTitle,
        'entranceSubtitle' : entranceSubtitle,
        'isEffective' : isEffective,
        'entranceImg' :imgUrl,
        'entranceId' : entranceId
    }
    if(imgId != null && imgId != ''){
        $.post(ctx + "/portalBeanCurdImgListController/update",beanCurdImg,function(data){
            if(data.returnValue == 0){
                alert("修改成功");
                $("#myModalThree").modal('hide');
                $("#sample_three").bootstrapTable('refresh',param);
                clear_modalThree();
            }else {
                alert("修改失败");
            }
        },"json");
    }else {
        $.post(ctx + "/portalBeanCurdImgListController/save",beanCurdImg,function (data) {
            if(data.returnValue  == 0){
                alert("新增成功");
                $("#myModalThree").modal('hide');
                $("#sample_three").bootstrapTable('refresh',param);
                clear_modalThreesy();
            }else {
                alert("新增失败");
            }
        },"json");
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
        'fileSizeLimit' : '5120KB',
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

            if (file_id == "recommendContentImg") {
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
function clear_modalThree() {

    $("#txt_imgId").val('');
    $("#txt_imgSeq").val('');
    $("#txt_entranceTitle").val('');
    $("#txt_entranceSubtitle").val('');
 /*   $("#text-isEffective").val('');*/
    $("#recommendContentImg").attr('src','');
    $("#recommendContentImgUrl").val('');
    $("#myModalThree").modal("hide");
}
function clear_modalThreesy(){
    $("#text-isEffective").val(1);
    $("#txt_imgId").val('');
    $("#txt_imgSeq").val('');
    $("#txt_entranceTitle").val('');
    $("#txt_entranceSubtitle").val('');
    $("#recommendContentImg").attr('src','');
    $("#recommendContentImgUrl").val('');
    $("#myModalThree").modal("hide");
}




function beanCurdNameLike() {
   var beanName = $("#txt_beanCurdEntranceName").val();
    var isEffictive = $("#txt_beanIsEffecitveLike").val();
/*    var ppt = {
        url : ctx + "/portalBeanCurdEditListController/getList",
        silent :true,
        query:{
            entranceName :beanName,
            isEffeCtice : isEffictive
        }
    }
    $("#sample_1").bootstrapTable('refresh',ppt);*/
    initTable(beanName,isEffictive);
}

function clean_entrance() {
    $("#txt_entrance_id").val('');
    $("#txt_entranceName").val('');
/*    $("#txt-entranceType").val('');*/
    $("#txt-entrance_app_name").val('');
    $("#txt_entranceNames").val('');
/*    $("#txt-entranceType").empty();*/
    $("#myModal").modal('hide');
}


function cleanEditSaveAndUpdate() {
    $("#txt_apkName").val('');
    $("#txt_apkId").val('');
    $("#txt_lastvarsion").val('');
    $("#txt_isefficite").val('');
    $("#txt_installStyle").val('');
    $("#sample_1").bootstrapTable('refresh',param);
}

function  clear_modalSy() {
    $("#myModalSy").modal('hide');
}
function beanCurdImg_submit() {
    $("#myModalSy").modal('hide');
}

function clear_modalTo() {
    $("#myModalTwo").modal('hide');

}

function beanCurdButton() {
    //先销毁表格
    $('#sample_ApkTable').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#sample_ApkTable").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/apk/getPageList", //获取数据的Servlet地址
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
            };
            return param;
        },
        columns : [ [ {
            field : '',
            title : '',
            width : '8%',
            align : 'center',
            radio : true,
            formatter : function(value, row, index) {
                return value;
            }

        },{
            field : 'apkName',
            title : '订制APK名称 ',
            width : '13%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        },{
            field :'lastVersion' ,
            title : '最新版本',
            width : '12%',
            align : 'center',
            formatter : function(value, row ,index){
              return value;
            }
        },{
            field : 'installStyle',
            title : '安装方式',
            width : '10%',
            align : 'center',
            formatter : function(value, row) {
                if(0 == value){
                    return "询问安装";
                }else {
                    return "静默安装";
                }
            }
        } ] ],
        onLoadSuccess: function () {  //加载成功时执行
            //layer.msg("加载成功");
        },
        onLoadError: function () {  //加载失败时执行
            // layer.msg("加载数据失败", {time: 1500, icon: 2});
        }
    });
    setTimeout(function(){
        $("#myModalDaboTwo").modal({backdrop: 'static', keyboard: false});
    },1500);
}

function cleanApkModal() {
    $("#myModalDaboTwo").modal('hide');
}
function apkNameListSelect() {
    var apkName = $("#apkNameLikeSelect").val();
    var ppt = {
        url : ctx + "/apk/getPageList",
        silent :true,
        query:{
            apkName : apkName
        }
    }
    $("#sample_ApkTable").bootstrapTable('refresh',ppt);
}
//初始化系统应用下拉框
function optionsy() {
    var pp  = {
        isEffective : 1
    }
    $.ajax({
        type : "post",
        async : true,
        url : ctx + "/start/getComboboxList",
        dataType : "json",
        data : pp,
        success : function (res) {
            $("#txt-entrance_app_name").empty();
            var select=$("<option value='' style='display:none'>-请选择-</option>");
            $("#txt-entrance_app_name").append(select);
            $.each(res,function (index,element) {
                var options = $("<option value='" + element['id'] + "'>" +  element['appName'] + "</option>");
                $("#txt-entrance_app_name").append(options);
            });
        }
    });
}

function clear_entran() {
    $("#txt-entrance_app_name").val('');
    $("#txt-entranceType").val('');
    $("#txt-entrance_app_name").val('');
}
//添加编辑
function beanCurdEdit_submit() {
    var ID = $("#txt_entrance_id").val();
    var type = $("#txt-entranceType").val();
    var appId = $("#txt-entrance_app_name").val();
    var appName = $("#txt-entrance_app_name option:selected").text();
    var entranceName  = $("#txt_entranceName").val();
    if(type == 0){
        var apkId = null;
        var apkName = null;
        var lastvarsion = null;
        var installStyle = null;
    }else {
        var apkId = $("#txt_apkId").val();
        var apkName = $("#txt_apkName").val();
        var lastvarsion = $("#txt_lastvarsion").val();
        var installStyle = $("#txt_installStyle").val();0
    }
   if($.trim(entranceName) == ''){
        alert("应用名称不能为空");
        return;
   }
   if($.trim(appName) == '' && $.trim(apkName) == ''){
       alert("关联应用不能为空");
       return;
   }

    var ppt = {
        entranceId : ID,
        entranceType: type,
        entranceName:entranceName,
        entranceAppId : appId,
/*        entranceAppName : appName,*/
        customizeAppId : apkId,
        custommizeAppName : apkName,
        entranceAppVersion :lastvarsion,
        entranceAppInstall : installStyle
    }

        if (ID != null && ID != '') {
            $.post(ctx + "/portalBeanCurdEditListController/update", ppt, function (data) {
                if (data.returnValue == 0) {
                    alert("修改成功");
                    cleanEditSaveAndUpdate();
                    clean_entrance();
                } else {
                    alert("修改失败");
                }
            }, "json");
        } else {
            $.post(ctx + "/portalBeanCurdEditListController/save", ppt, function (data) {
                if (data.returnValue == 0) {
                    alert("添加成功");
                    cleanEditSaveAndUpdate();
                    clean_entrance();
                } else {
                    alert("添加失败");
                }
            }, "json");
        }

}

function appIcon_submit() {
    var apkSelectRow = $("#sample_ApkTable").bootstrapTable('getSelections');
 $.each(apkSelectRow,function (index, value) {
     if(value.lastVersion == null){
         alert("不能关联版本号为空的应用");
         return;
     }else {
    $("#txt_apkId").val(value.id);
    $("#txt_apkName").val(value.apkName);
    $("#txt_lastvarsion").val(value.lastVersion);
    $("#txt_isefficite").val(value.isEffective);
    $("#txt_installStyle").val(value.installStyle);
     $("#txt_entranceNames").val(value.apkName);
         $("#myModalDaboTwo").modal('hide');
}}
 );

}