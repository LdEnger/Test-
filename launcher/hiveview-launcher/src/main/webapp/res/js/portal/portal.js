$(document).ready(function () {
    initTable();
    init();
    loadContentPage();
    initCombobox();
});
var param ={};
var contentparam = {};
function selectIsEffective() {
    var isEffective=$("#txt_selectIsEffective").val();
    var recommendName=$("#txt_selectName").val();
    initTable(isEffective,recommendName);
}
function initContent(belongRecomendId,imageType) {
    $.ajax({
        type:"get",
        data:{'belongRecomendId':belongRecomendId,'imageType':imageType},
        url:ctx + "/content2/getMinSeq",
        success:function (res) {
            $('#txt_minSeq').val(res);
        }
    });
}
function initTable(isEffective,recommendName) {
    $('#portal').bootstrapTable('destroy');
    $("#portal").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/portal/getPageList", //获取数据的Servlet地址
        striped: true,  //表格显示条纹
        pagination: true, //启动分页
        pageSize: 10,  //每页显示的记录数
        pageNumber: 1, //当前第几页
        pageList: [5, 10, 15, 20, 25],  //记录数可选列表
        search: false,  //是否启用查询
        showColumns: true,  //显示下拉框勾选要显示的列
        showRefresh: false,  //显示刷新按钮
        sidePagination: "server", //表示服务端请求
        queryParamsType: "undefined",
        queryParams: function queryParams(params) {   //设置查询参数
            param = {
                page: params.pageNumber,
                rows: params.pageSize,
                isEffective:isEffective,
                recommendName:recommendName

            };
            return param;
        },
        columns : [ [
            {
            field : 'recommendId',
            title : 'ID',
            width : '8%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        },{
            field : 'recommendName',
            title : '大图推荐位名称',
            width : '10%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        }, {
            field : 'timeSpan',
            title : '轮显时间间隔（分钟）',
            width : '16%',
            align : 'center',
            formatter : function(value) {
                return value;
            }
        }, {
            field : 'count',
            title : '大图数量',
            width : '12%',
            align : 'center',
            formatter : function(value) {
                if(value == null){
                    value = 0;
                }
                return value;
            }
        }, {
                field : 'isEffective',
                title : '上下线',
                width : '13%',
                align : 'center',
                formatter : function(value,row) {
                    var str = '';
                    if(value == 1){
                        str += '<span style="color:#808080">上线</span>';
                        str += '&nbsp;|&nbsp; <a href="javascript:down('+row.recommendId+')" class="btn btn-default btn-xs">下线</a>';
                    }else if(value == 0) {
                        str += '<a href="javascript:up('+row.recommendId+')" class="btn btn-primary btn-xs">上线</a>';
                        str += '&nbsp;|&nbsp; <span style="color:#808080">下线</span>';
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

                str += '<a href="javascript:edit('+row.recommendId+",'"+row.recommendName+"',"+row.timeSpan+')">编辑</a>';
                str += '&nbsp;|&nbsp;<a href="javascript:content('+row.recommendId+')">内容管理</a>';
                str += '&nbsp;|&nbsp; <a href="javascript:del('+row.recommendId+')">删除</a>';
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
}
function down(recommendId) {
    var portal = {
        'recommendId' : recommendId,
        'isEffective' : 0,
        'recommendType' :0
    };
    $.post(ctx + "/portal/update", portal, function(data) {
        if (data.returnValue == 0) {
            alert( '下线成功！');
            $("#portal").bootstrapTable('refresh', param);
        }/*else if(data.returnValue == 1){
            alert('数据被launcher关联，不可下线！');
        }*/ else {
            alert( '下线失败！');
        }
    }, "json");
}
function up(recommendId) {
    var portal = {
        'recommendId' : recommendId,
        'isEffective' : 1
    };
    $.post(ctx + "/portal/update", portal, function(data) {
        if (data.returnValue == 0) {
            alert( '上线成功！');
            $("#portal").bootstrapTable('refresh', param);
        } else {
            alert( '上线失败！');
        }
    }, "json");
}
function getContentList(){
    var contentName =  $('#content_contentName').val();
    var recommendId =  $('#content_recommendId').val();
    initContentTable(recommendId,contentName);
}
//初始化第二张表
function initContentTable(recommendId,contentName) {
    //先销毁表格
    $('#content').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#content").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/content/getPageList", //获取数据的Servlet地址
        striped: false,  //表格显示条纹
        clickToSelect: true,
        pagination: true, //启动分页
        pageSize: 5,  //每页显示的记录数
        pageNumber: 1, //当前第几页
        pageList: [5, 10, 15, 20, 25],  //记录数可选列表
        search: false,  //是否启用查询
        // showColumns: true,  //显示下拉框勾选要显示的列
        showRefresh: false,  //显示刷新按钮
        sidePagination: "server", //表示服务端请求
        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        //设置为limit可以获取limit, offset, search, sort, order
        queryParamsType: "undefined",
        queryParams: function queryParams(params) {   //设置查询参数
            contentparam = {
                page: params.pageNumber,
                rows: params.pageSize,
                belongRecomendId:recommendId,
                contentName:contentName
            };
            return contentparam;
           /* contentparam['page'] =params.pageNumber;
            contentparam['rows'] =params.pageSize;
            contentparam['belongRecomendId'] =recommendId;
            contentparam['recommendName'] = recommendName;
            return contentparam;*/
        },
        columns : [ [ {
            field : 'id',
            title : 'ID',
            width : '2%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        },{
            field : 'contentName',
            title : '大图名称',
            width : '10%',
            align : 'center',
            formatter : function(value) {
                return value;
            }
        },{
            field : 'title',
            title : '标题',
            width : '10%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        }, {
            field : 'contentType',
            title : '推荐位类型',
            width : '8%',
            align : 'center',
            formatter : function(value) {
                var str ="专辑详情";
                if(value == 1){
                    str ="专辑详情";
                }
                if(value == 0){
                    str ="影视专题";
                }
                if(value == 2){
                    str ="应用详情";
                }
                if(value == 3){
                    str ="应用专题";
                }
                if(value == 4){
                    str ="活动详情";
                }
                if(value == 5){
                    str ="启动应用";
                }
                if(value == 6){
                    str ="全屏大图";
                }
                if(value == 7){
                    str ="商品包";
                }
                return str;
            }
        }, {
            field : 'isPlay',
            title : '类型',
            width : '5%',
            align : 'center',
            formatter : function(value) {
                var str="图片";
                if(value == null){
                    value = 0;
                }
                if(value ==1){
                    str="视频";
                }
                return str;
            }
        }, {
            field : 'order',
            title : '顺序调整',
            width : '11%',
            align : 'center',
            formatter : function(value,row) {
                var str = '';
                str += '<a href="javascript:stick(' + row.id+","+row.belongRecomendId+","+row.imageType + ')">置顶</a>';
                str += '&nbsp;|&nbsp; <a href="javascript:moveUp('+row.id+","+row.seq+","+row.belongRecomendId+","+row.imageType+')">上移</a>';
                str += '&nbsp;|&nbsp; <a href="javascript:moveDown('+row.id+","+row.seq+","+row.belongRecomendId+","+row.imageType+')">下移</a>';
                return str;
            }
        }, {
            field : 'do',
            title : '操作',
            width : '8%',
            align : 'center',
            formatter : function(value, row) {
                var str = '';
                str += '<a href="javascript:contentEdit('+row.belongRecomendId+",'"+row.title+"','"+row.secondTitle+"',"+row.isPlay+",'"+row.bigPic+"',"+row.id+","+row.contentType+","+row.contentId+",'"+row.contentName+"','"+row.apkName+'\')">编辑</a>';
                str += '&nbsp;|&nbsp; <a href="javascript:contentdel('+row.recommendId+','+row.id+')">删除</a>';
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
}
function stick(id,belongRecomendId,imageType) {
    var minSeq=parseInt($("#txt_minSeq").val())-1;
    var content={
        'id':id,
        'seq':minSeq
    };
    $.post(ctx + "/content2/update", content, function(data) {
        if (data.returnValue == 0) {
            alert( '置顶成功！');
            // close_contentdiaglog();
            // $("#editcontent_myModal").modal('hide');
            initContent(belongRecomendId,imageType);
            $("#content").bootstrapTable('refresh', contentparam);

        } else {
            alert( '置顶失败！');
        }
    }, "json");
}
function moveUp(id,seq,belongRecomendId,imageType) {
    var content={
        'id':id,
        'seq':seq,
        'belongRecomendId':belongRecomendId,
        'imageType':imageType
    };
    $.post(ctx + "/content2/getMinContent", content, function(data) {
        var content1={
            'id':id,
            'seq':data.seq
        };
        var content2={
            'id':data.id,
            'seq':seq
        };
        $.post(ctx + "/content2/update", content1, function(data) {
            if (data.returnValue == 0) {
                $.post(ctx + "/content2/update", content2, function(data) {
                    if (data.returnValue == 0) {
                        alert( '上移成功！');
                        initContent(belongRecomendId,imageType);
                        $("#content").bootstrapTable('refresh', contentparam);
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
function moveDown(id,seq,belongRecomendId,imageType) {
    var content={
        'id':id,
        'seq':seq,
        'belongRecomendId':belongRecomendId,
        'imageType':imageType
    };
    $.post(ctx + "/content2/getMaxContent", content, function(data) {
        var content1={
            'id':id,
            'seq':data.seq
        };
        var content2={
            'id':data.id,
            'seq':seq
        };
        $.post(ctx + "/content2/update", content1, function(data) {
            if (data.returnValue == 0) {
                $.post(ctx + "/content2/update", content2, function(data) {
                    if (data.returnValue == 0) {
                        alert( '下移成功！');
                        initContent(belongRecomendId,imageType);
                        $("#content").bootstrapTable('refresh', contentparam);
                    } else {
                        alert( '下移失败！');
                    }
                }, "json");
            } else {
                alert( '上移失败！');
            }
        }, "json");
    }, "json");
}
//首张表
function init() {
    $("#btn_add").click(function () {
        $('#myModal').modal({backdrop: 'static', keyboard: false});
    });
}
function  edit(recommendId,recommendName,timeSpan) {
    //var selects = $('#portal').bootstrapTable('getSelections');
    $('#txt_recommendName').val(recommendName);
    $('#txt_timeSpan').val(timeSpan);
    $('#txt_recommendId').val(recommendId);
    $('#myModal').modal({backdrop: 'static', keyboard: false});
}
function  del(recommendId) {
    if (confirm("是否确认删除？")) {
        var portal = {
            'recommendId' : recommendId,
            'isEffective' : -1,
            'recommendType' :0
        };
        $.post(ctx + "/portal/update", portal, function(data) {
            if (data.returnValue == 0) {
                alert( '成功！');
                $("#myModal").modal('hide');
                $("#portal").bootstrapTable('refresh', param);
                if($("#portal").bootstrapTable('getData').length == 1){
                    $("#portal").bootstrapTable('prevPage');
                }
            }/*else if(data.returnValue == 1){
                alert( '数据被launcher关联，不可删除！');
                $("#contnet_myModal").modal('hide');
            }*/ else {
                alert( '失败！');
            }
        }, "json");
    }
}
function portal_submit() {
    var recommendId =$('#txt_recommendId').val();
    var name = $('#txt_recommendName').val();
    var timespan = $('#txt_timeSpan').val();
    if ($.trim(name) == "") {
        $.messager.show(0, "推荐位名称不能为空！！！",2000);
        return;
    }
    if ($.trim(timespan) == "") {
        $.messager.show(0, "轮显时间间隔不能为空！",2000);
        return;
    }
    var portal = {
        'recommendName' : name,
        'timeSpan' : timespan,
        'recommendType' : 0,
        'recommendId' :recommendId
    };
    if(recommendId !=null && recommendId !=''){
        $.post(ctx + "/portal/update", portal, function(data) {
            if (data.returnValue == 0) {
                $.messager.show(0, "修改成功！",2000);
                $("#myModal").modal('hide');
                $("#portal").bootstrapTable('refresh', param);
            } else {
                $.messager.show(0, "修改失败！",2000);
            }
        }, "json");

    }else{
        $.post(ctx + "/portal/save", portal, function(data) {
            if (data.returnValue == 0) {
                $.messager.show(0, "新增成功！",2000);
                $("#myModal").modal('hide');
                $("#portal").bootstrapTable('refresh', param);
            } else {
                $.messager.show(0, "新增失败！",2000);
            }
        }, "json");
    }
}
function  clear_diaglog(){
    $('#txt_recommendName').val('');
    $('#txt_timeSpan').val('');
    $('#txt_recommendId').val('');
    $("#myModal").modal('hide');
}
function  loadContentPage() {
    $("#btn_contentAdd").click(function () {
        beforeAdd();
       var recommendId = $('#content_recommendId').val();
        $('#editcontent_recommendId').val(recommendId);
        dataSource();//初始化一遍页面
        $('#editcontent_myModal').modal({backdrop: 'static', keyboard: false});
    });
}
//内容管理
function  content(recommendId) {
    //绑定事件页面
    initUploadJs("recommendContentImg");
    $('#content_recommendId').val(recommendId);
    initContent(recommendId,0);
    initContentTable(recommendId);
   setTimeout(function(){
       $('#content_myModal').modal({backdrop: 'static', keyboard: false});
   },1500)
}
function  contentEdit(recommendId,title,secondTitle,isPlay,bigPic,id,contentType,contentId,contentName,apkName) {
    $('#txt_title').val(title);
    $('#txt_secondTitle').val(secondTitle);
    $('#txt_isPlay').val(isPlay);
    if(isPlay == 0){
        $('#t').css('display','block');
        $('#s').css('display','none');
        $('#recommendContentImg').attr('src', bigPic);
    }else{
        $('#t').css('display','none');
        $('#s').css('display','block');
        $('#m3u8').val(bigPic);
    }
    $('#txt_recommendId').val(recommendId);
    $('#editcontent_recommendId').val(recommendId);
    $('#contentType').val(contentType);
    $('#edit_id').val(id);
    $('#editcontent_id').val(contentId);
    $('#apkName').val(apkName);
    $('#txt_contentname').val(contentName);
    dataSource();
    $('#editcontent_myModal').modal({backdrop: 'static', keyboard: false});
}
function  contentdel(recommendId,id) {
    if (confirm("是否确认删除？")) {
        var portal = {
            'recommendId' : recommendId,
            'id':id
        };
        $.post(ctx + "/content/del", portal, function(data) {
            if (data.returnValue == 0) {
                $.messager.show(0, "成功！",2000);
                $("#contnet_myModal").modal('hide');
                $("#content").bootstrapTable('refresh', param);
                if($("#content").bootstrapTable('getData').length == 1){
                    $("#content").bootstrapTable('prevPage');
                }
            } else {
                $.messager.show(0, "失败！",2000);
            }
        }, "json");
    }
}