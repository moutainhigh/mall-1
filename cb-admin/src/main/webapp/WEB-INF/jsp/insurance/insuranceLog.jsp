<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>操作日志</title>
    <script type="application/javascript">
        $(document).ready(function () {
            $("#createTime").kendoDatePicker({
                format: "yyyy-MM-dd",
                culture: "zh-CN",
                parseFormats: ["yyyy-MM-dd"]
            });
            $("#createTimes").kendoDatePicker({
                format: "yyyy-MM-dd",
                culture: "zh-CN",
                parseFormats: ["yyyy-MM-dd"]
            });
        });

        function formatOrderState(orderState) {
            switch (orderState) {
                case "UN_PAID":
                    return "待支付";
                case "ON_PAID":
                    return "已支付";
                case "BEEN_COMPLETED":
                    return "已完成";
                case "UN_SURRENDER":
                    return "退保审核";
                case "ON_SURRENDER":
                    return "已退保";
            }
        }

        function excelInsuranceOrder() {
            window.location.href = "excelInsuranceOrder.do";
        };

        function orderPrint() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                window.location.href = "insuranceLogDetail.do?orderId=" + dataItem.orderId;
            }
        }

        function orderPrintSurvey() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                // window.location.href = "printsSurvey.do?orderId=" + dataItem.orderId;
                window.open("printsSurvey.do?orderId=" + dataItem.orderId);
            }
        }
        function checkTime() {
            if ($('#createTime').val() > $('#createTimes').val() && '' != $('#createTimes').val()) {
                alert("开始时间不能大于结束时间")
                $('#createTimes').val('')
            }
        }

        function orderPDF() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                window.location.href = "prints.do?orderId=" + dataItem.orderId;
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
                    <li><a href="#">首页 </a></li>
                    <li><a href="#">保单管理 </a></li>
                    <li><a href="#" class="active">保单操作日志</a></li>
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
                    <h2>保单操作日志</h2>
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
                <div class="toolbar responsive-helper">
                    <form style="width: 100%">
                        <div class="pull-left">
                            <div class="toolbar-field">
                                <strong>保单编号:</strong>
                            </div>
                            <div class="toolbar-field">
                                <input type="text" data-filter="orderCode" data-operator="contains"
                                       class="form-control grid-filter" placeholder="请输入保单编号"/>
                            </div>
                            <div class="toolbar-field">
                                <strong>操作时间:</strong>
                            </div>
                            <div class="toolbar-field">
                                <input name="createTime" onchange="checkTime()"
                                       onkeyup="this.value=this.value.replace(/(^\s+)|(\s+$)/g,'')" id="createTime"
                                       placeholder="请选择开始时间" data-filter="createTime" data-operator="gte"
                                       class="form-control grid-filter"/>
                            </div>

                            <div class="toolbar-field">
                                <strong>-</strong>
                            </div>
                            <div class="toolbar-field">
                                <input name="createTime" onchange="checkTime()"
                                       onkeyup="this.value=this.value.replace(/(^\s+)|(\s+$)/g,'')" id="createTimes"
                                       placeholder="请选择结束时间" data-filter="createTime" data-operator="lte"
                                       class="form-control grid-filter"/>
                            </div>

                        </div>

                        <div class="pull-left">

                            <div class="toolbar-field">
                                <strong>投保人&nbsp;&nbsp;&nbsp;:</strong>
                            </div>
                            <div class="toolbar-field">
                                <input type="text" data-filter="policyholderName"
                                       style="width:165px;" data-operator="contains" class="form-control grid-filter"
                                       placeholder="请输入投保人"/>
                            </div>

                            <div class="toolbar-field">
                                <strong>被保人&nbsp;&nbsp;:</strong>
                            </div>
                            <div class="toolbar-field">
                                <input type="text" data-filter="insuredName"
                                       data-operator="contains" class="form-control grid-filter" placeholder="请输入被保人"/>
                            </div>

                            <div class="toolbar-field">
                                <strong>投保人手机号:</strong>
                            </div>
                            <div class="toolbar-field">
                                <input type="text" data-filter="policyholderMobile"
                                       data-operator="contains" class="form-control grid-filter"
                                       placeholder="请输入投保人手机号"/>
                            </div>

                        </div>
                        <!-- End .pull-left -->
                        <div class="pull-right">
                            <div class="toolbar-field">
                                <button type="button" class="btn btn-default" onclick="reloadGridFilters('grid')"><i
                                        class="fa fa-search"></i>查询
                                </button>
                                &nbsp;&nbsp;&nbsp;
                                <button type="button" class="btn btn-default" onclick="clearFilters('grid')">清空
                                </button>
                            </div>
                        </div>
                        <!-- End .pull-right -->
                    </form>
                </div>
                <div class="spacer-10"></div>

                <div class="toolbar responsive-helper">
                    <header>
                        <div class="pull-left">
                            <h3>操作日志</h3>
                        </div>

                        <div class="pull-right">

                            <div class="btn-group">
                                <a href="javascript:void(0);" onclick="orderPrint()" class="btn btn-default">
                                    <i class="fa fa-info-circle"></i>&nbsp;保单详情</a>
                            </div>
                        </div>


                    </header>
                </div>
                <div class="table-wrapper" id="prints">
                    <kendo:grid name="grid" pageable="false" sortable="true" selectable="true" height="450"
                                filterable="true">
                        <kendo:grid-pageable refresh="true" pageSizes="true" buttonCount="5" pageSize="10"/>
                        <kendo:grid-filterable extra="false">
                            <kendo:grid-filterable-messages filter="查询" clear="清除" info="请输入查询条件:"/>
                            <kendo:grid-filterable-operators>
                                <kendo:grid-filterable-operators-string contains="包含" eq="等于"/>
                                <kendo:grid-filterable-operators-date gte="小于" eq="等于" lte="大于"/>
                            </kendo:grid-filterable-operators>
                        </kendo:grid-filterable>
                        <kendo:grid-columns>
                            <kendo:grid-column title="保单编号" filterable="false" field="orderCode" width="100"
                                               template="<a href='insuranceLogDetail.do?orderId=#= orderId#' style='color:blue'>#= orderCode#</a>"/>

                            <kendo:grid-column title="投保人" filterable="false" field="policyholderName"
                                                width="100"/>
                            <kendo:grid-column title="投保人手机" filterable="false" field="policyholderMobile"
                                              width="100"/>
                            <kendo:grid-column title="被保人" filterable="false" field="insuredName" width="100"/>
                            <kendo:grid-column title="被保人手机" filterable="false" field="insuredMobile" width="100"/>
                            <kendo:grid-column title="保险金额" filterable="false" field="price"
                                               width="100"/>
                            <kendo:grid-column title="操作人" filterable="false" field="createName" width="100"/>
                            <kendo:grid-column title="操作账号" filterable="false" field="createOper" width="100"/>
                            <kendo:grid-column title="操作状态" filterable="false" field="orderState"
                                               template="#=formatOrderState(orderState)#" width="100"/>
                            <kendo:grid-column title="操作时间" filterable="false" field="createTime"
                                               format="{0:yyyy-MM-dd HH:mm}" width="100"/>
                            <kendo:grid-column title="操作人IP地址" filterable="false" field="ipAddress" width="100"/>
                        </kendo:grid-columns>
                        <kendo:dataSource serverPaging="true" serverFiltering="true" serverSorting="true">
                            <kendo:dataSource-schema data="content" total="totalElements">
                                <kendo:dataSource-schema-model>
                                    <kendo:dataSource-schema-model-fields>
                                        <kendo:dataSource-schema-model-field name="createTime" type="date"/>
                                    </kendo:dataSource-schema-model-fields>
                                </kendo:dataSource-schema-model>
                            </kendo:dataSource-schema>
                            <kendo:dataSource-transport>
                                <kendo:dataSource-transport-read url="pageInsuranceLog.do" type="POST"
                                                                 contentType="application/json"/>
                                <kendo:dataSource-transport-parameterMap>
                                    <script>
                                        function parameterMap(options, type) {
                                            return JSON.stringify(options);
                                        }
                                    </script>
                                </kendo:dataSource-transport-parameterMap>
                            </kendo:dataSource-transport>
                        </kendo:dataSource>
                    </kendo:grid>

                </div>
            </div>

            <div class="spacer-30"></div>
            <div class="hr-totop"><span>Top</span></div>
            <div class="spacer-10"></div>

            <!-- End .inner-padding -->
        </div>

        <jsp:include page="../layouts/footer.jsp"/>
        <!-- End #footer-main -->
    </div>
    <!-- End #content -->
</div>
<!-- End #main -->

<script type="text/javascript">
    $(document).ready(function () {
        $("#commodityAuditForm").validationEngine({
            autoHidePrompt: true, scroll: false, showOneMessage: true
        });
    });

</script>

</body>
</html>
