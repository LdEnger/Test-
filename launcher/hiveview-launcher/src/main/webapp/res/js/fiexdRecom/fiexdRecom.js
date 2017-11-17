$(document).ready(function(){
    initTable();
    //init();
    option();
    loadContentPage();
    initUploadJs("recommendContentImg");
});
function  qwer() {
    var isEffective=$("#isEffective").val();
    var fixed_recom_name1 = $("#fixed_recom_name1").val();
    if (isEffective == 2){
    initTable(fixed_recom_name1,null);
        }
    else {
        initTable(fixed_recom_name1,isEffective);
        }
}
var param = {};
function initTable(recomName,isEffective){

    //先销毁表格
    $('#sample_1').bootstrapTable('destroy');
    //初始化表格 动态加载数据
    $('#sample_1').bootstrapTable({
        method: "get",
        url: ctx + "/fiexdRecomList/getPageList",
        striped : true,
        clickToselect : false,
        pagination : true,
        pageSize : 10,
        pageNumber : 1,
        pageList : [5,10,15,20,25],
        search : false,
        showColumns : true,
        showRefresh : false,
        sidePagination : "server",
        queryParamsType : "undefined",
        queryParams : function queryParams(params){
            param = {
                page : params.pageNumber,
                rows : params.pageSize,
                recomName: recomName,
                isEffective:isEffective
            };
            return param;
        },
        columns : [[{
            field : 'recomName',
            title : "推荐位列表名称",
            width : '8%',
            align : 'center',
            formatter : function (value ,row,index) {
                return value;
            }
        },{
            field : 'operateType',
            title : "运营类型",
            width : '8%',
            align : 'center',
            formatter : function (value,row ,index) {
                var str  = '';

                if(value == 0){
                    str = '运营';
                }
                if(value == 1){
                    str = '自动';
                }
                return str ;
            }
        },{
            field : 'chnId',
            title : "频道类型",
            width : '12%',
            align : 'center',
            formatter : function (value ,row ,index) {
                if(row.operateContent == 1){
                    if(value == 2){
                        return "电视剧";
                    }else if(value == 1){
                        return "电影";}
                    else if(value == 15){
                        return "儿童";
                    }else if(value == 4){
                        return "动漫";}
                    else if(value == 6){
                        return "综艺";
                    }
                }else if(row.operateContent == 2){
                    if(row.appCategory == 1){
                        return "游戏";
                    }else if(row.appCategory == 2){
                        return "应用";
                    }else if(row.appCategory == 3){
                        return "教育";
                    }
                }
            }}
            ,{
                field : 'isEffective',
                title : "上下线",
                width : '10%',
                align : 'center',
                formatter : function (value ,row ,index) {
                    if (1 == value) {
                        return '<div class="abtn"><a   style="color:#7d7d7d;text-decoration: none;cursor:text;" class="a_on a_on2">上线</a>&nbsp;|&nbsp;&nbsp;<a href="javascript:isAPKPublish('+row.recomId+','+value+')" class="btn btn-default btn-xs">下线</a></div>';
                    } else {
                        return '<div class="abtn"><a href="javascript:isAPKPublish('+row.recomId+','+value+')" class="btn btn-primary btn-xs">上线</a>&nbsp;|&nbsp;&nbsp;<a style="color:#7d7d7d;text-decoration: none;cursor:text;" class="a_on a_on2">下线</a></div>';
                    }
                }
            },
            {
                field : 'do',
                title : "操作",
                width : '25%',
                align : 'center',
                formatter : function (value ,row) {
                    var str = '';
                    str += '<a href="javascript:edit('+row.recomId+')">编辑</a>';
                    if(row.operateType == 0){
                        str += '&nbsp;&nbsp;<a href="javascript:editys(' + row.recomId +')">编辑推荐位</a>'
                    }else {
                        str += '&nbsp;&nbsp;<a   style="color:#7d7d7d" class="a_on a_on2">编辑推荐位</a>'
                    }
                    str += '&nbsp;&nbsp;<a href="javascript:del('+row.recomId+')">删除</a>';
                    return str;
                }
            }]],
        onLoadSuccess: function(){

        },
        onLoadError : function () {

        }
    });
}

/*function init() {
    $("#btn_add").click(function () {
        $('#myModal').modal({backdrop: 'static', keyboard: false});
    });
}*/

function add() {
    $('#txt_recomId').val('');
    $('#txt_recomName').val('');
    $('#myModal').modal({backdrop: 'static', keyboard: false});
}


function clear_diaglog() {
    $('#txt_recomId').val('');
    $('#txt_recomName').val('');
    $('#myModal').modal('hide');
}
function clear1(){
    $('#myModal1').modal('hide');
}
function  edit(recomId) {
    $("#recomId").val(recomId);

    $.post(ctx + "/fiexdRecomList/getFixedRecomListOne", {recomId : recomId}, function(data) {

        $('#hidd-operateType').val(data.operateType);
        $('#txt_recomId').val(recomId);
        $('#txt_recomName1').val(data.recomName);
        $('#text-operateType').val(data.operateType);
        option1();
        if(data.operateContent == 1){
            $("#operateContent").val("大麦影视");
            if(data.chnId == 2){$("#chnType").val("电视剧");}
            else if(data.chnId == 1){$("#chnType").val("电影");}
            else if(data.chnId ==15){$("#chnType").val("儿童");}
            else if(data.chnId == 4){$("#chnType").val("动漫");}
            else if(data.chnId == 6){$("#chnType").val("综艺");}
        }else if(data.operateContent == 2){
            $("#operateContent").val("应用游戏");
            if(data.appCategory == 1){$("#chnType").val("游戏");}
            else if(data.appCategory == 2){$("#chnType").val("应用");}
            else if(data.appCategory == 3){$("#chnType").val("教育");}
        }

    }, "json");


    $('#myModal1').modal({backdrop: 'static', keyboard: false});

}
function del (recomId) {
    $.post(ctx + "/fixedRecommendContent/getPageList", {fixedRecomId : recomId}, function(data) {
        if(data.total!=0){
            if(confirm("是否确认删除")) {
                var recom = {
                    recomId: recomId
                };
                $.post(ctx + "/fiexdRecomList/delete", recom, function (data) {
                    if (data.returnValue == 0) {
                        var recom = {
                            fixedRecomId: recomId
                        };
                        $.post(ctx + "/fixedRecommendContent/delete",recom,function (data) {
                            if(data.returnValue == 0){
                                alert("成功");
                                $("#myModal").modal('hide');
                                $("#sample_1").bootstrapTable('refresh', param);
                                if( $("#sample_1").bootstrapTable('getData').length == 1){
                                    $("#sample_1").bootstrapTable('prevPage');
                                }
                            }else {
                                alert('失败！');
                                }
                        },"json")
                    }
                    else {
                        if(data.returnValue == 1){
                            alert("数据已作关联,无法删除");
                        }else{
                        alert('失败！');
                        }
                    }
                }, "json")
            }
      }else{

            if(confirm("是否确认删除")) {
                var recom = {
                    recomId: recomId
                };
                $.post(ctx + "/fiexdRecomList/delete", recom, function (data) {
                    if (data.returnValue == 0) {
                           alert("成功");
                        $("#myModal").modal('hide');
                        $("#sample_1").bootstrapTable('refresh', param);
                        if( $("#sample_1").bootstrapTable('getData').length == 1){
                            $("#sample_1").bootstrapTable('prevPage');
                        }
                    }
                    else {
                        if(data.returnValue == 1){
                            alert("数据已作关联,无法删除");
                        }else{
                            alert('失败！');
                        }
                    }
                }, "json")
            }
        }
    },"json")
}
function fiexdRecom_submit() {

    var recomId = $('#txt_recomId').val();
    var recomName = $('#txt_recomName').val();
    var operateContent =  $('#txt-content').val();
    var appCategory  =  $("#txt-type").val();
    var operateType = $('#text-operateType').val();
    var operateType1 = $('#text-operateType1').val();
    var recomName1 = $('#txt_recomName1').val();
    var isEffective = '0';
    if(recomId != null && recomId != ''){
        recomName = $('#txt_recomName1').val();
    }
    if($.trim(recomName) == ""){
        alert("推荐位标题不能为空");
        return ;
    }
    if(operateType!=1&&operateType!=0){
        alert("请选择运行类型");
        return ;
    }
    if(operateContent == 2){
        var recoms = {
            'recomId' : recomId,
            'recomName' :recomName,
            'operateContent' : operateContent,
            'operateType' :operateType,
            'appCategory' : appCategory,
            'isEffective' :isEffective
        };
    }else{
        var recoms = {
            'recomId' : recomId,
            'recomName' :recomName,
            'operateContent' : operateContent,
            'operateType' :operateType,
            'chnId' : appCategory,
            'chnType' : 10,
            'apkBagName' : "com.hiveview.cloudscreen.vipvideo",
            'templetId': 1,
            'isEffective' :isEffective
        };
    }


    if(recomId != null && recomId != ''){
        var recoms1 = {
            'recomId' : recomId,
            'recomName' :recomName1,
            'operateType' :operateType1,

        };
        $.post(ctx + "/fiexdRecomList/update",recoms1,function (data) {
            if(data.returnValue ==0 ){
                alert('修改成功');
                $("#myModal1").modal('hide');
                //clear_diaglog();
                $("#sample_1").bootstrapTable('refresh',param);
            }else {
                alert('修改失败');
            }
        },"json")
    }else {
        $.post(ctx + "/fiexdRecomList/save",recoms,function (data) {

            if(data.returnValue == 0){
                alert('新增成功');
                $("#myModal").modal('hide');
                clear_diaglog();
                $("#sample_1").bootstrapTable('refresh',param);
            }else {
                alert('新增失败')
            }
        },"json")
    }
}
function option1(){
    var hiddOperateType1 = $("#hidd-operateType").val();
    if(hiddOperateType1 == 0){
        $('#text-operateType1').val("0");
    }
    else{
        $('#text-operateType1').val("1");
    }
}
function option(){
    $("#text-operateType").append("<option value='0'>运营</option>");
    $("#text-operateType").append("<option value='1'>自动</option>");
    $("#txt-content").append("<option value='2'>应用游戏</option>");
    $("#txt-content").append("<option value='1'>大麦影视</option>");
    $("#txt-type").append("<option value='1'>游戏</option>");
    $("#txt-type").append("<option value='2'>应用</option>");
    $("#txt-type").append("<option value='3'>教育</option> ");
    $("#txt-content").change(
        function(){
            var txtvalue=$("#txt-content").val();
            if(txtvalue == 1){
                $("#txt-type").empty();
                $("#txt-type").append("<option value='2'>电视剧</option>");
                $("#txt-type").append("<option value='1'>电影</option>");
                $("#txt-type").append("<option value='15'>儿童</option>");
                $("#txt-type").append("<option value='4'>动漫</option>");
                $("#txt-type").append("<option value='6'>综艺</option>");
            }else{
                $("#txt-type").empty();
                $("#txt-type").append("<option value='1'>游戏</option>");
                $("#txt-type").append("<option value='2'>应用</option>");
                $("#txt-type").append("<option value='3'>教育</option> ");
            }
        });
}

function addFixeRecom() {
    $("#addFixeRecom").click(function () {
        $('#myModal').modal({backdrop: 'static', keyboard: false});
    });
}

function  loadContentPage() {
    $("#btn_contentAdd").click(function () {
        clear_diaglog();
        $('#myModalsy').modal({backdrop: 'static', keyboard: false});
    });
}

function isAPKPublish(recomId,isEffective) {
    var confirmInfo = null;
    var hotWords = {};
    var a = $('#sample_1').bootstrapTable('getSelections');
    var status = isEffective;
    if (status == 0) {
        hotWords = {
            "isEffective" : 1,
            "recomId" : recomId,

        };
        confirmInfo = "确定要置为上线状态?";

    } else {
        hotWords = {
            "isEffective" : 0,
            "recomId" : recomId,

        };
        confirmInfo = "确定要置为下线状态?";
    }
    if (confirm(confirmInfo)) {
        $.post(ctx +"/fiexdRecomList/updateEffective", hotWords, function(data) {
                if (data.returnValue  == 1) {
                    alert("修改失败");
                    $("#sample_1").bootstrapTable('refresh',param);

                } else if(data.returnValue == 0){
                    alert("修改成功");
                    $("#sample_1").bootstrapTable('refresh',param);
                }}
            , "json");
    }
}




