<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<script language="JavaScript">
    var ctx = '${ctx}';
</script>
<link rel="stylesheet" type="text/css" href="${ctx}/res/js/dist/jquery.gridster.min.css">
<link href="${ctx}/res/bootstrap/bootstrap-table.css" rel="stylesheet">
<link href="${ctx}/res/js/plugin/uploadify/uploadify.css" rel="stylesheet">
<link href="${ctx}/res/css/bootstrap-modal.css" rel="stylesheet">
<%--<link href="${ctx}/res/bootstrap/bootstrap-dialog.min.css" rel="stylesheet">--%>
<script type="text/javascript"src="${ctx}/res/js/dist/jquery.gridster.js" charset="utf-8"></script>

<script src="${ctx}/res/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/res/bootstrap/bootstrap-table.js" type="text/javascript"></script>
<script src="${ctx}/res/bootstrap/bootstrap-table-zh-CN.js"></script>
<script src="${ctx}/res/js/jquery.flot.js" type="text/javascript"></script>

<script src="${ctx}/res/js/bootstrap-modal.js" type="text/javascript"></script>
<script src="${ctx}/res/js/bootstrap-modalmanager.js" type="text/javascript"></script>
<%--<script src="${ctx}/res/bootstrap/bootstrap-dialog.min.js" type="text/javascript"></script>--%>

<script src="${ctx}/res/js/customRecomTemplete/customRecomContent.js" type="text/javascript"></script>
<script src="${ctx}/res/js/customRecomTemplete/customRecomAlternativeContent.js" type="text/javascript"></script>
<script src="${ctx}/res/js/jquery.flot.resize.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/res/js/dist/uuid.js"></script>
<script type="text/javascript" src="${ctx}/res/js/dist/drawTables.js"></script>
<script type="text/javascript" src="${ctx}/res/js/dist/contentGridster.js"></script>
<script src="${ctx}/res/js/plugin/uploadify/jquery.uploadify.js" type="text/javascript"></script>
<!--[if IE]>
<script src="${ctx}/res/js/common/html5.js" type="text/javascript"></script>
<![endif]-->
<!--[if IE]>
<script type="text/javascript" src="${ctx}/res/js/common/html5.js"></script>
<![endif]-->
<style>
    #gridsterLayoutSecond li {
        border: 2px solid red;
        list-style: none;
    }
    #customRecomConTempleteModal{
        width: 941px;
        height: 480px;
    }
    .fixed-table-toolbar .columns{
        top:-10px;
    }

    .modal-body{
        padding:0px!important;
    }
    /*#customLeftTable td{*/
        /*white-space: nowrap;*/
    /*}*/
    #customLeftDiv{
        position: absolute;
        padding-left:1px;
        padding-right: 5px;
    }
    #editCustomContentModalBody{
        position: relative;
        padding: 0px;
    }
    #editCustomContentModal {
        width: 810px;
        height: 762px;
        overflow: auto;
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
    #addCustomRecomConTemplete{
        border-radius:4px;
        -webkit-border-radius:4px;
        -moz-border-radius:4px;
        -ms-border-radius:4px;
        -o-border-radius:4px;
    }
    #searchCustomRecomList{
        border-radius:4px;
        -webkit-border-radius:4px;
        -moz-border-radius:4px;
        -ms-border-radius:4px;
        -o-border-radius:4px;
    }
    .fixed-table-toolbar .btn-group > .btn-group:last-child > .btn {
        top: -46px;
    }
    .fixed-table-pagination{
        height:55px;
    }
    #editCustomContentModal{
        position: relative;
        left:40%;
    }
    .btn-xs{
        vertical-align: middle;
    }
    /*#customContentTitle,#customContentSubtitle,#customContentImgSel{*/
        /*vertical-align: -15px;*/
    /*}*/
    .fixed-table-body{
        width:100%;
    }
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
                Group管理
                <small></small>
            </h3>
            <ul class="breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="${ctx}">Home</a>
                    <i class="icon-angle-right"></i>
                </li>
                <li><a href="#"> Group管理</a></li>
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
                    <div class="clearfix" style="position: relative;top:6px;">
                        <div class="btn-group">
                            <button id="addCustomRecomConTemplete" class="btn btn-default" onclick="addCustomRecomConTemplete()" style="position:relative;top:-5px;">
                                添加
                            </button>
                            <label style="font-size: 14px;display: inline-block" for="searchCustomRecomName">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Group名称：</label>
                            &nbsp;&nbsp; <input style="width: 100px;" type="text" id="searchCustomRecomName" name="searchCustomRecomName">&nbsp;&nbsp;&nbsp;&nbsp;
                            <label style="font-size: 14px;display: inline-block" for="searchContentIseffective">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上下线：</label>
                            <select  data-mobile="true" name="isEffective" style="width: 64px;" id="searchContentIseffective" data-placeholder="全部">
                                <option value="">全部</option>
                                <option value="1">上线</option>
                                <option value="0">下线</option>
                            </select>
                            <button type="button" class="btn btn-default" id="searchCustomRecomList" onclick="searchCustomRecomList()" style="position:relative;top:-5px;">查询</button>
                        </div>
                    </div>
                    <table class="table table-striped table-bordered table-hover dataTable" id="customRecomContentTable"
                           aria-describedby="customRecomContentTable">
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
<div class="modal fade hide" id="customRecomConTempleteModal" tabindex="-1" role="dialog" aria-labelledby="customRecomConTempleteModal" aria-hidden="true" style="width:941px;left: 37%;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="customRecomContentLabel">积木推荐位内容管理</h4>
            </div>
            <div class="modal-body" >
                <input id="templeteId" value="${templete.templeteId}" type="hidden" />
                <input id="layoutJson" value="${templete.layoutJson}" type="hidden" />
                <input id="templeteState" value="" type="hidden" />
                <input id="uuid" value="" type="hidden" />
                <div id="nameAndOption">
                <label style="display:inline-block;" for="customRecomConTempleteName"><span style="color:red" >*</span>积木推荐位名称：</label>
                    <input type="text"  class="form-control" id="customRecomConTempleteName" style="vertical-align: -1px;">
                <label for="contentFatherId" style="display:inline-block;">积木推荐位模板名称：</label>
                    <select class="form-control selectpicker" data-mobile="true" name="text-operateType" id="contentFatherId" style="vertical-align: -1px;">
                            onchange="contentChangeFaTem()">
                    </select>
                </div>
                <div style="overflow:auto;">
                    <div class="gridster" id="gridsterLayoutSecond" style="width:1200px; height:360px; padding:0px 10px 10px 0px;position:relative;">
                        <ul style="width: 0px; position: absolute;padding:0">

                        </ul>
                        <canvas id="a_canvas" style="background:grey;position:absolute;top:0;left: 0;">

                        </canvas>
                        <span style="position: absolute;top:0;left:640px;width: 0px;background:red;height:348px;border-right:1px solid #000;"></span>
                    </div>

                </div>


            </div>
            <div class="modal-footer">
                <button type="button" id = "submitCustomRecomTemplete" class="btn btn-default editLayout" onclick="submitCustomRecomContent();">保存</button>
                <button type="button" id = "clearCustomRecomTemplete" class="btn btn-default" onclick="clearCustomRecomContent();">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div class="modal fade hide" id="editCustomContentModal" tabindex="-1" role="dialog" aria-labelledby="editCustomContentModal" aria-hidden="true" >
    <div class="modal-dialog modal-lg" <%--style="width: 800px"--%>>
        <div class="modal-content">
            <div class="modal-header" style="margin-top: 20px;">
                <%--<input id="content_recommendId" type="hidden">--%>
              <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="editcontent_myModalLabel">编辑推荐位内容</h4>
            </div>
            <%--<div class="container"></div>--%>
            <div class="modal-body" id = "editCustomContentModalBody">
                <input id="title" type="hidden">
                <input id="contentBigPic" type="hidden">
                <input id="customContentId" type="hidden">
                <input id="layoutId" type="hidden">
                <input id="categoryId" type="hidden">
                <input id="chnId" type="hidden">
                <input id="chnType" type="hidden">
                <input id="hotId" type="hidden">
                <input id="hotType" type="hidden">
                <input id="aqyIsVip" type="hidden">
                <input id="apkBagName" type="hidden">
                <input id="specSn" type="hidden">
                <input id="videoId" type="hidden">
                <input id="apk" type="hidden">
                <input id="apkVersionCode" type="hidden">
                <input id="apkDownUrl" type="hidden">
                <input id="action" type="hidden">
                <input id="templeteName" type="hidden">
                <input id="playFlag" type="hidden">
                <input id="installStyle" type="hidden">
                <input id="appType" type="hidden">
               <div style="float: left;width:300px;height:640px;border: 1px #99BBE8 solid;" id="customLeftDiv">
                    <table id="customLeftTable">
                        <tr>
                            <td>标题：<input type="text"  id="customContentTitle" onblur="checkTitle()"></td>
                            <%--<td>  <span style="color: red">*</span><input type="text"  id="customContentTitle"></td>--%>
                        </tr>
                        <tr>
                            <td> 副标题：<input type="text" id="customContentSubtitle" onClick="checkTitle();"></td>
                            <%--<td><input type="text" id="customContentSubtitle"></td>--%>
                        </tr>
                        <tr style="display: none">
                            <td>显示控制：<select  id="customContentImgSel">
                                <option value ='1' selected = true>显示原图</option>
                                <option value ='2'>显示露头图</option>
                            </select></td>
                            <%--<td><select  id="customContentImgSel">--%>
                                <%--<option value ='1' selected = true>显示原图</option>--%>
                                <%--<option value ='2'>显示露头图</option>--%>
                            <%--</select></td>--%>
                        </tr>
                        <tr>
                            <td>显示内容：<select  id="isPlay" onchange="changeShow()">
                                <option value ='1' >视频</option>
                                <option value ='0' selected = true>图片</option>
                            </select></td>
                        <%--<td><select  id="text_is_play" onchange="changeShow()">--%>
                        <%--<option value ='1' >视频</option>--%>
                        <%--<option value ='0' selected = true>图片</option>--%>
                        <%--</select></td>--%>
                        </tr>
                        <tr id ="s"  style="display: none">
                            <td>视频：<input type="text" id="videoUrl" disabled = true></td>
                            <%--<td><input type="text" id="videoUrl"></td>--%>
                        </tr>
                        <tr id ="t_1">
                            <td>图片：<div class="txt"><input id="customRecomContentImgUrl" type="file" value="" style="vertical-align: -45px;"></div></td>
                            <%--<td><div class="txt"><input id="customRecomContentImgUrl" type="file" value="" style="vertical-align: -45px;"></div></td>--%>
                        </tr>
                        <tr id ="t_2">
                            <td colspan="2" > <div class="pic"><img id="customRecomContentImg" src="" alt="" style="margin-left: 50px;" width="123" height="50" /></div>
                                <span id = "customRecomContentImgSize">(图片大小：177 x 245）</span>
                            </td>
                        </tr>
                        <tr id ="t_3">
                            <td>露头图：<div class="txt"><input id="customRecomOutcropImgUrl" type="file" value="" style="vertical-align: -45px;"></div></td>
                            <%--<td><div class="txt"><input id="customRecomOutcropImgUrl" type="file" value="" style="vertical-align: -45px;"></div></td>--%>

                        </tr>
                        <tr id ="t_4">
                            <td colspan="2" > <div class="pic"><img id="customRecomOutcropImg" src="" alt="" style="margin-left: 50px;" width="123" height="50" /></div>
                                <span id = "customRecomOutcropImgSize">(图片大小：177 x 245）</span><input id="clearPic" type="button" value="清除露头图" onclick="clearPic();">
                            </td>
                        </tr>
                        <tr>
                            <td> <span id = "skip">跳转状态:不可跳转</span><input id="clearInfo" type="button" value="设为不可跳转" onclick="clearInfo();"></td>
                        </tr>
                    </table>
                </div>

                <div style="float: right;width:465px;height:640px;border: 1px #99BBE8 solid;">
                    <div id="customRightDiv" >
                        <select onchange="dataCustomSource()" id="customContentType">
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
                            <option value ='11'>常规活动</option>
                            <option value ='12'>启动应用</option>
                        </select>
                        <div id ="div_1"><label for="customTempletId" style="display: inline-block;">模板：</label><select id ="customTempletId" style="width: 91px;vertical-align: -1px;"></select><label for="customTempleApk" style="display: inline-block;">包名：</label><select style="width: 106px;vertical-align: -1px;" id = "customTempleApk" onchange="initCustomTempletApkBindEvent();"></select>
                            <div id ="dm" style="display: inline">
                                <label for="templetApkChannelType" style="display: inline-block;">频道类型：</label><select style="width: 91px;vertical-align: -1px;" id = "templetApkChannelType" onchange="initCustomTempletApkChannelTypeBindEvent();"></select><label for="templetApkChannel" style="display: inline-block;">频道：</label><select style="width: 142px;vertical-align: -1px;" id = "templetApkChannel"></select>
                            </div>
                            <label for="pay" style="display: inline-block;">付费：</label><select style="width: 106px;vertical-align: -1px;" id ="pay"></select>
                            <label for="vip" style="display: inline-block;">VIP：</label><select style="width: 100px;vertical-align: -1px;" id ="vip"></select>
                            <div id ="jq" style="display:none">
                                <label for="templetApkHot" style="display: inline-block;">热词：</label><select style="width: 92px;vertical-align: -1px" id = "templetApkHot"></select>
                            </div>
                            <label for="name_1" style="display: inline-block;">名称：</label><input id="name_1" style="width:80px;vertical-align: -1px;"><button class="btn btn-default" onclick="selectCustomData();" style="vertical-align: -1px;">查询</button>
                           <%-- <table id ="content1"></table>--%>
                        </div>
                        <div id ="div_6"><label for="name_6" style="display: inline-block;">名称：</label><input id="name_6" style="width:80px;vertical-align: -1px;"><button class="btn btn-default" onclick="selectCustomData();" style="vertical-align: -1px;">查询</button>
                            <%--<table id ="content6"></table>--%>
                        </div>
                        <div id ="div_0"><label for="customSubTempleteId" style="display: inline-block;">模板：</label><select id ="customSubTempleteId" style="width: 91px;vertical-align: -1px;"></select>
                            <label for="customSubApk" style="display: inline-block;">包名：</label><select style="width: 106px;vertical-align: -1px;" id = "customSubApk"></select>&nbsp;
                            <label for="name_0" style="display: inline-block;">名称：</label><input id="name_0" style="width:80px;vertical-align: -1px;"><button class="btn btn-default" onclick="selectCustomData();" style="vertical-align: -1px;">查询</button>
                            <%--<table id ="content0"></table>--%>
                        </div>
                        <div id ="div_2"><label for="name_2" style="display: inline-block;">名称：</label><input id="name_2" style="width:80px;vertical-align: -1px;"><label
                                for="app_type" style="display: inline-block;">应用类型：</label><select id="app_type" style="width:80px;vertical-align: -1px;"></select><button class="btn btn-default" onclick="selectCustomData();" style="vertical-align: -1px;">查询</button>
                           <%-- <table id ="content2"></table>--%>
                        </div>
                        <div id ="div_3"><label for="name_3" style="display: inline-block;">名称：</label><input id="name_3" style="width:80px;"><label
                                for="appsubject_type" style="display: inline-block;">应用类型：</label><select id="appsubject_type" style="width:80px;vertical-align: -1px;"></select><button class="btn btn-default" onclick="selectCustomData();" style="vertical-align: -1px;">查询</button>
                            <%--<table id ="content3"></table>--%>
                        </div>
                        <div id ="div_4"><label for="name_4" style="display: inline-block;">名称：</label><input id="name_4" style="width:80px;vertical-align: -1px;"><button class="btn btn-default" onclick="selectCustomData();" style="vertical-align: -1px;">查询</button>
                            <%--<table id ="content4"></table>--%>
                        </div>
                        <div id ="div_7"><label for="name_7" style="display: inline-block;">名称：</label><input id="name_7" style="width:80px;vertical-align: -1px;"><button class="btn btn-default" onclick="selectCustomData();" style="vertical-align: -1px;">查询</button>
                            <%--<table id ="content7"></table>--%>
                        </div>
                        <div id ="div_8"><label for="name_8" style="display: inline-block;">名称：</label><input id="name_8" style="width:80px;vertical-align: -1px;"><button class="btn btn-default" onclick="selectCustomData();" style="vertical-align: 0px;">查询</button>
                        </div>
                        <div id ="div_9">商品类型：<select style="width: 91px;vertical-align: -1px;" id = "firstType" onchange="firstType();"></select><select style="width: 91px;vertical-align: -1px;" id = "secondType" onchange="secondType();"></select><select style="width: 91px;vertical-align: -1px;" id = "thirdType"></select>名称:<input id="name_9" style="width:80px;vertical-align: -1px;"><button class="btn btn-default" onclick="selectCustomData();" style="vertical-align: 0px;">查询</button>
                        </div>
                        <div id ="div_10"><label for="name_10" style="display: inline-block;">名称：</label><input id="name_10" style="width:80px;vertical-align: -1px;"><button class="btn btn-default" onclick="selectCustomData();" style="vertical-align: 0px;">查询</button>
                        </div>
                        <div id ="div_11"><label for="name_11" style="display: inline-block;">名称：</label><input id="name_11" style="width:80px;vertical-align: -1px;"><button class="btn btn-default" onclick="selectCustomData();" style="vertical-align: 0px;">查询</button><table id ="content11"></table>
                        </div>
                        <div id ="div_12"><table id ="content12"></table>
                        </div>
                        <div id="customRecomContentByTypeDiv" style="height: 470px;overflow:auto;">
                            <table class="table table-striped table-bordered table-hover dataTable" id="customRecomContentByTypeTable"
                                   aria-describedby="customRecomContentByTypeTable">
                            </table>
                        </div>
                    </div>

                </div>
            </div>
            <div style="float:right;width:505px;border: 0px #99BBE8 solid;position: relative;bottom: 0px;">

               <button style="width: 80px;" class="btn btn-default" id ="save" onclick="customContentSubmit();">保存</button>&nbsp;&nbsp;&nbsp;
               <button style="width: 80px;" class="btn btn-default" id ="backups" onclick="customContentBackups();">备份</button>&nbsp;&nbsp;&nbsp;
                <button style="width: 80px;" class="btn btn-default" onclick="customContentClose();">取消</button>
            </div>

            <!-- </div>-->

        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div class="modal fade" id="editRecomContentModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="myModalLabel">Group管理</h4>
            </div>
            <div class="modal-body">
                <input id="temId" type="hidden">
                <input id="temName" type="hidden">
                <input id="layCount" type="hidden">
                <div class="form-group">
                    <label for="groupName" style="display: inline-block;"><span style="color:red">*</span>Group名称：</label>
                    <input type="text" name="logo_license_name" class="form-control" id="groupName" placeholder="Group名称" style="vertical-align: -1px;margin-left: 28px;">
                </div>
                <div class="form-group">
                    <label for="groupTitle" style="display: inline-block;"><span style="color:red">*</span>Group标题名称：</label>
                    <input type="text" name="logo_license_name" class="form-control" id="groupTitle" placeholder="Group标题名称" style="vertical-align: -1px;">
                </div>

                <div class="form-group">
                    <label for="recomTemplete" style="display: inline-block;"><span style="color:red">*</span>推荐位模板：</label>
                    <select class="form-control selectpicker" data-mobile="true" name="recomTemplete" id="recomTemplete" style="vertical-align: -1px;margin-left: 24px;">
                    </select>

                </div>
                <div class="form-group">
                    <label style="display: inline-block;vertical-align: -1px;">背景图片：</label>
                    <div class="txt" style="display: inline-block;"><input id="contentBackPicImgUrl" type="file" value="" style="vertical-align: -1px;"></div>
                    <div class="pic"><img id="contentBackPicImg" src="" alt="" width="1920" height="1080" />图片大小1920*1080</div>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="groupSubmit();">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
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
<script type="text/javascsript" src="${ctx}/res/js/common/excanvas.compiled.js"></script>

