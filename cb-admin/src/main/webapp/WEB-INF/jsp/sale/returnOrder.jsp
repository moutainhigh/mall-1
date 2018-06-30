<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>退货订单详情</title>

    <script type="text/javascript">
        $(document).ready(function () {
            $("#validateSubmitForm").validationEngine({
                autoHidePrompt: true, scroll: false, showOneMessage: true
//                ,onValidationComplete: function (form, valid) {
//                    if (valid) {
//                        var state = $("#returnState").val();
//                        var rece = $("#received").val();
//                        if (state == "WAIT_RETURNED_REFUND" && rece == 'false') {
//                            bootbox.confirm("确认您已收到买家寄回的货品吗？",function(result){
//                                if(result){
//                                    $.get("confirmReceivedReturnProduct.do",{returnId:$("#returnId").val()},function(result){
//                                        if(result.status){
//                                            bootbox.alert("成功！",function(){
//                                                window.location.href="returnOrders.do";
//                                                return true;
//                                            });
//                                        }else{
//                                            alert(result.error);
//                                            return false;
//                                        }
//                                    });
//                                }
//
//                            });
//                        } else {
//                            return true;
//                        }
//                    }
//                }
            });
        });

    </script>
</head>
<body>

<jsp:include page="../layouts/left.jsp"/>
<!-- End aside -->
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
                    <li><a href="#">市场销售</a></li>
                    <li><a href="#">售后服务</a></li>
                    <li class="active"><a href="#">退货管理</a></li>
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
                    <h2>退货订单详情</h2>
                </div>
                <div class="pull-right">
                    <a class="btn btn-default " href="returnOrders.do"><i class="fa fa-reply"></i></a>
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
                    <div class="inner-padding">
                        <div class="row">
                            <div class="col-sm-2">
                                <label>退货编号：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                <h5>${productReturn.returnCode}</h5>
                            </div>
                            <div class="col-sm-2">
                                <label>客户：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                ${productReturn.customer.accountName}
                            </div>
                            <div class="col-sm-1"></div>
                        </div>

                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>订单号：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                <span>${productReturn.order.orderCode}</span>
                            </div>
                            <div class="col-sm-2">
                                <label>购买时间：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                <span>${productReturn.purchasingTime}</span>
                            </div>
                        </div>

                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>退货申请时间：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                <span>${productReturn.applyTime}</span>
                            </div>
                            <div class="col-sm-2">
                                <label>买家是否收货：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                <c:if test="${productReturn.receivedBuyerProduct==true}">已收货</c:if>
                                <c:if test="${productReturn.receivedBuyerProduct==false}">未收货</c:if>
                            </div>
                        </div>

                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>退货原因：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                    <span>
                                    <c:choose>
                                        <c:when test="${productReturn.returnReason=='RETURN_FREIGHT'}">退运费</c:when>
                                        <c:when test="${productReturn.returnReason=='PERFORMANCE_FAULT'}">性能故障</c:when>
                                        <c:when test="${productReturn.returnReason=='DESC_DOES_NOT_MATCH'}">描述不符</c:when>
                                        <c:when test="${productReturn.returnReason=='FUNCTION_DOES_NOT_MATCH'}">功能/效果不符</c:when>
                                        <c:when test="${productReturn.returnReason=='LESS_LEAKAGE'}">少件/漏发</c:when>
                                        <c:when test="${productReturn.returnReason=='PACKING_GOODS_DAMAGED'}">包装/商品破损</c:when>
                                        <c:when test="${productReturn.returnReason=='INVOICE_PROBLEM'}">发票问题</c:when>
                                    </c:choose>
                                    </span>
                            </div>
                            <div class="col-sm-2">
                                <label>订单总额：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                    <span>
                                        ${productReturn.order.feeTotal}
                                    </span>
                            </div>

                        </div>

                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>退货状态：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                    <span>
                                    <c:choose>
                                        <c:when test="${productReturn.returnRefundState=='APPLY_RETURN_REFUND'}">申请退货退款</c:when>
                                        <c:when test="${productReturn.returnRefundState=='WAIT_RETURNED_REFUND'}">待退货退款</c:when>
                                        <c:when test="${productReturn.returnRefundState=='RETURNED_WAIT_REFUND'}">已退货待退款</c:when>
                                        <c:when test="${productReturn.returnRefundState=='RETURNED_REFUND'}">已退货退款</c:when>
                                        <c:when test="${productReturn.returnRefundState=='RETURN_REFUND_DENIED'}">拒绝退货退款</c:when>
                                        <c:when test="${productReturn.returnRefundState=='APPLY_REFUND'}">申请退款</c:when>
                                        <c:when test="${productReturn.returnRefundState=='WAIT_REFUND'}">待退款</c:when>
                                        <c:when test="${productReturn.returnRefundState=='REFUNDED'}">已退款</c:when>
                                        <c:when test="${productReturn.returnRefundState=='REFUND_DENIED'}">拒绝退款</c:when>
                                    </c:choose>
                                    </span>
                                <input type="hidden" id="returnState" value="${productReturn.returnRefundState}">
                            </div>
                            <div class="col-sm-2">
                                <label>退款金额：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                <span>${productReturn.refundPrice}</span>
                            </div>
                        </div>

                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>退货描述：</label>
                            </div>
                            <div class="col-sm-8 col-label">
                                <span>${productReturn.reasonRemark}</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="table-wrapper">
                    <header>
                        <h3>商品信息</h3>
                    </header>
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th width="5%">商品图片</th>
                            <th width="30%">商品名称</th>
                            <th width="30%">货品型号</th>
                            <th width="5%">单价</th>
                            <th width="5%">数量</th>
                            <th width="5%">总价</th>
                            <th width="20%">包装清单</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="item" value="${productReturn.orderItem}"/>
                        <tr>
                            <td><a><img src="../images/${item.product.commodity.defaultPicPath}_64_69.jpg"/></a></td>
                            <td>${item.product.commodity.commodityName}</td>
                            <td>${item.product.productName}</td>
                            <td>${item.product.salePrice}</td>
                            <td>${item.productNum}</td>
                            <td>${item.product.salePrice * item.productNum}</td>
                            <td>${item.product.commodity.packingList}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="spacer-30"></div>
                <hr>
                <div class="spacer-30"></div>
                <c:choose>
                    <c:when test="${productReturn.returnRefundState=='RETURNED_WAIT_REFUND'}">
                        <form:form id="validateReturnForm" cssClass="form-horizontal" action="../refund/submitRefund.do" method="post">
                            <input type="hidden" name="orderCode" value="${productReturn.order.orderCode}"/>
                            <input type="hidden" name="channelType" value="ALIPAY">
                            <fieldset>
                                <legend>退款操作</legend>

                                <div class="row">
                                    <div class="col-sm-2">
                                        <label>退货状态：<span class="asterisk">*</span></label>
                                    </div>
                                    <div class="col-sm-3 col-label">
                                <span>
                                    <c:choose>
                                        <c:when test="${productReturn.returnRefundState=='APPLY_RETURN_REFUND'}">申请退货退款</c:when>
                                        <c:when test="${productReturn.returnRefundState=='WAIT_RETURNED_REFUND'}">待退货退款</c:when>
                                        <c:when test="${productReturn.returnRefundState=='RETURNED_WAIT_REFUND'}">已退货待退款</c:when>
                                        <c:when test="${productReturn.returnRefundState=='RETURNED_REFUND'}">已退货退款</c:when>
                                        <c:when test="${productReturn.returnRefundState=='RETURN_REFUND_DENIED'}">拒绝退货退款</c:when>
                                        <c:when test="${productReturn.returnRefundState=='APPLY_REFUND'}">申请退款</c:when>
                                        <c:when test="${productReturn.returnRefundState=='WAIT_REFUND'}">待退款</c:when>
                                        <c:when test="${productReturn.returnRefundState=='REFUNDED'}">已退款</c:when>
                                        <c:when test="${productReturn.returnRefundState=='REFUND_DENIED'}">拒绝退款</c:when>
                                    </c:choose>
                                </span>
                                    </div>
                                    <div class="col-sm-2">
                                        <label>退款金额：</label>
                                    </div>
                                    <div class="col-sm-3 col-label">
                                            ${productReturn.refundPrice}
                                    </div>
                                </div>

                                <div class="spacer-10"></div>

                                <div class="row">
                                    <div class="col-sm-2">
                                        <label>备注：</label>
                                    </div>
                                    <div class="col-sm-7">
                                        ${productReturn.remark}
                                    </div>
                                </div>
                                <div class="spacer-30"></div>
                                <hr>
                                <div class="spacer-30"></div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="btn-group pull-right">
                                            <button class="btn btn-default" type="submit"><i class="fa fa-save"></i>&nbsp;保&nbsp;存&nbsp;</button>
                                            <button type="reset" class="btn btn-default"><i class="fa fa-reply"></i>&nbsp;重&nbsp;置&nbsp;</button>
                                        </div>
                                    </div>
                                </div>
                            </fieldset>
                        </form:form>
                    </c:when>
                    <c:otherwise>
                        <form:form id="validateRefundForm" cssClass="form-horizontal" action="returnRefundOrder.do" method="post" commandName="productReturn">
                            <form:hidden path="returnId" value="${productReturn.returnId}"/>
                            <form:hidden path="returnCode" value="${productReturn.returnCode}"/>
                            <input type="hidden" id="received" value="${productReturn.receivedSellerProduct}">
                            <fieldset>
                                <legend>退货操作</legend>

                                <div class="row">
                                    <div class="col-sm-2">
                                        <label>退货状态：<span class="asterisk">*</span></label>
                                    </div>
                                    <div class="col-sm-3 col-label">
                                <span>
                                    <c:choose>
                                        <c:when test="${productReturn.returnRefundState=='APPLY_RETURN_REFUND'}">申请退货退款</c:when>
                                        <c:when test="${productReturn.returnRefundState=='WAIT_RETURNED_REFUND'}">待退货退款</c:when>
                                        <c:when test="${productReturn.returnRefundState=='RETURNED_WAIT_REFUND'}">已退货待退款</c:when>
                                        <c:when test="${productReturn.returnRefundState=='RETURNED_REFUND'}">已退货退款</c:when>
                                        <c:when test="${productReturn.returnRefundState=='RETURN_REFUND_DENIED'}">拒绝退货退款</c:when>
                                        <c:when test="${productReturn.returnRefundState=='APPLY_REFUND'}">申请退款</c:when>
                                        <c:when test="${productReturn.returnRefundState=='WAIT_REFUND'}">待退款</c:when>
                                        <c:when test="${productReturn.returnRefundState=='REFUNDED'}">已退款</c:when>
                                        <c:when test="${productReturn.returnRefundState=='REFUND_DENIED'}">拒绝退款</c:when>
                                    </c:choose>
                                </span>
                                    </div>
                                    <div class="col-sm-2">
                                        <label>退款金额：</label>
                                    </div>
                                    <div class="col-sm-3 col-label">
                                        <form:input path="refundPrice" value="${productReturn.refundPrice}" cssClass="form-control validate[required,custom[number]]" maxlength="11"></form:input>
                                    </div>
                                </div>

                                <div class="spacer-10"></div>

                                <div class="row">
                                    <div class="col-sm-2">
                                        <label>备注：</label>
                                    </div>
                                    <div class="col-sm-7">
                                        <form:textarea cssClass="form-control validate[maxSize[255]]" path="remark"></form:textarea>
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
                    </c:otherwise>
                </c:choose>


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
