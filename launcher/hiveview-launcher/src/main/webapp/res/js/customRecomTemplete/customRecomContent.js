var param ={};
var templetContentMap = {};
var allTotal =0;
var total = 0;
var s =0;
var record =0;
$(document).ready(function(){
    initTable();
    setLayoutSelect();
    initCustomCombobox();
    initCustomUploadJs("customRecomContentImg");
    initCustomUploadJs("customRecomOutcropImg");
    initUploadJs("contentBackPicImg");
    optionTemplete();
});
function optionTemplete(){
    $.ajax({
        type:"get",
        async: false,
        url:ctx + "/customRecomTempleteList/getFirstList",
        data:{templeteLeve:1},
        dataType:"json",
        success:function (res) {
           // autoJson=res;
            //debugger;
            $("#recomTemplete").empty();
            var select=$("<option value=''>-请选择-</option>");
            $("#recomTemplete").append(select);
            var res1 = res.reverse();
            $.each(res1, function (index, element) {
                var $option = $("<option value='" + element['templeteId'] + "'>" + element['templeteName'] + "</option>");
                $("#recomTemplete").append($option);
            });
        }
    });
}
function  initTable() {
    var contentName = $("#searchCustomRecomName").val();
    var isEffective = $("#searchContentIseffective").val();
    //先销毁表格
    $('#customRecomContentTable').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#customRecomContentTable").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/customRecomTempleteList/getPageList", //获取数据的Servlet地址
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
                templeteLeve:2,
                contentName:contentName,
                isEffective:isEffective,
                page: params.pageNumber,
                rows: params.pageSize
            };
            return param;
        },
        columns : [[{
            field :'templeteId',
            title : 'ID',
            width : '10%',
            align : 'center',

            formatter : function(value, row ,index){
                return value;
            }

        }, {
            field: 'contentName',
            title: 'Group名称',
            width: '10%',
            align: 'center',
            // class:"hide",
            formatter: function (value, row, index) {
                return value;
            }
        },{
                field : 'templeteTitle',
                title : 'Group标题名称',
                width  : '10%',
                align : 'center',
                formatter : function (value,row ,index){
                    return value;
                }
        },{
            field : 'templeteName',
            title : '模板名称',
            width  : '10%',
            align : 'center',
            formatter : function (value,row ,index){
                return value;
            }
        },{
            field : 'isEffective',
            title : '上下线',
            width : '10%',
            align : 'center',
            formatter :function(value ,row ,index){
                if (1 == value) {
                    return '<button  style="color:#fff;background:rgb(46,130,255);cursor:default;border-radius:3px 0 0 3px;border-top:1px solid #808080;border-bottom:1px solid #808080;border-left:1px solid #808080;height:26px;border-right:0" class="a_on a_on2">上线</button><a href="javascript:updateIsEffective('+row.templeteId+','+value+')" style="color:rgb(255,102,0);display: inline-block;height:24px;width:40px;border-top:1px solid #808080;border-bottom:1px solid #808080;border-right:1px solid #808080;vertical-align: -1px;line-height: 24px;text-decoration: none;border-radius:0 3px 3px 0">下线</a>';
                } else {
                    return '<a href="javascript:updateIsEffective('+row.templeteId+','+value+')" style="color:rgb(255,102,0);display: inline-block;height:24px;width:40px;border-top:1px solid #808080;border-bottom:1px solid #808080;border-left:1px solid #808080;vertical-align: -1px;line-height: 24px;text-decoration: none;border-radius:3px 0 0 3px">上线</a><button  style="color:#fff;background:rgb(46,130,255);cursor:default;border-radius:0 3px 3px 0;border-top:1px solid #808080;border-bottom:1px solid #808080;border-right:1px solid #808080;height:26px;border-left:0" class="a_on a_on2">下线</button>';
                }
            }
        },{
            field : 'do',
            title :'操作',
            width : '25%',
            align : 'center',
            formatter : function(value ,row ){
                var str = '';
                str +=  '<a href="javascript:editGroup('+ row.templeteId +"," + row.fatherId+ ')">编辑</a>';
                str +=  '&nbsp;&nbsp;<a href="javascript:copy('+ row.templeteId +')">复制</a>';
                str +=  '&nbsp;&nbsp;<a href="javascript:editTempleteContent('+ row.templeteId +')">编辑推荐位</a>';
                str += '&nbsp;&nbsp;<a href="javascript:viewTempleteContent('+ row.templeteId +')">预览</a>';
                str += '&nbsp;&nbsp;<a href="javascript:delCustomRecomContent('+ row.templeteId +')">删除</a>';
                return str;
            }
        },]],
        onClickCell: function (field,value ,row, td) {
            $(td.parent()[0]).css({"background":"rgb(255,184,72)"}).siblings().css({"background":"none"});
        }
    });
}
/*上下线*/
function updateIsEffective(id,value) {
    $.post(ctx +'/customRecomContentList/getCount.json', {
        'recomTempletId' : id
    }, function(data) {
        if(data>0){
            var confirmInfo = null;
            var templeteParam = {};
            var param = {templeteLeve:2};
            if (value == 0) {
                templeteParam = {
                    "isEffective" : 1,
                    "templeteId" : id,

                };
                confirmInfo = "确定要置为上线状态?";

            } else if(value == 1){
                templeteParam = {
                    "isEffective" : 0,
                    "templeteId" : id,

                };
                confirmInfo = "确定要置为下线状态?";
            }
            if (confirm(confirmInfo)) {
                $.post(ctx +"/customRecomTempleteList/updateIsEffective", templeteParam, function(data) {
                        if (data.returnValue  == 0) {
                            alert("修改成功");
                            $("#customRecomContentTable").bootstrapTable('refresh',param);
                        } else {
                            if(data.returnValue  == -1){
                                alert("已经被Tab关联，不能下线");
                            }else{
                                alert("修改失败");
                                $("#customRecomContentTable").bootstrapTable('refresh',param);
                            }}}
                    , "json");
            }
        }else{
            alert("没有关联内容不能上线");
        }
    }, "json");

}
/*复制功能*/
function copy(templeteId1) {
    var templeteId = {
        templeteId : templeteId1
    };

    //alert(templeteId1);
    $.post(ctx +"/customRecomTempleteList/selectByTempleteId", templeteId, function(data) {

           // alert("1");
            data.contentName = data.contentName+"-副本";

                $.post(ctx +"/customRecomContentList/saveCopyContent", data, function(data) {
                    if(data.returnValue == 0) {
                        alert("复制成功");
                        $("#customRecomContentTable").bootstrapTable('refresh', param);
                    }else{
                        alert("复制失败");
                    }
                }  , "json");

    }  , "json");


}
/* 删除按钮 */
function delCustomRecomContent(templeteId) {
    if (!confirm("是否删除？")) {
        return;
    }
    var param = {"templeteLeve":2}
    $.post(ctx +"/customRecomContentList/delete.json", {
        'templeteId' : templeteId
    }, function(data) {
        if (data.returnValue == 0) {
            $.post(ctx + "/customRecomBackupsList/delete", {'templeteId': templeteId}, function(data) {
                    alert( '删除成功！');
                    $("#customRecomContentTable").bootstrapTable('refresh',param);
                    if($("#customRecomContentTable").bootstrapTable('getData').length == 1){
                        $("#customRecomContentTable").bootstrapTable('prevPage');
                    }
            }, "json");



        } else{
                alert( "数据已被关联，取消关联！");
        }
    }, "json");
}
/*查询*/
function searchCustomRecomList() {
    /*var contentName = $("#searchCustomRecomName").val();
    var isEffective = $("#searchContentIseffective").val();
    var opt = {
        url: ctx + "/customRecomTempleteList/getPageList",
        silent: true,
        query:{
            "contentName" : contentName,
            "isEffective" : isEffective,
            "templeteLeve" : 2
        }

    };

    $("#customRecomTempleteTable").bootstrapTable('refresh', opt);*/
    initTable();
}

/*更换模板*/
function contentChangeFaTem() {
    gridsterLayoutSecondClear();
    var fatherId = $("#contentFatherId").val();
    var templeteId = 0;
    $("#templeteId").val(0);
    $('#customRecomConTempleteModal').modal({backdrop: 'static', keyboard: false});
    $("#customRecomConTempleteModal").modal('show');
    getContent(templeteId, fatherId, 0);
}
/*清空ul形状*/
function gridsterLayoutSecondClear() {
    var gridster = $("#gridsterLayoutSecond ul").gridster().data('gridster');
    gridster.remove_all_widgets();
}
/*添加*/
function addCustomRecomConTemplete() {
    document.getElementById("recomTemplete").disabled = false;
    $("#groupName").val("");
    $("#groupTitle").val("");
    $("#contentBackPicImg").attr("src","");
    $("#recomTemplete").val("");
    $("#temId").val("");
    $('#editRecomContentModel').modal({backdrop: 'static', keyboard: false});
    $("#editRecomContentModel").modal('show');
}
/*编辑*/
function editGroup(templeteId,fatherId){
    $.post(ctx +'/customRecomContentList/getCount.json', {
        'recomTempletId' : templeteId
    }, function(data) {
       if(data>0){
           document.getElementById("recomTemplete").disabled = true;
       }else{
           document.getElementById("recomTemplete").disabled = false;
       }
    }, "json");
    $("#recomTemplete").val(fatherId);
    $("#temId").val(templeteId);
    $.post(ctx +'/customRecomTempleteList/getCustomRecomTemplete.json', {
        'templeteId' : templeteId
    }, function(data) {
        if (data != null) {
            $("#groupName").val(data.contentName);
            $("#groupTitle").val(data.templeteTitle);
            $('#contentBackPicImg').attr('src',data.backPic);

            $('#editRecomContentModel').modal({backdrop: 'static', keyboard: false});
            $("#editRecomContentModel").modal('show');
        } else {
            alert( "网络异常！");
        }
    }, "json");

}
//数据提交
function groupSubmit(){
    var groupName = $("#groupName").val();
    var groupTitle = $("#groupTitle").val();
    var backImg = $("#contentBackPicImg")[0].src;
    var fatherId =  $("#recomTemplete").val();
    if(fatherId != null && fatherId !=""){
    $.ajax({
        type:"post",
        async: false,
        url:ctx + "/customRecomTempleteList/getCustomRecomTemplete.json",
        data:{ 'templeteId' : fatherId},
        dataType:"json",
        success:function (data) {
            if (data != null) {
                $("#temName").val(data.templeteName);
                $("#layCount").val(data.layoutCount);

            } else {
                alert( "网络异常！");
            }
        }
    });}

    var templeteId = $("#temId").val();
    if($.trim(groupName) == ""){
        alert("Group名称不能为空");
        return ;
    }

    if($.trim(groupTitle) == ""){
        alert("Group标题名称不能为空");
        return ;
    }
    if($.trim($("#recomTemplete").val()) == "" || $("#recomTemplete").val() == null){
        alert("请选择模板");
        return ;
    }

    var  temName =$("#temName").val();
    var layCount = $("#layCount").val();
    if(templeteId!=null && templeteId!=""){
        var backImg1 = backImg.substring(backImg.length-4,backImg.length + 1);
        if(backImg1 == "list"){
            backImg = null;
        }
      $.post(ctx+"/customRecomTempleteList/update.json", {
            templeteId:templeteId,
            contentName:  groupName,
            templeteTitle:groupTitle,
            backPic:backImg,
            fatherId:fatherId,
            templeteName:temName,
            layoutCount:layCount,

        }, function(data) {
            if (data.returnValue == 0) {
                alert( '更新成功！');
                $("#editRecomContentModel").modal('hide');
                $("#customRecomContentTable").bootstrapTable('refresh',param);
            } else {
                alert( '更新失败！');
            }
        }, "json");

    }else{
     //   alert($("#temName").val());
        var backImg1 = backImg.substring(backImg.length-4,backImg.length + 1);
        if(backImg1 == "list"){
            backImg = null;
        }
        $.post(ctx + '/customRecomTempleteList/save.json', {
            isEffective:0,
            templeteLeve:2,
            contentName:  groupName,
            templeteTitle:groupTitle,
            backPic:backImg,
            fatherId:fatherId,
            templeteName:temName,
            layoutCount:layCount,


        }, function(data) {
            if (data.returnValue == 0) {
                alert( '添加成功！');
                $("#editRecomContentModel").modal('hide');
                $("#customRecomContentTable").bootstrapTable('refresh',param);
            } else {
                alert( '添加失败！请检查所有推荐位内容。');
                /* dialog.dialog('close'); */
            }
        }, "json");
    }

}
/*/!*添加*!/
function addCustomRecomConTemplete() {
    setLayoutSelect();
    $('#submitCustomRecomTemplete').show();
    document.getElementById("contentFatherId").disabled = false;
    var fatherId = $("#contentFatherId").val();
    var templeteId = 0;
    $("#templeteId").val(0);
    $('#customRecomConTempleteName').val('');
    getContent(templeteId, fatherId, 0);
    $('#customRecomConTempleteModal').modal({backdrop: 'static', keyboard: false});
    $("#customRecomConTempleteModal").modal('show');
    
}*/

/*预览*/
function viewTempleteContent(id) {
    $("#nameAndOption").css('display','none');
    document.getElementById("contentFatherId").disabled = true;
    $('#submitCustomRecomTemplete').hide();
    $.post(ctx +'/customRecomTempleteList/getCustomRecomTemplete.json', {
        'templeteId' : id
    }, function(data) {
        if (data != null) {
            $('#customRecomConTempleteName').val(data.contentName);
            $('#contentFatherId').val(data.fatherId);
            $('#templeteId').val(data.templeteId);
            getContent(id, data.fatherId, 1);
            $('#customRecomConTempleteModal').modal({backdrop: 'static', keyboard: false});
            $("#customRecomConTempleteModal").modal('show');
        } else {
            alert( "网络异常！");
        }
    }, "json");
}

/*编辑推荐位*/
function editTempleteContent(id) {
    allTotal = 0;
    record =0;
    $("#nameAndOption").css('display','none');
    $('.modal-body').animate({scrollLeft:0}, 'slow');
    $('#submitCustomRecomTemplete').show();
    document.getElementById("contentFatherId").disabled = true;
    $.post(ctx +'/customRecomTempleteList/getCustomRecomTemplete.json', {
        'templeteId' : id
    }, function(data) {
        if (data != null) {
            $('#customRecomConTempleteName').val(data.contentName);
            $('#contentFatherId').val(data.fatherId);
            $('#templeteId').val(data.templeteId);
            $('#templeteName').val(data.templeteName);
            getContent(id, data.fatherId, 0);
            $('#customRecomConTempleteModal').modal({backdrop: 'static', keyboard: false});
            $("#customRecomConTempleteModal").modal('show');
        } else {
            alert( "网络异常！");
        }
    }, "json");
}

// 页面编辑
function editLayoutContent(id,w,h,r,c) {
    clearCustomContentInfo();
    clearCustomNameInfo();
    $("#layoutId").val(id);
    /*编辑图片大小*/
    var size_y=$('#'+id).attr('data-sizey');
    var size_x=$('#'+id).attr('data-sizex');
    var width = w*r;
    var height = h*c;
    $('#customRecomContentImgSize').html('(图片大小：'+(width - 30)+'X'+(height - 30)+'）');
    $('#customRecomOutcropImgSize').html('(图片大小：'+width+'X'+height+'）');
    if(width == 885 && height == 510){
        $("#playFlag").val(1);
    }else{
        $("#playFlag").val(0);
    }
    $("#isPlay").attr("disabled",false);
    var contentId = $("#contentId" + id).val();
    if (contentId == "null" || contentId == " " || contentId == "undefined") {
        /*$("#contentType").val("1");*/
        //备份按钮隐藏
        $('#backups').css('display','none');
        //跳转提示
        $("#skip").html("跳转状态:不可跳转");
        dataCustomSource();
        $('#editCustomContentModal').modal({backdrop: 'static', keyboard: false});
        $("#editCustomContentModal").modal('show');

    } else {
        if(contentId == -1){
            $("#skip").html("跳转状态:不可跳转");
        }else{
            $("#skip").html("跳转状态:可跳转");
        }
        $('#backups').css('display','inline');
        var contentType = $("#contentType" + id).val();
        var contentSubtitle = $("#contentSubtitle" + id).val();
        var contentName = $("#contentName" + id).val();
        var contentImg = $("#contentImg" + id).val();
        var customContentImgSel = $("#customContentImgSel" + id).val();
        var contentOutcropImg = $("#contentOutcropImg" + id).val();
        var contentBigPic = $("#contentBigPic" + id).val();
        var categoryId = $("#categoryId" + id).val();
        var chnId = $("#chnId" + id).val();
        var chnType = $("#chnType" + id).val();
        var hotId = $("#hotId" + id).val();
        var hotType = $("#hotType" + id).val();
        var aqyIsVip = $("#aqyIsVip" + id).val();
        var apkBagName = $("#apkBagName" + id).val();
        var specSn = $("#specSn" + id).val();
        var apkVersionCode = $("#apkVersionCode" + id).val();
        var apkDownUrl = $("#apkDownUrl" + id).val();
        var videoId = $("#videoId" + id).val();
        var apk = $("#apk" + id).val();
        var title = $("#span" + id).html();
        var isPlay = $("#isPlay" + id).val();
        var videoUrl = $("#videoUrl" + id).val();
        var installStyle = $("#installStyle" + id).val();
        var appType = $("#appType" + id).val();
        var action = $("#action" + id).val();
        if(isPlay == 0){
            $('#t_1').css('display','block');
            $('#t_2').css('display','block');
            $('#t_3').css('display','block');
            $('#t_4').css('display','block');
            $('#s').css('display','none');
           s=0;
        }else{
            $('#t_1').css('display','none');
            $('#t_2').css('display','none');
            $('#t_3').css('display','none');
            $('#t_4').css('display','none');
            $('#s').css('display','block');
            s=1;
        }


        $("#customContentTitle").val(contentName);
        $("#customContentId").val(contentId);
        $("#customContentType").val(contentType);
        $("#title").val(title);
        $("#customContentSubtitle").val(contentSubtitle);
        $("#customContentImgSel").val(customContentImgSel);
        $("#customRecomContentImg").attr("src", contentImg);
        $("#customRecomOutcropImg").attr("src", contentOutcropImg);
        $("#contentBigPic").attr("src", contentBigPic);
        $("#categoryId").val(categoryId);
        $("#chnId").val(chnId);
        $("#chnType").val(chnType);
        $("#hotId").val(hotId);
        $("#hotType").val(hotType);
        $("#aqyIsVip").val(aqyIsVip);
        $("#apkBagName").val(apkBagName);
        $("#specSn").val(specSn);
        $("#apkVersionCode").val(apkVersionCode);
        $("#apkDownUrl").val(apkDownUrl);
        $("#videoId").val(videoId);
        $("#apk").val(apk);
        $("#isPlay").val(isPlay);
        $("#videoUrl").val(videoUrl);
        $("#installStyle").val(installStyle);
        $("#appType").val(appType);
        $("#action").val(action);
        dataCustomSource();
        $('#editCustomContentModal').modal({backdrop: 'static', keyboard: false});
        $("#editCustomContentModal").modal('show');

    }
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
function  clearPic() {
    $("#customRecomOutcropImg").attr("src", null);// 清除
}
/*页面保存*/
function customContentSubmit() {
    var contentId = $("#customContentId").val();
    if(contentId == "" || contentId == null ){
        contentId = -1;
    }
    var contentType =  $("#customContentType").val();
    if(contentType != 12){
        $.ajax({
            async : false,
            type : "get",
            url : ctx+"/jumpInstruction/getActionById",
            dataType : "json",
            data : {"id":contentType},
            success : function(jsonData) {
                var dat = jsonData;
                if(dat == null){
                    $("#action").val('')
                }else{
                    $("#action").val(dat.actionName);
                }
            }
        });
    }
    var action = $("#action").val();
    if (contentId != -1 && (action == null || action == "")) {
        alert( '请检查跳转action是否配置！');
        return;
    }
    var templeteId =  $("#templeteId").val();
    var layoutId = $("#layoutId").val();
    var title = $("#title").val();
    var contentName = $("#customContentTitle").val();
    var contentSubtitle = $("#customContentSubtitle").val();
    var contentImg = $("#customRecomContentImg")[0].src;
    var contentOutcropImg = $("#customRecomOutcropImg")[0].src;
    var contentBigPic = $("#contentBigPic")[0].src;
    var contentImgUrl = contentImg.substring(contentImg.length-4,contentImg.length + 1);
    if(contentImgUrl == "list"){
        contentImg = null;
    }
    var contentOutcropImgUrl = contentOutcropImg.substring(contentOutcropImg.length-4,contentOutcropImg.length + 1);
    if(contentOutcropImgUrl == "list"){
        contentOutcropImg = null;
    }
    var contentBigPicUrl = contentBigPic.substring(contentBigPic.length-4,contentBigPic.length + 1);
    if(contentBigPicUrl == "list"){
        contentBigPic = null;
    }
    //特殊处理原图和露头图逻辑
    if(contentOutcropImg == null ||contentOutcropImg ==""){
        $("#customContentImgSel").val(1);
    }else{
        $("#customContentImgSel").val(2);
    }
    var customContentImgSel = $("#customContentImgSel").val();
    var categoryId = $("#categoryId").val();
    var chnId = $("#chnId").val();
    var chnType = $("#chnType").val();
    var hotId = $("#hotId").val();
    var hotType = $("#hotType").val();
    var aqyIsVip = $("#aqyIsVip").val();
    var apkBagName = $("#apkBagName").val();
    var specSn = $("#specSn").val();
    var apkVersionCode = $("#apkVersionCode").val();
    var apkDownUrl = $("#apkDownUrl").val();
    var videoId = $("#videoId").val();
    var videoUrl = $("#videoUrl").val();
    var apk = $("#apk").val();
    var isPlay = $("#isPlay").val();
    var installStyle =$("#installStyle").val();
    var appType =$("#appType").val();
    if(isPlay == 0){
        videoUrl = "";
    }
    if(isPlay == 1 && (videoUrl ==null || videoUrl == "")){
        alert( '视频地址不能为空！');
        return;
    }
    if(isPlay == 1&&s==0){
        allTotal=allTotal+1;
    }
    if(isPlay == 0&&s==1){
        record=1;
        allTotal=allTotal-1;
    }
    if(isPlay == 1 && allTotal >=2){
        alert( '同一tab或同一group都不可有2个视频模块！');
        allTotal--;
        return;
     }
    var customContentImg = null;
    var count = 0;
    if(customContentImgSel == 1){
        customContentImg = contentImg;
    }else{
        customContentImg = contentOutcropImg;
    }

   /* $.ajax({
        async : false,
        type : "post",
        url : ctx+"/customRecomContentList/getCount",
        dataType : "json",
        data : {"contentId":contentId,
            "contentType":contentType,
            "recomTempletId":templeteId},
        success : function(jsonData) {
            count = jsonData;

        }

    });*/
    /*var editBoolean = contentId != $("#contentId" + layoutId).val() && contentType!= $("#contentType" + layoutId).val();
     if ((count > 0 && editBoolean) || (count >1 && !editBoolean)) {
     $.messager.alert( '无法关联重复内容！');
     return;
     }*/
    //需求改为图片也可以不传，注释
    // if (isPlay == 0 &&(contentImg == null || contentImg == "")) {
    //     alert( '原图不能为空！');
    //     return;
    // }

    // if (customContentImgSel == null || customContentImgSel == "") {
    //     $.messager.alert( '请选择显示图片！');
    //     return;
    // }
    var flag = true;
    if(contentId != -1) {
        if ($("#contentId" + layoutId).val() != contentId) {
            for (var key in templetContentMap) {
                if (templetContentMap[key] == contentId) {
                    flag = false;
                    break;
                }
            }
        } else {
            var flagcount = 0;
            for (var key in templetContentMap) {
                if (templetContentMap[key] == contentId) {
                    flagcount = flagcount + 1;
                }
            }
            if (flagcount > 1) {
                flag = false;
            }
        }
    }
    //注释关联重复内容
    // if (!flag) {
    //     alert( '无法关联重复内容！');
    //     return;
    // }


    $("#contentName" + layoutId).val(contentName);
    $("#" + layoutId)
        .attr(
            "style",
            "background: url("
            + customContentImg
            + ") center 0 no-repeat;background-size:cover;color:white;");
    var playFlag = $("#playFlag").val();//检查下是否
    var flag = true;
    if(playFlag == 1 && isPlay == 1 && s==0){
        $.ajax({
            async:false,
            type: "post",
            url: ctx + "/tabGroup/checkVideo",
            dataType: "json",
            data: {
                "belongGroupId": templeteId
            },
            success: function (jsonData) {
               if(!jsonData){
                   if(record !=1){
                       alert( '同一tab下不可关联多个视频group数据！');
                       flag = jsonData;
                   }
               }
            }
        });
    }
    if(flag){
        // alert("不可继续走");
        $("#contentImg" + layoutId).val(contentImg);
        $("#span" + layoutId).html(title);
        $("#contentType" + layoutId).val(contentType);
        $("#span" + layoutId).html(contentName);
        $("#contentSubtitle" + layoutId).val(contentSubtitle);
        $("#contentOutcropImg" + layoutId).val(contentOutcropImg);
        $("#contentBigPic" + layoutId).val(contentBigPic);
        $("#contentId" + layoutId).val(contentId);
        $("#customContentImgSel" + layoutId).val(customContentImgSel);
        $("#categoryId" + layoutId).val(categoryId);
        $("#chnId" + layoutId).val(chnId);
        $("#chnType" + layoutId).val(chnType);
        $("#hotId" + layoutId).val(hotId);
        $("#hotType" + layoutId).val(hotType);
        $("#aqyIsVip" + layoutId).val(aqyIsVip);
        $("#apkBagName" + layoutId).val(apkBagName);
        $("#specSn" + layoutId).val(specSn);
        $("#apkVersionCode" + layoutId).val(apkVersionCode);
        $("#apkDownUrl" + layoutId).val(apkDownUrl);
        $("#videoId" + layoutId).val(videoId);
        $("#videoUrl" + layoutId).val(videoUrl);
        $("#action" + layoutId).val(action);
        $("#apk" + layoutId).val(apk);
        $("#isPlay" + layoutId).val(isPlay);
        $("#installStyle" + layoutId).val(installStyle);
        $("#appType" + layoutId).val(appType);
        templetContentMap[layoutId] = contentId;
        $("#editCustomContentModal").modal('hide');
    }
}
// 提交
function submitCustomRecomContent() {
    var templeteName = $("#contentFatherId option:selected").text();
    var templeteId = $("#templeteId").val();
    var contentName = $("#customRecomConTempleteName").val();
    var fatherId = $("#contentFatherId").val();
    var dialog = $('#customRecomConTempleteModal');
    var gridster = $("#gridsterLayoutSecond ul").gridster().data('gridster');
    var table = $('#customRecomContentTable');
    var json = gridster.serialize();// 得到 所有的 widget
    var layoutJson = JSON.stringify(json);

    // 判断页面填写是否为空
    if (contentName == null || contentName == "") {
        alert( '名称不能为空！');
        return;
    }
    var flag = true;
    for(var key in templetContentMap){
        if(templetContentMap[key] == "" || templetContentMap[key] == null  || templetContentMap[key] == undefined){
            flag = false;
            break;
        }
    }
    if (!flag) {
       alert( '请检查模板内容是否有空缺！');
        return;
    }

    if (templeteId == "" || templeteId == null || templeteId == 0) {
        $.post(ctx + '/customRecomContentList/save.json', {

            'templeteName' : templeteName,
            'contentName' : contentName,
            'fatherId' : fatherId,
            'layoutJson' : layoutJson,

        }, function(data) {
            if (data.returnValue == 0) {
                alert( '添加成功！');
                dialog.modal('hide');
                table.bootstrapTable('refresh',param);
            } else {
                alert( '添加失败！请检查所有推荐位内容。');
                /* dialog.dialog('close'); */
            }
        }, "json");
    } else {
        $.post(ctx+"/customRecomContentList/update.json", {

            'templeteName' : templeteName,
            'contentName' : contentName,
            'fatherId' : fatherId,
            'templeteId' : templeteId,
            'layoutJson' : layoutJson,

        }, function(data) {
            if (data.returnValue == 0) {
                alert( '更新成功！');
                dialog.modal('hide');
                table.bootstrapTable('refresh',param);
            } else {
                alert( '更新失败！');
            }
        }, "json");
    }

}

function clearCustomRecomContent() {
    $("#customRecomConTempleteModal").modal('hide');
}

function customContentClose() {
    $("#editCustomContentModal").modal('hide');
}
function  clearInfo() {
    $("#customContentId").val("-1");
    /* $("#layoutId").val("");*/
    $("#title").val("");
    $("#customContentTitle").val("");
    $("#customContentSubtitle").val("");
    $("#customContentImgSel").val(1);
    $("#isPlay").val(0);
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
    $("#customRecomContentImgUrl").val("");
    $("#customRecomOutcropImgUrl").val("");
    $('#customRecomContentImg').attr('src', null);
    $('#customRecomOutcropImg').attr('src', null);
    //备份按钮隐藏
    $('#backups').css('display','none');
    //跳转提示
    $("#skip").html("跳转状态:不可跳转");
}
function clearCustomContentInfo() {
    $("#customContentId").val("");
   /* $("#layoutId").val("");*/
    $("#title").val("");
    $("#customContentTitle").val("");
    $("#customContentSubtitle").val("");
    $("#customContentImgSel").val(1);
    $("#isPlay").val(0);
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
    $("#customRecomContentImgUrl").val("");
    $("#customRecomOutcropImgUrl").val("");
    $('#customRecomContentImg').attr('src', null);
    $('#customRecomOutcropImg').attr('src', null);
    $('#videoUrl').val("");
    $('#videoId').val("");
    $('#specSn').val("");
}
function clearCustomNameInfo() {
    $('#name_1').val("");
    $('#name_6').val("");
    $('#name_0').val("");
    $('#name_2').val("");
    $('#name_3').val("");
    $('#name_4').val("");
    $('#name_7').val("");
    $('#name_8').val("");
    $('#name_10').val("");
    $('#name_11').val("");
}
function clearLayoutContent(layoutId) {
    $("#contentId" + layoutId).val('');
    $("#contentName" + layoutId).val('');
    $("#" + layoutId)
        .attr(
            "style",
            "background: url("
            + ''
            + ") center 0 no-repeat;background-size:cover;color:white;");

    $("#contentImg" + layoutId).val('');
    $("#span" + layoutId).html('未编辑');
    /*$("#contentType" + layoutId).val('');*/
    $("#contentSubtitle" + layoutId).val('');
    $("#contentOutcropImg" + layoutId).val('');
    $("#contentBigPic" + layoutId).val('');
   /* $("#customContentImgSel" + layoutId).val('');*/
    $("#categoryId" + layoutId).val('');
    $("#chnId" + layoutId).val('');
    $("#chnType" + layoutId).val('');
    $("#hotId" + layoutId).val('');
    $("#hotType" + layoutId).val('');
    $("#aqyIsVip" + layoutId).val('');
    $("#apkBagName" + layoutId).val('');
    $("#specSn" + layoutId).val('');
    $("#apkVersionCode" + layoutId).val('');
    $("#apkDownUrl" + layoutId).val('');
    $("#videoUrl" + layoutId).val('');
    $("#videoId" + layoutId).val('');
    $("#action" + layoutId).val('');
    $("#apk" + layoutId).val('');
    $("#isPlay" + layoutId).val(0);
}

function getCustomMoreList(contentType,url){
    initCustomMoreTable(contentType,url);
}
/*备份*/
function customContentBackups() {
    var contentId = $("#customContentId").val();
    if(contentId == "" ||contentId == null){
        contentId = -1;
    }
    var fatherId = $("#contentFatherId").val();
    var templeteId = $("#templeteId").val();
    var contentType =  $("#customContentType").val();
    var contentName = $("#customContentTitle").val();
    if(contentId == -1){
        alert( '不可跳转数据，不可进行备份！');
        return ;
    }

    var contentSubtitle = $("#customContentSubtitle").val();
    var customContentImgSel = $("#customContentImgSel").val();
    var contentImg = $("#customRecomContentImg")[0].src;
    var contentOutcropImg = $("#customRecomOutcropImg")[0].src;
    var contentBigPic = $("#contentBigPic")[0].src;
    var categoryId = $("#categoryId").val();
    var chnId = $("#chnId").val();
    var chnType = $("#chnType").val();
    var hotId = $("#hotId").val();
    var hotType = $("#hotType").val();
    var aqyIsVip = $("#aqyIsVip").val();
    var apkBagName = $("#apkBagName").val();
    var specSn = $("#specSn").val();
    var apkVersionCode = $("#apkVersionCode").val();
    var apkDownUrl = $("#apkDownUrl").val();
    var videoId = $("#videoId").val();
    var videoUrl = $("#videoUrl").val();
    var action = $("#action").val();
    var apk =$("#apk").val();
    var isPlay =$("#isPlay").val();
    var installStyle =$("#installStyle").val();
    var appType =$("#appType").val();
    var templeteName =$("#templeteName").val();
    var contentName1 = $("#customRecomConTempleteName").val();
    var layoutId = $("#layoutId").val();
    if(layoutId == null || layoutId == ""){
        alert( '推荐位Id不能为空！');
        return;
    }
    var layoutType = null;
    var size_w = $('#'+layoutId).attr('data-sizex')-0;
    var size_h=$('#'+layoutId).attr('data-sizey')-0;
    if(size_w == 6 && size_h == 6){
        layoutType = 1;
    }else if(size_w == 3 && size_h == 3){
        layoutType = 2;
    }else if(size_w == 6 && size_h == 3){
        layoutType = 3
    }
    var count = 0;

    var contentImgUrl = contentImg.substring(contentImg.length-4,contentImg.length + 1);
    if(contentImgUrl == "list"){
        contentImg = null;
    }
    // if ($.trim(contentName) == "") {
    //     alert( '内容名称不能为空！');
    //     return;
    // }
    //注释提示信息
    // if(isPlay == 0 && (contentImg == null || contentImg == "")){
    //     alert("请上传图片");
    //     return;
    // }
    // var bool = contentOutcropImg.endsWith(".jpg");
    // if(bool == false){
    //     return ;
    // }
    // alert(bool);
    var contentOutcropImgUrl = contentOutcropImg.substring(contentOutcropImg.length-4,contentOutcropImg.length + 1);
    if(contentOutcropImgUrl == "list"){
        contentOutcropImg = null;
    }
    var contentBigPicUrl = contentBigPic.substring(contentBigPic.length-4,contentBigPic.length + 1);
    if(contentBigPicUrl == "list"){
        contentBigPic = null;
    }
/**
    $.ajax({
        async : false,
        type : "post",
        url : ctx+"/customRecomBackupsList/getCount",
        dataType : "json",
        data : {"contentId":contentId,
            "contentType":contentType,"videoId":videoId,"templeteId":templeteId},
        success : function(jsonData) {
            count = jsonData;
        }
    });

    if(count > 0){
        alert("该内容已存在，请重新编辑！");
        return;
    }
**/
    var param = {};

    var backupsPparam = {
        'contentId':contentId,
        'contentType' : contentType,
        'contentName' : contentName,
        'contentSubtitle' : contentSubtitle,
        'customContentImgSel' : customContentImgSel,
        'contentImg' : contentImg,
        'contentOutcropImg' : contentOutcropImg,
        'contentBigPic' : contentBigPic,
        'categoryId' : categoryId,
        'chnId' : chnId,
        'chnType' : chnType,
        'hotId' : hotId,
        'hotType' : hotType,
        'aqyIsVip' : aqyIsVip,
        'apkBagName' : apkBagName,
        'layoutType' : layoutType,
        'specSn':specSn,
        'apkVersionCode':apkVersionCode,
        'apkDownUrl':apkDownUrl,
        'videoId':videoId,
        'videoUrl':videoUrl,
        'action':action,
        'apk':apk,
        'isPlay':isPlay,
        'templeteName':contentName1,
        'templeteId':templeteId,
        'layoutId' : layoutId,
        'installStyle':installStyle,
        'appType':appType
    };
    if(contentId !=null && contentId !=''){
        $.post(ctx + "/customRecomBackupsList/save", backupsPparam, function(data) {
            if (data.returnValue == 0) {
                alert( '备份成功！');
                $("#editCustomContentModal").modal('hide');
                clearLayoutContent(layoutId);
                templetContentMap[layoutId] = null;
            } else {
                alert( '备份失败！');
            }
        }, "json");

    }
    
}
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

            if (file_id == "logoLicenseImg") {
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
            var imgId = '#' + file_id;
            $(imgId).attr("src", "");// 预览
        }
    });
}

