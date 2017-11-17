<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<script language="JavaScript">
    var ctx = '${ctx}';
</script>
<link rel="stylesheet" type="text/css" href="${ctx}/res/js/dist/jquery.gridster.min.css">
<link href="${ctx}/res/bootstrap/bootstrap-table.css" rel="stylesheet">
<script src="${ctx}/res/js/jquery-1.10.1.min.js" type="text/javascript"></script>
<%--<link href="${ctx}/res/bootstrap/bootstrap-dialog.min.css" rel="stylesheet">--%>
<script type="text/javascript"src="${ctx}/res/js/dist/jquery.gridster.js" charset="utf-8"></script>

<script src="${ctx}/res/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/res/bootstrap/bootstrap-table.js" type="text/javascript"></script>
<script src="${ctx}/res/bootstrap/bootstrap-table-zh-CN.js"></script>
<script src="${ctx}/res/js/jquery.flot.js" type="text/javascript"></script>

<%--<script src="${ctx}/res/bootstrap/bootstrap-dialog.min.js" type="text/javascript"></script>--%>

<script src="${ctx}/res/js/customRecomTemplete/customRecomTemplete.js" type="text/javascript"></script>
<script src="${ctx}/res/js/jquery.flot.resize.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/res/js/dist/uuid.js"></script>
<script type="text/javascript" src="${ctx}/res/js/dist/drawTables.js"></script>
<script type="text/javascript" src="${ctx}/res/js/dist/templeteGridster.js"></script>
<!--[if IE]>
<script src="${ctx}/res/js/common/html5.js" type="text/javascript"></script>
<![endif]-->
<!--[if IE]>
<script type="text/javascript" src="${ctx}/res/js/common/html5.js"></script>
<![endif]-->
<style>
    #searchTempleteName{
        width : 120px;
        margin-right:30px;
    }

    #searchTempleteInfo{
        vertical-align: 1px;
        -webkit-border-radius:4px;
        -moz-border-radius:4px;
        -ms-border-radius:4px;
        -o-border-radius:4px;
    }


    #gridsterLayoutFirst li {
        border-top: 2px solid red;
        border-bottom: 2px solid red;
        border-left: 2px solid red;
        border-right: 2px solid red;
        list-style: none;
        /*box-sizing: border-box;*/
    }
    #gridsterLayoutFirst li span{
        height: 20px!important;
    }
    .fixed-table-toolbar .columns {
        height:0px;
    }
    .modal-backdrop, .modal-backdrop.fade.in {
        opacity: 0.7;
        filter: alpha(opacity=70);
        background: #888;
    }
    #customRecomTempleteModal{
        top:5%;
    }
    #addFirstCustomRecomModel{
        top:25% !important;
    }
/*   #addFirstCustomRecomModel{
        position: relative;
    }*/
    .table-striped tbody>tr:nth-child(odd)>td, .table-striped tbody>tr:nth-child(odd)>th{
        background:none;
    }
    .table-hover tbody tr:hover>td{
        background:none;
    }
</style>

<div class="container-fluid">
    <!-- BEGIN PAGE HEADER-->
    <div class="row-fluid">
        <div class="span12">
            <!-- BEGIN PAGE TITLE & BREADCRUMB-->
            <h3 class="page-title">
                推荐位模板管理
                <small></small>
            </h3>
            <ul class="breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="${ctx}">Home</a>
                    <i class="icon-angle-right"></i>
                </li>
                <li><a href="#">推荐位模板管理</a></li>
                <li class="pull-right no-text-shadow">
                    <div id="dashboard-report-range"
                         class="dashboard-date-range tooltips no-tooltip-on-touch-device responsive" data-tablet=""
                         data-desktop="tooltips" data-placement="top" data-original-title="Change dashboard date range">
                        <i class="icon-calendar"></i>
                        <span></span>
                        <i class="icon-angle-down"></i>
                    </div>
                </li>
            </ul>
            <!-- END PAGE TITLE & BREADCRUMB-->
        </div>
    </div>
    <!-- BEGIN DASHBOARD STATS -->
    <div id="dashboard">
        <div class="tab-pane active">
            <div class="portlet box yellow">
                <div class="portlet-title">
                    <div class="caption"><i class="icon-reorder"></i></div>
                </div>
                <div class="portlet-body ">
                    <div class="clearfix" style="position:relative;top:12px;">
                        <div class="btn-group">
                            <button id="btn_add" class="btn btn-default" style="margin-top:-10px;" onclick="addCustomRecomTemplete()">
                                添加</i>
                            </button>
                          <label style="display: inline-block;" for="searchTempleteName">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;模板名称：</label>
                            &nbsp;&nbsp; <input style="width: 200px;" type="text" id="searchTempleteName" name="searchTempleteName">&nbsp;&nbsp;
                            <button id="searchTempleteInfo" class="btn btn-default" onclick="searchTempleteInfo();">查询</button>

                        </div>
                    </div>
                    <table class="table table-striped table-bordered table-hover dataTable" id="customRecomTempleteTable"
                           aria-describedby="sample_1_info">
                    </table>
                    <div class="row-fluid">
                        <div class="span6" style="display:none">
                            <div class="dataTables_info" id="sample_1_info"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



<!-- 模态框（Modal） -->
<div class="modal fade hide" id="customRecomTempleteModal" tabindex="999" role="dialog" aria-labelledby="customRecomTempleteModal" aria-hidden="true" style="width:800px;left:40%;">
    <div class="modal-dialog" style="width: 800px;">
        <div class="modal-content" style="width: 800px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="myModalLabel">积木推荐位模板管理</h4>
            </div>
            <div class="modal-body" style="overflow-x: auto;overflow-y: hidden;width: 770px;max-height:436px;position:relative;" id="modalbody">
                <input id="templeteId" value="${templete.templeteId}" type="hidden" />
                <input id="layoutJson" value="${templete.layoutJson}" type="hidden" />
                <input id="templeteState" value="" type="hidden" />
                <input id="uuid" value="" type="hidden" />
                <label><span style="color:red;">*</span>积木推荐位模板名称：
                    <input type="text"  class="form-control" id="customRecomTempleteName" style="vertical-align: -1px;"></label>
                <%--<div class="canvas-container" style="position:absolute;left:10px;top:65px;height: 360px;">--%>
                <div class="gridster" id="gridsterLayoutFirst" style="width:1200px; height:360px; padding:0px 10px 10px 0px;position:relative;">
                    <ul style="width: 0px;position: absolute ;">

                    </ul>
                    <canvas id="a_canvas" style="background:grey;position:absolute;top:0;left: 0;">


                    </canvas>
                    <p style="position: absolute;top:0;left:640px;width: 0px;background:red;height:348px;border-right:1px solid #000;"></p>

                </div>
                <%--</div>--%>
            </div>
            <div class="modal-footer" style="height: 62px;line-height: 62px;">

                    <p style="float:left">
                        <button id="addTemplete4" class="btn" onclick="addTempleteLayoutTeam1(1)">添加模块</button>
                        <button id="addTemplete5" class="btn" onclick="addTempleteLayoutTeam1(2)">添加视频模块</button>
                    </p>
                    <%--<button id="addTemplete1" class="btn yellow icon-add editLayout" onclick="addTempleteLayoutTeam(1);">尺寸1</button>--%>
                    <%--<button id="addTemplete2" class="btn yellow icon-add editLayout" onclick="addTempleteLayoutTeam(2);">尺寸2</button>--%>
                    <%--<button id="addTemplete3" class="btn yellow icon-add editLayout" onclick="addTempleteLayoutTeam(3);">尺寸3</button>--%>

                <div style="float:right">
                    <button type="button" id = "submitCustomRecomTemplete" class="btn btn-default" onclick="submitCustomRecomTemplete();">保存</button>
                    <button type="button" id = "clearCustomRecomTemplete" class="btn btn-default" onclick="clearCustomRecomTemplete();">取消</button>
                </div>

            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div class="modal fade hide" style="margin-bottom:800px;" id="addFirstCustomRecomModel" tabindex="999" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="myModalLabel1">模块选择</h4>
            </div>
            <div class="modal-body">
                <div class="form-group" style="height: 50px;">
                    <font size="4">选择基础模块:&nbsp;&nbsp;&nbsp;&nbsp;</font>
                    <button  onclick="addCustomRecomTemplete1('295','255')"  style="height: 40px;width:140px;"><img  src="${ctx}\res\image\u77.png"  width="33px" height="27px"/>295*255</button><span>(带播放器推荐位)</span>
                </div>
                <div class="form-group"style="height: 50px;">
                    <input type="hidden" id="col">
                    <input type="hidden" id="row">
                     <button  onclick="addCustomRecomTemplete1('165','105')" style="height: 40px;width:140px;margin-left: 135px;"><img  src="${ctx}\res\image\u82.png"  width="20px" height="12px"/>165*105</button>
                </div>
                <div class="form-group"style="height: 50px;">
                     <button  onclick="addCustomRecomTemplete1('177','245')" style="height: 40px;width:140px;margin-left: 135px;"><img  src="${ctx}\res\image\u87.png"  width="19px" height="29px"/>177*245</button>
                </div>
                 <div class="form-group"style="height: 50px;">
                     <button  onclick="addCustomRecomTemplete1('295','95')" style="height: 40px;width:140px;margin-left: 135px;"><img  src="${ctx}\res\image\u92.png"  width="34px" height="11px"/>295*95</button>
                 </div>
            </div>
            <div class="modal-footer" style="text-align:right;">

            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script src="${ctx}/res/js/common/excanvas.compiled.js"></script>
<script>

        $(function(){
            $('#btn_add').click(
                  function(){
                    $('.modal-body').animate({scrollLeft:0}, 'slow');
                }
            );

//            $("#gridsterLayoutFirst>ul>li").each(function(el,index){
//                $(this).css({"margin-left":"1px"});
//            });
        });

</script>
<!--[if IE]>
<script type="text/javascript" src="${ctx}/res/js/common/html5.js"></script>
<script type="text/javascsript" src="${ctx}/res/js/common/excanvas.compiled.js"></script>
<![endif]-->

