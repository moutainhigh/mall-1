<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <!--<script src="../js/zoomify/zoomify.js"></script>-->

    <script src="../js/libs/jquery-1.11.0.min.js"></script>
    <script src="../js/libs/jquery-ui.min.js"></script>

    <script src="../js/district/district.js" type="text/javascript"></script>
    <script src="../js/profession/profession.js" type="text/javascript"></script>
    <script src="../js/zoomify/jquery.jqprint-0.3.js"></script>

    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <link rel="stylesheet" type="text/css" href="../css/insurance.css"/>

    <title></title>
    <script type="text/javascript">
        var insuredProvince = $.citySelector.getProvince('${map.insuranceOrder.insuranceOrderInsured.insuredProvince}');
        insuredProvince = insuredProvince.replace("省", "").replace("市", "");
        var insuredCity = $.citySelector.getCity(${map.insuranceOrder.insuranceOrderInsured.insuredCity});
        insuredCity = insuredCity.replace("市", "");
        var insuredDistrict = $.citySelector.getDistrict(${map.insuranceOrder.insuranceOrderInsured.insuredDistrict});
        insuredDistrict = insuredDistrict.replace("区", "").replace("县", "");
        var policyholderProvince = $.citySelector.getProvince(${map.insuranceOrder.insuranceOrderPolicyholder.policyholderProvince});
        policyholderProvince = policyholderProvince.replace("省", "").replace("市", "");
        var policyholderCity = $.citySelector.getCity(${map.insuranceOrder.insuranceOrderPolicyholder.policyholderCity});
        policyholderCity = policyholderCity.replace("市", "");
        var policyholderDistrict = $.citySelector.getDistrict(${map.insuranceOrder.insuranceOrderPolicyholder.policyholderDistrict});
        policyholderDistrict = policyholderDistrict.replace("区", "").replace("县", "");
        var insuredCareer = $.profession.getProfession('${map.insuranceOrder.insuranceOrderInsured.insuredCareer}');
        var policyholderCareer = $.profession.getProfession('${map.insuranceOrder.insuranceOrderPolicyholder.policyholderCareer}');
        //  $(document).ready(function () {

        $(function () {

            $("#insuredProvince").html(insuredProvince);

            $("#insuredCity").html(insuredCity);

            $("#insuredDistrict").html(insuredDistrict);

            $("#policyholderProvince").text(policyholderProvince);
            $("#policyholderCity").html(policyholderCity);
            $("#policyholderDistrict").html(policyholderDistrict);

            $("#policyholderProvinces").html(policyholderProvince);
            $("#policyholderCitys").html(policyholderCity);
            $("#policyholderDistricts").html(policyholderDistrict);

            $("#insuredCareer").html(insuredCareer);

            $("#policyholderCareer").html(policyholderCareer);

        });


        function jqPrints() {
            // $(".hidden-prints").css("display", "none");
            $(".prints").jqprint();
            // $(".hidden-prints").css("display", "block");
        }
    </script>
</head>
<body>
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

    <div id="content" class="clearfix">


        <header id="header-sec">
        </header>
        <div class="window">
            <div class="actionbar">
                <div class="pull-left">
                    <ul class="ext-tabs">
                        <li class="active">
                            <a class="btn btn-default pull-right"
                               href="javascript:void(0);" onclick="jqPrints()">打印</a>
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


            <div class="th-tab prints">
                <%--第一页--%>
                <div style="height: 356px;width: 100%;z-index: 20;position: absolute;"></div>
                <div style="position: absolute;z-index: 20;margin-top: 356px;">
                    <div class="al-tab">
                        <div class="al-line">
                            <div class="al-font div-row-2">
                                <div class="al-row-2"
                                     style="margin-top: 9px;">${map.insuranceOrder.insuranceOrderInsured.insuredName}</div>
                            </div>
                            <div class="al-font div-sex">
                                <div class="al-row-sex">
                                    <c:choose>
                                        <c:when test="${map.insuranceOrder.insuranceOrderInsured.insuredGender=='true'}">
                                            √
                                        </c:when>
                                        <c:otherwise>
                                            &nbsp;&nbsp;
                                        </c:otherwise>
                                    </c:choose>
                                </div>


                                <div>
                                    <c:choose>
                                        <c:when test="${map.insuranceOrder.insuranceOrderInsured.insuredGender=='false'}">
                                            √
                                        </c:when>
                                        <c:otherwise>
                                            &nbsp;&nbsp;
                                        </c:otherwise>
                                    </c:choose>

                                </div>
                            </div>
                            <div class="al-font div-row-4">
                                <div class="al-row-date">
                                    <div>${map.insurance_b_year}</div>
                                    <div style="margin-left: 56px;">${map.insurance_b_month}</div>
                                    <div style="margin-left: 50px;">${map.insurance_b_day}</div>
                                </div>
                            </div>
                            <div class="al-font div-row-2">
                                <div class="al-row-age">${map.age}</div>
                            </div>
                        </div>
                        <div class="al-line">
                            <div class="al-font div-row-5">
                                <div class="al-row-cardType" style="margin-top: 0px;">
                                    <div style="margin-left: -20px;">
                                        <c:choose>
                                            <c:when test="${map.insuranceOrder.insuranceOrderInsured.insuredMarriage=='居民身份证'}">
                                                √
                                            </c:when>
                                            <c:otherwise>
                                                &nbsp;&nbsp;
                                            </c:otherwise>
                                        </c:choose>

                                    </div>
                                    <div style="margin-left: 64px">
                                        <c:choose>
                                            <c:when test="${map.insuranceOrder.insuranceOrderInsured.insuredMarriage=='护照'}">
                                                √
                                            </c:when>
                                            <c:otherwise>
                                                &nbsp;&nbsp;
                                            </c:otherwise>
                                        </c:choose>

                                    </div>
                                    <div style="margin-left: 52px">
                                        <c:choose>
                                            <c:when test="${map.insuranceOrder.insuranceOrderInsured.insuredMarriage=='军官证'}">
                                                √
                                            </c:when>
                                            <c:otherwise>
                                                &nbsp;&nbsp;
                                            </c:otherwise>
                                        </c:choose>

                                    </div>
                                    <div style="margin-left: 65px">
                                        <c:choose>
                                            <c:when test="${map.insuranceOrder.insuranceOrderInsured.insuredMarriage!='军官证'&&map.insuranceOrder.insuranceOrderInsured.insuredMarriage!='护照'&&map.insuranceOrder.insuranceOrderInsured.insuredMarriage!='居民身份证'}">
                                                √
                                            </c:when>
                                            <c:otherwise>
                                                &nbsp;&nbsp;
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                            <div class="al-font">
                                <div class="card-num" style="letter-spacing:18.5px;margin-left: 100px;">
                                    ${map.insuranceOrder.insuranceOrderInsured.insuredCardNo}</div>
                            </div>
                        </div>

                        <div class="al-line">
                            <div class="al-font div-row-5">
                                <c:choose>
                                    <c:when test="${map.insurance_p_year!=null&&map.insurance_p_year!=''}">
                                        <div style="margin-left: 105px;float: left;">√</div>
                                        <div style="float: left;margin-left: 70px;font-size: 13px;margin-top: 2px;">${map.insurance_p_year}</div>
                                        <div style="float: left;margin-left: 30px;font-size: 13px;margin-top: 2px;">${map.insurance_p_month}</div>
                                        <div style="float: left;margin-left: 30px;font-size: 13px;margin-top: 2px;">${map.insurance_p_day}</div>
                                        <div style="margin-left: 45px;float: left;">&nbsp;</div>
                                    </c:when>
                                    <c:otherwise>
                                        <div style="margin-left: 105px;float: left;">&nbsp;</div>
                                        <div style="float: left;margin-left: 70px;font-size: 13px;margin-top: 2px;">
                                            &nbsp;&nbsp;&nbsp;&nbsp;
                                        </div>
                                        <div style="float: left;margin-left: 30px;font-size: 13px;margin-top: 2px;">
                                            &nbsp;&nbsp;
                                        </div>
                                        <div style="float: left;margin-left: 30px;font-size: 13px;margin-top: 2px;">
                                            &nbsp;&nbsp;
                                        </div>
                                        <div style="margin-left: 65px;float: left;">√</div>

                                    </c:otherwise>
                                </c:choose>
                                <%--<div style="margin-left: 105px;float: left;">&nbsp;</div>--%>
                                <%--<div style="float: left;margin-left: 70px;font-size: 13px;margin-top: 2px;">&nbsp;&nbsp;&nbsp;&nbsp;</div>--%>
                                <%--<div style="float: left;margin-left: 30px;font-size: 13px;margin-top: 2px;">&nbsp;&nbsp;</div>--%>
                                <%--<div style="float: left;margin-left: 30px;font-size: 13px;margin-top: 2px;">&nbsp;&nbsp;</div>--%>
                                <%--<div style="margin-left: 65px;float: left;">√</div>--%>
                                <%--<div style="margin-left: 105px;float: left;">√</div>--%>
                                <%--<div style="float: left;margin-left: 70px;font-size: 13px;margin-top: 2px;">&nbsp;&nbsp;&nbsp;&nbsp;</div>--%>
                                <%--<div style="float: left;margin-left: 30px;font-size: 13px;margin-top: 2px;">&nbsp;&nbsp;</div>--%>
                                <%--<div style="float: left;margin-left: 30px;font-size: 13px;margin-top: 2px;">&nbsp;&nbsp;</div>--%>
                                <%--<div style="margin-left: 65px;float: left;">√</div>--%>

                            </div>

                            <div class="al-font div-row-3" style="position: absolute">
                                <div style="margin-left: 130px;position: absolute;margin-top: 10px;"> ${map.insuranceOrder.insuranceOrderInsured.insuredCountry}</div>
                            </div>
                            <div class="al-font" style="margin-left: 350px;position: absolute;margin-top: 10px;">
                                ${map.insuranceOrder.insuranceOrderInsured.insuredHeight}
                            </div>
                            <div class="al-font" style="margin-left: 465px;position: absolute;margin-top: 10px;">
                                ${map.insuranceOrder.insuranceOrderInsured.insuredBodyWeight}
                            </div>


                        </div>

                        <div class="al-line">
                            <div class="al-font div-row-5">
                                <div style="margin-left: 102px;position: absolute">
                                    <c:choose>
                                        <c:when test="${map.insuranceOrder.insuranceOrderInsured.insuredMarriage=='未婚'}">
                                            √
                                        </c:when>
                                        <c:otherwise>
                                            &nbsp;&nbsp;
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div style="margin-left: 169px;position: absolute">
                                    <c:choose>
                                        <c:when test="${map.insuranceOrder.insuranceOrderInsured.insuredMarriage=='已婚'}">
                                            √
                                        </c:when>
                                        <c:otherwise>
                                            &nbsp;&nbsp;
                                        </c:otherwise>
                                    </c:choose>

                                </div>
                                <div style="margin-left: 235px;position: absolute">
                                    <c:choose>
                                        <c:when test="${map.insuranceOrder.insuranceOrderInsured.insuredMarriage=='离异'}">
                                            √
                                        </c:when>
                                        <c:otherwise>
                                            &nbsp;&nbsp;
                                        </c:otherwise>
                                    </c:choose>

                                </div>
                                <div style="margin-left: 312px;position: absolute">
                                    <c:choose>
                                        <c:when test="${map.insuranceOrder.insuranceOrderInsured.insuredMarriage=='丧偶'}">
                                            √
                                        </c:when>
                                        <c:otherwise>
                                            &nbsp;&nbsp;
                                        </c:otherwise>
                                    </c:choose>

                                </div>


                            </div>
                            <div class="al-font div-row-3">
                                <div style="position: absolute;margin-left: 105px;;margin-top: -3px;">
                                    ${map.insurance_q_tel}
                                </div>
                                <div style="position: absolute;margin-left: 170px;;margin-top: -3px;">
                                    ${map.insurance_h_tel}

                                </div>

                            </div>

                            <div class="al-font div-row-3">
                                <div style="margin-left: 810px;position: absolute;margin-top: -3px;">${map.insuranceOrder.insuranceOrderInsured.insuredMobile}</div>

                            </div>


                        </div>


                        <div class="al-line">
                            <div class="al-font div-row-7" style="margin-top: 13px;">

                                <div style="margin-left:75px;position: absolute">
                                    <div id="insuredProvince" style="width: 50px;"></div>
                                </div>
                                <div style="margin-left:135px;position: absolute">
                                    <div id="insuredCity" style="width: 50px;"></div>
                                </div>
                                <div style="margin-left:200px;position: absolute">
                                    <div id="insuredDistrict" style="width: 50px;"></div>
                                </div>
                                <div style="margin-left:320px;position: absolute">
                                    ${map.insuranceOrder.insuranceOrderInsured.insuredAddress}
                                </div>

                            </div>
                            <div class="al-font div-postcode" style="margin-top: 13px;margin-right: 35px;">

                                <div style="margin-right : 10px;letter-spacing:13px;">&nbsp;</div>
                            </div>
                        </div>

                        <div class="al-line">
                            <div class="al-font div-row-5" style="margin-top: 5px">
                                <div style="margin-left: 100px;">&nbsp;&nbsp;</div>

                            </div>
                            <div class="al-font div-row-3" style="position: absolute;margin-left: 10px;">
                                <div style="margin-left: 60px;position: absolute;margin-top: 15px;">
                                    <div id="insuredCareer" style="width: 200px;"></div>
                                </div>

                            </div>
                            <div class="al-font div-row-3" style="position: absolute;margin-left: 10px;">
                                <div style="margin-left: 370px;position: absolute;margin-top: 15px;">${map.insuranceOrder.insuranceOrderInsured.insuredIncome}</div>

                            </div>
                        </div>

                        <div class="al-line">
                            <div class="al-font div-row-7" style="margin-top: 15px;">

                                <div style="margin-left:100px;float: left;">
                                    &nbsp; &nbsp;
                                </div>
                                <div style="margin-left:35px;float: left;">
                                    &nbsp; &nbsp;
                                </div>
                                <div style="margin-left:35px;float: left;">
                                    &nbsp; &nbsp;
                                </div>
                                <div style="margin-left:70px;float: left;">
                                    &nbsp; &nbsp;
                                </div>

                            </div>
                            <div class="al-font div-postcode" style="margin-top: 15px;margin-right: 35px;">

                                <div style="margin-right : 10px;letter-spacing:13px;">518000</div>
                            </div>
                        </div>


                        <div class="al-line">
                            <div class="al-font ">
                                <div style="float: left;margin-top:15px;margin-left: 100px;">&nbsp;&nbsp;</div>
                                <div style="float: left;margin-top:15px;margin-left: 75px;">&nbsp;&nbsp;</div>
                            </div>
                            <div class="al-font " style="position: absolute;margin-left: 60px;">
                                <div style="position: absolute;margin-top:15px;margin-left: 100px;">${map.insuranceOrder.insuranceOrderInsured.insuredEmail}</div>
                            </div>
                            <div class="al-font div-row-7">
                                <div style="margin-left: 160px;float: left;">&nbsp;<!--√--></div>
                                <div style="margin-left: 40px;float: left;">&nbsp;<!--√-->
                                </div>
                                <div style="float: left;margin-left: 10px;">&nbsp;</div>

                            </div>


                        </div>


                    </div>

                    <div class="al-tab">
                        <div class="al-line" style="margin-top: 30px;">
                            <div class="al-font div-row-2">
                                <div class="al-row-2"
                                     style="margin-top: 9px;">${map.insuranceOrder.insuranceOrderPolicyholder.policyholderName}</div>
                            </div>
                            <div class="al-font div-sex">
                                <div class="al-row-sex">
                                    <c:choose>
                                        <c:when test="${map.insuranceOrder.insuranceOrderPolicyholder.policyholderGender=='true'}">
                                            √
                                        </c:when>
                                        <c:otherwise>
                                            &nbsp;&nbsp;
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div>
                                    <c:choose>
                                        <c:when test="${map.insuranceOrder.insuranceOrderPolicyholder.policyholderGender=='false'}">
                                            √
                                        </c:when>
                                        <c:otherwise>
                                            &nbsp;&nbsp;
                                        </c:otherwise>
                                    </c:choose>

                                </div>
                            </div>
                            <div class="al-font div-row-4">
                                <div class="al-row-date">
                                    <div>${map.policy_b_year}</div>
                                    <div style="margin-left: 56px;">${map.policy_b_month}</div>
                                    <div style="margin-left: 40px;">${map.policy_b_day}</div>
                                </div>
                            </div>
                            <div class="al-font div-row-2">
                                <div style="margin-left: 160px;">${map.insuranceOrder.insuranceOrderInsured.insuredRelation}</div>
                            </div>
                        </div>

                        <div class="al-line">
                            <div class="al-font div-row-5">
                                <div class="al-row-cardType" style="margin-top: 0px;position: absolute">
                                    <div style="margin-left: -10px;position: absolute">
                                        <c:choose>
                                            <c:when test="${map.insuranceOrder.insuranceOrderPolicyholder.policyholderMarriage=='居民身份证'}">
                                                √
                                            </c:when>
                                            <c:otherwise>
                                                &nbsp;&nbsp;
                                            </c:otherwise>
                                        </c:choose></div>

                                    <div style="margin-left: 64px;position: absolute">
                                        <c:choose>
                                            <c:when test="${map.insuranceOrder.insuranceOrderPolicyholder.policyholderMarriage=='护照'}">
                                                √
                                            </c:when>
                                            <c:otherwise>
                                                &nbsp;&nbsp;
                                            </c:otherwise>
                                        </c:choose>

                                    </div>
                                    <div style="margin-left: 125px;position: absolute">
                                        <c:choose>
                                            <c:when test="${map.insuranceOrder.insuranceOrderPolicyholder.policyholderMarriage=='军官证'}">
                                                √
                                            </c:when>
                                            <c:otherwise>
                                                &nbsp;&nbsp;
                                            </c:otherwise>
                                        </c:choose>

                                    </div>
                                    <div style="margin-left: 200px;position: absolute">
                                        <c:choose>
                                            <c:when test="${map.insuranceOrder.insuranceOrderPolicyholder.policyholderMarriage!='军官证'&&map.insuranceOrder.insuranceOrderPolicyholder.policyholderMarriage!='护照'&&map.insuranceOrder.insuranceOrderPolicyholder.policyholderMarriage!='居民身份证'}">
                                                √
                                            </c:when>
                                            <c:otherwise>
                                                &nbsp;&nbsp;
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                            <div class="al-font" style="position: absolute">
                                <div class="card-num"
                                     style="letter-spacing:17.8px;margin-left: 108px;position: absolute;margin-top: 10px;">${map.insuranceOrder.insuranceOrderPolicyholder.policyholderCardNo}</div>
                            </div>
                        </div>


                        <div class="al-line">
                            <div class="al-font div-row-5" style="margin-top: 3px;position: absolute">
                                <%--<c:choose>--%>
                                <%--<c:when test="${map.policy_p_year!=null&&map.policy_p_year!=''}">--%>
                                <%--<div style="margin-left: 110px;float: left;">√</div>--%>
                                <%--<div style="float: left;margin-left: 60px;font-size: 13px;margin-top: 2px;">${map.policy_p_year}</div>--%>
                                <%--<div style="float: left;margin-left: 30px;font-size: 13px;margin-top: 2px;">${map.policy_p_month}</div>--%>
                                <%--<div style="float: left;margin-left: 30px;font-size: 13px;margin-top: 2px;">${map.policy_p_day}</div>--%>
                                <%--<div style="margin-left: 35px;float: left;">&nbsp;</div>--%>
                                <%--</c:when>--%>
                                <%--<c:otherwise>--%>
                                <%--<div style="margin-left: 105px;float: left;">&nbsp;</div>--%>
                                <%--<div style="float: left;margin-left: 60px;font-size: 13px;margin-top: 2px;">&nbsp;&nbsp;&nbsp;&nbsp;</div>--%>
                                <%--<div style="float: left;margin-left: 30px;font-size: 13px;margin-top: 2px;">&nbsp;&nbsp;</div>--%>
                                <%--<div style="float: left;margin-left: 30px;font-size: 13px;margin-top: 2px;">&nbsp;&nbsp;</div>--%>
                                <%--<div style="margin-left: 35px;float: left;">√</div>--%>
                                <%--</c:otherwise>--%>
                                <%--</c:choose>--%>
                                <c:choose>
                                    <c:when test="${map.policy_p_year!=null&&map.policy_p_year!=''}">
                                        <div style="margin-left: 115px;position:absolute;">√</div>
                                        <div style="position:absolute;margin-left: 190px;font-size: 13px;margin-top: 2px;">${map.policy_p_year}</div>
                                        <div style="position:absolute;margin-left: 245px;font-size: 13px;margin-top: 2px;">${map.policy_p_month}</div>
                                        <div style="position:absolute;margin-left: 295px;font-size: 13px;margin-top: 2px;">${map.policy_p_day}</div>
                                        <div style="margin-left: 345px;float: left;">&nbsp;</div>
                                    </c:when>
                                    <c:otherwise>
                                        <div style="margin-left: 115px;position:absolute;">&nbsp;</div>
                                        <div style="position:absolute;margin-left: 190px;font-size: 13px;margin-top: 2px;">
                                            &nbsp;
                                        </div>
                                        <div style="position:absolute;margin-left: 245px;font-size: 13px;margin-top: 2px;">
                                            &nbsp;
                                        </div>
                                        <div style="position:absolute;margin-left: 295px;font-size: 13px;margin-top: 2px;">
                                            &nbsp;
                                        </div>
                                        <div style="margin-left: 345px;float: left;">√</div>

                                    </c:otherwise>
                                </c:choose>


                            </div>

                            <div class="al-font div-row-3" style="position: absolute">
                                <div style="margin-left: 535px;position: absolute;margin-top: 15px;width: 200px;"> ${map.insuranceOrder.insuranceOrderPolicyholder.policyholderCountry}</div>
                            </div>
                            <div class="al-font" style="margin-left: 790px;position: absolute;margin-top: 15px;">
                                ${map.insuranceOrder.insuranceOrderPolicyholder.policyholderHeight}
                            </div>
                            <div class="al-font" style="margin-left: 905px;position: absolute;margin-top: 15px;">
                                ${map.insuranceOrder.insuranceOrderPolicyholder.policyholderBodyWeight}
                            </div>


                        </div>


                        <div class="al-line">
                            <div class="al-font div-row-5" style="position: absolute;margin-top: 7px;">
                                <div style="margin-left: 102px;position: absolute;margin-top: -5px;">
                                    <c:choose>
                                        <c:when test="${map.insuranceOrder.insuranceOrderPolicyholder.policyholderMarriage=='未婚'}">
                                            √
                                        </c:when>
                                        <c:otherwise>
                                            &nbsp;&nbsp;
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div style="margin-left: 175px;position: absolute;margin-top: -5px;">
                                    <c:choose>
                                        <c:when test="${map.insuranceOrder.insuranceOrderPolicyholder.policyholderMarriage=='已婚'}">
                                            √
                                        </c:when>
                                        <c:otherwise>
                                            &nbsp;&nbsp;
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div style="margin-left: 245px;position: absolute;margin-top: -5px;">
                                    <c:choose>
                                        <c:when test="${map.insuranceOrder.insuranceOrderPolicyholder.policyholderMarriage=='离异'}">
                                            √
                                        </c:when>
                                        <c:otherwise>
                                            &nbsp;&nbsp;
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div style="margin-left: 315px;position: absolute;margin-top: -5px;">
                                    <c:choose>
                                        <c:when test="${map.insuranceOrder.insuranceOrderPolicyholder.policyholderMarriage=='丧偶'}">
                                            √
                                        </c:when>
                                        <c:otherwise>
                                            &nbsp;&nbsp;
                                        </c:otherwise>
                                    </c:choose>
                                </div>


                            </div>
                            <div class="al-font div-row-3" style="position: absolute;margin-left: 300px;">
                                <div style="margin-left:245px;position: absolute;margin-top: 15px;">
                                    ${map.policy_q_tel}
                                </div>
                                <div style="margin-left:305px;position: absolute; margin-top: 15px;">
                                    ${map.policy_h_tel}

                                </div>

                            </div>

                            <div class="al-font div-row-3" style="position: absolute;margin-left: 700px;">
                                <div style="margin-left: 115px;position: absolute;margin-top: 15px;">${map.insuranceOrder.insuranceOrderPolicyholder.policyholderMobile}</div>

                            </div>


                        </div>

                        <div class="al-line" style="margin-top: 2px;">
                            <div class="al-font div-row-7" style="margin-top: 13px;position: absolute">

                                <div style="margin-left:90px;position: absolute">
                                    <div id="policyholderProvince" style="width: 40px;"></div>
                                </div>
                                <div style="margin-left:180px;position: absolute">
                                    <div id="policyholderCity" style="width: 40px;"></div>
                                </div>
                                <div style="margin-left:250px;position: absolute">
                                    <div id="policyholderDistrict" style="width: 40px;"></div>
                                </div>
                                <div style="margin-left:355px;position: absolute;width: 360px;">
                                    ${map.insuranceOrder.insuranceOrderPolicyholder.policyholderAddress}
                                </div>

                            </div>
                            <div class="al-font div-postcode" style="margin-top: 13px;margin-right: 35px;">

                                <div style="letter-spacing:19px;">518000</div>
                            </div>
                        </div>


                        <div class="al-line" style="position: absolute">
                            <div class="al-font div-row-5" style="margin-top: 5px">
                                <div style="margin-left: 100px;">&nbsp;&nbsp;&nbsp;&nbsp;</div>

                            </div>
                            <div class="al-font div-row-3">
                                <div style="margin-left: 70px;">
                                    <div id="policyholderCareer" style="width: 200px;"></div>
                                </div>

                            </div>
                            <div class="al-font div-row-3" style="position: absolute">
                                <div style="margin-left: 110px;position: absolute;margin-top: 15px;">${map.insuranceOrder.insuranceOrderPolicyholder.policyholderIncome}</div>

                            </div>
                        </div>

                        <div class="al-line" style="position: absolute;margin-top: 35px;">
                            <div class="al-font div-row-7" style="margin-top: 15px;">

                                <div style="margin-left:100px;float: left;">
                                    &nbsp;&nbsp;
                                </div>
                                <div style="margin-left:35px;float: left;">
                                    &nbsp;&nbsp;
                                </div>
                                <div style="margin-left:35px;float: left;">
                                    &nbsp;&nbsp;
                                </div>
                                <div style="margin-left:70px;float: left;">
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                </div>

                            </div>
                            <div class="al-font div-postcode"
                                 style="margin-top: 15px;margin-left: 520px;position: absolute">

                                <div style="position: absolute;letter-spacing:19px;">618001</div>
                            </div>
                        </div>

                        <div class="al-line" style="position: absolute;margin-top:68px">
                            <div class="al-font ">
                                <div style="float: left;margin-top:15px;margin-left: 100px;">&nbsp;&nbsp;</div>
                                <div style="float: left;margin-top:15px;margin-left: 75px;">&nbsp;&nbsp;</div>
                            </div>
                            <div class="al-font " style="position: absolute">
                                <div style="float: left;margin-top:15px;margin-left: 165px;">${map.insuranceOrder.insuranceOrderPolicyholder.policyholderEmail}</div>
                            </div>
                            <div class="al-font div-row-7">
                                <div style="margin-left: 160px;float: left;">&nbsp;&nbsp;&nbsp;</div>


                            </div>


                        </div>


                        <div class="al-line" style="position: absolute;margin-top: 100px">
                            <div class="al-font div-row-7" style="margin-top: 38px;position: absolute">

                                <div style="margin-left:0px;position: absolute">
                                    <div id=""></div>
                                </div>
                                <div style="position: absolute;margin-left:130px;width: 40px;">
                                    <div id="policyholderProvinces"></div>
                                </div>
                                <div style="margin-left:225px;position: absolute;width: 40px;">
                                    <div id="policyholderCitys"></div>
                                </div>
                                <div style="margin-left:300px;position: absolute;width: 40px;">
                                    <div id="policyholderDistricts"></div>
                                </div>
                                <div style="margin-left:395px;position: absolute;width: 200px;">
                                    ${map.insuranceOrder.insuranceOrderPolicyholder.policyholderAddress}
                                </div>

                            </div>

                            <div class="al-font div-postcode"
                                 style="margin-top: 38px;position: absolute;margin-left: 650px;">

                                <div style="letter-spacing:19px;">518000</div>

                            </div>
                            <div class="al-font div-postcode"
                                 style="position: absolute;margin-top: 38px;margin-left: 880px;">
                                ${map.insuranceOrder.insuranceOrderPolicyholder.policyholderMobile}
                            </div>

                        </div>


                        <div class="al-line" style="position: absolute;margin-top: 157px">
                            <div class="al-font div-row-3" style="margin-top: 60px;float: left;">
                                <div style="margin-left: 202px;float: left;">&nbsp;</div>
                                <div style="margin-left: 55px;float: left;">&nbsp;</div>

                            </div>

                            <div class="al-font div-row-3"
                                 style="margin-left: 280px;position: absolute;margin-top: 38px">
                                <div style="margin-left: 100px;position: absolute;">${map.insuranceOrder.insuranceOrderPolicyholder.policyholderMobile}</div>


                            </div>


                        </div>


                    </div>

                    <div style="height: 250px;">
                        <div class="al-tab" style="margin-top: 400px;">
                            <c:if test="${map.beneficiaryList[0].beneficiaryName!=null&&''!=map.beneficiaryList[0].beneficiaryName}">
                                <div class="al-line">
                                    <div class="al-font">
                                        <div style="position: absolute;margin-left: 5px;">${map.beneficiaryList[0].beneficiaryName}</div>
                                        <div style="position: absolute;margin-left: 95px;">
                                            <c:choose>
                                                <c:when test="${map.beneficiaryList[0].beneficiaryGender=='true'}">
                                                    男
                                                </c:when>
                                                <c:otherwise>
                                                    女

                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <div style="position: absolute;margin-left: 180px;">${map.beneficiaryList[0].beneficiaryOrder}</div>
                                        <div style="position: absolute;margin-left: 260px;">${map.beneficiaryList[0].beneficiaryProportion}</div>
                                        <div style="position: absolute;margin-left: 325px;">
                                            <fmt:formatDate value="${map.beneficiaryList[0].beneficiaryBirthday}"
                                                            pattern="yyyy-MM-dd" type="date" dateStyle="long"/>
                                        </div>
                                        <div style="position: absolute;margin-left: 435px;">${map.beneficiaryList[0].insuredRelation}</div>
                                    </div>
                                    <div class="al-font"
                                         style="position: absolute;margin-left: 510px;margin-top: -6px;">
                                        <c:if test="${map.beneficiaryList[0].beneficiaryCardType=='居民身份证'}">
                                            √</c:if>√
                                    </div>

                                    <div class="al-font"
                                         style="position: absolute;margin-left: 590px;margin-top: -6px;">
                                        <c:if test="${map.beneficiaryList[0].beneficiaryCardType!='居民身份证'}">
                                            √</c:if></div>
                                    <div class="al-font"
                                         style="position: absolute;margin-left: 945px;margin-top: -6px;font-size: 10px;">
                                        <c:if test="${map.beneficiaryList[0].beneficiaryCardType=='长期'}">
                                            √</c:if>
                                    </div>
                                    <div class="al-font"
                                         style="position: absolute;margin-left: 840px;margin-top: -6px;font-size: 10px;">
                                        <c:if test="${map.beneficiaryList[0].beneficiaryCardType!='长期'}">
                                            <fmt:formatDate value="${map.beneficiaryList[0].beneficiaryCardPeroid}"
                                                            pattern="yyyy-MM-dd" type="date" dateStyle="long"/>
                                        </c:if>
                                    </div>
                                    <div class="al-font"
                                         style="position: absolute;margin-left: 390px;margin-top: 18px;">
                                        <div class="card-num"
                                             style="letter-spacing:19.8px;position: absolute;margin-top: 10px;">${map.beneficiaryList[0].beneficiaryCardNo}</div>
                                    </div>
                                </div>


                            </c:if>


                        </div>

                        <div class="al-tab" style="margin-top: 25px;">
                            <c:if test="${map.beneficiaryList[1].beneficiaryName!=null&&''!=map.beneficiaryList[1].beneficiaryName}">
                                <div class="al-line">
                                    <div class="al-font">
                                        <div style="position: absolute;margin-left: 5px;">${map.beneficiaryList[1].beneficiaryName}</div>
                                        <div style="position: absolute;margin-left: 95px;">

                                            <c:choose>
                                                <c:when test="${map.beneficiaryList[1].beneficiaryGender=='true'}">
                                                    男
                                                </c:when>
                                                <c:otherwise>
                                                    女

                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <div style="position: absolute;margin-left: 180px;">${map.beneficiaryList[1].beneficiaryOrder}</div>
                                        <div style="position: absolute;margin-left: 260px;">${map.beneficiaryList[1].beneficiaryProportion}</div>
                                        <div style="position: absolute;margin-left: 325px;"><fmt:formatDate
                                                value="${map.beneficiaryList[1].beneficiaryBirthday}"
                                                pattern="yyyy-MM-dd" type="date" dateStyle="long"/></div>
                                        <div style="position: absolute;margin-left: 435px;">${map.beneficiaryList[1].insuredRelation}</div>
                                    </div>
                                    <div class="al-font"
                                         style="position: absolute;margin-left: 510px;margin-top: -3px;">
                                        <c:if test="${map.beneficiaryList[1].beneficiaryCardType=='居民身份证'}">
                                            √</c:if></div>

                                    <div class="al-font"
                                         style="position: absolute;margin-left: 590px;margin-top: -3px;">
                                        <c:if test="${map.beneficiaryList[1].beneficiaryCardType!='居民身份证'}">
                                            √</c:if></div>
                                    <div class="al-font"
                                         style="position: absolute;margin-left: 945px;margin-top: -1px;font-size: 10px;">
                                        <c:if test="${map.beneficiaryList[1].beneficiaryCardType=='长期'}">
                                            √</c:if>
                                    </div>
                                    <div class="al-font"
                                         style="position: absolute;margin-left: 840px;margin-top: -1px;font-size: 10px;">
                                        <c:if test="${map.beneficiaryList[1].beneficiaryCardType!='长期'}">

                                            <fmt:formatDate value="${map.beneficiaryList[1].beneficiaryCardPeroid}"
                                                            pattern="yyyy-MM-dd" type="date" dateStyle="long"/>
                                        </c:if></div>
                                    <div class="al-font"
                                         style="position: absolute;margin-left: 390px;margin-top: 18px;">
                                        <div class="card-num"
                                             style="letter-spacing:19.8px;position: absolute;margin-top: 10px;">${map.beneficiaryList[1].beneficiaryCardNo}</div>
                                    </div>
                                </div>

                            </c:if>

                        </div>

                        <div class="al-tab" style="margin-top: 27px;">
                            <c:if test="${map.beneficiaryList[2].beneficiaryName!=null&&''!=map.beneficiaryList[2].beneficiaryName}">
                                <div class="al-line">
                                    <div class="al-font">
                                        <div style="position: absolute;margin-left: 5px;">${map.beneficiaryList[2].beneficiaryName}</div>
                                        <div style="position: absolute;margin-left: 95px;">
                                            <c:choose>
                                                <c:when test="${map.beneficiaryList[2].beneficiaryGender=='true'}">
                                                    男
                                                </c:when>
                                                <c:otherwise>
                                                    女

                                                </c:otherwise>
                                            </c:choose></div>
                                        <div style="position: absolute;margin-left: 180px;">${map.beneficiaryList[2].beneficiaryOrder}</div>
                                        <div style="position: absolute;margin-left: 260px;">${map.beneficiaryList[2].beneficiaryProportion}</div>
                                        <div style="position: absolute;margin-left: 325px;">
                                            <fmt:formatDate value="${map.beneficiaryList[2].beneficiaryBirthday}"
                                                            pattern="yyyy-MM-dd" type="date"
                                                            dateStyle="long"/></div>
                                        <div style="position: absolute;margin-left: 435px;">${map.beneficiaryList[2].insuredRelation}</div>
                                    </div>
                                    <div class="al-font"
                                         style="position: absolute;margin-left: 510px;margin-top: -3px;">
                                        <c:if test="${map.beneficiaryList[2].beneficiaryCardType=='居民身份证'}">
                                            √</c:if></div>

                                    <div class="al-font"
                                         style="position: absolute;margin-left: 590px;margin-top: -3px;">
                                        <c:if test="${map.beneficiaryList[2].beneficiaryCardType!='居民身份证'}">
                                            √</c:if></div>
                                    <div class="al-font"
                                         style="position: absolute;margin-left: 945px;margin-top: -1px;font-size: 10px;">
                                        <c:if test="${map.beneficiaryList[2].beneficiaryCardType=='长期'}">
                                            √</c:if>
                                    </div>
                                    <div class="al-font"
                                         style="position: absolute;margin-left: 840px;margin-top: -1px;font-size: 10px;">
                                        <c:if test="${map.beneficiaryList[2].beneficiaryCardType!='长期'}">
                                            <fmt:formatDate value="${map.beneficiaryList[2].beneficiaryCardPeroid}"
                                                            pattern="yyyy-MM-dd" type="date" dateStyle="long"/>
                                        </c:if></div>
                                    <div class="al-font"
                                         style="position: absolute;margin-left: 390px;margin-top: 18px;">
                                        <div class="card-num"
                                             style="letter-spacing:19.8px;position: absolute;margin-top: 10px;">${map.beneficiaryList[2].beneficiaryCardNo}</div>
                                    </div>
                                </div>

                            </c:if>
                        </div>


                    </div>

                    <%--第二页--%>
                    <div style="margin-top: 75px;">
                        <%--B1--%>
                        <div class="a-div-line-con" style="width: 480px;height: 40px;">
                            <div>
                                <div class="a-tick" style="margin-left: 95px;margin-top: 8px;"><!--√--></div>
                                <div class="a-tick" style="margin-left: 160px;margin-top: 8px;"><!--√--></div>
                                <div class="a-tick" style="margin-left: 223px;margin-top: 8px;"><!--√--></div>
                                <div class="a-tick" style="margin-left: 305px;margin-top: 8px;">√</div>
                                <div class="a-tick" style="margin-left: 368px;margin-top: 8px;"><!--√--></div>
                            </div>
                        </div>
                        <%--B2--%>
                        <div class="a-div-line-con" style="width: 520px;height: 40px;">
                            <div class="a-tick" style="margin-left: 93px;margin-top: 6px;"><!--√--></div>
                            <div class="a-tick" style="margin-left: 93px;margin-top: 20px;">√</div>
                        </div>
                        <br/>
                        <%--B3--%>
                        <div class="a-div-line-con" style="width: 480px;height: 40px;">
                            <div>
                                <div class="a-tick" style="margin-left: 145px;">√</div>
                                <div class="a-tick" style="margin-left: 207px;"><!--√--></div>
                            </div>
                        </div>
                        <%--B4--%>
                        <div class="a-div-line-con" style="width: 520px;height: 40px;">
                            <div class="a-tick" style="margin-left: 145px;">√</div>
                            <div class="a-tick" style="margin-left: 223px;"><!--√--></div>
                        </div>
                    </div>

                    <%--主保险合同 start--%>
                    <div style="height: 60px;margin-top: 65px;">
                        <%--B5--%>
                        <div class="a-div-table-2" style="width: 210px;">
                            <div class="a-tick al-font"
                                 style="margin-left: 2px;">${insuranceOrder.insuranceProduct.prodName}</div>
                        </div>
                        <%--B6--%>
                        <div class="a-div-table-2" style="width: 128px;">
                            <div class="a-tick" style="margin-left: 11px;"><!--√--><span
                                    style="padding-left: 15px;"><!--√--></span></div>
                            <div class="a-tick" style="margin-left: 11px;margin-top: 20px;"><!--√--> <span
                                    style="padding-left: 25px;"><!--√--></span></div>
                            <div class="a-tick" style="margin-left: 11px;margin-top: 40px">√</div>
                        </div>
                        <%--B7--%>
                        <div class="a-div-table-2" style="width: 226px;">
                            <div class="a-tick" style="margin-left: 60px;"><!--√--><span
                                    style="padding-left: 15px;"><!--√--></span></div>
                            <div class="a-tick" style="margin-left: 60px;margin-top: 20px;"><!--√--> <span
                                    style="padding-left: 25px;"><!--√--></span></div>
                            <div class="a-tick" style="margin-left: 60px;margin-top: 40px">√</div>
                        </div>
                        <%--B8--%>
                        <div class="a-div-table-2" style="width: 138px;">
                            <div class="a-tick al-font" style="margin-left: 2px;"><!--√--></div>
                        </div>
                        <%--B9--%>
                        <div class="a-div-table-2" style="width: 145px;">
                            <div class="a-tick" style="margin-left: 37px;">√</div>
                            <div class="a-tick" style="margin-left: 37px;margin-top: 20px"><!--√--></div>
                        </div>
                        <%--B10--%>
                        <div class="a-div-table-2" style="width: 153px;">
                            <div class="a-tick al-font" style="margin-left: 2px;"><!--√--></div>
                        </div>
                    </div>
                    <div style="height: 60px;margin-top: 60px;">
                        <%--领取时间--%>
                        <div class="a-div-table-2" style="width: 210px;">
                            <div class="a-tick" style="margin-left: 11px;margin-top: 20px;">√</div>
                            <div class="a-tick" style="margin-left: 11px;margin-top: 50px;"><!--√--></div>
                            <div class="a-tick" style="margin-left: 11px;margin-top: 77px;"><!--√--><span
                                    style="padding-left: 90px;"><!--√--></span></div>
                        </div>
                        <%--领取方式--%>
                        <div class="a-div-table-2" style="width: 128px;">
                            <div class="a-tick" style="margin-left: 11px;"><!--√--></div>
                            <div class="a-tick" style="margin-left: 11px;margin-top: 30px;"><!--√--></div>
                            <div class="a-tick" style="margin-left: 11px;margin-top: 55px"><!--√--><span
                                    style="padding-left: 30px;font-size: 14px;"><!--√--></span></div>
                            <div class="a-tick" style="margin-left: 11px;margin-top: 85px"><!--√--></div>
                        </div>
                        <%--给付方式--%>
                        <div class="a-div-table-2" style="width: 226px;">
                            <div class="a-tick" style="margin-left: 10px;margin-top: 2px;"><!--√--><span
                                    style="padding-left: 225px;"><!--√--></span></div>
                            <div class="a-tick" style="margin-left: 10px;margin-top: 20px;"><!--√--></div>
                            <div class="a-tick" style="margin-left: 10px;margin-top: 40px"><!--√--><span
                                    style="position:absolute;padding-left: 265px;"><!--√--> </span><span
                                    style="position:absolute;padding-left: 310px;"><!--√--> </span></div>
                            <div class="a-tick" style="margin-left: 10px;margin-top: 60px"><!--√--><span
                                    style="padding-left: 25px;font-size: 14px;"><!--√--></span></div>
                        </div>
                    </div>
                    <%--主保险合同 end--%>

                    <%--附加保险合同 start--%>
                    <div style="height: 160px;margin-top: 105px;">
                        <%--循环渲染--%>
                        <%--B12--%>
                        <div style="height:40px;">
                            <div class="a-div-table-2" style="width: 338px;">
                                <div class="a-tick al-font" style="margin-left: 2px;">&nbsp;</div>
                            </div>
                            <%--B13--%>
                            <div class="a-div-table-2" style="width: 156px;">
                                <div class="a-tick al-font" style="margin-left: 2px;">&nbsp;</div>
                            </div>
                            <%--B14--%>
                            <div class="a-div-table-2" style="width: 156px;">
                                <div class="a-tick al-font" style="margin-left: 2px;">&nbsp;</div>
                            </div>
                            <%--B15--%>
                            <div class="a-div-table-2" style="width: 175px;">
                                <div class="a-tick al-font" style="margin-left: 2px;">&nbsp;</div>
                            </div>
                            <%--B16--%>
                            <div class="a-div-table-2" style="width: 175px;">
                                <div class="a-tick al-font" style="margin-left: 2px;">&nbsp;</div>
                            </div>
                        </div>
                    </div>
                    <%--附加保险合同 end--%>

                    <%--B17 start--%>
                    <div style="margin-top: 10px;height: 80px;">
                        <div class="a-price"><span style="margin-left: -15px;">&nbsp;</span><span
                                style="margin-left: 60px;">&nbsp;</span><span
                                style="margin-left: 130px;">&nbsp;</span><span
                                style="margin-left: 190px;">&nbsp;</span><span
                                style="margin-left: 260px;">&nbsp;</span><span
                                style="margin-left: 320px;">&nbsp;</span><span
                                style="margin-left: 400px;">&nbsp;</span><span
                                style="margin-left: 470px;">&nbsp;</span><span
                                style="margin-left: 550px;">&nbsp;</span></div>
                        <div class="a-price" style="margin-top: 40px;"><span
                                style="margin-left: -15px;">&nbsp;</span><span
                                style="margin-left: 60px;">&nbsp;</span><span
                                style="margin-left: 130px;">&nbsp;</span><span
                                style="margin-left: 190px;">&nbsp;</span><span
                                style="margin-left: 260px;">&nbsp;</span><span
                                style="margin-left: 320px;">&nbsp;</span><span
                                style="margin-left: 400px;">&nbsp;</span><span
                                style="margin-left: 470px;">&nbsp;</span><span
                                style="margin-left: 550px;">&nbsp;</span></div>
                    </div>
                    <%--B17 end--%>

                    <%--第二页 可保资料告知 start--%>
                    <div style="margin-top: 75px;height: 640px;">
                        <div style="position:relative;height: 54px;margin-top: 40px;">
                            <%--B12--%>
                            <div class="a-tick" style="margin-left: 645px;margin-top: 15px;"><c:if
                                    test="${map.insurance_matterList[0].insuredResult=='true'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 685px;margin-top: 15px;"><c:if
                                    test="${map.insurance_matterList[0].insuredResult=='false'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 730px;margin-top: 15px;"><c:if
                                    test="${map.insurance_matterList[0].policyholderResult=='true'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 765px;margin-top: 15px;"><c:if
                                    test="${map.insurance_matterList[0].policyholderResult=='false'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 805px;width:180px;font-size: 13px;">
                                ${map.insurance_matterList[0].insuredRemark}&nbsp;${map.insurance_matterList[0].policyholderRemark}
                            </div>
                        </div>
                        <div style="position:relative;height: 53px;">
                            <%--B12--%>

                            <div class="a-tick" style="margin-left: 645px;margin-top: 15px;"><c:if
                                    test="${map.insurance_matterList[1].insuredResult=='true'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 685px;margin-top: 15px;"><c:if
                                    test="${map.insurance_matterList[1].insuredResult=='false'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 730px;margin-top: 15px;"><c:if
                                    test="${map.insurance_matterList[1].policyholderResult=='true'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 765px;margin-top: 15px;"><c:if
                                    test="${map.insurance_matterList[1].policyholderResult=='false'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 805px;width:180px;font-size: 13px;">
                                ${map.insurance_matterList[1].insuredRemark}&nbsp;${map.insurance_matterList[1].policyholderRemark}
                            </div>
                        </div>
                        <div style="position:relative;height: 53px;">
                            <%--B12--%>
                            <div class="a-tick" style="margin-left: 645px;margin-top: 15px;"><c:if
                                    test="${map.insurance_matterList[2].insuredResult=='true'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 685px;margin-top: 15px;"><c:if
                                    test="${map.insurance_matterList[2].insuredResult=='false'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 730px;margin-top: 15px;"><c:if
                                    test="${map.insurance_matterList[2].policyholderResult=='true'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 765px;margin-top: 15px;"><c:if
                                    test="${map.insurance_matterList[2].policyholderResult=='false'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 805px;width:180px;font-size: 13px;">
                                ${map.insurance_matterList[2].insuredRemark}&nbsp;${map.insurance_matterList[2].policyholderRemark}
                            </div>
                        </div>
                        <div style="position:relative;height: 25px;">
                            <%--B12--%>
                            <div class="a-tick" style="margin-left: 645px;margin-top: 5px;"><c:if
                                    test="${map.insurance_matterList[3].insuredResult=='true'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 685px;"><c:if
                                    test="${map.insurance_matterList[3].insuredResult=='false'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 730px;;margin-top: 5px;"><c:if
                                    test="${map.insurance_matterList[3].policyholderResult=='true'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 765px;"><c:if
                                    test="${map.insurance_matterList[3].policyholderResult=='false'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 805px;width:180px;font-size: 13px;">
                                ${map.insurance_matterList[3].insuredRemark}&nbsp;${map.insurance_matterList[3].policyholderRemark}
                            </div>
                        </div>
                        <div style="position:relative;height: 60px;">
                            <%--B12--%>
                            <div style="position:absolute;font-size: 14px;margin-left: 240px;">${map.insurance_matter_value[0].m_value0}</div>
                            <div style="position:absolute;font-size: 14px;margin-left: 310px;">${map.insurance_matter_value[0].m_value1}</div>
                            <div style="position:absolute;font-size: 14px;margin-left: 62px;margin-top: 20px">${map.insurance_matter_value[0].m_value2}</div>
                            <div style="position:absolute;font-size: 14px;margin-left: 350px;margin-top: 20px">${map.insurance_matter_value[0].m_value3}</div>
                            <div style="position:absolute;font-size: 14px;margin-left: 455px;margin-top: 20px">${map.insurance_matter_value[0].m_value4}</div>
                            <div style="position:absolute;font-size: 14px;margin-left: 35px;margin-top: 40px">${map.insurance_matter_value[0].m_value5}</div>
                            <div style="position:absolute;font-size: 14px;margin-left: 455px;margin-top: 40px">${map.insurance_matter_value[0].m_value6}</div>
                            <div class="a-tick" style="margin-left: 645px;margin-top: 15px;"><c:if
                                    test="${map.insurance_matterList[4].insuredResult=='true'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 685px;margin-top: 15px;"><c:if
                                    test="${map.insurance_matterList[4].insuredResult=='false'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 730px;margin-top: 15px;"><c:if
                                    test="${map.insurance_matterList[4].policyholderResult=='true'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 765px;margin-top: 15px;"><c:if
                                    test="${map.insurance_matterList[4].policyholderResult=='false'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 805px;width:180px;font-size: 13px;">
                                ${map.insurance_matterList[4].insuredRemark}&nbsp;${map.insurance_matterList[4].policyholderRemark}
                            </div>
                        </div>
                        <div style="position:relative;height: 30px;">
                            <%--B12--%>
                            <div class="a-tick" style="margin-left: 645px;"><c:if
                                    test="${map.insurance_matterList[5].insuredResult=='true'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 685px;"><c:if
                                    test="${map.insurance_matterList[5].insuredResult=='false'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 730px;"><c:if
                                    test="${map.insurance_matterList[5].policyholderResult=='true'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 765px;"><c:if
                                    test="${map.insurance_matterList[5].policyholderResult=='false'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 805px;width:180px;font-size: 13px;">
                                ${map.insurance_matterList[5].insuredRemark}&nbsp;${map.insurance_matterList[5].policyholderRemark}
                            </div>
                        </div>
                        <div style="position:relative;height: 30px;">
                            <%--B12--%>
                            <div class="a-tick" style="margin-left: 645px;"><c:if
                                    test="${map.insurance_matterList[6].insuredResult=='true'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 685px;"><c:if
                                    test="${map.insurance_matterList[6].insuredResult=='false'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 730px;"><c:if
                                    test="${map.insurance_matterList[6].policyholderResult=='true'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 765px;"><c:if
                                    test="${map.insurance_matterList[6].policyholderResult=='false'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 805px;width:180px;font-size: 13px;">
                                ${map.insurance_matterList[6].insuredRemark}&nbsp;${map.insurance_matterList[6].policyholderRemark}
                            </div>
                        </div>
                        <div style="position:relative;height: 60px;">
                            <%--B12--%>
                            <div class="a-tick" style="margin-left: 645px;margin-top: 15px;"><c:if
                                    test="${map.insurance_matterList[7].insuredResult=='true'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 685px;margin-top: 15px;"><c:if
                                    test="${map.insurance_matterList[7].insuredResult=='false'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 730px;margin-top: 15px;"><c:if
                                    test="${map.insurance_matterList[7].policyholderResult=='true'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 765px;margin-top: 15px;"><c:if
                                    test="${map.insurance_matterList[7].policyholderResult=='false'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 805px;width:180px;font-size: 13px;">
                                ${map.insurance_matterList[7].insuredRemark}&nbsp;${map.insurance_matterList[7].policyholderRemark}
                            </div>
                        </div>
                        <div style="position:relative;height: 160px;">
                            <%--B12--%>
                            <div class="a-tick" style="margin-left: 645px;margin-top: 65px;"><c:if
                                    test="${map.insurance_matterList[8].insuredResult=='true'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 685px;margin-top: 65px;"><c:if
                                    test="${map.insurance_matterList[8].insuredResult=='false'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 730px;margin-top: 65px;"><c:if
                                    test="${map.insurance_matterList[8].policyholderResult=='true'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 765px;margin-top: 65px;"><c:if
                                    test="${map.insurance_matterList[8].policyholderResult=='false'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 805px;width:180px;font-size: 13px;">
                                ${map.insurance_matterList[8].insuredRemark}&nbsp;${map.insurance_matterList[8].policyholderRemark}
                            </div>
                        </div>
                        <div style="position:relative;height: 53px;">
                            <%--B12--%>
                            <div class="a-tick" style="margin-left: 645px;margin-top: 15px;"><c:if
                                    test="${map.insurance_matterList[9].insuredResult=='true'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 685px;margin-top: 15px;"><c:if
                                    test="${map.insurance_matterList[9].insuredResult=='false'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 730px;margin-top: 15px;"><c:if
                                    test="${map.insurance_matterList[9].policyholderResult=='true'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 765px;margin-top: 15px;"><c:if
                                    test="${map.insurance_matterList[9].policyholderResult=='false'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 805px;width:180px;font-size: 13px;">
                                ${map.insurance_matterList[9].insuredRemark}&nbsp;${map.insurance_matterList[9].policyholderRemark}
                            </div>
                        </div>
                    </div>
                    <%--第二页 可保资料告知 end--%>

                    <%--第三页 可保资料告知 start--%>
                    <div style="height: 820px;">
                        <div style="position:relative;height: 58px;">
                            <%--B12--%>
                            <div style="margin-left: 495px;position: absolute;font-size: 14px;">${map.insurance_matter_value[1].m_value0}</div>
                            <div class="a-tick" style="margin-left: 650px;margin-top: 20px;"><c:if
                                    test="${map.insurance_matterList[10].insuredResult=='true'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 685px;margin-top: 20px;"><c:if
                                    test="${map.insurance_matterList[10].insuredResult=='false'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 733px;margin-top: 20px;"><c:if
                                    test="${map.insurance_matterList[10].policyholderResult=='true'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 770px;margin-top: 20px;"><c:if
                                    test="${map.insurance_matterList[10].policyholderResult=='false'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 810px;width:180px;font-size: 13px;">
                                ${map.insurance_matterList[10].insuredRemark}&nbsp;${map.insurance_matterList[10].policyholderRemark}
                            </div>
                        </div>
                        <div style="position:relative;height: 465px;">
                            <%--B12--%>
                            <div style="position: absolute;margin-top: 25px;">
                                <div class="a-tick" style="margin-left: 650px;"><c:if
                                        test="${map.insurance_matterList[11].insuredResult=='true'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 685px;"><c:if
                                        test="${map.insurance_matterList[11].insuredResult=='false'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 733px;"><c:if
                                        test="${map.insurance_matterList[11].policyholderResult=='true'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 770px;"><c:if
                                        test="${map.insurance_matterList[11].policyholderResult=='false'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 810px;width:180px;font-size: 13px;">
                                    ${map.insurance_matterList[11].insuredRemark}&nbsp;${map.insurance_matterList[11].policyholderRemark}
                                </div>
                            </div>
                            <div style="position: absolute;margin-top: 165px;">
                                <div class="a-tick" style="margin-left: 650px;"><c:if
                                        test="${map.insurance_matterList[12].insuredResult=='true'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 685px;"><c:if
                                        test="${map.insurance_matterList[12].insuredResult=='false'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 733px;"><c:if
                                        test="${map.insurance_matterList[12].policyholderResult=='true'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 770px;"><c:if
                                        test="${map.insurance_matterList[12].policyholderResult=='false'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 810px;width:180px;font-size: 13px;">
                                    ${map.insurance_matterList[12].insuredRemark}&nbsp;${map.insurance_matterList[12].policyholderRemark}
                                </div>
                            </div>
                            <div style="position: absolute;margin-top: 185px;">
                                <div class="a-tick" style="margin-left: 650px;"><c:if
                                        test="${map.insurance_matterList[13].insuredResult=='true'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 685px;"><c:if
                                        test="${map.insurance_matterList[13].insuredResult=='false'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 733px;"><c:if
                                        test="${map.insurance_matterList[13].policyholderResult=='true'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 770px;"><c:if
                                        test="${map.insurance_matterList[13].policyholderResult=='false'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 810px;width:180px;font-size: 13px;">
                                    ${map.insurance_matterList[13].insuredRemark}&nbsp;${map.insurance_matterList[13].policyholderRemark}
                                </div>
                            </div>
                            <div style="position: absolute;margin-top: 220px;">
                                <div class="a-tick" style="margin-left: 650px;"><c:if
                                        test="${map.insurance_matterList[14].insuredResult=='true'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 685px;"><c:if
                                        test="${map.insurance_matterList[14].insuredResult=='false'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 733px;"><c:if
                                        test="${map.insurance_matterList[14].policyholderResult=='true'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 770px;"><c:if
                                        test="${map.insurance_matterList[14].policyholderResult=='false'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 810px;width:180px;font-size: 13px;">
                                    ${map.insurance_matterList[14].insuredRemark}&nbsp;${map.insurance_matterList[14].policyholderRemark}
                                </div>
                            </div>
                            <div style="position: absolute;margin-top: 238px;">
                                <div class="a-tick" style="margin-left: 650px;"><c:if
                                        test="${map.insurance_matterList[15].insuredResult=='true'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 685px;"><c:if
                                        test="${map.insurance_matterList[15].insuredResult=='false'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 733px;"><c:if
                                        test="${map.insurance_matterList[15].policyholderResult=='true'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 770px;"><c:if
                                        test="${map.insurance_matterList[15].policyholderResult=='false'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 810px;width:180px;font-size: 13px;">
                                    ${map.insurance_matterList[15].insuredRemark}&nbsp;${map.insurance_matterList[15].policyholderRemark}
                                </div>
                            </div>
                            <div style="position: absolute;margin-top: 275px;">
                                <div class="a-tick" style="margin-left: 650px;"><c:if
                                        test="${map.insurance_matterList[16].insuredResult=='true'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 685px;"><c:if
                                        test="${map.insurance_matterList[16].insuredResult=='false'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 733px;"><c:if
                                        test="${map.insurance_matterList[16].policyholderResult=='true'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 770px;"><c:if
                                        test="${map.insurance_matterList[16].policyholderResult=='false'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 810px;width:180px;font-size: 13px;">
                                    ${map.insurance_matterList[16].insuredRemark}&nbsp;${map.insurance_matterList[16].policyholderRemark}
                                </div>
                            </div>
                            <div style="position: absolute;margin-top: 305px;">
                                <div class="a-tick" style="margin-left: 650px;"><c:if
                                        test="${map.insurance_matterList[17].insuredResult=='true'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 685px;"><c:if
                                        test="${map.insurance_matterList[17].insuredResult=='false'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 733px;"><c:if
                                        test="${map.insurance_matterList[17].policyholderResult=='true'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 770px;"><c:if
                                        test="${map.insurance_matterList[17].policyholderResult=='false'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 810px;width:180px;font-size: 13px;">
                                    ${map.insurance_matterList[17].insuredRemark}&nbsp;${map.insurance_matterList[17].policyholderRemark}
                                </div>
                            </div>
                            <div style="position: absolute;margin-top: 325px;">
                                <div class="a-tick" style="margin-left: 650px;"><c:if
                                        test="${map.insurance_matterList[18].insuredResult=='true'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 685px;"><c:if
                                        test="${map.insurance_matterList[18].insuredResult=='false'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 733px;"><c:if
                                        test="${map.insurance_matterList[18].policyholderResult=='true'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 770px;"><c:if
                                        test="${map.insurance_matterList[18].policyholderResult=='false'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 810px;width:180px;font-size: 13px;">
                                    ${map.insurance_matterList[18].insuredRemark}&nbsp;${map.insurance_matterList[18].policyholderRemark}
                                </div>
                            </div>
                            <div style="position: absolute;margin-top: 345px;">
                                <div class="a-tick" style="margin-left: 650px;"><c:if
                                        test="${map.insurance_matterList[19].insuredResult=='true'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 685px;"><c:if
                                        test="${map.insurance_matterList[19].insuredResult=='false'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 733px;"><c:if
                                        test="${map.insurance_matterList[19].policyholderResult=='true'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 770px;"><c:if
                                        test="${map.insurance_matterList[19].policyholderResult=='false'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 810px;width:180px;font-size: 13px;">
                                    ${map.insurance_matterList[19].insuredRemark}&nbsp;${map.insurance_matterList[19].policyholderRemark}
                                </div>
                            </div>
                            <div style="position: absolute;margin-top: 380px;">
                                <div class="a-tick" style="margin-left: 650px;"><c:if
                                        test="${map.insurance_matterList[20].insuredResult=='true'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 685px;"><c:if
                                        test="${map.insurance_matterList[20].insuredResult=='false'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 733px;"><c:if
                                        test="${map.insurance_matterList[20].policyholderResult=='true'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 770px;"><c:if
                                        test="${map.insurance_matterList[20].policyholderResult=='false'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 810px;width:180px;font-size: 13px;">
                                    ${map.insurance_matterList[20].insuredRemark}&nbsp;${map.insurance_matterList[20].policyholderRemark}
                                </div>
                            </div>
                            <div style="position: absolute;margin-top: 400px;">
                                <div class="a-tick" style="margin-left: 650px;"><c:if
                                        test="${map.insurance_matterList[21].insuredResult=='true'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 685px;"><c:if
                                        test="${map.insurance_matterList[21].insuredResult=='false'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 733px;"><c:if
                                        test="${map.insurance_matterList[21].policyholderResult=='true'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 770px;"><c:if
                                        test="${map.insurance_matterList[21].policyholderResult=='false'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 810px;width:180px;font-size: 13px;">
                                    ${map.insurance_matterList[21].insuredRemark}&nbsp;${map.insurance_matterList[21].policyholderRemark}
                                </div>
                            </div>
                            <div style="position: absolute;margin-top: 415px;">
                                <div class="a-tick" style="margin-left: 650px;"><c:if
                                        test="${map.insurance_matterList[22].insuredResult=='true'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 685px;"><c:if
                                        test="${map.insurance_matterList[22].insuredResult=='false'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 733px;"><c:if
                                        test="${map.insurance_matterList[22].policyholderResult=='true'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 770px;"><c:if
                                        test="${map.insurance_matterList[22].policyholderResult=='false'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 810px;width:180px;font-size: 13px;">
                                    ${map.insurance_matterList[22].insuredRemark}&nbsp;${map.insurance_matterList[22].policyholderRemark}
                                </div>
                            </div>
                            <div style="position: absolute;margin-top: 435px;">
                                <div class="a-tick" style="margin-left: 650px;"><c:if
                                        test="${map.insurance_matterList[23].insuredResult=='true'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 685px;"><c:if
                                        test="${map.insurance_matterList[23].insuredResult=='false'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 733px;"><c:if
                                        test="${map.insurance_matterList[23].policyholderResult=='true'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 770px;"><c:if
                                        test="${map.insurance_matterList[23].policyholderResult=='false'}">√</c:if></div>
                                <div class="a-tick" style="margin-left: 810px;width:180px;font-size: 13px;">
                                    ${map.insurance_matterList[23].insuredRemark}&nbsp;${map.insurance_matterList[23].policyholderRemark}
                                </div>
                            </div>
                        </div>
                        <div style="position:relative;height: 40px;">
                            <%--B12--%>
                            <div class="a-tick" style="margin-left: 650px;margin-top: 5px;"><c:if
                                    test="${map.insurance_matterList[24].insuredResult=='true'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 685px;margin-top: 5px;"><c:if
                                    test="${map.insurance_matterList[24].insuredResult=='false'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 733px;margin-top: 5px;"><c:if
                                    test="${map.insurance_matterList[24].policyholderResult=='true'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 770px;margin-top: 5px;"><c:if
                                    test="${map.insurance_matterList[24].policyholderResult=='false'}">√</c:if></div>
                            <div class="a-tick"
                                 style="margin-left: 810px;width:180px;font-size: 13px;margin-top: 5px;">
                                ${map.insurance_matterList[24].insuredRemark}&nbsp;${map.insurance_matterList[24].policyholderRemark}
                            </div>
                        </div>
                        <div style="position:relative;height: 197px;">
                            <%--B12--%>
                            <div style="margin-left: 189px;margin-top: 24px;position: absolute;font-size: 14px;">${map.insurance_matter_value[2].m_value0}</div>
                            <div style="margin-left: 325px;margin-top: 24px;position: absolute;font-size: 14px;">${map.insurance_matter_value[2].m_value1}</div>
                            <div style="margin-left: 500px;margin-top: 24px;position: absolute;font-size: 14px;">${map.insurance_matter_value[2].m_value2}</div>
                            <div style="margin-left: 100px;margin-top: 41px;position: absolute;font-size: 14px;">${map.insurance_matter_value[2].m_value3}</div>
                            <div class="a-tick" style="margin-left: 650px;margin-top: 85px;"><c:if
                                    test="${map.insurance_matterList[25].insuredResult=='true'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 685px;margin-top: 85px;"><c:if
                                    test="${map.insurance_matterList[25].insuredResult=='false'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 733px;margin-top: 85px;"><c:if
                                    test="${map.insurance_matterList[25].policyholderResult=='true'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 770px;margin-top: 85px;"><c:if
                                    test="${map.insurance_matterList[25].policyholderResult=='false'}">√</c:if></div>
                            <div class="a-tick"
                                 style="margin-left: 810px;width:180px;font-size: 13px;margin-top: 50px;">
                                ${map.insurance_matterList[25].insuredRemark}&nbsp;${map.insurance_matterList[25].policyholderRemark}
                            </div>
                        </div>
                        <div style="position:relative;height: 28px;">
                            <%--B12--%>
                            <div class="a-tick" style="margin-left: 650px;"><c:if
                                    test="${map.insurance_matterList[26].insuredResult=='true'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 685px;"><c:if
                                    test="${map.insurance_matterList[26].insuredResult=='false'}">√</c:if></div>
                            <%--<div class="a-tick" style="margin-left: 733px;"><c:if--%>
                            <%--test="${map.insurance_matterList[26].policyholderResult=='true'}">√</c:if></div>--%>
                            <%--<div class="a-tick" style="margin-left: 770px;"><c:if--%>
                            <%--test="${map.insurance_matterList[26].policyholderResult=='false'}">√</c:if></div>--%>
                            <div class="a-tick" style="margin-left: 810px;width:180px;font-size: 13px;">
                                ${map.insurance_matterList[26].insuredRemark}&nbsp;${map.insurance_matterList[26].policyholderRemark}
                            </div>
                        </div>
                        <div style="position:relative;height: 28px;">
                            <%--B12--%>
                            <div class="a-tick" style="margin-left: 650px;"><c:if
                                    test="${map.insurance_matterList[27].insuredResult=='true'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 685px;"><c:if
                                    test="${map.insurance_matterList[27].insuredResult=='false'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 733px;"><c:if
                                    test="${map.insurance_matterList[27].policyholderResult=='true'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 770px;"><c:if
                                    test="${map.insurance_matterList[27].policyholderResult=='false'}">√</c:if></div>
                            <div class="a-tick" style="margin-left: 810px;width:180px;font-size: 13px;">
                                ${map.insurance_matterList[27].insuredRemark}&nbsp;${map.insurance_matterList[27 ].policyholderRemark}
                            </div>
                        </div>
                    </div>
                    <%--第三页 可保资料告知 end--%>

                    <div style="z-index: 20;padding-left: 19px;">
                        <!-- Start .第一行-->
                        <div class="al-row-2" style="margin-top: 413px;margin-left: 10px;">
                            &nbsp;
                        </div>
                        <!-- End .第一行-->
                        <!-- Start .第二行-->
                        <div class="al-row-2" style="margin-top: 0;margin-left: 10px;">
                            &nbsp;
                        </div>
                        <!-- End .第二行-->
                        <!-- Start .第三行-->
                        <div class="al-row-2"
                             style="margin-top: 40px;margin-left: 80px;font-size: 13px;font-weight: bold;height: 20px;">
                            <div name="accountBank" style="margin-left: 12px;position: absolute;">
                                ${map.insuranceOrder.insuranceOrderPolicyholderBank.accountBank}</div>
                            <div style="margin-left: 145px;position: absolute;">&nbsp;</div>
                            <div style="margin-left: 275px;position: absolute;">&nbsp;</div>
                            <div style="margin-left: 520px;position: absolute;">&nbsp;</div>
                            <div style="margin-left: 655px;position: absolute;">&nbsp;</div>
                        </div>

                        <!-- End .第三行-->
                        <!-- Start .第四行-->
                        <div class="al-row-2" style="height: 20px;margin-top: 5px;">
                            <div style="margin-left: 150px;float: left;">
                                ${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 0, 1)}
                            </div>
                            <div style="margin-left: 10px;float: left;">
                                ${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 1, 2)}
                            </div>
                            <div style="margin-left: 10px;float: left;">
                                ${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 2, 3)}
                            </div>
                            <div style="margin-left: 10px;float: left;">
                                ${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 3, 4)}
                            </div>

                            <div style="margin-left: 32px;float: left;">
                                ${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 4, 5)}
                            </div>
                            <div style="margin-left: 10px;float: left;">
                                ${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 5, 6)}
                            </div>
                            <div style="margin-left: 10px;float: left;">
                                ${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 6, 7)}
                            </div>
                            <div style="margin-left: 10px;float: left;">
                                ${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 7, 8)}
                            </div>

                            <div style="margin-left: 34px;float: left;">
                                ${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 8, 9)}
                            </div>
                            <div style="margin-left: 10px;float: left;">
                                ${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 9, 10)}
                            </div>
                            <div style="margin-left: 10px;float: left;">
                                ${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 10, 11)}
                            </div>
                            <div style="margin-left: 10px;float: left;">
                                ${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 11, 12)}
                            </div>

                            <div style="margin-left: 36px;float: left;">
                                ${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 12, 13)}
                            </div>
                            <div style="margin-left: 10px;float: left;">
                                ${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 13, 14)}
                            </div>
                            <div style="margin-left: 10px;float: left;">
                                ${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 14, 15)}
                            </div>
                            <div style="margin-left: 10px;float: left;">
                                ${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 15, 16)}
                            </div>

                            <div style="margin-left: 37px;float: left;">
                                ${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 17, 18)}
                            </div>
                            <div style="margin-left: 10px;float: left;">
                                ${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 18, 19)}
                            </div>
                            <div style="margin-left: 10px;float: left;">
                                ${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 19, 20)}
                            </div>
                            <div style="margin-left: 10px;float: left;">
                                ${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 20, 21)}
                            </div>

                            <div style="margin-left: 35px;float: left;">
                                ${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 21, 22)}
                            </div>
                            <div style="margin-left: 10px;float: left;">
                                ${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 22, 23)}
                            </div>
                            <div style="margin-left: 10px;float: left;">
                                ${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 23, 24)}
                            </div>
                            <div style="margin-left: 10px;float: left;">
                                ${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 24, 25)}
                            </div>
                        </div>
                        <!-- End .第四行-->
                        <!-- Start .第五行-->
                        <div class="al-row-2" style="margin-top: 20px;height: 30px;">
                            <div name="policyholderSign" style="margin-left: 20px;position: absolute;">
                                <img style="width: 100px;height: 20px;"
                                     src="${map.insuranceOrder.insuranceOrderPolicyholder.policyholderSign}" alt=""/>
                            </div>
                            <div style="margin-left: 450px;position: absolute;">&nbsp;</div>
                            <div style="margin-left: 690px;position: absolute;">&nbsp;</div>
                            <div style="margin-left: 750px;position: absolute;">&nbsp;</div>
                            <div style="margin-left: 840px;position: absolute;">&nbsp;</div>
                        </div>
                        <!-- End .第五行-->
                        <!-- Start .第六行-->
                        <div class="al-row-2" style="margin-top: 5px;height:40px;">
                            <div style="margin-left: 50px;position: absolute;">&nbsp;</div>
                            <div style="margin-left: 320px;position: absolute;">&nbsp;</div>
                            <div style="margin-left: 600px;position: absolute;">&nbsp;</div>
                        </div>
                        <!-- End .第六行-->
                    </div>
                    <%--第三页 end--%>

                    <%--第四页 start--%>
                    <div style="position: absolute;z-index: 20;padding-left: 0px;margin-top: 30px;height: 650px;">
                        <!-- Start .第一行-->
                        <div class="al-tab" style="height: 160px;">
                            <div class="al-line">
                                <div class="al-font">
                                    <div class="al-row-2"
                                         style="margin-top: 145px;margin-left: 150px;">${map.insuranceOrder.insuranceOrderInsured.insuredName}
                                    </div>
                                    <div style="margin-left: 300px;">
                                        <div class="al-row-sex">${map.insuranceOrder.insuranceOrderPolicyholder.policyholderName}</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- End .第一行-->
                        <!-- Start .第二行-->
                        <div class="al-tab">
                            <div class="al-line">
                                <div class="al-font">
                                    <div class="al-row-2" style="margin-left: 68px;">
                                        <div style="margin-left: 105px;position: absolute;">
                                            &nbsp;
                                        </div>
                                        <div style="margin-left: 162px;position: absolute;">
                                            &nbsp;
                                        </div>
                                        <div style="margin-left: 220px;position: absolute;">
                                            &nbsp;
                                        </div>
                                        <div style="margin-left: 278px;position: absolute;">
                                            &nbsp;
                                        </div>
                                    </div>
                                    <div style="margin-left: 350px;position:absolute;">
                                        <div class="al-row-sex">&nbsp;</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- End .第二行-->
                        <!-- Start .第三行-->
                        <div class="al-tab">
                            <div class="al-line">
                                <div class="al-font">
                                    <div class="al-row-2" style="margin-left: 15px;">
                                        <div style="margin-top: -5px;margin-left: 105px;position: absolute;">&nbsp;
                                        </div>
                                        <div style="margin-top: -5px;margin-left: 190px;position: absolute;">&nbsp;
                                        </div>
                                        <div style="margin-top: -5px;margin-left: 275px;position: absolute;">&nbsp;
                                        </div>
                                        <div style="margin-top: -5px;margin-left: 400px;position: absolute;">&nbsp;
                                        </div>
                                    </div>
                                    <div style="margin-left: 400px;position: absolute;margin-top: -10px;">
                                        <div class="al-row-sex">&nbsp;</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- End .第三行-->
                        <!-- Start .第四行-->
                        <div class="al-tab">
                            <div class="al-line">
                                <div class="al-font">
                                    <div class="al-row-2" style="margin-left: 433px;">
                                        <div style="margin-left: 110px;position: absolute;margin-top: -15px;">&nbsp;
                                        </div>
                                        <div style="margin-left: 152px;position: absolute;margin-top: -15px;">&nbsp;
                                        </div>
                                    </div>
                                    <div class="al-font" style="margin-left: 60px;position: absolute;margin-top:8px;">
                                        <div class="al-row-sex">&nbsp;</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- End .第四行-->
                        <!-- Start .第六行-->
                        <div class="al-tab">
                            <div class="al-line">
                                <div class="al-font">
                                    <div class="al-row-2" style="margin-left: 295px;">
                                        <div style="margin-left: 105px;position: absolute;">&nbsp;</div>
                                        <div style="margin-left: 145px;position: absolute;">&nbsp;</div>
                                    </div>
                                    <div class="al-font" style="margin-left: 510px;position: absolute;">
                                        <div class="al-row-sex">&nbsp;</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- End .第六行-->
                        <!-- Start .第七行-->
                        <div class="al-tab" style="height: 20px;">
                            <div class="al-line">
                                <div class="al-font">
                                    <div class="al-row-2" style="margin-left: 237px;">
                                        <div style="margin-left: 105px;position: absolute;margin-top: -3px;">&nbsp;
                                        </div>
                                        <div style="margin-left: 145px;position: absolute;margin-top: -3px;">&nbsp;
                                        </div>
                                    </div>
                                    <div class="al-font"
                                         style="margin-left: 450px;position: absolute;margin-top: -6px;">
                                        <div class="al-row-sex">&nbsp;</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- End .第七行-->
                        <!-- Start .第八行-->
                        <div class="al-tab">
                            <div class="al-line">
                                <div class="al-font">
                                    <div class="al-row-2" style="margin-left: 295px;">
                                        <div style="margin-left: 105px;position: absolute;">&nbsp;</div>
                                        <div style="margin-left: 143px;position: absolute;">&nbsp;</div>
                                    </div>
                                    <div class="al-font"
                                         style="margin-left: 510px;position: absolute;margin-top: -3px;">
                                        <div class="al-row-sex">&nbsp;</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- End .第八行-->
                        <!-- Start .第九行-->
                        <div class="al-tab">
                            <div class="al-line">
                                <div class="al-font">
                                    <div class="al-row-2" style="margin-left: 100px;">
                                        <div style="margin-left: 95px;position: absolute;margin-top: -8px;">&nbsp;</div>
                                        <div style="margin-left: 138px;position: absolute;margin-top: -8px;">&nbsp;
                                        </div>
                                    </div>
                                    <div class="al-font"
                                         style="margin-left: 310px;position: absolute;margin-top: -11px;">
                                        <div class="al-row-sex">&nbsp;</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- End .第九行-->
                        <!-- Start .第十行-->
                        <div class="al-tab" style="height: 10px;">
                            <div class="al-line">
                                <div class="al-font">
                                    <div class="al-row-2" style="margin-left: 375px;margin-top: 0;">
                                        <div class="al-row-sex">&nbsp;</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- End .第十行-->
                        <!-- Start .第十一行-->
                        <div class="al-tab">
                            <div class="al-line">
                                <div class="al-font">
                                    <div class="al-row-2" style="margin-left: 50px;margin-top: 20px">
                                        <div style="margin-left: 105px;position: absolute;">${insuranceOrder.insuranceOrderInsured.insuredIncome}</div>
                                        <div style="margin-left: 350px;position: absolute;">${insuranceOrder.insuranceOrderPolicyholder.policyholderIncome}</div>
                                        <div style="margin-left: 600px;position: absolute;">&nbsp;</div>
                                        <div style="margin-left: 830px;position: absolute;">&nbsp;</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- End .第十一行-->
                        <!-- Start .第十二行-->
                        <div class="al-tab" style="margin-top: 26px;height: 200px;">
                            <div class="al-line">
                                <div class="al-font">
                                    <div class="al-row-2" style="margin-left: -20px;">
                                        <div class="al-row-sex">&nbsp;</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- End .第十二行-->
                        <!-- Start .第十三行-->
                        <div class="al-tab" style="margin-top: 160px;height: 120px;">
                            <div class="al-line">
                                <div class="al-font">
                                    <div class="al-row-2">
                                        &nbsp;
                                        <div style="margin-left: 55px;position: absolute;">&nbsp;</div>
                                        <div style="margin-left: 330px;position: absolute;">&nbsp;</div>
                                        <div style="margin-left: 570px;position: absolute;">&nbsp;</div>
                                        <div style="margin-left: 670px;position: absolute;">&nbsp;</div>
                                        <div style="margin-left: 730px;position: absolute;">&nbsp;</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- End .第十三行-->
                        <!-- Start .第十四行-->
                        <div class="al-tab" style="margin-top: 125px;">
                            <div class="al-line" style="height: 35px;">
                                <div class="al-font">
                                    <div class="al-row-2" style="margin-left: 10px">
                                        <div style="position: absolute;">&nbsp;</div>
                                        <div style="margin-left: 270px;position: absolute;">&nbsp;</div>
                                        <div style="margin-left: 330px;position: absolute;">&nbsp;</div>
                                        <div style="margin-left: 550px;position: absolute;">&nbsp;</div>
                                        <div style="margin-left: 750px;position: absolute;">&nbsp;</div>
                                    </div>
                                </div>
                            </div>
                            <div class="al-line" style="height: 35px;">
                                <div class="al-font">
                                    <div class="al-row-2" style="margin-left: 10px">
                                        <div style="position: absolute;">&nbsp;</div>
                                        <div style="margin-left: 270px;position: absolute;">&nbsp;</div>
                                        <div style="margin-left: 330px;position: absolute;">&nbsp;</div>
                                        <div style="margin-left: 550px;position: absolute;">&nbsp;</div>
                                        <div style="margin-left: 750px;position: absolute;">&nbsp;</div>
                                    </div>
                                </div>
                            </div>
                            <div class="al-line" style="height: 35px;">
                                <div class="al-font">
                                    <div class="al-row-2" style="margin-left: 10px">
                                        <div style="position: absolute;">&nbsp;</div>
                                        <div style="margin-left: 270px;position: absolute;">&nbsp;</div>
                                        <div style="margin-left: 330px;position: absolute;">&nbsp;</div>
                                        <div style="margin-left: 550px;position: absolute;">&nbsp;</div>
                                        <div style="margin-left: 750px;position: absolute;">&nbsp;</div>
                                    </div>
                                </div>
                            </div>
                            <div class="al-line" style="height: 35px;">
                                <div class="al-font">
                                    <div class="al-row-2" style="margin-left: 10px">
                                        <div style="position: absolute;">&nbsp;</div>
                                        <div style="margin-left: 270px;position: absolute;">&nbsp;</div>
                                        <div style="margin-left: 330px;position: absolute;">&nbsp;</div>
                                        <div style="margin-left: 550px;position: absolute;">&nbsp;</div>
                                        <div style="margin-left: 750px;position: absolute;">&nbsp;</div>
                                    </div>
                                </div>
                            </div>
                            <div class="al-line" style="height: 35px;">
                                <div class="al-font">
                                    <div class="al-row-2" style="margin-left: 10px">
                                        <div style="position: absolute;">&nbsp;</div>
                                        <div style="margin-left: 270px;position: absolute;">&nbsp;</div>
                                        <div style="margin-left: 330px;position: absolute;">&nbsp;</div>
                                        <div style="margin-left: 550px;position: absolute;">&nbsp;</div>
                                        <div style="margin-left: 750px;position: absolute;">&nbsp;</div>
                                    </div>
                                </div>
                            </div>
                            <div class="al-line" style="height: 35px;">
                                <div class="al-font">
                                    <div class="al-row-2" style="margin-left: 10px">
                                        <div style="margin-left: 550px;position: absolute;">&nbsp;</div>
                                        <div style="margin-left: 750px;position: absolute;">&nbsp;</div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                    <%--第四页 end--%>
                </div>
                <div class="hidden-prints" style="height:3751px;">
                    <div class="header">
                        <img src="../images/orderHeader/newlogo.png" width="729px" height="92px"/>
                        <%--<h1 style="font-size: 40px">富德生命人寿保险股份有限公司</h1>--%>
                        <%--<h2>FUNDE SINO LIFE INSUANCE CO.,LTD.</h2>--%>
                        <h2>个人保险投保单（经代渠道）</h2>
                        <div class="header-contract">
                            <div class="contract" style="width: 450px;"></div>
                            <div class="contract">
                                <p>（本单所示金额单位：人民币元）</p>
                                <div class="contract-num">
                                    <div style="width: 100px;vertical-align: text-bottom;margin-left: 5px;border: 0;">
                                        保险合同号码
                                    </div>
                                    <div></div>
                                    <div></div>
                                    <div></div>
                                    <div></div>
                                    <div></div>
                                    <div></div>
                                    <div></div>
                                    <div></div>
                                    <div></div>
                                    <div></div>
                                    <div></div>
                                    <div></div>
                                    <div></div>
                                    <div></div>
                                    <div></div>
                                    <div style="padding-right: 4px;"></div>
                                </div>
                                <p>本次同时投保共<input/>单，第<input/>单</p>
                            </div>
                            <div class="info">
                                <div style="display: inline-block;vertical-align: top;">
                                    投保须知:
                                </div>
                                <div style="display: inline-block">
                                    1、请您在仔细阅读人身保险投保提示书、产品说明书、保险条款后用蓝、黑墨水笔填写本投保单，并在合适的回答方框内打√。
                                    <br/>2、您必须在此投保单上真实填写一切有关事实，并亲笔签名。保险合同将以此为依据，否则可能影响所签合同的法律效力。
                                    <br/>3、富德生命人寿保险股份有限公司承诺未经客户同意，不会将客户信息用于人身保险公司和第三方机构的销售活动。
                                </div>
                                <div style="float: right;color: green;margin-top: 35px;">绿色栏由业务员填写。
                                </div>
                            </div>
                            <div style="margin-left: 5px;font-weight: bold;font-size: 18px;">A.基本资料</div>
                            <div class="div-title">被保险人资料</div>
                        </div>
                    </div>
                    <div class="div-tab">
                        <div class="div-line">
                            <div class="div-line-con div-row-2">
                                <div class="title">A 1.姓名</div>

                            </div>
                            <div class="div-line-con div-sex">
                                <div class="title">A 2.性别</div>
                                <div>
                                    <input class="i-cb" type="checkbox" style="margin-left: 15px;"/>
                                    男
                                    <input class="i-cb" style="margin-left: 35px; " type="checkbox"/>
                                    女
                                </div>
                            </div>
                            <div class="div-line-con div-row-4">
                                <div class="title">A 3.出生日期</div>
                                <div style="padding-left: 40px;margin-left: 30px;">
                                    <input class="b-input" type="text"/>
                                    年
                                    <input class="b-input" type="text"/>
                                    月
                                    <input class="b-input" type="text"/>
                                    日
                                </div>
                            </div>
                            <div class="div-line-con div-row-2">
                                <div class="title">A 4.年龄</div>
                                <input class="c-input" type="text"/>
                                <div>周岁</div>
                            </div>
                        </div>

                        <div class="div-line">
                            <div class="div-line-con div-row-5">
                                <div class="title">A 5.证件类型</div>
                                <div>
                                    <input class="i-cb" type="checkbox"/>
                                    身份证
                                    <input class="i-cb" type="checkbox" style="margin-left:15px; "/>
                                    护照
                                    <input class="i-cb" type="checkbox" style="margin-left:15px; "/>
                                    军官证
                                    <input class="i-cb" type="checkbox" style="margin-left:15px; "/>
                                    其他
                                </div>
                            </div>
                            <div class="div-line-con">
                                <div class="title">A 6.证件号码</div>
                            </div>
                            <div style="display: inline-block;float: right;margin-top: 1px;margin-right: 1px;height: 20px">
                                <div class="div-line-cert"></div>
                                <div class="div-line-cert"></div>
                                <div class="div-line-cert"></div>
                                <div class="div-line-cert"></div>
                                <div class="div-line-cert"></div>
                                <div class="div-line-cert"></div>
                                <div class="div-line-cert"></div>
                                <div class="div-line-cert"></div>
                                <div class="div-line-cert"></div>
                                <div class="div-line-cert"></div>
                                <div class="div-line-cert"></div>
                                <div class="div-line-cert"></div>
                                <div class="div-line-cert"></div>
                                <div class="div-line-cert"></div>
                                <div class="div-line-cert"></div>
                                <div class="div-line-cert"></div>
                                <div class="div-line-cert"></div>
                                <div class="div-line-cert"></div>
                            </div>
                        </div>

                        <div class="div-line">
                            <div class="div-line-con div-row-5">
                                <div class="title">A 7.证件有效期</div>
                                <div>
                                    <input class="i-cb" type="checkbox" style="margin-left: 10px;"/>
                                    有效期至
                                    <div>
                                        <input class="c-input" type="text" style="margin-left: 10px;"/>
                                        年
                                        <input class="c-input" type="text"/>
                                        月
                                        <input class="c-input" type="text"/>
                                        日
                                    </div>
                                    <input class="i-cb" style="margin-left: 15px" type="checkbox"/>
                                    长期
                                </div>
                            </div>
                            <div class="div-line-con div-row-3">
                                <div class="title">A 8.国籍、户籍</div>
                                <input class="a-input" type="text"/>
                            </div>
                            <div class="div-line-con div-row-3">
                                <div class="title">A 9.身高</div>
                                <input class="b-input" type="text" style="margin-left: 10px;"/>
                                厘米，
                                <div class="title">体重</div>
                                <input class="b-input" type="text" style="margin-left: 10px;"/>
                                公斤
                            </div>
                        </div>
                        <div class="div-line">
                            <div class="div-line-con div-row-5">
                                <div class="title">A 10.婚姻状况</div>
                                <div>
                                    <input class="i-cb" type="checkbox" style="margin-left: 14px;"/>
                                    未婚
                                    <input class="i-cb" type="checkbox" style="margin-left: 20px;"/>
                                    已婚
                                    <input class="i-cb" type="checkbox" style="margin-left: 20px;"/>
                                    离异
                                    <input class="i-cb" type="checkbox" style="margin-left: 25px;"/>
                                    丧偶
                                </div>
                            </div>
                            <div class="div-line-con div-row-3">
                                <div class="title">A 11.固定电话（<sub style="font-size: 1px">&nbsp;<input class="b-input"
                                                                                                      type="text"/></sub>)-(<input
                                        class="mobile-input" type="text"/></span>)
                                </div>
                            </div>
                            <div class="div-line-con div-row-3">
                                <div class="title">A 12.移动电话</div>
                                <input class="a-input" type="text"/>
                            </div>
                        </div>

                        <div class="div-line">
                            <div class="div-line-con div-row-7">
                                <div class="title">A 13.住址</div>
                                <div>
                                    <input class="b-input" type="text" style="margin-left: 10px;"/>
                                    省
                                    <input class="b-input" type="text" style="margin-left: 10px;"/>
                                    市
                                    <input class="b-input" type="text" style="margin-left: 25px;"/>
                                    区/县
                                    <input class="d-input" type="text"/>
                                </div>
                            </div>
                            <div class="div-line-con div-postcode" style="margin-right: 40px;">
                                邮编
                                <div style="margin-left: 20px;font-size: 0;margin-top: -10px;">
                                    <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                    <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                    <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                    <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                    <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                    <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                </div>
                            </div>
                        </div>
                        <div class="div-line">
                            <div class="div-line-con div-row-5">
                                <div class="title">A 14.单位名称</div>
                                <input class="a-input" type="text"/>
                            </div>
                            <div class="div-line-con div-row-3">
                                <div class="title">A 15.职业</div>
                                <input class="a-input" type="text"/>
                            </div>
                            <div class="div-line-con div-row-3">
                                <div class="title">A 16.年均收入</div>
                                <input class="a-input" type="text"/>
                            </div>
                        </div>
                        <div class="div-line">
                            <div class="div-line-con div-row-7">
                                <div class="title">A 17.单位地址</div>
                                <div>
                                    <input class="b-input" type="text"/>
                                    省
                                    <input class="b-input" type="text" style="margin-left: 10px;"/>
                                    市
                                    <input class="b-input" type="text" style="margin-left: 25px;"/>
                                    区/县
                                    <input class="d-input" type="text"/>
                                </div>
                            </div>
                            <div class="div-line-con div-postcode" style="margin-right: 40px;">
                                邮编
                                <div style="margin-left: 20px;font-size: 0;margin-top: -10px;">
                                    <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                    <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                    <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                    <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                    <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                    <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                </div>
                            </div>
                        </div>
                        <div class="div-line">
                            <div class="div-line-con div-career">
                                <div class="title">A 18.职业代码</div>
                                <input class="b-input" style="border-bottom: #000000 solid 1px" type="text"/>
                                <div class="title" style="margin-left: 25px;">职业类别</div>
                            </div>
                            <div class="div-line-con div-email">
                                <div class="title">A 19.&nbsp;E-mail</div>
                                <input class="a-input" type="text"/>
                            </div>
                            <div class="div-line-con div-part-job">
                                <div class="title">A 20.是否有兼职？</div>
                                <input class="i-cb" style="margin-left: 15px" type="checkbox"/>
                                是
                                <input class="i-cb" style="margin-left: 15px" type="checkbox"/>
                                否，如是请说明：
                            </div>
                        </div>
                    </div>
                    <div class="div-title">
                        投保人资料（如投保人为被保险人本人，可免填本栏）
                    </div>
                    <div class="div-tab">
                        <div class="div-line">
                            <div class="div-line-con div-row-2">
                                <div class="title">A 21.姓名</div>

                            </div>
                            <div class="div-line-con div-sex">
                                <div class="title">A 22.性别</div>
                                <div>
                                    <input class="i-cb" type="checkbox" style="margin-left: 10px; "/>
                                    男
                                    <input class="i-cb" style="margin-left: 30px; " type="checkbox"/>
                                    女
                                </div>
                            </div>
                            <div class="div-line-con div-row-4">
                                <div class="title">A 23.出生日期</div>
                                <div style="padding-left: 0; margin-left: 55px;">
                                    <input class="b-input" type="text"/>
                                    年
                                    <input class="b-input" type="text"/>
                                    月
                                    <input class="b-input" type="text"/>
                                    日
                                </div>
                            </div>
                            <div class="div-line-con div-row-2">
                                <div class="title">A 24.是被保险人的(<span style="font-size: 8px;">关系</span>)</div>
                                <input class="c-input" type="text" style=""/>
                                <div></div>
                            </div>
                        </div>

                        <div class="div-line">
                            <div class="div-line-con div-row-5">
                                <div class="title">A 25.证件类型</div>
                                <div>
                                    <input class="i-cb" type="checkbox"/>
                                    身份证
                                    <input class="i-cb" type="checkbox" style="margin-left:15px; "/>
                                    护照
                                    <input class="i-cb" type="checkbox" style="margin-left:15px; "/>
                                    军官证
                                    <input class="i-cb" type="checkbox" style="margin-left:15px; "/>
                                    其他
                                </div>
                            </div>
                            <div class="div-line-con">
                                <div class="title">A 26.证件号码</div>
                            </div>
                            <div style="display: inline-block;float: right;margin-top: 1px;margin-right: 1px;height: 20px">
                                <div class="div-line-cert"></div>
                                <div class="div-line-cert"></div>
                                <div class="div-line-cert"></div>
                                <div class="div-line-cert"></div>
                                <div class="div-line-cert"></div>
                                <div class="div-line-cert"></div>
                                <div class="div-line-cert"></div>
                                <div class="div-line-cert"></div>
                                <div class="div-line-cert"></div>
                                <div class="div-line-cert"></div>
                                <div class="div-line-cert"></div>
                                <div class="div-line-cert"></div>
                                <div class="div-line-cert"></div>
                                <div class="div-line-cert"></div>
                                <div class="div-line-cert"></div>
                                <div class="div-line-cert"></div>
                                <div class="div-line-cert"></div>
                                <div class="div-line-cert"></div>
                            </div>
                        </div>

                        <div class="div-line">
                            <div class="div-line-con div-row-5">
                                <div class="title">A 27.证件有效期</div>
                                <div>
                                    <input class="i-cb" type="checkbox" style="margin-left: 10px;"/>
                                    有效期至
                                    <div style="margin-left: 10px;">
                                        <input class="c-input" type="text"/>
                                        年
                                        <input class="c-input" type="text"/>
                                        月
                                        <input class="c-input" type="text"/>
                                        日
                                    </div>
                                    <input class="i-cb" style="margin-left: 10px" type="checkbox"/>
                                    长期
                                </div>
                            </div>
                            <div class="div-line-con div-row-3">
                                <div class="title">A 28.国籍、户籍</div>
                                <input class="a-input" type="text"/>
                            </div>
                            <div class="div-line-con div-row-3">
                                <div class="title">A 29.身高</div>
                                <input class="b-input" type="text" style="margin-left: 10px;"/>
                                厘米，
                                <div class="title">体重</div>
                                <input class="b-input" type="text" style="margin-left: 10px;"/>
                                公斤
                            </div>
                        </div>
                        <div class="div-line">
                            <div class="div-line-con div-row-5">
                                <div class="title">A 30.婚姻状况</div>
                                <div>
                                    <input class="i-cb" type="checkbox" style="margin-left: 15px;"/>
                                    未婚
                                    <input class="i-cb" type="checkbox" style="margin-left: 23px;"/>
                                    已婚
                                    <input class="i-cb" type="checkbox" style="margin-left: 23px;"/>
                                    离异
                                    <input class="i-cb" type="checkbox" style="margin-left: 23px;"/>
                                    丧偶
                                </div>
                            </div>
                            <div class="div-line-con div-row-3">
                                <div class="title">A 31.固定电话（<sub>&nbsp;<input class="b-input"
                                                                               type="text"/></sub>)-(<input
                                        class="mobile-input" type="text"/></span>)
                                </div>
                            </div>
                            <div class="div-line-con div-row-3">
                                <div class="title">A 32.移动电话</div>
                                <input class="a-input" type="text"/>
                            </div>
                        </div>

                        <div class="div-line">
                            <div class="div-line-con div-row-7">
                                <div class="title">A 33.住址</div>
                                <div>
                                    <input class="b-input" type="text" style="margin-left:40px; "/>
                                    省
                                    <input class="b-input" type="text" style="margin-left: 30px;"/>
                                    市
                                    <input class="b-input" type="text" style="margin-left: 20px;"/>
                                    区/县
                                    <input class="d-input" type="text"/>
                                </div>
                            </div>
                            <div class="div-line-con div-postcode" style="margin-right: 40px;">
                                邮编
                                <div style="margin-left: 20px;font-size: 0;margin-top: -10px;">
                                    <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                    <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                    <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                    <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                    <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                    <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                </div>
                            </div>
                        </div>
                        <div class="div-line">
                            <div class="div-line-con div-row-5">
                                <div class="title">A 34.单位名称</div>
                                <input class="a-input" type="text"/>
                            </div>
                            <div class="div-line-con div-row-3">
                                <div class="title">A 35.职业</div>
                                <input class="a-input" type="text"/>
                            </div>
                            <div class="div-line-con div-row-3">
                                <div class="title">A 36.年均收入</div>
                                <input class="a-input" type="text"/>
                            </div>
                        </div>
                        <div class="div-line">
                            <div class="div-line-con div-row-7">
                                <div class="title">A 37.单位地址</div>
                                <div>
                                    <input class="b-input" type="text"/>
                                    省
                                    <input class="b-input" type="text" style="margin-left: 10px;"/>
                                    市
                                    <input class="b-input" type="text" style="margin-left: 25px;"/>
                                    区/县
                                    <input class="d-input" type="text"/>
                                </div>
                            </div>
                            <div class="div-line-con div-postcode" style="margin-right: 40px;">
                                邮编
                                <div style="margin-left: 20px;font-size: 0;margin-top: -10px;">
                                    <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                    <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                    <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                    <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                    <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                    <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                </div>
                            </div>
                        </div>
                        <div class="div-line">
                            <div class="div-line-con div-career">
                                <div class="title">A 38.职业代码</div>
                                <input class="b-input" style="border-bottom: #000000 solid 1px" type="text"/>
                                <div class="title" style="margin-left: 25px;">职业类别</div>
                            </div>
                            <div class="div-line-con div-email">
                                <div class="title">A 39. E-mail</div>
                                <input class="a-input" type="text"/>
                            </div>
                            <div class="div-line-con div-part-job">
                                <div class="title">A 40.其它联系电话</div>
                                <input class="a-input" type="text"/>
                            </div>
                        </div>
                    </div>
                    <div class="div-title">本单联系地址（如此栏空缺，将以投保人住址为准</div>
                    <div class="div-tab">
                        <div class="div-line">
                            <div class="div-line-con" style="padding: 7px 5px 7px 10px;">
                                A 41. 信函寄往
                                <div style="margin-left: 75px;">省</div>
                                <div style="margin-left: 75px;">市</div>
                                <div style="margin-left: 60px;">区/县</div>
                            </div>
                            <div class="div-line-con div-postcode" style="margin-right: 120px;">
                                电话
                            </div>
                            <div class="div-line-con div-postcode" style="margin-right:  35px;">
                                邮编
                                <div style="margin-left: 20px;font-size: 0;margin-top: -10px;">
                                    <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                    <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                    <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                    <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                    <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                    <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="div-title">短信服务（未选默认为不需要）</div>
                    <div class="div-tab">
                        <div class="div-line">
                            <div class="div-line-con div-row-6" style="padding: 7px 5px 7px 10px;">
                                A 42. 投保人是否需要短信服务
                                <div>
                                    <input class="i-cb" type="checkbox"/>
                                    需要
                                    <input class="i-cb" style="margin-left: 15px; " type="checkbox"/>
                                    不需要
                                </div>
                            </div>
                            <div class="div-line-con">
                                A 43. 移动电话
                            </div>
                        </div>
                    </div>
                    <div class="div-title" style="line-height: 1.5;"><span>身故保险金受益人：</span>（投保养老年金保险产品需要指定剩余养老保险年金受益人时，请填写“投保与合同变更补充声明”告知剩余养老保险年金受益人，如未补充告知，则视剩余养老保险年金受益人与身故保险金受益人为同一人）
                        <br/>说明：1.指定受益人时，同一受益顺序的受益份额合计必须等于100%。2.后一受益顺序的受益人只有在前一受益顺序所有受益人丧失或放弃受益权后才能享有受益权。3.若未填写受益份额，同一顺序的保险金受益人按照相等份额享有保险金。4.若未指定受益人，或者受益人指定不明无法确定的，保险金将作为被保险人的遗产按照《中华人民共和国继承法》的规定进行分配。5.投保无身故保险利益的保险产品时无需填写本栏，填写亦视为无效。
                    </div>
                    <div class="div-tab" style="height: 240px;width: 1000px;">
                        <div class="table-line">
                            <div class="div-table-1">A 44.姓名</div>
                            <div class="div-table-1">A 45.性别</div>
                            <div class="div-table-1">
                                <div style="display: inline-block;vertical-align: top;">A 46.</div>
                                <div style="display: inline-block;">受益<br/>顺序</div>
                            </div>
                            <div class="div-table-1">
                                <div style="display: inline-block;vertical-align: top;">A 47.</div>
                                <div style="display: inline-block;">受益<br/>份额</div>
                            </div>
                            <div class="div-table-1">
                                <div style="display: inline-block;vertical-align: top;">A 48.</div>
                                <div style="display: inline-block;">出生<br/>日期</div>
                            </div>
                            <div class="div-table-1" style="width: 100px;">A 49.是被保险人的<span
                                    style="font-size: 10px;">（关系）</span></div>
                            <div class="div-table-2">A 50.证件类型及号码</div>
                        </div>
                        <div class="table-line">
                            <div class="div-table-1"></div>
                            <div class="div-table-1"></div>
                            <div class="div-table-1"></div>
                            <div class="div-table-1"></div>
                            <div class="div-table-1"></div>
                            <div class="div-table-1" style="width: 100px;"></div>
                            <div class="div-table-2">
                                <div class="t-line" style="padding: 5px 6px;">
                                    <input type="checkbox"/>
                                    <div>身份证</div>
                                    <input type="checkbox" style="margin-left: 15px;"/>
                                    <div>其他</div>
                                    <div style="margin:0 60px;margin-left: 130px;">有效期至</div>
                                    <input class="i-cb" style="margin-left: 55px;" type="checkbox"/>
                                    长期
                                </div>
                                <div class="t-line" style="display: block;height: 31px;">
                                    <div style="display: inline-block;height: 30px;font-size: 0;border-top: #000000 solid 1px;width: 100%;margin:0 -1px;text-align: left">
                                        <div class="t-line-cert" style="border: 0"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="table-line">
                            <div class="div-table-1"></div>
                            <div class="div-table-1"></div>
                            <div class="div-table-1"></div>
                            <div class="div-table-1"></div>
                            <div class="div-table-1"></div>
                            <div class="div-table-1" style="width: 100px;"></div>
                            <div class="div-table-2">
                                <div class="t-line" style="padding: 5px 6px;">
                                    <input type="checkbox"/>
                                    <div>身份证</div>
                                    <input type="checkbox" style="margin-left: 15px;"/>
                                    <div>其他</div>
                                    <div style="margin:0 60px;margin-left: 130px;">有效期至</div>
                                    <input class="i-cb" style="margin-left: 55px;" type="checkbox"/>
                                    长期
                                </div>
                                <div class="t-line" style="display: block;height: 31px;">
                                    <div style="display: inline-block;height: 30px;font-size: 0;border-top: #000000 solid 1px;border-left: 0;width: 100%;margin:0 -1px;text-align: left">
                                        <div class="t-line-cert" style="border: 0"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="table-line">
                            <div class="div-table-1"></div>
                            <div class="div-table-1"></div>
                            <div class="div-table-1"></div>
                            <div class="div-table-1"></div>
                            <div class="div-table-1"></div>
                            <div class="div-table-1" style="width: 100px;"></div>
                            <div class="div-table-2">
                                <div class="t-line" style="padding: 6px;">
                                    <input type="checkbox"/>
                                    <div>身份证</div>
                                    <input type="checkbox" style="margin-left: 15px;"/>
                                    <div>其他</div>
                                    <div style="margin:0 60px;margin-left: 130px;">有效期至</div>
                                    <input class="i-cb" style="margin-left: 55px;" type="checkbox"/>
                                    长期
                                </div>
                                <div class="t-line" style="display: block;height: 31px;">
                                    <div style="display: inline-block;height: 30px;font-size: 0;border-top: #000000 solid 1px;width: 100%;margin:0 -1px;text-align: left">
                                        <div class="t-line-cert" style="border: 0"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                        <div class="t-line-cert"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="div-title" style="font-size: 12px;margin-bottom: 10px;">
                        注：当受益人为被保险人法定继承人以外的指定受益人时，须在C16项勾选“是”，并填写身故受益人的姓名、国籍、职业名称、联系方式、住址地或者工作单位地址。
                    </div>

                    <%--第二页--%><br/><br/><br/>
                    <div>B.投保内容</div>
                    <div class="div-title" style="display: inline-block">
                        <div style="display: inline-block;vertical-align: top">注意:</div>
                        <div style="display: inline-block">
                            1.若主保险合同的交费周期为“趸交”，则附加“一年期”保险合同的交费周期必须为“年交”
                            <br/>2.溢交退费或留存本公司的保险费不计利息。
                        </div>
                    </div>
                    <div class="div-tab">
                        <%--表格层--%>
                        <div class="div-line">
                            <div class="div-line-con" style="width: 480px;padding: 10px 5px 10px 10px;">
                                <div class="div-block">B1.交费周期：<input type="checkbox"/>趸交<input
                                        style="margin-left: 25px;"
                                        type="checkbox"/>年交<input
                                        style="margin-left: 25px;" type="checkbox"/>半年交<input style="margin-left: 30px;"
                                                                                              type="checkbox"/>季交<input
                                        style="margin-left: 25px;" type="checkbox"/>月交
                                </div>
                                <div class="div-block tip">（半年交、季交、月交必须采用银行转账）</div>
                                <div class="div-block">B3.保险费溢交转下期：<input type="checkbox"/>同意<input
                                        style="margin-left: 20px;"
                                        type="checkbox"/>不同意<span class="tip">（未选默认为同意）</span>
                                </div>
                            </div>
                            <div class="div-line-con" style="width: 520px;">
                                <div class="div-block">
                                    <div>
                                        <div style="vertical-align: top">B2.交费方式：</div>
                                        <div>
                                            <div class="div-block"><input type="checkbox"/>首期自行交费；续期银行转账</div>
                                            <div class="div-block"><input type="checkbox"/>全部银行转账</div>
                                        </div>

                                    </div>
                                    <div class="div-block">B4.生存保险金领取方式 <input type="checkbox"/>累积生息<input
                                            type="checkbox"/>现金领取<span class="tip">（未选默认为累积生息）</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="div-title">主保险合同</div>
                    <div class="div-tab">
                        <div class="table-line" style="height: 50px;">
                            <div class="div-table-2" style="width: 210px;height: 50px;">B5.主保险合同名称
                                <div class="tip" style="display: block;line-height: 1">(请填写完整险种名称)</div>
                            </div>
                            <div class="div-table-2" style="width: 128px;height: 50px;">B6.交费期限</div>
                            <div class="div-table-2" style="width: 226px;height: 50px;">B7.保险期间
                                <div class="tip" style="display: block;line-height: 1">(养老年金产品，请直接填写B11项)</div>
                            </div>
                            <div class="div-table-2" style="width: 138px;height: 50px;">B8.基本保险金额<br/>或养老年金领取金额</div>
                            <div class="div-table-2" style="width: 145px;height: 50px;">B9.红利领取方式
                                <div class="tip" style="display: block;line-height: 1">(非分红险及保额<br/>分红险无需勾选)
                                </div>
                            </div>
                            <div class="div-table-2" style="width: 153px;height: 50px;">B10.每期标准保险费</div>
                        </div>
                        <div class="table-line">
                            <div class="div-table-2" style="width: 210px;"></div>
                            <div class="div-table-2" style="width: 128px;font-size: 14px">
                                <div style="text-align: left;display: inline-table;">
                                    <div class="div-block"><input type="checkbox"><input type="text"
                                                                                         style="border-bottom: 1px #000000 solid !important;width: 50px;">年
                                    </div>
                                    <div class="div-block"><input type="checkbox">至<input type="text"
                                                                                          style="border-bottom: 1px #000000 solid !important;width: 50px;">周岁
                                    </div>
                                    <div class="div-block"><input type="checkbox">终身</div>
                                </div>
                            </div>
                            <div class="div-table-2" style="width: 226px;font-size: 14px">
                                <div style="text-align: left;display: inline-table;">
                                    <div class="div-block"><input type="checkbox"><input
                                            style="border-bottom: 1px #000000 solid !important;width: 50px;"
                                            type="text">年
                                    </div>
                                    <div class="div-block"><input type="checkbox">至<input
                                            style="border-bottom: 1px #000000 solid !important;width: 50px;"
                                            type="text">周岁
                                    </div>
                                    <div class="div-block"><input type="checkbox">终身</div>
                                </div>
                            </div>
                            <div class="div-table-2" style="width: 138px;"></div>
                            <div class="div-table-2" style="width: 145px;">
                                <div class="div-block"><input type="checkbox">累积生息</div>
                                <div class="div-block"><input type="checkbox">现金领取</div>
                                <div class="div-block" style="font-size: 13px;color: red;">(未选择默认为积累生息)</div>
                            </div>
                            <div class="div-table-2" style="width: 153px;"></div>
                        </div>
                        <div class="table-line" style="height: 30px;">
                            <div style="padding: 5px">B11.养老年金领取</div>
                        </div>
                        <div class="table-line" style="height: 30px;">
                            <div class="div-table-2" style="width: 210px;height: 30px;">养老年金开始领取时间</div>
                            <div class="div-table-2" style="width: 128px;height: 30px;">养老年金领取方式</div>
                            <div class="div-table-2" style="width: 662px;height: 30px;">养老年金给付方式</div>
                        </div>
                        <div class="table-line" style="height: 110px;">
                            <div class="div-table-2" style="width: 210px;height: 120px;text-align: left;">
                                <div class="div-block" style="margin-left:10px; padding: 5px 0;"><input
                                        type="checkbox"/>自被保险人55周岁始
                                </div>
                                <div class="div-block" style="margin-left:10px; padding: 5px 0;"><input
                                        type="checkbox"/>自被保险人60周岁始
                                </div>
                                <div class="div-block" style="margin-left:10px; padding: 5px 0;"><input
                                        type="checkbox"/>自被保险人<input
                                        style="border-bottom: 1px #000000 solid !important;width: 50px;" type="text">周岁始
                                </div>
                            </div>
                            <div class="div-table-2" style="width: 790px;height: 120px;vertical-align: top">
                                <div style="height: 82px;width: 790px;border-bottom: #000000 1px solid;">
                                    <div style="width: 129px;height: 82px;border-right: #000000 1px solid;box-sizing: border-box;text-align: left;">
                                        <div class="div-block" style="margin-left:10px; padding: 4px 0;"><input
                                                type="checkbox"/>年领
                                        </div>
                                        <div class="div-block" style="margin-left:10px; padding: 4px 0;"><input
                                                type="checkbox"/>月领
                                        </div>
                                        <div class="div-block" style="margin-left:10px; padding: 4px 0;"><input
                                                type="checkbox"/>其他<input
                                                style="border-bottom: 1px #000000 solid !important;width: 50px;"
                                                type="text"></div>
                                    </div>
                                    <div style="width: 662px;height: 80px;box-sizing: border-box;text-align: left;">
                                        <div class="div-block" style="margin-left:10px;"><input
                                                type="checkbox"/>保证给付年限方式<span style="margin-left: 30px;">(保证给付<input
                                                style="border-bottom: 1px #000000 solid !important;width: 50px;"
                                                type="text">年)</span></div>
                                        <div class="div-block" style="margin-left:10px;"><input type="checkbox"/>身故返还本金方式
                                        </div>
                                        <div class="div-block" style="margin-left:10px;"><input
                                                type="checkbox"/>保证给付总额方式<span style="margin-left: 30px;">必须选择保证年限<input
                                                type="checkbox" style="margin-left: 5px;"/>30年<input
                                                type="checkbox" style="margin-left: 5px;"/>40年  (两者选一)</span></div>
                                        <div class="div-block" style="margin-left:10px;"><input
                                                type="checkbox"/>其他<input
                                                style="border-bottom: 1px #000000 solid !important;width: 200px;"
                                                type="text"></div>
                                    </div>
                                </div>
                                <div style="display: block;vertical-align: middle;text-align: left;margin-left: 10px;margin-top: 2px;">
                                    <input type="checkbox">一次性给付方式（如为趸交保险费，不得选择一次性给付方式）
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="div-title">附加保险合同 <span
                            style="font-size: 13px">(“保险期间”为“一年“的附加保险合同为非保证续保产品，期满前由保险公司通知是否续保）</span></div>
                    <div class="div-tab">
                        <div class="table-line" style="height: 30px;">
                            <div class="div-table-2" style="width: 338px;height: 30px;">B12.附加保险合同/可选责任名称</div>
                            <div class="div-table-2" style="width: 156px;height: 30px;">B13.交费期限</div>
                            <div class="div-table-2" style="width: 156px;height: 30px;">B14.保险期间</div>
                            <div class="div-table-2" style="width: 175px;height: 30px;">B15.基本保险金额</div>
                            <div class="div-table-2" style="width: 175px;height: 30px;">B16.每期标准保险费</div>
                        </div>
                        <div class="table-line" style="height: 40px;">
                            <div class="div-table-2" style="width: 338px;height: 40px;"></div>
                            <div class="div-table-2" style="width: 156px;height: 40px;"></div>
                            <div class="div-table-2" style="width: 156px;height: 40px;"></div>
                            <div class="div-table-2" style="width: 175px;height: 40px;"></div>
                            <div class="div-table-2" style="width: 175px;height: 40px;"></div>
                        </div>
                        <div class="table-line" style="height: 40px;">
                            <div class="div-table-2" style="width: 338px;height: 40px;"></div>
                            <div class="div-table-2" style="width: 156px;height: 40px;"></div>
                            <div class="div-table-2" style="width: 156px;height: 40px;"></div>
                            <div class="div-table-2" style="width: 175px;height: 40px;"></div>
                            <div class="div-table-2" style="width: 175px;height: 40px;"></div>
                        </div>
                        <div class="table-line" style="height: 40px;">
                            <div class="div-table-2" style="width: 338px;height: 40px;"></div>
                            <div class="div-table-2" style="width: 156px;height: 40px;"></div>
                            <div class="div-table-2" style="width: 156px;height: 40px;"></div>
                            <div class="div-table-2" style="width: 175px;height: 40px;"></div>
                            <div class="div-table-2" style="width: 175px;height: 40px;"></div>
                        </div>
                        <div class="table-line" style="height: 40px;">
                            <div class="div-table-2" style="width: 338px;height: 40px;"></div>
                            <div class="div-table-2" style="width: 156px;height: 40px;"></div>
                            <div class="div-table-2" style="width: 156px;height: 40px;"></div>
                            <div class="div-table-2" style="width: 175px;height: 40px;"></div>
                            <div class="div-table-2" style="width: 175px;height: 40px;"></div>
                        </div>
                    </div>
                    <div class="div-tab" style="margin-top: 10px;">
                        <div class="div-line">
                            <div class="div-line-con" style="width: 210px;box-sizing: border-box">B17.期交/趸交保险费总计：</div>
                            <div class="div-line-con" style="border: 0;">
                                <div>(大写）<span style="margin-left: 60px">仟</span><span
                                        style="margin-left: 55px">佰</span><span style="margin-left: 55px">拾</span><span
                                        style="margin-left: 55px">万</span><span style="margin-left: 55px">仟</span><span
                                        style="margin-left: 55px">佰</span><span style="margin-left: 55px">拾</span><span
                                        style="margin-left: 55px">元(小写)</span><span style="margin-left: 100px">元</span>
                                </div>
                            </div>
                        </div>
                        <div class="div-line">
                            <div class="div-line-con" style="width: 210px;box-sizing: border-box">B18.追加保险费：</div>
                            <div class="div-line-con" style="border: 0;">
                                <div>(大写）<span style="margin-left: 60px">仟</span><span
                                        style="margin-left: 55px">佰</span><span style="margin-left: 55px">拾</span><span
                                        style="margin-left: 55px">万</span><span style="margin-left: 55px">仟</span><span
                                        style="margin-left: 55px">佰</span><span style="margin-left: 55px">拾</span><span
                                        style="margin-left: 55px">元(小写)</span><span style="margin-left: 100px">元</span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div>C.可保资料告知</div>
                    <div class="div-title">请投保人告知被保险人的健康状况并对被保险人栏中的答案进行勾选；如保险条款中涉及对投保人承担保险责任事项、投保人栏也须填写:</div>
                    <div class="div-tab">
                        <div class="table-line" style="height: 40px;">
                            <div class="div-table-2" style="width: 636px;height: 40px;">问题及健康告知</div>
                            <div class="div-table-2" style="width: 83px;height: 40px;">被保险人</div>
                            <div class="div-table-2" style="width: 83px;height: 40px;">投保人</div>
                            <div class="div-table-2" style="width: 198px;height: 40px;text-align: left;">若“是”请说明<span
                                    class="tip">(原因、日期、医院名称及诊治结果等。)</span></div>
                        </div>
                        <div class="table-line" style="height: 53px;">
                            <div class="div-table-2"
                                 style="width: 636px;height: 53px;text-align: left;vertical-align: middle">
                                <div style="display: inline-block;vertical-align: top;">C1.</div>
                                <div style="display: inline-block;width: 600px;">
                                    您是否正在申请或已经拥有任何保险公司的保险合同？若是，请说明：承保公司、保险品种、保险金额总和、因被保险人死亡给付的保险金总和、住院每日补贴日额及保险合同生效日期。
                                </div>
                            </div>
                            <div class="div-table-2" style="width: 83px;height: 53px;"><input type="checkbox">是<input
                                    type="checkbox" style="margin-left: 10px;">否
                            </div>
                            <div class="div-table-2" style="width: 83px;height: 53px;border-right:#000000 1px solid;">
                                <input
                                        type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否
                            </div>
                        </div>
                        <div class="table-line" style="height: 53px;">
                            <div class="div-table-2"
                                 style="width: 636px;height: 53px;text-align: left;vertical-align: middle">
                                <div style="display: inline-block;vertical-align: top;">C2.</div>
                                <div style="display: inline-block;width: 600px;">
                                    您的人寿保险、人身意外或健康保险的投保申请是否曾被拒保、推迟、加费或作限制保障权益？是否有解除保险合同？是否曾向任何保险公司提出索赔申请？若“是”，请说明。
                                </div>
                            </div>
                            <div class="div-table-2" style="width: 83px;height: 53px;"><input type="checkbox">是<input
                                    type="checkbox" style="margin-left: 10px;">否
                            </div>
                            <div class="div-table-2" style="width: 83px;height: 53px;border-right:#000000 1px solid;">
                                <input
                                        type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否
                            </div>
                        </div>
                        <div class="table-line" style="height: 53px;">
                            <div class="div-table-2"
                                 style="width: 636px;height: 53px;text-align: left;vertical-align: middle">
                                <div style="display: inline-block;vertical-align: top;">C3.</div>
                                <div style="display: inline-block;width: 600px;">
                                    是否计划出国或改变居住地或工作地点？正在或试图参加私人性质飞行，或携带氧气瓶潜水、或登山、或从事危险性的运动？若“是”，请填妥相关问卷，连同此投保单一并交回本公司。
                                </div>
                            </div>
                            <div class="div-table-2" style="width: 83px;height: 53px;"><input type="checkbox">是<input
                                    type="checkbox" style="margin-left: 10px;">否
                            </div>
                            <div class="div-table-2" style="width: 83px;height: 53px;border-right:#000000 1px solid;">
                                <input
                                        type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否
                            </div>
                        </div>
                        <div class="table-line" style="height: 25px;">
                            <div class="div-table-2"
                                 style="width: 636px;height: 25px;text-align: left;vertical-align: middle">
                                <div style="display: inline-block;vertical-align: top;">C4.</div>
                                <div style="display: inline-block;width: 600px;">是否持有有效摩托车驾照？</div>
                            </div>
                            <div class="div-table-2" style="width: 83px;height: 25px;"><input type="checkbox">是<input
                                    type="checkbox" style="margin-left: 10px;">否
                            </div>
                            <div class="div-table-2" style="width: 83px;height: 25px;border-right:#000000 1px solid;">
                                <input
                                        type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否
                            </div>
                        </div>
                        <div class="table-line" style="height: 60px;">
                            <div class="div-table-2"
                                 style="width: 636px;height: 60px;text-align: left;vertical-align: middle">
                                <div style="display: inline-block;vertical-align: top;">C5.</div>
                                <div style="display: inline-block;width: 600px;">a.&nbsp;&nbsp; 是否吸烟? &nbsp;&nbsp;&nbsp;
                                    若“是”， 吸烟_________年_______支/天；&nbsp;&nbsp;若现已停止吸烟，&nbsp;&nbsp;停止吸烟原因<br/>及时间_________。b.
                                    是否饮酒？&nbsp;&nbsp;&nbsp;&nbsp;若“是”，&nbsp;&nbsp;&nbsp;饮酒________年，种类_______________，数<br/>量________（两/周）；&nbsp;&nbsp;&nbsp;&nbsp;若现已停止饮酒，停止饮酒原因及时间______________________。
                                </div>
                            </div>
                            <div class="div-table-2" style="width: 83px;height: 60px;"><input type="checkbox">是<input
                                    type="checkbox" style="margin-left: 10px;">否
                            </div>
                            <div class="div-table-2" style="width: 83px;height: 60px;border-right:#000000 1px solid;">
                                <input
                                        type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否
                            </div>
                        </div>
                        <div class="table-line" style="height: 30px;">
                            <div class="div-table-2"
                                 style="width: 636px;height: 30px;text-align: left;vertical-align: middle">
                                <div style="display: inline-block;vertical-align: top;">C6.</div>
                                <div style="display: inline-block;width: 600px;">是否曾经或正在使用镇静安眠剂、可成瘾药物、麻醉剂或接受戒毒、戒酒治疗？
                                </div>
                            </div>
                            <div class="div-table-2" style="width: 83px;height: 30px;"><input type="checkbox">是<input
                                    type="checkbox" style="margin-left: 10px;">否
                            </div>
                            <div class="div-table-2" style="width: 83px;height: 30px;border-right:#000000 1px solid;">
                                <input
                                        type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否
                            </div>
                        </div>
                        <div class="table-line" style="height: 30px;">
                            <div class="div-table-2"
                                 style="width: 636px;height: 30px;text-align: left;vertical-align: middle">
                                <div style="display: inline-block;vertical-align: top;">C7.</div>
                                <div style="display: inline-block;width: 600px;">最近六个月内是否有医生建议您服药、住院、接受诊疗、手术或其他医疗方案？
                                </div>
                            </div>
                            <div class="div-table-2" style="width: 83px;height: 30px;"><input type="checkbox">是<input
                                    type="checkbox" style="margin-left: 10px;">否
                            </div>
                            <div class="div-table-2" style="width: 83px;height: 30px;border-right:#000000 1px solid;">
                                <input
                                        type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否
                            </div>
                        </div>
                        <div class="table-line" style="height: 60px;">
                            <div class="div-table-2"
                                 style="width: 636px;height: 60px;text-align: left;vertical-align: middle">
                                <div style="display: inline-block;vertical-align: top;">C8.</div>
                                <div style="display: inline-block;width: 600px;">最近五年内，是否曾经作下列之一的检查，有无异常？
                                    核磁共振(MRI)、心电图、胃镜、纤维结肠镜、气管镜、CT、超声波、X光、眼底检查、脑电图、肝功能、肾功能、病理活检及其它特殊检查。
                                </div>
                            </div>
                            <div class="div-table-2" style="width: 83px;height: 60px;"><input type="checkbox">是<input
                                    type="checkbox" style="margin-left: 10px;">否
                            </div>
                            <div class="div-table-2" style="width: 83px;height: 60px;border-right:#000000 1px solid;">
                                <input
                                        type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否
                            </div>
                        </div>
                        <div class="table-line" style="height: 160px;">
                            <div class="div-table-2"
                                 style="width: 636px;height: 160px;text-align: left;vertical-align: middle">
                                <div style="display: inline-block;vertical-align: top;">C9.</div>
                                <div style="display: inline-block;width: 600px;">是否有下列身体残障状况：
                                    <br/>a 脊柱、胸廓、四肢、手指或手掌、足趾或足部缺损畸形、两上肢或两下肢长度不等、跛行？
                                    b 眼、耳、鼻、舌或其它颜面部软组织缺损畸形？牙齿脱落、上下颌骨缺失、颞下颌关节强直？肋骨骨折或缺失？颈部或腰部活动受限？肢体肌力下降？
                                    c 睾丸萎缩或缺失？阴茎缺失？输精管闭锁或缺失？（男性）
                                    d 子宫切除？阴道闭锁？乳房切除？（女性）
                                    e 视力、听力、语言、咀嚼、吞咽、嗅觉、触觉等功能障碍或中枢神经系统障碍？
                                    f 精神、智能障碍或性格行为异常？
                                    g 脾、肺、胃、小肠、结肠、直肠、胰腺、肝、肾、膀胱切除？心脏的结构损伤或功能障碍？输尿管闭锁或缺失？其它内脏或身体器官缺损、摘除或移植？
                                </div>
                            </div>
                            <div class="div-table-2" style="width: 83px;height: 160px;"><input type="checkbox">是<input
                                    type="checkbox" style="margin-left: 10px;">否
                            </div>
                            <div class="div-table-2" style="width: 83px;height: 160px;border-right:#000000 1px solid;">
                                <input type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否
                            </div>
                        </div>
                        <div class="table-line" style="height: 53px;">
                            <div class="div-table-2"
                                 style="width: 636px;height: 53px;text-align: left;vertical-align: middle">
                                <div style="display: inline-block;vertical-align: top;">C10.</div>
                                <div style="display: inline-block;width: 600px;">a
                                    您及您的配偶是否曾接受或试图接受与艾滋病(AIDS)有关的医疗咨询、检验或治疗，或艾滋病病毒(HIV)呈阳性反应？
                                    b 是否曾经验血而得知为乙肝表面抗原(HbsAg)阳性反应或不宜献血？
                                </div>
                            </div>
                            <div class="div-table-2" style="width: 83px;height: 53px;"><input type="checkbox">是<input
                                    type="checkbox" style="margin-left: 10px;">否
                            </div>
                            <div class="div-table-2" style="width: 83px;height: 53px;border-right:#000000 1px solid;">
                                <input
                                        type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否
                            </div>
                        </div>
                    </div>

                    <%--第三页--%>
                    <div class="div-tab" style="margin-top: 60px;">
                        <div class="table-line" style="height: 58px;">
                            <div class="div-table-2"
                                 style="width: 639px;height:58px;text-align: left;vertical-align: middle">
                                <div style="display: inline-block;vertical-align: top;">C11.</div>
                                <div style="display: inline-block;width: 600px;">若为16周岁(含)以上女性，&nbsp;&nbsp;&nbsp;请告知：&nbsp;&nbsp;&nbsp;a.目前是否怀孕？&nbsp;&nbsp;&nbsp;若是，&nbsp;&nbsp;&nbsp;已怀孕______
                                    周？
                                    b.&nbsp;&nbsp;（曾）患子宫、卵巢、乳房或其它生殖器官疾病？c.（曾）异常妊娠、阴道异常出血或接受下腹部手术？d.母亲、姐妹中是否有人（曾）患乳腺、子宫、卵巢等生殖器官恶性肿瘤？
                                </div>
                            </div>
                            <div class="div-table-2" style="width: 83px;height:58px;"><input type="checkbox">是<input
                                    type="checkbox" style="margin-left: 10px;">否
                            </div>
                            <div class="div-table-2" style="width: 83px;height:58px;border-right:#000000 1px solid;">
                                <input
                                        type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否
                            </div>
                        </div>
                        <div class="table-line" style="height: 465px;">
                            <div class="div-table-2"
                                 style="width: 639px;height: 465px;text-align: left;vertical-align: middle">
                                <div style="display: inline-block;vertical-align: top;">C12.</div>
                                <div style="display: inline-block;width: 600px;line-height: 1.3;">是否患有或曾经患有以下疾病：
                                    <br/>a.最近六个月内，是否有下列疾患或自觉症状?
                                    <br/>.不明原因皮肤出血点或瘀斑、鼻衄、反复齿龈出血?
                                    <br/>.不明原因的声嘶、关节红肿酸痛、难以愈合的舌、皮肤溃疡，持续低热，体重显著减轻(短期内5公斤以上)，痣的形态、大小或颜色改变、黄疸?
                                    <br/>.咳嗽、痰中有血块或血丝?眼睛胀痛、视力或听力明显下降、视物不清?
                                    <br/>.持续一周以上的吞咽困难、食欲不振、盗汗、腹泻、腹部不适?
                                    <br/>.紫绀、胸闷、心慌、气急、心前区疼痛、反复头痛、头晕?
                                    <br/>.小便困难、蛋白尿、血尿、便血、黑便、粘液便?
                                    <br/>b.视神经病变、白内障、青光眼、视网膜出血或剥离、近视800度以上?
                                    <br/>C.脑脊液鼻漏或耳漏、脑血管意外及后遗症、蛛网膜下腔出血、癫痫病、帕金森氏综合症、精神病、神经麻痹、心脏病、高血压、高脂血症、血管瘤、血管疾病?
                                    <br/>d.胸膜炎、肺炎、哮喘、肺结核、慢性支气管炎、支气管扩张症、肺气肿、气胸、尘肺、矽肺?
                                    <br/>e. 慢性胃肠炎、结肠炎、消化性溃疡、消化道出血穿孔、胰腺炎、肝炎、脂肪肝、肝硬化、肝脓肿、胆道结石、胆囊炎、腹膜炎、脾肿大、肛肠疾病?
                                    <br/>f.肾炎、肾病综合症、尿毒症、急性肾功能衰竭、尿路结石、尿道狭窄、肾囊肿、肾下垂、反复尿路感染、性病?
                                    <br/>g.糖尿病、垂体、甲状腺、肾上腺疾病等内分泌系统疾病?
                                    <br/>h.贫血、再生障碍性贫血、白血病、紫癜症、血友病?
                                    <br/>i.风湿热、 关节炎、类风湿性关节炎、 痛风、颈椎病、椎间盘突出症、 红斑狼疮、硬皮病、皮肌炎、重症肌无力、肌肉萎缩症、 其他结缔组织疾病?
                                    <br/>j.肿瘤(包括任何良性、恶性或尚未定性的肿瘤)、息肉、囊肿或增生物?
                                    <br/>k.先天性疾病、遗传性疾病?
                                    <br/>l.身体是否有瘢痕？
                                    <br/>m.除上述以外的其它疾病、症状或意外受伤史?
                                </div>
                            </div>
                            <div class="div-table-2" style="width: 83px;height: 465px;vertical-align: top">
                                <div style="display: block;margin-top:25px;"><input type="checkbox">是<input
                                        type="checkbox"
                                        style="margin-left: 10px;">否
                                </div>
                                <div style="display: block;margin-top:125px;"><input type="checkbox">是<input
                                        type="checkbox"
                                        style="margin-left: 10px;">否
                                </div>
                                <div style="display: block;margin-top:0;"><input type="checkbox">是<input type="checkbox"
                                                                                                         style="margin-left: 10px;">否
                                </div>
                                <div style="display: block;margin-top:15px;"><input type="checkbox">是<input
                                        type="checkbox"
                                        style="margin-left: 10px;">否
                                </div>
                                <div style="display: block;"><input type="checkbox">是<input type="checkbox"
                                                                                            style="margin-left: 10px;">否
                                </div>
                                <div style="display: block;margin-top:15px;"><input type="checkbox">是<input
                                        type="checkbox"
                                        style="margin-left: 10px;">否
                                </div>
                                <div style="display: block;margin-top:15px;"><input type="checkbox">是<input
                                        type="checkbox"
                                        style="margin-left: 10px;">否
                                </div>
                                <div style="display: block;"><input type="checkbox">是<input type="checkbox"
                                                                                            style="margin-left: 10px;">否
                                </div>
                                <div style="display: block;"><input type="checkbox">是<input type="checkbox"
                                                                                            style="margin-left: 10px;">否
                                </div>
                                <div style="display: block;margin-top:15px;"><input type="checkbox">是<input
                                        type="checkbox"
                                        style="margin-left: 10px;">否
                                </div>
                                <div style="display: block;"><input type="checkbox">是<input type="checkbox"
                                                                                            style="margin-left: 10px;">否
                                </div>
                                <div style="display: block;"><input type="checkbox">是<input type="checkbox"
                                                                                            style="margin-left: 10px;">否
                                </div>
                                <div style="display: block;"><input type="checkbox">是<input type="checkbox"
                                                                                            style="margin-left: 10px;">否
                                </div>
                            </div>
                            <div class="div-table-2"
                                 style="width: 83px;height: 465px;vertical-align: top;border-right:#000000 1px solid;">
                                <div style="display: block;margin-top:25px;"><input type="checkbox">是<input
                                        type="checkbox"
                                        style="margin-left: 10px;">否
                                </div>
                                <div style="display: block;margin-top:125px;"><input type="checkbox">是<input
                                        type="checkbox"
                                        style="margin-left: 10px;">否
                                </div>
                                <div style="display: block;margin-top:0;"><input type="checkbox">是<input type="checkbox"
                                                                                                         style="margin-left: 10px;">否
                                </div>
                                <div style="display: block;margin-top:15px;"><input type="checkbox">是<input
                                        type="checkbox"
                                        style="margin-left: 10px;">否
                                </div>
                                <div style="display: block;"><input type="checkbox">是<input type="checkbox"
                                                                                            style="margin-left: 10px;">否
                                </div>
                                <div style="display: block;margin-top:15px;"><input type="checkbox">是<input
                                        type="checkbox"
                                        style="margin-left: 10px;">否
                                </div>
                                <div style="display: block;margin-top:15px;"><input type="checkbox">是<input
                                        type="checkbox"
                                        style="margin-left: 10px;">否
                                </div>
                                <div style="display: block;"><input type="checkbox">是<input type="checkbox"
                                                                                            style="margin-left: 10px;">否
                                </div>
                                <div style="display: block;"><input type="checkbox">是<input type="checkbox"
                                                                                            style="margin-left: 10px;">否
                                </div>
                                <div style="display: block;margin-top:15px;"><input type="checkbox">是<input
                                        type="checkbox"
                                        style="margin-left: 10px;">否
                                </div>
                                <div style="display: block;"><input type="checkbox">是<input type="checkbox"
                                                                                            style="margin-left: 10px;">否
                                </div>
                                <div style="display: block;"><input type="checkbox">是<input type="checkbox"
                                                                                            style="margin-left: 10px;">否
                                </div>
                                <div style="display: block;"><input type="checkbox">是<input type="checkbox"
                                                                                            style="margin-left: 10px;">否
                                </div>
                            </div>
                        </div>
                        <div class="table-line" style="height: 40px;">
                            <div class="div-table-2"
                                 style="width: 639px;height: 40px;text-align: left;vertical-align: middle">
                                <div style="display: inline-block;vertical-align: top;">C13.</div>
                                <div style="display: inline-block;width: 600px;">
                                    直系亲属中，是否患有或曾经患有高血压、肾病、心脏病、肝炎、肝肾囊肿、肝硬化、糖尿病、精神病、癌症或早于60周岁因病身故者？
                                </div>
                            </div>
                            <div class="div-table-2" style="width: 83px;height: 40px;"><input type="checkbox">是<input
                                    type="checkbox" style="margin-left: 10px;">否
                            </div>
                            <div class="div-table-2" style="width: 83px;height: 40px;border-right:#000000 1px solid;">
                                <input
                                        type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否
                            </div>
                        </div>
                        <div class="table-line" style="height: 197px;">
                            <div class="div-table-2"
                                 style="width: 639px;height: 197px;text-align: left;vertical-align: middle">
                                <div style="display: inline-block;vertical-align: top;">C14.</div>
                                <div style="display: inline-block;width: 600px;">若为2周岁(不含)以下婴儿，请告知：
                                    <br/>a. &nbsp;&nbsp;被保险人出生时身长_________厘米，&nbsp;&nbsp;&nbsp;体重__________公斤，&nbsp;&nbsp;&nbsp;出生医院_______________，出生时留院天数_____天，如超过7天，请详细说明
                                    <br/>b. 出生时是否有早产、难产、窒息等情况？是否使用产钳等辅助器械？
                                    <br/>c. 出生时是否有抢救史？
                                    <br/>d. 是否未按要求接受预防接种？
                                    <br/>e.是否曾进行婴幼儿体检且结果异常？
                                    <br/>f.是否经常患腹痛、婴幼儿腹泻等消化系统疾病？
                                    <br/>g.是否曾患哮喘、肺炎、扁桃体炎等呼吸系统疾病？
                                    <br/>h.是否曾患疝气？
                                    i.是否曾出现“高热惊厥”？
                                </div>
                            </div>
                            <div class="div-table-2" style="width: 83px;height: 197px;"><input type="checkbox">是<input
                                    type="checkbox" style="margin-left: 10px;">否
                            </div>
                            <div class="div-table-2" style="width: 83px;height: 197px;border-right:#000000 1px solid;">
                                <input type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否
                            </div>
                        </div>
                        <div class="table-line" style="height: 28px;">
                            <div class="div-table-2"
                                 style="width: 639px;height: 28px;text-align: left;vertical-align: middle">
                                <div style="display: inline-block;vertical-align: top;">C15.</div>
                                <div style="display: inline-block;width: 600px;">是否已参加公费医疗或社会医疗保险。</div>
                            </div>
                            <div class="div-table-2" style="width: 83px;height: 28px;"><input type="checkbox">是<input
                                    type="checkbox" style="margin-left: 10px;">否
                            </div>
                            <!--  <div class="div-table-2" style="width: 83px;height: 28px;border-right:#000000 1px solid;"><input type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否</div>-->
                        </div>
                        <div class="table-line" style="height: 28px;">
                            <div class="div-table-2"
                                 style="width: 639px;height: 28px;text-align: left;vertical-align: middle">
                                <div style="display: inline-block;vertical-align: top;">C16.</div>
                                <div style="display: inline-block;width: 600px;">您是否有其他事项告知本公司？</div>
                            </div>
                            <div class="div-table-2" style="width: 83px;height: 28px;"><input type="checkbox">是<input
                                    type="checkbox" style="margin-left: 10px;">否
                            </div>
                            <div class="div-table-2" style="width: 83px;height: 28px;border-right:#000000 1px solid;">
                                <input
                                        type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否
                            </div>
                        </div>
                    </div>
                    <!-- End .第一层-->
                    <%@ include file="thirdPages.jsp" %>
                    <%@ include file="fourthPages.jsp" %>
                </div>
            </div>
        </div>
        <!-- End #content -->
    </div>
    <!-- End #main -->
</div>

</body>
</html>
