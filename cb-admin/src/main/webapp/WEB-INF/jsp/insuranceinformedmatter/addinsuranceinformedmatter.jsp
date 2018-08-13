<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

    <title>反馈详情</title>

    <script type="text/javascript">
        $(function () {
            $('img[name="viewImg"]').click(function () {
                var width = $(this).width();
                if (width == 200) {
                    $(this).width(600);
                    $(this).height(600);
                }
                else {
                    $(this).width(200);
                    $(this).height(200);
                }
            });
        });
        $(document).ready(function () {
            $("#validateSubmitForm").validationEngine({
                autoHidePrompt: true, scroll: false, showOneMessage: true
            });
            $('#serNo').val("")
        });
    </script>
</head>
<body>

<jsp:include page="../layouts/left.jsp"/>

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
                    <li><a href="#">首页 </a></li>
                    <li><a href="#">保单管理 </a></li>
                    <li><a href="#">保险告知事项</a></li>
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
                    <h2>保险告知事项</h2>
                </div>
                <div class="pull-right">
                    <a class="btn btn-default" href="insuranceInformedMatters.do">
                        <i class="fa fa-reply"></i>
                    </a>
                </div>
            </div>
            <!-- End .inner-padding -->
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
            <!-- End .actionbar-->
            <div class="inner-padding">
                <!-- * data-asf-time = seconds, data-asf-expireafter = minutes * -->
                <fieldset>
                    <legend>事项添加</legend>
                    <form:form id="validateSubmitForm" action="addinsuranceInformedMatter.do"
                               cssClass="form-horizontal" method="post"
                               commandName="insuranceInformedMatter">
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>序号：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input path="serNo" id="serNo" value="" cssClass="form-control validate[required,minSize[1]]"
                                            maxlength="32"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>事项描述：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:textarea path="matterDescription"
                                               cssClass="form-control validate[required,minSize[1]]"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>类型：</label>
                            </div>
                            <div class="col-sm-3">
                                <select class="form-control simpleselect grid-filter" name="matterType">
                                    <option value="0" <c:if test="${0 eq metterType}">selected</c:if>>是否题</option>
                                    <option value="1" <c:if test="${1 eq metterType}">selected</c:if>>填空题</option>
                                </select>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>事项组：</label>
                            </div>
                            <div class="col-sm-3">
                                <select class="form-control simpleselect grid-filter" name="groupId">
                                    <option value="0">不选</option>
                                    <c:forEach items="${groups}" var="group">
                                        <option value="${group.groupId}">${group.description}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>是否启用：<span class="asterisk">*</span></label>
                            </div>
                            <div class="col-sm-3">
                                <div class="inline-labels">
                                    <form:radiobutton path="enabled" value="1"/>是
                                    <form:radiobutton path="enabled" value="0"/>否
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>投保人被选：<span class="asterisk">*</span></label>
                            </div>
                            <div class="col-sm-3">
                                <div class="inline-labels">
                                    <form:radiobutton path="insurePeople" value="1"/>是
                                    <form:radiobutton path="insurePeople" value="0"/>否
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>被保人被选：<span class="asterisk">*</span></label>
                            </div>
                            <div class="col-sm-3">
                                <div class="inline-labels">
                                    <form:radiobutton path="insuredPeople" value="1"/>是
                                    <form:radiobutton path="insuredPeople" value="0"/>否
                                </div>
                            </div>
                        </div>
                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="btn-group pull-right">
                                    <button class="btn btn-default"><i class="fa fa-save"></i>&nbsp;保&nbsp;存&nbsp;
                                    </button>
                                    <button type="reset" class="btn btn-default"><i class="fa fa-reply"></i>&nbsp;重&nbsp;置&nbsp;
                                    </button>
                                </div>
                            </div>
                        </div>
                    </form:form>
                </fieldset>
                <div class="spacer-40"></div>
                <div class="hr-totop"><span>Top</span></div>
                <div class="spacer-40"></div>
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
