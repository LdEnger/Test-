<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<link href="${ctx}/res/bootstrap/bootstrap-table.css" rel="stylesheet">
<link href="${ctx}/res/css/bootstrap-modal.css" rel="stylesheet">
<link href="${ctx}/res/js/plugin/uploadify/uploadify.css" rel="stylesheet">
<%--<link href="${ctx}/res/bootstrap/bootstrap-dialog.min.css" rel="stylesheet">--%>
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/bootstrap-select.min.js"></script>--%>
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/i18n/defaults-*.min.js"></script>--%>
<script src="${ctx}/res/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/res/bootstrap/bootstrap-table.js" type="text/javascript"></script>
<script src="${ctx}/res/bootstrap/bootstrap-table-zh-CN.js"></script>
<script src="${ctx}/res/js/jquery.flot.js" type="text/javascript"></script>
<%--<script src="${ctx}/res/bootstrap/bootstrap-dialog.min.js" type="text/javascript"></script>--%>
<script language="JavaScript">
    var ctx = '${ctx}';
</script>
<style type="text/css">
    input.button1{
        Height:30px;
        width: 50px;
        BACKGROUND-COLOR: #ffffff;
        LINE-HEIGHT: 9pt;
        PADDING-LEFT: 0px;
        PADDING-RIGHT: 0px;
        PADDING-TOP: 2px;
        PADDING-BOTTOM: 2px;
        CURSOR: hand;
        border-width:1px;
        border-style:solid;
        FONT-FAMILY: 宋体,Sans-serif;
        FONT-SIZE: 9pt;
    }
    .fixed-table-container{
        top:0px;
    }
    .fixed-table-toolbar .columns {
        top: -10px;
    }
    #myModalSy{
        width: 670px;
    }
    #myModalDaboTwo{
        width: 670px;
    }
    .modal-body{
        width: 640px;
    }
    /*#myModalThree{*/
        /*text-align: center;*/
    /*}*/
    .btn{
        vertical-align: top;
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
        top: -43px;
    }
    #myModalSy{
        position: relative;
        left:45%;
    }
    #myModalSy{
        display: none;
    }
    #myModalDaboTwo{
        position: relative;
        left:46%;
    }
    #myModalDaboTwo{
        display: none;
    }
    .btn-xs{
        vertical-align: middle;
    }
    .fixed-table-body{
        width:100%;
    }
</style>
<script src="${ctx}/res/js/bootstrap-modal.js" type="text/javascript"></script>
<script src="${ctx}/res/js/bootstrap-modalmanager.js" type="text/javascript"></script>
<script src="${ctx}/res/js/plugin/ajaxupload.3.6.js" type="text/javascript"></script>
<script src="${ctx}/res/js/beanCurd/beanCurdEdit.js" type="text/javascript"></script>
<script src="${ctx}/res/js/jquery.flot.resize.js" type="text/javascript"></script>
<script src="${ctx}/res/js/plugin/uploadify/jquery.uploadify.js" type="text/javascript"></script>
<div class="container-fluid">
    <!-- BEGIN PAGE HEADER-->
    <div class="row-fluid">
        <div class="span12">
            <!-- BEGIN PAGE TITLE & BREADCRUMB-->
            <h3 class="page-title">
                豆腐块内容管理
                <small></small>
            </h3>
            <ul class="breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="${ctx}">Home</a>
                    <i class="icon-angle-right"></i>
                </li>
                <li><a href="#">豆腐块内容管理</a></li>
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
                            <div style="float:left ;  width:200px;  height:100%;">
                            <button id="btn_add" class="btn yellow"  onclick="editdd()" >
                                添加
                            </button>
                            </div>
                            <div style="float:left ;display: inline; width:50%; height:100%;position:relative;right:100px">

                                <%--  <span style="display: inline-block">列表名称</span>--%>
                             <label for="txt_beanCurdEntranceName" style="display: inline-block;"> 豆腐块名称： &nbsp;&nbsp;</label>
                                    &nbsp;&nbsp;&nbsp;
                                <input type="text"  name="txt_beanCurdEntranceName" id="txt_beanCurdEntranceName">
                                    <label for="txt_beanIsEffecitveLike" style="display: inline-block;inline-blockcursor: auto;"> &nbsp;&nbsp;&nbsp;&nbsp;上下线： &nbsp;&nbsp;</label>
                                    <select class="form-control" data-mobile="true" name="txt_beanIsEffecitveLike" id="txt_beanIsEffecitveLike">
                                        <option value="">全部</option>
                                        <option value="1">上线</option>
                                        <option value="0">下线</option>
                                    </select>
                                <button class="btn yellow" onclick="beanCurdNameLike()" id="baanCurdNameButton" style="vertical-align:top;">
                                    查询
                                </button>

                            </div>
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
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="myModalLabel">编辑豆腐块入口应用</h4>
            </div>
            <div class="modal-body">
                <input id="txt_entrance_id" type="hidden">
                <input id="txt_apkName" type="hidden">
                <input id="txt_apkId" type="hidden">
                <input id="txt_lastvarsion" type="hidden">
                <input id="txt_isefficite" type="hidden">
                <input id="txt_installStyle" type="hidden">
                <form class="form-inline" role="form">
            <div class="form-group">
<%--                <div style="float:left ;  width:20%;  height:100%;">--%>
                <label for="txt_entranceName"> <span style="color:red">*</span>豆腐块名称：&nbsp;</label>
                <%--</div>--%>
        <%--         <div style="float:left ;  width:50%;  height:100%;">--%>
                <input type="text" name="txt_entranceName" class="form-control" id="txt_entranceName" style="margin-left: 8px;" >
              <%--   </div>--%>
            </div>
                </form>
                <form class="form-inline" role="form">
                <div class="form-group">
                 <%--   <div style="float:left ;  width:20%;  height:100%;">--%>
                    <label for="txt-entranceType">入口应用类型：&nbsp;</label>
          <%--          </div>--%>
                      <%--  <div style="float:left ;  width:20%;  height:100%;">--%>
                    <select class="form-control " data-mobile="true" name="txt-entranceType" id="txt-entranceType" onchange="seleceChang()" class="selectId" data-width="222px" >
                        <option value="0" >系统应用</option>
                        <option value="1">定制应用</option>
                    </select>
           <%--             </div>--%>
                </div>
                </form>
                <form class="form-inline" role="form">
                <div class="form-group">
                    <%--<label for="txt_entranceNames" id="txt-entranceAppName"> <span style="color:red">*</span>关联应用：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>--%>
                    <%--<label for="txt-entrance_app_name" id="txt-entranceAppName1"> <span style="color:red">*</span>关联应用：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>--%>
                    <div id="entranAppId">
                    <label for="txt-entrance_app_name" id="txt-entranceAppName"><span style="color:red">*</span>关联应用：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                    <%--<div id="appId">--%>
                    <select class="form-control selectpicker" data-mobile="true" name="txt-entrance_app_name" id="txt-entrance_app_name" data-width="222px" >

                   <%--     <option value="1">极清首映</option>
                        <option value="2">大麦影视2222</option>--%>
                    </select>
                    </div>
                    <%--</div>--%>
                    <div id="nameId" style="display: inline-block;">
                    <%--<input type="text" name="txt_entrance_Name" class="form-control" id="txt_entranceNames" style="margin-left: -1px;" readonly="true"><input type="button" class="button1 btn btn-default" id="txt_entrance_name" onclick="beanCurdButton()"  value="选择" style="text-align: center;font-family: '微软雅黑'" />--%>
                        <label for="txt_entranceNames" id="txt-entranceAppNames"> <span style="color:red">*</span>关联应用：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                    <input type="text" name="txt_entrance_Name" class="form-control" id="txt_entranceNames" readonly="readonly" style="margin-left: -1px;">
                    <input type="button" class="button1 btn btn-default" id="txt_entrance_name" onclick="beanCurdButton()"  value="选择" style="text-align: center;font-family: '微软雅黑'" />
                    </div>
                </div>
                </form>
            </div>

        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" onclick="beanCurdEdit_submit();">保存</button>
            <button type="button" class="btn btn-default" onclick="clean_entrance();">取消</button>
        </div>
    </div><!-- /.modal-content -->
</div><!-- /.modal -->

<div class="modal fade" id="myModalTwo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">

                <div class="clearfix">
                    <div class="btn-group">
                        <div style="float:left ;  width:50%;  height:100%;">
                            <button id="sumbit_add" class="btn yellow" onclick="beanCurdImgAdd()">
                                添加
                            </button>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </div>
                        <div style="float:left ;  width:50%; height:100%;">
                            <input type="text"  name="imgNameLike" id="imgNameLike">
                            <button class="btn yellow" onclick="beanCurdNameSelect()">
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
            <button type="button" class="btn btn-default" onclick="appIcon_submit();">保存</button>
            <button type="button" class="btn btn-default" onclick="clear_modalTo();">取消</button>
        </div>
    </div><!-- /.modal-content -->
</div><!-- /.modal -->

<<div class="modal fade" id="myModalSy" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" >内容图管理</h4>
            </div>
            <div class="modal-body">
                <input id="txt_entranceId" type="hidden">
                <div class="clearfix" style="position: relative;top: 10px;">
                    <div class="btn-group">
                        <div style="float:left ;  width:50%;  height:100%;">
                            <button id="text-add" class="btn yellow" onclick="beanCurdImgAdd()">
                                新增
                            </button>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </div>
                        <div style="float:left ;  width:50%; height:100%;">
                            <input type="text"  name="entranceTitleLike" id="entranceTitleLike">
                            <button class="btn yellow" onclick="beanCurdNameSelect()">
                                查询
                            </button>
                        </div>
                    </div>
                </div>
                <table class="table table-striped table-bordered table-hover dataTable" id="sample_three"
                       aria-describedby="sample_1_info">
                </table>
                <div class="row-fluid">
                    <div class="span6" style="display: none;">
                        <div class="dataTables_info" id="sample_three_1"></div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" onclick="beanCurdImg_submit();">保存</button>
                    <button type="button" class="btn btn-default" onclick="clear_modalSy();">取消</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->


    </div>
</div>

<div class="modal fade" id="myModalThree" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalTable">内容图编辑</h4>
            </div>
            <div class="modal-body">
                <input id="txt_imgId" type="hidden">
                <div class="form-group">
                    <label for="txt_imgSeq" style="display:inline-block;"> <span style="color:red">*</span>顺序：</label>
                    <input type="text" name="txt_imgSeq" class="form-control" id="txt_imgSeq" style="vertical-align: -1px;margin-left:20px;" >
                </div>


                <div class="form-group">
                    <label for="txt_entranceTitle" style="display:inline-block;"> <span style="color:red">*</span>标题：</label>
                    <input type="text" name="txt_entranceTitle" class="form-control" id="txt_entranceTitle" style="vertical-align: -1px;margin-left:20px;">
                </div>


                <div class="form-group">
                    <label for="txt_entranceSubtitle" style="display:inline-block;"> 副标题：</label>
                    <input type="text" name="txt_entranceSubtitle" class="form-control" id="txt_entranceSubtitle" style="vertical-align: -1px;margin-left:12px;">
                </div>

                <div class="form-group">
                    <label for="text-isEffective" style="display:inline-block;">状态：</label>
                    <select class="form-control " data-mobile="true" name="text-isEffective" id="text-isEffective" style="vertical-align: -1px;margin-left:26px;">
                        <option value="1" selected = "selected">有效</option>
                        <option value="0">无效</option>
                    </select>
                </div>
                   <div class="form-group">
                <label> 豆腐块图片：<br/>(330*230)</label>
                <div class="txt"><input id="recommendContentImgUrl" type="file" value=""></div>
                <div class="pic"><img id="recommendContentImg" src="" alt="" width="160" height="50" /></div>

            </div>

            </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" onclick="beanCurdImgList_submit();">保存</button>
            <button type="button" class="btn btn-default"onclick="clear_modalThreesy();">取消</button>
        </div>
    </div><!-- /.modal-content -->
</div><!-- /.modal -->


<div class="modal fade" id="myModalDaboTwo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">

                <h4 class="modal-title" id="myModalLabelt">关联定制应用</h4>
            </div>
            <div class="modal-body">
                <input id="txt_apk_id" type="hidden">
                <div class="clearfix">
                    <div class="btn-group">
                        <%--   <div style="float:left ;  width:50%;  height:100%;">
                               <button id="sumbit_add" class="btn yellow" onclick="beanCurdImgAdd()">
                                   新增
                               </button>
                               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                           </div>--%>
                        <div style="float:left ;  width:50%; height:50%;">
                            <label  style="display: inline-block" for="apkNameLikeSelect"> 名称：&nbsp;&nbsp;</label>
                            <input type="text"  name="imgNameLike" id="apkNameLikeSelect">
                            <button class="btn yellow" onclick="apkNameListSelect()" >
                                查询
                            </button>
                        </div>
                    </div>

                </div>
                <table class="table table-striped table-bordered table-hover dataTable" id="sample_ApkTable"
                       aria-describedby="sample_1_info">
                </table>
            </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default"onclick="appIcon_submit();">保存</button>
            <button type="button" class="btn btn-default" onclick="cleanApkModal();">取消</button>
        </div>
    </div><!-- /.modal-content -->
</div><!-- /.modal -->


</div>