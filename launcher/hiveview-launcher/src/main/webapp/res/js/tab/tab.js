
$(document).ready(function () {
    $("#searchTabName").val("");
    $("#searchIsEffective").val("");
    initTable();
    initUploadJs("tabBackPicImg");
    initUploadJs("tabIconImg");
});




function initTable() {
    var tabName = $("#searchTabName").val();
    var isEffective = $("#searchIsEffective").val();
    //先销毁表格
    $('#tabTable').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#tabTable").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/tab/getPageList", //获取数据的Servlet地址
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
                tabName:tabName,
                isEffective:isEffective,
                page: params.pageNumber,
                rows: params.pageSize
            };
            return param;
        },
        columns : [ [{
            field : 'id',
            title : 'ID',
            width : '5%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        },{
            field : 'tabName',
            title : 'Tab名称',
            width : '20%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        }, {
            field : 'tabTitle',
            title : 'Tab标题名称',
            width : '20%',
            align : 'center',
            formatter : function(value) {
                return value;
            }
        }, {
            field : 'isEffective',
            title : '上下线',
            width : '20%',
            align : 'center',
            formatter : function(value, row) {
                var str = '';
                if(value == 1){
                    str += '<button style="color:#fff;background:rgb(46,130,255);cursor:default;border-radius:3px 0 0 3px;border-top:1px solid #808080;border-bottom:1px solid #808080;border-left:1px solid #808080;height:26px;border-right:0">上线</button>';
                    str += '<a href="javascript:downTab(' + row.id + ')" style="color:rgb(255,102,0);display: inline-block;height:24px;width:40px;border-top:1px solid #808080;border-bottom:1px solid #808080;border-right:1px solid #808080;vertical-align: -1px;line-height: 24px;text-decoration: none;border-radius:0 3px 3px 0">下线</a>';
                }else {
                    str += '<a href="javascript:upTab('+ row.id +')" style="color:rgb(255,102,0);display: inline-block;height:24px;width:40px;border-top:1px solid #808080;border-bottom:1px solid #808080;border-left:1px solid #808080;vertical-align: -1px;line-height: 24px;text-decoration: none;border-radius:3px 0 0 3px">上线</a>';
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
                str += '<a href="javascript:editTab('+row.id+",'"+row.tabName+"','"+row.tabTitle+"','"+row.tabBackPic+"','"+row.tabIcon+"'"+')">编辑</a>';
                str += '&nbsp;|&nbsp; <a href="javascript:linkGroup(' + row.id + ')">关联Group</a>';
                str += '&nbsp;|&nbsp; <a href="javascript:copyTab(' + row.id + ')">复制</a>';
                str += '&nbsp;|&nbsp; <a href="javascript:delTab(' + row.id + ')">删除</a>';
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
/*查询按钮*/
function searchTabInfo() {
    initTable();
}
/*添加按钮*/
function addTab() {
    $('#id').val("");
    $('#tabName').val("");
    $('#tabTitle').val("");
    $('#tabBackPicImg').attr('src', '');
    $('#tabIconImg').attr('src', '');
    $('#editTabDiv').modal({backdrop: 'static', keyboard: false});
    $('#editTabDiv').modal('show');
}
/*编辑按钮*/
function editTab(id,tabName,tabTitle,tabBackPic,tabIcon) {
    console.log(tabBackPic);
    $('#id').val(id);
    $('#tabName').val(tabName);
    $('#tabTitle').val(tabTitle);
    $('#tabBackPicImg').attr('src', tabBackPic);
    $('#tabIconImg').attr('src', tabIcon);
    $('#editTabDiv').modal({backdrop: 'static', keyboard: false});
    $('#editTabDiv').modal('show');
}
/*关联Group按钮*/
function linkGroup(id) {
    $('#tabId').val(id);
    initTabGroupTable(id);
    $('#tabGroupDiv').modal({backdrop: 'static', keyboard: false});
    $('#tabGroupDiv').modal('show');

    $('#groupDiv').modal({backdrop: 'static', keyboard: false});
    $('#groupDiv').modal('show');
    initGroupTable();
}
/*复制按钮*/
function copyTab(id) {
    var param = {};
    if (confirm("是否确认复制？")) {
        $.get(ctx + "/tab/add?id="+id, function(data) {
            if (data.returnValue == 0) {
                alert( '复制成功！');
                $("#tabTable").bootstrapTable('refresh', param);
            }else {
                alert( '复制失败！');
            }
        }, "json");
    }
/*    $.get(ctx + "/tabGroup/getPageList?belongTabId="+id, function(data) {
        console.log(data);
        if (data.total == 0) {
            alert( '没有关联数据！');
        }else {
            alert( '关联有数据！');
        }
    }, "json");*/

}
/*删除按钮*/
function delTab(id) {
    var param = {};
    if (confirm("是否确认删除？")) {
        $.get(ctx + "/tab/delete?id="+id, function(data) {
            if (data.returnValue == 0) {
                alert( '删除成功！');
                $("#tabTable").bootstrapTable('refresh', param);
            } else if(data.returnValue == -2){
                alert( '有launcher区域配置关联，请先取消关联！！');
            }else if(data.returnValue == -3){
                alert( '有推荐位关联，请先取消关联！！');
            }else {
                alert( '删除失败！');
            }
        }, "json");
    }
}


/*上下线*/
function downTab(id) {
    tab_param = {id : id,isEffective:'0'};
    var param = {};
    $.post(ctx + "/tab/update", tab_param, function(data) {
        if (data.returnValue == 0) {
            alert( '下线成功！');
            $("#tabTable").bootstrapTable('refresh', param);
        } else if(data.returnValue == -2){
            alert( '有launcher区域配置关联，请先取消关联！');
        }else if(data.returnValue == -3){
            alert( '有推荐位关联，请先取消关联！！');
        }else {
            alert( '下线失败！');
        }
    }, "json");
}
function upTab(id) {
    tab_param = {id : id,isEffective:'1'};
    var param = {};
    $.post(ctx + "/tab/update", tab_param, function(data) {
        if (data.returnValue == 0) {
            alert( '上线成功！');
            $("#editTabDiv").modal('hide');
            $("#tabTable").bootstrapTable('refresh', param);
        } else {
            alert( '上线失败！');
        }
    }, "json");
}
/*关闭编辑窗口*/
function closeEditTabDiv() {
    $('#id').val("");
    $('#tabName').val("");
    $('#tabTitle').val("");
    $('#tabBackPicImg').attr('src', '');
    $('#tabIconImg').attr('src', '');
    $("#editVipLogoDiv").modal('hide');
}
/*保存编辑内容*/
function submitTabInfo() {
    var id = $('#id').val();
    var tabName = $('#tabName').val();
    var tabTitle = $('#tabTitle').val();
    var tabBackPicImg = $('#tabBackPicImg')[0].src;
    var tabIconImg = $('#tabIconImg')[0].src;

    var ImgUrl1 = tabBackPicImg.substring(tabBackPicImg.length-4,tabBackPicImg.length + 1);
    var ImgUrl2 = tabIconImg.substring(tabIconImg.length-4,tabIconImg.length + 1);


    if (tabName == "") {
        alert( 'Tab名称不能为空！');
        return;
    }
    if (tabTitle == "") {
        alert( 'Tab标题名称不能为空！');
        return;
    }

    var tab_param = {
        'id':id,
        'tabName':tabName,
        'tabTitle' : tabTitle,
        'tabBackPic' : tabBackPicImg,
        'tabIcon' : tabIconImg
    };
    if(ImgUrl1 == "list" && ImgUrl2 == "list"){
        tab_param = {
            'id':id,
            'tabName':tabName,
            'tabTitle' : tabTitle,
            'tabBackPic' : null,
            'tabIcon' : null
        };
    }else if(ImgUrl1 == "list"){
        tab_param = {
            'id':id,
            'tabName':tabName,
            'tabTitle' : tabTitle,
            'tabBackPic' : null,
            'tabIcon' : tabIconImg
        };
    }else if(ImgUrl2 == "list"){
        tab_param = {
            'id':id,
            'tabName':tabName,
            'tabTitle' : tabTitle,
            'tabBackPic' : tabBackPicImg,
            'tabIcon' : null
        };
    }
    var param = {};

    if(id !=null && id !=''){
        console.log(tab_param);
        $.post(ctx + "/tab/update", tab_param, function(data) {
            if (data.returnValue == 0) {
                alert( '修改成功！');
                $("#editTabDiv").modal('hide');
                $("#tabTable").bootstrapTable('refresh', param);
            } else {
                alert( '修改失败！');
            }
        }, "json");

    }else{
        $.post(ctx + "/tab/add", tab_param, function(data) {
            console.log(tab_param);
            if (data.returnValue == 0) {
                alert( '新增成功！');
                $("#editTabDiv").modal('hide');
                $("#tabTable").bootstrapTable('refresh', param);
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
            var imgId = '#' + file_id;
            $(imgId).attr("src", "");// 预览
        }
    });
}

/*tabGroup*/
function initTabGroupTable(belongTabId) {
    //先销毁表格
    $('#tabGroupTable').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#tabGroupTable").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/tabGroup/getPageList", //获取数据的Servlet地址
        striped: true,  //表格显示条纹
        clickToSelect: false,
        pagination: true, //启动分页
        pageSize: 10,  //每页显示的记录数
        pageNumber: 1, //当前第几页
        pageList: [10, 20, 40, 80],  //记录数可选列表
        search: false,  //是否启用查询
        showColumns: false,  //显示下拉框勾选要显示的列
        showRefresh: false,  //显示刷新按钮
        sidePagination: "server", //表示服务端请求
        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        //设置为limit可以获取limit, offset, search, sort, order
        queryParamsType: "undefined",
        queryParams: function queryParams(params) {   //设置查询参数
            param = {
                belongTabId:belongTabId,
                page: params.pageNumber,
                rows: params.pageSize
            };
            return param;
        },
        columns : [ [{
            title : '排序',
            width : '5%',
            align : 'center',
            formatter : function(value, row, index) {
                return ((param.page-1)*param.rows)+index+1;
            }
        },{
            field : 'groupName',
            title : 'Group名称',
            width : '15%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        }, {
            field : 'groupTitle',
            title : 'Group标题名称',
            width : '15%',
            align : 'center',
            formatter : function(value) {
                return value;
            }
        }, {
            field : 'isShow',
            title : '标题状态',
            width : '25%',
            align : 'center',
            formatter : function(value, row) {
                var str = '';
                if(value == 1){
                    str += '<button style="color:#fff;background:rgb(46,130,255);cursor:default;border-radius:3px 0 0 3px;border-top:1px solid #808080;border-bottom:1px solid #808080;border-left:1px solid #808080;height:26px;border-right:0">显示</button>';
                    str += '<a href="javascript:showTabGroup(' + row.id + ')" style="color:rgb(255,102,0);display: inline-block;height:24px;width:53px;border-top:1px solid #808080;border-bottom:1px solid #808080;border-right:1px solid #808080;vertical-align: -1px;line-height: 24px;text-decoration: none;border-radius:0 3px 3px 0">不显示</a>';
                }else {
                    str += '<a href="javascript:hideTabGroup('+ row.id +')" style="color:rgb(255,102,0);display: inline-block;height:24px;width:39px;border-top:1px solid #808080;border-bottom:1px solid #808080;border-left:1px solid #808080;vertical-align: -1px;line-height: 24px;text-decoration: none;border-radius:3px 0 0 3px">显示</a>';
                    str += '<button style="color:#fff;background:rgb(46,130,255);cursor:default;border-radius:0 3px 3px 0;border-top:1px solid #808080;border-bottom:1px solid #808080;border-right:1px solid #808080;height:26px;border-left:0">不显示</button>';
                }
                return str;
            }
        }, {
            field : 'seq',
            title : '顺序调整',
            width : '23%',
            align : 'center',
            formatter : function(value,row) {
                var str='';
                if(row.upAndDown==2){
                    str += '纵向Group独占一个Tab！';
                }else if(row.seq==-1){
                    str += '没有确定行的的Group！';
                }else {
                    str += '<a href="javascript:stick('+row.id+","+row.belongTabId+","+row.seq+')">置顶</a>';
                    str += '&nbsp;|&nbsp;<a href="javascript:moveUp('+row.id+","+row.seq+","+row.belongTabId+')">上移</a>';
                    str += '&nbsp;|&nbsp;<a href="javascript:moveDown('+row.id+","+row.seq+","+row.belongTabId+')">下移</a>';
                }
                return str;
            }
        }, {
            field : 'do',
            title : '操作',
            width : '10%',
            align : 'center',
            formatter : function(value, row) {
                var str = '';
                str += '<a href="javascript:delTabGroup(' + row.id + ')">删除</a>';
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
/*添加Group按钮*/
/*function addGroup() {
    $('#groupDiv').modal({backdrop: 'static', keyboard: false});
    $('#groupDiv').modal('show');
    initGroupTable();
}*/

/*显示按钮*/
function showTabGroup(id) {
    tab_group_param = {id : id,isShow:'0'};
    var param = {};
    $.post(ctx + "/tabGroup/update", tab_group_param, function(data) {
        if (data.returnValue == 0) {
            alert( '不显示成功！');
            $("#tabGroupTable").bootstrapTable('refresh', param);
        } else {
            alert( '不显示失败！');
        }
    }, "json");
}
function hideTabGroup(id) {
    tab_group_param = {id : id,isShow:'1'};
    var param = {};
    $.post(ctx + "/tabGroup/update", tab_group_param, function(data) {
        if (data.returnValue == 0) {
            alert( '显示成功！');
            $("#tabGroupTable").bootstrapTable('refresh', param);
        } else {
            alert( '显示失败！');
        }
    }, "json");
}
/*删除按钮*/
function delTabGroup(id) {
    var param = {};
    if (confirm("是否确认删除？")) {
        var tab_group_param = {
            'id' : id
        };
        $.post(ctx + "/tabGroup/delete", tab_group_param, function(data) {
            if (data.returnValue == 0) {
                alert( '删除成功！');
                $("#tabGroupTable").bootstrapTable('refresh', param);
                if($("#tabGroupTable").bootstrapTable('getData').length == 1){
                    $("#tabGroupTable").bootstrapTable('prevPage');
                }
            }else {
                alert( '删除失败！');
            }
        }, "json");
    }
}
/*调整顺序*/
function moveUp(id,seq,belongTabId) {
    var content={
        'id':id,
        'seq':seq,
        'belongTabId':belongTabId,
    };
    $.post(ctx + "/tabGroup/getMaxContent", content, function(data) {
        var content1={
            'id':id,
            'seq':data.seq
        };
        var content2={
            'id':data.id,
            'seq':seq
        };
        $.post(ctx + "/tabGroup/update", content1, function(data) {
            if (data.returnValue == 0) {
                $.post(ctx + "/tabGroup/update", content2, function(data) {
                    if (data.returnValue == 0) {
                        $("#tabGroupTable").bootstrapTable('refresh');
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
function moveDown(id,seq,belongTabId) {
    var content={
        'id':id,
        'seq':seq,
        'belongTabId':belongTabId,
    };
    $.post(ctx + "/tabGroup/getMinContent", content, function(data) {
        var content1={
            'id':id,
            'seq':data.seq
        };
        var content2={
            'id':data.id,
            'seq':seq
        };
        if(data.seq==-1){
            alert( '下移失败！');
        }else {
            $.post(ctx + "/tabGroup/update", content1, function(data) {
                if (data.returnValue == 0) {
                    $.post(ctx + "/tabGroup/update", content2, function(data) {
                        if (data.returnValue == 0) {
                            $("#tabGroupTable").bootstrapTable('refresh');
                        } else {
                            alert( '下移失败！');
                        }
                    }, "json");
                } else {
                    alert( '下移失败！');
                }
            }, "json");
        }
    }, "json");
}

function stick(id,belongTabId,seq) {

    var tab_group_param = {id:id,belongTabId:belongTabId,seq:seq}
    $.post(ctx + "/tabGroup/getMaxSeq", tab_group_param, function(data) {
        console.log(data);
        if(data==seq){
            alert( '已在顶端！');
        }else {
            var new_param = {id:id,seq:data+1}
            $.post(ctx + "/tabGroup/update", new_param, function(data) {
                if (data.returnValue == 0) {
                    alert( '置顶成功！');
                    $("#tabGroupTable").bootstrapTable('refresh');

                } else {
                    alert( '置顶失败！');
                }
            }, "json");
        }
    }, "json");
}

/*不同种类的group*/
function showGroupType() {
    var groupType = $('#showGroupType').val();
    $('#searchGroupName').val('');
    console.log("选择类型！"+groupType);
    initGroupTable();
}
/*查询按钮*/
function searchGroupInfo() {
    initGroupTable();
}
function initGroupTable() {
    var groupType = $('#showGroupType').val();
    if (groupType==1){
        initGroup();
    }else if(groupType==2){
        initSpeGroup();
    }else if(groupType==3){
        initDataGroup();
    }else {
        initGroup();
    }

}

function initGroup() {
    var contentName=$('#searchGroupName').val();
    console.log(contentName);
    //先销毁表格
    $('#groupTable').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#groupTable").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/customRecomTempleteList/getPageList", //获取数据的Servlet地址
        striped: true,  //表格显示条纹
        clickToSelect: false,
        pagination: true, //启动分页
        pageSize: 5,  //每页显示的记录数
        pageNumber: 1, //当前第几页
        pageList: [ 10, 15, 20, 25],  //记录数可选列表
        search: false,  //是否启用查询
        showColumns: false,  //显示下拉框勾选要显示的列
        showRefresh: false,  //显示刷新按钮
        sidePagination: "server", //表示服务端请求
        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        //设置为limit可以获取limit, offset, search, sort, order
        queryParamsType: "undefined",
        queryParams: function queryParams(params) {   //设置查询参数
            param = {
                contentName:contentName,
                isEffective:'1',
                templeteLeve:'2',
                page: params.pageNumber,
                rows: params.pageSize
            };
            return param;
        },
        onClickRow: function (row, tr) {
            // 进行你的操作，如弹出新窗口
            var add_param = {
                belongTabId:$('#tabId').val(),
                groupType:$('#showGroupType').val(),
                belongGroupId:row.templeteId,
                groupName:row.contentName,
                groupTitle:row.templeteTitle,
                groupBackground:row.backPic
            }
            console.log(add_param);
            $.post(ctx + "/tabGroup/addOne", add_param, function(data) {
                console.log(data);
                if (data.returnValue == 0) {
                    alert("添加成功！");
                    $("#groupTable").bootstrapTable('refresh')//刷新GroupTable
                    $("#tabGroupTable").bootstrapTable('refresh')//刷新tabGroupTable
                }else if(data.returnValue == 3){
                    alert("已存在有带有视频的Group！不可再添加！");
                    $("#groupTable").bootstrapTable('refresh')//刷新GroupTable
                    $("#tabGroupTable").bootstrapTable('refresh')//刷新tabGroupTable
                }else if(data.returnValue == 2){
                    alert("已存在这个Group！不可再添加！");
                    $("#groupTable").bootstrapTable('refresh')//刷新GroupTable
                    $("#tabGroupTable").bootstrapTable('refresh')//刷新tabGroupTable
                }else if(data.returnValue == 5){
                    alert("纵向Group独占一个Tab！");
                    $("#groupTable").bootstrapTable('refresh')//刷新GroupTable
                    $("#tabGroupTable").bootstrapTable('refresh')//刷新tabGroupTable
                }else {
                    alert("添加失败！");
                    $("#groupTable").bootstrapTable('refresh')//刷新GroupTable
                    $("#tabGroupTable").bootstrapTable('refresh')//刷新tabGroupTable
                }
            }, "json");
        },
        columns : [ [{
            field : 'templeteId',
            title : 'ID',
            width : '15%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        },{
            field : 'contentName',
            title : 'Group名称',
            width : '15%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        }, {
            field : 'templeteTitle',
            title : 'Group标题名称',
            width : '15%',
            align : 'center',
            formatter : function(value) {
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
function initDataGroup() {
    var dataGroupName=$('#searchGroupName').val();
    console.log(dataGroupName);
    //先销毁表格
    $('#groupTable').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#groupTable").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/PortalDataGroupController/getPage", //获取数据的Servlet地址
        striped: true,  //表格显示条纹
        clickToSelect: false,
        pagination: true, //启动分页
        pageSize: 5,  //每页显示的记录数
        pageNumber: 1, //当前第几页
        pageList: [10, 15, 20, 25],  //记录数可选列表
        search: false,  //是否启用查询
        showColumns: false,  //显示下拉框勾选要显示的列
        showRefresh: false,  //显示刷新按钮
        sidePagination: "server", //表示服务端请求
        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        //设置为limit可以获取limit, offset, search, sort, order
        queryParamsType: "undefined",
        queryParams: function queryParams(params) {   //设置查询参数
            param = {
                dataGroupName:dataGroupName,
                effective:'1',
                page: params.pageNumber,
                rows: params.pageSize
            };
            return param;
        },
        onClickRow: function (row, tr) {
            // 进行你的操作，如弹出新窗口
            var xp=null;
            var add_param;
            if(row.channel==xp){
                add_param= {
                    belongTabId:$('#tabId').val(),
                    groupType:$('#showGroupType').val(),
                    contentType:row.contentType,
                    apkBagName:row.apkPackageName,
                    chnId:row.categoryId,
                    chnType:row.channelType,
                    hotId:row.hotId,
                    belongGroupId:row.id,
                    col:row.col,
                    row:row.row,
                    width:row.width,
                    height:row.height,
                    groupName:row.dataGroupName,
                    groupTitle:row.dataGroupTitle,
                    upAndDown:row.upAndDown,
                }
            }else {
                add_param= {
                    belongTabId:$('#tabId').val(),
                    groupType:$('#showGroupType').val(),
                    contentType:row.contentType,
                    apkBagName:row.apkPackageName,
                    chnId:row.channel,
                    chnType:row.channelType,
                    hotId:row.hotId,
                    belongGroupId:row.id,
                    col:row.col,
                    row:row.row,
                    width:row.width,
                    height:row.height,
                    groupName:row.dataGroupName,
                    groupTitle:row.dataGroupTitle,
                    upAndDown:row.upAndDown,
                }
            }

            console.log(add_param);
            $.post(ctx + "/tabGroup/addOne", add_param, function(data) {
                if (data.returnValue == 0) {
                    alert("添加成功！");
                    $("#groupTable").bootstrapTable('refresh')//刷新GroupTable
                    $("#tabGroupTable").bootstrapTable('refresh')//刷新tabGroupTable
                }else if(data.returnValue == 2){
                    alert("已存在这个Group！不可再添加！");
                    $("#groupTable").bootstrapTable('refresh')//刷新GroupTable
                    $("#tabGroupTable").bootstrapTable('refresh')//刷新tabGroupTable
                }else if(data.returnValue == 4){
                    alert("已存在一个未知行参数的Group！不可再添加！");
                    $("#groupTable").bootstrapTable('refresh')//刷新GroupTable
                    $("#tabGroupTable").bootstrapTable('refresh')//刷新tabGroupTable
                }else if(data.returnValue == 5){
                    alert("纵向Group独占一个Tab！");
                    $("#groupTable").bootstrapTable('refresh')//刷新GroupTable
                    $("#tabGroupTable").bootstrapTable('refresh')//刷新tabGroupTable
                }else {
                    alert("添加失败！");
                    $("#groupTable").bootstrapTable('refresh')//刷新GroupTable
                    $("#tabGroupTable").bootstrapTable('refresh')//刷新tabGroupTable
                }
            }, "json");
        },
        columns : [ [{
            field : 'id',
            title : 'ID',
            width : '8%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        },{
            field : 'dataGroupName',
            title : 'Group名称',
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
                if(value==0){
                    return "频道推荐";
                }else if(value==1){
                    return "热词列表";
                }else if(value==2){
                    return "频道内容列表";
                }
                return value;
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
function initSpeGroup() {
    var groupName=$('#searchGroupName').val();
    console.log(groupName);
    //先销毁表格
    $('#groupTable').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#groupTable").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/speGroup/getPageList", //获取数据的Servlet地址
        striped: true,  //表格显示条纹
        clickToSelect: false,
        pagination: true, //启动分页
        pageSize: 5,  //每页显示的记录数
        pageNumber: 1, //当前第几页
        pageList: [10, 15, 20, 25],  //记录数可选列表
        search: false,  //是否启用查询
        showColumns: false,  //显示下拉框勾选要显示的列
        showRefresh: false,  //显示刷新按钮
        sidePagination: "server", //表示服务端请求
        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        //设置为limit可以获取limit, offset, search, sort, order
        queryParamsType: "undefined",
        queryParams: function queryParams(params) {   //设置查询参数
            param = {
                speGroupName:groupName,
                page: params.pageNumber,
                rows: params.pageSize
            };
            return param;
        },
        onClickRow: function (row, tr) {
            // 进行你的操作，如弹出新窗口
            var add_param = {
                belongTabId:$('#tabId').val(),
                groupType:$('#showGroupType').val(),
                belongGroupId:row.id,
                groupName:row.speGroupName,
                groupTitle:row.speGroupTitle,
            }
            console.log(add_param);
            $.post(ctx + "/tabGroup/addOne", add_param, function(data) {
                if (data.returnValue == 0) {
                    alert("添加成功！");
                    $("#groupTable").bootstrapTable('refresh')//刷新GroupTable
                    $("#tabGroupTable").bootstrapTable('refresh')//刷新tabGroupTable
                }else if(data.returnValue == 2){
                    alert("已存在这个Group！不可再添加！");
                    $("#groupTable").bootstrapTable('refresh')//刷新GroupTable
                    $("#tabGroupTable").bootstrapTable('refresh')//刷新tabGroupTable
                }else if(data.returnValue == 3){
                    alert("已存在带有视频的Group！不可再添加！");
                    $("#groupTable").bootstrapTable('refresh')//刷新GroupTable
                    $("#tabGroupTable").bootstrapTable('refresh')//刷新tabGroupTable
                }else if(data.returnValue == 5){
                    alert("纵向Group独占一个Tab！");
                    $("#groupTable").bootstrapTable('refresh')//刷新GroupTable
                    $("#tabGroupTable").bootstrapTable('refresh')//刷新tabGroupTable
                }else {
                    alert("添加失败！");
                    $("#groupTable").bootstrapTable('refresh')//刷新GroupTable
                    $("#tabGroupTable").bootstrapTable('refresh')//刷新tabGroupTable
                }
            }, "json");
        },
        columns : [ [{
            field : 'id',
            title : 'ID',
            width : '15%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        },{
            field : 'speGroupName',
            title : 'Group名称',
            width : '15%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        }, {
            field : 'speGroupTitle',
            title : 'Group标题名称',
            width : '15%',
            align : 'center',
            formatter : function(value) {
                return value;
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
/*保存选项*/
/*function data_submit() {
    var selGroups= $("#groupTable").bootstrapTable('getSelections');
    if(selGroups.length<=0){
        alert("您还没有勾选任何信息！");
    }else{
        if (confirm("添加"+selGroups.length+"行信息")) {
            var successCount=0;//统计添加成功的条数
            var groupType = $('#showGroupType').val();
            var add_params=new Array;
            for(var i=0;i<selGroups.length;i++){
                var add_param;
                if(groupType==1){
                    add_param = {
                        belongTabId:$('#tabId').val(),
                        groupType:groupType,
                        belongGroupId:selGroups[i].templeteId,
                        groupName:selGroups[i].contentName,
                        groupTitle:selGroups[i].templeteTitle,
                    }
                }else if (groupType==2){
                    add_param = {
                        belongTabId:$('#tabId').val(),
                        groupType:groupType,
                        belongGroupId:selGroups[i].id,
                        groupName:selGroups[i].speGroupName,
                        groupTitle:selGroups[i].speGroupTitle,
                    }
                }else if(groupType==3){
                    add_param = {
                        belongTabId:$('#tabId').val(),
                        groupType:groupType,
                        contentType:selGroups[i].contentType,
                        apkBagName:selGroups[i].apkPackageName,
                        chnId:selGroups[i].channelId,
                        chnType:selGroups[i].channel,
                        hotId:selGroups[i].hotId,
                        belongGroupId:selGroups[i].id,
                        col:selGroups[i].col,
                        row:selGroups[i].row,
                        width:selGroups[i].width,
                        height:selGroups[i].height,
                        groupName:selGroups[i].dataGroupName,
                        groupTitle:selGroups[i].dataGroupTitle,
                    }
                }
                console.log(add_param);
                add_params.push(add_param);

            }
            console.log(selGroups);
            $.post(ctx + "/tabGroup/add", {add_params:JSON.stringify(add_params)}, function(data) {
                if (data.returnValue == 0) {
                    alert("添加成功！");
                    $("#groupTable").bootstrapTable('refresh')//刷新GroupTable
                }else if(data.returnValue == 2){
                    alert("选中多个Group中含有视频！请重新选择！Tab页最多关联一个带视频的Group！");
                }else if(data.returnValue == 3){
                    alert("原有tab中已存在含有视频的Group，请重新选择！Tab页最多关联一个带视频的Group！");
                }else {
                    alert("添加失败！");
                }
            }, "json");

        }
    }
}*/
/*关闭*/
function clear_submit() {
    $("#groupDiv").modal('hide');//关闭group列表
    $("#tabGroupTable").bootstrapTable('refresh');//刷新tabGroupTable
}

// if($('#tabGroupTable a').html().length == 3){
//     alert($(this).html());
// };

// console.log($('#tabGroupTable #tableA').text());
