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
<style type="text/css">
    #tabGroupDiv{
        width: 1200px;
        height: 500px;
        left: 30%;
    }
    .fixed-table-toolbar .btn-group > .btn-group {
         display: inline-block;
         /*margin-left: -1px !important;*/
         position: absolute;
         right: -100%;
     }
    .fixed-table-toolbar .btn-group > .btn-group:last-child > .btn {
        top: -43px;
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
<script src="${ctx}/res/js/tab/tab.js" type="text/javascript"></script>
<script src="${ctx}/res/js/jquery.flot.resize.js" type="text/javascript"></script>
<script src="${ctx}/res/js/plugin/uploadify/jquery.uploadify.js" type="text/javascript"></script>
<div class="container-fluid">
    <!-- BEGIN PAGE HEADER-->
    <div class="row-fluid">
        <div class="span12">
            <!-- BEGIN PAGE TITLE & BREADCRUMB-->
            <h3 class="page-title">
                Tab管理
                <small></small>
            </h3>
            <ul class="breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="${ctx}">Home</a>
                    <i class="icon-angle-right"></i>
                </li>
                <li><a href="#">Tab管理</a></li>
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

                                <button id="addTab" class="btn btn-default" onclick="addTab();" style="vertical-align: 1px;">添加</button>
                            <label for="searchTabName" style="display: inline-block;"><span style="margin-right:10px;">Tab名称：</span></label><input type="text"  class="form-control" id="searchTabName">
                            <label for="searchIsEffective" style="display: inline-block;"><span style="margin-right:10px;">上下线：</span></label>


                                <select class="form-control selectpicker" data-mobile="true" name="text-operateType" id="searchIsEffective" style="display: inline-block;width: 64px;">
                                    <option value="">全部</option>
                                    <option value="1">上线</option>
                                    <option value="0">下线</option>
                                </select>
                                <button id="searchTabInfo" class="btn btn-default" onclick="searchTabInfo();" style="vertical-align: 1px;">查询</button>
                        </div>
                    </div>
                    <table class="table table-striped table-bordered table-hover dataTable" id="tabTable"
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

    <div class="modal fade" id="editTabDiv" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title" id="myModalLabel">Tab管理</h4>
                </div>
                <div class="modal-body">
                    <input id="id" type="hidden">
                    <div class="form-group">
                        <label for="tabName" style="display: inline-block;"><span style="color:red">*</span>Tab名称：</label>
                        <input type="text" name="logo_license_name" class="form-control" id="tabName" placeholder="Tab名称" style="vertical-align: -1px;">
                    </div>
                    <div class="form-group">
                        <label for="tabTitle" style="display: inline-block;"><span style="color:red">*</span>Tab标题名称：</label>
                        <input type="text" name="logo_license_name" class="form-control" id="tabTitle" placeholder="Tab标题名称" style="vertical-align: -1px;">
                    </div>
                    <div class="form-group">
                        <label style="display: inline-block;vertical-align: -1px;">Tab背景图片：</label>
                        <div class="txt" style="display: inline-block;"><input id="tabBackPicImgUrl" type="file" value="" style="vertical-align: -1px;"></div>
                        <div class="pic"><img id="tabBackPicImg" src="" alt="" width="600px" height="200px" />图片大小1920*1080</div>
                    </div>
                    <div class="form-group">
                        <label style="display: inline-block;vertical-align: -1px;">导航栏图标：</label>
                        <div class="txt" style="display: inline-block;"><input id="tabIconImgUrl" type="file" value="" style="vertical-align: -1px;"></div>
                        <div class="pic"><img id="tabIconImg" src="" alt="" width="100px" height="20px" />图片大小w*70(宽度W可自定义)</div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" onclick="submitTabInfo();">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

    <!-- 关联Group列表 -->
    <div class="modal fade" id="tabGroupDiv" tabindex="-1" role="dialog" aria-labelledby="myModal" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content" style="float: left;width: 55%">
                <div class="modal-header">
                    <h4 class="modal-title" id="linkGroup">关联Group列表</h4>
                </div>
                <input type="hidden" id="tabId">
                <table class="table table-striped table-bordered table-hover dataTable" id="tabGroupTable"
                       aria-describedby="sample_2_info">
                </table>
                <div class="row-fluid">
                    <div class="span6">
                        <div class="dataTables_info" id="sample_2_info"></div>
                    </div>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
        <div class="modal-dialog" style="float: left;width: 45%">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title" id="group">Group列表（单击选中的一行关联Group）</h4>
                </div>
                <div class="">
                    <div class="btn-group">
                        <label for="showGroupType" style="display: inline-block;">Group类型：</label>
                        <select onchange="showGroupType()" id="showGroupType" style="width: auto">
                            <option value ='1' selected = true>Group</option>
                            <option value ='2'>spe Group</option>
                            <option value ='3'>data Group</option>
                        </select>
                        <label for="searchGroupName" style="display: inline-block;"><span style="margin-right:10px;">Group名称：</span></label><input type="text"  class="form-control" id="searchGroupName" style="width: 100px;">
                        <button id="searchJumpInstructionInfo" class="btn btn-default" onclick="searchGroupInfo();">查询</button>
                    </div>
                </div>
                <input type="hidden" id="tabId2">
                <table class="table table-striped table-bordered table-hover dataTable" id="groupTable"
                       aria-describedby="sample_3_info">
                </table>
                <div class="row-fluid">
                    <div class="span6">
                        <div class="dataTables_info" id="sample_3_info"></div>
                    </div>
                </div>
<%--                <div class="modal-footer" >
                    <button type="button" class="btn btn-default"  onclick="data_submit();" id="data_submit">保存</button>
                    <button type="button" class="btn btn-default"  onclick="clear_submit();" id="clear_submit">关闭</button>
                </div>--%>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
        <div class="modal-footer" >
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        </div>
    </div>

    <!-- Group列表-->
<%--    <div class="modal fade" id="groupDiv" tabindex="-1" role="dialog" aria-labelledby="myModal" aria-hidden="true" style="position:relative;left: 50%;
    margin-top: 217.5px;height: auto;">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title" id="group">Group列表</h4>
                </div>
                <div class="">
                    <div class="btn-group">
                        <label for="showGroupType" style="display: inline-block;">Group类型：</label>
                        <select onchange="showGroupType()" id="showGroupType" style="width: auto">
                            <option value ='1' selected = true>Group</option>
                            <option value ='2'>spe Group</option>
                            <option value ='3'>data Group</option>
                        </select>
                        <label for="searchGroupName" style="display: inline-block;"><span style="margin-right:10px;">Group名称：</span></label><input type="text"  class="form-control" id="searchGroupName" style="width: auto">
                        <button id="searchJumpInstructionInfo" class="btn btn-default" onclick="searchGroupInfo();">查询</button>
                    </div>
                </div>
                <input type="hidden" id="tabId2">
                <table class="table table-striped table-bordered table-hover dataTable" id="groupTable"
                       aria-describedby="sample_3_info">
                </table>
                <div class="row-fluid">
                    <div class="span6">
                        <div class="dataTables_info" id="sample_3_info"></div>
                    </div>
                </div>
                <div class="modal-footer" >
                    <button type="button" class="btn btn-default"  onclick="data_submit();" id="data_submit">保存</button>
                    <button type="button" class="btn btn-default"  onclick="clear_submit();" id="clear_submit">关闭</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>--%>


</div>
