<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>修改物流价格</title>

    <script type="text/javascript">
        $(document).ready(function () {

            $("#validateSubmitForm").validationEngine({
                autoHidePrompt: true, scroll: false, showOneMessage: true
            });

        });

        function returnLogistics() {
            window.location.href = "logistics.do";
        }

        function changeProvinceCheck(obj) {
            var checked = $(obj).is(':checked');
            var cityCboxs = $(obj).parent().siblings(":first").find("input[name='cityCode']");
            cityCboxs.each(function (i, data) {
                if (checked) {
                    $(this).prop("checked", true);
                } else {
                    $(this).prop("checked", false);
                }
            });


        }

        function expandProvince(obj) {
            var cityDiv = $(obj).parent().siblings(":first");
            if (cityDiv.is(":hidden")) {
                cityDiv.show();
            } else {
                cityDiv.hide();
            }
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
                    <li><a href="#">配送管理</a></li>
                    <li><a href="#">物流查询</a></li>
                    <li class="active">编辑物流价格</li>
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
                    <h2>编辑物流公司</h2>
                </div>
                <div class="pull-right">
                    <a class="btn btn-default " href="logisticPrice.do?logisticId=${logisticPrice.logistic.logisticId}"><i class="fa fa-reply"></i></a>
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
                <form:form id="validateSubmitForm" cssClass="form-horizontal" action="editLogisticPrice.do" method="post" commandName="logisticPrice">
                    <form:hidden path="priceId"/>
                    <form:hidden path="logistic.logisticId"/>
                    <fieldset>
                        <legend>物流价格</legend>

                        <div class="row">
                            <div class="col-sm-2">
                                <label>首重重量(千克)：<span class="asterisk">*</span></label>
                            </div>
                            <div class="col-sm-2">
                                <form:input type="text" cssClass="form-control validate[required,custom[number]]" path="weight" maxlength="11"/>
                            </div>
                            <div class="col-sm-2">
                                <label>首重价格：<span class="asterisk">*</span></label>
                            </div>
                            <div class="col-sm-2">
                                <form:input type="text" cssClass="form-control validate[required,custom[number]]" path="weightPrice" maxlength="11"/>
                            </div>
                            <div class="col-sm-2">
                                <label>续重价格：<span class="asterisk">*</span></label>
                            </div>
                            <div class="col-sm-2">
                                <form:input type="text" cssClass="form-control validate[required,custom[number]]" path="continuePrice" maxlength="11"/>
                            </div>
                        </div>

                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>运送区域：</label>
                            </div>
                            <div class="col-sm-8">
                                <span class="asterisk">(不选择任何省市默认为全国)</span>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-12">
                                <ul>
                                    <c:forEach var="province" items="${provinces}">
                                        <li>
                                            <p><i class="fa  fa-plus-square" style="cursor: pointer" onclick="expandProvince(this)"></i>
                                                <c:set var="checkPall" value="false"/>
                                                <c:forEach var="city" items="${province.cities}">
                                                    <c:if test="${fn:contains(logisticPrice.cityCodes, city.code)}">
                                                        <c:set var="checkPall" value="true"/>
                                                    </c:if>
                                                </c:forEach>
                                                <input type="checkbox" value="${province.code}" <c:if test="${checkPall}">checked</c:if> onchange="changeProvinceCheck(this)"> ${province.name}</p>
                                            <div style="display: none">
                                                <c:forEach var="city" items="${province.cities}">
                                                    <input type="checkbox" name="cityCode" <c:if test="${fn:contains(logisticPrice.cityCodes, city.code)}">checked</c:if> value="${city.code}"> ${city.name}&nbsp;&nbsp;
                                                </c:forEach>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>

                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>

                        <div class="row">
                            <div class="col-sm-2">
                                <label>备注：</label>
                            </div>
                            <div class="col-sm-8">
                                <form:textarea class="form-control" path="remark" maxlength="255"></form:textarea>
                            </div>
                        </div>
                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="btn-group pull-right">
                                    <button id="saveBtn" class="btn btn-default" type="submit"><i class="fa fa-save"></i>&nbsp;保&nbsp;存&nbsp;</button>
                                    <button type="reset" class="btn btn-default"><i class="fa fa-reply"></i>&nbsp;重&nbsp;置&nbsp;</button>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                </form:form>
                <div class="spacer-40"></div>
                <div class="hr-totop"><span>Top</span></div>
                <div class="spacer-40"></div>
            </div>
        </div>


        <jsp:include page="../layouts/footer.jsp"/>
    </div>
</div>

</body>
</html>
