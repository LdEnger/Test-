<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<link href="${ctx}/res/bootstrap/bootstrap-table.css" rel="stylesheet">
<link href="${ctx}/res/js/plugin/uploadify/uploadify.css" rel="stylesheet">
<!--<link href="${ctx}/res/bootstrap/bootstrap-dialog.min.css" rel="stylesheet">-->
<script src="${ctx}/res/js/bootstrap.min.js" type="text/javascript"></script>

<script src="${ctx}/res/js/jquery.flot.js" type="text/javascript"></script>

<%--<script src="${ctx}/res/bootstrap/bootstrap-dialog.min.js" type="text/javascript"></script>--%>
<script language="JavaScript">
    var ctx = '${ctx}';
</script>

<script src="${ctx}/res/js/jquery.flot.resize.js" type="text/javascript"></script>
<script src="${ctx}/res/js/fiexdRecom/fiexdRecom.js" type="text/javascript"></script>
<script src="${ctx}/res/js/fiexdRecom/fixedRecomContent.js" type="text/javascript"></script>

<script src="${ctx}/res/js/bootstrap-modal.js" type="text/javascript"></script>
<script src="${ctx}/res/js/bootstrap-modalmanager.js" type="text/javascript"></script>

<script src="${ctx}/res/bootstrap/bootstrap-table.js" type="text/javascript"></script>
<script src="${ctx}/res/bootstrap/bootstrap-table-zh-CN.js"></script>
<link href="${ctx}/res/css/bootstrap-modal.css" rel="stylesheet">
<script src="${ctx}/res/js/plugin/ajaxupload.3.6.js" type="text/javascript"></script>
<script src="${ctx}/res/js/plugin/uploadify/jquery.uploadify.js" type="text/javascript"></script>
<style type="text/css">
    #myModals,#myModalTwo,#editcontent_myModal{
        width: 800px;
    }
    .fixed-table-toolbar .columns {
        top: -5px;
    }
    .fixed-table-container{
        top:2px;
    }
    #myModalTwo, #editcontent_myModal {
        height: 640px;
    }
    .pull-right{
        height: 0px;
    }
    .pull-right .pagination{
        height: 34px;
    }
    .fixed-table-toolbar{
        height: 0px;
    }
    .fixed-table-pagination{
        height: 130px;
    }
    .fixed-table-pagination {
        height: 50px;
    }
    .pagination-detail {
        white-space: nowrap;
    }
    .btn-xs{
        vertical-align: middle;
    }
    .fixed-table-body{
        width:100%;
    }
</style>
<div class="container-fluid">
    <!-- BEGIN PAGE HEADER-->
    <div class="row-fluid">
        <div class="span12">
            <!-- BEGIN PAGE TITLE & BREADCRUMB-->
            <h3 class="page-title">
                固定推荐位列表管理
                <small></small>
            </h3>
            <ul class="breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="${ctx}">Home</a>
                    <i class="icon-angle-right"></i>
                </li>
                <li><a href="#">固定推荐位列表管理</a></li>
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

                        <div class="form-group" style="float: left">
                            <div class="btn-group">
                                <button id="btn_add" class="btn yellow" onclick="add();" style="vertical-align: -18px;">添加</button>
                            </div>
                            <label style="font-size: 14px;display:inline-block" for="fixed_recom_name1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;推荐位列表名称：</label>&nbsp;&nbsp; <input style="width: 100px;vertical-align: -1px;" type="text" id="fixed_recom_name1" name="fixed_recom_name1">&nbsp;&nbsp;&nbsp;&nbsp;
                            <label style="font-size: 14px;display:inline-block" for="isEffective">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上下线：</label>&nbsp;&nbsp;<select  data-mobile="true" name="isEffective" style="width: 114px;vertical-align: -1px;" id="isEffective" data-placeholder="全部">
                            <option value="2">全部</option>
                            <option value="1">上线</option>
                            <option value="0">下线</option>
                        </select>
                            <div class="btn-group">
                                <button type="button" class="btn btn-default" id="isEffective1" onclick="qwer()" style="vertical-align: -16px;">查询</button>
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
<div class="modal fade" style="margin-bottom:800px;" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="myModalLabel">固定推荐位管理</h4>
            </div>
            <div class="modal-body">
                <input id="txt_recomId" type="hidden">
                <div class="form-group">
                    <label for="txt_recomName" style="display: inline-block"><span style="color: red">*</span> 推荐位列表名称：</label>
                    <input type="text" name="txt_recomName" class="form-control" id="txt_recomName" placeholder="推荐位名称" style="vertical-align: -1px;">
                </div>
                <div class="form-group">
                    <label for="text-operateType" style="display: inline-block">运行类型：</label>
                    <select class="form-control selectpicker" data-mobile="true" name="text-operateType" id="text-operateType" style="vertical-align: -1px;margin-left: 51px;">
                    </select>
                </div>
                <div class="form-group">
                    <label for="txt-content" style="display: inline-block">运营内容：</label>
                    <select class="form-control selectpicker" data-mobile="true" name="txt-content" id="txt-content" style="vertical-align: -1px;margin-left: 51px;">
                    </select>
                </div>
                <div class="form-group">
                    <label for="txt-type" style="display: inline-block">类型：</label>
                    <select class="form-control selectpicker" data-mobile="true" name="txt-type" id="txt-type" style="vertical-align: -1px;margin-left: 79px;">
                    </select>
                </div>
            </div>
            <div class="modal-footer" style="text-align:right;">
                <button type="button"   class="btn btn-default" onclick="fiexdRecom_submit();">保存</button>
                <button type="button" class="btn btn-default" onclick="clear_diaglog();">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div class="modal fade" id="myModal1"  style="margin-bottom:800px;" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="myModalLabel1">固定推荐位管理</h4>
            </div>
            <div class="modal-body">
                <input id="txt_recomId1" type="hidden">
                <div class="form-group">
                    <label for="txt_recomName1" style="display: inline-block;"><span style="color: red">*</span> 推荐位列表名称：</label>
                    <input type="text" name="txt_recomName" class="form-control" id="txt_recomName1" placeholder="推荐位名称" style="vertical-align: -1px;">
                </div>
                <div class="form-group">
                    <label for="text-operateType1" style="display: inline-block;">运行类型：</label>
                    <select class="form-control selectpicker" data-mobile="true" name="text-operateType" id="text-operateType1" data-placeholder="请选择运营类型" style="vertical-align: -1px;margin-left: 51px;">
                        <option value='1'>自动</option>
                        <option value='0'>运营</option>
                        <input type="hidden" id="hidd-operateType">
                    </select>
                </div>
                <div class="form-group">
                    <input type="hidden" id="recomId">
                    <label for="operateContent" style="display:inline-block;"><span style="font-size: 13px;"> 运营内容：</span></label>
                    <input type="text" readonly="readonly" style="width: 100px;margin-left:57px;vertical-align: -1px;" id="operateContent" />
                </div>
                <div class="form-group">
                    <label for="chnType" style="display: inline-block;"><span style="font-size: 13px;">类型：</span></label>
                    <input  type="text" style="width: 100px;margin-left:83px;vertical-align: -1px;" readonly="readonly" id="chnType"> </input>

                </div>
            </div>


        </div>
        <div class="modal-footer" style="text-align:right;">
            <button type="button" class="btn btn-default" onclick="fiexdRecom_submit();">保存</button>
            <button type="button" class="btn btn-default" onclick="clear1();">取消</button>

        </div>
    </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>



<!--<div class="modal fade" id="myModals" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">-->
<div  class="modal fade hide bs-example-modal-lg" id="editcontent_myModal" tabindex="-1" role="dialog" aria-labelledby="editcontent_myModal1Label" aria-hidden="true" >
    <div class="modal-dialog modal-lg" >
        <div class="modal-content">
            <div class="modal-header" >
                <input id="content_recommendId" type="hidden">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="editcontent_myModalLabel">编辑推荐位内容</h4>
            </div>
            <div class="container"></div>
            <div class="modal-body">
                <input id="editcontent_recommendId" type="hidden">
                <input id="edit_id" type="hidden">
                <input id="editcontent_id" type="hidden">
                <input id="txt_contentname" type="hidden">
                <input id="content_chn_id" type="hidden">
                <input id="content_operate" type="hidden">
                <div style="float: left;width:310px;border: 0px #99BBE8 solid;" id="leftdiv">
                    <table id="lefttable">
                        <tr>
                            <td style="width: 50px;"><label for="txt_title"><span style="color: red">*</span>标题：</label></td>
                            <td><input type="text" style="width:auto;vertical-align: -14px;" id="txt_title"></td>
                        </tr>
                        <tr>
                            <td><label for="txt_secondTitle" style="white-space: nowrap;">副标题：</label></td>
                            <td><input type="text" style="width:auto;vertical-align: -10px;" id="txt_secondTitle"></td>
                        </tr>
                        <tr style="height: 10px;">
                        </tr>
                        <tr>
                            <td>图片：</td>
                            <td><div class="txt"><input id="recommendContentImgUrl" type="file" value=""></div></td>
        <input type="hidden" id="contentId1">
                        </tr>
                        <tr>
                            <td colspan="2" > <div class="pic"><img id="recommendContentImg" src="" alt=""  width="260" height="360" /></div>
                                <span>(图片大小：260*360）</span>
                            </td>

                        </tr>
                    </table>
                </div>

                <div style="float:left;">
                    <div style="margin-left:20px;" id="rightdiv" >
                        <div id="operateContentInfo">
                            <label for="contentType" style="display: inline-block;">付费</label>
                            <select onchange="dataSource()" id="contentType" style="width: 106px;vertical-align: -1px;">
                                <option value="-1">全部</option>
                                <option value ='0'>免费</option>
                                <option value ='1'>大麦付费</option>
                                <option value ='2'>爱奇艺付费</option>
                            </select>
                            &nbsp; &nbsp; &nbsp;<label for="contentType1" style="display: inline-block;">VIP</label>
                            <select onchange="dataSource()" id="contentType1" style="width:100px;vertical-align:-1px;">
                                <option value="-1">全部</option>
                                <option value ='0'>非VIP</option>
                                <option value ='1'>大麦VIP</option>
                                <option value ='2'>爱奇艺VIP</option>
                            </select>
                        </div>
                        <label for="contentName" style="display: inline-block;">名称：</label><input type="text" id="contentName" style="vertical-align: -1px;"><input type="button" value="查询" onclick="selecFixedRecomName(1)" style="vertical-align: -1px;">
                    </div>
                </div>
                    <div id="tableDiv" style="height: 440px;overflow:auto;width: 400px;">
                        <input type="hidden" id="hiddenId" value="">
                        <table class="table table-striped table-bordered table-hover dataTable" id="sample_table2"
                               aria-describedby="sample_1_info">
                        </table>
                    </div>
                    <div id="tableDiv1" style="height: 440px;overflow:auto;width: 400px;">
                        <table class="table table-striped table-bordered table-hover dataTable" id="sample_table3"
                               aria-describedby="sample_1_info">
                        </table>
                    </div>
                </div>
            </div>
            <div id="footer" style="float:right;width:505px;border: 0px #99BBE8 solid;position: relative;top:-17px;left:149px;">
                <button style="width: 80px;" class="btn btn-default" id ="save" onclick="content_submit();">保存</button>&nbsp;&nbsp;&nbsp;
                <button style="width: 80px;" class="btn btn-default" onclick="close_contentdiaglog();">取消</button></div>
            <!-- </div>-->
             </div><!-- /.modal-content -->
         </div><!-- /.modal -->
</div>


    <div class="modal fade" id="myModalTwo" tabindex="999" role="dialog" aria-labelledby="myModalTwo" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="margin-top: 30px;">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="myModal3" >编辑推荐位</h4>
            </div>
            <div class="modal-body" style="padding-top: 0px;">
                <div class="clearfix">
                    <div class="btn-group" >
                        <div style="float:left ;  width:50%;  height:100%;">
                            <input type="hidden" id="seleOperateContent" value="">
                            <button  class="btn yellow" onclick="add1()">
                                添加
                            </button>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </div>

                    </div>
                </div>
                <div id="fixedContentTable" style="height: 400px;overflow:auto;">
                    <table class="table table-striped table-bordered table-hover dataTable" id="sample_table1"
                           aria-describedby="sample_1_info">
                    </table>
                </div>
                <div class="row-fluid">
                    <div class="span6">
                        <div class="dataTables_info" id="sample_three_1"></div>
                    </div>
                </div>
                <div class="modal-footer" style="text-align:right;">

                    <button type="button" class="btn btn-default" onclick="clear_diaglog2();">保存</button>
                    <button type="button" class="btn btn-default" onclick="clear_diaglog2();">取消</button>
                </div>
            </div>
        </div>
    </div></div><!-- /.modal -->








