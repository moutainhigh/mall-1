<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
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
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <c:set var="titleStr" scope="session" value="${title}"/>
    <title>修改供应商</title>
    <script type="text/javascript">
        $(document).ready(function () {
            $("input[type='radio'][name='type']").change(function () {
                //选择 1.个人，2.企业
                if ($(this).val() == '2') {
                    $("#enterpriseDiv").show();
                } else {
                    $("#enterpriseDiv").hide();
                }
            });
        });
    </script>
</head>
<body>
<jsp:include page="../layouts/left.jsp"/>
<jsp:include page="../layouts/sidebarRight.jsp"/>
<div id="main" class="clearfix">
    <header id="header-main">
        <div class="header-main-top">
            <div class="pull-left">
                <!-- * This is the responsive logo * -->
                <a href="#" id="logo-small"><h4></h4><h5></h5></a>
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
                    <li><a href="#">首页</a></li>
                    <li><a href="#">供应商管理</a></li>
                    <li><a href="#">供应商查询</a></li>
                    <li class="active">修改供应商</li>
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
                    <h2>修改供应商</h2>
                </div>
                <div class="pull-right">

                    <div class="btn-group">
                        <a class="btn btn-default" href="#">
                            <i class="fa fa-star"></i>
                        </a>
                        <a class="btn btn-default" href="#" id="modal-update-trigger">
                            Modal
                        </a>
                        <a class="btn btn-default" href="#">
                            <i class="fa fa-cog"></i>
                        </a>
                    </div>
                    <!-- End .btn-group -->

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
                </div>
                <div class="pull-right">
                    <a data-toggle-sidebar="right" class="btn small-toggle-btn" href="#"></a>
                </div>
            </div>
            <!-- End .actionbar-->
            <div class="inner-padding">

                <%--<c:if test="${titleStr =  '新增'}">--%>
                <form:form id="autosavethisform" data-asf-expireafter="1" data-asf-time="10" action="addSupplier.do" method="post" enctype="multipart/form-data" commandName="supplier">
                    <%--</c:if>--%>
                    <%--<c:if test="${titleStr =  '修改'}">--%>
                    <%--<form:form id="autosavethisform" data-asf-expireafter="1" data-asf-time="10" action="editSupplier.do" method="post" enctype="multipart/form-data" commandName="supplier">--%>
                    <%--</c:if>--%>
                    <fieldset>
                        <legend>修改供应商</legend>


                        <div class="row">
                            <div class="col-sm-3">
                                <label>供应商名称：</label>
                            </div>
                            <div class="col-sm-9">
                                <form:input cssClass="form-control" required="true" path="supplierName" aria-required="true"/>
                            </div>
                        </div>

                        <div class="spacer-10"></div>

                        <div class="row">
                            <div class="col-sm-3">
                                <label>供应商编码：</label>
                            </div>
                            <div class="col-sm-9">
                                <form:input cssClass="form-control" required="true" path="supplierCode" aria-required="true"/>
                            </div>
                        </div>

                        <div class="spacer-10"></div>

                        <div class="row">
                            <div class="col-sm-3">
                                <label>所在地：</label>
                            </div>
                            <div class="col-sm-9">
                                <form:input cssClass="form-control" required="true" path="location" aria-required="true"/>
                            </div>
                        </div>

                        <div class="spacer-10"></div>

                        <div class="row">
                            <div class="col-sm-3">
                                <label>备注：</label>
                            </div>
                            <div class="col-sm-9">
                                <form:textarea cssClass="form-control" required="true" path="remark" aria-required="true"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-3"></div>
                            <div class="col-sm-9">

                                <button class="btn btn-default pull-right" type="reset">返回</button>
                                <button class="btn btn-primary pull-right" type="submit">提交</button>
                            </div>
                        </div>
                    </fieldset>
                </form:form>
                <div class="spacer-40"></div>
                <div class="hr-totop"><span>Top</span></div>
                <div class="spacer-10"></div>

            </div>
            <!-- End .inner-padding -->
        </div>
        <!-- End .window -->

        <!-- ********************************************
             * FOOTER MAIN:                             *
             *                                          *
             * the part which contains things like      *
             * chat, buttons, copyright and             *
             * dropup menu(s).                          *
             ******************************************** -->

        <jsp:include page="../layouts/footer.jsp"/>
        <!-- End #footer-main -->
    </div>
    <!-- End #content -->
</div>
<!-- End #main -->

</body>
</html>
