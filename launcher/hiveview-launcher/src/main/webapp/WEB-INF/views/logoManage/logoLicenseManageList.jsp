<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<link href="${ctx}/res/bootstrap/bootstrap-table.css" rel="stylesheet">
<link href="${ctx}/res/js/plugin/uploadify/uploadify.css" rel="stylesheet">
<link href="${ctx}/res/css/bootstrap-modal.css" rel="stylesheet">
<%--<link href="${ctx}/res/bootstrap/bootstrap-dialog.min.css" rel="stylesheet">--%>
<%--<link href="${ctx}/res/css/bootstrap-modal.css" rel="stylesheet">--%>
<script src="${ctx}/res/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/res/bootstrap/bootstrap-table.js" type="text/javascript"></script>
<script src="${ctx}/res/bootstrap/bootstrap-table-zh-CN.js"></script>
<script src="${ctx}/res/js/jquery.flot.js" type="text/javascript"></script>
<%--<script src="${ctx}/res/bootstrap/bootstrap-dialog.min.js" type="text/javascript"></script>--%>
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
        width:64px;
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
<%--<script src="${ctx}/res/js/bootstrap-modal.js" type="text/javascript"></script>
<script src="${ctx}/res/js/bootstrap-modalmanager.js" type="text/javascript"></script>--%>
<script src="${ctx}/res/js/logoManage/logo_license_manage_list.js" type="text/javascript"></script>
<script src="${ctx}/res/js/jquery.flot.resize.js" type="text/javascript"></script>
<script src="${ctx}/res/js/plugin/uploadify/jquery.uploadify.js" type="text/javascript"></script>
<div class="container-fluid">
    <!-- BEGIN PAGE HEADER-->
    <div class="row-fluid">
        <div class="span12">
            <!-- BEGIN PAGE TITLE & BREADCRUMB-->
            <h3 class="page-title">
                牌照LOGO管理
                <small></small>
            </h3>
            <ul class="breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="${ctx}">Home</a>
                    <i class="icon-angle-right"></i>
                </li>
                <li><a href="#">牌照LOGO管理</a></li>
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

                                <button id="addLogoLicense" class="btn btn-default" onclick="addLogoLicenseManage();">添加</button>
                            <label for="searchLogoLicenseName" style="display: inline-block;"><span style="margin-right:10px;">LOGO名称：</span></label><input type="text"  class="form-control" id="searchLogoLicenseName">
                            <label for="searchLogoLicenseState" style="display: inline-block;"><span style="margin-right:10px;">上下线：</span></label>


                                <select class="form-control selectpicker" data-mobile="true" name="text-operateType" id="searchLogoLicenseState" style="display: inline-block;">
                                    <option value="">全部</option>
                                    <option value="1">上线</option>
                                    <option value="0">下线</option>
                                </select>
                                <button id="searchLogoLicenseInfo" class="btn btn-default" onclick="searchLogoLicenseInfo();">查询</button>

                           <%-- <button id="addLogoLicense" class="btn yellow btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
                                新增
                            </button>--%>
                        </div>
                    </div>
                    <table class="table table-striped table-bordered table-hover dataTable" id="logoLicenseManageTable"
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

    <div class="modal fade" id="editLogoLicenseManageDiv" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title" id="myModalLabel">牌照LOGO图标配置</h4>
                </div>
                <div class="modal-body">
                    <input id="logo_license_id" type="hidden">
                    <input id="logoState" type="hidden">
                    <div class="form-group">
                        <label for="logo_license_name" style="display: inline-block;"><span style="color:red">*</span>LOGO名称：</label>
                        <input type="text" name="logo_license_name" class="form-control" id="logo_license_name" placeholder="LOGO名称" style="vertical-align: -1px;">
                    </div>
                    <div class="form-group">
                        <label style="display: inline-block;vertical-align: -1px;"><span style="color:red" style="display: inline-block;">*</span>图片：</label>
                        <div class="txt" style="display: inline-block;"><input id="logoLicenseImgUrl" type="file" value="" style="vertical-align: -1px;"></div>
                        <div class="pic"><img id="logoLicenseImg" src="" alt="" width="160" height="50" /></div>
                    </div>
                </div>
                <div class="modal-footer">
                    <%--<button type="button" class="btn btn-primary" onclick="closeEditLogoLicenseManageDiv();">关闭</button>--%>
                    <button type="button" class="btn btn-default" onclick="submitLogoLicenseManageInfo();">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

</div>
