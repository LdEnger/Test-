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
    <title>Launcher 4.0 api</title>
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

        .api_result, .api_params, .api_name, .api_method, .api_validate, .api_url, .api_desc, .api_params {
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
    </style>
    <script type="text/javascript" src="${ctx}/res/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="${ctx}/res/js/format.js"></script>
    <script>

        String.prototype.replaceAll = function (reallyDo, replaceWith, ignoreCase) {
            if (!RegExp.prototype.isPrototypeOf(reallyDo)) {
                return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi" : "g")), replaceWith);
            } else {
                return this.replace(reallyDo, replaceWith);
            }
        }

        $(function () {
            $('.api_test_btn').hide();
            $('#ajaxloading').ajaxStart(function () {
                $(this).show();
            });
            $('#ajaxloading').ajaxStop(function () {
                $(this).hide();
            });

            $('.return_type').click(function () {
                $(this).parent().parent().find('#return_type').html($(this).val());
            });

            $('.api_test_btn').click(function () {
                var div_block = $(this).parent().parent();
                var return_type = div_block.find('#return_type').html();
                var api_full_name = div_block.find('#api_name').html();
                var api_method = div_block.find('#api_method').children().find('.method').val();
                //console.log(api_method.children().val());
                var api_validate = div_block.find('#api_validate').html();
                var params = '';
                var MyCookie = document.cookie;

                var host = window.location.host;
                /*if(host.indexOf('46') != -1 || host.indexOf('177') != -1 || host.indexOf('localhost') != -1) {
                    host += '/adapi';
                } dont know*/
                api_full_name = "http://" + host + api_full_name + ".json";
                div_block.find('#params_list').find('input').each(function (i, v) {
                    if (i == 0) {
                        api_full_name += "?" + $(this).attr('name') + '=' + $(this).val();
                    } else {
                        api_full_name += "&" + $(this).attr('name') + '=' + $(this).val();
                    }
                })
                //params = params.substr(0, params.length - 1);
                $.ajax({
                    //data:params,
                    type: api_method,
                    dataType: 'json',
                    url: api_full_name,
                    success: function (resp) {
                        var json = Process(JSON.stringify(resp));
                        div_block.find('.return_area').html(json);
                    }, error: function () {
                        alert('请求失败,请稍后再试!');
                    }
                })
            });
            $('.post_btn').click(function () {
                var div_block = $(this).parent().parent();
                var return_type = div_block.find('#return_type').html();
                var host = window.location.host;
                /* if(host.indexOf('46') != -1 || host.indexOf('177') != -1 || host.indexOf('localhost') != -1) {
                     host += '/adapi';
                 }*/
                var api_full_name = div_block.find('#api_name').html();
                var api_method = div_block.find('#api_method').children().find('.method').val();
                var api_validate = div_block.find('#api_validate').html();
                var params = '{';
                div_block.find('#params_list').find('input').each(function () {
                    params += $(this).attr('name') + ':' + "\"" + $(this).val() + "\"" + ',';
                })
                api_full_name = "http://" + host + api_full_name + '.json';
                //api_full_name  = "http://" + host + api_full_name ;
                params = params + '}';
                var obj = eval('(' + params + ')');
                console.info(eval('(' + params + ')'));
                debugger;
                $.ajax({
                    data: obj,
                    type: "POST",
                    //contentType: 'application/json',
                    url: api_full_name,
                    dataType: 'json',
                    success: function (resp) {
                        var json = Process(JSON.stringify(resp));
                        div_block.find('.return_area').html(json);
                    }, error: function () {
                        alert('请求失败,请稍后再试!');
                    }
                })
            });

            $('.post_json_btn').click(function () {
                var div_block = $(this).parent().parent();
//                var return_type = div_block.find('#return_type').html();
                var host = window.location.host;
                /* if(host.indexOf('46') != -1 || host.indexOf('177') != -1 || host.indexOf('localhost') != -1) {
                     host += '/adapi';
                 }*/
                var api_full_name = div_block.find('#api_name').html();
//                var api_method = div_block.find('#api_method').children().find('.method').val();
//                var api_validate = div_block.find('#api_validate').html();
                var params = '{';
                div_block.find('#params_list').find('input').each(function () {
                    params += $(this).attr('name') + ':' + "\"" + $(this).val() + "\"" + ',';
                });
                api_full_name = "http://" + host + api_full_name;
                //api_full_name  = "http://" + host + api_full_name ;
                params = params + '}';
//                var obj = eval('(' + params + ')');
                console.info(eval('(' + params + ')'));
                debugger;
                $.ajax({
                    data: params,
                    type: "POST",
                    contentType: "application/json; charset=utf-8",
                    url: api_full_name,
                    dataType: 'json',
                    success: function (resp) {
                        var json = Process(JSON.stringify(resp));
                        div_block.find('.return_area').html(json);
                    }, error: function () {
                        alert('请求失败,请稍后再试!');
                    }
                })
            });

            $('.api_reset_btn').click(function () {
                var div_block = $(this).parent().parent();
                div_block.find('.return_area').html('');
            })

            $('.method').change(function () {
                //console.log($(this).val());
                var $get = $(this).parent().parent().parent().children('.api_params').children('.api_test_btn');
                var $post = $(this).parent().parent().parent().children('.api_params').children('.post_btn');
                if ($(this).val() == 'post') {
                    $get.hide();
                    $post.show();
                } else {
                    $post.hide();
                    $get.show();
                }
            });
        })
    </script>
</head>
<body>
<div id="title"><img src="${ctx}/res/image/text.png" style="padding-left: 300px;">Launcher 4.0 CMS API</div>
<ol id="menu">
    <li><a href="#/hiveview-launcherapi/logoManageList/getLogo" title="根据LogoId获取Logo牌照信息">/hiveview-launcherapi/logoManageList/getLogo</a>(根据LogoId获取Logo牌照信息)

    <li><a href="#/hiveview-launcherapi/yougou/specials/getList" title="获取优购专题列表">/hiveview-launcherapi/yougou/specials/getList</a>(获取优购专题列表)

    <li><a href="#/hiveview-launcherapi/yougou/goods/getTopCategoryList" title="获取一级品牌种类列表接口">/hiveview-launcherapi/yougou/goods/getTopCategoryList</a>(获取一级品牌种类列表接口)
    <li><a href="#/hiveview-launcherapi/yougou/goods/getSubCategoryList" title="获取下级(二三级)商品分类接口">/hiveview-launcherapi/yougou/goods/getSubCategoryList</a>(获取下级(二三级)商品分类接口)
    <li><a href="#/hiveview-launcherapi/yougou/goods/getList" title="商品种类下的所有顶级商品列表">/hiveview-launcherapi/yougou/goods/getList</a>(商品种类下的所有顶级商品列表)

</ol>

<a name="/hiveview-launcherapi/logoManageList/getLogo"></a>

<div class="api_block">
    <div class="api_name">接口名称： <span id="api_name">/hiveview-launcherapi/logoManageList/getLogo</span><span
            style="float:right; font-size:12px;">[<a href="#" title="TOP"
                                                     style="color:#FFF; text-decoration:none;">↑</a>]</span></div>
    <div class="api_method">请求方式： <span id="api_method"><select class='method'><option value='post'>post</option><option
            value='get'>get</select></span></div>
    <div class="api_url">接口地址： <span
            id="api_url">http://{Server}/hiveview-launcherapi/logoManageList/getLogo.json</span></div>
    <div class="api_desc">接口说明： 根据LogoId获取Logo牌照信息</div>
    <div class="api_result">
        返回值说明：<a href="#/RespCode" title="状态码">响应状态码</a>
        <ul>
            <li>logoId:Logo牌照ID</li>
            <li>logoName:Logo名称</li>
            <li>logoImg:Logo牌照图片地址</li>
            <li>createTime:创建时间</li>
            <li>updateTime:更新时间</li>
        </ul>
    </div>
    <div class="api_params">参数列表：
        <br/>
        <br/>
        <div id="params_list">
            logoId = <input type="text" name="logoId" value=""><span class="notes">LogoID</span><br/>
        </div>
        <input type="button" class="post_btn" value="测试接口"/>
        <input type="button" class="api_test_btn" value="测试接口"/>
        <input type="button" class="api_reset_btn" value="重置结果"/>
    </div>
    <div class="return_area"></div>
</div>

<a name="/hiveview-launcherapi/yougou/specials/getList"></a>

<div class="api_block">
    <div class="api_name">接口名称： <span id="api_name">/hiveview-launcherapi/yougou/specials/getList</span><span
            style="float:right; font-size:12px;">[<a href="#" title="TOP"
                                                     style="color:#FFF; text-decoration:none;">↑</a>]</span></div>
    <div class="api_method">请求方式： <span id="api_method"><select class='method'><option value='post'>post</option><option
            value='get'>get</select></span></div>
    <div class="api_desc">内容类型(Content-Type): application/json; charset=utf-8</div>
    <div class="api_url">接口地址： <span id="api_url">http://{Server}/hiveview-launcherapi/yougou/specials/getList</span>
    </div>
    <div class="api_desc">接口说明： 获取优购专题列表</div>
    <div class="api_result">
        返回值说明：<a href="#/RespCode" title="状态码">响应状态码</a>
        <ul>
            <li>name:专题名称</li>
            <li>pageIndex:分页索引</li>
            <li>pageSize:分页数量</li>
        </ul>
    </div>
    <div class="api_params">参数列表：
        <br/>
        <br/>
        <div id="params_list">
            name = <input type="text" name="name" value=""><span class="notes">name</span><br/>
            page = <input type="text" name="page" value="1"><span class="notes">page</span><br/>
            rows = <input type="text" name="rows" value="10"><span class="notes">rows</span><br/>
        </div>
        <input type="button" class="post_json_btn" value="测试接口"/>
        <input type="button" class="api_test_btn" value="测试接口"/>
        <input type="button" class="api_reset_btn" value="重置结果"/>
    </div>
    <div class="return_area"></div>
</div>

<a name="/hiveview-launcherapi/yougou/goods/getTopCategoryList"></a>
<div class="api_block">
    <div class="api_name">接口名称： <span id="api_name">/hiveview-launcherapi/yougou/goods/getTopCategoryList</span><span
            style="float:right; font-size:12px;">[<a href="#" title="TOP"
                                                     style="color:#FFF; text-decoration:none;">↑</a>]</span></div>
    <div class="api_method">请求方式： <span id="api_method"><select class='method'><option value='post'>post</option><option
            value='get'>get</select></span></div>
    <div class="api_desc">内容类型(Content-Type): application/json; charset=utf-8</div>
    <div class="api_url">接口地址： <span
            id="api_url">http://{Server}/hiveview-launcherapi/yougou/goods/getTopCategoryList</span></div>
    <div class="api_desc">接口说明： 获取一级品牌种类列表接口</div>
    <div class="api_result">
        返回值说明：<a href="#/RespCode" title="状态码">响应状态码</a>
        <ul>
            <li>pageIndex:分页索引</li>
            <li>pageSize:分页数量</li>
        </ul>
    </div>
    <div class="api_params">参数列表：
        <br/>
        <br/>
        <div id="params_list">
            page = <input type="text" name="page" value="1"><span class="notes">page</span><br/>
            rows = <input type="text" name="rows" value="10"><span class="notes">rows</span><br/>
        </div>
        <input type="button" class="post_json_btn" value="测试接口"/>
        <input type="button" class="api_test_btn" value="测试接口"/>
        <input type="button" class="api_reset_btn" value="重置结果"/>
    </div>
    <div class="return_area"></div>
</div>

<a name="/hiveview-launcherapi/yougou/goods/getSubCategoryList"></a>
<div class="api_block">
    <div class="api_name">接口名称： <span id="api_name">/hiveview-launcherapi/yougou/goods/getSubCategoryList</span><span
            style="float:right; font-size:12px;">[<a href="#" title="TOP"
                                                     style="color:#FFF; text-decoration:none;">↑</a>]</span></div>
    <div class="api_method">请求方式： <span id="api_method"><select class='method'><option value='post'>post</option><option
            value='get'>get</select></span></div>
    <div class="api_desc">内容类型(Content-Type): application/json; charset=utf-8</div>
    <div class="api_url">接口地址： <span
            id="api_url">http://{Server}/hiveview-launcherapi/yougou/goods/getSubCategoryList</span></div>
    <div class="api_desc">接口说明： 获取下级(二三级)商品分类接口</div>
    <div class="api_result">
        返回值说明：<a href="#/RespCode" title="状态码">响应状态码</a>
        <ul>
            <li>pageIndex:分页索引</li>
            <li>pageSize:分页数量</li>
            <li>categorySn:商品种类编码</li>
        </ul>
    </div>
    <div class="api_params">参数列表：
        <br/>
        <br/>
        <div id="params_list">
            page = <input type="text" name="page" value="1"><span class="notes">page</span><br/>
            rows = <input type="text" name="rows" value="10"><span class="notes">rows</span><br/>
            categorySn = <input type="text" name="categorySn" value=""><span class="notes">categorySn</span><br/>
        </div>
        <input type="button" class="post_json_btn" value="测试接口"/>
        <input type="button" class="api_test_btn" value="测试接口"/>
        <input type="button" class="api_reset_btn" value="重置结果"/>
    </div>
    <div class="return_area"></div>
</div>

<a name="/hiveview-launcherapi/yougou/goods/getList"></a>
<div class="api_block">
    <div class="api_name">接口名称： <span id="api_name">/hiveview-launcherapi/yougou/goods/getList</span><span
            style="float:right; font-size:12px;">[<a href="#" title="TOP"
                                                     style="color:#FFF; text-decoration:none;">↑</a>]</span></div>
    <div class="api_method">请求方式： <span id="api_method"><select class='method'><option value='post'>post</option><option
            value='get'>get</select></span></div>
    <div class="api_desc">内容类型(Content-Type): application/json; charset=utf-8</div>
    <div class="api_url">接口地址： <span id="api_url">http://{Server}/hiveview-launcherapi/yougou/goods/getList</span></div>
    <div class="api_desc">接口说明： 商品种类下的所有顶级商品列表</div>
    <div class="api_result">
        返回值说明：<a href="#/RespCode" title="状态码">响应状态码</a>
        <ul>
            <li>pageIndex:分页索引</li>
            <li>pageSize:分页数量</li>
            <li>categorySn:商品种类编码</li>
            <li>goodsName:商品名称</li>
        </ul>
    </div>
    <div class="api_params">参数列表：
        <br/>
        <br/>
        <div id="params_list">
            page = <input type="text" name="page" value="1"><span class="notes">page</span><br/>
            rows = <input type="text" name="rows" value="10"><span class="notes">rows</span><br/>
            categorySn = <input type="text" name="categorySn" value=""><span class="notes">categorySn</span><br/>
            goodsName = <input type="text" name="goodsName" value=""><span class="notes">goodsName</span><br/>
        </div>
        <input type="button" class="post_json_btn" value="测试接口"/>
        <input type="button" class="api_test_btn" value="测试接口"/>
        <input type="button" class="api_reset_btn" value="重置结果"/>
    </div>
    <div class="return_area"></div>
</div>


<a name="/RespCode"></a>
<div class="api_block">
    <div class="api_name">状态码<span style="float:right; font-size:12px;">[<a href="#" title="TOP"
                                                                            style="color:#FFF; text-decoration:none;">↑</a>]</span>
    </div>
    <ul id="RespCode">
        <li>1 响应成功</li>

    </ul>
</div>
<div class="return_area"></div>
<div style="position:relative;z-index:9999999;">
    <div id="ajaxloading" style="display:none;">正在加载，请稍候 ...</div>
</div>
</body>
</html>
