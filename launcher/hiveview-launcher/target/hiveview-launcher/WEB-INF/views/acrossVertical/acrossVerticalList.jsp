<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<link href="${ctx}/res/js/plugin/uploadify/uploadify.css" rel="stylesheet">
<link href="${ctx}/res/bootstrap/bootstrap-table.css" rel="stylesheet">
<link href="${ctx}/res/css/bootstrap-modal.css" rel="stylesheet">
<%--<link href="${ctx}/res/bootstrap/bootstrap-dialog.min.css" rel="stylesheet">--%>
<script src="${ctx}/res/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/res/bootstrap/bootstrap-table.js" type="text/javascript"></script>
<script src="${ctx}/res/bootstrap/bootstrap-table-zh-CN.js"></script>
<script src="${ctx}/res/js/jquery.flot.js" type="text/javascript"></script>
<%--<script src="${ctx}/res/bootstrap/bootstrap-dialog.min.js" type="text/javascript"></script>--%>
<script src="${ctx}/res/js/plugin/uploadify/jquery.uploadify.js" type="text/javascript"></script>
<script language="JavaScript">
    var ctx = '${ctx}';
</script>
<style type="text/css">
    #acrossModal,#verticalModal,#editcontent_myModal{
        width: 800px;
    }
    .fixed-table-toolbar .columns {
        top: -27px;
    }clearfix
    #leftdiv{
        position: relative;
        left:10px;
        padding-left:10px;
        padding-top:10px;
    }
    #rightdiv{
        padding-top:10px;
    }
    #lefttable td{
        width:30px;
        white-space: nowrap;
    }
    .fixed-table-toolbar{
        height:0px;
    }
    .pull-right{
        height:0px;
    }
    .keep-open{
        height: 0px;
    }
    #btn_arcossSelect{
        vertical-align: 1px;
    }
    #btn_verticalSelect{
        vertical-align: 1px;
    }
    #btn_addVertical{
        vertical-align: 1px;
    }
    #selectIsEffective{
        vertical-align: 1px;
        -webkit-border-radius:4px;
        -moz-border-radius:4px;
        -ms-border-radius:4px;
        -o-border-radius:4px;
    }
    .modal-footer{
        text-align: right;
    }
    .fixed-table-pagination{
        height: 50px;
    }
    .fixed-table-toolbar .btn-group > .btn-group:last-child > .btn {
        top: -32px;
    }
    #acrossModal{
        position:relative;
        left:40%;
        margin-top:0px;
        [;top:350px;]
    }
    #verticalModal{
        position:relative;
        left:40%;
        margin-top:0px;
        [;top:350px;]
    }

    #editcontent_myModal{
        position:relative;
        left:40%;
        display: none;
    }
    #sample_vertical_info{
        height: 0;
    }
    #acrossModal,#addModal,#verticalModal{
        display: none;
    }
    .btn-xs{
        vertical-align: middle;
    }
    #txt_acrossName,#btn_addAcross,#txt_verticalName{
        vertical-align: 1px;
    }
    .txt_acrossName_label{
        display: inline-block;
        margin-left: 20px;
    }
    #txt_title,#txt_secondTitle,#txt_isPlay{
        vertical-align: -10px;
    }
    #contentType{
        vertical-align: -1px;
    }
    .fixed-table-body{
        width:100%;
    }
</style>
<script src="${ctx}/res/js/bootstrap-modal.js" type="text/javascript"></script>
<script src="${ctx}/res/js/bootstrap-modalmanager.js" type="text/javascript"></script>
<script src="${ctx}/res/js/acrossVertical/acrossVertical.js" type="text/javascript"></script>
<script src="${ctx}/res/js/jquery.flot.resize.js" type="text/javascript"></script>
<script src="${ctx}/res/js/plugin/ajaxupload.3.6.js" type="text/javascript"></script>
<script src="${ctx}/res/js/common/common.js" type="text/javascript"></script>
<div class="container-fluid">
    <!-- BEGIN PAGE HEADER-->
    <div class="row-fluid">
        <div class="span12">
            <!-- BEGIN PAGE TITLE & BREADCRUMB-->
            <h3 class="page-title">
                 首屏横竖图推荐管理
                <small></small>
            </h3>
            <ul class="breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="${ctx}">Home</a>
                    <i class="icon-angle-right"></i>
                </li>
                <li><a href="#">首屏横竖图推荐管理</a></li>
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
                    <div class="clearfix" style="height: 50px;">
                        <div class="btn-group" style="position: relative;top:-4px;">
                            <label style="display: inline-block;margin-bottom: 5px;">
                                <button id="btn_add" class="btn yellow" onclick="add()">
                                    添加
                                </button>
                            </label>
                            <label style="display: inline-block;padding-top: 5px;">
                                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;横竖图推荐位名称：</span>
                                <input type="text" class="form-control" name="txt_selectName" id="txt_selectName" style="margin-top: 5px">
                            </label>
                            <label style="display: inline-block;padding-top: 5px;">
                                <span>&nbsp;&nbsp;&nbsp;&nbsp;上下线：</span>
                                <select class="form-control selectpicker" data-mobile="true" name="txt_selectIsEffective" id="txt_selectIsEffective" style="margin-top: 5px;">
                                    <option value="">全部</option>
                                    <option value="1">上线</option>
                                    <option value="0">下线</option>
                                </select>
                            </label>
                            <button id="selectIsEffective" class="btn btn-default" onclick="selectIsEffective();">查询</button>
                        </div>
                    </div>
                    <table class="table table-striped table-bordered table-hover dataTable" id="sample_1"
                           aria-describedby="sample_1_info">
                    </table>
                    <div class="row-fluid">
                        <div class="span6" style="display: none;">
                            <div class="dataTables_info" id="sample_1_info"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 横竖图推荐管理（Modal） -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="addclear_diaglog();"></button>
                <h4 class="modal-title" id="myModalLabel">横竖图推荐管理</h4>
            </div>
            <div class="modal-body">
                <input id="txt_recommendId" type="hidden">
                <div class="form-group">
                    <label for="txt_recommendName" style="display: inline-block;"><span style="color: red">*</span>横竖图推荐位名称：</label>
                    <input type="text" name="txt_recommendName" class="form-control" id="txt_recommendName" style="vertical-align: -1px;">
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="acrossVertical_submit();">保存</button>
                <button type="button" class="btn btn-default" onclick="addclear_diaglog();">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!-- 横图推荐位内容管理（Modal） -->
<div class="modal fade" id="acrossModal" tabindex="-1" role="dialog" aria-labelledby="myModal" aria-hidden="true" style="width: 800px;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="acrossclear_diaglog();"></button>
                <h4 class="modal-title" id="myModal">横图推荐位内容管理</h4>
            </div>
            <div class="clearfix">
                <div class="btn-group" style="position:relative;top:13px;">

                        <button id="btn_addAcross" class="btn yellow" onclick="addAcross()" style="border-top-right-radius:4px;border-bottom-right-radius:4px;-webkit-border-top-right-radius:4px;-webkit-border-bottom-right-radius:4px;">
                            添加
                        </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <label for="txt_acrossName" class="txt_acrossName_label">横图名称：</label><input type="text" class="form-control" name="txt_acrossName" id="txt_acrossName" style="margin-top: 5px;">&nbsp;&nbsp;
                        <button id="btn_arcossSelect" class="btn btn-default" onclick="selectArcoss();">查询</button>


                </div>
            </div>
            <input id="txt_belongRecommendId1" type="hidden">
            <input id="imageType1" type="hidden">
            <input id="txt_minSeq1" type="hidden">
            <table class="table table-striped table-bordered table-hover dataTable" id="sample_arcoss"
                   aria-describedby="sample_arcoss_info">
            </table>
            <div class="row-fluid">
                <div class="span6">
                    <div class="dataTables_info" id="sample_arcoss_info"></div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="acrossclear_diaglog();">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!-- 竖图推荐位内容管理（Modal） -->
<div class="modal fade" id="verticalModal" tabindex="-1" role="dialog" aria-labelledby="myModal" aria-hidden="true" style="width: 800px;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="verticalsclear_diaglog();"></button>
                <h4 class="modal-title" id="Modal">竖图推荐位内容管理</h4>
            </div>
            <div class="clearfix" style="position:relative;top:30px;">
                <div class="btn-group">
                   
                        <button id="btn_addVertical" class="btn yellow" onclick="addVertical()" style="border-top-right-radius:4px;border-bottom-right-radius:4px;-webkit-border-top-right-radius:4px;-webkit-border-bottom-right-radius:4px;">
                            添加
                        </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <label for="txt_verticalName" style="display: inline-block;margin-left: 20px;">竖图名称：</label><input type="text" class="form-control" name="txt_verticalName" id="txt_verticalName" style="margin-top: 5px">&nbsp;&nbsp;
                        <button id="btn_verticalSelect" class="btn btn-default" onclick="selectVertical();">查询</button>
                    
            </div>
            <input id="txt_belongRecommendId2" type="hidden"><input id="imageType2" type="hidden">
                <input id="txt_minSeq2" type="hidden">
            <table class="table table-striped table-bordered table-hover dataTable" id="sample_vertical"
                   aria-describedby="sample_vertical_info">
            </table>
            <div class="row-fluid">
                <div class="span6">
                    <div class="dataTables_info" id="sample_vertical_info"></div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="verticalsclear_diaglog();">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div style = "width: 820px" class="modal fade bs-example-modal-lg" id="editcontent_myModal" tabindex="-1" role="dialog" aria-labelledby="editcontent_myModalLabel" aria-hidden="true" style="width:auto;">
    <div class="modal-dialog modal-lg" style="width: 800px;height: 550px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="close_contentdiaglog();"></button>
                <h4 class="modal-title" id="editcontent_myModalLabel">编辑推荐位类型</h4>
            </div>
            <%--<div class="container"></div>--%>
            <div class="modal-body" style="padding: 0px;">
                <input id="editcontent_recommendId" type="hidden">
                <input id="edit_id" type="hidden">
                <input id="editcontent_id" type="hidden">
                <input id="txt_contentname" type="hidden">
                <input id="txt_maxSeq1" type="hidden">
                <input id="txt_maxSeq2" type="hidden">
                <div style="float: left;width:300px;height:462px;border: 1px #99BBE8 solid;padding-left:15px;" id="leftdiv">
                    <table id="lefttable">
                        <tr>
                            <td><label for="txt_title">  <span style="color: red">*</span>标题：</label></td><td><input type="text" id="txt_title"></td>
                        </tr>
                        <tr>
                            <td><label for="txt_secondTitle">副标题：</label></td><td><input type="text" id="txt_secondTitle"></td>
                        </tr>
                        <tr>
                            <td><label for="txt_isPlay">内容类型：</label></td><td><select id="txt_isPlay"><option value ='0' selected = true>图片</option></select></td>
                        </tr>
                        <tr>
                            <td>图片：</td><td>
                            <div id ="t">
                                <div class="txt"><input id="recommendContentImgUrl" type="file" value="" style="vertical-align: -45px;"></div>
                                <div class="pic"><img id="recommendContentImg" src="" alt="" width="160" height="50" /><input id="contentImg" type ="hidden"/><br><span id="imageSize"></span></div>
                            </div>
                            <div id ="s" style="display: none">
                                <div class="txt"><input id="m3u8" type="text" value=""></div>
                            </div>
                        </td>
                        </tr>
                    </table>
                </div>
                <div style="float: right;width:465px;height:452px;border: 1px #99BBE8 solid;" id="rightdiv"><label
                        for="contentType" style="display: inline-block">推荐位类型：</label>
                    <select onchange="dataSource()" id="contentType">
                        <option value ='1' selected = true>专辑详情</option>
                        <option value ='0'>影视专题</option>
                        <option value ='6'>全屏大图</option>
                        <option value ='2'>应用详情</option>
                        <option value ='3'>应用专题</option>
                        <option value ='4'>活动详情</option>
                        <option value ='7'>商品包</option>
                        <option value ='8'>商品专题页</option>
                        <option value ='9'>商品详情页</option>
                        <option value ='10'>商城首页</option>
                    </select>
                    <div id ="div_1"><label for="templet" style="display: inline-block;">模板：</label><select id ="templet" style="width: 91px;vertical-align: -1px;"></select>
                        <label for="templetApk" style="display: inline-block;">包名：</label><select style="width: 106px;vertical-align: -1px;" id = "templetApk" onchange="initTempletApkBindEvent();"></select><div id ="dm" style="display: inline">
                            <label for="templetApkChannelType" style="display: inline-block;">频道类型：</label><select style="width: 91px;vertical-align: -1px;" id = "templetApkChannelType" onchange="initTempletApkChannelTypeBindEvent();"></select><label
                                for="templetApkChannel" style="display: inline-block;">&nbsp;频道：</label><select style="width: 142px;vertical-align: -1px;" id = "templetApkChannel"></select></div>
                        <label for="pay" style="display: inline-block;">付费：</label><select style="width: 106px;vertical-align: -1px;" id ="pay"></select><label
                                for="vip" style="display: inline-block;">&nbsp;VIP：</label><select style="width: 100px;vertical-align: -1px;" id ="vip"></select>&nbsp;<div id ="jq" style="display:none">
                            <label for="c" style="display: inline-block;">热词：</label><select style="width: 92px;vertical-align: -1px;" id = "c"></select></div>
                        <label for="name_1" style="display: inline-block;">名称：</label><input id="name_1" style="width:80px;vertical-align: -1px;"><button class="btn btn-default" onclick="dataSource();" style="vertical-align: -1px;">查询</button><table id ="content1"></table></div>
                    <div id ="div_6"><label for="name_6" style="display: inline-block;">名称：</label><input id="name_6" style="width:80px;vertical-align: -1px;"><button class="btn btn-default" onclick="dataSource();" style="vertical-align: -1px;">查询</button><table id ="content6"></table></div>
                    <div id ="div_0"><label for="templet_subject" style="display: inline-block;">模板：</label><select id ="templet_subject" style="width: 91px;vertical-align: -1px;"></select><label
                            for="templetApk_subject" style="display: inline-block;">包名：</label><select style="width: 106px;vertical-align: -1px;" id = "templetApk_subject"></select><label
                            for="name_0" style="display: inline-block;">&nbsp;名称：</label><input id="name_0" style="width:80px;vertical-align: -1px;"><button class="btn btn-default" onclick="dataSource();" style="vertical-align: -1px;">查询</button><table id ="content0"></table></div>
                    <div id ="div_2"><label for="name_2" style="display: inline-block;">名称：</label><input id="name_2" style="width:80px;vertical-align: -1px;"><label
                            for="app_type" style="display: inline-block;">应用类型：</label><select id="app_type" style="width:80px;vertical-align: -1px;"></select><button class="btn btn-default" onclick="dataSource();" style="vertical-align: -1px;">查询</button><table id ="content2"></table></div>
                    <div id ="div_3"><label for="name_3" style="display: inline-block;">名称：</label><input id="name_3" style="width:80px;vertical-align: -1px;"><label
                            for="appsubject_type" style="display: inline-block;">应用类型：</label><select id="appsubject_type" style="width:80px;vertical-align: -1px;"></select><button class="btn btn-default" onclick="dataSource();" style="vertical-align: -1px;">查询</button><table id ="content3"></table></div>
                    <div id ="div_4"><label for="name_4" style="display: inline-block;">名称：</label><input id="name_4" style="width:80px;vertical-align: -1px;"><button class="btn btn-default" onclick="dataSource();" style="vertical-align: -1px;">查询</button><table id ="content4"></table></div>
                    <div id ="div_7"><label for="name_7" style="display: inline-block;">名称：</label><input id="name_7" style="width:80px;vertical-align: -1px;"><button class="btn btn-default" onclick="dataSource();" style="vertical-align: -1px;">查询</button><table id ="content7"></table></div>

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id ="save" onclick="submitByImage();">保存</button>
                <button type="button" class="btn btn-default" onclick="close_contentdiaglog();">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>