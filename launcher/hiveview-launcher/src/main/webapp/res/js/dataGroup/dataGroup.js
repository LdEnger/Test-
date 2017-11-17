
$(document).ready(function () {
    initTable();
    initUploadJs("areaImg");
    initUploadJs("recommendContentImg");
    channelApk();
    HotOption();
    hotChannel();
    packageNameSelectChange();
    selectChang();
    upAndDownSelect();
    pageckageSelect();
    appSelect();

});
var param ={};

//初始化第一张表格
function initTable(dataGroupName,effective) {
    //先销毁表格
    $('#sample_1').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#sample_1").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/PortalDataGroupController/getPage", //获取数据的Servlet地址
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
                dataGroupName :dataGroupName,
                effective :effective
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
            field : 'dataGroupName',
            title : 'Data Group名称',
            width : '13%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        },{
            field : 'dataGroupTitle',
            title : 'Data Group标题名称',
            width : '13%',
            align : 'center',
            formatter : function(value, row, index) {

                return value;
            }
        }, {
                field :'rowAndCol' ,
                title : '模版',
                width : '12%',
                align : 'center',
                formatter : function(value, row ,index){
                    var str = '';
                    if(row.row != null){
                        str += row.row + "行";
                    }
                    if(row.col !=  null){
                        str += row.col + "列";
                    }
                    return str;
                }
            },{
            field : 'contentType',
            title : '内容类型',
            width : '13%',
            align : 'center',
            formatter : function(value, row, index) {
                var type = '';
                if(row.contentType == 0){
                    type += '频道推荐';
                }else if(row.contentType == 1){
                    type += '热词内容列表';
                }else if (row.contentType == 2){
                    type += '频道内容列表';
                }
                return type;
            }
        }, {
            field :'effective' ,
            title : '上下线',
            width : '12%',
            align : 'center',
            formatter : function(value, row ,index){
                if (1 == value) {
                    return '<button style="display:inline-block;color:#fff;background:rgb(46,130,255);cursor:default;border-radius:3px 0 0 3px;border-top:1px solid #808080;border-bottom:1px solid #808080;border-left:1px solid #808080;height:26px;border-right:0" class="a_on a_on2">上线</button><a href="javascript:isEffective('+row.id+','+row.effective+')" style="color:rgb(255,102,0);display: inline-block;height:24px;width:40px;border-top:1px solid #808080;border-bottom:1px solid #808080;border-right:1px solid #808080;vertical-align: -1px;line-height: 24px;text-decoration: none;border-radius:0 3px 3px 0">下线</a>';
                } else {
                    return '<a href="javascript:isEffective('+row.id+','+row.effective+')" style="color:rgb(255,102,0);display: inline-block;height:24px;width:40px;border-top:1px solid #808080;border-bottom:1px solid #808080;border-left:1px solid #808080;vertical-align: -1px;line-height: 24px;text-decoration: none;border-radius:3px 0 0 3px">上线</a><button style="color:#fff;background:rgb(46,130,255);cursor:default;border-radius:0 3px 3px 0;border-top:1px solid #808080;border-bottom:1px solid #808080;border-right:1px solid #808080;height:26px;border-left:0" class="a_on a_on2">下线</button>';
                }
            }
        },{
                field : 'do',
                title : '操作',
                width : '10%',
                align : 'center',
                formatter : function(value, row) {
                    var str = '';
                    if(row.contentType == 2){
                        str += '<a href="javascript:dataGroupEdit(' + row.id +')">编辑</a>';
                        str += '&nbsp;|&nbsp;<span style="color:#7d7d7d">关联内容</span>';
                        str += '&nbsp;|&nbsp; <a href="javascript:del('+row.id+')">删除</a>';
                    }else if(row.contentType == 1){
                        str += '<a href="javascript:dataGroupEdit(' + row.id +')">编辑</a>';
                        str += '&nbsp;|&nbsp;<a href="javascript:rightDataGroup('+ row.id+ ",'" + row.hotName + "'," + row.hotId +')">关联内容</a>';
                        str += '&nbsp;|&nbsp; <a href="javascript:del('+row.id+')">删除</a>';
                    }else if(row.contentType == 0){
                        if(row.packageName == 1){
                        str += '<a href="javascript:dataGroupEdit(' + row.id +')">编辑</a>';
                        str += '&nbsp;|&nbsp;<a href="javascript:leftDataGroup('+row.id+  ')">关联内容</a>';
                        str += '&nbsp;|&nbsp; <a href="javascript:del('+row.id+')">删除</a>';
                        }else if(row.packageName == 2){
                            str += '<a href="javascript:dataGroupEdit(' + row.id +')">编辑</a>';
                            str += '&nbsp;|&nbsp;<a href="javascript:leftDataGroup('+row.id+  ')">关联内容</a>';
                            str += '&nbsp;|&nbsp; <a href="javascript:del('+row.id+')">删除</a>';
                        }
                    }
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
        $.post(ctx + "/PortalDataGroupController/updateEffective",hotWords,function (data) {
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


//专区管理编辑弹框
function dataGroupEdit(id) {
    $.post(ctx + "/tabGroup/getCount",{'belongGroupId':id,'groupType':3},function (data) {
        if(data > 0){
            alert("已经与Tab管理关联,无法编辑");
            return;
        }else {
            $.post(ctx + "/PortalDataGroupController/getGroupVo",{'id':id},function (data) {
                if(data.contentType == 0){
                    if(data.packageName ==1) {
                        $("#txt_dataGroupId").val(data.id);
                        $("#txt_dataGroupName").val(data.dataGroupName);
                        $("#txt_dataGroupTitle").val(data.dataGroupTitle);
                        $("#txt_contentType").val(data.contentType);
                        $("#txt_packageName").val(data.packageName);
                        $("#txt_channel").val(data.channel);
                        $("#txt_width").val(data.width);
                        $("#txt_height").val(data.height);
                        $("#txt_row").val(data.row);
                        $("#txt_col").val(data.col);
                        $("#txt_up_and_down").val(data.upAndDown)
                        $("#dataGroupPageName").show();
                        $("#apkChannelDiv").hide();
                        $("#channelId").hide();
                        $("#chanID").show();
                        $("#txt_up_and_down").attr("disabled","disabled");
                        $("#txt_apk_channel").attr("disabled","disabled");
                        $("#txt_apkPackageName").attr("disabled","disabled");
                        $("#txt_contentType").attr("disabled","disabled");
                        $("#txt_channel").attr("disabled","disabled");
                        $("#txt_channelId").attr("disabled","disabled");
                        $("#txt_packageName").attr("disabled","disabled");
                        heightInputEdit();
                        widthInputEdit();
                        $("#myModal").modal({backdrop: 'static', keyboard: false});
                    }else if(data.packageName == 2){
                        $("#txt_dataGroupId").val(data.id);
                        $("#txt_dataGroupName").val(data.dataGroupName);
                        $("#txt_dataGroupTitle").val(data.dataGroupTitle);
                        $("#txt_contentType").val(data.contentType);
                        $("#txt_packageName").val(data.packageName);
                        $("#txt_channelId").val(data.categoryId);
                        $("#txt_width").val(data.width);
                        $("#txt_height").val(data.height);
                        $("#txt_row").val(data.row);
                        $("#txt_col").val(data.col);
                        $("#txt_up_and_down").val(data.upAndDown)
                        $("#dataGroupPageName").show();
                        $("#apkChannelDiv").hide();
                        $("#channelId").show();
                        $("#chanID").hide();
                        $("#txt_up_and_down").attr("disabled","disabled");
                        $("#txt_apk_channel").attr("disabled","disabled");
                        $("#txt_apkPackageName").attr("disabled","disabled");
                        $("#txt_contentType").attr("disabled","disabled");
                        $("#txt_channel").attr("disabled","disabled");
                        $("#txt_channelId").attr("disabled","disabled");
                        $("#txt_packageName").attr("disabled","disabled");
                        heightInputEdit();
                        widthInputEdit();
                        $("#myModal").modal({backdrop: 'static', keyboard: false});
                    }
                }else if(data.contentType == 1){
                    $("#txt_dataGroupId").val(data.id);
                    $("#txt_dataGroupName").val(data.dataGroupName);
                    $("#txt_dataGroupTitle").val(data.dataGroupTitle);
                    $("#txt_contentType").val(data.contentType);
                    $("#txt_width").val(data.width);
                    $("#txt_height").val(data.height);
                    $("#txt_row").val(data.row);
                    $("#txt_col").val(data.col);
                    $("#txt_up_and_down").val(data.upAndDown)
                    $("#dataGroupPageName").hide();
                    $("#apkChannelDiv").hide();
                    $("#channelId").hide();
                    $("#chanID").hide();
                    $("#txt_up_and_down").attr("disabled","disabled");
                    $("#txt_apk_channel").attr("disabled","disabled");
                    $("#txt_apkPackageName").attr("disabled","disabled");
                    $("#txt_contentType").attr("disabled","disabled");
                    $("#txt_channel").attr("disabled","disabled");
                    $("#txt_channelId").attr("disabled","disabled");
                    $("#txt_packageName").attr("disabled","disabled");
                    heightInputEdit();
                    widthInputEdit();
                    $("#myModal").modal({backdrop: 'static', keyboard: false});
                }else  if (data.contentType == 2){
                    $("#txt_dataGroupId").val(data.id);
                    $("#txt_dataGroupName").val(data.dataGroupName);
                    $("#txt_dataGroupTitle").val(data.dataGroupTitle);
                    $("#txt_contentType").val(data.contentType);
                    $("#txt_apkPackageName").val(data.apkPackageName);
                    $("#txt_apk_channel").val(data.channel);
                    $("#txt_width").val(data.width);
                    $("#txt_height").val(data.height);
                    $("#txt_row").val(data.row);
                    $("#txt_col").val(data.col);
                    $("#txt_up_and_down").val(data.upAndDown)
                    $("#dataGroupPageName").hide();
                    $("#apkChannelDiv").show();
                    $("#channelId").hide();
                    $("#chanID").hide();
                    heightInputEdit();
                    widthInputEdit();
                    $("#txt_up_and_down").attr("disabled","disabled");
                    $("#txt_apk_channel").attr("disabled","disabled");
                    $("#txt_apkPackageName").attr("disabled","disabled");
                    $("#txt_contentType").attr("disabled","disabled");
                    $("#txt_channel").attr("disabled","disabled");
                    $("#txt_channelId").attr("disabled","disabled");
                    $("#txt_packageName").attr("disabled","disabled");
                    $("#myModal").modal({backdrop: 'static', keyboard: false});
                }
            },'json');
        }
    },'json');
}

//专区管理添加弹框
function areaAdd() {
        $("#txt_contentType option:first").prop("selected", 'selected');
        $("#txt_packageName option:first").prop("selected", 'selected');
        $("#txt_apkPackageName option:first").prop("selected", 'selected');
        $("#txt_apk_channel option:first").prop("selected", 'selected');
        $("#txt_row").attr("readOnly",true);
        $("#txt_col").attr("readOnly",true);
        $("#dataGroupPageName").show();
        $("#chanID").show();
        $("#apkChannelDiv").hide();
        $("#txt_channel option:first").prop("selected", 'selected');
         $("#txt_channelId option:first").prop("selected", 'selected');
        $("#txt_up_and_down option:first").prop("selected", 'selected');
        $("#txt_up_and_down").removeAttr("disabled");
        $("#txt_apk_channel").removeAttr("disabled");
        $("#txt_apkPackageName").removeAttr("disabled");
        $("#txt_contentType").removeAttr("disabled");
        $("#txt_channel").removeAttr("disabled");
        $("#txt_channelId").removeAttr("disabled");
        $("#txt_packageName").removeAttr("disabled");
    $("#myModal").modal({backdrop: 'static', keyboard: false});
}

//编辑添加按钮取消弹框
function  clear_diaglog() {
    $("#txt_dataGroupId").val('');
    $("#txt_dataGroupName").val('');
    $("#txt_dataGroupTitle").val('');
    $("#txt_contentType").val('');
    $("#txt_packageName").val('');
    $("#txt_channel").val('');
    $("#txt_channelId").val('');
    $("#txt_apkPackageName").val('');
    $("#txt_apk_channel").val('');
    $("#txt_up_and_down").val('');
    $("#txt_width").val('');
    $("#txt_height").val('');
    $("#txt_row").val('');
    $("#txt_col").val('');
    $("#myModal").modal("hide");
}
function area_submit() {
    var id =  $("#txt_dataGroupId").val();
    var dataGroupName = $("#txt_dataGroupName").val();
    var dataGroupTitle = $("#txt_dataGroupTitle").val();
    var contentType =  $("#txt_contentType").val();
    var packageName =  $("#txt_packageName").val();
    var channel =  $("#txt_channel").val();
    var width = $("#txt_width").val();
    var height = $("#txt_height").val();
    var row =  $("#txt_row").val();
    var col =  $("#txt_col").val();
    var apkBagName = $("#txt_apkPackageName").val();
    var apkChannel = $("#txt_apk_channel").val();
    var categoryId = $("#txt_channelId").val();
    var upAndDown = $("#txt_up_and_down").val();

    if($.trim(dataGroupName) == ""){
        alert("Data Group 名称不能为空");
        return;
    }
    if($.trim(dataGroupTitle) == ""){
        alert("Data Group 标题名称不能为空");
        return;
    }
        if(width == "" || height == ""){
            alert("推荐位尺寸不能为空");
            return;
        }
        if(row == '' && col == ''){
            alert("行列至少选填一项");
            return;
        }
/*    if(width <165 || width > 260 ){
            alert("标准尺寸应该为 165px<=w<=260px");
            return;
    }
    if(height < 95 || height > 360 ){
        alert("标准尺寸应该位95px<=h<=360px");
        return
    }*/
        if(contentType == 0){
        if(packageName == 1) {
            var dataGroup = {
                "id": id,
                "dataGroupName": dataGroupName,
                "dataGroupTitle": dataGroupTitle,
                "contentType": contentType,
                "packageName": packageName,
                "channel": channel,
                "width": width,
                "height": height,
                "row": row,
                "col": col,
                "apkPackageName": 'com.hiveview.cloudscreen.vipvideo',
                "categoryId": '',
                "upAndDown":upAndDown
            };
        }else {
            var dataGroup = {  "id": id,
                "dataGroupName": dataGroupName,
                "dataGroupTitle": dataGroupTitle,
                "contentType": contentType,
                "packageName": packageName,
                "channel": '',
                "width": width,
                "height": height,
                "row": row,
                "col": col,
                "apkPackageName": 'com.hiveview.cloudscreen.appstore',
                "categoryId": categoryId,
                "upAndDown":upAndDown
            }
        }
        }else if(contentType == 1){
            var dataGroup = {
                "id" : id,
                "dataGroupName" : dataGroupName,
                "dataGroupTitle" : dataGroupTitle,
                "contentType" : contentType,
                "packageName" : '',
                "channel" : '',
                "width" :width,
                "height" :height,
                "row":row,
                "col":col,
                "apkPackageName":'',
                "categoryId":'',
                "upAndDown":upAndDown
            };
        }else {
            var dataGroup = {
                "id" : id,
                "dataGroupName" : dataGroupName,
                "dataGroupTitle" : dataGroupTitle,
                "contentType" : contentType,
                "packageName" : '',
                "channel" : apkChannel,
                "width" :width,
                "height" :height,
                "row":row,
                "col":col,
                "apkPackageName":apkBagName,
                "categoryId":'',
                "upAndDown":upAndDown
            };
        }

    if(id != null && id != ""){
        $.post(ctx + "/PortalDataGroupController/update",dataGroup, function (data) {
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
        $.post(ctx + "/PortalDataGroupController/save",dataGroup,function (data) {
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
/*
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
                /!* str = '<a href="javascript:edit(' + row.entranceId + ",'" + row.entranceName + "'," + row.entranceType + ",'" + row.entranceAppName +"'," + row.entranceAppId + ')">编辑</a>';*!/
                str += '<a href="javascript:areaEdit(' + row.id +",'" + row.areaName + "','" + row.areaTitle + "','" + row.areaIntroduce +  "','" + row.areaImg + '\')" >编辑</a>';

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
}*/
//删除
function del(id) {
    if(confirm("是否删除")){
        var ID ={
            "id" : id}
        $.post(ctx + "/PortalDataGroupController/delete",ID,function (data) {
            if(data.returnValue == 0){
                alert("删除成功");
                $("#sample_1").bootstrapTable('refresh',param);
                if($("#sample_1").bootstrapTable('getData').length == 1){
                    $("#sample_1").bootstrapTable('prevPage');
                }
            }else if(data.returnValue == -1){
                alert("不能删除,有关联");
            }else {
                alert("删除失败");
            }
        },"json");
    }
}
/*

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
*/

/*

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
*/

//初始化下拉框
function selectChang() {
    var contentType = $("#txt_contentType").val();
    if(contentType == 0){
    $("#dataGroupPageName").show();
    $("#apkChannelDiv").hide();
    packageNameSelectChange();
    }else if(contentType == 1){
        $("#dataGroupPageName").hide();
        $("#apkChannelDiv").hide();
        $("#channelId").hide();
        $("#chanID").hide();
    }else if(contentType == 2){
        $("#dataGroupPageName").hide();
        $("#apkChannelDiv").show();
        $("#channelId").hide();
        $("#chanID").hide();
        packageNameSelectChange();
    }
}

function channelApk() {
    var  packageName = $("#txt_apkPackageName").val();
    var ppt = {apkBagName: packageName};
    $.ajax({
        type: "post",
        async : true,
        url : ctx + "/NewTempletApkChannelController/getChannelPage",
        dataType:"json",
        data :ppt,
        success: function (res) {
            $("#txt_apk_channel").empty();
            $.each(res ,function (index,element) {
                var options = $("<option value ='" + element['id'] +"'>" + element['cName'] + "</option>");
                $("#txt_apk_channel").append(options);
            })
        }
    });
}
function apkbagChannle(){
    var  packageName = $("#txt_apkPackageName").val();
    // console.info(packageName);
    var ppt = {apkBagName: packageName};
    $.ajax({
        type: "post",
        async : true,
        url : ctx + "/NewTempletApkChannelController/getChannelPage",
        dataType:"json",
        data :ppt,
        success: function (res) {
            $("#txt_apk_channel").empty();
            $.each(res ,function (index,element) {
                var options = $("<option value ='" + element['id'] +"'>" + element['cName'] + "</option>");
                $("#txt_apk_channel").append(options);
            })
        }
    });
}


//频道推荐下拉框初始
function packageNameSelectChange() {
    var contentType = $("#txt_contentType").val();
    if(contentType == 0){
    var  packageName = $("#txt_packageName").val();
    if(packageName == 2){
        $("#chanID").hide();
        $("#channelId").show();
    }else if(packageName == 1){
        $("#chanID").show();
        $("#channelId").hide();
    }
    }else if(contentType == 2){
        channelApk();
    }
}
function leftDataGroup(id,channel,categoryId) {
    // console.log("id:"+id)
    $.post(ctx + "/PortalDataGroupController/getGroupVo", {id : id}, function(data) {
        if(data.packageName == 2){
            $("#content_chn_id").val(data.categoryId);
        }else{
            $("#content_chn_id").val(data.channel);
        }
    }, "json");
    $("#seleOperateContent").val(id);
    // console.log("chanel:"+channel)
    var opt = {
        url: ctx + "/fixedRecommendContent/getPageList",
        silent: true,
        query: {
            fixedRecomId: id
        }
    };
    $("#sample_table1").bootstrapTable('refresh', opt);
    initTb(id);
    // console.log("categoryId:"+categoryId)
}

//初始化第二张表
function initTb(id){
    //先销毁表格
    $('#sample_table1').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#sample_table1").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/fixedRecommendContent/getPageList", //获取数据的Servlet地址
        striped: true,  //表格显示条纹
        clickToSelect: false,
        pagination: true, //启动分页
        pageSize: 10,  //每页显示的记录数
        pageNumber: 1, //当前第几页
        pageList: [5,10,20],  //记录数可选列表
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
                fixedRecomId:id,
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
            field : 'contentName',
            title : '推荐位标题',
            width : '13%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        },
            {
                field : 'contentImg',
                title : '图片',
                width : '18%',
                align : 'center',
                formatter : function(value) {
                    return "<img style='height:60px;width:75px;' src='" + value + "'/>";
                }
            },
            {
                field : 'seqDo',
                title : '顺序操作',
                width : '13%',
                align : 'center',
                formatter :function (value,row,index) {
                    var str = "";
                    if(true){
                        str += '<a href="javascript:stickFixedRecomment(' + row.id+"," + row.seq + ","+row.fixedRecomId + ')">置顶</a>';
                        str += '&nbsp;|&nbsp; <a href="javascript:upEntrance(' + row.id +"," + row.seq + "," + row.fixedRecomId+ ')">上移</a>'
                        str += '&nbsp;| <a href="javascript:downEntrance('+ row.id + "," + row.seq + "," + row.fixedRecomId + ')">下移</a>'
                    }
                    return str;
                }
            },
            {
                field : 'do',
                title : '操作',
                width : '14%',
                align : 'center',
                formatter : function(value, row) {
                    var str = '';

                    str += '<a href="javascript:edit11('+row.id+')">编辑</a>';
                    str += '&nbsp;|&nbsp; <a href="javascript:del11('+row.id+')">删除</a>';
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
    $('#myModalTwo').modal({width: '800Px',backdrop: 'static',keyboard: false });
}
function del11 (id) {
    if(confirm("是否确认删除")){
        var recom = {
            id : id
        };
        $.post(ctx + "/fixedRecommendContent/delete",recom,function (data) {
            if(data.returnValue == 0){
                alert("成功");
                $("#sample_table1").bootstrapTable('refresh',param);
                // console.info($("#sample_table1").bootstrapTable('getData').length);
                if( $("#sample_table1").bootstrapTable('getData').length == 1){
                    $("#sample_table1").bootstrapTable('prevPage');
                }
            }else {
                alert('失败！');
            }
        },"json")
    }
}
//置顶
function stickFixedRecomment(id,seq,fixedRecomId) {
    var fixedRecomContent = {
        'fixedRecomId':fixedRecomId,
    }
    var minSeq = null;
    $.post(ctx + "/fixedRecommendContent/getMinSeq", fixedRecomContent, function(data) {
        if(seq == data){

        }else {
            minSeq = parseInt(data) - 1;
            var content = {
                'id': id,
                'seq': minSeq,
            };
            $.post(ctx + "/fixedRecommendContent/update", content, function (data) {
                if (data.returnValue == 0) {
                    alert('置顶成功！');
                    $("#sample_table1").bootstrapTable('refresh', param);
                } else {
                    alert('置顶失败！');
                }
            }, "json");
        } }, "json");
}
//上移
function upEntrance(id,seq,fixedRecomId) {
    var mapping = {
        'id': id,
        'seq': seq,
        'fixedRecomId' :fixedRecomId
    }
    $.post(ctx + "/fixedRecommendContent/getMinMapping",mapping,function (data) {
        if(seq == data.seq ){
            alert("已经到第一个,不用上移");
        }else {
            var mapping1 = {
                'id': id,
                'seq': data.seq
            };
            var mapping2 = {
                'id': data.id,
                'seq': seq
            };
            $.post(ctx + "/fixedRecommendContent/update", mapping1, function (data) {
                if (data.returnValue == 0) {
                    $.post(ctx + "/fixedRecommendContent/update", mapping2, function (data) {
                        if (data.returnValue == 0) {
                            alert("上移成功");
                            $("#sample_table1").bootstrapTable('refresh', param);
                        } else {
                            alert("上移失败");
                        }
                    }, "json");
                } else {
                    alert("上移失败");
                }
            }, "json");

        }},"json");
}
//下移
function downEntrance(id,seq,fixedRecomId) {
    var mapping = {
        'id': id,
        'seq': seq,
        'fixedRecomId' :fixedRecomId
    };
    $.post(ctx + "/fixedRecommendContent/getMaxMapping",mapping,function (data) {
        if(seq == data.seq){
            alert("已经到最后一个,无法下移");
        }else {
            var mapping1 = {
                'id': id,
                'seq': data.seq
            };
            var mapping2 = {
                'id': data.id,
                'seq': seq
            };
            $.post(ctx + "/fixedRecommendContent/update", mapping1, function (data) {

                if (data.returnValue == 0) {
                    $.post(ctx + "/fixedRecommendContent/update", mapping2, function (data) {
                        if (data.returnValue == 0) {
                            alert("下移成功");
                            $("#sample_table1").bootstrapTable('refresh', param);
                        } else {
                            alert("下移失败");
                        }
                    }, "json");
                } else {
                    alert("下移失败");
                }
            }, "json");

        }},"json");
}
function clear_diaglog2(){
    $("#myModalTwo").modal('hide');
}
function add1(){
    // $("#footer").css
    // ({
    //     "top":"11px"
    // });
    if($("#recommendContentImg")[0].height<100){
        $("#footer").css
        ({
            "top":"-25px"
        });
    }else{
        $("#footer").css
        ({
            "top":"-50px"
        });
    }
    $("#hiddenId").val("");
    var seleOperateContent =  $("#seleOperateContent").val();
    $.post(ctx + "/PortalDataGroupController/getGroupVo", {id : seleOperateContent}, function(data) {
        // console.info( data);
        $('#content_operate').val(data.packageName);
        if( data.packageName == 1){
            $("#rightdiv").html(
                "<div>"+
                "<label for='contentType' style='display: inline-block'>付费：</label><select id='contentType' style='width:80px;vertical-align: -1px; '>" +
                "<option value='-1'>全部</option>" +
                "<option value ='0'>免费</option>" +
                "<option value ='1'>大麦付费</option>" +
                "<option value ='2'>爱奇艺付费</option>" +
                "</select>"+
                " &nbsp; &nbsp; "+
                "<label for='contentType1' style='display: inline-block;'>VIP：</label><select id='contentType1' style='width:80px;vertical-align: -1px;'>"+
                "<option value='-1'>全部</option>"+
                "<option value ='0'>非VIP</option>"+
                "<option value ='1'>大麦VIP</option>"+
                "<option value ='2'>爱奇艺VIP</option>"+
                "</select></div>"+
                "<label for='contentName' style='display: inline-block;'>名称：</label> <input type='text' id='contentName' style='vertical-align: -1px;'>"+
                "&nbsp;&nbsp;&nbsp;"+
                "<input type='button' class='btn yellow' style='margin-bottom:14px;vertical-align: -1px;' value='查询' onclick='selecFixedRecomName("+data.packageName+")'>"
            );
            $("#tableDiv").css("display", "block");
            $("#tableDiv1").css("display", "none");
            table2();
            $("#editcontent_myModal").modal({width: '800Px',backdrop: 'static',keyboard: false });

        }else {
            $("#rightdiv").html("<div>" +
                "名称： <input type='text' id='contentName'>"+
                "&nbsp;&nbsp;&nbsp;"+
                "<input type='button' class='btn yellow' value='查询'  style='margin-bottom:14px;' onclick='selecFixedRecomName("+data.packageName+")'>"
            );
            table3();
            $("#tableDiv").css("display", "none");
            $("#tableDiv1").css("display", "block");

            $("#editcontent_myModal").modal({width: '800Px',backdrop: 'static',keyboard: false });
        }
    }, "json");
    $('#txt_title').val("");
    $('#txt_secondTitle').val("");
    $('#recommendContentImg').attr('src',"");;
}
function edit11(id){
    // console.log("@########################################");
    //     if($("#recommendContentImg")[0].offsetHeight > 0){
    //         $("#footer").css({'top':'50px'})
    //     }else {
    //         $("#footer").css({'top':'11px'})
    //     }
    // if(document.getElementById("recommendContentImg")[0].offsetHeight == 0){
    //     console.log("%$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$")
    //     $("#footer")[0].style.top = 11 +'px'
    //     console.log("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^")
    // // }
    // console.info("$(\"#leftdiv\")[0].offsetTop:"+$("#leftdiv")[0].offsetTop)
    // console.info("$(\"#leftdiv\")[0].height:"+$("#leftdiv")[0].height)
    // console.info("$(\"#leftdiv\")[0].offsetLeft:"+$("#leftdiv")[0].offsetLeft)
    $("#hiddenId").val(id);
    $.post(ctx + "/fixedRecommendContent/getFixedRecommendContentOne", {id : id}, function(data) {
        // console.info(data);
        $('#txt_title').val(data.contentName);
        $("#contentId1").val(data.contentId);
        $('#editcontent_id').val(data.contentId);
        $('#txt_secondTitle').val(data.contentSubtitle);
        $('#recommendContentImg').attr('src',data.contentImg);
        $('#content_operate').val(data.operateContent);
        if( data.operateContent == 1){
            $("#rightdiv").html(
                "<div>"+
                "<label for='contentType' style='display: inline-block;'>付费：</label><select id='contentType' style='width:100px;vertical-align: -1px; '>" +
                "<option value='-1'>全部</option>" +
                "<option value ='0'>免费</option>" +
                "<option value ='1'>大麦付费</option>" +
                "<option value ='2'>爱奇艺付费</option>" +
                "</select>"+
                " &nbsp; &nbsp; "+
                "<label for='contentType1' style='display: inline-block;'>VIP：</label><select  id='contentType1' style='width:100px;vertical-align: -1px;'>"+
                "<option value='-1'>全部</option>"+
                "<option value ='0'>非VIP</option>"+
                "<option value ='1'>大麦VIP</option>"+
                "<option value ='2'>爱奇艺VIP</option>"+
                "</select></div>"+
                "<label for='contentName' style='display: inline-block;'>名称：</label> <input type='text' id='contentName' style='vertical-align: -1px;'>"+
                "<input type='button' class='btn yellow' value='查询'  style='margin-bottom:14px;vertical-align: -1px;' onclick='selecFixedRecomName("+data.operateContent+")'>"
            )
            $("#tableDiv").css("display", "block");
            $("#tableDiv1").css("display", "none");
            table2();
            if($("#recommendContentImg")[0].height<100){
                $("#footer").css
                ({
                    "top":"-25px"
                });
            }else{
                $("#footer").css
                ({
                    "top":"-50px"
                });
            }
            $("#editcontent_myModal").modal({width: '800Px',backdrop: 'static',keyboard: false });
        }else {
            $("#rightdiv").html("<div>" +
                "名称： <input type='text' id='contentName'>"+
                "&nbsp;&nbsp;&nbsp;"+
                "<input type='button'  class='btn yellow'  value='查询'  style='margin-bottom:14px;' onclick='selecFixedRecomName("+data.operateContent+")'>"
            );
            $("#tableDiv").css("display", "none");
            $("#tableDiv1").css("display", "block");
            table3();
            $("#footer").css
            ({
                "top":"-24px"
            })
            $("#editcontent_myModal").modal({width: '800Px',backdrop: 'static',keyboard: false });
        }
    }, "json");
    // console.log("$(\"$(\".pic\")[0].offsetHeight:"+$(".pic")[0].offsetHeight);
    // console.log("$(\"$(\".pic\")[0].height:"+$(".pic")[0].height);
}
//大麦影视
function table2(chargingType,isTvod,isVip,aqyIsVip,chn,chnTypeSelect) {
    var chnId =  $("#content_chn_id").val();
    var contentName = $("#contentName").val();
    $('#sample_table2').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#sample_table2").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/fixedRecommendContent/getNewContentChanPageList", //获取数据的Servlet地址
        striped: true,  //表格显示条纹
        clickToSelect: false,
        pagination: true, //启动分页
        pageSize: 10,  //每页显示的记录数
        pageNumber: 1, //当前第几页
        pageList: [5,10,20],  //记录数可选列表
        search: false,  //是否启用查询
        showColumns: false,  //显示下拉框勾选要显示的列
        showRefresh: false,  //显示刷新按钮
        sidePagination: "server", //表示服务端请求
        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        //设置为limit可以获取limit, offset, search, sort, order
        queryParamsType: "undefined",
        queryParams: function queryParams(params) {//设置查询参数
            param = {
                albumName : contentName,
                chnId:chnId,
                chnType:10,
                chargingType : chargingType,
                isTvod : isTvod,
                isVip : isVip,
                aqyIsVip : aqyIsVip,
                status :1,
                isEffective : 1,
                apkBagName:"com.hiveview.cloudscreen.vipvideo",
                page: params.pageNumber,
                rows: params.pageSize,
            };
            return param;
        },onClickRow: function(row){
            // console.info("row:"+row)
            $('#txt_title').val("");
            $('#txt_secondTitle').val("");
            $('#recommendContentImg').attr('src',"");
            $('#editcontent_id').val(row.programsetId);
            $('#txt_title').val(row.albumName);
            $('#txt_secondTitle').val(row.focus);
            $("#recommendContentImg").attr('src',row.albumHbPic);
            // console.log("$(\"#recommendContentImg\")[0].height:"+$("#recommendContentImg")[0].height);
            // console.log("$(\"#recommendContentImg\")[0].offsetHeight:"+$("#recommendContentImg")[0].offsetHeight);
            // if($("#recommendContentImg")[0].height<100){
            //     $("#footer").css
            //     ({
            //         "top":"-25px"
            //     });
            // }else{
            //     $("#footer").css
            //     ({
            //         "top":"-50px"
            //     });
            // }
            // console.info("$(\"#recommendContentImg\")[0].height:"+$("#recommendContentImg")[0].height)
            // console.log("$(\"#recommendContentImg\")[0].offsetHeight:"+$("#recommendContentImg")[0].offsetHeight);
        },
        columns : [ [ {
            field : 'programsetId',
            title : '内容id',
            width : '8%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        },{
            field : 'albumName',
            title : '专辑名称',
            width : '13%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        },{
            field : 'contentType',
            title : '专辑来源',
            width : '13%',
            align : 'center',
            formatter : function(value, row, index) {
                var str="";
                if(row.aqyId !=null && row.aqyIsEffective == 1){
                    str=str+"爱奇艺|"
                }
                if(row.jqId !=null && row.jqIsEffective == 1){
                    str=str+"极清|"
                }
                return str;
            }
        },
            {
                field : 'albumHbPic',
                title : '图片',
                width : '13%',
                align : 'center',
                formatter : function(value) {
                    return "<img style='height:60px;width:80px;' src='" + value + "'/>";
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
//应用游戏
function table3() {
    var contentName = $("#contentName").val();
    var categoryId = $("#content_chn_id").val();
    $('#sample_table3').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据

    $("#sample_table3").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/fixedRecommendContent/getAppPageList", //获取数据的Servlet地址
        striped: true,  //表格显示条纹
        clickToSelect: false,
        pagination: true, //启动分页
        pageSize: 10,  //每页显示的记录数
        pageNumber: 1, //当前第几页
        pageList: [5,10,20],  //记录数可选列表
        search: false,  //是否启用查询
        showColumns: false,  //显示下拉框勾选要显示的列
        showRefresh: false,  //显示刷新按钮
        sidePagination: "server", //表示服务端请求
        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        //设置为limit可以获取limit, offset, search, sort, order
        queryParamsType: "undefined",
        queryParams: function queryParams(params) {//设置查询参数

            param = {
                appName: contentName,
                categoryId:categoryId,
                state:1,
                page: params.pageNumber,
                rows: params.pageSize,

            };
            return param;
        }, onClickRow: function (row) {
            $('#txt_title').val("");

            $('#txt_secondTitle').val("");
            $('#editcontent_id').val(row.appId);
            $('#recommendContentImg').attr('src',"");
            $('#txt_title').val(row.appName);
            $("#recommendContentImg").attr('src',row.appIcon);
        },
        columns: [ [{
            field: 'appId',
            title: '内容ID',
            width: '8%',
            align: 'center',
            formatter: function (value, row, index) {

                return value;
            }
        }, {
            field: 'appName',
            title: '应用名称',
            width: '13%',
            align: 'center',
            formatter: function (value, row, index) {
                return value;
            }
        }, {
            field: 'appType',
            title: '应用类型',
            width: '13%',
            align: 'center',
            formatter: function (value, row, index) {
                if (value == 0) {
                    return "系统应用";
                } else {
                    return "安装应用"
                }


            }
        },
            {
                field: 'appIcon',
                title: '图片',
                width: '13%',
                align: 'center',
                formatter: function (value) {
                    return "<img style='height:60px;width:80px;' src='" + value + "'/>";
                }
            }]],
        onLoadSuccess: function () {  //加载成功时执行
            //layer.msg("加载成功");
        },
        onLoadError: function () {  //加载失败时执行
            // layer.msg("加载数据失败", {time: 1500, icon: 2});
        }
    });

}
function close_contentdiaglog() {
    $("#myModalTwo").modal({width: '800Px',backdrop: 'static',keyboard: false });
    $("#editcontent_myModal").modal('hide');

}
function content_submit() {
    var hiddenId =  $("#hiddenId").val();
    var contentId1 = $("#contentId1").val();
    var contentId =  $('#editcontent_id').val();
    var txt_title = $('#txt_title').val();
    var operateContent = $('#content_operate').val();
    var  fixedRecomId = $("#seleOperateContent").val();
    var txt_secondTitle =  $('#txt_secondTitle').val();
    var videoId = $("#video_id").val();
    var recommendContentImg = $('#recommendContentImg')[0].src;
    if(contentId1 == '' && contentId == ''){
        alert("请关联数据");
        return;
    }
    if ($.trim(txt_title) == "") {
        alert("标题不能为空");
        return;
    }
    var imgExist = recommendContentImg.substr(recommendContentImg.length-4);
     if(imgExist == 'list'){
     alert("图片不能为空");
     return;
     }
     if(recommendContentImg == null && recommendContentImg == ''){
     alert("图片不能为空");
     return;
     }
    var content = {
        'id':hiddenId,
        'contentId' : contentId,
        'contentName' : txt_title,
        'contentSubtitle' :txt_secondTitle,
        'contentImg' : recommendContentImg,
        'operateContent':operateContent,
        'fixedRecomId':fixedRecomId,
        'video_id':videoId

    };
    if(hiddenId != "" && hiddenId != null){
        if(contentId1 == contentId){
            var content3 = {
                'id':hiddenId,
                'contentName' : txt_title,
                'contentSubtitle' :txt_secondTitle,
                'contentImg' : recommendContentImg,
                'operateContent':operateContent,
                'video_id':videoId
            };
            $.post(ctx + "/fixedRecommendContent/update", content3, function(data) {

                if (data.returnValue == 0) {
                    alert( '修改成功！');
                    clear_diaglog1();
                    $("#hiddenId").val("");
                    $("#sample_table2").bootstrapTable('refresh', param);
                    $("#sample_table1").bootstrapTable('refresh', param);
                } else {
                    alert( '修改失败！');
                }
            }, "json");

        }else{
            $.post(ctx + "/fixedRecommendContent/update", content, function(data) {

                if (data.returnValue == 0) {
                    alert( '修改成功！');
                    clear_diaglog1();
                    $("#hiddenId").val("");
                    $("#sample_table2").bootstrapTable('refresh', param);
                    $("#sample_table1").bootstrapTable('refresh', param);
                } else {
                    if(data.returnValue == -2){
                        alert( '已存在列表中,不可重复编辑！');
                    }else{
                        alert( '修改失败！');
                    }
                }
            }, "json");
        }
    }else{
        $.post(ctx + "/fixedRecommendContent/save", content, function(data) {
            if (data.returnValue == 0) {
                alert( '新增成功！');
                clear_diaglog1();
                $("#hiddenId").val("");
                $("#sample_table2").bootstrapTable('refresh', param);
                $("#sample_table1").bootstrapTable('refresh', param);

            } else {
                if(data.returnValue == -2){
                    alert( '已存在列表中,不可重复添加！');
                }else{
                    alert( '新增失败！');
                }
            }
        }, "json");
    }
}
function clear_diaglog1(){
    $('#recommendContentImgUrl').val("");
    $('#txt_title').val("");

    $('#txt_secondTitle').val("");
    $('#recommendContentImg').attr('src',"");
    $("#editcontent_myModal").modal('hide');

}
function selecFixedRecomName(packageName) {

    if(packageName == 1) {
        var chn = $("#chn").val();
        var chnTypeSelect = $("#chnTypeSelect").val();
        var isCharge = $("#contentType").val();
        var isContentVip = $("#contentType1").val();
        var contentName = $("#contentName").val();
        var chargingType = null;//大麦收费
        var isTvod = null;//爱奇艺收费
        var isVip = null;//大麦VIP
        var aqyIsVip = null;//爱奇艺Vip
        var chnId =  $("#content_chn_id").val();
        if(isCharge =="-1" ){
            chargingType = null;
            isTvod= null;
        }else if(isCharge == "0"){
            chargingType = 3;
            //isTvod = 0;
        }else if(isCharge == "1"){
            chargingType = 1;
            isTvod = null;
        }else if(isCharge == "2"){
            chargingType = null;
            isTvod = 1;
        }
        if(isContentVip == "-1" ){
            isVip = null;
            aqyIsVip= null;
        }else if(isContentVip == "0"){
            isVip = 3;
            //aqyIsVip = 0;
        }else if(isContentVip == "1"){
            isVip = 1;
            aqyIsVip = null;
        }else if(isContentVip == "2"){
            isVip = null;
            aqyIsVip = 1;
        }
        table2(chargingType,isTvod,isVip,aqyIsVip,chn,chnTypeSelect);
    }
    else
    {
        table3();
    }
}
function rightDataGroup(id,hotName,hotId) {
    HotOption();
    if(hotId == null){
        $("#txtHotName").val('');
    }else {
        $("#txtHotName").val(hotName);
    }
    $("#apkId").val(id);
    $("#channelModal").modal({width: '800Px',backdrop: 'static',keyboard: false });
    var apkChannelId = $("#txt_hot_channel").val();
    var  apkBagName = $("#txt_hot_pagBakName").val();
    newTempletChannelTable(apkBagName,apkChannelId);
}

function newTempletClose() {
    $("#channelModal").modal('hide');
}
function newTempletChannelTable(apkBagName,apkChannelId,hotName) {
    $('#channl_table').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#channl_table").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/NewTempletChannelWordsController/getPage", //获取数据的Servlet地址
        striped: true,  //表格显示条纹
        clickToSelect: false,
        pagination: true, //启动分页
        pageSize: 10,  //每页显示的记录数
        pageNumber: 1, //当前第几页
        pageList: [5,10,20],  //记录数可选列表
        search: false,  //是否启用查询
        showColumns: false,  //显示下拉框勾选要显示的列
        showRefresh: false,  //显示刷新按钮
        sidePagination: "server", //表示服务端请求
        queryParamsType: "undefined",
        queryParams: function queryParams(params) {//设置查询参数
            param = {
                page: params.pageNumber,
                rows: params.pageSize,
                apkBagName : apkBagName,
                apkChannelId : apkChannelId,
                name :hotName
            };
            return param;
        }, onClickRow: function (row) {
            $("#hotID").val('');
            $("#hotID").val(row.id);
            $('#txtHotName').val('');
            $("#txtHotName").val(row.name);
            $("#apk_channel_id").val('');
            $("#apk_channel_id").val(row.apkChannelId);
        },
        columns: [ [{
            field: 'id',
            title: '内容ID',
            width: '8%',
            align: 'center',
            formatter: function (value, row, index) {

                return value;
            }
        }, {
            field: 'name',
            title: '热词名称',
            width: '13%',
            align: 'center',
            formatter: function (value, row, index) {
                return value;
            }
        }, {
            field: 'hotId',
            title: '数量',
            width: '13%',
            align: 'center',
            formatter: function (value, row, index) {
                return value;
            }
        }]],
        onLoadSuccess: function () {  //加载成功时执行
        },
        onLoadError: function () {  //加载失败时执行
            // layer.msg("加载数据失败", {time: 1500, icon: 2});
        }
    });
}
function pageckageSelect() {
    var  apkBagName = $("#txt_hot_pagBakName").val();
    var categoryId = $("#txt_app_channel").val();
    if(apkBagName == 'com.hiveview.premiere'){
        $("#hotDiv").show();
        $("#apkDiv").hide();
        $("#appDiv").hide();
        $("#appTagDiv").hide();
        premiereTable(apkBagName);
    }else if(apkBagName  == 'com.hiveview.cloudscreen.vipvideo'){
        var apkChannelId = $("#txt_hot_channel").val();
        $("#hotDiv").show();
        $("#appTagDiv").hide();
        $("#appDiv").hide();
        $("#apkDiv").show();
        hotChannel();
        newTempletChannelTable(apkBagName);
    }else  if(apkBagName == 'com.hiveview.cloudscreen.py'){
        var apkChannelId = $("#txt_hot_channel").val();
        $("#hotDiv").show();
        $("#appTagDiv").hide();
        $("#appDiv").hide();
        $("#apkDiv").show();
        hotChannel();
        newTempletChannelTable(apkBagName);
    }else {
        $("#hotDiv").hide();
        $("#appTagDiv").show();
        $("#appDiv").show();
        $("#apkDiv").hide();
        appTable();
    }
}
function beanCurdNameLike() {
    var hotName = $("#txt_hotName").val();
    var apkChannelId = $("#txt_hot_channel").val();
     var  apkBagName = $("#txt_hot_pagBakName").val();
    var categoryId = $("#txt_app_channel").val();
     if(apkBagName == 'com.hiveview.cloudscreen.appstore'){
         appTable(categoryId,hotName)
     }else if(apkBagName == "com.hiveview.premiere") {
         premiereTable(apkBagName,hotName)
     }else {
         if(hotName != null && hotName != ''){
             newTempletChannelTable(apkBagName,apkChannelId,hotName);
         }else {
             newTempletChannelTable(apkBagName,apkChannelId);
         }

     }
}
function hotSumbit() {
    var hot =  $("#hotID").val();
    var apkId = $("#apkId").val();
    var hotName = $("#txtHotName").val();
    var hotPackageName = $("#txt_hot_pagBakName").val();
    var hotApp = $("#apk_channel_id").val();
    var hotAppName = $("#tag_id").val();
    if(hotPackageName == 'com.hiveview.cloudscreen.appstore'){
        var ppt  = {
            'id' :apkId,
            "hotId" : hot,
            "hotName" :hotName,
            "apkPackageName": hotPackageName,
            "channel" :hotAppName
        }
    }/*else if(hotPackageName == 'com.hiveview.premiere') {
        var ppt  = {
            'id' :apkId,
            "hotId" : hot,
            "hotName" :hotName,
            "apkPackageName": hotPackageName,
            "channel" :''
        }
    }*/else {
        var ppt  = {
            'id' :apkId,
            "hotId" : hot,
            "hotName" :hotName,
            "apkPackageName": hotPackageName,
            "channel" :hotApp
        }
    }

    if(hot !== null && hot !== ''){
    $.post(ctx+ "/PortalDataGroupController/update",ppt,function (data) {
        if(data.returnValue == 0){
            alert("添加成功");
            $("#sample_1").bootstrapTable('refresh',param);
            newTempletCloses();
        }else {
            alert("添加失败")
        }
    },'json');
    }else {
        alert("请选择关联热词");
        return;
    }

}
function newTempletCloses() {
    $("#apkId").val('');
    $("#hotID").val('');
    $("#txtHotName").val('');
/*    $("#txt_hot_pagBakName").val('');
    $("#apk_channel_id").val('');
    $("#tag_id").val('');*/
    $("#channelModal").modal('hide');

}
//Data Group名称搜索
function dataGrouName() {
   var dataGroupName =  $("#txt_data_group_name").val();
    var effective = $("#txt_beanIsEffecitveLike").val();
    initTable(dataGroupName,effective);
}
function chan() {
    var hotChannel = $("#txt_hot_channel").val();
    var  packageName = $("#txt_hot_pagBakName").val();
    newTempletChannelTable(packageName,hotChannel);
}
function HotOption() {
    $("#txt_hot_pagBakName").empty();
    $("#txt_hot_pagBakName").append("<option value ='com.hiveview.cloudscreen.vipvideo' selected = 'selected'>大麦影视</option>");
    $("#txt_hot_pagBakName").append("<option value ='com.hiveview.cloudscreen.appstore'>应用游戏</option>");
    $("#txt_hot_pagBakName").append("<option value ='com.hiveview.premiere'>极清首映</option>");
    $("#txt_hot_pagBakName").append("<option value ='com.hiveview.cloudscreen.py'>鹏博士课堂</option>");
    var  pagName =  $("#txt_hot_pagBakName").val();
    if(pagName  != 'com.hiveview.cloudscreen.appstore'){
        $("#hotDiv").show();
        $("#appTagDiv").hide();
        $("#appDiv").hide();
        $("#apkDiv").show();
/*        newTempletChannelTable(apkBagName,apkChannelId);*/
    }else {
        $("#hotDiv").hide();
        $("#appTagDiv").show();
        $("#appDiv").show();
        $("#apkDiv").hide();
        appTable();
    }
    hotChannel();
}


function hotChannel() {
    var  apkBagName = $("#txt_hot_pagBakName").val();
    var ppt = {apkBagName: apkBagName};
    $.ajax({
        type: "get",
        async : true,
        url : ctx + "/NewTempletApkChannelController/getChannelPage",
        dataType:"json",
        data :ppt,
        success: function (res) {
            $("#txt_hot_channel").empty();
            $("#txt_hot_channel").append("<option value =''>全部</option>");
            $.each(res ,function (index,element) {
                var options = $("<option value ='" + element['id'] +"'>" + element['cName'] + "</option>");
                $("#txt_hot_channel").append(options);
            })
        }
    });
    var apkChannelId = $("#txt_hot_channel").val();
    var  apkBagName = $("#txt_hot_pagBakName").val();
    newTempletChannelTable(apkBagName,apkChannelId);
}
function appSelect() {
    $.ajax({
       type:'get',
        async : true,
        url : ctx + "/NewTempletApkChannelController/getApp",
        dataType :"json",
        success : function (res) {
           $("#txt_app_channel").empty();
            $("#txt_app_channel").append("<option value=''>全部</option>");
            $.each(res,function (index,element) {
                var options = $("<option value='"+ element['categoryId'] + "'>" + element['categoryName']  +"</option>");
                $("#txt_app_channel").append(options);
            })
        }
    });
}


function appTable(categoryId,tagName) {
    $('#app_tag_table').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#app_tag_table").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/PortalDataGroupController/getAppPage", //获取数据的Servlet地址
        striped: true,  //表格显示条纹
        clickToSelect: false,
        pagination: true, //启动分页
        pageSize: 10,  //每页显示的记录数
        pageNumber: 1, //当前第几页
        pageList: [5,10,20],  //记录数可选列表
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
                categoryId:categoryId,
                tagName:tagName
            };
            return param;
        }, onClickRow: function (row) {
            $("#hotID").val('');
            $("#hotID").val(row.tagId);
            $('#txtHotName').val('');
            $("#txtHotName").val(row.tagName)
            $("#tag_id").val('');
            $("#tag_id").val(row.categoryId);
        },
        columns: [ [{
            field: 'tagId',
            title: '内容ID',
            width: '8%',
            align: 'center',
            formatter: function (value, row, index) {
                return value;
            }
        }, {
            field: 'tagName',
            title: '热词名称',
            width: '13%',
            align: 'center',
            formatter: function (value, row, index) {
                return value;
            }
        }, {
            field: 'tagCount',
            title: '数量',
            width: '13%',
            align: 'center',
            formatter: function (value, row, index) {
                return value;
            }
        }]],
        onLoadSuccess: function () {  //加载成功时执行
        },
        onLoadError: function () {  //加载失败时执行
        }
    });
}

function appChaner() {
    var categoryId = $("#txt_app_channel").val();
    appTable(categoryId);
}
 function upAndDownSelect() {
     var upAndDown = $("#txt_up_and_down").val();
     var width = $("#txt_width").val();
     var heigth =$("#txt_height").val();
     if(upAndDown == 1){
         $("#txt_col").val('');
         heightInput();
     }else {
         $("#txt_row").val('');
         widthInput();
     }
 }
function widthInput() {
    var upAndDown = $("#txt_up_and_down").val();
    if(upAndDown == 2){
    var resule = $("#txt_width").val();
/*    var height = $("#txt_height").val();*/
    if(resule == ''){
        $("#txt_col").val('');
    }else {
        var resule1 = parseInt(resule) + parseInt(30);
        var resul = parseInt(1920/resule1);
        $("#txt_col").val(resul);
        $("#txt_row").attr("readOnly",false);
        $("#txt_col").attr("readOnly",true);
    }
    }else {
        return false;
    }
}
function heightInput() {
    var upAndDown = $("#txt_up_and_down").val();
    if(upAndDown == 1){
    var resule = $("#txt_height").val();
/*    var width = $("#txt_width").val();*/
    if(resule == ''){
        $("#txt_row").val('');
    }else {
        var resule1 = parseInt(resule) + parseInt(30);
        var resul = parseInt(837 / resule1);
        $("#txt_row").val(resul);
        $("#txt_col").attr("readOnly",false);
        $("#txt_row").attr("readOnly",true);
    }
    }else {
        return false;
    }
}

function premiereTable(apkBagName,hotName) {
    $('#channl_table').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#channl_table").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/NewTempletChannelWordsController/getPageList", //获取数据的Servlet地址
        striped: true,  //表格显示条纹
        clickToSelect: false,
        pagination: true, //启动分页
        pageSize: 10,  //每页显示的记录数
        pageNumber: 1, //当前第几页
        pageList: [5,10,20],  //记录数可选列表
        search: false,  //是否启用查询
        showColumns: false,  //显示下拉框勾选要显示的列
        showRefresh: false,  //显示刷新按钮
        sidePagination: "server", //表示服务端请求
        queryParamsType: "undefined",
        queryParams: function queryParams(params) {//设置查询参数
            param = {
                page: params.pageNumber,
                rows: params.pageSize,
                apkBagName : apkBagName,
                name :hotName
            };
            return param;
        }, onClickRow: function (row) {
            $("#hotID").val('');
            $("#hotID").val(row.id);
            $('#txtHotName').val('');
            $("#txtHotName").val(row.name);
            $("#apk_channel_id").val('');
            $("#apk_channel_id").val(row.apkChannelId);

        },
        columns: [ [{
            field: 'id',
            title: '内容ID',
            width: '8%',
            align: 'center',
            formatter: function (value, row, index) {

                return value;
            }
        }, {
            field: 'name',
            title: '热词名称',
            width: '13%',
            align: 'center',
            formatter: function (value, row, index) {
                return value;
            }
        }, {
            field: 'hotId',
            title: '数量',
            width: '13%',
            align: 'center',
            formatter: function (value, row, index) {
                return value;
            }
        }]],
        onLoadSuccess: function () {  //加载成功时执行
        },
        onLoadError: function () {  //加载失败时执行
            // layer.msg("加载数据失败", {time: 1500, icon: 2});
        }
    });
}





function widthInputEdit() {
    var upAndDown = $("#txt_up_and_down").val();
    if(upAndDown == 2){
        var resule = $("#txt_width").val();
        /*    var height = $("#txt_height").val();*/
    /*    if(resule == ''){
            $("#txt_col").val('');
        }else {*/
            var resule1 = parseInt(resule) + parseInt(30);
            var resul = parseInt(1920/resule1);
            $("#txt_col").val(resul);
            $("#txt_row").attr("readOnly",false);
            $("#txt_col").attr("readOnly",true);
  /*      }*/
    }else {
        return false;
    }
}
function heightInputEdit() {
    var upAndDown = $("#txt_up_and_down").val();
    if(upAndDown == 1){
        var resule = $("#txt_height").val();
        /*    var width = $("#txt_width").val();*/
/*        if(resule == ''){
            $("#txt_row").val('');
        }else {*/
            var resule1 = parseInt(resule) + parseInt(30);
            var resul = parseInt(837 / resule1);
            $("#txt_row").val(resul);
            $("#txt_col").attr("readOnly",false);
            $("#txt_row").attr("readOnly",true);
   /*     }*/
    }else {
        return false;
    }
}