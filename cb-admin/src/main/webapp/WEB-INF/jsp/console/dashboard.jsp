<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if lt IE 7]> <html class="ie ie6 lte9 lte8 lte7 no-js"> <![endif]-->
<!--[if IE 7]> <html class="ie ie7 lte9 lte8 lte7 no-js"> <![endif]-->
<!--[if IE 8]> <html class="ie ie8 lte9 lte8 no-js"> <![endif]-->
<!--[if IE 9]> <html class="ie ie9 lte9 no-js"> <![endif]-->
<!--[if gt IE 9]> <html class="no-js"> <![endif]-->
<!--[if !IE]><!-->
<html class="no-js">                       <!--<![endif]-->
<head>
</head>
<body>
<jsp:include page="../layouts/left.jsp"/>
<!-- End aside -->


<div id="sidebar-sec" class="sidebar">

    <div class="sidebar-sec-top"></div>

    <!-- ********** -->
    <!-- NEW MODULE -->
    <!-- ********** -->

    <div class="sidebar-module">
        <form class="input-group">
            <input type="text" name="" class="form-control" placeholder="Type A head..." id="typeahead-sidebar-search"/>

            <div class="input-group-btn">
                <!-- can NOT be used with typeahead
                <a href="#" class="clear-input"><i class="fa fa-times-circle"></i></a>
                -->
                <button class="btn btn-default" type="submit"><i class="fa fa-search"></i></button>
            </div>
        </form>
    </div>
    <!-- End .sidebar-module -->

    <div class="sidebar-line"><!-- A seperator line --></div>

    <!-- * to remove the .tab-pane divs(wrapper). * -->

    <ul class="ext-tabs-sidebar">
        <li class="active">
            <a href="#sidebar-tab-3"><i class="fa fa-group"></i> Users</a>
        </li>
        <li>
            <a href="#sidebar-tab-4"><i class="fa fa-check"></i> ToDo</a>
        </li>
    </ul>
    <!-- End .ext-tabs-sidebar -->
    <div class="tab-content">
        <div id="sidebar-tab-3" class="tab-pane active">

            <!-- ********** -->
            <!-- NEW MODULE -->
            <!-- ********** -->

            <div class="sidebar-module">
                <ul class="mini-list">
                    <li>
                        <img src="../images/users/user-1.jpg" alt="" class="avatar"/>
                        <ul>
                            <li><a href="#" class="bold">Steven Watson</a></li>
                            <li><a href="#">dummyemail@mail.com</a></li>
                        </ul>
                    </li>
                    <li>
                        <img src="../images/users/user-2.jpg" alt="" class="avatar"/>
                        <ul>
                            <li><a href="#" class="bold">Maris Bradley</a></li>
                            <li><a href="#">dummyemail@mail.com</a></li>
                        </ul>
                    </li>
                    <li>
                        <img src="../images/users/user-3.jpg" alt="" class="avatar"/>
                        <ul>
                            <li><a href="#" class="bold">Wyatt Brooke</a></li>
                            <li><a href="#">dummyemail@mail.com</a></li>
                        </ul>
                    </li>
                    <li>
                        <img src="../images/users/user-4.jpg" alt="" class="avatar"/>
                        <ul>
                            <li><a href="#" class="bold">Elly Martel</a></li>
                            <li><a href="#">dummyemail@mail.com</a></li>
                        </ul>
                    </li>
                    <li>
                        <img src="../images/users/user-5.jpg" alt="" class="avatar"/>
                        <ul>
                            <li><a href="#" class="bold">Martin Gardenar</a></li>
                            <li><a href="#">dummyemail@mail.com</a></li>
                        </ul>
                    </li>
                    <li>
                        <img src="../images/users/user-6.jpg" alt="" class="avatar"/>
                        <ul>
                            <li><a href="#" class="bold">Debra Hopper</a></li>
                            <li><a href="#">dummyemail@mail.com</a></li>
                        </ul>
                    </li>
                    <li>
                        <img src="../images/users/user-7.jpg" alt="" class="avatar"/>
                        <ul>
                            <li><a href="#" class="bold">Nathan Rupertson</a></li>
                            <li><a href="#">dummyemail@mail.com</a></li>
                        </ul>
                    </li>
                </ul>
                <!-- End .mini-list -->
            </div>
            <!-- End .sidebar-module -->
        </div>
        <div id="sidebar-tab-4" class="tab-pane">

            <!-- ********** -->
            <!-- NEW MODULE -->
            <!-- ********** -->

            <div class="sidebar-module">
                <div class="sidebar-todo">
                    <div class="sidebar-todo-day">
                        <h5>Due Today</h5>
                        <ul>
                            <li>
                                <label class="line-through">Start project X <input type="checkbox" name="" checked/><span></span></label>
                            </li>
                            <li>
                                <label>Email the invoice<input type="checkbox" name=""/><span></span></label>
                            </li>
                            <li>
                                <label>Call client T<input type="checkbox" name=""/><span></span></label>
                            </li>
                            <li>
                                <label>Take a coffe break<input type="checkbox" name=""/><span></span></label>
                            </li>
                        </ul>
                    </div>
                    <!-- End .sidebar-todo-day -->
                    <div class="sidebar-todo-day">
                        <h5>Due Tomorrow <span class="indicator-pill">32</span></h5>
                        <ul>
                            <li>
                                <label>Meeting with client T<input type="checkbox" name=""/><span></span></label>
                            </li>
                            <li>
                                <label>Meeting with client X<input type="checkbox" name=""/><span></span></label>
                            </li>
                            <li>
                                <label class="line-through">Buy new apple<input type="checkbox" name="" checked/><span></span></label>
                            </li>
                        </ul>
                    </div>
                    <!-- End .sidebar-todo-day -->
                    <div class="sidebar-todo-day">
                        <h5>Due Next Week</h5>
                        <ul>
                            <li>
                                <label>Start project T<input type="checkbox" name=""/><span></span></label>
                            </li>
                            <li>
                                <label>Buy new headphones<input type="checkbox" name=""/><span></span></label>
                            </li>
                        </ul>
                    </div>
                    <!-- End .sidebar-todo-day -->
                </div>
                <!-- End .sidebar-todo -->
            </div>
            <!-- End .sidebar-module -->
        </div>
    </div>
    <!-- End .tab-content -->

    <div class="sidebar-line"><!-- A seperator line --></div>

    <div class="sidebar-module">
        <div class="circular-stats">
            <div class="circular-stats-inner">
                <div class="circular-stats-data">
                    <strong>2779</strong>
                    <span>+ 31%</span>
                </div>
                <input class="knob" data-width="100" data-cursor="false" data-fgColor="#aaaaaa" data-bgColor="#cccccc" data-thickness=".20"
                       value="31" data-readOnly="true" data-angleOffset="-0" data-displayInput="false">
            </div>
        </div>
        <!-- End .circular-stats -->
        <div class="circular-stats-infobox">
            <strong>This day</strong>
            <span>Lorem ipsum</span>
            <a href="#" class="btn btn-default">View</a>
        </div>
        <!-- End .circular-stats-infobox -->
        <div class="spacer-20"></div>
        <div class="circular-stats">
            <div class="circular-stats-inner">
                <div class="circular-stats-data">
                    <strong>12899</strong>
                    <span>+ 77%</span>
                </div>
                <input class="knob" data-width="100" data-cursor="false" data-fgColor="#aaaaaa" data-bgColor="#cccccc" data-thickness=".20"
                       value="77" data-readOnly="true" data-angleOffset="-0" data-displayInput="false">
            </div>
        </div>
        <!-- End .circular-stats -->
        <div class="circular-stats-infobox">
            <strong>This month</strong>
            <span>Lorem ipsum</span>
            <a href="#" class="btn btn-default">View</a>
        </div>
        <!-- End .circular-stats-infobox -->
        <div class="spacer-20"></div>
        <div class="circular-stats">
            <div class="circular-stats-inner">
                <div class="circular-stats-data">
                    <strong>82229</strong>
                    <span>+ 89%</span>
                </div>
                <input class="knob" data-width="100" data-cursor="false" data-fgColor="#aaaaaa" data-bgColor="#cccccc" data-thickness=".20"
                       value="89" data-readOnly="true" data-angleOffset="-0" data-displayInput="false">
            </div>
        </div>
        <!-- End .circular-stats -->
        <div class="circular-stats-infobox">
            <strong>This year</strong>
            <span>Lorem ipsum</span>
            <a href="#" class="btn btn-default">View</a>
        </div>
        <!-- End .circular-stats-infobox -->
    </div>
    <!-- End .sidebar-module -->
</div>
<!-- End #sidebar-sec -->

<div id="main" class="clearfix">
    <header id="header-main">
        <div class="header-main-top">
            <div class="pull-left">

                <!-- * This is the responsive logo * -->

                <a href="../index.html" id="logo-small"><h4>karma</h4><h5>/webapp</h5></a>
            </div>
            <div class="pull-right">

                <!-- * This is the trigger that will show/hide the menu * -->
                <!-- * if the layout is in responsive mode              * -->

                <a href="#" id="responsive-menu-trigger">
                    <i class="fa fa-bars"></i>
                </a>
            </div>
        </div>
        <!-- End #header-main-top -->
        <div class="header-main-bottom">
            <div class="pull-left">
                <ul class="breadcrumb">
                    <li class="active">首页</li>
                </ul>
                <!-- End .breadcrumb -->
            </div>
            <div class="pull-right">
                <p>Version 1.0.0</p>
            </div>
        </div>
        <!-- End #header-main-bottom -->
    </header>
    <!-- End #header-main -->

    <div id="content" class="clearfix">


        <header id="header-sec">
            <div class="inner-padding">
                <div class="pull-left">
                    <h2>首页</h2>
                </div>
                <div class="pull-right">
                    <div class="dropdown">
                        <%--<a class="btn btn-default dropdown-toggle" data-toggle="dropdown" href="#">--%>
                            <%--<i class="fa fa-comments"></i>--%>
                            <%--<span class="indicator-dot">3</span>--%>
                        <%--</a>--%>

                        <%--<div role="menu" class="dropdown-menu pull-center ext-dropdown-comments">--%>
                            <%--<div class="ext-dropdown-header">--%>
                                <%--<i class="fa fa-comments"></i>--%>
                                <%--<h5>Comments</h5>--%>
                                <%--<a href="#" class="btn btn-default btn-sm delete-master"><i class="fa fa-trash-o"></i></a>--%>
                                <%--<span class="indicator-dot">3</span>--%>
                            <%--</div>--%>
                            <%--<div class="ext-dropdown-comments-content">--%>
                                <%--<div>--%>
                                    <%--<img src="../images/users/user-1.jpg" alt="" class="avatar"/>--%>
                                    <%--<a href="#">Karma, a good thing</a>--%>
                                    <%--<ul>--%>
                                        <%--<li><span>Posted by:</span> <a href="#">Steven</a></li>--%>
                                        <%--<li><span>Date:</span> Dec 11, 2012</li>--%>
                                        <%--<li>--%>
                                            <%--<span>Actions:</span>--%>
                                            <%--<a href="#">Read</a> ---%>
                                            <%--<a href="#" class="delete">Delete</a>--%>
                                        <%--</li>--%>
                                    <%--</ul>--%>
                                <%--</div>--%>
                                <%--<div>--%>
                                    <%--<img src="../images/users/user-4.jpg" alt="" class="avatar"/>--%>
                                    <%--<a href="#">A simple, fast way to reduce stress</a>--%>
                                    <%--<ul>--%>
                                        <%--<li><span>Posted by:</span> <a href="#">Linda</a></li>--%>
                                        <%--<li><span>Date:</span> Dec 3, 2012</li>--%>
                                        <%--<li>--%>
                                            <%--<span>Actions:</span>--%>
                                            <%--<a href="#">Read</a> ---%>
                                            <%--<a href="#" class="delete">Delete</a>--%>
                                        <%--</li>--%>
                                    <%--</ul>--%>
                                <%--</div>--%>
                                <%--<div>--%>
                                    <%--<img src="../images/users/user-6.jpg" alt="" class="avatar"/>--%>
                                    <%--<a href="#">Blog: karma and revenge</a>--%>
                                    <%--<ul>--%>
                                        <%--<li><span>Posted by:</span> <a href="#">Debra Hopper</a></li>--%>
                                        <%--<li><span>Date:</span> Nov 18, 2012</li>--%>
                                        <%--<li>--%>
                                            <%--<span>Actions:</span>--%>
                                            <%--<a href="#">Read</a> ---%>
                                            <%--<a href="#" class="delete">Delete</a>--%>
                                        <%--</li>--%>
                                    <%--</ul>--%>
                                <%--</div>--%>
                                <%--<span>No new comments</span>--%>
                            <%--</div>--%>
                            <%--<div class="ext-dropdown-footer">--%>
                                <%--<p>Updated: 11/12/2012 - 14:12</p>--%>
                                <%--<a href="#" class="btn btn-default btn-sm"><i class="fa fa-caret-right"></i></a>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    </div>
                    <!-- End .dropdown -->
                    <%--<div class="btn-group">--%>
                        <%--<a class="btn btn-default" href="#">--%>
                            <%--<i class="fa fa-star"></i>--%>
                        <%--</a>--%>
                        <%--<a class="btn btn-default" href="#" id="modal-update-trigger">--%>
                            <%--Modal--%>
                        <%--</a>--%>
                        <%--<a class="btn btn-default" href="#">--%>
                            <%--<i class="fa fa-cog"></i>--%>
                        <%--</a>--%>
                    <%--</div>--%>
                    <!-- End .btn-group -->
                    <%--<div class="dropdown">--%>
                        <%--<a class="btn btn-default dropdown-toggle" data-toggle="dropdown" href="#">--%>
                            <%--<i class="fa fa-inbox"></i>--%>
                            <%--<span class="indicator-dot">3</span>--%>
                        <%--</a>--%>

                        <%--<div role="menu" class="dropdown-menu pull-right ext-dropdown-inbox">--%>
                            <%--<div class="ext-dropdown-header">--%>
                                <%--<h5>Inbox</h5>--%>
                                <%--<a href="#" class="btn btn-default btn-sm delete-master"><i class="fa fa-trash-o"></i></a>--%>
                                <%--<span class="indicator-dot">3</span>--%>
                            <%--</div>--%>
                            <%--<div class="ext-dropdown-inbox-content">--%>
                                <%--<div>--%>
                                    <%--<a href="#">He did you get my new blog post?</a>--%>
                                    <%--<ul class="nav">--%>
                                        <%--<li><span>Send by:</span> <a href="#">Debra Hopper</a></li>--%>
                                        <%--<li><span>Date:</span> Dec 12, 2012 - 14:03:09</li>--%>
                                        <%--<li>--%>
                                            <%--<span>Actions:</span>--%>
                                            <%--<a href="#">Reply</a> ---%>
                                            <%--<a href="#">Read</a> ---%>
                                            <%--<a href="#">Spam</a> ---%>
                                            <%--<a href="#" class="delete">Delete</a>--%>
                                        <%--</li>--%>
                                    <%--</ul>--%>
                                <%--</div>--%>
                                <%--<div>--%>
                                    <%--<a href="#">I really love your karma theme</a>--%>
                                    <%--<ul class="nav">--%>
                                        <%--<li><span>Send by:</span> <a href="#">Nathan Rupertson</a></li>--%>
                                        <%--<li><span>Date:</span> Dec 3, 2012 - 22:44:12</li>--%>
                                        <%--<li>--%>
                                            <%--<span>Actions:</span>--%>
                                            <%--<a href="#">Reply</a> ---%>
                                            <%--<a href="#">Read</a> ---%>
                                            <%--<a href="#">Spam</a> ---%>
                                            <%--<a href="#" class="delete">Delete</a>--%>
                                        <%--</li>--%>
                                    <%--</ul>--%>
                                <%--</div>--%>
                                <%--<div>--%>
                                    <%--<a href="#">Feedback of a happy customer</a>--%>
                                    <%--<ul class="nav">--%>
                                        <%--<li><span>Send by:</span> <a href="#">Steven Watson</a></li>--%>
                                        <%--<li><span>Date:</span> Dec 11, 2012 - 10:53:59</li>--%>
                                        <%--<li>--%>
                                            <%--<span>Actions:</span>--%>
                                            <%--<a href="#">Reply</a> ---%>
                                            <%--<a href="#">Read</a> ---%>
                                            <%--<a href="#">Spam</a> ---%>
                                            <%--<a href="#" class="delete">Delete</a>--%>
                                        <%--</li>--%>
                                    <%--</ul>--%>
                                <%--</div>--%>
                                <%--<span>No new emails</span>--%>
                            <%--</div>--%>
                            <%--<div class="ext-dropdown-footer">--%>
                                <%--<div class="progress bar-small">--%>
                                    <%--<div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">--%>
                                        <%--<span class="sr-only">60% Complete</span>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<p>60%</p>--%>
                                <%--<a href="#" class="btn btn-default btn-sm"><i class="fa fa-caret-right"></i></a>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <!-- End .dropdown -->
                </div>
            </div>
            <!-- End .inner-padding -->
        </header>
        <!-- End #header-sec -->


        <div class="window">
            <div class="actionbar">
                <div class="pull-left">
                    <a href="#" class="btn small-toggle-btn" data-toggle-sidebar="left"></a>
                    <a href="#" id="lockscreen-slider-trigger" class="btn">
                        <i class="fa fa-lock"></i>&nbsp; Lock screen
                    </a>

                    <div class="dropdown">
                        <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" href="#">
                            <img src="../images/icons/flags/shiny/16/United-Kingdom.png" alt=""/>
                            <i class="fa fa-caret-down"></i>
                        </a>
                        <ul role="menu" class="dropdown-menu ext-flags">
                            <li>
                                <a href="#">English <img src="../images/icons/flags/shiny/16/United-Kingdom.png" alt=""/></a>
                            </li>
                            <li>
                                <a href="#">German <img src="../images/icons/flags/shiny/16/Germany.png" alt=""/></a>
                            </li>
                            <li>
                                <a href="#">French <img src="../images/icons/flags/shiny/16/France.png" alt=""/></a>
                            </li>
                            <li>
                                <a href="#">Chinees <img src="../images/icons/flags/shiny/16/China.png" alt=""/></a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="pull-right">
                    <a href="#" class="btn small-toggle-btn" data-toggle-sidebar="right"></a>
                </div>
            </div>
            <!-- End .actionbar-->
            <div class="row ext-raster">
                <div class="col-sm-8">
                    <div class="row ext-raster">
                        <div class="col-sm-4">
                            <div class="inner-padding">
                                <div class="progress-project">
                                    <div class="progress-project-header">
                                        <h5>本月销售统计</h5><span>90%</span>
                                    </div>
                                    <div class="progress">
                                        <div class="progress-bar" role="progressbar" aria-valuenow="90" aria-valuemin="0" aria-valuemax="100" style="width: 90%;">
                                            <span class="sr-only">90% Complete</span>
                                        </div>
                                    </div>
                                </div>
                                <!-- End .progress-project -->
                            </div>
                            <!-- End .inner-padding -->
                        </div>
                        <span class="breakpoint-sm ext-raster-line-4"></span>

                        <div class="col-sm-8">
                            <div class="grouped raster-4-blocks">
                                <div class="duo-val-block">
                                    <span class="font-big">${orderMonthCount}</span>
                                    <span class="font-small">订单数量</span>
                                </div>
                                <!-- End .duo-val-block -->
                                <div class="duo-val-block">
                                    <span class="font-big">${orderMonthPayedCount}</span>
                                    <span class="font-small">已支付数量</span>
                                </div>
                                <!-- End .duo-val-block -->
                                <div class="duo-val-block">
                                    <span class="font-big">
                                        <fmt:formatNumber type="number" value="${orderMonthTotalPrice}" pattern="0.00" maxFractionDigits="2"/>
                                    </span>
                                    <span class="font-small">订单金额</span>
                                </div>
                                <div class="duo-val-block">
                                    <span class="font-big">
                                        <fmt:formatNumber type="number" value="${orderMonthPayedTotalPrice}" pattern="0.00" maxFractionDigits="2"/>
                                    </span>
                                    <span class="font-small">实收金额</span>
                                </div>
                            </div>
                        </div>
                        <span class="ext-raster-line-bottom"></span>
                    </div>
                    <!-- End .row -->
                    <div class="row">
                        <div class="col-sm-12">

                            <div class="inner-padding">
                                <div class="spacer-20"></div>
                                <div id="chart-mixed-1" style="width:100%; height:240px"></div>
                                <div class="spacer-20"></div>
                                <div class="subheading">
                                    <h3>月度销售统计</h3>

                                    <p>月度销售统计.</p>
                                </div>
                                <div class="row">

                                    <!-- Start grid -->

                                    <section class="col-sm-12">

                                        <!-- New widget -->

                                        <div class="widget">
                                            <header>
                                                <h2>最新订单</h2>
                                            </header>
                                            <div>
                                                <div class="scrollbar-y">
                                                    <table class="table">
                                                        <thead>
                                                        <tr>
                                                            <th scope="col" class="th-square"><label><input type="checkbox" class="checkbox-master"/><span></span></label></th>
                                                            <th scope="col">订单号</th>
                                                            <th scope="col">客户名称</th>
                                                            <th scope="col">金额</th>
                                                            <th scope="col">订单状态</th>
                                                            <th scope="col">下单时间</th>
                                                            <th scope="col">操作</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <c:forEach var="order" items="${orders}">
                                                            <tr>
                                                                <td><label><input type="checkbox"/><span></span></label></td>
                                                                <td><a href="../sale/getOrderDetailById.do?orderId=${order.orderId}">${order.orderCode}</a></td>
                                                                <td>${order.customer.accountName}</td>
                                                                <td>${order.feeTotal}</td>
                                                                <td>
                                                                    <c:choose>
                                                                        <c:when test="${order.orderState=='PENDING_PAYMENT'}">待付款</c:when>
                                                                        <c:when test="${order.orderState=='PAID_PAYMENT'}">待发货</c:when>
                                                                        <c:when test="${order.orderState=='OUT_STOCK'}">已发货</c:when>
                                                                        <c:when test="${order.orderState=='RECEIVED'}">已签收</c:when>
                                                                        <c:when test="${order.orderState=='REFUSE'}">拒签收</c:when>
                                                                        <c:when test="${order.orderState=='RETURN_GOODS'}">退货</c:when>
                                                                        <c:when test="${order.orderState=='CHANGE_GOODS'}">换货</c:when>
                                                                        <c:when test="${order.orderState=='CANCELED'}">已取消</c:when>
                                                                        <c:when test="${order.orderState=='WAIT_EVALUATE'}">待评价</c:when>
                                                                        <c:when test="${order.orderState=='TIMEOUT'}">超时</c:when>
                                                                        <c:when test="${order.orderState=='SUCCESS'}">交易成功</c:when>
                                                                    </c:choose>
                                                                </td>
                                                                <td><fmt:formatDate value="${order.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                                                                <td><a href="../sale/getOrderDetailById.do?orderId=${order.orderId}">查看</a></td>
                                                            </tr>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- End .widget -->

                                    </section>
                                </div>
                                <div class="spacer-20"></div>
                                <div class="hr-totop"><span>Top</span></div>
                                <div class="spacer-40"></div>

                            </div>
                            <!-- End .inner-padding -->
                        </div>
                    </div>
                    <!-- End .row -->
                </div>
                <span class="breakpoint-sm ext-raster-line-8"></span>

                <div class="col-sm-4">
                    <ul class="ext-tabs tabs-negative-position">
                        <li class="active">
                            <a href="#content-tab-10">最近商品评价</a>
                        </li>
                        <li>
                            <a href="#content-tab-11">最新客户投诉</a>
                        </li>
                    </ul>
                    <!-- End .ext-tabs -->
                    <div class="tab-content">
                        <div id="content-tab-10" class="tab-pane active">
                            <div class="inner-padding">
                                <div class="subheading">
                                    <h3>最近评价</h3>

                                    <div class="btn-group pull-right">
                                        <a href="#" class="btn btn-default btn-sm tooltip-top" title="Show less" id="as-min-trigger">
                                            <i class="fa fa-th-large"></i>
                                        </a>
                                        <a href="#" class="btn btn-default btn-sm active tooltip-top" title="Show more" id="as-plus-trigger">
                                            <i class="fa fa-bars"></i>
                                        </a>
                                    </div>
                                </div>
                                <div class="activity-stream">
                                    <c:forEach var="evaluate" items="${evaluates}">
                                        <div class="activity-stream-item" id="as-item-${evaluate.evaluateId}">
                                            <img src="../images/users/user-5.jpg" alt="" class="avatar"/>

                                            <div class="activity-stream-content">
                                                <div class="activity-stream-user">
                                                    <a href="#" class="bold">${evaluate.customer.accountName}</a> <span class="busy-dot"></span>
                                                    <small class="text-muted pull-right"><fmt:formatDate value="${evaluate.createTime}" pattern="yyyy-MM-dd HH:mm"/></small>
                                                </div>
                                                <div class="activity-stream-title">
                                                        ${evaluate.commodity.commodityName}
                                                </div>
                                                <div class="activity-stream-sub">
                                                    <i>${evaluate.content}</i>

                                                    <%--<div class="thumbs-list thumbs-small">--%>
                                                        <%--<ul>--%>
                                                            <%--<li>--%>
                                                                <%--<img src="../images/gallery/60x60/img-1.jpg" alt=""/>--%>
                                                            <%--</li>--%>
                                                            <%--<li>--%>
                                                                <%--<img src="../images/gallery/60x60/img-2.jpg" alt=""/>--%>
                                                            <%--</li>--%>
                                                            <%--<li>--%>
                                                                <%--<img src="../images/gallery/60x60/img-3.jpg" alt=""/>--%>
                                                            <%--</li>--%>
                                                            <%--<li>--%>
                                                                <%--<img src="../images/gallery/60x60/img-4.jpg" alt=""/>--%>
                                                            <%--</li>--%>
                                                            <%--<li>--%>
                                                                <%--<img src="../images/gallery/60x60/img-5.jpg" alt=""/>--%>
                                                            <%--</li>--%>
                                                        <%--</ul>--%>
                                                    <%--</div>--%>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                                <!-- End .activity-stream -->
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
                        <div id="content-tab-11" class="tab-pane">
                            <div class="inner-padding">
                                <div class="subheading">
                                    <h3>最近投诉</h3>
                                </div>
                                <div class="activity-stream">
                                    <c:forEach var="complaint" items="${complaints}">
                                        <div class="activity-stream-item" id="as-item-${complaint.complaintId}">
                                            <img src="../images/users/user-5.jpg" alt="" class="avatar"/>

                                            <div class="activity-stream-content">
                                                <div class="activity-stream-user">
                                                    <a href="#" class="bold">${complaint.customer.accountName}</a> <span class="busy-dot"></span>
                                                    <small class="text-muted pull-right"><fmt:formatDate value="${complaint.createTime}" pattern="yyyy-MM-dd HH:mm"/></small>
                                                </div>
                                                <div class="activity-stream-title">
                                                        ${complaint.title}
                                                </div>
                                                <div class="activity-stream-sub">
                                                        ${complaint.content}
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>

                        </div>
                    </div>
                    <hr/>

                </div>
                <span class="ext-raster-line-bottom"></span>
            </div>
            <div class="inner-padding">
                <div class="row">
                    <div class="col-sm-12">
                        <div class="project-block">
                            <header>
                                <h3>用户统计</h3>

                                
                            </header>
                            <ul>
                                <li>
                                    <div class="pull-left">
                                        注册用户总数:
                                    </div>
                                    <div class="pull-right">
                                       ${customerCount}
                                    </div>
                                </li>
                                <li>
                                    <div class="pull-left">
                                        本月注册用户:
                                    </div>
                                    <div class="pull-right">
                                        ${customerCountMonthly}
                                    </div>
                                </li>
                                <li>
                                    <div class="pull-left">
                                        在线用户数:
                                    </div>
                                    <div class="pull-right">
                                        10
                                    </div>
                                </li>
                                <li>
                                    <div class="pull-left">
                                        历史最大在线用户数:
                                    </div>
                                    <div class="pull-right">
                                        10
                                    </div>
                                </li>
                            </ul>
                            <footer>
                                <div class="pull-left">
                                    注册用户总数:
                                </div>
                                <div class="pull-right">
                                    <span class="label label-primary">${customerCount}</span>
                                </div>
                            </footer>
                        </div>
                        <!-- End .project-block -->
                        <div class="project-block">
                            <header>
                                <h3>商家统计</h3>

                                
                            </header>
                            <ul>
                                <li>
                                    <div class="pull-left">
                                        注册商家数量:
                                    </div>
                                    <div class="pull-right">
                                        ${sellerCount}
                                    </div>
                                </li>
                                <li>
                                    <div class="pull-left">
                                        End date:
                                    </div>
                                    <div class="pull-right">
                                        11 Jan
                                    </div>
                                </li>
                                <li>
                                    <div class="pull-left">
                                        Developer:
                                    </div>
                                    <div class="pull-right">
                                        Wyatt
                                    </div>
                                </li>
                                <li>
                                    <div class="pull-left">
                                        Design by:
                                    </div>
                                    <div class="pull-right">
                                        Apple
                                    </div>
                                </li>
                            </ul>
                            <footer>
                                <div class="pull-left">
                                    注册商家数量:
                                </div>
                                <div class="pull-right">
                                    <span class="label label-danger">${sellerCount}</span>
                                </div>
                            </footer>
                        </div>
                        <!-- End .project-block -->
                        <div class="project-block">
                            <header>
                                <h3>商品统计</h3>

                                
                            </header>
                            <ul>
                                <li>
                                    <div class="pull-left">
                                        货品总数量:
                                    </div>
                                    <div class="pull-right">
                                        ${productCount}
                                    </div>
                                </li>
                                <li>
                                    <div class="pull-left">
                                        上架商品数:
                                    </div>
                                    <div class="pull-right">
                                       ${commodityUpShelvesCount}
                                    </div>
                                </li>
                                <li>
                                    <div class="pull-left">
                                        热卖商品数:
                                    </div>
                                    <div class="pull-right">
                                        ${commodityUpShelvesPopularCount}
                                    </div>
                                </li>
                                <li>
                                    <div class="pull-left">
                                        推荐商品数:
                                    </div>
                                    <div class="pull-right">
                                        ${commodityUpShelvesRecommendCount}
                                    </div>
                                </li>
                            </ul>
                            <footer>
                                <div class="pull-left">
                                    商品总数量:
                                </div>
                                <div class="pull-right">
                                    <span class="label label-success">${commodityCount}</span>
                                </div>
                            </footer>
                        </div>
                        <!-- End .project-block -->
                        <div class="project-block">
                            <header>
                                <h3>内容统计</h3>

                                
                            </header>
                            <ul>
                                <li>
                                    <div class="pull-left">
                                        文章总数量:
                                    </div>
                                    <div class="pull-right">
                                       ${articleCount}
                                    </div>
                                </li>
                                <li>
                                    <div class="pull-left">
                                        频道总数:
                                    </div>
                                    <div class="pull-right">
                                       ${channelCount}
                                    </div>
                                </li>
                                <li>
                                    <div class="pull-left">
                                        栏目数量:
                                    </div>
                                    <div class="pull-right">
                                        ${programaCount}
                                    </div>
                                </li>
                                <li>
                                    <div class="pull-left">
                                        专题总数量:
                                    </div>
                                    <div class="pull-right">
                                        ${subjectCount}
                                    </div>
                                </li>
                            </ul>
                            <footer>
                                <div class="pull-left">
                                    文章总数量:
                                </div>
                                <div class="pull-right">
                                    <span class="label label-default">${articleCount}</span>
                                </div>
                            </footer>
                        </div>
                        <!-- End .project-block -->
                        <div class="project-block">
                            <header>
                                <h3>销售统计</h3>
                                
                            </header>
                            <ul>
                                <li>
                                    <div class="pull-left">
                                        总订单数量:
                                    </div>
                                    <div class="pull-right">
                                       ${orderCount}
                                    </div>
                                </li>
                                <li>
                                    <div class="pull-left">
                                        成交商品种类数量:
                                    </div>
                                    <div class="pull-right">
                                        ${orderCommodityCount}
                                    </div>
                                </li>
                                <li>
                                    <div class="pull-left">
                                        成交货品数量:
                                    </div>
                                    <div class="pull-right">
                                        ${orderProductCount}
                                    </div>
                                </li>
                                <li>
                                    <div class="pull-left">
                                        平均每订单金额:
                                    </div>
                                    <div class="pull-right">
                                        <fmt:formatNumber type="number" value="${orderAvgPrice}" pattern="0.00" maxFractionDigits="2"/>
                                    </div>
                                </li>
                            </ul>
                            <footer>
                                <div class="pull-left">
                                    总销售额:
                                </div>
                                <div class="pull-right">
                                    <span class="label label-warning">
                                        <fmt:formatNumber type="number" value="${orderTotalPrice}" pattern="0.00" maxFractionDigits="2"/>
                                    </span>
                                </div>
                            </footer>
                        </div>
                        <!-- End .project-block -->
                        <div class="project-block">
                            <header>
                                <h3>设备统计</h3>

                                <p class="text-muted">厨电设备数据分析</p>
                            </header>
                            <ul>
                                <li>
                                    <div class="pull-left">
                                        注册PAD数:
                                    </div>
                                    <div class="pull-right">
                                       ${concentCount}
                                    </div>
                                </li>
                                <li>
                                    <div class="pull-left">
                                        当前在线数:
                                    </div>
                                    <div class="pull-right">
                                       1
                                    </div>
                                </li>
                                <li>
                                    <div class="pull-left">
                                        历史最大在线数:
                                    </div>
                                    <div class="pull-right">
                                       1
                                    </div>
                                </li>
                                <li>
                                    <div class="pull-left">
                                        接入厨电总数:
                                    </div>
                                    <div class="pull-right">
                                        ${deviceCount}
                                    </div>
                                </li>
                            </ul>
                            <footer>
                                <div class="progress">
                                    <div class="progress-bar" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 45%;">
                                        <span class="sr-only">45% Complete</span>
                                    </div>
                                </div>
                            </footer>
                        </div>
                        <!-- End .project-block -->
                    </div>
                </div>
            </div>
        </div>
        <!-- End .window -->


        <jsp:include page="../layouts/footer.jsp"/>
        <!-- End #footer-main -->
    </div>
    <!-- End #content -->
</div>
<!-- End #main -->
</div>
<!-- End #container -->

<!--Modal -->
<div class="modal fade" id="modal-update" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Update 13.2.1 available</h4>
            </div>
            <div class="modal-body">
                <div class="row" style="margin-bottom:-20px">
                    <div class="col-xs-3">
                        <i class="fa fa-download" style="font-size:120px;color:#ccc"></i>
                    </div>
                    <div class="col-xs-9">
                        <p>
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse laoreet molestie justo at pulvinar.
                            In hac habitasse platea dictumst. Proin accumsan, tellus quis varius molestie, mi dolor facilisis risus,
                            quis tristique neque augue eget nunc. Curabitur turpis sapien, lacinia in lacinia nec,
                        </p>

                        <div class="spacer-20"></div>
                        <h4>Whats new in version 13.2.1</h4>

                        <div class="spacer-20"></div>
                        <ul>
                            <li>Suspendisse laoreet molestie justo at pulvinar.</li>
                            <li>Proin accumsan, tellus quis varius molestie, mi dolor facilisis risus.</li>
                            <li>In hac habitasse platea dictumst. Proin accumsan, tellus quis varius molestie dolum ipkut. Curabitur turpis sapien lorem.</li>
                            <li>Curabitur turpis sapien, lacinia in lacinia necr.</li>
                            <li>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse laoreet molestie justo.</li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" data-dismiss="modal">Close</button>
                <button class="btn btn-primary pull-right">Get it now</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
