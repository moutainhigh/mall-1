<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>文件管理</title>
    <script type="text/javascript" src="../js/media/media.js"></script>
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
                        <li><a href="#">首页</a></li>
                        <li><a href="#">资源管理</a></li>
                        <li><a href="#" class="active">图片管理</a></li>
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
                        <h2>图片管理</h2>
                    </div>
                    <div class="pull-right">

                        <%--<div class="btn-group">
                            <a class="btn btn-default" href="#">
                                <i class="fa fa-star"></i>
                            </a>
                            <a class="btn btn-default" href="#" id="modal-update-trigger">
                                Modal
                            </a>
                            <a class="btn btn-default" href="#">
                                <i class="fa fa-cog"></i>
                            </a>
                        </div>--%>
                    </div>
                </div>
                <!-- End .inner-padding -->
            </header>
            <!-- End #header-sec -->

            <div class="window">
                <div class="actionbar">
                    <div class="pull-left">
                        <p>Some text goes here</p>
                    </div>
                    <div class="pull-right">
                        <p>More text here...</p>
                    </div>
                </div>
                <div class="inner-padding">
                    <jsp:include page="mediaManager.jsp"/>
                    <div class="spacer-40"></div>
                    <div class="hr-totop"><span>Top</span></div>
                    <div class="spacer-40"></div>
                </div>
                <!-- End .inner-padding -->
                <div class="spacer-30"></div>
            </div>

            <jsp:include page="../layouts/footer.jsp"/>
        </div>
    </div>
</body>
</html>
