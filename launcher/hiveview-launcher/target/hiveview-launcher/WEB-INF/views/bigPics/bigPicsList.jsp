<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<link href="${ctx}/res/bootstrap/bootstrap-table.css" rel="stylesheet">
<link href="${ctx}/res/js/plugin/uploadify/uploadify.css" rel="stylesheet">
<link href="${ctx}/res/css/bootstrap-modal.css" rel="stylesheet">
<%--<link href="${ctx}/res/bootstrap/bootstrap-dialog.min.css" rel="stylesheet">--%>
<script src="${ctx}/res/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/res/bootstrap/bootstrap-table.js" type="text/javascript"></script>
<script src="${ctx}/res/bootstrap/bootstrap-table-zh-CN.js"></script>
<script src="${ctx}/res/js/jquery.flot.js" type="text/javascript"></script>
<%--<script src="${ctx}/res/bootstrap/bootstrap-dialog.min.js" type="text/javascript"></script>--%>
<script language="JavaScript">
    var ctx = '${ctx}';
</script>
<script src="${ctx}/res/js/bootstrap-modal.js" type="text/javascript"></script>
<script src="${ctx}/res/js/bootstrap-modalmanager.js" type="text/javascript"></script>
<script src="${ctx}/res/js/bigPics/bigPicsList.js" type="text/javascript"></script>
<script src="${ctx}/res/js/jquery.flot.resize.js" type="text/javascript"></script>
<script src="${ctx}/res/js/plugin/uploadify/jquery.uploadify.js" type="text/javascript"></script>
<style>
    .table-striped tbody>tr:nth-child(odd)>td, .table-striped tbody>tr:nth-child(odd)>th{
        background:none;
    }
    .table-hover tbody tr:hover>td{
        background:none;
    }
    .fixed-table-toolbar .columns {
        height:0px;
    }
    .fixed-table-toolbar .btn-group > .btn-group:last-child > .btn{
        top:-48px;
    }
    .modal-backdrop, .modal-backdrop.fade.in {
        opacity: 0.7;
        filter: alpha(opacity=70);
        background: #888;
    }
    .portlet{
        margin-bottom: 40px;
    }
</style>
<div class="container-fluid">
    <!-- BEGIN PAGE HEADER-->
    <div class="row-fluid">
        <div class="span12">
            <!-- BEGIN PAGE TITLE & BREADCRUMB-->
            <h3 class="page-title">
                Tab背景/全屏大图管理
                <small></small>
            </h3>
            <ul class="breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="${ctx}">Home</a>
                    <i class="icon-angle-right"></i>
                </li>
                <li><a href="#">Tab背景/全屏大图管理</a></li>
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
                    <div class="clearfix" style="position: relative;top:5px;">
                        <div class="btn-group">
                            <div style="float:left ;  width:100px;  height:100%;">
                            <button id="btn_add" class="btn yellow" onclick="add()" >
                                添加
                            </button>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            </div>
                            <div style="float:left ;  width:50%; height:100%;">
                                <label  style="display: inline-block" for="imgNameLike"> 大图名称： &nbsp;&nbsp;</label>
                            <input type="text"  name="imgNameLike" id="imgNameLike">
                            <button class="btn yellow" onclick="bigPicsNameSelect()" style="vertical-align:top;">
                                查询
                            </button>
                            </div>
                        </div>

                    </div>
                    <table class="table table-striped table-bordered table-hover dataTable" id="sample_1"
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
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="clear_diaglog();"></button>
                <h4 class="modal-title" id="myModalLabel">Tab背景/全屏大图管理</h4>
            </div>
            <div class="modal-body">
                <input id="txt_bigPicsId" type="hidden">
                <div class="form-group">
                    <label for="txt_bigPicsName" style="display: inline-block;"> <span style=" color:red">*</span>大图名称：</label>
                    <input type="text" name="txt_bigPicsName" class="form-control" id="txt_bigPicsName" placeholder="应用名称" style="vertical-align: -1px;">
                </div>

             <div class="form-group">
                    <label style="display: inline-block;vertical-align: -1px;"> <span style="color:red">*</span>图片：</label>
                    <div class="txt" style="display: inline-block;"><input id="recommendContentImgUrl" type="file" value=""></div>
                    <div class="pic"><img id="recommendContentImg" src="" alt="" width="160" height="50" /></div>

                </div>

            </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="appIcon_submit();">保存</button>
                <button type="button" class="btn btn-default" onclick="clear_diaglog();">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>