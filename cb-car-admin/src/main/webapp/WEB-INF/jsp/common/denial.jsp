<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>操作无权限</title>

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
                    <li><a href="articles.do">文章管理</a></li>
                    <li class="active">${title}文章</li>
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
                    <h2>无权限访问</h2>
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
            <div class="alert alert-block alert-info alert-inline-mid">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <h4>Good to see you agian...</h4>
                I am a inline alert box and you can use me as an warning, error, info and success alert. Usage: <code>.alert-inline-mid</code>.
            </div>

            <div class="inner-padding">
                <div class="example-box">
                    <div class="inner-padding">
                        <div class="subheading">
                            <h3>提示</h3>

                            <p>你没有访问的权限.</p>
                        </div>
                        <div class="row">
                            <div class="col-sm-3">
                                <label>Default</label>
                            </div>
                            <div class="col-sm-9">
                                <a class="btn btn-default" href="#" id="lockscreen-button-trigger">Button type</a>
                                <a class="btn btn-default" href="#" id="lockscreen-form-trigger">Form type</a>
                                <a class="btn btn-default" href="#" id="lockscreen-target-trigger">Target</a>

                                <div class="helper-text-box">
                                    <div class="form-helper-header">Lock the screen</div>
                                    <p>
                                        This is a cool way to lock the screen(target or complete screen), once activated you only can unlock the screen by dragging a button to a certain value(this
                                        example 100),
                                        just clicking on a button or enter your password(form). Notice that this plugin only provides the unlocking type,
                                        the box is custom made and can be changed. For some visualisation effect we have added some loaders.
                                    </p>
                                </div>
                                <!-- End .helper-text-box -->
                            </div>
                        </div>
                        <div class="spacer-10"></div>
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
