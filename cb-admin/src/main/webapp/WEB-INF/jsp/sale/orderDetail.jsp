<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

    <title>订单详情</title>

    <script type="text/javascript">
        $(document).ready(function () {
            $("#pcdAddress").html($.citySelector.getPCDNames(${order.province}, ${order.city}, ${order.district}));
            $("#comfrimDiv").hide();

        });

        function changeDelivery() {
            $("#comfrimDiv").show();
            $("#conNameDiv").empty().append("<input type='text' id='consigneeName' maxlength='32'/>");
            $("#conMobileDiv").empty().append("<input type='text' id='consigneeMobile' maxlength='11'/>");
            $("#conPCDDiv").empty().append("<select id='province'></select>")
                    .append("<select id='city'></select>")
                    .append("<select id='district'></select>");
            $.citySelector.init({
                province: "province",
                city: "city",
                district: "district"
            });

            $("#conAddressDiv").empty().append("<input type='text' id='consigneeAddress' style='width: auto' maxlength='255'/>");
            $("#conTelephoneDiv").empty().append("<input type='text' id='consigneeTelephone' maxlength='16'/>");
            $("#conPostCodeDiv").empty().append("<input type='text' id='postCode' maxlength='6'/>");

        }

        function comfrimChangeDelivery() {
            var name = $("#consigneeName").val();
            if (null == name || "" == name || undefined == name) {
                alert("请填写收货人名称");
                return;
            }

            var province = $("#province").val();
            if ($("#province").find("option:selected").index() == 0 || undefined == province) {
                alert("请选择省份");
                return;
            }

            var city = $("#city").val();
            if ($("#city").find("option:selected").index() == 0 || undefined == city) {
                alert("请选择市县");
                return;
            }
            var district = $("#district").val();
            if ($("#district").find("option:selected").index() == 0 || undefined == district) {
                alert("请选择区域");
                return;
            }
            var address = $("#consigneeAddress").val();
            if (null == address || "" == address || undefined == address) {
                alert("请填写收货人地址");
                return;
            }
            var postCode = $("#postCode").val();
            if (null == postCode || "" == postCode || undefined == postCode) {
                alert("请填写邮编");
                return;
            }
            var telephone = $("#consigneeTelephone").val();
            var mobile = $("#consigneeMobile").val();
            if (null == mobile || "" == mobile || undefined == mobile) {
                alert("请填写收货人手机号码");
                return;
            }
            $.post("changeDelivery.do", {
                orderId: $("#orderId").val(), consigneeName: name, province: province, city: city, district: district,
                consigneeAddress: address, postCode: postCode, consigneeTelephone: telephone, consigneeMobile: mobile
            }, function (result) {
                if (result) {
                    window.location.href = "getOrderDetailById.do?orderId=" + $("#orderId").val();
                } else {
                    alert("调价失败！");
                }
            });
        }
    </script>
</head>
<body>


<!-- ********************************************
     * SIDEBAR MAIN:                            *
     *                                          *
     * the part which contains the main         *
     * navigation, logo, search and more...     *
     * (parts can be in both sidebars).         *
     ******************************************** -->

<jsp:include page="../layouts/left.jsp"/>
<!-- End aside -->

<!-- ********************************************
     * SIDEBAR SEC:                             *
     *                                          *
     * the part which contains things like      *
     * calendar, users, lists, blocks and       *
     * much more.                               *
     ******************************************** -->

<jsp:include page="../layouts/sidebarRight.jsp"/>
<div id="main" class="clearfix">

    <!-- ********************************************
         * MAIN HEADER:                             *
         *                                          *
         * the part which contains the breadcrumbs, *
         * dropdown menus, toggle sidebar button    *
         ******************************************** -->

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
                    <li><a href="#">市场销售</a></li>
                    <li><a href="#">订单管理</a></li>
                    <li class="active"><a href="#">订单详情</a></li>
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

        <!-- ********************************************
             * HEADER SEC:                              *
             *                                          *
             * the part which contains the page title,  *
             * buttons and dropdowns.                   *
             ******************************************** -->

        <header id="header-sec">
            <div class="inner-padding">
                <div class="pull-left">
                    <h2>订单详情</h2>
                </div>
                <div class="pull-right">
                    <a class="btn btn-default " href="orders.do"><i class="fa fa-reply"></i></a>
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

                <div class="widget">
                    <header>
                        <h2>订单信息</h2>
                    </header>
                    <c:if test="${null != oldOrder}">
                        <div class="inner-padding">
                            <div class="row">
                                <div class="col-sm-2">
                                    <label>原订单号：</label>
                                </div>
                                <div class="col-sm-3 col-label">
                                    <h5>${oldOrder.orderCode}</h5>
                                </div>
                                <div class="col-sm-2">
                                    <label>下单时间：</label>
                                </div>
                                <div class="col-sm-3 col-label">
                                        ${oldOrder.createTime}
                                </div>
                                <div class="col-sm-1"></div>
                            </div>

                            <div class="spacer-10"></div>
                            <div class="row">
                                <div class="col-sm-2">
                                    <label>订单金额：</label>
                                </div>
                                <div class="col-sm-3 col-label">
                                    <span>${oldOrder.totalPrice}</span>
                                </div>
                                <div class="col-sm-2">
                                    <label>运费金额：</label>
                                </div>
                                <div class="col-sm-3 col-label">
                                    <span>${oldOrder.deliveryFeeTotal}</span>
                                </div>
                            </div>

                            <div class="spacer-10"></div>
                            <div class="row">
                                <div class="col-sm-2">
                                    <label>订单总金额：</label>
                                </div>
                                <div class="col-sm-3 col-label">
                                    <span>${oldOrder.feeTotal}</span>
                                </div>
                                <div class="col-sm-2">
                                    <label>订单状态：</label>
                                </div>
                                <div class="col-sm-3 col-label">
                                <span>
                                    <c:choose>
                                        <c:when test="${oldOrder.orderState=='PENDING_PAYMENT'}">待付款</c:when>
                                        <c:when test="${oldOrder.orderState=='PAID_PAYMENT'}">待发货</c:when>
                                        <c:when test="${oldOrder.orderState=='OUT_STOCK'}">已发货</c:when>
                                        <c:when test="${oldOrder.orderState=='RECEIVED'}">已签收</c:when>
                                        <c:when test="${oldOrder.orderState=='REFUSE'}">拒签收</c:when>
                                        <c:when test="${oldOrder.orderState=='RETURN_GOODS'}">退货</c:when>
                                        <c:when test="${oldOrder.orderState=='CHANGE_GOODS'}">换货</c:when>
                                        <c:when test="${oldOrder.orderState=='CANCELED'}">已取消</c:when>
                                        <c:when test="${oldOrder.orderState=='WAIT_EVALUATE'}">待评价</c:when>
                                        <c:when test="${oldOrder.orderState=='TIMEOUT'}">超时</c:when>
                                        <c:when test="${oldOrder.orderState=='SUCCESS'}">交易成功</c:when>
                                    </c:choose>
                                </span>
                                </div>
                            </div>

                            <div class="spacer-10"></div>
                            <div class="row">
                                <div class="col-sm-2">
                                    <label>买家：</label>
                                </div>
                                <div class="col-sm-3 col-label">
                                    <span>${oldOrder.customer.accountName}</span>
                                </div>
                                <div class="col-sm-2">
                                    <label>店铺：</label>
                                </div>
                                <div class="col-sm-3 col-label">
                                    <span>${oldOrder.seller.sellerName}</span>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <div class="inner-padding">
                        <div class="row">
                            <div class="col-sm-2">
                                <label>订单号：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                <input type="hidden" id="orderId" value="${order.orderId}"/>
                                <h5>${order.orderCode}</h5>
                            </div>
                            <div class="col-sm-2">
                                <label>下单时间：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                <fmt:formatDate value="${order.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </div>
                            <div class="col-sm-1"></div>
                        </div>

                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>订单金额：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                <span>
                                <fmt:formatNumber value="${order.totalPrice}" pattern="#.##" minFractionDigits="2" > </fmt:formatNumber> </span>
                            </div>
                            <div class="col-sm-2">
                                <label>运费金额：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                <span>
                                    <fmt:formatNumber value="${order.deliveryFeeTotal}" pattern="#.##" minFractionDigits="2" > </fmt:formatNumber>
                                </span>
                            </div>
                        </div>

                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>订单总金额：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                <span>
                                      <fmt:formatNumber value="${order.feeTotal}" pattern="#.##" minFractionDigits="2" > </fmt:formatNumber>
                                </span>
                            </div>
                            <div class="col-sm-2">
                                <label>订单状态：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                <span>
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
                                </span>
                            </div>
                        </div>

                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>买家：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                <span>${order.customer.accountName}</span>
                            </div>
                            <div class="col-sm-2">
                                <label>店铺：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                <span>${order.seller.sellerName}</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="widget">
                    <header>
                        <div class="pull-left">
                            <h2>收货人信息</h2>
                        </div>
                        <div class="pull-right">
                            <a href="javascript:void(0);" class="btn btn-default" onclick="changeDelivery();">
                                <i class="fa fa-cog"></i>编辑地址
                            </a>
                        </div>
                    </header>
                    <div class="inner-padding">
                        <div class="row">
                            <div class="col-sm-2">
                                <label>收货人：</label>
                            </div>
                            <div class="col-sm-3 col-label" id="conNameDiv">
                                <span>${order.consigneeName}</span>
                            </div>
                            <div class="col-sm-2">
                                <label>手机：</label>
                            </div>
                            <div class="col-sm-3 col-label" id="conMobileDiv">
                                <span>${order.consigneeMobile}</span>
                            </div>
                        </div>
                        <div class="spacer-10"></div>

                        <div class="row">
                            <div class="col-sm-2">
                                <label>电话：</label>
                            </div>
                            <div class="col-sm-3 col-label" id="conTelephoneDiv">
                                <span>${order.consigneeTelephone}</span>
                            </div>
                            <div class="col-sm-2">
                                <label>邮编：</label>
                            </div>
                            <div class="col-sm-3 col-label" id="conPostCodeDiv">
                                <span>${order.postCode}</span>
                            </div>
                        </div>
                        <div class="spacer-10"></div>

                        <div class="row">
                            <div class="col-sm-2">
                                <label>收货地区：</label>
                            </div>
                            <div class="col-sm-7 col-label" id="conPCDDiv">
                                <span id="pcdAddress"></span>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>详细地址：</label>
                            </div>
                            <div class="col-sm-7 col-label" id="conAddressDiv">
                                <span style="white-space:normal; word-break:break-all;">${order.consigneeAddress}</span>
                            </div>
                        </div>


                        <div class="spacer-10"></div>

                        <div class="row" id="comfrimDiv">
                            <div class="col-sm-3 col-label">
                                <button class="btn btn-primary pull-right" onclick="comfrimChangeDelivery();" id="btnComfrim">确认</button>
                            </div>
                        </div>

                    </div>
                </div>

                <div class="widget">
                    <header>
                        <h2>发票信息</h2>
                    </header>
                    <div class="inner-padding">
                        <c:choose>
                            <c:when test="${not empty order.orderInvoices}">
                                <c:forEach items="${order.orderInvoices}" var="invoice">
                                    <div class="row">
                                        <div class="col-sm-2">
                                            <label>发票编号：</label>
                                        </div>
                                        <div class="col-sm-3 col-label">
                                            <span>${invoice.invoiceCode}</span>
                                        </div>
                                        <div class="col-sm-2">
                                            <label>发票抬头：</label>
                                        </div>
                                        <div class="col-sm-3 col-label">
                                            <span>${invoice.invoiceTitle}</span>
                                        </div>
                                        <div class="col-sm-1"></div>
                                    </div>
                                    <div class="spacer-10"></div>

                                    <div class="row">
                                        <div class="col-sm-2">
                                            <label>发票类型：</label>
                                        </div>
                                        <div class="col-sm-3 col-label">
                                            <span>${invoice.invoiceType}</span>
                                        </div>
                                        <div class="col-sm-2">
                                            <label>抬头类型：</label>
                                        </div>
                                        <div class="col-sm-3 col-label">
                                            <span>${invoice.invoiceTitleType}</span>
                                        </div>
                                        <div class="col-sm-1"></div>
                                    </div>
                                    <div class="spacer-10"></div>
                                    <c:choose>
                                        <c:when test="${invoice.invoiceType eq 'NORMAL'}">
                                            <div class="row">
                                                <div class="col-sm-2">
                                                    <label>发票内容：</label>
                                                </div>
                                                <div class="col-sm-3 col-label">
                                                    <span>${invoice.content}</span>
                                                </div>
                                                <div class="col-sm-2">
                                                    <label>发票金额：</label>
                                                </div>
                                                <div class="col-sm-3 col-label">
                                                    <span>${invoice.invoiceAmount}</span>
                                                </div>
                                                <div class="col-sm-1"></div>
                                            </div>
                                            <div class="spacer-10"></div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="row">
                                                <div class="col-sm-2">
                                                    <label>纳税人识别号：</label>
                                                </div>
                                                <div class="col-sm-3 col-label">
                                                    <span>${invoice.taxpayerNo}</span>
                                                </div>
                                                <div class="col-sm-2">
                                                    <label>注册地址：</label>
                                                </div>
                                                <div class="col-sm-3 col-label">
                                                    <span>${invoice.registerAddress}</span>
                                                </div>
                                                <div class="col-sm-1"></div>
                                            </div>
                                            <div class="spacer-10"></div>
                                            <div class="row">
                                                <div class="col-sm-2">
                                                    <label>注册电活：</label>
                                                </div>
                                                <div class="col-sm-3 col-label">
                                                    <span>${invoice.registerPhone}</span>
                                                </div>
                                                <div class="col-sm-2">
                                                    <label>开户行：</label>
                                                </div>
                                                <div class="col-sm-3 col-label">
                                                    <span>${invoice.bankName}</span>
                                                </div>
                                                <div class="col-sm-1"></div>
                                            </div>
                                            <div class="spacer-10"></div>
                                            <div class="row">
                                                <div class="col-sm-2">
                                                    <label>银行账户：</label>
                                                </div>
                                                <div class="col-sm-3 col-label">
                                                    <span>${invoice.bankAccount}</span>
                                                </div>
                                                <div class="col-sm-2">
                                                    <label></label>
                                                </div>
                                                <div class="col-sm-3 col-label">
                                                    <span></span>
                                                </div>
                                                <div class="col-sm-1"></div>
                                            </div>
                                            <div class="spacer-10"></div>
                                            <div class="row">
                                                <div class="col-sm-2">
                                                    <label>发票内容：</label>
                                                </div>
                                                <div class="col-sm-3 col-label">
                                                    <span>${invoice.content}</span>
                                                </div>
                                                <div class="col-sm-2">
                                                    <label>发票金额：</label>
                                                </div>
                                                <div class="col-sm-3 col-label">
                                                    <span>${invoice.invoiceAmount}</span>
                                                </div>
                                                <div class="col-sm-1"></div>
                                            </div>
                                            <div class="spacer-10"></div>

                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <div class="row">
                                    <h5>不开发票</h5>
                                </div>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </div>

                <div class="table-wrapper">
                    <header>
                        <h3>商品信息</h3>
                    </header>
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th style="width: 90px">商品图片</th>
                            <th>商品名称</th>
                            <th>货品型号</th>
                            <th>单价</th>
                            <th>数量</th>
                            <th>小计</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${order.orderItems}" var="item">
                            <tr>
                                <td><a><img src="${item.product.commodity.defaultPicPath}_64_69.jpg"/></a></td>
                                <td>${item.product.commodity.commodityName}</td>
                                <td>${item.product.productName}</td>
                                <td>
                                    <fmt:formatNumber value="${item.product.salePrice}" pattern="#.##" minFractionDigits="2" ></fmt:formatNumber>
                                </td>
                                <td>${item.productNum}</td>
                                <td><span class="btn brand-pinterest">
                                         <fmt:formatNumber value="${item.product.salePrice * item.productNum}" pattern="#.##" minFractionDigits="2" ></fmt:formatNumber>
                                    </span></td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>

                <div class="spacer-30"></div>
                <hr>
                <div class="spacer-30"></div>

                <div class="row">
                    <div class="col-sm-12">
                        <a class="btn btn-default pull-right" href="orders.do">返回</a>
                    </div>
                </div>
                <div class="spacer-40"></div>
                <div class="hr-totop"><span>Top</span></div>
                <div class="spacer-40"></div>

            </div>
            <!-- End .inner-padding -->
        </div>
        <!-- End .window -->


        <jsp:include page="../layouts/footer.jsp"/>
        <!-- End #footer-main -->
    </div>
    <!-- End #content -->
</div>
<!-- End #main -->


</body>
</html>
