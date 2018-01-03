$(document).ready(function () {
    initTable();
    initUploadJs("areaImg");
    initUploadJs("txt_recommendImg");
    initCondition();
    initContentByTypeTable();
});
var moreCustomParam ={};
var vipCombobox = [{
    'id' : '0',
    'text' : '全部',
    'selected':true
}, {
    'id' : '1',
    'text' : '大麦VIP'
}, {
    'id' : '2',
    'text' : '爱奇艺VIP'
}, {
    'id' : '3',
    'text' : '非VIP'
}];
var payCombobox =  [{
    'id' : '0',
    'text' : '全部',
    'selected':true
}, {
    'id' : '1',
    'text' : '大麦付费'
}, {
    'id' : '2',
    'text' : '爱奇艺付费'
}, {
    'id' : '3',
    'text' : '免费'
}];
var param ={};
function initCondition() {
    var vipSelects = vipCombobox;
    if(vipSelects != null && vipSelects != "" && vipSelects.length > 0){
        var tr = ""
        for(var i = 0 ; i < vipSelects.length ; i ++){
            tr = tr + "<option value='" + vipSelects[i].id + "'>" + vipSelects[i].text + "</option>";
        }
        $('#vip').append(tr);
    }
    var paySelects = payCombobox;
    if(paySelects != null && paySelects != "" && paySelects.length > 0){
        var tr = ""
        for(var i = 0 ; i < paySelects.length ; i ++){
            tr = tr + "<option value='" + paySelects[i].id + "'>" + paySelects[i].text + "</option>";
        }
        $('#pay').append(tr);
    }
    $.ajax({
        async : false,
        type : "post",
        url : ctx+"/portal/templet/getList",
        dataType : "json",
        data : { },
        success : function(jsonData) {
            var select = jsonData;
            if(select != null && select != "" && select.length > 0){//如果子列表有值则把值赋上去
                var tr = ""
                for(var i = 0 ; i < select.length ; i ++){
                    tr = tr + "<option value='" + select[i].id + "'>" + select[i].name + "</option>";
                }
                $('#customTempletId').append(tr);
            }
            var templetId = $("#customTempletId").val();
            $.ajax({
                async : false,
                type : "post",
                url : ctx+"/portal/templetApk/getList",
                dataType : "json",
                data : {"templetId":templetId},
                success : function(jsonData) {
                    $('#customTempleApk').empty();
                    var select = jsonData;
                    if(select != null && select != "" && select.length > 0){//如果子列表有值则把值赋上去
                        var tr = ""
                        for(var i = 0 ; i < select.length ; i ++){
                            //if(select[i].apkBagName =='com.hiveview.cloudscreen.vipvideo'||select[i].apkBagName =='com.hiveview.premiere'||select[i].apkBagName=='com.hiveview.cloudscreen.py'){
                            tr = tr + "<option value='" + select[i].apkBagName + "'>" + select[i].apkName + "</option>";
                            // }
                        }
                        $('#customTempleApk').append(tr);
                    }
                    var templetApk = $("#customTempleApk").val();
                    $.ajax({
                        async : false,
                        type : "post",
                        url : ctx+"/portal/templetApkChannel/getChannelTypeList",
                        dataType : "json",
                        data : {"apkBagName":templetApk},
                        success : function(jsonData) {
                            $('#templetApkChannelType').empty();
                            var select = jsonData;
                            if(select != null && select != "" && select.length > 0){
                                var tr = ""
                                for(var i = 0 ; i < select.length ; i ++){
                                    tr = tr + "<option value='" + select[i].cType + "'>" + select[i].cTypeName + "</option>";
                                }
                                $('#templetApkChannelType').append(tr);
                                $('#templetApkChannelType').val("10");
                                $("#templetApkChannelType").attr("disabled",true);
                            }
                            var templetApkChannelType = $("#templetApkChannelType").val();
                            $.ajax({
                                async : false,
                                type : "post",
                                url : ctx+"/portal/templetApkChannel/getChannelList",
                                dataType : "json",
                                data : {"apkBagName":templetApk,"cType":templetApkChannelType},
                                success : function(jsonData) {
                                    $('#templetApkChannel').empty();
                                    var select = jsonData;
                                    if(select != null && select != "" && select.length > 0){
                                        var tr = ""
                                        for(var i = 0 ; i < select.length ; i ++){
                                            tr = tr + "<option value='" + select[i].id + "'>" + select[i].cName + "</option>";
                                        }
                                        $('#templetApkChannel').append(tr);
                                    }
                                    // var templetApkChannel = $("#templetApkChannel").val();
                                }
                            });
                        }
                    });
                }
            });
        }
    });
}
function initCustomTempletApkBindEvent(){
    var templetId = $("#customTempletId").find("option:selected").val();
    var templetApk = $("#customTempleApk").find("option:selected").val();
    if("com.hiveview.premiere"==templetApk){
        $('#jq').css('display','inline');
        $('#dm').css('display','none');
        $.ajax({
            async : false,
            type : "post",
            url : ctx+"/portal/templetHotWord/getList",
            dataType : "json",
            data : {"apkBagName":templetApk,"templetId":templetId},
            success : function(jsonData) {
                $('#templetApkHot').empty();
                var select = jsonData;
                if(select != null && select != "" && select.length > 0){
                    var tr = "";
                    for(var i = 0 ; i < select.length ; i ++){
                        tr = tr + "<option value='" + select[i].id + "'>" + select[i].name + "</option>";
                    }
                    $('#templetApkHot').append(tr);
                }
            }
        });
    }else{
        $('#jq').css('display','none');
        $('#dm').css('display','inline');
        $.ajax({
            async : true,
            type : "post",
            url : ctx+"/portal/templetApkChannel/getChannelTypeList",
            dataType : "json",
            data : {"apkBagName":templetApk},
            success : function(jsonData) {
                $('#templetApkChannelType').empty();
                var select = jsonData;
                if(select != null && select != "" && select.length > 0){
                    var tr = ""
                    for(var i = 0 ; i < select.length ; i ++){
                        tr = tr + "<option value='" + select[i].cType + "'>" + select[i].cTypeName + "</option>";
                    }
                    $('#templetApkChannelType').append(tr);
                }
                $('#templetApkChannelType').val("10");
                $("#templetApkChannelType").attr("disabled",true);
                var templetApkChannelType = $("#templetApkChannelType").find("option:selected").val();
                $.ajax({
                    async : false,
                    type : "post",
                    url : ctx+"/portal/templetApkChannel/getChannelList",
                    dataType : "json",
                    data : {"apkBagName":templetApk,"cType":templetApkChannelType},
                    success : function(jsonData) {
                        $('#templetApkChannel').empty();
                        var select = jsonData;
                        if(select != null && select != "" && select.length > 0){
                            var tr = ""
                            for(var i = 0 ; i < select.length ; i ++){
                                tr = tr + "<option value='" + select[i].id + "'>" + select[i].cName + "</option>";
                            }
                            $('#templetApkChannel').append(tr);
                        }
                    }
                });
            }
        });
    }
}

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
/*                str += '<a href="javascript:areaEdit(' + row.id +",'" + row.areaName + "','" + row.areaTitle + "','" + row.areaIntroduce +  "','" + row.areaImg + '\')">编辑</a>';
                str += '&nbsp;|&nbsp;<a href="javascript:areaContent(' + row.id+ ')">关联内容</a>';
                str += '&nbsp;|&nbsp; <a href="javascript:del('+row.id+')">删除</a>';*/
                str += '<a href="javascript:areaEdit(' + row.id +",'" + row.areaName + "','" + row.areaTitle + "','" + row.areaIntroduce +  "','" + row.areaImg + "'," + row.albumPicType + "," + row.areaTemplate + ",'" + row.backgroundImg + '\')">编辑</a>';
                str += '&nbsp;|&nbsp;<a href="javascript:areaTable(' + row.id+ ')">关联内容</a>';
                str += '&nbsp;|&nbsp; <a href="javascript:del('+row.id+')">删除</a>';
                return str;
            }
        } ] ],
        onClickCell: function (field,value ,row, td) {
            $(td.parent()[0]).css({"background":"rgb(255,184,72)"}).siblings().css({"background":"none"});
        },
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

function areaEdit(id,areaName,areaTitle,areaIntroduce,areaImg,albumPicType,areaTemplate,backgroundImg) {
    $("#txt_areaName").val(areaName);
    $("#txt_areaTitle").val(areaTitle);
    $("#txt_areaIntroduce").val(areaIntroduce);
    $("#txt_areaId").val(id);
    $("#areaImg").attr('src',areaImg);
    $("#areaArgImg").attr('src',backgroundImg);
    $("#txt_albumPicType").val(albumPicType);
    $("#txt_areaTemplate").val(areaTemplate);
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
    var backgroundImg = $("#areaArgImg")[0].src;
    var albumPicType = $("#txt_albumPicType").val();
    var areaTemplate = $("#txt_areaTemplate").val();
    var areaImg = $("#areaImg")[0].src;
    var templeteImg = $("#templeteImg")[0].src;
    var img = areaImg.substring(areaImg.length-4,areaImg.length + 1);
    var backImg = backgroundImg.substring(backgroundImg.length-4,backgroundImg.length + 1);

    if($.trim(areaName) == ""){
        alert("专区名称不能为空");
        return;
    }
    if($.trim(areaTitle) == ""){
        alert("专区标题名称不能为空");
        return;
    }
    if(img == "list"){
        alert("请上传图片");
        return;
    }
    if(backImg == "list"){
        backgroundImg = null;
    }

    var areaAdminiration = {
        "id" : id,
        "areaName" : areaName,
        "areaTitle" : areaTitle,
        "areaIntroduce" : areaIntroduce,
        "areaImg" : areaImg,
        "backgroundImg":backgroundImg,
        "albumPicType":albumPicType,
        "areaTemplate":areaTemplate,
        "templeteImg":templeteImg
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

            if (file_id == "areaImg" || file_id == "txt_recommendImg") {
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
function areaContent(areaId) {
    initAreaTable(areaId);
    $("#areaId").val(areaId);
    setTimeout(function () {
        $("#areaModal").modal({backdrop: 'static', keyboard: false});
    },1500);

}
function addContent() {
    moreCustomParam ={};
    initContentByTypeTable();
    $('#editContentModal').modal({backdrop: 'static', keyboard: false});
}
function selectByName() {
    var contentName=$("#selectByName").val();
    var areaId=$("#areaId").val();
    initAreaTable(areaId,contentName);
}
function initAreaTable(areaId,contentName) {
    //先销毁表格
    $('#areaContentTable').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#areaContentTable").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/PortalAreaContenController/getPage", //获取数据的Servlet地址
        striped: true,  //表格显示条纹
        clickToSelect: false,
        pagination: true, //启动分页
        pageSize: 10,  //每页显示的记录数
        pageNumber: 1, //当前第几页
        pageList: [5, 10],  //记录数可选列表
        search: false,  //是否启用查询
        //showColumns: true,  //显示下拉框勾选要显示的列
        showRefresh: false,  //显示刷新按钮
        sidePagination: "server", //表示服务端请求
        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        //设置为limit可以获取limit, offset, search, sort, order
        queryParamsType: "undefined",
        queryParams: function queryParams(params) {//设置查询参数
            param = {
                page: params.pageNumber,
                rows: params.pageSize,
                areaId : areaId,
                contentName:contentName
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
                str += '<a href="javascript:moveTop(' + row.id +"," + row.seq+"," + row.seqIsTop+"," + row.areaId + ')">置顶</a>'
                str += '&nbsp;|&nbsp;<a href="javascript:moveUp(' + row.id +"," + row.seq+"," + row.seqIsTop+"," + row.areaId + ')">上移</a>'
                str += '&nbsp;|&nbsp;<a href="javascript:moveDown('+ row.id + "," + row.seq+"," + row.seqIsTop+"," + row.areaId + ')">下移</a>'
                return str;
            }
        },{
                field : 'do',
                title : '操作',
                width : '10%',
                align : 'center',
                formatter : function(value, row) {
                    var str = '';
                    str += '<a href="javascript:editContent(' + row.id +",'" + row.contentName + "'," + row.areaId+",'"+row.areaContent +"','"+ row.recommendImg+"',"+row.recommendType+",'"+row.packageName+"','"+row.areaTemplate+"',"+row.channelType+","+row.channel+","+row.payState+","+row.vipState+","+row.contentId+","+row.videoId+",'"+row.relateContent+"'"+')">编辑</a>';
                    str += '&nbsp;|&nbsp; <a href="javascript:delContent('+row.id+"," + row.seq+"," + row.seqIsTop+"," + row.areaId+')">删除</a>';
                    return str;
                }
            } ] ],
        onClickCell: function (field,value ,row, td) {
            $(td.parent()[0]).css({"background":"rgb(255,184,72)"}).siblings().css({"background":"none"});
        },
        onLoadSuccess: function () {  //加载成功时执行
            //$("#areaModal").modal({backdrop: 'static', keyboard: false});
        },
        onLoadError: function () {  //加载失败时执行
            // layer.msg("加载数据失败", {time: 1500, icon: 2});
        }
    });
}
function clear_contentlog() {
    $("#areaId").val('');
    $("#selectByName").val('');
    $('#areaModal').modal('hide');
}
function editContent(id,contentName,areaId,areaContent,recommendImg,recommendType,packageName,areaTemplate,channelType,channel,payState,vipState,contentId,videoId,relateContent) {
    $("#id").val(id);
    $("#contentName").val(contentName);
    $("#content").val(relateContent);
    if(contentId==null){
        $("#content").val('');
    }
    $("#areaContent").val(areaContent);
    $("#txt_recommendImg").attr('src',recommendImg);
    $("#recommendType").val(recommendType);
    $("#packageName").val(packageName);
    $("#areaTemplate").val(areaTemplate);
    $("#channelType").val(channelType);
    $("#channel").val(channel);
    $("#payState").val(payState);
    $("#vipState").val(vipState);
    $("#contentId").val(contentId);
    $("#videoId").val(videoId);
    moreCustomParam ={};
    initContentByTypeTable();
    $('#editContentModal').modal({backdrop: 'static', keyboard: false});
}
function ContentClose() {
    $("#id").val('');
    $("#contentName").val('');
    $("#content").val('');
    $("#areaContent").val('');
    $("#txt_recommendImg").attr('src','');
    $("#recommendType").val('');
    $("#packageName").val('');
    $("#areaTemplate").val('');
    $("#channelType").val('');
    $("#channel").val('');
    $("#payState").val('');
    $("#vipState").val('');
    $("#contentId").val('');
    $("#videoId").val('');
    $("#customContentTitle").val('');
    $("#customContentSubTitle").val('');
    $("#name_1").val('');
    $('#editContentModal').modal('hide');
}
function ContentSubmit() {
    var id = $("#id").val();
    var contentName = $("#contentName").val();
    var areaId = $("#areaId").val();
    var areaContent = $("#areaContent").val();
    var recommendImg = $("#txt_recommendImg")[0].src;
    var recommendType = $("#recommendType").val();
    var packageName = $("#packageName").val();
    var areaTemplate = $("#areaTemplate").val();
    var channelType = $("#channelType").val();
    var channel = $("#channel").val();
    var payState = $("#payState").val();
    var vipState = $("#vipState").val();
    var contentId = $("#contentId").val();
    var videoId=$("#videoId").val();
    if($.trim(recommendImg) == ""){
        alert("请上传图片");
        return;
    }

    var content = {
        "id" : id,
        "contentName" : contentName,
        "areaId" : areaId,
        "areaContent" : areaContent,
        "recommendImg" : recommendImg,
        'recommendType':recommendType,
        'packageName':packageName,
        'areaTemplate':areaTemplate,
        'channelType':channelType,
        'channel':channel,
        'payState':payState,
        'vipState':vipState,
        'contentId':contentId,
        'videoId':videoId
    };
    if(id != null && id != ""){
        $.post(ctx + "/PortalAreaContenController/update",content, function (data) {
            if(data.returnValue == 0){
                alert("修改成功");
                $("#areaContentTable").modal("hide");
                ContentClose();
                $("#areaContentTable").bootstrapTable("refresh",param);
            }else {
                alert("修改失败");
            }
        },'json');
    }else {
        $.post(ctx + "/PortalAreaContenController/save",content,function (data) {
            if(data.returnValue == 0){
                alert("添加成功");
                $("#areaContentTable").modal("hide");
                $("#areaContentTable").bootstrapTable("refresh",param);
                ContentClose();
            }else {
                alert("添加失败")
            }
        },'json');
    }

}
function delContent(id,seq,seqIsTop,areaId) {
    if (confirm("是否确认删除？")) {
        var content = {
            'id' : id,
            'seq':seq,
            'seqIsTop':seqIsTop,
            'areaId':areaId
        };
        $.post(ctx + "/PortalAreaContenController/delete", content, function(data) {
            if (data.returnValue == 0) {
                alert( '成功！');
                //$("#addTempletContentModal").modal('hide');
                $("#areaContentTable").bootstrapTable('refresh', param);
                if($("#areaContentTable").bootstrapTable('getData').length == 1){
                    $("#areaContentTable").bootstrapTable('prevPage');
                }
            } else {
                alert( '失败！');
            }
        }, "json");
    }
}
//置顶
function moveTop(id,seq,seqIsTop,areaId) {
    var content = {
        'id': id,
        'seq': seq,
        'seqIsTop':seqIsTop,
        'areaId':areaId
    }
    $.post(ctx + "/PortalAreaContenController/moveTop",content,function (data) {
        if (data.returnValue == 0) {
            alert( '置顶成功！');
            $("#areaContentTable").bootstrapTable('refresh', param);
        } else {
            alert( '置顶失败！');
        }

    },"json");
}
//上移
function moveUp(id,seq,seqIsTop,areaId) {
    var content = {
        'id': id,
        'seq': seq,
        'seqIsTop':seqIsTop,
        'areaId':areaId
    }
    $.post(ctx + "/PortalAreaContenController/moveUp",content,function (data) {
        if (data.returnValue == 0) {
            alert( '上移成功！');
            $("#areaContentTable").bootstrapTable('refresh', param);
        } else {
            alert( '上移失败！');
        }

    },"json");
}


//下移
function moveDown(id,seq,seqIsTop,areaId) {
    var content = {
        'id': id,
        'seq': seq,
        'seqIsTop':seqIsTop,
        'areaId':areaId
    }
    $.post(ctx + "/PortalAreaContenController/moveDown",content,function (data) {
        if (data.returnValue == 0) {
            alert( '下移成功！');
            $("#areaContentTable").bootstrapTable('refresh', param);
        } else {
            alert( '下移失败！');
        }

    },"json");
}
function initContentByTypeTable() {
    //先销毁表格
    $('#ContentByTypeTable').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#ContentByTypeTable").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/portal/newContentChan/getNewContentChanPageList", //获取数据的Servlet地址
        striped: true,  //表格显示条纹
        clickToSelect: false,
        pagination: true, //启动分页
        pageSize: 5,  //每页显示的记录数
        pageNumber: 1, //当前第几页
        pageList: [5],  //记录数可选列表
        search: false,  //是否启用查询
        //showColumns: true,  //显示下拉框勾选要显示的列
        showRefresh: false,  //显示刷新按钮
        sidePagination: "server", //表示服务端请求
        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        //设置为limit可以获取limit, offset, search, sort, order
        queryParamsType: "undefined",
        queryParams: function queryParams(params) {   //设置查询参数
            moreCustomParam['page'] =params.pageNumber;
            moreCustomParam['rows'] =params.pageSize;
            return moreCustomParam;
        },
        columns : [ {
            field : 'programsetId',
            title : '内容ID',
            width : '8%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        },{
            field : 'albumName',
            title : '专辑名称',
            width : '10%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        },{
            field : 'joinVideo',
            title : '关联剧集',
            width : '10%',
            align : 'center',
            formatter : function(value, row, index) {
                var str = '<a href="javascript:joinVideo('+row.programsetId+')">剧集</a>';
                return str;
            }
        },{
            field : 'albumHbPic',
            title : '图片',
            width : '10%',
            align : 'center',
            formatter : function(value, row, index) {
                return "<img style='height:30px;width:30px;' src='"
                    + value + "' />";
            }
        }] ,
        onLoadSuccess: function () {  //加载成功时执行
            //$("#areaModal").modal({backdrop: 'static', keyboard: false});
        },
        onClickRow: function(row) {
            console.log(row);
            $("#contentName").val(row.albumName);
            /*$("#areaId").val('');
            $("#payState").val(moreCustomParam['']);
            $("#vipState").val(moreCustomParam['']);*/
            $("#areaTemplate").val(row.templetId);
            $("#txt_recommendImg").attr('src',row.albumHbPic);
            $("#areaContent").val(row.albumDesc);
            $("#recommendType").val(1);
            $("#packageName").val(row.apkBagName);
            $("#channelType").val(row.chnType);
            $("#channel").val(row.chnId);
            $("#contentId").val(row.programsetId);
            $("#content").val(row.albumName);
            $("#videoId").val('');

        },
        onLoadError: function () {  //加载失败时执行
            // layer.msg("加载数据失败", {time: 1500, icon: 2});
        }
    });
}
function joinVideo(programsetId){
    initVideoTable(programsetId);
    $('#video_myModal').modal({backdrop: 'static', keyboard: false});
}
//剧集列表
function initVideoTable(programsetId) {
    $('#videoContent').bootstrapTable('destroy');
    $("#videoContent").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/program/getPageList", //获取数据的Servlet地址
        striped: true,  //表格显示条纹
        pagination: true, //启动分页
        pageSize: 5,  //每页显示的记录数
        pageNumber: 1, //当前第几页
        pageList: [5, 10],  //记录数可选列表
        search: false,  //是否启用查询
        showColumns: false,  //显示下拉框勾选要显示的列
        showRefresh: false,  //显示刷新按钮
        sidePagination: "server", //表示服务端请求
        queryParamsType: "undefined",
        queryParams: function queryParams(params) {   //设置查询参数
            param = {
                page: params.pageNumber,
                rows: params.pageSize,
                belongAlbumId:programsetId
            };
            return param;
        },
        columns : [ [
            {
                field : 'videoId',
                title : 'ID',
                width : '8%',
                align : 'center',
                formatter : function(value, row, index) {
                    return value;
                }
            },{
                field : 'videoName',
                title : '剧集名称',
                width : '10%',
                align : 'center',
                formatter : function(value, row, index) {
                    return value;
                }
            }, {
                field : 'daCome',
                title : '专辑来源',
                width : '10%',
                align : 'center',
                formatter : function(value, row, index) {
                    var str ="";
                    if(row.jqIsEffective==1 && row.jqVideoId !=null){
                        str+="极清|";
                    }
                    if(row.aqyIsEffective==1 && row.aqyVideoId !=null){
                        str+="爱奇艺|";
                    }
                    if(row.hkdbIsEffective==1 && row.hkdbVideoId !=null){
                        str+="回看点播|";
                    }
                    if(row.thirdIsEffective==1 && row.thirdVideoId !=null){
                        str+="第三方|";
                    }
                    return str;
                }
            } ] ],
        onLoadSuccess: function () {  //加载成功时执行
            //layer.msg("加载成功");
        },
        onLoadError: function () {  //加载失败时执行
            // layer.msg("加载数据失败", {time: 1500, icon: 2});
        },onClickRow: function(row) {
            $('#videoId').val(row.videoId);
            $('#customContentTitle').val(row.videoName);
            $('#contentName').val(row.videoName);
            $('#customContentSubTitle').val(row.videoDesc);
            if(row.videoDesc!=null){
                $('#areaContent').val(row.videoDesc);
            }
            console.log(row);
            $('#video_myModal').modal("hide");
        }
    });
}

function selectCustomData() {
    moreCustomParam ={};
    //查询参数
    var templet = $("#customTempletId").val();
    var templetApk = $("#customTempleApk").val();
    var templetApkChannelType = $("#templetApkChannelType").val();
    var templetApkChannel = $("#templetApkChannel").val();
    var templetApkHot = $("#templetApkHot").val();
    var vip = $("#vip").val();
    var charge = $("#pay").val();
    if(vip =='1'){
        moreCustomParam['isVip']=1;
    }else if(vip =='0'){
    }else if(vip =='3'){
        moreCustomParam['isVip']=3;
    }else{
        moreCustomParam['aqyIsVip']=1;
    }
    if(charge =='1'){
        moreCustomParam['chargingType']=1;
    }else if(charge =='0'){
    }else if(charge =='3'){
        moreCustomParam['chargingType']=3;
    }else{
        moreCustomParam['isTvod']=1;
    }
    moreCustomParam['albumName'] =$("#name_1").val();
    moreCustomParam['apkBagName'] =templetApk;
    moreCustomParam['templetId'] =templet;
    if("com.hiveview.premiere" ==templetApk ){
        moreCustomParam['hotId'] =templetApkHot;
    }else{
        moreCustomParam['chnType'] =templetApkChannelType;
        moreCustomParam['chnId'] =templetApkChannel;
    }
    moreCustomParam['status'] =1;
    moreCustomParam['isEffective'] =1;
    initContentByTypeTable();
}

//查询方法
function bigPicsNameSelect() {
    debugger;
    var areaName = $("#imgNameLike").val();
    var effictive = $("#effictiveSelect").val();
    var opt = {
        url : ctx + "/areaController/getPage",
        silent : true,
        query :{
            areaName :areaName,
            effective :effictive
        }
    }
    $('#sample_1').bootstrapTable('refresh',opt);
}