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
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" type="text/css" href="../css/insurance.css">

    <title></title>

    <script type="text/javascript">
        function jqPrints() {
            $("#prints").jqprint();
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
                                <div class="al-row-2" style="margin-top: 9px;">哈哈哈哈</div>
                            </div>
                            <div class="al-font div-sex">
                                <div class="al-row-sex">√</div>
                                <div>√</div>
                            </div>
                            <div class="al-font div-row-4">
                                <div class="al-row-date">
                                    <div>1996</div>
                                    <div style="margin-left: 50px;">02</div>
                                    <div style="margin-left: 50px;">02</div>
                                </div>
                            </div>
                            <div class="al-font div-row-2">
                                <div class="al-row-age">18</div>
                            </div>
                        </div>
                        <div class="al-line">
                            <div class="al-font div-row-5">
                                <div class="al-row-cardType">
                                    <div>√</div>
                                    <div style="margin-left: 64px">√</div>
                                    <div style="margin-left: 52px">√</div>
                                    <div style="margin-left: 65px">√</div>
                                </div>
                            </div>
                            <div class="al-font">
                                <div class="card-num">445281166666666666</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="div-tab">
                    <div class="div-line">
                        <div class="div-line-con div-row-2">
                            <div class="title">A 1.姓名</div>
                            <%--<input class="a-input" type="text" value="${insuranceOrder.insuranceOrderInsured.insuredName}"/>--%>
                        </div>
                        <div class="div-line-con div-sex">
                            <div class="title">A 2.性别</div>
                            <div>
                                <input class="i-cb" type="checkbox"/>
                                男
                                <input class="i-cb" style="margin-left: 20px; " type="checkbox"/>
                                女
                            </div>
                        </div>
                        <div class="div-line-con div-row-4">
                            <div class="title">A 3.出生日期</div>
                            <div style="padding-left: 40px;">
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
                                <input class="i-cb" type="checkbox"/>
                                护照
                                <input class="i-cb" type="checkbox"/>
                                军官证
                                <input class="i-cb" type="checkbox"/>
                                其他
                            </div>
                        </div>
                        <div class="div-line-con">
                            <div class="title">A 5.证件号码</div>
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
                            <div class="title">A 5.证件有效期</div>
                            <div>
                                <input class="i-cb" type="checkbox"/>
                                有效期至
                                <div>
                                    <input class="c-input" type="text"/>
                                    年
                                    <input class="c-input" type="text"/>
                                    月
                                    <input class="c-input" type="text"/>
                                    日
                                </div>
                                <input class="i-cb" style="margin-left: 5px" type="checkbox"/>
                                长期
                            </div>
                        </div>
                        <div class="div-line-con div-row-3">
                            <div class="title">A 11.国籍、户籍</div>
                            <input class="a-input" type="text"/>
                        </div>
                        <div class="div-line-con div-row-3">
                            <div class="title">A 9.身高</div>
                            <input class="b-input" type="text"/>
                            厘米，
                            <div class="title">体重</div>
                            <input class="b-input" type="text"/>
                            公斤
                        </div>
                    </div>
                    <div class="div-line">
                        <div class="div-line-con div-row-5">
                            <div class="title">A 5.婚姻状况</div>
                            <div>
                                <input class="i-cb" type="checkbox"/>
                                未婚
                                <input class="i-cb" type="checkbox"/>
                                已婚
                                <input class="i-cb" type="checkbox"/>
                                离异
                                <input class="i-cb" type="checkbox"/>
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
                            <div class="title">A 10.住址</div>
                            <div>
                                <input class="b-input" type="text"/>
                                省
                                <input class="b-input" type="text"/>
                                市
                                <input class="b-input" type="text"/>
                                区、县
                                <input class="d-input" type="text"/>
                            </div>
                        </div>
                        <div class="div-line-con div-postcode">
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
                            <div class="title">A 5.单位名称</div>
                            <input class="a-input" type="text"/>
                        </div>
                        <div class="div-line-con div-row-3">
                            <div class="title">A 11.职业</div>
                            <input class="a-input" type="text"/>
                        </div>
                        <div class="div-line-con div-row-3">
                            <div class="title">A 11.年收入</div>
                            <input class="a-input" type="text"/>
                        </div>
                    </div>
                    <div class="div-line">
                        <div class="div-line-con div-row-7">
                            <div class="title">A 10.单位地址</div>
                            <div>
                                <input class="b-input" type="text"/>
                                省
                                <input class="b-input" type="text"/>
                                市
                                <input class="b-input" type="text"/>
                                区、县
                                <input class="d-input" type="text"/>
                            </div>
                        </div>
                        <div class="div-line-con div-postcode">
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
                            <div class="title">A 5.职业代码</div>
                            <input class="b-input" style="border-bottom: #000000 solid 1px" type="text"/>
                            <div class="title">职业类别</div>
                        </div>
                        <div class="div-line-con div-email">
                            <div class="title">A 11.职业</div>
                            <input class="a-input" type="text"/>
                        </div>
                        <div class="div-line-con div-part-job">
                            <div class="title">A 11.是否有兼职？</div>
                            <input class="i-cb" style="margin-left: 10px" type="checkbox"/>
                            是
                            <input class="i-cb" type="checkbox"/>
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
                            <div class="title">A 1.姓名</div>
                            <%--<input class="a-input" type="text" value="${insuranceOrder.insuranceOrderInsured.insuredName}"/>--%>
                        </div>
                        <div class="div-line-con div-sex">
                            <div class="title">A 2.性别</div>
                            <div>
                                <input class="i-cb" type="checkbox"/>
                                男
                                <input class="i-cb" style="margin-left: 20px; " type="checkbox"/>
                                女
                            </div>
                        </div>
                        <div class="div-line-con div-row-4">
                            <div class="title">A 3.出生日期</div>
                            <div style="padding-left: 0;">
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
                                <input class="i-cb" type="checkbox"/>
                                护照
                                <input class="i-cb" type="checkbox"/>
                                军官证
                                <input class="i-cb" type="checkbox"/>
                                其他
                            </div>
                        </div>
                        <div class="div-line-con">
                            <div class="title">A 5.证件号码</div>
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
                            <div class="title">A 5.证件有效期</div>
                            <div>
                                <input class="i-cb" type="checkbox"/>
                                有效期至
                                <div>
                                    <input class="c-input" type="text"/>
                                    年
                                    <input class="c-input" type="text"/>
                                    月
                                    <input class="c-input" type="text"/>
                                    日
                                </div>
                                <input class="i-cb" style="margin-left: 5px" type="checkbox"/>
                                长期
                            </div>
                        </div>
                        <div class="div-line-con div-row-3">
                            <div class="title">A 11.国籍、户籍</div>
                            <input class="a-input" type="text"/>
                        </div>
                        <div class="div-line-con div-row-3">
                            <div class="title">A 9.身高</div>
                            <input class="b-input" type="text"/>
                            厘米，
                            <div class="title">体重</div>
                            <input class="b-input" type="text"/>
                            公斤
                        </div>
                    </div>
                    <div class="div-line">
                        <div class="div-line-con div-row-5">
                            <div class="title">A 5.婚姻状况</div>
                            <div>
                                <input class="i-cb" type="checkbox"/>
                                未婚
                                <input class="i-cb" type="checkbox"/>
                                已婚
                                <input class="i-cb" type="checkbox"/>
                                离异
                                <input class="i-cb" type="checkbox"/>
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
                            <div class="title">A 10.住址</div>
                            <div>
                                <input class="b-input" type="text"/>
                                省
                                <input class="b-input" type="text"/>
                                市
                                <input class="b-input" type="text"/>
                                区、县
                                <input class="d-input" type="text"/>
                            </div>
                        </div>
                        <div class="div-line-con div-postcode">
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
                            <div class="title">A 5.单位名称</div>
                            <input class="a-input" type="text"/>
                        </div>
                        <div class="div-line-con div-row-3">
                            <div class="title">A 11.职业</div>
                            <input class="a-input" type="text"/>
                        </div>
                        <div class="div-line-con div-row-3">
                            <div class="title">A 11.年收入</div>
                            <input class="a-input" type="text"/>
                        </div>
                    </div>
                    <div class="div-line">
                        <div class="div-line-con div-row-7">
                            <div class="title">A 10.单位地址</div>
                            <div>
                                <input class="b-input" type="text"/>
                                省
                                <input class="b-input" type="text"/>
                                市
                                <input class="b-input" type="text"/>
                                区、县
                                <input class="d-input" type="text"/>
                            </div>
                        </div>
                        <div class="div-line-con div-postcode">
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
                            <div class="title">A 5.职业代码</div>
                            <input class="b-input" style="border-bottom: #000000 solid 1px" type="text"/>
                            <div class="title">职业类别</div>
                        </div>
                        <div class="div-line-con div-email">
                            <div class="title">A 11.职业</div>
                            <input class="a-input" type="text"/>
                        </div>
                        <div class="div-line-con div-part-job">
                            <div class="title">A 11.是否有兼职？</div>
                            <input class="i-cb" style="margin-left: 10px" type="checkbox"/>
                            是
                            <input class="i-cb" type="checkbox"/>
                            否，如是请说明：
                        </div>
                    </div>
                </div>
                <div class="div-title">本单联系地址（如此栏空缺，将以投保人住址为准</div>
                <div class="div-tab">
                    <div class="div-line">
                        <div class="div-line-con" style="padding: 7px 5px 7px 10px;">
                            A 41. 信函寄往
                            <div style="margin-left: 80px;">省</div>
                            <div style="margin-left: 80px;">市</div>
                            <div style="margin-left: 80px;">区/县</div>
                        </div>
                        <div class="div-line-con div-postcode" style="margin-right: 120px;">
                            电话
                        </div>
                        <div class="div-line-con div-postcode">
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
                            A 41. 投保人是否需要短信服务
                            <div>
                                <input class="i-cb" type="checkbox"/>
                                需要
                                <input class="i-cb" style="margin-left: 10px; " type="checkbox"/>
                                不需要
                            </div>
                        </div>
                        <div class="div-line-con">
                            A 41. 移动电话
                        </div>
                    </div>
                </div>
                <div class="div-title" style="line-height: 1.5;"><span>身故保险金受益人：</span>（投保养老年金保险产品需要指定剩余养老保险金受益人时，请填写“投保与合同变更补充声明”告知剩余养老保险年金受益人，如未补充报告，则是剩余养老保险年金受益人与身故保险金受益人为同一人）
                    <br>说明：1.指定受益人时，同一受益顺序的受益份额合计必须等于100%。2.后一受益顺序的受益人只有在前一受益顺序所有受益人丧失或放弃收益权后才能享有受益权。3.若未填写受益份额，同一顺序的保险金受益人按照相等份额享有保险金。4.若未指定受益人，或者受益人指定不明无法确定的，保险金将作为被保险人的遗产按照《中华人名共和国继承法》的规定进行分配。5.投保无身故保险利益的保险产品是无需填写本栏，填写亦视为无效。
                </div>
                <div class="div-tab" style="height: 240px;width: 1000px;">
                    <div class="table-line">
                        <div class="div-table-1">A 44.姓名</div><div class="div-table-1">A 45.姓名</div><div class="div-table-1">A 46.收益顺序</div><div class="div-table-1">A 47.收益份额</div><div class="div-table-1">A 48.出生日期</div><div class="div-table-1">A 49.是被保险人的（关系）</div><div class="div-table-2">A 50.证件类型及号码</div>
                    </div>
                    <div class="table-line">
                        <div class="div-table-1"></div><div class="div-table-1"></div><div class="div-table-1"></div><div class="div-table-1"></div><div class="div-table-1"></div><div class="div-table-1"></div><div class="div-table-2"><div class="t-line" style="padding: 6px;">
                        <input type="checkbox"/>
                        <div>身份证</div>
                        <input type="checkbox"/>
                        <div>其他</div>
                        <div style="margin:0 60px">有效期至</div>
                        <input class="i-cb" style="margin-left: 5px" type="checkbox"/>
                        长期
                    </div>
                        <div class="t-line" style="display: block;height: 31px;">
                            <div style="display: inline-block;height: 30px;font-size: 0;border: #000000 solid 1px;width: 100%;margin-left: -2px;">
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
                        </div></div>
                    </div>
                    <div class="table-line">
                        <div class="div-table-1"></div><div class="div-table-1"></div><div class="div-table-1"></div><div class="div-table-1"></div><div class="div-table-1"></div><div class="div-table-1"></div><div class="div-table-2"><div class="t-line" style="padding: 6px;">
                        <input type="checkbox"/>
                        <div>身份证</div>
                        <input type="checkbox"/>
                        <div>其他</div>
                        <div style="margin:0 60px">有效期至</div>
                        <input class="i-cb" style="margin-left: 5px" type="checkbox"/>
                        长期
                    </div>
                        <div class="t-line" style="display: block;height: 31px;">
                            <div style="display: inline-block;height: 30px;font-size: 0;border: #000000 solid 1px;width: 100%;margin-left: -2px;">
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
                        </div></div>
                    </div>
                    <div class="table-line">
                        <div class="div-table-1"></div><div class="div-table-1"></div><div class="div-table-1"></div><div class="div-table-1"></div><div class="div-table-1"></div><div class="div-table-1"></div><div class="div-table-2"><div class="t-line" style="padding: 6px;">
                        <input type="checkbox"/>
                        <div>身份证</div>
                        <input type="checkbox"/>
                        <div>其他</div>
                        <div style="margin:0 60px">有效期至</div>
                        <input class="i-cb" style="margin-left: 5px" type="checkbox"/>
                        长期
                    </div>
                        <div class="t-line" style="display: block;height: 31px;">
                            <div style="display: inline-block;height: 30px;font-size: 0;border: #000000 solid 1px;width: 100%;margin-left: -2px;">
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
                        </div></div>
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
