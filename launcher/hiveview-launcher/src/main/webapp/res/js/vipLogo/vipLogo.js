
$(document).ready(function () {
    initTable();
    initUploadJs("vipLogoImg");
});

function initTable() {
    //先销毁表格
    $('#vipLogoTable').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#vipLogoTable").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/vipLogo/getPageList", //获取数据的Servlet地址
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
                page: params.pageNumber,
                rows: params.pageSize
            };
            return param;
        },
        columns : [ [ {
            title : '排序',
            width : '5%',
            align : 'center',
            formatter : function(value, row, index) {
                return ((param.page-1)*param.rows)+index+1;
            }
        },{
            field : 'logoId',
            title : 'ID',
            width : '5%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        },{
            field : 'vipName',
            title : 'VIP名称',
            width : '20%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        }, {
            field : 'vipLogo',
            title : 'vip图片',
            width : '20%',
            align : 'center',
            formatter : function(value) {
                return "<img style='width:60px;height:60px;' src='"
                    + value + "' />";
            }
        }, {
            field : 'seq',
            title : '顺序调整',
            width : '20%',
            align : 'center',
            formatter : function(value,row) {
                var str='';
                str += '<a href="javascript:moveUp('+row.id+","+row.seq+')">上移</a>';
                str += '&nbsp;|&nbsp;<a href="javascript:moveDown('+row.id+","+row.seq+')">下移</a>';
                return str;
            }
        }, {
            field : 'effective',
            title : '状态',
            width : '20%',
            align : 'center',
            formatter : function(value, row) {
                var str = '';
                if(value == 0){
                    str += '<button style="color:#fff;background:rgb(46,130,255);cursor:default;border-radius:3px 0 0 3px;border-top:1px solid #808080;border-bottom:1px solid #808080;border-left:1px solid #808080;height:26px;border-right:0">上线</button>';
                    str += '<a href="javascript:downVipLogo(' + row.id + ')" style="color:rgb(255,102,0);display: inline-block;height:24px;width:40px;border-top:1px solid #808080;border-bottom:1px solid #808080;border-right:1px solid #808080;vertical-align: -1px;line-height: 24px;text-decoration: none;border-radius:0 3px 3px 0">下线</a>';
                }else {
                    str += '<a href="javascript:upVipLogo('+ row.id +')" style="color:rgb(255,102,0);display: inline-block;height:24px;width:40px;border-top:1px solid #808080;border-bottom:1px solid #808080;border-left:1px solid #808080;vertical-align: -1px;line-height: 24px;text-decoration: none;border-radius:3px 0 0 3px">上线</a>';
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
                str += '<a href="javascript:editVipLogo('+row.id+",'"+row.vipName+"','"+row.vipLogo+"','"+row.logoId+"'"+')">编辑</a>';
                str += '&nbsp;|&nbsp; <a href="javascript:delVipLogo(' + row.id + ')">删除</a>';
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
/*添加按钮*/
function addVipLogo() {
    $('#id').val("");
    $('#logoId').val("").removeAttr("readonly");
    $('#vipName').val("")
    $('#vipLogoImg').attr('src', '');
    $('#editVipLogoDiv').modal({backdrop: 'static', keyboard: false});
    $('#editVipLogoDiv').modal('show');
}
/*编辑按钮*/
function editVipLogo(id,vipName,vipLogo,logoId) {
    console.log(id+","+vipName+","+vipLogo);
    $('#id').val(id);
    $('#logoId').val(logoId).attr("readonly","readonly");
    $('#vipName').val(vipName);
    $('#vipLogoImg').attr("src",vipLogo);
    $('#editVipLogoDiv').modal({backdrop: 'static', keyboard: false});
    $('#editVipLogoDiv').modal('show');
}
/*删除按钮*/
function delVipLogo(id) {
    var param = {};
    if (confirm("是否确认删除？")) {
        var logo_param = {
            'id' : id
        };
        $.post(ctx + "/vipLogo/delete", logo_param, function(data) {
            if (data.returnValue == 0) {
                alert( '删除成功！');
                $("#editVipLogoDiv").modal('hide');
                $("#vipLogoTable").bootstrapTable('refresh', param);
                if($("#vipLogoTable").bootstrapTable('getData').length == 1){
                    $("#vipLogoTable").bootstrapTable('prevPage');
                }
            }else {
                alert( '删除失败！');
            }
        }, "json");
    }
}
/*上下线*/
function downVipLogo(id) {
    logo_param = {id : id,effective:'1'};
    var param = {};
    $.post(ctx + "/vipLogo/update", logo_param, function(data) {
        if (data.returnValue == 0) {
            alert( '下线成功！');
            $("#editVipLogoDiv").modal('hide');
            $("#vipLogoTable").bootstrapTable('refresh', param);
        } else {
            alert( '下线失败！');
        }
    }, "json");
}
function upVipLogo(id) {
    logo_param = {id : id,effective:'0'};
    var param = {};
    console.info(logo_param);
    $.post(ctx + "/vipLogo/update", logo_param, function(data) {
        if (data.returnValue == 0) {
            alert( '上线成功！');
            $("#editVipLogoDiv").modal('hide');
            $("#vipLogoTable").bootstrapTable('refresh', param);
        } else {
            alert( '上线失败！');
        }
    }, "json");
}
/*调整位置*/
function moveUp(id,seq) {
    var content={
        'id':id,
        'seq':seq
    };
    $.post(ctx + "/vipLogo/getMaxContent", content, function(data) {
        var content1={
            'id':id,
            'seq':data.seq
        };
        var content2={
            'id':data.id,
            'seq':seq
        };
        $.post(ctx + "/vipLogo/update", content1, function(data) {
            if (data.returnValue == 0) {
                $.post(ctx + "/vipLogo/update", content2, function(data) {
                    if (data.returnValue == 0) {
                        $("#vipLogoTable").bootstrapTable('refresh');
                    } else {
                        alert( '上移失败！');
                    }
                }, "json");
            } else {
                alert( '上移失败！');
            }
        }, "json");
    }, "json");
}
function moveDown(id,seq) {
    var content={
        'id':id,
        'seq':seq
    };
    $.post(ctx + "/vipLogo/getMinContent", content, function(data) {
        var content1={
            'id':id,
            'seq':data.seq
        };
        var content2={
            'id':data.id,
            'seq':seq
        };
        $.post(ctx + "/vipLogo/update", content1, function(data) {
            if (data.returnValue == 0) {
                $.post(ctx + "/vipLogo/update", content2, function(data) {
                    if (data.returnValue == 0) {
                        $("#vipLogoTable").bootstrapTable('refresh');
                    } else {
                        alert( '下移失败！');
                    }
                }, "json");
            } else {
                alert( '下移失败！');
            }
        }, "json");
    }, "json");
}
/*关闭编辑窗口*/
function closeEditVipLogoDiv() {
    $('#id').val("");
    $('#logoId').val("");
    $('#vipName').val("")
    $("#editVipLogoDiv").modal('hide');
}
/*保存编辑内容*/
function submitVipLogoInfo() {
    var id =  $('#id').val();
    var logoId = $('#logoId').val();
    var vipName = $('#vipName').val()
    var logoImg = $('#vipLogoImg')[0].src;

    var logoImgUrl = logoImg.substring(logoImg.length-4,logoImg.length + 1);

    if (logoId == "") {
        alert( 'logoId名称不能为空！');
        return;
    }

    if (vipName == "") {
        alert( 'vip名称不能为空！');
        return;
    }

    if(logoImgUrl == "list"){
        alert("请上传图片");
        return;
    }
    var param = {};

    var vip_logo_param = {
        'id':id,
        'logoId':logoId,
        'vipName' : vipName,
        'vipLogo' : logoImg,
    };
    if(id !=null && id !=''){
        $.post(ctx + "/vipLogo/update", vip_logo_param, function(data) {
            if (data.returnValue == 0) {
                alert( '修改成功！');
                $("#editVipLogoDiv").modal('hide');
                $("#vipLogoTable").bootstrapTable('refresh', param);
            } else {
                alert( '修改失败！');
            }
        }, "json");

    }else{
        $.post(ctx + "/vipLogo/add", vip_logo_param, function(data) {
            if (data.returnValue == 0) {
                alert( '新增成功！');
                $("#editVipLogoDiv").modal('hide');
                $("#vipLogoTable").bootstrapTable('refresh', param);
            }else if(data.returnValue == 2){
                alert( 'ID已存在！请检查重新添加！');
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
