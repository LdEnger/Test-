<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<link href="${ctx}/res/bootstrap/bootstrap-table.css" rel="stylesheet">
<link href="${ctx}/res/css/bootstrap-modal.css" rel="stylesheet">
<link href="${ctx}/res/js/plugin/uploadify/uploadify.css" rel="stylesheet">
<%--<link href="${ctx}/res/bootstrap/bootstrap-dialog.min.css" rel="stylesheet">--%>
<script src="${ctx}/res/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/res/bootstrap/bootstrap-table.js" type="text/javascript"></script>
<script src="${ctx}/res/bootstrap/bootstrap-table-zh-CN.js"></script>
<script src="${ctx}/res/js/jquery.flot.js" type="text/javascript"></script>
<%--<script src="${ctx}/res/bootstrap/bootstrap-dialog.min.js" type="text/javascript"></script>--%>
<script language="JavaScript">
    var ctx = '${ctx}';
</script>
<style type="text/css">
    #verSionManageModal{
        width: 800px;
    }
    .fixed-table-toolbar .btn-group > .btn-group:last-child > .btn{
        position: relative;
        top: 10px;
    }
    #sample_2 tbody tr td{
        word-break: break-all;
    }
    .fixed-table-container{
        top:9px;
    }
    .portlet-body .btn-group{
        position: relative;
        top: -29px;
    }
    #btn_select{
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
    .fixed-table-toolbar{
        height: 0px;
    }
     .btn-group {
        height: 0px;!important;
    }
    #btn_addApkVersion{
        top:-11px;
    }
    #verSionManageModal{
        position:relative;
        left: 40%;
        [;top: 370px;]
    }
    .btn-group>.btn:first-child {
        position: relative;
        top: 17px;
    }
    #verSionManageModal{
        display: none;
    }
    #txt_versionUrl_file_upload{
        vertical-align: top;
    }
    #txt_apkName,#txt_installStyle{
        vertical-align: -1px;
    }
    #txt_versionNo,#txt_versionUrl{
        vertical-align: 1px;
    }
    .fixed-table-body{
          width:100%;
      }
    .page-content{
        min-height: 700px;
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
<script src="${ctx}/res/js/apk/apk.js" type="text/javascript"></script>
<script src="${ctx}/res/js/jquery.flot.resize.js" type="text/javascript"></script>
<script src="${ctx}/res/js/plugin/uploadify/jquery.uploadify.js" type="text/javascript"></script>
<div class="container-fluid">
    <!-- BEGIN PAGE HEADER-->
    <div class="row-fluid">
        <div class="span12">
            <!-- BEGIN PAGE TITLE & BREADCRUMB-->
            <h3 class="page-title">
                定制APK管理
                <small></small>
            </h3>
            <ul class="breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="${ctx}">Home</a>
                    <i class="icon-angle-right"></i>
                </li>
                <li><a href="#">定制APK管理</a></li>
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
                    <div class="clearfix" style="position:relative;top:20px;">
                        <div class="btn-group">
                            <label style="display: inline-block;margin-bottom: 5px;">
                                <button id="btn_add" class="btn yellow" onclick="add()">
                                    添加
                                </button>
                            </label>
                            <label style="display: inline-block;padding-top: 5px;for="txt_seleteName">
                                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;APK名称：</span><input type="text" name="txt_seleteName" id="txt_seleteName" style="margin-top: 5px">
                            </label>
                            <button id="btn_select" class="btn btn-default" onclick="select();">查询</button>

                    </div>

                    <table class="table table-striped table-bordered table-hover dataTable" id="sample_1"
                           aria-describedby="sample_1_info">
                    </table>
                    <div class="row-fluid">
                        <div class="span6">
                            <div class="dataTables_info" id="sample_1_info"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 添加单个框（Modal） -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="addclear_diaglog();"></button>
                <h4 class="modal-title" id="myModalLabel">添加定制APK</h4>
            </div>
            <div class="modal-body">
                <input id="txt_id" type="hidden">
                <div class="form-group">
                    <label for="txt_apkName" style="display: inline-block;margin-right:22px;"><span style="color: red">*</span>名称：</label>
                    <input type="text" name="txt_apkName" class="form-control" id="txt_apkName" placeholder="APK名称">
                </div>
                <div class="form-group">
                    <label for="txt_packageName" style="display: inline-block;margin-right:22px;"><span style="color: red">*</span>包名：</label>
                    <input type="text" name="txt_packageName" class="form-control" id="txt_packageName" placeholder="包名">
                </div>
                <div class="form-group">
                    <label for="txt_appType" style="display: inline-block;">应用类型：</label>
                    <select class="form-control selectpicker" data-mobile="true" name="txt_appType" id="txt_appType">
                        <option value="0" selected="selected">系统应用</option>
                        <option value="1">定制应用</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="txt_installStyle" style="display: inline-block;">安装方式：</label>
                    <select class="form-control selectpicker" data-mobile="true" name="txt_installStyle" id="txt_installStyle">
                        <option value="1" selected="selected">静默安装</option>
                        <option value="0">询问安装</option>
                    </select>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="apk_submit();">保存</button>
                <button type="button" class="btn btn-default" onclick="addclear_diaglog();">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!-- 管理分组框（Modal） -->
<div class="modal fade" id="verSionManageModal" tabindex="-1" role="dialog" aria-labelledby="myModal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="manageclear_diaglog();"></button>
                <h4 class="modal-title" id="myModal">版本管理</h4>
            </div>
            <div class="clearfix">
                <div class="btn-group">
                    <button id="btn_addApkVersion" class="btn yellow" onclick="addApkVersion()">
                        添加
                    </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </div>
            </div>
            <input id="txt_apkId" type="hidden">
            <table class="table table-striped table-bordered table-hover dataTable" id="sample_2"
                   aria-describedby="sample_2_info">
            </table>
            <div class="row-fluid">
                <div class="span6">
                    <div class="dataTables_info" id="sample_2_info"></div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="manageclear_diaglog();">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!-- 添加版本框（Modal） -->
<div class="modal fade" id="addApkVersionModal" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="addApkVersionclear_diaglog();"></button>
                <h4 class="modal-title" id="ModalLabel">添加(编辑)版本</h4>
            </div>
            <div class="modal-body">
                <input id="txt_ApkVersionpId" type="hidden">
                <div class="form-group">
                    <label for="txt_versionNo" style="display: inline-block;margin-right:21px;"><span style="color: red">*</span>版本号：</label>
                    <input type="text" name="txt_versionNo" class="form-control" id="txt_versionNo" placeholder="版本号">
                </div>
                <%--<div class="form-group">
                    <label for="txt_packageName" style="display: inline-block;margin-right:35px;"><span style="color: red">*</span>包名：</label>
                    <input type="text" name="txt_packageName" class="form-control" id="txt_packageName" placeholder="包名">
                </div>--%>
                <div class="form-group">
                    <label for="txt_versionUrl" style="display: inline-block;margin-right:9px;"><span style="color: red">*</span>文件地址：</label>
                    <input type="text" name="txt_versionUrl" class="form-control" id="txt_versionUrl" readonly="true">
                    <input id="txt_versionUrl_file_upload" type="file" />
                </div>
                <div class="form-group">
                    <label for="txt_versionDescribe"> 版本简介：</label>
                    <textarea name="txt_versionDescribe" id="txt_versionDescribe" style="resize:none;width:515px;"></textarea>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="apkVersion_submit();">保存</button>
                <button type="button" class="btn btn-default" onclick="addApkVersionclear_diaglog();">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
    <%--<script>--%>
        <%--var container = document.getElementsByClassName('fixedTableContainer')[0];--%>
        <%--console.log(container);--%>
    <%--</script>--%>