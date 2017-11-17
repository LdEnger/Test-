/**
 * Created by user on 2017/7/10.
 */
$(document).ready(function () {
    $("#searchLogoLicenseName").val("");
    $("#searchLogoLicenseState").val("");
    initLogoTable();
    initUploadJs("logoLicenseImg");
});

function initLogoTable() {
    var logoName = $("#searchLogoLicenseName").val();
    var isOnline = $("#searchLogoLicenseState").val();
    //先销毁表格
    $('#logoLicenseManageTable').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#logoLicenseManageTable").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/logoManage/getPageList", //获取数据的Servlet地址
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
                logoName:logoName,
                isOnline:isOnline,
                page: params.pageNumber,
                rows: params.pageSize
            };
            return param;
        },
        columns : [ [ {
            field : 'logoId',
            title : 'ID',
            width : '20%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        },{
            field : 'logoName',
            title : 'logo名称',
            width : '20%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        }, {
            field : 'logoImg',
            title : '图片',
            width : '20%',
            align : 'center',
            formatter : function(value) {
                return "<img style='width:60px;height:60px;' src='"
                    + value + "' />";
            }
        }, {
            field : 'isOnline',
            title : '状态',
            width : '20%',
            align : 'center',
            formatter : function(value, row) {
                var str = '';
                if(value == 1){
                    str += '<button style="color:#fff;background:rgb(46,130,255);cursor:default;border-radius:3px 0 0 3px;border-top:1px solid #808080;border-bottom:1px solid #808080;border-left:1px solid #808080;height:26px;border-right:0">上线</button>';
                    str += '<a href="javascript:downLogoLicenseManage(' + row.logoId + ')" style="color:rgb(255,102,0);display: inline-block;height:24px;width:40px;border-top:1px solid #808080;border-bottom:1px solid #808080;border-right:1px solid #808080;vertical-align: -1px;line-height: 24px;text-decoration: none;border-radius:0 3px 3px 0">下线</a>';
                }else {
                    str += '<a href="javascript:upLogoLicenseManage('+ row.logoId +')" style="color:rgb(255,102,0);display: inline-block;height:24px;width:40px;border-top:1px solid #808080;border-bottom:1px solid #808080;border-left:1px solid #808080;vertical-align: -1px;line-height: 24px;text-decoration: none;border-radius:3px 0 0 3px">上线</a>';
                    str += '<button style="color:#fff;background:rgb(46,130,255);cursor:default;border-radius:0 3px 3px 0;border-top:1px solid #808080;border-bottom:1px solid #808080;border-right:1px solid #808080;height:26px;border-left:0">下线</button>';
                }
                return str;
            }
        }, {
            field : 'do',
            title : '操作',
            width : '20%',
            align : 'center',
            formatter : function(value, row) {
                var str = '';
                str += '<a href="javascript:editLogoLicesneManage('+ row.logoId +')">编辑</a>';
                str += '&nbsp;|&nbsp; <a href="javascript:delLogoLicenseManage(' + row.logoId + ')">删除</a>';
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

function addLogoLicenseManage() {
    $("#logo_license_id").val("");
    $('#logo_license_name').val("");
    $('#logoLicenseImg').attr('src', '');
    $("#logoState").val('0');
    $('#editLogoLicenseManageDiv').modal({backdrop: 'static', keyboard: false});
    $('#editLogoLicenseManageDiv').modal('show');

}

function closeEditLogoLicenseManageDiv() {
    $('#logo_license_name').val("");
    $("#logo_license_id").val("");
    $("#logoState").val("");
    $("#editLogoLicenseManageDiv").modal('hide');

}

function submitLogoLicenseManageInfo() {
    var logoId = $("#logo_license_id").val();
    var logoName = $("#logo_license_name").val();
    var logoImg = $('#logoLicenseImg')[0].src;
    var logoState =  $("#logoState").val();

    var logoImgUrl = logoImg.substring(logoImg.length-4,logoImg.length + 1);

    if ($.trim(logoName) == "") {
        alert( 'LOGO牌照名称不能为空！');
        return;
    }

    if(logoImgUrl == "list"){
        alert("请上传图片");
        return;
    }
    var param = {};

    var logo_param = {
        'logoId':logoId,
        'logoName' : logoName,
        'logoImg' : logoImg,
        'isOnline' : logoState
    };
    if(logoId !=null && logoId !=''){
        $.post(ctx + "/logoManage/update", logo_param, function(data) {
            if (data.returnValue == 0) {
                alert( '修改成功！');
                $("#editLogoLicenseManageDiv").modal('hide');
                $("#logoLicenseManageTable").bootstrapTable('refresh', param);
            } else {
                alert( '修改失败！');
            }
        }, "json");

    }else{
        $.post(ctx + "/logoManage/save", logo_param, function(data) {
            if (data.returnValue == 0) {
                alert( '新增成功！');
                $("#editLogoLicenseManageDiv").modal('hide');
                $("#logoLicenseManageTable").bootstrapTable('refresh', param);
            } else {
                alert( '新增失败！');
            }
        }, "json");
    }

}

function editLogoLicesneManage(id) {
    $("#logo_license_id").val(id);
    $.post(ctx + "/logoManage/getLogo", {"logoId":id}, function(data) {
        $('#logo_license_name').val(data.logoName);
        $('#logoLicenseImg').attr('src', data.logoImg);
        $("#logoState").val(data.isOnline);
    }, "json");
    $('#editLogoLicenseManageDiv').modal({backdrop: 'static', keyboard: false});
    $('#editLogoLicenseManageDiv').modal('show');
}

function delLogoLicenseManage(logoId) {
    var param = {};
    if (confirm("是否确认删除？")) {
        var logo_param = {
            'logoId' : logoId
        };
        $.post(ctx + "/logoManage/delete", logo_param, function(data) {
            if (data.returnValue == 0) {
                alert( '删除成功！');
                $("#editLogoLicenseManageDiv").modal('hide');
                $("#logoLicenseManageTable").bootstrapTable('refresh', param);
                if($("#logoLicenseManageTable").bootstrapTable('getData').length == 1){
                    $("#logoLicenseManageTable").bootstrapTable('prevPage');
                }
            }else if (data.returnValue == 1){
                alert( '数据被launcher关联，不可删除！');
            }else {
                alert( '删除失败！');
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
            alert(errMsg);
        }
    });
}

function searchLogoLicenseInfo() {
    initLogoTable();
    /*var logoName = $("#searchLogoLicenseName").val();
    var isOnline = $("#searchLogoLicenseState").val();
    var opt = {
        url: ctx + "/logoManage/getPageList",
        silent: true,
        query:{
            logoName:logoName,
            isOnline:isOnline
        }
    };

    $("#logoLicenseManageTable").bootstrapTable('refresh', opt);*/


}

function downLogoLicenseManage(id) {
    logo_param = {logoId : id,isOnline:'0'};
    var param = {};
    $.post(ctx + "/logoManage/update", logo_param, function(data) {
        if (data.returnValue == 0) {
            alert( '下线成功！');
            $("#editLogoLicenseManageDiv").modal('hide');
            $("#logoLicenseManageTable").bootstrapTable('refresh', param);
        } else {
            alert( '下线失败！');
        }
    }, "json");
}

function upLogoLicenseManage(id) {
    logo_param = {logoId : id,isOnline:'1'};
    var param = {};
    console.info(logo_param);
    $.post(ctx + "/logoManage/update", logo_param, function(data) {
        if (data.returnValue == 0) {
            alert( '上线成功！');
            $("#editLogoLicenseManageDiv").modal('hide');
            $("#logoLicenseManageTable").bootstrapTable('refresh', param);
        } else {
            alert( '上线失败！');
        }
    }, "json");
}