<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->

<!--[if !IE]><!-->
<html lang="en" class="no-js"> <!--<![endif]-->

<!-- BEGIN HEAD -->

<head>

    <meta charset="utf-8"/>

    <title>Hiveview | 门户管理</title>

    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>

    <meta content="" name="description"/>

    <meta content="" name="author"/>

    <!-- BEGIN GLOBAL MANDATORY STYLES -->

    <link href="${ctx}/res/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

    <link href="${ctx}/res/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>

    <link href="${ctx}/res/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>



    <link href="${ctx}/res/css/style.css" rel="stylesheet" type="text/css"/>

    <link href="${ctx}/res/css/style-responsive.css" rel="stylesheet" type="text/css"/>

    <link href="${ctx}/res/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>

    <link href="${ctx}/res/css/uniform.default.css" rel="stylesheet" type="text/css"/>

    <!-- END GLOBAL MANDATORY STYLES -->

    <!-- BEGIN PAGE LEVEL STYLES -->
    <script src="${ctx}/res/js/jquery-1.10.1.min.js" type="text/javascript"></script>
    <script src="${ctx}/res/bootstrap/jquery.messager.js" type="text/javascript"></script>
    <link href="${ctx}/res/css/jquery.gritter.css" rel="stylesheet" type="text/css"/>

    <link href="${ctx}/res/css/daterangepicker.css" rel="stylesheet" type="text/css"/>

    <link href="${ctx}/res/css/fullcalendar.css" rel="stylesheet" type="text/css"/>

    <link href="${ctx}/res/css/jqvmap.css" rel="stylesheet" type="text/css" res="screen"/>

    <link href="${ctx}/res/css/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css" res="screen"/>

    <!-- END PAGE LEVEL STYLES -->

    <link rel="shortcut icon" href="${ctx}/res/image/favicon.ico"/>
    <style>
        .page-sidebar{
            position:fixed;
            top:42px;
            bottom:33px;
            /*height: 100%;*/
            overflow-y:auto;
            /*width:220px;*/
            overflow-x:hidden;
        }
        .navbar-fixed-top{
            background-color: RGB(61,61,61);
            height:42px;
        }
        .page-sidebar ul > li > a > .arrow:before{
            margin:0;
        }
        .footer{
            position:fixed;
            left:225px;
            bottom: 0;
            display:block;
            width:100%;
            background-color: rgb(61,61,61);
            text-indent:-432px;
            z-index: 1000;
        }
        .page-sidebar ul > li > a > .arrow:before{
            content: "\f105";
        }
        /*.page-sidebar ul > li > a{*/
            /*white-space: nowrap;*/
        /*}*/
        /*ul.page-sidebar-menu > li > a{*/
            /*padding: 10px 5px;*/
        /*}*/
        ul.page-sidebar-menu > li > a{
            padding-left:20px;
            padding-right:20px;
        }
    </style>
</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="page-header-fixed">

<!-- BEGIN HEADER -->

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
    <!-- BEGIN TOP NAVIGATION BAR -->

    <div class="navbar navbar-header">

        <!-- BEGIN LOGO -->

        <a class="brand" href="#">

            <img src="${ctx}/res/image/logo.png" alt="logo"/>

        </a>

        <!-- END LOGO -->

        <!-- BEGIN RESPONSIVE MENU TOGGLER -->

        <a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">

            <img src="${ctx}/res/image/menu-toggler.png" alt=""/>

        </a>

        <!-- END RESPONSIVE MENU TOGGLER -->

        <!-- BEGIN TOP NAVIGATION MENU -->



        <!-- END TOP NAVIGATION MENU -->

    </div>

</div>

<!-- END TOP NAVIGATION BAR -->

</div>

<!-- END HEADER -->

<!-- BEGIN CONTAINER -->

<div class="page-container">

    <!-- BEGIN SIDEBAR -->

    <div class="page-sidebar" id="sidebarcontainer">

        <!-- BEGIN SIDEBAR MENU -->

        <ul class="page-sidebar-menu" id="sidebar" style="width:225px;">
            <li>
                <a href="${ctx}" target='_self' id="hit">
                    <i class="icon-home"></i>
                    <span class="title">Launcher4.0 后台管理</span>
                    <span class="arrow"></span>
                </a>
            </li>
            <li>
                <a href="${ctx}/templet/list">
                    <i class="icon-cogs"></i>
                    <span class="title">Launcher区域模版配置</span>
                    <span class="arrow"></span>
                </a>
            </li>
            <li>
                <a href="${ctx}/areaController/list">
                    <i class="icon-cogs"></i>
                    <span class="title">专区管理</span>
                    <span class="arrow"></span>
                </a>
            </li>
            <%--<li>
                <a href="${ctx}/portal/list">
                    <i class="icon-film"></i>
                    <span class="title">首屏大图推荐管理</span>
                    <span class="arrow "></span>
                </a>
            </li>
            <li>
                <a href="${ctx}/acrossVertical/list">
                    <i class="icon-bookmark-empty"></i>
                    <span class="title">首屏横竖图推荐管理</span>
                    <span class="arrow "></span>
                </a>
            </li>--%>
            <%--<li>
                <a href="${ctx}/portalBeanCurdList/list">
                    <i class="icon-table"></i>
                    <span class="title">豆腐块列表管理</span>
                    <span class="arrow "></span>
                </a>
            </li>
            <li>
                <a href="${ctx}/portalBeanCurdEditListController/list">
                    <i class="icon-briefcase"></i>
                    <span class="title">豆腐块内容管理</span>
                    <span class="arrow "></span>
                </a>
            </li>--%>
            <li>
                <a href="${ctx}/tab/list">
                    <i class="icon-leaf"></i>
                    <span class="title">Tab管理</span>
                    <span class="arrow "></span>
                </a>
            </li>
            <li>
                <a href="${ctx}/PortalDataGroupController/list">
                    <i class="icon-briefcase"></i>
                    <span class="title">Data Group管理</span>
                    <span class="arrow "></span>
                </a>
            </li>
            <li>
                <a href="${ctx}/customRecomContentList/list">
                    <i class="icon-gift"></i>
                    <span class="title">Group管理</span>
                    <span class="arrow "></span>
                </a>
            </li>
            <li>
                <a href="${ctx}/customRecomTempleteList/list">
                    <i class="icon-sitemap"></i>
                    <span class="title">推荐位模板管理</span>
                    <span class="arrow "></span>
                </a>
            </li>
            <li>
                <a href="${ctx}/customRecomBackupsList/list">
                    <i class="icon-book"></i>
                    <span class="title">Group备份管理</span>
                    <span class="arrow "></span>
                </a>
            </li>
            <%--<li>
                <a href="${ctx}/fiexdRecomList/list">
                    <i class="icon-folder-open"></i>
                    <span class="title">固定推荐位列表管理</span>
                    <span class="arrow "></span>
                </a>
            </li>--%>
            <li>
                <a href="${ctx}/apk/list">
                    <i class="icon-user"></i>
                    <span class="title">定制APK管理</span>
                    <span class="arrow "></span>
                </a>
            </li>
            <li>
                <a href="${ctx}/logoManage/list">
                    <i class="icon-th"></i>
                    <span class="title">牌照LOGO管理</span>
                    <span class="arrow "></span>
                </a>
            </li>
            <li>
                <a href="${ctx}/bigPics/list">
                    <i class="icon-file-text"></i>
                    <span class="title">Tab背景/全屏大图管理</span>
                    <span class="arrow "></span>
                </a>
            </li>
            <li>
                <a href="${ctx}/start/list">
                    <i class="icon-map-marker"></i>
                    <span class="title">开机启动指令管理</span>
                    <span class="arrow "></span>
                </a>
            </li>
            <li>
                <a href="${ctx}/PortalNotStartInstruction/list">
                    <i class="icon-map-marker"></i>
                    <span class="title">定制APK启动指令管理</span>
                    <span class="arrow "></span>
                </a>
            </li>
            <li>
                <a href="${ctx}/appIcon/list">
                    <i class=" icon-filter"></i>
                    <span class="title">桌面图标过滤</span>
                    <span class="arrow "></span>
                </a>
            </li>
            <li>
                <a href="${ctx}/vipLogo/list">
                    <i class="icon-heart"></i>
                    <span class="title">vip图标管理</span>
                    <span class="arrow "></span>
                </a>
            </li>
            <li>
                <a href="${ctx}/jumpInstruction/list">
                    <i class="icon-hand-right"></i>
                    <span class="title">推荐位跳转指令管理</span>
                    <span class="arrow "></span>
                </a>
            </li>
            <li>
                <a href="${ctx}/macsn/list">
                    <i class="icon-bar-chart"></i>
                    <span class="title">MACSN名单组管理</span>
                    <span class="arrow "></span>
                </a>
            </li>
        </ul>

        <!-- END SIDEBAR MENU -->

    </div>

    <!-- END SIDEBAR -->

    <!-- BEGIN PAGE -->

    <div class="page-content">

        <!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->

        <div id="portlet-config" class="modal hide">

            <div class="modal-header">

                <button data-dismiss="modal" class="close" type="button"></button>

                <h3>Widget Settings</h3>

            </div>

            <div class="modal-body">

                Widget settings form goes here

            </div>

        </div>

        <!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->

        <!-- BEGIN PAGE CONTAINER-->
        <sitemesh:write property='body'/>

        <!-- END PAGE CONTAINER-->

    </div>

    <!-- END PAGE -->

</div>

<!-- END CONTAINER -->

<!-- BEGIN FOOTER -->

<div class="footer">

    <div class="text-center" >

        2017 &copy; 版权所属 <a href="http://www.hiveview.com/" title="家视天下" target="_blank">家视天下</a>

    </div>

</div>

<%--<iframe src ="${ctx}" width="100%" height="100%" style="position:absolute;top:0px;left:0px;z-index:1000">--%>

<%--</iframe>--%>


<!-- END FOOTER -->
<%--<iframe src="${ctx}/templet/list" frameborder="0">--%>

<%--</iframe>--%>


<!-- BEGIN PAGE LEVEL SCRIPTS -->

<script src="${ctx}/res/js/app.js" type="text/javascript"></script>

<script src="${ctx}/res/js/index.js" type="text/javascript"></script>

<!-- END PAGE LEVEL SCRIPTS -->
<script>
    var ul = document.getElementById("sidebar");
    var lis = ul.getElementsByTagName("li");
//    var div = document.getElementById('sidebarcontainer');
    var sidebar = document.getElementById('sidebarcontainer');
    var storage = window.localStorage;
    sidebar.onscroll = function(){
        storage.scroll = this.scrollTop;
    };


    for(var i =0;i<lis.length;i++){
        lis[i].idx = i;
        lis[i].onclick = function(e){
          for(var j=0;j<lis.length;j++){
              lis[j].setAttribute('class','');
          }
            storage.id = this.idx;
            lis[this.idx].setAttribute('class','start active');
        };
    };
        lis[storage.id].setAttribute('class','start active');
        sidebar.scrollTop = storage.scroll;



        //点击页面刷新按钮或关闭再打开页面,左侧导航栏第一个选线为默认状态,页面为默认状态
        window.onload = function() {
            storage.id = 0;
//                $("html,body").animate({"scrollTop":top});
        }


    //左侧固定导航部分 滚动条位置调整(页面刷新时,滚动条在顶部;当滚动条滚动时,选项被点击滚动条在上次滚动到的位置)
        if( storage.id == 0){
            sidebar.scrollTop = 0;
            $("html,body").animate({"scrollTop":top});
        }else{
            sidebar.scrollTop = storage.scroll;
        }

    </script>

<!-- END JAVASCRIPTS -->

</body>

<!-- END BODY -->

</html>







