<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<link href="${ctx}/res/bootstrap/bootstrap-table.css" rel="stylesheet">
<%--<link href="${ctx}/res/bootstrap/bootstrap-dialog.min.css" rel="stylesheet">--%>
<link rel="stylesheet" type="text/css" href="${ctx}/res/js/dist/jquery.gridster.min.css">
<link href="${ctx}/res/css/bootstrap-modal.css" rel="stylesheet">
<script type="text/javascript"src="${ctx}/res/js/dist/jquery.gridster.js" charset="utf-8"></script>
<script src="${ctx}/res/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/res/bootstrap/bootstrap-table.js" type="text/javascript"></script>
<script src="${ctx}/res/bootstrap/bootstrap-table-zh-CN.js"></script>
<script src="${ctx}/res/js/jquery.flot.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/res/js/dist/drawTables.js"></script>
<script language="JavaScript">
    var ctx = '${ctx}';
</script>
<style type="text/css">
    #addTempletTabModal,#showModal{
        width: 800px;
    }
    #showModal{
        height:550px;
        overflow:auto;
        [;top:350px;]
    }
    #tabManageModal{
        width: 800px;
    }
    #areaModal{
        width: 800px;
        margin-top: -130px;
        margin-bottom: 15px;
    }
    #selectFixedRecom{
        margin-bottom: 7px;
    }
    .fixed-table-toolbar{
        height:0px;
    }
    .keep-open{
        height:0px;
    }
    .pull-right{
        height:0px;!important;
    }
    .fixed-table-toolbar .btn-group > .btn-group:last-child > .btn {
        top: -51px;
    }
    .modal .fade in{
        left: 40%;
        margin-top: -241px;
        display: block;
    }
    #addTempletTabModal{
        position: relative;
        left: 40%;
        [;top:300px;]
    }
    #areaModal,#tabManageModal,#showModal,#addTempletTabModal{
        display: none;
    }
    #tabManageModal{
        [;top:350px;]
    }
    .modal-header{
        border-bottom:  none;
    }
    #controllerType_name,#controllerType,.t_input{
        vertical-align: -1px!important;
    }
    .fixed-table-body{
        width:100%;
    }
    .portlet{
        margin-bottom:47px;
    }

    #modalbody{
        overflow:auto;
    }
    #previewModal ul li{
        list-style: none;
    }
    /*.portlet-body{*/
        /*max-height: 559px;*/
    /*}*/
    .table-striped tbody>tr:nth-child(odd)>td, .table-striped tbody>tr:nth-child(odd)>th{
        background:none;
    }
    .table-hover tbody tr:hover>td{
        background:none;
    }
</style>
<script src="${ctx}/res/js/launcher/launcher.js" type="text/javascript"></script>
<script src="${ctx}/res/js/bootstrap-modal.js" type="text/javascript"></script>
<script src="${ctx}/res/js/bootstrap-modalmanager.js" type="text/javascript"></script>
<script src="${ctx}/res/js/plugin/ajaxupload.3.6.js" type="text/javascript"></script>
<script src="${ctx}/res/js/jquery.flot.resize.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/res/js/dist/LauncherGridster.js"></script>
<script src="${ctx}/res/js/plugin/uploadify/jquery.uploadify.js" type="text/javascript"></script>
<div class="container-fluid">
    <!-- BEGIN PAGE HEADER-->
    <div class="row-fluid">
        <div class="span12">
            <!-- BEGIN PAGE TITLE & BREADCRUMB-->
            <h3 class="page-title">
                Launcher区域模板配置
                <small></small>
            </h3>
            <ul class="breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="${ctx}">Home</a>
                    <i class="icon-angle-right"></i>
                </li>
                <li><a href="#">Launcher区域模板配置</a></li>
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
                    <div class="clearfix">
                        <div class="btn-group">
                            <button id="btn_addTemplet" class="btn yellow" onclick="addTemplet()">
                                添加
                            </button>
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
<!-- 添加templet框（Modal） -->
<div class="modal fade" id="addTempletModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 800px;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="templetclear_diaglog();"></button>
                <h4 class="modal-title" id="myModalLabel">模板内容编辑</h4>
            </div>
            <div class="modal-body">
                <input id="txt_id" type="hidden">
                <div class="form-group">
                    <label for="txt_name" style="display: inline-block;">
                        <span style="color:red;">*</span>模板名称：&nbsp;&nbsp;
                        <input type="text" name="txt_name" class="form-control" id="txt_name" placeholder="名称" style="vertical-align: 1px;">
                    </label>
                    <label for="txt_logoId" style="display: inline-block;">
                        牌照LOGO：
                        <select name="txt_logoId" id="txt_logoId" style="vertical-align: 1px;"></select>
                    </label>
                    <label for="txt_autoId" style="display: inline-block;">
                        开机启动：&nbsp;&nbsp;&nbsp;
                        <select name="txt_autoId" id="txt_autoId" style="vertical-align: 1px;"></select>
                    </label>
                    <%--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;
                    <label for="txt_blockId" style="display: inline-block;">豆腐块列表：</label>
                    <select name="txt_blockId" id="txt_blockId" style="vertical-align: 1px;"></select>--%>
                </div>
                <%--<div class="form-group">
                    <label for="txt_bigImageId" style="display: inline-block;">首屏大图推荐位：</label>
                    <select name="txt_bigImageId" id="txt_bigImageId" style="vertical-align: 1px;"></select>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <label for="txt_smallImageId" style="display: inline-block;">首屏横竖图推荐位：</label>
                    <select name="txt_smallImageId" id="txt_smallImageId" style="vertical-align: 1px;"></select>
                </div><hr>
                <div class="form-group">
                    <label for="txt_isHide" style="display: inline-block;">下滑推荐位列表：</label>
                    <select name="txt_isHihe" id="txt_isHide" style="vertical-align: 1px;">
                        <option value="1" selected="selected">展示</option>
                        <option value="0">隐藏</option>
                    </select>
                </div>--%>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="templet_submit();">保存</button>
                <button type="button" class="btn btn-default" onclick="templetclear_diaglog();">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!-- 添加Tab框（Modal） -->
<div class="modal fade" id="addTempletTabModal" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="templetTabclear_diaglog();"></button>
                <h4 class="modal-title" id="ModalLabel">选择导航栏模板</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label>
                        <span>Tab名称：</span><input type="text" name="txt_tabName" id="txt_tabName">
                        <button type="button" class="btn btn-default" onclick="selectTabByTabName()" style="margin-bottom: 10px;">查询</button>
                    </label>
                </div>
                <table class="table table-striped table-bordered table-hover dataTable" id="sample_3"
                       aria-describedby="sample_3_info">
                </table>
                <div class="row-fluid">
                    <div class="span6">
                        <div class="dataTables_info" id="sample_3_info"></div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onClick="addTabGroup_submit();">保存</button>
                <button type="button" class="btn btn-default" onClick="templetTabclear_diaglog();">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!-- 关联推荐位列表（Modal） -->
<div class="modal fade" id="tabManageModal" tabindex="-1" role="dialog" aria-labelledby="myModal" aria-hidden="true" style="position:relative;left: 40%;
    margin-top: 217.5px;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="tabManageclear_diaglog();"></button>
                <h4 class="modal-title" id="Modal">关联导航栏</h4>
            </div>
            <div class="clearfix">
                <div class="btn-group">
                    <button id="btn_addGroup" class="btn yellow" onclick="addTempletTab()">
                        添加
                    </button>
                </div>
            </div>
            <input type="hidden" id="txt_belongTempletId">
            <table class="table table-striped table-bordered table-hover dataTable" id="sample_2"
                   aria-describedby="sample_2_info">
            </table>
            <div class="row-fluid">
                <div class="span6">
                    <div class="dataTables_info" id="sample_2_info"></div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="tabManageclear_diaglog();">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<%--<!-- 选择Tab模板（Modal） -->
<div class="modal fade" id="fixedRecomModal" tabindex="-1" role="dialog" aria-labelledby="myModal" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="fixedRecomclear_diaglog();"></button>
                <h4 class="modal-title" id="myModal">选择推荐位列表模板</h4>
            </div>
            <table class="table table-striped table-bordered table-hover dataTable" id="sample_3"
                   aria-describedby="sample_3_info">
            </table>
            <div class="row-fluid">
                <div class="span6">
                    <div class="dataTables_info" id="sample_3_info"></div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="fixedRecomclear_diaglog();">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>--%>

<!-- 显示查看（Modal） -->
<div class="modal fade" id="showModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 800px;position: relative;left: 40%;margin-top:200px;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="showClear_diaglog();"></button>
                <h4 class="modal-title" id="showLabel">查看模板</h4>
            </div>
            <div class="modal-body">
                <input id="txtShow_id" type="hidden">
                <div class="form-group">
                    模板名称：
                    <span name="txtShow_name" id="txtShow_name"></span>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;
                    牌照LOGO：
                    <span name="txtShow_logoId" id="txtShow_logoId"></span>
                </div>
                <hr>
                <div class="form-group">
                    开机启动：
                    <span name="txtShow_autoId" id="txtShow_autoId"></span>
                    <%--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;
                    豆腐块列表：
                    <span name="txtShow_blockId" id="txtShow_blockId"></span>--%>
                </div>
                <hr>
                <%--<div class="form-group">
                    首屏大图推荐位：
                    <span name="txtShow_bigImageId" id="txtShow_bigImageId"></span>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    首屏横竖图推荐位：
                    <span name="txtShow_smallImageId" id="txtShow_smallImageId"></span>
                </div><hr>--%>
                <div class="clearfix">
                    <div class="btn-group">
                        <label>导航栏列表：</label>
                    </div>
                </div>
                <table class="table table-striped table-bordered table-hover dataTable" id="sample_show"
                       aria-describedby="sample_show_info">
                </table><%--<hr>
                <div class="form-group">
                    下滑推荐位列表：
                    <span name="txtShow_isHihe" id="txtShow_isHihe"></span>
                </div>--%>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="showClear_diaglog();">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!-- 区域配置（Modal） -->
<div class="modal fade" id="areaModal" tabindex="-999" role="dialog" aria-labelledby="areaModal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="clear_submit();"></button>
                <h4 class="modal-title">区域配置</h4>
            </div>
            <div class="modal-body">
                <input type="hidden" id="area_id" />
                <div class="form-group">
                    <label for="controllerType" style="display: inline-block;">控制类型：</label>
                    <select  name="txt_controllerType" id="controllerType" onchange="controllerOnChange();">
                        <option value="2" selected="true">MACSN</option>
                        <option value="5">硬件型号+IP地址</option>
                        <option value="4">rom版本</option>
                        <option value="3">硬件型号</option>
                        <option value="1" >IP地址</option>
                    </select>
                    <hr/>
                    <div id = comboxControllerType>
                        <label for="controllerType_name" style="display: inline-block;">名称：</label>
                        <input type="text" id="controllerType_name" style="width: 150px;" value="" style="vertical-align: 1px;"/><a id="searchControllerByName" href="javascript:searchControllerByName();" class="btn btn-default btn-xs" style="vertical-align: 0px;">查询</a>
                    <table>
                        <tbody>
                        <tr>
                            <td colspan="2">
                                <p>所属区域：</p>
                                <div
                                        style="margin_top: 17px; width: 250px; height: 160px; overflow: auto; visibility: visible;"
                                        id="area_tds"></div>
                            </td>
                            <td style="text-align: center;"><input type="button" onclick="add();" value="<<" style=" text-align:center;"/>
                            </td>
                            <td colspan="2">
                                <p style="color: gray;"></p>
                                <select multiple="multiple" id="area_tagId" style="margin: 17px; width: 200px; height: 160px;">
                            </select>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                    <div id = inputControllerType style="overflow: hidden;display:none;">
                        <div style="float: left;width:250px;">
                            <p id = "area_rom_title">所属区域：</p>
                            <table id="entrance_area_rom_table" ></table>
                        </div>
                        <div style="float: left">
                            <input type="text" id="addrom_name" style="width: 150px;color:gray" value="PB.30.32.01" onBlur="javascript:romNameOnBlur();"
                                   OnFocus="javascript:romNameOnFocus();"/>
                            <a id="addRomName"  href="javascript:addIntoRomNameList();" class="btn btn-default" style="vertical-align:4px;">添加</a>
                        </div>
                    </div>
                    <div id = doubleCombControllerType style="overflow: hidden;display:none;">
                        <div style="float: left;width:380px;">
                            <label for="hardware_type_name" style="display:inline-block;">名称：</label><input type="text" id="hardware_type_name" style="width: 150px;vertical-align: -1px;" value="" class="t_input" />
                            <a id="searchHardwareByName" href="javascript:searchHardwareByName();" class="btn btn-default">查询</a>
                            <table class="tableE">
                                <tbody>
                                <tr>
                                    <td colspan="2">
                                        <p style="color: gray;">&nbsp;&nbsp;所属区域</p>

                                        <div
                                                style="margin_top: 17px; width: 158px; height: 160px; overflow: auto; visibility: visible;"
                                                id="dou_hardware_tds"></div>

                                    </td>
                                    <td style="text-align: center;">
                                        <input type="button" onclick="addDouble1();" value="<<" style=" text-align:center;"/>
                                    </td>
                                    <td colspan="2">
                                        <p style="color: gray;">新增硬件类型：</p> <select multiple="multiple"
                                                                                    id="dou_hardware_tagId" style="margin: 17px; width: 158px; height: 160px;">
                                        <option value="1">城市1</option>
                                    </select>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div style="float: left;width:380px;">
                            <label for="ipaddress_type_name" style="display: inline-block;">名称：</label><input type="text" id="ipaddress_type_name" style="width: 150px;vertical-align: -1px;" value="" class="t_input" />
                            <a id="searchIpaddressByName" href="javascript:searchIpaddressByName();" class="btn btn-default">查询</a>
                            <table class="tableF">
                                <tbody>
                                <tr>
                                    <td colspan="2">
                                        <p style="color: gray;">&nbsp;&nbsp;所属区域</p>

                                        <div
                                                style="margin_top: 17px; width: 158px; height: 160px; overflow: auto; visibility: visible;"
                                                id="dou_ip_tds"></div>

                                    </td>
                                    <td style="text-align: center;">
                                        <input type="button" onclick="addDouble2();" value="<<" style=" text-align:center;"/>
                                    </td>
                                    <td colspan="2">
                                        <p style="color: gray;">新增城市：</p> <select multiple="multiple"
                                                                                  id="dou_ip_tagId" style="margin: 17px; width: 158px; height: 160px;">
                                        <option value="1">城市1</option>
                                    </select>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>

                </div><!-- /.modal-content -->
            </div>
            <div class="modal-footer" >
                <button type="button" class="btn btn-default"  onclick="data_submit();" id="">保存</button>
                <button type="button" class="btn btn-default" onclick="clear_submit();">取消</button>
            </div>
        </div>
    </div><!-- /.modal -->
</div>

<!-- 模态框（Modal） -->
<div class="modal fade hide" id="previewModal" tabindex="999" role="dialog" aria-labelledby="customRecomTempleteModal" aria-hidden="true" style="width:800px;left:50%;">
    <div class="modal-dialog" style="width: 800px;">
        <div class="modal-content" style="width: 800px;">
            <div class="modal-header" style="border-bottom:1px solid #ccc">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" ></button>
                <h4 class="modal-title" >Tab页预览</h4>
            </div>
            <div class="modal-body" style="overflow: auto;width: 770px;max-height:436px;position:relative;" id="modalbody">
                <%--<div class="canvas-container" style="position:absolute;left:10px;top:65px;height: 360px;">--%>
                <%--<div class="gridster" id="gridsterLayoutFirst" style="width:1200px; height:360px; padding:0px 10px 10px 0px;position:relative;">--%>
                    <%--<span style="color:#000;font-size: 16px;font-weight: bold;position:absolute;top:0">积木推荐位模板名称</span>--%>
                    <%--<ul style="width: 0px;position: absolute ;top:35px;">--%>

                    <%--</ul>--%>
                    <%--<canvas id="a_canvas" style="background:grey;position:absolute;top:35px;left: 0;">--%>

                    <%--</canvas>--%>
                <%--</div>--%>
                <%--</div>--%>
            </div>
        </div><!-- /.modal-content -->
        <div class="modal-footer" >
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        </div>
    </div><!-- /.modal -->
</div>


<script type="text/template" id="template">
    <div class="gridster" id="#divId#" style="width:1200px; height:400px; padding:0px 10px 10px 0px;position:relative;overflow-x: auto;">
    <span style="color:#000;font-size: 16px;font-weight: bold;position:absolute;top:#span#">#title#</span>
    <ul style="width: 0px;position: absolute ;top:#ul#;" id="#id#">

    </ul>
    <canvas id="#canvasId#" style="background:grey;position:absolute;top:#canvas#;left: 0;">

    </canvas>
        <p style="position: absolute;top:35px;left:640px;width: 0px;background:red;height:348px;border-right:1px solid #000;"></p>
    </div>
</script>