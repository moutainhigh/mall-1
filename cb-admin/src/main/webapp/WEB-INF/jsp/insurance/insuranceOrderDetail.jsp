<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <!--<script src="../js/zoomify/zoomify.js"></script>-->
    <script src="../js/zoomify/viewer.min.js"></script>
    <script src="../js/zoomify/viewer-jquery.min.js"></script>
    <link rel="stylesheet" href="../js/zoomify/viewer.min.css">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>保单详情</title>
    <script type="text/javascript">
        function updInsuranceOrderState(orderId,oState){
            bootbox.confirm("确认操作？", function (result) {
                if (result) {
                    $.get("updInsuranceOrderState.do", {
                        orderId: orderId,
                        orderState : oState
                    }, function (data) {
                        if (data) {
                            bootbox.alert("操作成功");
                            window.location.href="insurances.do";
                            bootbox.alert("操作成功");
                        } else {
                            bootbox.alert("操作失败");
                        }
                    });
                }
           });
        }
        function editItem(orderId){
            window.location.href = "toEditInsuranceOrderOffsite.do?orderId=" + orderId;
        }
        $(document).ready(function () {
            debugger;
            var province=$.citySelector.getAddress('${insuranceOrder.insuranceOrderPolicyholderBank.bankProvince}');
           $("#province").html(province);
           var city=  $.citySelector.getAddress('${insuranceOrder.insuranceOrderPolicyholderBank.bankCity}');
            $("#city").html(city);


            <%--var policyholderProvince= $.citySelector.getPCDNames('${insuranceOrder.insuranceOrderPolicyholder.policyholderProvince}','${insuranceOrder.insuranceOrderPolicyholder.policyholderCity}','${insuranceOrder.insuranceOrderPolicyholder.policyholderDistrict}');--%>
           var policyholderProvince= $.citySelector.getAddress('${insuranceOrder.insuranceOrderPolicyholder.policyholderProvince}');
            var policyholderCity= $.citySelector.getAddress('${insuranceOrder.insuranceOrderPolicyholder.policyholderCity}');
            var policyholderDistrict= $.citySelector.getAddress('${insuranceOrder.insuranceOrderPolicyholder.policyholderDistrict}');
            <%--var policyholderCity=  $.citySelector.getCity(${insuranceOrder.insuranceOrderPolicyholder.policyholderCity});--%>
            <%--var policyholderDistrict=  $.citySelector.getDistrict(${insuranceOrder.insuranceOrderPolicyholder.policyholderDistrict});--%>
            <%--var address=policyholderProvince+policyholderCity+policyholderDistrict;--%>
            $("#policyholderDistrict").html(policyholderProvince+policyholderCity+policyholderDistrict);

            <%--var insuredProvince= $.citySelector.getPCDNames('${insuranceOrder.insuranceOrderInsured.insuredProvince}','${insuranceOrder.insuranceOrderInsured.insuredCity}','${insuranceOrder.insuranceOrderInsured.insuredDistrict}');--%>
            var insuredProvince=$.citySelector.getAddress('${insuranceOrder.insuranceOrderInsured.insuredProvince}');
            var insuredCity=$.citySelector.getAddress('${insuranceOrder.insuranceOrderInsured.insuredCity}');
            var insuredDistrict=$.citySelector.getAddress('${insuranceOrder.insuranceOrderInsured.insuredDistrict}');
            <%--var insuredCity= $.citySelector.getCity(${insuranceOrder.insuranceOrderInsured.insuredCity});--%>
            <%--var insuredDistrict= $.citySelector.getDistrict(${insuranceOrder.insuranceOrderInsured.insuredDistrict});--%>
            <%--var insuredAddress=insuredProvince+insuredCity+insuredDistrict;--%>
            $("#insuredDistrict").html(insuredProvince+insuredCity+insuredDistrict);
            var insuredCareer =$.profession.getProfession('${insuranceOrder.insuranceOrderInsured.insuredCareer}');
            $("#insuredCareer").html(insuredCareer);

            var policyholderCareer =$.profession.getProfession('${insuranceOrder.insuranceOrderPolicyholder.policyholderCareer}');
            $("#policyholderCareer").html(policyholderCareer);

            for (var i=1;i<7;i++){
                $('#example'+i).viewer({
                    url: 'data-original',
                });
            }
        });

        function changeOtherMatter(flag) {
            if(flag){
                $("#otherMatterDiv").show();
            }else {
                $("#otherMatterDiv").hide();
            }
        }

        function submitForm() {
            var flag = $("input[name='otherMatter']:checked").val();
            if(flag=="false"){
                $("#insuranceOrderOffsite_otherMatter").val("");
            }
            return true;
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
                    <li><a href="#">保单管理</a></li>
                    <li class="active">保单详情</li>
                </ul>
                <!-- End .breadcrumb -->
            </div>
            <div class="pull-right">
                <p>Version 1.0.0</p>
            </div>
        </div>
        <!-- End #header-main-bottom -->
    </header>

    <div id="content" class="clearfix">


        <header id="header-sec">
            <div class="inner-padding">
                <div class="pull-left">
                    <h2>保单详情</h2>
                </div>
                <div class="pull-right">
                    <a class="btn btn-default" href="insurances.do">
                        <i class="fa fa-reply"></i>
                    </a>

                </div>
            </div>
        </header>
        <div class="window">
            <div class="actionbar">
                <div class="pull-left">
                    <ul class="ext-tabs">
                        <li class="active">
                            <a href="#content-tab-1">保单信息</a>
                        </li>
                        <li>
                            <a href="#content-tab-2">告知事项</a>
                        </li>
                        <li>
                            <a href="#content-tab-3">异地投保</a>
                        </li>

                    </ul>
                </div>
                <div class="pull-right">
                    <a class="btn" href="#" id="lockscreen-slider-trigger">
                        <i class="fa fa-lock"></i>
                    </a>
                    <a data-toggle-sidebar="right" class="btn small-toggle-btn" href="#"></a>
                </div>
            </div><!-- End .actionbar-->
            <div class="tab-content">
                <div id="content-tab-1" class="tab-pane active">
                    <div class="inner-padding">
                        <div class="spacer-30">投保资料
                        </div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk"></span> 出生日期：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                <fmt:formatDate value="${insuranceOrder.insuranceOrderInsured.insuredBirthday}" pattern="yyyy-MM-dd" type="date" dateStyle="long"/>

                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk"></span> 性别：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                <c:choose>
                                    <c:when test="${insuranceOrder.insuranceOrderInsured.insuredGender=='false'}">
                                        女
                                    </c:when>
                                    <c:otherwise>男</c:otherwise>
                                </c:choose>
                            </div>

                            <div class="col-sm-2">
                                <label><span class="asterisk"></span> 被保人职业：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                    <div name="insuredCareer" id="insuredCareer"></div>
                            </div>

                        </div>

                        <div class="spacer-10"></div>
                        <div class="row">

                            <div class="col-sm-2">
                                <label><span class="asterisk"></span> 保单编号：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                ${insuranceOrder.orderCode}
                            </div>

                            <div class="col-sm-2">
                                <label><span class="asterisk"></span> 合同编号：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                ${insuranceOrder.contractNo}
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk"></span> 保险期间：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                <c:if test="${insuranceOrder.insuranceProduct.insurePeriod=='TEN_YEAR'}">
                                        十年
                                </c:if>
                                <c:if test="${insuranceOrder.insuranceProduct.insurePeriod=='TWENTY_YEAR'}">
                                    20年
                                </c:if>
                                <c:if test="${insuranceOrder.insuranceProduct.insurePeriod=='LIFITIME'}">
                                    终身
                                </c:if>
                            </div>
                        </div>


                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk"></span> 缴费期限：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                    <c:if test="${insuranceOrder.insuranceProduct.protectionYear=='TEN_YEAR'}">
                                        十年
                                    </c:if>
                                    <c:if test="${insuranceOrder.insuranceProduct.protectionYear=='TWENTY_YEAR'}">
                                        20年
                                    </c:if>
                                    <c:if test="${insuranceOrder.insuranceProduct.protectionYear=='LIFITIME'}">
                                        终身
                                    </c:if>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk"></span> 基本保额：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                ${insuranceOrder.insuranceProductPrice.price}
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk"></span> 保单状态：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                <c:if test="${insuranceOrder.orderState=='UN_PAID'}">待支付</c:if>
                                <c:if test="${insuranceOrder.orderState=='ON_PAID'}">已支付</c:if>
                                <c:if test="${insuranceOrder.orderState=='BEEN_COMPLETED'}">已完成</c:if>
                                <c:if test="${insuranceOrder.orderState=='UN_SURRENDER'}">退保审核</c:if>
                                <c:if test="${insuranceOrder.orderState=='ON_SURRENDER'}">已退保</c:if>

                                <c:if test="${insuranceOrder.orderState=='UN_PAID'}">
                                    <a class="btn pull-right " style="margin-right: 100px;background-color:#449d44;margin-top: -5px;color: #0d1114;border-color: #ccc;font-weight: bold" href="javascript:void(0);" onclick="updInsuranceOrderState('${insuranceOrder.orderId}','ON_PAID')">确认支付</a></c:if>
                                <c:if test="${insuranceOrder.orderState=='UN_SURRENDER'}">
                                    <a class="btn btn-default pull-right" style="margin-right: 100px;background-color:#449d44;margin-top: -5px;color: #0d1114;border-color: #ccc;font-weight: bold" href="javascript:void(0);" onclick="updInsuranceOrderState('${insuranceOrder.orderId}','ON_SURRENDER')">确认退保</a></c:if>
                            </div>
                        </div>
                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30">投保人信息</div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk"></span> 姓名：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                ${insuranceOrder.insuranceOrderPolicyholder.policyholderName}
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk"></span> 性别：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                <c:choose>
                                    <c:when test="${insuranceOrder.insuranceOrderPolicyholder.policyholderGender=='false'}">
                                        女
                                    </c:when>
                                    <c:otherwise>男</c:otherwise>
                                </c:choose>
                            </div>

                            <div class="col-sm-2">
                                <label><span class="asterisk"></span> 投保人职业：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                    <div name="policyholderCareer" id="policyholderCareer"></div>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">

                            <div class="col-sm-2">
                                <label><span class="asterisk"></span> 出生日期：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                <fmt:formatDate value="${insuranceOrder.insuranceOrderPolicyholder.policyholderBirthday}" pattern="yyyy-MM-dd" type="date" dateStyle="long"/>

                            </div>

                            <div class="col-sm-2">
                                <label><span class="asterisk"></span>  证件类型：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                ${insuranceOrder.insuranceOrderPolicyholder.policyholderCardType}
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk"></span> 证件号码：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                ${insuranceOrder.insuranceOrderPolicyholder.policyholderCardNo}
                            </div>
                        </div>

                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk"></span>  证件有效期：</label>
                            </div>
                            <div class="col-sm-2 col-label">

                                    <fmt:formatDate value="${insuranceOrder.insuranceOrderPolicyholder.policyholderCardPeroid}" pattern="yyyy-MM-dd" type="date" dateStyle="long"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk"></span> 国籍：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                ${insuranceOrder.insuranceOrderPolicyholder.policyholderCountry}
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk"></span> 身高(cm)：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                ${insuranceOrder.insuranceOrderPolicyholder.policyholderHeight}
                            </div>
                        </div>


                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk"></span>  体重(kg)：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                ${insuranceOrder.insuranceOrderPolicyholder.policyholderBodyWeight}
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk"></span> 年收入(万元)：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                ${insuranceOrder.insuranceOrderPolicyholder.policyholderIncome}
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk"></span>  婚姻状况：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                ${insuranceOrder.insuranceOrderPolicyholder.policyholderMarriage}
                            </div>
                        </div>

                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk"></span>  移动电话：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                ${insuranceOrder.insuranceOrderPolicyholder.policyholderMobile}
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk"></span> email：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                ${insuranceOrder.insuranceOrderPolicyholder.policyholderEmail}
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk"></span>  家庭住址：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                <div name="policyholderDistrict" id="policyholderDistrict"></div>${insuranceOrder.insuranceOrderPolicyholder.policyholderAddress}
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk"></span>  固定电话：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                ${insuranceOrder.insuranceOrderPolicyholder.policyholderTel}
                            </div>

                            <div class="col-sm-2">
                                <label><span class="asterisk"></span>  涉税人身份信息：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                ${insuranceOrder.insuranceOrderPolicyholder.policyholderTaxRelated}
                            </div>
                        </div>




                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30">被保人信息</div>

                        <div class="row">
                            <div class="col-sm-2">
                                <label>姓名：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                ${insuranceOrder.insuranceOrderInsured.insuredName}
                            </div>
                            <div class="col-sm-2">
                                <label>证件类型：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                ${insuranceOrder.insuranceOrderInsured.insuredCardType}
                            </div>

                            <div class="col-sm-2">
                                <label>证件号码：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                ${insuranceOrder.insuranceOrderInsured.insuredCardNo}
                            </div>

                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>证件有效期：</label>
                            </div>
                            <div class="col-sm-2 col-label">

                                    <fmt:formatDate value="${insuranceOrder.insuranceOrderInsured.insuredCardPeriod}" pattern="yyyy-MM-dd" type="date" dateStyle="long"/>
                            </div>
                            <div class="col-sm-2">
                                <label>国籍：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                ${insuranceOrder.insuranceOrderInsured.insuredCountry}
                            </div>

                            <div class="col-sm-2">
                                <label>身高(cm)：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                ${insuranceOrder.insuranceOrderInsured.insuredHeight}
                            </div>

                        </div>


                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>体重(kg)：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                ${insuranceOrder.insuranceOrderInsured.insuredBodyWeight}
                            </div>
                            <div class="col-sm-2">
                                <label>年收入(万元)：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                ${insuranceOrder.insuranceOrderInsured.insuredIncome}
                            </div>

                            <div class="col-sm-2">
                                <label>婚姻状况：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                ${insuranceOrder.insuranceOrderInsured.insuredMarriage}
                            </div>

                        </div>

                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>移动电话：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                ${insuranceOrder.insuranceOrderInsured.insuredMobile}
                            </div>
                            <div class="col-sm-2">
                                <label>email：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                ${insuranceOrder.insuranceOrderInsured.insuredEmail}
                            </div>

                            <div class="col-sm-2">
                                <label>家庭住址：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                <div name="insuredDistrict" id="insuredDistrict"></div>${insuranceOrder.insuranceOrderInsured.insuredAddress}
                            </div>

                        </div>


                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>固定电话：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                ${insuranceOrder.insuranceOrderInsured.insuredTel}
                            </div>

                            <div class="col-sm-2">
                                <label>与投保人关系：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                ${insuranceOrder.insuranceOrderInsured.insuredRelation}
                            </div>
                        </div>

                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30">受益人信息
                        </div>
                        <c:choose>
                            <c:when test="${insuranceOrder.legalBeneficiary=='true'}">
                                <div class="row">
                                    <div class="col-sm-2">
                                        <label><span class="asterisk"></span> 受益人为法定受益人</label>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${insuranceOrder.insuranceOrderBeneficiarys}" var="insuranceOrderBeneficiarys">
                                    <div class="row">
                                        <div class="col-sm-2">
                                            <label><span class="asterisk"></span> 姓名：</label>
                                        </div>
                                        <div class="col-sm-2 col-label">
                                                ${insuranceOrderBeneficiarys.beneficiaryName}
                                        </div>
                                        <div class="col-sm-2">
                                            <label><span class="asterisk"></span> 性别：</label>
                                        </div>
                                        <div class="col-sm-2 col-label">
                                            <c:choose>
                                                <c:when test="${insuranceOrderBeneficiarys.beneficiaryGender=='false'}">
                                                    女
                                                </c:when>
                                                <c:otherwise>男</c:otherwise>
                                            </c:choose>
                                        </div>
                                        <div class="col-sm-2">
                                            <label><span class="asterisk"></span>  国籍：</label>
                                        </div>
                                        <div class="col-sm-2 col-label">
                                                ${insuranceOrderBeneficiarys.beneficiaryCountry}
                                        </div>
                                    </div>
                                    <div class="spacer-10"></div>
                                    <div class="row">
                                        <div class="col-sm-2">
                                            <label><span class="asterisk"></span> 受益顺序：</label>
                                        </div>
                                        <div class="col-sm-2 col-label">
                                                ${insuranceOrderBeneficiarys.beneficiaryOrder}
                                        </div>
                                        <div class="col-sm-2">
                                            <label><span class="asterisk"></span> 受益份额：</label>
                                        </div>
                                        <div class="col-sm-2 col-label">
                                                ${insuranceOrderBeneficiarys.beneficiaryProportion}
                                        </div>
                                        <div class="col-sm-2">
                                            <label><span class="asterisk"></span>   出生日期：</label>
                                        </div>
                                        <div class="col-sm-2 col-label">
                                            <fmt:formatDate value="${insuranceOrderBeneficiarys.beneficiaryBirthday}" pattern="yyyy-MM-dd" type="date" dateStyle="long"/>

                                        </div>
                                    </div>




                                    <div class="spacer-10"></div>
                                    <div class="row">
                                        <div class="col-sm-2">
                                            <label><span class="asterisk"></span> 证件类型 ：</label>
                                        </div>
                                        <div class="col-sm-2 col-label">
                                                ${insuranceOrderBeneficiarys.beneficiaryCardType}
                                        </div>
                                        <div class="col-sm-2">
                                            <label><span class="asterisk"></span> 证件号码：</label>
                                        </div>
                                        <div class="col-sm-2 col-label">
                                                ${insuranceOrderBeneficiarys.beneficiaryCardNo}
                                        </div>
                                        <div class="col-sm-2">
                                            <label><span class="asterisk"></span>   证件有效期：</label>
                                        </div>
                                        <div class="col-sm-2 col-label">
                                            <fmt:formatDate value="${insuranceOrderBeneficiarys.beneficiaryCardPeroid}" pattern="yyyy-MM-dd" type="date" dateStyle="long"/>

                                        </div>
                                    </div>

                                    <div class="spacer-10"></div>
                                    <div class="row">
                                        <div class="col-sm-2">
                                            <label><span class="asterisk"></span> 与被保人关系 ：</label>
                                        </div>
                                        <div class="col-sm-2 col-label">
                                                ${insuranceOrderBeneficiarys.insuredRelation}
                                        </div>

                                    </div>
                                </c:forEach>

                            </c:otherwise>
                        </c:choose>


                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30">
                            投保资料
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk"></span> 投保人银行卡正面 ：</label>
                            </div>
                            <div class="col-sm-2 col-label" id="example1">
                                        <img data-original="${insuranceOrder.insuranceOrderPolicyholderBank.bankCardImg}"  src="${insuranceOrder.insuranceOrderPolicyholderBank.bankCardImg}" alt="投保人银行卡正面" width="200" height="150"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk"></span> 投保人身份证正面 ：</label>
                            </div>
                            <div class="col-sm-2 col-label"  id="example2">
                                <img data-original="${insuranceOrder.insuranceOrderPolicyholder.cardPositiveImg}" src="${insuranceOrder.insuranceOrderPolicyholder.cardPositiveImg}" alt="投保人身份证正面" width="200" height="150"/>
                            </div>

                            <div class="col-sm-2">
                                <label><span class="asterisk"></span> 投保人身份证反面 ：</label>
                            </div>
                            <div class="col-sm-2 col-label"  id="example3">
                                <img data-original="${insuranceOrder.insuranceOrderPolicyholder.cardNegativeImg}" src="${insuranceOrder.insuranceOrderPolicyholder.cardNegativeImg}" alt="投保人身份证反面" width="200" height="150"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <c:if test="${insuranceOrder.insuranceOrderPolicyholder.otherImg1!=null&&insuranceOrder.insuranceOrderPolicyholder.otherImg1!=''}">

                                <div class="col-sm-2">
                                    <label><span class="asterisk"></span> 其他资料 ：</label>
                                </div>
                                <div class="col-sm-2 col-label"  id="example4">
                                    <img data-original="${insuranceOrder.insuranceOrderPolicyholder.otherImg1}" src="${insuranceOrder.insuranceOrderPolicyholder.otherImg1}" alt="其他资料" width="200" height="150"/>
                                </div>
                            </c:if>

                            <c:if test="${insuranceOrder.insuranceOrderPolicyholder.otherImg2!=null&&insuranceOrder.insuranceOrderPolicyholder.otherImg2!=''}">

                                <div class="col-sm-2">
                                    <label><span class="asterisk"></span> 其他资料 ：</label>
                                </div>
                                <div class="col-sm-2 col-label" id="example5">
                                    <img data-original="${insuranceOrder.insuranceOrderPolicyholder.otherImg2}" src="${insuranceOrder.insuranceOrderPolicyholder.otherImg2}" alt="其他资料" width="200" height="150"/>
                                </div>
                            </c:if>

                            <c:if test="${insuranceOrder.insuranceOrderPolicyholder.otherImg3!=null&&insuranceOrder.insuranceOrderPolicyholder.otherImg3!=''}">

                                <div class="col-sm-2">
                                    <label><span class="asterisk"></span> 其他资料 ：</label>
                                </div>
                                <div class="col-sm-2 col-label"  id="example6">
                                    <img data-original="${insuranceOrder.insuranceOrderPolicyholder.otherImg3}" src="${insuranceOrder.insuranceOrderPolicyholder.otherImg3}" alt="其他资料" width="200" height="150"/>
                                </div>
                            </c:if>
                        </div>

                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30">
                            扣款信息
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk"></span> 投保单号 ：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                ${insuranceOrder.orderCode}
                            </div>


                            <div class="col-sm-2">
                                <label><span class="asterisk"></span> 账户姓名 ：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                ${insuranceOrder.insuranceOrderPolicyholderBank.bankName}
                            </div>


                            <div class="col-sm-2">
                                <label><span class="asterisk"></span> 交易金额(元) ：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                ${insuranceOrder.insuranceOrderPolicyholderBank.amount}
                            </div>

                        </div>


                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk"></span> 手机号码 ：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                ${insuranceOrder.insuranceOrderPolicyholderBank.bankMobile}
                            </div>


                            <div class="col-sm-2">
                                <label><span class="asterisk"></span>  开户行 ：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                ${insuranceOrder.insuranceOrderPolicyholderBank.accountBank}

                            </div>


                            <div class="col-sm-2">
                                <label><span class="asterisk"></span> 开户行省份 ：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                <div name="province" id="province"></div>
                            </div>

                        </div>



                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk"></span> 开户行城市 ：</label>
                            </div>
                            <div class="col-sm-2 col-label">

                                    <div name="city" id="city"></div>
                            </div>


                            <div class="col-sm-2">
                                <label><span class="asterisk"></span>  账户类型 ：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                ${insuranceOrder.insuranceOrderPolicyholderBank.accountType}
                            </div>


                            <div class="col-sm-2">
                                <label><span class="asterisk"></span> 账户号码 ：</label>
                            </div>
                            <div class="col-sm-2 col-label">
                                ${insuranceOrder.insuranceOrderPolicyholderBank.accountNo}
                            </div>

                        </div>

                    </div><!-- End .inner-padding -->
                </div>
                <div id="content-tab-2" class="tab-pane">
                    <div class="inner-padding">
                        <div class="subheading">
                            <h3>告知事项</h3>
                        </div>
                        <div class="row">
                            <div class="col-sm-8">
                                <table id="specTable" class="table table-bordered table-striped">
                                    <thead id="attribute-table-th">
                                    <th scope="col" width="60%">问题及健康告知</th>
                                    <th scope="col"  width="8%">被保人</th>
                                    <th scope="col" width="13%">被保人备注</th>
                                    <th scope="col"  width="8%">投保人</th>
                                    <th scope="col" width="13%">投保人备注</th>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${matterList}" var="matterLists">
                                        <tr>
                                            <c:choose>
                                                <c:when test="${matterLists.no=='0'}">
                                                    <th scope="col" colspan="5">${matterLists.matter}</th>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:choose>
                                                        <c:when test="${matterLists.no=='1'}">
                                                            <th scope="col">${matterLists.matter}</th>
                                                            <th scope="col">
                                                                <c:if test="${matterLists.insured=='false'}">否</c:if>
                                                                <c:if test="${matterLists.insured=='true'}">是</c:if></th>
                                                            <th scope="col" colspan="3" style="word-break: break-all">${matterLists.insured_remark}</th>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <th scope="col" >${matterLists.matter}</th>
                                                            <th scope="col" width="100" >
                                                                <c:if test="${matterLists.insured=='false'}">否</c:if>
                                                                <c:if test="${matterLists.insured=='true'}">是</c:if>
                                                            </th>
                                                            <th scope="col" style="word-break: break-all">
                                                                    ${matterLists.insured_remark}
                                                            </th>
                                                            <th scope="col" width="100" >
                                                                <c:if test="${matterLists.policy=='false'}">否</c:if>
                                                                <c:if test="${matterLists.policy=='true'}">是</c:if>
                                                            </th>
                                                            <th scope="col" style="word-break: break-all">
                                                                    ${matterLists.policy_remark}
                                                            </th>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:otherwise>
                                            </c:choose>

                                        </tr>
                                    </c:forEach>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div><!-- End .inner-padding -->
                    <div class="btn-group"  style="margin-left: 150px;">
                        <a class="btn btn-default pull-right" href="insurances.do">返回</a>
                    </div>
                </div>

                <div id="content-tab-3" class="tab-pane">
                    <div class="inner-padding">
                    <%--@elvariable id="insuranceOrderOffsite" type="JDOMAbout"--%>
                    <form:form id="validateSubmitForm" action="editInsuranceOrderOffsite.do" cssClass="form-horizontal" method="post" commandName="insuranceOrder" onsubmit="return submitForm();">

                    <div class="subheading">
                        <h3>异地投保</h3>
                    </div>
                        <c:choose>
                            <c:when test="${insuranceOrder.insuranceOrderOffsite!=null}">
                                <form:hidden path="insuranceOrderOffsite.offsiteId"/>
                                <form:hidden path="orderId"/>
                    <div class="inner-padding">
                        <div class="spacer-30">1、您的户籍所在地是哪里？
                        </div>
                        <div style="float: left;margin-left: 10px">
                                <%--<label><span class="asterisk"></span>${insuranceOrder.insuranceOrderOffsite.sensue}</label>--%>
                                    <form:textarea rows="10" cols="101" path="insuranceOrderOffsite.sensue" maxlength="255" readonly="true"></form:textarea>
                        </div>

                    </div>
                        <div class="inner-padding">
                            <div class="spacer-30"></div>
                            <hr>
                            <div class="spacer-30">
                                2、您目前工作所在城市或地区名？单位名称？工作单位所属行业？您的职务？
                            </div>
                            <div style="float: left;margin-left: 10px">
                                    <%--<label><span class="asterisk"></span>${insuranceOrder.workplace}</label>--%>
                                <form:textarea rows="10" cols="101" path="insuranceOrderOffsite.workplace" maxlength="255"  readonly="true"></form:textarea>
                            </div>
                        </div>

                        <div class="inner-padding">
                            <div class="spacer-30"></div>
                            <hr>
                            <div class="spacer-30">
                                3、请说明您离开投保地的原因？前往何地？出行目的？（如是工作或学习，请提供单位或学校的名称和地址，并详细告知工作内容）
                            </div>
                            <div  style="float: left;margin-left: 10px">
                                    <%--<label><span class="asterisk"></span>${insuranceOrder.leaveReason}</label>--%>
                                <form:textarea rows="10" cols="101" path="insuranceOrderOffsite.leaveReason" maxlength="255"  readonly="true"></form:textarea>
                            </div>
                        </div>

                        <div class="inner-padding">
                            <div class="spacer-30"></div>
                            <hr>
                            <div class="spacer-30">
                                4、您一年中平均在投保地逗留的时间多长？每次回投保地的时间间隔多久？您往来投保地和上述异地之间经常乘坐的交通工具是什么？
                            </div>
                            <div  style="float: left;margin-left: 10px">
                                    <%--<label><span class="asterisk"></span>${insuranceOrder.stayTime}</label>--%>
                                <form:textarea rows="10" cols="101" path="insuranceOrderOffsite.stayTime" maxlength="255"  readonly="true"></form:textarea>
                            </div>
                        </div>

                        <div class="inner-padding">
                            <div class="spacer-30"></div>
                            <hr>
                            <div class="spacer-30">
                                5、您在投保地或异地是否已落实居住住所？如已落实请简述居住地址、环境？
                            </div>
                            <div style="float: left;margin-left: 10px">
                                <%--<label><span class="asterisk"></span>${offsiteAddress}</label>--%>
                                <%--<label><input readonly="true">${offsiteAddress}</input></label>--%>
                                    <form:textarea rows="10" cols="101" path="insuranceOrderOffsite.offsiteAddress" maxlength="255"  readonly="true"></form:textarea>
                                        <%--<label><input type="text" name="lname" value=${offsiteAddress}></label>--%>
                            </div>
                        </div>


                        <div class="inner-padding">
                            <div class="spacer-30"></div>
                            <hr>
                            <div class="spacer-30">
                                6、是否有其他需要说明事项：
                                <input type="radio"  name="otherMatter" value="true" <c:if test="${not empty insuranceOrder.insuranceOrderOffsite.otherMatter}"> checked</c:if> onclick="changeOtherMatter(true);"/> 是&nbsp;&nbsp;
                                <input type="radio"  name="otherMatter" value="false" <c:if test="${empty insuranceOrder.insuranceOrderOffsite.otherMatter}" > checked </c:if> onclick="changeOtherMatter(false);"/> 否
                            </div>
                                    <div id="otherMatterDiv" style='float: left;margin-left: 10px; <c:if test="${empty insuranceOrder.insuranceOrderOffsite.otherMatter}"> display:none;</c:if>' >
                                            <%--<label><span class="asterisk"></span>${otherMatter}</label>--%>
                                        <form:textarea id="insuranceOrderOffsite_otherMatter" rows="10" cols="101" path="insuranceOrderOffsite.otherMatter" maxlength="255"  readonly="true"></form:textarea>
                                            <%--<label><textarea>${insuranceOrder.otherMatter}</textarea></label>--%>
                                    </div>

                        </div>
                            </c:when>
                            <c:otherwise>
                                <div class="inner-padding">
                                    <div class="spacer-30" style="color: red;">无异地投保
                                    </div>
                                </div>

                            </c:otherwise>
                        </c:choose>

                </div><!-- End .inner-padding -->

                    <div class="btn-group" style="margin-left: 50px;">
                    <c:if test="${insuranceOrder.insuranceOrderOffsite!=null}">
                        <%--<button id="saveBtn" class="btn btn-default" type="submit"><i class="fa fa-save"></i>&nbsp;保&nbsp;存&nbsp;</button>--%>
                        <a href="javascript:void(0);" onclick="editItem('${insuranceOrder.orderId}')" class="btn btn-default"><i class="fa fa-pencil-square-o"></i>&nbsp;编辑</a>
                    </c:if>
                        <div class="btn-group"  style="margin-left: 150px;">
                            <a class="btn btn-default pull-right" href="insurances.do">返回</a>
                        </div>
                    </div>

                </div>



                <div class="spacer-30"></div>
                <hr>
                <div class="spacer-30"></div>
                <div class="row">
                    <div class="col-sm-2">
                        <div class="btn-group pull-right">

                            </div>
                    </div>
                    <div class="col-sm-2">

                        <%--<div class="btn-group pull-right">
                            <c:if test="${insuranceOrder.orderState=='UN_PAID'}">
                                <a class="btn btn-default pull-right" href="javascript:void(0);" onclick="updInsuranceOrderState('${insuranceOrder.orderId}','ON_PAID')">确认修改</a></c:if>
                        </div>--%>
                    </div>
                    <div class="col-sm-2">

                    </div>
                </div>


                <div class="spacer-30"></div>
                <hr>
                <div class="spacer-30"></div>
                <div class="row">
                    <div class="col-sm-2">
                        <div class="btn-group pull-right">

                        </div>
                    </div>
                </div>
            </div>
        </div>
        </form:form>

        <jsp:include page="../layouts/footer.jsp"/>
    </div>
    <!-- End #content -->
</div>
<!-- End #main -->
</div>

</body>
</html>
