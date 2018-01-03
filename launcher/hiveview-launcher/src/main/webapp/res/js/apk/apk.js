/**
 * Created by Administrator on 2017/7/19.
 */
$(document).ready(function () {
    initTable();
    select();
    initUploadJs("txt_versionUrl");
    //添加编辑APK时应用类型改变
    $('#txt_appType').change(function () {
        var appType=$('#txt_appType').val();
        if(appType==0){
            $("#txt_installStyle").val('1');
            $("#txt_installStyle").attr("disabled",true);
        }else {
            $("#txt_installStyle").attr("disabled",false);
        }
    });
});

function select() {
    var selectName=$('#txt_seleteName').val();
    initTable(selectName);
}

function add() {
    $("#txt_installStyle").attr("disabled",true);
    //addclear_diaglog();
    $('#addModal').modal({backdrop: 'static', keyboard: false});
}

function addclear_diaglog() {
    $('#txt_id').val('');
    $('#txt_apkName').val('');
    $('#txt_packageName').val('');
    $('#txt_isEffective').val('0');
    $('#txt_appType').val('0');
    $('#txt_installStyle').val('1');
    $('#addModal').modal('hide');
}

function manage(apkId) {
    initVersionTable(apkId);
    //manageclear_diaglog();
    $('#txt_apkId').val(apkId);
    setTimeout(
      function(){
          $('#verSionManageModal').modal({backdrop: 'static', keyboard: false});
      } ,1500
    );
}
function manageclear_diaglog() {
    $('#txt_apkId').val('');
    $('#verSionManageModal').modal('hide');
}

function addApkVersion() {
    //addApkVersionclear_diaglog();
    $('#addApkVersionModal').modal({backdrop: 'static', keyboard: false});
}
function addApkVersionclear_diaglog() {
    $('#txt_ApkVersionpId').val('');
    $('#txt_versionNo').val('');
    //$('#txt_packageName').val('');
    $('#txt_versionUrl').val('');
    $('#txt_versionDescribe').val('');
    $('#addApkVersionModal').modal('hide');
}

function initTable(apkName) {
    //先销毁表格
    $('#sample_1').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#sample_1").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/apk/getPageList", //获取数据的Servlet地址
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
                apkName:apkName
            };
            return param;
        },
        columns : [ [ {
            field : 'apkName',
            title : '定制APK名称',
            width : '13%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        }, {
            field : 'packageName',
            title : '包名',
            width : '13%',
            align : 'center',
            formatter : function(value) {
                return value;
            }
        }, {
            field : 'lastVersion',
            title : '最新版本',
            width : '13%',
            align : 'center',
            formatter : function(value) {
                return value;
            }
        }, {
            field : 'appType',
            title : '应用类型',
            width : '10%',
            align : 'center',
            formatter : function(value) {
                if(value==0){
                    value="系统应用";
                    return value;
                }
                if(value==1){
                    value="定制应用";
                    return value;
                }
            }
        }, {
            field : 'installStyle',
            title : '安装方式',
            width : '10%',
            align : 'center',
            formatter : function(value) {
                if(value==1){
                    value="静默安装";
                    return value;
                }
                if(value==0){
                    value="询问安装";
                    return value;
                }
            }
        }, {
            field : 'do',
            title : '操作',
            width : '20%',
            align : 'center',
            formatter : function(value, row) {
                var str = '';
                str += '<a href="javascript:edit('+row.id+",'"+row.apkName+"','"+row.packageName+"',"+row.appType+","+row.installStyle+')">编辑</a>';
                if(row.appType==0){
                    str += '&nbsp;|&nbsp; <span style="color: #CCCCCC;">管理版本</span>';
                }else{
                    str += '&nbsp;|&nbsp; <a href="javascript:manage('+row.id+')">管理版本</a>';
                }
                str += '&nbsp;|&nbsp; <a href="javascript:del('+row.id+')">删除</a>';
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


function  edit(id,apkName,packageName,appType,installStyle) {
    if(appType==0){
        $("#txt_installStyle").attr("disabled",true);
    }else {
        $("#txt_installStyle").attr("disabled",false);
    }
    $('#txt_id').val(id);
    $('#txt_apkName').val(apkName);
    $('#txt_packageName').val(packageName);
    $('#txt_appType').val(appType);
    $('#txt_installStyle').val(installStyle);
    $("#addModal").modal({backdrop: 'static', keyboard: false});
}
function  del(id) {
    if (confirm("是否确认删除？")) {
        var apk = {
            'id' : id,
        };
        $.post(ctx + "/apk/del", apk, function(data) {
            if (data.returnValue == 0) {
                alert( '成功！');
                $("#addModal").modal('hide');
                $("#sample_1").bootstrapTable('refresh', param);
                if($("#sample_1").bootstrapTable('getData').length == 1){
                    $("#sample_1").bootstrapTable('prevPage');}
            }else if(data.returnValue == 1){
                alert('数据被定制APK启动指令管理关联，不可删除');
            }else {
                alert( '失败！');
            }
        }, "json");
    }
}
function apk_submit() {
    var id=$('#txt_id').val();
    var apkName=$('#txt_apkName').val();
    var packageName=$('#txt_packageName').val();
    var appType=$('#txt_appType').val();
    var installStyle=$('#txt_installStyle').val();
    var time=new Date();

    if ($.trim(apkName) == "") {
        alert("APK名称不能为空！");
        return;
    }
    if ($.trim(packageName) == "") {
        alert("包名不能为空！");
        return;
    }
    if ($.trim(appType) == "") {
        alert("应用类型不能为空！");
        return;
    }
    if ($.trim(installStyle) == "") {
        alert("询问安装不能为空！");
        return;
    }

    if(id !=null && id !=''){
        var apk = {
            'id' : id,
            'apkName' : apkName,
            'packageName':packageName,
            'isEffective':0,
            'appType':appType,
            'installStyle' : installStyle,
            'updateTime':time
        };
        $.post(ctx + "/apk/update", apk, function(data) {
            if (data.returnValue == 0) {
                alert( '修改成功！');
                addclear_diaglog();
                $("#addModal").modal('hide');
                $("#sample_1").bootstrapTable('refresh', param);
            } else {
                alert( '修改失败！');
            }
        }, "json");

    }else{
        console.log(appType);
        if(appType==0){
            var apk = {
                'id' : id,
                'apkName' : apkName,
                'packageName':packageName,
                'isEffective':0,
                'appType':appType,
                'lastVersion':'1.0',
                'installStyle' : installStyle,
                'createTime' :time,
                'updateTime':time
            };
        }else {
            var apk = {
                'id' : id,
                'apkName' : apkName,
                'packageName':packageName,
                'isEffective':0,
                'appType':appType,
                'installStyle' : installStyle,
                'createTime' :time,
                'updateTime':time
            };
        }
        console.log(apk);
        $.post(ctx + "/apk/save", apk, function(data) {
            if (data.returnValue == 0) {
                alert( '新增成功！');
                $("#addModal").modal('hide');
                addclear_diaglog();
                $("#sample_1").bootstrapTable('refresh', param);
            } else {
                alert( '新增失败！');
            }
        }, "json");
    }
}

function initVersionTable(apkId) {
    //先销毁表格
    $('#sample_2').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#sample_2").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/apkVersion/getPageList", //获取数据的Servlet地址
        striped: true,  //表格显示条纹
        clickToSelect: false,
        pagination: true, //启动分页
        pageSize: 5,  //每页显示的记录数
        pageNumber: 1, //当前第几页
        pageList: [5],  //记录数可选列表
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
                apkId:apkId
            };
            return param;
        },
        columns : [ [ {
            field : 'versionNo',
            title : '版本号',
            width : '13%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        }, {
            field : 'versionDescribe',
            title : '版本描述',
            width : '13%',
            align : 'center',
            formatter : function(value) {
                return value;
            }
        }/*, {
            field : 'packageName',
            title : '包名',
            width : '10%',
            align : 'center',
            formatter : function(value) {
                    return value;
            }
        }*/, {
            field : 'updateTime',
            title : '修改时间',
            width : '17%',
            align : 'center',
            formatter : function(value) {
                var date = new Date(value);
                return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()+' '+date.getHours()+':'+date.getMinutes()+':'+date.getSeconds();
            }
        }, {
            field : 'do',
            title : '操作',
            width : '13%',
            align : 'center',
            formatter : function(value, row) {
                var str = '';
                str += '<a href="javascript:editVersion('+row.id+",'"+row.versionNo+"','"/*+row.packageName+"','"*/+row.versionUrl+"','"+row.versionDescribe+"'"+')">编辑</a>';
                str += '&nbsp;|&nbsp; <a href="javascript:delVersion('+row.id+')">删除</a>';
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


function  editVersion(id,versionNo/*,packageName*/,versionUrl,versionDescribe) {

    $('#txt_ApkVersionpId').val(id);
    $('#txt_versionNo').val(versionNo);
    //$('#txt_packageName').val(packageName);
    $('#txt_versionUrl').val(versionUrl);
    $('#txt_versionDescribe').val(versionDescribe);
    $("#addApkVersionModal").modal({backdrop: 'static', keyboard: false});
}
function  delVersion(id) {

    if (confirm("是否确认删除？")) {
        var apkId=$('#txt_apkId').val();
        var apkVersion = {
            'id' : id,
            'apkId':apkId
        };
        $.post(ctx + "/apkVersion/del", apkVersion, function(data) {
            if (data.returnValue == 0) {
                alert( '成功！');
                $("#sample_1").bootstrapTable('refresh', param);
                $("#sample_2").bootstrapTable('refresh', param);
                if($("#sample_1").bootstrapTable('getData').length == 1){
                    $("#sample_1").bootstrapTable('prevPage');}
                if($("#sample_2").bootstrapTable('getData').length == 1){
                    $("#sample_2").bootstrapTable('prevPage');}
            }else if(data.returnValue == 1){
                alert( '定制APK启动指令管理关联该APK，版本号必须保留最少一条记录，不可删除！');
            }else {
                alert( '失败！');
            }
        }, "json");
    }
}
function apkVersion_submit() {
    var id=$('#txt_ApkVersionpId').val();
    var apkId=$('#txt_apkId').val();
    var versionNo=$('#txt_versionNo').val();
    //var packageName=$('#txt_packageName').val();
    var versionUrl=$('#txt_versionUrl').val();
    var versionDescribe=$('#txt_versionDescribe').val();
    var time=new Date();

    if ($.trim(versionNo) == "") {
        alert("版本号不能为空！");
        return;
    }
    /*if ($.trim(packageName) == "") {
        alert("包名不能为空！");
        return;
    }*/
    if ($.trim(versionUrl) == "") {
        alert("文件地址不能为空！");
        return;
    }
    var apkVersion = {
        'id' : id,
        'apkId' : apkId,
        'versionNo':versionNo,
        //'packageName' : packageName,
        'versionUrl':versionUrl,
        'versionDescribe':versionDescribe,
        'createTime' :time,
        'updateTime':time
    }

    if(id !=null && id !=''){
        $.post(ctx + "/apkVersion/update", apkVersion, function(data) {
            if (data.returnValue == 0) {
                alert( '修改成功！');
                addApkVersionclear_diaglog();
                $("#addApkVersionModal").modal('hide');
                $("#sample_1").bootstrapTable('refresh', param);
                $("#sample_2").bootstrapTable('refresh', param);
            }else if(data.returnValue == 1){
                alert( '版本号添加重复，请重新修改！');
            }else {
                alert( '修改失败！');
            }
        }, "json");

    }else{
        $.post(ctx + "/apkVersion/save", apkVersion, function(data) {
            if (data.returnValue == 0) {
                alert( '新增成功！');
                $("#addApkVersionModal").modal('hide');
                addApkVersionclear_diaglog();
                $("#sample_1").bootstrapTable('refresh', param);
                $("#sample_2").bootstrapTable('refresh', param);
            }else if(data.returnValue == 1){
                alert( '版本号添加重复，请重新添加！');
            } else {
                alert( '新增失败！');
            }
        }, "json");
    }
}

//upload
function initUploadJs(file_id) {
    var file_upload = '#' + file_id + '_file_upload';
    $(file_upload).uploadify({
        'swf' :ctx +  '/res/js/plugin/uploadify/uploadify.swf',
        'uploader' :ctx +  '/upload/init.json', // fileUpload/newIcpUpload.json
        'height' : 25,
        'whith' : 120,
        'auto' : true,
        'fileDataName' : 'file',
        'buttonText' : '浏览...',
        'fileTypeExts' : '*.apk;',
        'multi' : false,
        'method' : 'post',
        'debug' : false,
        'queueSizeLimit' : 1,
        'removeTimeout' : 1,
        'onSelect' : function(file) {
            var myself = this;
            var size = file.size

            if (file_id == "txt_versionUrl") {
                if (file.type != ".apk") {
                    alert(titleInfo, '上传文件错误，请选择合法apk文件！');
                    $(file_upload).uploadify('cancel', '*');
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
            $(imgId).val(data);
        },
        'onUploadError' : function(file, errorCode, errorMsg, errorString) {
            var errMsg = 'The file ' + file.name + ' could not be uploaded: '
                + errorString
            alert(errMsg);
        }
    });
}