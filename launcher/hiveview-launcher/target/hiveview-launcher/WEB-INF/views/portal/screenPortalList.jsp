<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<link href="${ctx}/res/js/plugin/uploadify/uploadify.css" rel="stylesheet">
<link href="${ctx}/res/bootstrap/bootstrap-table.css" rel="stylesheet">
<%--<link href="${ctx}/res/bootstrap/bootstrap-select.css" rel="stylesheet">--%>
<link href="${ctx}/res/css/bootstrap-modal.css" rel="stylesheet">
<script src="${ctx}/res/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/res/bootstrap/bootstrap-table.js" type="text/javascript"></script>
<%--<script src="${ctx}/res/bootstrap/bootstrap-select.js" type="text/javascript"></script>--%>
<script src="${ctx}/res/bootstrap/bootstrap-table-zh-CN.js"></script>
<script src="${ctx}/res/js/jquery.flot.js" type="text/javascript"></script>
<script language="JavaScript">
    var ctx = '${ctx}';
</script>
<style>
    #content_myModal,#editcontent_myModal{
        width: 800px;
        margin-top: -130px;
    }
    .fixed-table-toolbar .columns {
        top: -35px;
    }
    #leftdiv{
        position: relative;
        left:10px;
        padding-left:10px;
        padding-right: 5px;
    }
    #rightdiv{
        position: relative;
        right:10px;
    }
    #lefttable td{
        width:30px;
        white-space: nowrap;
    }
    .fixed-table-toolbar{
        height:0px;
    }
    .pull-right{
        height:0px;
    }
    .keep-open{
        height:0px;
    }
    .btn-group>.btn:last-child, .btn-group>.dropdown-toggle{
        border-radius: 4px;
        vertical-align: 1px;
    }
    #txt_selectName,#txt_selectIsEffective,#selectIsEffective{
        vertical-align: 1px;
    }
    .fixed-table-toolbar .btn-group > .btn-group:last-child > .btn {
        top: -21px;
    }
    .fixed-table-pagination{
        height: 56px;
    }
    #content_myModal{
        position: relative;
        left: 40%;
        [;top:380px;]
    }
    #editcontent_myModal{
        position: relative;
        left: 40%;
    }
    #editcontent_myModal,#content_myModal,#myModal{
        display: none;
    }
    #txt_timeSpan{
        vertical-align: 1px;
        margin-left: 10px;
        margin-right: 5px;
    }
    #content_contentName,#contentType{
        vertical-align: -1px;
    }
    #txt_isPlay,#txt_secondTitle,#txt_title{
        vertical-align: -10px;
    }
    .fixed-table-body{
        width:100%;
    }
    /*.page-content{*/
        /*min-height: 678px;*/
    /*}*/
    /*.portlet-body{*/
        /*min-height:407px;*/
    /*}*/
</style>
<script src="${ctx}/res/js/bootstrap-modal.js" type="text/javascript"></script>
<script src="${ctx}/res/js/bootstrap-modalmanager.js" type="text/javascript"></script>
<script src="${ctx}/res/js/portal/portal.js" type="text/javascript"></script>
<script src="${ctx}/res/js/common/common.js" type="text/javascript"></script>
<script src="${ctx}/res/js/jquery.flot.resize.js" type="text/javascript"></script>
<script src="${ctx}/res/js/plugin/uploadify/jquery.uploadify.js" type="text/javascript"></script>
<div class="container-fluid">
    <!-- BEGIN PAGE HEADER-->
    <div class="row-fluid">
        <div class="span12">
            <!-- BEGIN PAGE TITLE & BREADCRUMB-->
            <h3 class="page-title">
                首屏大图推荐管理
                <small></small>
            </h3>
            <ul class="breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="${ctx}">Home</a>
                    <i class="icon-angle-right"></i>
                </li>
                <li><a href="#">首屏大图推荐管理</a></li>
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
                    <div class="clearfix" style="position: relative;top:-3px;height:50px;">
                        <div class="btn-group">
                            <div class="btn-group">
                                <label style="display: inline-block;margin-bottom: 5px;">
                                    <button id="btn_add" class="btn yellow" style="vertical-align: 2px;">
                                        添加
                                    </button>
                                </label>
                                <label style="display: inline-block;padding-top: 5px;margin-left:30px;" for="txt_selectName" style="display: inline-block;">大图推荐位名称：</label>
                                    <input type="text" class="form-control" name="txt_selectName" id="txt_selectName" style="margin-top: 5px">&nbsp;&nbsp;&nbsp;&nbsp;

                                <label style="display: inline-block;padding-top: 5px;margin-left:30px;" for="txt_selectIsEffective">上下线：</label>
                                    <select class="form-control selectpicker" data-mobile="true" name="txt_selectIsEffective" id="txt_selectIsEffective" style="margin-top: 12px;">
                                        <option value="">全部</option>
                                        <option value="1">上线</option>
                                        <option value="0">下线</option>
                                    </select>&nbsp;

                                <button id="selectIsEffective" class="btn btn-default" onclick="selectIsEffective();">查询</button>

                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-bordered table-hover dataTable" id="portal"
                           aria-describedby="portal_info">
                    </table>
                    <div class="row-fluid">
                        <div class="span6" style="display:none;">
                            <div class="dataTables_info" id="portal_info"></div>
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
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="clear_diaglog();"></button>
                <h4 class="modal-title" id="myModalLabel">大图推荐位管理</h4>
            </div>
            <div class="modal-body">
                <input id="txt_recommendId" type="hidden">
                <div class="form-group">
                    <label for="txt_recommendName" style="display: inline-block;"><span style="color:red">*</span>大图推荐位名称：</label><input type="text" name="txt_recommendName" class="form-control" id="txt_recommendName" placeholder="大图推荐位名称" style="vertical-align: 1px;">
                </div>
                <div class="form-group">
                    <label for="txt_timeSpan" style="display: inline-block;"><span style="color:red">*</span>轮显时间间隔：</label>
                    <input type="text" name="txt_timeSpan" class="form-control" id="txt_timeSpan" placeholder="轮显时间间隔">分钟
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="portal_submit();">保存</button>
                <button type="button" class="btn btn-default" onclick="clear_diaglog();">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div class="modal fade bs-example-modal-lg" id="content_myModal" tabindex="-1" role="dialog" aria-labelledby="content_myModalLabel" aria-hidden="true" style="margin-top:100px;">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="content_myModalLabel">大图推荐位内容管理</h4>
                <div class="clearfix">
                    <div class="btn-group" >

                            <button id="btn_contentAdd" class="btn yellow" id ="btn_contentAdd" onclick="loadContentPage();" style="vertical-align: 1px; border-top-right-radius: 4px;-webkit-border-top-right-radius: 4px;border-bottom-right-radius: 4px;-webkit-border-bottom-right-radius: 4px;">
                                添加
                            </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <input id="content_recommendId" type="hidden">
                            <input id="txt_minSeq" type="hidden">
                            <label for="content_contentName" style="display: inline-block;margin-left:20px;">大图推荐位名称：</label><input type="text" class="form-control" name="content_contentName" id="content_contentName" style="margin-top: 5px;vertical-align:1px;">&nbsp;&nbsp;
                            <button class="btn btn-default" onclick="getContentList();">查询</button>


                    </div>
                </div>

                <%--<button type="button" class="btn btn-primary" id ="btn_contentAdd" onclick="loadContentPage();">新增</button>--%>
            </div>
            <div class="modal-body">
                <%--<input id="content_recommendId" type="hidden">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;大图推荐位名称:<input type="text" class="input-group" id = "content_recommendName"></input>
                <button type="button" class="btn btn-primary" onclick="getContentList();">查询</button>--%>
                <div class ="bootstrap-table" id ="content" style="width: 750px;"></div>
            </div>
        </div><!-- /.modal-content -->
        <div class="modal-footer">
            <button class="btn btn-default" data-dismiss="modal">取消</button>
        </div>
    </div><!-- /.modal -->
</div>
<div style = "width: 820px" class="modal fade bs-example-modal-lg" id="editcontent_myModal" tabindex="-1" role="dialog" aria-labelledby="editcontent_myModalLabel" aria-hidden="true" style="width:auto;">
    <div class="modal-dialog modal-lg" style="width: 800px;height: 580px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="close_contentdiaglog();"></button>
                <h4 class="modal-title" id="editcontent_myModalLabel">编辑推荐位类型</h4>
            </div>
            <%--<div class="container"></div>--%>
            <div class="modal-body" style="padding: 0px;">
                <input id="editcontent_recommendId" type="hidden">
                <input id="edit_id" type="hidden">
                <input id="editcontent_id" type="hidden">
                <input id="txt_contentname" type="hidden">
                <input id="back_content_type" type="hidden">
                <input id="back_templet_id" type="hidden">
                <input id="back_templet_apk" type="hidden">
                <input id="back_templet_channel_type" type="hidden">
                <input id="back_templet_channel" type="hidden">
                <input id="back_is_vip" type="hidden">
                <input id="back_is_pay" type="hidden">
                <input id="back_aqy_is_vip" type="hidden">
                <input id="back_aqy_is_pay" type="hidden">
                <input id="back_hot_id" type="hidden">
                <input id="back_app_style" type="hidden">
                <input id="spec_sn" type="hidden">
                <div style="float: left;width:290px;height:460px;border: 1px #99BBE8 solid;" id="leftdiv">
                    <table id="lefttable">
                        <tr>
                            <td><label for="txt_title"><span style="color: red">*</span>标题：</label></td><td><input type="text" id="txt_title"></td>
                        </tr>
                        <tr>
                            <td><label for="txt_secondTitle">副标题：</label></td><td><input type="text" id="txt_secondTitle"></td>
                        </tr>
                        <tr>
                            <td><label for="txt_isPlay">内容类型：</label></td><td><select id="txt_isPlay" onchange="typeChange();"><option value ='0' selected = "selected">图片</option><option value ='1'>视频</option></select></td>
                        </tr>
                        <tr>
                            <td><span style="color:red">*</span>图片：</td><td>
                            <div id ="t">
                            <div class="txt"><input id="recommendContentImgUrl" type="file" value="" style="vertical-align: -45px;"></div>
                                <div class="pic"><img id="recommendContentImg" src="" alt="" width="160" height="50" /><input id="contentImg" type ="hidden"/><span><br>(图片大小  1000x562)</span></div>
                            </div>
                            <div id ="s" style="display: none">
                            <div class="txt"><input id="m3u8" type="text" value="" disabled></div>
                            </div>
                        </td>
                        </tr>
                    </table>
                        </div>
                <div style="float: right;width:465px;height:450px;border: 1px #99BBE8 solid;padding-top:10px;" id="rightdiv"><label
                        for="contentType" style="display: inline-block;">推荐位类型：</label>
                            <select onchange="dataSource()" id="contentType">
                                <option value ='1' selected = true>专辑详情</option>
                                <option value ='0'>影视专题</option>
                                <option value ='6'>全屏大图</option>
                                <option value ='2'>应用详情</option>
                                <option value ='3'>应用专题</option>
                                <option value ='4'>活动详情</option>
                                <option value ='7'>商品包</option>
                                <option value ='8'>优购专题页</option>
                                <option value ='9'>优购详情页</option>
                                <option value ='10'>Tab页</option>
                                <option value ='11'>会员活动</option>
                                <option value ='12'>启动应用</option>
                            </select>
                    <div id ="div_1"><label for="templet" style="display: inline-block;">模板：</label><select id ="templet" style="width: 91px;vertical-align: -1px;"></select>
                        <label for="templetApk" style="display: inline-block;">包名：</label><select style="width: 106px;vertical-align: -1px;" id = "templetApk" onchange="initTempletApkBindEvent();"></select><div id ="dm" style="display: inline">
                            <label for="templetApkChannelType" style="display: inline-block;">频道类型：</label><select style="width: 91px;vertical-align: -1px;" id = "templetApkChannelType" onchange="initTempletApkChannelTypeBindEvent();"></select><label
                                for="templetApkChannel" style="display: inline-block;">&nbsp;频道：</label><select style="width: 142px;vertical-align: -1px;" id = "templetApkChannel"></select></div>
                        <label for="pay" style="display: inline-block;">付费：</label><select style="width: 106px;vertical-align: -1px;" id ="pay"></select><label
                                for="vip" style="display: inline-block;">VIP：</label><select style="width: 100px;vertical-align: -1px;" id ="vip"></select>&nbsp;<div id ="jq" style="display:none">
                            <label for="templetApkHot" style="display: inline-block;">热词：</label><select style="width: 92px;vertical-align: -1px;" id = "templetApkHot"></select></div>
                        <label for="name_1" style="display: inline-block;">名称：</label><input id="name_1" style="width:80px;vertical-align: -1px;"><button class="btn btn-default" onclick="dataSource();" style="vertical-align: 0px;">查询</button><table id ="content1"></table></div>
                            <div id ="div_6"><label for="name_6" style="display: inline-block;">名称：</label><input id="name_6" style="width:80px;vertical-align: -1px;"><button class="btn btn-default" onclick="dataSource();" style="vertical-align: 0px;">查询</button><table id ="content6"></table></div>
                            <div id ="div_0"><label for="templet_subject" style="display: inline-block;">模板：</label><select id ="templet_subject" style="width: 91px;vertical-align: -1px;"></select>
                                <label for="templetApk_subject" style="display: inline-block;">包名：</label><select style="width: 106px;vertical-align: -1px;" id = "templetApk_subject"></select>&nbsp;<label
                                        for="name_0" style="display: inline-block;">名称：</label><input id="name_0" style="width:80px;vertical-align: -1px;"><button class="btn btn-default" onclick="dataSource();" style="vertical-align: 0px;">查询</button><table id ="content0"></table></div>
                            <div id ="div_2"><label for="name_2" style="display: inline-block;">名称：</label><input id="name_2" style="width:80px;vertical-align: -1px;"><label
                                    for="app_type" style="display: inline-block;">应用类型：</label><select id="app_type" style="width:80px;vertical-align: -1px;"></select><button class="btn btn-default" onclick="dataSource();" style="vertical-align: 0px;">查询</button><table id ="content2"></table></div>
                            <div id ="div_3"><label for="name_3" style="display: inline-block;">名称：</label><input id="name_3" style="width:80px;vertical-align: -1px;"><label
                                    for="appsubject_type" style="display: inline-block;">应用类型：</label><select id="appsubject_type" style="width:80px;vertical-align: -1px;"></select><button class="btn btn-default" onclick="dataSource();" style="vertical-align: 0px;">查询</button><table id ="content3"></table></div>
                            <div id ="div_4"><label for="name_4" style="display: inline-block;">名称：</label><input id="name_4" style="width:80px;vertical-align: -1px;"><button class="btn btn-default" onclick="dataSource();" style="vertical-align: 0px;">查询</button><table id ="content4"></table></div>
                            <div id ="div_7"><label for="name_7" style="display: inline-block;">名称：</label><input id="name_7" style="width:80px;vertical-align: -1px;"><button class="btn btn-default" onclick="dataSource();" style="vertical-align: 0px;">查询</button><table id ="content7"></table></div>
                            <div id ="div_8"><label for="name_8" style="display: inline-block;">名称：</label><input id="name_8" style="width:80px;vertical-align: -1px;"><button class="btn btn-default" onclick="dataSource();" style="vertical-align: 0px;">查询</button><table id ="content8"></table></div>
                            <div id ="div_9">商品类型：<select style="width: 91px;vertical-align: -1px;" id = "firstType" onchange="firstType();"><select style="width: 91px;vertical-align: -1px;" id = "secondType" onchange="secondType();"><select style="width: 91px;vertical-align: -1px;" id = "thirdType"><br>名称:<input id="name_9" style="width:80px;vertical-align: -1px;"><button class="btn btn-default" onclick="dataSource();" style="vertical-align: 0px;">查询</button><table id ="content9"></table></div>
                            <div id ="div_10"><label for="name_10" style="display: inline-block;">名称：</label><input id="name_10" style="width:80px;vertical-align: -1px;"><button class="btn btn-default" onclick="dataSource();" style="vertical-align: 0px;">查询</button><table id ="content10"></table></div>
                            <div id ="div_11"><table id ="content11"></table></div>
                            <div id ="div_12"><table id ="content12"></table></div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id ="save" onclick="content_submit(0);">保存</button>
                <button type="button" class="btn btn-default" onclick="close_contentdiaglog();">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<script>
//    $(function(){
//        console.log($("span:contains('上线')"));
//    });
</script>