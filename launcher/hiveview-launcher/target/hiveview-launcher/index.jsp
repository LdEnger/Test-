<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<style>
    .welcome{
        padding-top:50px;
        position:relative;
    }
    .welcome span{
        width: 52px;
        display:block;
        position: absolute;
        top:22px;
        left:50%;
        margin-left: -174px;
    }
    .welcome p{
        position: absolute;
        top:58px;
        left:50%;
        margin-left: -174px;
        font-size: 45px;
        color:rgb(231,52,40);
    }
    .welcome i{
        position: absolute;
        top:97px;
        left:50%;
        margin-left: -50px;
        width: 180px;
        display:block;
        text-align: center;
        color:#5c5c5c;
        background-color:rgb(231,52,40);
        -webkit-transform: skewX(30deg);
        -moz-transform:skewX(30deg);
        transform:skewX(30deg);
    }
</style>
<div class="container-fluid">

    <!-- BEGIN PAGE HEADER-->

    <div class="row-fluid">

        <div class="span12">


            <!-- BEGIN PAGE TITLE & BREADCRUMB-->

            <h3 class="page-title">

                Launcher4.0
                <small>后台管理</small>

            </h3>

            <ul class="breadcrumb">

                <li>

                    <i class="icon-home"></i>

                    <a href="${ctx}">Home</a>

                    <i class="icon-angle-right"></i>

                </li>

                <li><a href="#">系统首页</a></li>

            </ul>

            <!-- END PAGE TITLE & BREADCRUMB-->

        </div>

    </div>

    <!-- END PAGE HEADER-->

    <div class="welcome">

        <span>欢迎进入</span>
        <p>Welcome >>>></p>
        <i>我是后台管理信息系统</i>
    </div>
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

<!-- BEGIN CORE PLUGINS-->

<script src="${ctx}/res/js/jquery-1.10.1.min.js" type="text/javascript"></script>

<script src="${ctx}/res/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>

<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->

<script src="${ctx}/res/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>

<script src="${ctx}/res/js/bootstrap.min.js" type="text/javascript"></script>


<script src="${ctx}/res/js/jquery.slimscroll.min.js" type="text/javascript"></script>

<script src="${ctx}/res/js/jquery.blockui.min.js" type="text/javascript"></script>

<script src="${ctx}/res/js/jquery.cookie.min.js" type="text/javascript"></script>

<script src="${ctx}/res/js/jquery.uniform.min.js" type="text/javascript"></script>

<!-- END CORE PLUGINS -->

<!-- BEGIN PAGE LEVEL PLUGINS-->

<script src="${ctx}/res/js/jquery.vmap.js" type="text/javascript"></script>

<script src="${ctx}/res/js/jquery.vmap.russia.js" type="text/javascript"></script>

<script src="${ctx}/res/js/jquery.vmap.world.js" type="text/javascript"></script>

<script src="${ctx}/res/js/jquery.vmap.europe.js" type="text/javascript"></script>

<script src="${ctx}/res/js/jquery.vmap.germany.js" type="text/javascript"></script>

<script src="${ctx}/res/js/jquery.vmap.usa.js" type="text/javascript"></script>

<script src="${ctx}/res/js/jquery.vmap.sampledata.js" type="text/javascript"></script>

<script src="${ctx}/res/js/jquery.flot.js" type="text/javascript"></script>

<script src="${ctx}/res/js/jquery.flot.resize.js" type="text/javascript"></script>

<script src="${ctx}/res/js/jquery.pulsate.min.js" type="text/javascript"></script>

<script src="${ctx}/res/js/date.js" type="text/javascript"></script>

<script src="${ctx}/res/js/daterangepicker.js" type="text/javascript"></script>

<script src="${ctx}/res/js/jquery.gritter.js" type="text/javascript"></script>

<script src="${ctx}/res/js/fullcalendar.min.js" type="text/javascript"></script>

<script src="${ctx}/res/js/jquery.easy-pie-chart.js" type="text/javascript"></script>

<script src="${ctx}/res/js/jquery.sparkline.min.js" type="text/javascript"></script>

<!-- END PAGE LEVEL PLUGINS -->