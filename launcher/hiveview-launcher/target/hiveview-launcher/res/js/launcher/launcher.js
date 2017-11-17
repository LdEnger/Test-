/**
 * Created by Administrator on 2017/7/27.
 */
$(document).ready(function () {
    initTable();

    var autoJson={};
    var logoJson={};
    //var bigImageJson={};
    //var smallImageJson={};
    //var blockJson={};
    optionAutoId();
    //optionBigImageId();
    //optionSmallImageId();
    //optionBlockId();
    optionLogoId();
    generateInputDialog();
});


function optionAutoId() {
    $.ajax({
        type:"get",
        async: false,
        url:ctx + "/templet/getStartList",
        data:{isEffective:1,startType:1},
        dataType:"json",
        success:function (res) {
            autoJson=res;
            $("#txt_autoId").empty();
            var select=$("<option value=''>-请选择-</option>");
            $("#txt_autoId").append(select);
            $.each(res, function (index, element) {
                var $option = $("<option value='" + element['id'] + "'>" + element['appName'] + "</option>");
                $("#txt_autoId").append($option);
            });
        }
    });
}
function autoToMap(autoJson,autoMap) {
    $.each(autoJson, function (index, element) {
        autoMap[element.id]=element.appName;
    });
}
function optionLogoId() {
    $.ajax({
        type:"get",
        async: false,
        url:ctx + "/templet/getLogoList",
        data:{isOnline:1},
        dataType:"json",
        success:function (res1) {
            logoJson=res1;
            $("#txt_logoId").empty();
            var select=$("<option value=''>-请选择-</option>");
            $("#txt_logoId").append(select);
            $.each(res1, function (index, element) {
                var $option1 = $("<option value='" + element['logoId'] + "'>" + element['logoName'] + "</option>");
                $("#txt_logoId").append($option1);
            });
        }
    });
}
function logoToMap(logoJson,logoMap) {
    $.each(logoJson, function (index, element) {
        logoMap[element.logoId]=element.logoImg;
    });
}
/*function optionBigImageId() {
    $.ajax({
        type:"get",
        async: false,
        url:ctx + "/templet/getImageList",
        data:{recommendType:0,isEffective:1},
        dataType:"json",
        success:function (res2) {
            bigImageJson=res2;
            $("#txt_bigImageId").empty();
            var select=$("<option value=''>-请选择-</option>");
            $("#txt_bigImageId").append(select);
            $.each(res2, function (index, element) {
                var $option2 = $("<option value='" + element['recommendId'] + "'>" + element['recommendName'] + "</option>");
                $("#txt_bigImageId").append($option2);
            });
        }
    });
}
function bigImageToMap(bigImageJson,bigImageMap) {
    $.each(bigImageJson, function (index, element) {
        bigImageMap[element.recommendId]=element.recommendName;
    });
}
function optionSmallImageId() {
    $.ajax({
        type:"get",
        async: false,
        url:ctx + "/templet/getImageList",
        data:{recommendType:1,isEffective:1},
        dataType:"json",
        success:function (res2) {
            smallImageJson=res2;
            $("#txt_smallImageId").empty();
            var select=$("<option value=''>-请选择-</option>");
            $("#txt_smallImageId").append(select);
            $.each(res2, function (index, element) {
                var $option2 = $("<option value='" + element['recommendId'] + "'>" + element['recommendName'] + "</option>");
                $("#txt_smallImageId").append($option2);
            });
        }
    });
}
function smallImageToMap(smallImageJson,smallImageMap) {
    $.each(smallImageJson, function (index, element) {
        smallImageMap[element.recommendId]=element.recommendName;
    });
}*/
/*function optionBlockId() {
    $.ajax({
        type:"get",
        async: false,
        url:ctx + "/templet/getBlockList",
        dataType:"json",
        success:function (res2) {
            blockJson=res2;
            $("#txt_blockId").empty();
            var select=$("<option value=''>-请选择-</option>");
            $("#txt_blockId").append(select);
            $.each(res2, function (index, element) {
                var $option2 = $("<option value='" + element['curdID'] + "'>" + element['curdName'] + "</option>");
                $("#txt_blockId").append($option2);
            });
        }
    });
}
function blockToMap(blockJson,blockMap) {
    $.each(blockJson, function (index, element) {
        blockMap[element.curdID]=element.curdName;
    });
}*/



var param ={};
//显示添加templet框
function addTemplet() {
    //templetclear_diaglog();
    $('#addTempletModal').modal({backdrop: 'static', keyboard: false});
}
//隐藏添加templet框
function templetclear_diaglog() {
    $("#txt_id").val('');
    $("#txt_name").val('');
    $("#txt_logoId").val('');
    $("#txt_autoId").val('');
    //$("#txt_blockId").val('');
    //$("#txt_bigImageId").val('');
    //$("#txt_smallImageId").val('');
    //$("#txt_isHihe").val('');
    $('#addTempletModal').modal('hide');
}
//显示添加templetTab框
function addTempletTab() {
    //templetContentclear_diaglog();
    var  belongTempletId=$("#txt_belongTempletId").val();
    initTabTable();
    $('#addTempletTabModal').modal({backdrop: 'static', keyboard: false});
}
//隐藏添加templet
//   Content框
function templetTabclear_diaglog() {
    // setTimeout(fucntion(){
       //$("#txt_contentId").val('');
        //$("#txt_recommendId").val('');
        //$("#txt_recommendType").val(1);
        //$("#txt_operType").val('');
        //$("#txt_recomName").val('');
        $("#txt_tabName").val('');
        $('#addTempletTabModal').modal('hide');
    // },500)
}
/*//显示选择推荐位类型框
function selectFixedRecom() {
    if($("#txt_recommendType").val()==1){
        initFixedRecomTable();
    }else if($("#txt_recommendType").val()==2){
        initCustomRecomTable();
    }
    setTimeout(function(){
        $('#fixedRecomModal').modal({backdrop: 'static', keyboard: false});
    },1500);
}*/
//隐藏选择推荐位类型框
function fixedRecomclear_diaglog() {
    $('#fixedRecomModal').modal('hide');
}
//显示关联推荐位框
function templetTabTable(templetId){
            $("#txt_belongTempletId").val(templetId);
            initTempletTabTable(templetId);
            setTimeout(function () {
                $("#tabManageModal").modal({backdrop: 'static', keyboard: false});
            },1500);
}
//隐藏关联推荐位框
function tabManageclear_diaglog() {
    $("#txt_belongTempletId").val('');
    $("#tabManageModal").modal('hide');
}
//显示show
function show(id,templetName,logoId,autoId/*,blockId,bigImageId,smallImageId,isHide*/) {
    initShowTable(id);
    var logoMap={};
    var autoMap={};
    //var bigImageMap={};
    //var smallImageMap={};
    //var blockMap={};
    autoToMap(autoJson,autoMap);
    logoToMap(logoJson,logoMap);
    //bigImageToMap(bigImageJson,bigImageMap);
    //smallImageToMap(smallImageJson,smallImageMap);
    //blockToMap(blockJson,blockMap);
    $("#txtShow_name").html(templetName);
    $("#txtShow_autoId").html(autoMap[autoId]);
    var img=$("<img style='width: 60px;height: 60px; border: 1px solid #CCCCCC;' src='"+ logoMap[logoId] +"'>");
    $("#txtShow_logoId").html(img);
    //$("#txtShow_bigImageId").html(bigImageMap[bigImageId]);
    //$("#txtShow_smallImageId").html(smallImageMap[smallImageId]);
    //$("#txtShow_blockId").html(blockMap[blockId]);
    // if(isHide==0){
    //     $("#txtShow_isHihe").html("隐藏");
    // }else if(isHide==1){
    //     $("#txtShow_isHihe").html("展示");
    // }
    $("#showModal").modal({backdrop: 'static', keyboard: false});
}
function showClear_diaglog() {
    $("#showModal").modal('hide');
}

function initTable() {
    //先销毁表格
    $('#sample_1').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#sample_1").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/templet/getPageList", //获取数据的Servlet地址
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
                rows: params.pageSize
            };
            return param;
        },
        columns : [ [ {
            field : 'templetName',
            title : '模板名称',
            width : '12%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        }, {
            field : 'type',
            title : '控制类型',
            width : '12%',
            align : 'center',
            formatter : function(value) {
                var str='';
                if(value==1){
                    str='IP地址';
                }else if(value==2){
                    str="MACSN";
                }else if(value==3){
                    str="硬件型号";
                }else if(value==4){
                    str="rom版本";
                }else if(value==5){
                    str="硬件型号+IP地址";
                }
                return str;

            }
        }, {
            field : 'cityNames',
            title : '配置区域',
            width : '15%',
            align : 'center',
            formatter : function(value) {
                return value;
            }
        }, {
            field : 'do',
            title : '操作',
            width : '25%',
            align : 'center',
            formatter : function(value, row) {
                var str = '';
                str += '<a href="javascript:edit('+row.id+",'"+row.templetName+"',"+row.logoId+","+row.autoId/*+","+row.blockId+","+row.bigImageId+","+row.smallImageId+","+row.isHide*/+')">编辑</a>';
                if(row.id != 1){
                    str += '&nbsp;|&nbsp; <a href="javascript:cityManage('+row.id+",'"+row.templetName+"',"+row.type+",'"+row.cityNames+"'"+')">区域配置</a>';
                }
                str += '&nbsp;|&nbsp; <a href="javascript:templetTabTable('+row.id+')">关联导航栏</a>';
                str += '&nbsp;|&nbsp; <a href="javascript:show('+row.id+",'"+row.templetName+"',"+row.logoId+","+row.autoId/*+","+row.blockId+","+row.bigImageId+","+row.smallImageId+","+row.isHide*/+')">查看</a>';
                str += '&nbsp;|&nbsp; <a href="javascript:copy('+row.id+",'"+row.templetName+"',"+row.logoId+","+row.autoId+","+row.isHide+')">复制</a>';
                if(row.id != 1){
                    str += '&nbsp;|&nbsp; <a href="javascript:del('+row.id+')">删除</a>';
                }
                return str;
            }
        } ] ],
        onClickCell: function (field,value ,row, td) {
            $(td.parent()[0]).css({"background":"rgb(255,184,72)"}).siblings().css({"background":"none"});
        },

        onLoadSuccess: function () {  //加载成功时执行
            //layer.msg("加载成功");
        },
        onLoadError: function () {  //加载失败时执行
            // layer.msg("加载数据失败", {time: 1500, icon: 2});
        }
    });
}


function  edit(id,templetName,logoId,autoId/*,blockId,bigImageId,smallImageId,isHide*/) {
    $("#txt_id").val(id);
    $("#txt_name").val(templetName);
    $("#txt_logoId").val(logoId);
    $("#txt_autoId").val(autoId);
    //$("#txt_blockId").val(blockId);
    //$("#txt_bigImageId").val(bigImageId);
    //$("#txt_smallImageId").val(smallImageId);
    //$("#txt_isHide").val(isHide);
    $('#addTempletModal').modal({backdrop: 'static', keyboard: false});
}
function  del(id) {
    if (confirm("是否确认删除？")) {
        var templet = {
            'id' : id,
        };
        $.post(ctx + "/templet/del", templet, function(data) {
            if (data.returnValue == 0) {
                alert( '成功！');
                $("#addModal").modal('hide');
                $("#sample_1").bootstrapTable('refresh', param);
                if($("#sample_1").bootstrapTable('getData').length == 1){
                    $("#sample_1").bootstrapTable('prevPage');
                }
            } else {
                alert( '失败！');
            }
        }, "json");
    }
}
//复制launcher
function copy(id,templetName,logoId,autoId,isHide) {
    var templet={
        'id' : id,
        'templetName' : templetName,
        'logoId':logoId,
        'autoId' : autoId,
        'isHide':isHide
    }
    $.post(ctx + "/templet/addCopy", templet, function(data) {
        if (data.returnValue == 0) {
            alert( '复制成功！');
            $("#addTempletModal").modal('hide');
            templetclear_diaglog();
            $("#sample_1").bootstrapTable('refresh', param);
        } else {
            alert( '复制失败！');
        }
    }, "json");
}

function templet_submit() {
    var id = $("#txt_id").val();
    var templetName = $("#txt_name").val();
    var logoId = $("#txt_logoId").val();
    var autoId = $("#txt_autoId").val();
    if(autoId==""){
        autoId=-1;
    }
    //var blockId = $("#txt_blockId").val();
    //var bigImageId = $("#txt_bigImageId").val();
    //var smallImageId = $("#txt_smallImageId").val();
    var isHide=1;

    if ($.trim(templetName) == "") {
        alert("模板名称不能为空！");
        return;
    }
    /*if($.trim(logoId)==""){
        alert('请选择牌照LOGO！');
        return;
    }*/
    /*if($.trim(blockId)==""){
        alert('请选择豆腐块列表！');
        return;
    }*/
    /*if($.trim(bigImageId)==""){
        alert('请选择首屏大图推荐位！');
        return;
    }
    if($.trim(smallImageId)==""){
        alert('请选择首屏横竖图推荐位！');
        return;
    }*/
    var templet = {
        'id' : id,
        'templetName' : templetName,
        'logoId':logoId,
        'autoId' : autoId,
        //'blockId' :blockId,
        //'bigImageId':bigImageId,
        //'smallImageId':smallImageId,
        'isHide':isHide
    };

    if(id !=null && id !=''){
        $.post(ctx + "/templet/update", templet, function(data) {
            if (data.returnValue == 0) {
                alert( '修改成功！');
                templetclear_diaglog();
                $("#addTempletModal").modal('hide');
                $("#sample_1").bootstrapTable('refresh', param);
            } else {
                alert( '修改失败！');
            }
        }, "json");

    }else{
        $.post(ctx + "/templet/save", templet, function(data) {
            if (data.returnValue == 0) {
                alert( '新增成功！');
                $("#addTempletModal").modal('hide');
                templetclear_diaglog();
                $("#sample_1").bootstrapTable('refresh', param);
            } else {
                alert( '新增失败！');
            }
        }, "json");
    }
}

//通过Tab名称查询
function selectTabByTabName(){
    var tabName=$("#txt_tabName").val();
    initTabTable(tabName);
}

//初始化选择Tab页面列表
var selections=[];
function  initTabTable(tabName) {
    selections=[];
    //定义存储数据的变量
    var selectionIds=[];
    //先销毁表格
    $('#sample_3').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#sample_3").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/tab/getPageList", //获取数据的Servlet地址
        striped: true,  //表格显示条纹
        clickToSelect: false,
        pagination: true, //启动分页
        pageSize: 3,  //每页显示的记录数
        pageNumber: 1, //当前第几页
        //pageList: [5, 10, 15, 20, 25],  //记录数可选列表
        search: false,  //是否启用查询
        //showColumns: true,  //显示下拉框勾选要显示的列
        showRefresh: false,  //显示刷新按钮
        sidePagination: "server", //表示服务端请求
        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        //设置为limit可以获取limit, offset, search, sort, order
        queryParamsType: "undefined",
        queryParams: function queryParams(params) {//设置查询参数

            param = {
                page: params.pageNumber,
                rows: params.pageSize,
                tabName:tabName,
                isEffective:1
            };
            return param;
        },
        columns : [[{
            title : '多选',
            field: 'checkStatus',
            checkbox: true,
            width : '5%',
            align : 'center'
        },{
            field :'id',
            title : 'ID',
            width : '5%',
            align : 'center',
            formatter : function(value){
                return value;
            }

        },{
            field : 'tabName',
            title : 'Tab名称',
            width  : '12%',
            align : 'center',
            formatter : function (value){
                return value;
            }
        },{
            field : 'tabTitle',
            title : 'Tab标题名称',
            width  : '12%',
            align : 'center',
            formatter : function (value){
                return value;
            }
        },{
            field : 'tabIcon',
            title : '导航栏图标',
            width  : '12%',
            align : 'center',
            formatter : function (value){
                return "<img style='height:80px;width:100px;' src='" + value + "'/>";
            }
        }]],
        onClickCell: function (field,value ,row, td) {
            $(td.parent()[0]).css({"background":"rgb(255,184,72)"}).siblings().css({"background":"none"});
        },
        responseHandler:function(res){
            $.each(res.rows, function (i, row) {
                row.checkStatus = $.inArray(row.id, selectionIds) != -1;
                //console.log($.inArray(row.id, selectionIds) != -1,row);
            });
            // console.log(res,"after get response and reset data")
            return res;
        },
        onCheck:function(row){
            selectionIds.push(row.id);
            selections.push(row);
            //console.log(selectionIds," after add a element")
        },
        onUncheck:function(row){
            var index = $.inArray(row.id,selectionIds);
            selectionIds.splice(index, 1);
            selections.splice(index,1);
            //console.log(selectionIds," after remove a element")
        },
        onCheckAll:function(rows){
            $.each(rows,function(i,row){
                var unExist = $.inArray(row.id,selectionIds) == -1;
                if(unExist){
                    selectionIds.push(row.id);
                    selections.push(row);
                }
            });
            //console.log(selectionIds," after add  elements")
        },
        onUncheckAll:function(rows){
            $.each(rows,function(i,row){
                if(!row.checkStatus){
                    var index = $.inArray(row.id,selectionIds);
                    selectionIds.splice(index, 1);
                    selections.splice(index,1);
                }
            });
            //console.log(selectionIds," after remove  elements")
        }
        /*onClickRow: function(row) {
            addTempletTabSubmit(row.id,row.tabName,row.tabIcon);
            templetTabclear_diaglog();
        }*/
    });
}
/*function addTempletTabSubmit(tabId,tabName,tabIcon){
    var tabId = tabId;
    var tabName = tabName;
    var tabIcon = tabIcon;
    var createTime = new Date();
    var belongTempletId = $("#txt_belongTempletId").val();

    var templetTab = {
        'tabId' : tabId,
        'tabName' : tabName,
        'tabIcon' : tabIcon,
        'createTime':createTime,
        'belongTempletId' : belongTempletId,
        'isEffective':1
    };
    console.log(templetTab);
    $.post(ctx + "/templetTab/save", templetTab, function(data) {
        if (data.returnValue == 0) {
            alert( '新增成功！');
            $("#sample_2").bootstrapTable('refresh', param);
        } else {
            alert( '新增失败！');
        }
    }, "json");
}*/
/*保存选项*/
function addTabGroup_submit() {
    var selGroups= selections;
    if(selGroups.length<=0){
        alert("您还没有勾选任何信息！");
    }else{
        var successCount=0;//统计添加成功的条数
        var add_params=new Array;
        for(var i=0;i<selGroups.length;i++){
            var add_param;
            var createTime = new Date();
            add_param = {
                'tabId' : selGroups[i].id,
                'tabName' : selGroups[i].tabName,
                'tabIcon' : selGroups[i].tabIcon,
                'createTime':createTime,
                'belongTempletId' : $("#txt_belongTempletId").val(),
                'isEffective':1
            }
            console.log(add_param);
            add_params.push(add_param);

        }
        console.log(selGroups);
        $.post(ctx + "/templetTab/save", {add_params:JSON.stringify(add_params)}, function(data) {
            if (data.returnValue == 0) {
                alert("添加成功！");
                selections=[];
                $("#sample_2").bootstrapTable('refresh', param);
                templetTabclear_diaglog();
            }else if(data.returnValue == 1){
                alert("有重复Tab添加，请重新选择！");
            }else {
                alert("添加失败！");

            }
        }, "json");

    }
}

//初始化关联推荐位列表
function initTempletTabTable(belongTempletId) {
    //先销毁表格
    $('#sample_2').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#sample_2").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/templetTab/getPageList", //获取数据的Servlet地址
        striped: true,  //表格显示条纹
        clickToSelect: false,
        pagination: true, //启动分页
        pageSize: 5,  //每页显示的记录数
        pageNumber: 1, //当前第几页
        pageList: [5],  //记录数可选列表
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
                belongTempletId:belongTempletId
            };
            return param;
        },
        columns : [ [ {
            field : 'seq',
            title : '排序',
            width : '8%',
            align : 'center',
            formatter : function(value, row, index) {
                return (param.page-1)*5+index+1;
            }
        }, {
            field : 'tabName',
            title : 'Tab名称',
            width : '17%',
            align : 'center',
            formatter : function(value) {
                return value;
            }
        },{
            field : 'tabTitle',
            title : 'Tab标题名称',
            width  : '17%',
            align : 'center',
            formatter : function (value){
                return value;
            }
        }, {
            field : 'order',
            title : '顺序调整',
            width : '20%',
            align : 'center',
            formatter : function(value,row) {
                var str='';
                str += '<a href="javascript:moveTop('+row.id+","+row.seq+","+row.belongTempletId+","+row.seqIsTop+')">置顶</a>';
                str += '&nbsp;|&nbsp;<a href="javascript:moveUp('+row.id+","+row.seq+","+row.belongTempletId+","+row.seqIsTop+')">上移</a>';
                str += '&nbsp;|&nbsp;<a href="javascript:moveDown('+row.id+","+row.seq+","+row.belongTempletId+","+row.seqIsTop+')">下移</a>';
                return str;
            }
        }, {
            field : 'do',
            title : '操作',
            width : '13%',
            align : 'center',
            formatter : function(value, row) {
                var str = '';
                //str += '<a href="javascript:editTab('+row.id+","+row.recommendType+",'"+row.recomName+"',"+row.operType+","+row.recommendId+')">编辑</a>';
                str += '<a href="javascript:delTab('+row.id+","+row.seq+","+row.seqIsTop+","+row.belongTempletId+')">删除</a>';
                return str;
            }
        } ] ],
        onLoadSuccess: function () {  //加载成功时执行
            // layer.msg("加载成功");
        },
        onLoadError: function () {  //加载失败时执行
            // layer.msg("加载数据失败", {time: 1500, icon: 2});
        },
        onClickCell: function (field,value ,row, td) {
            $(td.parent()[0]).css({"background":"rgb(255,184,72)"}).siblings().css({"background":"none"});
        }
    });
}
function moveTop(id,seq,belongTempletId,seqIsTop) {
    var tab={
        'id':id,
        'seq':seq,
        'belongTempletId':belongTempletId,
        'seqIsTop':seqIsTop
    };
    $.post(ctx + "/templetTab/moveTop", tab, function(data) {
        if (data.returnValue == 0) {
            alert( '置顶成功！');
            $("#sample_2").bootstrapTable('refresh', param);
        } else {
            alert('置顶失败！');
        }
    }, "json");
}
function moveUp(id,seq,belongTempletId,seqIsTop) {
    var tab={
        'id':id,
        'seq':seq,
        'belongTempletId':belongTempletId,
        'seqIsTop':seqIsTop
    };
    $.post(ctx + "/templetTab/moveUp", tab, function(data) {
        if (data.returnValue == 0) {
            alert( '上移成功！');
            $("#sample_2").bootstrapTable('refresh', param);
        } else {
            alert( '上移失败！');
        }
    }, "json");
}
function moveDown(id,seq,belongTempletId,seqIsTop) {
    var tab={
        'id':id,
        'seq':seq,
        'belongTempletId':belongTempletId,
        'seqIsTop':seqIsTop
    };
    $.post(ctx + "/templetTab/moveDown", tab, function(data) {
        if (data.returnValue == 0) {
            alert( '下移成功！');
            //templetContentclear_diaglog();
            //$("#addTempletContentModal").modal('hide');
            $("#sample_2").bootstrapTable('refresh', param);
        } else {
            alert( '下移失败！');
        }

    }, "json");
}

function  delTab(id,seq,seqIsTop,belongTempletId) {
    if (confirm("是否确认删除？")) {
        var tab = {
            'id' : id,
            'seq':seq,
            'seqIsTop':seqIsTop,
            'belongTempletId':belongTempletId
        };
        $.post(ctx + "/templetTab/del", tab, function(data) {
            if (data.returnValue == 0) {
                alert( '成功！');
                //$("#addTempletContentModal").modal('hide');
                $("#sample_2").bootstrapTable('refresh', param);
                if($("#sample_2").bootstrapTable('getData').length == 1){
                    $("#sample_2").bootstrapTable('prevPage');
                }
            } else {
                alert( '失败！');
            }
        }, "json");
    }
}


//初始化查看的table
function initShowTable(belongTempletId) {
    //先销毁表格
    $('#sample_show').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#sample_show").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/templetTab/getPageList", //获取数据的Servlet地址
        striped: true,  //表格显示条纹
        clickToSelect: false,
        pagination: true, //启动分页
        pageSize: 5,  //每页显示的记录数
        pageList: [5],  //记录数可选列表
        pageNumber: 1, //当前第几页
        search: false,  //是否启用查询
        showRefresh: false,  //显示刷新按钮
        sidePagination: "server", //表示服务端请求
        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        //设置为limit可以获取limit, offset, search, sort, order
        queryParamsType: "undefined",
        queryParams: function queryParams(params) {//设置查询参数
            param = {
                page: params.pageNumber,
                rows: params.pageSize,
                belongTempletId:belongTempletId
            };
            return param;
        },
        columns : [ [ {
            field : 'seq',
            title : '排序',
            width : '8%',
            align : 'center',
            formatter : function(value, row, index) {
                return (param.page-1)*5+index+1;
            }
        }, {
            field : 'tabName',
            title : 'Tab名称',
            width : '17%',
            align : 'center',
            formatter : function(value) {
                return value;
            }
        },{
            field : 'tabTitle',
            title : 'Tab标题名称',
            width  : '17%',
            align : 'center',
            formatter : function (value){
                return value;
            }
        }, {
            field : 'count',
            title : 'Group数量',
            width : '12%',
            align : 'center',
            formatter : function(value,row) {
                /*$.get(ctx + "/tabGroup/getPageList?belongTabId=1",function(data) {
                    console.log(data);
                    return data.total;
                }, "json");*/
                return row.groupCount;
            }
        },{
            field : 'tabIcon',
            title : '导航栏图标',
            width  : '14%',
            align : 'center',
            formatter : function (value){
                return "<img style='height:80px;width:100px;' src='" + value + "'/>";
            }
        }, {
            field : 'do',
            title : '操作',
            width : '12%',
            align : 'center',
            formatter : function(value, row) {
                var str='';
                str += '<a href="javascript:showview('+row.tabId+')">预览</a>';
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

//tab页预览

function showview(tabId) {

    $("#previewModal").modal({backdrop: 'static', keyboard: false,width :'800px'});
    $('#previewModal').modal('show');

    var tab = {
        'belongTabId':tabId,
        'groupType':1
    };
    var gridsters = [];
    $.post(ctx + "/templetTab/preview",tab, function(data) {
        getTemplete(gridsters,data);                                                            //调用getLayout方法
    }, "json");

}

function cityManage(id,name,type,cityNames){
    $('#controllerType_name').val("");
    $('#hardware_type_name').val("");
    $('#ipaddress_type_name').val("");
    $('#templete_name').val(name);
    $("#controllerType").val(type);
    $("#dou_hardware_tds").html(""); //清空填充的数据
    $("#dou_ip_tds").html("");
    $("#area_tds").html(""); //清空填充的数据
        if(type == 5){
            $('#comboxControllerType').hide();
            $('#inputControllerType').hide();
            $('#doubleCombControllerType').show();
            $('#areaModal').modal({width: '800Px',backdrop: 'static', keyboard: false});
            generateDoubleDialogInit(id);
        }else if(type == 4){
            $('#addrom_name').val("PB.30.32.01");
            $('#inputControllerType').show();
            $('#doubleCombControllerType').hide();
            $('#comboxControllerType').hide();
            $('#areaModal').modal({width: '800px',backdrop: 'static', keyboard: false});
            editRomDialog(id,type);
        }else{
            $('#comboxControllerType').show();
            $('#inputControllerType').hide();
            $('#doubleCombControllerType').hide();
            $('#areaModal').modal({width: '800px',backdrop: 'static', keyboard: false});
            generateDialog(id,type);
        }
        $('#area_id').val(id);
        $('#areaModal').modal({backdrop: 'static', keyboard: false});
}
function searchControllerByName(){
    var str1 ="";
    var str = "";
    $("#area_tds").html("");
    $("#area_tagId").html("");
    var name = $("#controllerType_name").val();
    var id = $('#area_id').val()
    var controllerType = $("#controllerType").find("option:selected").val();

    var url1 =ctx+ "/entranceArea/getRightCodeName.json";
    var p = {"areaId" : id,
        "controllerType" : controllerType};
    $.post(url1,p,function(data) {
        for (var i = 0; i < data.length; i++) {
            str1 += "<input type='hidden' name='tagId' value='"
                + data[i].code
                + "'/>"
                + "<input type='text' name='tagName' alt='tagName' value='"
                + data[i].name
                + "' class='t_input' disabled='disabled'/>"
                + "<input type='button' value='-' onclick='removes(this);'> <br>";
        }
        $("#area_tds").append(str1);
    }, "json");

    var url2 = ctx+"/entranceArea/getDiffCityList.json";
    param = {"areaId" : id,
        "controllerType" : controllerType};
    $.post(url2,param,function(data) {
        for (var i = 0; i < data.length; i++) {
            if(data[i].name.indexOf(name) != -1){
                str += "<option value='" + data[i].code + "'>" + data[i].name
                    + "</option>";
            }
        }
        $("#area_tagId").append(str);
    }, "json");
}
function  controllerOnChange() {
    var id = $('#area_id').val();
    $('#controllerType_name').val("");
    $('#hardware_type_name').val("");
    $('#ipaddress_type_name').val("");
    var type = $("#controllerType").find("option:selected").val();
    if(type == 5){
        $('#comboxControllerType').hide();
        $('#inputControllerType').hide();
        $('#doubleCombControllerType').show();
        // $('#entrance_area_detail_dialog').modal({width: '790'});
        $("#area_tds").html("");
        generateDoubleDialogInit(id);
        $("#areaModal").modal({backdrop: 'static', keyboard: false});
    }else if(type== 4){
        $('#inputControllerType').show();
        $('#doubleCombControllerType').hide();
        $('#comboxControllerType').hide();
        generateInputDialog();
        $("#areaModal").modal({backdrop: 'static', keyboard: false});
    }else{
        $('#inputControllerType').hide();
        $('#comboxControllerType').show();
        $('#doubleCombControllerType').hide();
        $("#dou_hardware_tds").html(""); //清空填充的数据
        $("#dou_ip_tds").html("");
        generateDialogInit(id);
        $('#areaModal').modal({backdrop: 'static', keyboard: false});
    }
}
function generateInputDialog(){
    $("#dou_hardware_tds").html(""); //清空填充的数据
    $("#dou_ip_tds").html("");
    $("#area_tds").html("");
    param = {
        areaId : -1
    }
    $("#entrance_area_rom_table").bootstrapTable('destroy');
    $("#entrance_area_rom_table").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/entranceArea/getCityByAreaId.json", //获取数据的Servlet地址
        striped: true,  //表格显示条纹
        clickToSelect: false,
        pagination: true, //启动分页
        pageSize: 10,  //每页显示的记录数
        pageNumber: 1, //当前第几页
        pageList: [5, 10, 15, 20, 25],  //记录数可选列表
        search: false,  //是否启用查询
        showColumns: false,  //显示下拉框勾选要显示的列
        showRefresh: false,  //显示刷新按钮
        sidePagination: "server", //表示服务端请求
        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        //设置为limit可以获取limit, offset, search, sort, order
        queryParamsType: "undefined",
        uniqueId:"uniqueId",
        queryParams: function queryParams(params) {//设置查询参数
            param = {
                page: params.pageNumber,
                rows: params.pageSize
            };
            return param;
        },
        columns : [ [
            {
                field : 'code',
                title : '区域',
                width : '40%',
                align : 'center'
            },
            {
                field : 'operate',
                title : '操作',
                width : '40%',
                align : 'center',
                formatter : function(value, row, index) {
                    var str = '';
                    var city=row.code;
                    if(city != null){
                        str = '<input type="button" value="-" onclick="tableRemove('+"'"+row.code+"'"+');">';
                    }
                    return str;
                }
            } ] ],
        pagination : false,
        pageSize : 10,
        rownumbers : true,
        onClickRow : function(rowIndex) {

        },
        onClickCell: function (field,value ,row, td) {
            $(td.parent()[0]).css({"background":"rgb(255,184,72)"}).siblings().css({"background":"none"});
        }
    });
}

/*硬件型号+IP*/
//配置地区
function generateDoubleDialogInit(id) {
    //编辑时，城市的循环显示
    $("#dou_hardware_tds").html(""); //清空填充的数据
    $("#dou_ip_tds").html("");
    $('#entrance_area_rom_table').bootstrapTable('refresh', { total: 0, rows: [] });
    var strHardTds = "";
    var strHardTagId = "";
    var strIPTds = "";
    var strIPTagId = "";
    var url = null;
    var paramHard = {};
    var paramIP = {};
    var controllerType = $("#controllerType").find("option:selected").val();
    url = ctx+"/entranceArea/getRightCodeName.json";
    paramHard = {"areaId" : id,"controllerType" : 3,"diffCityType" : 5};
    paramIP = {"areaId" : id,"controllerType" : 1,"diffCityType" : 5};

    $.post(url,paramHard,function(data) {for (var i = 0; i < data.length; i++) {
        strHardTds += "<input type='hidden' name='hardwareId' value='"
            + data[i].code
            + "'/>"
            + "<input type='text' name='hardwareName' alt='hardwareName' value='"
            + data[i].name
            + "' class='t_input' disabled='disabled'/>"
            + "<input type='button' value='-' onclick='removes(this);'> <br>";
    }
        $("#dou_hardware_tds").append(strHardTds);
    }, "json");
    $("#dou_hardware_tagId").html("");
    $.post(ctx+"/entranceArea/getDiffCityList.json",paramHard, function(data) {
        for (var i = 0; i < data.length; i++) {
            strHardTagId += "<option value='" + data[i].code + "'>" + data[i].name
                + "</option>";
        }
        $("#dou_hardware_tagId").html(strHardTagId);
    }, "json");

    /*IP*/
    $.post(url,paramIP,function(data) {for (var i = 0; i < data.length; i++) {
        strIPTds += "<input type='hidden' name='ipId' value='"
            + data[i].code
            + "'/>"
            + "<input type='text' name='ipName' alt='ipName' value='"
            + data[i].name
            + "' class='t_input' disabled='disabled'/>"
            + "<input type='button' value='-' onclick='removes(this);'> <br>";
    }
        $("#dou_ip_tds").append(strIPTds);
    }, "json");
    $("#dou_ip_tagId").html("");
    $.post(ctx+"/entranceArea/getDiffCityList.json",paramIP, function(data) {
        for (var i = 0; i < data.length; i++) {
            strIPTagId += "<option value='" + data[i].code + "'>" + data[i].name
                + "</option>";
        }
        $("#dou_ip_tagId").html(strIPTagId);
    }, "json");
}
function editRomDialog(id,controllerType){
    $("#area_tds").html("");
    $("#dou_hardware_tds").html("");
    $("#dou_ip_tds").html("");
    var romparam ={};
    romparam = {"areaId" : id,
        "controllerType" : controllerType};
    $('#entrance_area_rom_table').bootstrapTable('destroy');
    $("#entrance_area_rom_table").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/entranceArea/getRightCodeName.json", //获取数据的Servlet地址
        striped: true,  //表格显示条纹
        clickToSelect: false,
        search: false,  //是否启用查询
        showColumns: false,  //显示下拉框勾选要显示的列
        showRefresh: false,  //显示刷新按钮
        sidePagination: "server", //表示服务端请求
        queryParamsType: "undefined",
        queryParams: function queryParams(params) {//设置查询参数
            return romparam;
        },
        columns : [ [
            {
                field : 'code',
                title : '区域',
                width : '40%',
                align : 'center'
            },
            {
                field : 'operate',
                title : '操作',
                width : '40%',
                align : 'center',
                formatter : function(value, row, index) {
                    var str = '';
                    var city=row.code;
                    if(city != null){
                        str = '<input type="button" value="-" onclick="tableRemove('+"'"+row.code+"'"+');">';
                    }
                    return str;
                }
            } ] ],
        pagination : false,
        pageSize : 10,
        rownumbers : true,
        onClickRow : function(rowIndex) {

        },
        onClickCell: function (field,value ,row, td) {
            $(td.parent()[0]).css({"background":"rgb(255,184,72)"}).siblings().css({"background":"none"});
        }
    });
}
//配置地区
function generateDialogInit(id) {
    //编辑时，城市的循环显示
    $("#area_tds").html(""); //清空填充的数据
    $('#entrance_area_rom_table').bootstrapTable('destroy');
    var str = "";
    var str1 = "";
    var url = null;
    var param = {};
    var controllerType = $("#controllerType").find("option:selected").val();
    url =ctx+ "/entranceArea/getRightCodeName.json";
    param = {"areaId" : id,
        "controllerType" : controllerType};
    $.post(url,param,function(data) {
        for (var i = 0; i < data.length; i++) {
            str += "<input type='hidden' name='tagId' value='"
                + data[i].code
                + "'/>"
                + "<input type='text' name='tagName' alt='tagName' value='"
                + data[i].name
                + "' class='t_input' disabled='disabled'/>"
                + "<input type='button' value='-' onclick='removes(this);'> <br>";
        }
        $("#area_tds").append(str);
    }, "json");

    $("#area_tagId").html("");
    $.post(ctx+"/entranceArea/getDiffCityList.json", {"areaId" : id,"controllerType" : controllerType}, function(data) {
        for (var i = 0; i < data.length; i++) {
            str1 += "<option value='" + data[i].code + "'>" + data[i].name
                + "</option>";
        }
        $("#area_tagId").html(str1);
    }, "json");
}
function addIntoRomNameList(){
    var inx = $('#entrance_area_rom_table').bootstrapTable('getOptions').totalRows;
    if("undefined" == typeof inx){
        inx = 0;
    }
    var rom_name = $("#addrom_name").val();
    //获取当前页面的数据值
    var allTableData = $('#entrance_area_rom_table').bootstrapTable('getData');
    for( i=0;i<allTableData.length;i++) {
       var code = allTableData[i].code;
       if(code == rom_name){
           alert("不可重复添加！");
           return ;
       }
    }
    var row = new Object();
    row.code = rom_name;
    var str = '<input type="button" value="-" onclick="tableRemove('+"'"+rom_name+"'"+');">';
    row.operate = str;
    $("#entrance_area_rom_table").bootstrapTable('insertRow', {
        index:inx,
        row:row
    });
}

function tableRemove(code){
    $("#entrance_area_rom_table").bootstrapTable('remove', {
        field:'code',
        values: [code]
    });
}
/*rom 控制 样式设定*/
function romNameOnFocus(){
    var romName =  $("#addrom_name").val();
    if(romName == "PB.30.32.01"){
        $("#addrom_name").val('');
    }
}

function romNameOnBlur(){
    var romName =  $("#addrom_name").val();
    if(romName == null || romName == ''){
        $("#addrom_name").val("PB.30.32.01");
    }
}

//配置地区
function generateDialog(id,controllerType) {
    //编辑时，城市的循环显示
    $("#area_tds").html(""); //清空填充的数据
    $('#entrance_area_rom_table').bootstrapTable("refresh", { total: 0, rows: []});
    var str = "";
    var str1 = "";
    var url = null;
    var param = {};
    if(controllerType == null){
        controllerType = 2;
    }
    $("#controllerType").val(controllerType);
    url =ctx+ "/entranceArea/getRightCodeName.json";
    param = {"areaId" : id,
        "controllerType" : controllerType};
    $.post(url,param,function(data) {
        for (var i = 0; i < data.length; i++) {
            str += "<input type='hidden' name='tagId' value='"
                + data[i].code
                + "'/>"
                + "<input type='text' name='tagName' alt='tagName' value='"
                + data[i].name
                + "' class='t_input' disabled='disabled'/>"
                + "<input type='button' value='-' onclick='removes(this);'> <br>";
        }
        $("#area_tds").append(str);
    }, "json");

    $("#area_tagId").html("");
    $.post(ctx+"/entranceArea/getDiffCityList.json", {"areaId" : id,"controllerType" : controllerType}, function(data) {
        for (var i = 0; i < data.length; i++) {
            str1 += "<option value='" + data[i].code + "'>" + data[i].name
                + "</option>";
        }
        $("#area_tagId").html(str1);
    }, "json");
}

//新增城市
function add() {
    var name = $("#area_tagId option:selected").text();
    var val = $("#area_tagId option:selected").val();
    var str = "<input type='hidden' name='tagId' value='" + val + "'/>"
        + "<input type='text' alt='tagName' name='tagName' value='" + name
        + "' class='t_input' disabled='disabled'/>"
        + "<input type='button' value='-' onclick='removes(this);'> <br>";
    var flag = true;
    if (name != "") {
        $("input[alt='tagId']").each(function() {
            var tagname = $(this).val();
            if (val == tagname) {
                alert(' 不能添加重复数据！');
                flag = false;
                return;
            }
        });
        ;
        if (flag) {
            $("#area_tds").append(str);
        }
    } else {
       alert('请选择城市！');
    }
    $("#area_tagId option:selected").remove();
}

//移除城市
function removes(obj) {
    var str1="";
    var str=$(obj).prev().val();
    $(obj).prev().remove(); //移除当前元素的前面兄弟元素
    var id=$(obj).prev().val();
    $(obj).prev().remove(); //移除当前元素的前面兄弟元素
    $(obj).next().remove(); //移除当前元素的后一个兄弟元素
    $(obj).remove(); //移除当前元素
    var temp = document.getElementById('area_tagId');
    var options = temp.options;
    for(var i=0,len=options.length;i<len;i++){
        var opt = options[i];
        // alert(opt.value + '----' + opt.text);
        if(opt.value == id){
            return;
        }
    }
    str1 += "<option value='" + id + "'>" + str
        + "</option>";
    $("#area_tagId option:last").after(str1);
    //重新刷新页面
    searchHardwareByName();
    searchIpaddressByName();
}
//清空
function ClearDialog() {
    $('#controllerType').val(1);
    $("#area_tds").html(""); //清空填充的数据
    $("#area_tagId").html("");
    $('#entrance_area_rom_table').bootstrapTable("destroy");
    var str1 = "";
    $.post("entranceArea/getDiffCityList.json", {"controllerType" : 1}, function(data) {
        for (var i = 0; i < data.length; i++) {
            str1 += "<option value='" + data[i].code + "'>" + data[i].name
                + "</option>";
        }
        $("#area_tagId").html(str1);
    }, "json");
}

//提交地区
function data_submit() {
    var controllerType = $("#controllerType").find("option:selected").val();
    var arrid="";
    var arrName ="";
    var areaId = $('#area_id').val();
    //循环城市
    $("input[name='tagId']").each(function() {
        var tagid = $(this).val();
        if (arrid == "") {
            arrid = tagid;
        } else {
            arrid += "," + tagid;
        }
    });
    $("input[name='hardwareId']").each(function() {
        var hardwareId = $(this).val();
        if (arrid == "") {
            arrid = hardwareId;
        } else {
            arrid += "," + hardwareId;
        }
    });

    $("input[name='ipId']").each(function() {
        var ipId = $(this).val();
        if (arrid == "") {
            arrid = ipId;
        } else {
            arrid += "," + ipId;
        }
    });
    $("input[name='tagName']").each(function() {
        var tagName = $(this).val();
        if (arrName == "") {
            arrName = tagName;
        } else {
            arrName += "," + tagName;
        }
    });
    $("input[name='ipName']").each(function() {
        var tagName = $(this).val();
        if (arrName == "") {
            arrName = tagName;
        } else {
            arrName += "," + tagName;
        }
    });
    $("input[name='hardwareName']").each(function() {
        var tagName = $(this).val();
        if (arrName == "") {
            arrName = tagName;
        } else {
            arrName += "," + tagName;
        }
    });
    if(controllerType == 4){
        //做校验code不能重复
        var all = $("#entrance_area_rom_table").bootstrapTable('getData');
        for(var i=0;i<all.length;i++){
            var romId = all[i].code;
            if (arrid == "") {
                arrid = romId;
            } else {
                arrid += "," + romId;
            }
            var tagName = all[i].name;
            if("undefined" == typeof tagName){
                tagName =all[i].code;
            }
            if (arrName == "") {
                arrName = tagName;
            } else {
                arrName += "," + tagName;
            }
        }
    }
    if ($.trim(arrid) == "") {
        alert("区域不能为空！");
        return;
    };

    var area = null;
    area = {
        'controllerType' : controllerType,
        'citys' : arrid,
        'cityNames':arrName
    };
    if (areaId != null && areaId != "") {
        area['areaId'] = areaId;
        $.post(ctx+"/entranceArea/update.json", area, function(data) {
            if (data.returnValue == 0) {
                $('#areaModal').modal('hide');
                alert("修改成功！");
                $('#entrance_area_table').bootstrapTable('refresh');
                $('#sample_1').bootstrapTable('refresh');
            }else if(data.returnValue == -2){
                alert("请检查数据，不可重复添加！");
            } else {
                alert("新增失败！！");
            }
        }, "json");
    } else {
        $.post(ctx+"/entranceArea/save.json", area, function(data) {
            if (data.returnValue == 0) {
                $('#areaModal').modal('hide');
                alert("修改成功！");
                $('#entrance_area_table').bootstrapTable('refresh');
                $('#sample_1').bootstrapTable('refresh');
            } else {
                $.messager.show(0, "修改失败！",2000);
            }
        }, "json");
    }
}
function clear_submit() {
    $('#areaModal').modal('hide');
}

/*硬件类型+IP地址 新增硬件类型*/
function addDouble1() {
    var name = $("#dou_hardware_tagId option:selected").text();
    var val = $("#dou_hardware_tagId option:selected").val();
    var str = "<input type='hidden' name='hardwareId' value='" + val + "'/>"
        + "<input type='text' alt='hardwareName' name='hardwareName' value='" + name
        + "' class='t_input' disabled='disabled'/>"
        + "<input type='button' value='-' onclick='removes(this);'> <br>";
    var flag = true;
    if (name != "") {
        $("input[alt='hardwareName']").each(function() {
            var tagname = $(this).val();
            if (name == tagname) {
                alert(' 不能添加重复数据！');
                flag = false;
                return;
            }
        });
        ;
        if (flag) {
            $("#dou_hardware_tds").append(str);
        }
    } else {
        alert('请选择城市！');
    }
    $("#dou_hardware_tagId option:selected").remove();
}

/*硬件类型+IP地址 新增城市*/
function addDouble2() {
    var name = $("#dou_ip_tagId option:selected").text();
    var val = $("#dou_ip_tagId option:selected").val();
    var str = "<input type='hidden' name='ipId' value='" + val + "'/>"
        + "<input type='text' alt='ipName' name='ipName' value='" + name
        + "' class='t_input' disabled='disabled'/>"
        + "<input type='button' value='-' onclick='removes(this);'> <br>";
    var flag = true;
    if (name != "") {
        $("input[alt='ipName']").each(function() {
            var tagname = $(this).val();
            if (name == tagname) {
                alert( ' 不能添加重复数据！');
                flag = false;
                return;
            }
        });
        ;
        if (flag) {
            $("#dou_ip_tds").append(str);
        }
    } else {
        alert('请选择城市！');
    }
    $("#dou_ip_tagId option:selected").remove();
}

/*硬件类型+IP地址 查询硬件类型*/
function searchIpaddressByName(){
    var str = "";
    $("#dou_ip_tagId").html("");
    var name = $("#ipaddress_type_name").val();
    var id = $('#area_id').val()
    var controllerType = $("#controllerType").find("option:selected").val();
    url =ctx+ "/entranceArea/getDiffCityList.json";
    param = {"areaId" : id,
        "controllerType" : 1,
        "diffCityType" : 5};
    $.post(url,param,function(data) {
        for (var i = 0; i < data.length; i++) {
            if(data[i].name.indexOf(name) != -1){
                str += "<option value='" + data[i].code + "'>" + data[i].name
                    + "</option>";
            }
        }
        $("#dou_ip_tagId").append(str);
    }, "json");
}
/*硬件类型+IP地址 查询IP地址*/
function searchHardwareByName(){
    var str = "";
    $("#dou_hardware_tagId").html("");
    var name = $("#hardware_type_name").val();
    var id = $('#area_id').val()
    var controllerType = $("#controllerType").find("option:selected").val();
    url = ctx+"/entranceArea/getDiffCityList.json";
    param = {"areaId" : id,
        "controllerType" : 3,
        "diffCityType" : 5};
    $.post(url,param,function(data) {
        for (var i = 0; i < data.length; i++) {
            if(data[i].name.indexOf(name) != -1){
                str += "<option value='" + data[i].code + "'>" + data[i].name
                    + "</option>";
            }
        }
        $("#dou_hardware_tagId").append(str);
    }, "json");
}
/*预览*/
function preview(id) {
    var param = {"templeteId" : id};
    var url =ctx +"/customRecomTempleteList/getCustomRecomTemplete.json";
    $.post(url,param,function(data) {
        if (data != null) {
            $('#templeteId').val(data.templeteId);
            getContent(id, data.fatherId, 1);
            $("#previewModal").modal({backdrop: 'static', keyboard: false,width :'1000px'});
        } else {
            alert( "网络异常！");
        }
    }, "json");
}