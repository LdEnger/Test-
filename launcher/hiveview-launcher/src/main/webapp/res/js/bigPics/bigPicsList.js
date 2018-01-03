/**
 * Created by Administrator on 2017/7/10.
 */

$(document).ready(function () {
    initTable();
/*    init();*/
    initUploadJs("recommendContentImg");
});
var param ={};

function initTable(imgName) {
    //先销毁表格
    $('#sample_1').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#sample_1").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/bigPics/getList", //获取数据的Servlet地址
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
            var imgName = $("#imgNameLike").val();
            param = {
                page: params.pageNumber,
                rows: params.pageSize,
                imgName: imgName
            };
            return param;
        },
        columns : [ [ {
            field : 'imgId',
            title : 'ID',
            width : '8%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        },{
            field : 'imgName',
            title : '大图名称',
            width : '13%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        },
        {
            field : 'imgAddr',
            title : '图片',
            width : '13%',
            align : 'center',
            formatter : function(value) {
                return "<img style='height:80px;width:100px;' src='" + value + "'/>";
            }
        }, {
            field : 'do',
            title : '操作',
            width : '20%',
            align : 'center',
            formatter : function(value, row) {
                var str = '';
                str += '<a href="javascript:edit('+row.imgId+",'"+row.imgName+"','" + row.imgAddr + '\')">编辑</a>';
                if(row.tabBackground == 0){
                str += '&nbsp;|&nbsp;<a href="javascript:tabBackgroup(' + row.imgId + "," + row.tabBackground + ')"><span style="color:#7d7d7d">设为Tab背景</a>';
                }else if(row.tabBackground == 1){
                   /* '&nbsp;|&nbsp;<a href="javascript:tabBackgroup(' + row.imgId + "," + row.tabBackground + ')">设为Tab背景</a>'*/
                 /*   str += '&nbsp;|&nbsp;<a href="javascript:tabBackgroup(' + row.imgId + "," + row.tabBackground + ')"><span style="color:#7d7d7d">设为Tab背景</span></a>';*/
                    str += '&nbsp;|&nbsp;<span style="color: #0d638f">设为Tab背景</span>';
                }
                str += '&nbsp;|&nbsp; <a href="javascript:del('+row.imgId+','+ row.tabBackground + ')">删除</a>';
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
/*function init() {
    $("#btn_add").click(function () {
        // $("#myModalLabel").text("桌面图标过滤管理");
        $('#myModal').modal({backdrop: 'static', keyboard: false});
    });
}*/
function  add() {
/*    clear_modal();*/
    $('#myModal').modal({backdrop: 'static', keyboard: false});
    $('#myModalsy').modal({backdrop: 'static', keyboard: false});
}

function clear_modal() {
    $('#txt_bigPicsId').val('');
    $('#txt_bigPicsName').val('');
    $('#recommendContentImg').attr('src','');
    $("#recommendContentImgUrl").val('');
}
function  clear_diaglog(){
    $('#txt_bigPicsId').val('');
    $('#txt_bigPicsName').val('');
    $('#recommendContentImg').attr('src','');
    $("#recommendContentImgUrl").val('');
    $("#myModal").modal('hide');
}
function  edit(imgId,imgName,imgAddr) {
    $('#txt_bigPicsId').val(imgId);
    $('#txt_bigPicsName').val(imgName);
    $("#recommendContentImg").attr("src",imgAddr);
    $("#myModal").modal({backdrop: 'static', keyboard: false});
}
function  del(imgId,tabBackground) {
    if(tabBackground == 1){
        alert("数据已作关联，无法删除");

    }else if (confirm("是否确认删除？")) {
        var bigpics = {
            imgId : imgId
        };
        $.post(ctx + "/bigPics/delete", bigpics, function(data) {
            if (data.returnValue == 0) {
                alert( '成功！');
                $("#myModal").modal('hide');
                $("#sample_1").bootstrapTable('refresh', param);
                if($("#sample_1").bootstrapTable('getData').length == 1){
                    $("#sample_1").bootstrapTable('prevPage');
                }
            }else  if(data.returnValue == 1){
                alert("不能删除,已与其它模块关联");
            }
            else {
                alert( '失败！');
            }
        }, "json");
    }
}
function appIcon_submit() {
    debugger;
    var imgId =$('#txt_bigPicsId').val();
    var bigPicsNames = $('#txt_bigPicsName').val();
    var imgAddr = $('#recommendContentImg')[0].src;
    if ($.trim(bigPicsNames) == "") {
        alert("应用名称不能为空");
        return;
    }
    var img = imgAddr.substring(imgAddr.length-12,imgAddr.length + 1);

    if(img == 'bigPics/list'){
        alert("请上传图片");
        return;
    }
    var bigpiscs = {
        'imgName' : bigPicsNames,
        'imgId' :imgId,
        'imgAddr' : imgAddr
    };
    if(imgId !=null && imgId !=''){
        $.post(ctx + "/bigPics/update", bigpiscs, function(data) {
            if (data.returnValue == 0) {
                alert( '修改成功！');
                $("#myModal").modal('hide');
                clear_diaglog();
                $("#sample_1").bootstrapTable('refresh', param);
            } else {
                alert( '修改失败！');
            }
        }, "json");

    }else{
        $.post(ctx + "/bigPics/save", bigpiscs, function(data) {
            if (data.returnValue == 0) {
                alert( '新增成功！');
                $("#myModal").modal('hide');
                clear_diaglog();
                $("#sample_1").bootstrapTable('refresh', param);
            } else {
                alert( '新增失败！');
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

function bigPicsNameSelect() {

    var imgName = $("#imgNameLike").val();
/*    var opt = {
        url: ctx + "/bigPics/getPageList",
        silent: true,
        query: {
            imgName: imgName
        }
    };
    $("#sample_1").bootstrapTable('refresh', opt);*/
    initTable(imgName);
}

//设为背景Tab图片
function tabBackgroup(imgId,tabBackground) {
    var ppt = {"imgId": imgId,
        "tabBackground" : 0
    };
/*    alert(ppt);*/
    if(imgId != null){
    $.post(ctx + "/bigPics/updateBig",ppt,function (data) {
/*        if(data.returnValue == 0){*/
/*            alert("修改成功");
            /!*  clear_diaglog();*!/*/
            $("#sample_1").bootstrapTable('refresh', param);
/*        } else {
            alert( '修改失败！');
        }*/
    },'json');
        }
    if (tabBackground == 0){
        var tabBack = {
            "imgId" :imgId,
            "tabBackground": 1
        };
        if(confirm("是否设为背景图片")){
            $.post(ctx + "/bigPics/update",tabBack,function (data) {
                if(data.returnValue == 0){
                    alert("修改成功");
                    /*  clear_diaglog();*/
                    $("#sample_1").bootstrapTable('refresh', param);
                } else {
                    alert( '修改失败！');
                }
            },'json');
        }
    }/*else if(tabBackground == 1){
        var tabBack = {
            "imgId" :imgId,
            "tabBackground": 0
        };

        if(confirm("是否取消设为背景图片")){
            $.post(ctx + "/bigPics/update",tabBack,function (data) {
                if(data.returnValue == 0){
                    alert("修改成功");
                    /!*  clear_diaglog();*!/
                    console.log(tabBack);
                    $("#sample_1").bootstrapTable('refresh', param);
                } else {
                    alert( '修改失败！');
                }

            },'json');
        }
    }*/
}