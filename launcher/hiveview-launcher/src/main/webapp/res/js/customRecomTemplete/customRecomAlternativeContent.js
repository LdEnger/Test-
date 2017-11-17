var moreCustomParam ={};
var RecomSweepstakesTypeMap ={};
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
//专题
var col0 =[{
    field : 'subjectId',
    title : '内容ID',
    width : 100,
    align : 'center',
    formatter : function(value) {
        return value;
    }
}, {
    field : 'subjectName',
    title : '专题名称',
    width : 100,
    align : 'center',
    formatter : function(value) {
        return value;
    }
}, {
    field : 'subjectVerPic',
    title : '图片',
    width : 120,
    align : 'center',
    formatter : function(value) {
        return "<img style='width:30px;height:30px;' src='"
            + value + "' />";
    }
}];
//专辑详情
var col1 = [ {
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
    field : 'albumHbPic',
    title : '图片',
    width : '10%',
    align : 'center',
    formatter : function(value, row, index) {
        return "<img style='height:30px;width:30px;' src='"
            + value + "' />";
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
}];
//应用详情
var col2 =[{
    field : 'appId',
    title : '编号',
    width : 90,
    align : 'center',
    formatter : function(value) {
        return value;
    }
}, {
    field : 'appName',
    title : '名称',
    width : 100,
    align : 'center',
    formatter : function(value) {
        return value;
    }
}, {
    field : 'appIcon',
    title : '图片',
    width : 90,
    align : 'center',
    formatter : function(value, row, index) {
        return "<img style='height:30px;width:30px;' src='"
            + value + "' />";
    }
}, {
    field : 'categoryId',
    title : '应用分类',
    width : 100,
    align : 'center',
    formatter : function(value, row, index) {
        if (value == 1) {
            return "游戏";
        }
        if (value == 2) {
            return "应用";
        }
        if (value == 3) {
            return "教育";
        }
    }
}];
//应用专题
var col3 =[{
    field : 'pubId',
    title : '编号',
    width : 90,
    align : 'center',
    formatter : function(value) {
        return value;
    }
}, {
    field : 'pubName',
    title : '名称',
    width : 100,
    align : 'center',
    formatter : function(value) {
        return value;
    }
}, {
    field : 'pubImg',
    title : '图片',
    width : 90,
    align : 'center',
    formatter : function(value, row, index) {
        return "<img style='height:30px;width:30px;' src='"
            + value + "' />";
    }
}, {
    field : 'categoryId',
    title : '应用分类',
    width : 100,
    align : 'center',
    formatter : function(value, row, index) {
        if (value == 1) {
            return "游戏";
        }
        if (value == 2) {
            return "应用";
        }
        if (value == 3) {
            return "教育";
        }
    }
}];
var col4 =[{
    field : 'activityId',
    title : '内容ID',
    width : 100,
    align : 'center'
}, {
    field : 'title',
    title : '名称',
    width : 100,
    align : 'center'
}, {
    field : 'type',
    title : '活动类型',
    width : 100,
    align : 'center',
    formatter : function(value) {
        return RecomSweepstakesTypeMap[value];
    }
}];

//全屏大图
var col6 = [ {
    field : 'imgId',
    title : '内容ID',
    width : '8%',
    align : 'center',
    formatter : function(value, row, index) {
        return value;
    }
},{
    field : 'imgName',
    title : '大图名称',
    width : '10%',
    align : 'center',
    formatter : function(value, row, index) {
        return value;
    }
},{
    field : 'imgAddr',
    title : '图片',
    width : '10%',
    align : 'center',
    formatter : function(value, row, index) {
        return "<img style='height:30px;width:30px;' src='"
            + value + "' />";
    }
}];
//商品包
var col7 = [ {
    field : 'id',
    title : '内容ID',
    width : '8%',
    align : 'center',
    formatter : function(value, row, index) {
        return value;
    }
},{
    field : 'name',
    title : '商品包名称',
    width : '10%',
    align : 'center',
    formatter : function(value, row, index) {
        return value;
    }
},{
    field : 'firstPosterPic',
    title : '图片',
    width : '10%',
    align : 'center',
    formatter : function(value, row, index) {
        return "<img style='height:30px;width:30px;' src='"
            + value + "' />";
    }
}];

//优购专题页
var col8 = [ {
    field : 'specSn',
    title : '内容ID',
    width : '8%',
    align : 'center',
    formatter : function(value, row, index) {
        return value;
    }
},{
    field : 'name',
    title : '商品名称',
    width : '10%',
    align : 'center',
    formatter : function(value, row, index) {
        return value;
    }
},{
    field : 'imgUrl',
    title : '图片',
    width : '10%',
    align : 'center',
    formatter : function(value, row, index) {
        return "<img style='height:30px;width:30px;' src='"
            + value + "' />";
    }
}];
//优购详情页
var col9 = [ {
    field : 'goodsSn',
    title : '内容ID',
    width : '8%',
    align : 'center',
    formatter : function(value, row, index) {
        return value;
    }
},{
    field : 'name',
    title : '商品名称',
    width : '10%',
    align : 'center',
    formatter : function(value, row, index) {
        return value;
    }
},{
    field : 'productImages',
    title : '图片',
    width : '10%',
    align : 'center',
    formatter : function(value, row, index) {
        return "<img style='height:30px;width:30px;' src='"
            + value + "' />";
    }
}];

//Tab页
var col10 = [ {
    field : 'id',
    title : 'ID',
    width : '8%',
    align : 'center',
    formatter : function(value, row, index) {
        return value;
    }
},{
    field : 'tabName',
    title : 'Tab名称',
    width : '10%',
    align : 'center',
    formatter : function(value, row, index) {
        return value;
    }
},{
    field : 'tabTitle',
    title : 'Tab标题名称',
    width : '10%',
    align : 'center',
    formatter : function(value, row, index) {
        return value;
    }
}];

//会员活动
var col11 = [ {
    field : 'activityId',
    title : '活动ID',
    width : '8%',
    align : 'center',
    formatter : function(value, row, index) {
        return value;
    }
},{
    field : 'activityName',
    title : '活动名称',
    width : '10%',
    align : 'center',
    formatter : function(value, row, index) {
        return value;
    }
},{
    field : 'activityImg',
    title : '活动海报图',
    width : '10%',
    align : 'center',
    formatter : function(value, row, index) {
        return "<img style='height:30px;width:30px;' src='"
            + value + "' />";
    }
}];

//启动应用(数据来源“非开机启动指令管理”页面)
var col12 = [ {
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
    width : '10%',
    align : 'center',
    formatter : function(value, row, index) {
        return value;
    }
},{
    field : 'apkName',
    title : '启动APK',
    width : '10%',
    align : 'center',
    formatter : function(value, row, index) {
        return value;
    }
},{
    field : 'actionStr',
    title : 'Action指令',
    width : '10%',
    align : 'center',
    formatter : function(value, row, index) {
        return value;
    }
}];
function initCustomCombobox() {
    initActivitiesType();
    initAppType();
    initCondition();
    initSubCondition();
    initFirstType();

}
//剧集列表
function initVideoTable(programsetId) {
    $('#videoContent').bootstrapTable('destroy');
    $("#videoContent").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/program/getPageList", //获取数据的Servlet地址
        striped: true,  //表格显示条纹
        pagination: true, //启动分页
        pageSize: 10,  //每页显示的记录数
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
            $('#customContentSubTitle').val(row.focus);
        }
    });
}
//初始化优购一级分类
function initFirstType() {
    $.post(ctx+"/portal/youGouGoods/getTopCategoryList.json",{"page":1,"rows":1000}, function(data) {
        if (data.length > 0)
            $.each(data, function(idx, item) {
                var option = $("<option value='" + item.categorySn + "'>" + item.name + "</option>");
                $("#firstType").append(option);
            });
        var firstType = $("#firstType").find("option:selected").val();
        $.post(ctx+"/portal/youGouGoods/getSubCategoryList.json",{"categorySn":firstType,"page":1,"rows":1000}, function(data) {
            $('#secondType').empty();
            if (data.length > 0)
                $.each(data, function(idx, item) {
                    var option = $("<option value='" + item.categorySn + "'>" + item.name + "</option>");
                    $("#secondType").append(option);
                });
            var secondType = $("#secondType").find("option:selected").val();
            $.post(ctx+"/portal/youGouGoods/getSubCategoryList.json",{"categorySn":secondType,"page":1,"rows":1000}, function(data) {
                $('#thirdType').empty();
                if (data.length > 0)
                    $.each(data, function(idx, item) {
                        var option = $("<option value='" + item.categorySn + "'>" + item.name + "</option>");
                        $("#thirdType").append(option);
                    });
            }, "json");
        }, "json");
    }, "json");
}
//初始化优购一级分类
function firstType() {
    var firstType = $("#firstType").find("option:selected").val();
    $.post(ctx+"/portal/youGouGoods/getSubCategoryList.json",{"categorySn":firstType,"page":1,"rows":1000}, function(data) {
        $('#secondType').empty();
        if (data.length > 0)
            $.each(data, function(idx, item) {
                var option = $("<option value='" + item.categorySn + "'>" + item.name + "</option>");
                $("#secondType").append(option);
            });
        var secondType = $("#secondType").find("option:selected").val();
        $.post(ctx+"/portal/youGouGoods/getSubCategoryList.json",{"categorySn":secondType,"page":1,"rows":1000}, function(data) {
            $('#thirdType').empty();
            if (data.length > 0)
                $.each(data, function(idx, item) {
                    var option = $("<option value='" + item.categorySn + "'>" + item.name + "</option>");
                    $("#thirdType").append(option);
                });
        }, "json");
    }, "json");
}
//初始化优购一级分类
function secondType() {
    var secondType = $("#secondType").find("option:selected").val();
    $.post(ctx+"/portal/youGouGoods/getSubCategoryList.json",{"categorySn":secondType,"page":1,"rows":1000}, function(data) {
        $('#thirdType').empty();
        if (data.length > 0)
            $.each(data, function(idx, item) {
                var option = $("<option value='" + item.categorySn + "'>" + item.name + "</option>");
                $("#thirdType").append(option);
            });
    }, "json");
}
//初始化活动类型
function initActivitiesType() {
    $.post(ctx+"/activityFreeVip/getComboboxList.json", function(data) {
        if (data.length > 0)
            $.each(data, function(idx, item) {
                RecomSweepstakesTypeMap[item.typeCode] = item.typeName;
            });
    }, "json");
}
//初始化app类型
function initAppType() {
    $.post(ctx+"/appCategory/getList.json",{}, function(data) {
        if (data.length > 0)
            $.each(data, function(idx, item) {
                var option = $("<option value='" + item.categoryId + "'>" + item.categoryName + "</option>");
                var option1 = $("<option value='" + item.categoryId + "'>" + item.categoryName + "</option>");
                $("#app_type").append(option);
                $("#appsubject_type").append(option1);
            });
    }, "json");
}
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
                    var tr = ""
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
function initCustomTempletApkChannelTypeBindEvent(){
    var templetId = $("#customTempletId").val();
    var templetApk = $("#customTempleApk").val();
    var templetApkChannelType = $("#templetApkChannelType").find("option:selected").val();
    $.ajax({
        async : true,
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
function initSubCondition(){
    $.ajax({
        async : true,
        type : "post",
        url : ctx+"/portal/templet/getList",
        dataType : "json",
        data : {},
        success : function(jsonData) {
            var select = jsonData;
            if(select != null && select != "" && select.length > 0){//如果子列表有值则把值赋上去
                var tr = ""
                for(var i = 0 ; i < select.length ; i ++){
                    tr = tr + "<option value='" + select[i].id + "'>" + select[i].name + "</option>";
                }
                $('#customSubTempleteId').append(tr);
            }
            var tId = $("#customSubTempleteId").find("option:selected").val();
            $.ajax({
                async : true,
                type : "post",
                url : ctx+"/portal/templetApk/getList",
                dataType : "json",
                data : {"templetId":tId},
                success : function(jsonData) {
                    $('#customSubApk').empty();
                    var select = jsonData;
                    if(select != null && select != "" && select.length > 0){
                        var tr = ""
                        for(var i = 0 ; i < select.length ; i ++){
                            tr = tr + "<option value='" + select[i].apkBagName + "'>" + select[i].apkName + "</option>";
                        }
                        $('#customSubApk').append(tr);
                    }
                }
            });
        }
    });
}

/*推荐位模板下拉框*/
function setLayoutSelect() {
    $.post(ctx +"/customRecomTempleteList/setFatherSelect.json",{"templeteLeve" : 1}, function(json) {
        var appText = "";
        for (var i = 0; i < json.length; i++) {
            appText += '<option value="' + json[i].templeteId + '">'
                + json[i].templeteName + '</option>';
        }
        $("#contentFatherId").html(appText);
    }, "json");
}

//所有数据来源
function initCustomMoreTable(contentType,url) {
   /* $('#content'+contentType).bootstrapTable('destroy');*/
    $('#customRecomContentByTypeTable').bootstrapTable('destroy');
    $('#customRecomContentByTypeTable').bootstrapTable({
    /*$('#content'+contentType).bootstrapTable({*/
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + url, //获取数据的Servlet地址
        striped: false,  //表格显示条纹
        pagination: true, //启动分页
        pageSize: 5,  //每页显示的记录数
        pageNumber: 1, //当前第几页
        pageList: [5,10,20],  //记录数可选列表
        sidePagination: "server", //表示服务端请求
        queryParamsType: "undefined",
        queryParams: function queryParams(params) {   //设置查询参数
            moreCustomParam['page'] =params.pageNumber;
            moreCustomParam['rows'] =params.pageSize;
            return moreCustomParam;
        },
        columns : [ eval("col"+contentType) ],
        onClickRow: function(row) {
            $('#installStyle').val(0);
            $('#appType').val(0);
            $('#customContentSubtitle').val("");
            $('#customRecomOutcropImg').attr('src', '');
            $("#skip").html("跳转状态:可跳转");
            $("#isPlay").attr("disabled",false);
            var playFlag = $('#playFlag').val();
            //全屏大图
            if(contentType == 6){
                $('#customContentId').val(row.imgId);
                $('#customContentTitle').val(row.imgName);
               /* $('#txt_title').val(row.imgName);*/
                $('#customRecomContentImg').attr('src', row.imgAddr);
                $('#contentBigPic').attr('src', row.imgAddr);
                $('#categoryId').val('');
                $('#chnId').val('');
                $('#chnType').val('');
                $('#hotId').val('');
                $('#hotType').val('');
                $('#aqyIsVip').val('');
                $('#apkBagName').val('');
                $('#specSn').val('');
                $('#apkVersionCode').val('');
                $('#apkDownUrl').val('');
                $('#apk').val('');
            }
            //专辑详情
            if(contentType == 1){
                var preM3u8 = row.preM3u8;
                $('#customContentId').val(row.programsetId);
                $('#customContentTitle').val(row.albumName);
                $('#customContentSubtitle').val(row.focus);
                $('#customRecomContentImg').attr('src', row.albumHbPic);
                $('#apkBagName').val(row.apkBagName);
                if("com.hiveview.premiere" ==row.apkBagName && preM3u8 !=null && preM3u8!='' && playFlag ==1){
                    $("#isPlay").val(1);
                    $("#videoUrl").val(preM3u8);
                    $('#customRecomContentImg').attr('src', '');
                }else{
                    $("#isPlay").val(0);
                    $("#videoUrl").val('');
                }
                var isPlay = $("#isPlay").find("option:selected").val();
                if(isPlay == 0){
                    $('#t_1').css('display','block');
                    $('#t_2').css('display','block');
                    $('#t_3').css('display','block');
                    $('#t_4').css('display','block');
                    $('#s').css('display','none');
                    $("#isPlay").attr("disabled",true);
                }else{
                    $('#t_1').css('display','none');
                    $('#t_2').css('display','none');
                    $('#t_3').css('display','none');
                    $('#t_4').css('display','none');
                    $('#s').css('display','block');
                    $("#isPlay").attr("disabled",false);
                }
                if("com.hiveview.premiere" ==row.apkBagName){
                    var templetApkHotId = $("#templetApkHot").find("option:selected").val();
                    $('#hotId').val(templetApkHotId);
                    $('#hotType').val(3);
                }else{
                    $('#hotId').val('');
                    $('#hotType').val('');
                }
                $('#chnId').val(row.chnId);
                $('#chnType').val(row.chnType);
                $('#aqyIsVip').val(row.aqyIsVip);
                $('#categoryId').val('');
                $('#contentBigPic').attr('src', '');
                $('#specSn').val('');
                $('#apkVersionCode').val('');
                $('#apkDownUrl').val('');
                $('#videoId').val('');
                $('#apk').val('');
            }
            //影视专题
            if(contentType == 0){
                $('#customContentId').val(row.subjectId);
                $('#customContentTitle').val(row.subjectName);
                $('#apkBagName').val(row.apkBagName);
                /*$('#txt_title').val(row.subjectName);*/
                $('#customRecomContentImg').attr('src', row.subjectVerPic);
                $('#contentBigPic').attr('src', '');
                $('#categoryId').val('');
                $('#chnId').val('');
                $('#chnType').val('');
                $('#hotId').val('');
                $('#hotType').val('');
                $('#aqyIsVip').val('');
                $('#specSn').val('');
                $('#apkVersionCode').val('');
                $('#apkDownUrl').val('');
                $('#videoId').val('');
                $('#apk').val('');
            }
            //应用详情
            if(contentType == 2){
                $('#customContentId').val(row.appId);
                $('#customContentTitle').val(row.appName);
                $('#categoryId').val(row.categoryId);
                /*$('#txt_title').val(row.appName);*/
                $('#customRecomContentImg').attr('src', row.appIcon);
                $('#contentBigPic').attr('src', '');
                $('#chnId').val(row.categoryId);
                $('#chnType').val('');
                $('#hotId').val('');
                $('#hotType').val('');
                $('#aqyIsVip').val('');
                $('#apkBagName').val('');
                $('#specSn').val('');
                $('#apkVersionCode').val('');
                $('#apkDownUrl').val('');
                $('#videoId').val('');
                $('#apk').val('');
            }
            //应用专题
            if(contentType == 3){
                $('#customContentId').val(row.pubId);
                $('#customContentTitle').val(row.pubName);
                $('#categoryId').val(row.categoryId);
               /* $('#txt_title').val(row.pubName);*/
                $('#customRecomContentImg').attr('src', row.pubImg);
                $('#contentBigPic').attr('src', '');
                // var catId = row.categoryId;
                // if(catId ==){
                //
                // }
                $('#chnId').val(row.categoryId);
                $('#chnType').val('');
                $('#hotId').val('');
                $('#hotType').val('');
                $('#aqyIsVip').val('');
                $('#apkBagName').val('');
                $('#apkVersionCode').val('');
                $('#apkDownUrl').val('');
                $('#videoId').val('');
                $('#apk').val('');
            }
            //活动详情
            if(contentType == 4){
                $('#customContentId').val(row.activityId);
                $('#customContentTitle').val(row.title);
               /* $('#txt_title').val(row.title);*/
                $('#customRecomContentImg').attr('src', row.bgUrl);
                $('#contentBigPic').attr('src', '');
                $('#categoryId').val('');
                $('#chnId').val('');
                $('#chnType').val('');
                $('#hotId').val('');
                $('#hotType').val('');
                $('#aqyIsVip').val('');
                $('#apkBagName').val('');
                $('#specSn').val('');
                $('#apkVersionCode').val('');
                $('#apkDownUrl').val('');
                $('#videoId').val('');
                $('#apk').val('');
            }
            //商品包
            if(contentType == 7){
                $('#customContentId').val(row.id);
                $('#customContentTitle').val(row.name);
               /* $('#txt_title').val(row.name);*/
                $('#customRecomContentImg').attr('src', row.firstPosterPic);
                $('#contentBigPic').attr('src', '');
                $('#categoryId').val('');
                $('#chnId').val('');
                $('#chnType').val('');
                $('#hotId').val('');
                $('#hotType').val('');
                $('#aqyIsVip').val('');
                $('#apkBagName').val('');
                $('#specSn').val('');
                $('#apkVersionCode').val('');
                $('#apkDownUrl').val('');
                $('#videoId').val('');
                $('#apk').val('');
            }
            //优购专题
            if(contentType == 8){
                //优购的唯一标示字段是字符串 specSn
                var sn = row.specSn;
                var id = sn.substr(sn.length-5,6);
                $('#customContentId').val(id);
                $('#customContentTitle').val(row.name);
                /* $('#txt_title').val(row.name);*/
                $('#customRecomContentImg').attr('src', row.imgUrl);
                $('#contentBigPic').attr('src', '');
                $('#categoryId').val('');
                $('#chnId').val('');
                $('#chnType').val('');
                $('#hotId').val('');
                $('#hotType').val('');
                $('#aqyIsVip').val('');
                $('#apkBagName').val('');
                $('#specSn').val(row.specSn);
                $('#apkVersionCode').val('');
                $('#apkDownUrl').val('');
                $('#videoId').val('');
                $('#apk').val('');
            }
            //优购详情
            if(contentType == 9){
                //优购的唯一标示字段是字符串 specSn
                var sn = row.goodsSn;
                var id = sn.substr(sn.length-5,6);
                $('#customContentId').val(id);
                $('#customContentTitle').val(row.name);
                /* $('#txt_title').val(row.name);*/
                $('#customRecomContentImg').attr('src', row.productImages);
                $('#contentBigPic').attr('src', '');
                $('#categoryId').val('');
                $('#chnId').val('');
                $('#chnType').val('');
                $('#hotId').val('');
                $('#hotType').val('');
                $('#aqyIsVip').val('');
                $('#apkBagName').val('');
                $('#specSn').val(row.goodsSn);
                $('#apkVersionCode').val('');
                $('#apkDownUrl').val('');
                $('#videoId').val('');
                $('#apk').val('');
            }
            //tab页
            if(contentType == 10){
                //关联的只是tab
                $('#customContentId').val(row.id);
                $('#customContentTitle').val(row.tabTitle);
                /* $('#txt_title').val(row.name);*/
                $('#customRecomContentImg').attr('src','');
                $('#contentBigPic').attr('src', '');
                $('#categoryId').val('');
                $('#chnId').val('');
                $('#chnType').val('');
                $('#hotId').val('');
                $('#hotType').val('');
                $('#aqyIsVip').val('');
                $('#apkBagName').val('');
                $('#specSn').val('');
                $('#apkVersionCode').val('');
                $('#apkDownUrl').val('');
                $('#videoId').val('');
                $('#apk').val('');
            }
            //会员活动
            if(contentType == 11){
                //关联的只是会员活动
                $('#customContentId').val(row.activityId);
                $('#customContentTitle').val(row.activityName);
                /* $('#txt_title').val(row.name);*/
                $('#customRecomContentImg').attr('src',row.activityImg);
                $('#contentBigPic').attr('src', '');
                $('#categoryId').val('');
                $('#chnId').val('');
                $('#chnType').val('');
                $('#hotId').val('');
                $('#hotType').val('');
                $('#aqyIsVip').val('');
                $('#apkBagName').val('');
                $('#specSn').val('');
                $('#apkVersionCode').val('');
                $('#apkDownUrl').val('');
                $('#videoId').val('');
                $('#apk').val('');
            }
            //启动应用
            if(contentType == 12){
                //关联的只是启动应用
                $('#customContentId').val(row.id);
                $('#customContentTitle').val(row.appName);
                /* $('#txt_title').val(row.name);*/
                $('#customRecomContentImg').attr('src','');
                $('#contentBigPic').attr('src', '');
                $('#categoryId').val('');
                $('#chnId').val('');
                $('#chnType').val('');
                $('#hotId').val('');
                $('#hotType').val('');
                $('#aqyIsVip').val('');
                $('#apkBagName').val(row.packageName);
                $('#specSn').val('');
                $('#apkVersionCode').val(row.versionNo);
                $('#apkDownUrl').val(row.versionUrl);
                $('#videoId').val('');
                $('#action').val(row.actionStr);
                $('#apk').val(row.packageName);
                $('#installStyle').val(row.installStyle);
                $('#appType').val(row.appType);
            }
            var title = $('#customContentTitle').val();
            $("#customContentSubtitle").attr("disabled",false);
        }
    });
}
function checkTitle(){
    var customContentTitle = $("#customContentTitle").val();
    if(customContentTitle == null ||customContentTitle ==""){
        $("#customContentSubtitle").val(customContentTitle);
        $("#customContentSubtitle").attr("disabled",true);
    }else{
        $("#customContentSubtitle").attr("disabled",false);
    }
}
function dataCustomSource(){
    var contentType = $("#customContentType").val();

    if(contentType == 6){//全屏大图
        moreCustomParam ={};
        moreCustomParam['imgName'] =$("#name_6").val();
        $('#div_'+0).css('display','none');
        $('#div_'+1).css('display','none');
        $('#div_'+2).css('display','none');
        $('#div_'+3).css('display','none');
        $('#div_'+4).css('display','none');
        $('#div_'+6).css('display','block');
        $('#div_'+7).css('display','none');
        $('#div_'+8).css('display','none');
        $('#div_'+9).css('display','none');
        $('#div_'+10).css('display','none');
        $('#div_'+11).css('display','none');
        $('#div_'+12).css('display','none');
        getCustomMoreList(6,"/bigPics/getList");
    }
    //专辑详情
    if(contentType == 1){
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
        $('#div_'+0).css('display','none');
        $('#div_'+1).css('display','block');
        $('#div_'+2).css('display','none');
        $('#div_'+3).css('display','none');
        $('#div_'+4).css('display','none');
        $('#div_'+6).css('display','none');
        $('#div_'+7).css('display','none');
        $('#div_'+8).css('display','none');
        $('#div_'+9).css('display','none');
        $('#div_'+10).css('display','none');
        $('#div_'+11).css('display','none');
        $('#div_'+12).css('display','none');
        getCustomMoreList(1,"/portal/newContentChan/getNewContentChanPageList");
    }
    //影视专题
    if(contentType == 0){
        moreCustomParam ={};
        moreCustomParam['subjectName'] =$("#name_0").val();
        var templet = $("#customSubTempleteId").find("option:selected").val();
        var templetApk = $("#customSubApk").find("option:selected").val();
        moreCustomParam['apkBagName'] =templetApk;
        moreCustomParam['templetId'] =templet;
        moreCustomParam['isOnline'] =1;
        $('#div_'+0).css('display','block');
        $('#div_'+1).css('display','none');
        $('#div_'+2).css('display','none');
        $('#div_'+3).css('display','none');
        $('#div_'+4).css('display','none');
        $('#div_'+6).css('display','none');
        $('#div_'+7).css('display','none');
        $('#div_'+8).css('display','none');
        $('#div_'+9).css('display','none');
        $('#div_'+10).css('display','none');
        $('#div_'+11).css('display','none');
        $('#div_'+12).css('display','none');
        getCustomMoreList(0,"/compositeSubject/getPageList");
    }
    //应用详情
    if(contentType == 2){
        moreCustomParam ={};
        var appType = $("#app_type").val();
        moreCustomParam['appName'] =$("#name_2").val();
        moreCustomParam['categoryId'] = appType;
        moreCustomParam['state'] = 1;
        $('#div_'+0).css('display','none');
        $('#div_'+1).css('display','none');
        $('#div_'+2).css('display','block');
        $('#div_'+3).css('display','none');
        $('#div_'+4).css('display','none');
        $('#div_'+6).css('display','none');
        $('#div_'+7).css('display','none');
        $('#div_'+8).css('display','none');
        $('#div_'+9).css('display','none');
        $('#div_'+10).css('display','none');
        $('#div_'+11).css('display','none');
        $('#div_'+12).css('display','none');
        getCustomMoreList(2,"/app/getPageList");
    }
    //应用专题
    if(contentType == 3){
        moreCustomParam ={};
        var appType = $("#appsubject_type").val();
        moreCustomParam['subjectName'] =$("#name_3").val();
        moreCustomParam['categoryId'] = appType;
        moreCustomParam['isEffective'] = 1;
        $('#div_'+0).css('display','none');
        $('#div_'+1).css('display','none');
        $('#div_'+2).css('display','none');
        $('#div_'+3).css('display','block');
        $('#div_'+4).css('display','none');
        $('#div_'+6).css('display','none');
        $('#div_'+7).css('display','none');
        $('#div_'+8).css('display','none');
        $('#div_'+9).css('display','none');
        $('#div_'+10).css('display','none');
        $('#div_'+11).css('display','none');
        $('#div_'+12).css('display','none');
        getCustomMoreList(3,"/cloudAppSubject/getPageList");
    }
    //活动详情
    if(contentType == 4){
        moreCustomParam ={};
        moreCustomParam['title'] =$("#name_4").val();
        $('#div_'+0).css('display','none');
        $('#div_'+1).css('display','none');
        $('#div_'+2).css('display','none');
        $('#div_'+3).css('display','none');
        $('#div_'+4).css('display','block');
        $('#div_'+6).css('display','none');
        $('#div_'+7).css('display','none');
        $('#div_'+8).css('display','none');
        $('#div_'+9).css('display','none');
        $('#div_'+10).css('display','none');
        $('#div_'+11).css('display','none');
        $('#div_'+12).css('display','none');
        getCustomMoreList(4,"/activityFreeVip/getPageList");
    }
    //商品包
    if(contentType == 7){
        moreCustomParam ={};
        moreCustomParam['title'] =$("#name_7").val();
        moreCustomParam['isEffective'] =1;
        $('#div_'+0).css('display','none');
        $('#div_'+1).css('display','none');
        $('#div_'+2).css('display','none');
        $('#div_'+3).css('display','none');
        $('#div_'+4).css('display','none');
        $('#div_'+6).css('display','none');
        $('#div_'+7).css('display','block');
        $('#div_'+8).css('display','none');
        $('#div_'+9).css('display','none');
        $('#div_'+10).css('display','none');
        $('#div_'+11).css('display','none');
        $('#div_'+12).css('display','none');
        getCustomMoreList(7,"/portal/onlineGoods/getPageList");
    }
    //优购专题
    if(contentType == 8){
        moreCustomParam ={};
        moreCustomParam['name'] =$("#name_8").val();
        $('#div_'+0).css('display','none');
        $('#div_'+1).css('display','none');
        $('#div_'+2).css('display','none');
        $('#div_'+3).css('display','none');
        $('#div_'+4).css('display','none');
        $('#div_'+6).css('display','none');
        $('#div_'+7).css('display','none');
        $('#div_'+8).css('display','block');
        $('#div_'+9).css('display','none');
        $('#div_'+10).css('display','none');
        $('#div_'+11).css('display','none');
        $('#div_'+12).css('display','none');
        getCustomMoreList(8,"/portal/youGouSpecials/getPageList");
    }
    //优购详情
    if(contentType == 9){
        moreCustomParam ={};
        moreCustomParam['goodsName'] =$("#name_9").val();
        var thirdType = $("#thirdType").find("option:selected").val();
        moreCustomParam['categorySn'] = thirdType;
        $('#div_'+0).css('display','none');
        $('#div_'+1).css('display','none');
        $('#div_'+2).css('display','none');
        $('#div_'+3).css('display','none');
        $('#div_'+4).css('display','none');
        $('#div_'+6).css('display','none');
        $('#div_'+7).css('display','none');
        $('#div_'+8).css('display','none');
        $('#div_'+9).css('display','block');
        $('#div_'+10).css('display','none');
        $('#div_'+11).css('display','none');
        $('#div_'+12).css('display','none');
        getCustomMoreList(9,"/portal/youGouGoods/getPageList");
    }
    //tab
    if(contentType == 10){
        moreCustomParam ={};
        moreCustomParam['tabName'] =$("#name_10").val();
        moreCustomParam['isEffective'] =1;
        $('#div_'+0).css('display','none');
        $('#div_'+1).css('display','none');
        $('#div_'+2).css('display','none');
        $('#div_'+3).css('display','none');
        $('#div_'+4).css('display','none');
        $('#div_'+6).css('display','none');
        $('#div_'+7).css('display','none');
        $('#div_'+8).css('display','none');
        $('#div_'+9).css('display','none');
        $('#div_'+10).css('display','block');
        $('#div_'+11).css('display','none');
        $('#div_'+12).css('display','none');
        getCustomMoreList(10,"/tab/getPageList");
    }
    //会员活动
    if(contentType == 11){
        moreCustomParam ={};
        moreCustomParam['activityName'] =$("#name_11").val();
        moreCustomParam['activityStatus'] =1;
        //会员活动没有参数
        $('#div_'+0).css('display','none');
        $('#div_'+1).css('display','none');
        $('#div_'+2).css('display','none');
        $('#div_'+3).css('display','none');
        $('#div_'+4).css('display','none');
        $('#div_'+6).css('display','none');
        $('#div_'+7).css('display','none');
        $('#div_'+8).css('display','none');
        $('#div_'+9).css('display','none');
        $('#div_'+10).css('display','none');
        $('#div_'+11).css('display','block');
        $('#div_'+12).css('display','none');
        getCustomMoreList(11,"/portal/activity/getPageList");
    }
    //启动应用(豆腐块)
    if(contentType == 12){
        moreCustomParam ={};
        //启动应用没有参数
        $('#div_'+0).css('display','none');
        $('#div_'+1).css('display','none');
        $('#div_'+2).css('display','none');
        $('#div_'+3).css('display','none');
        $('#div_'+4).css('display','none');
        $('#div_'+6).css('display','none');
        $('#div_'+7).css('display','none');
        $('#div_'+8).css('display','none');
        $('#div_'+9).css('display','none');
        $('#div_'+10).css('display','none');
        $('#div_'+11).css('display','none');
        $('#div_'+12).css('display','block');
        getCustomMoreList(12,"/PortalNotStartInstruction/getPageList");
    }

}
function joinVideo(programsetId){
    initVideoTable(programsetId);
    $('#video_myModal').modal({backdrop: 'static', keyboard: false});
}
function selectCustomData(){
    /*clearCustomContentInfo();*/
    /*clearCustomNameInfo();*/
    var contentType = $("#customContentType").val();
    moreCustomParam = {};
    if(contentType == 6){
        moreCustomParam = {};
        moreCustomParam['imgName'] =$("#name_6").val();
        $('#div_'+0).css('display','none');
        $('#div_'+1).css('display','none');
        $('#div_'+2).css('display','none');
        $('#div_'+3).css('display','none');
        $('#div_'+4).css('display','none');
        $('#div_'+6).css('display','block');
        $('#div_'+7).css('display','none');
        $('#div_'+8).css('display','none');
        $('#div_'+9).css('display','none');
        $('#div_'+10).css('display','none');
        $('#div_'+11).css('display','none');
        $('#div_'+12).css('display','none');
        getCustomMoreList(6,"/bigPics/getList");
    }
    //专辑详情
    if(contentType == 1){
        moreCustomParam = {};
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
        $('#div_'+0).css('display','none');
        $('#div_'+1).css('display','block');
        $('#div_'+2).css('display','none');
        $('#div_'+3).css('display','none');
        $('#div_'+4).css('display','none');
        $('#div_'+6).css('display','none');
        $('#div_'+7).css('display','none');
        $('#div_'+8).css('display','none');
        $('#div_'+9).css('display','none');
        $('#div_'+10).css('display','none');
        $('#div_'+11).css('display','none');
        $('#div_'+12).css('display','none');
        getCustomMoreList(1,"/portal/newContentChan/getNewContentChanPageList");
    }
    //影视专题
    if(contentType == 0){
        moreCustomParam ={};
        moreCustomParam['subjectName'] =$("#name_0").val();
        var templet = $("#customSubTempleteId").find("option:selected").val();
        var templetApk = $("#customSubApk").find("option:selected").val();
        moreCustomParam['apkBagName'] =templetApk;
        moreCustomParam['templetId'] =templet;
        moreCustomParam['isOnline'] =1;
        $('#div_'+0).css('display','block');
        $('#div_'+1).css('display','none');
        $('#div_'+2).css('display','none');
        $('#div_'+3).css('display','none');
        $('#div_'+6).css('display','none');
        $('#div_'+7).css('display','none');
        $('#div_'+8).css('display','none');
        $('#div_'+9).css('display','none');
        $('#div_'+10).css('display','none');
        $('#div_'+11).css('display','none');
        $('#div_'+12).css('display','none');
        getCustomMoreList(0,"/compositeSubject/getPageList");
    }
    //应用详情
    if(contentType == 2){
        var appType = $("#app_type").val();
        moreCustomParam['appName'] =$("#name_2").val();
        moreCustomParam['categoryId'] = appType;
        moreCustomParam['state'] = 1;
        $('#div_'+0).css('display','none');
        $('#div_'+1).css('display','none');
        $('#div_'+2).css('display','block');
        $('#div_'+3).css('display','none');
        $('#div_'+4).css('display','none');
        $('#div_'+6).css('display','none');
        $('#div_'+7).css('display','none');
        $('#div_'+8).css('display','none');
        $('#div_'+9).css('display','none');
        $('#div_'+10).css('display','none');
        $('#div_'+11).css('display','none');
        $('#div_'+12).css('display','none');
        getCustomMoreList(2,"/app/getPageList");
    }
    //应用专题
    if(contentType == 3){
        var appType = $("#appsubject_type").val();
        moreCustomParam['subjectName'] =$("#name_3").val();
        moreCustomParam['categoryId'] = appType;
        $('#div_'+0).css('display','none');
        $('#div_'+1).css('display','none');
        $('#div_'+2).css('display','none');
        $('#div_'+3).css('display','block');
        $('#div_'+4).css('display','none');
        $('#div_'+6).css('display','none');
        $('#div_'+7).css('display','none');
        $('#div_'+8).css('display','none');
        $('#div_'+9).css('display','none');
        $('#div_'+10).css('display','none');
        $('#div_'+11).css('display','none');
        $('#div_'+12).css('display','none');
        getCustomMoreList(3,"/cloudAppSubject/getPageList");
    }
    //活动详情
    if(contentType == 4){
        var name4 = $("#name_4").val();
        moreCustomParam['title'] = name4;
        $('#div_'+0).css('display','none');
        $('#div_'+1).css('display','none');
        $('#div_'+2).css('display','none');
        $('#div_'+3).css('display','none');
        $('#div_'+4).css('display','block');
        $('#div_'+6).css('display','none');
        $('#div_'+7).css('display','none');
        $('#div_'+8).css('display','none');
        $('#div_'+9).css('display','none');
        $('#div_'+10).css('display','none');
        $('#div_'+11).css('display','none');
        $('#div_'+12).css('display','none');
        getCustomMoreList(4,"/activityFreeVip/getPageList");
    }
    //商品包
    if(contentType == 7){
        moreCustomParam['name'] =$("#name_7").val();
        moreCustomParam['isEffective'] =1;
        $('#div_'+0).css('display','none');
        $('#div_'+1).css('display','none');
        $('#div_'+2).css('display','none');
        $('#div_'+3).css('display','none');
        $('#div_'+4).css('display','none');
        $('#div_'+6).css('display','none');
        $('#div_'+7).css('display','block');
        $('#div_'+8).css('display','none');
        $('#div_'+9).css('display','none');
        $('#div_'+10).css('display','none');
        $('#div_'+11).css('display','none');
        $('#div_'+12).css('display','none');
        getCustomMoreList(7,"/portal/onlineGoods/getPageList");
    }
    //优购专题
    if(contentType == 8){
        moreCustomParam ={};
        moreCustomParam['name'] =$("#name_8").val();
        $('#div_'+0).css('display','none');
        $('#div_'+1).css('display','none');
        $('#div_'+2).css('display','none');
        $('#div_'+3).css('display','none');
        $('#div_'+4).css('display','none');
        $('#div_'+6).css('display','none');
        $('#div_'+7).css('display','none');
        $('#div_'+8).css('display','block');
        $('#div_'+9).css('display','none');
        $('#div_'+10).css('display','none');
        $('#div_'+11).css('display','none');
        $('#div_'+12).css('display','none');
        getCustomMoreList(8,"/portal/youGouSpecials/getPageList");
    }
    //优购详情
    if(contentType == 9){
        moreCustomParam ={};
        moreCustomParam['goodsName'] =$("#name_9").val();
        var thirdType = $("#thirdType").find("option:selected").val();
        moreCustomParam['categorySn'] = thirdType;
        $('#div_'+0).css('display','none');
        $('#div_'+1).css('display','none');
        $('#div_'+2).css('display','none');
        $('#div_'+3).css('display','none');
        $('#div_'+4).css('display','none');
        $('#div_'+6).css('display','none');
        $('#div_'+7).css('display','none');
        $('#div_'+8).css('display','none');
        $('#div_'+9).css('display','block');
        $('#div_'+10).css('display','none');
        $('#div_'+11).css('display','none');
        $('#div_'+12).css('display','none');
        getCustomMoreList(9,"/portal/youGouGoods/getPageList");
    }
    //tab
    if(contentType == 10){
        moreCustomParam ={};
        moreCustomParam['tabName'] =$("#name_10").val();
        moreCustomParam['isEffective'] =1;
        $('#div_'+0).css('display','none');
        $('#div_'+1).css('display','none');
        $('#div_'+2).css('display','none');
        $('#div_'+3).css('display','none');
        $('#div_'+4).css('display','none');
        $('#div_'+6).css('display','none');
        $('#div_'+7).css('display','none');
        $('#div_'+8).css('display','none');
        $('#div_'+9).css('display','none');
        $('#div_'+10).css('display','block');
        $('#div_'+11).css('display','none');
        $('#div_'+12).css('display','none');
        getCustomMoreList(10,"/tab/getPageList");
    }
    //会员活动
    if(contentType == 11){
        moreCustomParam ={};
        moreCustomParam['activityName'] =$("#name_11").val();
        moreCustomParam['activityStatus'] =1;
        //会员活动没有参数
        $('#div_'+0).css('display','none');
        $('#div_'+1).css('display','none');
        $('#div_'+2).css('display','none');
        $('#div_'+3).css('display','none');
        $('#div_'+4).css('display','none');
        $('#div_'+6).css('display','none');
        $('#div_'+7).css('display','none');
        $('#div_'+8).css('display','none');
        $('#div_'+9).css('display','none');
        $('#div_'+10).css('display','none');
        $('#div_'+11).css('display','block');
        $('#div_'+12).css('display','none');
        getCustomMoreList(11,"/portal/activity/getPageList");
    }
    //启动应用(豆腐块)
    if(contentType == 12){
        moreCustomParam ={};
        //会员活动没有参数
        $('#div_'+0).css('display','none');
        $('#div_'+1).css('display','none');
        $('#div_'+2).css('display','none');
        $('#div_'+3).css('display','none');
        $('#div_'+4).css('display','none');
        $('#div_'+6).css('display','none');
        $('#div_'+7).css('display','none');
        $('#div_'+8).css('display','none');
        $('#div_'+9).css('display','none');
        $('#div_'+10).css('display','none');
        $('#div_'+11).css('display','none');
        $('#div_'+12).css('display','block');
        getCustomMoreList(12,"/PortalNotStartInstruction/getPageList");
    }
    $('#editcontent_myModal').modal();
}
function changeShow(){
    var isPlay = $("#isPlay").find("option:selected").val();
    if(isPlay == 0){
        $('#t_1').css('display','block');
        $('#t_2').css('display','block');
        $('#t_3').css('display','block');
        $('#t_4').css('display','block');
        $('#s').css('display','none');
    }else{
        $('#t_1').css('display','none');
        $('#t_2').css('display','none');
        $('#t_3').css('display','none');
        $('#t_4').css('display','none');
        $('#s').css('display','block');
    }
}
// 上传图片
function initCustomUploadJs(file_id) {
    var file_upload = '#' + file_id + 'Url';
    $(file_upload).uploadify({
        'swf' :ctx +  '/res/js/plugin/uploadify/uploadify.swf',
        'uploader' :ctx +  '/upload/init.json', // fileUpload/newIcpUpload.json
        'height' : 25,
        'width' : 120,
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

            if (file_id == "customRecomOutcropImg" || file_id == "customRecomContentImg") {
                if (file.type != ".jpg" && file.type != ".png"
                    && file.type != ".gif") {
                    alert( '上传文件错误，请选择合法图片！');
                    $(file_upload).uploadify('cancel', '*');
                }
            }

            if (file.type == ".jpg" || file.type == ".png") {
                if (size > 1024 * 1024 * 1) {
                    alert( '图片大小超出1MB！');
                    myself.cancelUpload(file.id);
                    $('#' + file.id).remove();
                }
            } else {
                if (size > 1024 * 1024 * 200) {
                    alert( '视频大小超出200MB！');
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
            var imgId = '#' + file_id;
            $(imgId).attr("src", "");
        }
    });
}