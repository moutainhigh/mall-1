<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>报账列表</title>
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
                case "FINANCE_IN_APPROVAL":
                    return "财务员审批中";
                case "DIRECTOR_IN_APPROVAL":
                    return "财务主管审批中";
                case "CASHIER_IN_APPROVAL":
                    return "出纳审批中";
                case "ALREADY_TO_ACCOUNT":
                    return "已到账";
                case "NOT_PASS_THROUGH":
                    return "审批不通过";
            }
        }
        function formatReimbursementOrder(reimbursementOrder){
                alert(reimbursementOrder);
        }

        function excelInsuranceOrder() {
            window.location.href = "excelInsuranceOrder.do";
        };

        function orderPrint() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                window.location.href = "prints.do?orderId=" + dataItem.orderId;
            }
        }

        function orderPrintSurvey() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                // window.location.href = "printsSurvey.do?orderId=" + dataItem.orderId;
                window.open("printsSurvey.do?orderId=" + dataItem.orderId);
            }
        }

        function orderPrint3() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                window.location.href = "prints3.do?orderId=" + dataItem.orderId;
            }
        }

        function orderPrint4() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                window.location.href = "prints4.do?orderId=" + dataItem.orderId;
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
        <!-- End #header-main-bottom -->
    </header>
    <!-- End #header-main -->

    <div id="content" class="clearfix">


        <header id="header-sec">
            <div class="inner-padding">
                <div class="pull-left">
                    <h2>报账订单</h2>
                </div>
                <div class="pull-right">
                    <a class="btn btn-default" href="reimbursements.do">
                        <i class="fa fa-reply"></i>
                    </a>

                </div>
            </div>
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
                <div class="table-wrapper">
                    <div id="content-tab-1" class="tab-pane active">
                        <div class="inner-padding">
                            <div class="spacer-30">报账订单：
                            </div>
                        </div>
                        <div style="width: 30%;height: 300px;">
                            <div style="margin-left: 20px;width: 100px;">
                                <label>订单号：fdsfsd </label>
                            </div>
                            <div style="margin-left: 30px;">
                                <img src="http://test.resource.999shuijingqiu.com/FgbdBSuPK_3QMselUrmfBYE89b_d" alt="" width="50px" height="50px"> 2018款C200L运动版

                            </div>
                            <div style="margin-left: 20px;width: 300px;">
                                <label>订单金额：28.22万，应缴纳税：7.07万
                                </label>
                            </div>
                        </div>
                        <div>


                        </div>
                    </div>
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
