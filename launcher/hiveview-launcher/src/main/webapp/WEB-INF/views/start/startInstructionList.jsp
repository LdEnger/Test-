<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<link href="${ctx}/res/bootstrap/bootstrap-table.css" rel="stylesheet">
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
<style>
    .fixed-table-toolbar .columns {
        height:0px;
    }
    /*.fixed-table-container{*/
        /*top: -55px;*/
    /*}*/
    #selectIsEffective{
        vertical-align: 1px;
        -webkit-border-radius:4px;
        -moz-border-radius:4px;
        -ms-border-radius:4px;
        -o-border-radius:4px;
    }
    #btn_add{
        vertical-align: 1px;
        -webkit-border-radius:4px;
        -moz-border-radius:4px;
        -ms-border-radius:4px;
        -o-border-radius:4px;
    }
    #selectIsEffective{
        vertical-align: -3px;
    }
    .fixed-table-toolbar .btn-group > .btn-group:last-child > .btn {
        top: -47px;
    }
    .btn-xs{
        vertical-align: middle;
    }
    .table-striped tbody>tr:nth-child(odd)>td, .table-striped tbody>tr:nth-child(odd)>th{
        background:none;
    }
    .table-hover tbody tr:hover>td{
        background:none;
    }
</style>
<script src="${ctx}/res/js/bootstrap-modal.js" type="text/javascript"></script>
<script src="${ctx}/res/js/bootstrap-modalmanager.js" type="text/javascript"></script>
<script src="${ctx}/res/js/start/startInstruction.js" type="text/javascript"></script>
<script src="${ctx}/res/js/jquery.flot.resize.js" type="text/javascript"></script>
<script src="${ctx}/res/js/plugin/ajaxupload.3.6.js" type="text/javascript"></script>
<div class="container-fluid">
    <!-- BEGIN PAGE HEADER-->
    <div class="row-fluid">
        <div class="span12">
            <!-- BEGIN PAGE TITLE & BREADCRUMB-->
            <h3 class="page-title">
                开机启动指令管理
                <small></small>
            </h3>
            <ul class="breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="${ctx}">Home</a>
                    <i class="icon-angle-right"></i>
                </li>
                <li><a href="#">开机启动指令管理</a></li>
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
                    <div class="clearfix" style="position: relative;top:4px;">
                        <div class="btn-group">
                            <label style="display: inline-block;margin-bottom: 5px;">
                                <button id="btn_add" class="btn btn-default" onclick="add()">
                                    添加
                                </button>
                            </label>
                            <label style="display: inline-block;padding-left: 25px;">
                                启动名称：
                            </label>
                            <input type="text" style="margin-left: 5px;" name="appname" id="appname">
                            <label style="display: inline-block;padding-top: 5px;">
                                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上下线：</span></label>
                                <select class="form-control selectpicker" data-mobile="true" name="txt_selectIsEffective" id="txt_selectIsEffective" style="margin-top: 5px;width: 64px;">
                                    <option value="">全部</option>
                                    <option value="1">上线</option>
                                    <option value="0">下线</option>
                                </select>&nbsp;&nbsp;
                                <button id="selectIsEffective" class="btn btn-default" onclick="select();">查询</button>
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
                <h4 class="modal-title" id="myModalLabel">启动指令管理</h4>
            </div>
            <div class="modal-body">
                <input id="txt_startInstructionId" type="hidden">
                <div class="form-group">
                    <label for="txt_AppName" style="display: inline-block;">启动名称：</label>
                    <input type="text" name="txt_AppName" class="form-control" id="txt_AppName" placeholder="应用名称" style="vertical-align: -1px;margin-left: 31px;">
                </div>
                <%--<div class="form-group">
                    <label for="txt_startType" style="display: inline-block;">启动类型：</label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <select class="form-control selectpicker" data-mobile="true" name="txt_startType" id="txt_startType" style="vertical-align: -1px;margin-left: 18px;">
                        <option value="1" selected="selected">开机启动</option>
                        <option value="0">非开机启动</option>
                    </select>

                </div>--%>
                <div class="form-group">
                    <label for="txt_instructionType" style="display: inline-block;">字符串类型：</label>
                    <select class="form-control selectpicker" data-mobile="true" name="txt_instructionType" id="txt_instructionType" style="vertical-align: -1px;margin-left: 18px;">
                        <option value="1" selected="selected">包名</option>
                        <option value="0">Action名</option>
                    </select>

                </div>
                <div class="form-group">
                    <label for="txt_characterString" style="display: inline-block;">字符串：</label>
                    <input type="text" name="txt_characterString" class="form-control" id="txt_characterString" placeholder="字符串" style="vertical-align: -1px;margin-left: 46px;">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="startInstruction_submit();">保存</button>
                <button type="button" class="btn btn-default" onclick="clear_diaglog();">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>