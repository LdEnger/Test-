<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<script language="JavaScript">
    var ctx = '${ctx}';
</script>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Expires" content="0"/>
    <meta http-equiv="Cache-Control" content="no-cache"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <title>Launcher 4.0</title>
    <style>
        body {
            margin: 0;
            font-size: 13px;
            line-height: 150%;
            font-family: Consolas, Monospace;
        }

        a {
            color: #00F;
            text-decoration: none;
            padding: 10px;
        }

        input {
            font-size: 13px;
            font-family: Consolas, Courier;
        }

        #ajaxloading {
            right: 0;
            top: 0;
            width: 150px;
            position: fixed;
            padding: 6px;
            text-align: center;
            font-weight: bold;
            background: #900;
            color: #FFF;
        }

        #title {
            background: #FFF;
            color: #000;
            font-size: 15px;
            padding: 25px;
        }

        #menu {
            border: 1px solid #EEE;
            width: 90%;
            margin: 0px auto;
        }

        #menu li {
            border: 1px solid #EEE;
            padding: 10px 10px 10px 10px;
        }

        .api_block {
            border: 1px solid #eee;
            background: #fff;
            margin: 0px auto;
            width: 90%;
        }

        .api_result, .api_params, .api_name, .api_method, .api_validate,
        .api_url, .api_desc, .api_params {
            background: #ececec;
            padding: 3px;
            margin-bottom: 5px;
            color: #000;
        }

        .api_name {
            font-size: 14px;
            background: url(${ctx}/res/image/menu.jpg) repeat;
            color: #fff;
        }

        .notes {
            color: red;
            font-style: italic;
            font-size: 12px;
        }

        .resp_title {
            color: #F00;
        }

        .return_area {
            font-size: 13px;
            font-family: Consolas, Monospace;
        }

        @charset "utf-8";
        /* CSS Document */
        .ObjectBrace {
            color: #00AA00;
            font-weight: bold;
        }

        .ArrayBrace {
            color: #0033FF;
            font-weight: bold;
        }

        .PropertyName {
            color: #000000;
            font-weight: bold;
        }

        .String {
            color: #007766;
        }

        .Number {
            color: #FF0000;
        }

        .Boolean {
            color: #0000FF;
        }

        .Function {
            color: #AA6633;
            text-decoration: italic;
        }

        .Null {
            color: #0000FF;
        }

        .Comma {
            color: #000000;
            font-weight: bold;
        }

        PRE.CodeContainer {
            margin-top: 0px;
            margin-bottom: 0px;
        }

        PRE.CodeContainer img {
            cursor: pointer;
            border: none;
            margin-bottom: -1px;
        }

        A.OtherToolsLink {
            color: #555;
            text-decoration: none;
        }

        A.OtherToolsLink:hover {
            text-decoration: underline;
        }

        #RespCode li {
            border: 1px solid #EEE;
            padding: 10px 10px 10px 10px;
            width: 50%;
            list-style-type: none;
        }

        .versiontable {
            border-collapse: collapse;
            border: 1px solid #DDDDDD;
        }

        .versiontable td {
            border: 1px solid #DDDDDD;
            line-height: 18px;
        }
    </style>
    <script type="text/javascript" src="${ctx}/res/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="${ctx}/res/js/format.js"></script>
    <script>
        String.prototype.replaceAll = function (reallyDo, replaceWith, ignoreCase) {
            if (!RegExp.prototype.isPrototypeOf(reallyDo)) {
                return this.replace(
                    new RegExp(reallyDo, (ignoreCase ? "gi" : "g")),
                    replaceWith);
            } else {
                return this.replace(reallyDo, replaceWith);
            }
        }

        $(function () {
            $('#ajaxloading').ajaxStart(function () {
                $(this).show();
            });
            $('#ajaxloading').ajaxStop(function () {
                $(this).hide();
            });

            $('.return_type').click(function () {
                $(this).parent().parent().find('#return_type').html($(this).val());
            });

            $('.api_test_btn').click(function() {
                var div_block = $(this).parent().parent();
                var return_type = div_block.find('#return_type').html();
                var api_full_name = div_block.find('#api_name').html();
                var api_method = div_block.find('#api_method').html();
                var api_validate = div_block.find('#api_validate').html();
                var params = '';
                var MyCookie =  document.cookie;
                api_full_name = api_full_name+"/";

                div_block.find('#params_list').find('input').each(function(i,v) {
                    if(i==0){
                        api_full_name+=$(this).val();
                    }else{
                        api_full_name+="/"+$(this).val();
                    }
                })


                var host = window.location.host;
                if(host.indexOf('localhost') == 0){
                    host += "";
                }
                if(host.indexOf('211.103.138.119') == 0){
                    host += "";
                }

                var pathname = window.location.pathname;

                if(pathname == "/dev/api/doc/"){
                    host += "/dev";
                }

                if(pathname == "/pro/api/doc/"){
                    host +=  "/pro";
                }
                var lastStr = api_full_name.substring(api_full_name.length-1,api_full_name.length);
                if("/"==lastStr){
                    api_full_name = api_full_name.substring(0,api_full_name.length-1);
                }
                api_full_name  = "http://" + host + api_full_name + ".json";
                if(api_full_name.indexOf("api/open/special/video/videoList")>0){
                    var nn1=document.getElementById("nn1").value;
                    var nn2=document.getElementById("nn2").value;
                    api_full_name+="?equipmentNo="+nn1+"&apkBagName="+nn2;
                }
                console.log(api_full_name);
                //params = params.substr(0, params.length - 1);
                $.ajax({
                    //data:params,
                    type:api_method,
                    dataType:'json',
                    url: api_full_name,
                    success: function(resp){
                        var json = Process(JSON.stringify(resp));
                        div_block.find('.return_area').html(json);
                    },error:function(){
                        alert('请求失败,请稍后再试!');
                    }
                })
            });
            $('.api_test_btn_').click(function () {
                var div_block = $(this).parent().parent();
                var return_type = div_block.find('#return_type').html();
                var api_full_name = div_block.find('#api_name').html();
                var api_method = div_block.find('#api_method').html();
                var api_validate = div_block.find('#api_validate').html();
                var params = '';
                var MyCookie = document.cookie;
                div_block.find('#params_list').find('input').each(function () {
                    //params += $(this).attr('name') + '=' + $(this).val() + '&';
                    api_full_name += "-" + $(this).val();
                })

                var host = window.location.host;

                if (host.indexOf('localhost') == 0) {
                    host += "/";
                }

                var pathname = window.location.pathname;

                if (pathname == "/dev/api/doc/index_cloud.html") {
                    host += "/dev";
                }

                if (pathname == "/pro/api/doc/index_cloud.html") {
                    host += "/pro";
                }
                //host="api.newapi.pthv.gitv.tv";
                api_full_name = "http://" + host + api_full_name + ".json";

                //为了getTvList 中增加额外的？参数设计的
                if (api_full_name.indexOf("/api/open/vip/live/getTvList") > 0) {
                    debugger;
                    var s_ = api_full_name.lastIndexOf('-');
                    var temp = api_full_name.substring(0, s_);
                    temp = temp.substring(0, temp.lastIndexOf('-')) + ".json";
                    temp += "?random=" + $("#liveTvRandom").val() + "&sign=" + $("#liveTvSign").val();
                    api_full_name = temp;
                    console.log(api_full_name)
                }
                //params = params.substr(0, params.length - 1);
                $.ajax({
                    //data:params,
                    type: api_method,
                    dataType: 'json',
                    url: api_full_name,
                    success: function (resp) {
                        var json = Process(JSON.stringify(resp));
                        div_block.find('.return_area').html(json);
                    },
                    error: function () {
                        alert('请求失败,请稍后再试!');
                    }
                })
            });

            $('.devicecheck_btn').click(
                function () {
                    var div_block = $(this).parent().parent();
                    var return_type = div_block.find('#return_type').html();
                    var host = window.location.host;
                    if (host.indexOf('localhost') == 0) {
                        host += "/";
                    }
                    var pathname = window.location.pathname;

                    if (pathname == "/dev/api/doc/index_cloud.html") {
                        host += "/dev";
                    }

                    if (pathname == "/pro/api/doc/index_cloud.html") {
                        host += "/pro";
                    }
                    var api_full_name = "http://" + host
                        + "/api/sys/deviceCheck/1.2.json"

                    var api_method = div_block.find('#api_method').html();
                    var api_validate = div_block.find('#api_validate').html();
                    var params = '{';
                    div_block.find('#params_list').find('input').each(
                        function () {
                            params += $(this).attr('name') + ':' + "\""
                                + $(this).val() + "\"" + ',';
                        })

                    params = params.substr(0, params.length - 1) + '}';
                    var obj = eval('(' + params + ')');
                    $.ajax({
                        data: obj,
                        type: "post",
                        url: api_full_name,
                        dataType: 'json',
                        success: function (resp) {
                            var json = Process(JSON.stringify(resp));
                            div_block.find('.return_area').html(json);
                        },
                        error: function () {
                            alert('请求失败,请稍后再试!');
                        }
                    })
                });

            $('.post_btn').click(
                function () {
                    var div_block = $(this).parent().parent();
                    var return_type = div_block.find('#return_type').html();
                    var host = window.location.host;
                    if (host.indexOf('localhost') == 0) {
                        host += "/";
                    }

                    var pathname = window.location.pathname;

                    if (pathname == "/dev/api/doc/index_cloud.html") {
                        host += "/dev";
                    }

                    if (pathname == "/pro/api/doc/index_cloud.html") {
                        host += "/pro";
                    }
                    var api_full_name = div_block.find('#api_name').html();
                    var api_method = div_block.find('#api_method').html();
                    var api_validate = div_block.find('#api_validate').html();
                    var params = '{';
                    div_block.find('#params_list').find('input').each(
                        function () {
                            params += $(this).attr('name') + ':' + "\""
                                + $(this).val() + "\"" + ',';
                        })

                    //host="api.newapi.pthv.gitv.tv";
                    api_full_name = "http://" + host + api_full_name + ".json";
                    params = params.substr(0, params.length - 1) + '}';
                    var obj = eval('(' + params + ')');
                    $.ajax({
                        data: obj,
                        type: "POST",
                        url: api_full_name,
                        dataType: 'json',
                        success: function (resp) {
                            var json = Process(JSON.stringify(resp));
                            div_block.find('.return_area').html(json);
                        },
                        error: function () {
                            alert('请求失败,请稍后再试!');
                        }
                    })
                });

            $('.api_reset_btn').click(function () {
                var div_block = $(this).parent().parent();
                div_block.find('.return_area').html('');
            })
        })
    </script>
</head>
<body>
<div id="title">
    <img src="${ctx}/res/image/text.png" style="padding-left: 300px;">Drpeng
    门户管理 Launcher 4.0
</div>
<div>
    <table align="center" class="versiontable" style="width: 550px;"
           border="1px">
        <tr style="background-color: #effeef; border: 1px;">
            <td colspan="3" align="center">更新说明</td>
        </tr>
        <tr align="left">
            <td>更新日期</td>
            <td>更新接口</td>
            <td>更新内容</td>
        </tr>
    </table>
</div>
<br/>
<ol id="menu">


    <%--<li><a href="#/hiveview-launcherapi/portalHome/getPortalHome" title="开机启动获取“七合一”信息接口">/hiveview-launcherapi/portalHome/getPortalHome</a>(开机启动获取“七合一”信息接口)--%>
    <li><a href="#/hiveview-launcherapi/logoManageList/getLogoLicenseList" title="根据盒子信息获取LOGO牌照接口">/hiveview-launcherapi/logoManageList/getLogoLicenseList</a>(根据盒子信息获取LOGO牌照接口)
    <%--<li><a href="#/hiveview-launcherapi/screenRecommendList/getList" title="首屏大图接口">/hiveview-launcherapi/screenRecommendList/getList</a>(首屏大图接口)--%>
    <%--<li><a href="#/hiveview-launcherapi/screenRecommendList/getPortalList" title="首屏横竖图接口">/hiveview-launcherapi/screenRecommendList/getPortalList</a>(首屏横竖图接口)--%>
    <li><a href="#/hiveview-launcherapi/startInstructionList/getList" title="开机自启动接口">/hiveview-launcherapi/startInstructionList/getList</a>(开机自启动接口)
    <li><a href="#/hiveview-launcherapi/sysAppIconsList/getList" title="桌面过滤接口">/hiveview-launcherapi/sysAppIconsList/getList</a>(桌面过滤接口)
    <li><a href="#/hiveview-launcherapi/vipUser/getVipList" title="用户vip信息接口">/hiveview-launcherapi/vipUser/getVipList</a>(用户vip信息接口)
    <%--<li><a href="#/hiveview-launcherapi/portalBeanCurd/launcherHome" title="豆腐块接口">/hiveview-launcherapi/portalBeanCurd/launcherHome</a>(豆腐块接口)--%>
    <%--<li><a href="#/hiveview-launcherapi/portalRecommendList/getPortalRecommendList" title="推荐位列表接口">/hiveview-launcherapi/portalRecommendList/getPortalRecommendList</a>(推荐位列表接口)--%>
    <%--<li><a href="#/hiveview-launcherapi/fixedRecommendContent/getPortalFixedRecomContentList" title="智能推荐/猜你喜欢Group接口">/hiveview-launcherapi/fixedRecommendContent/getPortalFixedRecomContentList</a>(智能推荐/猜你喜欢Group接口)--%>
    <%--<li><a href="#/hiveview-launcherapi/portalCustomRecomContent/getPortalCustomRecomContentList" title="积木推荐位接口">/hiveview-launcherapi/portalCustomRecomContent/getPortalCustomRecomContentList</a>(自定义推荐位接口)--%>
    <%--<li><a href="#/hiveview-launcherapi/newContentChan/getList" title="热搜接口">/hiveview-launcherapi/newContentChan/getList</a>(热搜接口)--%>
    <li><a href="#/hiveview-launcherapi/Navigation/getNavigationList" title="获取导航栏信息">/hiveview-launcherapi/Navigation/getNavigationList</a>(获取导航栏信息)
    <li><a href="#/hiveview-launcherapi/Navigation/getDefaultTapPic" title="Tab默认背景地址">/hiveview-launcherapi/Navigation/getDefaultTapPic</a>(获取Tab默认图片地址)
    <li><a href="#/hiveview-launcherapi/portalTabGroup/getList" title="根据导航栏ID获取GroupList">/hiveview-launcherapi/portalTabGroup/getList</a>(根据导航栏ID获取GroupList)
    <li><a href="#/hiveview-launcherapi/PortalDataGroupController/getDataGroupList" title="智能推荐/猜你喜欢Group接口">/hiveview-launcherapi/PortalDataGroupController/getDataGroupList</a>(智能推荐/猜你喜欢Group接口)
     <%--<li><a href="#/hiveview-launcherapi/unbundling/unbundlingList" title="下线通知接口">/hiveview-launcherapi/unbundling/unbundlingList</a>(下线通知接口 ps:供测试同学测试专用)--%>
</ol>




<!--<a name="/hiveview-launcherapi/portalHome/getPortalHome"></a>

<div class="api_block">
    <div class="api_name">
        接口名称： <span id="api_name">/hiveview-launcherapi/portalHome/getPortalHome</span>
        <span style="float: right; font-size: 12px;">[<a href="#" title="TOP" style="color: #FFF; text-decoration: none;">↑</a>] </span>
    </div>
    <div class="api_method">
        请求方式： <span id="api_method">GET</span>
    </div>
    <div class="api_url">接口地址：http://{Server}/hiveview-launcherapi/portalHome/getPortalHome/{mac}/{sn}/{model}/{userId}/{templateId}/{romversion}/{page}/{size}/{version}.json</div>
    <div class="api_desc">接口说明： 开机启动获取“七合一”信息接口</div>
    <div class="api_result">
        返回值说明：<a href="#/RespCode" title="状态码">响应状态码</a>
        <ul>
            <li>logo:Logo牌照</li>
            <li>vip:用户VIP</li>
            <li>bigPic:首屏大图数据</li>
            <li>portalList:横竖图数据</li>
            <li>launcherHome:豆腐块数据</li>
            <li>start:开机自启动数据</li>
        </ul>
    </div>
    <div class="api_params">
        参数列表： <br/> <br/>
        <div id="params_list">
            mac = <input type="text" name="mac" value=""><span class="notes">mac</span><br/>
            sn = <input type="text" name="sn" value=""><span class="notes">sn</span><br/>
            model = <input type="text" name="model" value=""><span class="notes">硬件型号</span><br/>
            userId = <input type="text" name="userId" value=""><span class="notes">用户ID</span><br/>
            templateId = <input type="text" name="templateId" value=""><span class="notes">模板ID</span><br/>
            romversion = <input type="text" name="romversion" value=""><span class="notes">ROM版本</span><br/>
            page = <input type="text" name="page" value=""><span class="notes">当前页数</span><br/>
            size = <input type="text" name="size" value=""><span class="notes">每页数量</span><br/>
            version = <input type="text" name="version" value=""><span class="notes">版本号</span><br/>
        </div>
        <input type="button" class="api_test_btn" value="测试接口"/>
        <input type="button" class="api_reset_btn" value="重置结果"/>
    </div>
    <div class="return_area"></div>
</div>-->

<a name="/hiveview-launcherapi/logoManageList/getLogoLicenseList"></a>

<div class="api_block">
    <div class="api_name">
        接口名称： <span id="api_name">/hiveview-launcherapi/logoManageList/getLogoLicenseList</span>
        <span style="float: right; font-size: 12px;">[<a href="#" title="TOP" style="color: #FFF; text-decoration: none;">↑</a>] </span>
    </div>
    <div class="api_method">
        请求方式： <span id="api_method">GET</span>
    </div>
    <div class="api_url">接口地址：http://{Server}/hiveview-launcherapi/logoManageList/getLogoLicenseList/{mac}/{sn}/{model}/{romversion}/{page}/{size}/{version}.json</div>
    <div class="api_desc">接口说明： 根据盒子信息获取LOGO牌照接口</div>
    <div class="api_result">
        返回值说明：<a href="#/RespCode" title="状态码">响应状态码</a>
        <ul>
            <li>logoName:Logo牌照名称</li>
            <li>logoImg:Logo牌照图片地址</li>
        </ul>
    </div>
    <div class="api_params">
        参数列表： <br/> <br/>
        <div id="params_list">
            mac = <input type="text" name="mac" value=""><span class="notes">mac</span><br/>
            sn = <input type="text" name="sn" value=""><span class="notes">sn</span><br/>
            model = <input type="text" name="model" value=""><span class="notes">硬件型号</span><br/>
            romversion = <input type="text" name="romversion" value=""><span class="notes">ROM版本</span><br/>
            page = <input type="text" name="page" value=""><span class="notes">当前页数</span><br/>
            size = <input type="text" name="size" value=""><span class="notes">每页数量</span><br/>
            version = <input type="text" name="version" value=""><span class="notes">版本号</span><br/>
        </div>
        <input type="button" class="api_test_btn" value="测试接口"/>
        <input type="button" class="api_reset_btn" value="重置结果"/>
    </div>
    <div class="return_area"></div>
</div>

<!--<a name="/hiveview-launcherapi/screenRecommendList/getList"></a>

<div class="api_block">
    <div class="api_name">
        接口名称： <span id="api_name">/hiveview-launcherapi/screenRecommendList/getList</span>
        <span style="float: right; font-size: 12px;">[<a href="#" title="TOP" style="color: #FFF; text-decoration: none;">↑</a>] </span>
    </div>
    <div class="api_method">
        请求方式： <span id="api_method">GET</span>
    </div>
    <div class="api_url">接口地址：http://{Server}/hiveview-launcherapi/screenRecommendList/getList/{mac}/{sn}/{model}/{romversion}/{version}.json</div>
    <div class="api_desc">接口说明： 首屏大图接口</div>
    <div class="api_result">
        返回值说明：<a href="#/RespCode" title="状态码">响应状态码</a>
        <ul>
            <li>isPlay: 0 为图片 1为视频</li>
            <li>bigPic:图片地址或者视频地址</li>
            <li>contentId:专辑/专题/活动Id</li>
            <li>title:标题</li>
            <li>secondTitle:副标题</li>
            <li>timeSpan:间隔时间</li>
            <li>contentType:内容类型 1专辑详情 0 影视专题2应用详情3应用专题 4 活动详情 5启动应用6全屏大图 7商品包</li>
            <li>seq:顺序</li>
            <li>apkName:包名 如:com.hiveview.cloudscreen.vipvideo</li>
            <li>cid:频道id</li>
            <li>templetId:模板Id</li>
        </ul>
    </div>
    <div class="api_params">
        参数列表： <br/> <br/>
        <div id="params_list">
            mac = <input type="text" name="mac" value=""><span class="notes">mac</span><br/>
            sn = <input type="text" name="sn" value=""><span class="notes">sn</span><br/>
            model = <input type="text" name="model" value=""><span class="notes">硬件型号</span><br/>
            romversion = <input type="text" name="romversion" value=""><span class="notes">ROM版本</span><br/>
            version = <input type="text" name="version" value=""><span class="notes">版本号</span><br/>
        </div>
        <input type="button" class="api_test_btn" value="测试接口"/>
        <input type="button" class="api_reset_btn" value="重置结果"/>
    </div>
    <div class="return_area"></div>
</div>

<a name="/hiveview-launcherapi/screenRecommendList/getPortalList"></a>

<div class="api_block">
    <div class="api_name">
        接口名称： <span id="api_name">/hiveview-launcherapi/screenRecommendList/getPortalList</span>
        <span style="float: right; font-size: 12px;">[<a href="#" title="TOP" style="color: #FFF; text-decoration: none;">↑</a>] </span>
    </div>
    <div class="api_method">
        请求方式： <span id="api_method">GET</span>
    </div>
    <div class="api_url">接口地址：http://{Server}/hiveview-launcherapi/screenRecommendList/getPortalList/{mac}/{sn}/{model}/{romversion}/{version}/{page}/{size}.json</div>
    <div class="api_desc">接口说明： 首屏横竖图接口</div>
    <div class="api_result">
        返回值说明：<a href="#/RespCode" title="状态码">响应状态码</a>
        <ul>
            <li>isPlay: 0 为图片 1为视频</li>
            <li>bigPic:图片地址或者视频地址</li>
            <li>contentId:专辑/专题/活动Id</li>
            <li>title:标题</li>
            <li>secondTitle:副标题</li>
            <li>timeSpan:间隔时间</li>
            <li>contentType:内容类型 1专辑详情 0 影视专题2应用详情3应用专题 4 活动详情 5启动应用6全屏大图 7商品包</li>
            <li>seq:顺序</li>
            <li>apkName:包名 如:com.hiveview.cloudscreen.vipvideo</li>
            <li>cid:频道id</li>
            <li>templetId:模板Id</li>
        </ul>
    </div>
    <div class="api_params">
        参数列表： <br/> <br/>
        <div id="params_list">
            mac = <input type="text" name="mac" value=""><span class="notes">mac</span><br/>
            sn = <input type="text" name="sn" value=""><span class="notes">sn</span><br/>
            model = <input type="text" name="model" value=""><span class="notes">硬件型号</span><br/>
            romversion = <input type="text" name="romversion" value=""><span class="notes">ROM版本</span><br/>
            version = <input type="text" name="version" value=""><span class="notes">版本号</span><br/>
            page = <input type="text" name="page" value=""><span class="notes">页码</span><br/>
            size = <input type="text" name="size" value=""><span class="notes">大小</span><br/>
        </div>
        <input type="button" class="api_test_btn" value="测试接口"/>
        <input type="button" class="api_reset_btn" value="重置结果"/>
    </div>
    <div class="return_area"></div>
</div>-->

<a name="/hiveview-launcherapi/startInstructionList/getList"></a>

<div class="api_block">
    <div class="api_name">
        接口名称： <span id="api_name">/hiveview-launcherapi/startInstructionList/getList</span>
        <span style="float: right; font-size: 12px;">[<a href="#" title="TOP" style="color: #FFF; text-decoration: none;">↑</a>] </span>
    </div>
    <div class="api_method">
        请求方式： <span id="api_method">GET</span>
    </div>
    <div class="api_url">接口地址：http://{Server}/hiveview-launcherapi/startInstructionList/getList/{mac}/{sn}/{model}/{romversion}/{version}.json</div>
    <div class="api_desc">接口说明： 开机自启动接口</div>
    <div class="api_result">
        返回值说明：<a href="#/RespCode" title="状态码">响应状态码</a>
        <ul>
            <li>packageName:包名 如:com.hiveview.cloudscreen.vipvideo</li>
            <li>priority:优先级</li>
            <li>startType:1-action方式启动 2-包名方式启动</li>
        </ul>
    </div>
    <div class="api_params">
        参数列表： <br/> <br/>
        <div id="params_list">
            mac = <input type="text" name="mac" value=""><span class="notes">mac</span><br/>
            sn = <input type="text" name="sn" value=""><span class="notes">sn</span><br/>
            model = <input type="text" name="model" value=""><span class="notes">硬件型号</span><br/>
            romversion = <input type="text" name="romversion" value=""><span class="notes">ROM版本</span><br/>
            version = <input type="text" name="version" value=""><span class="notes">版本号</span><br/>
        </div>
        <input type="button" class="api_test_btn" value="测试接口"/>
        <input type="button" class="api_reset_btn" value="重置结果"/>
    </div>
    <div class="return_area"></div>
</div>

<a name="/hiveview-launcherapi/sysAppIconsList/getList"></a>

<div class="api_block">
    <div class="api_name">
        接口名称： <span id="api_name">/hiveview-launcherapi/sysAppIconsList/getList</span>
        <span style="float: right; font-size: 12px;">[<a href="#" title="TOP" style="color: #FFF; text-decoration: none;">↑</a>] </span>
    </div>
    <div class="api_method">
        请求方式： <span id="api_method">GET</span>
    </div>
    <div class="api_url">接口地址：http://{Server}/hiveview-launcherapi/sysAppIconsList/getList/{mac}/{sn}/{model}/{romversion}/{version}.json</div>
    <div class="api_desc">接口说明： 桌面过滤接口</div>
    <div class="api_result">
        返回值说明：<a href="#/RespCode" title="状态码">响应状态码</a>
        <ul>
            <li>appName:应用名称</li>
            <li>appPackage:包名 如:com.hiveview.cloudscreen.vipvideo</li>
            <li>isShow:是否显示  0 否 1 是</li>
            <li>id:主键</li>
        </ul>
    </div>
    <div class="api_params">
        参数列表： <br/> <br/>
        <div id="params_list">
            mac = <input type="text" name="mac" value=""><span class="notes">mac</span><br/>
            sn = <input type="text" name="sn" value=""><span class="notes">sn</span><br/>
            model = <input type="text" name="model" value=""><span class="notes">硬件型号</span><br/>
            romversion = <input type="text" name="romversion" value=""><span class="notes">ROM版本</span><br/>
            version = <input type="text" name="version" value=""><span class="notes">版本号</span><br/>
        </div>
        <input type="button" class="api_test_btn" value="测试接口"/>
        <input type="button" class="api_reset_btn" value="重置结果"/>
    </div>
    <div class="return_area"></div>
</div>

<a name="/hiveview-launcherapi/vipUser/getVipList"></a>

<div class="api_block">
    <div class="api_name">
        接口名称： <span id="api_name">/hiveview-launcherapi/vipUser/getVipList</span>
        <span style="float: right; font-size: 12px;">[<a href="#" title="TOP" style="color: #FFF; text-decoration: none;">↑</a>] </span>
    </div>
    <div class="api_method">
        请求方式： <span id="api_method">GET</span>
    </div>
    <div class="api_url">接口地址：http://{Server}/hiveview-launcherapi/vipUser/getVipList/{mac}/{sn}/{model}/{romversion}/{version}/{userId}.json</div>
    <div class="api_desc">接口说明： 用户VIP信息接口</div>
    <div class="api_result">
        返回值说明：<a href="#/RespCode" title="状态码">响应状态码</a>
        <ul>
            <li>expiredDate:到期时间</li>
            <li>cp:来源 domyVip 大麦VIP  qiyiVip 奇艺VIP</li>
            <li>isVip:是否VIP  0 否 1 是</li>
        </ul>
    </div>
    <div class="api_params">
        参数列表： <br/> <br/>
        <div id="params_list">
            mac = <input type="text" name="mac" value=""><span class="notes">mac</span><br/>
            sn = <input type="text" name="sn" value=""><span class="notes">sn</span><br/>
            model = <input type="text" name="model" value=""><span class="notes">硬件型号</span><br/>
            romversion = <input type="text" name="romversion" value=""><span class="notes">ROM版本</span><br/>
            version = <input type="text" name="version" value=""><span class="notes">版本号</span><br/>
            userId = <input type="text" name="userId" value=""><span class="notes">用户ID</span><br/>
        </div>
        <input type="button" class="api_test_btn" value="测试接口"/>
        <input type="button" class="api_reset_btn" value="重置结果"/>
    </div>
    <div class="return_area"></div>
</div>

<!--<a name="/hiveview-launcherapi/portalBeanCurd/launcherHome"></a>

<div class="api_block">
    <div class="api_name">
        接口名称： <span id="api_name">/hiveview-launcherapi/portalBeanCurd/launcherHome</span>
        <span style="float: right; font-size: 12px;">[<a href="#" title="TOP" style="color: #FFF; text-decoration: none;">↑</a>] </span>
    </div>
    <div class="api_method">
        请求方式： <span id="api_method">GET</span>
    </div>
    <div class="api_url">接口地址：http://{Server}/hiveview-launcherapi/portalBeanCurd/launcherHome/{mac}/{sn}/{model}/{romversion}/{version}.json</div>
    <div class="api_desc">接口说明： 豆腐块接口</div>
    <div class="api_result">
        返回值说明：<a href="#/RespCode" title="状态码">响应状态码</a>
        <ul>
            <li>entranceId: 入口Id</li>
            <li>entranceName: 名称</li>
            <li>seq: 顺序</li>
            <li>entranceType: 入口类别 0系统 1定制</li>
            <li>entranceAppAction: 入口应用</li>
            <li>entranceUrl: 定制应用URL</li>
            <li>installStyle: 定制应用升级方式 0 询问  1 静默</li>
            <li>entranceAppName: 定制应用包名</li>
            <li>entranceAppVersion:定制应用版本</li>
            <li>entranceContentName:定制应用版本</li>
            <li>entranceContentFocus:副标题</li>
            <li>seq:顺序</li>
            <li>entranceContentImage:顺序</li>
        </ul>
    </div>
    <div class="api_params">
        参数列表： <br/> <br/>
        <div id="params_list">
            mac = <input type="text" name="mac" value=""><span class="notes">mac</span><br/>
            sn = <input type="text" name="sn" value=""><span class="notes">sn</span><br/>
            model = <input type="text" name="model" value=""><span class="notes">硬件型号</span><br/>
            romversion = <input type="text" name="romversion" value=""><span class="notes">ROM版本</span><br/>
            <%--page = <input type="text" name="page" value=""><span class="notes">当前页数</span><br/>
            size = <input type="text" name="size" value=""><span class="notes">每页数量</span><br/>--%>
            version = <input type="text" name="version" value=""><span class="notes">版本号</span><br/>
        </div>
        <input type="button" class="api_test_btn" value="测试接口"/>
        <input type="button" class="api_reset_btn" value="重置结果"/>
    </div>
    <div class="return_area"></div>
</div>


<a name="/hiveview-launcherapi/portalRecommendList/getPortalRecommendList"></a>

<div class="api_block">
    <div class="api_name">
        接口名称： <span id="api_name">/hiveview-launcherapi/portalRecommendList/getPortalRecommendList</span>
        <span style="float: right; font-size: 12px;">[<a href="#" title="TOP" style="color: #FFF; text-decoration: none;">↑</a>] </span>
    </div>
    <div class="api_method">
        请求方式： <span id="api_method">GET</span>
    </div>
    <div class="api_url">接口地址：http://{Server}/hiveview-launcherapi/portalRecommendList/getPortalRecommendList/{mac}/{sn}/{model}/{romversion}/{page}/{size}/{version}.json</div>
    <div class="api_desc">接口说明： 根据盒子信息获取推荐位列表接口</div>
    <div class="api_result">
        返回值说明：<a href="#/RespCode" title="状态码">响应状态码</a>
        <ul>
            <li>templetId:模板ID</li>
            <li>recommendId:推荐位ID</li>
            <li>recommendName:推荐位名称</li>
            <li>recommendType:推荐位类型（固定推荐位1；积木推荐位2；下拉推荐位3）</li>
        </ul>
    </div>
    <div class="api_params">
        参数列表： <br/> <br/>
        <div id="params_list">
            mac = <input type="text" name="mac" value=""><span class="notes">mac</span><br/>
            sn = <input type="text" name="sn" value=""><span class="notes">sn</span><br/>
            model = <input type="text" name="model" value=""><span class="notes">硬件型号</span><br/>
            romversion = <input type="text" name="romversion" value=""><span class="notes">ROM版本</span><br/>
            page = <input type="text" name="page" value=""><span class="notes">当前页数</span><br/>
            size = <input type="text" name="size" value=""><span class="notes">每页数量</span><br/>
            version = <input type="text" name="version" value=""><span class="notes">版本号</span><br/>
        </div>
        <input type="button" class="api_test_btn" value="测试接口"/>
        <input type="button" class="api_reset_btn" value="重置结果"/>
    </div>
    <div class="return_area"></div>
</div>

<a name="/hiveview-launcherapi/fixedRecommendContent/getPortalFixedRecomContentList"></a>

<div class="api_block">
    <div class="api_name">
        接口名称： <span id="api_name">/hiveview-launcherapi/fixedRecommendContent/getPortalFixedRecomContentList</span>
        <span style="float: right; font-size: 12px;">[<a href="#" title="TOP" style="color: #FFF; text-decoration: none;">↑</a>] </span>
    </div>
    <div class="api_method">
        请求方式： <span id="api_method">GET</span>
    </div>
    <div class="api_url">接口地址：http://{Server}/hiveview-launcherapi/fixedRecommendContent/getPortalFixedRecomContentList/{id}/{userId}/{mac}/{sn}/{page}/{size}/{version}.json</div>
    <div class="api_desc">接口说明： 根据推荐位ID获取固定推荐位列表接口</div>
    <div class="api_result">
        返回值说明：<a href="#/RespCode" title="状态码">响应状态码</a>
        <ul>
            <li>contentId:内容ID</li>
            <li>contentType:内容类型</li>
            <li>contentName:内容名称</li>
            <li>contentSubtitle:内容副标题</li>
            <li>contentImg:内容图片</li>
            <li>operateContent:运营内容（1大麦影视，2应用游戏）</li>
            <li>appCategory:应用类型（1游戏2应用3教育）</li>
            <li>chnId:频道ID</li>
            <li>chnType:频道类型（固定频道 10）</li>
            <li>apkBagName:APK包名</li>
            <li>templetId:大麦影视模板ID</li>
            <li>qiyiIsVip:是否爱奇艺VIP</li>

        </ul>
    </div>
    <div class="api_params">
        参数列表： <br/> <br/>
        <div id="params_list">
            id = <input type="text" name="id" value=""><span class="notes">推荐位id</span><br/>
            userId = <input type="text" name="userId" value=""><span class="notes">用户Id</span><br/>
            mac = <input type="text" name="mac" value=""><span class="notes">mac</span><br/>
            sn = <input type="text" name="sn" value=""><span class="notes">sn</span><br/>
            page = <input type="text" name="page" value=""><span class="notes">当前页数</span><br/>
            size = <input type="text" name="size" value=""><span class="notes">每页数量</span><br/>
            version = <input type="text" name="version" value=""><span class="notes">版本号</span><br/>
        </div>
        <input type="button" class="api_test_btn" value="测试接口"/>
        <input type="button" class="api_reset_btn" value="重置结果"/>
    </div>
    <div class="return_area"></div>
</div>


<a name="/hiveview-launcherapi/portalCustomRecomContent/getPortalCustomRecomContentList"></a>

<div class="api_block">
    <div class="api_name">
        接口名称： <span id="api_name">/hiveview-launcherapi/portalCustomRecomContent/getPortalCustomRecomContentList</span>
        <span style="float: right; font-size: 12px;">[<a href="#" title="TOP" style="color: #FFF; text-decoration: none;">↑</a>] </span>
    </div>
    <div class="api_method">
        请求方式： <span id="api_method">GET</span>
    </div>
    <div class="api_url">接口地址：http://{Server}/hiveview-launcherapi/portalCustomRecomContent/getPortalCustomRecomContentList/{id}/{page}/{size}/{version}.json</div>
    <div class="api_desc">接口说明： 根据推荐位ID获取积木推荐位列表接口</div>
    <div class="api_result">
        返回值说明：<a href="#/RespCode" title="状态码">响应状态码</a>
        <ul>
            <li>layoutTeam:矩阵组ID</li>
            <li>layoutTeamType:矩阵组布局</li>
            <li>layoutList:矩阵列表</li>
            <li>contentId:内容ID</li>
            <li>contentType:内容类型（专辑详情：1、影视专题：0、应用详情:11、应用专题:12、活动详情:2、全屏大图:13、商品包:7）</li>
            <li>contentName:内容名称</li>
            <li>contentSubtitle:内容副标题</li>
            <li>contentImg:内容图片</li>
            <li>contentOutcropImg:露头图</li>
            <li>contentBigPic:大图</li>
            <li>categoryId:应用类型（1游戏2应用3教育）</li>
            <li>seq:顺序</li>
            <li>chnId:频道ID</li>
            <li>chnType:频道类型（固定频道 10）</li>
            <li>apkBagName:APK包名</li>
            <li>templetId:大麦影视模板ID</li>
            <li>hotId:热词ID</li>
            <li>hotType:热词类型（默认热词类型 3）</li>
            <li>layoutId:矩阵ID</li>
            <li>layoutX:矩阵横坐标</li>
            <li>layoutY:矩阵纵坐标</li>
            <li>layoutW:矩阵宽</li>
            <li>layoutH:矩阵高</li>
            <li>positionSeq:积木位置标记</li>
            <li>recomTempletId:积木推荐位模板ID</li>
        </ul>
    </div>
    <div class="api_params">
        参数列表： <br/> <br/>
        <div id="params_list">
            id = <input type="text" name="id" value=""><span class="notes">推荐位id</span><br/>
            page = <input type="text" name="page" value=""><span class="notes">当前页数</span><br/>
            size = <input type="text" name="size" value=""><span class="notes">每页矩阵组数量</span><br/>
            version = <input type="text" name="version" value=""><span class="notes">版本号</span><br/>
        </div>
        <input type="button" class="api_test_btn" value="测试接口"/>
        <input type="button" class="api_reset_btn" value="重置结果"/>
    </div>
    <div class="return_area"></div>
</div>

<a name="/hiveview-launcherapi/newContentChan/getList"></a>

<div class="api_block">
    <div class="api_name">
        接口名称： <span id="api_name">/hiveview-launcherapi/newContentChan/getList</span>
        <span style="float: right; font-size: 12px;">[<a href="#" title="TOP" style="color: #FFF; text-decoration: none;">↑</a>] </span>
    </div>
    <div class="api_method">
        请求方式： <span id="api_method">GET</span>
    </div>
    <div class="api_url">接口地址：http://{Server}/hiveview-launcherapi/newContentChan/getList.json</div>
    <div class="api_desc">接口说明： 调用统计提供的最热的接口</div>
    <div class="api_result">
        返回值说明：<a href="#/RespCode" title="状态码">响应状态码</a>
        <ul>
            <li>programsetId:专辑ID</li>
            <li>chnId:频道</li>
            <li>albumName:专辑名称</li>
        </ul>
    </div>
    <div class="api_params">
        参数列表： <br/> <br/>
        <input type="button" class="api_test_btn" value="测试接口"/>
    </div>
    <div class="return_area"></div>
</div>-->

<a name="/hiveview-launcherapi/portalTabGroup/getList"></a>

<div class="api_block">
    <div class="api_name">
        接口名称： <span id="api_name">/hiveview-launcherapi/portalTabGroup/getList</span>
        <span style="float: right; font-size: 12px;">[<a href="#" title="TOP" style="color: #FFF; text-decoration: none;">↑</a>] </span>
    </div>
    <div class="api_method">
        请求方式： <span id="api_method">GET</span>
    </div>
    <div class="api_url">接口地址：http://{Server}/hiveview-launcherapi/portalTabGroup/getList/{id}/{page}/{size}/{version}.json</div>
    <div class="api_desc">接口说明： 根据导航栏ID获取GroupList</div>
    <div class="api_result">
        返回值说明：<a href="#/RespCode" title="状态码">响应状态码</a>
        <ul>
            <li>tabId:导航栏ID</li>
            <li>backgroud:背景图片</li>
            <li>page:当前的页码值</li>
            <li>size:大小</li>
            <li>list - groupId:group的id  ps:当groupType 为2 时,groupId为1 历史记录 2 会员中心 3 系统应用 4 首映Tab播放器 :当groupType 为3 时,频道类型为 0  调用智能推荐接口 此时 groupId为智能推荐接口参数的推荐位id</li>
            <li>list - groupName:group的名称</li>
            <li>list - groupTitle:group的标题</li>
            <li>list - groupType:group的类型  1.Group  2.Spe Group  3.Data Group</li>
            <li>list - contentType:推荐类型  0.推荐频道  1.热词内容  2.频道内容</li>
            <li>list - isShow:是否显示   1显示  0不显示</li>
            <li>list - col:列</li>
            <li>list - row:行</li>
            <li>list - width:宽</li>
            <li>list - height:高</li>
            <li>list - chnId:频道Id</li>
            <li>list - chnType:频道类型</li>
            <li>list - hotId: ps:当包名 com.hiveview.cloudscreen.appstore 此字段为标签id  包名为其他时 此字段 热词Id</li>
            <li>list - templetId:模板Id</li>
            <li>list - apkBagName:包名 ps:当推荐类型为 1 时,包名 com.hiveview.cloudscreen.appstore 是指应用游戏 </li>
            <li>list - upAndDown:横纵(1横,2纵)</li>
            <li>list - groupBackground:group背景图</li>
            <li>list -list - action:跳转Activity的URI</li>
            <li>list -list - apkVersionCode:版本号</li>
            <li>list -list - apkDownUrl:下载地址</li>
            <li>list -list - installStyle:安装方式 0 询问安装 1 静默安装</li>
            <li>list -list - appType:应用类型，0系统应用，1定制应用</li>
            <li>list -list - apk:apk包名(用于类型为12启动应用)</li>
            <li>list -list - contentId:内容ID</li>
            <li>list -list - contentType:内容类型（专辑详情：1、影视专题：0、应用详情:2、应用专题:3、活动详情:4、全屏大图:6、商品包:7 优购专题(优购的唯一标识是specSn,不是contentId): 8 优购详情:(优购的唯一标识是specSn,不是contentId) 9 tab页:10 会员活动:11 启动应用:12）</li>
            <li>list -list - contentName:内容名称</li>
            <li>list -list - contentSubtitle:内容副标题</li>
            <li>list -list - contentImg:内容图片</li>
            <li>list -list - contentOutcropImg:露头图</li>
            <li>list -list - contentBigPic:大图 </li>
            <li>list -list - chnId:频道ID</li>
            <li>list -list - chnType:频道类型（固定频道 10）</li>
            <li>list -list - apkBagName:APK包名</li>
            <li>list -list - templetId:大麦影视模板ID</li>
            <li>list -list - hotId:热词ID</li>
            <li>list -list - hotType:热词类型（默认热词类型 3）</li>
            <li>list -list - layoutId:矩阵ID</li>
            <li>list -list - layoutX:行号起点</li>
            <li>list -list - layoutY:列号的起点</li>
            <li>list -list - layoutW:跨列的合并数</li>
            <li>list -list - layoutH:跨行的合并数</li>
            <li>list -list - isFocusable: 1可跳转  0不可跳转</li>
            <li>list -list - col: 单元高px</li>
            <li>list -list - row: 单元宽</li>
            <li>list -list - videoId: 剧集Id</li>
            <li>list -list - videoUrl: 视频url</li>
            <li>list -list - specSn: 优购专题和详情唯一标识</li>
        </ul>
    </div>
    <div class="api_params">
        参数列表： <br/> <br/>
        <div id="params_list">
            id = <input type="text" name="id" value=""><span class="notes">导航栏id</span><br/>
            page = <input type="text" name="page" value=""><span class="notes">当前页数</span><br/>
            size = <input type="text" name="size" value=""><span class="notes">每页矩阵组数量</span><br/>
            version = <input type="text" name="version" value=""><span class="notes">版本号</span><br/>
        </div>
        <input type="button" class="api_test_btn" value="测试接口"/>
    </div>
    <div class="return_area"></div>
</div>

<a name="/hiveview-launcherapi/PortalDataGroupController/getDataGroupList"></a>

<div class="api_block">
    <div class="api_name">
        接口名称： <span id="api_name">/hiveview-launcherapi/PortalDataGroupController/getDataGroupList</span>
        <span style="float: right; font-size: 12px;">[<a href="#" title="TOP" style="color: #FFF; text-decoration: none;">↑</a>] </span>
    </div>
    <div class="api_method">
        请求方式： <span id="api_method">GET</span>
    </div>
    <div class="api_url">接口地址：http://{Server}/hiveview-launcherapi/PortalDataGroupController/getDataGroupList/{id}/{userId}/{mac}/{sn}/{type}/{page}/{size}/{version}.json</div>
    <div class="api_desc">接口说明： 智能推荐/猜你喜欢Group接口</div>
    <div class="api_result">
        返回值说明：<a href="#/RespCode" title="状态码">响应状态码</a>
        <ul>
            <li>contentId:内容ID</li>
            <li>contentType:内容类型</li>
            <li>contentName:内容名称</li>
            <li>contentSubtitle:内容副标题</li>
            <li>contentImg:内容图片</li>
            <li>operateContent:运营内容（1大麦影视，2应用游戏）</li>
            <li>appCategory:应用类型（1游戏2应用3教育）</li>
            <li>chnId:频道ID</li>
            <li>chnType:频道类型（固定频道 10）</li>
            <li>apkBagName:APK包名</li>
            <li>templetId:大麦影视模板ID</li>
            <li>qiyiIsVip:是否爱奇艺VIP</li>
        </ul>
    </div>
    <div class="api_params">
        参数列表： <br/> <br/>
        <div id="params_list">
            id = <input type="text" name="id" value=""><span class="notes">推荐位id</span><br/>
            userId = <input type="text" name="userId" value=""><span class="notes">用户Id</span><br/>
            mac = <input type="text" name="mac" value=""><span class="notes">mac</span><br/>
            sn = <input type="text" name="sn" value=""><span class="notes">sn</span><br/>
            type = <input type="text" name="type" value=""><span class="notes">type</span><br/>
            page = <input type="text" name="page" value=""><span class="notes">当前页数</span><br/>
            size = <input type="text" name="size" value=""><span class="notes">每页数量</span><br/>
            version = <input type="text" name="version" value=""><span class="notes">版本号</span><br/>
        </div>
        <input type="button" class="api_test_btn" value="测试接口"/>
    </div>
    <div class="return_area"></div>
</div>

<a name="/hiveview-launcherapi/Navigation/getNavigationList"></a>
<div class="api_block">
    <div class="api_name">
        接口名称： <span id="api_name">/hiveview-launcherapi/Navigation/getNavigationList</span>
        <span style="float: right; font-size: 12px;">[<a href="#" title="TOP" style="color: #FFF; text-decoration: none;">↑</a>] </span>
    </div>
    <div class="api_method">
        请求方式： <span id="api_method">GET</span>
    </div>
    <div class="api_url">接口地址：http://{Server}/hiveview-launcherapi/Navigation/getNavigationList/{mac}/{sn}/{userId}/{model}/{templateId}/{romversion}/{version}.json</div>
    <div class="api_desc">接口说明： 导航栏获取</div>
    <div class="api_result">
        返回值说明：<a href="#/RespCode" title="状态码">响应状态码</a>
        <ul>
            <li>id:导航栏ID</li>
            <li>navBackgroud:背景图片</li>
            <li>title:标题文字</li>
            <li>titleBackgroud:标题图片</li>
        </ul>
    </div>
    <div class="api_params">
        参数列表： <br/> <br/>
        <div id="params_list">
            mac = <input type="text" name="mac" value=""><span class="notes">mac:</span><br/>
        </div>
        <div id="params_list">
            sn = <input type="text" name="sn" value=""><span class="notes">sn:</span><br/>
        </div>
        <div id="params_list">
            userId = <input type="text" name="userId" value=""><span class="notes">用户id:</span><br/>
        </div>
        <div id="params_list">
            model = <input type="text" name="model" value=""><span class="notes">硬件版本:</span><br/>
        </div>
        <div id="params_list">
            templateId = <input type="text" name="templateId" value=""><span class="notes">模板Id:</span><br/>
        </div>
        <div id="params_list">
            romversion = <input type="text" name="romversion" value=""><span class="notes">rom版本:</span><br/>
        </div>
        <div id="params_list">
            version = <input type="text" name="version" value=""><span class="notes">接口版本号:</span><br/>
        </div>
        <input type="button" class="api_test_btn" value="测试接口"/>
    </div>
    <div class="return_area"></div>
</div>



<a name="/hiveview-launcherapi/Navigation/getDefaultTapPic"></a>
<div class="api_block">
    <div class="api_name">
        接口名称： <span id="api_name">/hiveview-launcherapi/Navigation/getDefaultTapPic</span>
        <span style="float: right; font-size: 12px;">[<a href="#" title="TOP" style="color: #FFF; text-decoration: none;">↑</a>] </span>
    </div>
    <div class="api_method">
        请求方式： <span id="api_method">GET</span>
    </div>
    <div class="api_url">接口地址：http://{Server}/hiveview-launcherapi/Navigation/getDefaultTapPic/{version}.json</div>
    <div class="api_desc">接口说明： Tab默认背景图接口</div>
    <div class="api_result">
        返回值说明：<a href="#/RespCode" title="状态码">响应状态码</a>
        <ul>
            <li>imgUrl:图片地址</li>
        </ul>
    </div>
    <div class="api_params">
        参数列表： <br/> <br/>
        <div id="params_list">
            version = <input type="text" name="version" value=""><span class="notes">版本号</span><br/>
        </div>
        <input type="button" class="api_test_btn" value="测试接口"/>
    </div>
    <div class="return_area"></div>
</div>


<%--<a name="/hiveview-launcherapi/unbundling/unbundlingList"></a>--%>
<%--<div class="api_block">--%>
    <%--<div class="api_name">--%>
        <%--接口名称： <span id="api_name">/hiveview-launcherapi/unbundling/unbundlingList</span>--%>
        <%--<span style="float: right; font-size: 12px;">[<a href="#" title="TOP" style="color: #FFF; text-decoration: none;">↑</a>] </span>--%>
    <%--</div>--%>
    <%--<div class="api_method">--%>
        <%--请求方式： <span id="api_method">GET</span>--%>
    <%--</div>--%>
    <%--<div class="api_url">接口地址：http://{Server}/hiveview-launcherapi/unbundling/unbundlingList/{contentType}/{apkBagName}/{contentId}/{isEffective}.json</div>--%>
    <%--<div class="api_desc">接口说明： 下线通知接口</div>--%>
    <%--<div class="api_result">--%>
        <%--返回值说明：<a href="#/RespCode" title="状态码">响应状态码</a>--%>
        <%--<ul>--%>
            <%--<li>returnValue: 0 成功  其他 失败</li>--%>
        <%--</ul>--%>
    <%--</div>--%>
    <%--<div class="api_params">--%>
        <%--参数列表： <br/> <br/>--%>
        <%--<div id="params_list">--%>
            <%--contentType = <input type="text" name="contentType" value=""><span class="notes">内容类型</span><br/>--%>
            <%--apkBagName = <input type="text" name="apkBagName" value=""><span class="notes">包名</span><br/>--%>
            <%--contentId = <input type="text" name="contentId" value=""><span class="notes">内容Id</span><br/>--%>
            <%--isEffective = <input type="text" name="isEffective" value="0"><span class="notes">下线状态</span><br/>--%>
        <%--</div>--%>
        <%--<input type="button" class="api_test_btn" value="测试接口"/>--%>
    <%--</div>--%>
    <%--<div class="return_area"></div>--%>
<%--</div>--%>


<div class="api_block">
    <div class="api_name">
        状态码<span style="float: right; font-size: 12px;">
        [<a href="#" title="TOP" style="color: #FFF; text-decoration: none;">↑</a>]
			</span>
    </div>
    <ul id="RespCode">
        <li>E000000 错误</li>
        <li>N000000 成功</li>
        <li>E000001 client_key错误</li>
        <li>E000002 密钥错误</li>
        <li>E000003 mac检查未通过</li>
        <li>E000004 超过最大注册数量</li>
        <li>E000005 cp错误</li>
        <li>E000006 鉴权失败</li>
    </ul>
</div>
<div class="return_area"></div>
<div style="position: relative; z-index: 9999999;">
    <div id="ajaxloading" style="display: none;">正在加载，请稍候 ...</div>
</div>
</body>
</html>


