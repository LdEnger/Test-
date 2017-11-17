$(document).ready(function () {
    $("#searchBackupsName").val("");
    $("#searchBackupsLayoutType").val("");
    initBackupsTable();
    initCustomCombobox();
    initUploadJs("customRecomContentImg");
    initUploadJs("customRecomOutcropImg");
});

function initBackupsTable() {
    var backupsName = $("#searchBackupsName").val();
    var backupsType = $("#searchBackupsLayoutType").val();
    //先销毁表格
    $('#recomBackupsContentTable').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#recomBackupsContentTable").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/customRecomBackupsList/getPageList", //获取数据的Servlet地址
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
                templeteName:backupsName,
                layoutType:backupsType,
                page: params.pageNumber,
                rows: params.pageSize
            };
            return param;
        },
        columns : [ [ {
            field : 'id',
            title : 'ID',
            width : '10%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        },{
            field : 'templeteName',
            title : 'Group名称',
            width : '15%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        },{
            field : 'contentName',
            title : '主标题',
            width : '15%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        }, {
            field : 'contentImg',
            title : '图片',
            width : '20%',
            align : 'center',
            formatter : function(value) {
                return "<img style='width:60px;height:60px;' src='"
                    + value + "' />";
            }
        },{
            field : 'layoutId',
            title : '推荐位尺寸',
            width : '15%',
            align : 'center',
            formatter : function(value, row, index) {
                var a= null;
                var b= null;
                $.ajax({
                    type:"post",
                    async: false,
                    /*$.post(ctx +'/customRecomTempleteList/getLayout.json', {
                        'templeteId' : templeteId
                    },*/

                    url:ctx +'/customRecomBackupsList/selectRowCol',
                    data:{'layoutId' : row.layoutId,'id':row.id},
                    dataType:"json",
                    success:function (data) {
                        if(data.length == 0){
                            a="";
                            b="";
                            return "";
                        }
                        a = data.row*data.layoutW+"*";
                            b = data.col*data.layoutH;

                    }
                });
                return a+b;
            }
        }, /*{
            field : 'layoutType',
            title : '状态',
            width : '20%',
            align : 'center',
            formatter : function(value, row, index) {
                var str = '';
                if(value == 1){
                    str = '大图';
                }else if(value == 2){
                    str = '竖图';
                }else if(value == 3){
                    str = '横图';
                }
                return str;
            }
        }, */{
            field : 'seqDo',
            title : '顺序操作',
            width : '13%',
            align : 'center',
            formatter :function (value,row,index) {
                var str = "";
                if(true){
                    str += '<a href="javascript:stickFixedRecomment(' + row.id+"," + row.seq +')">置顶</a>';
                    str += '&nbsp;|&nbsp; <a href="javascript:upEntrance(' + row.id +"," + row.seq +')">上移</a>'
                    str += '&nbsp;| <a href="javascript:downEntrance('+ row.id + "," + row.seq +')">下移</a>'
                }
                return str;
            }
        },{
            field : 'do',
            title : '操作',
            width : '20%',
            align : 'center',
            formatter : function(value, row) {
                var str = '';
                str += '<a href="javascript:editBackupsContent('+ row.id +','+row.layoutId+')">编辑</a>';
                str += '&nbsp;|&nbsp; <a href="javascript:delBackupsContent(' + row.id + ')">删除</a>';
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

//置顶
function stickFixedRecomment(id,seq) {
    var maxSeq = null;
    $.post(ctx + "/customRecomBackupsList/getMaxSeq",  function(data) {
        if(seq == data){
                alert("已经置顶");
        }else {

            maxSeq = parseInt(data) + 1;
            var content = {
                'id': id,
                'seq': maxSeq,
            };
            $.post(ctx + "/customRecomBackupsList/update", content, function (data) {
                if (data.returnValue == 0) {
                    alert('置顶成功！');
                    $("#recomBackupsContentTable").bootstrapTable('refresh', param);
                } else {
                    alert('置顶失败！');
                }
            }, "json");
        } }, "json");
}
//上移
function upEntrance(id,seq) {
    var mapping = {
        'id': id,
        'seq': seq,
    }
    $.post(ctx + "/customRecomBackupsList/getMaxMapping",mapping,function (data) {
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
            $.post(ctx + "/customRecomBackupsList/update", mapping1, function (data) {
                if (data.returnValue == 0) {
                    $.post(ctx + "/customRecomBackupsList/update", mapping2, function (data) {
                        if (data.returnValue == 0) {
                            alert("上移成功");
                            $("#recomBackupsContentTable").bootstrapTable('refresh', param);
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
function downEntrance(id,seq) {
    var mapping = {
        'id': id,
        'seq': seq,
    };
    $.post(ctx + "/customRecomBackupsList/getMinMapping",mapping,function (data) {
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
            $.post(ctx + "/customRecomBackupsList/update", mapping1, function (data) {

                if (data.returnValue == 0) {
                    $.post(ctx + "/customRecomBackupsList/update", mapping2, function (data) {
                        if (data.returnValue == 0) {
                            alert("下移成功");
                            $("#recomBackupsContentTable").bootstrapTable('refresh', param);
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

function clearCustomNameInfo() {
    $('#name_1').val("");
    $('#name_6').val("");
    $('#name_0').val("");
    $('#name_2').val("");
    $('#name_3').val("");
    $('#name_4').val("");
    $('#name_7').val("");
}
function customContentClose() {
    $("#editCustomContentModal").modal('hide');
}

function getCustomMoreList(contentType,url){
    initCustomMoreTable(contentType,url);
}

function submitBackupsContentInfo() {
    var id =  $("#id").val();
    var contentId = $("#customContentId").val();
    var contentType =  $("#customContentType").val();
    var contentName = $("#customContentTitle").val();
    var contentSubtitle = $("#customContentSubtitle").val();
    var customContentImgSel = $("#customContentImgSel").val();
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
    var templeteId =$("#templeteId").val();
    var isPlay =$("#isPlay").val();
    var count = 0;
    var contentImgUrl = contentImg.substring(contentImg.length-4,contentImg.length + 1);

    // if ($.trim(contentName) == "") {
    //     alert( '内容名称不能为空！');
    //     return;
    // }

    if(isPlay == 0 &&contentImg ==null){
        alert("请上传图片");
        return;
    }
    $.ajax({
        async : false,
        type : "post",
        url : ctx+"/customRecomBackupsList/getCount",
        dataType : "json",
        data : {"contentId":contentId,
            "contentType":contentType,"templeteId":templeteId},
        success : function(jsonData) {
            count = jsonData;
        }
    });

    if(count > 1){
        alert("该内容已存在，请重新编辑！");
        return;
    }

    var param = {};

    var backupsPparam = {
        'id':id,
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
        'specSn':specSn,
        'apkVersionCode':apkVersionCode,
        'apkDownUrl':apkDownUrl,
        'videoId':videoId,
        'videoUrl':videoUrl,
        'action':action,
        'apk':apk
    };
    if(id !=null && id !=''){
        $.post(ctx + "/customRecomBackupsList/update", backupsPparam, function(data) {
            if (data.returnValue == 0) {
                alert( '修改成功！');
                $("#editCustomContentModal").modal('hide');
                $("#recomBackupsContentTable").bootstrapTable('refresh', param);
            } else {
                alert( '修改失败！');
            }
        }, "json");

    }

}

function editBackupsContent(id,layoutId) {
    $("#id").val(id);
    $.post(ctx + "/customRecomBackupsList/get", {"id":id}, function(data) {
        $("#id").val(id);
        $("#customContentId").val(data.contentId);
        $("#customContentType").val(data.contentType);
        $("#customContentTitle").val(data.contentName);
        $("#customContentSubtitle").val(data.contentSubtitle);
        $("#customContentImgSel").val(data.customContentImgSel);
        $("#customRecomContentImg").attr("src", data.contentImg);
        $("#customRecomOutcropImg").attr("src", data.contentOutcropImg);
        $("#contentBigPic").attr("src", data.contentBigPic);
        $("#categoryId").val(data.categoryId);
        $("#chnId").val(data.chnId);
        $("#chnType").val(data.chnType);
        $("#hotId").val(data.hotId);
        $("#hotType").val(data.hotType);
        $("#aqyIsVip").val(data.aqyIsVip);
        $("#apkBagName").val(data.apkBagName);
        $('#specSn').val(data.specSn);
        $('#apkVersionCode').val(data.apkVersionCode);
        $('#apkDownUrl').val(data.apkDownUrl);
        $('#videoId').val(data.videoId);
        $('#apk').val(data.apk);
        $('#videoUrl').val(data.videoUrl);
        $('#templeteId').val(data.templeteId);//模板
        $('#isPlay').val(data.isPlay);
        if(data.isPlay == 1){
            $('#t_1').css('display','none');
            $('#t_2').css('display','none');
            $('#t_3').css('display','none');
            $('#t_4').css('display','none');
            $('#s').css('display','block');
        }else{
            $('#t_1').css('display','block');
            $('#t_2').css('display','block');
            $('#t_3').css('display','block');
            $('#t_4').css('display','block');
            $('#s').css('display','none');
        }
        var width= null;
        var height= null;
        $.ajax({
            type:"post",
            async: false,
            url:ctx +'/customRecomBackupsList/selectRowCol',
            data:{'layoutId' : layoutId,'id':id},
            dataType:"json",
            success:function (data) {
                if(data.length == 0){
                    a="";
                    b="";
                    return "";
                }
                width = data.row*data.layoutW;
                height = data.col*data.layoutH;
                $('#customRecomContentImgSize').html('(图片大小：'+(width -30)+'X'+(height -30)+'）');
                $('#customRecomOutcropImgSize').html('(图片大小：'+width+'X'+height+'）');
                if(width == 885 && height == 510){
                    $("#playFlag").val(1);
                }else{
                    $("#playFlag").val(0);
                }
            }
        });

        dataCustomSource();
    }, "json");
    $('#editCustomContentModal').modal({backdrop: 'static', keyboard: false});
    $('#editCustomContentModal').modal('show');
}

function delBackupsContent(id) {
    var param = {};
    if (confirm("是否确认删除？")) {
        var backups_param = {
            'id' : id
        };
        $.post(ctx + "/customRecomBackupsList/delete", backups_param, function(data) {
            if (data.returnValue == 0) {
                alert( '删除成功！');
                $("#editCustomContentModal").modal('hide');
                $("#recomBackupsContentTable").bootstrapTable('refresh', param);
                if($("#recomBackupsContentTable").bootstrapTable('getData').length == 1){
                    $("#recomBackupsContentTable").bootstrapTable('prevPage');
                }
            }
        }, "json");
    }
}

// 上传图片
function initUploadJs(file_id) {
    var file_upload = '#' + file_id + 'Url';
    $(file_upload).uploadify({
        'swf' :ctx +  '/res/js/plugin/uploadify/uploadify.swf',
        'uploader' :ctx +  '/upload/init.json', // fileUpload/newIcpUpload.json
        'height' : 25,
        'width' : 130,
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

            if (file_id == "customRecomContentImg" || file_id == "customRecomOutcropImg" ) {
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
            $(imgId).attr("src", "");
        }
    });
}

function searchBackupsInfo() {
    initBackupsTable();


}
