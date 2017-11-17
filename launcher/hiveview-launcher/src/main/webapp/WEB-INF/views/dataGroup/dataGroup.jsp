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

<script src="${ctx}/res/js/plugin/uploadify/jquery.uploadify.js" type="text/javascript"></script>
<script src="${ctx}/res/js/bootstrap-modal.js" type="text/javascript"></script>
<script src="${ctx}/res/js/bootstrap-modalmanager.js" type="text/javascript"></script>
<script src="${ctx}/res/js/plugin/ajaxupload.3.6.js" type="text/javascript"></script>
<script src="${ctx}/res/js/dataGroup/dataGroup.js" type="text/javascript"></script>
<script src="${ctx}/res/js/jquery.flot.resize.js" type="text/javascript"></script>
<style>
    /*.row-fluid{*/
        /*display:none;*/
    /*}*/
/*    .fixed-table-loading{
        display:none;
    }*/
/*    .fixed-table-toolbar .btn-group > .btn-group {
         display: inline-block;
         !* margin-left: -1px !important; *!
         !* margin-bottom: 23px !important; *!
         position: absolute;
         right: 1%;
         top: -11px;
     }*/
    .table-striped tbody>tr:nth-child(odd)>td, .table-striped tbody>tr:nth-child(odd)>th{
        background:none;
    }
    .table-hover tbody tr:hover>td{
        background:none;
    }
</style>

<div class="container-fluid">
    <!-- BEGIN PAGE HEADER-->
    <div class="row-fluid">
        <div class="span12">
            <!-- BEGIN PAGE TITLE & BREADCRUMB-->
            <h3 class="page-title">
                Data Group管理
                <small></small>
            </h3>
            <ul class="breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="${ctx}">Home</a>
                    <i class="icon-angle-right"></i>
                </li>
                <li><a href="#">Data Group管理</a></li>
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

                            <div style="float:left ;display: inline; width:50%; height:100%;right:100px">
                                <%--  <span style="display: inline-block">列表名称</span>--%>
                                <label for="txt_data_group_name" style="display: inline-block;"> Data Group名称： &nbsp;&nbsp;</label>
                                &nbsp;&nbsp;&nbsp;
                                <input type="text"  name="txt_data_group_name" id="txt_data_group_name">
                                <label for="txt_beanIsEffecitveLike" style="display: inline-block;inline-blockcursor: auto;"> &nbsp;&nbsp;&nbsp;&nbsp;上下线： &nbsp;&nbsp;</label>
                                <select class="form-control" data-mobile="true" name="txt_beanIsEffecitveLike" id="txt_beanIsEffecitveLike" style="width: 64px;">
                                    <option value="">全部</option>
                                    <option value="1">上线</option>
                                    <option value="0">下线</option>
                                </select>
                                <button class="btn yellow" onclick="dataGrouName()" id="baanCurdNameButton" style="vertical-align:top;">
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
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="clear_diaglog();"></button>
                <h4 class="modal-title" id="myModalLabel">Data Group管理</h4>
            </div>
            <div class="modal-body">
                <input id="txt_dataGroupId" type="hidden">
                <input id="txt_bakName" type="hidden">
                <div class="form-group">
                    <label for="txt_dataGroupName" style="display: inline-block;"> <span style=" color:red">*</span>Data Group名称：</label>
                    <input type="text" name="txt_dataGroupName" class="form-control" id="txt_dataGroupName" style="vertical-align: -1px;">
                </div>

                <div class="form-group">
                    <label for="txt_dataGroupTitle" style="display: inline-block;"> <span style=" color:red">*</span>Data Group标题名称：</label>
                    <input type="text" name="txt_dataGroupTitle" class="form-control" id="txt_dataGroupTitle" style="vertical-align: -1px;">
                </div>


                <div class="form-group">
                    <label for="txt_contentType" style="display: inline-block;">内容类型：</label>
                    <select class="form-control selectpicker" data-mobile="true" name="txt_contentType" id="txt_contentType" onchange="selectChang()">
                        <option value="0">频道智能推荐 </option>
                        <option value="1">热词内容列表</option>
                        <option value="2">频道全部节目</option>
                    </select>
                </div>

                <div id="dataGroupPageName">
                <div class="form-group">
                    <label for="txt_packageName" style="display: inline-block;">包名：</label>
                    <select class="form-control selectpicker" data-mobile="true" name="txt_packageName" id="txt_packageName" onchange="packageNameSelectChange()" style="width: 214px;">
                        <option value="1">大麦影视</option>
                        <option value="2">应用游戏</option>
                    </select>
                </div>

                </div>

                <div id="chanID">
                    <div class="form-group">
                        <label for="txt_channel" style="display: inline-block;">频道：</label>
                        <select class="form-control selectpicker" data-mobile="true" name="txt_channel" id="txt_channel">
                            <option value="2">电视剧</option>
                            <option value="1">电影</option>
                            <option value="15">儿童</option>
                            <option value="4">动漫</option>
                            <option value="6">综艺</option>
                        </select>
                    </div>
                </div>
                <div id="channelId">
                    <div class="form-group">
                        <label for="txt_channelId" style="display: inline-block;">频道：</label>
                        <select class="form-control selectpicker" data-mobile="true" name="txt_channelId" id="txt_channelId">
                            <option value="1">游戏</option>
                            <option value="2">应用</option>
            <%--                <option value="3">教育</option>--%>
                        </select>
                    </div>
                </div>
                <div id="apkChannelDiv">
                    <div class="form-group">
                        <label for="txt_apkPackageName" style="display: inline-block;">包名：</label>
                        <select class="form-control selectpicker" data-mobile="true" name="txt_apkPackageName" id="txt_apkPackageName" onchange="apkbagChannle();">
                            <option value ='com.hiveview.cloudscreen.vipvideo'>大麦影视</option>
                            <option value ='com.hiveview.cloudscreen.py'>鹏博士课堂</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="txt_apk_channel" style="display: inline-block;">频道：</label>
                        <select class="form-control selectpicker" data-mobile="true" name="txt_apk_channel" id="txt_apk_channel">
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label for="txt_up_and_down" style="display: inline-block;">移动方向：</label>
                    <select class="form-control selectpicker" data-mobile="true" name="txt_up_and_down" id="txt_up_and_down" onchange="upAndDownSelect()">
                        <option value="1" selected = "selected" >横向移动</option>
                        <option value="2">纵向移动</option>
                    </select>
                </div>


                <div class="form-group">
                    <label for="txt_width" style="display: inline-block;"> <span style=" color:red">*</span>推荐位尺寸：</label>
                    <input type="text" name="txt_areaIntroduce" class="form-control" id="txt_width" style="vertical-align: -1px;width: 33px;"placeholder="W" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" oninput="widthInput()" maxlength="3"  ><span>&nbsp;&nbsp;X &nbsp;&nbsp;</span>
                    <input type="text" name="txt_areaIntroduce" class="form-control" id="txt_height" style="vertical-align: -1px;width: 33px;"placeholder="H" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" oninput="heightInput()"maxlength="3" > <span>&nbsp;&nbsp;165px<=w<=260px，95px<=h<=360px</span>
                </div>



                <div class="form-group">
                <label for="txt_row" style="display: inline-block;"> <span style=" color:red">*</span>推荐位模板：</label>
                <input type="text" name="txt_areaIntroduce" class="form-control" id="txt_row" style="vertical-align: -1px; width: 33px;"     onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" oninput='this.value=this.value.replace(/^[0]+[0-9]*$/gi,"")'><span>&nbsp;&nbsp;行 &nbsp;&nbsp;</span>
                <input type="text" name="txt_areaIntroduce" class="form-control" id="txt_col" style="vertical-align: -1px;width: 33px;"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" oninput='this.value=this.value.replace(/^[0]+[0-9]*$/gi,"")'><span>&nbsp;&nbsp;列&nbsp;&nbsp;&nbsp;(行列至少填一项)</span>
                </div>


            </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" onclick="area_submit();">保存</button>
            <button type="button" class="btn btn-default" onclick="clear_diaglog();">取消</button>
        </div>
    </div><!-- /.modal-content -->
</div><!-- /.modal -->


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
                <input id="video_id" type="hidden">
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
                        <tr style="height: 30px;">
                        </tr>
                        <tr>
                            <td>图片：</td>
                            <td><div class="txt"><input id="recommendContentImgUrl" type="file" value=""></div></td>
                            <input type="hidden" id="contentId1">
                        </tr>
                        <tr>
                            <td colspan="2" id="recommendContentImgId">
                                <div class="pic"><img id="recommendContentImg" src="" alt=""  style="width: 260px;height:360px;" /></div>
                                <span>(图片大小：260*360）</span>
                            </td>

                        </tr>
                    </table>
                </div>

                <div style="float:left;">
                    <div style="margin-left:20px;" id="rightdiv" >
                        <div id="operateContentInfo">
                            <label for="contentType" style="display: inline-block;">付费</label>
                            <select onchange="dataSource()" id="contentType" style="width:100px;vertical-align: -1px;">
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
                <div id="tableDiv" style="height: 400px;overflow:auto;width: 400px;">
                    <input type="hidden" id="hiddenId" value="">
                    <table class="table table-striped table-bordered table-hover dataTable" id="sample_table2"
                           aria-describedby="sample_1_info">
                    </table>
                </div>
                <div id="tableDiv1" style="height: 400px;overflow:auto;width: 400px;">
                    <table class="table table-striped table-bordered table-hover dataTable" id="sample_table3"
                           aria-describedby="sample_1_info">
                    </table>
                </div>
            </div>
        </div>
        <div id="footer" style="float:right;width:505px;border: 0px #99BBE8 solid;position: relative;top:-10px;left:149px;">

            <button style="width: 80px;position:absolute;top:12px;" class="btn btn-default" id ="save" onclick="content_submit();">保存</button>&nbsp;&nbsp;&nbsp;
            <button style="width: 80px;position:absolute;top:12px;left: 103px;" class="btn btn-default" onclick="close_contentdiaglog();">取消</button></div>
        <!-- </div>-->
    </div><!-- /.modal-content -->
</div><!-- /.modal -->

<%--ss--%>
<div class="modal fade" id="myModalTwo" tabindex="999" role="dialog" aria-labelledby="myModalTwo" aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="margin-top: 30px;">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="myModal3" >关联内容</h4>
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
                <div class="modal-footer" style="text-align:center;">

                    <button type="button" class="btn btn-default" onclick="clear_diaglog2();">保存</button>
                    <button type="button" class="btn btn-default" onclick="clear_diaglog2();">取消</button>
                </div>
            </div>
        </div>
    </div>
</div><!-- /.modal -->

<div class="modal fade" id="channelModal" tabindex="-1" role="dialog" aria-labelledby="channelModal" aria-hidden="true" style="display: none;">
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
            <h4 class="modal-title" >关联内容</h4>
        </div>
        <div style="float:left ;display: inline; width:30%; height:100%;">
            <input type="hidden" id="hotID" value="">
            <input type="hidden" id="apkId" value="">
            <input type="hidden" id="apk_channel_id" value="">
            <input type="hidden" id="tag_id" value="">
            <label for="txtHotName" style="display: inline-block;">已选择热词：</label>
            <input type="text"  name="txtHotName" id="txtHotName" readonly="true">
        </div>
        <div class="modal-body" style="float:right ;display: inline; width:70%; height:100%;">
            <input id="data_group_id" type="hidden">
            <div class="clearfix" style="position: relative;top: -12px;">
                <div class="btn-group">

                    </div>

                    <%--  <span style="display: inline-block">列表名称</span>--%>
                    <div style="float:left ;display: inline; width:50%; height:100%;" >
                        <div class="form-group">
                            <label for="txt_hot_pagBakName" style="display: inline-block;">包名：</label>
                            <select class="form-control selectpicker" data-mobile="true" name="" id="txt_hot_pagBakName" onchange="pageckageSelect()" style="width: 215px;">
                            </select>
                        </div>
                    </div>
                <div style="float:right ;display: inline; width:50%; height:100%;" id="apkDiv">
                    <label for="txt_hot_channel" style="display: inline-block;margin-left:20px;"> 频道： </label>
                    <select class="form-control selectpicker" data-mobile="true" name="txt_hot_channel" id="txt_hot_channel" onchange="chan();" style="width:214px;">
                    </select>
                </div>
                <div style="float:right ;display: inline; width:50%; height:100%;" id="appDiv">
                    <label for="txt_app_channel" style="display: inline-block;margin-left:20px;"> 频道： &nbsp;&nbsp;</label>
                    <select class="form-control selectpicker" data-mobile="true" name="txt_app_channel" id="txt_app_channel" onchange="appChaner();">
                    </select>
                </div>
                <div style="float:left ;display: inline; width:50%; height:100%;">
                    <label for="txt_hotName" style="display: inline-block;inline-blockcursor: auto;">名称：</label>
                    <input type="text"  name="txt_hotName" id="txt_hotName" style="width:201px;">
                    <button class="btn yellow" onclick="beanCurdNameLike();" id="beanCurdNameButton" style="vertical-align:top;">
                        查询
                    </button>
                </div>
            <div id="hotDiv">
                <table class="table table-striped table-bordered table-hover dataTable" id="channl_table" aria-describedby="sample_1_info">
                </table>
            </div>
                </div>
            <div id="appTagDiv">
                <table class="table table-striped table-bordered table-hover dataTable" id="app_tag_table" aria-describedby="sample_1_info">
                </table>
            </div>

        </div>
            </div>
            <div class="row-fluid">
                <div class="span6">
                    <div class="dataTables_info" id="channlTable"></div>
                </div>
            </div>

    <div class="modal-footer" style="text-align: center">
        <button type="button" class="btn btn-default" onclick="hotSumbit();" >保存</button>
        <button type="button" class="btn btn-default" onclick="newTempletCloses();">取消</button>
    </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->


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