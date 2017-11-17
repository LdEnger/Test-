<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<link href="${ctx}/res/bootstrap/bootstrap-table.css" rel="stylesheet">
<%--<link href="${ctx}/res/bootstrap/bootstrap-dialog.min.css" rel="stylesheet">--%>
<link href="${ctx}/res/css/bootstrap-modal.css" rel="stylesheet">
<script src="${ctx}/res/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/res/bootstrap/bootstrap-table.js" type="text/javascript"></script>
<script src="${ctx}/res/bootstrap/bootstrap-table-zh-CN.js"></script>
<script src="${ctx}/res/js/jquery.flot.js" type="text/javascript"></script>
<%--<script src="${ctx}/res/bootstrap/bootstrap-dialog.min.js" type="text/javascript"></script>--%>
<script language="JavaScript">
    var ctx = '${ctx}';
</script>
<style type="text/css">
    #sample_1{
        table-layout:fixed;
    }
    td{
        overflow:hidden;
        text-overflow:ellipsis;
        white-space:nowrap;
    }
    .fixed-table-toolbar .columns {
        top: -10px;
    }
    .fixed-table-container{
        top:-5px;
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
    .fixed-table-toolbar .btn-group > .btn-group:last-child > .btn {
        top: -47px;
    }
    .btn-xs{
        vertical-align: middle;
    }
    .fixed-table-body{
        width:100%;
    }
    .portlet{
        margin-bottom: 52px;
    }
</style>
<script src="${ctx}/res/js/bootstrap-modal.js" type="text/javascript"></script>
<script src="${ctx}/res/js/bootstrap-modalmanager.js" type="text/javascript"></script>
<script src="${ctx}/res/js/plugin/ajaxupload.3.6.js" type="text/javascript"></script>
<script src="${ctx}/res/js/beanCurd/beanCurd.js" type="text/javascript"></script>
<script src="${ctx}/res/js/jquery.flot.resize.js" type="text/javascript"></script>
<div class="container-fluid">
    <!-- BEGIN PAGE HEADER-->
    <div class="row-fluid">
        <div class="span12">
            <!-- BEGIN PAGE TITLE & BREADCRUMB-->
            <h3 class="page-title">
                豆腐块列表管理
                <small></small>
            </h3>
            <ul class="breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="${ctx}">Home</a>
                    <i class="icon-angle-right"></i>
                </li>
                <li><a href="#">豆腐块列表管理</a></li>
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
                    <div class="clearfix" style="position: relative;top:8px;">
                        <div class="btn-group">

                            <div style="float:left ;  width:106px;  height:100%;">
                                <button id="btn_add" class="btn yellow" onclick="beanCurdAdd()">
                                    添加
                                </button>
                            </div>
                            <div style="float:left ;display: inline; width:50%; height:100%;">
                              <%--  <span style="display: inline-block">列表名称</span>--%>
                                  <label for="beanCurdNameLike" style="display: inline-block"> 列表名称： &nbsp;&nbsp;</label>
                                <input type="text"  name="beanCurdNameLike" id="beanCurdNameLike">
                                  <label for="txt_beanIsEffecitveLike" style="display: inline-block;margin-left:20px;"> 上下线： &nbsp;&nbsp;</label>
                                 <select class="form-control selectpicker" data-mobile="true" name="txt_beanIsEffecitveLike" id="txt_beanIsEffecitveLike">
                                      <option value="">全部</option>
                                      <option value="1">上线</option>
                                      <option value="0">下线</option>
                                  </select>
                                  <button class="btn yellow" onclick="beanCurdNameLike()" id="baanCurdNameButton" style="position: relative;top:-6px;">
                                    查询
                                </button>
                            </div>
                        </div>
                    </div>
                    <table style="table-layout:fixed" class="table table-striped table-bordered table-hover dataTable" id="sample_1"
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
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="myModalLabel">桌面图标过滤管理</h4>
            </div>
            <div class="modal-body">
                <input id="txt_bigPicsId" type="hidden">
                <div class="form-group">
                    <label for="txt_bigPicsName"> <span style="color:red">*</span>大图名称：</label>
                    <input type="text" name="txt_bigPicsName" class="form-control" id="txt_bigPicsName" placeholder="应用名称">
                </div>
                <div class="form-group">
                    <label> <span style="color:red">*</span>图片：</label>
                    <div class="txt"><input id="recommendContentImgUrl" type="file" value=""></div>
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

<div class="modal fade" id="myModalTwo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="myModalLabelsy">关联豆腐块列表</h4>
            </div>
            <div class="modal-body">

                <div class="clearfix" style="position: relative;top:-3px;">
                    <div class="btn-group">
                        <input id="txt_curNamedId" type="hidden">
                        <div style="float:left ;  width:50%;  height:100%;">
                            <button id="sumbit_add" class="btn yellow" onclick="editAdd()">
                                添加
                            </button>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </div>
                        <div style="float:left ;  width:50%; height:100%;">
                            <input id="txt_bean_curd_id" type="hidden">
                            <input id="txt_bean_Name" type="hidden">
                            <input id="txt_entrance_type" type="hidden">
                            <input type="text"  name="beanNameLike" id="beanNameLike">
                            <button class="btn yellow" onclick="beanCurdNameSelect()" id="baanCurdButton" style="vertical-align: top;">
                                查询
                            </button>
                        </div>
                    </div>

                </div>
                <table class="table table-striped table-bordered table-hover dataTable" id="sample_table"
                       aria-describedby="sample_1_info">
                </table>
            </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" onclick="beanCurd_submit();">保存</button>
            <button type="button" class="btn btn-default"onclick="clear_modalTwo();">取消</button>
        </div>
    </div><!-- /.modal-content -->
</div><!-- /.modal -->




<div class="modal fade" id="modalEdit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="myModalLabelst">关联豆腐块</h4>
            </div>
          <%--  <h1 class="page-title">
                关联豆腐块
                <small></small>
            </h1>--%>
            <div>
            </div>
            <div class="modal-body">
                <input id="txt_id" type="hidden">
                <input id="txt_entrance_id" type="hidden">
                <input id="txt_is_effective" type="hidden">
                <input id="txt_entrance_seq" type="hidden">
                <input id="txt_bean_curd_name" type="hidden">
                <div class="form-group">
                    <label for="txt_beanCurdEntranceName" style="display: inline-block;"> <span style="color:red">*</span>入口名称：</label>
                    <select class="form-control selectpicker" data-mobile="true" name="txt_beanCurdEntranceName" id="txt_beanCurdEntranceName" style="vertical-align: -1px;">
                </select>
                </div>

        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" onclick="buanCurd_submit();">保存</button>
            <button type="button" class="btn btn-default" onclick="clear_entrance();">取消</button>
        </div>
    </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>



<div class="modal fade" id="beanCurdModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="myModalLabels">列表名称编辑</h4>
            </div>
            <div class="modal-body">
                <input id="txt_beabCurdId" type="hidden">
                <div class="form-group">
                <label for="txt_beanCurdName" style="display: inline-block;"> <span style="color:red;">*</span>列表名称：</label>
                <input type="text" name="txt_beanCurdName" class="form-control" id="txt_beanCurdName" style="vertical-align: -1px;">
            </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="beanCurdNameSubimt();">保存</button>
                <button type="button" class="btn btn-default" onclick="cleanBeanCurdName();">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>