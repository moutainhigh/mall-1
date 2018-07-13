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
                <%--第一页--%>
                <div class="header">
                    <h1 style="font-size: 40px">富德生命人寿保险股份有限公司</h1>
                    <h2>FUNDE SINO LIFE INSUANCE CO.,LTD.</h2>
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
                                <div></div>
                            </div>
                            <p>本次同时投保共<input/>单，第<input/>单</p>
                        </div>
                        <div class="info">
                            <div style="display: inline-block;vertical-align: top;">
                                投保须知:
                            </div>
                            <div style="display: inline-block">
                                1、请您在仔细阅读人身保险投保提示书、产品说明书、保险条款后用蓝、黑墨水笔填写本投保单，并在合适的回答方框内打↓。
                                <br/>2、您必须在此投保单上真实填写一切有关事实，并亲笔签名。保险合同将以此为依据，否则可能影响所签合同的法律效力。
                                <br/>3、富德生命人寿保险股份有限公司承诺未经客户同意，不会将客户信息用于人身保险公司和第三方机构的销售活动。
                            </div>
                            <div style="float: right;color: darkgreen;margin-top: 35px;font-weight: bold;">绿色栏由业务员填写。
                            </div>
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
                        <div class="div-table-1">A 44.姓名</div>
                        <div class="div-table-1">A 45.姓名</div>
                        <div class="div-table-1">A 46.收益顺序</div>
                        <div class="div-table-1">A 47.收益份额</div>
                        <div class="div-table-1">A 48.出生日期</div>
                        <div class="div-table-1">A 49.是被保险人的（关系）</div>
                        <div class="div-table-2">A 50.证件类型及号码</div>
                    </div>
                    <div class="table-line">
                        <div class="div-table-1"></div>
                        <div class="div-table-1"></div>
                        <div class="div-table-1"></div>
                        <div class="div-table-1"></div>
                        <div class="div-table-1"></div>
                        <div class="div-table-1"></div>
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
                            </div>
                        </div>
                    </div>
                    <div class="table-line">
                        <div class="div-table-1"></div>
                        <div class="div-table-1"></div>
                        <div class="div-table-1"></div>
                        <div class="div-table-1"></div>
                        <div class="div-table-1"></div>
                        <div class="div-table-1"></div>
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
                            </div>
                        </div>
                    </div>
                    <div class="table-line">
                        <div class="div-table-1"></div>
                        <div class="div-table-1"></div>
                        <div class="div-table-1"></div>
                        <div class="div-table-1"></div>
                        <div class="div-table-1"></div>
                        <div class="div-table-1"></div>
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
                            </div>
                        </div>
                    </div>
                </div>
                <div class="div-title" style="font-size: 12px;margin-bottom: 10px;">
                    注：当受益人为被保险人法定继承人以外的指定受益人时，须在C16项勾选“是”，并填写身故受益人的姓名、国籍、职业名称、联系方式、住址地或者工作单位地址。
                </div>

                <%--第二页--%><br><br><br>
                <div>B.投保内容</div>
                <div style="display: inline-block">
                    <div style="display: inline-block;vertical-align: top">注意:</div>
                    <div style="display: inline-block">
                        1.若主保险合同的交费周期为“趸交”，则附加“年期”保险合同的交费周期必须为°年交”
                        <br/>2.溢交退费或留存本公司的保险费不计利息。
                    </div>
                </div>
                <div class="div-tab">
                    <div class="div-line">
                        <div class="div-line-con" style="width: 500px;">
                            <div class="div-block">B1.交费周期：<input type="checkbox"/>趸交<input style="margin-left: 20px;"
                                                                                            type="checkbox"/>年交<input
                                    style="margin-left: 20px;" type="checkbox"/>半年交<input style="margin-left: 20px;"
                                                                                          type="checkbox"/>季交<input
                                    style="margin-left: 20px;" type="checkbox"/>月交
                            </div>
                            <div class="div-block">（半年交、季交、月交必须采用银行转账）</div>
                            <div class="div-block">B3.保险费溢交转下期：<input type="checkbox"/>同意<input
                                    style="margin-left: 20px;"
                                    type="checkbox"/>不同意（未选默认为同意）
                            </div>
                        </div>
                        <div class="div-line-con" style="width: 500px;">
                            <div class="div-block">
                                <div>
                                    <div style="vertical-align: top">B1.交费方式：</div>
                                    <div>
                                        <div class="div-block"><input type="checkbox"/>首期自行交费；续期银行转账</div>
                                        <div class="div-block"><input type="checkbox"/>全部银行转账</div>
                                    </div>

                                </div>
                                <div class="div-block">B4.生存保险金领取方式： <input type="checkbox"/>积累生息<input
                                        type="checkbox"/>现金领取（未选默认为积累生息）
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="div-title">主保险合同</div>
                <div class="div-tab">
                    <div class="table-line" style="height: 50px;">
                        <div class="div-table-2" style="width: 210px;height: 50px;">B5.主保险合同名称
                            <div style="display: block;font-size: 13px;color: red;">(请填写完整险种名称)</div>
                        </div>
                        <div class="div-table-2" style="width: 128px;height: 50px;">B6.交费期限</div>
                        <div class="div-table-2" style="width: 226px;height: 50px;">B7.保险期间
                            <div style="display: block;font-size: 13px;color: red;">(养老年金产品，请填写B11项)</div>
                        </div>
                        <div class="div-table-2" style="width: 138px;height: 50px;">B8.基本保险金额<br/>或养老年金领取金额</div>
                        <div class="div-table-2" style="width: 145px;height: 50px;">B9.红利领取方式
                            <div style="display: block;font-size: 13px;color: red;line-height: 1">(无分红险及保额<br/>分红险无需勾选)
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
                                        style="border-bottom: 1px #000000 solid !important;width: 50px;" type="text">年
                                </div>
                                <div class="div-block"><input type="checkbox">至<input
                                        style="border-bottom: 1px #000000 solid !important;width: 50px;" type="text">周岁
                                </div>
                                <div class="div-block"><input type="checkbox">终身</div>
                            </div>
                        </div>
                        <div class="div-table-2" style="width: 138px;"></div>
                        <div class="div-table-2" style="width: 145px;">
                            <div class="div-block"><input type="checkbox">积累生息</div>
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
                        <div class="div-table-2" style="width: 128px;height: 30px;">养老年领取方式</div>
                        <div class="div-table-2" style="width: 662px;height: 30px;">养老金给付方式</div>
                    </div>
                    <div class="table-line" style="height: 110px;">
                        <div class="div-table-2" style="width: 210px;height: 110px;text-align: left;">
                            <div class="div-block" style="margin-left:10px; padding: 5px 0;"><input type="checkbox"/>自被保险人55周岁始
                            </div>
                            <div class="div-block" style="margin-left:10px; padding: 5px 0;"><input type="checkbox"/>自被保险人60周岁始
                            </div>
                            <div class="div-block" style="margin-left:10px; padding: 5px 0;"><input type="checkbox"/>自被保险人<input
                                    style="border-bottom: 1px #000000 solid !important;width: 50px;" type="text">周岁始
                            </div>
                        </div>
                        <div class="div-table-2" style="width: 790px;height: 110px;vertical-align: top">
                            <div style="height: 82px;width: 790px;border-bottom: #000000 1px solid;">
                                <div style="width: 129px;height: 82px;border-right: #000000 2px solid;box-sizing: border-box;text-align: left;">
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
                                            type="checkbox"/>30年<input
                                            type="checkbox"/>40年  (两者选一)</span></div>
                                    <div class="div-block" style="margin-left:10px;"><input type="checkbox"/>其他<input
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
                    <div class="table-line" style="height: 35px;">
                        <div class="div-table-2" style="width: 338px;height: 35px;"></div>
                        <div class="div-table-2" style="width: 156px;height: 35px;"></div>
                        <div class="div-table-2" style="width: 156px;height: 35px;"></div>
                        <div class="div-table-2" style="width: 175px;height: 35px;"></div>
                        <div class="div-table-2" style="width: 175px;height: 35px;"></div>
                    </div>
                    <div class="table-line" style="height: 35px;">
                        <div class="div-table-2" style="width: 338px;height: 35px;"></div>
                        <div class="div-table-2" style="width: 156px;height: 35px;"></div>
                        <div class="div-table-2" style="width: 156px;height: 35px;"></div>
                        <div class="div-table-2" style="width: 175px;height: 35px;"></div>
                        <div class="div-table-2" style="width: 175px;height: 35px;"></div>
                    </div>
                    <div class="table-line" style="height: 35px;">
                        <div class="div-table-2" style="width: 338px;height: 35px;"></div>
                        <div class="div-table-2" style="width: 156px;height: 35px;"></div>
                        <div class="div-table-2" style="width: 156px;height: 35px;"></div>
                        <div class="div-table-2" style="width: 175px;height: 35px;"></div>
                        <div class="div-table-2" style="width: 175px;height: 35px;"></div>
                    </div>
                    <div class="table-line" style="height: 35px;">
                        <div class="div-table-2" style="width: 338px;height: 35px;"></div>
                        <div class="div-table-2" style="width: 156px;height: 35px;"></div>
                        <div class="div-table-2" style="width: 156px;height: 35px;"></div>
                        <div class="div-table-2" style="width: 175px;height: 35px;"></div>
                        <div class="div-table-2" style="width: 175px;height: 35px;"></div>
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
                        <div class="div-line-con" style="width: 210px;box-sizing: border-box">B17.追加保险费：</div>
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
                        <div class="div-table-2" style="width: 641px;height: 40px;">问题及健康告知</div>
                        <div class="div-table-2" style="width: 83px;height: 40px;">被保险人</div>
                        <div class="div-table-2" style="width: 83px;height: 40px;">投保人</div>
                        <div class="div-table-2" style="width: 193px;height: 40px;text-align: left;">若“是”请说明(原因、日期、医院名称及诊治结果等。)</div>
                    </div>
                    <div class="table-line" style="height: 60px;">
                        <div class="div-table-2" style="width: 641px;height: 60px;text-align: left;vertical-align: middle"><div style="display: inline-block;vertical-align: top;">C1.</div><div style="display: inline-block;width: 600px;">您是否正在申请或已经拥有任何保险公司的保险合同？若是，请说明：承包公司、保险品种、保险金额总和、因被保险人死亡给付的保险金总和。住院每日补贴日额及保险合同生效日期。</div></div>
                        <div class="div-table-2" style="width: 83px;height: 60px;"><input type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否</div>
                        <div class="div-table-2" style="width: 83px;height: 60px;"><input type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否</div>
                    </div>
                    <div class="table-line" style="height: 60px;">
                        <div class="div-table-2" style="width: 641px;height: 60px;text-align: left;vertical-align: middle"><div style="display: inline-block;vertical-align: top;">C2.</div><div style="display: inline-block;width: 600px;">您的人寿保险、人身意外或健康保险的投保申请是否曾被拒保、推迟、加费、或作限制保障权益？是否有解除保险合同？是否曾向任何保险公司提出索赔申请？若“是”，请说明。</div></div>
                        <div class="div-table-2" style="width: 83px;height: 60px;"><input type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否</div>
                        <div class="div-table-2" style="width: 83px;height: 60px;"><input type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否</div>
                    </div>
                    <div class="table-line" style="height: 60px;">
                        <div class="div-table-2" style="width: 641px;height: 60px;text-align: left;vertical-align: middle"><div style="display: inline-block;vertical-align: top;">C3.</div><div style="display: inline-block;width: 600px;">是否计划出国或改变居住地或工作地点？正在试图参加私人性质飞行，或携带氧气瓶潜水、或登山、或从事危险性的运动？若“是”，请填妥相关问卷，连同此投保单一并交回本公司。</div></div>
                        <div class="div-table-2" style="width: 83px;height: 60px;"><input type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否</div>
                        <div class="div-table-2" style="width: 83px;height: 60px;"><input type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否</div>
                    </div>
                    <div class="table-line" style="height: 30px;">
                        <div class="div-table-2" style="width: 641px;height: 30px;text-align: left;vertical-align: middle"><div style="display: inline-block;vertical-align: top;">C4.</div><div style="display: inline-block;width: 600px;">是否持有有效摩托车驾照？</div></div>
                        <div class="div-table-2" style="width: 83px;height: 30px;"><input type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否</div>
                        <div class="div-table-2" style="width: 83px;height: 30px;"><input type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否</div>
                    </div>
                    <div class="table-line" style="height: 60px;">
                        <div class="div-table-2" style="width: 641px;height: 60px;text-align: left;vertical-align: middle"><div style="display: inline-block;vertical-align: top;">C5.</div><div style="display: inline-block;width: 600px;">a.是否吸烟?若“是”，吸烟__年__支/天；若现在已停止吸烟，停止吸烟原因及时间__。b.是否饮酒？若“是”，饮酒__年，种类____，数量__（两/周）；若现在已停止饮酒，停止饮酒原因及时间____。</div></div>
                        <div class="div-table-2" style="width: 83px;height: 60px;"><input type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否</div>
                        <div class="div-table-2" style="width: 83px;height: 60px;"><input type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否</div>
                    </div>
                    <div class="table-line" style="height: 30px;">
                        <div class="div-table-2" style="width: 641px;height: 30px;text-align: left;vertical-align: middle"><div style="display: inline-block;vertical-align: top;">C6.</div><div style="display: inline-block;width: 600px;">您是否曾经或正在使用麻醉剂、可成瘾药物、毒品或接受戒毒、戒酒治疗？</div></div>
                        <div class="div-table-2" style="width: 83px;height: 30px;"><input type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否</div>
                        <div class="div-table-2" style="width: 83px;height: 30px;"><input type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否</div>
                    </div>
                    <div class="table-line" style="height: 30px;">
                        <div class="div-table-2" style="width: 641px;height: 30px;text-align: left;vertical-align: middle"><div style="display: inline-block;vertical-align: top;">C7.</div><div style="display: inline-block;width: 600px;">最近六个月内是否有医生建议您服药、住院、手术或其他医疗方案？</div></div>
                        <div class="div-table-2" style="width: 83px;height: 30px;"><input type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否</div>
                        <div class="div-table-2" style="width: 83px;height: 30px;"><input type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否</div>
                    </div>
                    <div class="table-line" style="height: 60px;">
                        <div class="div-table-2" style="width: 641px;height: 60px;text-align: left;vertical-align: middle"><div style="display: inline-block;vertical-align: top;">C8.</div><div style="display: inline-block;width: 600px;">最近五年内，您是否曾经作下列之一的检查且结果异常：核磁共振(MRI)、心电图、胃镜、纤维结肠镜、气管镜、CT、超声波、X光、眼底检查、脑电图、肝功能、肾功能、病理活检及其它特殊检查？</div></div>
                        <div class="div-table-2" style="width: 83px;height: 60px;"><input type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否</div>
                        <div class="div-table-2" style="width: 83px;height: 60px;"><input type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否</div>
                    </div>
                    <div class="table-line" style="height: 180px;">
                        <div class="div-table-2" style="width: 641px;height: 180px;text-align: left;vertical-align: middle"><div style="display: inline-block;vertical-align: top;">C9.</div><div style="display: inline-block;width: 600px;">您是否有下列身体残障状况：
                            <br>a 脊柱、胸廓、四肢、手指或手掌、足趾或足部缺损畸形、两上肢或两下肢长度不等、跛行？
                            b 眼、耳、鼻、舌或其它颜面部软组织缺损畸形？牙齿脱落、上下颌骨缺失、颞下颌关节强直？肋骨骨折或缺失？颈部或腰部活动受限？肢体肌力下降？
                            c 睾丸萎缩或缺失？阴茎缺失？输精管闭锁或缺失？（男性）
                            d 子宫切除？阴道闭锁？乳房切除？（女性）
                            e 视力、听力、语言、咀嚼、吞咽、嗅觉、触觉等功能障碍或中枢神经系统障碍？
                            f 精神、智能障碍或性格行为异常？
                            g 脾、肺、胃、小肠、结肠、直肠、胰腺、肝、肾、膀胱切除？心脏的结构损伤或功能障碍？输尿管闭锁或缺失？其它内脏或身体器官缺损、摘除或移植？</div></div>
                        <div class="div-table-2" style="width: 83px;height: 180px;"><input type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否</div>
                        <div class="div-table-2" style="width: 83px;height: 180px;"><input type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否</div>
                    </div>
                    <div class="table-line" style="height: 60px;">
                        <div class="div-table-2" style="width: 641px;height: 60px;text-align: left;vertical-align: middle"><div style="display: inline-block;vertical-align: top;">C10.</div><div style="display: inline-block;width: 600px;">a 您及您的配偶是否曾接受或试图接受与艾滋病(AIDS)有关的医疗咨询、检验或治疗，或艾滋病病毒(HIV)呈阳性反应？
                            b 是否曾经验血而得知为乙肝表面抗原(HbsAg)阳性反应或不宜献血？</div></div>
                        <div class="div-table-2" style="width: 83px;height: 60px;"><input type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否</div>
                        <div class="div-table-2" style="width: 83px;height: 60px;"><input type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否</div>
                    </div>
                </div>
                    <br><br>
                <div class="div-tab" style="margin-top: 40px;">
                    <div class="table-line" style="height: 60px;">
                        <div class="div-table-2" style="width: 641px;height: 60px;text-align: left;vertical-align: middle"><div style="display: inline-block;vertical-align: top;">C11.</div><div style="display: inline-block;width: 600px;">您若为16周岁(含)以上女性，请告知：a.目前是否怀孕？怀孕___ 周数？
                            b.（曾）患子宫、卵巢、乳房或其他生殖器官疾病？c.（曾）异常妊娠、阴道异常出血或接受下腹部手术？d.母亲、姐妹中是否患有人（曾）患乳腺、子宫、卵巢等生殖器官恶性肿瘤？</div></div>
                        <div class="div-table-2" style="width: 83px;height: 60px;"><input type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否</div>
                        <div class="div-table-2" style="width: 83px;height: 60px;"><input type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否</div>
                    </div>
                    <div class="table-line" style="height: 520px;">
                        <div class="div-table-2" style="width: 641px;height: 520px;text-align: left;vertical-align: middle"><div style="display: inline-block;vertical-align: top;">C12.</div><div style="display: inline-block;width: 600px;">是否患有或曾经患有一下疾病：
                            <br>a.最近六个月内，您是否有下列疾患或自觉症状？
                            <br>.不明原因皮肤出血点或瘀斑、鼻衄、反复齿龈出血？
                            <br>.不明原因的声嘶、关节红肿酸痛、难以愈合的舌、皮肤溃疡，持续低热，体重显著减轻（短期内5公斤以上），痣的形态、大小或颜色改变、黄疸？
                            <br>.咳嗽、痰中有血块或血丝？眼睛胀痛、视力或听力明显下降、视物不清？
                            <br>.持续一周以上的吞咽困难、食欲不振、盗汗、腹部不适？
                            <br>.紫绀、胸闷、心慌、气急、心前区疼痛、反复头痛、头晕？
                            <br>.小便困难、蛋白尿、血尿、便血、黑便、粘液便？
                            <br>b 视神经病变、白内障、青光眼、视网膜出血或剥离、近视800度以上、听力下降、任何耳鼻喉疾患？
                            <br>c 脑脊液鼻漏、脑血管意外及后遗症、蛛网膜下腔出血、癫痫病、帕金森氏综合症、精神病、神经麻痹、心脏病、高血压、高脂血症、血管瘤、血管疾病？
                            <br>d 脑膜炎、肺炎、哮喘、肺结核、慢性支气管炎、支气管扩张症、肺气肿、气胸、尘肺、矽肺？
                            <br>e 慢性胃肠炎、结肠炎、消化性溃疡、消化道出血穿孔、胰腺炎、肝炎、脂肪肝、肝硬化、肝脓肿、胆道结石、胆囊炎、腹膜炎、脾肿大、肛肠疾病？
                            <br>f 肾炎、肾病综合症、急性肾功能衰竭、尿路结石、尿道狭窄、肾囊肿、肾下垂、反复尿路感染、前列腺疾病、性病？
                            <br>g 糖尿病、垂体、甲状腺、肾上腺疾病等内分泌系统疾病？
                            <br>h 贫血、再生障碍性贫血、白血病、紫癜病、血友病？
                            <br>i 风湿热、关节炎、类风湿性关节炎、痛风、颈椎病、椎间盘突出症、红斑狼疮、硬皮病、皮肌炎、重症肌无力、肌肉萎缩症、其他结缔组织疾病？
                            <br>j 肿瘤(包括任何良性、恶性或尚未定性的肿瘤)、息肉、囊肿、结节或增生物？
                            <br>k 先天性疾病、遗传性疾病？
                            <br>l 身体是否瘢痕？
                            <br>m 除上述以外的其它疾病、症状或意外受伤史？</div></div>
                        <div class="div-table-2" style="width: 83px;height: 520px;"><input type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否</div>
                        <div class="div-table-2" style="width: 83px;height: 520px;"><input type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否</div>
                    </div>
                    <div class="table-line" style="height: 40px;">
                        <div class="div-table-2" style="width: 641px;height: 40px;text-align: left;vertical-align: middle"><div style="display: inline-block;vertical-align: top;">C13.</div><div style="display: inline-block;width: 600px;">直系亲属中，是否患有或曾经患有高血压、肾病、心脏病、肝炎、肝肾囊肿、肝硬化、糖尿病、精神病、癌症或早于60周岁因病身故者？</div></div>
                        <div class="div-table-2" style="width: 83px;height: 40px;"><input type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否</div>
                        <div class="div-table-2" style="width: 83px;height: 40px;"><input type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否</div>
                    </div>
                    <div class="table-line" style="height: 220px;">
                        <div class="div-table-2" style="width: 641px;height: 220px;text-align: left;vertical-align: middle"><div style="display: inline-block;vertical-align: top;">C14.</div><div style="display: inline-block;width: 600px;">您若为2周岁(不含)以下婴儿，请告知：
                            <br>a 出生时身长__厘米(cm)，体重__公斤(kg)，出生时留院天数__天，如超过7天，请详细说明
                            <br>b 出生时是否有早产、难产、窒息等情况？是否使用产钳等辅助器械？
                            <br>c 出生时是否有抢救史？
                            <br>d 是否未按要求接受预防接种？
                            <br>e是否曾进行婴幼儿体检且结果异常？
                            <br>f是否经常患腹痛、婴幼儿腹泻等消化系统疾病？
                            <br>g是否曾患哮喘、肺炎、扁桃体炎等呼吸系统疾病？
                            <br>h是否曾患疝气？
                            <br>i是否曾出现“高热惊厥”</div></div>
                        <div class="div-table-2" style="width: 83px;height: 220px;"><input type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否</div>
                        <div class="div-table-2" style="width: 83px;height: 220px;"><input type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否</div>
                    </div>
                    <div class="table-line" style="height: 30px;">
                        <div class="div-table-2" style="width: 641px;height: 30px;text-align: left;vertical-align: middle"><div style="display: inline-block;vertical-align: top;">C13.</div><div style="display: inline-block;width: 600px;">您是否参加公费医疗或社会医疗保险？</div></div>
                        <div class="div-table-2" style="width: 83px;height: 30px;"><input type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否</div>
                        <div class="div-table-2" style="width: 83px;height: 30px;"><input type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否</div>
                    </div>
                    <div class="table-line" style="height: 30px;">
                        <div class="div-table-2" style="width: 641px;height: 30px;text-align: left;vertical-align: middle"><div style="display: inline-block;vertical-align: top;">C13.</div><div style="display: inline-block;width: 600px;">您是否有其他事项告知本公司？</div></div>
                        <div class="div-table-2" style="width: 83px;height: 30px;"><input type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否</div>
                        <div class="div-table-2" style="width: 83px;height: 30px;"><input type="checkbox">是<input type="checkbox" style="margin-left: 10px;">否</div>
                    </div>
                </div>


            </div>
        </div>
        <!-- End #content -->
    </div>
    <!-- End #main -->
</div>

</body>
</html>
