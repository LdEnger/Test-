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
<style type="text/css">
    .fixed-table-toolbar .columns {
        top: -13px;
        height:0px;
    }
    #manageModal{
        width: 800px;
        position: relative;
        left:40%;
        [;top:350px;];
    }
    #addModal,#manageModal{
        display: none;
    }
    .portlet{
        margin-bottom:50px;
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
<script src="${ctx}/res/js/macsn/macArea.js" type="text/javascript"></script>
<script src="${ctx}/res/js/jquery.flot.resize.js" type="text/javascript"></script>
<script src="${ctx}/res/js/plugin/ajaxupload.3.6.js" type="text/javascript"></script>
<div class="container-fluid">
    <!-- BEGIN PAGE HEADER-->
    <div class="row-fluid">
        <div class="span12">
            <!-- BEGIN PAGE TITLE & BREADCRUMB-->
            <h3 class="page-title">
                MACSN名单组管理
                <small></small>
            </h3>
            <ul class="breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="${ctx}">Home</a>
                    <i class="icon-angle-right"></i>
                </li>
                <li><a href="#">MACSN名单组管理</a></li>
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
                    <div class="clearfix" style="display:inline-block;position: relative;top:3px;">
                        <div class="btn-group">
                            <button id="btn_add" class="btn yellow"  onclick="add()">
                                单个添加
                            </button>
                        </div>&nbsp;&nbsp;
                        <div class="btn-group">
                            <button id="btn_manage" class="btn yellow" type="button" onclick="manage()">管理分组</button>
                        </div>&nbsp;&nbsp;
                        <div class="btn-group">
                            <button id="btn_import" class="btn yellow" type="button">导入excel</button>
                        </div>&nbsp;&nbsp;
                        <label style="display:inline-block;vertical-align: 3px;" for="txt_areaCode1">分组：</label>
                        <div class="btn-group">
                            <select class="form-control selectpicker" data-mobile="true" name="txt_areaCode1" id="txt_areaCode1" style="width: 150px;vertical-align: -21px;">
                            </select>
                        </div>
                    </div>
                    <div class="clearfix" style="display:inline-block;position: relative;top:0px;">
                        <label style="display:inline-block;" for="txt_seleteMac">MAC：</label><input type="text" name="txt_seleteMac" id="txt_seleteMac" style="width: 150px;vertical-align: 0px;">&nbsp;&nbsp;
                        <label style="display:inline-block;" for="txt_seleteSn">SN：</label><input type="text" name="txt_seleteSn" id="txt_seleteSn" style="width: 150px;vertical-align: 0px;">
                        <div class="btn-group">
                            <button id="btn_select" class="btn btn-default" onclick="select();" style="vertical-align: -16px;">查询</button>
                            <%--<button id="btn_select" class="btn btn-default" type="button" onclick="select()">查询</button>--%>
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
<!-- 添加单个框（Modal） -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="addclear_diaglog();"></button>
                <h4 class="modal-title" id="myModalLabel">添加</h4>
            </div>
            <div class="modal-body">
                <input id="txt_macAreaId" type="hidden">
                <div class="form-group">
                    <label for="txt_areaCode" style="display:inline-block;">选择分组：</label>
                    <select class="form-control selectpicker" data-mobile="true" name="txt_areaCode" id="txt_areaCode" style="vertical-align: -1px;">
                    </select>
                </div>
                <div class="form-group">
                    <label for="txt_areaMac" style="display:inline-block;">MAC：</label>
                    <input type="text" name="txt_areaMac" class="form-control" id="txt_areaMac" placeholder="MAC" style="vertical-align: -1px;margin-left: 25px;">
                </div>
                <div class="form-group">
                    <label for="txt_areaSn" style="display:inline-block;">SN：</label>
                    <input type="text" name="txt_areaSn" class="form-control" id="txt_areaSn" placeholder="SN" style="vertical-align: 2px;margin-left: 36px;">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="area_submit" onClick="area_submit();">保存</button>
                <button type="button" class="btn btn-default" onclick="addclear_diaglog();">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!-- 管理分组框（Modal） -->
<div class="modal fade" id="manageModal" tabindex="-1" role="dialog" aria-labelledby="myModal" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="manageclear_diaglog();"></button>
                <h4 class="modal-title" id="myModal">管理分组</h4>
            </div>
            <div class="clearfix">
                <div class="btn-group">
                    <button id="btn_addGroup" class="btn yellow"  onclick="addGroup()">
                        添加
                    </button>
                </div>
            </div>
            <table class="table table-striped table-bordered table-hover dataTable" id="sample_2"
                   aria-describedby="sample_2_info" style="margin-top:-1px;">
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

<!-- 添加分组框（Modal） -->
<div class="modal fade" id="addGroupModal" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="addGroupclear_diaglog();"></button>
                <h4 class="modal-title" id="ModalLabel">添加/编辑分组</h4>
            </div>
            <div class="modal-body">
                <input id="txt_areaGroupId" type="hidden">
                <div class="form-group">
                    <label for="txt_areaGroupCode" style="display: inline-block;">分组Code：</label>
                    <input type="text" name="txt_areaGroupCode" class="form-control" id="txt_areaGroupCode" placeholder="分组Code" style="vertical-align: -1px;">
                </div>
                <div class="form-group">
                    <label for="txt_areaGroupName" style="display: inline-block;">分组名称：</label>
                    <input type="text" name="txt_areaGroupName" class="form-control" id="txt_areaGroupName" placeholder="分组名称" style="vertical-align: -1px;margin-left: 6px;">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="areaGroup_submit();">保存</button>
                <button type="button" class="btn btn-default" onclick="addGroupclear_diaglog();">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>