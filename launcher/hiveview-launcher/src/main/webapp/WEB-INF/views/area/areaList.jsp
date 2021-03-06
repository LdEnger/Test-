<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<link href="${ctx}/res/bootstrap/bootstrap-table.css" rel="stylesheet">
<%--<link href="${ctx}/res/bootstrap/bootstrap-dialog.min.css" rel="stylesheet">--%>
<link href="${ctx}/res/js/plugin/uploadify/uploadify.css" rel="stylesheet">
<link href="${ctx}/res/css/bootstrap-modal.css" rel="stylesheet">
<script src="${ctx}/res/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/res/bootstrap/bootstrap-table.js" type="text/javascript"></script>
<script src="${ctx}/res/bootstrap/bootstrap-table-zh-CN.js"></script>
<script src="${ctx}/res/js/jquery.flot.js" type="text/javascript"></script>

<script language="JavaScript">
    var ctx = '${ctx}';
</script>
<style type="text/css">
    #areaModal{
        width: 800px;
    }
    #editContentModal{
        width: 820px;
        height: 700px;
        overflow: auto;
        position: relative;
        left:40%;
    }
    .table-striped tbody>tr:nth-child(odd)>td, .table-striped tbody>tr:nth-child(odd)>th{
        background:none;
    }
    .table-hover tbody tr:hover>td{
        background:none;
    }
</style>

<script src="${ctx}/res/js/plugin/uploadify/jquery.uploadify.js" type="text/javascript"></script>
<script src="${ctx}/res/js/bootstrap-modal.js" type="text/javascript"></script>
<script src="${ctx}/res/js/bootstrap-modalmanager.js" type="text/javascript"></script>
<script src="${ctx}/res/js/plugin/ajaxupload.3.6.js" type="text/javascript"></script>
<script src="${ctx}/res/js/area/areaList.js" type="text/javascript"></script>
<script src="${ctx}/res/js/jquery.flot.resize.js" type="text/javascript"></script>

<div class="container-fluid">
    <!-- BEGIN PAGE HEADER-->
    <div class="row-fluid">
        <div class="span12">
            <!-- BEGIN PAGE TITLE & BREADCRUMB-->
            <h3 class="page-title">
                专区管理
                <small></small>
            </h3>
            <ul class="breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="${ctx}">Home</a>
                    <i class="icon-angle-right"></i>
                </li>
                <li><a href="#">专区管理</a></li>
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
                                <button id="btn_add" class="btn yellow" onclick="areaAdd()" >
                                    添加
                                </button>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            </div>
                            <div style="float:left ;width: 300px;margin-right: 20px;" >
                                <label  style="display: inline-block" for="imgNameLike"> 专区名称： &nbsp;&nbsp;</label>
                                <input type="text"  name="imgNameLike" id="imgNameLike">
                            </div>
                            <div style="float:right;width: 300px;margin: auto"  >
                                <select  id="effictiveSelect" name="effictiveSelect" class="form-control selectpicker" style="width: 200px;">
                                    <option value="">全部</option>
                                    <option value="1">上线</option>
                                    <option value="0">下线</option>
                                </select>
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
<!-- 添加/编辑专区管理（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="clear_diaglog();"></button>
                <h4 class="modal-title" id="myModalLabel">专区管理</h4>
            </div>
            <div class="modal-body">
                <input id="txt_areaId" type="hidden">
                <div class="form-group">
                    <label for="txt_areaName" style="display: inline-block;"> <span style=" color:red">*</span>专区名称：</label>
                    <input type="text" name="txt_areaName" class="form-control" id="txt_areaName" placeholder="应用名称" style="vertical-align: -1px;">
                </div>

                <div class="form-group">
                    <label for="txt_areaTitle" style="display: inline-block;"> <span style=" color:red">*</span>专区标题名称：</label>
                    <input type="text" name="txt_areaTitle" class="form-control" id="txt_areaTitle" placeholder="应用名称" style="vertical-align: -1px;">
                </div>


                <div class="form-group">
                    <label for="txt_areaTemplate" style="display: inline-block;">专区模版：</label>
                    <select class="form-control selectpicker" data-mobile="true" name="txt_areaTemplate" id="txt_areaTemplate">
                        <option value="1">模版1</option>
                        <option value="2">模版2</option>
                        <option value="3">模版3</option>
                        <option value="3">模版4</option>
                    </select>
                </div>


                <div class="form-group">
                    <label for="txt_areaTemplatePriview" style="display: inline-block;">模版预览：</label>
                    <select class="form-control selectpicker" data-mobile="true" name="txt_areaTemplatePriview" id="txt_areaTemplatePriview">
                    </select>
                </div>

                <div class="form-group">
                    <label for="txt_areaIntroduce" style="display: inline-block;"> <span style=" color:red">*</span>专区介绍：</label>
<%--                    <input type="text" name="txt_areaIntroduce" class="form-control" id="txt_areaIntroduce" &lt;%&ndash;placeholder="应用名称" &ndash;%&gt;style="vertical-align: -1px;">--%>
                    <textarea class="form-control" id="txt_areaIntroduce" name="txt_areaIntroduce" style="height: 120px;"></textarea>
                </div>

                <div class="form-group">
                    <label style="display: inline-block;vertical-align: -1px;">专区背景图</label>
                    <div class="txt" style="display: inline-block;"><input type="file" id="backgroundImgUrl"></div>
                    <div class="pic"><img src="" alt="" width="160" height="50" id="backgroundImg" /></div>
                </div>


                <div class="form-group">
                    <label style="display: inline-block;vertical-align: -1px;"> <span style="color:red">*</span>专区外显图：</label>
                    <div class="txt" style="display: inline-block;"><input id="areaImgUrl" type="file" value=""></div>
                    <div class="pic"><img id="areaImg" src="" alt="" width="160" height="50" /></div>
                </div>


            </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" onclick="area_submit();">保存</button>
            <button type="button" class="btn btn-default" onclick="clear_diaglog();">取消</button>
        </div>
    </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>




<!-- 关联内容（Modal） -->
<div class="modal fade" id="areaModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="clear_contentlog();"></button>
                <h4 class="modal-title" id="myModalLabelss">关联内容</h4>
            </div>
            <div class="clearfix">
                <div class="btn-group">
                    <button id="btn_addContent" class="btn yellow" onclick="addContent()" style="margin-bottom: 5px">
                        添加
                    </button>
                    <input id="areaId" type="hidden">
                    <label style="display: inline-block">
                        <span>&nbsp;&nbsp;&nbsp;&nbsp;标题：</span> <input type="text" id="selectByName" style="margin-top: 10px">&nbsp;
                        <button class="btn yellow" onclick="selectByName()">查询</button>
                    </label>

                </div>
            </div>
            <div class="modal-body">
                <table class="table table-striped table-bordered table-hover dataTable" id="areaContentTable"
                       aria-describedby="sample_1_info">
                </table>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</div>

<%--添加、编辑关联内容--%>
<div class="modal fade hide" id="editContentModal" tabindex="-1" role="dialog" aria-labelledby="editCustomContentModal" aria-hidden="true" >
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="margin-top: 20px;">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="ContentClose()"></button>
                <h4 class="modal-title" id="editcontent_myModalLabel">编辑推荐位内容</h4>
            </div>
            <div class="modal-body" id = "editCustomContentModalBody">
                <input id="id" type="hidden">

                <input id="contentId" type="hidden">
                <input id="recommendType" type="hidden">
                <input id="packageName" type="hidden">
                <input id="areaTemplate" type="hidden">
                <input id="channelType" type="hidden">
                <input id="channel" type="hidden">
                <input id="payState" type="hidden">
                <input id="vipState" type="hidden">
                <input id="videoId" type="hidden">
                <input id="customContentTitle" type="hidden">
                <input id="customContentSubTitle" type="hidden">
                <div style="float: left;width:300px;height:640px;border: 1px #99BBE8 solid;" id="customLeftDiv">
                    <table id="customLeftTable">
                        <tr>
                            <td>标题：<input type="text"  id="contentName" onblur="checkTitle()"></td>
                        </tr>
                        <tr>
                            <td> 内容介绍：<textarea name="areaContent" id="areaContent" style="resize:none;width:200px;"></textarea></td>
                        </tr>
                        <tr id ="t_1">
                            <td>推荐位图片：<div class="txt">
                                <img id="txt_recommendImg" src="" alt="" width="160" height="50" />
                                <%--<input type="text" name="txt_recommendImg" class="form-control" id="txt_recommendImgImg" readonly="true">--%>
                                <input id="txt_recommendImgUrl" type="file" />
                            </div></td>
                        </tr>
                        <tr>
                            <td> 关联内容：<input type="text" id="content" readonly="readonly" style="width: 200px"></td>
                        </tr>
                    </table>
                </div>

                <div style="float: right;width:465px;height:640px;border: 1px #99BBE8 solid;">
                    <div id="customRightDiv" >
                        <select onchange="dataCustomSource()" id="customContentType">
                            <option value ='1' selected = true>专辑详情</option>
                        </select>
                        <div id ="div_1">
                            <label for="customTempleApk" style="display: inline-block;">包名：</label><select style="width: 106px;vertical-align: -1px;" id = "customTempleApk" onchange="initCustomTempletApkBindEvent();"></select>
                            <label for="customTempletId" style="display: inline-block;">模板：</label><select id ="customTempletId" style="width: 91px;vertical-align: -1px;"></select>
                            <div id ="dm" style="display: inline">
                                <label for="templetApkChannelType" style="display: inline-block;">频道类型：</label><select style="width: 91px;vertical-align: -1px;" id = "templetApkChannelType" onchange="initCustomTempletApkChannelTypeBindEvent();"></select>
                                <label for="templetApkChannel" style="display: inline-block;">频道：</label><select style="width: 142px;vertical-align: -1px;" id = "templetApkChannel"></select>
                            </div>
                            <label for="pay" style="display: inline-block;">付费：</label><select style="width: 106px;vertical-align: -1px;" id ="pay"></select>
                            <label for="vip" style="display: inline-block;">VIP：</label><select style="width: 100px;vertical-align: -1px;" id ="vip"></select>
                            <div id ="jq" style="display:none">
                                <label for="templetApkHot" style="display: inline-block;">热词：</label><select style="width: 92px;vertical-align: -1px" id = "templetApkHot"></select>
                            </div>
                            <label for="name_1" style="display: inline-block;">名称：</label><input id="name_1" style="width:80px;vertical-align: -1px;"><button class="btn btn-default" onClick="selectCustomData();" style="vertical-align: -1px;">查询</button>
                            <%-- <table id ="content1"></table>--%>
                        </div>
                        <div id="customRecomContentByTypeDiv" style="height: 470px;overflow:auto;">
                            <table class="table table-striped table-bordered table-hover dataTable" id="ContentByTypeTable"
                                   aria-describedby="ContentByTypeTable">
                            </table>
                        </div>
                    </div>

                </div>
            </div>
            <div style="float:right;width:505px;border: 0px #99BBE8 solid;position: relative;bottom: 0px;">

                <button style="width: 80px;" class="btn btn-default" id ="save" onclick="ContentSubmit();">保存</button>&nbsp;&nbsp;&nbsp;
                <button style="width: 80px;" class="btn btn-default" onclick="ContentClose();">取消</button>
            </div>

            <!-- </div>-->

        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div class="modal fade bs-example-modal-lg" id="video_myModal" tabindex="-1" role="dialog" aria-labelledby="video_myModal" aria-hidden="true" style="margin-top:100px;">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="content_myModalLabel">剧集列表</h4>
            </div>
            <div class="modal-body">
                <div class ="bootstrap-table" id ="videoContent"></div>
            </div>
        </div><!-- /.modal-content -->
        <div class="modal-footer">
            <button class="btn btn-default" data-dismiss="modal">取消</button>
        </div>
    </div><!-- /.modal -->
</div>