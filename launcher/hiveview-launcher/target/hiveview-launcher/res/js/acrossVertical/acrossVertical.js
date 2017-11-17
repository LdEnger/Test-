/**
 * Created by Administrator on 2017/7/24.
 */
$(document).ready(function () {
    initTable();
    initCombobox();
    init();
});
var param ={};
var contentparam = {};
var type ={};
function init() {
    $("#btn_add").click(function () {
        $("#addModal").modal({backdrop: 'static', keyboard: false});
    });
}
/*function add() {
    //addclear_diaglog();
    $("#addModal").modal({backdrop: 'static', keyboard: false});

}*/
function addclear_diaglog() {
    $('#txt_recommendId').val('');
    $('#txt_recommendName').val('');
    $("#addModal").modal('hide');
}
function initContent1(belongRecomendId,imageType) {
    $.ajax({
        type:"get",
        data:{'belongRecomendId':belongRecomendId,'imageType':imageType},
        url:ctx + "/content2/getMinSeq",
        success:function (res) {
            $('#txt_minSeq1').val(res);
        }
    });
}
//显示横图模板
function across(belongRecomendId,imageType) {
    initUploadJs("recommendContentImg");
    initContent1(belongRecomendId,imageType);
    initArcossTable(belongRecomendId,imageType,null);
    $("#txt_belongRecommendId1").val(belongRecomendId);
    $('#editcontent_recommendId').val(belongRecomendId);
    $("#imageType1").val(imageType);
    type['imageType'] =imageType;
    setTimeout(function(){
        $("#acrossModal").modal({backdrop: 'static', keyboard: false});
    },1500);

}
//通过横图名称查询
function selectArcoss() {
    var belongRecommendId=$("#txt_belongRecommendId1").val();
    var imageType=$("#imageType1").val();
    var contentName=$("#txt_acrossName").val();
    initArcossTable(belongRecommendId,imageType,contentName);
}
//关闭横图模板
function acrossclear_diaglog() {
    $("#txt_belongRecommendId1").val('');
    $("#imageType1").val('');
    $("#txt_minSeq1").val('00');
    $("#acrossModal").modal('hide');
}
//通过横图名称查询
function selectVertical() {
    var belongRecommendId=$("#txt_belongRecommendId2").val();
    var imageType=$("#imageType2").val();
    var contentName=$("#txt_verticalName").val();
    initVerticalTable(belongRecommendId,imageType,contentName);
}
function initContent2(belongRecomendId,imageType) {
    $.ajax({
        type:"get",
        data:{'belongRecomendId':belongRecomendId,'imageType':imageType},
        url:ctx + "/content2/getMinSeq",
        success:function (res) {
            $('#txt_minSeq2').val(res);
        }
    });
}
//显示竖图模板
function vertical(belongRecomendId,imageType) {
    initUploadJs("recommendContentImg");
    initContent2(belongRecomendId,imageType);
    initVerticalTable(belongRecomendId,imageType,null);
    $("#txt_belongRecommendId2").val(belongRecomendId);
    $('#editcontent_recommendId').val(belongRecomendId);
    $("#imageType2").val(imageType);
    type['imageType'] =imageType;
   setTimeout(function(){
       $("#verticalModal").modal({backdrop: 'static', keyboard: false});
   },1500);

}
//关闭竖图模板
function verticalsclear_diaglog() {
    $("#txt_belongRecommendId2").val('');
    $("#imageType2").val('');
    $("#txt_minSeq2").val('00')
    $("#verticalModal").modal('hide');
}
function selectIsEffective() {
    var isEffective=$("#txt_selectIsEffective").val();
    var recommendName=$("#txt_selectName").val();
    initTable(isEffective,recommendName);
}

//初始化横竖图Table
function initTable(isEffective,recommendName) {
    //先销毁表格
    $('#sample_1').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#sample_1").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/acrossVertical/getPageList", //获取数据的Servlet地址
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
                isEffective:isEffective,
                recommendName:recommendName
            };
            return param;
        },
        columns : [ [ {
            field : 'recommendId',
            title : 'ID',
            width : '8%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        }, {
            field : 'recommendName',
            title : '横竖图推荐位名称',
            width : '18%',
            align : 'center',
            formatter : function(value) {
                return value;
            }
        }, {
            field : 'count1',
            title : '横图推荐位数量',
            width : '10%',
            align : 'center',
            formatter : function(value) {
                return value;
            }
        }, {
            field : 'count2',
            title : '竖图推荐位数量',
            width : '10%',
            align : 'center',
            formatter : function(value) {
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
                    str += '&nbsp;|&nbsp;&nbsp;<a href="javascript:down('+row.recommendId+')" class="btn btn-default btn-xs">下线</a>';
                }else if(value == 0) {
                    str += '<a href="javascript:up('+row.recommendId+')" class="btn btn-primary btn-xs">上线</a>';
                    str += '&nbsp;|&nbsp;&nbsp;<span style="color:#808080">下线</span>';
                }
                return str;
            }
        }, {
            field : 'do',
            title : '操作',
            width : '23%',
            align : 'center',
            formatter : function(value, row) {
                var str = '';
                str += '<a href="javascript:edit('+row.recommendId+",'"+row.recommendName+"'"+')">编辑</a>';
                str += '&nbsp;|&nbsp; <a href="javascript:across('+row.recommendId+',1)">编辑横图</a>';
                str += '&nbsp;|&nbsp; <a href="javascript:vertical('+row.recommendId+',2)">编辑竖图</a>';
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
        'recommendType' :1
    };
    $.post(ctx + "/acrossVertical/update", portal, function(data) {
        if (data.returnValue == 0) {
            alert( '下线成功！');
            $("#sample_1").bootstrapTable('refresh', param);
        }/*else if(data.returnValue == 1){
            alert('数据被launcher关联，不可下线！');
        }*/else {
            alert( '下线失败！');
        }
    }, "json");
}
function up(recommendId) {
    var portal = {
        'recommendId' : recommendId,
        'isEffective' : 1
    };
    $.post(ctx + "/acrossVertical/update", portal, function(data) {
        if (data.returnValue == 0) {
            alert( '上线成功！');
            $("#sample_1").bootstrapTable('refresh', param);
        } else {
            alert( '上线失败！');
        }
    }, "json");
}
function  edit(recommendId,recommendName) {
    $('#txt_recommendName').val(recommendName);
    $('#txt_recommendId').val(recommendId);
    $('#addModal').modal({backdrop: 'static', keyboard: false});

}
function acrossVertical_submit() {
    var recommendId =$('#txt_recommendId').val();
    var name = $('#txt_recommendName').val();
    if ($.trim(name) == "") {
        alert("推荐位名称不能为空！！");
        return;
    }
    var portal = {
        'recommendName' : name,
        'recommendType' : 1,
        'recommendId' :recommendId
    };
    if(recommendId !=null && recommendId !=''){
        console.log(portal);
        $.post(ctx + "/acrossVertical/update", portal, function(data) {
            if (data.returnValue == 0) {
                alert( '修改成功！');
                addclear_diaglog();
                $("#addModal").modal('hide');
                $("#sample_1").bootstrapTable('refresh', param);
            } else {
                alert( '修改失败！');
                addclear_diaglog();
            }
        }, "json");

    }else{
        $.post(ctx + "/acrossVertical/save", portal, function(data) {
            if (data.returnValue == 0) {
                alert( '新增成功！');
                addclear_diaglog();
                $("#addModal").modal('hide');
                $("#sample_1").bootstrapTable('refresh', param);
            } else {
                alert( '新增失败！');
                addclear_diaglog();
            }
        }, "json");
    }
}
function  del(recommendId) {
    if (confirm("是否确认删除？")) {
        var portal = {
            'recommendId' : recommendId,
            'isEffective' : -1,
            'recommendType' :1
        };
        $.post(ctx + "/acrossVertical/update", portal, function(data) {
            if (data.returnValue == 0) {
                alert( '成功！');
                $("#sample_1").bootstrapTable('refresh', param);
                if($("#sample_1").bootstrapTable('getData').length == 1){
                    $("#sample_1").bootstrapTable('prevPage');}
            }/*else if(data.returnValue == 1){
                alert( '数据被launcher关联，不可删除！');
            } */else {
                alert( '失败！');
            }
        }, "json");
    }
}
//初始化横图Table
function initArcossTable(belongRecomendId,imageType,contentName) {

    //先销毁表格
    $('#sample_arcoss').bootstrapTable('destroy');

    //初始化表格,动态从服务器加载数据
    $("#sample_arcoss").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/content2/getPageList", //获取数据的Servlet地址
        striped: true,  //表格显示条纹
        clickToSelect: false,
        pagination: true, //启动分页
        pageSize: 5,  //每页显示的记录数
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
            contentparam = {
                page: params.pageNumber,
                rows: params.pageSize,
                belongRecomendId:belongRecomendId,
                imageType:imageType,
                contentName:contentName
            };
            return contentparam;
        },
        columns : [ [ {
            field : 'id',
            title : 'ID',
            width : '8%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        }, {
            field : 'contentName',
            title : '横图名称',
            width : '15%',
            align : 'center',
            formatter : function(value) {
                return value;
            }
        }, {
            field : 'title',
            title : '标题',
            width : '13%',
            align : 'center',
            formatter : function(value) {
                return value;
            }
        }, {
            field : 'contentType',
            title : '推荐位类型',
            width : '13%',
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
            field : 'order',
            title : '顺序调整',
            width : '17%',
            align : 'center',
            formatter : function(value,row) {
                    var str = '';
                    str += '<a href="javascript:stickAcross(' + row.id+","+row.belongRecomendId+","+row.imageType + ')">置顶</a>';
                    str += '&nbsp;|&nbsp; <a href="javascript:moveUp('+row.id+","+row.seq+","+row.belongRecomendId+","+row.imageType+')">上移</a>';
                    str += '&nbsp;|&nbsp; <a href="javascript:moveDown('+row.id+","+row.seq+","+row.belongRecomendId+","+row.imageType+')">下移</a>';
                    return str;
            }
        }, {
            field : 'do',
            title : '操作',
            width : '13%',
            align : 'center',
            formatter : function(value, row) {
                var str = '';
                str += '<a href="javascript:editContent('+row.belongRecomendId+",'"+row.title+"','"+row.secondTitle+"',"+row.isPlay+",'"+row.bigPic+"',"+row.id+","+row.contentType+","+row.contentId+",'"+row.contentName+"','"+row.apkName+"'"+')">编辑</a>';
                str += '&nbsp;|&nbsp; <a href="javascript:delContent('+row.id+')">删除</a>';
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

//初始化竖图Table
function initVerticalTable(belongRecomendId,imageType,contentName) {

    //先销毁表格
    $('#sample_vertical').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#sample_vertical").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/content2/getPageList", //获取数据的Servlet地址
        striped: true,  //表格显示条纹
        clickToSelect: false,
        pagination: true, //启动分页
        pageSize: 5,  //每页显示的记录数
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
            contentparam = {
                page: params.pageNumber,
                rows: params.pageSize,
                belongRecomendId:belongRecomendId,
                imageType:imageType,
                contentName:contentName
            };
            return contentparam;
        },
        columns : [ [ {
            field : 'id',
            title : 'ID',
            width : '8%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        }, {
            field : 'contentName',
            title : '竖图名称',
            width : '15%',
            align : 'center',
            formatter : function(value) {
                return value;
            }
        }, {
            field : 'title',
            title : '标题',
            width : '13%',
            align : 'center',
            formatter : function(value) {
                return value;
            }
        }, {
            field : 'contentType',
            title : '推荐位类型',
            width : '13%',
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
            field : 'order',
            title : '顺序调整',
            width : '17%',
            align : 'center',
            formatter : function(value,row) {
                var str = '';
                str += '<a href="javascript:stickVertical('+row.id+","+row.belongRecomendId+","+row.imageType+')">置顶</a>';
                str += '&nbsp;|&nbsp; <a href="javascript:moveUp('+row.id+","+row.seq+","+row.belongRecomendId+","+row.imageType+')">上移</a>';
                str += '&nbsp;|&nbsp; <a href="javascript:moveDown('+row.id+","+row.seq+","+row.belongRecomendId+","+row.imageType+')">下移</a>';
                return str;
            }
        }, {
            field : 'do',
            title : '操作',
            width : '13%',
            align : 'center',
            formatter : function(value, row) {
                var str = '';
                str += '<a href="javascript:editContent('+row.belongRecomendId+",'"+row.title+"','"+row.secondTitle+"',"+row.isPlay+",'"+row.bigPic+"',"+row.id+","+row.contentType+","+row.contentId+",'"+row.contentName+"','"+row.apkName+"'"+')">编辑</a>';
                str += '&nbsp;|&nbsp; <a href="javascript:delContent('+row.id+')">删除</a>';
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
function stickAcross(id,belongRecomendId,imageType) {
    var minSeq=parseInt($("#txt_minSeq1").val())-1;
    var content={
        'id':id,
        'seq':minSeq
    };
    $.post(ctx + "/content2/update", content, function(data) {
        if (data.returnValue == 0) {
            alert( '置顶成功！');
            // close_contentdiaglog();
            // $("#editcontent_myModal").modal('hide');
            initContent1(belongRecomendId,imageType);
            $("#sample_arcoss").bootstrapTable('refresh', contentparam);

        } else {
            alert( '置顶失败！');
        }
    }, "json");
}
function stickVertical(id,belongRecomendId,imageType) {
    var minSeq=parseInt($("#txt_minSeq2").val())-1;
    var content={
        'id':id,
        'seq':minSeq
    };
    $.post(ctx + "/content2/update", content, function(data) {
        if (data.returnValue == 0) {
            alert( '置顶成功！');
            //close_contentdiaglog();
            //$("#editcontent_myModal").modal('hide');
            initContent2(belongRecomendId,imageType);
            $("#sample_vertical").bootstrapTable('refresh', contentparam);

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
                       // close_contentdiaglog();
                        //$("#editcontent_myModal").modal('hide');
                        initContent1(belongRecomendId,imageType);
                        initContent2(belongRecomendId,imageType);
                        $("#sample_arcoss").bootstrapTable('refresh', contentparam);
                        $("#sample_vertical").bootstrapTable('refresh', contentparam);

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
                        //close_contentdiaglog();
                        //$("#editcontent_myModal").modal('hide');
                        initContent1(belongRecomendId,imageType);
                        initContent2(belongRecomendId,imageType);
                        $("#sample_arcoss").bootstrapTable('refresh', contentparam);
                        $("#sample_vertical").bootstrapTable('refresh', contentparam);
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
function  delContent(id) {
    if (confirm("是否确认删除？")) {
        var content = {
            'id' : id
        };
        $.post(ctx + "/content2/del", content, function(data) {
            if (data.returnValue == 0) {
                alert( '成功！');
                $("#sample_arcoss").bootstrapTable('refresh', contentparam);
                $("#sample_vertical").bootstrapTable('refresh', contentparam);
                $("#sample_1").bootstrapTable('refresh', param);
            }else {
                alert( '失败！');
            }
        }, "json");
    }
}
//内容管理
function  content(recommendId) {
    //绑定事件页面
    initUploadJs("recommendContentImg");
    $('#content_recommendId').val(recommendId);
    initContentTable(recommendId);
    $('#content_myModal').modal({backdrop: 'static', keyboard: false});

}
function addAcross() {
    dataSource();//初始化一遍页面
    beforeAdd();
    var belongRecomendId=$("#txt_belongRecommendId1").val();
    var imageType=$("#imageType1").val();
    $("#imageSize").html("(图片大小  350x230)");
    $('#editcontent_myModal').modal({backdrop: 'static', keyboard: false});

}
function addVertical() {
    dataSource();//初始化一遍页面
    beforeAdd();
    var belongRecomendId=$("#txt_belongRecommendId2").val();
    var imageType=$("#imageType2").val();
    $("#imageSize").html("(图片大小  350x440)");
    $('#editcontent_myModal').modal({backdrop: 'static', keyboard: false});

}

function  editContent(recommendId,title,secondTitle,isPlay,bigPic,id,contentType,contentId,contentName,apkName) {
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
    if(type['imageType']==1){
        $("#imageSize").html("(图片大小  350x230)");
    }
    if(type['imageType']==2){
        $("#imageSize").html("(图片大小  350x440)");
    }
    $('#editcontent_myModal').modal({backdrop: 'static', keyboard: false});
}
function submitByImage(){
    content_submit(type['imageType'])
}
