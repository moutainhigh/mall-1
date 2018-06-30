
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>模板管理</title>
    <link href="../js/plugins/codeprettifier/prettify.min.css" type="text/css" rel="stylesheet">
    <script src="../js/plugins/codeprettifier/prettify.min.js"></script>
    <script type="application/javascript">
        function onSelect(e) {
            var data = $('#treeview').data('kendoTreeView').dataItem(e.node);
            if(data.nodeType=="file"){
                var fileName=data.path+"/"+data.name;
                $("#currentFilePath").attr("value",fileName);
                $("#currentPath").html("当前选择模板文件："+fileName);
                $.get("../templates/"+fileName,function(data,status){
                    $(".prettyprint").text(data).html();
                });
            }else{
                $("#currentPath").html("当前选择目录："+data.path);
            }

        }

        function loadDirFiles() {
            $("#breadcrumb li").remove();
            var array = currentPath.split("/");
            var path = "";
            for (var i = 0; i < array.length; i++) {
                if (i == 0) {
                    var an = $("<a>Home</a>");
                    an.click(function () {
                        naviToDir("/");
                    });
                    $("#breadcrumb").append($("<li/>").append(an));
                } else {
                    path += "\/";
                    path = path + array[i];
                    var an = $("<a>" + array[i] + "</a>");
                    var tpath = new String(path);
                    an.click(function () {
                        naviToDir(tpath);
                        //todo 存在错误
                    });
                    $("#breadcrumb").append($("<li/>").append(an));
                }
            }
            $.getJSON("getFileNodesByPath.do", {
                path: currentPath
            }, function (json) {
                $("#mix-1 li").remove();
                $('#eachFile').tmpl(json).appendTo("#mix-1");

                $('.lightbox').magnificPopup({
                    type:'image',
                    callbacks: {
                        open: function(){
                            // remove the marginright from the body added by this plugin
                            $('html').css({marginRight: 0});
                        },
                        close: function(){ }
                    }
                });
            });
        }

        function toEditTemplate(){
            var fPath = $("#currentFilePath").val();
            if(null ==fPath || undefined == fPath || "" == fPath){
                bootbox.alert("请选择模板文件");
                return ;
            }
            window.location.href = "toEditTemplate.do?templatePath=" + fPath;
        }
    </script>
    <style>
        #treeview{
            max-height: 570px;
        }
    </style>
</head>
<body>

<jsp:include page="../layouts/left.jsp"/>
<jsp:include page="../layouts/sidebarRight.jsp"/>

<div id="main" class="clearfix">

    <header id="header-main">
        <div class="header-main-top">
            <div class="pull-left">
                <a href="#" id="logo-small"><h4></h4><h5></h5></a>
            </div>
            <div class="pull-right">
                <a href="#" id="responsive-menu-trigger">
                    <i class="fa fa-bars"></i>
                </a>
            </div>
        </div>
        <div class="header-main-bottom">
            <div class="pull-left">
                <ul class="breadcrumb">
                    <li><a href="../console/dashboard.do">首页</a></li>
                    <li><a href="#">内容管理</a></li>
                    <li class="active">模板管理</li>
                </ul>
            </div>
            <div class="pull-right">
                <p>Version 1.0.0</p>
            </div>
        </div>
    </header>

    <div id="content" class="clearfix">


        <header id="header-sec">
            <div class="inner-padding">
                <div class="pull-left">
                    <h2>模板管理</h2>
                </div>
                <div class="pull-right">
                    <a class="btn btn-default" href="articles.do">
                        <i class="fa fa-reply"></i>
                    </a>
                </div>
            </div>
        </header>


        <div class="window">
            <div class="actionbar">
                <div class="pull-left">
                    <a href="#" class="btn small-toggle-btn" data-toggle-sidebar="left"></a>
                    <a href="#" id="lockscreen-slider-trigger" class="btn">
                        <i class="fa fa-lock"></i>&nbsp; Lock screen
                    </a>
                </div>
                <div class="pull-right">
                    <a data-toggle-sidebar="right" class="btn small-toggle-btn" href="#"></a>
                </div>
            </div>
            <div class="inner-padding">
                <section class="col-md-3">

                    <!-- New widget -->

                    <div class="widget">
                        <header>
                            <h2>模板目录</h2>
                        </header>
                        <div>
                            <div class="inner-padding" style="height: 600px;padding: 10px 5px 10px 5px">
                                <input type="hidden" id="currentFilePath"/>
                                <kendo:treeView name="treeview" dataTextField="name" select="onSelect" dataSpriteCssClassField="nodeType">
                                    <kendo:dataSource>
                                        <kendo:dataSource-transport>
                                            <kendo:dataSource-transport-read url="getTemplateNodesByPath.do" type="POST" contentType="application/json"/>
                                            <kendo:dataSource-transport-parameterMap>
                                                <script>
                                                    function parameterMap(options, type) {
                                                        return JSON.stringify(options);
                                                    }
                                                </script>
                                            </kendo:dataSource-transport-parameterMap>
                                        </kendo:dataSource-transport>
                                        <kendo:dataSource-schema>
                                            <kendo:dataSource-schema-hierarchical-model id="path" hasChildren="hasChildren"/>
                                        </kendo:dataSource-schema>
                                    </kendo:dataSource>
                                </kendo:treeView>
                            </div>
                        </div>
                    </div><!-- End .widget -->
                </section>
                <section class="col-md-9">
                    <div class="widget">
                        <header>
                            <div class="pull-left">
                                <h2><i class="fa fa-comments"></i> 模板内容&nbsp;&nbsp;&nbsp;<span id="currentPath"></span></h2>
                            </div>
                            <div class="pull-right">
                                <div class="btn-group">
                                    <a href="toAddTemplate.do" class="btn btn-default"><i class="fa fa-plus-circle"></i>&nbsp;新增</a>
                                    <a href="javascript:toEditTemplate();"  class="btn btn-default"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a>
                                    <%--<a href="javascript:toDeleteComplaint();"  class="btn btn-default"><i class="fa fa-trash-o"></i>&nbsp; 删除</a>--%>
                                </div>
                            </div>
                        </header>
                        <div>
                            <div class="inner-padding" style="height: 600px;overflow-y: auto">
                                <pre class="prettyprint">这里贴出你所要显示的代码</pre>
                            </div>
                        </div>
                    </div><!-- End .widget -->



                </section>
                <div class="spacer-40"></div>
                <div class="hr-totop"><span>Top</span></div>
                <div class="spacer-40"></div>
            </div>

            <jsp:include page="../layouts/footer.jsp"/>
        </div>
    </div>
</div>

</body>
</html>
