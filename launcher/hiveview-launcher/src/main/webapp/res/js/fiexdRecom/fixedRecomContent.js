/**
 * Created by user on 2017/7/26.
 */

var param ={};
function editys(recomId) {
    $.post(ctx + "/fiexdRecomList/getFixedRecomListOne", {recomId : recomId}, function(data) {
        if(data.operateContent == 2){
            $("#content_chn_id").val(data.appCategory);
        }else{
            $("#content_chn_id").val(data.chnId);
        }

    }, "json");
    $("#seleOperateContent").val(recomId);
    var opt = {
        url: ctx + "/fixedRecommendContent/getPageList",
        silent: true,
        query: {
            fixedRecomId: recomId
        }
    };

    $("#sample_table1").bootstrapTable('refresh', opt);
    initTb(recomId);

}
//初始化第二张表
function initTb(recomId){
    //先销毁表格

    $('#sample_table1').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据

    $("#sample_table1").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/fixedRecommendContent/getPageList", //获取数据的Servlet地址
        striped: true,  //表格显示条纹
        clickToSelect: false,
        pagination: true, //启动分页
        pageSize: 10,  //每页显示的记录数
        pageNumber: 1, //当前第几页
        pageList: [5,10,20],  //记录数可选列表
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
                fixedRecomId:recomId,
            };
            return param;
        },
        columns : [ [ {
            field : 'id',
            title : 'ID',
            width : '8%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        },{
            field : 'contentName',
            title : '推荐位标题',
            width : '13%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        },{
            field : 'contentType',
            title : '推荐位类型',
            width : '13%',
            align : 'center',
            formatter : function(value, row, index) {
                if(value == 1){
                    return "专辑详情";
                }
                else if(value == 11){
                    return "应用详情";
                }
                else if(value == 3){
                    return "应用专题";
                }
                else if(value == 4){
                    return "活动详情";
                }
                else if(value == 5){
                    return "启动应用";
                }
                else if(value == 6){
                    return "全屏大图";
                }
                else if(value == 0){
                    return "影视专题";
                }
                else {
                    return "专辑详情";
                }
            }
        },
            {
                field : 'contentImg',
                title : '图片',
                width : '18%',
                align : 'center',
                formatter : function(value) {
                    return "<img style='height:60px;width:75px;' src='" + value + "'/>";
                }
            },
            {
                field : 'seqDo',
                title : '顺序操作',
                width : '13%',
                align : 'center',
                formatter :function (value,row,index) {
                    var str = "";
                    if(true){
                        str += '<a href="javascript:stickFixedRecomment(' + row.id+"," + row.seq + ","+row.fixedRecomId + ')">置顶</a>';
                        str += '&nbsp;|&nbsp; <a href="javascript:upEntrance(' + row.id +"," + row.seq + "," + row.fixedRecomId+ ')">上移</a>'
                        str += '&nbsp;| <a href="javascript:downEntrance('+ row.id + "," + row.seq + "," + row.fixedRecomId + ')">下移</a>'
                    }
                    return str;
                }
            },
            {
                field : 'do',
                title : '操作',
                width : '14%',
                align : 'center',
                formatter : function(value, row) {
                    var str = '';

                    str += '<a href="javascript:edit11('+row.id+')">编辑</a>';
                    str += '&nbsp;|&nbsp; <a href="javascript:del11('+row.id+')">删除</a>';
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
    $('#myModalTwo').modal({width: '800Px',backdrop: 'static',keyboard: false });
}
//置顶
function stickFixedRecomment(id,seq,fixedRecomId) {
    var fixedRecomContent = {
        'fixedRecomId':fixedRecomId,
    }
    var minSeq = null;
    $.post(ctx + "/fixedRecommendContent/getMinSeq", fixedRecomContent, function(data) {
      if(seq == data){

      }else {
          minSeq = parseInt(data) - 1;
          var content = {
              'id': id,
              'seq': minSeq,
          };
          $.post(ctx + "/fixedRecommendContent/update", content, function (data) {
              if (data.returnValue == 0) {
                  alert('置顶成功！');
                  $("#sample_table1").bootstrapTable('refresh', param);
              } else {
                  alert('置顶失败！');
              }
          }, "json");
      } }, "json");
}
//上移
function upEntrance(id,seq,fixedRecomId) {
    var mapping = {
        'id': id,
        'seq': seq,
        'fixedRecomId' :fixedRecomId
    }
    $.post(ctx + "/fixedRecommendContent/getMinMapping",mapping,function (data) {
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
            $.post(ctx + "/fixedRecommendContent/update", mapping1, function (data) {
                if (data.returnValue == 0) {
                    $.post(ctx + "/fixedRecommendContent/update", mapping2, function (data) {
                        if (data.returnValue == 0) {
                            alert("上移成功");
                            $("#sample_table1").bootstrapTable('refresh', param);
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
function downEntrance(id,seq,fixedRecomId) {
    var mapping = {
        'id': id,
        'seq': seq,
        'fixedRecomId' :fixedRecomId
    };
    $.post(ctx + "/fixedRecommendContent/getMaxMapping",mapping,function (data) {
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
            $.post(ctx + "/fixedRecommendContent/update", mapping1, function (data) {

                if (data.returnValue == 0) {
                    $.post(ctx + "/fixedRecommendContent/update", mapping2, function (data) {
                        if (data.returnValue == 0) {
                            alert("下移成功");
                            $("#sample_table1").bootstrapTable('refresh', param);
                        } else {
                            alert("下移失败");
                        }
                    }, "json");
                } else {
                    debugger;
                    alert("下移失败");
                }
            }, "json");

        }},"json");
}


function clear_diaglog2(){

    $("#myModalTwo").modal('hide');
}

function del11 (id) {
    if(confirm("是否确认删除")){
        var recom = {
            id : id
        };
        $.post(ctx + "/fixedRecommendContent/delete",recom,function (data) {
            if(data.returnValue == 0){
                alert("成功");
                $("#sample_table1").bootstrapTable('refresh',param);
                console.info($("#sample_table1").bootstrapTable('getData').length);
                 if( $("#sample_table1").bootstrapTable('getData').length == 1){
                 $("#sample_table1").bootstrapTable('prevPage');
                 }
            }else {
                alert('失败！');
            }
        },"json")
    }
}
function add1(){

        /*$("#footer").css
        ({
            "top":"-10px"
        });*/

    $("#hiddenId").val("");
    var seleOperateContent =  $("#seleOperateContent").val();
    $.post(ctx + "/fiexdRecomList/getFixedRecomListOne", {recomId : seleOperateContent}, function(data) {
        console.info( data);
        $('#content_operate').val(data.operateContent);
        if( data.operateContent == 1){
            $("#rightdiv").html(
                "<div>"+
                /*"&nbsp; &nbsp;&nbsp;&nbsp;"+
                 "模板："+
                "<select id='templet' style='width:100px; '>"+
                "<option value=''>基础模板</option>" +
                "</select>"+
                "&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
                "频道类型："+
                "<select id='chnTypeSelect' style='width:100px; '>"+
                "<option value='9'>自定义</option>" +
                "<option value ='0'>专题</option>"+
                "<option value ='10'>基础频道</option>"+
                "</select>"+
                "</div>"+
                "<div>" +
                "频道："+
        "<select id='chn' style='width:80px; '>"+
                "<option value=''>全部</option><option value ='电视剧'>电视剧</option>"+
                "<option value ='电影'>电影</option><option value ='综艺'>综艺</option>"+
                "<option value ='儿童'>儿童</option><option value ='动漫'>动漫</option>"+
                "<option value ='生活'>生活</option>"+
                "<option value ='专题'>专题</option>"+
                "<option value ='音乐'>音乐</option>" +
                "<option value ='纪录片'>纪录片</option>"+
                "<option value ='体育'>体育</option>"+
                "<option value ='片花'>片花</option>"+
                "<option value ='娱乐'>娱乐</option>"+
                "<option value ='搞笑'>搞笑</option>"+
                "<option value ='汽车'>汽车</option>"+
                "<option value ='时尚'>时尚</option>"+
                "<option value ='教育'>教育</option>"+
                "</select>"+
                "&nbsp; &nbsp;"+*/
                "<label for='contentType' style='display: inline-block'>付费：</label><select id='contentType' style='width:80px;vertical-align: -1px; '>" +
                "<option value='-1'>全部</option>" +
                "<option value ='0'>免费</option>" +
                "<option value ='1'>大麦付费</option>" +
                "<option value ='2'>爱奇艺付费</option>" +
                "</select>"+
                " &nbsp; &nbsp; "+
                "<label for='contentType1' style='display: inline-block;'>VIP：</label><select id='contentType1' style='width:80px;vertical-align: -1px;'>"+
                "<option value='-1'>全部</option>"+
                "<option value ='0'>非VIP</option>"+
                "<option value ='1'>大麦VIP</option>"+
                "<option value ='2'>爱奇艺VIP</option>"+
                "</select></div>"+
                "<label for='contentName' style='display: inline-block;'>名称：</label> <input type='text' id='contentName' style='vertical-align: -1px;'>"+
                "&nbsp;&nbsp;&nbsp;"+
                "<input type='button' class='btn yellow' style='margin-bottom:14px;vertical-align: -1px;' value='查询' onclick='selecFixedRecomName("+data.operateContent+")'>"
            );

            $("#tableDiv").css("display", "block");
            $("#tableDiv1").css("display", "none");
            table2();

            $("#editcontent_myModal").modal({width: '800Px',backdrop: 'static',keyboard: false });

        }else {
            $("#rightdiv").html("<div>" +
                "名称： <input type='text' id='contentName'>"+
                "&nbsp;&nbsp;&nbsp;"+
                "<input type='button' class='btn yellow' value='查询'  style='margin-bottom:14px;' onclick='selecFixedRecomName("+data.operateContent+")'>"
            );
            table3();
            $("#tableDiv").css("display", "none");
            $("#tableDiv1").css("display", "block");

            $("#editcontent_myModal").modal({width: '800Px',backdrop: 'static',keyboard: false });
        }
    }, "json");

    $('#txt_title').val("");

    $('#txt_secondTitle').val("");
    $('#recommendContentImg').attr('src',"");;

}
function edit11(id){
    // $("#footer").css
    // ({
    //     "top":"-35px"
    // });
    $("#hiddenId").val(id);
    $.post(ctx + "/fixedRecommendContent/getFixedRecommendContentOne", {id : id}, function(data) {
        console.info(data);
        $('#txt_title').val(data.contentName);
        $("#contentId1").val(data.contentId);
        $('#editcontent_id').val(data.contentId);
        $('#txt_secondTitle').val(data.contentSubtitle);
        $('#recommendContentImg').attr('src',data.contentImg);
        $('#content_operate').val(data.operateContent);
        if( data.operateContent == 1){
            $("#rightdiv").html(
                "<div>"+
                /*"&nbsp; &nbsp;&nbsp;&nbsp;"+
                "模板："+
                "<select id='templet' style='width:100px; '>"+
                "<option value=''>基础模板</option>" +
                "</select>"+
                "&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
                "频道类型："+
                "<select id='chnTypeSelect' style='width:100px; '>"+
                "<option value ='9'>自定义</option>" +
                "<option value ='0'>专题</option>"+
                "<option value ='10'>基础频道</option>"+
                "</select>"+
                "</div>"+
                "<div>" +
                "频道："+
                "<select id='chn' style='width:80px; '>"+
                "<option value=''>全部</option><option value ='电视剧'>电视剧</option>"+
                "<option value ='电影'>电影</option><option value ='综艺'>综艺</option>"+
                "<option value ='儿童'>儿童</option><option value ='动漫'>动漫</option>"+
                "<option value ='生活'>生活</option>"+
                "<option value ='专题'>专题</option>"+
                "<option value ='音乐'>音乐</option>" +
                "<option value ='纪录片'>纪录片</option>"+
                "<option value ='体育'>体育</option>"+
                "<option value ='片花'>片花</option>"+
                "<option value ='娱乐'>娱乐</option>"+
                "<option value ='搞笑'>搞笑</option>"+
                "<option value ='汽车'>汽车</option>"+
                "<option value ='时尚'>时尚</option>"+
                "<option value ='教育'>教育</option>"+
                "</select>"+
                "&nbsp; &nbsp;"+*/
                "<label for='contentType' style='display: inline-block;'>付费：</label><select id='contentType' style='width:106px;vertical-align: -1px; '>" +
                "<option value='-1'>全部</option>" +
                "<option value ='0'>免费</option>" +
                "<option value ='1'>大麦付费</option>" +
                "<option value ='2'>爱奇艺付费</option>" +
                "</select>"+
                " &nbsp; &nbsp; "+
                "<label for='contentType1' style='display: inline-block;'>VIP：</label><select  id='contentType1' style='width:100px;vertical-align: -1px;'>"+
                "<option value='-1'>全部</option>"+
                "<option value ='0'>非VIP</option>"+
                "<option value ='1'>大麦VIP</option>"+
                "<option value ='2'>爱奇艺VIP</option>"+
                "</select></div>"+
                "<label for='contentName' style='display: inline-block;'>名称：</label> <input type='text' id='contentName' style='vertical-align: -1px;'>"+
                "<input type='button' class='btn yellow' value='查询'  style='margin-bottom:14px;vertical-align: -1px;' onclick='selecFixedRecomName("+data.operateContent+")'>"
            )
            $("#tableDiv").css("display", "block");
            $("#tableDiv1").css("display", "none");
            table2();
            /*if($("#recommendContentImg")[0].height<100){
                console.log("111"+$("#recommendContentImg")[0].height);
                $("#footer").css
                ({
                    "top":"3px"
                });
            }else{
                $("#footer").css
                ({
                    "top":"-35px"
                });
            }*/
            $("#editcontent_myModal").modal({width: '800Px',backdrop: 'static',keyboard: false });
            }else {
            $("#rightdiv").html("<div>" +
                "名称： <input type='text' id='contentName'>"+
                "&nbsp;&nbsp;&nbsp;"+
                "<input type='button'  class='btn yellow'  value='查询'  style='margin-bottom:14px;' onclick='selecFixedRecomName("+data.operateContent+")'>"
            );
            $("#tableDiv").css("display", "none");
            $("#tableDiv1").css("display", "block");
            table3();
            /*$("#footer").css
            ({
                "top":"0px"
            });*/
            $("#editcontent_myModal").modal({width: '800Px',backdrop: 'static',keyboard: false });
        }
    }, "json");


}
function selecFixedRecomName(operateContent) {
    if(operateContent == 1) {
        var chn = $("#chn").val();
        var chnTypeSelect = $("#chnTypeSelect").val();
        var isCharge = $("#contentType").val();
        var isContentVip = $("#contentType1").val();
        console.log(isContentVip);
        var contentName = $("#contentName").val();
        var chargingType = null;//大麦收费
        var isTvod = null;//爱奇艺收费
        var isVip = null;//大麦VIP
        var aqyIsVip = null;//爱奇艺Vip
        var chnId =  $("#content_chn_id").val();
        if(isCharge =="-1" ){
            chargingType = null;
            isTvod= null;
        }else if(isCharge == "0"){
            chargingType = 3;
            //isTvod = 0;
        }else if(isCharge == "1"){
            chargingType = 1;
            isTvod = null;
        }else if(isCharge == "2"){
            chargingType = null;
            isTvod = 1;
        }

        if(isContentVip == "-1" ){
            isVip = null;
            aqyIsVip= null;
        }else if(isContentVip == "0"){
            isVip = 3;
            //aqyIsVip = 0;
        }else if(isContentVip == "1"){
            isVip = 1;
            aqyIsVip = null;
        }else if(isContentVip == "2"){
            isVip = null;
            aqyIsVip = 1;
        }
        table2(chargingType,isTvod,isVip,aqyIsVip,chn,chnTypeSelect);
     }
    else
    {
        table3();

        }

}
//大麦影视
function table2(chargingType,isTvod,isVip,aqyIsVip,chn,chnTypeSelect) {
    var chnId =  $("#content_chn_id").val();
    var contentName = $("#contentName").val();
    $('#sample_table2').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#sample_table2").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/fixedRecommendContent/getNewContentChanPageList", //获取数据的Servlet地址
        striped: true,  //表格显示条纹
        clickToSelect: false,
        pagination: true, //启动分页
        pageSize: 10,  //每页显示的记录数
        pageNumber: 1, //当前第几页
        pageList: [5,10,20],  //记录数可选列表
        search: false,  //是否启用查询
        showColumns: false,  //显示下拉框勾选要显示的列
        showRefresh: false,  //显示刷新按钮
        sidePagination: "server", //表示服务端请求
        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        //设置为limit可以获取limit, offset, search, sort, order
        queryParamsType: "undefined",
        queryParams: function queryParams(params) {//设置查询参数

            param = {
                //chnName:chn,
               // chnType:chnTypeSelect,
                albumName : contentName,
                chnId:chnId,
                chnType:10,
                chargingType : chargingType,
                isTvod : isTvod,
                isVip : isVip,
                aqyIsVip : aqyIsVip,
                status :1,
                isEffective : 1,
                apkBagName:"com.hiveview.cloudscreen.vipvideo",
                page: params.pageNumber,
                rows: params.pageSize,
            };
            return param;
        },onClickRow: function(row){
            $('#txt_title').val("");

            $('#txt_secondTitle').val("");
            $('#recommendContentImg').attr('src',"");
            $('#editcontent_id').val(row.programsetId);
            $('#txt_title').val(row.albumName);
            $('#txt_secondTitle').val(row.focus);
            $("#recommendContentImg").attr('src',row.albumHbPic);
            console.log($("#recommendContentImg")[0].height);
            /*if($("#recommendContentImg")[0].height<100){
                $("#footer").css
                ({
                  "top":"-10px"
                });
                  }else{
                $("#footer").css
                ({
                    "top":"-10px"
                });
            }*/
        },
        columns : [ [ {
            field : 'programsetId',
            title : '内容id',
            width : '8%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        },{
            field : 'albumName',
            title : '专辑名称',
            width : '13%',
            align : 'center',
            formatter : function(value, row, index) {
                return value;
            }
        },{
            field : 'contentType',
            title : '专辑来源',
            width : '13%',
            align : 'center',
            formatter : function(value, row, index) {
                var str="";
                if(row.aqyId !=null && row.aqyIsEffective == 1){
                    str=str+"爱奇艺|"
                }
                if(row.jqId !=null && row.jqIsEffective == 1){
                    str=str+"极清|"
                }
                return str;
            }
        },
            {
                field : 'albumHbPic',
                title : '图片',
                width : '13%',
                align : 'center',
                formatter : function(value) {
                    return "<img style='height:60px;width:80px;' src='" + value + "'/>";
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
//应用游戏
function table3() {
    var contentName = $("#contentName").val();
    var categoryId = $("#content_chn_id").val();
    $('#sample_table3').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据

    $("#sample_table3").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: ctx + "/fixedRecommendContent/getAppPageList", //获取数据的Servlet地址
        striped: true,  //表格显示条纹
        clickToSelect: false,
        pagination: true, //启动分页
        pageSize: 10,  //每页显示的记录数
        pageNumber: 1, //当前第几页
        pageList: [5,10,20],  //记录数可选列表
        search: false,  //是否启用查询
        showColumns: false,  //显示下拉框勾选要显示的列
        showRefresh: false,  //显示刷新按钮
        sidePagination: "server", //表示服务端请求
        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        //设置为limit可以获取limit, offset, search, sort, order
        queryParamsType: "undefined",
        queryParams: function queryParams(params) {//设置查询参数

            param = {
                appName: contentName,
                categoryId:categoryId,
                state:1,
                page: params.pageNumber,
                rows: params.pageSize,

            };
            return param;
        }, onClickRow: function (row) {
            $('#txt_title').val("");

            $('#txt_secondTitle').val("");
            $('#editcontent_id').val(row.appId);
            $('#recommendContentImg').attr('src',"");
            $('#txt_title').val(row.appName);
            $("#recommendContentImg").attr('src',row.appIcon);
        },
        columns: [ [{
            field: 'appId',
            title: '内容ID',
            width: '8%',
            align: 'center',
            formatter: function (value, row, index) {

                return value;
            }
        }, {
            field: 'appName',
            title: '应用名称',
            width: '13%',
            align: 'center',
            formatter: function (value, row, index) {
                return value;
            }
        }, {
            field: 'appType',
            title: '应用类型',
            width: '13%',
            align: 'center',
            formatter: function (value, row, index) {
                if (value == 0) {
                    return "系统应用";
                } else {
                    return "安装应用"
                }


            }
        },
            {
                field: 'appIcon',
                title: '图片',
                width: '13%',
                align: 'center',
                formatter: function (value) {
                    return "<img style='height:60px;width:80px;' src='" + value + "'/>";
                }
            }]],
        onLoadSuccess: function () {  //加载成功时执行
            //layer.msg("加载成功");
        },
        onLoadError: function () {  //加载失败时执行
            // layer.msg("加载数据失败", {time: 1500, icon: 2});
        }
    });

}


function close_contentdiaglog() {
    $("#myModalTwo").modal({width: '800Px',backdrop: 'static',keyboard: false });



    $("#editcontent_myModal").modal('hide');

}


function content_submit() {
    var hiddenId =  $("#hiddenId").val();
    var contentId1 = $("#contentId1").val();
    var contentId =  $('#editcontent_id').val();
    var txt_title = $('#txt_title').val();
    var operateContent = $('#content_operate').val();
    var  fixedRecomId = $("#seleOperateContent").val();
    var txt_secondTitle =  $('#txt_secondTitle').val();
    var recommendContentImg = $('#recommendContentImg')[0].src;
    if ($.trim(txt_title) == "") {
        alert("标题不能为空");
        return;
    }
    console.info($('#recommendContentImg')[0].width);
    console.info($('#recommendContentImg')[0].height);
    /*var imgExist = recommendContentImg.substr(recommendContentImg.length-4);
    if(imgExist == 'list'){
        alert("图片不能为空");
        return;
    }
    if(recommendContentImg == null && recommendContentImg == ''){
        alert("图片不能为空");
        return;
    }*/

    debugger;
    var content = {
        'id':hiddenId,
        'contentId' : contentId,
        'contentName' : txt_title,
        'contentSubtitle' :txt_secondTitle,
        'contentImg' : recommendContentImg,
        'operateContent':operateContent,
        'fixedRecomId':fixedRecomId

    };
    if(hiddenId != "" && hiddenId != null){
    if(contentId1 == contentId){
        var content3 = {
            'id':hiddenId,
            'contentName' : txt_title,
            'contentSubtitle' :txt_secondTitle,
            'contentImg' : recommendContentImg,
            'operateContent':operateContent,
      };

        $.post(ctx + "/fixedRecommendContent/update", content3, function(data) {

            if (data.returnValue == 0) {
                alert( '修改成功！');
                 clear_diaglog1();
                $("#hiddenId").val("");
                $("#sample_table2").bootstrapTable('refresh', param);
                $("#sample_table1").bootstrapTable('refresh', param);
            } else {
                alert( '修改失败！');
            }
        }, "json");

    }else{
        $.post(ctx + "/fixedRecommendContent/update", content, function(data) {

            if (data.returnValue == 0) {
                alert( '修改成功！');
                clear_diaglog1();
                $("#hiddenId").val("");
                $("#sample_table2").bootstrapTable('refresh', param);
                $("#sample_table1").bootstrapTable('refresh', param);
            } else {
                if(data.returnValue == -2){
                    alert( '已存在列表中,不可重复编辑！');
                }else{
                    alert( '修改失败！');
                }
            }
        }, "json");
    }

    }else{
        $.post(ctx + "/fixedRecommendContent/save", content, function(data) {
            if (data.returnValue == 0) {
                alert( '新增成功！');
                 clear_diaglog1();
                $("#hiddenId").val("");
                $("#sample_table2").bootstrapTable('refresh', param);
                $("#sample_table1").bootstrapTable('refresh', param);

            } else {
                if(data.returnValue == -2){
                    alert( '已存在列表中,不可重复添加！');
                }else{
                    alert( '新增失败！');
                }
            }
        }, "json");
    }
}

function clear_diaglog1(){
    $('#recommendContentImgUrl').val("");
    $('#txt_title').val("");

    $('#txt_secondTitle').val("");
    $('#recommendContentImg').attr('src',"");
    $("#editcontent_myModal").modal('hide');

}
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