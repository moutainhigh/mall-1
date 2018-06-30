<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>系统参数</title>
    <script type="application/javascript">
        function searchTest(){
            var text=$("#searchWord").val();
            text = $.trim(text);
            if(null == text || "" == text){
                alert("搜索内容为空!");
                return ;
            }
            $.post('searchCommodities.do', {text:text}, function(data) {
                if(data != null){
                    var sbStr="";
                    for(var i=0;i<data.length;i++){
                        sbStr+=data[i].commodityName+"<br>";
                    }
                    $("#testLucence").html(sbStr);
                }else{
                    alert("无相关索引");
                }

            } );
        }

        function startIndex(){
            $.post('startIndex.do', {text:"1"}, function(data) {
                alert("索引已经开始重建")
            } );
        }

        function searchArticles(){
            var text=$("#searchWord1").val();
            text = $.trim(text);
            if(null == text || "" == text){
                alert("搜索内容为空!");
                return ;
            }
            $.post('searchArticles.do', {text:text}, function(data) {
                if(data != null ||data !=[]){
                    var sbStr="";
                    for(var i=0;i<data.length;i++){
                        sbStr+=data[i].articleTitle+"<br>";
                    }
                    $("#testLucence1").html(sbStr);
                }else{
                    alert("无相关索引");
                }

            } );
        }

        function startArticleIndex(){
            $.post('startArticleIndex.do', {text:"1"}, function(data) {
                alert("索引已经开始重建")
            } );
        }
    </script>
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
                    <li>参数设置</li>
                    <li><a href="../system/searchEngine.do">搜索引擎</a></li>
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
                    <h2>搜索引擎</h2>
                </div>
                <div class="pull-right">
                    <a class="btn btn-default" href="articles.do">
                        <i class="fa fa-backward"></i>
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
                <div class="example-box">
                    <div class="inner-padding">
                        <div class="subheading">
                            <h3>提示</h3>

                            <p> .</p>
                        </div>
                        <div class="row">
                            <div class="col-sm-3">
                                <label>操作</label>
                            </div>
                            <div class="col-sm-9">
                                <a class="btn btn-default" href="javascript:startIndex();">重建索引</a>
                                <input type="text" id="searchWord">
                                <a class="btn btn-default" href="javascript:searchTest();">商品测试</a>
                                <br>
                                <div class="helper-text-box">
                                    <div class="form-helper-header"> </div>
                                    <p id="testLucence">

                                    </p>
                                </div>
                                <!-- End .helper-text-box -->
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-3">
                                <label>操作</label>
                            </div>
                            <div class="col-sm-9">
                                <a class="btn btn-default" href="javascript:startArticleIndex();">重建文章索引</a>
                                <input type="text" id="searchWord1">
                                <a class="btn btn-default" href="javascript:searchArticles();">文章测试</a>
                                <div class="helper-text-box">
                                    <div class="form-helper-header"> </div>
                                    <p id="testLucence1">

                                    </p>
                                </div>
                                <!-- End .helper-text-box -->
                            </div>
                        </div>
                    </div>
                    <!-- End .inner-padding -->

                    <!-- Lockscreen -->
                    <div class="lockscreen" id="lockscreen-target">
                        <div class="lockscreen-overlay"></div>
                        <div class="lockscreen-modal">
                            <div class="lockscreen-placeholder"></div>
                        </div>
                    </div>

                </div>
            </div>

            <jsp:include page="../layouts/footer.jsp"/>
        </div>
    </div>
</div>

</body>
</html>
