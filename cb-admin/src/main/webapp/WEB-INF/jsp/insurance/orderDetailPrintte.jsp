<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <!--<script src="../js/zoomify/zoomify.js"></script>-->

    <script src="../js/zoomify/jquery-1.4.4.min.js"></script>
    <script src="../js/zoomify/jquery.jqprint-0.3.js"></script>
    <script src="../js/district/district.js" type="text/javascript"></script>
    <script src="../js/profession/profession.js" type="text/javascript"></script>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" type="text/css" href="../css/insurance.css">

    <title></title>

    <script type="text/javascript">
        function jqPrints() {
            $("#prints").jqprint();
        }
        $(document).ready(function () {
            <%--$.citySelector.getProvince(${insuranceOrder.insuranceOrderInsured.insuredProvince});--%>
            <%--var policyholderCity=  $.citySelector.getCity(${insuranceOrder.insuranceOrderInsured.policyholderCity});--%>
            <%--var policyholderDistrict=  $.citySelector.getDistrict(${insuranceOrder.insuranceOrderInsured.policyholderDistrict});--%>

            var insuredProvince= $.citySelector.getProvince(${insuranceOrder.insuranceOrderInsured.insuredProvince});
            insuredProvince=insuredProvince.replace("省","").replace("市","");
            $("#insuredProvince").html(insuredProvince);
            var insuredCity= $.citySelector.getCity(${insuranceOrder.insuranceOrderInsured.insuredCity});
            insuredCity=insuredCity.replace("市","");
            $("#insuredCity").html(insuredCity);
            var insuredDistrict= $.citySelector.getDistrict(${insuranceOrder.insuranceOrderInsured.insuredDistrict});
            insuredDistrict=insuredDistrict.replace("区","").replace("县","");
            $("#insuredDistrict").html(insuredDistrict);
            var policyholderProvince= $.citySelector.getProvince(${insuranceOrder.insuranceOrderPolicyholder.policyholderProvince});
            policyholderProvince=policyholderProvince.replace("省","").replace("市","");
            var policyholderCity=  $.citySelector.getCity(${insuranceOrder.insuranceOrderPolicyholder.policyholderCity});
            policyholderCity =policyholderCity.replace("市","");
            var policyholderDistrict=  $.citySelector.getDistrict(${insuranceOrder.insuranceOrderPolicyholder.policyholderDistrict});
            policyholderDistrict=policyholderDistrict.replace("区","").replace("县","");
            $("#policyholderProvince").html(policyholderProvince);
            $("#policyholderCity").html(policyholderCity);
            $("#policyholderDistrict").html(policyholderDistrict);

            $("#policyholderProvinces").html(policyholderProvince);
            $("#policyholderCitys").html(policyholderCity);
            $("#policyholderDistricts").html(policyholderDistrict);

            var insuredCareer =$.profession.getProfession('${insuranceOrder.insuranceOrderInsured.insuredCareer}');
            $("#insuredCareer").html(insuredCareer);
            var policyholderCareer =$.profession.getProfession('${insuranceOrder.insuranceOrderPolicyholder.policyholderCareer}');
            $("#policyholderCareer").html(policyholderCareer);

        });
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


            <div class="th-tab" id="prints">
                <div class="header">
                    <h1 style="font-size: 40px">富德生命人寿保险股份有限公司</h1>
                    <h2>FUNDE  SINO  LIFE  INSUANCE  CO.,LTD.</h2>
                    <h2>个人保险投保单（经代渠道）</h2>
                    <div class="header-contract">
                        <div class="contract" style="width: 450px;"></div>
                        <div class="contract">
                            <p>（本单所示金额单位：人民币元）</p>
                            <div class="contract-num">
                                <div style="width: 100px;vertical-align: text-bottom;margin-left: 5px;border: 0;">保险合同号码</div>
                                <div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div>
                            </div>
                            <p>本次同时投保共<input/>单，第<input/>单</p>
                        </div>
                        <div class="info">
                            <div  style="display: inline-block;vertical-align: top;">
                                投保须知:
                            </div>
                            <div style="display: inline-block">
                                1、请您在仔细阅读人身保险投保提示书、产品说明书、保险条款后用蓝、黑墨水笔填写本投保单，并在合适的回答方框内打↓。
                                <br/>2、您必须在此投保单上真实填写一切有关事实，并亲笔签名。保险合同将以此为依据，否则可能影响所签合同的法律效力。
                                <br/>3、富德生命人寿保险股份有限公司承诺未经客户同意，不会将客户信息用于人身保险公司和第三方机构的销售活动。
                            </div>
                            <div style="float: right;color: darkgreen;margin-top: 35px;font-weight: bold;">绿色栏由业务员填写。</div>
                        </div>
                        <div style="margin-left: 5px;font-weight: bold;font-size: 18px;">A.基本资料</div>
                        <div class="div-title">被保险人资料</div>
                    </div>
                </div>

                <div style="position: absolute;z-index: 20;">
                <div class="al-tab">
                    <div class="al-line">
                        <div class="al-font div-row-2">
                            <div class="al-row-2" style="margin-top: 9px;">${insuranceOrder.insuranceOrderInsured.insuredName}</div>
                        </div>
                        <div class="al-font div-sex">
                            <div class="al-row-sex">
                                <c:choose>
                                    <c:when test="${insuranceOrder.insuranceOrderInsured.insuredGender=='true'}">
                                             √
                                    </c:when>
                                    <c:otherwise>
                                        &nbsp;
                                    </c:otherwise>
                                </c:choose>
                            </div>


                            <div>
                                <c:choose>
                                    <c:when test="${insuranceOrder.insuranceOrderInsured.insuredGender=='false'}">
                                        √
                                    </c:when>
                                    <c:otherwise>
                                        &nbsp;
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
                                        <c:when test="${insuranceOrder.insuranceOrderInsured.insuredMarriage=='身份证'}">
                                            √
                                        </c:when>
                                        <c:otherwise>
                                            &nbsp;
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div style="margin-left: 64px">
                                    <c:choose>
                                        <c:when test="${insuranceOrder.insuranceOrderInsured.insuredMarriage=='护照'}">
                                            √
                                        </c:when>
                                        <c:otherwise>
                                            &nbsp;
                                        </c:otherwise>
                                    </c:choose>
                                    </div>
                                <div style="margin-left: 55px">
                                    <c:choose>
                                        <c:when test="${insuranceOrder.insuranceOrderInsured.insuredMarriage=='军官证'}">
                                            √
                                        </c:when>
                                        <c:otherwise>
                                            &nbsp;
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div style="margin-left: 80px">
                                    <c:choose>
                                        <c:when test="${insuranceOrder.insuranceOrderInsured.insuredMarriage!='军官证'&&insuranceOrder.insuranceOrderInsured.insuredMarriage!='护照'&&insuranceOrder.insuranceOrderInsured.insuredMarriage!='身份证'}">
                                            √
                                        </c:when>
                                        <c:otherwise>
                                            &nbsp;
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                        <div class="al-font" >
                            <div class="card-num"  style="letter-spacing:17.1px;margin-left: 120px;">
                                ${insuranceOrder.insuranceOrderInsured.insuredCardNo}</div>
                        </div>
                    </div>

                    <div class="al-line">
                        <div class="al-font div-row-5">
                            <c:choose>
                                <c:when test="${map.insurance_p_year!=null&&map.insurance_p_year!=''}">
                                    <div style="margin-left: 105px;float: left;">√</div>
                                    <div style="float: left;margin-left: 60px;font-size: 13px;margin-top: 2px;">${map.insurance_p_year}</div>
                                    <div style="float: left;margin-left: 30px;font-size: 13px;margin-top: 2px;">${map.insurance_p_month}</div>
                                    <div style="float: left;margin-left: 30px;font-size: 13px;margin-top: 2px;">${map.insurance_p_day}</div>
                                    <div style="margin-left: 35px;float: left;">&nbsp;</div>
                                </c:when>
                                <c:otherwise>
                                    <div style="margin-left: 105px;float: left;">&nbsp;</div>
                                    <div style="float: left;margin-left: 60px;font-size: 13px;margin-top: 2px;">&nbsp;&nbsp;&nbsp;&nbsp;</div>
                                    <div style="float: left;margin-left: 30px;font-size: 13px;margin-top: 2px;">&nbsp;&nbsp;</div>
                                    <div style="float: left;margin-left: 30px;font-size: 13px;margin-top: 2px;">&nbsp;&nbsp;</div>
                                    <div style="margin-left: 35px;float: left;">√</div>

                                </c:otherwise>
                            </c:choose>

                        </div>

                        <div class="al-font div-row-3">
                            <div style="margin-left: 120px;"> ${insuranceOrder.insuranceOrderInsured.insuredCountry}</div>
                        </div>
                        <div class="al-font" style="margin-left: 80px;">
                            ${insuranceOrder.insuranceOrderInsured.insuredHeight}
                        </div>
                        <div class="al-font" style="margin-left: 80px;">
                            ${insuranceOrder.insuranceOrderInsured.insuredBodyWeight}
                        </div>


                    </div>

                    <div class="al-line">
                        <div class="al-font div-row-5">
                            <div style="margin-left: 105px;float: left;">
                                <c:choose>
                                    <c:when test="${insuranceOrder.insuranceOrderInsured.insuredMarriage=='未婚'}">
                                        √
                                    </c:when>
                                    <c:otherwise>
                                        &nbsp;
                                    </c:otherwise>
                                </c:choose>
                                </div>
                            <div style="margin-left: 55px;float: left;">
                                <c:choose>
                                    <c:when test="${insuranceOrder.insuranceOrderInsured.insuredMarriage=='已婚'}">
                                        √
                                    </c:when>
                                    <c:otherwise>
                                        &nbsp;
                                    </c:otherwise>
                                </c:choose>
                                </div>
                            <div style="margin-left: 55px;float: left;">
                                <c:choose>
                                    <c:when test="${insuranceOrder.insuranceOrderInsured.insuredMarriage=='离异'}">
                                        √
                                    </c:when>
                                    <c:otherwise>
                                        &nbsp;
                                    </c:otherwise>
                                </c:choose>
                                </div>
                            <div style="margin-left: 60px;float: left;">
                                <c:choose>
                                    <c:when test="${insuranceOrder.insuranceOrderInsured.insuredMarriage=='丧偶'}">
                                        √
                                    </c:when>
                                    <c:otherwise>
                                        &nbsp;
                                    </c:otherwise>
                                </c:choose>
                                </div>


                        </div>
                        <div class="al-font div-row-3">
                            <div style="margin-left:130px;float: left;margin-top: 15px;">
                                ${map.insurance_q_tel}
                            </div>
                            <div style="margin-left:20px;float: left; margin-top: 15px;">
                                ${map.insurance_h_tel}

                            </div>

                        </div>

                        <div class="al-font div-row-3">
                            <div style="margin-left: 100px;float: left;">${insuranceOrder.insuranceOrderInsured.insuredMobile}</div>

                        </div>


                    </div>


                    <div class="al-line">
                        <div class="al-font div-row-7" style="margin-top: 13px;">

                            <div style="margin-left:75px;position: absolute">
                                <div id="insuredProvince"></div>
                            </div>
                            <div style="margin-left:135px;position: absolute">
                                <div id="insuredCity"></div>
                            </div>
                            <div style="margin-left:200px;position: absolute">
                                <div id="insuredDistrict"></div>
                            </div>
                            <div style="margin-left:320px;position: absolute">
                                ${insuranceOrder.insuranceOrderInsured.insuredAddress}
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
                        <div class="al-font div-row-3">
                            <div style="margin-left: 110px;"><div id="insuredCareer"></div></div>

                        </div>
                        <div class="al-font div-row-3">
                            <div  style="margin-left: 110px;">${insuranceOrder.insuranceOrderInsured.insuredIncome}</div>

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
                        <div class="al-font ">
                            <div style="float: left;margin-top:15px;margin-left: 100px;">${insuranceOrder.insuranceOrderInsured.insuredEmail}</div>
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
                            <div class="al-row-2" style="margin-top: 9px;">${insuranceOrder.insuranceOrderPolicyholder.policyholderName}</div>
                        </div>
                        <div class="al-font div-sex">
                            <div class="al-row-sex">
                                <c:choose>
                                    <c:when test="${insuranceOrder.insuranceOrderPolicyholder.policyholderGender=='true'}">
                                        √
                                    </c:when>
                                    <c:otherwise>
                                        &nbsp;
                                    </c:otherwise>
                                </c:choose>
                                </div>
                            <div>
                                <c:choose>
                                    <c:when test="${insuranceOrder.insuranceOrderPolicyholder.policyholderGender=='false'}">
                                        √
                                    </c:when>
                                    <c:otherwise>
                                        &nbsp;
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
                            <div style="margin-left: 120px;">${insuranceOrder.insuranceOrderInsured.insuredRelation}</div>
                        </div>
                    </div>

                    <div class="al-line">
                        <div class="al-font div-row-5">
                            <div class="al-row-cardType" style="margin-top: 0px;">
                                <div style="margin-left: -10px;">
                                    <c:choose>
                                        <c:when test="${insuranceOrder.insuranceOrderPolicyholder.policyholderMarriage=='身份证'}">
                                            √
                                        </c:when>
                                        <c:otherwise>
                                            &nbsp;
                                        </c:otherwise>
                                    </c:choose></div>
                                <div style="margin-left: 64px">
                                    <c:choose>
                                        <c:when test="${insuranceOrder.insuranceOrderPolicyholder.policyholderMarriage=='护照'}">
                                            √
                                        </c:when>
                                        <c:otherwise>
                                            &nbsp;
                                        </c:otherwise>
                                    </c:choose></div>
                                <div style="margin-left: 55px">
                                    <c:choose>
                                        <c:when test="${insuranceOrder.insuranceOrderPolicyholder.policyholderMarriage=='军官证'}">
                                            √
                                        </c:when>
                                        <c:otherwise>
                                            &nbsp;
                                        </c:otherwise>
                                    </c:choose></div>
                                <div style="margin-left: 80px">
                                    <c:choose>
                                        <c:when test="${insuranceOrder.insuranceOrderPolicyholder.policyholderMarriage!='军官证'&&insuranceOrder.insuranceOrderPolicyholder.policyholderMarriage!='护照'&&insuranceOrder.insuranceOrderPolicyholder.policyholderMarriage!='身份证'}">
                                            √
                                        </c:when>
                                        <c:otherwise>
                                            &nbsp;
                                        </c:otherwise>
                                </c:choose></div>
                            </div>
                        </div>
                        <div class="al-font" >
                            <div class="card-num"  style="letter-spacing:17.1px;margin-left: 120px;">${insuranceOrder.insuranceOrderPolicyholder.policyholderCardNo}</div>
                        </div>
                    </div>


                    <div class="al-line">
                        <div class="al-font div-row-5" style="margin-top: 3px;">
                    <c:choose>
                        <c:when test="${map.policy_p_year!=null&&map.policy_p_year!=''}">
                            <div style="margin-left: 110px;float: left;">√</div>
                            <div style="float: left;margin-left: 60px;font-size: 13px;margin-top: 2px;">${map.policy_p_year}</div>
                            <div style="float: left;margin-left: 30px;font-size: 13px;margin-top: 2px;">${map.policy_p_month}</div>
                            <div style="float: left;margin-left: 30px;font-size: 13px;margin-top: 2px;">${map.policy_p_day}</div>
                            <div style="margin-left: 35px;float: left;">&nbsp;</div>
                        </c:when>
                        <c:otherwise>
                            <div style="margin-left: 105px;float: left;">&nbsp;</div>
                            <div style="float: left;margin-left: 60px;font-size: 13px;margin-top: 2px;">&nbsp;&nbsp;&nbsp;&nbsp;</div>
                            <div style="float: left;margin-left: 30px;font-size: 13px;margin-top: 2px;">&nbsp;&nbsp;</div>
                            <div style="float: left;margin-left: 30px;font-size: 13px;margin-top: 2px;">&nbsp;&nbsp;</div>
                            <div style="margin-left: 35px;float: left;">√</div>
                        </c:otherwise>
                    </c:choose>
                        </div>

                        <div class="al-font div-row-3">
                            <div style="margin-left: 120px;"> ${insuranceOrder.insuranceOrderPolicyholder.policyholderCountry}</div>
                        </div>
                        <div class="al-font" style="margin-left: 80px;">
                            ${insuranceOrder.insuranceOrderPolicyholder.policyholderHeight}
                        </div>
                        <div class="al-font" style="margin-left: 85px;">
                            ${insuranceOrder.insuranceOrderPolicyholder.policyholderBodyWeight}
                        </div>


                    </div>


                    <div class="al-line">
                        <div class="al-font div-row-5">
                            <div style="margin-left: 105px;float: left;">
                                <c:choose>
                                    <c:when test="${insuranceOrder.insuranceOrderPolicyholder.policyholderMarriage=='未婚'}">
                                        √
                                    </c:when>
                                    <c:otherwise>
                                        &nbsp;
                                    </c:otherwise>
                                </c:choose>
                                </div>
                            <div style="margin-left: 55px;float: left;">
                                <c:choose>
                                    <c:when test="${insuranceOrder.insuranceOrderPolicyholder.policyholderMarriage=='已婚'}">
                                        √
                                    </c:when>
                                    <c:otherwise>
                                        &nbsp;
                                    </c:otherwise>
                                </c:choose></div>
                            <div style="margin-left: 55px;float: left;">
                                <c:choose>
                                    <c:when test="${insuranceOrder.insuranceOrderPolicyholder.policyholderMarriage=='离异'}">
                                        √
                                    </c:when>
                                    <c:otherwise>
                                        &nbsp;
                                    </c:otherwise>
                                </c:choose></div>
                            <div style="margin-left: 60px;float: left;">
                                <c:choose>
                                    <c:when test="${insuranceOrder.insuranceOrderPolicyholder.policyholderMarriage=='丧偶'}">
                                        √
                                    </c:when>
                                    <c:otherwise>
                                        &nbsp;
                                    </c:otherwise>
                                </c:choose></div>


                        </div>
                        <div class="al-font div-row-3">
                            <div style="margin-left:130px;float: left;margin-top: 15px;">
                                ${map.policy_q_tel}
                            </div>
                            <div style="margin-left:20px;float: left; margin-top: 15px;">
                                ${map.policy_h_tel}

                            </div>

                        </div>

                        <div class="al-font div-row-3">
                            <div style="margin-left: 100px;float: left;">${insuranceOrder.insuranceOrderPolicyholder.policyholderMobile}</div>

                        </div>


                    </div>

                    <div class="al-line" style="margin-top: 2px;">
                        <div class="al-font div-row-7" style="margin-top: 13px;">

                            <div style="margin-left:90px;position: absolute">
                               <div id="policyholderProvince"></div>
                            </div>
                            <div style="margin-left:180px;position: absolute">
                                <div id="policyholderCity"></div>
                            </div>
                            <div style="margin-left:250px;position: absolute">
                                <div id="policyholderDistrict"></div>
                            </div>
                            <div style="margin-left:355px;position: absolute">
                                ${insuranceOrder.insuranceOrderPolicyholder.policyholderAddress}
                            </div>

                        </div>
                        <div class="al-font div-postcode" style="margin-top: 13px;margin-right: 35px;">

                            <div style="margin-right : 10px;letter-spacing:13px;">518000</div>
                        </div>
                    </div>



                    <div class="al-line">
                        <div class="al-font div-row-5" style="margin-top: 5px">
                            <div style="margin-left: 100px;">&nbsp;&nbsp;&nbsp;&nbsp;</div>

                        </div>
                        <div class="al-font div-row-3">
                            <div style="margin-left: 110px;"><div id="policyholderCareer"></div></div>

                        </div>
                        <div class="al-font div-row-3">
                            <div  style="margin-left: 110px;">${insuranceOrder.insuranceOrderPolicyholder.policyholderIncome}</div>

                        </div>
                    </div>

                    <div class="al-line">
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
                        <div class="al-font div-postcode" style="margin-top: 15px;margin-right: 35px;">

                            <div style="margin-right : 10px;letter-spacing:13px;">518000</div>
                        </div>
                    </div>

                    <div class="al-line">
                        <div class="al-font ">
                            <div style="float: left;margin-top:15px;margin-left: 100px;">&nbsp;&nbsp;</div>
                            <div style="float: left;margin-top:15px;margin-left: 75px;">&nbsp;&nbsp;</div>
                        </div>
                        <div class="al-font ">
                            <div style="float: left;margin-top:15px;margin-left: 100px;">${insuranceOrder.insuranceOrderPolicyholder.policyholderEmail}</div>
                        </div>
                        <div class="al-font div-row-7">
                            <div style="margin-left: 160px;float: left;">&nbsp;&nbsp;&nbsp;</div>



                        </div>


                    </div>


                    <div class="al-line">
                        <div class="al-font div-row-7" style="margin-top: 38px;">

                            <div style="margin-left:0px;position: absolute">
                                <div id=""></div>
                            </div>
                            <div style="position: absolute;margin-left:130px">
                                <div id="policyholderProvinces"></div>
                            </div>
                            <div style="margin-left:225px;position: absolute">
                                <div id="policyholderCitys"></div>
                            </div>
                            <div style="margin-left:300px;position: absolute">
                                <div id="policyholderDistricts"></div>
                            </div>
                            <div style="margin-left:395px;position: absolute">
                                ${insuranceOrder.insuranceOrderPolicyholder.policyholderAddress}
                            </div>

                        </div>

                        <div class="al-font div-postcode" style="margin-top: 38px;position: absolute;margin-left: 660px;">

                            <div style="margin-left: 20px;letter-spacing:13px;">518000</div>

                        </div>
                        <div class="al-font div-postcode" style="position: absolute;margin-top: 38px;margin-left: 770px;">
                        ${insuranceOrder.insuranceOrderPolicyholder.policyholderMobile}
                    </div>

                    </div>


                    <div class="al-line">
                        <div class="al-font div-row-3" style="margin-top: 60px;float: left;">
                            <div style="margin-left: 202px;float: left;">&nbsp;</div>
                            <div style="margin-left: 55px;float: left;">&nbsp;</div>

                        </div>

                        <div class="al-font div-row-3" style="margin-left: 280px;float: left;margin-top: 38px">
                            <div style="margin-left: 100px;float: left;">${insuranceOrder.insuranceOrderPolicyholder.policyholderMobile}</div>


                        </div>


                    </div>


                </div>

                    <div class="al-tab" style="margin-top: 240px;">
                        <div class="al-line">
                            <div class="al-font">
                                <div style="position: absolute;margin-left: 5px;">张三</div>
                                <div style="position: absolute;margin-left: 95px;">李四</div>
                                <div style="position: absolute;margin-left: 185px;">rer</div>
                                <div style="position: absolute;margin-left: 275px;">trtr</div>
                                <div style="position: absolute;margin-left: 355px;">gfgf</div>
                                <div style="position: absolute;margin-left: 435px;">ytyt</div>
                            </div>
                            <div class="al-font" style="position: absolute;margin-left: 490px;margin-top: -10px;">√</div>
                            <div class="al-font" style="position: absolute;margin-left: 550px;margin-top: -10px;">√</div>
                            <div class="al-font" style="position: absolute;margin-left: 706px;margin-top: -7px;font-size: 10px;">2018-09-08</div>
                            <div class="al-font" style="position: absolute;margin-left: 800px;margin-top: -10px;">√</div>
                        </div>


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
                            <input class="i-cb" type="checkbox"  style="margin-left: 15px;" />
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
                            <input class="i-cb" type="checkbox" style="margin-left:15px; " />
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
                            <input class="i-cb" type="checkbox" style="margin-left: 10px;" />
                            有效期至
                            <div>
                                <input class="c-input" type="text"/>
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
                        <input class="b-input" type="text" style="margin-left: 10px;" />
                        厘米，
                        <div class="title">体重</div>
                        <input class="b-input" type="text" style="margin-left: 10px;" />
                        公斤
                    </div>
                </div>
                <div class="div-line">
                    <div class="div-line-con div-row-5">
                        <div class="title">A 10.婚姻状况</div>
                        <div>
                            <input class="i-cb" type="checkbox" style="margin-left: 20px;"/>
                            未婚
                            <input class="i-cb" type="checkbox" style="margin-left: 20px;"/>
                            已婚
                            <input class="i-cb" type="checkbox" style="margin-left: 20px;"/>
                            离异
                            <input class="i-cb" type="checkbox" style="margin-left: 20px;"/>
                            丧偶
                        </div>
                    </div>
                    <div class="div-line-con div-row-3">
                        <div class="title">A 11.固定电话（<span style="font-size: 1px">区号<input class="b-input"
                                                                                           type="text"/></span>)-(<input
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
                            区、县
                            <input class="d-input" type="text"/>
                        </div>
                    </div>
                    <div class="div-line-con div-postcode" style="margin-right: 40px;">
                        邮编
                        <div style="margin-left: 20px;font-size: 0">
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
                    <div class="div-line-con div-row-3" >
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
                            区、县
                            <input class="d-input" type="text"/>
                        </div>
                    </div>
                    <div class="div-line-con div-postcode"  style="margin-right: 40px;">
                        邮编
                        <div style="margin-left: 20px;font-size: 0">
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
                投保人资料（如投保人为保险人本人，可免填本栏）
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
                        <div class="title">A 24.是被保人的</div>
                        <input class="c-input" type="text" style="" />
                        <div></div>
                    </div>
                </div>

                <div class="div-line">
                    <div class="div-line-con div-row-5">
                        <div class="title">A 25.证件类型</div>
                        <div>
                            <input class="i-cb" type="checkbox"/>
                            身份证
                            <input class="i-cb" type="checkbox" style="margin-left:15px; " />
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
                            <input class="i-cb" type="checkbox" style="margin-left: 10px;" />
                            有效期至
                            <div>
                                <input class="c-input" type="text"/>
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
                        <div class="title">A 28.国籍、户籍</div>
                        <input class="a-input" type="text"/>
                    </div>
                    <div class="div-line-con div-row-3">
                        <div class="title">A 29.身高</div>
                        <input class="b-input" type="text" style="margin-left: 10px;" />
                        厘米，
                        <div class="title">体重</div>
                        <input class="b-input" type="text" style="margin-left: 10px;" />
                        公斤
                    </div>
                </div>
                <div class="div-line">
                    <div class="div-line-con div-row-5">
                        <div class="title">A 30.婚姻状况</div>
                        <div>
                            <input class="i-cb" type="checkbox" style="margin-left: 20px;"/>
                            未婚
                            <input class="i-cb" type="checkbox" style="margin-left: 20px;"/>
                            已婚
                            <input class="i-cb" type="checkbox" style="margin-left: 20px;"/>
                            离异
                            <input class="i-cb" type="checkbox" style="margin-left: 20px;"/>
                            丧偶
                        </div>
                    </div>
                    <div class="div-line-con div-row-3">
                        <div class="title">A 31.固定电话（<span style="font-size: 1px">区号<input class="b-input"
                                                                                           type="text"/></span>)-(<input
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
                            区、县
                            <input class="d-input" type="text"/>
                        </div>
                    </div>
                    <div class="div-line-con div-postcode" style="margin-right: 40px;">
                        邮编
                        <div style="margin-left: 20px;font-size: 0">
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
                            区、县
                            <input class="d-input" type="text"/>
                        </div>
                    </div>
                    <div class="div-line-con div-postcode"  style="margin-right: 40px;">
                        邮编
                        <div style="margin-left: 20px;font-size: 0">
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
                        <div class="title">A 40.其他联系电话</div>
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
                        <div style="margin-left: 20px;font-size: 0">
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


                <div class="div-title" style="line-height: 1.5;"><span>身故保险金受益人：</span>（投保养老年金保险产品需要指定剩余养老保险金受益人时，请填写“投保与合同变更补充声明”告知剩余养老保险年金受益人，如未补充报告，则是剩余养老保险年金受益人与身故保险金受益人为同一人）
                    <br>说明：1.指定受益人时，同一受益顺序的受益份额合计必须等于100%。2.后一受益顺序的受益人只有在前一受益顺序所有受益人丧失或放弃收益权后才能享有受益权。3.若未填写受益份额，同一顺序的保险金受益人按照相等份额享有保险金。4.若未指定受益人，或者受益人指定不明无法确定的，保险金将作为被保险人的遗产按照《中华人名共和国继承法》的规定进行分配。5.投保无身故保险利益的保险产品是无需填写本栏，填写亦视为无效。
                </div>
                <div class="div-tab" style="height: 240px;width: 1000px;">
                    <div class="table-line">
                        <div class="div-table-1">A 44.姓名</div>
                        <div class="div-table-1">A 45.性别</div>
                        <div class="div-table-1"><div style="display: inline-block;vertical-align: top;">A 46.</div><div style="display: inline-block;">受益<br>顺序</div></div>
                        <div class="div-table-1"><div style="display: inline-block;vertical-align: top;">A 47.</div><div style="display: inline-block;">受益<br>份额</div></div>
                        <div class="div-table-1"><div style="display: inline-block;vertical-align: top;">A 48.</div><div style="display: inline-block;">出生<br>日期</div></div>
                        <div class="div-table-1" style="width: 100px;">A 49.是被保险人的<span style="font-size: 10px;">（关系）</span></div>
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
                                <input type="checkbox"/>
                                <div>其他</div>
                                <div style="margin:0 60px">有效期至</div>
                                <input class="i-cb" style="margin-left: 5px" type="checkbox"/>
                                长期
                            </div>
                            <div class="t-line" style="display: block;height: 31px;">
                                <div style="display: inline-block;height: 30px;font-size: 0;border: #000000 solid 1px;width: 100%;margin-left: -2px;text-align: left">
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
                                <input type="checkbox"/>
                                <div>其他</div>
                                <div style="margin:0 60px">有效期至</div>
                                <input class="i-cb" style="margin-left: 5px" type="checkbox"/>
                                长期
                            </div>
                            <div class="t-line" style="display: block;height: 31px;">
                                <div style="display: inline-block;height: 30px;font-size: 0;border: #000000 solid 1px;width: 100%;margin-left: -2px;text-align: left">
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
                                <input type="checkbox"/>
                                <div>其他</div>
                                <div style="margin:0 60px">有效期至</div>
                                <input class="i-cb" style="margin-left: 5px" type="checkbox"/>
                                长期
                            </div>
                            <div class="t-line" style="display: block;height: 31px;">
                                <div style="display: inline-block;height: 30px;font-size: 0;border: #000000 solid 1px;width: 100%;margin-left: -2px;text-align: left">
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
                                    <div class="t-line-cert"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="div-title" style="font-size: 12px;margin-bottom: 10px;">注：当受益人为被保险人法定继承人以外的指定受益人时，须在C16项勾选“是”，并填写身故受益人的姓名、国籍、职业名称、联系方式、住址地或者工作单位地址。</div>
            </div>
        </div>
        <!-- End #content -->
    </div>
    <!-- End #main -->
</div>

</body>
</html>
