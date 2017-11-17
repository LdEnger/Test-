<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<link href="${ctx}/res/bootstrap/bootstrap-table.css" rel="stylesheet">
<link href="${ctx}/res/js/plugin/uploadify/uploadify.css" rel="stylesheet">
<link href="${ctx}/res/css/bootstrap-modal.css" rel="stylesheet">
<script src="${ctx}/res/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/res/bootstrap/bootstrap-table.js" type="text/javascript"></script>
<script src="${ctx}/res/bootstrap/bootstrap-table-zh-CN.js"></script>
<script src="${ctx}/res/js/jquery.flot.js" type="text/javascript"></script>
<script src="${ctx}/res/js/bootstrap-modal.js" type="text/javascript"></script>
<script src="${ctx}/res/js/bootstrap-modalmanager.js" type="text/javascript"></script>
<script language="JavaScript">
    var ctx = '${ctx}';
</script>
<style>
    #addLogoLicense,#searchLogoLicenseState{
        margin-right:40px;
    }
    #searchLogoLicenseName{
        width : 120px;
        margin-right:30px;
    }
    .fixed-table-toolbar .columns{
        height:0px;
    }
    .modal-backdrop, .modal-backdrop.fade.in {
        opacity: 0.7;
        filter: alpha(opacity=70);
        background: #888;
    }
    #searchLogoLicenseInfo{
        vertical-align: 1px;
        -webkit-border-radius:4px;
        -moz-border-radius:4px;
        -ms-border-radius:4px;
        -o-border-radius:4px;
    }
    #addLogoLicense{
        vertical-align: 1px;
        border-radius:4px;
        -webkit-border-radius:4px;
        -moz-border-radius:4px;
        -ms-border-radius:4px;
        -o-border-radius:4px;
    }
    .fixed-table-toolbar .btn-group > .btn-group:last-child > .btn {
        top: -43px;
    }
    #searchLogoLicenseState{
        margin-right:0px;
    }
    .btn-xs{
        vertical-align: middle;
    }
    .portlet{
        margin-bottom:40px;
    }
    .table-striped tbody>tr:nth-child(odd)>td, .table-striped tbody>tr:nth-child(odd)>th{
        background:none;
    }
    .table-hover tbody tr:hover>td{
        background:none;
    }
</style>
<script src="${ctx}/res/js/jumpInstruction/jumpInstruction.js" type="text/javascript"></script>
<script src="${ctx}/res/js/jquery.flot.resize.js" type="text/javascript"></script>
<script src="${ctx}/res/js/plugin/uploadify/jquery.uploadify.js" type="text/javascript"></script>
<div class="container-fluid">
    <!-- BEGIN PAGE HEADER-->
    <div class="row-fluid">
        <div class="span12">
            <!-- BEGIN PAGE TITLE & BREADCRUMB-->
            <h3 class="page-title">
                推荐位跳转指令管理
                <small></small>
            </h3>
            <ul class="breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="${ctx}">Home</a>
                    <i class="icon-angle-right"></i>
                </li>
                <li><a href="#">推荐位跳转指令管理</a></li>
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
                    <div class="clearfix" style="position:relative;top:9px;">
                        <div class="btn-group">

                                <button id="addLogoLicense" class="btn btn-default" onclick="addJumpInstruction();">添加</button>
                            <label for="searchType" style="display: inline-block;"><span style="margin-right:10px;">启动名称：</span></label><input type="text"  class="form-control" id="searchType">
                            <label for="searchEffective" style="display: inline-block;"><span style="margin-right:10px;">上下线：</span></label>


                                <select class="form-control selectpicker" data-mobile="true" name="text-operateType" id="searchEffective" style="display: inline-block;width: 64px;">
                                    <option value="">全部</option>
                                    <option value="0">上线</option>
                                    <option value="1">下线</option>
                                </select>
                                <button id="searchJumpInstructionInfo" class="btn btn-default" onclick="searchJumpInstructionInfo();" style="vertical-align: 1px;">查询</button>
                        </div>
                    </div>
                    <table class="table table-striped table-bordered table-hover dataTable" id="jumpInstructionTable"
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

    <div class="modal fade" id="editJumpInstructionDiv" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title" id="myModalLabel">跳转指令配置</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="id" style="display: inline-block;"><span style="color:red">*</span>ID：</label>
                        <input type="text" name="logo_license_name" class="form-control" id="id" placeholder="请输入int类型的ID" style="vertical-align: -1px;">
                    </div>
                    <div class="form-group">
                        <label for="type" style="display: inline-block;"><span style="color:red">*</span>启动类型：</label>
                        <select class="form-control selectpicker" data-mobile="true" name="text-operateType" id="type" style="display: inline-block;">
                            <option value="">请选择</option>
                            <option value="专辑详情">专辑详情</option>
                            <option value="影视专题">影视专题</option>
                            <option value="应用详情">应用详情</option>
                            <option value="应用专题">应用专题</option>
                            <option value="专区">专区</option>
                            <option value="活动详情">活动详情</option>
                            <option value="全屏大图">全屏大图</option>
                            <option value="商品包详情页">商品包详情页</option>
                            <option value="优购专题页">优购专题页</option>
                            <option value="优购详情页">优购详情页</option>
                            <option value="Tab页">Tab页</option>
                            <option value="广告专题页">广告专题页</option>
                            <option value="广告播放页">广告播放页</option>
                            <option value="启动应用">启动应用</option>
                            <option value="会员活动">会员活动</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="actionName" style="display: inline-block;"><span style="color:red">*</span>action名称：</label>
                        <input type="text" name="logo_license_name" class="form-control" id="actionName" placeholder="请输入action名称" style="vertical-align: -1px;">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" onclick="submitJumpInstructionInfo();">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

</div>
